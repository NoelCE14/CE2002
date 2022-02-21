package RRPSS;

/**
 * AlaCarte is a subclass of MenuItem that only contains only a single dish.
 * @author Zhao Chen Xuan Joe
 * @version 1.1
 * @since 2021-11-3
 */
public class AlaCarte extends MenuItem{

	/**
	 * The food type of this ala carte item.
	 */
	private FoodType foodType;

	/**
	 * Creates a new ala carte item with the given parameters.
	 * @param name			The name of this ala carte item.
	 * @param description	The description of this ala carte item.
	 * @param price			The price of this ala carte item.
	 * @param foodType		The food type of this ala carte item.
	 */
	public AlaCarte(String name, String description, double price, FoodType foodType) {
		super(name, description, price);
		this.foodType = foodType;
	}

	/**
	 * Creates a new ala carte item with the parameters in String format. 
	 * Used to read the menu text file.
	 * @param name			The name of this ala carte item.
	 * @param description	The description of this ala carte item.
	 * @param price			The price of this ala carte item.
	 * @param foodType		The food type of this ala carte item.
	 */
	public AlaCarte(String name, String description, String price, String foodType) {
		super(name, description, Double.parseDouble(price));
		this.foodType = FoodType.valueOf(foodType);
	}
	
	/**
     * Format of the String to update to the text file.
     * @return String of all menu items.
     */
	@Override
	public String updateMenu() {
		return super.getName() + "," + super.getDescription() + "," + super.getPrice() + ","  + foodType.toString();
	}

	/**
     * Format of the order of ala carte items in the text file for readability.
     * @return String of all ala carte descriptions in this ala carte item.
     */
	@Override
	public String updateMenuDescription() {
		return "Name | Description | Price | FoodType ";
	}


	/**
	 * Format of item descriptions to be printed when displaying items.
	 */
	@Override
	public void displayItemDescription() {
		System.out.println("=============================================  AlaCarte Menu =============================================");
		System.out.printf("%-15s %-30s %-50s %s\n", "FoodType", "Name", "Description", "Price($)");
	}

	/**
	 * Prints all information of this ala carte item.
	 */
	@Override
	public void displayItem(){
		System.out.printf("%-15s %-30s %-50s %.2f\n", ("[" + foodType.toString() + "]"), super.getName(), super.getDescription(), super.getPrice());
	}

	/**
	 * Parameter to sort the list of ala carte items by.
	 * @return value of foodType in String format.
	 */
	@Override
	public String compareValue() {
		return String.valueOf(foodType.ordinal());
	}
	
	/**
	 * Gets the food type of the ala carte item.
	 * @return the food type of this ala carte item.
	 */
	public FoodType getFoodType() {
		return this.foodType;
	}

	/**
	 * Changes the food type of the ala carte item.
	 * @param foodType the new food type of this ala carte item.
	 */
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

}