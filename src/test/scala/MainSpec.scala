import Main.{Inference, Premise, inferPremise}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class MainSpec extends AnyWordSpec with Matchers {
  "Main" when {
    "given a knowledge database" should {
      val a = Premise(Some(true))
      val b = Premise()
      val aThenB = Inference(a, b)
      val knowledgeDatabase = Set(aThenB)
      "infer if a true then b true" in {
        val b = inferPremise(a, knowledgeDatabase)
        b shouldBe Premise(Some(true))
      }
    }
  }
}
