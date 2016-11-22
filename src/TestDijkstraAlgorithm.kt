import java.util.ArrayList

import org.junit.Test

import models.*

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

class TestDijkstraAlgorithm {

    private var nodes: MutableList<Vertex> = ArrayList()
    private var edges: MutableList<Edge> = ArrayList()

    @Test
    fun testExcute() {

        for (i in 0..10) {
            val location = Vertex("Node_" + i, "Node_" + i)
            nodes.add(location)
        }

        addLane("Edge_0", 0, 1, 85)
        addLane("Edge_1", 0, 2, 217)
        addLane("Edge_2", 0, 4, 173)
        addLane("Edge_3", 2, 6, 186)
        addLane("Edge_4", 2, 7, 103)
        addLane("Edge_5", 3, 7, 183)
        addLane("Edge_6", 5, 8, 250)
        addLane("Edge_7", 8, 9, 84)
        addLane("Edge_8", 7, 9, 167)
        addLane("Edge_9", 4, 9, 502)
        addLane("Edge_10", 9, 10, 40)
        addLane("Edge_11", 1, 10, 600)

        // Lets check from location Loc_1 to Loc_10
        val graph = Graph(nodes, edges)
        val dijkstra = DijkstraAlgorithm(graph)
        dijkstra.execute(nodes[0])
        val path = dijkstra.getPath(nodes[10])

        assertNotNull(path)
        assertTrue(path!!.size > 0)

        for (vertex in path) {
            println(vertex)
        }

    }

    private fun addLane(laneId: String, sourceLocNo: Int, destLocNo: Int,
                        duration: Int) {
        val lane = Edge(laneId, nodes[sourceLocNo], nodes[destLocNo], duration)
        edges.add(lane)
    }
}
