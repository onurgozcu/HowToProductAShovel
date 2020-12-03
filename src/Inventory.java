import java.util.HashMap;

public class Inventory {
    HashMap<String, Integer> amountOnHand;
    HashMap<String, Integer> scheduledReceipt;
    HashMap<String, Integer> arrivalWeek;

    public HashMap<String, Integer> getAmountOnHand() {
        return amountOnHand;
    }

    public void setAmountOnHand(HashMap<String, Integer> amountOnHand) {
        this.amountOnHand = amountOnHand;
    }

    public HashMap<String, Integer> getScheduledReceipt() {
        return scheduledReceipt;
    }

    public void setScheduledReceipt(HashMap<String, Integer> scheduledReceipt) {
        this.scheduledReceipt = scheduledReceipt;
    }

    public HashMap<String, Integer> getArrivalWeek() {
        return arrivalWeek;
    }

    public void setArrivalWeek(HashMap<String, Integer> arrivalWeek) {
        this.arrivalWeek = arrivalWeek;
    }

    public int checkOnHand(String id){
        return amountOnHand.get(id);
    }

    public void addOnHand(String id, int number){
        amountOnHand.put(id,amountOnHand.get(id)+number);
    }

    public void resetFromHand(String id){
        amountOnHand.put(id,0);
    }

    public int checkReceipt(String id){
        return scheduledReceipt.get(id);
    }

    public void resetFromReceipt(String id){
        scheduledReceipt.put(id,0);
    }

    public int checkArrivalWeek(String id){
        return arrivalWeek.get(id);
    }

    public void resetArrivalWeek(String id){
        arrivalWeek.put(id,0);
    }
}
