package com.wholebean.robots;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by john on 9/17/18.
 */

public class Entity implements Drawable {
    public enum TYPE {
        NOTHING,
        PLAYER,
        ROBOT,
        WALL,
        SCRAP,
        HATCH
    }

    public enum VERB {
        NONE,
        STUN
    }

    protected Vector2 position;
    protected Animation<TextureRegion> sprite;
    protected float accumulator = 0;
    protected TYPE type;

    Entity(TYPE type, int position, float animationSpeed, Array<TextureRegion> sprite) {
        this.type = type;
        this.position = Utils.coordsFromIndex(position, Playfield.width);
        this.sprite = new Animation<TextureRegion>(animationSpeed, sprite);
        this.sprite.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void act() {}
    public void react(Entity other, VERB verb) {}
    public void draw(float delta, SpriteBatch batch) {
        this.accumulator += delta;
        batch.draw(
                this.sprite.getKeyFrame(this.accumulator),
                Playfield.getScreenX((int) this.position.x),
                Playfield.getScreenY((int) this.position.y)
        );
    }

    public int getPositionIndex() {
        return Utils.indexFromCoords(this.position, Playfield.width);
    }

    public Vector2 getPosition() {
        return this.position;
    }
}