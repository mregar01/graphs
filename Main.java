import java.util.*;

public class Main {

    // Run "java -ea Main" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)
    
    public static void add_node() {
			Graph g = new ListGraph();
			assert g.addNode("a");
			assert g.hasNode("a");
    }

		public static void add_all() {
			Graph g = new ListGraph();
			assert g.addNode("a");
			assert g.addNode("b");
			assert g.addEdge("a", "b");
			assert g.hasEdge("a", "b");
			assert !g.addNode("a");
			assert !g.addNode("b");
			assert !g.addEdge("a", "b");
		}

		public static void remove_node() {
			Graph g = new ListGraph();
			assert g.addNode("a");
			assert g.addNode("b");
			assert g.addEdge("a", "b");
			assert g.hasEdge("a", "b");
			assert !g.addNode("a");
			assert !g.addNode("b");
			assert !g.addEdge("a", "b");
			assert g.removeNode("a");
			assert !g.hasNode("a");
		}

		public static void remove_edge() {
			Graph g = new ListGraph();
			assert g.addNode("a");
			assert g.addNode("b");
			assert g.addEdge("a", "b");
			assert g.hasEdge("a", "b");
			assert !g.addNode("a");
			assert !g.addNode("b");
			assert !g.addEdge("a", "b");
			assert g.removeEdge("a", "b");
			assert !g.hasEdge("a", "b");
		}

		public static void all_nodes(){
			Graph g = new ListGraph();
			assert g.addNode("a");
			assert g.addNode("b");
			assert g.addEdge("a", "b");
			assert g.addNode("c");
			assert g.addNode("d");
			assert g.nodes().size() == 4;
			assert g.nodes().contains("a");
			assert g.nodes().contains("b");
			assert g.nodes().contains("c");
			assert g.nodes().contains("d");
		}

		public static void successors(){
			Graph g = new ListGraph();
			assert g.addNode("a");
			assert g.addNode("b");
			assert g.addEdge("a", "b");
			assert g.addNode("c");
			assert g.addNode("d");
			assert g.addEdge("a", "c");
			assert g.addEdge("a", "d");
			assert g.succ("a").size() == 3;
			assert g.succ("a").contains("b");
			assert g.succ("a").contains("c");
			assert g.succ("a").contains("d");
		}

		public static void predeccesors(){
			Graph g = new ListGraph();
			assert g.addNode("a");
			assert g.addNode("b");
			assert g.addEdge("a", "b");
			assert g.addNode("c");
			assert g.addNode("d");
			assert g.addEdge("c", "b");
			assert g.addEdge("d", "b");
			assert g.addEdge("b", "b");
			assert g.pred("b").size() == 4;
			assert g.pred("b").contains("a");
			assert g.pred("b").contains("b");
			assert g.pred("b").contains("c");
			assert g.pred("b").contains("d");
		}

		public static void union(){
			Graph g = new ListGraph();
			Graph g1 = new ListGraph();
			assert g.addNode("a");
			assert g.addNode("b");
			assert g.addEdge("a", "b");
			assert g.addNode("c");
			assert g.addNode("d");
			assert g.addEdge("c", "b");
			assert g.addEdge("d", "b");
			assert g.addEdge("b", "b");
			assert g1.addNode("e");
			assert g1.addNode("f");
			assert g1.addNode("g");
			assert g1.addNode("h");
			assert g1.addEdge("e","f");
			assert g1.addEdge("e","g");
			assert g1.addEdge("e","h");
			assert g1.addEdge("g","h");
			g.union(g1);
			assert g.nodes().size() == 8;
			assert g.nodes().contains("a");
			assert g.nodes().contains("b");
			assert g.nodes().contains("c");
			assert g.nodes().contains("d");
			assert g.nodes().contains("e");
			assert g.nodes().contains("f");
			assert g.nodes().contains("g");
			assert g.nodes().contains("h");
			assert g.hasEdge("a", "b");
			assert g.hasEdge("c", "b");
			assert g.hasEdge("d", "b");
			assert g.hasEdge("b", "b");
			assert g.hasEdge("e", "f");
			assert g.hasEdge("e", "g");
			assert g.hasEdge("e", "h");
			assert g.hasEdge("g", "h");
		}

		public static void subgraph(){
			Graph g = new ListGraph();
			assert g.addNode("a");
			assert g.addNode("b");
			assert g.addEdge("a", "b");
			assert g.addNode("c");
			assert g.addNode("d");
			assert g.addEdge("c", "b");
			assert g.addEdge("d", "b");
			assert g.addEdge("b", "b");
			
			Set<String> nodes = new HashSet<>();
			nodes.add("a");
			nodes.add("b");
			g.subGraph(nodes);
			assert g.nodes().size() == 2;
			

		}

		// edge graph tests

		public static void eg_add_edge() {
			Graph g = new ListGraph();
			EdgeGraph eg = new EdgeGraphAdapter(g);
			Edge e = new Edge("a", "b");	
			assert eg.addEdge(e);
			assert eg.hasNode("a");
			assert eg.hasNode("b");
    }

		public static void eg_remove_edge() {
			Graph g = new ListGraph();
			EdgeGraph eg = new EdgeGraphAdapter(g);
			Edge e = new Edge("a", "b");	
			assert eg.addEdge(e);
			assert eg.hasNode("a");
			assert eg.hasNode("b");
			assert eg.removeEdge(e);
			assert !eg.hasNode("a");
			assert !eg.hasNode("b");
    }

