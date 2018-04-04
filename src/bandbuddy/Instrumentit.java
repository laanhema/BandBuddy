/**
 * 
 */
package bandbuddy;

import java.util.*;
import java.io.*;

/**
 * Instrumentti-luokka
 * Instrumentti2 luokan yll�pito alkiomuodossa.
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class Instrumentit implements Iterable<Instrumentti> {
	
	// private boolean muutettu = false;
	private String tiedostoPerus = "instrumentit.dat";
	
	/* Instrumenttien lista */
	private final Collection<Instrumentti> alkiot = new ArrayList<Instrumentti>();
	
	
    /**
     * Testip��ohjelma
     * @param args ei k�yt�ss�
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
     * @param tunnusnro toistaiseksi ei mit��n
     * @return tietorakenne jossa on kaikki instrumentit.

     * #import java.util.*;
     * @example
     * <pre name="test">
     *  Instrumentit i = new Instrumentit();
     *  Instrumentti a = new Instrumentti("kitara"); a.rekisteroi(); i.lisaa(a);
     *  Instrumentti b = new Instrumentti("rummut"); b.rekisteroi(); i.lisaa(b);
     *  Instrumentti c = new Instrumentti("basso"); c.rekisteroi(); i.lisaa(c);
     *  i.soitin(1) === "kitara";
     *  i.soitin(3) === "basso";
     * </pre> 
     */
    public String soitin(int tunnusnro) {
         List<Instrumentti> kaikki = new ArrayList<Instrumentti>();
         for (Instrumentti soitin : alkiot) {
        	  kaikki.add(soitin);
         }
         for (int i = 0; i < kaikki.size(); i++) {
              if (tunnusnro == kaikki.get(i).getTunnusNro())return kaikki.get(i).getInstrumentti();
         }
         return "ei toimi";        
    }
    
    
    /**
     * lis�� instrumentin ja ottaa sen omistukseen tietorakenteessa
     * @param soitin lis�tt�v� instrumentti.
     */
    public void lisaa(Instrumentti soitin) {
    	alkiot.add(soitin);
    }
    
    
    /*
     
     
    public void tallenna() {
        if ( !muutettu ) return;

        File fbak = new File(getVara());
        File ftied = new File(getTiedostoPerus());
        fbak.delete(); 
        ftied.renameTo(fbak); 

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Instrumentti in : this) {
                fo.println(in.toString());
            }
        } catch ( FileNotFoundException ex ) {
        	System.err.println("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
        	System.err.println("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    */

    
    /**
     * Lukee instrumentit tiedostosta
     */
    public void lueTiedostosta() {
    	try ( BufferedReader fi = new BufferedReader(new FileReader(this.tiedostoPerus)) ) {
            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                if (rivi.contains("ä") ) rivi = rivi.replaceAll("ä", "�");
                if (rivi.contains("ö") ) rivi = rivi.replaceAll("ö", "�");
                Instrumentti in = new Instrumentti();
                in.parse(rivi); 
                lisaa(in);
            }
            // muutettu = false;

        } catch ( FileNotFoundException fnfe ) {
           System.err.println("tiedoston lukeminen ei onnistunut" + fnfe.getMessage());
        } catch ( IOException e ) {
            System.err.println("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
        
    
    /**
     * Lukee tietorakenteen alkiot ja luo sen mukaa rivej� tiedostoon
     */
    public void kirjoitaTiedostoon() {
        String kohdetiedostonNimi = this.tiedostoPerus;
        
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
     * Instrumentin alustaminen
     */
    public Instrumentit() {
    	//
    }
    
    
    /**
     * Iteraattori kaikkien instrumenttejen l�pik�ymiseen.
     * return instrumentti-iteraattori
     */
	@Override
	public Iterator<Instrumentti> iterator() {
		return alkiot.iterator();
	}

	
}