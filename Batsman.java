enum BattingStatus { NOT_PLAYED, PLAYING, OUT };

class Batsman {
    int runs;
    int fours;
    int sixes;
    int balls;
    int dotBalls;
    BattingStatus status;

    Batsman() {
        this.runs = 0;
        this.fours = 0;
        this.sixes = 0;
        this.balls = 0;
        this.dotBalls = 0;
        this.status = BattingStatus.NOT_PLAYED;
    }
    BattingStatus getStatus() {
        return status;
    }

    void setStatus(BattingStatus status) {
        this.status = status;
    }

    void addRuns(int runs) {
        this.runs += runs;
        this.addBall();
        if(runs == 4) {
            fours++;
        } else if(runs == 6) {
            sixes++;
        } else if(runs == 0) {
            dotBalls++;
        }
    }

    void addBall() {
        this.balls++;
    }

    int getRuns() {
        return this.runs;
    }

    int getFours() {
        return this.fours;
    }

    int getSixes() {
        return this.sixes;
    }

    int getBalls() {
        return this.balls;
    }
}