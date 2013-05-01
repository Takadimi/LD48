package game;


public class Block {
	
	private int entityID = 0;
	
	public Block(int entityID) {
		
		this.entityID = entityID;
		
	}
	
	public void blockClicked(int mouseX, int mouseY) {
		
		
		
	}
	
	public boolean canDecrease(int entityID1, int entityID2, int entityID3, int entityID4) {
		
		int result1 = entityID - entityID1;
		System.out.println(result1);
		int result2 = entityID - entityID2;
		System.out.println(Math.abs(result2));
		int result3 = entityID - entityID3;
		System.out.println(Math.abs(result3));
		int result4 = entityID - entityID4;
		System.out.println(Math.abs(result4));
		
		if ((entityID - entityID1 == 1 || entityID1 - entityID == 1) || (entityID - entityID2 == 1 || entityID2 - entityID == 1) || (entityID - entityID3 == 1 || entityID3 - entityID == 1) || (entityID - entityID4 == 1 || entityID4 - entityID == 1)) {
			return true;
		} else if (entityID - entityID1 == 0 || entityID - entityID2 == 0 || entityID - entityID3 == 0 || entityID - entityID4 == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean canDecrease(int entityID1, int entityID2, int entityID3) {
		
		if ((entityID - entityID1 == 1 || entityID1 - entityID == 1) || (entityID - entityID2 == 1 || entityID2 - entityID == 1) || (entityID - entityID3 == 1 || entityID3 - entityID == 1)) {
			return true;
		} else if (entityID - entityID1 == 0 || entityID - entityID2 == 0 || entityID - entityID3 == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean canDecrease(int entityID1, int entityID2) {
		
		if ((entityID - entityID1 == 1 || entityID1 - entityID == 1) || (entityID - entityID2 == 1 || entityID2 - entityID == 1)) {
			return true;
		} else if (entityID - entityID1 == 0 || entityID - entityID2 == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
