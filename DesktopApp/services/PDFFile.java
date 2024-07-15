
package services;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PDFFile {
    private String ruta;
    private Image header;
    private Image title;
    private Paragraph content;
    private Image footer;

    public PDFFile() {
        this.ruta = System.getProperty("user.home");
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Image getHeader() {
        return header;
    }

    public void setHeader(Image header) {
        this.header = header;
    }

    public Image getTitle() {
        return title;
    }

    public void setTitle(Image title) {
        this.title = title;
    }

    public Paragraph getContent() {
        return content;
    }

    public void setContent(Paragraph content) {
        this.content = content;
    }

    public Image getFooter() {
        return footer;
    }

    public void setFooter(Image footer) {
        this.footer = footer;
    }
    
    public void generate(String name) {
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(this.getRuta() + "\\Desktop\\"+name+".pdf"));
            documento.open();
            if(title != null) {
                title.scaleToFit(650, 1000);
                title.setAlignment(Chunk.ALIGN_CENTER);
                documento.add((Element) title);
            } 
            if(header != null) {
                header.scaleToFit(650, 1000);
                header.setAlignment(Chunk.ALIGN_CENTER);
                documento.add((Element) header);
            } 
            if(content != null)
                documento.add(content);
            if(footer != null) {
                footer.scaleToFit(650, 1000);
                footer.setAlignment(Chunk.ALIGN_CENTER);
                documento.add((Element) footer);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Documento generado correctamente");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
