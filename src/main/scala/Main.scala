import scala.annotation.tailrec
import scala.collection.mutable

/**
 * If superman is able and willing to stop evil, he would do it.
 * If superman is not able to stop evil, he is unable.
 * If superman is not willing to stop evil, he is evil.
 * Superman does not stop evil.
 * If superman exists, he is neither unable not evil.
 * Therefore, superman does not exist.
 *
 * Able & Willing -> StopsEvil
 * !Able -> Unable
 * !Willing -> Evil
 * StopsEvil = false
 * Exists -> Able & Willing
 * --> !Exists
 *
 * Simplified:
 * Exists -> Able&Willing -> StopsEvil
 *
 * StopsEvil is false
 * ____________________
 * .: Exists is false
 */
object Main extends App {

  val stopsEvil = "stopsEvil"
  val exists = "exists"
  val ableAndWilling = "ableAndWilling"

  var premises = mutable.HashMap(
    stopsEvil -> Premise(stopsEvil, Some(false)),
    exists -> Premise(exists, None),
    ableAndWilling -> Premise(ableAndWilling, None))

  inferBackwards(Set(
    Inference(premises(ableAndWilling), premises(stopsEvil)),
    Inference(premises(exists), premises(ableAndWilling))))
  println(premises)

  @tailrec
  def inferBackwards(knowledgeDatabase: Set[Inference]): Set[Inference] = {
    println("premises: " + premises)
    val newKnowledgeDatabase = knowledgeDatabase
      // direct update
      .map(inference => if (inference.thenThat.truth.isDefined) {
        val updatedPremise = Premise(inference.ifThis.description, inference.thenThat.truth)
        setPremise(updatedPremise.description, updatedPremise.truth)
        inference.copy(ifThis = updatedPremise)
      } else {
        inference
      })
      // indirect update
      .map(inference => {
        val ifThisDescription = inference.ifThis.description
        val thanThatDescription = inference.thenThat.description
        inference.copy(ifThis = Premise(ifThisDescription, premises(ifThisDescription).truth),
          thenThat = Premise(thanThatDescription, premises(thanThatDescription).truth))
      })

    // Stop Condition
    if (premises(exists).truth.isEmpty) {
      inferBackwards(newKnowledgeDatabase)
    } else {
      newKnowledgeDatabase
    }
  }

  def setPremise(description: String, truth: Option[Boolean]): Unit = {
    premises.remove(description)
    premises.addOne(description -> Premise(description, truth))
  }
}

case class Premise(description: String, truth: Option[Boolean] = None) {
  override def toString: String = if (truth.isDefined) {
    s"${description} is ${truth.get}"
  } else {
    s"${description} is not sure"
  }
}

case class Inference(ifThis: Premise, thenThat: Premise) {
  override def toString: String = s"if ${ifThis.description} then ${thenThat.description}"
}