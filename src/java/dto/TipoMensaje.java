package dto;

public enum TipoMensaje {

    INFO("noti-info"),
    SUCCESS("noti-success"),
    WARNING("noti-warning"),
    ERROR("noti-error");
    
    public final String className;
    
    private TipoMensaje(String className) {
        this.className = className;
    }
}
