import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static InputReader in = new InputReader(System.in);
    public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args)
    {

        out.flush();
        out.close();
    }
}
class split_tree //划分树 查询区间k大 O(nlogn) 原序列在tree[0]
{
    int tree[][];
    int sorted[];
    int toleft[][];
    split_tree(int f,int MAXN)
    {
        tree=new int[f][MAXN];
        sorted=new int[MAXN];
        toleft =new int[f][MAXN];
    }
    void init(int L,int R,int n)
    {
        //sorted=Arrays.copyOf(tree[0],tree[0].length);
        Arrays.sort(sorted,1,n+1);
        build(L,R,0);
    }
    void build(int l,int r,int dep)
    {
        if(l==r)    return;
        int mid = (l+r)>>1;
        int i,sum = mid-l+1;  //表示等于中间值而且被分入左边的个数
        for(i=l;i<=r;i++)
        {
            if(tree[dep][i]<sorted[mid])    sum--;
        }
        int lpos=l;
        int rpos=mid+1;
        for(i=l;i<=r;i++)
        {
            if(tree[dep][i]<sorted[mid])    //比中间的数小，分入左边
            {
                tree[dep+1][lpos++]=tree[dep][i];
            }
            else if(tree[dep][i]==sorted[mid]&&sum>0) //等于中间的数值，分入左边，直到sum==0后分到右边
            {
                tree[dep+1][lpos++]=tree[dep][i];
                sum--;
            }
            else  //右边
            {
                tree[dep+1][rpos++]=tree[dep][i];
            }
            toleft[dep][i] = toleft[dep][l-1] + lpos - l;   //从1到i放左边的个数
        }
        build(l,mid,dep+1);
        build(mid+1,r,dep+1);
    }
    int query(int L,int R,int l,int r,int dep,int k)
    {
        if(l==r)    return tree[dep][l];
        int mid = (L+R)>>1;
        int cnt = toleft[dep][r] - toleft[dep][l-1];  //[l,r]中位于左边的个数
        if(cnt>=k)
        {
            int newl = L + toleft[dep][l-1] - toleft[dep][L-1]; //L+要查询的区间前被放在左边的个数
            int newr = newl + cnt - 1;  //左端点加上查询区间会被放在左边的个数
            return query(L,mid,newl,newr,dep+1,k);
        }
        else
        {
            int newr = r + toleft[dep][R] - toleft[dep][r];
            int newl = newr - (r - l - cnt);
            return query(mid+1,R,newl,newr,dep+1,k-cnt);
        }

    }
}
class InputReader{
    private final static int BUF_SZ = 65536;
    BufferedReader in;
    StringTokenizer tokenizer;
    public InputReader(InputStream in) {
        super();
        this.in = new BufferedReader(new InputStreamReader(in),BUF_SZ);
        tokenizer = new StringTokenizer("");
    }
    public boolean hasNext() {  //处理EOF
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                String line = in.readLine();
                if(line == null) return false;
                tokenizer = new StringTokenizer(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
    public String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }
    public int nextInt() {
        return Integer.parseInt(next());
    }
    public long nextLong()
    {
        return Long.parseLong(next());
    }
}