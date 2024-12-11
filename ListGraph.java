import java.util.*;

public class ListGraph implements Graph {
    private HashMap<String, LinkedList<String>> nodes = new HashMap<>();

    public boolean addNode(String n) {
        if(nodes.containsKey(n)){
            return false;
        }
        nodes.put(n, new LinkedList<String>());
        return true;
    }

    public boolean addEdge(String n1, String n2) {
        if(!nodes.containsKey(n1) || !nodes.containsKey(n2)){
            throw new NoSuchElementException();
        }

        for(String n : nodes.get(n1)){
            if (n == n2){
                return false;
            }
        }
        nodes.get(n1).push(n2);
        return true;
    }

    public boolean hasNode(String n) {
        return nodes.containsKey(n);
    }

    public boolean hasEdge(String n1, String n2) {
	    if(!nodes.containsKey(n1) || !nodes.containsKey(n2)){
            return false;
        }
        
        for(String n : this.succ(n1)){
            if (n == n2){
                return true;
            }
        }
        return false;
    }

    public boolean removeNode(String n) {
	    if (!nodes.containsKey(n)){
            return false;
        }

        
        for (String s: this.succ(n)){
            // if (this.nodes())
            if(this.succ(s).size() == 0 && this.pred(n).size() == 1){
                this.removeNode(s);
            }
        }

        for (String s: this.pred(n)){
            // if (this.nodes())
            if(this.pred(s).size() == 0 && this.succ(n).size() == 1){
                this.removeNode(s);
            }
        }

        nodes.get(n).clear();
        nodes.remove(n);

        return true;
    }

    public boolean removeEdge(String n1, String n2) {
        if(!nodes.containsKey(n1) || !nodes.containsKey(n2)){
            throw new NoSuchElementException();
        }

        LinkedList<String> currList = nodes.get(n1); // get corresponding successor lists        
        Iterator<String> iterator = currList.iterator();

        boolean deleted = false;

        while (iterator.hasNext()){
            String currEdge = iterator.next();
            if(currEdge.equals(n2)){
                iterator.remove();
                deleted = true;
            }
        }

        return deleted;        
        
    }

    public List<String> nodes() {
	    List<String> allNodes = new ArrayList<>();

        for (String currNode : nodes.keySet()) {
            allNodes.add(currNode);
        }

        return allNodes;
    }

    public List<String> succ(String n) {
	    List<String> successors = new ArrayList<>();
        
        if (!nodes.containsKey(n)){
            throw new NoSuchElementException();
        }

        for(String curr : nodes.get(n)){
            successors.add(curr);
        }

        return successors;


    }

    public List<String> pred(String n) {
	    if (!nodes.containsKey(n)){
            throw new NoSuchElementException();
        }
        
        List<String> predeccesors = new ArrayList<>();

        for (String currNode : nodes.keySet()) { // iterate over key of HashMap
            LinkedList<String> currList = nodes.get(currNode); // get corresponding successor lists        
            Iterator<String> iterator = currList.iterator();
            while(iterator.hasNext()){
                String currEdge = iterator.next();
                if(currEdge.equals(n)){
                    predeccesors.add(currNode);
                }
            }
        }

        return predeccesors;
    }

    public Graph union(Graph g) {
	    // throw new UnsupportedOperationException();
        if(this.nodes() == null){
            return g;
        }
        if (g.nodes() == null){
            return this;
        }

        //add all nodes from g to this
        for (String n : g.nodes()) {
            this.addNode(n);
            
        }
        
        for (String s : g.nodes()) {
            for(String e : g.succ(s)){
                this.addEdge(s, e);
            }
            for(String x : g.pred(s)){
                this.addEdge(x, s);
            }
        }

        return this;
    }

    public Graph subGraph(Set<String> nodes) {
        for (String s : this.nodes()){
            if (!nodes.contains(s)){
                this.removeNode(s);
            }
        }

        return this;
    }

    public boolean connected(String n1, String n2) {
	    if(!nodes.containsKey(n1) || !nodes.containsKey(n2)){
            throw new NoSuchElementException();
        }

        if (n1.equals(n2)){
            return true;
        }

        Set<String> visited = new HashSet<>();
        return dfs(n1, n2, visited);
    }

    private boolean dfs(String current, String target, Set<String> visited) {
        if (current.equals(target)) {
            return true;
        }
        
        visited.add(current);

        List<String> neighbors = this.succ(current);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (!(visited.contains(neighbor)) && dfs(neighbor, target, visited)) {
                    return true;
                }
            }
        }
        

        return false;
    }
}
