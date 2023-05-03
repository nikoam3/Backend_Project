window.addEventListener('load', function (){

    (function(){
      const url = '/paciente';
      const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
         for(Paciente of data){

          var table = document.getElementById("paciente_table");
          var PacienteRow = table.insertRow();
          let tr_id = 'tr_' + Paciente.id;
          PacienteRow.id = tr_id;

          let deleteButton = '<button' +
                                    ' id=' + '\"' + 'btn_delete_' + Paciente.id + '\"' +
                                    ' type="button" onclick="deleteBy('+Paciente.id+')" class="btn btn-danger btn_delete">' +
                                    '&times' +
                                    '</button>';

          let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + Paciente.id + '\"' +
                                      ' type="button" onclick="findBy('+Paciente.id+')" class="btn btn-info btn_id">' +
                                      Paciente.id +
                                      '</button>';
          let direccionButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + Paciente.id + '\"' +
                                      ' type="button" onclick="findByDireccion('+Paciente.id+')" class="btn btn-info btn_id">' +
                                      '</button>';

         PacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                              '<td class=\"td_dni\">' + Paciente.dni + '</td>' +
                              '<td class=\"td_nombre\">' + Paciente.nombre + '</td>' +
                              '<td class=\"td_apellido\">' + Paciente.apellido + '</td>' +
                              '<td class=\"td_fecha_alta\">' + Paciente.fechaAlta + '</td>' +
                              '<td>' + direccionButton + '</td>' +
                              '<td>' + deleteButton + '</td>';

        };

})
})

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/Pacientes.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})


})