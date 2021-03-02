package mobil_szolgaltato;

public class Alap extends Csomag {
	
	private static int sd = 25; 
	private static int pd = 35; 
	private static int ad = 1000;
	
	public Alap() {
		super(sd, pd, ad);
		setCsNev("Alap");
	}
	
	public int tarifa(int sms, int perc) {
		return ad+sms*sd+perc*pd;
	}
}
