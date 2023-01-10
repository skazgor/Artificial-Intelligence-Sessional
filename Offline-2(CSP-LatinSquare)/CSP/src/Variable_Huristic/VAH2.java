package Variable_Huristic;

import variable.Variable;

import java.util.HashSet;

public class VAH2 extends VariableOrderHeuristic {
    private HashSet<Variable> set;
    public VAH2(Variable[][] variables){
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
    public Variable getNextVariable()
    {
        if(set.isEmpty()){
            return null;
        }
        else {
//            System.out.println(set.size());
            Variable max=null;
            for (Variable variable : set) {
                if(max==null){
                    max=variable;
                }else if(max.forwardDegree<variable.forwardDegree){
                    max=variable;
                }
            }
            set.remove(max);
            return max;
        }
    }

    @Override
    public void add(Variable var) {
        set.add(var);
    }
}

