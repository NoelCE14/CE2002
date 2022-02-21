package RRPSS;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Scanner;

/**
 * Main class of the application.
 * Database classes used: TableDB, StaffDB, SalesRevenueDB, ReservationDB.
 * Other classes used: Menu, Order.
 * 
 * @author LeCong
 * @version 1.0
 * @since 2021-11-01
 */

public class RRPSSApp {
	
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Staff database for storing staff details.
	 * @throws IOException If an input or output exception occurred.
	 */
	static StaffDB staffDB;
	static {
		try {
			staffDB = new StaffDB();
		} catch(IOException io) {
			io.printStackTrace();
		}
	}
	
	/**
	 * Order database for storing the orders of different customers.
	 * @throws IOException If an input or output exception occurred.
	 */
	static OrderDB orderDB;
	static {
		try {
			orderDB = new OrderDB();
		} catch(IOException io) {
			io.printStackTrace();
		}
	}

	/**
	 * Sales database for storing every confirmed order.
	 * @throws IOException If an input or output exception occurred.
	 */
	static SalesRevenueDB salesDB;
	static{
		try{
			salesDB = new SalesRevenueDB();
		}
		catch(IOException io) {
			io.printStackTrace();
		}
	} 
	
	/**
	 * Reservation database for storing all reservations.
	 * @throws IOException If an input or output exception occurred.
	 */
	static ReservationDB reservationDB;
	static{
		try{
			reservationDB = new ReservationDB();
		}
		catch(IOException io) {
			io.printStackTrace();
		}
	}
	
	/**
	 * Menu database for storing all menu items.
	 * @throws IOException If an input or output exception occurred.
	 */
	static MenuDB menu;
	static{
		try{
			menu = new MenuDB();
		}
		catch(IOException io) {
			io.printStackTrace();
		}
	}	
	

	/**
	 * Table database for storing all tables.
	 */
	static TableDB tableDB = new TableDB();
	
	/**
	 * Details of staff currently operating this POS.
	 * Set default as null as Staff ID is required to start POS.
	 */
	static Staff staff = null;
	
	/**
	 * Member database for storing phone numbers of all members.
	 * @throws IOException If an input or output exception occurred.
	 */
	static MemberDB memberDB;
	static{
		try{
			memberDB = new MemberDB();
		}
		catch(IOException io) {
			io.printStackTrace();
		}
	}
	
	
	/**
	 * Main method which combines all the classes and methods.
	 * @param Unused.
	 */
	public static void main(String arg[]) throws IOException{
		
		//Staff ID to start system
		while(staff==null) {
			System.out.printf("Enter Staff ID to start (Use 650 for testing): ");
			while(!sc.hasNextInt())sc.nextInt();
			staff = staffDB.searchStaff(sc.nextInt());
		}
		
		System.out.printf("\nWelcome "+staff.getName());
		
		boolean shouldBreak = false; //simple boolean to allow exit of program only when input
		while(!shouldBreak) {
			mainAppMenuPrint(); //prints main option menu
			while(!sc.hasNextInt()) sc.next(); //Simple input check
			shouldBreak = mainAppSwitch(sc.nextInt());
			
		}
		tableDB.updateToTableFile();
		reservationDB.updateToReservationFile();
		sc.close();
	}
	
	
	//========Start of Main Menu methods========
	/**
	 * Prints the main option menu to allow operator to choose the sub menus.
	 */
	private static void mainAppMenuPrint() { //Prints 1st/main option menu of App
		System.out.println("\n====================================  Bad Street Kitchen POS System  =====================================");
		int optNum = 0;
		System.out.printf("(%d) Menu\n",++optNum);
		System.out.printf("(%d) Order\n",++optNum);
		System.out.printf("(%d) Reservation\n",++optNum);
		System.out.printf("(%d) Check Table Availability\n",++optNum);
		System.out.printf("(%d) Check Sales\n",++optNum);
		System.out.printf("(%d) Staff Option\n",++optNum);
		System.out.printf("(%d) Membership\n",++optNum);
		System.out.printf("(0) Exit\n");
		System.out.println("==========================================================================================================");
		//System.out.println("\n=============================================  Table Details =============================================
		System.out.printf("Input Choice: ");
		
		
	}
	
