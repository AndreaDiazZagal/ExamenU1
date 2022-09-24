package server;

import modal.BeanExam;
import modal.DaoExam;

import java.sql.Date;
import java.util.ArrayList;

public class Methods {
    DaoExam daoExam = new DaoExam();
    private String curp;
    static String getRambomString(int i) {

        StringBuilder builder;
        String theAlphaNumbricS;

        theAlphaNumbricS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        builder = new StringBuilder(i);
        for (int m = 0; m < i; m++) {
            //generate numeric
            int myindex
                    = (int) (theAlphaNumbricS.length() * Math.random());
            //add the characters
            builder.append(theAlphaNumbricS.charAt(myindex));
        }
        return builder.toString();
    }


    public String registrar(String nombre, String primerapellido, String segundoapellido,
                            String sexo, String nacimiento, String fecha) {

        this.curp = String.valueOf(primerapellido.charAt(0) + "" + (primerapellido.charAt(1)) + "" +
                (segundoapellido.charAt(0)) + "" + (nombre.charAt(0))+"" +(fecha.charAt(2))+ "" +(fecha.charAt(3))+""+
                (fecha.charAt(5))+""+(fecha.charAt(6))+""+(fecha.charAt(8))+""+(fecha.charAt(9))+""+(sexo.charAt(0))
                + "" + (nacimiento.charAt(0)) + "" + (nacimiento.charAt(1)) +""+
                getRambomString(0)+""+getRambomString(1));
        dao(nombre, primerapellido, segundoapellido, sexo, nacimiento, Date.valueOf(fecha), curp);
        return "su curp es: " + curp;
    }
    public String encontrar(String a){

        ArrayList<BeanExam> aux = daoExam.showRegistrar();
        String response = "";
        for (BeanExam persona: aux){
            response +=  "Nombre: " + persona.getNombre() + "\n"
                    + "Primer apellido: " + persona.getPrimerapellido() + "\n"
                    + "Segundo apellido: " + persona.getSegundoapellido() + "\n"
                    + "Sexo: " + persona.getSexo() + "\n"
                    + "Estado de nacimiento: " + persona.getNacimiento() + "\n"
                    + "Fecha de nacimiento: " + persona.getFecha() + "\n"
                    + "Curp: " + persona.getCurp() + "\n";
        }

        return response;
    }
    public void dao(String nombre, String primerapellido, String segundoapellido, String sexo, String nacimiento, Date fecha, String curp){
        daoExam.saveRegistro(nombre, primerapellido, segundoapellido, sexo, nacimiento, fecha, curp);
    }
}


