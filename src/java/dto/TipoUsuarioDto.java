package dto;

public class TipoUsuarioDto {

    private Integer id;
    private String abreviatura;
    private String descripcion;
    private Boolean habilitado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String toString() {
        return "TipoUsuarioDto{" + "id=" + id + ", abreviatura=" + abreviatura + ", descripcion=" + descripcion + ", habilitado=" + habilitado + '}';
    }

}
