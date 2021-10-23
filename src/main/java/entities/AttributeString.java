package entities;

public class AttributeString extends Attribute {

    private final String item;

    public AttributeString(String item) {
        this.item = item;
    }

    public String getAttribute() {
        return item;
    }
}
