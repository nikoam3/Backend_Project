window.addEventListener('load', function () {

    cargaSelectPacientes();
    cargaSelectOdontologos();

    const formulario = document.querySelector('#add_new_turno');

    formulario.addEventListener('submit', function (event) {
        const indexPaciente = document.querySelector('#paciente_select').value
        const indexOdontologo = document.querySelector('#odontologo_select').value
        const formData = {
            fechaHora: document.querySelector('#fechaHora').value,
            paciente: {id: indexPaciente},
            odontologo: {id: indexOdontologo}
            };

        const url = '/turno';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno agregado correctamente</div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";
                   //resetUploadForm()
                   })
    });

    function resetUploadForm(){
        document.querySelector('#fechaHora').value = "";
        //document.querySelector('#nombre').value = "";
        //document.querySelector('#apellido').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/Turno.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
    function cargaSelectPacientes(){
            const url = '/paciente';
            const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            select = document.getElementById("paciente_select");
            for(paciente of data){
                const option = document.createElement('option');
                const valor = paciente.id;
                option.value = valor;
                option.text = paciente.nombre + ' ' + paciente.apellido;
                select.appendChild(option)};
        })};

    function cargaSelectOdontologos(){
        const url = '/odontologo';
        const settings = {
        method: 'GET'
            }
            fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                select = document.getElementById("odontologo_select");
                for(odontologo of data){
                    const option = document.createElement('option');
                    const valor = odontologo.id;
                    option.value = valor;
                    option.text = odontologo.nombre + ' ' + odontologo.apellido;
                    select.appendChild(option)};
            })};
});