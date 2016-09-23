package ie.gmit.sw;

import java.util.concurrent.*;

public class CipherBreaker {
	private static final int MAX_QUEUE_SIZE = 100;
	private String cypherText;

	private ArrayBlockingQueue<Resultable> queue;
	private Resultable r = null;

	private QuadGramMap map = new QuadGramMap(null);
	private FileParser fp = new FileParser();
	private Consumer consumer = null;
	
	/*
	 * Constructor for CypherBreaker, that sets the String cypherText And
	 * creates a ArrayBlockingQueue of the interface <Resultable> with a Fixed
	 * MAX_QUEUE_SIZE
	 */
	public CipherBreaker(String cypherText) {
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		this.cypherText = cypherText;
	}

	/*
	 * Method gets the Map of <Strings,Doubles>, Then creates threads and gives
	 * them the job to decrypt/score the key it used. Once the threads all
	 * return the consumer checks each of the results in the queue comparing
	 * them by the score
	 */
	public void init() {
		// Calls the method getMap() from FileParser that returns a
		// Map<String,Double>
		map.setMap(fp.getMap());

		/*
		 * For loop that relates to the Key used to encrypt/decrypt loops from
		 * 2(Min key needed) to the (cypherTexts length input from the user) / 2
		 * and passes the key into a new thread along with the cypherText, map
		 * and queue
		 */
		for (int i = 2; i < cypherText.length() / 2; i++) {
			// Starts a new Thread and gives it a job with the parameters below
			new Thread(new Decryptor(queue, cypherText, i, map.getMap()))
					.start();
		}
		// Pass the queue to the CONSUMER class which gets the best Result
		consumer = new Consumer(queue);
		r = consumer.getResults();
	}

	public String toString() {
		return ("\nPlain Text: " + r.getPlainText() + "\nScore: "
				+ r.getScore() + "\nKey: " + r.getKey());
	}
}
