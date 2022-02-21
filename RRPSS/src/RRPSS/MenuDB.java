package RRPSS;

import java.io.*;
import java.util.*;

/**
 * Menu class of the application
 * @author Zhao Chen Xuan Joe
 * @version 1.1
 * @since 2021-11-3
 */
public class MenuDB {
	
    /**
	 * Initialize an arraylist of MenuItem
	 */
	private static ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
	
	/**
	 * Read in menu from text file when creating a Menu class
	 * @throws IOException If an input or output exception occurred.
	 */
	public MenuDB() throws IOException{
		getFromMenuFile();
	}

	/**
	 * Read in MenuItems from text file and store in menu arraylist
	 * @throws IOException If an input or output exception occurred.
	 */
	public void getFromMenuFile() throws IOException {
        menu.clear();
		File f = new File("database" + File.separator + "Menu.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = "";
        Class<?> c = null;
		while ((line = reader.readLine()) != null) {
            try {
                String[] str = line.split(",");
                if (str.length == 1) {
                    c = Class.forName(getClass().getPackageName() + "." + str[0]);
                    reader.readLine();
                    continue;
                }

                Class<?> cls[] = new Class[str.length];
                Object arg[] = new String[str.length];
                for (int i = 0; i < str.length; i++) {
                    cls[i] = String.class;
                    arg[i] = str[i];
                }
                
                MenuItem i = (MenuItem) c.getConstructor(cls).newInstance(arg);
                menu.add(i);
            } catch(Exception e) {
                e.printStackTrace();
            }
		}
		reader.close();
	}
	
	/**
	 * Print all MenuItem that are in the menu arraylist
	 */
	public void printMenu() {
		ArrayList<Class<?>> distinctMenuClass = getAllClasses();
        
        boolean first;
        for (Class<?> menuClass : distinctMenuClass) {
            first = true;
            for (MenuItem dish : menu) {
                if (dish.getClass() == menuClass) {
                    if (first) {
                        dish.displayItemDescription();
                        first = false;
                    }
                    dish.displayItem();
                }
            }
            System.out.println();
        }
	}

	/**
	 * Update the text file with all menu items in the menu array list.
	 * Prints the class name first, followed by the class descriptor then followed by the menu item's details.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void updateMenu() throws IOException{

		File f = new File("database" + File.separator + "Menu.txt");
        PrintWriter writer = new PrintWriter(f);
        ArrayList<Class<?>> distinctMenuClass = getAllClasses();
        int count;
        for (Class<?> menuClass : distinctMenuClass) {
            writer.println(menuClass.getSimpleName() + ",");
            count = 1;
            for (MenuItem dish : menu) {
                if (count == 1) {
                    writer.println(dish.updateMenuDescription()); //Header
                    count++;
                }
                if (dish.getClass() == menuClass) {
                    writer.println(dish.updateMenu());
                }
            }
        }
        writer.close();
	}

	/**
	 * Finds all unique classes that are in the menu arraylist and stores it in an ArrayList.
	 * @return ArrayList of classes that are in the menu.
	 */
	public ArrayList<Class<?>> getAllClasses() {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (MenuItem menuItem : menu) {
            if (!classes.contains(menuItem.getClass()))
                classes.add(menuItem.getClass());
        }

        return classes;
    }
	
	/**
	 * Adds an item into the menu arraylist.
	 * The item must be a subclass of MenuItem so that it can be upcasted.
	 * The item must have a unique name that is different from all MenuItems in the menu arraylist.
	 * @param MenuItem The MenuItem object that is to be added into the menu arraylist.
	 */
	public void addMenuItem(MenuItem item)  {
        if (searchMenu(item.getName()) == null) {
            menu.add(item);
            
            menu.sort(new Comparator<MenuItem>() {
			
                @Override
                public int compare(MenuItem o1, MenuItem o2) {
                    return o1.compareValue().compareTo(o2.compareValue());
                }
            });

            System.out.println("Item added.");
            return;
        }
        
        System.out.println("Item already exists!");
    }
	
	/**
	 * Remove an item from the menu arraylist if found.
	 * @param itemName The name of the MenuItem to be removed.
	 */
	public void removeMenuItem(String itemName) {
        MenuItem searchItem = searchMenu(itemName);
        if (searchItem != null) {
            menu.remove(searchItem);
            
            menu.sort(new Comparator<MenuItem>() {
			
                @Override
                public int compare(MenuItem o1, MenuItem o2) {
                    return o1.compareValue().compareTo(o2.compareValue());
                }
            });
            System.out.println("Item removed.");
            return;
        }

        System.out.println("Item not found!");
    }
	
	/**
	 * Search the menu arraylist for the MenuItem.
	 * @param itemName The name of the MenuItem to be searched.
	 * @return MenuItem that matches the itemName.
	 */
	public MenuItem searchMenu(String itemName) {
        for (MenuItem dish : menu) {
            if (dish.getName().equals(itemName))
                return dish;
        }

        return null;
    }
	
	/**
	 * Search the menu arraylist for the MenuItem.
	 * @param itemName The name of the MenuItem to be searched.
	 * @param price The new price to be changed to.
	 */
	public void updateMenuItem(String itemName, int price) {
		for (MenuItem dish : menu) {
            if (dish.getName().equals(itemName)) {
            	dish.setPrice(price);
            	System.out.println("Price updated successfully.");
            	return;
            }
        }
		System.out.println("Item not found.");
	}
	
	/**
	 * Search the menu arraylist for the MenuItem.
	 * @param itemName The name of the MenuItem to be searched.
	 * @param newDescription The new description to be changed to.
	 */
	public void updateMenuItem(String itemName, String newDescription) {
		for (MenuItem dish : menu) {
            if (dish.getName().equals(itemName)) {
                dish.setDescription(newDescription);
                System.out.println("Description updated successfully.");
                return;
            }
        }
		System.out.println("Item not found.");
	}
	

}