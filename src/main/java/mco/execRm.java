package mco;

public class execRm extends Room{
    public execRm(String roomID, double basePrice){
        super(roomID);
        this.Price = basePrice * 1.35;
        setAvail();
        setState();
    }
    
}
