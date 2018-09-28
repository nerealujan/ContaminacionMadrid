import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Medida {

	public static void main(String[] args) {
		ArrayList lista = new ArrayList();
		String ruta = "C:\\contaminacion\\";
		lista = obtenerDatos(ruta + "horario.csv");
		String html = generarHtml(ruta, lista);
		crearHTML(html);

	}

	private static void crearHTML(String html) {
		
		try {
			FileWriter fw = new FileWriter("C:\\contaminacion\\htmlContaminacion.html");
			fw.write(html);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String generarHtml(String ruta, ArrayList<Contaminacion> lista) {
		String html="<html><head></head><body><table border=1px>";
		
		for (int i=0; i<lista.size(); i++)
		{
			Contaminacion conta=lista.get(i);
			html+="<tr>";
			html+="<td>"+conta.getEstacion()+"</td>";
			html+="<td>"+conta.getMagnitud()+"</td>";
			html+="<td>"+conta.getFecha()+"</td>";
			String[] cantidad=conta.getHoras();
			for (int j = 0; j < cantidad.length; j++) {
				html+="<td>"+cantidad[j]+"</td>";
			}
			html+="</tr>";
		}
		return html+="</table></body></html>";
		
		
	}

	private static ArrayList obtenerDatos(String ruta) {
		File f = new File(ruta);
		System.out.println(f.exists());
		String linea = "";
		FileReader fr;
		BufferedReader bf;
		ArrayList listaDeContaminaciones=new ArrayList<>();
		try {
			fr = new FileReader(f);
			bf = new BufferedReader(fr);
			String[] datos;
			String fecha;
			
			
			while ((linea = bf.readLine()) != null) {
				String[] cantidades = new String[48];
				datos = linea.split(";");
				Contaminacion contaminacion = new Contaminacion();
				contaminacion.setEstacion(datos[2]);
				contaminacion.setMagnitud(datos[3]);
				fecha = datos[7] + "/" + datos[6] + "/" + datos[5];
				contaminacion.setFecha(fecha);

				for (int i = 8; i < datos.length; i++) {
					cantidades[i - 8] = datos[i];
				}
				
				contaminacion.setHoras(cantidades);
				listaDeContaminaciones.add(contaminacion);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaDeContaminaciones;
	}

}
