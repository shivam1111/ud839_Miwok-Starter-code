package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    // This is on audo focus change listener
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS ) {
                        // Permanent loss of audio focus
                        // stop playback and release resources
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mediaPlayer.start();
                    }
                }
            };


    private AudioManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request the focus
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

/**
        //TODO  Add Words
        String[] words = new String[10];
        words[0] = "one";
        words[1] = "two";
        words[2] = "three";
        words[3] = "four";
        words[4] = "five";
        words[5] = "six";
        words[6] = "seven";
        words[7] = "eight";
        words[8] = "nine";
        words[9] = "ten";
        Log.v("NumbersActivity","The word at 0: "+words[0]); */

/** THis is a sample code for using the default arrayAdapter
        ArrayList<String> listWord = new ArrayList<String>();
        for (int i=0;i<words.length;i++){
            listWord.add(words[i]);
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter); */

/*  keeping this code as an example while we switch over to ListView & ArrayAdapter


        // Create a variable to keep track of the current index position
        int index = 0;
        // Find the root view so we can add child views to it
        LinearLayout rootview = (LinearLayout) findViewById(R.id.number_root_view);

        // Keep looping until we've reached the end of the list (which means keep looping
        // as long as the current index position is less than the length of the list)
        while (index < listWord.size()){
            // Create a new TextView
            TextView view = new TextView(this);
            // Set the text to be word at the current index
            view.setText(listWord.get(index));
            // Add this TextView as another child to the root view of this layout
            rootview.addView(view);
            // Increment the index variable by 1
            index++;
        }  */

        /* We use final keyword because words ArrayList is being used in AdapterView.OnItemClickListener()
           This is an anonymous class in which we can only refere global scope variable or local variable
           if they are used with 'final' keyword
         */

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","ottiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four  ));
        words.add(new Word("five","massoka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmoka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawita",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));

        WordAdapter itemAdapter = new WordAdapter(this,words,R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currenWord = words.get(i);
                Log.v("NumbersActivity", words.toString());
                releaseMediaPlayer();
                // Request Audio Focus

                int result = am.requestAudioFocus(mOnAudioFocusChangeListener,
                        //Use the music stream
                        AudioManager.STREAM_MUSIC,
                        //Use permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    // We have audio focus

                    //Create and setup the {@link MediaPlayer} for the audo resource and associated with word
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this,currenWord.getAudioResourceId());
                    //Start the audio file
                    mediaPlayer.start();
                    //Setup the listener on media player, so that we can stop and release the media player once
                    //the sound has finished playing
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });

    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            // Abandon audio focus when playback complete
            am.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    /*
    We can override super methods - On Windows, the keyboard shortcut is ALT + Insert.
    On Mac, the keyboard shortcut is CMD + N.
    Then select override method option or Directly type ctrl + O
     */

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
