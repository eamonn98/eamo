package eamo.engine.sandbox;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import eamo.engine.component.Component;
import eamo.engine.component.attribute.Attribute;
import eamo.engine.component.message.Message;
import eamo.engine.rendering.AbstractRenderer;
import eamo.engine.transform.Transform;

public class GameCharacter
{
    public static final int CHARACTER_ID = 1;
    public static final long CHARACTER_TRANSFORM = 1L;
    public static final long CHARACTER_RENDERER = 1L;
    
    public static final String TEST_SPRITE = "data/sprites2.png";
    public static final Rectangle BOUNDS = new Rectangle( 0, 0, 32, 48 );

    public Component characterComponent;

    /**
     *
     */
    private class Sprite extends Attribute< Texture >
    {
        public static final String ATTRIBUTE_ID = "Sprite_Attribute";

        public Sprite( Texture texture )
        {
            super( ATTRIBUTE_ID, texture );
        }
    }

    /**
     * A defined set of bounds in local coordinates.
     */
    private class Bounds extends Attribute< Rectangle >
    {
        public static final String ATTRIBUTE_ID = "Bounds_Attribute";

        public Bounds( Rectangle attribute )
        {
            super( ATTRIBUTE_ID, attribute );
        }
    }

    private class CharacterRenderer extends AbstractRenderer
    {
        public CharacterRenderer( long id, Component parent )
        {
            super( id, parent, "GAME_LAYER" );
        }

        int animSpeed = 8;
        int frames = 4;
        int currentFrame = 1;
        int animDirection = 1;
        int midFrame = 0;

        @Override
        protected void render( SpriteBatch batch, float delta )
        {
            if ( midFrame >= animSpeed )
            {
                currentFrame += animDirection;
                midFrame = 0;
            }
            midFrame++;

            if ( currentFrame >= frames || currentFrame <= 0 )
            {
                animDirection *= -1;
                currentFrame += animDirection * 2;
                midFrame = 0;
            }

            Transform transform = (Transform) characterComponent.getSubComponent( CHARACTER_TRANSFORM );
            
            Rectangle bounds = (Rectangle) getRootComponent().getAttribute( Bounds.ATTRIBUTE_ID ).getAttribute();
            Texture texture = (Texture) getRootComponent().getAttribute( Sprite.ATTRIBUTE_ID ).getAttribute();

            int x = ( (int) bounds.width * currentFrame );
//            System.out.println( "Cur: " + currentFrame + ", dir: " + animDirection );
            TextureRegion region = new TextureRegion( texture, x, 0, (int) bounds.width, (int) bounds.height );
            batch.draw( region, transform.getPosition().x, transform.getPosition().y, 128, 128 );
        }
    }

    public static class RenderMessage extends Message
    {
        public static final String MESSAGE_ID = "Render_Message";

        private SpriteBatch batch;
        private float delta;

        public RenderMessage( SpriteBatch batch, float delta )
        {
            super( MESSAGE_ID );
            this.batch = batch;
            this.delta = delta;
        }

        public SpriteBatch getBatch()
        {
            return batch;
        }

        public float getDelta()
        {
            return delta;
        }
    }

    /**
     * Constructor
     */
    public GameCharacter()
    {
        characterComponent = new Component( CHARACTER_ID );
        characterComponent.addAttribute( new Sprite( new Texture( TEST_SPRITE ) ) );
        characterComponent.addAttribute( new Bounds( BOUNDS ) );
        
        Component transform =  new Transform( CHARACTER_TRANSFORM, characterComponent );
        characterComponent.addSubComponent( transform );
        
        Component renderer = new CharacterRenderer( CHARACTER_RENDERER, characterComponent );
        characterComponent.addSubComponent( renderer );
    }

    /**
     * Test method
     * 
     * @return
     */
    public static Component createInstance()
    {
        GameCharacter c = new GameCharacter();
        return c.characterComponent;
    }
}
