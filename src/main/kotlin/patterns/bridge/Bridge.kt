package patterns.bridge

/**
 * Not completed
 */
interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)
}

open class StormTrooper: Trooper {
    override fun move(x: Long, y: Long) {

    }

    override fun attackRebel(x: Long, y: Long) {

    }
}

open class ShockTrooper: Trooper {
    override fun move(x: Long, y: Long) {

    }

    override fun attackRebel(x: Long, y: Long) {

    }
}

class RiotControlTrooper: StormTrooper() {
    override fun attackRebel(x: Long, y: Long) {

    }
}

class FlameTrooper: ShockTrooper() {
    override fun attackRebel(x: Long, y: Long) {

    }
}

class ScoutTrooper: ShockTrooper() {
    override fun move(x: Long, y: Long) {

    }
}