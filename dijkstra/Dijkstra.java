package algorithms.dijkstra;

import java.util.List;

public class Dijkstra {
	public static int[] dijkstra(WeightedGraph G, int s) {
		final int[] dist = new int[G.size];//����
		final int[] pred = new int[G.size];//�Ⱥ�˳��
		final boolean[] visited = new boolean[G.size];//�Ƿ����

		for (int i = 0; i < dist.length; i++) {//��ʼ�����룬��Ϊ��󳤶�
			dist[i] = Integer.MAX_VALUE;
		}
		dist[s] = 0;//����ʼ��ĳ�����0
		for (int i = 0; i < dist.length; i++) {
			final int next = minVertex(dist, visited);//Ѱ�����е��о�����С����δ�����ʵĵ�
			visited[next] = true;//��Ϊ����
			final int[] n = G.neighbors(next);//����һ�����飬�洢next���ڽӵ�
			for (int j = 0;j<n.length;j++) {//������next���ӵĵ㵽next�ľ���
				final int v = n[j];
				final int d = dist[next] + G.getWeight(next, v);
				if (dist[v] > d) {
					dist[v] = d;
					pred[v] = next;
				}
			}
		}

		return pred;
	}

	private static int minVertex(int[] dist, boolean[] v) {
		int x = Integer.MAX_VALUE;
		int y = -1;
		for (int i = 0; i < dist.length; i++) {
			if (!v[i] && dist[i] < x) {//���δ�����Ҿ����С��x
				y = i;
				x = dist[i];
			}
		}
		return y;
	}
}
