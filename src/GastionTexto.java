import java.io.IOException;

public class GastionTexto {
    public static void main(String[] args) throws IOException {
        System.out.println("Que desea hacer: \n 1: Crear Archivo \n 2: Cargar Archivo \n Otro Num: Salir");
        int opcion = Menu.rOpcion();
        switch (opcion){
            case 1:
                String archivo = Menu.Nuevo();
                new crud(archivo).menu();
                break;
            case 2:
                String archivoC = Menu.Subir();
                new crud(archivoC).menu();
                break;
            default: System.out.println("Finalizado");
        }

    }
}