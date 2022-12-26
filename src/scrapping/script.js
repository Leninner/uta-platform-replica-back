const semesters = document.getElementsByClassName('category with_children loaded')
const careers = ['Telecomunicaciones', 'Tecnologías de la Información', 'Ingeniería Industrial', 'Software']
const childBySemester = Array.from(semesters)
  .map(semester => {
    return {
      career: careers.find(career => semester.children[0].innerText.includes(career)),
      semester: semester.children[0].innerText,
      subjects: Array.from(semester.children[1].children[0].children).map(subject => {
        console.log(subject.children[1].children, semester.children[0].innerText)
        const name = subject.children[0].innerText
        const hasDescription = subject.children[1].children.length > 1

        if (hasDescription) {
          const description = subject.children[1].children[0].innerText
          const teacher = subject.children[1].children[1].innerText
          return { name, description, teacher }
        }

        const teacher = subject.children[1].children[0].innerText

        return { name, teacher, description: '' }
      })
    }
  }
  )
copy(childBySemester)


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
  {
    "semester": "Complexivos",
    "subjects": [
      {
        "name": "Complexivo - TI",
        "teacher": "MARITZA ELIZABETH CASTRO MAYORGA",
        "description": ""
      }
    ]
  }
]