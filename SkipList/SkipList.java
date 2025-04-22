// Logan Isom
// COP 3503, Spring 2023
// NID lo854102

import javax.swing.event.HyperlinkEvent;
import java.util.*;


class Node <T extends Comparable<T>>
{
    T data;
    int height;
    ArrayList<Node> arrayNodes;
    Node next;
    

    Node(int height)
    {
        // Initiates node values and sets array next references for a a newly created node
        Node node = new node();
        this.height = height;
        this.arrayNodes = new ArrayList<Node>();
        
        for(int i = 0; i < height; i++)
        {
            this.arrayNodes.get(i).next = null;
        }

    }

    Node(T data, int height)
    {
        // Initiates node values and sets array next references for a a newly created node
        // Also sets the value of the node
        Node node = new node();
        this.data = data;
        this.height = height;
        this.arrayNodes = height;
        
        for(int i = 0; i < height; i++)
        {
            this.arrayNodes.get(i).next = null;
        }

    }

    public T value()
    {
        return this.data;
    }

    public int height()
    {
        return this.height;
    }

    public Node<T> next(int level)
    {
        // If the next level is out of bounds of the nodes height return null
        // Otherwise return the next height level up
        if(level < 0 || level > this.height - 1)
            return null;

        return this.arrayNodes.get(level);
    }

    public void setNext(int level, Node<T> node)
    {
        // Set the next node at a specific height level
        this.arrayNodes.get(level).next = node;
    }

    public void grow()
    {
        // Grows the height of the node by 1
        this.height += 1;
        this.arrayNodes = height;
        this.arrayNodes.get(height).next = null;
    }

    public void maybeGrow()
    {
        return;
    }

    public void trim(int height)
    {
        return;
    }
}

public class SkipList <T extends Comparable<T>>
{
    Node head;
    SkipList()
    {
        this.head = new Node();
    }

    SkipList(int height)
    {
        this.head = new Node(height);
    }

    public int size()
    {
        int n = 0;
        while(this.head.next != null)
        {
            n++;
        }
        return n;
    }

    public int height()
    {
        return  head.height;
    }

    public Node<T> head()
    {
        return head;
    }

    public void insert(T data)
    {
        return;
    }

    public void insert(T data, int height)
    {
        return;
    }

    public void delete(T data)
    {
        return;
    }

    public boolean contains(T data)
    {
        return true;
    }

    public Node<T> get(T data)
    {
        return this.head;
    }

    private static int getMaxHeight(int n)
    {
        return n;
    }

    private static int generateRandomHeight(int maxHeight)
    {
        return maxHeight;

    }

    private void growSkipList()
    {

    }

    private void trimSkipList()
    {

    }

    public static double difficultyRating()
    {
        return 10.0;
    }

    public static double hoursSpent()
    {
        return 20.0;
    }
}
