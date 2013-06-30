package eamo.engine.managers;

import com.badlogic.gdx.utils.Disposable;

import eamo.engine.stage.Stage;

/**
 * A manager manages general subsystems from components, rendering, assets and
 * input. A manager associates itself with a parent stage.
 */
public abstract class Manager implements Disposable
{
    private Stage stage;

    /**
     * Construct a manager supplying a parent stage object.
     * 
     * @param stage the parent stage to supply to this manager.
     */
    public Manager( Stage stage )
    {
        this.stage = stage;
    }

    /**
     * Get the parent stage of this manager.
     * 
     * @return the parent stage of this manager.
     */
    public Stage getStage()
    {
        return stage;
    }

    @Override
    public abstract void dispose();
}
