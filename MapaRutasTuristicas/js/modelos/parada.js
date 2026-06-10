class Parada {

    constructor(json) {
        this.id = json ? (json.id || 0) : 0;
        this.nombre = json ? json.nombre : "";
        this.orden = json ? (json.orden || 0) : 0;
        this.latitud = json ? json.latitud : 0;
        this.longitud = json ? json.longitud : 0;
        this.tiempo = json ? json.tiempo : "";
        this.descripcion = json ? json.descripcion : "";
        this.ruta = json ? json.ruta : null;
    }
}