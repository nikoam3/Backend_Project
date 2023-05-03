window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_turno_form');
    formulario.addEventListener('submit', function (event) {

        const formData = {
            id: document.querySelector('#turno_id').value,
            fechaHora: document.querySelector('#fechaHora').value,
            paciente: {id: document.querySelector('#paciente_select').value},
            odontologo: {id: document.querySelector('#odontologo_select').value}
        };

        const url = '/turno';
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
          const url = "/turno/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(turno => {
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#fechaHora').value = turno.fechaHora;
              document.querySelector('#paciente_select').value = turno.paciente.id;
              document.querySelector('#odontologo_select').value = turno.odontologo.id

              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }