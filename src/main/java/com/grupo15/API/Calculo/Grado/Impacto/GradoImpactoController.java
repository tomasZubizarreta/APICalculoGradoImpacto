package com.grupo15.API.Calculo.Grado.Impacto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@RequestMapping("/api")
public class GradoImpactoController {
    private Resultado resultado;

    @GetMapping("/calcularImpacto {entidad} {tiempoResolucionIncidente} {cantIncidentesNoResueltos} {cnf} {totalPersonasImpactadas}")
    public ResponseEntity<Void> calculateImpactGet(@RequestBody String entidad, Integer tiempoResolucionIncidente, Integer cantIncidentesNoResueltos, Integer cnf, Integer totalPersonasImpactadas) {
        resultado.resultado = (tiempoResolucionIncidente + cantIncidentesNoResueltos * cnf) * totalPersonasImpactadas;
        resultado.entidadResultado = entidad;
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @PostMapping("/calcularImpacto")
    public ResponseEntity<Resultado> calculateImpactPost() {
    @GetMapping("/calcularImpacto")
    public List<EntidadValor> calculateImpactGet() {
        return RepositorioResultados.getInstance().obtenerTodosLosResultadosOrdenados();
    }

    @PostMapping("/calcularImpacto")
    public ResponseEntity<Integer> calculateImpactPost(@RequestBody ListadoValores listado) {
        for (ValoresFormula valoresFormula:listado.getValoresPorEntidad()) {
            double gradoImpacto = CalculadoraGradoImpacto.getInstance().calcularGradoImpacto(valoresFormula.tiempoResolucionIncidente,
                valoresFormula.cantIncidentesNoResueltos,
                valoresFormula.cnf,
                valoresFormula.totalPersonasImpactadas);
            RepositorioResultados.getInstance().guardarResultado(new EntidadValor(valoresFormula.entidad_id, gradoImpacto));
        }
        return ResponseEntity.ok(resultado);
    }
}

