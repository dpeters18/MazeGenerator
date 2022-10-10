// Class: GridPlotter
//
// Author: Your Name Here
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

import edu.kzoo.grid.ColorBlock;
import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.Direction;
import edu.kzoo.grid.gui.GridChangeListener;
import edu.kzoo.grid.gui.nuggets.ColorChoiceMenu;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;

import java.awt.Component;
/**
 *  Grid Plotter:<br>
 *
 *    The GridPlotter class provides methods for drawing in
 *    a grid by placing color blocks in its cells.
 *    Each drawing method should take zero arguments, should
 *    have a void return type, and should have a name that conforms
 *    to the on...ButtonClick format.  (This restriction allows the
 *    GridPlotterGUI to recognize drawing methods, for which
 *    it automatically generates buttons.)
 *
 *  @author Your Name (based on a template provided by Alyce Brady)
 *  @version Appropriate Date
 **/

public class GridPlotter implements GridChangeListener
{
  // Instance Variables: Encapsulated data for EACH GridPlotter object
    private Grid theGrid = null;
    private GridPlotterGUI display = null;
    private ColorChoiceMenu drawingColorChooser = null;
    private ArrayList<Location> plaid;
    private ArrayList<Integer> visitlist;
    private Random rand;
    private Location atloc;
    private SimpleAudioPlayer audioPlayer;
  // constructors and initialization
 private boolean keyLoop=true;
    /** Constructs an object that could draw in a grid.
     *      @param disp    an object that knows how to display a grid
     *      @param colorChooser  a menu for choosing the color block color
     **/
    public GridPlotter(GridPlotterGUI disp,
                       ColorChoiceMenu colorChooser)
    {
        this.display = disp;
        this.drawingColorChooser = colorChooser;
        this.rand=new Random();
        this.atloc=new Location(0,1);
        try
        {
            this.audioPlayer = 
                            new SimpleAudioPlayer();
              
        } 
          
        catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
          
          }
    }

    /** Sets the grid in which to draw.
     **/
    public void reactToNewGrid(Grid grid)
    {
        theGrid = grid;
    }

  // drawing methods

    /** Removes all objects from the grid.
     **/
    public void onNewMazeButtonClick()
    {
        // CODE MISSING !
        //     Should call ensureEmpty for every location in the grid.
            for(int i=0;i<theGrid.numRows();i++)
            {
                for(int j=0;j<theGrid.numCols();j++)
                {
                    ensureEmpty(i,j);
                }
            }
      
        // Display the grid after it has been completely cleared.
        display.showGrid();
        onMazeGeneratorButtonClick();
        display.showGrid();
        atloc=new Location(0,1);
    }

    public void plaid()
    {
        
        //Filling in the grid in a chex mix pretzel pattern first (only works for grids with odd dimensions), 
        //and storing locations of any empty spaces in an array list:
        plaid=new ArrayList<Location>();
        visitlist= new ArrayList<Integer>();
        for(int i=0;i<theGrid.numRows();i++)
        {
            for(int j=0;j<theGrid.numCols();j++)
            {
                if(i%2==0||j%2==0)
                placeColorBlock(i,j);
                else 
                plaid.add(new Location(i,j));
            }
        }
        visitlist.add(0);
    }
    /** Produces the maze, then loads it. 
     * 
     **/
    public void onMazeGeneratorButtonClick()
    {
        //Filling in the grid in a chex mix pretzel pattern first (only works for grids with odd dimensions), 
         try{
             plaid();
             //now to generate the maze:
             mazeTraversal(0,1);
             System.out.println("-");
             //and clear the endpoint and fixed starting point:
             ensureEmpty(0,1);
             ensureEmpty(theGrid.numRows()-1,theGrid.numCols()-2);
             theGrid.add(new ColorBlock(Color.BLACK),new Location(0,1));
             atloc=new Location(0,1);
             display.showGrid();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
    }
    public void onDownButtonClick()
    {      Location target= new Location(atloc.row()+1,atloc.col());
        if (theGrid.isValid(target)&&theGrid.isEmpty(target))
        {
            ensureEmpty(atloc.row(),atloc.col());
            theGrid.add(new ColorBlock(Color.BLACK),target);
            atloc=target;
        }
        else{try{
            audioPlayer.resumeAudio();
            Thread.sleep(217);
            audioPlayer.pause();
        }
        catch(Exception e){
           e.printStackTrace();
        }
        }
        display.showGrid();
    }
    public void onUpButtonClick()
    {
        Location target= new Location(atloc.row()-1,atloc.col());
        if (theGrid.isValid(target)&&theGrid.isEmpty(target))
        {
            ensureEmpty(atloc.row(),atloc.col());
            theGrid.add(new ColorBlock(Color.BLACK),target);
            atloc=target;
        }
        else{
            try{
            audioPlayer.resumeAudio();
            Thread.sleep(217);
            audioPlayer.pause();
        }
        catch(Exception e){
           e.printStackTrace();
        }
        }
                display.showGrid();
    }
    public void onLeftButtonClick()
    {
        Location target= new Location(atloc.row(),atloc.col()-1);
        if (theGrid.isValid(target)&&theGrid.isEmpty(target))
        {
            ensureEmpty(atloc.row(),atloc.col());
            theGrid.add(new ColorBlock(Color.BLACK),target);
            atloc=target;
        }
        else{
            try{
            audioPlayer.resumeAudio();
            Thread.sleep(217);
            audioPlayer.pause();
        }
        catch(Exception e){
           e.printStackTrace();
        }
        }
                display.showGrid();
    }
    public void onRightButtonClick()
    {
        Location target= new Location(atloc.row(),atloc.col()+1);
        if (theGrid.isValid(target)&&theGrid.isEmpty(target))
        {
            ensureEmpty(atloc.row(),atloc.col());
            theGrid.add(new ColorBlock(Color.BLACK),target);
            atloc=target;
        }
        else{
            try{
            audioPlayer.resumeAudio();
            Thread.sleep(217);
            audioPlayer.pause();
        }
        catch(Exception e){
           e.printStackTrace();
        }
        }
                display.showGrid();
    }
  // helper methods that are called by other methods
    private boolean plaidNeighbors(Location loc, Location loca)
    {
        if (loc.row()-loca.row()==2||loc.row()-loca.row()==-2)
        {
            if(loc.col()==loca.col())
            return true;
        }
        if (loc.col()-loca.col()==2||loc.col()-loca.col()==-2)
        {
            if(loc.row()==loca.row())
            return true;
        }
        return false;
    }
    private boolean Visited(Location loc)
    {
        for (Location loca: theGrid.neighborsOf(loc))
        {
            if(theGrid.isEmpty(loca))
            return true;
        }
        return false;
    }
    private boolean mazeTraversal(int current, int visit)
    {
        if(visit==plaid.size())
        return true;
            //storing potential candidates for locations that are in the plaid array and adjacent to the current location:
            ArrayList<Integer> indeces= new ArrayList<Integer>();
        
            if(0<=current-1&&current-1<plaid.size())
            indeces.add(current-1);
            if(0<=current+1&&current+1<plaid.size())
            indeces.add(current+1);
            if(0<=current-theGrid.numCols()/2&&current-theGrid.numCols()/2<plaid.size())
            indeces.add(current-theGrid.numCols()/2);
            if(0<=current+theGrid.numCols()/2&&current+theGrid.numCols()/2<plaid.size())
            indeces.add(current+theGrid.numCols()/2);
           //System.out.println(indeces.toString());
           //checking the candidates one-by-one:
           ArrayList<Location> newLocs= new ArrayList<Location>();
            for (int num: indeces)
            {
                if(plaidNeighbors(plaid.get(num),plaid.get(current))&&!Visited(plaid.get(num)))
                  {//System.out.println(plaid.get(num)+""+plaid.get(current));
                   newLocs.add(plaid.get(num));
                }
            }
            int allvisited=0;
            //checking surrounding locations to see if they've been visited:
            for(Location loc: newLocs)
            {
                if (Visited(loc))
                allvisited++;
            }
            if (allvisited==newLocs.size())
            return mazeTraversal(visitlist.get(visitlist.indexOf(current)-1),visit);
            //choosing an unvisited neighbor at random, then making that neighbor the current object
            int x= rand.nextInt(newLocs.size());
            Direction dir= theGrid.getDirection(plaid.get(current),newLocs.get(x));
            ensureEmpty(theGrid.getNeighbor(plaid.get(current),dir).row(),theGrid.getNeighbor(plaid.get(current),dir).col());
           // System.out.println(newLocs.get(x).toString());
            
            int newcurrent=plaid.indexOf(newLocs.get(x));
            visitlist.add(newcurrent);
            return mazeTraversal(newcurrent,visit+1);
    }
    private boolean isOccupied(int row,int column)
    {
        Location loc=new Location(row,column);
        if( ! theGrid.isEmpty(loc) )
        return true;
        return false;
    }
    
    /** Ensures that the specified location is empty by removing the object
     *  there, if there is one.
     *      @param row      row number of location that should be empty
     *      @param column   column number of location that should be empty
     **/
    private void ensureEmpty(int row, int column)
    {
        // If the specified location in the grid is not empty,
        // remove whatever object is there.
        Location loc = new Location(row, column);
        if ( ! theGrid.isEmpty(loc) )
            theGrid.remove(theGrid.objectAt(loc));
    }

    /** Puts a color block in the specified location and redisplays the grid.
     *      @param row      row in which to place the new color block
     *      @param column   column in which to place the new color block
     **/
    private void placeColorBlock(int row, int column)
    {
        // First remove any color block that happens to be at this location.
        ensureEmpty(row, column);

        // Determine the color to use for this color block.
        Color color = drawingColorChooser.currentColor();
        // Construct the color block and add it to the grid at the
        // specificed location.  Then display the grid.
        Location loc = new Location(row, column);
        theGrid.add(new ColorBlock(color), loc);
        display.showLocation(loc);
    }

}
