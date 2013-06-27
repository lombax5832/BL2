package BL2.tile;

public interface IMachine {

    public boolean isActive();

    public boolean manageLiquids();

    public boolean manageSolids();

    public boolean allowActions();

}