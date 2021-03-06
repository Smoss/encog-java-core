package org.encog.ml.prg.generator;

import java.util.Random;

import org.encog.ml.CalculateScore;
import org.encog.ml.prg.EncogProgram;
import org.encog.ml.prg.EncogProgramContext;

public class RampedHalfAndHalfGenerator extends PrgAbstractGenerate {

	private final PrgFullGenerator generateFull;
	private final PrgGrowGenerator generateGrow;
	private final int minDepth;

	public RampedHalfAndHalfGenerator(final EncogProgramContext theContext,
			final CalculateScore theScoreFunction, final int theMinDepth,
			final int theMaxDepth) {
		super(theContext, theScoreFunction, theMaxDepth);
		this.generateFull = new PrgFullGenerator(theContext, theScoreFunction,
				theMaxDepth);
		this.generateGrow = new PrgGrowGenerator(theContext, theScoreFunction,
				theMaxDepth);
		this.minDepth = theMinDepth;
	}

	@Override
	public void createNode(final Random rnd, final EncogProgram prg,
			final int currentDepth, final int desiredDepth) {
		int actualDesiredDepth = desiredDepth;

		// If we are at the root, then adjust the desired depth to be ramped
		// value.
		// The ramped value is an equal distribution between the min and max.
		if (currentDepth == 0) {
			actualDesiredDepth = rnd.nextInt(desiredDepth - this.minDepth)
					+ this.minDepth;
		}

		// split half-and-half between full and grow
		if (rnd.nextDouble() > 0.5) {
			this.generateFull.createNode(rnd, prg, currentDepth,
					actualDesiredDepth);
		} else {
			this.generateGrow.createNode(rnd, prg, currentDepth,
					actualDesiredDepth);
		}
	}

}
