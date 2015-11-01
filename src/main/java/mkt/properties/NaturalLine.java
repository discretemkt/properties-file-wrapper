package mkt.properties;

/**
 * Represents a natural line. An instance of this class is immutable.
 * 
 * @since 0.0.1
 * @author mkt
 */
public class NaturalLine {
    
    private final int lineNumber;
    private final String stringValue;
    private final NaturalLineTerminator lineTerminator;
    
    NaturalLine(int lineNumber, String stringValue, NaturalLineTerminator lineTerminator) throws IllegalArgumentException {
        if (lineNumber < 1 || stringValue == null || lineTerminator == null) {
            throw new IllegalArgumentException();
        }
        this.lineNumber = lineNumber;
        this.stringValue = stringValue;
        this.lineTerminator = lineTerminator;
    }
    
    /**
     * Returns the line number where this natural line is positioned in the properties file.
     * The line number is not less than 1.
     * 
     * @return the line number
     */
    public int getLineNumber() {
        return lineNumber;
    }
    
    /**
     * Returns the string value of this natural line.
     * The string value does not include the line terminator, so it can be empty.
     * 
     * @return the string value of this natural line
     */
    public String getStringValue() {
        return stringValue;
    }
    
    /**
     * Returns the line terminator that follows this natural line.
     * The line terminator is either of the following four values:
     * <ul>
     * <li>{@link NaturalLineTerminator#NONE} (no line terminator)</li>
     * <li>{@link NaturalLineTerminator#LF} ("\n")</li>
     * <li>{@link NaturalLineTerminator#CR} ("\r")</li>
     * <li>{@link NaturalLineTerminator#CRLF} ("\r\n")</li>
     * </ul>
     * 
     * @return the line terminator
     */
    public NaturalLineTerminator getLineTerminator() {
        return lineTerminator;
    }
    
}
