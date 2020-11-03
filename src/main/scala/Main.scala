/**
 * Wenn Superman in der Lage und willig ist, Unheil zu verhindern, dann würde er es tun. Wenn
 * Superman nicht in der Lage ist, Unheil zu verhindern, dann ist er unfähig. Wenn Superman
 * nicht willig ist, Unheil zu verhindern, dann ist er bösartig. Superman verhindert kein Unheil.
 * Wenn Superman existiert, dann ist er weder unfähig noch bösartig. Deswegen existiert Superman nicht.
 *
 * Able & Willing -> StopsEvil
 * !Able -> Unable
 * !Willing -> Evil
 * StopsEvil = false
 * Exists -> Able & Willing
 * => !Exists
 *
 *
 * ,---> Willing ------.
 * |                   v
 * Exists -> Able -> StopsEvil
 *
 * StopsEvil is false
 * ____________________
 * .: Exists is false
 */
object Main extends App {

  val stopsEvil = Premise("stops evil", Some(false))
  val able = Premise("able")
  val willing = Premise("willing")
  val exists = Premise("exists")

  val ableThenStopsEvil = Inference(able, stopsEvil)
  val willingThenStopsEvil = Inference(willing, stopsEvil)
  val existsThenAble = Inference(exists, able)
  val existsThenWilling = Inference(exists, willing)
  val knowledgeDatabase = Set(ableThenStopsEvil, willingThenStopsEvil, existsThenAble, existsThenWilling)

  println(inferPremise(able, knowledgeDatabase))

  /**
   * Returns a premise that is true, if this can be inferred by the knowledge database.
   * Returns a premise that has no truth otherwise.
   */
  def inferPremise(premise: Premise, knowledgeDatabase: Set[Inference]): Premise = {
    if (knowledgeDatabase
      .filter(inference => inference.thanThat.description.equals(premise.description))
      .exists(inference => inference.ifThis.truth.getOrElse(false))) {
      Premise(premise.description, Some(true))
    }
    else {
      Premise(premise.description, None)
    }
  }


  case class Premise(description: String, truth: Option[Boolean] = None)

  case class Inference(ifThis: Premise, thanThat: Premise)

}