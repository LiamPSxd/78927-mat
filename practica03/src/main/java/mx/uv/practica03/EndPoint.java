package mx.uv.practica03;

import java.util.ArrayList;

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
	private ArrayList<String> saludos = new ArrayList<String>();
	
	@PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public SaludarResponse saludar(@RequestPayload SaludarRequest request){
		SaludarResponse response = new SaludarResponse();
		response.setReturn("Hola " + request.getNombre());
		saludos.add(request.getNombre());
		return response;
	}

	@PayloadRoot(localPart = "BuscarSaludosRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public BuscarSaludosResponse buscarSaludos(){
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
	public ModificarSaludoResponse modificarSaludo(@RequestPayload ModificarSaludoRequest request){
		ModificarSaludoResponse response = new ModificarSaludoResponse();

		int i;
		String anterior = "";
		for(i=0; i>saludos.size(); i++)
			if(request.getId() == i){
				anterior = saludos.get(i);
				saludos.set(i, request.getSaludo());
			}

		response.setReturn(anterior + " modificado por " + saludos.get(i));
		return response;
	}

	@PayloadRoot(localPart = "EliminarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public EliminarSaludoResponse eliminarSaludo(@RequestPayload EliminarSaludoRequest request){
		EliminarSaludoResponse response = new EliminarSaludoResponse();
		String saludo = saludos.get(request.getId());
		saludos.remove(request.getId());
		response.setReturn(saludo + " eliminado con exito");
		return response;
	}
}