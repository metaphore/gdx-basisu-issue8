package com.crashinvaders.basisugwttest;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.crashinvaders.basisu.gdx.*;
import com.crashinvaders.basisu.wrapper.BasisuTranscoderTextureFormat;

public class App extends ApplicationAdapter {
    private static final String TAG = "App";

    private SpriteBatch batch;
    private AssetManager assetManager;
    private Texture texture0;
    private Texture texture1;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        batch = new SpriteBatch();

        BasisuNativeLibLoader.loadIfNeeded();
        testToGlTextureFormat();
        testTextureDataPrepare();
        Gdx.app.log(TAG, "Tests passed!");

        InternalFileHandleResolver fhResolver = new InternalFileHandleResolver();
        assetManager = new AssetManager(fhResolver);
        assetManager.setLoader(Texture.class, ".basis", new BasisuTextureLoader(fhResolver));
        assetManager.setLoader(Texture.class, ".ktx2", new Ktx2TextureLoader(fhResolver));
//        assetManager.load("screen_stuff.etc1s.basis", Texture.class);
//        assetManager.load("screen_stuff.uastc.ktx2", Texture.class);
        assetManager.finishLoading();
//        texture0 = assetManager.get("screen_stuff.etc1s.basis", Texture.class);
//        texture1 = assetManager.get("screen_stuff.uastc.ktx2", Texture.class);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//        batch.draw(texture0, 0, 0, 100, 100);
//        batch.draw(texture1, 100, 0, 100, 100);
//        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetManager.dispose();
    }

    private void testToGlTextureFormat() {
        for (BasisuTranscoderTextureFormat value : BasisuTranscoderTextureFormat.values()) {
            BasisuGdxUtils.toGlTextureFormat(value);
        }
    }

    private void testTextureDataPrepare() {
        FileHandle textureFile = Gdx.files.internal("kodim3.basis");
        BasisuTextureData textureData = new BasisuTextureData(textureFile, 0);
        textureData.setTextureFormatSelector(BasisuTranscoderTextureFormat.ETC2_RGBA);
        textureData.prepare();
    }
}
