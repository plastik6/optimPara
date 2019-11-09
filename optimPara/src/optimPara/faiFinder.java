package optimPara;

import static optimPara.Maths.fastComputeDistance;

import java.util.ArrayList;

public class faiFinder {

	private final ArrayList<Point> initialPointList;
	private final double initialGuessPerim = 0.0;
	private final double minSideRatio = 0.28;

	public faiFinder(ArrayList<Point> initialPointList) {
		this.initialPointList = initialPointList;
	}

	public int[] findOptimum() {

		double maxPerim = initialGuessPerim;
		double minDistThreshold = maxPerim * minSideRatio;
		int p1Index = -1;
		int p2Index = -1;
		int p3Index = -1;

		Point p1;
		Point p2;
		Point p3;

		double d1;
		double d2;
		double d3;

		int indexP1Best = 0;
		int indexP2Best = 0;
		int indexP3Best = 0;

		double d1Best = 0;
		double d2Best = 0;
		double d3Best = 0;

//		double currentMinSide = 0.0;

		// start P1 iteration loop
		while (p1Index < initialPointList.size() - 1) {
			p1Index++;
			p1 = initialPointList.get(p1Index);

			p2Index = p1Index;
			// start P2 iteration loop
			while (p2Index < initialPointList.size() - 1) {
				p2Index++;

				p2 = initialPointList.get(p2Index);
				d3 = fastComputeDistance(p1, p2);

				// if P2 is acceptable
				if (d3 > minDistThreshold) {

					p3Index = p2Index;
					// start P3 iteration loop
					while (p3Index < initialPointList.size() - 1) {
						p3Index++;
						p3 = initialPointList.get(p3Index);

						d1 = fastComputeDistance(p2, p3);
						d2 = fastComputeDistance(p1, p3);

						double currentPerim = d1 + d2 + d3;
						double currentMinSide = Math.min(Math.min(d1, d2), d3);

						// if P3 is acceptable
						if (currentPerim > maxPerim && currentMinSide > (currentPerim * minSideRatio)) {

							maxPerim = currentPerim;
							indexP1Best = p1Index;
							indexP2Best = p2Index;
							indexP3Best = p3Index;

						}

					} // end of P3 iteration loop

				} // end of if P2 is acceptable

			} // end of P2 iteration loop

			System.out.println("P1 index : " + p1Index + " | max périm : " + maxPerim);
		} // end of P1 iteration loop

		Point p1Best = initialPointList.get(indexP1Best);
		Point p2Best = initialPointList.get(indexP2Best);
		Point p3Best = initialPointList.get(indexP3Best);

		d1Best = Maths.computeDistanceExact(p2Best, p3Best);
		d2Best = Maths.computeDistanceExact(p3Best, p1Best);
		d3Best = Maths.computeDistanceExact(p1Best, p2Best);

		double minSide = Math.min(Math.min(d1Best, d2Best), d3Best);
		double realMaxPerim = d1Best + d2Best + d3Best;

		System.out.println("Max perimeter = " + maxPerim);
		System.out.println("P1 = " + indexP1Best + "| P2 = " + indexP2Best + "| P3 = " + indexP3Best);
		System.out.println("d1 = " + d1Best + "| d2 = " + d2Best + "| d3 = " + d3Best);
		System.out.println("Max perimeter = " + maxPerim);
		System.out.println("real Max perimeter = " + realMaxPerim);
		System.out.println("Min side = " + minSide);
		System.out.println("Min side ratio = " + minSide / realMaxPerim);

		System.out.println("P1 : " + p1Best.toString());
		System.out.println("P2 : " + p2Best.toString());
		System.out.println("P3 : " + p3Best.toString());

		return new int[] { indexP1Best, indexP2Best, indexP3Best };
	}

}
