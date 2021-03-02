package mobil_szolgaltato;

import java.io.Serializable;

public class TarifaAdat implements Serializable {

	//automatikusan generalt UID
	private static final long serialVersionUID = -3989474539862919688L;
	
	private String telefonszam;
	private int smsDb = 0;
	private int hivasPerc = 0;
	
	public TarifaAdat(String tel) {
		telefonszam = tel;
	}
	
	public void setTelefonszam(String t) {
		telefonszam = t;
	}	
	public String getTelefonszam() {
		return telefonszam;
	}
	
	public void setPerc(int p) {
		hivasPerc = p;
	}
	
	public int getPerc() {
		return hivasPerc;
	}
	
	public void setSms(int sms) {
		smsDb = sms;
	}
	
	public int getSms() {
		return smsDb;
	}
	
	public void hivas(int perc) {
		hivasPerc += perc;
	}
	
	public void sms() {
		smsDb ++;
	}
	
	@Override
	public String toString() {
		return telefonszam + " " + smsDb + " " + hivasPerc;
	}

}
