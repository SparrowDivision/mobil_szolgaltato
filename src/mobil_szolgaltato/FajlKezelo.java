package mobil_szolgaltato;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FajlKezelo {
	
	private String fajlNev1;
	private String fajlNev2;
	
	public FajlKezelo(String f1, String f2)
	{
		fajlNev1 = f1;
		fajlNev2 = f2;
	}
	
	public void kiiras(Adatbazis adatB)
	{
		//ugyfelek kiirasa
        try
        {
            ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream(fajlNev1));
            os1.writeObject(adatB.getUgyfelek());
            os1.close();
        } catch(Exception e)
        {
        	Logger.getLogger(FajlKezelo.class.getName()).log(Level.SEVERE, "Ügyfél kiírás sikertelen");
        }
        
        //adatok kiirasa
        try
        {
            ObjectOutputStream os2 = new ObjectOutputStream(new FileOutputStream(fajlNev2));
            os2.writeObject(adatB.getAdatok());
            os2.close();
        } catch(Exception e)
        {
        	Logger.getLogger(FajlKezelo.class.getName()).log(Level.SEVERE, "Tarifaadat kiírás sikertelen");
        }
	}
	
	public void beolvas(Adatbazis adatB)
	{
		//ugyfelek beolvasasa
		try
	    {
	        ObjectInputStream is1 = new ObjectInputStream(new FileInputStream(fajlNev1));
	        adatB.setUgyfelek((ArrayList<Ugyfel>)is1.readObject());
	        is1.close();
	    } catch(Exception e)
	    {
	    	Logger.getLogger(FajlKezelo.class.getName()).log(Level.SEVERE, "Ügyfél beolvasás sikertelen");
	    }
		
		//tarifaadatok beolvasasa
		try
	    {
	        ObjectInputStream is2 = new ObjectInputStream(new FileInputStream(fajlNev2));
	        adatB.setAdatok((ArrayList<TarifaAdat>)is2.readObject());
	        is2.close();
	    } catch(Exception e)
	    {
	    	Logger.getLogger(FajlKezelo.class.getName()).log(Level.SEVERE, "Tarifaadat beolvasás sikertelen");
	    }

	}
}
