app.service("RutaServicio", function($http) {

    const URL = "http://localhost:8080/api/rutas/";

    this.listar = () => {
        return $http.get(URL).then(respuesta => respuesta.data);
    }

    this.obtener = (id) => {
        return $http.get(`${URL}${id}`).then(respuesta => respuesta.data);
    }

    this.listarPorCiudad = (idCiudad) => {
        return $http.get(`${URL}ciudad/${idCiudad}`)
            .then(respuesta => respuesta.data);
    }

    this.agregar = (ruta) => {
        return $http.post(URL, ruta).then(respuesta => respuesta.data);
    }

    this.modificar = (ruta) => {
        return $http.put(URL, ruta).then(respuesta => respuesta.data);
    }

    this.eliminar = (id) => {
        return $http.delete(`${URL}${id}`).then(respuesta => respuesta.data);
    }

});