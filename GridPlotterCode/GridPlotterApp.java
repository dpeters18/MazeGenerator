// Class: GridPlotterApp
//
// Author: Alyce Brady
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

import edu.kzoo.grid.gui.nuggets.BasicHelpMenu;

/**
 *  Grid Plotter:<br>
 *
 *    The <code>GridPlotterApp</code> class provides a <code>main</code>
 *    method for an application that draws by placing color blocks in a
 *    grid.
 *
 *  @author Alyce Brady
 *  @version 1 September 2004
 **/

public class GridPlotterApp
{
    // Specify the dimensions of the grid display.
    private static final int DISPLAY_WIDTH = 400;    // in pixels
    private static final int DISPLAY_HEIGHT = 400;   // in pixels

    // Specify the minimum individual cell size.
    protected static final int MIN_CELL_SIZE = 10;   // in pixels

    // Specify constants used to initialize slider, which adjusts the speed
    // of the program.  The slider controls a delay or pause that is inserted
    // after displaying the grid to allow the user time to view it.
    // The longest delay value defines the slowest rate; the shortest delay
    // defines the fastest rate.  Both delay times are in milliseconds.
    private static final int LONGEST_DELAY_MSECS = 10;  // slowest
    private static final int SHORTEST_DELAY_MSECS = 1;  // fastest

    /**
     *  Starts the Grid Plotter program.
     *  The String arguments (args) are not used in this application.
     **/
    public static void main(String[] args)
    {
        // Construct a graphical user interface (GUI).  The GUI
        // provides buttons to run the traversal algorithms and
        // displays the results of each traversal by highlighting
        // the appropriate cells in the grid.
        GridPlotterGUI gui =
            new GridPlotterGUI(LONGEST_DELAY_MSECS, SHORTEST_DELAY_MSECS);

        gui.includeMenu(new BasicHelpMenu("GridPlotter", "Your Name Here",
                                "with assistance from (whom?)",
                                "version date", "file:GridPlotterHelp.html"));

        // Construct the visible window with the specified components.
        gui.constructWindowContents("Grid Plotter",
                                    DISPLAY_WIDTH, DISPLAY_HEIGHT,
                                    MIN_CELL_SIZE);
    }

}
