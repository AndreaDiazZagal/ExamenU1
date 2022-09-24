package client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

public class RPCClient {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        String nombre, curp, primerapellido,
                segundoapellido, sexo, nacimiento, fecha;
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("MENU");
            System.out.println("Seleccione una opción...");
            System.out.println("1. Registrar datos de una persona");
            System.out.println("2. Consultar datos  ");
            System.out.println("3. Salir");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("REGISTRAR DATOS DE UNA PERSONA ");
                    System.out.println("Ingresar el nombre de la persona:");
                    nombre = teclado.next();
                    System.out.println("Ingresar el primer apellido de la persona:");
                    primerapellido = teclado.next();
                    System.out.println("Ingresar el segundo apellido de la persona:");
                    segundoapellido = teclado.next();
                    System.out.println("Ingresar el sexo de la persona de la persona:");
                    System.out.println("Nota poner Hombre o Mujer");
                    sexo = teclado.next();
                    System.out.println("Ingresar por ejemplo de Aguascalientes (AGS): ");
                    System.out.println("Ingresar el estado de nacimiento de la persona: " +
                            "AGS Aguascalientes\n" +
                            "BCN Baja California\n" +
                            "BCS Baja California Sur\n" +
                            "CAM Campeche\n" +
                            "CHS Chiapas\n" +
                            "CHI Chihuahua\n" +
                            "CDMX Ciudad de México\n" +
                            "COA Coahuila\n" +
                            "COL Colima\n" +
                            "DGO Durango\n" +
                            "EM Estado de México\n" +
                            "GTO Guanajuato\n" +
                            "GRO Guerrero\n" +
                            "HGO Hidalgo\n" +
                            "JAL Jalisco\n" +
                            "MICH Michoacán\n" +
                            "MOR Morelos\n" +
                            "NAY Nayarit\n" +
                            "NL Nuevo León\n" +
                            "OAX Oaxaca\n" +
                            "PUE Puebla\n" +
                            "QRO Querétaro\n" +
                            "QR Quintana Roo\n" +
                            "SLP San Luis Potosí\n" +
                            "SIN Sinaloa\n" +
                            "SON Sonora\n" +
                            "TAB Tabasco\n" +
                            "TAM Tamaulipas\n" +
                            "TLAX Tlaxcala\n" +
                            "VER Veracruz\n" +
                            "YUC Yucatán\n" +
                            "ZAC Zacatecas");
                    nacimiento = teclado.next();
                    System.out.println("Ingresar la fecha de nacimiento de la persona: ");
                    fecha = teclado.next();
                    //Ejecución del método en el servidor.
                    Object[] data = {nombre, primerapellido, segundoapellido, sexo,
                            nacimiento, fecha};
                    String response = (String) client.execute("Methods.registrar", data);
                    System.out.println(response);
                    break;
                case 2:
                    System.out.println("CONSULTAR DATOS DE UNA PERSONA POR MEDIO DE LA CURP");
                    System.out.println("Ingresar EL CURP de la persona que desea buscar:");
                    curp = teclado.next();
                    String query = "";
                    //Ejecución del método en el servidor.
                    Object[] data2 = {"a"};
                    String response1 = (String) client.execute("Methods.consulta", data2);
                    System.out.println(response1);
                    break;

                case 3:
                    System.out.println("Bye....");
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        }
    }
}