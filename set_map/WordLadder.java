package set_map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLadder {
	/*
	 * 判断两个单词是否只有一个字母不一样
	 */
	private static boolean oneCharOff (String word1,String word2) {
		if (word1.length()!=word2.length()) {//判断情况1：若长度不同肯定不符合要求
			return false;
		}
		int differents = 0;
		for (int i = 0;i<word1.length();i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				if (++differents > 1) {//判断情况2：若果有1个以上字母不同不符合要求
					return false;
				}
			}
		}
		return differents == 1;//判断情况3:不同为0则不满足要求
	}
	/*
	 * 传入value和对应的key，更新Map m
	 */
	private static <KeyType> void updata(Map<KeyType,List<String>> m,KeyType key,String value) {
		List<String> lst = m.get(key);//获取m中对应key的value
		if (lst == null) {//若lst为空则说明传入的是新的key
			lst = new ArrayList<>();//新建一个新的空的顺序表存储新key的value
			m.put(key, lst);//将新的key和存储value的集合添加到Map m中
		}
		lst.add(value);//向key的value集合中添加value
	}
	/*
	 * 查找改变一个字母就能变成另一个单词的单词的方法1
	 * 循环比较一个单词和其它单词是否满足要求，若满足则将该单词作为key其它满足要求的单词最为value加入Map中
	 * 代码简单但是速度最慢
	 */
	public static Map<String,List<String>> computeWord1(List<String> theWords) {
		Map<String,List<String>> adjWords = new HashMap<>();
		String[] words = new String[theWords.size()];
		theWords.toArray(words);
		for (int i = 0;i<words.length;i++) {
			for (int j = i + 1;j < words.length;j++) {
				if (oneCharOff(words[i], words[j])) {//循环比较
					updata(adjWords,words[i],words[j]);
					updata(adjWords,words[i],words[j]);
					
				}
			}
		}
		return adjWords;
	}
	/*
	 * 方法2
	 * 先以长度为key将单词存入一个map中，比较时就只需在一个长度的单词集合中比较免去比较单词长度的时间
	 */
	public static Map<String,List<String>> computeWord2(List<String> theWords) {
		Map<String,List<String>> adjWords = new HashMap<>();
		Map<Integer,List<String>> wordsByLength = new HashMap<>();
		
		for(String w:theWords) {
			updata(wordsByLength,w.length(),w);//按长度将单词加入map
		}
		
		for (List<String> grounpWords:wordsByLength.values()) {//循环取出每个长度的单词
			String[] words = new String[wordsByLength.size()];
			grounpWords.toArray(words);//将同样长度的单词进行比较
			for (int i = 0;i<words.length;i++) {
				for (int j = i+1;j<words.length;j++) {
					if (oneCharOff(words[i], words[j])) {
						updata(adjWords, words[i], words[j]);
						updata(adjWords, words[j], words[i]);
					}
				}
			}
		}
		return adjWords;
	}
}
