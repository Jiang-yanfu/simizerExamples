package fr.isep.simizer.example.policy;

import fr.isep.simizer.network.MessageReceiver;
import fr.isep.simizer.nodes.Node;
import fr.isep.simizer.nodes.VM;
import fr.isep.simizer.requests.Request;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeastLoadedPolicy extends Policy.Callback {

  /** Stores the list of Nodes in the system. */
  private List<VM> nodes;

  /** Stores the current load for each Node. */
  private final Map<Integer, Integer> load = new ConcurrentHashMap<>();

  @Override
  public void initialize(List<VM> availableNodes) {
    nodes = new LinkedList(availableNodes);
    for (Node node : nodes) {
      load.put(node.getId(), 0);
    }
  }

  @Override
  public void addNode(VM vm) {
    nodes.add(vm);
    load.put(vm.getId(), 0);
  }

  @Override
  public void removeNode(VM vm) {
    nodes.remove(vm);
    load.remove(vm.getId());
  }

  @Override
  public MessageReceiver loadBalance(Request request) {
    Iterator<VM> iterator = nodes.iterator();
    Node target = iterator.next();

    // try to find a node with less load
    while (iterator.hasNext()) {
      Node next = iterator.next();
      if (load.get(next.getId()) < load.get(target.getId())) {
        target = next;
      }
    }

    // mark the Node we choose with an additional request
    load.put(target.getId(), load.get(target.getId()) + 1);

    return target;
  }

  @Override
  public void receivedRequest(VM node, Request request) {
    // when the request is finished, we need to update our tables
    load.put(node.getId(), load.get(node.getId()) - 1);
  }

}
