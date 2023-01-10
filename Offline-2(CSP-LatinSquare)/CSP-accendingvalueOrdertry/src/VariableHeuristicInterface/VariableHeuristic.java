package VariableHeuristicInterface;

import variable.Variable;

public interface VariableHeuristic {
    public abstract Variable getNextVariable();
    public abstract void add(Variable var);
}
