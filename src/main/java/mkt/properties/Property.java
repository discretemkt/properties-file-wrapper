package mkt.properties;

/**
 * Represents a property. An instance of this class is immutable.
 * 
 * @since 0.0.1
 * @author mkt
 */
public class Property {
    
    private final int lineNumber;
    private final String nameStringValue;
    private final String valueStringValue;
    private final String name;
    private final String value;
    
    Property(int lineNumber, String nameStringValue, String valueStringValue, String name, String value) throws IllegalArgumentException {
        if (lineNumber < 0 || nameStringValue == null || valueStringValue == null) {
            throw new IllegalArgumentException();
        }
        this.lineNumber = lineNumber;
        this.nameStringValue = nameStringValue;
        this.valueStringValue = valueStringValue;
        this.name = name;
        this.value = value;
    }
    
    /**
     * Returns the line number where the logical line that represents this property is positioned or starts in the properties file.
     * The line number is not less than 1.
     * 
     * @return the line number
     */
    public int getLineNumber() {
        return lineNumber;
    }
    
    /**
     * Returns the string value that represents the property name.
     * Escaped characters are not decoded, and the returned string value is as it appears in the logical line.
     * 
     * @return the string value that represents the property name.
     */
    public String getNameStringValue() {
        return nameStringValue;
    }
    
    /**
     * Returns the string value that represents the property value.
     * Escaped characters are not decoded, and the returned string value is as it appears in the logical line.
     * 
     * @return the string value that represents the property value.
     */
    public String getValueStringValue() {
        return valueStringValue;
    }
    
    /**
     * Returns the property name.
     * 
     * @return the property name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the property value.
     * 
     * @return the property value
     */
    public String getValue() {
        return value;
    }
    
}
