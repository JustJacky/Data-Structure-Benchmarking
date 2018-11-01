package pack;

import java.util.Random;
public class main {
	public static void main(String[] args) {
	int KVCount = 1000;
	String[] keys = new String[KVCount];
	LinkedMap newList = new LinkedMap();
	HashMap newHash = new HashMap();
	BSTMap newTree = new BSTMap();
	AVLTree newAVL = new AVLTree();
	long insertTime, removeTime, insertSum, removeSum;
	insertTime = removeTime = insertSum = removeSum = 0;
	
	//LinkedMap
	System.out.println("~~~~~LinkedMap~~~~~");
	//runs each benchmark 10 times
	for(int i = 0; i < 10; i++){
		insertTime = insertBenchmark(newList, keys, KVCount);
		removeTime = removeBenchmark(newList, keys, KVCount);
		System.out.println("Insert: " + insertTime + " ns");
		System.out.println("Remove: " + removeTime + " ns");
		insertSum += insertTime;
		removeSum += removeTime;
	}
	System.out.println("~~~~~~~~~~~~~~~~~~");
	System.out.println("Average insertion time: " + insertSum/10 + " ns  | Average removal time: " + removeSum/10 + " ns \n \n \n");
	
	//HashMap
	System.out.println("~~~~~HashMap~~~~~");
	insertTime = removeTime = insertSum = removeSum = 0;
	for(int i = 0; i < 10; i++){
		insertTime = insertBenchmark(newHash, keys, KVCount);
		removeTime = removeBenchmark(newHash, keys, KVCount);
		System.out.println("insertBenchmark: " + insertTime + " ns");
		System.out.println("removeBenchmark: " + removeTime + " ns");
		insertSum += insertTime;
		removeSum += removeTime;
	}
	System.out.println("~~~~~~~~~~~~~~~~~~");
	System.out.println("Average insertion time: " + insertSum/10 + " ns  | Average removal time: " + removeSum/10 + " ns \n \n \n");
	
	//BSTMap
	System.out.println("~~~~~BSTMap~~~~~");
	insertTime = removeTime = insertSum = removeSum = 0;
	for(int i = 0; i < 10; i++){
		insertTime = insertBenchmark(newTree, keys, KVCount);
		removeTime = removeBenchmark(newTree, keys, KVCount);
		System.out.println("insertBenchmark: " + insertTime + " ns");
		System.out.println("removeBenchmark: " + removeTime + " ns");
		insertSum += insertTime;
		removeSum += removeTime;
	}
	System.out.println("~~~~~~~~~~~~~~~~~~");
	System.out.println("Average insertion time: " + insertSum/10 + " ns  | Average removal time: " + removeSum/10 + " ns \n \n \n");
	
	//AVLTree
		System.out.println("~~~~~AVLTree~~~~~");
		insertTime = removeTime = insertSum = removeSum = 0;
		for(int i = 0; i < 10; i++){
			insertTime = insertBenchmark(newAVL, keys, KVCount);
			removeTime = removeBenchmark(newAVL, keys, KVCount);
			System.out.println("insertBenchmark: " + insertTime + " ns");
			System.out.println("removeBenchmark: " + removeTime + " ns");
			insertSum += insertTime;
			removeSum += removeTime;
		}
		System.out.println("~~~~~~~~~~~~~~~~~~");
		System.out.println("Average insertion time: " + insertSum/10 + " ns  | Average removal time: " + removeSum/10 + " ns");
	}
	
	//random string of 12 characters
	public static String randomKeyGen() {
		String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
				StringBuilder randomKey = new StringBuilder();
		Random rand = new Random();
		while(randomKey.length() < 12) {
			int index = (int) (rand.nextFloat() * alphabet.length());
			randomKey.append(alphabet.charAt(index));
		}
		String genKey = randomKey.toString();
			return genKey;
	}
	
	//random integer between 0 to 100000
	public static int randomValueGen() {
		Random rand = new Random();
		int genValue = rand.nextInt(100000);
		return genValue;
	}
	
	public static long insertBenchmark(Maps spam, String[] keys, int count) {;
		long endTime, startTime;
		Random rand = new Random();
		startTime = System.nanoTime();
		for(int i = 0; i < count; i++) {
			String s = randomKeyGen();
			spam.put(s,randomValueGen());
			//needs to save the keys for later removal
			keys[i] = s;
		}
		endTime = System.nanoTime();
		return endTime - startTime;
	}
	
	public static long removeBenchmark(Maps spam, String[] keys, int count) {
		long endTime, startTime;
		startTime = System.nanoTime();
		for(int i = 0; i < count; i++) {
			//uses keys stored to find and remove them
			spam.remove(keys[i]);
		}
		endTime = System.nanoTime();
		return endTime - startTime;
	}
}