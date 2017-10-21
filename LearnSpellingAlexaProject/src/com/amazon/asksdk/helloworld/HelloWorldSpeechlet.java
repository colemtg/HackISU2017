package com.amazon.asksdk.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import sun.applet.Main;

public class HelloWorldSpeechlet implements Speechlet
{
    // The user's guess
    private String userGuessSoFar = "";

    // The solution word
    private String wordToGuess = "";

    // The Word selected from the pool
    private Word currentWord;

    // Create the pool of available words
    public HelloWorldSpeechlet()
    {
        WordDriver.initializeWords();
    }

    private static final Logger log = LoggerFactory.getLogger(HelloWorldSpeechlet.class);

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException
    {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException
    {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return startSpellingLesson();
    }

    // Called whenever the user states an intent
    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException
    {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("GuessLetterIntent".equals(intentName))
        {
            return getLetterInWord(intent.getSlot("Letter").getValue());
        }
        else if ("GiveDefinitionIntent".equals(intentName))
        {
            return giveDefinition();
        }
        else if ("DoneIntent".equals(intentName))
        {
            return userIsDoneGuessingWord();
        }
        else if ("LearnSpellingIntent".equals(intentName))
        {
            return startSpellingLesson();
        }
        else if ("HelloWorldIntent".equals(intentName))
        {
            return getHelloResponse();
        }
        else if ("AMAZON.HelpIntent".equals(intentName))
        {
            return getHelpResponse();
        }
        else if ("AMAZON.StopIntent".equals(intentName))
        {
            return endLesson();
        }
        else
        {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException
    {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    // The user has decided to quit. Give them a good bye message.
    private SpeechletResponse endLesson()
    {
        final String speechText = "Thank you for spelling!";
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return SpeechletResponse.newTellResponse(speech);
    }

    // Tell the user how the skill works
    private SpeechletResponse startSpellingLesson()
    {
        final String speechText = "Okay, I am going to give you a list of words to spell. " +
                "If you can spell it right, I'll give you a harder word. " +
                "Otherwise, I'll give you something easier. " +
                "I will acknowledge each letter you say. " +
                "When you are done spelling the word, say done. " +
                "Let's begin! ";

        return getWordToSpell(speechText);
    }

    // Get a new word for the user to spell.
    private SpeechletResponse getWordToSpell(String speechText)
    {
        currentWord = Pool.generateWord();
        wordToGuess = currentWord.getWord();

        final String wordToSpell = "..Please spell, " + wordToGuess + " for me.";
        speechText += wordToSpell;

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(wordToSpell);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }


    // Called when the user guesses a single letter
    // If Alexa thought the response was a word (not a letter),
    // this method will ask the user to repeat the letter
    private SpeechletResponse getLetterInWord(String letter)
    {
        String speechText = "Next?"; // Alexa asks for the next letter?

        if(letter.length() > 2 || (letter.length() == 2 && letter.charAt(1) != '.'))
        {
            speechText = "Sorry, can you repeat that last letter?";
        }
        else
        {
            userGuessSoFar += letter;
        }

        SimpleCard card = new SimpleCard();
        card.setTitle("Guess");
        card.setContent("The guess was: " + letter);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    // The user has finished saying the letters of the word.
    // Check to see if they got the word right.
    // If they got it wrong, give the correct spelling
    private SpeechletResponse userIsDoneGuessingWord()
    {
        String temp = "";

        // Traverse the user's guess. Remove all non-letters.
        for(int i=0; i<userGuessSoFar.length(); i++)
        {
            if(Character.isLetter(userGuessSoFar.charAt(i)))
            {
                temp += userGuessSoFar.substring(i, i+1);
            }
        }

        wordToGuess = wordToGuess.toLowerCase();
        temp = temp.toLowerCase();
        final boolean userGotWordCorrect = wordToGuess.compareTo(temp) == 0;

        String speechText = userGotWordCorrect ? "Correct. Good Job!" : "Incorrect, " + currentWord.getWord() + " is spelled, ";

        // If the user got the word wrong, tell them how to spell it.
        if(!userGotWordCorrect)
        {
            for(int i=0; i<wordToGuess.length(); i++)
            {
                speechText += wordToGuess.toUpperCase().substring(i, i+1) + ". ";
            }
            speechText += "..";
        }

        userGuessSoFar = ""; // Reset the user's guesses
        Pool.update(currentWord, userGotWordCorrect);
        return getWordToSpell(speechText); // Start with a new word again
    }

    // Say hello to the user
    private SpeechletResponse getHelloResponse()
    {
        String speechText = "Hello hack I S U";

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech);
    }

    // If the user doesn't know what the current word is
    private SpeechletResponse getHelpResponse()
    {
        final String speechText = "The current word to spell is " + currentWord.getWord();

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }

    // Tell the user the definition
    private SpeechletResponse giveDefinition()
    {
        final String speechText = "The definition of " + currentWord.getWord() + " is " + currentWord.getDefinitions();

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }
}
