package ar.edu.utn.frba.dds.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductoTest {

  private SistemaEnvioDePaquetes sistemaEnvio;
  @InjectMocks
  private Producto unProducto;

  @BeforeEach
  public void setUp() {
    Direccion direccion = new Direccion("Cabildo 3289");
    this.sistemaEnvio = mock(SistemaEnvioDePaquetes.class);
    this.unProducto = new Producto("AR-120", direccion, sistemaEnvio);
  }

  @Test
  public void verificarEnviarNormalSinRetornoYSinEfecto() {

    doAnswer(invocation -> {
      Direccion dirAux = (Direccion) invocation.getArgument(1);
      dirAux.marcarComoValida();
      return null;
    }).when(sistemaEnvio).envioNormal(anyString(), any(Direccion.class));

    unProducto.enviarNormal();

    verify(sistemaEnvio,times(1))
        .envioNormal(anyString(),any(Direccion.class));

  }

  @Test
  public void verificarEnviarSeguroConRetornoYSinEfecto() {

    when(sistemaEnvio.envioSeguro(anyString(), any(Direccion.class))).thenReturn("CODSEG01");

    Assertions.assertEquals("CODSEG01", unProducto.enviarSeguro());

    verify(sistemaEnvio,times(1))
        .envioSeguro(anyString(),any(Direccion.class));

  }

  @Test
  public void verificarEnviarSeguroConRetornoYConEfecto() {

    when(sistemaEnvio.envioSeguro(any(), any())).thenAnswer(invocation -> {
      Direccion dirAux = (Direccion) invocation.getArgument(1);
      dirAux.marcarComoValida();
      return "CODSEG01";
    });

    Assertions.assertEquals("CODSEG01", unProducto.enviarSeguro());
    Assertions.assertTrue(unProducto.getDireccion().estaValidada());

    verify(sistemaEnvio,times(1))
        .envioSeguro(anyString(),any(Direccion.class));

  }

  @Test
  public void verificarEnviarMuySeguroSinRetornoYConEfecto() {

    doAnswer(invocation -> {
      Direccion dirAux = (Direccion) invocation.getArgument(1);
      dirAux.marcarComoValida();
      Runnable callback = (Runnable) invocation.getArgument(2);
      callback.run();
      return null;
    }).when(sistemaEnvio).envioSuperSeguro(anyString(),any(Direccion.class),any(Runnable.class));

    unProducto.enviarMuySeguro(() -> {
      Assertions.assertTrue(unProducto.getDireccion().estaValidada());
    });

    verify(sistemaEnvio,times(1))
        .envioSuperSeguro(anyString(),any(Direccion.class),any(Runnable.class));

  }

}
