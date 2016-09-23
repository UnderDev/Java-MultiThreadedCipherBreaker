package ie.gmit.sw;

import java.util.Map;

public class QuadGramMap {
	private Map<String, Double> map;
	public static int GRAM_SIZE = 4;

	public QuadGramMap(Map<String, Double> map) {
		this.map = map;
	}

	public Map<String, Double> getMap() {
		return map;
	}

	public void setMap(Map<String, Double> map) {
		this.map = map;
	}
}