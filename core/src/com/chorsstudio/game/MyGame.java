package com.chorsstudio.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGame extends ApplicationAdapter {

	public int WIDTH;
	public int HEIGHT;
	public int SCREEN_WIDTH;
	public int SCREEN_HEIGHT;

	SpriteBatch batch;
	Texture img;

	private OrthographicCamera camera;
	private StretchViewport viewport;

	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	private int xpos;
	private int ypos;
	private int xdelta = 2;
	private int ydelta = 2;

	@Override
	public void create () {

		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		WIDTH = 8*128;
		HEIGHT = 14*128;

		batch = new SpriteBatch();
		img = new Texture("graphics/hero.png");

		camera = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT, camera);

		camera.update();
		camera.position.set(WIDTH/2, HEIGHT/2, 0);

		tiledMap = new TmxMapLoader().load("maps/kremek_math.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport.apply();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0.6f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		camera.update();

	//	camera.position.set(WIDTH/2, HEIGHT/2, 0);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(img, xpos, ypos);
		batch.end();




 		if ( (xpos > (WIDTH - 300)) || (xpos < 0) ) xdelta = -xdelta;
		if ( (ypos > (HEIGHT - 240)) || (ypos < -60) ) ydelta = -ydelta;
		xpos += xdelta;
		ypos += ydelta;

		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			//camera.unproject(touchPos);
			xpos = (int) touchPos.x - 150;
			ypos = HEIGHT - (int) touchPos.y - 150;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
