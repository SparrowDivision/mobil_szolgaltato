package mobil_szolgaltato;

public class MobiNet extends Csomag {
	
	private static int sd = 25; 
	private static int pd = 20; 
	private static int ad = 2500;
	
	//10 sms ingyen van
	private final int ingyenSmsDb = 10;
	
	public MobiNet() {
		super(sd, pd, ad);
		setCsNev("MobiNet");
	}
	
	public int tarifa(int sms, int perc) {
		int fizetettSms = sms-ingyenSmsDb;
		if (fizetettSms < 0)
			fizetettSms = 0;
		return ad+fizetettSms*sd+perc*pd;
	}
	
	

}
