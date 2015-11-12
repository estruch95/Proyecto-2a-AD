import java.io.Serializable;
import java.util.ArrayList;


public class Libro implements Serializable{
	//Atributos de la clase Libro
	private String titulo;
	private int año;
	private ArrayList<String> autores;
	private String editor;
	private int paginas;
	
	//Parametros que recibe el constructor de la clase libro
	public Libro(String t, int año, ArrayList<String> a, String e, int pa) {
		// TODO Auto-generated constructor stub
		this.titulo = t;
		this.año = año;
		this.autores = a;
		this.editor = e;
		this.paginas = pa;
	}

	//Getters y Setters de los atributos de la clase Libro
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	
	//Método cuya función es imprimir por consola los datos de dicho libro
	public void print(){
		System.out.print("Título "+titulo+", autor");
		for (String string : autores) {
			System.out.print(" "+string);
		}
		System.out.println(", año "+año+", editor "+editor+" y número de páginas "+this.getPaginas());
	}

}
