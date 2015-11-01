package mkt.properties;

/**
 * Represents a logical line. An instance of this class is immutable.
 * <br />
 * Note: this class does not represent a comment line or a blank line.
 * 
 * @since 0.0.1
 * @author mkt
 */
public class LogicalLine {
    
    private final int lineNumber;
    private final String stringValue;
    
    LogicalLine(int lineNumber, String stringValue) throws IllegalArgumentException {
        if (lineNumber < 1 || stringValue == null) {
            throw new IllegalArgumentException();
        }
        this.lineNumber = lineNumber;
        this.stringValue = stringValue;
    }
    
    /**
     * Returns the line number where this logical line is positioned or starts in the properties file.
     * The line number is not less than 1.
     * 
     * @return the line number
     */
    public int getLineNumber() {
        return lineNumber;
    }
    
    /**
     * Returns the string value of this logical line.
     * The string value is not be empty, and does not start with white space characters.
     * <br />
     * Note: even if the string value starts with '!' or '#', this logical line is not a comment line.
     * 
     * @return the string value of this logical line
     */
    public String getStringValue() {
        return stringValue;
    }
    
}
