package ie.gmit.sw;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer {

	private volatile double highest;
	private ArrayBlockingQueue<Resultable> queue;
	private Resultable result = null;

	public Consumer(ArrayBlockingQueue<Resultable> queue) {
		super();
		this.queue = queue;
	}

	/*
	 * getResults() takes each Result in the Head of the Queue, compares the
	 * Score of each result against all the objects in the queue and adds the
	 * result with the highest score to Result
	 */
	public Resultable getResults() {
		// Copies the score from the head of the Queue
		highest = queue.peek().getScore();
			
		// WHILE Q IS NOT EMPTY
		while (!queue.isEmpty()) {
			//System.out.println("Queue Length: "+queue.remainingCapacity());
			try {
				// Copies the score from the head of the Queue
				double current = queue.peek().getScore();
				/*
				 * if the score of the current(loops through all in the queue)
				 * is greater than the highest, Set the highest to current and
				 * add that to Result
				 */
				if (current >= highest) {
					highest = current;
					/*
					 * Takes from the head of the Queue the result with the
					 * highest score and stores it in Result
					 */
					result = queue.take();
					// System.out.println("\nPlain Text: "+r.getPlainText()+"\nScore: "+r.getScore()+"\nKey: "+r.getKey());
				} else
					queue.take();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
