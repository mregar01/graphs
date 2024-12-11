import java.util.*;

public class EdgeGraphAdapter implements EdgeGraph {

    private Graph g;

    public EdgeGraphAdapter(Graph g) { this.g = g; }

    public boolean addEdge(Edge e) {
      g.addNode(e.getSrc());
      g.addNode(e.getDst());
      return g.addEdge(e.getSrc(), e.getDst());
    }

    public boolean hasNode(String n) {
	    return g.hasNode(n);
    }

    public boolean hasEdge(Edge e) {
	    return (g.hasEdge(e.getSrc(), e.getDst()));
    }

    public boolean removeEdge(Edge e) {
	    
      boolean value = g.removeEdge(e.getSrc(), e.getDst());

      if(g.pred(e.getSrc()).isEmpty() && g.succ(e.getSrc()).isEmpty()){
        g.removeNode(e.getSrc());
      }

      if(g.pred(e.getDst()).size() == 0 && g.succ(e.getDst()).size() == 0){
        g.removeNode(e.getDst());
      }

      return value;

    }

    //all edges that start at n
    public List<Edge> outEdges(String n) {
      List<Edge> edges = new ArrayList<>();

      if (!g.hasNode(n)){
        return edges;
      }

      for (String s : g.succ(n)) {
        Edge currEdge = new Edge(n, s);
        edges.add(currEdge);
      }

      return edges;
    }

    public List<Edge> inEdges(String n) {
      
      List<Edge> edges = new ArrayList<>();

      if (!g.hasNode(n)){
        return edges;
      }

      for (String s : g.pred(n)) {
        Edge currEdge = new Edge(s, n);
        edges.add(currEdge);
      }

      return edges;
    }

    public List<Edge> edges() {
      List<Edge> edges = new ArrayList<>();

      for(String n : g.nodes()){
        for(String e : g.succ(n)){
          Edge currEdge = new Edge(n, e);
          edges.add(currEdge);
        }
      }

      return edges;
    }

    public EdgeGraph union(EdgeGraph g) {
      if(this.edges() == null){
        return g;
      }
      if (g.edges() == null){
          return this;
      }
      
      for (Edge e : g.edges()){
        this.addEdge(e);
      }

      return this;
    }

    public boolean hasPath(List<Edge> e) {
	    if(e.isEmpty()){
        return true;
      }

      for (Edge currEdge : e){
        if (!this.hasNode(currEdge.getDst()) || !this.hasNode(currEdge.getSrc())){
          return false;
        }
      }

      for (int i = 0; i < e.size() - 1; i++){
        if(e.get(i).getDst() != e.get(i+1).getSrc()){
          throw new BadPath();
        }
      }

      return true;
    }

}