	/**
	 * Method that takes in the input of operator into switch case to choose next sub menu.
	 * Options of food menu,order menu, reservation menu, table menu, sales menu and staff menu.
	 * Will loop until input 0 is registered
	 * @param choice Input of operator to choose next sub menu.
	 * @return true if input is 0 to break loop and exit program if not return false to continue loop.
	 */
	private static boolean mainAppSwitch(int choice) {
		
		System.out.println();
		
		switch(choice) {
			
		    case 0: //Exit
		    	return true;
		
		    case 1:
		    	try { //Food menu
		    		menuSwitch();
		    	}
		    	catch(IOException io) {io.printStackTrace();}
		    	break;
		    
			case 2:
				try { //Order menu
					orderSwitch();
				}
				catch(IOException io) {io.printStackTrace();}
				break;
				
			case 3: 
				try { //Reservation menu
					resSwitch();
				}
				catch(IOException io) {io.printStackTrace();}
				break;	
			
			case 4:
				try { //Table menu
					tableSwitch();
				}
				catch(IOException io) {io.printStackTrace();}
				break;

			case 5: 
				try { //Sales menu
					salesSwitch();
				}
				catch(IOException io) {io.printStackTrace();}
				break;
			
			case 6: 
				try { //Staff menu
					staffSwitch();
				}
				catch(IOException io) {io.printStackTrace();}
				break;
				
			case 7:
				try { //Member menu
					memberSwitch();
				}
				catch(IOException io) {io.printStackTrace();}
				break;
				
			default: return true;
		}
		return false;
	}
	//========End of main menu methods=========
	
