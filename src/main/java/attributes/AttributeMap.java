package attributes;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AttributeMap extends Attribute {

    // A map between attribute names and attributes
    private final Map<String, Attribute> items;

    /** Constructs a new, empty AttributeMap */
    public AttributeMap() {
        items = new HashMap<>();
    }


    /**
     * Adds the given item Object to the map with the given name
     *
     * @param name
     * @param item the Object to be added to the map
     * @throws ClassCastException
     */
    public void addItem(String name, Object item) throws ClassCastException {
        this.addItem(name, AttributeFactory.createAttribute(item));
    }

    /**
     * Adds the given item (Attribute) to the map with the given name
     *
     * @param name
     * @param item
     */
    public void addItem(String name, Attribute item) {
        items.put(name, item);
    }

    /**
     * Returns the item with the given name from this map If no item in the map exists with this
     * name, throws NullPointerException
     *
     * @param name
     * @return
     * @throws NullPointerException if no item exists in the map with the given name
     */
    public Attribute getItem(String name) throws NullPointerException {
        if (!items.containsKey(name)) {
            throw new NullPointerException("No item in map with this name");
        }
        return items.get(name);
    }

    /**
     * Returns the map between attribute names and Attributes stored in this AttributeMap
     *
     * @return
     */
    public Map<String, Attribute> getAttribute() {
        return items;
    }

    public static AttributeMap combine(AttributeMap first, AttributeMap second) {
        Map<String, Attribute> firstItems = first.getAttribute();
        Map<String, Attribute> secondItems = second.getAttribute();
        AttributeMap combined = new AttributeMap();
        for (String s : firstItems.keySet()) {
            combined.addItem(s, firstItems.get(s));
        }
        for (String s : secondItems.keySet()) {
            combined.addItem(s, secondItems.get(s));
        }
        return combined;
    }
}
