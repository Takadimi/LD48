package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class LD26Game extends StateBasedGame {
	
	private static final String gameTitle = "Take it Down";
	
	public static final int RULESSTATE = 2;
	public static final int GAMEPLAYSTATE = 1;
	public static final int MENUSTATE = 0;
	
	public LD26Game(String title) {
		super(title);
	}


	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.addState(new LD26MenuState(MENUSTATE));
		this.addState(new LD26GameplayState(GAMEPLAYSTATE, 5));
		this.addState(new LD26RulesState(RULESSTATE));
		
	}	
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer appgc = new AppGameContainer(new LD26Game(gameTitle));
		
		appgc.setDisplayMode(800, 600, false);
		appgc.setShowFPS(false);
		appgc.start();
		
	}
	
}