	//(1)========Start of Food menu methods========
	/**
	 * Prints food sub menu and also take in input to choose next option of food menu.
	 * Options of printing different food menu and also editing food menus.
	 * Will loop until input 0 is registered.
	 * @throws IOException If an input or output exception occurred.
	 */
	private static void menuSwitch() throws IOException {
		
		//Loops in Food Menu until option0 is chosen
		boolean shouldBreak = false;
		while(!shouldBreak) {
			
			//Print Food Menu
			menu.printMenu();
			System.out.println("\n===============================================  Food Menu  ==============================================");
			int optNum = 0;
			System.out.printf("(%d) Edit Ala Carte Menu\n",++optNum);
			System.out.printf("(%d) Edit Promo Menu\n",++optNum);
			System.out.printf("(0) Main Menu\n",++optNum);
			System.out.println("==========================================================================================================");
			System.out.printf("Input Choice: ");
			//End of print Food Menu
			
			while(!sc.hasNextInt()) sc.next(); //Simple input check
			switch(sc.nextInt()) {
			
				case 0: //Exit
					shouldBreak = true;
					break;
				
				case 1: //Edit Ala Carte
					boolean shouldBreak2 = false;
					while(!shouldBreak2) {
						//Print Ala Carte menu
						System.out.println("\n(1) Add Item");
						System.out.println("(2) Remove Item");
						System.out.println("(3) Edit Item price");
						System.out.println("(4) Edit Item description");
						System.out.println("(0) Exit");
						System.out.println("==========================================================================================================");
						System.out.printf("Input Choice: ");
						while(!sc.hasNextInt()) sc.next();
						switch(sc.nextInt()) {
							
							case 0: //Exit
								shouldBreak2 = true;
								break;
							
							case 1: //Add
								System.out.printf("Item Name: ");
								sc.nextLine();
								String name = sc.nextLine();
								System.out.printf("Description: ");
								String description = sc.nextLine();
								System.out.printf("Price: ");
								while(!sc.hasNextDouble()) sc.next(); //Simple input check
								double price = sc.nextDouble();
								System.out.printf("Food Type\n(1) Main\n(2) Drink\n(3) Dessert\nInput Choice: ");
								while(!sc.hasNextInt()) sc.next();
								int type = sc.nextInt();
								while(type<1||type>3) { //Simple input check
									System.out.println("Invalid Input");
									while(!sc.hasNextInt()) sc.next();
									type = sc.nextInt();
								}
								FoodType foodtype = FoodType.MAIN; //preinitialise for error ignore
								switch(type) { //Set foodtype enum
									case 1: foodtype = FoodType.MAIN; break;
									case 2: foodtype = FoodType.DRINK; break;
									case 3: foodtype = FoodType.DESSERT; break;
								}
								
								menu.addMenuItem(new AlaCarte(name, description, price, foodtype));
								System.out.println("Added successfully.\n");
								break;
								
							case 2: //Remove
								System.out.printf("Item Name: ");
								String itemName = sc.next();
								if(menu.searchMenu(itemName)!=null) {
									if(menu.searchMenu(itemName).getClass()!=AlaCarte.class) { //Check for correct menu type
										System.out.println("Wrong menu type!");
										break;
									}
								}
								menu.removeMenuItem(itemName);
								break;
								
							case 3: //Edit price
								System.out.printf("Item Name: ");
								sc.nextLine();
								itemName = sc.nextLine();
								if(menu.searchMenu(itemName)!=null) {
									if(menu.searchMenu(itemName).getClass()!=AlaCarte.class) { //Check for correct menu type
										System.out.println("Wrong menu type!");
										break;
									}
									System.out.printf("New price: ");
									while(!sc.hasNextInt()) sc.next(); //Simple input check
									int newPrice = sc.nextInt();
									menu.updateMenuItem(itemName, newPrice);
								}
								else {
									System.out.println("Item not found.");
								}
								break;
								
							case 4: //Edit description
								System.out.printf("Item Name: ");
								sc.nextLine();
								itemName = sc.nextLine();
								if(menu.searchMenu(itemName)!=null) {
									if(menu.searchMenu(itemName).getClass()!=AlaCarte.class) { //Check for correct menu type
										System.out.println("Wrong menu type!");
										break;
									}
									System.out.printf("New description: ");
									String newDescription = sc.nextLine();
									menu.updateMenuItem(itemName, newDescription);
								}
								else {
									System.out.println("Item not found.");
								}
								break;
							
							default:
								System.out.println("Invalid Input.");
								break;
						}
						menu.updateMenu();
					}
					break;
					
				case 2: //Edit Promo
					boolean shouldBreak3 = false;
					while(!shouldBreak3) {
						//Print Promo menu
						System.out.println("\n(1) Add new Promo");
						System.out.println("(2) Remove Promo");
						System.out.println("(3) Add item to Promo");
						System.out.println("(4) Remove item from Promo");
						System.out.println("(5) Edit Promo price");
						System.out.println("(6) Edit Promo description");
						System.out.println("(0) Exit");
						System.out.println("==========================================================================================================");
						System.out.printf("Input Choice: ");
						while(!sc.hasNextInt()) sc.next();
						switch(sc.nextInt()) {
							
							case 0: //Exit
								shouldBreak3 = true;
								break;
							
							case 1: //Add
								System.out.printf("Set Name: ");
								sc.nextLine();
								String name = sc.nextLine();
								System.out.printf("Description: ");
								String description = sc.nextLine();
								System.out.printf("Price: ");
								while(!sc.hasNextDouble()); //Simple input check
								double price = sc.nextDouble();
								System.out.printf("Discount: ");
								while(!sc.hasNextDouble()); //Simple input check
								double discount = sc.nextDouble();
								System.out.printf("Food Type\n(1) Breakfast\n(2) Lunch\n(3) Dinner\nInput Choice:  ");
								while(!sc.hasNextInt()) sc.next(); //Simple input check
								int type = sc.nextInt();
								while(type<1||type>3) { //Simple input check
									System.out.println("Invalid Input");
									while(!sc.hasNextInt()) sc.next();
									type = sc.nextInt();
								}
								PromoType promotype = PromoType.BREAKFAST; //preinitialise for error ignore
								switch(type) { //Set type enum
									case 1: promotype = PromoType.BREAKFAST; break;
									case 2: promotype = PromoType.LUNCH; break;
									case 3: promotype = PromoType.DINNER; break;
								}
								
								menu.addMenuItem(new PromoSet(name, description, price, discount, promotype));
								System.out.println("Added successfully.\n");
								menu.updateMenu();
								break;
								
							case 2: //Remove
								System.out.printf("Item Name: ");
								sc.nextLine();
								String itemName = sc.nextLine();
								if(menu.searchMenu(itemName)!=null) {
									if(menu.searchMenu(itemName).getClass()!=PromoSet.class) { //Check for correct menu type
										System.out.println("Wrong menu type!");
										break;
									}
								}
								menu.removeMenuItem(itemName);
								break;
								
							case 3: //Add item to promo
								System.out.printf("Set name:");
								sc.nextLine();
								String setName = sc.nextLine();
								if(menu.searchMenu(setName)!=null) {
									if(menu.searchMenu(setName).getClass()!=PromoSet.class) { //Check for correct menu type
										System.out.println("Wrong menu type!");
										break;
									}
								}
								else {
									System.out.println("Promo Set does not exist");
									break;
								}
								boolean shouldBreak4 = false;
								while(!shouldBreak4) { //Loop in add item until case 0 input
									System.out.printf("\nItem name (0 to exit): ");
									itemName = sc.nextLine();
									if(itemName.equalsIgnoreCase("0")) //Exit case
										shouldBreak4 = true;
									else if(menu.searchMenu(itemName)!=null) {
										if(((PromoSet) menu.searchMenu(setName)).addItemToSet((AlaCarte)menu.searchMenu(itemName))==1)
											System.out.println("Item added.");
									}
									else {
										System.out.println("Item not found.");
									}
								}
								break;
								
							case 4: //Remove item from promo
								System.out.printf("Set name:");
								sc.nextLine();
								setName = sc.nextLine();
								if(menu.searchMenu(setName)!=null) {
									if(menu.searchMenu(setName).getClass()!=PromoSet.class) { //Check for correct menu type
										System.out.println("Wrong menu type!");
										break;
									}
								}
								else {
									System.out.println("Promo Set does not exist");
									break;
								}
								System.out.printf("Item name:");
								itemName = sc.next();
								if(menu.searchMenu(itemName)==null) 
									System.out.println("Item not found.");
								else
									((PromoSet) menu.searchMenu(setName)).removeItemFromSet((AlaCarte)menu.searchMenu(itemName));
								break;
							
							case 5: //Edit price
								System.out.printf("Item Name: ");
								sc.nextLine();
								itemName = sc.nextLine();
								if(menu.searchMenu(itemName)!=null) {
									if(menu.searchMenu(itemName).getClass()!=PromoSet.class) { //Check for correct menu type
										System.out.println("Wrong menu type!");
										break;
									}
									System.out.printf("New price: ");
									while(!sc.hasNextInt()) sc.next(); //Simple input check
									int newPrice = sc.nextInt();
									menu.updateMenuItem(itemName, newPrice);
								}
								else {
									System.out.println("Item not found.");
								}
								break;
								
							case 6: //Edit description
								System.out.printf("Item Name: ");
								sc.nextLine();
								itemName = sc.nextLine();
								if(menu.searchMenu(itemName)!=null) {
									if(menu.searchMenu(itemName).getClass()!=PromoSet.class) { //Check for correct menu type
										System.out.println("Wrong menu type!");
										break;
									}
									System.out.printf("New description: ");
									String newDescription = sc.nextLine();
									menu.updateMenuItem(itemName, newDescription);
								}
								else {
									System.out.println("Item not found.");
								}
								break;
								
							default:
								System.out.println("Invalid input.");
								break;
								
						}
						menu.updateMenu();
					}
					break;
			}
			
			
		}
		
	}
	//(1)=========End of food menu methods===========
	
