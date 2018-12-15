import java.util.Arrays;

public class split_tree {
    class split_tree1 //划分树 查询区间k大 O(nlogn) 原序列在tree[0]
    {
        int tree[][];
        int sorted[];
        int toleft[][];
        split_tree1(int f,int MAXN)
        {
            tree=new int[f][MAXN];
            sorted=new int[MAXN];
            toleft =new int[f][MAXN];
        }
        void init(int L,int R,int n)
        {
            sorted=Arrays.copyOf(tree[0],tree[0].length);
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
}
