package dimitriye98.core.logic;

import java.util.ArrayList;

public class LogicMap {
	public static class LogicNode {
		private ILogicConnector connector;
		private LogicMap map;
		private ArrayList<LogicNode> connectedNodes;

		/**
		 * Constructor for LogicNode
		 * @param connector an implementation of ILogicConnector to wrap the LogicNode around
		 * @param map the LogicMap that this node is in
		 */
		LogicNode(ILogicConnector connector, LogicMap map) {
			this.connector = connector;
			this.map = map;
		}

		/**
		 * Calculates the connections for this node using ILogicConnector's
		 * calculateConnections method and stores them.
		 * @throws LogicHandlingException called if the LogicTypes mismatch between this node's map and a given node.
		 */
		public void calculateConnections() throws LogicHandlingException {
			ArrayList<ILogicConnector> temp = connector.calculateConnectedNodes();
			ArrayList<LogicNode> calculated = new ArrayList<LogicNode>();
			for (ILogicConnector connected : temp) {
				if (connected.getType() != map.getType()) {
					throw new LogicHandlingException("Mismatched logic types "+map.getType()+" and "+connected.getType());
				}
				LogicNode getResult = map.getNodeFromConnector(connected);
				calculated.add((getResult != null) ? getResult : new LogicNode(connected, map));
			}
		}

		public ILogicConnector getConnector() {
			return connector;
		}

		public ArrayList<LogicNode> getConnected() {
			return (ArrayList<LogicNode>)connectedNodes.clone();
		}
	}

	ArrayList<LogicNode> map;
	String type;

	public LogicNode getNodeFromConnector(ILogicConnector connector) {
		for (LogicNode node : map) {
			if (node.getConnector() == connector) {
				return node;
			}
		}
		return null;
	}

	public String getType() {
		return this.type;
	}
}
