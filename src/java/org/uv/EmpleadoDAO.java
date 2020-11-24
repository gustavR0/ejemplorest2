package org.uv;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private static EmpleadoDAO instance;
    private static List<Empleado> data = new ArrayList<>();

    static {

        data.add(new Empleado("01", "fulanito", "av 1", "32165556"));
        data.add(new Empleado("02", "fulanita", "av 3", "12324124"));
    }

    private EmpleadoDAO() {

    }

    public static EmpleadoDAO getInstance() {
        if (instance == null) {
            instance = new EmpleadoDAO();
        }

        return instance;
    }

    public List<Empleado> listAll() {
        return new ArrayList<Empleado>(data);
    }

    public String add(Empleado empleado) {
        String newId = "" + data.size() + 1;
        empleado.setId(newId);
        data.add(empleado);

        return newId;
    }

    public Empleado get(String id) {
        Empleado empleadoToFind = new Empleado(id);
        int index = data.indexOf(empleadoToFind);
        if (index >= 0) {
            return data.get(index);
        }
        return null;
    }

    public boolean delete(String id) {
        Empleado productToFind = new Empleado(id);
        int index = data.indexOf(productToFind);
        if (index >= 0) {
            data.remove(index);
            return true;
        }

        return false;
    }

    public boolean update(Empleado product) {
        int index = data.indexOf(product);
        if (index >= 0) {
            data.set(index, product);
            return true;
        }
        return false;
    }
}
