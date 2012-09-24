/**
 * @author cadesalaberry
 * I will try to optimize this code
 * so that it induces the less computation possible.
 */

package temperature;
    
/**
 * We want to use the Java collections library to sort 
 * a collection of temperatures. Therefore, the {@code Temperature} 
 * class implements the {@code Comparable} interface by overriding 
 * {@code compareTo} method.
 */

public class Temperature implements Comparable<Temperature> {
    
    Units units;
    double value;
    
    double celcius;
    double fahrenheit;
    double kelvin;
    
    /**
     * Create a new {@code Temperature} with given attributes
     * @param value numerical value of {@code Temperature}
     * @param units {@code Units} of {@code Temperature}
     */
  public Temperature (double value, Units units) {
    this.units = units;
    this.value = value;
    
      if (this.units == Units.CELCIUS){
          this.celcius = value;
          this.fahrenheit = value * 9/5 + 32;
          this.kelvin = value + 273.15;
      } else if (this.units == Units.FAHRENHEIT) {
          this.celcius = (value - 32) * 5/9;
          this.fahrenheit = value;
          this.kelvin = (value - 32) * 5/9 + 273.15;
      } else if (this.units == Units.KELVIN){
          this.celcius = value - 273.15;
          this.fahrenheit = (value - 273.15) * 9/5 + 32 ;
          this.kelvin = value;
      } else {
          System.err.println("Weirdo! This is not a valid unit!");
      }
      
  }

  /**
    * Get the value of the {@code Temperature}
    * @return numerical value of the {@code Temperature} in the current {@code Units}
    */
  public double getValue() { 
    return this.value;
  }

  /**
   * Get the current {@code Units} of the {@code Temperature}
   * @return current {@code Units} of {@code Temperature}
   */
  public Units getUnits() {
    return this.units;
  }

  /**
   * Change the current {@code Units} of the {@code Temperature}. 
   * Changing the {@code Units} also changes the numerical value 
   * in a consistent manner.
   * @param units the new {@code Units} 
   */
    public void changeUnits(Units units) {
      
      /**
       * Gets the corresponding value already calculated.
       */
      
        if (units == Units.CELCIUS){ this.value = celcius;}
        else if (units == Units.KELVIN){ this.value = kelvin;}
        else if (units == Units.FAHRENHEIT){ this.value = fahrenheit;}

        this.units = units;
    }
    
    /**
     * Gets the Temperature in the specified unit,
     * without modifying the unit.
     */
    private double getTemperatureIn(Units units) {
        
        if (units == Units.CELCIUS){ return celcius;}
        else if (units == Units.KELVIN){ return kelvin;}
        else if (units == Units.FAHRENHEIT){ return fahrenheit;}
        else {System.err.println("Hey Buddy, you might reconsider your choice of units...");return 0;}
    }
  /** 
   * Convert the {@code Temperature} to {@code String}. The output is
   * as follows
   * <pre><code>
   *    Temperature temperature = new Temperature(0, Units.CELCIUS);
   *    System.out.println(temperature.toString()); // prints "0 Â°C"
   *    temperature.changeUnits(Units.FAHRENHEIT);
   *    System.out.println(temperature.toString()); // prints "32 Â°F"
   *    temperature.changeUnits(Units.KELVIN);
   *    System.out.println(temperature.toString()); // prints "273.15 K"
   * </code></pre>
   */
  @Override
  public String toString() {
    
      /**
       * Cast the value to get 2 significant figures.
       */
      double iTemp = this.value*100;
      
      int iTemp2 = (int) iTemp;
      iTemp2 = iTemp2/100;
      
      String string = Double.toString(iTemp2);
      
      if (this.units == Units.CELCIUS) {string += " °C";}
      else if (this.units == Units.FAHRENHEIT) {string += " °F";}
      else if (this.units == Units.KELVIN) {string += " K";}
      
      return string;
  }

  /**
   * In order to implement {@code Comparable}, we need to override
   * the {@code compareTo} method. 
   * @param temperature The {@code Temperature} to compare against
   * @return -1 if current object is less than {@code temperature}
   *          0 if both are equal
   *          1 if current object is greater than {@code temperature}
   */
  @Override
  public int compareTo (Temperature temperature) {
    
      double a = this.getTemperatureIn(temperature.getUnits());
      double b = temperature.getValue();
      
      return (a == b ? 0 : a > b ? 1 : -1);
  }

  /**
   * Indicates whether some object is "equal" to this one.
   * To maintain consistency, whenever a class overrides 
   * {@code compareTo}, it must override {@code equals} so 
   * that
   * <pre>
   *   <code>o1.compareTo(o2) == 0</code> implies <code>o1.equals(o2) == true</code>
   * </pre>
   * See the API documentation of {@code Object} class for more details.
   */
  @Override
    public boolean equals(Object o) {
        
        return (this.hashCode() == o.hashCode());
        
    }

    /**
     * Return a hash code of the object. To maintain consistency,
     * whenever a class overrides {@code equals} it mush also override
     * {@code hashCode} in such a manner that 
     * <pre>
     *   <code>o1.equals(o2) == true</code> implies <code>o1.hashCode() == o2.hashCode()</code>
     * </pre>
     * See the API documentation of {@code Object} class for more details.
     */
    
    @Override
    public int hashCode() {
    
      
      int hash = 17*Double.valueOf(kelvin).hashCode();
      
      /**      
       * Line to add for distinction between temperature units as well
       * hash = hash * 17 + kelvin.hashCode();
       * hash = hash * 31 + this.units.hashCode();
       */
 
      return hash;
  }
}