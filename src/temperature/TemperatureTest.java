/**
 * @author cadesalaberry
 */

package temperature;

import static org.junit.Assert.*;
import org.junit.*;

public class TemperatureTest {
    
    @BeforeClass
    public static void beforeClass(){
    	System.out.println("Starting the Test module...\n");
    }
    
    @AfterClass
    public static void afterClass(){
    	System.out.println("\nTest module finished !!!\n");
    }
    
    /**
     * Tests getUnits method of class Temperature.
     * We create three objects temperature, with different units.
     * We then check back if the returned value is correct.
     */
    @Test
    public void testGetUnits() {
        System.out.println("- getUnits function");
        
        Temperature celcius = new Temperature(0.0, Units.CELCIUS);
        Temperature fahrenheit = new Temperature(0.0, Units.FAHRENHEIT);
        Temperature kelvin = new Temperature(0.0, Units.KELVIN);
        
        try {
        	assertEquals(Units.CELCIUS, celcius.getUnits());
        	assertEquals(Units.FAHRENHEIT, fahrenheit.getUnits());
        	assertEquals(Units.KELVIN, kelvin.getUnits());
        	
        } catch (AssertionError e){
        	
        	fail("getUnits of class Temperature is f***ing up bro.");
        }
    }
    
    /**
     * Test of getValue method, of class Temperature.
     * It only checks if the value set at the beginning is the one returned.
     */
    @Test
    public void testGetValue() {
        System.out.println("- getValue function");
        
        Temperature positive = new Temperature(40.999, Units.CELCIUS);
        Temperature negative = new Temperature(-200, Units.FAHRENHEIT);
        Temperature zero     = new Temperature(0.0, Units.KELVIN);
        
        try {
        	
            assertEquals(40.999, positive.getValue(), 0.0);
            assertEquals(-200  , negative.getValue(), 0.0);
            assertEquals(0.0   ,     zero.getValue(), 0.0);

        } catch (AssertionError e){
        	
            fail("getValue method of class Temperature is NOT WORKING !!!");
        }
    }
    
    /**
     * Tests the Constructor, and thus the conversion method
     * of class Temperature.
     */
    @Test
    public void testChangeUnits(){
        System.out.println("- changeUnits");
        
        try {
        	
	        assertTrue(testConversion(0,      Units.CELCIUS,    0, 32, 273.15));
	        assertTrue(testConversion(32,     Units.FAHRENHEIT, 0, 32, 273.15));
	        assertTrue(testConversion(273.15, Units.KELVIN,     0, 32, 273.15));
        
        } catch (AssertionError e) {
            
        	fail("The Constructor of class Temperature is acting weird...");
        }
    }
    
    /**
     * Tests the equals method of class Temperature.
     * we are testing for both equality and inequality,
     * we are trying a == b, and the other way around,
     * b == a
     * 
     * By structure,
     * there is no way the Temperature class can modify the unit,
     * or value of the object.
     * Thus, there is no need to check for that error.
     */
    @Test
    public void testEquals() {
    	
        System.out.println("- equals");
        
        Temperature a = new Temperature(0, Units.CELCIUS);
        Temperature b = new Temperature(0, Units.KELVIN);
        Temperature c = new Temperature(32, Units.FAHRENHEIT);
        
        try {
        	
        	assertTrue(a.equals(c) && c.equals(a) && !b.equals(a) && !a.equals(b));
        	
        } catch (AssertionError e){
        	
        	fail("Equals method of class Temperature does not know what equals means anymore...");
        }
    }

    
    /**
     * Allows us to test converting temperature only changing the parameters.
     * 
     * @param value
     * @param units
     * @param C
     * @param F
     * @param K
     * @return
     */
    private static boolean testConversion(double value, Units units, double C, double F, double K)
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
    
    
    
    /**
     * Prints a text version of the Temperature Object.
     * Used to help debugging the Temperature class.
     * 
     * @param temp
     */
    private static void displayTemperature(Temperature temp){
        
        System.out.println("Object Temperature:");
        System.out.println("Value: " + temp.getValue() + "\tString: " + temp.toString());
        System.out.println("celcius: " + temp.celcius);
        System.out.println("kelvin: " + temp.kelvin);
        System.out.println("fahrenheit: " + temp.fahrenheit);
    }
    
    /**
     * Test of toString method, of class Temperature.
     * Not really used, only has a debugging purpose.
     */
    @Deprecated
    @Test
    public void testToString() {
        System.out.println("- toString ");
        Temperature instance = new Temperature(32.112, Units.FAHRENHEIT);
        String expResult = "32.11 °F";
        String result = instance.toString();
        
        try {
        	
        	assertEquals(expResult, result);
        	
        } catch (AssertionError e){
        	
        	fail("Meuh, you don't really care if this test fails, it's not graded !!");
        }
    }
}
