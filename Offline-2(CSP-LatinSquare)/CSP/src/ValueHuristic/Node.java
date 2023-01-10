package ValueHuristic;

public class Node implements Comparable<Node>{
    public int number;
    public int count;
    public Node (int index){
        number=index;
    }
    @Override
    public int compareTo(Node o) {
        if(this.count>o.count) return 1;
        else if(this.count<o.count) return -1;
        else return 0;
    }

}