	//(2)=========Start of Order methods=============
	/**
	 * Ordering menu to create, view, edit and confirm orders.
	 * Order has to be created before allowing to edit or confirm.
	 * Confirm order when customer is ready to pay to add order into sales database.
	 * Will loop until input 0 is registered.
	 * @throws IOException If an input or output exception occurred.
	 */
	private static void orderSwitch() throws IOException{
		
		
		boolean shouldBreak = false;
		while(!shouldBreak) {
			
			//Print Order Menu
			System.out.println("\n===============================================  Order Menu ==============================================");
			int optNum = 0;
			System.out.printf("(%d) Create Order\n",++optNum);
			System.out.printf("(%d) View Orders\n",++optNum);
			System.out.printf("(%d) Edit Orders\n",++optNum);
			System.out.printf("(%d) Confirm Orders\n",++optNum);
			System.out.printf("(0) Main Menu\n",++optNum);
			System.out.println("==========================================================================================================");
			System.out.printf("Input Choice:");
			//End of print Order Menu
			
			while(!sc.hasNextInt()) sc.next(); //Simple input check
			switch(sc.nextInt()) {
				
				case 0: //Exit
					shouldBreak = true;
					break;
				
				case 1: //Create Order
					int pax=0, orderNumber;
					int tableID = -1;
					boolean shouldBreak2 = false;
					while(tableID == -1 && !shouldBreak2) {
						while(!shouldBreak2) {
							System.out.printf("Input pax: ");
							while(!sc.hasNextInt()) sc.next(); //Simple input check
							pax = sc.nextInt();
							if(pax>0)
								shouldBreak2 = true;
							}
						tableID = tableDB.findAvailableTable(pax, LocalDateTime.now());
						if (tableID == -1) { //If no table found
							System.out.println("No available table!\n");
						}
					}
					if(tableID==-1) //Break out of loop if no table found
						break;
					tableDB.setToOccupied(tableID);
					boolean member = false; //Set default member as false
					System.out.printf("Member? (Y/N): ");
					char confirmation = sc.next().charAt(0);
					if(confirmation== 'Y'||confirmation=='y') { //Confirmation check
						while(member == false) {
							System.out.printf("Input phone number (q to skip): ");
							String memPhone = sc.next();
							if(memPhone.equalsIgnoreCase("q")) //Exit case
								break;
							member = memberDB.searchMember(memPhone);
						}
						
					}
					orderNumber = orderDB.createOrder(staff.getName(), member, pax, tableDB.searchTable(tableID).getTableNumber());
					addOrder(orderNumber, tableDB.searchTable(tableID).getTableNumber());
					break;
					
				
				case 2: //View order
					orderDB.viewOrder();
					System.out.printf("Enter Order Number to view: ");
					orderNumber = sc.nextInt();
					if (orderDB.searchOrder(orderNumber) != null) {
						orderDB.searchOrder(orderNumber).printOrderList();
					}
					else {System.out.println("Invalid order number!"); break;}
					break;
				
				case 3: //Edit order
					orderDB.viewOrder();
					System.out.printf("Select Order to Edit: ");
					orderNumber = sc.nextInt();
					if (orderDB.searchOrder(orderNumber) != null) {
						orderDB.searchOrder(orderNumber).printOrderList();
					}
					else {System.out.println("Invalid order number!"); break;}
					boolean shouldBreak3 = false;
					while(!shouldBreak3) {
						//Start of edit order print
						System.out.println("\n==============================================  Edit Order  ==============================================");
						System.out.printf("(1) Add Item\n");
						System.out.printf("(2) Remove Item\n");
						System.out.printf("(0) Exit\n");
						System.out.println("==========================================================================================================");
						System.out.printf("Input Choice:");
						//End of edit order print
						
						while(!sc.hasNextInt()) sc.next(); //Simple input check
						switch(sc.nextInt()) {
						
							case 0: //Exit
								shouldBreak3 = true;
								break;
						
							case 1: //Add
								addOrder(orderNumber,orderDB.searchOrder(orderNumber).getTableNum());
								break;
								
							case 2: //Remove
								orderDB.searchOrder(orderNumber).printOrderList();
								System.out.printf("Input item to remove: ");
								sc.nextLine();
								String itemName = sc.nextLine();
								orderDB.searchOrder(orderNumber).removeOrderItem(menu.searchMenu(itemName));
								break;
						}
					}
					break;
				
				case 4: //Confirm order
					orderDB.viewOrder();
					System.out.printf("Select Order to Confirm: ");
					orderNumber = sc.nextInt();
					if (orderDB.searchOrder(orderNumber) != null) {
						orderDB.searchOrder(orderNumber).printOrderDetails();
						System.out.printf("Confirm Order (Y/N): ");
						confirmation = sc.next().charAt(0);
						if (confirmation == 'Y'|| confirmation=='y') { //Order confirmed
							orderDB.confirmOrder(orderNumber);
							tableDB.releaseTable(orderDB.searchOrder(orderNumber).getTableNum());
							orderDB.removeOrder(orderNumber);
						} else {
							System.out.println("Order was not confirmed.");
						}
					} else {System.out.println("Invalid order number!"); break;}
					break;

			}
			//update databases
			orderDB.updateToOrderFile();
			salesDB.updateToSalesFile();
			tableDB.updateToTableFile();
		}
	}
	
