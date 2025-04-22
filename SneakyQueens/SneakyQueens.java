// Logan Isom
// COP 3503 Spring 2023
// lo854102
import java.util.ArrayList;

public class SneakyQueens
{
    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        int[] diagonalCheckPosotive = new int[coordinateStrings.size()];
        int[] diagonalCheckNegative = new int[coordinateStrings.size()];
        // Use a nested for loop to loop through the ArrayList comparing the rows and collums of the current i and j positions respectively.
        // Make sure i loop is size  of array list -1 and j loop is the full size of the array list with the compaare action being j + 1
        for (int i = 0; i < coordinateStrings.size(); i++)
        {
            // Split up the current coordinate into column and row as strings.
            String strColumn = coordinateStrings.get(i).replaceAll("[a-z]", "");
            String strRow = coordinateStrings.get(i).replaceAll("[0-9]","");
            char[] charRow = strRow.toCharArray();

            // Convert the Strings into ints
            int height = 0;
            int width = 0;
            
            height = Integer.parseInt(strColumn);
            
            for (int j = 0; j < strRow.length(); j++)
            {
                width += charRow[j] - 'a';
            }
            // Placing the diagonal positions in an array from bottom left to top right direction.
            // Placing the diagonal positions in an array from top left to bottom right direction.
            // Take the height - width of the coordinate to
            diagonalCheckPosotive[i] = height - width;
            diagonalCheckNegative[i] = height + width;
            
            for (int j = i + 1; j < coordinateStrings.size(); j++)
            {
                String strColumnCurr = coordinateStrings.get(j).replaceAll("[a-z]", "");
                String strRowCurr = coordinateStrings.get(j).replaceAll("[0-9]","");
                // Row Check
                if (strRowCurr == strRow)
                {
                    return false;
                }  

                // Column check
                if (strColumnCurr == strColumn)
                {
                    return false;
                }   
            }
            for (int j = i - 1; j >= 0; j--)
            {
                if (i != 0 || i != coordinateStrings.size())
                {
                    // Diagonal Check from bottom left corner to top right corner
                    if (diagonalCheckPosotive[i] == diagonalCheckPosotive[j])
                    {
                        return false;
                    }
                    // Diagonal check from top left corner to bottomr right corner
                    if (diagonalCheckNegative[i] == diagonalCheckNegative[j])
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
        return 3.0;
    }

    public static double hoursSpent()
    {
        return 10.0;
    }
}
