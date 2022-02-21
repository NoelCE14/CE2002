package RRPSS;

import java.util.*;

/**
 * PromoSet is a subclass of MenuItem that only contains a list of ala carte items.
 * @author Zhao Chen Xuan Joe
 * @version 1.1
 * @since 2021-11-3
 */
public class PromoSet extends MenuItem {

	/**
	 * The list of ala carte items in this promotional set.
	 */
	private ArrayList<AlaCarte> itemList = new ArrayList<>();	

	/**
	 * The discount of this promotional set.
	 */
	private double discount;

	/**
	 * The promo type of this promotional set.
	 */
	private PromoType promoType;

	/**
	 * Creates a new promotional set item with the given parameters with an empty item list.
	 * @param name			The name of this promotional set.
	 * @param description	The description of this promotional set.
	 * @param price			The price of this promotional set.
	 * @param discount		The discount of this promotional set.
	 * @param promoType		The promo type of this promotional set.
	 */
	public PromoSet(String name, String description, double price, double discount, PromoType promoType) {
		super(name, description, price);
		this.promoType=promoType;
		this.discount = discount;	
	}

	/**
	 * Creates a new promotional set item with the parameters in String format.
	 * Used to read the menu text file.
	 * List of ala carte items is updated with the content of the menu text file. 
	 * @param name			The name of this promotional set.
	 * @param description	The description of this promotional set.
	 * @param price			The price of this promotional set.
	 * @param discount		The discount of this promotional set.
	 * @param promoType		The promo type of this promotional set.
	 * @param itemList		The list of ala carte items to be added into itemList.
	 */
	public PromoSet(String name, String description, String price, String discount, String promoType, String itemList) {
		super(name, description, Double.parseDouble(price));
		this.discount = Double.parseDouble(discount);
		this.promoType = PromoType.valueOf(promoType);

		String[] str = itemList.split("-");
		for (int i = 0; i < str.length; i++) {
			String[] substr = str[i].split("/"); 
			if (substr.length == 0)
				break;
			addItemToSet(new AlaCarte(substr[0], substr[1], substr[2], substr[3]));
		}
		
	}

	/**
     * Format of the String to update to the text file.
     * @return String of all promotional set items.
     */
	@Override
	public String updateMenu() {
		String info = super.getName() + "," + super.getDescription() + "," + super.getPrice() + "," + this.discount + "," + this.promoType.toString() + ",";
		if (itemList.size() == 0)
			info += "/";
		for (AlaCarte m : itemList) 
			info += m.getName() + "/" + m.getDescription() + "/" + m.getPrice() + "/" + m.getFoodType() + "-";

		return info;
		
	}

	/**
     * Format of the order of promotional set items in the text file for readability.
     * @return String of all menu descriptions in this menu item.
     */
	@Override
	public String updateMenuDescription() {
		return "Name | Description | Price | Discount | PromoType | ItemList";
	}

	/**
     * Prints all information of this promotional set item.
     */
	@Override
    public void displayItem() {
        String info = "";
        for (AlaCarte item : itemList)
            info += "| " + item.getName() +  " ";
        info+="|";
        System.out.printf("%-15s %-20s %-30s %-60s %.2f\n", ("[" + promoType.toString() + "]"), super.getName(), super.getDescription(), info, super.getPrice());
    }

	/**
	 * Format of item descriptions to be printed when displaying items.
	 */
	@Override
    public void displayItemDescription() {
        System.out.println("============================================================ Promo Set Menu ============================================================");
        System.out.printf("%-15s %-20s %-30s %-60s %s\n", "FoodType", "Name", "Description", "Item List", "Price");
    }

	/**
	 * Parameter to sort the list of promotional set items by.
	 * @return value of promoType in String format.
	 */
	@Override
	public String compareValue() {
		return String.valueOf(promoType.ordinal());
	}

	/**
	 * Adds a new ala carte item into the item list of this promotional set.
	 * @param item AlaCarte object to be added into itemList.
	 * @return 1 if added succesfully and -1 if failed.
	 */
	public int addItemToSet(AlaCarte item){
		if (searchItem(item.getName()) != null){
			System.out.println("This item " + item.getName() + " already in promoSet.");
			return -1;
		}

		itemList.add(item);
		super.setPrice(getPrice() + item.getPrice());
		return 1;
	}

	/**
	 * Removes an existing ala carte item from this promotional set.
	 * @param item AlaCarte item to be remoeved from itemList.
	 */
	public void removeItemFromSet(AlaCarte item){
		if (searchItem(item.getName())!= null){
			itemList.remove(item);
			super.setPrice(getPrice() - item.getPrice());
			System.out.println("Remove "+ item.getName() + " from Set successfully.");
			return;
		}
		System.out.println("The item "+ item.getName() + " is not in the Set.");
	}

	/**
	 * Search the list of ala carte item in this promotional set.
	 * @param name the name of ala carte item to be found.
	 * @return AlaCarte object with the matching name.
	 */
	public AlaCarte searchItem(String name){
        for (AlaCarte i : itemList){
            if (i.getName().equals(name)){
                return i;
            }
        }

        return null;
    }

	/**
	 * Gets the discount of the promotional set item.
	 * @return the discount of this promotional set item.
	 */
	public double getDiscount() {
		return this.discount;
	}

	/**
	 * Changes the discount of the promotional set item.
	 * @param discount the new discount of this promotional set item.
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * Gets the promotional type of the promotional set item.
	 * @return the promotional type of this promotional set item.
	 */
	public PromoType getPromoType() {
		return this.promoType;
	}

	/**
	 * Changes the promotional type of the promotional set item.
	 * @param promoType the new promotional type of this promotional set item.
	 */
	public void setPromoType(PromoType promoType) {
		this.promoType = promoType;
	}

}