	/**
	 * Method for adding order as order can be added from different sub menu, order menu or confirm reservation menu.
	 * Allow adding of order from promo menu or A la Carte Menu.
	 * Will loop until input 0 is registered.
	 * @param orderNumber Current order number of customer.
	 * @param tableNum Table number the customer is assigned to.
	 * @throws IOException If an input or output exception occurred.
	 */
	private static void addOrder(int orderNumber, int tableNum) throws IOException{
		boolean shouldBreak2 = false;
		sc.nextLine();
		while(!shouldBreak2) {
			System.out.println("\nTable "+ tableNum);
			menu.printMenu();
			System.out.printf("\n(0) Exit\n");
			System.out.println("==========================================================================================================");
			System.out.printf("Input Choice: ");
			String promoName = sc.nextLine(); 
			if(promoName.equalsIgnoreCase("0")) { //Exit case
				shouldBreak2 = true;
				break;
			}
			MenuItem s = menu.searchMenu(promoName);
			if(s==null) {System.out.println("Item not found");}
			else {
				orderDB.searchOrder(orderNumber).addOrderItem(s);
				System.out.println("\nAdded successfully.");
			}
		}
	}
	
	//(2)=========End of order methods===============
	
	//(3)=========Start of Reservation methods=======
	/**
	 * Prints reservation menu and takes in input to the sub menus.
	 * Allows adding, searching to confirm reservation when customer arrives for reservation, removing reservation.
	 * Will loop until input 0 is registered.
	 * @throws IOException If an input or output exception occurred.
	 */
	private static void resSwitch() throws IOException {
	
		//Loops in Reservation Menu until option0 is chosen
		boolean shouldBreak = false;
		while(!shouldBreak) {
			
			//Print Reservation Menu
			System.out.println("\n===========================================  Reservation Menu  ===========================================");
			int optNum = 0;
			System.out.printf("(%d) Check all Reservations\n",++optNum);
			System.out.printf("(%d) Add Reservation\n",++optNum);
			System.out.printf("(%d) Search Reservation\n",++optNum);
			System.out.printf("(%d) Remove Reservation\n",++optNum);
			System.out.printf("(0) Main Menu\n",++optNum);
			System.out.println("==========================================================================================================");
			System.out.printf("Input Choice: ");
			//End of print Reservation Menu
			
			
			while(!sc.hasNextInt()) sc.next(); //Simple input check
				switch(sc.nextInt()) {
				
					case 0: //Exit
						shouldBreak = true;
						break;
					
					case 1: //Check
						reservationDB.printReservationList();
						tableDB.checkExpiry(); //Remove any expired reservations in tableDB
						break;
					
					case 2: //Add
						System.out.println("\n===========================================  Add Reservation  ==========================================");
						System.out.printf("Reservation Date and Time (HHMMDDMMYY): ");
						double timeOfReservation = sc.nextDouble();
						LocalDateTime dateTime = LocalDateTime.of((int)(timeOfReservation%100+2000), //Year
																Month.of((int)(timeOfReservation/100%100)), //Month
																(int)(timeOfReservation/10000%100), //day
																(int)(timeOfReservation/100000000%100), //hour
																(int)(timeOfReservation/1000000%100)); //minute
						System.out.printf("Input pax: ");
						while(!sc.hasNextInt()) sc.next(); //Simple input check
						int pax = sc.nextInt();
						System.out.println();
						int tableNum = tableDB.findAvailableTable(pax, dateTime);
						if(tableNum!=-1) { 
							System.out.println("Table Available!");
							System.out.printf("Customer Name: ");
							String customerName = sc.next();
							System.out.printf("Contact Number: ");
							String phoneNumber = sc.next();
							reservationDB.addReservation(dateTime,
														customerName,
														phoneNumber, 
														pax,
														staff,
														tableDB.searchTable(tableNum).getTableSize(),
														tableDB.searchTable(tableNum).getTableNumber());
							tableDB.addReservation(tableNum, dateTime);
							break;
							
						}
						
						System.out.printf("No available table for %d pax\n", pax);
						break;
						
					case 3: //Search
						System.out.printf("Customer name:");
						String name = sc.next();
						Reservation res = reservationDB.searchReservation(name);
						if(res!=null) {
							System.out.printf("Confirm Reservation? (Y/N): ");
							char confirmation = sc.next().charAt(0);
							if (confirmation == 'Y') {
								boolean member = memberDB.searchMember(res.getphoneNumber()); //<=====Auto check membership when confirming reservation
								tableDB.removeReservation(res.getTableNumber(), res.getTimeofReservation());
								reservationDB.confirmReservation(name);
								addOrder(orderDB.createOrder(res.getStaff().getName(), //staffID  <======Auto add order when confirming reservation
															member,  //member
															res.getpax(), //pax
															res.getTableNumber()),
															res.getTableNumber());
							}
						}
						else
							System.out.println("Reservation not found!");
						break;
					
					case 4://Remove
						System.out.printf("Customer name:");
						String name2 = sc.next();
						LocalDateTime time = LocalDateTime.now();
						tableNum = 0;
						if(reservationDB.searchReservation(name2)!=null) { //get reservation details for removing reservation in table
							tableNum = reservationDB.searchReservation(name2).getTableNumber();
							time = reservationDB.searchReservation(name2).getTimeofReservation();
						}
						int check = reservationDB.removeReservation(name2,sc);
						if(check!=-1) { //if reservation removed
							tableDB.removeReservation(tableNum, time);
						}
						
						break;
				}
				//update databases
				reservationDB.updateToReservationFile();
				tableDB.updateToTableFile();
		}
	}
	//(3)=========End of Reservation methods=========
	
