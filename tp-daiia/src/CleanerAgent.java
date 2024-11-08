import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class CleanerAgent extends Agent {
    private Environment environment;
    private int cleaningCost;
    private int bestProposalCost = Integer.MAX_VALUE;

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0 && args[0] instanceof Environment && args[1] instanceof Integer) {
            environment = (Environment) args[0];
            cleaningCost = (Integer) args[1];
        } else {
            System.err.println("Invalid arguments provided to CleanerAgent.");
            doDelete();
            return;
        }

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null && msg.getPerformative() == ACLMessage.PROPOSE) {
                    String content = msg.getContent();
                    String[] coordinates = content.split(",");
                    if (coordinates.length != 2) {
                        // Reject proposals with invalid format
                        send(createResponse(msg, ACLMessage.REJECT_PROPOSAL, "Invalid proposal format"));
                        return;
                    }
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);

                    if (cleaningCost < bestProposalCost) {
                        // Accept proposal if the cost is smaller
                        bestProposalCost = cleaningCost;
                        send(createResponse(msg, ACLMessage.ACCEPT_PROPOSAL, "Accepted"));

                        
                        environment.markAsClean(x, y);
                        block(1000);
                    } else {
                        
                        send(createResponse(msg, ACLMessage.REJECT_PROPOSAL, "Rejected"));
                        block(1000);
                    }
                } else {
                	block(1000);
                }
            }
        });}

    private ACLMessage createResponse(ACLMessage originalMsg, int performative, String content) {
        ACLMessage response = new ACLMessage(performative);
        response.addReceiver(originalMsg.getSender());
        response.setContent(content);
        return response;
    }
}