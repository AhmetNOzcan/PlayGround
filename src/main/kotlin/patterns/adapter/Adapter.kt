package patterns.adapter

interface USPlug {
    val hasPower: Int
}

interface EUPlug {
    val hasPower: String
}

interface UsbMini {
    val hasPower: Power
}

interface UsbTypeC {
    val hasPower: Boolean
}

enum class Power {
    TRUE, FALSE
}

fun cellPhone(chargeCable: UsbTypeC) {
    if (chargeCable.hasPower) {
        println("I've Got The Power!")
    } else {
      println("No power")
    }
}

fun usPowerOutlet(): USPlug {
    return object : USPlug {
        override val hasPower = 1
    }
}

fun charger(plug: EUPlug): UsbMini {
    return object : UsbMini {
        override val hasPower = Power.valueOf(plug.hasPower)
    }
}

fun main() {

}