/**
 * function FOL-BC-Ask(KB, goals, θ) returns a set of substitutions inputs: KB, a knowledge base
 * goals, a list of conjuncts forming a query (θ already applied)
 * θ, the current substitution, initially the empty substitution { } local variables: answers, a set of substitutions, initially empty
 * if goals is empty then return {θ} q ′ ← Subst(θ, First(goals))
 * for each sentence r in KB
 * where Standardize-Apart(r) = ( p1 ∧ . . . ∧ pn ⇒ q)
 * and θ′ ←Unify(q,q′) succeeds
 * new goals ← [ p1, . . . , pn|Rest(goals)]
 * answers ← FOL-BC-Ask(KB, new goals, Compose(θ , θ)) ∪ answers
 * return answers
 *
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
 */
object Main extends App {
  // TODO lol
}
