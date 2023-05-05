package mx.uv.practica08;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Practica08Application{
	private List<Coche> coches = new ArrayList<Coche>();

	public Practica08Application(){
		coches.add(new Coche(0, "Supra", "Toyota", 500000.00));
		coches.add(new Coche(1, "Mustang", "Ford", 100000.00));
		coches.add(new Coche(2, "Diablo", "Ferrari", 5000000.00));
	}

	public static void main(String[] args){
		SpringApplication.run(Practica08Application.class, args);
	}

	@RequestMapping("/get/usuarios")
	public List<Coche> getUsuarios(){
		return coches;
	}

	@RequestMapping(value = "/get/usuarios/{id}", method = RequestMethod.GET)
	public Coche getUsuario(@PathVariable Integer id){
		return coches.get(id);
	}

	@RequestMapping(value = "/post/usuarios", method = RequestMethod.POST)
	public String postUsuario(@RequestBody Coche coche){
		coches.add(coche);

		if(coches.contains(coche)) return "{\"status\": \"Exitoso\"}";
		else return "{\"status\": \"Fallido\"}";
	}

	@RequestMapping(value = "put/usuarios/{id}", method = RequestMethod.PUT)
	public String putUsuario(@PathVariable Integer id, @RequestBody Coche coche){
		coches.set(id, coche);

		if(coches.get(id) == coche) return "{\"message\": \"Exitoso\"}";
		else return "{\"status\": \"Fallido\"}";
	}

	@RequestMapping(value = "delete/usuarios/{id}", method = RequestMethod.DELETE)
	public String deleteUsuario(@PathVariable Integer id){
		Coche coche = coches.get(id);
		coches.remove(coche);

		if(!coches.contains(coche)) return "{\"status\": \"Exitoso\"}";
		else return "{\"status\": \"Fallido\"}";
	}
}