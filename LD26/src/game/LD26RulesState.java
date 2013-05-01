package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LD26RulesState extends BasicGameState {
	
	private int stateID = -1;
	
	public LD26RulesState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		
		g.drawString("Press 'Enter' to start the game", 120, 120);
		g.drawString("Press 'M' to return to the title screen", 120, 140);
		
		g.drawString("Rules: ", 120, 220);
		g.drawString("1. Attempt to clear the screen of all color", 160, 240);
		g.drawString("2. Left-Click to decrease the number of a square", 160, 260);
		g.drawString("3. Right-Click to increase the number of a square", 160, 280);
		g.drawString("4. A square may only be decreased if one of the surrounding", 160, 300);
		g.drawString("four squares are within '1' of the square clicked", 190, 320);
		g.drawString("5. The goal is to clear the screen with the highest score", 160, 340);
		g.drawString("6. Each time you INCREASE a block your score is docked 15 points", 160, 360);
		g.drawString("7. Each time you DECREASE a block your score is awarded 20 points", 160, 380);
		g.drawString("8. Each time you clear a tile down to '0' you're awarded 50 points", 160, 400);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(1);
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_M)) {
			sbg.enterState(0);
		}
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
