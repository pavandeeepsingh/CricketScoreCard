import java.util.Scanner;

class CricketScoreCard {

    public static void printFinalResult(Team t1, Team t2) {
        int runDiff = t1.getRuns() - t2.getRuns();
        if(runDiff > 0) {
            System.out.println(t1.getName() + " Won by " + runDiff + " Runs!");
        } else {
            System.out.println(t2.getName() + " Won by " + (10 - t2.getWickets()) + " Wickets!");
        }
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Print number of players : ");
        int numberOfPlayers = in.nextInt();
        System.out.print("Print number of overs : ");
        int numberOfOvers = in.nextInt();
        Team t1 = new Team("Team 1");
        Team t2 = new Team("Team 2");
        System.out.println("Print batting order for " + t1.getName());
        Player[] playersTeam = new Player[numberOfPlayers];
        for( int i=0; i<numberOfPlayers; i++) {
            String playerName = in.next();
            playersTeam[i] = new Player(playerName);
        }
        t1.setPlayers(playersTeam);
        System.out.println("Print batting order for " + t2.getName());
        playersTeam = new Player[numberOfPlayers];
        for( int i=0; i<numberOfPlayers; i++) {
            String playerName = in.next();
            playersTeam[i] = new Player(playerName);
        }
        t2.setPlayers(playersTeam);
        SessionStats firstSession = new SessionStats(t1, t2);
        firstSession.startSession(numberOfOvers);
        firstSession.endSession();
        SessionStats secondSession = new SessionStats(t2, t1);
        secondSession.startSession(numberOfOvers);
        secondSession.endSession();
        printFinalResult(t1, t2);
    }
}