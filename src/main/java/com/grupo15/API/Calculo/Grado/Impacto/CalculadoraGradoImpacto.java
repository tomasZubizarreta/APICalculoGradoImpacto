package com.grupo15.API.Calculo.Grado.Impacto;


public class CalculadoraGradoImpacto {
  private static CalculadoraGradoImpacto instance = null;

  private CalculadoraGradoImpacto(){
  }

  public static CalculadoraGradoImpacto getInstance(){
    if(instance == null){
      instance = new CalculadoraGradoImpacto();
    }
    return instance;
  }

  public double calcularGradoImpacto(int tiempoResolucionIncidente, int cantIncidentesNoResueltos, double cnf, int totalPersonasImpactadas){
    double resultado = (tiempoResolucionIncidente + cantIncidentesNoResueltos * cnf) * totalPersonasImpactadas;
    return resultado;
  }
}
