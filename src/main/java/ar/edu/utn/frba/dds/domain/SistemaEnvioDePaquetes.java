package ar.edu.utn.frba.dds.domain;

public interface SistemaEnvioDePaquetes {
  void envioNormal(String codigo, Direccion direccion);
  String envioSeguro(String codigo, Direccion direccion);
  void envioSuperSeguro(String codigo, Direccion direccion, Runnable callback);
}
