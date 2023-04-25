package mx.uv.practica06;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Practica06Application {

	public static void main(String[] args) {
		SpringApplication.run(Practica06Application.class, args);
	}

	@RequestMapping("/")
	public String hola(){
		return "<h1>Hola mundo</h1>";
	}

	@RequestMapping("/adios")
	public String adios(){
		return "<h1>Adiós mundo</h1>";
	}

	@RequestMapping("/pregunta")
	public String pregunta(){
		return "<h1>¿Cómo estás?</h1>";
	}

	@RequestMapping("/productos")
	public List<String> productos(){
		List<String> p = new ArrayList<String>();
		p.add("pambazos");
		p.add("tamales");
		p.add("refrescos");

		return p;
	}

	@RequestMapping("/productos2")
	public List<Producto> productos2(){
		List<Producto> lista = new ArrayList<Producto>();
		lista.add(new Producto("pambazos", 10));
		lista.add(new Producto("tamales", 50));
		lista.add(new Producto("refrescos", 20));

		return lista;
	}
}
