package com.secondpartial.platformreplica.enums;

public enum SendStatus {
    TO_SEND("Por Enviar"),
    SENDED("Enviado"),
    GRADED("Calificado");

    private String sendStatus;

    private SendStatus(String SendStatus) {
        this.sendStatus = SendStatus;
    }

    public static SendStatus getSendStatus(String sendStatus) {
        switch (sendStatus) {
            case "Por Enviar":
                return SendStatus.TO_SEND;
            case "Enviado":
                return SendStatus.SENDED;
            case "Calificado":
                return SendStatus.GRADED;
            default:
                return null;
        }
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

}
