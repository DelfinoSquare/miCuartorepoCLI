/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import service.DepartamentoService;

public class App {
    public static void main(String[] args) {
        try {
            DepartamentoService ds = new DepartamentoService();
            ds.inicializarDepartamentos();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Aquí continúa el resto de tu lógica del programa
    }
}



