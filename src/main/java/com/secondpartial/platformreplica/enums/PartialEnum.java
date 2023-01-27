package com.secondpartial.platformreplica.enums;

public enum PartialEnum {
    FIRST("Primer Parcial"),
    SECOND("Segundo Parcial"),
    APE("Ape"),
    CIERRE("Cierre");

    private String parcial;

    private PartialEnum(String parcial) {
        this.parcial = parcial;
    }

    public static PartialEnum getPartialEnum(String partial) {
        switch (partial) {
            case "Primer Parcial":
                return PartialEnum.FIRST;
            case "Segundo Parcial":
                return PartialEnum.SECOND;
            case "Ape":
                return PartialEnum.APE;
            case "Cierre":
                return PartialEnum.CIERRE;
            default:
                return null;
        }
    }

    public String getParcial() {
        return parcial;
    }

    public void setParcial(String parcial) {
        this.parcial = parcial;
    }

}
