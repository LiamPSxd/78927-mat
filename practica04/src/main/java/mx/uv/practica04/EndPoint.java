package mx.uv.practica04;

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

	@PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public SaludarResponse Saludar(@RequestPayload SaludarRequest request){
		SaludarResponse response = new SaludarResponse();
		response.setReturn("Hola " + request.getNombre());

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

		String resultado = "{\n";
		for(Saludador saludo : iSaludador.findAll()){
			resultado += "	{'id': '" + String.valueOf(saludo.getId()) + "', 'nombre': '" + saludo.getNombre() + "'}\n";
		}

		response.setReturn(resultado + "}");
		
		return response;
	}

	@PayloadRoot(localPart = "ModificarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public ModificarSaludoResponse ModificarSaludo(@RequestPayload ModificarSaludoRequest request){
		ModificarSaludoResponse response = new ModificarSaludoResponse();
		Saludador saludador = iSaludador.findById(request.getId()).get();

		if(saludador != null){
			String anterior = saludador.getNombre();
			saludador.setNombre(request.getSaludo());
			iSaludador.save(saludador);

			response.setReturn(anterior + " modificado por " + saludador.getNombre());
		}else response.setReturn("Saludo no encontrado...");

		return response;
	}

	@PayloadRoot(localPart = "EliminarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
	@ResponsePayload
	public EliminarSaludoResponse EliminarSaludo(@RequestPayload EliminarSaludoRequest request){
		EliminarSaludoResponse response = new EliminarSaludoResponse();
		Saludador saludador = iSaludador.findById(request.getId()).get();

		if(saludador != null){
			iSaludador.deleteById(saludador.getId());
			response.setReturn(saludador.getNombre() + " eliminado con exito");
		}else response.setReturn("Saludo no encontrado...");

		return response;
	}
}