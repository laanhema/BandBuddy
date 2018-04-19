package bandbuddy.testit;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import bandbuddy.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2018.04.19 22:18:27 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class GenreTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi59 */
  @Test
  public void testRekisteroi59() {    // Genre: 59
    Genre testigenre1 = new Genre("djent"); 
    Genre testigenre2 = new Genre("rock"); 
    testigenre1.rekisteroi(); 
    testigenre2.rekisteroi(); 
    assertEquals("From: Genre line: 64", 1, testigenre1.getTunnusNro()); 
    assertEquals("From: Genre line: 65", 2, testigenre2.getTunnusNro()); 
    assertEquals("From: Genre line: 66", "djent", testigenre1.getGenre()); 
    assertEquals("From: Genre line: 67", "rock", testigenre2.getGenre()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString91 */
  @Test
  public void testToString91() {    // Genre: 91
    Genre testigenre3 = new Genre("Psytrance"); 
    testigenre3.rekisteroi(); 
    Genre testigenre4 = new Genre(); 
    assertEquals("From: Genre line: 95", "3|Psytrance|", testigenre3.toString()); 
    assertEquals("From: Genre line: 96", "0||", testigenre4.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse109 */
  @Test
  public void testParse109() {    // Genre: 109
    Genre testigenre5 = new Genre("EDM"); 
    testigenre5.rekisteroi(); 
    Genre testigenre6 = new Genre(); 
    testigenre5.parse(""); 
    testigenre6.parse("1|Pop|"); 
    assertEquals("From: Genre line: 115", "4|EDM|", testigenre5.toString()); 
    assertEquals("From: Genre line: 116", "1|Pop|", testigenre6.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetNimi148 */
  @Test
  public void testSetNimi148() {    // Genre: 148
    Genre testigenre7 = new Genre(); 
    testigenre7.setNimi("Blues"); 
    assertEquals("From: Genre line: 151", "0|Blues|", testigenre7.toString()); 
  } // Generated by ComTest END
}