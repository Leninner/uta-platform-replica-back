package com.secondpartial.platformreplica.enums;

public enum RolEnum {
  ADMIN, STUDENT, TEACHER;

  public static RolEnum getRolEnum(String rol) {
    switch (rol) {
      case "ADMIN":
        return RolEnum.ADMIN;
      case "STUDENT":
        return RolEnum.STUDENT;
      case "TEACHER":
        return RolEnum.TEACHER;
      default:
        return null;
    }
  }
}
