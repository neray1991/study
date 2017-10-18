package dataStructures;

public class RandomStruct {
	public static int getRandomInt(int top) {
		int random = (int)(top * Math.random());
		return random;
	}
	public static int[] getRandomArray(int size, int range) {
		int[] rand = new int[size];
		for (int i = 0; i < size; i++) {
			rand[i] = ((int)(range*(Math.random())));
			//System.out.println(rand[i]);
		}
		return rand;
	}
}