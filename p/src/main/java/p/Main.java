package p;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Main {

	public static void main(String[] args) {
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("C:\\Users\\tomhe\\Desktop\\OCR\\Tess4J\\tessdata");
		tesseract.setLanguage("ENG");
		try {
			String text = tesseract.doOCR(new File("C:\\Users\\tomhe\\Desktop\\Java Coding\\Eclipse\\fabric-example-mod-1.18\\slimy\\p\\src\\main\\java\\p\\test6.JPG"));
			System.out.println(text);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
	}
}
