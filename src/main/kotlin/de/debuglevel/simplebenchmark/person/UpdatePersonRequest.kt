package de.debuglevel.simplebenchmark.person

data class UpdatePersonRequest(
    val name: String,
) {
    fun toPerson(): Person {
        return Person(
            id = null,
            name = this.name,
        )
    }
}