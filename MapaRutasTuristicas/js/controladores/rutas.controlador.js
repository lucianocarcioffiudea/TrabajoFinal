app.controller("RutaControlador",
    function (
        $scope,
        CiudadServicio,
        RutaServicio,
        ParadaServicio,
        TipoServicio,
        MapaServicio
    ) {

        $scope.ciudades = [];
        $scope.tipos = [];
        $scope.rutas = [];
        $scope.paradas = [];

        $scope.ciudadSeleccionada = null;
        $scope.rutaSeleccionada = null;

        $scope.frmRuta = {};
        $scope.frmParada = {};

        $scope.inicializar = () => {
            CiudadServicio.listar().then(ciudadesJSON => {
                $scope.ciudades =
                    ciudadesJSON.map(c => new Ciudad(c));
            });
            TipoServicio.listar().then(tiposJSON => {
                $scope.tipos =
                    tiposJSON.map(t => new Tipo(t));
            });
        };

        $scope.seleccionarCiudad = (ciudad) => {
            $scope.ciudadSeleccionada = ciudad;
            $scope.rutaSeleccionada = null;
            $scope.rutas = [];
            $scope.paradas = [];
            $scope.frmRuta = {};
            $scope.frmParada = {};
            RutaServicio.listarPorCiudad(ciudad.id)
                .then(rutasJSON => {
                    $scope.rutas =
                        rutasJSON.map(r => new Ruta(r));
                });
        };

        $scope.seleccionarRuta = (ruta) => {
            $scope.rutaSeleccionada = ruta;
            $scope.frmParada = {};
            $scope.cargarParadasRuta(ruta.id);
        };

        $scope.cargarParadasRuta = (idRuta) => {
            ParadaServicio.listarPorRuta(idRuta)
                .then(paradasJSON => {
                    $scope.paradas =
                        paradasJSON
                            .map(p => new Parada(p))
                            .sort((a, b) => a.orden - b.orden);
                    setTimeout(() => {
                        MapaServicio.actualizar(
                            $scope.paradas
                        );
                    }, 100);
                });

        };

        $scope.guardarRuta = () => {
            let ruta = {
                id: $scope.frmRuta.id || 0,
                nombre: $scope.frmRuta.nombre,
                descripcion: $scope.frmRuta.descripcion || "",
                ciudad: {
                    id: $scope.ciudadSeleccionada.id
                },
                tipo: {
                    id: $scope.frmRuta.tipo.id
                }
            };
            if (ruta.id > 0) {
                RutaServicio.modificar(ruta)
                    .then(() => {
                        $scope.seleccionarCiudad($scope.ciudadSeleccionada);
                        $scope.frmRuta = {};
                    });
            } else {
                RutaServicio.agregar(ruta)
                    .then(() => {
                        $scope.seleccionarCiudad($scope.ciudadSeleccionada);
                        $scope.frmRuta = {};
                    });
            }
        };

        $scope.editarRuta = (ruta) => {
            $scope.frmRuta =
                angular.copy(ruta);
        };

        $scope.eliminarRuta = (id) => {
            if (confirm("¿Desea eliminar esta ruta?")) {
                RutaServicio.eliminar(id)
                    .then(() => {
                        $scope.seleccionarCiudad(
                            $scope.ciudadSeleccionada
                        );
                    });
            }
        };

        $scope.nuevaRuta = () => {
            $scope.frmRuta = {};
        };

        $scope.guardarParada = () => {

            let parada = {
                id: $scope.frmParada.id || 0,
                orden: Number($scope.frmParada.orden),
                nombre: $scope.frmParada.nombre,
                latitud: Number($scope.frmParada.latitud),
                longitud: Number($scope.frmParada.longitud),
                tiempo: Number($scope.frmParada.tiempo),
                descripcion: $scope.frmParada.descripcion,
                ruta: {
                    id: $scope.rutaSeleccionada.id
                }
            };
            let promesa;
            if (parada.id > 0) {
                promesa = ParadaServicio.modificar(parada);
            } else {
                promesa = ParadaServicio.agregar(parada);
            }
            promesa.then(() => {
                $scope.cargarParadasRuta($scope.rutaSeleccionada.id);
                $scope.frmParada = {};
            });

        };

        $scope.editarParada = (parada) => {
            $scope.frmParada =
                angular.copy(parada);
        };

        $scope.eliminarParada = (id) => {
            if (confirm("¿Desea eliminar esta parada?")) {
                ParadaServicio.eliminar(id)
                    .then(() => {
                        $scope.cargarParadasRuta(
                            $scope.rutaSeleccionada.id
                        );
                    });
            }
        };

        $scope.nuevaParada = () => {
            $scope.frmParada = {};
        };

        $scope.inicializar();
    });