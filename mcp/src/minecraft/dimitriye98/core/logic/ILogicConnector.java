package dimitriye98.core.logic;

import java.util.ArrayList;

import dimitriye98.core.logic.LogicMap.LogicNode;

public interface ILogicConnector {

	/**
	 * Gets the type of this logic connector
	 * Used to determine which LogicMaps it can connect to.
	 * @return the type of logic
	 */
	String getType();

	/**
	 * Data handler for passed logic data
	 * @param map the LogicMap passing the data
	 * @param data the data passed to the node
	 * @return was passing successful, will be returned to original passer
	 */
	boolean handlePassedData(LogicMap map, Object[] ... data);

	/**
	 * Allows for custom handling of data, for example in an EnderPipe
	 * @param map the LogicMap passing the data
	 * @param data the data passing through this node
	 * @return DEFAULT tells the LogicMap to continue with default handling,
	 * while the other two tell it whether or not the eventual handlePassedData
	 * returned true or false
	 */
	DataHandlingResult handlePassingData(LogicMap map, Object[] ... data);

	/**
	 * Called when the path forks. The return value will only be used if handlePassingData
	 * returns DEFAULT
	 * @param map the LogicMap passing the data
	 * @param from the preceding LogicNode
	 * @param data the data passing through this node
	 * @return the LogicNode to continue to
	 */
	LogicNode handleForkingData(LogicMap map, LogicNode from, Object[] ... data);

	/**
	 * Used to calculate connected notes for LogicMap construction.
	 * @return a list of adjacent nodes
	 */
	ArrayList<ILogicConnector> calculateConnectedNodes();

}
