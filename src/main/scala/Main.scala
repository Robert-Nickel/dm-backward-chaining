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

  val stopsEvil = Premise(Some(false))
  val able = Premise()
  val willing = Premise()
  val exists = Premise()

  val ableThenStopsEvil = Inference(able, stopsEvil)
  val willingThenStopsEvil = Inference(willing, stopsEvil)
  val existsThenAble = Inference(exists, able)
  val existsThenWilling = Inference(exists, willing)
  val knowledgeDatabase = Set(ableThenStopsEvil, willingThenStopsEvil, existsThenAble, existsThenWilling)

  println(inferPremise(able, knowledgeDatabase))

  /**
   * Returns a premise that is true, if this can be infered by the knowledge database.
   * Returns a premise that has no truth otherwise.
   */
  def inferPremise(premise: Premise, knowledgeDatabase: Set[Inference]): Premise = {
    // TODO: this is broken somehow. Will find out.
    if (knowledgeDatabase
      .filter(inference => inference.thanThat == premise)
      .filter(inference => inference.ifThis.truth.getOrElse(false))
      .exists(inference => inference.ifThis.truth.get)) {
      Premise(Some(true))
    }
    else {
      Premise(None)
    }
  }


  case class Premise(truth: Option[Boolean] = None)

  case class Inference(ifThis: Premise, thanThat: Premise)

}