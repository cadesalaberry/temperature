/**
* @author cadesalaberry
*/
package temperature;

public class AlphaTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.print("Testing the Class Temperature... ");
        
        if (!testClass(0,Units.CELCIUS,0,32,273.15)){
            System.err.print("\nTest Celcius error.");
        }
        if (!testClass(32,Units.FAHRENHEIT,0,32,273.15)){
            System.err.print("\nTest Fahrenheit error.");
        }
        if (!testClass(273.15,Units.KELVIN,0,32,273.15)){
            System.err.print("\nTest Kelvin error.");
        }
        else {System.out.println("OK");}
        
        System.out.print("\nTesting Comparison... ");
        
        if (!testComparison(Units.CELCIUS)){
            System.err.print("\nTest Celcius error.");
        }
        else if (!testComparison(Units.FAHRENHEIT)){
            System.err.print("\nTest Fahrenheit error.");
        }
        else if (!testComparison(Units.KELVIN)){
            System.err.print("\nTest Kelvin error.");
        }
        else {System.out.println("OK");}
        
        System.out.print("\nTesting Equals & HashCode... ");
        
        if (!testEquals()){
            System.err.print("\nTest error.");
        }
        else {System.out.println("OK");}        

    }
    
    public static boolean testClass(double value, Units units, double C, double F, double K)
    {
        Temperature temp = new Temperature(value, units);
        
        temp.changeUnits(Units.CELCIUS);
        boolean celcius = C == temp.getValue();
        if (!celcius) {display(temp);}
        
        temp.changeUnits(Units.KELVIN);
        boolean kelvin = K == temp.getValue();
        if (!kelvin) {display(temp);}
        
        
        temp.changeUnits(Units.FAHRENHEIT);
        boolean fahrenheit = F == temp.getValue();
        if (!fahrenheit) {display(temp);}
        
        return celcius && kelvin && fahrenheit;
    }
    
    public static boolean testComparison(Units units){
        
        Temperature a = new Temperature(1, units);
        Temperature aa = new Temperature(1, units);
        Temperature b = new Temperature(16000000, units);
        Temperature c = new Temperature(-200, units);
        Temperature d = new Temperature(0, units);
        
        boolean equal = a.compareTo(aa) == 0 && aa.compareTo(a) == 0;
        if(!equal){display(a);display(aa);}
        
        boolean less = a.compareTo(b) == -1 && c.compareTo(d) == -1;
        if(!less){display(a);display(b);display(c);display(d);}
        
        boolean more = b.compareTo(a) == 1 && d.compareTo(c) == 1;
        if(!more){display(a);display(b);display(c);display(d);}
        
        return equal && less && more;
    }
    
    public static boolean testEquals(){
        
        Temperature a = new Temperature(0, Units.CELCIUS);
        Temperature aa = new Temperature(32, Units.FAHRENHEIT);
        Temperature b = new Temperature(0, Units.KELVIN);
        
        return a.equals(aa) && aa.equals(a) && !b.equals(a) && !a.equals(b);
    
    }
    public static void display(Temperature temp){
        
        System.out.println("Object Temperature:");
        System.out.println("Value: " + temp.getValue() + "\tString: " + temp.toString());
        System.out.println("celcius: " + temp.celcius);
        System.out.println("kelvin: " + temp.kelvin);
        System.out.println("fahrenheit: " + temp.fahrenheit);
    }

}
