package dto;

import java.io.Serializable;
import util.Table;

@Table(name = "departamento")
public class DepartamentoDto implements Serializable {

    private Integer id;
    
    private String codigo;
    
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "DepartamentoDto{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }

}
