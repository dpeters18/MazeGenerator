// Class: GridPlotterGUI
//
// Author: Alyce Brady
//
// This class is based on the College Board's FishToolbar class,
// as allowed by the GNU General Public License.  FishToolbar is a
// black-box GUI class within the AP(r) CS Marine Biology Simulation
// case study (see www.collegeboard.com/ap/students/compsci).
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

import java.awt.BorderLayout;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.kzoo.grid.Location;

import edu.kzoo.grid.display.ColorBlockDisplay;
import edu.kzoo.grid.display.DisplayMap;

import edu.kzoo.grid.gui.EnabledDisabledStates;
import edu.kzoo.grid.gui.GeneratedButtonList;
import edu.kzoo.grid.gui.GridAppFrame;
import edu.kzoo.grid.gui.nuggets.BGColorChoiceMenu;
import edu.kzoo.grid.gui.nuggets.ColorChoiceMenu;
import edu.kzoo.grid.gui.nuggets.MinimalFileMenu;
import edu.kzoo.grid.gui.nuggets.NewBoundedGridButton;

/**
 *  Grid Plotter:<br>
 *
 *    The <code>GridPlotterGUI</code> class provides the graphical
 *    user interface for a program that draws in a grid by placing
 *    color blocks in its cells.
 *
 *  @author Alyce Brady (based on code by Julie Zelenski)
 *  @version 1 September 2004
 **/
public class GridPlotterGUI extends GridAppFrame
{
    // Define control buttons attributes.
    private static final boolean INCLUDE_ONLY_ON_BUTTONCLICK_METHODS = true;
    private static final boolean DISPLAY_GRID_AFTER_BUTTON_PRESSES = true;

    // Keep track of background color chooser menu for redefined
    // constructWindowContents method.
    private ColorChoiceMenu bgColorChooser;
  // constructor
    /** Constructs the graphical user interface for the Grid
     *  Plotter program.
     *    @param maxDelayMsecs longest delay value for slider, in milliseconds
     *    @param minDelayMsecs shortest delay value for slider, in milliseconds
     **/
    public GridPlotterGUI(int maxDelayMSecs, int minDelayMsecs)
    {
        // Construct and initialize the GUI window.  It will need the list
        // of drawing methods from the GridPlotter class.
        super();

        includeMenu(new MinimalFileMenu());

        // Include a New Grid button.
        includeControlComponent(new NewBoundedGridButton(this, "New Grid"),
                                EnabledDisabledStates.NEEDS_APP_WAITING);

        // Include a background color chooser component.
        bgColorChooser = new BGColorChoiceMenu(this);
        includeControlComponent(bgColorChooser,
                               EnabledDisabledStates.NEEDS_APP_WAITING);

        // Include a color chooser component for color blocks.
        ColorChoiceMenu colorBlockColorChooser =
                                          new ColorChoiceMenu("Drawing Color:");
        includeControlComponent(colorBlockColorChooser,
                                EnabledDisabledStates.NEEDS_APP_WAITING);

        // Generate control buttons derived from the methods of the target
        // GridPlotter object and include them in the user interface.  The
        // target object needs to know when a new grid is created, so register
        // it as a grid change listener.
        GridPlotter plotter = new GridPlotter(this, colorBlockColorChooser);
        
        
        addGridChangeListener(plotter);
        GeneratedButtonList generatedButtonList = 
            new GeneratedButtonList(this, plotter,
                                    INCLUDE_ONLY_ON_BUTTONCLICK_METHODS,
                                    DISPLAY_GRID_AFTER_BUTTON_PRESSES);
        includeControlComponents(generatedButtonList,
                            EnabledDisabledStates.NEEDS_GRID_AND_APP_WAITING);

        // Include a speed slider.
        includeSpeedSlider(maxDelayMSecs, minDelayMsecs);

        // Specify what object knows how to display color blocks.
        DisplayMap.associate("edu.kzoo.grid.ColorBlock",
                             new ColorBlockDisplay());
    }

    /* Inherit description from GridAppFrame. */
    public void constructWindowContents(String title,
                                        int viewingWidth, int viewingHeight,
                                        int minCellSize)
    { 
        constructWindowContents(title, bgColorChooser.currentColor(),
                                viewingWidth, viewingHeight,
                                minCellSize);
    }

    /* Inherit description from GridAppFrame. */
    protected JPanel defineContent()
    {
        JPanel content = new JPanel();
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        content.setLayout(new BorderLayout());

        // Create a panel to display the grid and speed slider bar
        // and another for the control buttons (e.g., a Reset button),
        // and add them to the main panel. 
        JPanel gridAndSliderPanel = new JPanel();
        gridAndSliderPanel.setLayout(new BorderLayout());

        gridAndSliderPanel.add(makeDisplayPanel(), BorderLayout.CENTER);

        JComponent sliderPanel = makeSliderPanel();
        if ( sliderPanel != null )
            gridAndSliderPanel.add(sliderPanel, BorderLayout.SOUTH);

        content.add(gridAndSliderPanel, BorderLayout.CENTER);

        JComponent controlPanel = makeControlPanel(null);
        if ( controlPanel != null )
            content.add(controlPanel, BorderLayout.WEST);

        return content;
    }

    /** Shows the contents of a single location in the grid.  This allows
     *  for speedier display than the showGrid method because it only updates
     *  the contents of the one, specified location.
     *  (Precondition: must have called <code>setGrid</code>.)
     **/
    public void showLocation(Location loc)
    {
        getDisplay().updateLocation(loc);
        if ( getDelay() > 0 )
        {
            try 
            {
              Thread.sleep(getDelay());
            }
            catch (InterruptedException e) {}
        }
    }

}
