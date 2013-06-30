package eamo.engine.rendering;

import java.util.ArrayList;
import java.util.List;

import eamo.engine.component.Component;

public class Layer implements Comparable< Layer >
{
    private String layerID;
    private int zIndex;
    private List< Component > components;

    public Layer( String layerID, int zIndex )
    {
        this.layerID = layerID;
        this.components = new ArrayList< Component >();
    }

    public String getLayerID()
    {
        return layerID;
    }

    public void addComponent( Component component )
    {
        components.add( component );
    }

    public List< Component > getComponents()
    {
        return components;
    }

    @Override
    public int compareTo( Layer o )
    {
        return ( (Integer) zIndex ).compareTo( (Integer) o.zIndex );
    }
}