	//(4)=========Start of Table methods=============
	/**
	 * Prints table sub menu and takes in input to different sub menus.
	 * Allows check status of all tables.
	 * Allows finding available table with time,date and pax.
	 * Will loop until input 0 is registered.
	 * @throws IOException when database fails to read.
	 */
	private static void tableSwitch() throws IOException{
		
		//Loops in Table Menu until option0 is chosen
		boolean shouldBreak = false;
		while(!shouldBreak) {
			
			//Print table Menu
			System.out.println("\n==============================================  Table Menu  ==============================================");
			int optNum = 0;
			System.out.printf("(%d) Check status of all tables\n",++optNum);
			System.out.printf("(%d) Check available table according to pax and time\n",++optNum);
			System.out.printf("(0) Main Menu\n",++optNum);
			System.out.println("==========================================================================================================");
			System.out.printf("Input Choice: ");
			//End of print table Menu
			
			while(!sc.hasNextInt()) sc.next(); //Simple input check
			switch(sc.nextInt()) {
				
				case 0: //Exit
					shouldBreak = true;
					break;
				
				case 1: //Check Status
					tableDB.printTableList();
					break;
					
				case 2: //Check available table
					System.out.printf("Input Date and Time to check (HHMMDDMMYY / 0 to check now): ");
					long timeOfReservation = sc.nextLong();
					LocalDateTime dateTime = LocalDateTime.now();
					if(timeOfReservation!=0) {
						dateTime = LocalDateTime.of((int)(timeOfReservation%100+2000), //Year
													Month.of((int)(timeOfReservation/100%100)), //Month
													(int)(timeOfReservation/10000%100), //day
													(int)(timeOfReservation/100000000%100), //hour
													(int)(timeOfReservation/1000000%100)); //minute
					}
					System.out.printf("Input pax:");
					while(!sc.hasNextInt()) sc.next(); //Simple input check
					int pax = sc.nextInt();
					if(tableDB.findAvailableTable(pax, dateTime)!=-1)
						System.out.println("Table(s) are available!");
					else
						System.out.println("No tables available.");
					break;
			
			}
		}
	}
	
