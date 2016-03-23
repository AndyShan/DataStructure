package algorithms.dijkstra;

import java.util.List;

public class Dijkstra {
	public static int[] dijkstra(WeightedGraph G, int s) {
		final int[] dist = new int[G.size];//距离
		final int[] pred = new int[G.size];//先后顺序
		final boolean[] visited = new boolean[G.size];//是否访问

		for (int i = 0; i < dist.length; i++) {//初始化距离，设为最大长度
			dist[i] = Integer.MAX_VALUE;
		}
		dist[s] = 0;//将起始点的长度置0
		for (int i = 0; i < dist.length; i++) {
			final int next = minVertex(dist, visited);//寻找所有点中距离最小，且未被访问的点
			visited[next] = true;//设为访问
			final int[] n = G.neighbors(next);//定义一个数组，存储next的邻接点
			for (int j = 0;j<n.length;j++) {//更新与next链接的点到next的距离
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
			if (!v[i] && dist[i] < x) {//如果未访问且距距离小于x
				y = i;
				x = dist[i];
			}
		}
		return y;
	}
}
