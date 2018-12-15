import java.io.*;
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.List;
        import java.util.StringTokenizer;

public class Main {
    public static InputReader in = new InputReader(System.in);
    public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args)
    {
        int n,q;

        out.flush();
        out.close();
    }
}
class tree_array  //区间修改 + 区间和
{
    long sum1[],sum2[];
    long a[]; //原序列
    int n;
    tree_array(int n1)
    {
        n=n1;
        sum1=new long[n+1];
        a=new long[n+1];
        sum2=new long[n+1];
    }
    void init()
    {
        for(int i=1;i<=n;++i)
            a[i]+=a[i-1];
        Arrays.fill(sum1,0);
        Arrays.fill(sum2,0);
    }
    void add(int p, long x){
        for(int i = p; i <= n; i += i & -i)
        {
            sum1[i] += x;
            sum2[i] += x * p;
        }
    }
    void range_add(int l, int r, long x){
        add(l, x);
        add(r + 1, -x);
    }
    long ask(int p){
        long res = 0;
        for(int i = p; i!=0; i -= i & -i)
            res += (p + 1) * sum1[i] - sum2[i];
        return res;
    }
    long range_ask(int l, int r){
        return ask(r) - ask(l - 1)+a[r]-a[l-1];
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