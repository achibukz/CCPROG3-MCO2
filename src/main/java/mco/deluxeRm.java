package mco;

public class deluxeRm extends Room{
    public deluxeRm(String roomID, double basePrice){
        super(roomID);
        this.Price = basePrice * 1.2;
        setAvail();
        setState();
    }
    
}
