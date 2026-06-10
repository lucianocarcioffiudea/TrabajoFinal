class Ruta {

    constructor(json) {
        this.id = json ? (json.id || 0) : 0;
        this.nombre = json ? json.nombre : "";
        this.descripcion = json ? json.descripcion : "";
        this.ciudad = json ? json.ciudad : null;
        this.tipo = json ? json.tipo : null;
    }
}