package com.tvd12.designparttern.visitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public class Graph<K, V> {

	private final Map<K, Vertex<K, V>> vertexByKey;
	
	// adjacency matrix
	private final Map<Vertex<K, V>, List<Vertex<K, V>>> adjVertices;
	
	public Graph() {
		this.vertexByKey = new HashMap<>();
		this.adjVertices = new HashMap<>();
	}
	
	public void addVertex(K key, V value) {
		Vertex<K, V> vertex = new Vertex<>(key, value);
		vertexByKey.put(key, vertex);
		adjVertices.computeIfAbsent(vertex, k -> new LinkedList<>());
	}
	
	public void removeVertex(K key) {
		Vertex<K, V> vertex = vertexByKey.remove(key);
		if(vertex != null) {
			adjVertices.values().stream().forEach(e -> e.remove(vertex));
			adjVertices.remove(vertex);
		}
	}
	
	public void addEdge(K key1, K key2) {
		Vertex<K, V> v1 = vertexByKey.get(key1);
		Vertex<K, V> v2 = vertexByKey.get(key2);
		if(v1 != null && v2 != null) {
			addEdge(v1, v2);
		}
	}
	
	public void addEdge(Vertex<K, V> v1, Vertex<K, V> v2) {
	    adjVertices.computeIfAbsent(v1, k -> new LinkedList<>()).add(v2);
	    adjVertices.computeIfAbsent(v2, k -> new LinkedList<>()).add(v1);
	}
	
	public void removeEdge(K key1, K key2) {
	    Vertex<K, V> v1 = vertexByKey.get(key1);
	    Vertex<K, V> v2 = vertexByKey.get(key2);
	    if(v1 != null && v2 != null) {
		    List<Vertex<K, V>> eV1 = adjVertices.get(v1);
		    List<Vertex<K, V>> eV2 = adjVertices.get(v2);
		    if (eV1 != null)
		        eV1.remove(v2);
		    if (eV2 != null)
		        eV2.remove(v1);
	    }
	}
	
	public List<Vertex<K, V>> getAdjVertices(K key) {
		Vertex<K, V> vertex = vertexByKey.get(key);
		return vertex != null ? adjVertices.get(vertex) : null;
	}
	
	public void depthFirstTraversal(Visitor<Vertex<K, V>> visitor) {
		depthFirstTraversal(null, visitor);
	}
	
	public void depthFirstTraversal(K rootKey, Visitor<Vertex<K, V>> visitor) {
		Stack<Vertex<K, V>> stack = new Stack<>();
		Vertex<K, V> root = getRootByKey(rootKey);
		if(root != null)
			stack.add(root);
		Set<K> visited = new HashSet<>();
		while(stack.size() > 0) {
			Vertex<K, V> vertex = stack.pop();
			if(!visited.contains(vertex.key)) {
				visited.add(vertex.key);
				vertex.accept(visitor);
				for(Vertex<K, V> v : adjVertices.get(vertex)) {
					stack.push(v);
				}
			}
		}
	}
	
	public void breadthFirstTraversal(Visitor<Vertex<K, V>> visitor) {
		breadthFirstTraversal(null, visitor);
	}
	
	public void breadthFirstTraversal(K rootKey, Visitor<Vertex<K, V>> visitor) {
		Set<K> visited = new HashSet<>();
	    Queue<Vertex<K, V>> queue = new LinkedList<>();
	    Vertex<K, V> root = getRootByKey(rootKey);
		if(root != null) {
			queue.add(root);
			visited.add(root.key);
		}
	    while(queue.size() > 0) {
	    	Vertex<K, V> vertex = queue.poll();
	    	vertex.accept(visitor);
	    	for(Vertex<K, V> v : adjVertices.get(vertex)) {
	    		if(!visited.contains(v.key)) {
	    			visited.add(v.key);
	    			queue.offer(v);
	    		}
	    	}
	    }
	}
	
	private Vertex<K, V> getRootByKey(K rootKey) {
		Vertex<K, V> root = null;
		if(rootKey != null) {
			root = vertexByKey.get(rootKey);
		}
		else if(adjVertices.size() > 0) {
			root = adjVertices.keySet().iterator().next();
		}
		return root;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Vertex<K, V> vertex : adjVertices.keySet()) {
			builder.append(vertex).append(" => ").append(adjVertices.get(vertex));
			builder.append("\n");
		}
		return builder.toString();
	}
	
	@Getter
	@AllArgsConstructor
	@EqualsAndHashCode(of = "key")
	private static class Vertex<K,V> {
		private final K key;
		private final V value;
		
		void accept(Visitor<Vertex<K, V>> visitor) {
			visitor.visit(this);
		}
		
		@Override
		public String toString() {
			return "(" + key + ":" + value + ")";
		}
	}
	
	public static class PrintVisitor<K, V> implements Visitor<Vertex<K, V>> {
		@Override
		public void visit(Vertex<K, V> element) {
			System.out.print(element + " => ");
		}
	}
	
	@Getter
	public static class CacheVisitor<K, V> implements Visitor<Vertex<K, V>> {
		
		private final List<Vertex<K, V>> buffer = new LinkedList<>();
		
		@Override
		public void visit(Vertex<K, V> element) {
			buffer.add(element);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Graph<String, Integer> graph = new Graph<>();
	    graph.addVertex("Bob", 1);
	    graph.addVertex("Alice", 2);
	    graph.addVertex("Mark", 3);
	    graph.addVertex("Rob", 4);
	    graph.addVertex("Maria", 5);
	    graph.addEdge("Bob", "Alice");
	    graph.addEdge("Bob", "Rob");
	    graph.addEdge("Alice", "Mark");
	    graph.addEdge("Rob", "Mark");
	    graph.addEdge("Alice", "Maria");
	    graph.addEdge("Rob", "Maria");
	    System.out.println(graph);
	    
	    Visitor<Vertex<String, Integer>> printVisitor = new PrintVisitor<>();
	    graph.breadthFirstTraversal(printVisitor);
	    System.out.println();
	    
	    Visitor<Vertex<String, Integer>> cacheVisitor = new CacheVisitor<>();
	    graph.depthFirstTraversal(cacheVisitor);
	    System.out.println(((CacheVisitor)cacheVisitor).getBuffer());
	}
	
}
