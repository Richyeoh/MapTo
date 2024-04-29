import com.copite.kotlin.m2.annotations.NoArgs
import com.copite.kotlin.m2.mapTo

@NoArgs
data class Source(val name: String, val age: Int, val properties: Map<String, String>? = null)

@NoArgs
data class Target(val name: String, val age: String, val properties: String? = null)

fun main() {
    testJavaConvert()
    println("========================Kotlin========================")
    testKotlinConvert()
}

private fun testJavaConvert() {
    val source = Source("ak47", 18, mapOf("aaa" to "111", "bbb" to "222"))
    println("cvt1=$source")
    val target = source.mapTo(Target::class.java)
    println("cvt2=$target")
    val finally = target.mapTo(Source::class.java)
    println("cvt3=$finally")
}

private fun testKotlinConvert() {
    val source = Source("ak47", 18, mapOf("aaa" to "111", "bbb" to "222"))
    println("cvt1=$source")
    val target = source.mapTo(Target::class)
    println("cvt2=$target")
    val finally = target.mapTo(Source::class)
    println("cvt3=$finally")
}
