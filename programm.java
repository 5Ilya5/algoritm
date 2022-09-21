import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class Program {

	public static void main(String[] args) {
		int height = 0;
		int width = 0;
		int x = 0;
		int y = 0;
		int nx = 0;
		int ny = 0;
		WaveAlg Path = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document xDoc = db.parse(XmlReader.class.getResourceAsStream("/data.xml"));
			xDoc.normalize();
			NodeList nList = xDoc.getElementsByTagName("Alg");

			Node node;
			node = nList.item(0);
			Element element = (Element) node;

			height = Integer.parseInt(element.getElementsByTagName("Height").item(0).getTextContent());
			width = Integer.parseInt(element.getElementsByTagName("Width").item(0).getTextContent());
			Path = new WaveAlg(width, height);
			int i = 0;
			while (true) {
				try {
					x = Integer.parseInt(element.getElementsByTagName("X").item(i).getTextContent());
					y = Integer.parseInt(element.getElementsByTagName("Y").item(i).getTextContent());
					Path.block(x, y);
				} catch (NullPointerException e) {
					break;
				}
				i++;
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Path.traceOut();
		System.out.println();

		Scanner scan = new Scanner(System.in);
		try {
		System.out.println("объявление x начало операции");
		x = scan.nextInt();
		System.out.println("объявление y начало операции");
		y = scan.nextInt();
		System.out.println("объявление x окончание операции");
		nx = scan.nextInt();
		System.out.println("объявление y окончание операции");
		ny = scan.nextInt();
		}
		catch(InputMismatchException e)
		{
			System.out.println("Защита!");
		}

		Path.findPath(x, y, nx, ny);

		Path.traceOut();
		System.out.println();

		Path.waveOut();
	}

}
