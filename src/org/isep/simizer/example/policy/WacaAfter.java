/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isep.simizer.example.policy;

import java.util.List;
import simizer.LBNode;
import simizer.Node;
import simizer.ServerNode;
import simizer.policy.Policy;
import simizer.policy.PolicyAfterCallback;
import simizer.requests.Request;

/**
 *
 * @author isep
 */
public class WacaAfter implements Policy, PolicyAfterCallback {

    @Override
    public void initialize(List<ServerNode> availableNodes, LBNode lbn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addNode(Node n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeNode(Node n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Node loadBalance(Request r) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void printAdditionnalStats() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void receivedRequest(Node n, Request r) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}