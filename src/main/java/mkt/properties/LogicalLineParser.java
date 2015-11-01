package mkt.properties;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
class LogicalLineParser {
    
    private static String skipPrecedingWhiteSpaces(String stringValue) {
        assert stringValue != null;
        for (int i = 0; i < stringValue.length(); i++) {
            char c = stringValue.charAt(i);
            if (c == '\t' || c == '\f' || c == ' ') {
                continue;
            }
            return stringValue.substring(i);
        }
        return "";
    }
    
    public static List<LogicalLine> load(List<NaturalLine> naturalLineList) throws IllegalArgumentException {
        if (naturalLineList == null) {
            throw new IllegalArgumentException();
        }
        List<LogicalLine> list = new LinkedList<>();
        int lineNumber = 1;
        boolean continued = false;
        StringBuilder sb = new StringBuilder();
        for (NaturalLine naturalLine : naturalLineList) {
            String stringValue = skipPrecedingWhiteSpaces(naturalLine.getStringValue());
            if (!continued) {
                if (stringValue.length() == 0) {
                    // We think of this natural line as a blank line.
                    continue;
                }
                if (stringValue.charAt(0) == '!' || stringValue.charAt(0) == '#') {
                    // We think of this natural line as a comment line.
                    continue;
                }
                lineNumber = naturalLine.getLineNumber();
            }
            continued = false;
            for (int i = stringValue.length() - 1; i >= 0; --i) {
                if (stringValue.charAt(i) != '\\') {
                    break;
                }
                continued = !continued;
            }
            sb.append(stringValue);
            if (continued) {
                sb.deleteCharAt(sb.length() - 1);
                continue;
            }
            list.add(new LogicalLine(lineNumber, sb.toString()));
            sb.delete(0, sb.length());
        }
        return list;
    }
    
}
