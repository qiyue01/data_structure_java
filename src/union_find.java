import java.util.Arrays;

public class union_find
{
    class union_find1
{
    int fa[];
    union_find1(int n)
    {
        fa=new int[n];
        Arrays.fill(fa,-1);
    }
    int find(int u)
    {
        if(fa[u]<0)
            return u;
        int x=find(fa[u]);
        fa[u]=x;
        return x;
    }
    void union(int u,int v)
    {
        int x=find(u);
        int y=find(v);
        fa[x]=y;
    }
}
}
