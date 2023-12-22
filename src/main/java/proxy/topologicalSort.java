package proxy;

import java.util.*;


//拓扑排序是在邻接表的基础上基于深度优先搜索实现的
public class topologicalSort {

    static class Graph {
        int vertices;
        List<List<Integer>> adjList;

        public Graph(int vertices) {
            //总节点数
            this.vertices = vertices;
            this.adjList = new ArrayList<>(vertices);
            //邻接表上每个节点使用链表记录其连接的节点
            for (int i = 0; i < vertices; i++) {
                this.adjList.add(new LinkedList<>());
            }
        }

        public void addEdge(int src, int dest) {
            adjList.get(src).add(dest);
        }
    }

    // 拓扑排序输出次序
    public static List<Integer> topologicalSortOutput(Graph graph) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = graph.vertices-1; i >=0; i--) {
            if (!visited[i]) {
                topologicalSortUtil(graph, i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private static void topologicalSortUtil(Graph graph, int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        for (int neighbor : graph.adjList.get(vertex)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(graph, neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public static void main(String[] args) {
        // 创建图
        int vertices = 6;
        Graph graph = new Graph(vertices);

        // 添加边
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        // 打印拓扑排序结果
        System.out.println("Topological Sort:");
        List<Integer> topologicalOrder = topologicalSortOutput(graph);
        System.out.println(topologicalOrder);

    }
}
