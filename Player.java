class Player {
    String name;
    Bowler bowled;
    Batsman batted;

    Player(String name) {
        this.name = name;
        this.batted = new Batsman();
        this.bowled = new Bowler();
    }

    BattingStatus getBattingStatus() {
        return batted.getStatus();
    }

    void setBattingStatus(BattingStatus status) {
        batted.setStatus(status);
    }

    void addRuns(int runs) {
        this.batted.addRuns(runs);
    }

    String getName() {
        return name;
    }

    int getRuns() {
        return batted.getRuns();
    }

    int getFours() {
        return batted.getFours();
    }

    int getSixes() {
        return batted.getSixes();
    }

    int getBalls() {
        return batted.getBalls();
    }

    void addBall() {
        this.batted.addBall();
    }

}