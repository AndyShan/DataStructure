package algorithms.union_find;

public class UnionFind {
	private int[] id;
	private int[] sz;
	private int count;
	public UnionFind(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0;i<N;i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	public int count() {
		return count;
	}
	public boolean isConnected(int p,int q) {
		return find(p) ==  find(q);
	}
	public int find(int p) {
		int root  = p;
		/*
		 * 这个循环找到根节点
		 */
		while(root != id[root]) {
			root = id[root];
		}
		/*
		 * 这个循环实现路径压缩
		 */
		while (p != root) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
		return root;
	}
	public void union (int p,int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) {
			return;
		}
		if(sz[pRoot] <sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		}else{
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
		count--;
	}
	/*
	 * 不好的quick-find方法，union需要遍历id数组，时间复杂度高
	 */
	/*
	public int find(int p){
		return id[p];
	}
	
	public void union(int p,int q) {
		int pID = id[p];
		int qID = id[q];
		if (pID == qID) {
			return;
		}
		for (int i = 0;i<id.length;i++) {
			if (id[i] == pID) {
				id[i] = qID;
			}
		}
		count--;
	}
	*/
}
