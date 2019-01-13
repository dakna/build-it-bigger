package app.knapp.lib;

import java.util.Random;

public class JokeRepository {

    private String[] jokeArray = {
            "What is the hardest part in skydiving? The ground",
            "Dad, did you get a haircut? No I got them all cut.",
            "What do you call a Mexican who has lost his car? Carlos.",
            "What do you call a fake noodle? An Impasta.",
            "Why did the blonde stare at the orange juice container?  It said concentrate!",
            "Why did the octopus beat the shark in a fight? Because it was well armed.",
            "You know, people say they pick their nose, but I feel like I was just born with mine."

    };

    public String getJoke() {

        int randomIndex = new Random().nextInt(jokeArray.length);
        return jokeArray[randomIndex];

    }
}
