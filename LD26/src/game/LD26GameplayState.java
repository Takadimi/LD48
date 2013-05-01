package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class LD26GameplayState extends BasicGameState {
	
	private int stateID = -1;
	private int difficulty = 10;
	
	private Rectangle[][] tiles = null;
	private int rectHeight = 32;
	private int rectWidth = 32;
	
	private SpriteSheet spriteSheet = null;
	
	private Image selectionSprite = null;
	private int selectionX = 0;
	private int selectionY = 24;
	
	private Sound clickSound = null;
	private Sound rightClickSound = null;
	
	private TiledMap tileMap = null;
	
	private boolean[][] tilesCleared = null;
	
	private int highScore;
	private String highScoreString = "0";
	
	private String complete = "";
	private String replay = "";
	
	public LD26GameplayState(int stateID, int difficulty) throws SlickException {
		this.stateID = stateID;
		this.difficulty = difficulty;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		tileMap = new TiledMap("res/LD26TileMap2.tmx");
		
		spriteSheet = new SpriteSheet("res/SpriteSheet.png", 32, 32);
		
		selectionSprite = spriteSheet.getSprite(3, 2);
		
		tiles = new Rectangle[tileMap.getWidth()][tileMap.getHeight()];
		tilesCleared = new boolean[tileMap.getWidth()][tileMap.getHeight()];
		
		generateNewMap();
		
		clickSound = new Sound("res/sound2.wav");
		rightClickSound = new Sound("res/sound3.wav");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		tileMap.render(0, 24);
		selectionSprite.draw(selectionX, selectionY);
		g.drawString("HighScore: ", 540, 6);
		g.drawString(renderHighScore(highScore), 640, 6);
		g.drawString(complete, 175, 6);
		g.drawString(replay, 325, 6);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		int mouseX = gc.getInput().getMouseX();
		int mouseY = gc.getInput().getMouseY();
		
		if (gc.getInput().isKeyPressed(Input.KEY_M)) {
			sbg.enterState(0);
		}
		
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			for (int i = 0; i <tileMap.getWidth(); i++) {
				for (int j = 0; j < tileMap.getHeight(); j++) {
					
					int baseTileID = tileMap.getTileId(i, j, 0);
					
					int northTile;
					int southTile;
					int westTile;
					int eastTile;
					
					Block currentTile = new Block(baseTileID);
					
					if (((mouseX >= tiles[i][j].getX() && mouseX <= tiles[i][j].getMaxX()) && (mouseY >= tiles[i][j].getY() && mouseY <= tiles[i][j].getMaxY())) && baseTileID < 19) {
						
						selectionX = (int) tiles[i][j].getX();
						selectionY = (int) tiles[i][j].getY();
						
						if (i == 0 && j == 0) {
							eastTile = tileMap.getTileId((i + 1), j, 0);
							southTile = tileMap.getTileId(i, (j + 1), 0);
							
							if (currentTile.canDecrease(eastTile, southTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
							
						} else if (i == 24 && j == 0) {
							westTile = tileMap.getTileId((i - 1), j, 0);
							southTile = tileMap.getTileId(i, (j + 1), 0);
							
							if (currentTile.canDecrease(westTile, southTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
						} else if (i == 24 && j == 17) {
							westTile = tileMap.getTileId((i - 1), j, 0);
							northTile = tileMap.getTileId(i, (j - 1), 0);
							
							if (currentTile.canDecrease(westTile, northTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
						} else if (i == 0 && j == 17) {
							eastTile = tileMap.getTileId((i + 1), j, 0);
							northTile = tileMap.getTileId(i, (j - 1), 0);
							
							if (currentTile.canDecrease(eastTile, northTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
						} else if ((i >= 1 && i <= 23) && j == 0) {
							eastTile = tileMap.getTileId((i + 1), j, 0);
							westTile = tileMap.getTileId((i - 1), j, 0);
							southTile = tileMap.getTileId(i, (j + 1), 0);
							
							if (currentTile.canDecrease(eastTile, southTile, westTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
						} else if (i == 24 && (j >= 1 && j <= 16)) {
							northTile = tileMap.getTileId(i, (j - 1), 0);
							westTile = tileMap.getTileId((i - 1), j, 0);
							southTile = tileMap.getTileId(i, (j + 1), 0);
							
							if (currentTile.canDecrease(northTile, southTile, westTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
						} else if ((i >= 1 && i <= 23) && j == 17) {
							northTile = tileMap.getTileId(i, (j - 1), 0);
							westTile = tileMap.getTileId((i - 1), j, 0);
							eastTile = tileMap.getTileId((i + 1), j, 0);
							
							if (currentTile.canDecrease(northTile, eastTile, westTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
						} else if (i == 0 && (j >= 1 && j <= 16)) {
							northTile = tileMap.getTileId(i, (j - 1), 0);
							southTile = tileMap.getTileId(i, (j + 1), 0);
							eastTile = tileMap.getTileId((i + 1), j, 0);
							
							if (currentTile.canDecrease(northTile, eastTile, southTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
						}
						
						else {
							
							northTile = tileMap.getTileId(i, (j - 1), 0);
							southTile = tileMap.getTileId(i, (j + 1), 0);
							westTile = tileMap.getTileId((i - 1), j, 0);
							eastTile = tileMap.getTileId((i + 1), j, 0);
							
							if (currentTile.canDecrease(northTile, southTile, westTile, eastTile)) {
								highScore += 15;
								tileMap.setTileId(i, j, 0, ++baseTileID);
								clickSound.play();
								
								if (baseTileID == 19 && !tilesCleared[i][j]) {
									highScore += 35;
									tilesCleared[i][j] = true;
								}
								
							} else {
								rightClickSound.play();
							}
						}
					}
				}
			}
		}
		
		if (gc.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			for (int i = 0; i < tileMap.getWidth(); i++) {
				for (int j = 0; j < tileMap.getHeight(); j++) {
					
					int baseTileID = tileMap.getTileId(i, j, 0);
					
					if ((mouseX >= tiles[i][j].getX() && mouseX <= tiles[i][j].getMaxX()) && (mouseY >= tiles[i][j].getY() && mouseY <= tiles[i][j].getMaxY()) && baseTileID > 9) {
						highScore -= 20;
						tileMap.setTileId(i, j, 0, --baseTileID);
						rightClickSound.play();
					}
				}
			}
		}
		
		if (isGameOver()) {
			complete = "Complete";
			replay = "Play Again? Y/N";
			
			if (gc.getInput().isKeyPressed(Input.KEY_Y)) {
				
				generateNewMap();
				
			} else if (gc.getInput().isKeyPressed(Input.KEY_N)) {
				gc.exit();
			}
			
		}
		
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	public int randomTileIdGenerator(int difficulty) {
		
		if (difficulty == 3) {
			if ((int)(Math.random() * ((difficulty - 0) + 1)) == 1) {
				
				return 19;
				
			} else {
				return 9 + (int)(Math.random() * ((18 - 9) + 1));
			}
		} else if (difficulty == 2) {
			if ((int)(Math.random() * ((difficulty - 0) + 1)) == 1) {
				
				return 19;
				
			} else {
				return 9 + (int)(Math.random() * ((18 - 9) + 1));
			}
		} else if (difficulty == 1) {
			if ((int)(Math.random() * ((difficulty - 0) + 1)) == 1) {
				
				return 19;
				
			} else {
				return 9 + (int)(Math.random() * ((18 - 9) + 1));
			}
		} else if (difficulty == 5){
			if ((int)(Math.random() * ((6 - 0) + 1)) == 1) {
				
				return 9 + (int)(Math.random() * ((18 - 9) + 1));
				
			} else {
				return 19;
			}
		} else {
			return 19;
		}
		
	}
	
	public String renderHighScore(int highScore) {
		
		highScoreString = Integer.toString(highScore);
		
		return highScoreString;
		
	}
	
	public void generateNewMap() {
		
		for (int i = 0; i < tileMap.getWidth(); i++) {
			for (int j = 0; j < tileMap.getHeight(); j++) {
				tiles[i][j] = new Rectangle((i * rectWidth), ((j * rectHeight) + 24), rectWidth, rectHeight);
				tileMap.setTileId(i, j, 0, randomTileIdGenerator(difficulty));
				tilesCleared[i][j] = false;
			}
		}
		
		complete = "";
		replay = "";
		
		highScore = 0;
		
	}
	
	public boolean isGameOver() {
		
		int numOfClearedTiles = 0;
		
		for (int i = 0; i < tileMap.getWidth(); i++) {
			for (int j = 0; j < tileMap.getHeight(); j++) {
				int tempTileId = tileMap.getTileId(i, j, 0);
				if (tempTileId == 19) {
					numOfClearedTiles++;
				}
			}
		}
		
		if (numOfClearedTiles == 450) {
			return true;
		} else {
			return false;
		}
		
	}
	
}

