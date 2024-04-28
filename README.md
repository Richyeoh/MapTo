# MapTo

`MapTo is a kotlin tools, Convert a class to another class`

## Todo List
- [ ] Support annotation mark
- [ ] Support alias fields mapping
- [x] Support different type mapping

## Examples

```kotlin
class Source(val id:Int, val name:String)
class Target(val id:Int, val name:String)

fun main(){
  val source = Source(0, "Mapper")
  val target:Target = source.mapTo()
}  
```
