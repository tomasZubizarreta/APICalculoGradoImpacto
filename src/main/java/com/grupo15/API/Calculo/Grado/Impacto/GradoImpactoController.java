package com.grupo15.API.Calculo.Grado.Impacto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RestController
@RequestMapping("/api")
public class GradoImpactoController {
    private int resultado;

    @GetMapping("/calcularImpacto")
    public List<EntidadValor> calculateImpactGet() {
        return RepositorioResultados.getInstance().obtenerTodosLosResultadosOrdenados();
    }

    @PostMapping("/calcularImpacto")
    public ResponseEntity<String> calculateImpactPost(@RequestBody ListadoValores listado) throws InterruptedException {
        for (ValoresFormula valoresFormula:listado.getValoresPorEntidad()) {
            double gradoImpacto = CalculadoraGradoImpacto.getInstance().calcularGradoImpacto(valoresFormula.tiempoResolucionIncidente,
                    valoresFormula.cantIncidentesNoResueltos,
                    valoresFormula.cnf,
                    valoresFormula.totalPersonasImpactadas);
            RepositorioResultados.getInstance().guardarResultado(new EntidadValor(valoresFormula.entidad_id, gradoImpacto));
        }

        return ResponseEntity.ok("Valores de entidades recibidos correctamente.");
    }
}

