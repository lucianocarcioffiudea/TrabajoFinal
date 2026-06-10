package rutasturisticas.api.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "ruta", uniqueConstraints = {@UniqueConstraint(columnNames = {"idciudad", "nombre"})})
public class Ruta {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO, generator = "ruta_id_seq")
    @SequenceGenerator(name = "ruta_id_seq", sequenceName = "ruta_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    // Muchas rutas pertenecen a una Ciudad
    @ManyToOne
    @JoinColumn(name = "idciudad", nullable = false, foreignKey = @ForeignKey(name = "fkruta_idciudad"))
    private Ciudad ciudad; // 'ciudad' o 'ciudad' para evitar colisiones visuales, mapeado a idciudad

    // Muchas rutas tienen un Tipo de ruta
    @ManyToOne
    @JoinColumn(name = "idtipo", nullable = false, foreignKey = @ForeignKey(name = "fkruta_idtipo"))
    private Tipo tipo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    public Ruta() {
    }

    public Ruta(int id, String nombre, Ciudad ciudad, Tipo tipo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
