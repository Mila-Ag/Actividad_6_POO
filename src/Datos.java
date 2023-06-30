public class Datos {
    String Documento;
    String Nombre;
    String Apellido;
    Datos(String doc, String nom, String ape){
        this.Documento = doc;
        this.Nombre = nom;
        this.Apellido = ape;
    }

    String resumen(){
        return "Cedula: "+Documento+", Nombre: "+Nombre+", Apellido: "+Apellido;
    }
}