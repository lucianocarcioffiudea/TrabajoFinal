app.service("MapaServicio", function () {

    let mapa = null;
    let capaMarcadores = null;

    this.actualizar = (paradas) => {
        if (!paradas || paradas.length === 0)
            return;
        if (!mapa) {
            mapa = L.map("mapa").setView(
                [
                    paradas[0].latitud,
                    paradas[0].longitud
                ],
                13
            );
            L.tileLayer(
                "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
                {
                    attribution: "&copy; OpenStreetMap"
                }
            ).addTo(mapa);
        }
        if (capaMarcadores) {
            mapa.removeLayer(capaMarcadores);
        }
        capaMarcadores = L.layerGroup().addTo(mapa);
        let coordenadas = [];
        paradas.forEach(parada => {
            let punto = [
                parada.latitud,
                parada.longitud
            ];
            coordenadas.push(punto);
            L.marker(punto)
                .addTo(capaMarcadores)
                .bindPopup(
                    parada.orden +
                    " - " +
                    parada.nombre
                );
        });
        L.polyline(
            coordenadas,
            {
                color: "blue"
            }
        ).addTo(capaMarcadores);
        mapa.fitBounds(coordenadas);
    };
});