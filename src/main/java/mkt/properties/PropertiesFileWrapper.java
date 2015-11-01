package mkt.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
public class PropertiesFileWrapper {
    
    private final List<NaturalLine> naturalLineList;
    private final List<LogicalLine> logicalLineList;
    private final List<Property> propertyList;
    
    public PropertiesFileWrapper(Reader reader) throws IllegalArgumentException, IOException {
        naturalLineList = NaturalLineParser.load(reader);
        logicalLineList = LogicalLineParser.load(naturalLineList);
        propertyList = PropertyParser.load(logicalLineList);
    }
    
    public PropertiesFileWrapper(InputStream in) throws IllegalArgumentException, IOException {
        this(new InputStreamReader(in));
    }
    
    public Map<Integer, NaturalLine> toNaturalLineTable() {
        Map<Integer, NaturalLine> table = new TreeMap<>();
        for (NaturalLine naturalLine : naturalLineList) {
            table.put(naturalLine.getLineNumber(), naturalLine);
        }
        return table;
    }
    
    public  Map<Integer, LogicalLine> toLogicalLineTable() {
        Map<Integer, LogicalLine> table = new TreeMap<>();
        for (LogicalLine logicalLine : logicalLineList) {
            table.put(logicalLine.getLineNumber(), logicalLine);
        }
        return table;
    }
    
    public Map<Integer, Property> toPropertyTable() {
        Map<Integer, Property> table = new TreeMap<>(); 
        for (Property property : propertyList) {
            table.put(property.getLineNumber(), property);
        }
        return table;
    }
    
    public List<List<Property>> findDuplicatedEntries() {
        List<List<Property>> list = new LinkedList<>();
        Map<String, List<Property>> map = new HashMap<>();
        for (Property property : propertyList) {
            String name = property.getName();
            if (!map.containsKey(name)) {
                map.put(name, new LinkedList<Property>());
            }
            map.get(name).add(property);
        }
        for (List<Property> l : map.values()) {
            if (1 < l.size()) {
                list.add(l);
            }
        }
        return list;
    }
    
    public Map<String, String> toLegacyPropertyMap() {
        Map<String, String> map = new HashMap<>();
        for (Property property : propertyList) {
            map.put(property.getName(), property.getValue());
        }
        return map;
    }
    
    public Map<String, List<String>> toDuplicablePropertyMap() {
        Map<String, List<String>> map = new HashMap<>();
        for (Property property : propertyList) {
            String name = property.getName();
            if (!map.containsKey(name)) {
                map.put(name, new LinkedList<String>());
            }
            map.get(name).add(property.getValue());
        }
        return map;
    }
    
}
