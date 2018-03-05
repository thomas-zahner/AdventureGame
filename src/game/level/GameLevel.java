package game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.level.generation.GenerateLevel;
import game.util.Print;
import game.util.TileCoordinate;

public class GameLevel extends Level
{
	private String path;
	private long seed;
	private String levelName;
	private boolean customLevel;

	public GameLevel(String path, String levelName, int spawnX, int spawnY)
	{
		super(new TileCoordinate(spawnX, spawnY));
		this.path = path;
		this.customLevel = true;
		this.levelName = levelName;
	}

	public GameLevel(long seed, String levelName, int spawnX, int spawnY)
	{
		super(new TileCoordinate(spawnX, spawnY));
		this.seed = seed;
		this.customLevel = false;
		this.levelName = levelName;
	}

	public void loadLevel(String path)
	{
		try
		{
			BufferedImage image = ImageIO.read(GameLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			//Converting image into the array levelPixels
			image.getRGB(0, 0, width, height, tiles, 0, width);
		}
		catch(IOException e)
		{
			Print.printError(e.getMessage());
		}
	}

	public void loadLevel(long seed)
	{
		height = width = 64;
		tiles = new int[width * height];
		tiles = GenerateLevel.generateLevel(seed, 64);
	}

	public String getPath()
	{
		return path;
	}

	public long getSeed()
	{
		return seed;
	}

	public boolean isCustomLevel()
	{
		return customLevel;
	}

	public String getLevelName()
	{
		return levelName;
	}
}
