// Logan Isom
// COP 3503, Spring 2023
// NID lo854102
// RunLikeHell.Java

public class RunLikeHell
{
	public static int maxGain(int [] blocks)
	{
		// Starts with an 2 different starting blocks
		int path = blocks[0];
		int path2 = blocks[1];

		// Starts with the very 1st block and loops through to find best knowledge block
		for(int i = 2; i <= blocks.length - 1; i += 2)
		{
			// Compares the next 3 knowledge block after the skipped one
			// If the 3rd is better than the 2nd than adds 1st and 3rd
			if (i + 1 > blocks.length - 1)
			{
				path += blocks[i];
				continue;
			}
			// Checks to make sure block is within bounds
			if(i + 2 < blocks.length)
			{
				if(blocks[i+2] > blocks[i+1])
				{
					path += blocks[i] + blocks[i+2];
					i += 2;
					continue;
				}
			}
			if(blocks[i] > blocks[i+1])
			{
				path += blocks[i];
			}
			// Otherwise add the 2nd	
			else
			{
				path += blocks[i+1];
				i++;
			}
		}

		// Starts with the 2nd block and loops through to find best knowledge block
		for(int i = 3; i <= blocks.length - 1; i += 2)
		{
			// Compares the next 3 knowledge block after the skipped one
			// If the 3rd is better than the 2nd than adds 1st and 3rd
			if(i + 1 > blocks.length - 1)
			{
				path2 += blocks[i];
				continue;
			}
			// Checks to make sure block is within bounds
			// Then compares 2 blbocks
			if(i + 2 < blocks.length)
			{
				if(blocks[i+2] > blocks[i+1])
				{
					path2 += blocks[i] + blocks[i+2];
					i += 2;
					continue;
				}
			}
			if(blocks[i] > blocks[i+1])
			{
				path2 += blocks[i];
			}
			// Otherwise add the 2nd	
			else
			{
				path2 += blocks[i+1];
				i++;
			}
		}

		// Picks the better of the 2 paths
		if(path > path2)
			return path;
		return path2;
	}

	public static   double  difficultyRating()
	{
	    return  3.0;
	}

	public static   double  hoursSpent()
	{
	    return  8.0;
	}
}