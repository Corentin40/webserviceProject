package data;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.text.html.HTMLEditorKit;

public class Fenetre extends JFrame {
private JEditorPane apercu, apercu2;
private File htmlFile, htmlFile2; 

public Fenetre(String fileName, String fileName2){
  this.setSize(550, 400);
  this.setTitle("Caffichage du résultat");
  this.setLocationRelativeTo(null);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.htmlFile = new File(fileName);
  this.htmlFile2 = new File(fileName2);
  this.init();
  this.setVisible(true);
}

private void init(){
   apercu = new JEditorPane();
   apercu2 = new JEditorPane();
   try {
       apercu.setEditorKit(new HTMLEditorKit());               
       apercu.setPage(this.htmlFile.toURI().toURL());
       apercu.setEditable(false);
       
       apercu2.setEditorKit(new HTMLEditorKit());               
       apercu2.setPage(this.htmlFile2.toURI().toURL());
       apercu2.setEditable(false);
       
    } catch (FileNotFoundException e) {
       e.printStackTrace();
    } catch (IOException e) {
       e.printStackTrace();
    }     
   this.getContentPane().setLayout(new BorderLayout());
   this.getContentPane().add(apercu, BorderLayout.WEST);
   this.getContentPane().add(apercu2, BorderLayout.EAST);
}

}
