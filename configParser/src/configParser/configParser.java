package configParser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class configParser {

	public static void createConfig(String inputFile, String outputFile, String seed, String mutation, String crossoverRate) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder(); 
			Document doc = dBuilder.parse("config.xml");
			doc.getDocumentElement().normalize();

			NodeList props = doc.getElementsByTagName("property");
			boolean a = false;
			for(int i = 0; i<props.getLength(); i++) {
				if(props.item(i).getAttributes().item(0)
						.getTextContent().equals("filename")) {
					props.item(i).setTextContent(outputFile);
				}
				if(props.item(i).getAttributes().item(0)
						.getTextContent().equals("seed")) {
					props.item(i).setTextContent(seed);
				}
				if(props.item(i).getAttributes().item(0)
						.getTextContent().equals("alpha")) {
					if(a) {
						props.item(i).setTextContent(mutation);
					} else {
						a = true;
						continue;
					}
				}
				if(props.item(i).getAttributes().item(0)
						.getTextContent().equals("mu")) {
					props.item(i).setTextContent(mutation);
				}
				if(props.item(i).getAttributes().item(0)
						.getTextContent().equals("lambda")) {
					props.item(i).setTextContent(mutation);
				}
				
				if(props.item(i).getAttributes().item(0)
						.getTextContent().equals("crossoverRate")) {
					props.item(i).setTextContent(crossoverRate);
				}
				
				
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource src = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(inputFile));
			t.transform(src, result);
						
		}catch(Exception e) {
			e.printStackTrace();
		}
	  }
	
	
	public static void main(String[] args){
		for(int i = 1; i <= 30; i++) {
			createConfig("configurations/conf0508_"+i+".txt", "outputs/output0508_"+i+".tsv", String.valueOf(i), "5", "0.8");
			createConfig("configurations/conf05042_"+i+".txt","outputs/output05042_"+i+".tsv", String.valueOf(i), "5", "0.42");
			createConfig("configurations/conf13708_"+i+".txt","outputs/output13708_"+i+".tsv", String.valueOf(i), "137", "0.8");
			createConfig("configurations/conf137042_"+i+".txt","outputs/output137042_"+i+".tsv", String.valueOf(i), "137", "0.42");
		}
	}
}
