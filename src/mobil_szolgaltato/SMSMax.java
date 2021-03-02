package mobil_szolgaltato;

public class SMSMax extends Csomag {
	
	private static int sd = 8; 
	private static int pd = 25; 
	private static int ad = 3000;
	
	//minden harmadik sms ingyen van
	private final int ingyenSms = 3;
	
	public SMSMax() {
		super(sd, pd, ad);
		this.setCsNev("SMSMax");
	}
	
	public int tarifa(int sms, int perc) {
		int fizetettSms = sms - sms/3;
		return ad+fizetettSms*sd+perc*pd;
	}

}
