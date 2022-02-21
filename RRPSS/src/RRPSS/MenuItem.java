package RRPSS;

/**
 * MenuItem is the abstract base class of all forms of menu items in the menu.
 * The item to be added into the menu list must first extend this class.
 * @author Chua Chong Yih
 * @version 1.0
 * @since 2021-11-06
 */
public abstract class MenuItem {
    
    /**
     * The name of the menu item.
     */
    private String name;

    /**
     * The description of the menu item.
     */
	private String description;

    /**
     * The price of the menu item.
     */
	private double price;

    /**
     * Creates a new menu item with the given parameters.
     * @param name          The name of the menu item.
     * @param description   The description of the menu item.
     * @param price         The price of the menu item.
     */
    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Displays all menu items in the menu array list.
     */
    public abstract void displayItem();

    /**
     * Description of menu items to be printed
     * @return String of menu item descriptions.
     */
    public abstract void displayItemDescription();

    /**
     * Format of the String to update to the text file.
     * Constructor must be set up to accomodate this String format.
     * @return String of all menu items.
     */
	public abstract String updateMenu();

    /**
     * Format of the order of menu items in the text file for readability.
     * @return String of all menu descriptions in the menu item.
     */
	public abstract String updateMenuDescription();

    /**
	 * Parameter to sort the list of menu items by.
     * Default sorts by menu item name.
	 * @return default comparison value.
	 */
    public String compareValue() {
        return this.name;
    };

    /**
	 * Gets the name of the menu item.
	 * @return the name of this menu item.
	 */
    public String getName() {
		return this.name;
	}

    /**
	 * Changes the name of the menu item.
	 * @param name the new name of this menu item.
	 */
	public void setName(String name) {
		this.name = name;
	}

    /**
	 * Gets the description of the menu item.
	 * @return the description of this menu item.
	 */
	public String getDescription() {
		return this.description;
	}

    /**
	 * Changes the description of the menu item.
	 * @param description the new description of this menu item.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
	 * Gets the price of the menu item.
	 * @return the price of this menu item.
	 */
	public double getPrice() {
		return this.price;
	}

    /**
	 * Changes the price of the menu item.
	 * @param price the new price of this menu item.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}