import java.util.Arrays;

public class tree_array
{
    class tree_array1  //区间修改 + 区间和
    {
        long sum1[],sum2[];
        long a[]; //原序列
        int n;
        tree_array1(int n1)
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
}
