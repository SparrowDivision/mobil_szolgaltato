package mobil_szolgaltato;
import javax.swing.JFrame;

public class Main {
	
	static JFrame mobilsz;
	public static void main(String args[]) {
		
		mobilsz = new MobilFrame();
		mobilsz.setVisible(true);
		mobilsz.setSize(800, 300);
		mobilsz.setResizable(false);
		mobilsz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
	}
}
