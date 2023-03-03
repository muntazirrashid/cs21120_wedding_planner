package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;
import uk.ac.aber.cs21120.wedding.interfaces.ISolver;

public class Solver implements ISolver {
    String[] guests;
    IPlan plan;
    IRules rules;

    public Solver(String[] guests, IPlan plan, IRules rules) {
        this.guests = guests;
        this.plan = plan;
        this.rules = rules;
    }
    /**
     * Attempt to solve the plan with the rules and guests provided in
     * the constructor. Will recurse.
     *
     * @return true if a solution was found, false if no solution was found.
     */
    @Override
    public boolean solve() {
        boolean result;
        for (int i =0; i< plan.getNumberOfTables(); i++){
            for (int j = plan.getGuestsAtTable(i).size(); j<plan.getSeatsPerTable(); j++){
                for (String k: guests) {
                    if (!(plan.isGuestPlaced(k))){
                        plan.addGuestToTable(i, k);
                        if(rules.isPlanOK(plan)){
                            result = solve();
                            if (result){
                                return true;
                            }
                        } plan.removeGuestFromTable(k);
                    }
                }
                return false;
            }
        }
        return true;

    }
}
