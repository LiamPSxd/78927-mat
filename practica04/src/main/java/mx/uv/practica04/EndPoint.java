package mx.uv.practica04;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import https.t4is_uv_mx.saludos.SaludarResponse;
import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
import https.t4is_uv_mx.saludos.EliminarSaludoRequest;
import https.t4is_uv_mx.saludos.EliminarSaludoResponse;
import https.t4is_uv_mx.saludos.ModificarSaludoRequest;
import https.t4is_uv_mx.saludos.ModificarSaludoResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;

@Endpoint
public class EndPoint{
	@Autowired
	private ISaludador iSaludador;
	private ArrayList<String> saludos = new ArrayList<String>();
	
	@PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public SaludarResponse Saludar(@RequestPayload SaludarRequest request){
		SaludarResponse response = new SaludarResponse();
		response.setReturn("Hola " + request.getNombre());
		saludos.add(request.getNombre());
		
		//Persistencia a la Base de Datos
		Saludador saludador = new Saludador();
		saludador.setNombre(request.getNombre());
		iSaludador.save(saludador);

		return response;
	}

	@PayloadRoot(localPart = "BuscarSaludosRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public BuscarSaludosResponse BuscarSaludos(){
		BuscarSaludosResponse response = new BuscarSaludosResponse();
		
		String resultado = "";
		for(String saludo : saludos){
			resultado += ", " + saludo;
		}

		response.setReturn(resultado);
		return response;
	}

	@PayloadRoot(localPart = "ModificarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public ModificarSaludoResponse ModificarSaludo(@RequestPayload ModificarSaludoRequest request){
		ModificarSaludoResponse response = new ModificarSaludoResponse();

		int i;
		boolean ban = false;
		String anterior = "";
		for(i=0; i<saludos.size(); i++)
			if(request.getId() == i){
				anterior = saludos.get(i);
				saludos.set(i, request.getSaludo());
				ban = true;
				response.setReturn(anterior + " modificado por " + saludos.get(i));
			}

		
		if(!ban){
			response.setReturn("Saludo no encontrado...");
			ban = false;
		}

		return response;
	}

	@PayloadRoot(localPart = "EliminarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public EliminarSaludoResponse EliminarSaludo(@RequestPayload EliminarSaludoRequest request){
		EliminarSaludoResponse response = new EliminarSaludoResponse();

		String saludo = saludos.get(request.getId());
		if(saludo != null){
			saludos.remove(request.getId());
			response.setReturn(saludo + " eliminado con exito");
		}else response.setReturn("Saludo no encontrado...");
		
		return response;
	}
}