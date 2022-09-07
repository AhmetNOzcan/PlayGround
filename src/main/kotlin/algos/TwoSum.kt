package algos

fun twoSum(nums: IntArray, target: Int): IntArray {
    val numsX = nums.copyOfRange(1, nums.size)
    nums.forEachIndexed { index1, i1 ->
        numsX.forEachIndexed { index2, i2 ->
             if (i2 + i1 == target) {
                return intArrayOf(index1, index2 + 1)
             }
        }
    }
    return intArrayOf(-1, -1)
}

fun main() {
    val x = twoSum(intArrayOf(3,3), 6)
    println("i1: ${x[0]}, i2: ${x[1]}")
}