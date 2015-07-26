import java.util.Scanner;

/* Not the best solution, but it works (simulating FSM) */

public class LaraCroft {

    public static class Lara {

        private static enum State{
            LEFT, RIGHT, UP, DOWN
        }


        public int i = 1;  /* coordinates */
        public int j = 1;
        public State state = State.RIGHT;

        public void turn() {
            switch (state) {
                case LEFT:
                    state = State.UP;
                    break;
                case RIGHT:
                    state = State.DOWN;
                    break;
                case UP:
                    state = State.RIGHT;
                    break;
                case DOWN:
                    state = State.LEFT;
                    break;
            }
        }

        public void move() {
            switch (state) {
                case LEFT:
                    j = j - 1;
                    break;
                case RIGHT:
                    j = j + 1;
                    break;
                case UP:
                    i = i - 1;
                    break;
                case DOWN:
                    i = i + 1;
                    break;
            }
        }

    }

    public static class Graveyard {

        public Lara lara;
        public byte graves[][];

        public Graveyard(int N, int M) {
            graves = new byte[N + 2][M + 2];
            for (int i = 0; i <= N + 1; i++) {
                graves[i][0]     = 1;
                graves[i][M + 1] = 1;
            }
            for (int i = 0; i <= M + 1; i++) {
                graves[0][i]     = 1;
                graves[N + 1][i] = 1;
            }

            lara = new Lara();
            graves[1][1] = 1;
        }

        public boolean canMove() {
            switch (lara.state) {
                case LEFT:
                    return graves[lara.i][lara.j - 1] == 0;
                case RIGHT:
                    return graves[lara.i][lara.j + 1] == 0;
                case UP:
                    return graves[lara.i - 1][lara.j] == 0;
                case DOWN:
                    return graves[lara.i + 1][lara.j] == 0;
            }
            return false;
        }


    }


    public static void main(String[] args) throws Exception{


        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int i1 = sc.nextInt();
        int j1 = sc.nextInt();
        int i2 = sc.nextInt();
        int j2 = sc.nextInt();

        Graveyard graveyard = new Graveyard(N, M);

        int days = 0;
        int day1 = -1;
        int day2 = -1;
        while (day1 == -1 || day2 == -1) {

            if (graveyard.lara.i == i1 && graveyard.lara.j == j1) {
                day1 = days;
            }
            if (graveyard.lara.i == i2 && graveyard.lara.j == j2) {
                day2 = days;
            }

            if (graveyard.canMove()) {
                days++;
                graveyard.lara.move();
                graveyard.graves[graveyard.lara.i][graveyard.lara.j] = 1;
            } else {
                graveyard.lara.turn();
            }
        }


        System.out.println(Math.abs(day1 - day2));

    }


}
