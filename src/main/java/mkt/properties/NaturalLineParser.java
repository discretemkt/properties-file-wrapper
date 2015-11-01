package mkt.properties;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
class NaturalLineParser {
    
    public static List<NaturalLine> load(Reader reader) throws IllegalArgumentException, IOException {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        List<NaturalLine> list = new LinkedList<>();
        PushbackReader r = new PushbackReader(reader);
        StringBuilder sb = new StringBuilder();
        for (int i = r.read(); i != -1; i = r.read()) {
            char c = (char) i;
            if (c == '\n') {
                list.add(new NaturalLine(list.size() + 1, sb.toString(), NaturalLineTerminator.LF));
                sb.delete(0, sb.length());
                continue;
            }
            if (c == '\r') {
                i = r.read();
                if (i == '\n') {
                    list.add(new NaturalLine(list.size() + 1, sb.toString(), NaturalLineTerminator.CRLF));
                    sb.delete(0, sb.length());
                    continue;
                }
                list.add(new NaturalLine(list.size() + 1, sb.toString(), NaturalLineTerminator.CR));
                sb.delete(0, sb.length());
                if (i == -1) {
                    break;
                }
                r.unread(i);
                continue;
            }
            sb.append(c);
        }
        if (0 < sb.length()) {
            list.add(new NaturalLine(list.size() + 1, sb.toString(), NaturalLineTerminator.NONE));
        }
        return list;
    }
    
}
