package models

data class Edge(val id: String, val source: Vertex, val destination: Vertex, val weight: Int) {

    override fun toString(): String {
        return source.toString() + " " + destination
    }

}
