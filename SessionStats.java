import java.util.Scanner;

class SessionStats {
    Team battingTeam;
    Team bowlingTeam;
    Player onStrikeBatsman;
    Player offStrikeBatsman;
    Player activeBowler;
    int currentOver;
    int currentBall;
    int runs;
    int wickets;
    int balls;
    int noBalls;
    int wides;
    int dotBalls;
    int fours;
    int sixes;

    public SessionStats(Team battingTeam, Team bowlingTeam) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.onStrikeBatsman = battingTeam.getBatsman();
        this.onStrikeBatsman.setBattingStatus(BattingStatus.PLAYING);
        this.offStrikeBatsman = battingTeam.getBatsman();
        this.offStrikeBatsman.setBattingStatus(BattingStatus.PLAYING);
        this.activeBowler = bowlingTeam.getBowler();
        this.currentOver = 0;
        this.currentBall = 0;
        this.runs = 0;
        this.wickets = 0;
        this.balls = 0;
        this.wides = 0;
        this.noBalls = 0;
        this.fours = 0;
        this.sixes = 0;
    }

    public void addExtraEvent(String event) {
        runs++;
        if(event == "wd") {
            wides++;
        } else {
            noBalls++;
        }
    }

    public void addRunEvent(int runs) {
        this.runs += runs;
        this.balls++;
        this.onStrikeBatsman.addRuns(runs);
        if(runs == 4) {
            this.fours++;
        } else if(runs == 6) {
            this.sixes++;
        } else if(runs == 0) {
            dotBalls++;
        }
        else if(runs % 2 == 1) {
            this.changeStrike();
        }
    }

    public void addWicketEvent() {
        this.wickets++;
        this.onStrikeBatsman.setBattingStatus(BattingStatus.OUT);
        this.onStrikeBatsman.addBall();
        this.onStrikeBatsman = this.battingTeam.getBatsman();
    }

    public int getWickets() {
        return this.wickets;
    }

    public void addBalls(int balls) {
        this.balls = balls;
    }

    public void changeStrike() {
        Player temp = onStrikeBatsman;
        onStrikeBatsman = offStrikeBatsman;
        offStrikeBatsman = temp;
    }

    public void startSession(int numberOfOvers) {
        Scanner in = new Scanner(System.in);
        int oversIndex = 1;
        while(oversIndex <= numberOfOvers) {
            System.out.println("Enter Over: " + oversIndex);
            int ballCount = 1;
            while(ballCount <= 6) {
                String event = in.next();
                if(event.equals("Wd") || event.equals("Nb")) {
                    addExtraEvent(event);
                } else if(event.equals("W")) {
                    addWicketEvent();
                    ballCount++;
                    if(getWickets() == (this.battingTeam.players.length-1)) {
                        break;
                    }
                } else {
                    int runs = Integer.parseInt(event);
                    addRunEvent(runs);
                    ballCount++;
                }
            }
            printStatusAfterOver();
            if(getWickets() == (this.battingTeam.players.length-1)) {
                System.out.println("Overs: " + (oversIndex-1) + "." + (ballCount-1));
                addBalls(ballCount);
                break;
            }
            System.out.println("Overs: " + oversIndex);
            addBalls(6);
            changeStrike();
            oversIndex++;
        }
        System.out.println("Batting End for : " + battingTeam.getName());
    }

    public void printStatusAfterOver() {
        System.out.println("Score Card for " + this.battingTeam.getName());
        Player[] players = this.battingTeam.getPlayers();
        System.out.println("Player Name    Score   4s  6s  B");
        for(int i=0; i<players.length; i++) {
            String activePlaying = players[i].getBattingStatus() == BattingStatus.PLAYING ? "*" : "";
            System.out.println(players[i].getName() + activePlaying + "      " +
                    players[i].getRuns() + "   " +
                    players[i].getFours() + "   " +
                    players[i].getSixes() + "   " +
                    players[i].getBalls());
        }
        System.out.println("Total: " + this.runs + "/" + this.wickets);
    }

    public void endSession() {
        this.battingTeam.runs = this.runs;
        this.battingTeam.sixes = this.sixes;
        this.battingTeam.fours = this.fours;
        this.battingTeam.wickets = this.wickets;
        this.battingTeam.wides = this.wides;
        this.battingTeam.noBalls = this.noBalls;
        this.battingTeam.ballsFaced = this.balls;
        this.battingTeam.dotBalls = this.dotBalls;
    }
}