package cfg.setter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import ui.cui.Printer;

import cfg.DeBug;

public class GameXmlReader {
	
	public static GameSetter readSetter() throws DocumentException {
		return readSetter("data/basic.xml");
	}
	
	/**
	 * 浠嶺ML璇诲彇涓�満娓告垙閰嶇疆锛堟畫灞�級
	 * */
	public static GameSetter readSetter(String path) throws DocumentException {
		GameSetter gs = new GameSetter();
		SAXReader READER = new SAXReader();
		Document doc = READER.read(path);
		Element elGame = doc.getRootElement();
		if(elGame.elements("dai").size() < 1) {
			throw new DocumentException("XML654");
		}
		gs.setName(elGame);
		gs.setBan(elGame.element("ban"));
		gs.setDai0((Element)elGame.elements("dai").get(0));
		gs.setDai1((Element)elGame.elements("dai").get(1));
		if(DeBug.CUI_ON) {
			Printer.message("111" + gs.getName());
		}
		return gs;
	}
	
}
