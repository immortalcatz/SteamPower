package buildcraft.api;

public interface ILiquidContainer
{
    int fill(Orientations var1, int var2, int var3, boolean var4);

    int empty(int var1, boolean var2);

    int getLiquidQuantity();

    int getCapacity();

    int getLiquidId();
}
