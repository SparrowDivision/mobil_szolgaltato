package mobil_szolgaltato;
import java.io.Serializable;

public class Ugyfel implements Serializable{
	
	//automatikusan generalt UID
	private static final long serialVersionUID = -7328165942820325180L;
	
	private String nev;
	private String telefonszam;
	private Csomag csomag;
	private String varos;
	private String irszam;
	private String utca;
	private String hazszam;
	
	public Ugyfel(String n, String t, Csomag cs, String i, String v,String u,String h) {
		nev = n;
		telefonszam = t;
		csomag = cs;
		irszam = i;
		varos = v;
		utca = u;
		hazszam = h;
	}
		
	public void setNev(String n) {
		nev = n;
	}	
	public String getNev() {
		return nev;
	}
	
	public void setTelefonszam(String t) {
		telefonszam = t;
	}	
	public String getTelefonszam() {
		return telefonszam;
	}
	
	public void setCsomag(Csomag cs) {
		csomag = cs;
	}
	public Csomag getCsomag() {
		return csomag;
	}
	
	public int fizetendo(int sms, int perc) {
		return csomag.tarifa(sms, perc);
	}
	
	public String getCim() {
		return irszam + " " + varos + " " + utca + " " + hazszam;
	}
	
	@Override
	public String toString() {
		return nev + " " + irszam + " " + varos + " " + utca + " " + hazszam + " " + telefonszam + " " + csomag.getCsNev();
	}
}
