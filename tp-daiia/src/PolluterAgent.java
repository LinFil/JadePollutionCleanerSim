import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PolluterAgent extends Agent {
    private Random random = new Random();
    private int gridSize = 10;
    private Environment environment;
    private List<Integer> pollutedCells = new ArrayList<>();
    

    // Default constructor
    public PolluterAgent() {
        // Initialize the cleanerAgents list
    	Tp1.cleanerAgents = new ArrayList<>();
    }

    // Constructor to initialize the PolluterAgent with a list of cleaner agents
    public PolluterAgent(List<AID> cleanerAgents) {
        Tp1.cleanerAgents = cleanerAgents;
    }

    protected void setup() {
        System.out.println("PolluterAgent setup started.");
        Object[] args = getArguments();
        if (args != null && args.length > 0 && args[0] instanceof Environment) {
            environment = (Environment) args[0];
        } else {
            System.err.println("Environment object not provided or invalid argument.");
            doDelete();
            return;
        }

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                int x, y;
                do {
                    x = random.nextInt(gridSize);
                    y = random.nextInt(gridSize);
                } while (pollutedCells.contains(x * gridSize + y)); 
                environment.markAsPolluted(x, y); 
                pollutedCells.add(x * gridSize + y); 
                sendPollutedCellMessage(x, y);
                sendProposalToCleaners(x, y); 
                block(1000);
            }
        });
        System.out.println("PolluterAgent setup completed.");
    }

    private void sendPollutedCellMessage(int x, int y) {
    	

        for (AID cleaner : Tp1.cleanerAgents) {
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.addReceiver(cleaner);
            String content = x + "," + y;
            message.setContent(content);
            send(message);
        }
    }
    
    private void sendProposalToCleaners(int x, int y) {
        ACLMessage proposal = new ACLMessage(ACLMessage.PROPOSE);
        proposal.setContent(x + "," + y);
        for (AID cleaner : Tp1.cleanerAgents) {
            proposal.addReceiver(cleaner);
        }
        send(proposal);
    }
}