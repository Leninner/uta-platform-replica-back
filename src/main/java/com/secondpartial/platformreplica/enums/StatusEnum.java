package com.secondpartial.platformreplica.enums;

public enum StatusEnum {
    AVAILABLE("Activo"),
    UNAVAILABLE("Inactivo");

    private String status;

    private StatusEnum(String status) {
        this.status = status;
    }

    public static StatusEnum getStatusEnum(String status) {
        switch (status) {
            case "Activo":
                return StatusEnum.AVAILABLE;
            case "Inactivo":
                return StatusEnum.UNAVAILABLE;
            default:
                return null;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
