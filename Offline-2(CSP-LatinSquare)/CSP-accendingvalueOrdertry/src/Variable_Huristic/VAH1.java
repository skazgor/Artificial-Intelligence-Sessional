package Variable_Huristic;

import variable.Variable;

import java.util.HashSet;

public class VAH1 extends VariableOrderHeuristic {
     private HashSet<Variable> set;

    public VAH1(Variable[][] variables){
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
            Variable min=null;
            for (Variable variable : set) {
                if(min==null){
                    min=variable;
                }else if(min.getNumberOfDomain()>variable.getNumberOfDomain()){
                    min=variable;
                }
            }
            set.remove(min);
            return min;
        }
    }

    @Override
    public void add(Variable var) {
        set.add(var);
    }
}

