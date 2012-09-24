/**
 * @author cdesalaberry
 */

package temperature;

import static org.junit.Assert.*;
import org.junit.*;

public class TemperatureTest {
    
    @BeforeClass
    public static void beforeClass(){
    	System.out.println("Starting the Test module...");
    }
    
    /**
     * Test of getUnits method, of class Temperature.
     */
    @Test
    public void testGetUnits() {
        System.out.println("- getUnits function");
        Temperature instance = new Temperature(0.0, Units.CELCIUS);
        Units result = instance.getUnits();
        
        try {
        	
        	assertEquals(Units.CELCIUS, result);
        	
        } catch (AssertionError e){
        	
        	fail("getUnits of class Temperature is f***ing up bro.");
        }

        //
    }
    
    /**
     * Test of getValue method, of class Temperature.
     * It only checks if the value set is the one returned.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Temperature instance = new Temperature(40.999, Units.CELCIUS);
        double expResult = 40.999;
        double result = instance.getValue();
        
        try {
        	
            assertEquals(expResult, result, 0.0);

        } catch (AssertionError e){
        	
            fail("getValue method of class Temperature is NOT WORKING !!!");
        }
    }
    
    /**
     * Test of the Constructor, and thus conversion
     * of class Temperature.
     */
    @Test
    public void testChangeUnits(){
        System.out.println("Testing the Conversion... ");
        
        try {
        	
	        assertTrue(testConversion(0,Units.CELCIUS,0,32,273.15));
	        assertTrue(testConversion(32,Units.FAHRENHEIT,0,32,273.15));
	        assertTrue(testConversion(273.15,Units.KELVIN,0,32,273.15));
        
        } catch (AssertionError e) {
            
        	fail("The Constructor of class Temperature is acting weird...");
        }
    }

    /**
     * Test of toString method, of class Temperature.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Temperature instance = new Temperature(32.112, Units.FAHRENHEIT);
        String expResult = "32.11 °F";
        String result = instance.toString();
        
        display(instance);
        assertEquals(expResult, result);

        //fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Temperature.
     */
    @Test
    public void testCompareTo() {
        
        System.out.println("\nTesting Comparison... ");
        
        assertTrue(testComparison(Units.CELCIUS));
        assertTrue(testComparison(Units.FAHRENHEIT));
        assertTrue(testComparison(Units.KELVIN));  
       
        //fail("Yo man, teach your CompareTo method of class Temperature how to compare properly...");
    }

    /**
     * Test of equals method, of class Temperature.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Temperature a = new Temperature(0, Units.CELCIUS);
        Temperature aa = new Temperature(32, Units.FAHRENHEIT);
        Temperature b = new Temperature(0, Units.KELVIN);
        
        // Tests for equality and inequality
        assertTrue(a.equals(aa) && aa.equals(a) && !b.equals(a) && !a.equals(b));
        
        //fail("Equals method of class Temperature does not know what equals means anymore...");
    }

    /**
     * Test of hashCode method, of class Temperature.
     */
    @Test
    public void testHashCode() {
        /**
         * The hashCode test is already performed in the equals test.
         * (because it uses hashCode to determine equality)
         */
    }
    
    public static boolean testConversion(double value, Units units, double C, double F, double K)
    {
        Temperature temp = new Temperature(value, units);
        
        temp.changeUnits(Units.CELCIUS);
        boolean celcius = C == temp.getValue();

        temp.changeUnits(Units.KELVIN);
        boolean kelvin = K == temp.getValue();
        
        temp.changeUnits(Units.FAHRENHEIT);
        boolean fahrenheit = F == temp.getValue();
        
        return celcius && kelvin && fahrenheit;
    }
    
    public static boolean testComparison(Units units){
        
        Temperature a = new Temperature(1, units);
        Temperature aa = new Temperature(1, units);
        Temperature b = new Temperature(16000000, units);
        Temperature c = new Temperature(-200, units);
        Temperature d = new Temperature(0, units);
        
        // Tests equality
        boolean equal = a.compareTo(aa) == 0 && aa.compareTo(a) == 0;
        
        // Tests less operator
        boolean less = a.compareTo(b) == -1 && c.compareTo(d) == -1;

        // Tests more operator
        boolean more = b.compareTo(a) == 1 && d.compareTo(c) == 1;
      
        return equal && less && more;
    }
    
    public static void display(Temperature temp){
        
        System.out.println("Object Temperature:");
        System.out.println("Value: " + temp.getValue() + "\tString: " + temp.toString());
        System.out.println("celcius: " + temp.celcius);
        System.out.println("kelvin: " + temp.kelvin);
        System.out.println("fahrenheit: " + temp.fahrenheit);
    }
}
