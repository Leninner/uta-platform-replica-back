// const semesters = document.getElementsByClassName('category with_children loaded')
// const careers = ['Telecomunicaciones', 'Tecnologías de la Información', 'Ingeniería Industrial', 'Software']
// const childBySemester = Array.from(semesters)
//   .map(semester => {
//     return {
//       career: careers.find(career => semester.children[0].innerText.includes(career)),
//       semester: semester.children[0].innerText,
//       subjects: Array.from(semester.children[1].children[0].children).map(subject => {
//         console.log(subject.children[1].children, semester.children[0].innerText)
//         const name = subject.children[0].innerText
//         const hasDescription = subject.children[1].children.length > 1

//         if (hasDescription) {
//           const description = subject.children[1].children[0].innerText
//           const teacher = subject.children[1].children[1].innerText
//           return { name, description, teacher }
//         }

//         const teacher = subject.children[1].children[0].innerText

//         return { name, teacher, description: '' }
//       })
//     }
//   }
//   )
// copy(childBySemester)


const courses = [
  {
    "career": "Telecomunicaciones",
    "semester": "Nivelación",
    "subjects": [
      {
        "name": "Química - B",
        "teacher": "ALEXANDRA VIRGINIA LASCANO SUMBANA",
        "description": ""
      },
      {
        "name": "Química - A",
        "teacher": "ALEXANDRA VIRGINIA LASCANO SUMBANA",
        "description": ""
      },
      {
        "name": "Física - B",
        "teacher": "MAURICIO XAVIER LOPEZ FLORES",
        "description": ""
      },
      {
        "name": "Física - A",
        "teacher": "LUIS ARMANDO CAMPAÑA MUQUINCHE",
        "description": ""
      },
      {
        "name": "Matemática - A",
        "teacher": "CLARA AUGUSTA SANCHEZ BENITEZ",
        "description": ""
      },
      {
        "name": "Matemática - B",
        "teacher": "DANIEL SEBASTIAN JEREZ MAYORGA",
        "description": ""
      },
      {
        "name": "Universidad y Sociedad - B",
        "teacher": "MAURICIO XAVIER LOPEZ FLORES",
        "description": ""
      },
      {
        "name": "Universidad y Sociedad - A",
        "teacher": "LUIS ARMANDO CAMPAÑA MUQUINCHE",
        "description": ""
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Primer Semestre",
    "subjects": [
      {
        "name": "Química - B",
        "description": "Los avances científicos y tecnológicos han provocado en todo el mundo cambios, destacados en la electrónica y telecomunicaciones provocando un salto tecnológico, combinando la química con estas asignaturas en aspectos relacionados con la estructura de los materiales, los dispositivos electrónicos y dieléctricos mas comunes, la aparición de nuevos dispositivos y sistemas electrónicos.",
        "teacher": "MARTHA ESPERANZA SEVILLA ABARCA"
      },
      {
        "name": "Química - A",
        "teacher": "MARTHA ESPERANZA SEVILLA ABARCA",
        "description": ""
      },
      {
        "name": "Tecnologías de la Información (Tic) - B",
        "teacher": "JOSE VICENTE MORALES LOZADA",
        "description": ""
      },
      {
        "name": "Tecnologías de la Información (Tic) - A",
        "teacher": "JOSE VICENTE MORALES LOZADA",
        "description": ""
      },
      {
        "name": "Algebra Lineal - B",
        "teacher": "CLARA AUGUSTA SANCHEZ BENITEZ",
        "description": ""
      },
      {
        "name": "Algebra Lineal - A",
        "teacher": "CLARA AUGUSTA SANCHEZ BENITEZ",
        "description": ""
      },
      {
        "name": "Física Básica - A",
        "teacher": "FREDDY GEOVANNY BENALCAZAR PALACIOS",
        "description": ""
      },
      {
        "name": "Física Básica - B",
        "teacher": "PERCY ALBERTO RIOS VILLACORTA",
        "description": ""
      },
      {
        "name": "Metodología de la Investigación - B",
        "teacher": "PERCY ALBERTO RIOS VILLACORTA",
        "description": ""
      },
      {
        "name": "Metodología de la Investigación - A",
        "teacher": "PERCY ALBERTO RIOS VILLACORTA",
        "description": ""
      },
      {
        "name": "Cálculo de una Variable - B",
        "teacher": "FABIAN RODRIGO SALAZAR ESCOBAR",
        "description": ""
      },
      {
        "name": "Cálculo de una Variable - A",
        "teacher": "JAIME RODRIGO GUILCAPI MOSQUERA",
        "description": ""
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Segundo Semestre",
    "subjects": [
      {
        "name": "Gestión de Calidad - B",
        "teacher": "MARTHA ESPERANZA SEVILLA ABARCA",
        "description": ""
      },
      {
        "name": "Gestión de Calidad - A",
        "teacher": "MARTHA ESPERANZA SEVILLA ABARCA",
        "description": ""
      },
      {
        "name": "Evolución de Las Telecomunicaciones - B",
        "teacher": "ELIZABETH PAULINA AYALA BAÑO",
        "description": ""
      },
      {
        "name": "Evolución de Las Telecomunicaciones - A",
        "teacher": "ELIZABETH PAULINA AYALA BAÑO",
        "description": ""
      },
      {
        "name": "Base de Datos - B",
        "teacher": "JOSE VICENTE MORALES LOZADA",
        "description": ""
      },
      {
        "name": "Base de Datos - A",
        "teacher": "JOSE VICENTE MORALES LOZADA",
        "description": ""
      },
      {
        "name": "Fundamentos de Programación - B",
        "teacher": "ANDREA PATRICIA SANCHEZ ZUMBA",
        "description": ""
      },
      {
        "name": "Fundamentos de Programación - A",
        "teacher": "VICTOR SANTIAGO MANZANO VILLAFUERTE",
        "description": ""
      },
      {
        "name": "Física Aplicada - A",
        "teacher": "FREDDY GEOVANNY BENALCAZAR PALACIOS",
        "description": ""
      },
      {
        "name": "Cálculo de Varias Variables - B",
        "teacher": "FABIAN RODRIGO SALAZAR ESCOBAR",
        "description": ""
      },
      {
        "name": "Cálculo de Varias Variables - A",
        "teacher": "JAIME RODRIGO GUILCAPI MOSQUERA",
        "description": ""
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Tercer Semestre",
    "subjects": [
      {
        "name": "Métodos Numéricos - A",
        "teacher": "CLARA AUGUSTA SANCHEZ BENITEZ",
        "description": ""
      },
      {
        "name": "Física para Electrónica - A",
        "teacher": "SANTIAGO MAURICIO ALTAMIRANO MELENDEZ",
        "description": ""
      },
      {
        "name": "Programación Avanzada - A",
        "teacher": "ANA PAMELA CASTRO MARTIN",
        "description": ""
      },
      {
        "name": "Dispositivos y Medidas - B",
        "teacher": "EDGAR FREDDY ROBALINO PEÑA",
        "description": ""
      },
      {
        "name": "Dispositivos y Medidas - A",
        "teacher": "EDGAR FREDDY ROBALINO PEÑA",
        "description": ""
      },
      {
        "name": "Probabilidad y Estadística - A",
        "teacher": "MARTHA ESPERANZA SEVILLA ABARCA",
        "description": ""
      },
      {
        "name": "Ecuaciones Diferenciales - B",
        "teacher": "FABIAN RODRIGO SALAZAR ESCOBAR",
        "description": ""
      },
      {
        "name": "Ecuaciones Diferenciales - A",
        "teacher": "JAIME RODRIGO GUILCAPI MOSQUERA",
        "description": ""
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Cuarto Semestre",
    "subjects": [
      {
        "name": "Sistemas Digitales - A",
        "teacher": "CARLOS DIEGO GORDON GALLEGOS",
        "description": ""
      },
      {
        "name": "Electromagnetismo - A",
        "teacher": "FREDDY GEOVANNY BENALCAZAR PALACIOS",
        "description": ""
      },
      {
        "name": "Software de Simulación - A",
        "teacher": "CARLOS DIEGO GORDON GALLEGOS",
        "description": ""
      },
      {
        "name": "Análisis de Circuitos - A",
        "teacher": "MARIO GEOVANNY GARCIA CARRILLO",
        "description": ""
      },
      {
        "name": "Análisis de Circuitos - B",
        "teacher": "PERCY ALBERTO RIOS VILLACORTA",
        "description": ""
      },
      {
        "name": "Sistemas Lineales - B",
        "teacher": "MARIO GEOVANNY GARCIA CARRILLO",
        "description": ""
      },
      {
        "name": "Sistemas Lineales - A",
        "teacher": "MARIO GEOVANNY GARCIA CARRILLO",
        "description": ""
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Quinto Semestre",
    "subjects": [
      {
        "name": "Legislación Laboral - A",
        "teacher": "ELIZABETH PAULINA AYALA BAÑO",
        "description": ""
      },
      {
        "name": "Sistemas Embebidos (VlSI) - B",
        "teacher": "EDGAR PATRICIO CORDOVA CORDOVA",
        "description": ""
      },
      {
        "name": "Sistemas Embebidos (VlSI) - A",
        "teacher": "EDGAR PATRICIO CORDOVA CORDOVA",
        "description": ""
      },
      {
        "name": "Realidad Nacional - A",
        "teacher": "ELIZABETH PAULINA AYALA BAÑO",
        "description": ""
      },
      {
        "name": "Procesos Estocásticos - B",
        "teacher": "ANA PAMELA CASTRO MARTIN",
        "description": ""
      },
      {
        "name": "Procesos Estocásticos - A",
        "teacher": "MARCO ANTONIO JURADO LOZADA",
        "description": ""
      },
      {
        "name": "Circuitos Electrónicos - A",
        "teacher": "MARIO GEOVANNY GARCIA CARRILLO",
        "description": ""
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Sexto Semestre",
    "subjects": [
      {
        "name": "Sistemas de Telefonía - A",
        "teacher": "GEOVANNI DANILO BRITO MONCAYO",
        "description": ""
      },
      {
        "name": "Redes de Datos - A",
        "teacher": "EDGAR FREDDY ROBALINO PEÑA",
        "description": ""
      },
      {
        "name": "Proyecto de Telecomunicaciones - A",
        "teacher": "ELIZABETH PAULINA AYALA BAÑO",
        "description": ""
      },
      {
        "name": "Procesamiento Digital de Señales e Imágenes - B",
        "teacher": "ANA PAMELA CASTRO MARTIN",
        "description": ""
      },
      {
        "name": "Procesamiento Digital de Señales e Imágenes - A",
        "teacher": "MARCO ANTONIO JURADO LOZADA",
        "description": ""
      },
      {
        "name": "Líneas de Transmisión - A",
        "teacher": "JULIO ENRIQUE CUJI RODRIGUEZ",
        "description": ""
      },
      {
        "name": "Comunicación Analógica - A",
        "description": "",
        "teacher": "JUAN PABLO PALLO NOROÑA"
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Séptimo Semestre",
    "subjects": [
      {
        "name": "Conmutación y Enrutamiento de Redes - A",
        "teacher": "VICTOR SANTIAGO MANZANO VILLAFUERTE",
        "description": ""
      },
      {
        "name": "Propagación y Antenas - A",
        "teacher": "JULIO ENRIQUE CUJI RODRIGUEZ",
        "description": ""
      },
      {
        "name": "Circuitos Rf - B",
        "teacher": "SANTIAGO MAURICIO ALTAMIRANO MELENDEZ",
        "description": ""
      },
      {
        "name": "Circuitos Rf - A",
        "teacher": "SANTIAGO MAURICIO ALTAMIRANO MELENDEZ",
        "description": ""
      },
      {
        "name": "Comunicación Digital - A",
        "description": "",
        "teacher": "JUAN PABLO PALLO NOROÑA"
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Octavo Semestre",
    "subjects": [
      {
        "name": "Diseño de Proyectos - A",
        "teacher": "EDGAR PATRICIO CORDOVA CORDOVA",
        "description": ""
      },
      {
        "name": "Comunicaciones Avanzadas - A",
        "teacher": "VICTOR SANTIAGO MANZANO VILLAFUERTE",
        "description": ""
      },
      {
        "name": "Sistemas Satelitales y GPS - A",
        "teacher": "SANTIAGO MAURICIO ALTAMIRANO MELENDEZ",
        "description": ""
      },
      {
        "name": "Televisión Digital - A",
        "teacher": "MARCO ANTONIO JURADO LOZADA",
        "description": ""
      },
      {
        "name": "Comunicaciones Ópticas - A",
        "teacher": "CARLOS DIEGO GORDON GALLEGOS",
        "description": ""
      },
      {
        "name": "Comunicaciones Móviles - A",
        "description": "",
        "teacher": "JUAN PABLO PALLO NOROÑA"
      },
      {
        "name": "Sistemas Inalámbricos - A",
        "teacher": "EDGAR FREDDY ROBALINO PEÑA",
        "description": ""
      }
    ]
  },
  {
    "career": "Telecomunicaciones",
    "semester": "Noveno Semestre",
    "subjects": [
      {
        "name": "Desarrollo de Proyectos - A",
        "teacher": "ANA PAMELA CASTRO MARTIN",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Nivelación",
    "subjects": [
      {
        "name": "Lógica de Programación - B",
        "teacher": "LUIS ARMANDO CAMPAÑA MUQUINCHE",
        "description": ""
      },
      {
        "name": "Lógica de Programación - A",
        "description": "",
        "teacher": "LUIS ARMANDO CAMPAÑA MUQUINCHE"
      },
      {
        "name": "Física - B",
        "teacher": "JUAN CAMILO ESCOBAR NARANJO",
        "description": ""
      },
      {
        "name": "Física - A",
        "teacher": "JUAN CAMILO ESCOBAR NARANJO",
        "description": ""
      },
      {
        "name": "Matemática - B",
        "teacher": "GLADYS ISABEL TITUAÑA PULLUQUITIN",
        "description": ""
      },
      {
        "name": "Matemática - A",
        "teacher": "GLADYS ISABEL TITUAÑA PULLUQUITIN",
        "description": ""
      },
      {
        "name": "Universidad y Sociedad - B",
        "teacher": "ALEXANDRA VIRGINIA LASCANO SUMBANA",
        "description": ""
      },
      {
        "name": "Universidad y Sociedad - A",
        "teacher": "ALEXANDRA VIRGINIA LASCANO SUMBANA",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Primer Semestre",
    "subjects": [
      {
        "name": "Metodología de la Investigación - B",
        "teacher": "ANITA LUCIA LARREA BUSTOS",
        "description": ""
      },
      {
        "name": "Metodología de la Investigación - A",
        "teacher": "ANITA LUCIA LARREA BUSTOS",
        "description": ""
      },
      {
        "name": "Física - B",
        "teacher": "PABLO RAFAEL MUÑOZ VALVERDE",
        "description": ""
      },
      {
        "name": "Física - A",
        "teacher": "PABLO RAFAEL MUÑOZ VALVERDE",
        "description": ""
      },
      {
        "name": "Calculo Diferencial - B",
        "teacher": "PAULO CESAR TORRES ABRIL",
        "description": ""
      },
      {
        "name": "Calculo Diferencial - A",
        "teacher": "PAULO CESAR TORRES ABRIL",
        "description": ""
      },
      {
        "name": "Fundamentos de Programación - B",
        "teacher": "LEONARDO DAVID TORRES VALVERDE",
        "description": ""
      },
      {
        "name": "Fundamentos de Programación - A",
        "teacher": "MARCO VINICIO GUACHIMBOZA VILLALVA",
        "description": ""
      },
      {
        "name": "Algebra Lineal - B",
        "teacher": "MARITZA ELIZABETH CASTRO MAYORGA",
        "description": ""
      },
      {
        "name": "Algebra Lineal - A",
        "teacher": "MARITZA ELIZABETH CASTRO MAYORGA",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Segundo Semestre",
    "subjects": [
      {
        "name": "Programación Orientada A Objetos - A",
        "teacher": "LEONARDO DAVID TORRES VALVERDE",
        "description": ""
      },
      {
        "name": "Programación Orientada A Objetos - B",
        "teacher": "FELIX OSCAR FERNANDEZ PEÑA",
        "description": ""
      },
      {
        "name": "Realidad Nacional - A",
        "teacher": "JOSE VICENTE MORALES LOZADA",
        "description": ""
      },
      {
        "name": "Estructura de Datos - A",
        "teacher": "FELIX OSCAR FERNANDEZ PEÑA",
        "description": ""
      },
      {
        "name": "Medidas Eléctricas - A",
        "teacher": "FRANKLIN WILFRIDO SALAZAR LOGROÑO",
        "description": ""
      },
      {
        "name": "Lógica Matemática - A",
        "teacher": "PABLO RAFAEL MUÑOZ VALVERDE",
        "description": ""
      },
      {
        "name": "Calculo Integral - A",
        "teacher": "MARLON ANTONIO SANTAMARIA VILLACIS",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Tercer Semestre",
    "subjects": [
      {
        "name": "Fundamentos de Redes y Comunicación de Datos - A",
        "teacher": "DENNIS VINICIO CHICAIZA CASTILLO",
        "description": ""
      },
      {
        "name": "Fundamentos de Bases de Datos - A",
        "teacher": "EDWIN HERNANDO BUENAÑO VALENCIA",
        "description": ""
      },
      {
        "name": "Sistemas Operativos - A",
        "teacher": "FRANKLIN OSWALDO MAYORGA MAYORGA",
        "description": ""
      },
      {
        "name": "Probabilidad y Estadística - A",
        "teacher": "BOLIVAR EFRAIN MORALES OÑATE",
        "description": ""
      },
      {
        "name": "Programación Avanzada - A",
        "teacher": "HERNAN FABRICIO NARANJO AVALOS",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Cuarto Semestre",
    "subjects": [
      {
        "name": "Gestión de Bases de Datos - A",
        "teacher": "EDWIN HERNANDO BUENAÑO VALENCIA",
        "description": ""
      },
      {
        "name": "Administración de Sistemas Operativos - A",
        "description": "",
        "teacher": "DAVID OMAR GUEVARA AULESTIA"
      },
      {
        "name": "Interacción Hombre Maquina - A",
        "teacher": "MARCO VINICIO GUACHIMBOZA VILLALVA",
        "description": ""
      },
      {
        "name": "Ingeniería de Software - A",
        "teacher": "OSCAR FERNANDO IBARRA TORRES",
        "description": ""
      },
      {
        "name": "Métodos Numéricos - A",
        "teacher": "MARLON ANTONIO SANTAMARIA VILLACIS",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Quinto Semestre",
    "subjects": [
      {
        "name": "Gestión y Evaluación de Proyectos TI - A",
        "teacher": "SANDRA LUCRECIA CARRILLO RIOS",
        "description": ""
      },
      {
        "name": "Conmutación y Enrutamiento Básico - A",
        "teacher": "ELSA PILAR URRUTIA URRUTIA",
        "description": ""
      },
      {
        "name": "Investigación Operativa - A",
        "teacher": "VICTOR HUGO GUACHIMBOSA VILLALBA",
        "description": ""
      },
      {
        "name": "Tecnologías y Desarrollo Web - A",
        "teacher": "HERNAN FABRICIO NARANJO AVALOS",
        "description": ""
      },
      {
        "name": "Sistemas de Bases de Datos Distribuidos - A",
        "teacher": "EDISON HOMERO ALVAREZ MAYORGA",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Sexto Semestre",
    "subjects": [
      {
        "name": "Aplicaciones Móviles - A",
        "teacher": "CLAY FERNANDO ALDAS FLORES",
        "description": ""
      },
      {
        "name": "Gobiernos TI - A",
        "teacher": "JOSE VICENTE MORALES LOZADA",
        "description": ""
      },
      {
        "name": "Conmutación y Enrutamiento Avanzado - A",
        "teacher": "DENNIS VINICIO CHICAIZA CASTILLO",
        "description": ""
      },
      {
        "name": "Sistemas de Soporte de Decisiones - A",
        "teacher": "EDISON HOMERO ALVAREZ MAYORGA",
        "description": ""
      },
      {
        "name": "Administración de Base de Datos - A",
        "teacher": "CARLOS ISRAEL NUÑEZ MIRANDA",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Séptimo Semestre",
    "subjects": [
      {
        "name": "Administración de Redes - A",
        "teacher": "ANDREA PATRICIA SANCHEZ ZUMBA",
        "description": ""
      },
      {
        "name": "Arquitectura y Plataformas de Servidores - A",
        "description": "",
        "teacher": "DAVID OMAR GUEVARA AULESTIA"
      },
      {
        "name": "Emprendimiento y Gestión Financiera de TI - A",
        "teacher": "ANITA LUCIA LARREA BUSTOS",
        "description": ""
      },
      {
        "name": "Inteligencia de Negocios - A",
        "teacher": "EDISON HOMERO ALVAREZ MAYORGA",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Octavo Semestre",
    "subjects": [
      {
        "name": "Diseño de Proyectos - A",
        "teacher": "FRANKLIN OSWALDO MAYORGA MAYORGA",
        "description": ""
      },
      {
        "name": "Auditoria de TI - A",
        "teacher": "JULIO ENRIQUE BALAREZO LOPEZ",
        "description": ""
      },
      {
        "name": "Seguridad de la Información en Redes de Comunicación de Dat - A",
        "description": "",
        "teacher": "DAVID OMAR GUEVARA AULESTIA"
      },
      {
        "name": "Integración de Sistemas - A",
        "teacher": "RUBEN EDUARDO NOGALES PORTERO",
        "description": ""
      }
    ]
  },
  {
    "career": "Tecnologías de la Información",
    "semester": "Noveno Semestre",
    "subjects": [
      {
        "name": "Desarrollo de Proyectos - A",
        "teacher": "FRANKLIN OSWALDO MAYORGA MAYORGA",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Nivelación",
    "subjects": [
      {
        "name": "Química - B",
        "teacher": "GLADYS ISABEL TITUAÑA PULLUQUITIN",
        "description": ""
      },
      {
        "name": "Química - A",
        "teacher": "GLADYS ISABEL TITUAÑA PULLUQUITIN",
        "description": ""
      },
      {
        "name": "Física - B",
        "teacher": "JUAN CAMILO ESCOBAR NARANJO",
        "description": ""
      },
      {
        "name": "Física - A",
        "teacher": "JUAN CAMILO ESCOBAR NARANJO",
        "description": ""
      },
      {
        "name": "Matemática - B",
        "teacher": "MAURICIO XAVIER LOPEZ FLORES",
        "description": ""
      },
      {
        "name": "Matemática - A",
        "teacher": "MAURICIO XAVIER LOPEZ FLORES",
        "description": ""
      },
      {
        "name": "Universidad y Sociedad - A",
        "teacher": "MAURICIO XAVIER LOPEZ FLORES",
        "description": ""
      },
      {
        "name": "Universidad y Sociedad - B",
        "teacher": "ANGEL MAURICIO CARRANZA GARCES",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Primer Semestre",
    "subjects": [
      {
        "name": "Tecnologías de la Información y la Comunicación (Tic) - B",
        "description": "",
        "teacher": "JAIME BOLIVAR RUIZ BANDA"
      },
      {
        "name": "Tecnologías de la Información y la Comunicación (Tic) - A",
        "description": "",
        "teacher": "JAIME BOLIVAR RUIZ BANDA"
      },
      {
        "name": "Lógica Matemática - B",
        "description": "",
        "teacher": "JAIME BOLIVAR RUIZ BANDA"
      },
      {
        "name": "Lógica Matemática - A",
        "description": "",
        "teacher": "JAIME BOLIVAR RUIZ BANDA"
      },
      {
        "name": "Introducción a la Ingeniería Industrial - B",
        "teacher": "DAYSI MARGARITA ORTIZ GUERRERO",
        "description": ""
      },
      {
        "name": "Introducción a la Ingeniería Industrial - A",
        "teacher": "CARLOS HUMBERTO SANCHEZ ROSERO",
        "description": ""
      },
      {
        "name": "Algebra - B",
        "teacher": "CHRISTIAN ISMAEL ORTIZ SAILEMA",
        "description": ""
      },
      {
        "name": "Algebra - A",
        "teacher": "JOSE LUIS GAVIDIA GARCIA",
        "description": ""
      },
      {
        "name": "Física Básica - B",
        "teacher": "BOLIVAR EFRAIN MORALES OÑATE",
        "description": ""
      },
      {
        "name": "Física Básica - A",
        "teacher": "BOLIVAR EFRAIN MORALES OÑATE",
        "description": ""
      },
      {
        "name": "Química - B",
        "teacher": "FREDDY ROBERTO LEMA CHICAIZA",
        "description": ""
      },
      {
        "name": "Química - A",
        "teacher": "FREDDY ROBERTO LEMA CHICAIZA",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Segundo Semestre",
    "subjects": [
      {
        "name": "Programación - B",
        "description": "",
        "teacher": "JAIME BOLIVAR RUIZ BANDA"
      },
      {
        "name": "Programación - A",
        "description": "",
        "teacher": "JAIME BOLIVAR RUIZ BANDA"
      },
      {
        "name": "Algebra Lineal - B",
        "teacher": "JOSE EZEQUIEL NARANJO ROBALINO",
        "description": ""
      },
      {
        "name": "Algebra Lineal - A",
        "teacher": "JOSE EZEQUIEL NARANJO ROBALINO",
        "description": ""
      },
      {
        "name": "Realidad Nacional - B",
        "teacher": "JOSE EZEQUIEL NARANJO ROBALINO",
        "description": ""
      },
      {
        "name": "Realidad Nacional - A",
        "teacher": "SANDRA LUCRECIA CARRILLO RIOS",
        "description": ""
      },
      {
        "name": "Metodología de la Investigación - B",
        "teacher": "SANDRA LUCRECIA CARRILLO RIOS",
        "description": ""
      },
      {
        "name": "Metodología de la Investigación - A",
        "teacher": "SANDRA LUCRECIA CARRILLO RIOS",
        "description": ""
      },
      {
        "name": "Calculo Diferencial - B",
        "teacher": "EDITH ELENA TUBÓN NÚÑEZ",
        "description": ""
      },
      {
        "name": "Calculo Diferencial - A",
        "teacher": "EDITH ELENA TUBÓN NÚÑEZ",
        "description": ""
      },
      {
        "name": "Física Aplicada - B",
        "teacher": "EDITH ELENA TUBÓN NÚÑEZ",
        "description": ""
      },
      {
        "name": "Física Aplicada - A",
        "teacher": "EDITH ELENA TUBÓN NÚÑEZ",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Tercer Semestre",
    "subjects": [
      {
        "name": "Calculo Integral - A",
        "teacher": "FREDDY ROBERTO LEMA CHICAIZA",
        "description": ""
      },
      {
        "name": "Tecnología de Materiales - A",
        "teacher": "CHRISTIAN ISMAEL ORTIZ SAILEMA",
        "description": ""
      },
      {
        "name": "Investigación de Operaciones - A",
        "teacher": "VICTOR HUGO GUACHIMBOSA VILLALBA",
        "description": ""
      },
      {
        "name": "Electrónica y Electricidad - A",
        "teacher": "JOSE EZEQUIEL NARANJO ROBALINO",
        "description": ""
      },
      {
        "name": "Termodinámica - B",
        "teacher": "EDISSON PATRICIO JORDAN HIDALGO",
        "description": ""
      },
      {
        "name": "Termodinámica - A",
        "teacher": "EDISSON PATRICIO JORDAN HIDALGO",
        "description": ""
      },
      {
        "name": "Estadística y Probabilidades - A",
        "teacher": "BOLIVAR EFRAIN MORALES OÑATE",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Cuarto Semestre",
    "subjects": [
      {
        "name": "Ingeniería de Métodos - B",
        "teacher": "FRANKLIN GEOVANNY TIGRE ORTEGA",
        "description": ""
      },
      {
        "name": "Ingeniería de Métodos - A",
        "teacher": "FRANKLIN GEOVANNY TIGRE ORTEGA",
        "description": ""
      },
      {
        "name": "Dibujo Asistido Por Computador - B",
        "teacher": "FRANKLIN GEOVANNY TIGRE ORTEGA",
        "description": ""
      },
      {
        "name": "Dibujo Asistido Por Computador - A",
        "teacher": "FRANKLIN GEOVANNY TIGRE ORTEGA",
        "description": ""
      },
      {
        "name": "Maquinas Eléctricas - A",
        "teacher": "JOSE EZEQUIEL NARANJO ROBALINO",
        "description": ""
      },
      {
        "name": "Operaciones Unitarias - A",
        "teacher": "CHRISTIAN ISMAEL ORTIZ SAILEMA",
        "description": ""
      },
      {
        "name": "Contabilidad y Costos Industriales - B",
        "teacher": "ANITA LUCIA LARREA BUSTOS",
        "description": ""
      },
      {
        "name": "Contabilidad y Costos Industriales - A",
        "teacher": "ANITA LUCIA LARREA BUSTOS",
        "description": ""
      },
      {
        "name": "Seguridad Industrial - A",
        "teacher": "EDISSON PATRICIO JORDAN HIDALGO",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Quinto Semestre",
    "subjects": [
      {
        "name": "Instrumentación Industrial - A",
        "teacher": "LUIS ALBERTO MORALES PERRAZO",
        "description": ""
      },
      {
        "name": "Instrumentación Industrial - B",
        "teacher": "FRANKLIN WILFRIDO SALAZAR LOGROÑO",
        "description": ""
      },
      {
        "name": "Gestión de Operaciones - B",
        "teacher": "CESAR ANIBAL ROSERO MANTILLA",
        "description": ""
      },
      {
        "name": "Gestión de Operaciones - A",
        "teacher": "CESAR ANIBAL ROSERO MANTILLA",
        "description": ""
      },
      {
        "name": "Procesos Industriales - A",
        "teacher": "CHRISTIAN ISMAEL ORTIZ SAILEMA",
        "description": ""
      },
      {
        "name": "Ergonomía - B",
        "teacher": "FERNANDO URRUTIA URRUTIA",
        "description": ""
      },
      {
        "name": "Ergonomía - A",
        "teacher": "FERNANDO URRUTIA URRUTIA",
        "description": ""
      },
      {
        "name": "Administración de la Producción - A",
        "teacher": "JOHN PAUL REYES VASQUEZ",
        "description": ""
      },
      {
        "name": "Maquinas Herramientas - A",
        "teacher": "JOSE LUIS GAVIDIA GARCIA",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Sexto Semestre",
    "subjects": [
      {
        "name": "Gestión Ambiental y Energías Alternativas - B",
        "teacher": "LUIS ALBERTO MORALES PERRAZO",
        "description": ""
      },
      {
        "name": "Gestión Ambiental y Energías Alternativas - A",
        "teacher": "LUIS ALBERTO MORALES PERRAZO",
        "description": ""
      },
      {
        "name": "Diseño y Organización de Plantas - B",
        "description": "Clave: DOP2022",
        "teacher": "ISRAEL ERNESTO NARANJO CHIRIBOGA"
      },
      {
        "name": "Diseño y Organización de Plantas - A",
        "description": "Clave: DOP2022",
        "teacher": "ISRAEL ERNESTO NARANJO CHIRIBOGA"
      },
      {
        "name": "Higiene Industrial - A",
        "teacher": "LUIS ALBERTO MORALES PERRAZO",
        "description": ""
      },
      {
        "name": "Higiene Industrial - B",
        "teacher": "FERNANDO URRUTIA URRUTIA",
        "description": ""
      },
      {
        "name": "Gestión Por Procesos - B",
        "teacher": "CHRISTIAN ISMAEL ORTIZ SAILEMA",
        "description": ""
      },
      {
        "name": "Gestión Por Procesos - A",
        "teacher": "CESAR ANIBAL ROSERO MANTILLA",
        "description": ""
      },
      {
        "name": "Control Neumático y Oleo hidráulica - A",
        "teacher": "CHRISTIAN JOSE MARIÑO RIVERA",
        "description": ""
      },
      {
        "name": "Control Neumático y Oleo hidráulica - B",
        "teacher": "FRANKLIN WILFRIDO SALAZAR LOGROÑO",
        "description": ""
      },
      {
        "name": "Sistemas Cad/Cam - A",
        "teacher": "JESSICA PAOLA LOPEZ ARBOLEDA",
        "description": ""
      },
      {
        "name": "Sistemas Cad/Cam - B",
        "teacher": "JOSE LUIS GAVIDIA GARCIA",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Séptimo Semestre",
    "subjects": [
      {
        "name": "Instrumentación Virtual - B",
        "teacher": "MARCELO VLADIMIR GARCIA SANCHEZ",
        "description": ""
      },
      {
        "name": "Instrumentación Virtual - A",
        "teacher": "MARCELO VLADIMIR GARCIA SANCHEZ",
        "description": ""
      },
      {
        "name": "Gestión del Mantenimiento - B",
        "teacher": "FERNANDO URRUTIA URRUTIA",
        "description": ""
      },
      {
        "name": "Gestión del Mantenimiento - A",
        "teacher": "FERNANDO URRUTIA URRUTIA",
        "description": ""
      },
      {
        "name": "Control de Calidad - A",
        "teacher": "LUIS ALBERTO MORALES PERRAZO",
        "description": ""
      },
      {
        "name": "Control de Calidad - B",
        "teacher": "JOSE LUIS GAVIDIA GARCIA",
        "description": ""
      },
      {
        "name": "Gerencia Empresarial - B",
        "teacher": "ANGEL MAURICIO CARRANZA GARCES",
        "description": ""
      },
      {
        "name": "Gerencia Empresarial - A",
        "teacher": "ANGEL MAURICIO CARRANZA GARCES",
        "description": ""
      },
      {
        "name": "Emprendimiento e Innovación - B",
        "teacher": "JESSICA PAOLA LOPEZ ARBOLEDA",
        "description": ""
      },
      {
        "name": "Emprendimiento e Innovación - A",
        "teacher": "VICTOR HUGO GUACHIMBOSA VILLALBA",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Octavo Semestre",
    "subjects": [
      {
        "name": "Gestión de Calidad - B",
        "teacher": "DAYSI MARGARITA ORTIZ GUERRERO",
        "description": ""
      },
      {
        "name": "Gestión de Calidad - A",
        "teacher": "DAYSI MARGARITA ORTIZ GUERRERO",
        "description": ""
      },
      {
        "name": "Diseño de Proyectos - B",
        "teacher": "JESSICA PAOLA LOPEZ ARBOLEDA",
        "description": ""
      },
      {
        "name": "Diseño de Proyectos - A",
        "teacher": "JESSICA PAOLA LOPEZ ARBOLEDA",
        "description": ""
      },
      {
        "name": "Simulación y Laboratorio - B",
        "teacher": "JOHN PAUL REYES VASQUEZ",
        "description": ""
      },
      {
        "name": "Simulación y Laboratorio - A",
        "teacher": "JOHN PAUL REYES VASQUEZ",
        "description": ""
      },
      {
        "name": "Logística y Cadena de Abastecimiento - B",
        "description": "Clave: LCA2022",
        "teacher": "ISRAEL ERNESTO NARANJO CHIRIBOGA"
      },
      {
        "name": "Logística y Cadena de Abastecimiento - A",
        "description": "Clave: LCA2022",
        "teacher": "ISRAEL ERNESTO NARANJO CHIRIBOGA"
      },
      {
        "name": "Automatización Industrial y Robótica - A",
        "teacher": "MARCELO VLADIMIR GARCIA SANCHEZ",
        "description": ""
      },
      {
        "name": "Automatización Industrial y Robótica - B",
        "teacher": "FRANKLIN WILFRIDO SALAZAR LOGROÑO",
        "description": ""
      }
    ]
  },
  {
    "career": "Ingeniería Industrial",
    "semester": "Noveno Semestre",
    "subjects": [
      {
        "name": "Desarrollo de Proyectos - A",
        "teacher": "DAYSI MARGARITA ORTIZ GUERRERO",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Nivelación",
    "subjects": [
      {
        "name": "Lógica de Programación - B",
        "teacher": "LUIS ARMANDO CAMPAÑA MUQUINCHE",
        "description": ""
      },
      {
        "name": "Lógica de Programación - A",
        "teacher": "LUIS ARMANDO CAMPAÑA MUQUINCHE",
        "description": ""
      },
      {
        "name": "Física - B",
        "teacher": "JUAN CAMILO ESCOBAR NARANJO",
        "description": ""
      },
      {
        "name": "Física - A",
        "teacher": "JUAN CAMILO ESCOBAR NARANJO",
        "description": ""
      },
      {
        "name": "Matemática - B",
        "teacher": "DANIEL SEBASTIAN JEREZ MAYORGA",
        "description": ""
      },
      {
        "name": "Matemática - A",
        "teacher": "DANIEL SEBASTIAN JEREZ MAYORGA",
        "description": ""
      },
      {
        "name": "Universidad y Sociedad - B",
        "teacher": "GLADYS ISABEL TITUAÑA PULLUQUITIN",
        "description": ""
      },
      {
        "name": "Universidad y Sociedad - A",
        "teacher": "DANIEL SEBASTIAN JEREZ MAYORGA",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Primer Semestre",
    "subjects": [
      {
        "name": "Lógica Matemática - B",
        "teacher": "PABLO RAFAEL MUÑOZ VALVERDE",
        "description": ""
      },
      {
        "name": "Lógica Matemática - A",
        "teacher": "EDWIN HERNANDO BUENAÑO VALENCIA",
        "description": ""
      },
      {
        "name": "Algoritmos y Lógica de Programación - B",
        "teacher": "MARCOS RAPHAEL BENITEZ ALDAS",
        "description": ""
      },
      {
        "name": "Algoritmos y Lógica de Programación - A",
        "teacher": "MARCOS RAPHAEL BENITEZ ALDAS",
        "description": ""
      },
      {
        "name": "Calculo Diferencial - B",
        "teacher": "PABLO RAFAEL MUÑOZ VALVERDE",
        "description": ""
      },
      {
        "name": "Calculo Diferencial - A",
        "teacher": "PAULO CESAR TORRES ABRIL",
        "description": ""
      },
      {
        "name": "Algebra Lineal - A",
        "teacher": "ANDREA PATRICIA SANCHEZ ZUMBA",
        "description": ""
      },
      {
        "name": "Algebra Lineal - B",
        "teacher": "MARITZA ELIZABETH CASTRO MAYORGA",
        "description": ""
      },
      {
        "name": "Física - B",
        "teacher": "MARLON ANTONIO SANTAMARIA VILLACIS",
        "description": ""
      },
      {
        "name": "Física - A",
        "teacher": "MARLON ANTONIO SANTAMARIA VILLACIS",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Segundo Semestre",
    "subjects": [
      {
        "name": "Metodología de la Investigación - A",
        "teacher": "MARITZA ELIZABETH CASTRO MAYORGA",
        "description": ""
      },
      {
        "name": "Metodología de la Investigación - B",
        "teacher": "MARCOS RAPHAEL BENITEZ ALDAS",
        "description": ""
      },
      {
        "name": "Sistemas Operativos - B",
        "teacher": "PAULO CESAR TORRES ABRIL",
        "description": ""
      },
      {
        "name": "Sistemas Operativos - A",
        "teacher": "FRANKLIN OSWALDO MAYORGA MAYORGA",
        "description": ""
      },
      {
        "name": "Programación Orientada A Objetos - A",
        "teacher": "OSCAR FERNANDO IBARRA TORRES",
        "description": ""
      },
      {
        "name": "Programación Orientada A Objetos - B",
        "teacher": "PAULO CESAR TORRES ABRIL",
        "description": ""
      },
      {
        "name": "Calculo Integral - A",
        "teacher": "MARLON ANTONIO SANTAMARIA VILLACIS",
        "description": ""
      },
      {
        "name": "Fundamentos de Ingeniería de Software - A",
        "teacher": "OSCAR FERNANDO IBARRA TORRES",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Tercer Semestre",
    "subjects": [
      {
        "name": "Modelamiento y Diseño de Software - A",
        "teacher": "LEONARDO DAVID TORRES VALVERDE",
        "description": ""
      },
      {
        "name": "Introducción A Redes - A",
        "teacher": "ANDREA PATRICIA SANCHEZ ZUMBA",
        "description": ""
      },
      {
        "name": "Probabilidad y Estadística - A",
        "teacher": "PABLO RAFAEL MUÑOZ VALVERDE",
        "description": ""
      },
      {
        "name": "Estructura de Datos - A",
        "teacher": "FELIX OSCAR FERNANDEZ PEÑA",
        "description": ""
      },
      {
        "name": "Métodos Numéricos - A",
        "teacher": "SANDRA LUCRECIA CARRILLO RIOS",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Cuarto Semestre",
    "subjects": [
      {
        "name": "Manejo y Configuración del Software - A",
        "description": "",
        "teacher": "SANTIAGO DAVID JARA MOYA"
      },
      {
        "name": "Computación Visual - B",
        "teacher": "OSCAR FERNANDO IBARRA TORRES",
        "description": ""
      },
      {
        "name": "Computación Visual - A",
        "teacher": "CARLOS ISRAEL NUÑEZ MIRANDA",
        "description": ""
      },
      {
        "name": "Base de Datos - A",
        "teacher": "EDWIN HERNANDO BUENAÑO VALENCIA",
        "description": ""
      },
      {
        "name": "Redes - A",
        "description": "",
        "teacher": "DAVID OMAR GUEVARA AULESTIA"
      },
      {
        "name": "Metodologías Agiles - A",
        "teacher": "HERNAN FABRICIO NARANJO AVALOS",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Quinto Semestre",
    "subjects": [
      {
        "name": "Interacción Humano / Computador - A",
        "teacher": "MARCO VINICIO GUACHIMBOZA VILLALVA",
        "description": ""
      },
      {
        "name": "Aplicaciones Orientadas A Servicios - A",
        "teacher": "CARLOS ISRAEL NUÑEZ MIRANDA",
        "description": ""
      },
      {
        "name": "Patrones de Software - A",
        "teacher": "MARCO VINICIO GUACHIMBOZA VILLALVA",
        "description": ""
      },
      {
        "name": "Sistemas de Soporte de Decisiones - A",
        "teacher": "EDISON HOMERO ALVAREZ MAYORGA",
        "description": ""
      },
      {
        "name": "Investigación Operativa - A",
        "teacher": "VICTOR HUGO GUACHIMBOSA VILLALBA",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Sexto Semestre",
    "subjects": [
      {
        "name": "Realidad Nacional - A",
        "teacher": "ANGEL MAURICIO CARRANZA GARCES",
        "description": ""
      },
      {
        "name": "Gestión de Prueba e Implantación de Software - A",
        "teacher": "JULIO ENRIQUE BALAREZO LOPEZ",
        "description": ""
      },
      {
        "name": "Aplicaciones Distribuidas - A",
        "description": "",
        "teacher": "SANTIAGO DAVID JARA MOYA"
      },
      {
        "name": "Inteligencia de Negocios - A",
        "teacher": "RUBEN EDUARDO NOGALES PORTERO",
        "description": ""
      },
      {
        "name": "Aplicaciones Web y Móviles - A",
        "teacher": "DENNIS VINICIO CHICAIZA CASTILLO",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Séptimo Semestre",
    "subjects": [
      {
        "name": "Inteligencia Artificial - A",
        "teacher": "RUBEN EDUARDO NOGALES PORTERO",
        "description": ""
      },
      {
        "name": "Gestión de Calidad del Software - A",
        "description": "",
        "teacher": "SANTIAGO DAVID JARA MOYA"
      },
      {
        "name": "Gestión de Proyectos de Software - A",
        "teacher": "LEONARDO DAVID TORRES VALVERDE",
        "description": ""
      },
      {
        "name": "Auditoria de Sistemas de Información - A",
        "teacher": "JULIO ENRIQUE BALAREZO LOPEZ",
        "description": ""
      },
      {
        "name": "Desarrollo Asistido Por Software - A",
        "description": "",
        "teacher": "SANTIAGO DAVID JARA MOYA"
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Octavo Semestre",
    "subjects": [
      {
        "name": "Diseño de Proyectos - A",
        "teacher": "JULIO ENRIQUE BALAREZO LOPEZ",
        "description": ""
      },
      {
        "name": "Ingeniería Económica para Software - A",
        "description": "",
        "teacher": "SANTIAGO DAVID JARA MOYA"
      },
      {
        "name": "Seguridad en el Desarrollo del Software - A",
        "teacher": "FELIX OSCAR FERNANDEZ PEÑA",
        "description": ""
      },
      {
        "name": "Emprendimiento y Legislación Laboral - A",
        "teacher": "ANGEL MAURICIO CARRANZA GARCES",
        "description": ""
      }
    ]
  },
  {
    "career": "Software",
    "semester": "Noveno Semestre",
    "subjects": [
      {
        "name": "Desarrollo de Proyectos - A",
        "teacher": "CLAY FERNANDO ALDAS FLORES",
        "description": ""
      }
    ]
  },
]

const careers = {
  1: 'Telecomunicaciones',
  2: 'Software',
  3: 'Tecnologías de la Información',
  4: 'Ingeniería Industrial',
}

const generatePassword = () => {
  const password = Math.random().toString(36).slice(-8)
  return password
}

const generateAddress = () => {
  const indexRandom = Math.floor(Math.random() * 10)

  const addresses = [
    'Calle 12 de Octubre y Av. 6 de Diciembre',
    'Calle 13 de Mayo y Av. 6 de Diciembre',
    'Avenida 6 de Diciembre y Av. 12 de Octubre',
    'Avenida siempre viva y Av. 12 de Octubre',
    'Avenida 78 y Av. 12 de Octubre',
    'Av. 12 de Octubre',
    'Av. 6 de Diciembre',
    'Corredor interprovincial',
    'Av. 9 de Diciembre',
    'Av. 10 de Agosto',
  ]

  return addresses[indexRandom]
}

const generateEcuadorianNumber = () => {
  const start = '09'
  const middle = Math.floor(Math.random() * 10000000)
  const end = Math.floor(Math.random() * 10)

  return `${start}${middle}${end}`
}

const generateCityId = () => {
  return Math.floor(Math.random() * 23) + 1
}

// const teachers = courses.map(course => {
//   return course.subjects.map(subject => {
//     return {
//       name: subject.teacher,
//       email: `${subject.teacher?.replace(/ /g, '.').toLowerCase()}@uta.edu.ec`,
//       password: generatePassword(),
//       address: generateAddress(),
//       phoneNumber: generateEcuadorianNumber(),
//       image: 'image url',
//       rol: 'TEACHER',
//       cityId: generateCityId(),
//       careerId: Number(Object.keys(careers).find(key => careers[key] === course.career)),
//     }
//   })
// })
// copy(teachers.flat())
// console.log(teachers)

// const uniqueTeachers = teachers.reduce((acc, current) => {
//   const x = acc.find(item => item.email === current.email);
//   if (!x) {
//     return acc.concat([current]);
//   } else {
//     return acc;
//   }
// }, [])

// copy(uniqueTeachers)
// console.log(uniqueTeachers)

const uniqueTeachers = [
  {
      "name": "ALEXANDRA VIRGINIA LASCANO SUMBANA",
      "email": "alexandra.virginia.lascano.sumbana@uta.edu.ec",
      "password": "27f783g3",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0987075019",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 22,
      "careerId": 1
  },
  {
      "name": "MAURICIO XAVIER LOPEZ FLORES",
      "email": "mauricio.xavier.lopez.flores@uta.edu.ec",
      "password": "hzg5chn4",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0979741572",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 11,
      "careerId": 1
  },
  {
      "name": "LUIS ARMANDO CAMPAÑA MUQUINCHE",
      "email": "luis.armando.campaña.muquinche@uta.edu.ec",
      "password": "2yvp2b0o",
      "address": "Av. 9 de Diciembre",
      "phoneNumber": "0994409537",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 7,
      "careerId": 1
  },
  {
      "name": "CLARA AUGUSTA SANCHEZ BENITEZ",
      "email": "clara.augusta.sanchez.benitez@uta.edu.ec",
      "password": "tkx3949x",
      "address": "Av. 6 de Diciembre",
      "phoneNumber": "0952470019",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 11,
      "careerId": 1
  },
  {
      "name": "DANIEL SEBASTIAN JEREZ MAYORGA",
      "email": "daniel.sebastian.jerez.mayorga@uta.edu.ec",
      "password": "ag5u2sqk",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0935019310",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 3,
      "careerId": 1
  },
  {
      "name": "MARTHA ESPERANZA SEVILLA ABARCA",
      "email": "martha.esperanza.sevilla.abarca@uta.edu.ec",
      "password": "qfqdmhje",
      "address": "Av. 6 de Diciembre",
      "phoneNumber": "0925512163",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 5,
      "careerId": 1
  },
  {
      "name": "JOSE VICENTE MORALES LOZADA",
      "email": "jose.vicente.morales.lozada@uta.edu.ec",
      "password": "v5hk97ku",
      "address": "Avenida 6 de Diciembre y Av. 12 de Octubre",
      "phoneNumber": "0910200467",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 13,
      "careerId": 1
  },
  {
      "name": "FREDDY GEOVANNY BENALCAZAR PALACIOS",
      "email": "freddy.geovanny.benalcazar.palacios@uta.edu.ec",
      "password": "2ltrff4f",
      "address": "Calle 12 de Octubre y Av. 6 de Diciembre",
      "phoneNumber": "0915645367",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 5,
      "careerId": 1
  },
  {
      "name": "PERCY ALBERTO RIOS VILLACORTA",
      "email": "percy.alberto.rios.villacorta@uta.edu.ec",
      "password": "edorn7ta",
      "address": "Av. 6 de Diciembre",
      "phoneNumber": "0961724376",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 3,
      "careerId": 1
  },
  {
      "name": "FABIAN RODRIGO SALAZAR ESCOBAR",
      "email": "fabian.rodrigo.salazar.escobar@uta.edu.ec",
      "password": "9k55c9ce",
      "address": "Calle 12 de Octubre y Av. 6 de Diciembre",
      "phoneNumber": "0981730222",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 10,
      "careerId": 1
  },
  {
      "name": "JAIME RODRIGO GUILCAPI MOSQUERA",
      "email": "jaime.rodrigo.guilcapi.mosquera@uta.edu.ec",
      "password": "4bmr85ju",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0982907384",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 12,
      "careerId": 1
  },
  {
      "name": "ELIZABETH PAULINA AYALA BAÑO",
      "email": "elizabeth.paulina.ayala.baño@uta.edu.ec",
      "password": "l1ycq2un",
      "address": "Avenida 78 y Av. 12 de Octubre",
      "phoneNumber": "0936313989",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 17,
      "careerId": 1
  },
  {
      "name": "ANDREA PATRICIA SANCHEZ ZUMBA",
      "email": "andrea.patricia.sanchez.zumba@uta.edu.ec",
      "password": "jghc2thw",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0966733000",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 1,
      "careerId": 1
  },
  {
      "name": "VICTOR SANTIAGO MANZANO VILLAFUERTE",
      "email": "victor.santiago.manzano.villafuerte@uta.edu.ec",
      "password": "rdbpy3w9",
      "address": "Avenida 6 de Diciembre y Av. 12 de Octubre",
      "phoneNumber": "0946347755",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 6,
      "careerId": 1
  },
  {
      "name": "SANTIAGO MAURICIO ALTAMIRANO MELENDEZ",
      "email": "santiago.mauricio.altamirano.melendez@uta.edu.ec",
      "password": "2fh5dj47",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0952258677",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 1,
      "careerId": 1
  },
  {
      "name": "ANA PAMELA CASTRO MARTIN",
      "email": "ana.pamela.castro.martin@uta.edu.ec",
      "password": "o1n6b1qh",
      "address": "Corredor interprovincial",
      "phoneNumber": "0987187225",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 21,
      "careerId": 1
  },
  {
      "name": "EDGAR FREDDY ROBALINO PEÑA",
      "email": "edgar.freddy.robalino.peña@uta.edu.ec",
      "password": "ijw45yip",
      "address": "Av. 10 de Agosto",
      "phoneNumber": "0960271042",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 1,
      "careerId": 1
  },
  {
      "name": "CARLOS DIEGO GORDON GALLEGOS",
      "email": "carlos.diego.gordon.gallegos@uta.edu.ec",
      "password": "2qbd9dkm",
      "address": "Calle 12 de Octubre y Av. 6 de Diciembre",
      "phoneNumber": "0996722642",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 2,
      "careerId": 1
  },
  {
      "name": "MARIO GEOVANNY GARCIA CARRILLO",
      "email": "mario.geovanny.garcia.carrillo@uta.edu.ec",
      "password": "9kvctqsn",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "092024288",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 18,
      "careerId": 1
  },
  {
      "name": "EDGAR PATRICIO CORDOVA CORDOVA",
      "email": "edgar.patricio.cordova.cordova@uta.edu.ec",
      "password": "4iktf3at",
      "address": "Calle 12 de Octubre y Av. 6 de Diciembre",
      "phoneNumber": "0920917237",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 3,
      "careerId": 1
  },
  {
      "name": "MARCO ANTONIO JURADO LOZADA",
      "email": "marco.antonio.jurado.lozada@uta.edu.ec",
      "password": "vctclo0r",
      "address": "Avenida 78 y Av. 12 de Octubre",
      "phoneNumber": "0975260152",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 18,
      "careerId": 1
  },
  {
      "name": "GEOVANNI DANILO BRITO MONCAYO",
      "email": "geovanni.danilo.brito.moncayo@uta.edu.ec",
      "password": "de9wt73u",
      "address": "Av. 9 de Diciembre",
      "phoneNumber": "0953129085",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 4,
      "careerId": 1
  },
  {
      "name": "JULIO ENRIQUE CUJI RODRIGUEZ",
      "email": "julio.enrique.cuji.rodriguez@uta.edu.ec",
      "password": "y17ivd54",
      "address": "Avenida 6 de Diciembre y Av. 12 de Octubre",
      "phoneNumber": "0986557483",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 7,
      "careerId": 1
  },
  {
      "name": "JUAN PABLO PALLO NOROÑA",
      "email": "juan.pablo.pallo.noroña@uta.edu.ec",
      "password": "c0l10vwk",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0984305905",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 23,
      "careerId": 1
  },
  {
      "name": "JUAN CAMILO ESCOBAR NARANJO",
      "email": "juan.camilo.escobar.naranjo@uta.edu.ec",
      "password": "cj5bkwzd",
      "address": "Corredor interprovincial",
      "phoneNumber": "0933479949",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 3,
      "careerId": 3
  },
  {
      "name": "GLADYS ISABEL TITUAÑA PULLUQUITIN",
      "email": "gladys.isabel.tituaña.pulluquitin@uta.edu.ec",
      "password": "26zncn0e",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0991681040",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 15,
      "careerId": 3
  },
  {
      "name": "ANITA LUCIA LARREA BUSTOS",
      "email": "anita.lucia.larrea.bustos@uta.edu.ec",
      "password": "v81he5a5",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0926370862",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 19,
      "careerId": 3
  },
  {
      "name": "PABLO RAFAEL MUÑOZ VALVERDE",
      "email": "pablo.rafael.muñoz.valverde@uta.edu.ec",
      "password": "bvyae8q8",
      "address": "Av. 10 de Agosto",
      "phoneNumber": "0955325148",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 17,
      "careerId": 3
  },
  {
      "name": "PAULO CESAR TORRES ABRIL",
      "email": "paulo.cesar.torres.abril@uta.edu.ec",
      "password": "hufpsm2k",
      "address": "Avenida 6 de Diciembre y Av. 12 de Octubre",
      "phoneNumber": "0916106445",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 4,
      "careerId": 3
  },
  {
      "name": "LEONARDO DAVID TORRES VALVERDE",
      "email": "leonardo.david.torres.valverde@uta.edu.ec",
      "password": "e4uoesgd",
      "address": "Av. 9 de Diciembre",
      "phoneNumber": "0933807154",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 2,
      "careerId": 3
  },
  {
      "name": "MARCO VINICIO GUACHIMBOZA VILLALVA",
      "email": "marco.vinicio.guachimboza.villalva@uta.edu.ec",
      "password": "hd45l624",
      "address": "Corredor interprovincial",
      "phoneNumber": "0928386470",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 22,
      "careerId": 3
  },
  {
      "name": "MARITZA ELIZABETH CASTRO MAYORGA",
      "email": "maritza.elizabeth.castro.mayorga@uta.edu.ec",
      "password": "p5ndgm6l",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0975462718",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 13,
      "careerId": 3
  },
  {
      "name": "FELIX OSCAR FERNANDEZ PEÑA",
      "email": "felix.oscar.fernandez.peña@uta.edu.ec",
      "password": "q9881sei",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0986125962",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 7,
      "careerId": 3
  },
  {
      "name": "FRANKLIN WILFRIDO SALAZAR LOGROÑO",
      "email": "franklin.wilfrido.salazar.logroño@uta.edu.ec",
      "password": "6fhpduwk",
      "address": "Calle 12 de Octubre y Av. 6 de Diciembre",
      "phoneNumber": "0929568542",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 15,
      "careerId": 3
  },
  {
      "name": "MARLON ANTONIO SANTAMARIA VILLACIS",
      "email": "marlon.antonio.santamaria.villacis@uta.edu.ec",
      "password": "iowadeap",
      "address": "Avenida siempre viva y Av. 12 de Octubre",
      "phoneNumber": "0980568085",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 15,
      "careerId": 3
  },
  {
      "name": "DENNIS VINICIO CHICAIZA CASTILLO",
      "email": "dennis.vinicio.chicaiza.castillo@uta.edu.ec",
      "password": "infmkl12",
      "address": "Av. 6 de Diciembre",
      "phoneNumber": "0971535142",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 12,
      "careerId": 3
  },
  {
      "name": "EDWIN HERNANDO BUENAÑO VALENCIA",
      "email": "edwin.hernando.buenaño.valencia@uta.edu.ec",
      "password": "3kd76lsb",
      "address": "Avenida 78 y Av. 12 de Octubre",
      "phoneNumber": "0924630735",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 21,
      "careerId": 3
  },
  {
      "name": "FRANKLIN OSWALDO MAYORGA MAYORGA",
      "email": "franklin.oswaldo.mayorga.mayorga@uta.edu.ec",
      "password": "u20xzgqy",
      "address": "Av. 6 de Diciembre",
      "phoneNumber": "0927642622",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 5,
      "careerId": 3
  },
  {
      "name": "BOLIVAR EFRAIN MORALES OÑATE",
      "email": "bolivar.efrain.morales.oñate@uta.edu.ec",
      "password": "fbrweazb",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0998164524",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 16,
      "careerId": 3
  },
  {
      "name": "HERNAN FABRICIO NARANJO AVALOS",
      "email": "hernan.fabricio.naranjo.avalos@uta.edu.ec",
      "password": "d75vdr2i",
      "address": "Avenida siempre viva y Av. 12 de Octubre",
      "phoneNumber": "0982410397",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 7,
      "careerId": 3
  },
  {
      "name": "DAVID OMAR GUEVARA AULESTIA",
      "email": "david.omar.guevara.aulestia@uta.edu.ec",
      "password": "g0lod3p8",
      "address": "Av. 6 de Diciembre",
      "phoneNumber": "0965202347",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 13,
      "careerId": 3
  },
  {
      "name": "OSCAR FERNANDO IBARRA TORRES",
      "email": "oscar.fernando.ibarra.torres@uta.edu.ec",
      "password": "09pw0rwq",
      "address": "Avenida siempre viva y Av. 12 de Octubre",
      "phoneNumber": "0997653469",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 18,
      "careerId": 3
  },
  {
      "name": "SANDRA LUCRECIA CARRILLO RIOS",
      "email": "sandra.lucrecia.carrillo.rios@uta.edu.ec",
      "password": "59ltyopa",
      "address": "Corredor interprovincial",
      "phoneNumber": "0928566452",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 12,
      "careerId": 3
  },
  {
      "name": "ELSA PILAR URRUTIA URRUTIA",
      "email": "elsa.pilar.urrutia.urrutia@uta.edu.ec",
      "password": "ousqw3wz",
      "address": "Av. 6 de Diciembre",
      "phoneNumber": "099751251",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 21,
      "careerId": 3
  },
  {
      "name": "VICTOR HUGO GUACHIMBOSA VILLALBA",
      "email": "victor.hugo.guachimbosa.villalba@uta.edu.ec",
      "password": "56ytrhio",
      "address": "Avenida siempre viva y Av. 12 de Octubre",
      "phoneNumber": "0980947690",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 10,
      "careerId": 3
  },
  {
      "name": "EDISON HOMERO ALVAREZ MAYORGA",
      "email": "edison.homero.alvarez.mayorga@uta.edu.ec",
      "password": "49tpma0n",
      "address": "Avenida siempre viva y Av. 12 de Octubre",
      "phoneNumber": "0969716924",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 6,
      "careerId": 3
  },
  {
      "name": "CLAY FERNANDO ALDAS FLORES",
      "email": "clay.fernando.aldas.flores@uta.edu.ec",
      "password": "xz7164v7",
      "address": "Corredor interprovincial",
      "phoneNumber": "0913854484",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 13,
      "careerId": 3
  },
  {
      "name": "CARLOS ISRAEL NUÑEZ MIRANDA",
      "email": "carlos.israel.nuñez.miranda@uta.edu.ec",
      "password": "8fo7hrgb",
      "address": "Calle 12 de Octubre y Av. 6 de Diciembre",
      "phoneNumber": "0974157325",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 17,
      "careerId": 3
  },
  {
      "name": "JULIO ENRIQUE BALAREZO LOPEZ",
      "email": "julio.enrique.balarezo.lopez@uta.edu.ec",
      "password": "tpsb8q1c",
      "address": "Corredor interprovincial",
      "phoneNumber": "0984440081",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 3,
      "careerId": 3
  },
  {
      "name": "RUBEN EDUARDO NOGALES PORTERO",
      "email": "ruben.eduardo.nogales.portero@uta.edu.ec",
      "password": "9a89aeir",
      "address": "Avenida 6 de Diciembre y Av. 12 de Octubre",
      "phoneNumber": "0913352047",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 3,
      "careerId": 3
  },
  {
      "name": "ANGEL MAURICIO CARRANZA GARCES",
      "email": "angel.mauricio.carranza.garces@uta.edu.ec",
      "password": "cu9v8j71",
      "address": "Avenida 78 y Av. 12 de Octubre",
      "phoneNumber": "093386196",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 23,
      "careerId": 4
  },
  {
      "name": "JAIME BOLIVAR RUIZ BANDA",
      "email": "jaime.bolivar.ruiz.banda@uta.edu.ec",
      "password": "76usw8xd",
      "address": "Avenida 78 y Av. 12 de Octubre",
      "phoneNumber": "0966281860",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 15,
      "careerId": 4
  },
  {
      "name": "DAYSI MARGARITA ORTIZ GUERRERO",
      "email": "daysi.margarita.ortiz.guerrero@uta.edu.ec",
      "password": "zlyxp212",
      "address": "Avenida siempre viva y Av. 12 de Octubre",
      "phoneNumber": "091228333",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 5,
      "careerId": 4
  },
  {
      "name": "CARLOS HUMBERTO SANCHEZ ROSERO",
      "email": "carlos.humberto.sanchez.rosero@uta.edu.ec",
      "password": "ub5e3b9w",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0921425459",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 11,
      "careerId": 4
  },
  {
      "name": "CHRISTIAN ISMAEL ORTIZ SAILEMA",
      "email": "christian.ismael.ortiz.sailema@uta.edu.ec",
      "password": "gs6qqxmc",
      "address": "Av. 6 de Diciembre",
      "phoneNumber": "0924115222",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 8,
      "careerId": 4
  },
  {
      "name": "JOSE LUIS GAVIDIA GARCIA",
      "email": "jose.luis.gavidia.garcia@uta.edu.ec",
      "password": "5rkdezid",
      "address": "Corredor interprovincial",
      "phoneNumber": "0939188527",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 16,
      "careerId": 4
  },
  {
      "name": "FREDDY ROBERTO LEMA CHICAIZA",
      "email": "freddy.roberto.lema.chicaiza@uta.edu.ec",
      "password": "sh83ovjc",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0992334063",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 19,
      "careerId": 4
  },
  {
      "name": "JOSE EZEQUIEL NARANJO ROBALINO",
      "email": "jose.ezequiel.naranjo.robalino@uta.edu.ec",
      "password": "rxlp9bp8",
      "address": "Corredor interprovincial",
      "phoneNumber": "0954104679",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 2,
      "careerId": 4
  },
  {
      "name": "EDITH ELENA TUBÓN NÚÑEZ",
      "email": "edith.elena.tubón.núñez@uta.edu.ec",
      "password": "1603stcu",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0970908209",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 16,
      "careerId": 4
  },
  {
      "name": "EDISSON PATRICIO JORDAN HIDALGO",
      "email": "edisson.patricio.jordan.hidalgo@uta.edu.ec",
      "password": "yj0k3r0h",
      "address": "Corredor interprovincial",
      "phoneNumber": "0999936371",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 17,
      "careerId": 4
  },
  {
      "name": "FRANKLIN GEOVANNY TIGRE ORTEGA",
      "email": "franklin.geovanny.tigre.ortega@uta.edu.ec",
      "password": "vzdrftie",
      "address": "Avenida 6 de Diciembre y Av. 12 de Octubre",
      "phoneNumber": "0940642246",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 4,
      "careerId": 4
  },
  {
      "name": "LUIS ALBERTO MORALES PERRAZO",
      "email": "luis.alberto.morales.perrazo@uta.edu.ec",
      "password": "khqr8f9g",
      "address": "Av. 9 de Diciembre",
      "phoneNumber": "0996934493",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 8,
      "careerId": 4
  },
  {
      "name": "CESAR ANIBAL ROSERO MANTILLA",
      "email": "cesar.anibal.rosero.mantilla@uta.edu.ec",
      "password": "7q01013m",
      "address": "Avenida 6 de Diciembre y Av. 12 de Octubre",
      "phoneNumber": "0963294543",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 1,
      "careerId": 4
  },
  {
      "name": "FERNANDO URRUTIA URRUTIA",
      "email": "fernando.urrutia.urrutia@uta.edu.ec",
      "password": "61p5umyp",
      "address": "Av. 9 de Diciembre",
      "phoneNumber": "0914905312",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 17,
      "careerId": 4
  },
  {
      "name": "JOHN PAUL REYES VASQUEZ",
      "email": "john.paul.reyes.vasquez@uta.edu.ec",
      "password": "h1g4e1aa",
      "address": "Avenida 78 y Av. 12 de Octubre",
      "phoneNumber": "0922549060",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 15,
      "careerId": 4
  },
  {
      "name": "ISRAEL ERNESTO NARANJO CHIRIBOGA",
      "email": "israel.ernesto.naranjo.chiriboga@uta.edu.ec",
      "password": "jqarx48l",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0960640279",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 12,
      "careerId": 4
  },
  {
      "name": "CHRISTIAN JOSE MARIÑO RIVERA",
      "email": "christian.jose.mariño.rivera@uta.edu.ec",
      "password": "ggen9moa",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0985375336",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 2,
      "careerId": 4
  },
  {
      "name": "JESSICA PAOLA LOPEZ ARBOLEDA",
      "email": "jessica.paola.lopez.arboleda@uta.edu.ec",
      "password": "kxg1md3k",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "095836934",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 12,
      "careerId": 4
  },
  {
      "name": "MARCELO VLADIMIR GARCIA SANCHEZ",
      "email": "marcelo.vladimir.garcia.sanchez@uta.edu.ec",
      "password": "kdkp81nm",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "0960391638",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 14,
      "careerId": 4
  },
  {
      "name": "MARCOS RAPHAEL BENITEZ ALDAS",
      "email": "marcos.raphael.benitez.aldas@uta.edu.ec",
      "password": "njzvcfzk",
      "address": "Av. 12 de Octubre",
      "phoneNumber": "0921579985",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 20,
      "careerId": 2
  },
  {
      "name": "SANTIAGO DAVID JARA MOYA",
      "email": "santiago.david.jara.moya@uta.edu.ec",
      "password": "h9xpxbbi",
      "address": "Calle 13 de Mayo y Av. 6 de Diciembre",
      "phoneNumber": "095340811",
      "image": "image url",
      "rol": "TEACHER",
      "cityId": 17,
      "careerId": 2
  }
]