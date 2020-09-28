import java.io.*;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static final String FILEPATH = "L7g2.txt";

    public static void main(String[] args) {
        Graph graph = createGraphFromFile(FILEPATH);
        List<Stack<Integer>> components = graph.getComponents(graph.DepthFirstSearch());
        System.out.println("The graph: " + FILEPATH + " has " + components.size() + " strongly connected components");
        if (graph.getNodeTable().length < 100) {
            System.out.println("Component     Nodes in component");
            final String space = "              ";
            for (int i = 0; i < components.size(); i++) {
                //Added the substring bit to make to output string prettier
                System.out.println((i + 1) + space.substring(0,space.length()-String.valueOf(i+1).length()) + components.get(i).toString());
            }
        }

    }

    public static Graph createGraphFromFile(String filePath){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))))){
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int numberOfNodes = Integer.parseInt(stringTokenizer.nextToken());
            int numberOfEdges = Integer.parseInt(stringTokenizer.nextToken());
            Node[] nodeTable = new Node[numberOfNodes];
            for (int i = 0; i < numberOfEdges; i++){
                stringTokenizer = new StringTokenizer(br.readLine());
                int index = Integer.parseInt(stringTokenizer.nextToken());
                Node input = new Node(Integer.parseInt(stringTokenizer.nextToken()));
                if ((nodeTable[index] == null)) {
                    nodeTable[index] = input;
                } else {
                    nodeTable[index].getTail().setNext(input);
                }
            }
            return new Graph(numberOfEdges, nodeTable);
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
}
