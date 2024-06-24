package ar.edu.utn.frba.dds.domain;

public class Direccion {

  private String valor;
  private boolean validada;

  public Direccion(String valor) {
    this.valor = valor;
    this.validada = false;
  }

  public boolean estaValidada() {
    return validada;
  }

  public void marcarComoValida() {
    this.validada = true;
  }

}
