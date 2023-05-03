package com.example.Integrador_Amaya_Nicolas_Agustin.repository.implement;

import com.example.Clase28.model.Odontologo;
import com.example.Clase28.repository.Idao;
import com.example.Clase28.service.BaseDato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements Idao<Odontologo> {
    BaseDato BBDD = new BaseDato();
    @Override
    public Odontologo agregar(Odontologo odontologo) {
        Connection connection = BBDD.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.insert("ODONTOLOGOS"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,odontologo.getMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                odontologo.setId(resultSet.getLong(1));
            }
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = BBDD.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.delete("ODONTOLOGOS"));
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Odontologo listar(Long id) {
        Connection connection = BBDD.connection();
        Odontologo odontologoEncontrado = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.select("ODONTOLOGOS"));
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                odontologoEncontrado = new Odontologo(rs.getLong(2) ,
                        rs.getString(3) , rs.getString(4));
                odontologoEncontrado.setId(id);
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return odontologoEncontrado;
    }
    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = BBDD.connection();
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.selectAll("ODONTOLOGOS"));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                odontologos.add(listar(rs.getLong(1)));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return odontologos;
    }

    @Override
    public Odontologo modificar(Long id, Odontologo odontologo) {
        Connection connection = BBDD.connection();
        Odontologo odontologoModificado = listar(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.update("ODONTOLOGOS"));
            //aca vienen los parametros del body
            preparedStatement.setLong(1,odontologo.getMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.setLong(4, id);
            //ResultSet result = preparedStatement.executeQuery();
            preparedStatement.execute();

            ResultSet result = preparedStatement.getGeneratedKeys();
            while (result.next()) {
                //aca modifico el objeto encontrado, le seteo los nuevos valores enviados por el body
                odontologoModificado.setMatricula(result.getLong(2));
                odontologoModificado.setNombre(result.getString(3));
                odontologoModificado.setApellido(result.getString(4));
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return odontologoModificado;
    }
}
