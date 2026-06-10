app.service("TipoServicio", function($http) {

    const URL = "http://localhost:8080/api/tipos/";

    this.listar = () => {
        return $http.get(URL)
            .then(respuesta => respuesta.data);
    }

    this.obtener = (id) => {
        return $http.get(`${URL}${id}`)
            .then(respuesta => respuesta.data);
    }

});