package mkt.properties;

/**
 * Represents line terminators.
 * 
 * @since 0.0.1
 * @author mkt
 */
public enum NaturalLineTerminator {
    
    /**
     * Means the associated natural line is followed by no line terminator,
     * that is to say, the EOF is reached in the end of the natural line.
     * <br />
     * {@link #toString()} of this enum object returns an empty string.
     */
    NONE(""),
    
    /**
     * Means the associated natural line is followed by <tt>"\n"</tt>.
     * <br />
     * {@link #toString()} of this enum object returns <tt>"\n"</tt>.
     */
    LF("\n"),
    
    /**
     * Means the associated natural line is followed by <tt>"\r"</tt>.
     * <br />
     * {@link #toString()} of this enum object returns <tt>"\r"</tt>.
     */
    CR("\r"),
    
    /**
     * Means the associated natural line is followed by <tt>"\r\n"</tt>.
     * <br />
     * {@link #toString()} of this enum object returns <tt>"\r\n"</tt>.
     */
    CRLF("\r\n");
    
    private final String stringValue;
    
    private NaturalLineTerminator(String stringValue) {
        this.stringValue = stringValue;
    }
    
    /**
     * Returns the string value that denotes this line terminator.
     * 
     * @return the string value that denotes this line terminator
     */
    @Override
    public String toString() {
        return stringValue;
    }
    
}
