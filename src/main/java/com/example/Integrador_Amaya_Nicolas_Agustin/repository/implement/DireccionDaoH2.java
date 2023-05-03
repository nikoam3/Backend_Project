package com.example.Integrador_Amaya_Nicolas_Agustin.repository.implement;

import com.example.Clase28.model.Direccion;
import com.example.Clase28.repository.Idao;
import com.example.Clase28.service.BaseDato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionDaoH2 implements Idao<Direccion> {
    BaseDato BBDD = new BaseDato();
    @Override
    public Direccion agregar(Direccion direccion) {
        Connection connection = BBDD.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.insert("DIRECCIONES"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1 , direccion.getCalle());
            preparedStatement.setString(2 , direccion.getNumero());
            preparedStatement.setString(3 , direccion.getLocalidad());
            preparedStatement.setString(4 , direccion.getProvincia());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                direccion.setId(resultSet.getLong(1));
            }
            preparedStatement.close();
            connection.close();
            return direccion;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = BBDD.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.delete("DIRECCIONES"));
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Direccion listar(Long id) {
        Connection connection = BBDD.connection();
        Direccion direccionEncontrada = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.select("DIRECCIONES"));
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                direccionEncontrada = new Direccion(rs.getString(2) ,
                                                    rs.getString(3) ,
                                                    rs.getString(4) ,
                                                    rs.getString(5));
                direccionEncontrada.setId(id);
            }

            preparedStatement.close();
            connection.close();
            return direccionEncontrada;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Direccion> listarTodos() {
        Connection connection = BBDD.connection();
        List<Direccion> direcciones = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.selectAll("DIRECCIONES"));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                direcciones.add(listar(rs.getLong(1)));
            }
            preparedStatement.close();
            connection.close();
            return direcciones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Direccion modificar(Long id, Direccion direccion) {
        Connection connection = BBDD.connection();
        Direccion direccionModificada = listar(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BBDD.update("DIRECCIONES"));
            //aca vienen los parametros del body
            preparedStatement.setString(1 , direccion.getCalle());
            preparedStatement.setString(2 , direccion.getNumero());
            preparedStatement.setString(3 , direccion.getLocalidad());
            preparedStatement.setString(4 , direccion.getProvincia());
            preparedStatement.setLong(5, id);
            preparedStatement.execute();

            ResultSet result = preparedStatement.getGeneratedKeys();
            while (result.next()) {
                //aca busco el objeto a modificar
                direccionModificada.setCalle(result.getString(2));
                direccionModificada.setNumero(result.getString(3));
                direccionModificada.setLocalidad(result.getString(4));
                direccionModificada.setProvincia(result.getString(5));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return direccionModificada;
    }
}

