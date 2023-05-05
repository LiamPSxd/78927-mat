package mx.uv.practica08;

public class Coche{
    private Integer id;
    private String nombre;
    private String marca;
    private Double valor;

    public Coche(Integer id, String nombre, String marca, Double valor){
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.valor = valor;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarcar(String marca){
        this.marca = marca;
    }

    public Double getValor(){
        return valor;
    }

    public void setValor(Double valor){
        this.valor = valor;
    }
}
