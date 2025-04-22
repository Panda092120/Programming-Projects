// Logan Isom
// COP 3503, Spring 2023

// ====================
// GenericBST: BST.java
// ====================
// Basic binary search tree (BST) implementation that supports insert() and
// delete() operations. This framework is provided for you to modify as part of
// Programming Assignment #2.

import java.io.*;
import java.util.*;

class Node <T extends Comparable<T>>
{
	T data;
	Node<T> left, right;

	Node(T data)
	{
		this.data = data;
	}
}

public class GenericBST <T extends Comparable<T>>
{
	private Node<T> root;

	// Calls the insert method to create a Node in a sorted order
	// with T Data inside.
	public void insert(T data)
	{
		root = insert(root, data);
	}

	// Inserts a Node with T data in the correct sorted order.
	private Node<T> insert(Node<T> root, T data)
	{
		// If we have reached an empty spot in the BST than create the node
		// with the data inputted.
		if (root == null)
		{
			return new Node<T>(data);
		}
		// If the current Node is larger than the data we are trying to insert
		// Move further down the left smaaller side of the BST.
		else if (root.data.compareTo(data) > 0)
		{
			root.left = insert(root.left, data);
		}
		// Otherwise if the current Node is smaller than the data trying to be inserted
		// Move further down the larger right side of the BST.
		else if (root.data.compareTo(data) < 0)
		{
			root.right = insert(root.right, data);
		}

		return root;
	}

	// Takes in a generic data type and uses it to find that data in the bst and delete that node.
	public void delete(T data)
	{
		root = delete(root, data);
	}
	// Searches the nodes for the given data. Returns Null if the node doesn't exist.
	private Node<T> delete(Node<T> root, T data)
	{
		// Null Check
		if (root == null)
		{
			return null;
		}
		// If the current Nodes data is larger than the Node we are looking for than
		// we go further into the smaller left side of the BST.
		else if (root.data.compareTo(data) > 0)
		{
			root.left = delete(root.left, data);
		}
		// If the current Nodes data is smaller than the Node we are looking for than
		// we go further into the larger right side of the BST.
		else if (root.data.compareTo(data) < 0)
		{
			root.right = delete(root.right, data);
		}
		// Otherwise either we are at the end of the BST or we have found the desired Node to delete.
		else
		{
			// We have found a leaf at the end of the BST without finding the desire Node.
			if (root.left == null && root.right == null)
			{  
				return null;
			}
			// If only the smaller left side is Null than return the right
			else if (root.left == null)
			{
				return root.right;
			}
			// Otherwise the larger right side is null return the left
			else if (root.right == null)
			{
				return root.left;
			}
			// Else set the current roots data to the largest node
			// Also delete the next smaller root and replace it with NULL.
			else
			{
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}

		return root;
	}

	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is not empty.
	private T findMax(Node<T> root)
	{
		while (root.right != null)
		{
			root = root.right;
		}

		return root.data;
	}

	// Calls the method that looks into the BST to find if the data inputted is within the BST.
	public boolean contains(T data)
	{
		return contains(root, data);
	}

	// Checks if the inputted data is within a Node in the BST.
	private boolean contains(Node<T> root, T data)
	{
		// Null check if empty return false
		if (root == null)
		{
			return false;
		}
		// Else if the data in the current node is larger than the inputted data
		// then move further down the left smaller side of the BST to see if the data is present.
		else if (root.data.compareTo(data) > 0)
		{
			return contains(root.left, data);
		}
		// Else if the data in the current node is smaller than the inputted data
		// then move further down the right smaller side of the BST to see if data is present.
		else if (root.data.compareTo(data) < 0)
		{
			return contains(root.right, data);
		}
		else
		// If neither of the previous 2 statements are true than the data is in the current Node
		// return true
		{
			return true;
		}
	}

	// Calls inorder method passing in root of class
	public void inorder()
	{
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}

	// Prints inorder traversel of BST using recursive calls of the function
	private void inorder(Node root)
	{
		// If null then at the end of a branch
		if (root == null)
			return;

		// Goes to the furthest left of the BST and prints that before
		// Going to the right side of each leaf after.
		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}

	// Calls the preorder traversal method with the root
	public void preorder()
	{
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}

	// Recursively prints the preorder traversal from the root
	private void preorder(Node root)
	{
		// Null check
		if (root == null)
			return;

		//Prints at the top to print from the top down
		// Starts from the left side and prints the entire left side
		// Then moves to the right side and prints that entire side
		// Does this for each node.
		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}

	// Calls the postorder traversal method with the root
	public void postorder()
	{
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}
	
	// Recursively prints the preorder traversal from the root
	private void postorder(Node root)
	{
		// Null check
		if (root == null)
			return;

		// Starts from the left side and prints the entire left side
		// Then moves to the right side and prints that entire side
		// Does this for each node and then prints at the end of the function to start from the bottom up
		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}
	
    public static double difficultyRating()
    {
        return 2.0;
    }

    public static double hoursSpent()
    {
        return 9.0;
    }
}
