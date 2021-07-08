package game;

import fixtures.Room;

public class RoomManager {
	public Room startingRoom;
	public Room[] rooms;
	public void init() {
		Room family = new Room("Family Room", "Comfortable seating area to relax", "Large area to spend time with your family with.\n"
				+ "Includes a T.V., various couches, and a coffee table.\n"
				+ "has access to the garage, the downstairs bedroom, and\n"
				+ "is conncected to stairs leading to the upstairs loft");
		Room garage = new Room("Garage", "2 car garage", "The garage is a large two car space holding both your Ferrari,\n"
				+ "and two smart cars (because two can fit in one spot),\n"
				+ "The garage has access to the driveway when the garage door is open\n"
				+ "as wellas the kitchen and family room.");
		Room kitchen = new Room("Kitchen", "It's a kitchen", "Large kitchen with all the appliances you can think of.\n"
				+ "Like a refigerator, stove, oven, microwave and sink.\n"
				+ "Kitchen has access to the pantry to the north\n"
				+ "and the garage to the east.");
		Room pantry = new Room("Pantry", "Food loaded Pantry", "Your walk in pantry loaded with a variety of foods both healthy and unhealthy\n "
				+ "Things from cerial and soup to the never ending stacks of oreos and bags of chips\n"
				+ "From the pantry you have access to the kitchen going south\n"
				+ "As well as the Dining room to the north.");
		Room diningRoom = new Room("Dining Room", "Dinning Room", "The Dining Room area is a wonderful chandelure lit room with a 6 seat class table\n"
				+ "The Dining Room has access to the backyard to the west\n"
				+ "As well as the pantry to the south");
		Room backyard = new Room("Backyard", "Backyard with pool", "Spacous backyard with a pool/hottub.\n"
				+ "The pool has a wet deck and depths ranging from 4 feet to 6 feet deep.\n"
				+ "The backyard only has access to the dining room to the east. ");
		Room bedroom1 = new Room("North Bedroom", "North Bedroom", "North Bedroom isn't too large but has enough room for all your essentials:\n"
				+ "Bed, nightstand, dresser, and T.V. As well as a closet and a connecting bathroom to the east.\n"
				+ "The North Bedroom also has access to the family room to the south. ");
		Room driveway = new Room("Driveway", "Stone path Driveway", "A nice stone driveway that really brings the exterior of the house together\n"
				+ "The driveway can only be accessed if the garage has been opened\n"
				+ "You can only go between the driveway and the garage because you are not allowed to leave the house. Ever ");
		Room bathroom1 = new Room("Downstairs Bathroom", "Downstairs Bathroom", "The downstairs bathroom is a personal bathroom connected to the North Bedroom.\n"
				+ "Its a small space with a single sink, mirror, medicine cabinet, toilet, and walk in shower.\n"
				+ "The Bathroom is connected to the North Bedroom to the west. ");
		
		Room loft = new Room("Upstairs Loft", "Large loft upstairs", "The upstairs loft is equipped with an L couch, and home entertainment system.\n"
				+ "As well as a large space between rooms for the children to play.\n "
				+ "The loft has access to two bedrroms to the north and the east.\n"
				+ "As well as the laundry room to the south. ");
		Room bedroom2 = new Room("North upstairs bedroom", "North upstairs bedroom", "Much like the downstairs bedroom it is a small space but has enough room for essentials:\n"
				+ "Bed, dresser, T.V., and nightstand. The room comes with a closet is connected to a bathroom to the east. \n"
				+ "Unlike the downstairs bedroom this bathroom is also shared with the east bedroom.\n"
				+ "The north upstairs bedroom also has the upstairs loft to the south. ");
		Room bedroom3 = new Room("East upstairs bedroom", "East upstairs bedroom", "Much like the downstairs bedroom it is a small space but has enough room for essentials:\n"
				+ "Bed, dresser, T.V., and nightstand. The room comes with a closet is connected to a bathroom to the north. \n"
				+ "Unlike the downstairs bedroom this bathroom is also shared with the north bedroom.\n"
				+ "The east upstairs bedroom also has the upstairs loft to the west. ");
		Room bathroom2 = new Room("Upstairs Bathroom", "UpStairs Bathroom", "The Upstairs bathroom is a personal bathroom connected to the North Upstairs Bedroom to the west.\n"
				+ "As well as the East Upstairs Bedroom to the south.\n"
				+ "Its a small space with a single sink, mirror, medicine cabinet, toilet, and walk in shower.\n");
		Room masterBedroom = new Room("Master Bedroom", "Master Bedroom", "Largest bedroom in the house.\n"
				+ "It has its own personal balcony that over looks the backyard to the west.\n"
				+ "And it has a master bathroom to the south.");
		Room masterBathroom = new Room("Master Bathroom", "Master Bathroom", "The Master Bathroom is the largest bathroom in the house.\n"
				+ "It has jack and jill sinks, Two walk in closets, a bathtub, and walking in shower (separate from bathtub)\n"
				+ "The Master Bathroom leads to the Master Bedroom to the north\n"
				+ "and the laundry room to the east.");
		Room balcony = new Room("Balcony", "Balcony for the Master Bedroom", "The Balcony has a nice over look to the backyard and rest of the neighborhood.\n"
				+ "It has an outdoor couch for sitting around at night.\n"
				+ "The only exit to the balcony is through the master bedroom.\n"
				+ "However, there may or may not be a way to get to the backyard by jumping into the pool.");
		Room laundry = new Room("Laundry Room", "Calm place to wash clothes", "The laundry room is equipt with top of the line washer and dryer.\n"
				+ "It is connected to the master bathroom to the west\n"
				+ "As well as the loft to the north.");
		
		Room[] setExits = family.getExits();
		setExits[0] = bedroom1;
		setExits[1] = loft;
		setExits[2] = garage;
		setExits[3] = null;
		
		setExits = garage.getExits();
		setExits[0] = family;
		setExits[1] = null;
		setExits[2] = null;
		setExits[3] = kitchen;
		
		setExits = kitchen.getExits();
		setExits[0] = pantry;
		setExits[1] = garage;
		setExits[2] = null;
		setExits[3] = null;
		
		setExits = pantry.getExits();
		setExits[0] = diningRoom;
		setExits[1] = null;
		setExits[2] = kitchen;
		setExits[3] = null;
		
		setExits = diningRoom.getExits();
		setExits[0] = null;
		setExits[1] = null;
		setExits[2] = pantry;
		setExits[3] = backyard;
		
		setExits = backyard.getExits();
		setExits[0] = null;
		setExits[1] = diningRoom;
		setExits[2] = null;
		setExits[3] = null;
		
		setExits = bedroom1.getExits();
		setExits[0] = null;
		setExits[1] = bathroom1;
		setExits[2] = family;
		setExits[3] = null;
		
		setExits = driveway.getExits();
		setExits[0] = garage;
		setExits[1] = null;
		setExits[2] = null;
		setExits[3] = null;
		
		setExits = bathroom1.getExits();
		setExits[0] = null;
		setExits[1] = null;
		setExits[2] = null;
		setExits[3] = bedroom1;
		
		setExits = loft.getExits();
		setExits[0] = bedroom2;
		setExits[1] = bedroom3;
		setExits[2] = laundry;
		setExits[3] = null;
		
		setExits = bedroom2.getExits();
		setExits[0] = null;
		setExits[1] = bathroom2;
		setExits[2] = loft;
		setExits[3] = null;
		
		setExits = bedroom3.getExits();
		setExits[0] = bathroom2;
		setExits[1] = null;
		setExits[2] = null;
		setExits[3] = loft;
		
		setExits = bathroom2.getExits();
		setExits[0] = null;
		setExits[1] = null;
		setExits[2] = bedroom3;
		setExits[3] = bedroom2;
		
		setExits = masterBedroom.getExits();
		setExits[0] = null;
		setExits[1] = null;
		setExits[2] = masterBathroom;
		setExits[3] = balcony;
		
		setExits = masterBathroom.getExits();
		setExits[0] = masterBedroom;
		setExits[1] = laundry;
		setExits[2] = null;
		setExits[3] = null;
		
		setExits = balcony.getExits();
		setExits[0] = null;
		setExits[1] = masterBedroom;
		setExits[2] = null;
		setExits[3] = backyard;
		
		setExits = laundry.getExits();
		setExits[0] = loft;
		setExits[1] = null;
		setExits[2] = null;
		setExits[3] = masterBathroom;
		
		rooms = new Room[17];
		rooms[0] = family;
		rooms[1] = garage;
		rooms[2] = kitchen;
		rooms[3] = pantry;
		rooms[4] = diningRoom;
		rooms[5] = backyard;
		rooms[6] = bedroom1;
		rooms[7] = driveway;
		rooms[8] = bathroom1;
		rooms[9] = loft;
		rooms[10] = bedroom2;
		rooms[11] = bedroom3;
		rooms[12] = bathroom2;
		rooms[13] = masterBedroom;
		rooms[14] = masterBathroom;
		rooms[15] = balcony;
		rooms[16] = laundry;
		
		startingRoom = family;
		
				
	}
}
