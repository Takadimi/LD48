package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LD26MenuState extends BasicGameState {
	
	private int stateID = -1;
	
	private SpriteSheet menuSpriteSheet = null;
	
	private float scale = 0.001f;
	
	private Sound sound1 = null;
	
	private Image tBlock = null;
	private Image aBlock = null;
	private Image kBlock = null;
	private Image eBlock = null;
	
	private Image iBlock = null;
	
	private Image dBlock = null;
	private Image oBlock = null;
	private Image wBlock = null;
	private Image nBlock = null;
	
	private Image spaceBlock = null;
	
	public LD26MenuState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		sound1 = new Sound("res/sound1.wav");
		
		menuSpriteSheet = new SpriteSheet("res/SpriteSheet.png", 16, 16);
		
		tBlock = menuSpriteSheet.getSprite(0,1);
		aBlock = menuSpriteSheet.getSprite(1,1);
		kBlock = menuSpriteSheet.getSprite(2,1);
		eBlock = menuSpriteSheet.getSprite(3,1);
		
		iBlock = menuSpriteSheet.getSprite(4,1);
		
		dBlock = menuSpriteSheet.getSprite(5,1);
		oBlock = menuSpriteSheet.getSprite(6,1);
		wBlock = menuSpriteSheet.getSprite(7,1);
		nBlock = menuSpriteSheet.getSprite(8,1); 
		
		spaceBlock = menuSpriteSheet.getSprite(9,1);
		
		int scale = 4;
		
		tBlock.getScaledCopy(scale);
		aBlock.getScaledCopy(scale);
		kBlock.getScaledCopy(scale);
		eBlock.getScaledCopy(scale);
		
		iBlock.getScaledCopy(scale);
		
		dBlock.getScaledCopy(scale);
		oBlock.getScaledCopy(scale);
		wBlock.getScaledCopy(scale);
		nBlock.getScaledCopy(scale);
		
		spaceBlock.getScaledCopy(scale);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		int y = 12;
		int yOffset = 48;
		
		g.setAntiAlias(true);
		g.drawString("Hold 'Enter' to start the game!", 40, 220);
		g.drawString("Press 'R' to read the rules!", 460, 220);
		
		tBlock.getScaledCopy(scale).draw(352, y);
		aBlock.getScaledCopy(scale).draw(352, (y + yOffset));
		kBlock.getScaledCopy(scale).draw(352, (y + (yOffset * 2)));
		eBlock.getScaledCopy(scale).draw(352, y + (yOffset * 3));
		
		//spaceBlock.getScaledCopy(scale).draw(368, y + (yOffset * 4));

		iBlock.getScaledCopy(scale).draw(368, y + (yOffset * 5));
		tBlock.getScaledCopy(scale).draw(368, y + (yOffset * 6));
		
		//spaceBlock.getScaledCopy(scale).draw(368, y + (yOffset * 7));
		
		dBlock.getScaledCopy(scale).draw(384, y + (yOffset * 8));
		oBlock.getScaledCopy(scale).draw(384, y + (yOffset * 9));
		wBlock.getScaledCopy(scale).draw(384, y + (yOffset * 10));
		nBlock.getScaledCopy(scale).draw(384, y + (yOffset * 11));
		
//		tBlock.draw(368 - 24, 128);
//		aBlock.draw(368 + 16 - 24, 128 + 8);
//		kBlock.draw(368 + 32 - 24, 128 + 16);
//		eBlock.draw(368 + 48 - 24, 128 + 24);
//		
//		spaceBlock.draw(368 + 64 - 24, 128 + 32);
//		
//		iBlock.draw(368 + 80 - 24, 128 + 40);
//		tBlock.draw(368 + 96 - 24, 128 + 48);
//		
//		spaceBlock.draw(368 + 112 - 24, 128 + 56);
//		
//		dBlock.draw(368 + 128 - 24, 128 + 64);
//		oBlock.draw(368 + 144 - 24, 128 + 72);
//		wBlock.draw(368 + 160 - 24, 128 + 80);
//		nBlock.draw(368 + 176 -24, 128 + 88);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		boolean insideTitleText = false;
		float scaleStep = 0.0015f;
		
		int mouseX = gc.getInput().getMouseX();
		int mouseY = gc.getInput().getMouseY();
		
		if ((mouseX >= 352 && mouseX <= 440) && (mouseY >= 12 && mouseY <= 580)) {
			
			insideTitleText = true;
			
		} else {
			insideTitleText = false;
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_ENTER)) {
			
//			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
//				sbg.enterState(1);
//			}
			
			if (scale >= 0) {
				scale -= delta * scaleStep;
				
				if (scale < 0) {
					sound1.play();
					sbg.enterState(1);
				}
				
			} 
			
		} else {
			
			if (scale <= 3) {
				scale += delta * scaleStep;
			}
			
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_R)) {
			sbg.enterState(2);
		}
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
