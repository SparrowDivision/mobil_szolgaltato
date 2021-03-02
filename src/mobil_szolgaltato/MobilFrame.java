package mobil_szolgaltato;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class MobilFrame extends JFrame {
	
	private Adatbazis ugyfelAdatb;
	private FajlKezelo fk;
	
	public MobilFrame() {
		super("Mobil szolg�ltat�");
		fk = new FajlKezelo("ugyfelek.txt", "adatok.txt");
		ugyfelAdatb = new Adatbazis();
		fk.beolvas(ugyfelAdatb);
		
        this.setLayout(new GridLayout(1,2,3,3));
		
		//uj felhasznalo felvetel panel
		JPanel panel = new JPanel(new GridLayout(8,2,3,3));

		TitledBorder tbChange = new TitledBorder("�j �gyf�l felv�tele");
		tbChange.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(tbChange);

        panel.add(new JLabel("N�v:", SwingConstants.LEFT));
		JTextField jtNev = new JTextField(10);
        panel.add(jtNev);

        panel.add(new JLabel("Telefonsz�m:                            +36 / ", SwingConstants.LEFT));
        JTextField jtTelSz = new JTextField(10);
        panel.add(jtTelSz);

        panel.add(new JLabel("Ir�ny�t�sz�m:", SwingConstants.LEFT));
        JTextField jtIrSz = new JTextField(10);
        panel.add(jtIrSz);
        
        panel.add(new JLabel("V�ros:", SwingConstants.LEFT));
        JTextField jtVaros = new JTextField(10);
        panel.add(jtVaros);
        
        panel.add(new JLabel("Utca/T�r/Egy�b:", SwingConstants.LEFT));
        JTextField jtUtca = new JTextField(10);
        panel.add(jtUtca);
        
        panel.add(new JLabel("H�zsz�m:", SwingConstants.LEFT));
        JTextField jtHazSz = new JTextField(10);
        panel.add(jtHazSz);
        
        String[] mobilCsomagok = new String[] {"Alap", "MobiNet", "SMSMax"};    
        JComboBox<String> jcCsomagok = new JComboBox<String>(mobilCsomagok);
        panel.add(new JLabel("Csomag:", SwingConstants.LEFT));
		panel.add(jcCsomagok);		
        
        JButton jbFelvesz = new JButton("�gyf�l felv�tele *");
        panel.add(jbFelvesz);
        panel.add(new JLabel("*K�rem minden adatot t�lts�n ki!", SwingConstants.CENTER));
        
        this.add(panel);
        
        jbFelvesz.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	  String nev = jtNev.getText();
        	  String telSzam = jtTelSz.getText();
        	  String irSzam = jtIrSz.getText();
        	  String varos = jtVaros.getText();
        	  String utca = jtUtca.getText();
        	  String hazSzam = jtHazSz.getText();
        	  
        	  if (nev.isEmpty() || telSzam.isEmpty() || irSzam.isEmpty() || varos.isEmpty() 
        			  || utca.isEmpty() || hazSzam.isEmpty())
        		  kitoltesFelhivas();
        	  
        	  else {
            	  int csomagIdx = jcCsomagok.getSelectedIndex();
            	  
            	  jtNev.setText("");
            	  jtTelSz.setText("");
            	  jtIrSz.setText("");
            	  jtVaros.setText("");
            	  jtUtca.setText("");
            	  jtHazSz.setText("");
            	  jcCsomagok.setSelectedIndex(0);
            	  
            	  Csomag csomag;
            	  if(csomagIdx == 0)
            		  csomag = new Alap();
            	  else if(csomagIdx == 1)
            		  csomag = new MobiNet();
            	  else
            		  csomag = new SMSMax();
            		  
            	  ugyfelAdatb.felvesz(new Ugyfel(nev, telSzam, csomag, irSzam, varos, utca, hazSzam));
            	  fk.kiiras(ugyfelAdatb);  
        	  }
          }
        });
        
        //hivas es sms kuldes panel
		//uj felhasznalo felvetel panel
		JPanel panel2 = new JPanel(new GridLayout(7,2,3,3));

		TitledBorder tbChange2 = new TitledBorder("Szimul�ci�/Lek�rdez�s");
		tbChange2.setTitleJustification(TitledBorder.CENTER);
		panel2.setBorder(tbChange2);

        panel2.add(new JLabel("Telefonsz�m:                            +36 / ", SwingConstants.LEFT));
		JTextField jtKezdemenyezo = new JTextField(10);
        panel2.add(jtKezdemenyezo);
        
        panel2.add(new JLabel("H�v�s perc: ", SwingConstants.LEFT));
		JTextField jtPerc = new JTextField(10);
        panel2.add(jtPerc);
        
		JButton jbSMS = new JButton("(T) SMS k�ld�s");
		panel2.add(jbSMS);
		
		JButton jbHivas = new JButton("(T,Hs) H�v�s ind�t�s");
		panel2.add(jbHivas);
        
		JButton jbCsomag = new JButton("(T) Telsz�m csomag lek�r");
		panel2.add(jbCsomag);
		
		JButton jbFizetendo = new JButton("(T) Telsz�m fizetend�");
		panel2.add(jbFizetendo);
		
		JButton jbTorles = new JButton("(T) Telsz�m adatok t�rl�se");
		panel2.add(jbTorles);
		
		JButton jbListazHanyan = new JButton("Csomagok ig�nyl�sek");
		panel2.add(jbListazHanyan);
		
		JButton jbListazTelCsomag = new JButton("Telsz�m+Csomag list�z");
		panel2.add(jbListazTelCsomag);
		
		JButton jbListazMindent = new JButton("�sszes adat list�z");
		panel2.add(jbListazMindent);
		
		panel2.add(new JLabel("T: Telefonsz�m k�telez�!", SwingConstants.CENTER));
		panel2.add(new JLabel("T,Hs: Mind k�t adat k�telez�!", SwingConstants.CENTER));
		
        this.add(panel2);
        
        jbSMS.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  if (jtKezdemenyezo.getText().isEmpty())
        		  kitoltesFelhivas();
        	  else {
        		  ugyfelAdatb.smsKuld(jtKezdemenyezo.getText());
        		  fk.kiiras(ugyfelAdatb);
        	  }  		  
          }
        });
        
        jbHivas.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  if (jtKezdemenyezo.getText().isEmpty() || jtPerc.getText().isEmpty())
        		  kitoltesFelhivas();
        	  else {
            	  int perc = Integer.parseInt(jtPerc.getText());
            	  ugyfelAdatb.hivas(jtKezdemenyezo.getText(), perc);
            	  fk.kiiras(ugyfelAdatb);
        	  }
          }
        });
        
        jbCsomag.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  if (jtKezdemenyezo.getText().isEmpty())
        		  kitoltesFelhivas();
        	  else {
            	  JDialog dialog = new JDialog();
            	  
            	  Csomag cs = ugyfelAdatb.lekerCsomag(jtKezdemenyezo.getText());

            	  dialog.add(new JLabel(cs.getCsNev(), SwingConstants.CENTER));
            	  dialog.setSize(100, 100);
            	  dialog.setVisible(true);
            	  dialog.setResizable(false);
        	  }
          }
        });
        
        jbTorles.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  if (jtKezdemenyezo.getText().isEmpty())
        		  kitoltesFelhivas();
        	  else {
            	  ugyfelAdatb.torol(jtKezdemenyezo.getText());
            	  fk.kiiras(ugyfelAdatb);
        	  }
          }
        });
        
        jbFizetendo.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  if (jtKezdemenyezo.getText().isEmpty())
        		  kitoltesFelhivas();
        	  else {
            	  JDialog dialog = new JDialog();
            	  
            	  Ugyfel u = ugyfelAdatb.keresUgyfel(jtKezdemenyezo.getText());
            	  TarifaAdat a = ugyfelAdatb.keresAdat(jtKezdemenyezo.getText());
            	  if (u!=null && a!=null) {
            		  int fizetendo = u.fizetendo(a.getSms(), a.getPerc());
            		  dialog.add(new JLabel(String.valueOf(fizetendo), SwingConstants.CENTER));
            	  }	  
            	  else
            		  dialog.add(new JLabel("Nincs ilyen sz�m", SwingConstants.CENTER));

            	  dialog.setSize(100, 100);
            	  dialog.setVisible(true);
            	  dialog.setResizable(false);
        	  }
          }
        });
        
        jbListazHanyan.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
  	  
        	  int db1 = ugyfelAdatb.hanyanVeszikIgenybe(new Alap());
        	  int db2 = ugyfelAdatb.hanyanVeszikIgenybe(new MobiNet());
        	  int db3 = ugyfelAdatb.hanyanVeszikIgenybe(new SMSMax());
        	  
        	  JDialog dialog = new JDialog();

        	  String[] oszlopNevek = { "Csomag", "H�nyan"};
        	  JTable tabla = new JTable();
        	  DefaultTableModel modell = new DefaultTableModel();
        	  modell.setColumnIdentifiers(oszlopNevek);
        	  tabla.setModel(modell);

      	      modell.addRow(new Object[] { "Alap", db1});
      	      modell.addRow(new Object[] { "MobiNet", db2});
      	      modell.addRow(new Object[] { "SMSMax", db3});
      		
        	  JScrollPane tekero = new JScrollPane(tabla);

        	  dialog.add(tekero);
        	  dialog.setSize(500, 400);
        	  dialog.setVisible(true);
        	  dialog.setResizable(false);
        	  
        	  jtKezdemenyezo.setText("");
        	  jtPerc.setText("");
          }
        });
        
        jbListazTelCsomag.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  JDialog dialog = new JDialog();

        	  String[] oszlopNevek = { "Telefonsz�m", "Csomag"};
        	  JTable tabla = new JTable();
        	  DefaultTableModel modell = new DefaultTableModel();
        	  modell.setColumnIdentifiers(oszlopNevek);
        	  tabla.setModel(modell);
        	  ArrayList<String> adatok = ugyfelAdatb.tablaAdatok();
        	  for (int i = 0; i < (adatok.size() / 5); i++) {
        		  modell.addRow(new Object[] { adatok.get((5 * i) + 2), adatok.get((5 * i) + 3)});
      	      }
      		
        	  JScrollPane tekero = new JScrollPane(tabla);

        	  dialog.add(tekero);
        	  dialog.setSize(500, 400);
        	  dialog.setVisible(true);
        	  dialog.setResizable(false);
        	  
        	  jtKezdemenyezo.setText("");
        	  jtPerc.setText("");
          }
        });
        
        jbListazMindent.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  JDialog dialog = new JDialog();

        	  String[] oszlopNevek = { "N�v", "C�m", "Telefonsz�m", "Csomag", "Fizetend�" };
        	  JTable tabla = new JTable();
        	  DefaultTableModel modell = new DefaultTableModel();
        	  modell.setColumnIdentifiers(oszlopNevek);
        	  tabla.setModel(modell);
        	  ArrayList<String> adatok = ugyfelAdatb.tablaAdatok();
        	  for (int i = 0; i < (adatok.size() / 5); i++) {
        		  modell.addRow(new Object[] { adatok.get(5 * i), adatok.get((5 * i) + 1), 
        		     adatok.get((5 * i) + 2), adatok.get((5 * i) + 3), adatok.get((5 * i) + 4)});
      	      }
      		
        	  JScrollPane tekero = new JScrollPane(tabla);

        	  dialog.add(tekero);
        	  dialog.setSize(1000, 400);
        	  dialog.setVisible(true);
        	  dialog.setResizable(false);
          }
        });
        
	}

	public void kitoltesFelhivas() {
  	  JDialog dialog = new JDialog();
	  JLabel figyelmeztetes = new JLabel("Hi�nyz� adatok!", SwingConstants.CENTER);
	  figyelmeztetes.setForeground(Color.RED);
  	  dialog.add(figyelmeztetes);

  	  dialog.setSize(50, 50);
  	  dialog.setVisible(true);
  	  dialog.setResizable(false);
	}
}
