/**
 * 
 */
package bandbuddy;

import java.util.*;
import java.io.*;

/**
 * Instrumentti-luokka
 * Instrumentti2 luokan ylläpito alkiomuodossa.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class Instrumentit implements Iterable<Instrumentti> {
	
	private String tiedostonNimi = "instrumentit.dat";
	
	/* Instrumenttien lista */
	private final Collection<Instrumentti> alkiot = new ArrayList<Instrumentti>();
	
	
    /**
     * Instrumentit-luokan alustaminen ilman parametreja
     */
    public Instrumentit() {
    }
	
    
//	/**
//	 * Toinen alustaja testaamista varten
//	 * @param luettavaTiedosto     testitiedosto joka laitetaan attribuutiksi
//	 */
//    public Instrumentit(String luettavaTiedosto) {
//        this.tiedostoPerus = luettavaTiedosto;
//    }
    
    
    /**
     * Testipääohjelma
     * @param args ei käytässä
     */	
    public static void main(String[] args) {
    	 Instrumentit soitinlista = new Instrumentit();
         Instrumentti kitara = new Instrumentti("kitara");
         Instrumentti rummut = new Instrumentti("rummut");
         Instrumentti basso = new Instrumentti("basso");

         soitinlista.lisaa(kitara);
         soitinlista.lisaa(rummut);
         soitinlista.lisaa(basso);
         
         
       //  List<Instrumentti> soitinlista2 = soitinlista.soittimet(2);
       //  for (int i = 0; i < soitinlista2.size(); i++)
       //  System.out.println(soitinlista2.get(i).getInstrumentti());
         
         
    }
    
    
    /**
     * Hakee kaikki instrumentit
     * @param tunnusnro toistaiseksi ei mitään
     * @return tietorakenne jossa on kaikki instrumentit.
     * #import java.util.*;
     * @example
     * 
     * <pre name="test">
     * Instrumentit i = new Instrumentit();
     * Instrumentti a = new Instrumentti("Kitara"); 
     * a.rekisteroi(); 
     * i.lisaa(a);
     * Instrumentti b = new Instrumentti("Rummut"); 
     * b.rekisteroi(); 
     * i.lisaa(b);
     * Instrumentti c = new Instrumentti("Basso"); 
     * c.rekisteroi(); 
     * i.lisaa(c);
     * i.getLkm() === 3;
     * i.soitin(-1) === "ei toimi";
     * i.soitin(1) === "Kitara";
     * </pre> 
     */
    public String soitin(int tunnusnro) {
         List<Instrumentti> kaikki = new ArrayList<Instrumentti>();
         for (Instrumentti soitin : this.alkiot) {
        	  kaikki.add(soitin);
         }
         for (int i = 0; i < kaikki.size(); i++) {
              if (tunnusnro == kaikki.get(i).getTunnusNro()) {
                  return kaikki.get(i).getInstrumentti();
              }
         }
         return "ei toimi";
    }
    
    
    /**
     * lisää instrumentin tietorakenteeseen
     * @param soitin lisättävä instrumentti.
     */
    public void lisaa(Instrumentti soitin) {
    	this.alkiot.add(soitin);
    }

    
    /**
     * Lukee instrumentit tiedostosta ja lisää ne tietorakenteeseen
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * VertaaTiedosto.kirjoitaTiedosto("testi.dat", ";nid|instrumentti|\n1|Sähkökitara|\n2|Sähköbasso|\n3|Oboe|\n4|Fagotti|\n5|Saksofoni|\n");
     * Instrumentit testiInstrumentitLuokka = new Instrumentit();
     * testiInstrumentitLuokka.setLuettavaTiedosto("testi.dat");
     * testiInstrumentitLuokka.lueTiedostosta();
     * VertaaTiedosto.tuhoaTiedosto("testi.dat");
     * testiInstrumentitLuokka.getLkm() === 5;
     * testiInstrumentitLuokka.soitin(1) === "Sähkökitara";
     * testiInstrumentitLuokka.soitin(2) === "Sähköbasso";
     * testiInstrumentitLuokka.soitin(3) === "Oboe";
     * testiInstrumentitLuokka.soitin(4) === "Fagotti";
     * testiInstrumentitLuokka.soitin(5) === "Saksofoni";
     * </pre>
     */
    public void lueTiedostosta() {
    	try ( Scanner fi = new Scanner(new FileInputStream(new File(this.tiedostonNimi)), "ISO-8859-1") ) {
            String rivi;
            while (fi.hasNextLine()) {
                rivi = fi.nextLine().trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Instrumentti in = new Instrumentti();
                in.parse(rivi); 
                lisaa(in);
            }

        } catch ( FileNotFoundException fnfe ) {
           System.err.println("Tiedoston lukeminen ei onnistunut " + fnfe.getMessage());
        }
    }
        
    
    /**
     * Lukee tietorakenteen alkiot ja luo sen mukaa rivejä tiedostoon
     */
    public void kirjoitaTiedostoon() {
        String kohdetiedostonNimi = this.tiedostonNimi;
        
        try ( PrintStream fo = new PrintStream(new FileOutputStream(kohdetiedostonNimi))) {
            fo.println(";nid|instrumentti|");
            for (Instrumentti in : this.alkiot) {
                fo.println(in.toString());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Tiedostoon kirjoittaminen ei onnistunut! " + fnfe.getMessage());
        }
    }
    

    /**
     * @return instrumentin listan koko
     */
    public int getLkm() {
    	return alkiot.size();
    }
        
    
    /**
     * Iteraattori kaikkien instrumenttejen läpikäymiseen.
     * return instrumentti-iteraattori
     */
	@Override
	public Iterator<Instrumentti> iterator() {
		return alkiot.iterator();
	}	
	
	
	/**
	 * Laittaa luettavaksi tiedostoksi annetun merkkijonon
	 * @param tiedosto     tiedosto jota halutaan lukea
	 */
	public void setLuettavaTiedosto(String tiedosto) {
	    this.tiedostonNimi = tiedosto;
	}	
	
	
	/**
	 * Etsii merkkijonoa vastaavan instrumentin, jos löytyy
	 * @param etsittava        etsittava instrumentti
	 * @return                 etsittävän instrumentin, muutoin null
	 */
	public Instrumentti loytyykoInstrumenttia(String etsittava) {
	    for (Instrumentti alkio : this.alkiot) {
	        if (alkio.getInstrumentti().equalsIgnoreCase(etsittava)) return alkio;
	    }
	    return null;
	}
	
	
}