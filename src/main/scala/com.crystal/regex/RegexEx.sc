val theUltimateAnswer: String = "To life, the universe, and everything is 42."
val pattern = """.* ([\d]+).*""".r
val pattern(answerString) = theUltimateAnswer
val answer = answerString.toInt;

val picard: String = "picard"
val bestCaptain: String = "Picards"
val mach = picard == bestCaptain

//EXERCISE
// write some code that takes the value of pi, doubles it, and then prints it
// with a three decimal places if precision to the right

val pi = 14.145145444752142;
println(f"double pi: ${pi * 2}%.4f")

//Fibonacci sequence
//write some code that prints out 10 values of Fibonacci sequence.
// This is the seq where every number is sum of two numbers before it.
// so, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34

var num = 0;
var num1 = 1;
var num2 = 0;
for (i <- 1 to 8) {
  if (i == 1) {
    println(num)
    println(num1)
  }
  num2 = num1 + num
  num = num1
  num1 = num2
  println(num2)
}

val testList: List[Matchable] = List(5, "gn", 54.55)

for(i <- 1 to 20){
  if(i%3==0)
    println(i)
}


