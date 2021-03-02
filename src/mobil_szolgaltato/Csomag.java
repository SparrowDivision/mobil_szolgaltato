package mobil_szolgaltato;
import java.io.Serializable;

public abstract class Csomag implements Serializable{
	private int smsDij;
	private int percDij;
	private int alapDij;
	private String csNev;
	
	public Csomag(int sd, int pd, int ad) {
		smsDij = sd;
		percDij = pd;
		alapDij = ad;
	}
	
	public void setCsNev(String csn) {
		csNev = csn;
	}
	
	public String getCsNev() {
		return csNev;
	}
	
	public void setSmsDij(int sd) {
		smsDij = sd;
	}
	public int getSmsDij() {
		return smsDij;
	}
	
	public void setPercDij(int pd) {
		percDij = pd;
	}
	public int getPercDij() {
		return percDij;
	}
	
	public void setAlapDij(int ad) {
		alapDij = ad;
	}
	public int getAlapDij() {
		return alapDij;
	}

	//absztrakt tarifa szamolo fuggveny
	abstract public int tarifa(int sms, int perc);

}
