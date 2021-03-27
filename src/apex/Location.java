/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pekin
 */
public class Location
{
    private static List<Location> mapOne = new ArrayList<>();
    private String locationName;
    private int xValue;
    private int yValue;
    private Location tempLocation;

    Location( String location, int x, int y )
    {
        locationName = location;
        xValue = x;
        yValue = y;
    }

    private Location createLocations( String location, int x, int y )
    {
        locationName = location;
        xValue = x;
        yValue = y;
        tempLocation = new Location(locationName, xValue, yValue);
        return tempLocation;
    }

    public static Location verifyLocationValues( String[] locationValues )
    {
        Location tempLocation = null;
        try
        {
            String location = locationValues[0];
            int x = Integer.valueOf(locationValues[1]);
            int y = Integer.valueOf(locationValues[2]);
            tempLocation = new Location(location.replace("_", " "), x, y);
        }
        catch ( NumberFormatException e )
        {
            System.out.println("Error " + e);
        }
        catch(Exception e)
        {
            System.out.println("Error " + e);
        }
        return tempLocation;
    }

    public String getLocationName()
    {
        return locationName;
    }

    public int getxValue()
    {
        return xValue;
    }

    public int getyValue()
    {
        return yValue;
    }

}
