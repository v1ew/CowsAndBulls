package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 23.06.2016.
 */
public class CowsAndBullsGame {
    public CowsAndBullsGame() {
    }

    public static int start(String number) {
        String guess;
        GuessStore guessStore = new GuessStore();
        System.out.println("Number is: " + number);
        Master master = new Master(number);
        Guesser guesser = new Guesser();
        int answer;
        int counter = 0;
        do {
            try {
                guess = guesser.guess(guessStore);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return -1;
            }
            answer = master.ask(guess);
            guessStore.saveGuess(guess, answer);
            System.out.println(++counter + ". Guess is: " + guess +
                    ", answer is: cows - " + Master.cows(answer) + ", bulls - " + Master.bulls(answer));
        } while(answer < 40);
        return counter;
    }

    public static void main(String[] args) {
        String number = "";
        CowsAndBullsGame game = new CowsAndBullsGame();
        if(args.length == 0 || !Helper.isCorrectNumber(args[0])) {
            Generator generator = new Generator(Guesser.NUMBER_LENGTH);
            number = generator.generate();
            game.usage();
        } else {
            number = args[0];
        }

        game.start(number);
    }

    public void usage() {
        System.out.println("Usage:");
        System.out.println("CowsAndBullsGame 1234");
        System.out.println("Out for example:");
    }

}
