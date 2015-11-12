import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Parser {
	//Atributos de la clase
	private Document dom;
	private ArrayList<Libro> libros;

	public Parser() {
		//Inicialización y declaración de atributos de la clase
		dom = null;
		libros = new ArrayList<Libro>();
	}
	
	public void parseFicheroXml(String nombreFichero){
		//Creamos un DocumentBuiderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//Encapsulado try/catch (captura de errores)
		try {
			//Creamos un DocumentBuilder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//Parseamos el fichero XML recibido por parámetro y lo obtenemos representado como un objeto Document
			dom = db.parse(nombreFichero);
		}
		catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseDocument(){
		//Obtenemos y guardamos el elemento raíz
		Element documentElements = dom.getDocumentElement();
		//Recogemos y guardamos la lista de nodos del documento dom a partir del tag pasado por parámetro
		//ACLARACIÓN: Obtendriamos todos los libros existentes en el fichero XML
		NodeList listaNodos = documentElements.getElementsByTagName("libro");
		
		//Recorrido de la lista de nodos obtenida (OBTENER LIBROS)
		if(listaNodos != null && listaNodos.getLength() > 0){
			for(int a=0; a<listaNodos.getLength(); a++){
				//En cada iteración recogemos un elemento de la lista (OBJETO LIBRO)
				Element elementoObtenido = (Element) listaNodos.item(a);
				
				//Obtenemos el libro recogido
				Libro libroObtenido = getLibro(elementoObtenido);
				
				//Añadido de libro obtenido al array de libros
				libros.add(libroObtenido);
			}
		}
	}
	
	public Libro getLibro(Element elementoLibro){
		//Para el elemento libro recibido por parámetro RECOGER TODOS SUS DATOS (ATRIBUTOS)
		String titulo = getTextValue(elementoLibro, "titulo");
		int año = getIntAttributeValue(elementoLibro, "titulo", "anyo");
		ArrayList<String> autores = getNombresAutor(elementoLibro, "autor", "nombre");
		String editor = getTextValue(elementoLibro, "editor");
		int paginas = getIntValue(elementoLibro, "paginas");
		
		//Creamos un nuevo objeto Libro con los atributos recogidos del elemento recibido
		Libro nuevoLibro = new Libro(titulo, año, autores, editor, paginas);
		
		//Devolvemos el objeto Libro creado y seteado correctamente
		return nuevoLibro;
	}
	
	public int getIntAttributeValue(Element element, String tag, String attribute){
		int i = -1;
		NodeList list = element.getElementsByTagName(tag);
		if(list != null && list.getLength() > 0){
			Element e = (Element) list.item(0);
			String value = e.getAttribute(attribute);
			try{
				i = Integer.parseInt(value);
			}catch(NumberFormatException ex){
				i = -1;
			}
		}
		return i;
	}
	
	//Este método a partir de un elemento y un tag nos devuelve el valor de dicho elemento
	public String getTextValue(Element elemento, String tagName){
		String textValue = null;
		NodeList listaNodos = elemento.getElementsByTagName(tagName);
		
		if(listaNodos != null && listaNodos.getLength() > 0){
			Element elementoObtenido = (Element) listaNodos.item(0);
			textValue = elementoObtenido.getFirstChild().getNodeValue();
		}
		return textValue;
	}
	
	//Este método a partir de un elemento y un tag nos devuelve el valor entero de dicho elemento
	public int getIntValue(Element elemento, String tagName){
		return Integer.parseInt(getTextValue(elemento, tagName));
	}
	
	public ArrayList<String> getNombresAutor(Element libroEle, String autor, String nombre){
		ArrayList<String> nombres = new ArrayList<String>();
		NodeList list = libroEle.getElementsByTagName(autor);
		if(list != null && list.getLength() > 0){
			for(int i = 0; i < list.getLength(); i++){
				Element elementNombre = (Element) list.item(i);
				String nom = getTextValue(elementNombre, nombre);
				nombres.add(nom);
			}
		}
		return nombres;
	}

	//Método cuya función es realizar un impreso por consola
	public void print(){
		//Recorremos el ArrayList<Libros> y para cada libro imprimimos todos sus datos
		Iterator it = libros.iterator();
		while(it.hasNext()){
			Libro libro = (Libro) it.next();
			System.out.println("Titulo: "+libro.getTitulo());
			System.out.println("Año: "+libro.getAño());
			System.out.println("Autor1: "+libro.getAutores().get(0));
			//System.out.println("Autor2: "+libro.getAutores().get(1));
			System.out.println("Editor: "+libro.getEditor());
			System.out.println("Paginas: "+libro.getPaginas());
			System.out.println("------------------------------");
		}
	}
}
