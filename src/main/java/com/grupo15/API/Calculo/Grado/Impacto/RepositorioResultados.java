package com.grupo15.API.Calculo.Grado.Impacto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepositorioResultados {

  private static RepositorioResultados instance = null;
  private List<EntidadValor> entidadValorList = new ArrayList<>();

  private RepositorioResultados(){
  }

  public static RepositorioResultados getInstance(){
    if(instance == null){
      instance = new RepositorioResultados();
    }
    return instance;
  }

  public EntidadValor obtenerImpactoEntidadDeId(long id){
    Optional<EntidadValor> buscado = entidadValorList.stream().filter(entidadValor -> entidadValor.getEntidad_id() == id).findFirst();
    if (buscado.isPresent()){
      return buscado.get();
    } else {
      return null;
    }
  }
  public void guardarResultado(EntidadValor resultado){
    EntidadValor entidadValor = obtenerImpactoEntidadDeId(resultado.getEntidad_id());
    if (entidadValor == null){
      entidadValorList.add(resultado);
    } else {
      entidadValor.setResultadoGradoImpacto(resultado.getResultadoGradoImpacto());
    }
  }

  public List<EntidadValor> obtenerTodosLosResultadosOrdenados(){
    entidadValorList.sort(Comparator.comparingDouble(EntidadValor::getResultadoGradoImpacto).reversed());
    return entidadValorList;
  }
}
