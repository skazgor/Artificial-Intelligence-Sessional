package variable;

import ValueHuristic.ValueHuristic;
import ValueHuristic.Node;

public class Variable {

    public int row;
    public int col;
    public int value=0;

    private int valueMark=0;
    private  Node[] preference;
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
        preference=null;
    }
    public void setIndex(int row,int col){
        this.row=row;
        this.col=col;
    }


    public int getAValue(){
        if(preference==null){
            preference= ValueHuristic.getPreference();
        } else if(valueMark==preference.length){
            value=0;
           return -1;
        }
        while (valueMark<preference.length && (!domain[preference[valueMark].number])   ){
                valueMark++;
            }

        if(valueMark==preference.length){
            value=0;
            return -1;
        }else {
            value = preference[valueMark].number+1;
            valueMark++;
            return value;
        }
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
        preference=null;
    }

    @Override
    public String toString() {
        return String.format(" %2d",value);
    }
}
