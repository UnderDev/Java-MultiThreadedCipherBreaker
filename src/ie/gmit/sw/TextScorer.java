package ie.gmit.sw;

import java.util.Map;

public class TextScorer {
	private Map<String, Double> map = null;

	public TextScorer(Map<String, Double> m) {
		this.map = m;
	}

	/*
	 * Method used passing in a string and looping through its length comparing
	 * a substring of i(incrementing) to the GRAM_SIZE. Calling the method
	 * computeLogScore() and getting returned the Score
	 */
	public double getScore(String text) {
		double score = 0f;

		for (int i = 0; i < text.length(); i++) {
			if (i + QuadGramMap.GRAM_SIZE <= text.length() - 1) {
				score += computeLogScore(text.substring(i, i
						+ QuadGramMap.GRAM_SIZE));
			}
		}
		return score;
	}

	/*
	 * Method gets the String of length 4, and compares it against the Map to
	 * get its frequency/total and probability
	 */
	public double computeLogScore(String quadgram) {
		if (map.containsKey(quadgram)) {
			double frequency = map.get(quadgram);
			double total = (double) map.size();
			double probability = (double) (frequency / total);

			return Math.log10(probability);
		} else {
			return 0f;
		}
	}
}
