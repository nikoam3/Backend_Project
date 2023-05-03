package com.example.Integrador_Amaya_Nicolas_Agustin.repository.implement;

import com.example.Clase28.model.Turno;
import com.example.Clase28.repository.Idao;
import com.example.Clase28.service.BaseDato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDaoH2 implements Idao<Turno> {
    BaseDato BBDD = new BaseDato();
    @Override
    public Turno agregar(Turno turno) {
        Connection connection = BBDD.connection();
        PacienteDaoH2 pacienteDaoH2 = new PacienteDaoH2();
        OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();

        if (turno.getPaciente().getId() == null){
            pacienteDaoH2.agregar(turno.getPaciente());
        }
        if (turno.getOdontologo().getId() == null){
            odontologoDaoH2.agregar(turno.getOdontologo());
        }


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.insert("TURNOS"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,turno.getPaciente().getId());
            preparedStatement.setLong(2,turno.getOdontologo().getId());
            preparedStatement.setString(3, turno.getFechaHora());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                turno.setId(resultSet.getLong(1));
            }
            preparedStatement.close();
            connection.close();
            return turno;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = BBDD.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.delete("TURNOS"));
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Turno listar(Long id) {
        Connection connection = BBDD.connection();
        Turno turnoEncontrado = null;
        PacienteDaoH2 buscarIdPaciente = new PacienteDaoH2();
        OdontologoDaoH2 buscarIdOdontologo= new OdontologoDaoH2();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.select("TURNOS"));
            preparedStatement.setLong(1,id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                turnoEncontrado = new Turno(buscarIdPaciente.listar(result.getLong(2)),
                        buscarIdOdontologo.listar(result.getLong(3)), result.getString(4));
                turnoEncontrado.setId(id);
            }
            preparedStatement.close();
            connection.close();

            return turnoEncontrado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Turno> listarTodos() {
        Connection connection = BBDD.connection();
        List<Turno> turnos = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.selectAll("TURNOS"));
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                turnos.add(listar(result.getLong(1)));
            }
            preparedStatement.close();
            connection.close();

            return turnos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Turno modificar(Long id, Turno turno) {
        Connection connection = BBDD.connection();
        PacienteDaoH2 pacienteDaoH2 = new PacienteDaoH2();
        OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();
        Turno turnoModificado = listar(id);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.update("TURNOS"));
            //aca vienen los parametros del body
            preparedStatement.setLong(1,turno.getPaciente().getId());
            preparedStatement.setLong(2,turno.getOdontologo().getId());
            preparedStatement.setString(3, turno.getFechaHora());
            preparedStatement.execute();

            ResultSet result = preparedStatement.getGeneratedKeys();
            while (result.next()) {
                //aca busco el objeto a modificar
                turnoModificado.setPaciente(pacienteDaoH2.listar(result.getLong(2)));
                turnoModificado.setOdontologo(odontologoDaoH2.listar(result.getLong(3)));
                turnoModificado.setFechaHora(result.getString(4));
            }
            preparedStatement.close();
            connection.close();
            return turnoModificado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
