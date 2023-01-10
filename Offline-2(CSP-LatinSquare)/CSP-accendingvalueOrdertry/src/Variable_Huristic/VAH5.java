package Variable_Huristic;

import variable.Variable;

import java.util.HashSet;

public class VAH5 extends VariableOrderHeuristic {
    private HashSet<Variable> set;
    public VAH5(Variable[][] variables){
        set=new HashSet<Variable>(variables.length* variables.length);
        for (int i = 0; i < variables.length; i++) {
            for (int j = 0; j < variables[0].length; j++) {
                if(variables[i][j].value==0){
                    set.add(variables[i][j]);
                }
            }
        }
        System.out.println("Number of unassainged variable: "+set.size());
    }
    @Override
    public Variable getNextVariable() {
        if(set.isEmpty()){
            return null;
        }
        else {
//            System.out.println(set.size());
            //randomly select a variable from the set
            int index=(int) (Math.random()*set.size());
            Variable temp= set.iterator().next();
            while (index>0){
                if(set.iterator().hasNext()){
                    temp=set.iterator().next();
                }
                index--;
            }
            set.remove(temp);
            return temp;
        }
    }

    @Override
    public void add(Variable var) {
        set.add(var);
    }
}
