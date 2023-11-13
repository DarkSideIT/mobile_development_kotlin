fun main(){
    a: float = readline()
    b: float = readline()
    m: float = readline()
    n: float = readline()


    if ((a >= m) && (b >= n)) || ((a >= n) && (b >= m)){
        println("YES")
    }
    else {
        println("NO")
    }
}