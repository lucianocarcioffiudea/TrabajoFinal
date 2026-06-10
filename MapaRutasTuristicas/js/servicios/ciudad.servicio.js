app.service("CiudadServicio", function ($http) {

    const URL = "http://localhost:8080/api/ciudades/";

    this.listar = () => {
        return $http.get(URL).then(respuesta => respuesta.data);
    }

    this.obtener = (id) => {
        return $http.get(`${URL}${id}`).then(respuesta => respuesta.data);
    }

    this.agregar = (ciudad) => {
        return $http.post(URL, ciudad).then(respuesta => respuesta.data);
    }

    this.modificar = (ciudad) => {
        return $http.put(URL, ciudad).then(respuesta => respuesta.data);
    }

    this.eliminar = (id) => {
        return $http.delete(`${URL}${id}`).then(respuesta => respuesta.data);
    }

});