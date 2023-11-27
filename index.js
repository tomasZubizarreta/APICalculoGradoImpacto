const express = require('express');
const app = express();

// Clase CalculadoraGradoImpacto
class CalculadoraGradoImpacto {
    static getInstance() {
        if (!this.instance) {
            this.instance = new CalculadoraGradoImpacto();
        }
        return this.instance;
    }

    calcularGradoImpacto(tiempoResolucionIncidente, cantIncidentesNoResueltos, cnf, totalPersonasImpactadas) {
        const resultado = (tiempoResolucionIncidente + cantIncidentesNoResueltos * cnf) * totalPersonasImpactadas;
        return resultado;
    }
}

// Clase EntidadValor
class EntidadValor {
    constructor(entidad_id, resultadoGradoImpacto) {
        this.entidad_id = entidad_id;
        this.resultadoGradoImpacto = resultadoGradoImpacto;
    }
}

// Clase ListadoValores
class ListadoValores {
    constructor(valoresPorEntidad) {
        this.valoresPorEntidad = valoresPorEntidad;
    }
}

// Clase RepositorioResultados
class RepositorioResultados {
    constructor() {
        this.entidadValorList = [];
    }

    obtenerImpactoEntidadDeId(id) {
        const buscado = this.entidadValorList.find(entidadValor => entidadValor.entidad_id === id);
        return buscado || null;
    }

    guardarResultado(resultado) {
        const entidadValor = this.obtenerImpactoEntidadDeId(resultado.entidad_id);
        if (!entidadValor) {
            this.entidadValorList.push(resultado);
        } else {
            entidadValor.resultadoGradoImpacto = resultado.resultadoGradoImpacto;
        }
    }

    obtenerTodosLosResultadosOrdenados() {
        this.entidadValorList.sort((a, b) => b.resultadoGradoImpacto - a.resultadoGradoImpacto);
        return this.entidadValorList;
    }
}

// Controlador GradoImpactoController
const gradoImpactoController = {
    calculateImpactGet: (req, res) => {
        const resultados = RepositorioResultados.getInstance().obtenerTodosLosResultadosOrdenados();
        res.json(resultados);
    },

    calculateImpactPost: (req, res) => {
        const listado = new ListadoValores(req.body.valoresPorEntidad);
        listado.valoresPorEntidad.forEach(valoresFormula => {
            const gradoImpacto = CalculadoraGradoImpacto.getInstance().calcularGradoImpacto(
                valoresFormula.tiempoResolucionIncidente,
                valoresFormula.cantIncidentesNoResueltos,
                valoresFormula.cnf,
                valoresFormula.totalPersonasImpactadas
            );
            RepositorioResultados.getInstance().guardarResultado(new EntidadValor(valoresFormula.entidad_id, gradoImpacto));
        });
        res.send('Valores de entidades recibidos correctamente.');
    }
};

// Rutas
app.get('/api/calcularImpacto', gradoImpactoController.calculateImpactGet);
app.post('/api/calcularImpacto', express.json(), gradoImpactoController.calculateImpactPost);

// Iniciar el servidor en el puerto especificado (por ejemplo, 3000)
const PORT = 8080;
app.listen(PORT, () => {
    console.log(`Servidor API de Cálculo de Grado de Impacto iniciado en el puerto ${PORT}`);
});