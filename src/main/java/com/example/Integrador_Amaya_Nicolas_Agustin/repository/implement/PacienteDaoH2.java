package com.example.Integrador_Amaya_Nicolas_Agustin.repository.implement;

import com.example.Clase28.model.Paciente;
import com.example.Clase28.repository.Idao;
import com.example.Clase28.service.BaseDato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements Idao<Paciente> {
    BaseDato BBDD = new BaseDato();
    @Override
    public Paciente agregar(Paciente paciente) {
        DireccionDaoH2 direccionDaoH2 = new DireccionDaoH2();

        if (direccionDaoH2.listar(paciente.getDireccion().getId()) == null){
            direccionDaoH2.agregar(paciente.getDireccion());
        }

        Connection connection = BBDD.connection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.insert("PACIENTES"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,paciente.getNombre());
            preparedStatement.setString(2,paciente.getApellido());
            preparedStatement.setString(3,paciente.getDni());
            preparedStatement.setString (4,paciente.getFechaIngreso());
            preparedStatement.setLong(5,paciente.getDireccion().getId());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                paciente.setId(resultSet.getLong(1));
            }
            preparedStatement.close();
            connection.close();
            return paciente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = BBDD.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.delete("PACIENTES"));
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Paciente listar(Long id) {
        Connection connection = BBDD.connection();
        Paciente pacienteEncontrado = null;
        DireccionDaoH2 buscarIdDireccion = new DireccionDaoH2();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.select("PACIENTES"));
            preparedStatement.setLong(1,id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                pacienteEncontrado = new Paciente(result.getString(2), result.getString(3),
                        result.getString(4),result.getString(5),
                        buscarIdDireccion.listar(result.getLong(6)));
                pacienteEncontrado.setId(id);
            }
            preparedStatement.close();
            connection.close();

            return pacienteEncontrado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Paciente> listarTodos() {
        Connection connection = BBDD.connection();
        List<Paciente> pacientes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.selectAll("PACIENTES"));
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                pacientes.add(listar(result.getLong(1)));
            }
            preparedStatement.close();
            connection.close();

            return pacientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Paciente modificar(Long id, Paciente paciente) {
        Connection connection = BBDD.connection();
        Paciente pacienteModificado = listar(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.update("PACIENTES"));
            //aca vienen los parametros del body
            preparedStatement.setString(1,paciente.getNombre());
            preparedStatement.setString(2,paciente.getApellido());
            preparedStatement.setString(3,paciente.getDni());
            preparedStatement.setString (4,paciente.getFechaIngreso());
            preparedStatement.setLong(5, id);
            preparedStatement.execute();

            ResultSet result = preparedStatement.getGeneratedKeys();
            while (result.next()) {
                //aca busco el objeto a modificar
                pacienteModificado.setNombre(result.getString(2));
                pacienteModificado.setApellido(result.getString(3));
                pacienteModificado.setDni(result.getString(4));
                pacienteModificado.setFechaIngreso(result.getString(5));
            }
            preparedStatement.close();
            connection.close();
            return pacienteModificado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

