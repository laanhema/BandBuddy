package bandbuddy;
/**
 * 
 */


import java.util.*;
import java.io.*;

/**
 * Genret-luokka
 * Sis�lt�� tietorakenteen josta l�ytyy kaikki yksitt�iset genre-alkiot
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 18.04.2018
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
	
    
    /**
     * Testip��ohjelma
     * @param args ei k�yt�ss�
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
     * Hakee kaikki genret
     * @param tunnusnro toistaiseksi ei mit��n
     * @return tietorakenne jossa on kaikki genret.
     * #import java.util.*;
     * @example
     * 
     * <pre name="test">
     * Genret i = new Genret();
     * Genre a = new Genre("EDM"); 
     * a.rekisteroi(); 
     * i.lisaa(a);
     * Genre b = new Genre("Folk"); 
     * b.rekisteroi(); 
     * i.lisaa(b);
     * Genre c = new Genre("Metal"); 
     * c.rekisteroi(); 
     * i.lisaa(c);
     * i.getLkm() === 3;
     * i.hGenre(-1) === "ei toimi";
     * i.hGenre(1) === "EDM";
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
     * lis�� genren tietorakenteeseen
     * @param genre lis�tt�v� genre
     */
    public void lisaa(Genre genre) {
    	this.alkiot.add(genre);
    }

    
    /**
     * Lukee genret tiedostosta ja lis�� ne tietorakenteeseen
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * VertaaTiedosto.kirjoitaTiedosto("testi.dat", ";gid|genre|\n1|Jazz|\n2|Rock|\n3|Metal|\n4|Pop|\n5|EDM|\n");
     * Genret testiGenreLuokka = new Genret();
     * testiGenreLuokka.setLuettavaTiedosto("testi.dat");
     * testiGenreLuokka.lueTiedostosta();
     * VertaaTiedosto.tuhoaTiedosto("testi.dat");
     * testiGenreLuokka.getLkm() === 5;
     * testiGenreLuokka.hGenre(1) === "Jazz";
     * testiGenreLuokka.hGenre(2) === "Rock";
     * testiGenreLuokka.hGenre(3) === "Metal";
     * testiGenreLuokka.hGenre(4) === "Pop";
     * testiGenreLuokka.hGenre(5) === "EDM";
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
     * Lukee tietorakenteen alkiot ja luo sen mukaa rivej� tiedostoon
     */
    public void kirjoitaTiedostoon() {
        String kohdetiedostonNimi = this.tiedostonNimi;
        
        try ( PrintStream fo = new PrintStream(new FileOutputStream(kohdetiedostonNimi))) {
            fo.println(";gid|genre|");
            for (Genre ge : this.alkiot) {
                fo.println(ge.toString());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Tiedostoon kirjoittaminen ei onnistunut! " + fnfe.getMessage());
        }
    }
    

    /**
     * @return genret-listan koko
     */
    public int getLkm() {
    	return alkiot.size();
    }
        
    
    /**
     * Iteraattori kaikkien genrien l�pik�ymiseen.
     * return genre-iteraattori
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
	 * Etsii merkkijonoa vastaavan genren, jos l�ytyy
	 * @param etsittava        etsittava genre
	 * @return                 etsitt�v�n genre, muutoin null
	 */
	public Genre loytyykoGenrea(String etsittava) {
	    for (Genre alkio : this.alkiot) {
	        if (alkio.getGenre().equalsIgnoreCase(etsittava)) return alkio;
	    }
	    return null;
	}
	
	
}