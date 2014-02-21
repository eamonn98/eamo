package eamo.engine.component;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eamo.engine.component.common.LifecycleObject;
import eamo.engine.transform.Transform;

public class GameEntity extends LifecycleObject
{
    private String[] tags;
    private Transform transform;
    private GameEntity parent;
    private List< GameEntity > children;
    private List< Component > components;

    public GameEntity( String id )
    {
        super( id );
        components = new ArrayList< Component >();
        children = new ArrayList< GameEntity >();
        transform = new Transform( "Transform", this );
    }

    public GameEntity( String id, GameEntity parent )
    {
        super( id );
        this.parent = parent;
        transform = new Transform( "Transform", this );
    }

    public void addChild( GameEntity child )
    {
        if ( children == null )
        {
            children = new ArrayList< GameEntity >();
        }
        children.add( child );
        child.setParent( this );
    }

    /**
     * Calculate the default Z index of the component in a world according to
     * it's Y position in the world.
     * 
     * @return the Z index of the component according to its transform.
     */
    public float calculateZIndex()
    {
        return getTransform().getPosition().y;
    }

    public void addComponent( Component c )
    {
        c.setParent( this );

        if ( !c.isInitialised() )
        {
            c.init();
        }
        components.add( c );
    }

    public void removeComponent( Component c )
    {
        c.setParent( null );
        components.remove( c );
    }

    public Component getComponent( Class< ? extends Component > type )
    {
        for ( Component component : components )
        {
            if ( component.getClass().equals( type ) )
            {
                return component;
            }
        }
        return null;
    }

    public Component getComponent( Class< ? extends Component > type, String id )
    {
        for ( Component component : components )
        {
            if ( component.getID().equals( id ) && component.getClass().equals( type ) )
            {
                return component;
            }
        }
        return null;
    }

    public Component getComponent( String id )
    {
        for ( Component component : components )
        {
            if ( component.getID().equals( id ) )
            {
                return component;
            }
        }
        return null;
    }

    public GameEntity getParent()
    {
        return parent;
    }

    public Transform getTransform()
    {
        return transform;
    }

    @Override
    public void show()
    {
        for ( Component c : components )
        {
            c.show();
        }
    }

    @Override
    public void hide()
    {
        for ( Component c : components )
        {
            c.hide();
        }
    }

    @Override
    public void init()
    {
        for ( Component c : components )
        {
            c.init();
        }
    }

    @Override
    public void dispose()
    {
        for ( Component c : components )
        {
            c.dispose();
        }
    }

    @Override
    public void update( float delta )
    {
        for ( Component c : components )
        {
            c.update( delta );
        }
    }

    @Override
    public void fixedUpdate()
    {
        for ( Component c : components )
        {
            c.fixedUpdate();
        }
    }

    @Override
    public void render( float delta )
    {
        for ( Component c : components )
        {
            c.render( delta );
        }
    }

    public void render( SpriteBatch batch, float delta )
    {
        for ( Component c : components )
        {
            c.render( batch, delta );
        }
    }

    public boolean isTopLevel()
    {
        return parent == null;
    }

    public void setParent( GameEntity parent )
    {
        this.parent = parent;
    }

    public void setTransform( Transform transform )
    {
        this.transform = transform;
    }

}
