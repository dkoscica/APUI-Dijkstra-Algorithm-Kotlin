package models

data class Vertex(val id: String?, val name: String) {

    override fun toString(): String {
        return name
    }

}
