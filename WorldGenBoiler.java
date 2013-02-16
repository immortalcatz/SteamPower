package boiler;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBoiler implements IWorldGenerator   //Implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, //This is the base generate method
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case 0:
                generateSurface(random, chunkX * 16, chunkZ * 16, world);

            case 1:
                generateNether(random, chunkX * 16, chunkZ * 16, world);

            case -1:
                generateEnd(random, chunkX * 16, chunkZ * 16, world);

            default:
                generateMod(random, chunkX * 16, chunkZ * 16, world);
        }
    }

    private void generateMod(Random random, int i, int j, World world)
    {
        // TODO Auto-generated method stub
    }

    private void generateEnd(Random random, int i, int j, World world)
    {
        // TODO Auto-generated method stub
    }

    private void generateNether(Random random, int i, int j, World world)
    {
        // TODO Auto-generated method stub
    }

    public void generateSurface(Random random, int x, int z, World w)
    {
        for (int i = 0; i < 35; i++) //This makes it gen multiple times in each chunk
        {
            int posX = x + random.nextInt(16); //X coordinate to gen at
            int posY = random.nextInt(64); //Y coordinate less than 40 to gen at
            int posZ = z + random.nextInt(16); //Z coordinate to gen at
            new WorldGenMinable(BoilerMod.copperOre.blockID, 6).generate(w, random, posX, posY, posZ); //The gen call
        }

        for (int i = 0; i < 30; i++) //This makes it gen multiple times in each chunk
        {
            int posX = x + random.nextInt(16); //X coordinate to gen at
            int posY = random.nextInt(64); //Y coordinate less than 40 to gen at
            int posZ = z + random.nextInt(16); //Z coordinate to gen at
            new WorldGenMinable(BoilerMod.zincOre.blockID, 5).generate(w, random, posX, posY, posZ); //The gen call
        }
    }
}
