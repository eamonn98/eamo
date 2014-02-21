package eamo.engine.input;

import java.util.HashMap;
import java.util.Map;

public class Input
{
    private static Map< Integer, Boolean > keyStatusMap;
    private static Map< String, Integer > inputClassification;

    static
    {
        new Input();
    }

    private Input()
    {
        keyStatusMap = new HashMap< Integer, Boolean >();
        inputClassification = new HashMap< String, Integer >();
    }

    private static synchronized Map< Integer, Boolean > getKeyStatusMap()
    {
        return keyStatusMap;
    }

    public static boolean isKeyDown( int keycode )
    {
        return ( getKeyStatusMap().get( keycode ) );
    }

    public static boolean isKeyUp( int keycode )
    {
        return ( getKeyStatusMap().get( keycode ) );
    }

    public static void setKeyDown( int keycode )
    {
        getKeyStatusMap().put( keycode, true );
    }

    public static void setKeyUp( int keycode )
    {
        getKeyStatusMap().put( keycode, false );
    }

}
