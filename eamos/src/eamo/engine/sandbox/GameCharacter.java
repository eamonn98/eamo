package eamo.engine.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import eamo.engine.component.Component;
import eamo.engine.component.GameEntity;
import eamo.engine.transform.Transform;

public class GameCharacter extends GameEntity
{
    public static final String CHARACTER_ID = "CHARACTER_ID";
    public static final String TEST_SPRITE = "data/sprites2.png";
    private Rectangle bounds = new Rectangle( 0, 0, 32, 48 );
    float moveSpeed = 2.0f;

    /**
     * Handles drawing of entity sprite
     */
    public class SpriteComponent extends Component
    {
        private Sprite sprite;
        private Texture tex;

        // TODO temp fields

        public SpriteComponent()
        {
            super( "TEST SPRITE COMPONENT" );
            tex = new Texture( TEST_SPRITE );
            sprite = new Sprite( tex );
        }

        @Override
        public void render( SpriteBatch batch, float delta )
        {
            Transform transform = (Transform) getParent().getTransform();
            Texture texture = tex;

            AnimationComponent comp = (AnimationComponent) getParent().getComponent( AnimationComponent.class );
            
            int currentFrame = 1;
            if( comp != null ) {
                currentFrame = comp.getCurrentFrame();
            }
            int x = ( (int) bounds.width * currentFrame );
//            System.out.println( "Cur: " + currentFrame + ", dir: " + animDirection );
            TextureRegion region = new TextureRegion( texture, x, 0, (int) bounds.width, (int) bounds.height );
            region.flip( false, true );

            float scale = 1.2f;
            batch.draw( region, transform.getPosition().x - bounds.width * scale / 2f, transform.getPosition().y - bounds.height * scale, bounds.width * scale, bounds.height * scale );
        }
    }

    /**
     * Processes frames and sprite animation
     */
    public class AnimationComponent extends Component
    {
        int animSpeed = 16;
        int frames = 4;
        int currentFrame = 1;
        int animDirection = 1;
        int midFrame = 0;

        public AnimationComponent()
        {
            super( "Animation Component" );
        }
        
        @Override
        public void update( float delta )
        {
            super.update( delta );

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
        }
        
        public int getCurrentFrame()
        {
            return currentFrame;
        }
    }
    
    /**
     * Script that processes and updates character's movement based on where the
     * user clicks the mouse.
     */
    public class MovementComponent extends Component
    {
        Transform transform;
        Vector2 moveTo;

        public MovementComponent()
        {
            super( "Movement Component" );
        }

        @Override
        public void init()
        {
            super.init();
            transform = (Transform) getParent().getTransform();
            moveTo = Vector2.Zero;
        }

        @Override
        public void update( float delta )
        {
            if ( Gdx.input.isTouched() == true )
            {
                moveTo = new Vector2( Gdx.input.getX(), Gdx.input.getY() );
            }

            if ( !moveTo.equals( transform.getPosition() ) )
            {
                transform.getPosition().add( ( ( moveTo.x - transform.getPosition().x ) * moveSpeed ) * delta, ( ( moveTo.y - transform.getPosition().y ) * moveSpeed ) * delta );
            }
        }
    }

    /**
     * Constructor
     */
    public GameCharacter()
    {
        super( CHARACTER_ID + System.currentTimeMillis() );
    }

    @Override
    public void init()
    {
        super.init();
        addComponent( new SpriteComponent() );
        addComponent( new MovementComponent() );
        addComponent( new AnimationComponent() );
    }

    public void setMoveSpeed( float moveSpeed )
    {
        this.moveSpeed = moveSpeed;
    }

    /**
     * Test method
     * 
     * @return
     */
    public static GameCharacter createInstance()
    {
        return new GameCharacter();
    }
}
