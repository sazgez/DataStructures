package Graph;

import java.util.Stack;
import Queues.CircularQueue.*;

class Graph
{
    private final int MAX_VERTS = 20;
    private Vertex vertexList[]; // array of vertices
    private int adjMat [ ][ ]; // adjacency matrix
    private int nVerts; // current number of vertices

    public Graph() // constructor
    {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int j=0; j<MAX_VERTS; j++) // set adjacency
            for(int k=0; k<MAX_VERTS; k++) // matrix to 0
                adjMat[j][k] = 0;
    } // end constructor

    public void addVertex(char lab) // argument is label
    {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end)
    {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int v)
    {
        System.out.print(vertexList[v].label);
    }

    public int getAdjUnvisitedVertex(int v)
    {
        for(int j=0; j<nVerts; j++)
            if (adjMat[v][j]==1 && vertexList[j].wasVisited==false)
                return j; // return first such vertex
        return -1; // no such vertices
    } // end getAdjUnvisitedVertex()

    public void dfs() // depth-first search
    { // begin at vertex 0
        Stack<Integer> theStack = new Stack<Integer>();
        vertexList[0].wasVisited = true; // mark it
        displayVertex(0); // display it
        theStack.push(0); // push it
        while( ! theStack.isEmpty() ) // until stack empty,
        { // get an unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex( theStack.peek() );
            if(v == -1) // if no such vertex,
                theStack.pop(); // pop a new one
            else // if it exists,
            {
                vertexList[v].wasVisited = true; // mark it
                displayVertex(v); // display it
                theStack.push(v); // push it
            }
        } // end while
        // stack is empty, so we’re done
        for(int j=0; j<nVerts; j++) // reset flags
            vertexList[j].wasVisited = false;
    } // end dfs

    public void bfs() // breadth-first search
    { // begin at vertex 0
        Queue theQueue = new Queue(MAX_VERTS); // self-created Queue class
        vertexList[0].wasVisited = true; // mark it
        displayVertex(0); // display it
        theQueue.insert(0); // insert at tail
        int v2;
        while( !theQueue.isEmpty() ) // until queue empty,
        {
            int v1 = theQueue.remove(); // remove vertex at head
            // until it has no unvisited neighbors
            while( (v2 = getAdjUnvisitedVertex(v1)) != -1 )
            { // get one,
                vertexList[v2].wasVisited = true; // mark it
                displayVertex(v2); // display it
                theQueue.insert(v2); // insert it
            } // end while(unvisited neighbors)
        } // end while(queue not empty)
        // queue is empty, so we’re done
        for(int j=0; j<nVerts; j++) // reset flags
            vertexList[j].wasVisited = false;
    } // end bfs()

    public void mst() // minimum spanning tree (depth first)
    { // start at 0
        Stack<Integer> theStack = new Stack<Integer>();
        vertexList[0]. wasVisited = true; // mark it
        theStack.push(0); // push it
        while( !theStack.isEmpty() ) // until stack empty
        { // get stack top
            int currentVertex = theStack.peek();
            // get next unvisited neighbor
            int v = getAdjUnvisitedVertex(currentVertex);
            if(v == -1) // if no more neighbors
                theStack.pop(); // pop it away
            else // got a neighbor
            {
                vertexList[v].wasVisited = true; // mark it
                theStack.push(v); // push it
                // display edge
                displayVertex(currentVertex); // from currentV
                displayVertex(v); // to v
                System.out.print(" ");
            }
        } // end while(stack not empty)
        // stack is empty, so we’re done
        for(int j=0; j<nVerts; j++) // reset flags
            vertexList[j].wasVisited = false;
    } // end mst()
} // end class Graph