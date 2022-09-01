package dev.CodeWizz.flowy;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.hud.Button;
import dev.CodeWizz.engine.hud.HudManager;
import dev.CodeWizz.engine.hud.Slider;
import dev.CodeWizz.engine.util.Textures;

public class Flowy extends AbstractGame {

	public static List<Water> p = new CopyOnWriteArrayList<>();

	public static int brushSize = 25;

	private Tool tool = Tool.Point;
	private Tile tile = Tile.Sand;
	public boolean drawingWater = false;
	public float height = 0f;
	public float displayHeight = 0f;

	public Flowy() {

		for (int i = 0; i < Cell.SCALE; i++) {
			for (int j = 0; j < Cell.SCALE; j++) {
				Cell.cells[i][j] = new Cell(i, j);
			}
		}
	}

	@Override
	public void update(GameContainer gc, float dt) {
		for (Water p : Flowy.p) {
			p.update();
		}

		if (gc.getInput().isButton(1)) {
			Cell cell = Cell.getCell(gc.getInput().getMouseX(), gc.getInput().getMouseY());
			if (cell != null) {
				if (tool == Tool.Point) {
					if (drawingWater) {
						p.add(new Water(cell.x, cell.y));
					} else {

						for (int i = -(brushSize / 2); i < brushSize / 2; i++) {
							for (int j = -(brushSize / 2); j < brushSize / 2; j++) {
								Cell c = Cell.getCellIndex(i + cell.indexX, j + cell.indexY);
								if (c != null) {
									c.tile = tile;
									c.height = height;
								}
							}
						}
					}
				} else if (tool == Tool.Fill) {
					cell.fill(tile, cell.tile);
				} else if (tool == Tool.Line) {

				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for (int i = 0; i < Cell.SCALE; i++) {
			for (int j = 0; j < Cell.SCALE; j++) {
				Cell.cells[i][j].render(gc, r);
			}
		}

		for (Water p : Flowy.p) {
			p.render(gc, r);
		}
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		r.fillRectUI(540, 0, gc.getWidth(), gc.getHeight(), 0xff4e5453, Light.NONE);

		r.fillRectUI(540, 0, 5, gc.getHeight(), 0xff222034, Light.NONE);
		r.fillRectUI(540, 0, gc.getWidth(), 5, 0xff222034, Light.NONE);
		r.fillRectUI(540, gc.getHeight() - 5, gc.getWidth(), 5, 0xff222034, Light.NONE);
		r.fillRectUI(gc.getWidth() - 5, 0, 5, gc.getHeight(), 0xff222034, Light.NONE);

		r.fillRectUI(540, 94, gc.getWidth(), 5, 0xff222034, Light.NONE);

		r.drawText("HOOGTE: " + (int) displayHeight + " M", 545 + 213, 35, 2, 0xffffffff);

		r.fillRectUI(540, 185, gc.getWidth(), 5, 0xff222034, Light.NONE);

	}

	@Override
	public void init(GameContainer gc) {
		int b = 545;

		HudManager.addComponent(new Slider(b + 217, 58, 169) {
			@Override
			public void valueSet(float value) {
				float c = ((value * 2f) - 1f);
				float b = (int) (c * 10f);

				displayHeight = b;
				height = value;
			}
		});

		// FILL TOOL ICON BUTTON
		HudManager.addComponent(new Button(b + 20, 25, "", Textures.get("icon"), Textures.get("icon-pressed"),
				Textures.get("bucket-icon"), 2) {
			@Override
			public void declick(GameContainer gc) {
				tool = Tool.Fill;
			}
		});
		// POINT TOOL ICON BUTTON
		HudManager.addComponent(new Button(b + 84, 25, "", Textures.get("icon"), Textures.get("icon-pressed"),
				Textures.get("point-icon"), 2) {
			@Override
			public void declick(GameContainer gc) {
				tool = Tool.Point;
			}
		});

		// LINE TOOL ICON BUTTON
		HudManager.addComponent(new Button(b + 148, 25, "", Textures.get("icon"), Textures.get("icon-pressed"),
				Textures.get("line-icon"), 2) {
			@Override
			public void declick(GameContainer gc) {
				tool = Tool.Line;
			}
		});

		// GROUND TOOL ICON BUTTON
		HudManager.addComponent(
				new Button(b + 20, 119, "GROND", Textures.get("button"), Textures.get("button-pressed"), 2) {
					@Override
					public void declick(GameContainer gc) {
						tile = Tile.Ground;
					}
				});
		// GROUND TOOL ICON BUTTON
		HudManager.addComponent(
				new Button(b + 128, 119, "ZAND", Textures.get("button"), Textures.get("button-pressed"), 2) {
					@Override
					public void declick(GameContainer gc) {
						tile = Tile.Sand;
					}
				});

		// GROUND TOOL ICON BUTTON
		HudManager.addComponent(
				new Button(b + 236, 119, "KLEI", Textures.get("button"), Textures.get("button-pressed"), 2) {
					@Override
					public void declick(GameContainer gc) {
						tile = Tile.Clay;
					}
				});

		HudManager.addComponent(
				new Button(b + 20, 200, "WATER", Textures.get("button"), Textures.get("button-pressed"), 2) {

					@Override
					public void declick(GameContainer gc) {
						drawingWater = !drawingWater;
					}

				});

	}

	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Flowy());
		gc.start();
	}
}
