package com.chorsstudio.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGame extends ApplicationAdapter {

	public int WIDTH;
	public int HEIGHT;

	SpriteBatch batch;
	Texture img;

	private OrthographicCamera camera;
	private StretchViewport viewport;



	@Override
	public void create () {

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		camera = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT, camera);
		viewport.apply();
		camera.position.set(WIDTH/2, HEIGHT/2, 0);

	}

	@Override
	public void render () {
		camera.update();

		camera.position.set(WIDTH/2, HEIGHT/2, 0);


		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(img, WIDTH/2, HEIGHT/2);
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
