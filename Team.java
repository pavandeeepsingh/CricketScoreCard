class Team {
    String name;
    Player[] players;
    int runs;
    int sixes;
    int fours;
    int wickets;
    int wides;
    int noBalls;
    int ballsFaced;
    int ballsBawled;
    int dotBalls;
    int activeBowler;

    public Team(String name) {
        this.name = name;
        this.activeBowler = -1;
    }

    String getName() {
        return this.name;
    }
    void setPlayers(Player[] players) {
        this.players = players;
    }

    Player[] getPlayers() {
        return this.players;
    }

    Player getBatsman() {
        for(int i=0; i<this.players.length; i++) {
            if(this.players[i].getBattingStatus() == BattingStatus.NOT_PLAYED) {
                this.players[i].setBattingStatus(BattingStatus.PLAYING);
                return this.players[i];
            }
        }
        return null;
    }

    Player getBowler() {
        this.activeBowler = (this.activeBowler + 1) % this.players.length;
        return players[activeBowler];
    }

    int getRuns() {
        return this.runs;
    }

    int getWickets() {
        return this.wickets;
    }
}