package set_map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLadder {
	/*
	 * �ж����������Ƿ�ֻ��һ����ĸ��һ��
	 */
	private static boolean oneCharOff (String word1,String word2) {
		if (word1.length()!=word2.length()) {//�ж����1�������Ȳ�ͬ�϶�������Ҫ��
			return false;
		}
		int differents = 0;
		for (int i = 0;i<word1.length();i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				if (++differents > 1) {//�ж����2��������1��������ĸ��ͬ������Ҫ��
					return false;
				}
			}
		}
		return differents == 1;//�ж����3:��ͬΪ0������Ҫ��
	}
	/*
	 * ����value�Ͷ�Ӧ��key������Map m
	 */
	private static <KeyType> void updata(Map<KeyType,List<String>> m,KeyType key,String value) {
		List<String> lst = m.get(key);//��ȡm�ж�Ӧkey��value
		if (lst == null) {//��lstΪ����˵����������µ�key
			lst = new ArrayList<>();//�½�һ���µĿյ�˳���洢��key��value
			m.put(key, lst);//���µ�key�ʹ洢value�ļ�����ӵ�Map m��
		}
		lst.add(value);//��key��value���������value
	}
	/*
	 * ���Ҹı�һ����ĸ���ܱ����һ�����ʵĵ��ʵķ���1
	 * ѭ���Ƚ�һ�����ʺ����������Ƿ�����Ҫ���������򽫸õ�����Ϊkey��������Ҫ��ĵ�����Ϊvalue����Map��
	 * ����򵥵����ٶ�����
	 */
	public static Map<String,List<String>> computeWord1(List<String> theWords) {
		Map<String,List<String>> adjWords = new HashMap<>();
		String[] words = new String[theWords.size()];
		theWords.toArray(words);
		for (int i = 0;i<words.length;i++) {
			for (int j = i + 1;j < words.length;j++) {
				if (oneCharOff(words[i], words[j])) {//ѭ���Ƚ�
					updata(adjWords,words[i],words[j]);
					updata(adjWords,words[i],words[j]);
					
				}
			}
		}
		return adjWords;
	}
	/*
	 * ����2
	 * ���Գ���Ϊkey�����ʴ���һ��map�У��Ƚ�ʱ��ֻ����һ�����ȵĵ��ʼ����бȽ���ȥ�Ƚϵ��ʳ��ȵ�ʱ��
	 */
	public static Map<String,List<String>> computeWord2(List<String> theWords) {
		Map<String,List<String>> adjWords = new HashMap<>();
		Map<Integer,List<String>> wordsByLength = new HashMap<>();
		
		for(String w:theWords) {
			updata(wordsByLength,w.length(),w);//�����Ƚ����ʼ���map
		}
		
		for (List<String> grounpWords:wordsByLength.values()) {//ѭ��ȡ��ÿ�����ȵĵ���
			String[] words = new String[wordsByLength.size()];
			grounpWords.toArray(words);//��ͬ�����ȵĵ��ʽ��бȽ�
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
