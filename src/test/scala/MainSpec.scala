import Main.{Inference, Premise, inferPremise}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class MainSpec extends AnyWordSpec with Matchers {
  "Main" when {
    "given a knowledge database and a true premise a" should {
      val a = Premise("a", Some(true))
      val b = Premise("b")
      val aThenB = Inference(a, b)
      val knowledgeDatabase = Set(aThenB)
      "infer if a true then b true" in {
        val inferredB = inferPremise(b, knowledgeDatabase)
        inferredB shouldBe Premise("b", Some(true))
      }
    }
    "given a knowledge database and a false premise a" should {
      val a = Premise("a", Some(false))
      val b = Premise("b")
      val aThenB = Inference(a, b)
      val knowledgeDatabase = Set(aThenB)
      "infer if a false then b None" in {
        val inferredB = inferPremise(b, knowledgeDatabase)
        inferredB shouldBe Premise("b", None)
      }
    }
  }
}
