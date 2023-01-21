package com.secondpartial.platformreplica.enums;

public enum SemesterEnum {
  NIVELATION("Nivelación"),
  FIRST("Primer Semestre"),
  SECOND("Segundo Semestre"),
  THIRD("Tercer Semestre"),
  FOURTH("Cuarto Semestre"),
  FIFTH("Quinto Semestre"),
  SIXTH("Sexto Semestre"),
  SEVENTH("Séptimo Semestre"),
  EIGHTH("Octavo Semestre"),
  NINTH("Noveno Semestre");

  private String name;

  private SemesterEnum(String name) {
    this.name = name;
  }

  public static SemesterEnum getSemesterEnum(String semester) {
    switch (semester) {
      case "Nivelación":
        return SemesterEnum.NIVELATION;
      case "Primer Semestre":
        return SemesterEnum.FIRST;
      case "Segundo Semestre":
        return SemesterEnum.SECOND;
      case "Tercer Semestre":
        return SemesterEnum.THIRD;
      case "Cuarto Semestre":
        return SemesterEnum.FOURTH;
      case "Quinto Semestre":
        return SemesterEnum.FIFTH;
      case "Sexto Semestre":
        return SemesterEnum.SIXTH;
      case "Séptimo Semestre":
        return SemesterEnum.SEVENTH;
      case "Octavo Semestre":
        return SemesterEnum.EIGHTH;
      case "Noveno Semestre":
        return SemesterEnum.NINTH;
      default:
        return null;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static String[] getSemestersEnumToString() {
    String[] semesters = new String[SemesterEnum.values().length];
    for (int i = 0; i < SemesterEnum.values().length; i++) {
      semesters[i] = SemesterEnum.values()[i].toString();
    }
    return semesters;
  }

}
