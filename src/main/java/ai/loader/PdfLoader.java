package ai.loader;

import ai.document.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.nio.file.Path;


public class PdfLoader {
	
	public Document load(Path pdfPath) {
		try (PDDocument pdf = PDDocument.load(pdfPath.toFile())) {
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(pdf);
			System.out.println("PDF text length = " + text.length());
			
			return new Document(
					pdfPath.getFileName().toString(),
					text,
					pdfPath.toString()
			);	
		} catch (Exception e) {
			throw new RuntimeException("Failed to load PDF: " + pdfPath, e);
		}
	}
}
