// Sean Szumlanski
// COP 3503, Spring 2023

// ==========================
// TopoPaths: TestCase04.java
// ==========================
// This corresponds to graph G2 from the assignment PDF.


import java.io.*;

public class TestCase04
{
	public static void main(String [] args) throws IOException
	{
		System.out.print("Test Case #4: ");

		boolean success = (TopoPaths.countTopoPaths("input_files/TestCase04-graph.txt") == 0);
		System.out.println(success ? "PASS" : "fail whale :(");
	}
}
