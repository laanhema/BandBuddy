package bandbuddy;
/**
 * 
 */


import java.util.*;
import java.io.*;

/**
 * Instrumentti-luokka
 * Instrumentti2 luokan ylläpito alkiomuodossa.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class Genret implements Iterable<Genre> {
	
	private String tiedostonNimi = "genret.dat";
	
	/* genrelista lista */
	private final Collection<Genre> alkiot = new ArrayList<Genre>();
	
	
    /**
     * Instrumentit-luokan alustaminen ilman parametreja
     */
    public Genret() {
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
    	 Genret genret = new Genret();
         Genre metalli = new Genre("metalli");


         genret.lisaa(metalli);
         
         
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
    public String hGenre(int tunnusnro) {
         List<Genre> kaikki = new ArrayList<Genre>();
         for (Genre genre : this.alkiot) {
        	  kaikki.add(genre);
         }
         for (int i = 0; i < kaikki.size(); i++) {
              if (tunnusnro == kaikki.get(i).getTunnusNro()) {
                  return kaikki.get(i).getGenre();
              }
         }
         return "ei toimi";
    }
    
    
    /**
     * lisää instrumentin tietorakenteeseen
     * @param soitin lisättävä instrumentti.
     */
    public void lisaa(Genre genre) {
    	this.alkiot.add(genre);
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
                Genre ge = new Genre();
                ge.parse(rivi); 
                lisaa(ge);
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
            fo.println(";nid|genre|");
            for (Genre ge : this.alkiot) {
                fo.println(ge.toString());
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
	public Iterator<Genre> iterator() {
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
	public Genre loytyykoGenrea(String etsittava) {
	    for (Genre alkio : this.alkiot) {
	        if (alkio.getGenre().equalsIgnoreCase(etsittava)) return alkio;
	    }
	    return null;
	}
	
	
}