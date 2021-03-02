package mobil_szolgaltato;
import java.util.ArrayList;
import java.util.Iterator;


public class Adatbazis {
	private ArrayList <Ugyfel> ugyfelek;
	private ArrayList <TarifaAdat> adatok;
	
	public Adatbazis() {
		ugyfelek = new ArrayList <Ugyfel> ();
		adatok = new ArrayList <TarifaAdat> ();
	}
	
	public void setUgyfelek(ArrayList <Ugyfel> u) {
		ugyfelek = u;
	}
	
	public ArrayList <Ugyfel> getUgyfelek() {
		return ugyfelek;
	}
	
	public void setAdatok(ArrayList <TarifaAdat> ta) {
		adatok = ta;
	}
	
	public ArrayList <TarifaAdat> getAdatok() {
		return adatok;
	}
	
	//csak ha meg nincs felveve az a telefonszam - egyedi azonosito
	public void felvesz(Ugyfel u) {
		Ugyfel foglaltTel = keresUgyfel(u.getTelefonszam());
		if (foglaltTel == null) {
			ugyfelek.add(u);
			//tarifa adatokba is felvesszuk
			adatok.add(new TarifaAdat(u.getTelefonszam()));
		}
	}
	
	public void torol(String tel) {
		
		//torles ugyfelekbol
		Iterator <Ugyfel> iter = ugyfelek.iterator();
		while(iter.hasNext())
		{
		    Ugyfel u = iter.next();
		    if(u.getTelefonszam().equals(tel))
		        iter.remove();
		}
		
		//torles adatokbol
		Iterator <TarifaAdat> iter2 = adatok.iterator();
		while(iter2.hasNext())
		{
		    TarifaAdat a = iter2.next();
		    if(a.getTelefonszam().equals(tel))
		        iter2.remove();
		}
	}
	
	//adott telefonszamhoz milyen csomag tartozik
	public Csomag lekerCsomag(String tel) {
		for(Ugyfel u : ugyfelek) {
			if(u.getTelefonszam().equals(tel))
				return u.getCsomag();
		}
		return null;
	}
	
	//ugyfel megkeresese telefonszam alapjan
	public Ugyfel keresUgyfel(String tel) {
		for(Ugyfel u : ugyfelek) {
			if(u.getTelefonszam().equals(tel))
				return u;
		}
		return null;
	}
	
	//tarifaadat megkeresese telefonszam alapjan
	public TarifaAdat keresAdat(String tel) {
		for(TarifaAdat a : adatok) {
			if(a.getTelefonszam().equals(tel))
				return a;
		}
		return null;
	}
	
	public void smsKuld(String tel) {
		TarifaAdat a  = keresAdat(tel);
		if (a != null)
			a.sms();
	}
	
	public void hivas(String tel, int perc) {
		TarifaAdat a  = keresAdat(tel);
		if (a != null)
			a.hivas(perc);
	}
	
	public ArrayList<String> tablaAdatok() {
		int ugyfelSzam = ugyfelek.size();
		ArrayList<String> adatok = new ArrayList<String>();
		for(int i = 0; i<ugyfelSzam; i++) {
			Ugyfel u = ugyfelek.get(i);
			TarifaAdat a = keresAdat(u.getTelefonszam());
			
			adatok.add(u.getNev());
			adatok.add(u.getCim());
			adatok.add(" +36" + u.getTelefonszam());
			adatok.add(u.getCsomag().getCsNev());
			
			if (a != null)
				adatok.add(String.valueOf(u.fizetendo(a.getSms(), a.getPerc())));
			else
				adatok.add(String.valueOf(0));
		}
		return adatok;
	}
	
	public int hanyanVeszikIgenybe(Csomag cs) {
		int db = 0;
		for(Ugyfel u : ugyfelek) {
			if(u.getCsomag().getCsNev().equals(cs.getCsNev()))
				db++;
		}
		return db;
	}
}
