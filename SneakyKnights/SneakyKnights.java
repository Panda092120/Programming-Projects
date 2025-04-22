// Logan Isom
// COP 3503, Spring 2023
// NID lo854102

import java.lang.Math;
import java.util.ArrayList;
import java.util.*;

public class SneakyKnights
{

    public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        for(int i = 0; i < coordinateStrings.size(); i++)
        {
            // Split up the current coordinate into column and row as strings.
            String strColumn = coordinateStrings.get(i).replaceAll("[a-z]", "");
            String strRow = coordinateStrings.get(i).replaceAll("[0-9]","");
            char[] charRow = strRow.toCharArray();
            
            // Convert the Strings into ints
            int height = 0;
            int width = 0;
            
            height = Integer.parseInt(strColumn);
            
            for (int j = 0; j < charRow.length; j++)
            {
                width += charRow[j] - 'a';
            }

            for (int j = i - 1; j >= 0; j--)
            {
                if (i != 0 || i != coordinateStrings.size())
                {
                    // Split up the current coordinate into column and row as strings.
                    String strColumn2 = coordinateStrings.get(j).replaceAll("[a-z]", "");
                    String strRow2 = coordinateStrings.get(j).replaceAll("[0-9]","");
                    char[] charRow2 = strRow2.toCharArray();
            
                    // Convert the Strings into ints
                    int height2 = 0;
                    int width2 = 0;
            
                    height2 = Integer.parseInt(strColumn2);
            
                    for (int k = 0; k < charRow2.length; k++)
                    {
                        width2 += charRow2[k] - 'a';
                    }

                    // Checks if the difference between the 2 coordinates is ever (2, 1) or (1,2)
                    // If so then they are in jumping range
                    if(Math.abs(height - height2) == 2 && Math.abs(width - width2) == 1 ||
                        Math.abs(height - height2) == 1 && Math.abs(width - width2) == 2)
                        {
                            return false;
                        }
                }
            }
        }
        return true;
    }

    public static double difficultyRating()
    {
        return 4.0;
    }

    public static double hoursSpent()
    {
        return 15.0;
    }
}