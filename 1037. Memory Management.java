import java.io.FileInputStream;
import java.io.IOException;import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.stream.events.NotationDeclaration;

public class MemoryManagement {
    
    
    public static class TreeNode implements Comparable<TreeNode> {
        
        public long timestamp;
        public int  id;
        
        public TreeNode(int id, long timestamp){
            this.timestamp = timestamp;
            this.id        = id;
        }
        
        @Override
        public int compareTo(TreeNode obj) {
            
            if (this.timestamp < obj.timestamp) {
                return -1;
            } else if (this.timestamp == obj.timestamp) {
                if (this.id < obj.id) {
                    return -1;
                } else if (this.id == obj.id) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
            }
            return 1;
        }
    }
    
    
    public static void main(String[] args) throws IOException {

        TreeMap<TreeNode, Integer> blocks = new TreeMap<TreeNode, Integer>(); 
        
        /* references to the blocks nodes in the tree */
        TreeNode nodes[] = new TreeNode[30001];
        
        Scanner sc = new Scanner(System.in);
        int N = 30000;
        int T = 10*60; /* 10 min */
        int minFreeBlock = 1;
        int oldtime = 0;
        while (sc.hasNext()) {
            
            int time = 0;
            char c   = 0;
            try {
                time = sc.nextInt();
                c    = (char) sc.next().charAt(0);
            } catch (Exception e) {
                System.exit(0);
            }
            
            /* Remove expired blocks */
            if (time != oldtime) {
            
                TreeNode n = null;
                while (!blocks.isEmpty()) {
                    n = blocks.firstKey();
                    /* expired */
                    if (time - n.timestamp >= T ) {
                        blocks.remove(n);
                        nodes[n.id] = null;
                        if (n.id < minFreeBlock) {
                            minFreeBlock = n.id;
                        }
                    } else {
                        break;
                    }
                }
                
                oldtime = time;
            }
                
            if (c == '+') {
                System.out.println(minFreeBlock);
                TreeNode n = new TreeNode(minFreeBlock, time);
                blocks.put(n, minFreeBlock);
                nodes[n.id] = n;
                for (int i = minFreeBlock + 1; i <= 30000; i++) {
                    if (nodes[i] == null) {
                        minFreeBlock = i;
                        break;
                    }
                }
            } else {
                /* request for block access */
                int blockNo = sc.nextInt();
                //System.out.println(blockNo);
                if (blockNo > N || nodes[blockNo] == null) {
                    System.out.println("-");
                }
                else {
                    /* update block time */
                    blocks.remove(nodes[blockNo]);
                    nodes[blockNo].timestamp = time;
                    blocks.put(nodes[blockNo], blockNo);
                    System.out.println("+");
                }
            }
            
            
        }
        sc.close();
            
    }

}