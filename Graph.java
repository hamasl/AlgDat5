import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {
    private int numberOfEdges;
    private Node[] nodeTable;

    public Graph(int numberOfEdges, Node[] nodeTable) {
        this.numberOfEdges = numberOfEdges;
        this.nodeTable = nodeTable;
    }

    //Copy constructor to avoid references problems by using aggregation
    public Graph(Graph original) {
        this.nodeTable = new Node[original.nodeTable.length];
        for (int i = 0; i < original.nodeTable.length; i++) {
            if (original.nodeTable[i] != null) this.nodeTable[i] = new Node(original.nodeTable[i]);
        }
        this.numberOfEdges = original.numberOfEdges;
    }

    public Node[] getNodeTable() {
        return nodeTable;
    }

    public Graph getInvertedGraph() {
        Graph invertedGraph = new Graph(this.numberOfEdges, new Node[this.nodeTable.length]);
        for (int i = 0; i < invertedGraph.nodeTable.length; i++) {
            Node currentNode = this.nodeTable[i];
            while (currentNode != null) {
                invertedGraph.nodeTable[currentNode.getNUMBER()] = new Node(invertedGraph.nodeTable[currentNode.getNUMBER()], i);
                currentNode = currentNode.getNext();
            }
        }
        return invertedGraph;
    }

    public Stack<Integer> DepthFirstSearch() {
        //Using composition to avoid reference problems, by using a copy constructor
        Graph graph = new Graph(this);
        //Array that signifies if the node has been visited where the array index corresponds to the nodes number
        boolean[] visited = new boolean[graph.nodeTable.length];
        Stack<Integer> sortedStack = new Stack<>();
        for (int i = 0; i < graph.nodeTable.length; i++) {
            if (!visited[i]) depthFirstHelper(sortedStack, visited, graph, i);
        }
        return sortedStack;
    }

    public void depthFirstHelper(Stack<Integer> sortedStack, boolean[] visited, Graph graph, int currentNodeIndex) {
        int currentNodeNumber = currentNodeIndex;
        visited[currentNodeIndex] = true;
        Node nextNode = graph.nodeTable[currentNodeIndex];
        while (nextNode != null) {
            currentNodeIndex = nextNode.getNUMBER();
            if (!visited[currentNodeIndex]) {
                depthFirstHelper(sortedStack, visited, graph, currentNodeIndex);
            }
            if (nextNode != null) nextNode = nextNode.getNext();
        }
        sortedStack.push(currentNodeNumber);
    }

    public List<Stack<Integer>> getComponents(Stack<Integer> sortedStack) {
        List<Stack<Integer>> components = new ArrayList<>();
        Graph invertedGraph = getInvertedGraph();
        boolean[] visited = new boolean[invertedGraph.getNodeTable().length];
        while (!sortedStack.isEmpty()) {
            Stack<Integer> component = new Stack<>();
            int currentNodeIndex = sortedStack.pop();
            if (!visited[currentNodeIndex]) {
                invertedGraph.depthFirstHelper(component, visited, invertedGraph, currentNodeIndex);
            }
            if (component.size() > 0) components.add(component);
        }
        return components;
    }

}
