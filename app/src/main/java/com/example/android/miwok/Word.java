package com.example.android.miwok;

import android.content.Context;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;

    public Word(String defaultTranslation, String miwokTranslation, int AudioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = AudioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int AudioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = AudioResourceId;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setMiwokTranslation(String t) {
        mMiwokTranslation = t;
    }

    public void setDefaultTranslation(String t) {
        mDefaultTranslation = t;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != -1;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }


    /*
      use a keyboard shortcut to automatically “Generate a method (Getters, Setters, Constructors, toString, etc..)”.
      On Windows, the keyboard shortcut is ALT + Insert. On Mac, the keyboard shortcut is CMD + N.
      When we log the object of this class this method is automatically called.
      We can explicitly call it by object.toString();
     */
    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
