package bandbuddy.testit;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import bandbuddy.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2018.03.23 12:13:54 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class HenkiloJaInstrumenttiTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi98 */
  @Test
  public void testRekisteroi98() {    // HenkiloJaInstrumentti: 98
    HenkiloJaInstrumentti soittaja= new HenkiloJaInstrumentti(1); 
    soittaja.rekisteroi(); 
    assertEquals("From: HenkiloJaInstrumentti line: 101", 1, soittaja.getTunnusNro()); 
    HenkiloJaInstrumentti soittaja2= new HenkiloJaInstrumentti(1); 
    soittaja2.rekisteroi(); 
    assertEquals("From: HenkiloJaInstrumentti line: 104", 2, soittaja2.getTunnusNro()); 
  } // Generated by ComTest END
}