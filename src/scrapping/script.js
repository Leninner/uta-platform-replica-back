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
  }
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

// las passwords est'an generadas aleatoriamente
const uniqueTeachers = [
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
]

const teachersWithId = {
    "1": "ALEXANDRA VIRGINIA LASCANO SUMBANA",
}

const coursesToSave = courses.map(course => {
  return course.subjects.map(subject => {
    const teacherID = Number(Object.keys(teachersWithId).find(key => teachersWithId[key] === subject.teacher))
    const careerID = Number(Object.keys(careers).find(key => careers[key] === course.career))
    return {
      name: subject.name,
      semester: course.semester,
      description: subject.description,
      image: 'image random',
      careerId: careerID,
      teacherId: teacherID
    }
  })
})

copy(coursesToSave.flat())
console.log(coursesToSave)


const coursesToDB = [
  {
      "name": "Química - B",
      "semester": "Nivelación",
      "description": "",
      "image": "image random",
      "careerId": 1,
      "teacherId": 1
  },
 ]
