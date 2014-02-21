package eamo.engine.component.common;

import com.badlogic.gdx.utils.Disposable;

public abstract class LifecycleObject implements Disposable
{
    private String id;

    public LifecycleObject( String id )
    {
        this.id = id;
    }

    public String getID()
    {
        return id;
    }

    public void setID( String id )
    {
        this.id = id;
    }

    public abstract void show();

    public abstract void hide();

    public abstract void init();

    public abstract void dispose();
    
    public void update( float delta )
    {

    }

    public void fixedUpdate()
    {

    }

    public void render( float delta )
    {

    }
}
