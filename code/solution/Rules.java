package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Rules implements IRules {

    HashMap<String, Set<String>> friends = new HashMap<>();
    HashMap<String, Set<String>> enemies = new HashMap<>();

    /**
     * Add a rule that two guests must be seated at the same table.
     * Adding the same rule twice has no effect.
     *
     * @param a a guest
     * @param b another guest
     */
    @Override
    public void addMustBeTogether(String a, String b) {
        if (friends.get(a) != null) {
            friends.get(a).add(b);
        }
        else {
            Set<String> friend = new HashSet<>();
            friend.add(b);
            friends.put(a, friend);
        }
    }

    /**
     * Add a rule that two guests must never be seated at the same table.
     * Adding the same rule twice has no effect.
     *
     * @param a a guest
     * @param b another guest
     */
    @Override
    public void addMustBeApart(String a, String b) {
        if (enemies.get(a) != null) {
            enemies.get(a).add(b);
        }
        else {
            Set<String> enemy = new HashSet<>();
            enemy.add(b);
            enemies.put(a, enemy);
        }
    }

    /**
     * Return true if the given plan does not break any of the stored rules.
     *
     * @param p a plan
     * @return true if the plan is OK, false if it breaks rules.
     */
    @Override
    public boolean isPlanOK(IPlan p) {
        for (int i = 0; i < p.getNumberOfTables(); i++) {
            for (String j : p.getGuestsAtTable(i)) {
                if (enemies.get(j) != null) {
                    for (String k : p.getGuestsAtTable(i)) {
                        if (enemies.get(j).contains(k)) {
                            return false;
                        }
                    }
                }
                if (p.getGuestsAtTable(i).size() > p.getSeatsPerTable()-1) {
                    if (friends.get(j) != null) {
                        for (String k : friends.get(j)) {
                            if (!(p.getGuestsAtTable(i).contains(k))) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
