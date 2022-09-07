package patterns.prototype

enum class Role {
    ADMIN,
    SUPER_ADMIN,
    REGULAR_USER
}

data class User(
    val name: String,
    val role: Role,
    val permissions: Set<String>,
    val tasks: List<String>,
) {
    fun hasPermission(permission: String) = permission in permissions
}

val allUsers = mutableListOf<User>()

fun createUser(name: String, role: Role) {
    for (u in allUsers) {
        if (u.role == role) {
            allUsers += User(name, role, u.permissions, u.tasks)
        }
    }
}


fun createUser_v2(name: String, role: Role) {
    for (u in allUsers) {
        if (u.role == role) {
            allUsers += u.copy(name = name, role = role)
        }
    }
}