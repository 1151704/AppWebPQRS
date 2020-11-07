package dto;

import java.io.Serializable;
import util.Column;
import util.Table;

@Table(name = "municipio")
public class MunicipioDto implements Serializable {

    private Integer id;
    
    @Column(name = "fk_departamento")
    private Integer fkDepartamento;
    
    private String codigo;
    
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkDepartamento() {
        return fkDepartamento;
    }

    public void setFkDepartamento(Integer fkDepartamento) {
        this.fkDepartamento = fkDepartamento;
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
        return "MunicipioDto{" + "id=" + id + ", fkDepartamento=" + fkDepartamento + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }

}
