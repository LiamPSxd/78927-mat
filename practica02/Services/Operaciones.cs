using System;
using WSDL.mensajes;

namespace WSDL.operaciones{
    public class Operaciones : Mensajes{
        private List<string> saludos = new List<string>();

        public string Saludar(string nombre){
            saludos.Add(nombre);
            return "Hola " + nombre;
        }

        public string Mostrar(int id){
            return saludos[id];
        }
    }
}