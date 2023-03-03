package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;

import java.util.*;

public class Plan implements IPlan {
    int numberOfTables, seatsPerTable;
    Set<String> guests;
    HashMap<Integer, Set<String>> guestsAtTable;

    public Plan(int numberOfTables, int seatsPerTable){
        this.numberOfTables = numberOfTables;
        this.seatsPerTable = seatsPerTable;
        this.guests = new HashSet<>();
        this.guestsAtTable = new HashMap<>();
    }

    /**
     * return the number of seats per table. All tables have the same number of seats.
     * This doesn't change once the Plan has been created.
     *
     * @return the number of seats, a positive non-zero integer
     */
    @Override
    public int getSeatsPerTable() {
        return seatsPerTable;
    }

    /**
     * Return the number of tables.
     * This doesn't change once the Plan has been created.
     *
     * @return the number of tables, a positive non-zero integer
     */
    @Override
    public int getNumberOfTables() {
        return numberOfTables;
    }

    /**
     * Add a guest to a table. If the guest is already seated at any table it will
     * do nothing. If the table is already full (i.e. the number of guests at that table is
     * equal to getSeatsPerTable()) it will do nothing. If the table number is less than zero,
     * or greater than or equal to getNumberOfTables(), it will raise IndexOutOfBoundsException.
     *
     * @param table the table number
     * @param guest the name of the guest
     */
    @Override
    public void addGuestToTable(int table, String guest) throws IndexOutOfBoundsException{
            if (guests.add(guest)) {
                if (guestsAtTable.get(table) != null) {
                    if (guestsAtTable.get(table).size() < seatsPerTable) {
                        guestsAtTable.get(table).add(guest);
                    }
                } else {
                    Set<String> guestNames = new HashSet<>();
                    guestNames.add(guest);
                    guestsAtTable.put(table, guestNames);
                }
            }
    }

    /**
     * Remove a guest from any table they are sitting at. If the guest is not at any
     * table, it will do nothing.
     *
     * @param guest the name of the guest
     */
    @Override
    public void removeGuestFromTable(String guest) {
        if (guests.contains(guest)){
            guests.remove(guest);
            for (Integer i : guestsAtTable.keySet()) {
                if (guestsAtTable.get(i).contains(guest)){
                    guestsAtTable.get(i).remove(guest);
                }
            }
        }
    }

    /**
     * Return whether a guest is sitting at any table.
     *
     * @param guest the name of the guest
     * @return true if the guest is at a table, false otherwise
     */
    @Override
    public boolean isGuestPlaced(String guest) {
        return guests.contains(guest);
    }

    /**
     * Return a set of the guests seated at a particular table. If the
     * table number of out of range it will raise IndexOutOfBoundsException.
     *
     * @param t the table number
     * @return a set of strings - the guests at that table
     */
    @Override
    public Set<String> getGuestsAtTable(int t) throws IndexOutOfBoundsException{
            if (guestsAtTable.get(t) != null) {
                return guestsAtTable.get(t);
            }
        Set<String> guestNames = new HashSet<>();
        return guestNames;
    }

    /**
     * To string method
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<getNumberOfTables();i++){
            Set<String> t = getGuestsAtTable(i);
            sb.append('(');
            List<String> list = new ArrayList<String>(t);
            sb.append(String.join(",", list));
            sb.append(") ");
        }
        return sb.toString();
    }
}
