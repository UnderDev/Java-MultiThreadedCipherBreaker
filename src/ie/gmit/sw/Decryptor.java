package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * This method is called from the  CipherBreaker class,
 * and is used by each thread created from said class.
 * Each thread gets passed the Queue,cipherText,its key and the Map 
 * to compare its Decrypted plain text from
 */
public class Decryptor implements Runnable {

	private ArrayBlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	private volatile Resultable r = null;

	private Map<String, Double> map = null;
	private RailFence rf = new RailFence();
	private TextScorer ts = null;

	public Decryptor(ArrayBlockingQueue<Resultable> queue, String cipherText, int key, Map<String, Double> map) {
		super();
		this.queue = queue;
		this.cypherText = cipherText;
		this.key = key;
		this.map = map;
	}

	public void run() {
		String plainText;
		double score;
		ts = new TextScorer(map);
		// Each thread passes in the text and the key and gets returned a string
		// of plaintext
		plainText = rf.decrypt(cypherText, key);
		// SET THE SCORE BASED ON THE TEXT FROM THE THREAD AND PUT IN RESULTABLE
		score = (ts.getScore(plainText));

		r = new Result(plainText, key, score);
		try {
			// PUT THE THREAD ON THE QUEUE
			queue.put((Resultable) r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
