package com.kodesnippets.aaqib.custompasswordedittext;

import android.text.method.PasswordTransformationMethod;
import android.view.View;


/**
 * Created by silen on 8/6/2017.
 */

public class CustomMaskingCharacter extends PasswordTransformationMethod {

    private char specialCharacter;

    public CustomMaskingCharacter(char specialCharacter) {

        this.specialCharacter = specialCharacter;
    }

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {

        return new PasswordSequence(source, specialCharacter);
    }

    private class PasswordSequence implements CharSequence {
        private CharSequence mSource;
        private char specialCharacter;

        public PasswordSequence(CharSequence source, char specialCharacter) {
            mSource = source; // Store char sequence
            this.specialCharacter = specialCharacter;
        }

        public char charAt(int index) {
            return specialCharacter; // This is the important part
        }

        public int length() {
            return mSource.length(); // Return default
        }

        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end); // Return default
        }
    }
}
