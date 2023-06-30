import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.*;

public class crud {
    String clasificador;

    crud(String archivo) throws IOException {
        this.clasificador = archivo;
    }

    String Doc(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite su documento de identidad: ");
        return input.nextLine().strip();
    }

    String Repertorio(String doc) throws IOException {
        String linea;
        BufferedReader br = new BufferedReader(new FileReader(clasificador));
        while((linea = br.readLine()) != null){
            if (linea.contains("Cedula: " + doc+",")) {
                return linea;
            }
        }
        return null;
    }

    void Exis(String repertorio) {
        System.out.println(repertorio);
        System.out.println("Alguna Tecla para continuar");
        new Scanner(System.in).nextLine();
    }

    void Exis_2(String repertorio,String doc){
        if (repertorio==null){
            System.out.println("No se encontro el contacto con documento: "+doc);
            System.out.println("Alguna tecla para continuar");
            new Scanner(System.in).nextLine();
        } else{Exis(repertorio);}
    }

    void consulta() throws IOException {
        String doc = Doc();
        String repertorio = Repertorio(doc);
        Exis_2(repertorio,doc);
    }

    void add() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(clasificador,true));
        Scanner input = new Scanner(System.in);
        System.out.println("El documento del contacto no podrá ser editado luego ");
        String doc = Doc();
        String repertorio = Repertorio(doc);
        if(repertorio != null){
            System.out.println("Se encontro un contacto con el documento:  "+doc);
            Exis(repertorio);
            return;
        }
        System.out.println("Digite el nombre: ");
        String nom = input.nextLine().strip();
        System.out.println("Digite el apellido: ");
        String ape = input.nextLine().strip();
        bw.write(new Datos(doc,nom,ape).resumen());
        bw.newLine();
        System.out.println("Contacto añadido");
        System.out.println("Alguna tecla para continuar");
        input.nextLine();
        bw.close();
    }

    void delete() throws IOException {
        Scanner scan = new Scanner(System.in);
        String doc = Doc();
        String repertorio = Repertorio(doc);
        if(repertorio == null){
            Exis_2(null,doc);
            return;
        }else{
            Exis(repertorio);
        }
        System.out.println("Desea Eliminar?\n 1: Si \n Otro num: No ");
        if (Menu.rOpcion() != 1) {
            return;
        }
        editxt(repertorio,"");
        System.out.println("Contacto Eliminado");
        System.out.println("Alguna Tecla para continuar");
        scan.nextLine();
    }

    void edit() throws IOException {
        String doc = Doc();
        String repertorio = Repertorio(doc);
        if(repertorio == null){
            Exis_2(null,doc);
            return;
        }else{
            Exis(repertorio);
        }
        System.out.println("Desea editar registro?\n 1: Si \n Otro num: No ");
        if (Menu.rOpcion() != 1) {
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Digite el nombre: ");
        String nom = input.nextLine().strip();
        System.out.println("Digite el apellido: ");
        String ape = input.nextLine().strip();
        String cambio = new Datos(doc,nom,ape).resumen();
        editxt(repertorio,cambio);
        System.out.println("Contacto Editado");
        System.out.println("Alguna Tecla para continuar");
    }

    private void editxt(String registro,String cambio) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(clasificador));
        StringBuilder antiguo = new StringBuilder();
        String linea = br.readLine();
        while (linea != null) {
            antiguo.append(linea).append(System.lineSeparator());
            linea = br.readLine();
        }
        String content = antiguo.toString();
        String nuevo = content.replaceAll(registro,cambio);
        try (FileWriter writer = new FileWriter(clasificador)) {
            writer.write(nuevo);
        }
    }

    public void mostrar() throws IOException {
        String contenido = new String(Files.readAllBytes(Path.of(clasificador)));
        String line = contenido.strip();
        Scanner scan = new Scanner(System.in);
        System.out.println(line.replaceAll("(?m)^[ \t]*\r?\n", ""));
        System.out.println("Alguna Tecla para continuar");
        scan.nextLine();
        System.out.println();
    }


    void menu() throws IOException {
        while(true){
            System.out.println("Que desea hacer con "+clasificador+": \n 1: Añadir dato \n 2: Eliminar dato \n 3: Editar dato \n 4: Consultar dato \n 5: Mostrar datos \n Otro Num: Salir");
            switch (Menu.rOpcion()){
                case 1:
                    add();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    edit();
                    break;
                case 4:
                    consulta();
                    break;
                case 5:
                    mostrar();
                    break;
                default:
                    System.out.println("Finalizado");
                    System.exit(0);
                    break;
            }
        }
    }
}