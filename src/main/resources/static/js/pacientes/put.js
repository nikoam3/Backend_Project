window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {

    formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaAlta: document.querySelector('#fechaAlta').value,
            direccion:
            {id: document.querySelector('#paciente_direccion_id').value,
             domicilio: document.querySelector('#domicilio').value,
             localidad: document.querySelector('#localidad').value,
             provincia: document.querySelector('#provincia').value}
        };

        const url = '/paciente';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
    const formularioDireccion = document.querySelector('#update_paciente_direccion_form');
        formularioDireccion.addEventListener('submit', function (event) {
            const formData = {
                id: document.querySelector('#paciente_direccion_id').value,
                domicilio: document.querySelector('#domicilio').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            };

            const url = '/direccion';
            const settings = {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }
              fetch(url,settings)
              .then(response => response.json())
        })
 })

    function findBy(id) {
          const url = "/paciente/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(paciente => {
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#nombre').value = paciente.nombre;
              document.querySelector('#apellido').value = paciente.apellido;
              document.querySelector('#dni').value = paciente.dni,
              document.querySelector('#fechaAlta').value = paciente.fechaAlta;

              document.querySelector('#div_paciente_updating').style.display = "block";
              findByDireccion(paciente.id);
              document.querySelector('#div_paciente_direccion_updating').style.display = "block";

          }).catch(error => {
              alert("Error: " + error);
          })
      }
  function findByDireccion(id) {
            const url = "/paciente/"+id;
            const settings = {
                method: 'GET'
            }
            fetch(url,settings)
            .then(response => response.json())
            .then(paciente => {
                document.querySelector('#paciente_direccion_id').value = paciente.direccion.id;
                document.querySelector('#domicilio').value = paciente.direccion.domicilio;
                document.querySelector('#localidad').value = paciente.direccion.localidad;
                document.querySelector('#provincia').value = paciente.direccion.provincia,

                document.querySelector('#div_paciente_direccion_updating').style.display = "block";
            }).catch(error => {
                alert("Error: " + error);
            })
}