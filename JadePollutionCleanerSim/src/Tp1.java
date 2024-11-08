import java.util.ArrayList;
import java.util.List;
import jade.core.AID;
import jade.wrapper.AgentController;
import jade.wrapper.AgentContainer;

public class Tp1 {
    static Environment environment = new Environment(10);
     static List<AID> cleanerAgents = new ArrayList<>();

    public static void main(String[] args) {

        launchJADEPlatform(environment);

    }

    public static void launchJADEPlatform(Environment environment) {
        try {
            jade.core.Runtime rt = jade.core.Runtime.instance();
            jade.util.leap.Properties properties = new jade.util.leap.Properties();
            properties.setProperty("gui", "true");
            jade.core.ProfileImpl profile = new jade.core.ProfileImpl(properties);
            AgentContainer container = rt.createMainContainer(profile);
            container.start();
            AgentController polluterAgent = container.createNewAgent("PolluterAgent", "PolluterAgent",
                    new Object[] { environment, cleanerAgents });
            polluterAgent.start();
            for (int i = 1; i <= 4; i++) {
                int cleaningCost;
                switch (i) {
                case 1:
                    cleaningCost = 2;
                    break;
                case 2:
                    cleaningCost = 5;
                    break;
                case 3:
                    cleaningCost = 7;
                    break;
                case 4:
                    cleaningCost = 3;
                    break;
                default:
                    cleaningCost = 0; 
                }
                AgentController cleanerAgent = container.createNewAgent("CleanerAgent" + i, "CleanerAgent",
                        new Object[] { environment, cleaningCost });
                cleanerAgent.start();
                // Add AID of cleaner agent to the list
                cleanerAgents.add(new AID("CleanerAgent" + i, AID.ISLOCALNAME));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
