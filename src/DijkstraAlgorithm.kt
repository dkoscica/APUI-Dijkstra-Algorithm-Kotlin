import java.util.*
import models.*

class DijkstraAlgorithm(graph: Graph) {

    private val nodes: List<Vertex>
    private val edges: List<Edge>
    private var settledNodes: MutableSet<Vertex> = HashSet()
    private var unSettledNodes: MutableSet<Vertex> = HashSet()
    private var predecessors: MutableMap<Vertex, Vertex> = HashMap()
    private var distance: MutableMap<Vertex, Int> = HashMap()

    init {
        // create a copy of the array so that we can operate on this array
        this.nodes = ArrayList(graph.vertexes)
        this.edges = ArrayList(graph.edges)
    }

    fun execute(source: Vertex) {
        unSettledNodes.add(source)

        while (unSettledNodes.size > 0) {

            val node = getMinimum(unSettledNodes as HashSet<Vertex>)

            settledNodes.add(node as Vertex)
            unSettledNodes.remove(node)

            findMinimalDistances(node)
        }
    }

    private fun findMinimalDistances(node: Vertex) {
        val adjacentNodes = getNeighbors(node)
        for (target in adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target))
                predecessors.put(target, node)
                unSettledNodes.add(target)
            }
        }

    }

    private fun getDistance(node: Vertex, target: Vertex): Int {
        for ((id, source, destination, weight) in edges) {
            if (source == node && destination == target) {
                return weight
            }
        }
        throw RuntimeException("Should not happen")
    }

    private fun getNeighbors(node: Vertex): List<Vertex> {
        val neighbors = ArrayList<Vertex>()
        for ((id, source, destination) in edges) {
            if (source == node && !isSettled(destination)) {
                neighbors.add(destination)
            }
        }
        return neighbors
    }

    private fun getMinimum(vertexes: Set<Vertex>): Vertex? {
        var minimum: Vertex? = null
        for (vertex in vertexes) {
            if (minimum == null) {
                minimum = vertex
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex
                }
            }
        }
        return minimum
    }

    private fun isSettled(vertex: Vertex): Boolean {
        return settledNodes.contains(vertex)
    }

    private fun getShortestDistance(destination: Vertex): Int {
        val d = distance[destination]
        if (d == null) {
            return Integer.MAX_VALUE
        } else {
            return d
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    fun getPath(target: Vertex): LinkedList<Vertex>? {
        val path = LinkedList<Vertex>()
        var step: Vertex = target
        // check if a path exists
        if (predecessors[step] == null) {
            return null
        }
        path.add(step)
        while (predecessors[step] != null) {
            step = predecessors[step] as Vertex
            path.add(step)
        }
        // Put it into the correct order
        Collections.reverse(path)
        return path
    }

}

