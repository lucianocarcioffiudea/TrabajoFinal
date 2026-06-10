package rutasturisticas.api.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pais_id_seq")
    @SequenceGenerator(name = "pais_id_seq", sequenceName = "pais_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(name = "codigoalfa2")
    private String codigoalfa2;

    public Pais() {
    }

    public Pais(int id, String nombre, String codigoalfa2) {
        this.id = id;
        this.nombre = nombre;
        this.codigoalfa2 = codigoalfa2;
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

    public String getCodigoalfa2() {
        return codigoalfa2;
    }

    public void setCodigoalfa2(String codigoalfa2) {
        this.codigoalfa2 = codigoalfa2;
    }

    
}
