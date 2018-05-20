package trees;

// reTrieval data structure
public class Trie {

	static final int ALPHABET_SIZE = 26;
	static TrieNode root;

	static class TrieNode {
		// instaed of left,right...its childs
		TrieNode[] childs = new TrieNode[ALPHABET_SIZE];
		boolean isEndOfWord;

		TrieNode() {
			isEndOfWord = false;

			for (int i = 0; i < ALPHABET_SIZE; i++) {
				childs[i] = null;
			}
		}
	}

	public static void insert(String key) {
		int len = key.length();
		int index;
		TrieNode temp = root;

		for (int i = 0; i < len; i++) {
			index = key.charAt(i) - 'a';
			if (temp.childs[index] == null)
				temp.childs[index] = new TrieNode();
			temp = temp.childs[index];
		}

		temp.isEndOfWord = true;

	}

	public static boolean search(String key) {
		int len = key.length();
		int index;
		TrieNode temp = root;

		for (int i = 0; i < len; i++) {
			index = key.charAt(i) - 'a';
			if (temp.childs[index] == null)
				return false;

			temp = temp.childs[index];
		}

		return (temp != null && temp.isEndOfWord);
	}

	public static void main(String[] args) {

		// Input keys (use only 'a' through 'z' and lower case)
		String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

		String output[] = { "Not present in trie", "Present in trie" };

		root = new TrieNode();
		for (int i = 0; i < keys.length; i++)
			insert(keys[i]);
		
	       // Search for different keys
        if(search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);
         
        if(search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);
         
        if(search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);
         
        if(search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);

	}

}