		public static void eg_out_edges() {
			Graph g = new ListGraph();
			EdgeGraph eg = new EdgeGraphAdapter(g);
			Edge e = new Edge("a", "b");	
			Edge e1 = new Edge("a", "c");
			assert eg.addEdge(e);
			assert eg.addEdge(e1);
			assert eg.hasNode("a");
			assert eg.hasNode("b");
			assert eg.hasNode("c");			
			assert eg.inEdges("z").size() == 0;
			assert eg.outEdges("a").contains(e);
			assert eg.outEdges("a").contains(e1);
			assert eg.outEdges("a").size() == 2;
    }

		public static void eg_in_edges() {
			Graph g = new ListGraph();
			EdgeGraph eg = new EdgeGraphAdapter(g);
			Edge e = new Edge("a", "b");	
			Edge e1 = new Edge("a", "c");
			Edge e2 = new Edge("s", "c");
			assert eg.addEdge(e);
			assert eg.addEdge(e1);
			assert eg.addEdge(e2);
			assert eg.hasNode("a");
			assert eg.hasNode("b");
			assert eg.hasNode("c");
			assert eg.hasNode("s");
			assert eg.inEdges("z").size() == 0;
			assert eg.inEdges("a").size() == 0;
			assert eg.inEdges("c").size() == 2;
			assert eg.inEdges("c").contains(e1);
			assert eg.inEdges("c").contains(e2);
    }

		public static void eg_edges() {
			Graph g = new ListGraph();
			EdgeGraph eg = new EdgeGraphAdapter(g);
			Edge e = new Edge("a", "b");	
			Edge e1 = new Edge("a", "c");
			Edge e2 = new Edge("s", "c");
			assert eg.addEdge(e);
			assert eg.addEdge(e1);
			assert eg.addEdge(e2);
			assert eg.hasNode("a");
			assert eg.hasNode("b");
			assert eg.hasNode("c");
			assert eg.hasNode("s");
			assert eg.edges().size() == 3;
			assert eg.edges().contains(e);
			assert eg.edges().contains(e1);
			assert eg.edges().contains(e2);
		}

		public static void eg_union() {
			Graph g = new ListGraph();
			Graph g1 = new ListGraph();
			EdgeGraph eg = new EdgeGraphAdapter(g);
			Edge e = new Edge("a", "b");	
			Edge e1 = new Edge("a", "c");
			Edge e2 = new Edge("s", "c");
			Edge e3 = new Edge("x", "y");
			assert eg.addEdge(e);
			assert eg.addEdge(e1);
			assert eg.addEdge(e2);
			assert g1.addNode("x");
			assert g1.addNode("y");
			// assert g1.addEdge("x", "y");
			EdgeGraph eg1 = new EdgeGraphAdapter(g1);
			assert eg1.addEdge(e3);
			eg.union(eg1);
			assert eg.hasNode("a");
			assert eg.hasNode("b");
			assert eg.hasNode("c");
			assert eg.hasNode("s");
			assert eg.hasNode("x");
			assert eg.hasNode("y");
			assert eg.edges().contains(e1);
			assert eg.edges().contains(e2);
			assert eg.edges().contains(e3);
		}

		public static void eg_has_path() {
			Graph g = new ListGraph();
			EdgeGraph eg = new EdgeGraphAdapter(g);
			Edge e = new Edge("a", "b");	
			Edge e1 = new Edge("b", "c");
			Edge e2 = new Edge("c", "d");
			assert eg.addEdge(e);
			assert eg.addEdge(e1);
			assert eg.addEdge(e2);
			List<Edge> edges = new ArrayList<>();
			edges.add(e);
			edges.add(e1);
			edges.add(e2);
			assert eg.hasPath(edges);
		}
		public static void eg_no_has_path() {
			Graph g = new ListGraph();
			EdgeGraph eg = new EdgeGraphAdapter(g);
			Edge e = new Edge("a", "b");	
			Edge e1 = new Edge("b", "c");
			Edge e2 = new Edge("c", "d");
			Edge e3 = new Edge("d", "a");
			assert eg.addEdge(e);
			assert eg.addEdge(e1);
			assert eg.addEdge(e2);
			List<Edge> edges = new ArrayList<>();
			edges.add(e);
			edges.add(e1);
			edges.add(e3);
			try {
				eg.hasPath(edges);
			} catch (Exception x) {
				System.err.println(x);
			}
		}

		public static void new_test(){
			Graph g = new ListGraph();
			g.addNode("a");
			g.addNode("b");
			g.addNode("c");
			g.addNode("d");
			g.addEdge("a", "b");
			g.addEdge("b", "c");
			g.addEdge("c", "d");
			assert g.removeNode("b");
			assert g.nodes().size() == 2;
			assert g.nodes().contains("c");
			assert g.nodes().contains("d");
		}

		public static void has_path(){
			Graph g = new ListGraph();
			g.addNode("a");
			g.addNode("b");
			g.addNode("c");
			g.addNode("d");
			g.addEdge("a", "b");
			g.addEdge("b", "c");
			g.addEdge("c", "d");
			assert g.connected("a", "d");
		}
    
    public static void main(String[] args) {
			add_node();
			add_all();
			remove_node();
			remove_edge();
			all_nodes();
			successors();
			predeccesors();
			subgraph();
			eg_add_edge();
			eg_remove_edge();
			eg_out_edges();
			eg_in_edges();
			eg_edges();
			union();
			eg_union();
			eg_has_path();
			eg_no_has_path();
			new_test();
			has_path();
			System.out.println("Tests Passed!");
    }

}