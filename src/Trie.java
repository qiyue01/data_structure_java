public class Trie {
    class trie
    {
        class Trie_node
        {
            Trie_node next[];
            boolean end;
            Trie_node(int sigma)
            {
                next=new Trie_node[sigma];
                end=false;
            }
        }
        Trie_node root;
        int sigma;
        trie(int sigma1)
        {
            root=new Trie_node(sigma1);
            sigma=sigma1;
        }
        void insert(String s)
        {
            Trie_node find=root;
            for(int i=0;i<s.length();++i)
            {
                if(find.next[s.charAt(i)-'a']==null)
                {
                    find.next[s.charAt(i)-'a']=new Trie_node(sigma);
                    find=find.next[s.charAt(i)-'a'];
                }
                else
                    find=find.next[s.charAt(i)-'a'];
            }
            find.end=true;
        }
        void dfs()
        {

        }

    }
}
