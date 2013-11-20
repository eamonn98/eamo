package eamo.engine.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eamo.engine.component.common.LifecycleObject;

/**
 * Game component class TODO javadoc
 */
public class Component extends LifecycleObject
{
    private GameEntity parent;
    private float age;
    private boolean initialised;

    /**
     * Create a new component supplying only an ID. Notice that a component
     * without any parent component is considered to be a root level component.
     * A root level component is a component that exists at the top of a
     * component-subcomponent hierarchy.
     * <p>
     * A root level component has it's own message dispatcher in which sub
     * components may register for events with.
     * 
     * @param id the component unique identifier.
     */
    public Component( String id )
    {
        super( id );
    }

    /**
     * Create a component object and supply it's parent and unique identifier
     * attributes.
     * 
     * @param id the id of the component.
     * @param parent this component's parent.
     */
    public Component( String id, GameEntity parent )
    {
        super( id );
        this.parent = parent;
    }

    /**
     * This method permits a component to update itself once per frame. As it
     * stands in a generic empty component, this method does not actually
     * perform any functionality whatsoever.
     * 
     * @param delta the delta time since the last update.
     */
    public void update( float delta )
    {
        age += delta;
    }

    /**
     * Initialise this component and assign it's component manager.
     * 
     * @param manager the component manager to assign.
     */
    @Override
    public void init()
    {
        this.age = 0.0f;
        this.initialised = true;
    }

    /**
     * Destroy the component and all sub-components of this component. Causes
     * any event listeners registered with the component manager to be
     * invalidated and removed.
     */
    @Override
    public void dispose()
    {

    }

    /**
     * Get the parent game entity of this component.
     * 
     * @return this component's parent entity.
     */
    public GameEntity getParent()
    {
        return parent;
    }

    /**
     * Set the parent component of this component to a different value. Note
     * that a component's new parent must share the same root component. It is
     * not possible to move components between two different component
     * hierarchies.
     * 
     * @param parent the component to assign to this component as a parent.
     */
    public void setParent( GameEntity parent )
    {
        this.parent = parent;
    }

    /**
     * Determine whether this component has been initialised. This basically
     * evaluates to true if the component manager has been assigned to this
     * component.
     * 
     * @return true if this component has been initialised.
     */
    public boolean isInitialised()
    {
        return initialised;
    }

    /**
     * Get the age of the component in seconds since it's first update.
     * 
     * @return the component's age in seconds.
     */
    public float getAge()
    {
        return age;
    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

    }

    public void render( SpriteBatch batch, float delta )
    {

    }
}
