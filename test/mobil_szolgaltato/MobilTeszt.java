package mobil_szolgaltato;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MobilTeszt {

	Adatbazis adatB;
	
	@Before
	public void init() {
		adatB = new Adatbazis();
	}
	
	@Test
	public void ugyfelLetrehozTeszt() {
		Ugyfel u = new Ugyfel("Béla", "206152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		assertEquals("Béla", u.getNev());
		assertEquals("206152816", u.getTelefonszam());
		assertEquals("Alap", u.getCsomag().getCsNev());
		assertEquals("1010 Budapest Fõ 10", u.getCim());
	}
	
	@Test
	public void tarifaLetrehozTeszt() {
		TarifaAdat a = new TarifaAdat("206152816");
		assertEquals("206152816", a.getTelefonszam());
		assertEquals(0, a.getSms());
		assertEquals(0, a.getPerc());
	}
	
	@Test
	public void smsKuldesTeszt() {
		TarifaAdat a = new TarifaAdat("206152816");
		a.sms();
		assertEquals(1, a.getSms());
		assertNotEquals(0, a.getSms());
		
	}
	
	@Test
	public void hivasInditasTeszt() {
		TarifaAdat a = new TarifaAdat("206152816");
		a.hivas(30);
		assertEquals(30, a.getPerc());
		assertNotEquals(0, a.getPerc());
	}
	
	@Test
	public void csomagIgenybevetelTeszt() {
		Ugyfel u1 = new Ugyfel("Béla1", "206152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		Ugyfel u2 = new Ugyfel("Béla2", "306152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		Ugyfel u3 = new Ugyfel("Béla3", "706152816", new MobiNet(), "1010", "Budapest", "Fõ", "10");
		
		adatB.felvesz(u1);
		adatB.felvesz(u2);
		adatB.felvesz(u3);
		
		assertEquals(2, adatB.hanyanVeszikIgenybe(new Alap()));
		assertEquals(1, adatB.hanyanVeszikIgenybe(new MobiNet()));
		assertEquals(0, adatB.hanyanVeszikIgenybe(new SMSMax()));
	}
	
	@Test
	public void azonosTelefonszamTeszt() {
		//ugyanaz Bela1 es Bela2 telefonszama
		//a masodikat nem veszi fel mert egyedinek kell lennie
		Ugyfel u1 = new Ugyfel("Béla1", "206152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		Ugyfel u2 = new Ugyfel("Béla2", "206152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		
		adatB.felvesz(u1);
		adatB.felvesz(u2);
		
		assertEquals(1, adatB.hanyanVeszikIgenybe(new Alap()));
	}
	
	@Test
	public void csomagNevTeszt() {
		Ugyfel u1 = new Ugyfel("Béla1", "206152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		Ugyfel u2 = new Ugyfel("Béla2", "306152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		
		assertEquals(u1.getCsomag().getCsNev(), u2.getCsomag().getCsNev());
	}
	
	@Test
	public void tarifaSzamolasTeszt() {
		Ugyfel u = new Ugyfel("Béla1", "206152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		
		adatB.felvesz(u);
		
		TarifaAdat a = adatB.keresAdat("206152816");
		
		assertEquals(1000, u.fizetendo(a.getSms(), a.getPerc()));
	}
	@Test

	public void azonosCsomagTeszt() {
		Ugyfel u1 = new Ugyfel("Béla1", "206152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		Ugyfel u2 = new Ugyfel("Béla2", "306152816", new Alap(), "1010", "Budapest", "Fõ", "10");
		
		assertTrue(u1.getCsomag().equals(u2.getCsomag()));
		assertFalse(u1.getTelefonszam().equals(u2.getTelefonszam()));
	}
	
	@Test
	public void SMSMaxTarifaTeszt() {
		Csomag cs = new SMSMax();
		
		//minden harmadik ingyen van
		assertEquals(3016, cs.tarifa(3, 0));
	}
	
}
