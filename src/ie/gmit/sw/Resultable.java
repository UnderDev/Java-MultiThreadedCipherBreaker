package ie.gmit.sw;

/*
 * Resultable is and interface that if any classes want to implements it,
 * the class must first implement the following methods below
 */
public interface Resultable {

	public abstract String getPlainText();

	public abstract void setPlainText(String plainText);

	public abstract int getKey();

	public abstract void setKey(int key);

	public abstract double getScore();

	public abstract void setScore(double score);

}