	//(4)=========End of Table methods===============
	
	//(5)=========Start of Sales methods=============
	/**
	 * Prints sales menu and takes in input to sub menus.
	 * ALlows printing of today's revenue, or revenue of given day or month.
	 * Will loop until input 0 is registered.
	 * @throws IOException If an input or output exception occurred.
	 */
	private static void salesSwitch() throws IOException{
		
		//Loops in Sales Menu until option0 is chosen
		boolean shouldBreak = false;
		while(!shouldBreak) {
			
			//Print Sales Menu
			System.out.println("\n==============================================  Order Menu  ==============================================");
			int optNum = 0;
			System.out.printf("(%d) Print Today's Revenue\n",++optNum);
			System.out.printf("(%d) Print Day's Revenue\n", ++optNum);
			System.out.printf("(%d) Print Month's Revenue\n",++optNum);
			System.out.printf("(0) Main Menu\n",++optNum);
			System.out.println("==========================================================================================================");
			System.out.printf("Please select your option:");
			//End of Sales Menu
			
			while(!sc.hasNextInt()) sc.next(); //Simple input check
			switch(sc.nextInt()) {
				
				case 0: //Exit
					shouldBreak = true;
					break;

				case 1: //Today's revenue
					salesDB.printRevenue(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue());
					break;

				case 2: //Day's Revenue
					System.out.printf("Day of revenue to print (DDMM): ");
					while(!sc.hasNextInt()) sc.next();
					int input = sc.nextInt();
					salesDB.printRevenue(input/100, input%100);
					break;
				
				case 3: //Month's revenue
					System.out.printf("Month of revenue to print (MM): ");
					while(!sc.hasNextInt()) sc.next();
					input = sc.nextInt();
					salesDB.printRevenue(input);
					break;
				
			}
		}
	}
	//(5)=========End of Sales methods===============
	
