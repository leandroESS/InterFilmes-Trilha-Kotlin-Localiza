package model.model

data class GenreModel (val name: String, val id: Int){
    override fun toString(): String {
        return name
    }
}