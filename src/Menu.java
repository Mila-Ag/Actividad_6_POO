import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static int rOpcion() {
        boolean s = true;
        int opcion = 0;
        while (s){
            try {
                Scanner I = new Scanner(System.in);
                opcion = I.nextInt();
                s = false;
            } catch (InputMismatchException e){System.out.println("Digite una opcion valida");}
        }
        return opcion;
    }

    static String Nuevo() {
        System.out.println("Digite el nombre del archivo a crear: ");
        Scanner inp = new Scanner(System.in);
        String FICHERO = inp.nextLine().toUpperCase() + ".txt";
        try {
            if (new File(FICHERO).isFile()){
                System.out.println("Archivo " + FICHERO + " ya existe, desea reescribirlo? \n 1: Si \n Otro num: No");
                if((rOpcion() != 1)){
                    System.out.println("Finalizado, no se reescribio "+FICHERO);
                    System.exit(0);
                }
            }
            new FileWriter(FICHERO);
        } catch (IOException e) {
            System.out.println("No se pudo crear"+FICHERO);
            System.exit(0);
        }
        System.out.println("Archivo " + FICHERO + " creado correctamente.");
        return FICHERO;
    }

    static String Subir(){
        while(true){
            System.out.println("Digite el nombre del archivo para cargar: ");
            Scanner inp = new Scanner(System.in);
            String FICHERO = inp.nextLine().toUpperCase()+".txt";
            try {
                new FileReader(FICHERO);
                System.out.println(FICHERO +" Cargado");
                return FICHERO;
            } catch (FileNotFoundException e) {
                System.out.println("No se encontro " + FICHERO);
                System.out.println("Desea intentar denuevo con otro archivo? \n 1: Si \n Otro num: No");

                if (rOpcion() != 1){System.out.println("Finalizado"); System.exit(0); }
            }
        }
    }
}