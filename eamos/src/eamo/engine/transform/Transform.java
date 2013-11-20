package eamo.engine.transform;

import com.badlogic.gdx.math.Vector2;

import eamo.engine.component.Component;
import eamo.engine.component.GameEntity;

/**
 * Transform component representing a position, rotation and scale of an entity.
 * Typically a transform stores a game entities position in the world, it's
 * rotation (in radians) and percentage of original size.
 */
public class Transform extends Component
{
    private Vector2 position;
    private Float rotation;
    private Float scale;

    /**
     * Create a transform component with all attributes initialised to defaults.
     * 
     * @param id
     */
    public Transform( String id, GameEntity parent )
    {
        this( id, parent, new Vector2( 0.0f, 0.0f ), 0.0f, 1.0f );
    }

    /**
     * Create a transform component with all attributes initialised to the
     * specified values.
     * 
     * @param id this component's unique identifier.
     * @param position the position attribute of this transform.
     * @param rotation this transform's rotation attribute.
     * @param scale this transform's scale attribute.
     */
    public Transform( String id, GameEntity parent, Vector2 position, float rotation, float scale )
    {
        super( id, parent );

        setPosition( position );
        setRotation( rotation );
        setScale( scale );
    }

    /**
     * Get the position attribute of this transform representing this
     * transform's position.
     * 
     * @return the position attribute of this transform.
     */
    public Vector2 getPosition()
    {
        return position;
    }

    /**
     * Get this transform's rotation attribute representing the transform's
     * rotation in radians.
     * 
     * @return this transform's rotation attribute.
     */
    public float getRotation()
    {
        return rotation;
    }

    /**
     * Get this transform's scale attribute representing this transform's scale
     * as a percentage of an original width and height.
     * 
     * @return this transform's scale attribute.
     */
    public float getScale()
    {
        return scale;
    }

    /**
     * Set this transform's position attribute.
     * 
     * @param position this transform's position attribute.
     */
    public void setPosition( Vector2 position )
    {
       this.position = position;
    }

    /**
     * Set this transform's rotation attribute.
     * 
     * @param rotation this transform's rotation attribute.
     */
    public void setRotation( float rotation )
    {
        this.rotation = rotation;
    }

    /**
     * Set this transform's scale attribute.
     * 
     * @param scale this transform's scale attribute.
     */
    public void setScale( float scale )
    {
        this.scale = scale;
    }

    /**
     * Add the supplied transform's attribute values to this transform's
     * attributes.
     * 
     * @param other the other transform to add.
     */
    public void addTransform( Transform other )
    {
        setPosition( getPosition().add( other.getPosition() ) );
        setRotation( getRotation() + other.getRotation() );
        setScale( getScale() + other.getScale() );
    }

    /**
     * Subtract the supplied transform's attribute values from this transform's
     * attributes. TODO is this required?
     * 
     * @param other the other transform to subtract.
     */
    public void subtractTransform( Transform other )
    {
        setPosition( getPosition().sub( other.getPosition() ) );
        setRotation( getRotation() - other.getRotation() );
        setScale( getScale() - other.getScale() );
    }
}
