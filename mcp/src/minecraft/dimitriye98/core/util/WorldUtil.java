package dimitriye98.core.util;

import java.util.ArrayList;
import java.util.LinkedList;

import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class WorldUtil
{
    public static Vec3[] getAdjacentCoordinates(World world, int x, int y, int z)
    {
        Vec3[] adjacentCoords = new Vec3[6];
        adjacentCoords[0] = Vec3.createVectorHelper(x - 1, y, z);
        adjacentCoords[1] = Vec3.createVectorHelper(x + 1, y, z);
        adjacentCoords[2] = Vec3.createVectorHelper(x, y - 1, z);
        adjacentCoords[3] = Vec3.createVectorHelper(x, y + 1, z);
        adjacentCoords[4] = Vec3.createVectorHelper(x, y, z - 1);
        adjacentCoords[5] = Vec3.createVectorHelper(x, y, z + 1);
        return adjacentCoords;
    }

    public static ArrayList<Vec3> floodGet(World world, int targetID, int x, int y, int z)
    {
        LinkedList<Vec3> Queue = new LinkedList<Vec3>();
        ArrayList<Vec3> out = new ArrayList<Vec3>();
        Queue.add(Vec3.createVectorHelper(x, y, z));

        while (!Queue.isEmpty())
        {
            Vec3 node = Queue.removeLast();

            if (world.getBlockId((int)node.xCoord, (int)node.yCoord, (int)node.zCoord) == targetID)
            {
                out.add(node);
                Vec3[] adjacent = getAdjacentCoordinates(world, (int)node.xCoord, (int)node.yCoord, (int)node.zCoord);

                for (Vec3 newNode : adjacent)
                {
                    if (!out.contains(newNode))
                    {
                        Queue.add(newNode);
                    }
                }
            }
        }

        return out;
    }
}
