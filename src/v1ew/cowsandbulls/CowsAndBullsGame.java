package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 23.06.2016.
 */
public class CowsAndBullsGame {
    public CowsAndBullsGame() {
    }

    public static int start(String number) {
        String guess;
        System.out.println("Number is: " + number);
        Master master = new Master(number);
        Guesser guesser = new Guesser();
        int answer;
        int counter = 0;
        do {
            guess = guesser.guess();
            answer = master.ask(guess);
            guesser.saveAnswer(guess, answer);
            System.out.println(++counter + ". Guess is: " + guess +
                    ", answer is: cows - " + Master.cows(answer) + ", bulls - " + Master.bulls(answer));
        } while(answer < 40);
        return counter;
    }
}
