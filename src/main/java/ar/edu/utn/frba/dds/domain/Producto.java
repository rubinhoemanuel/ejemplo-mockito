package ar.edu.utn.frba.dds.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Producto {

  private String codigo;
  private Direccion direccion;
  private SistemaEnvioDePaquetes sistemaEnvio;

  public void enviarNormal() {
    this.sistemaEnvio.envioNormal(this.codigo, this.direccion);
  }

  public String enviarSeguro() {
    return this.sistemaEnvio.envioSeguro(this.codigo, this.direccion);
  }

  public void enviarMuySeguro(Runnable callback) {
    this.sistemaEnvio.envioSuperSeguro(this.codigo, this.direccion, callback);
  }

}
