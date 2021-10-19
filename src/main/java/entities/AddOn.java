package entities;

/**
 * A class to represent an individual add-on that
 * can be added to a car.
 */
public class AddOn {
    private String name;
    private double price;
    private String description;

    /**
     * Constructs a new AddOn object with the given name.
     * The price and description fields are given placeholder values.
     * @param name
     */
    public AddOn(String name) {
        this(name, -1, "");
    }

    /**
     * Constructs a new AddOn objects with the given name, price,
     * and description.
     * @param name
     * @param price
     * @param description
     */
    public AddOn(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * Returns the name of this add-on
     * @return
     */
    public String getName() {
        return name;
    }

    // TODO: is this needed?
    /**
     * Updates the name of this add-on
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of this AddOn
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the price of this AddOn
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the description of this AddOn
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of this AddOn
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