	//(6)=========Start of Staff methods=============
	/**
	 * Prints staff menu and take in input to sub menus.
	 * Allows adding, removing and updating of staff details.
	 * Will loop until option 0 is registered.
	 * @throws IOException If an input or output exception occurred.
	 */
	private static void staffSwitch() throws IOException{
		//Loops in Staff Menu until option0 is chosen
		boolean shouldBreak = false;
		while(!shouldBreak) {
			
			//Print Staff Menu
			System.out.println("\n==============================================  Staff Menu  ==============================================");
			int optNum = 0;
			System.out.printf("(%d) Add Staff\n",++optNum);
			System.out.printf("(%d) Remove Staff\n", ++optNum);
			System.out.printf("(%d) Update Staff details\n",++optNum);
			System.out.printf("(0) Main Menu\n",++optNum);
			System.out.println("==========================================================================================================");
			System.out.printf("Please select your option: ");
			//End of Staff Menu
			
			while(!sc.hasNextInt()) sc.next(); //Simple input check
			switch(sc.nextInt()) {
				
				case 0: 
					shouldBreak = true;
					break;

				case 1: //Add
					boolean shouldBreak2 = false;
					int staffID = 0;
					while(!shouldBreak2) {
						System.out.printf("Input new Staff ID: ");
						while(!sc.hasNextInt()) sc.next(); //simple input check
						staffID = sc.nextInt();
						if(staffDB.searchStaff(staffID)==null) shouldBreak2 = true;
						else System.out.println("Staff ID already exist.");
					}
					System.out.printf("Input Name: ");
					sc.nextLine();
					String name = sc.nextLine();
					shouldBreak2 = false;
					Gender gender2 = Gender.FEMALE; //Initialise
					while(!shouldBreak2) { //Loop until correct gender input
						System.out.printf("Input gender (M/F): " );
						char gender = sc.next().charAt(0);
						if(gender=='M') {
							gender2 = Gender.MALE;
							shouldBreak2 = true;
						}
						else if(gender=='F') {
							gender2 = Gender.FEMALE;
							shouldBreak2 = true;
						}
					}
					System.out.printf("Job Title: ");
					sc.nextLine();
					String jobTitle = sc.nextLine();
					staffDB.addStaff(name, gender2, staffID, jobTitle);
					break;

				case 2: //Remove
					System.out.printf("Input Staff ID to remove: ");
					while(!sc.hasNextInt()) sc.next();
					staffDB.removeStaff(sc.nextInt());
					break;
				
				case 3: //Update
					System.out.printf("Input Staff ID to update: ");
					while(!sc.hasNextInt()) sc.next();
					staffDB.updateStaff(sc.nextInt(), sc);
					break;
				
			}
			//update database
			staffDB.updateToStaffFile();
		}
	}	
	//(6)=========End of Staff methods===============
	
	//(7)=========Start of Member methods============
	/**
	 * Prints member menu and take in input to sub menus.
	 * Allows adding, removing and checking of member details.
	 * Will loop until option 0 is registered. 
	 * @throws IOException If an input or output exception occurred.
	 */
	private static void memberSwitch() throws IOException {
		//Loops in Member Menu until option0 is chosen
		boolean shouldBreak = false;
		while(!shouldBreak) {
			
			//Print Member Menu
			System.out.println("\n=============================================  Members Menu  =============================================");
			int optNum = 0;
			System.out.printf("(%d) Check membership\n",++optNum);
			System.out.printf("(%d) Add member\n", ++optNum);
			System.out.printf("(%d) Remove member\n",++optNum);
			System.out.printf("(0) Main Menu\n",++optNum);
			System.out.println("==========================================================================================================");
			System.out.printf("Please select your option:");
			//End of Member Menu
			
			while(!sc.hasNextInt()) sc.next(); //Simple input check
			switch(sc.nextInt()) {
				
				case 0: //Exit
					shouldBreak = true;
					break;

				case 1: //Check membership
					System.out.printf("Input member's phone number: ");
					if(memberDB.searchMember(sc.next())!=false)
						System.out.println("Customer has valid membership.");
					else
						System.out.println("Invalid membership.");
					break;

				case 2: //Add member
					System.out.printf("Input customer's phone number: ");
					memberDB.addMember(sc.next());
					break;
				
				case 3: //Remove member
					System.out.printf("Input member's phone number: ");
					memberDB.removeMember(sc.next());
					break;
				
			}
			//update database
			memberDB.updateToMemberFile();
		}
	}
	//(7)=========End of Member methods==============
}