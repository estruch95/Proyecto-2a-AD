
public class ParserLibro {

	private static Parser parser;
	//Atributos de la clase
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Declaración de atributos (Nuevo objeto parser)
		parser = new Parser();
		
		//EJECUCIÓN DE MÉTODOS
		
		//Llamada al método "parseFicheroXml()" para parsear el xml de libros
		parser.parseFicheroXml("biblioteca.xml");
		//Parseado del documento para obtener sus elementos
		parser.parseDocument();
		//Impreso por pantalla
		parser.print();
	}

}
