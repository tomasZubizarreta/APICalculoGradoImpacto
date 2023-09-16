package com.grupo15.API.Calculo.Grado.Impacto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo15.API.Calculo.Grado.Impacto.Resultado;



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
        return ResponseEntity.ok(resultado);
    }
}

