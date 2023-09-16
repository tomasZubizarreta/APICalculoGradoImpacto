package com.grupo15.API.Calculo.Grado.Impacto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class GradoImpactoController {
    private int resultado;

    @GetMapping("/calcularImpacto {entidad} {tiempoResolucionIncidente} {cantIncidentesNoResueltos} {cnf} {totalPersonasImpactadas}")
    public ResponseEntity<Integer> calculateImpactGet(@RequestBody String entidad, Integer tiempoResolucionIncidente, Integer cantIncidentesNoResueltos, Integer cnf, Integer totalPersonasImpactadas) {
        resultado = (tiempoResolucionIncidente + cantIncidentesNoResueltos * cnf) * totalPersonasImpactadas;
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/calcularImpacto")
    public ResponseEntity<Integer> calculateImpactPost() {
        return ResponseEntity.ok(resultado);
    }
}

