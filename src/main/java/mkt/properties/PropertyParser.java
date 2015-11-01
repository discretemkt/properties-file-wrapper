package mkt.properties;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
class PropertyParser {
    
    private enum STATE {
        NAME, AFTER_NAME, BEFORE_VALUE, VALUE;
    }
    
    public static List<Property> load(List<LogicalLine> logicalLineList) throws IllegalArgumentException {
        if (logicalLineList == null) {
            throw new IllegalArgumentException();
        }
        List<Property> list = new LinkedList<>();
        for (LogicalLine logicalLine : logicalLineList) {
            String stringValue = logicalLine.getStringValue();
            StringBuilder nameStringValue = new StringBuilder();
            StringBuilder valueStringValue = new StringBuilder();
            StringBuilder name = new StringBuilder();
            StringBuilder value = new StringBuilder();
            STATE state = STATE.NAME;
            for (int i = 0; i < stringValue.length(); i++) {
                char c = stringValue.charAt(i);
                if (c == '\t' || c == '\f' || c == ' ') {
                    if (state == STATE.NAME) {
                        state = STATE.AFTER_NAME;
                    } else if (state == STATE.VALUE) {
                        valueStringValue.append(c);
                        value.append(c);
                    }
                    continue;
                }
                if (c == ':' || c == '=') {
                    if (state == STATE.NAME || state == STATE.AFTER_NAME) {
                        state = STATE.BEFORE_VALUE;
                    } else {
                        state = STATE.VALUE;
                        valueStringValue.append(c);
                        value.append(c);
                    }
                    continue;
                }
                if (c != '\\') {
                    if (state == STATE.NAME) {
                        nameStringValue.append(c);
                        name.append(c);
                    } else {
                        state = STATE.VALUE;
                        valueStringValue.append(c);
                        value.append(c);
                    }
                    continue;
                }
                i++;
                if (i == stringValue.length()) {
                    throw new IllegalArgumentException();
                }
                c = stringValue.charAt(i);
                String escapedChars;
                char unescapedChar = c;
                switch (c) {
                    case 't':
                        escapedChars = "\\t";
                        unescapedChar = '\t';
                        break;
                    case 'n':
                        escapedChars = "\\n";
                        unescapedChar = '\n';
                        break;
                    case 'f':
                        escapedChars = "\\f";
                        unescapedChar = '\f';
                        break;
                    case 'r':
                        escapedChars = "\\r";
                        unescapedChar = '\r';
                        break;
                    case '\\':
                        escapedChars = "\\\\";
                        unescapedChar = '\\';
                        break;
                    case 'u':
                        if (i + 4 >= stringValue.length()) {
                            throw new IllegalArgumentException();
                        }
                        i++;
                        String fourDigit = stringValue.substring(i, i + 4);
                        escapedChars = String.format("\\u%s", fourDigit);
                        try {
                            unescapedChar = (char) Integer.parseInt(fourDigit, 16);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException(e.toString(), e.getCause());
                        }
                        i += 3;
                        break;
                    default:
                        escapedChars = String.valueOf(c);
                        unescapedChar = c;
                }
                if (state == STATE.NAME) {
                    nameStringValue.append(escapedChars);
                    name.append(unescapedChar);
                } else {
                    state = STATE.VALUE;
                    valueStringValue.append(escapedChars);
                    value.append(unescapedChar);
                }
            }
            int lineNumber = logicalLine.getLineNumber();
            Property property = new Property(
                    logicalLine.getLineNumber(),
                    nameStringValue.toString(),
                    valueStringValue.toString(),
                    name.toString(),
                    value.toString()
            );
            list.add(property);
        }
        return list;
    }
    
}
