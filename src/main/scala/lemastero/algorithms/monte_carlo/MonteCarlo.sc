import lemastero.algorithms.monte_carlo.CircleInSquareSimulation

val sim = new CircleInSquareSimulation(1)


val throws = 4000
val hits = sim.run(throws)


val pi = (hits * 4).toDouble / throws



