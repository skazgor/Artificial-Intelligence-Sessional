package variable;

import java.util.HashSet;


public class Variable {

    public int row;
    public int col;
    public int value=0;

    private int valueMark=0;
    public boolean[] domain;
   int [] trackForTrue;
   public int forwardDegree;
    public Variable(int size){
        domain=new boolean[size];
        trackForTrue=new int[size];
        forwardDegree=2*size;
        for (int i = 0; i < size; i++) {
            domain[i]= true;
            trackForTrue[i]=0;
        }
    }
    public void setIndex(int row,int col){
        this.row=row;
        this.col=col;
    }
    public int getAValue(){
        while ( valueMark<domain.length  ){
                if(domain[valueMark]){
                    value=valueMark+1;
                    valueMark++;
                    return value;
                }
                else {
                    valueMark++;
                }
            }
        value=0;
        return -1;
    }
    public int getNumberOfDomain(){
        int count=0;
        for (int i = 0; i < domain.length; i++) {
            if(domain[i]){
                count++;
            }
        }
        return count;
    }
  public  boolean update(int x){
        domain[x]=false;
        trackForTrue[x]++;
        forwardDegree--;

        if(getNumberOfDomain()==0 && value==0){
            return false;
        }
        else return true;
    }
    public void backTrack(int x){
        trackForTrue[x]--;
        if(trackForTrue[x]==0) {
            domain[x] = true;
        }
        forwardDegree++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable)) return false;
        Variable variable = (Variable) o;
        return row == variable.row && col == variable.col;
    }

    @Override
    public int hashCode(){
        return row*193+col;
    }

    public void reset() {
        valueMark=0;
        value=0;
    }

    @Override
    public String toString() {
        return String.format(" %2d",value);
    }
}
