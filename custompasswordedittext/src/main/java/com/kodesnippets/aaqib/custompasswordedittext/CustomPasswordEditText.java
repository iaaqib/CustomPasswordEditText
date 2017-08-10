package com.kodesnippets.aaqib.custompasswordedittext;

import android.content.Context;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * Created by silen on 8/5/2017.
 */

public class CustomPasswordEditText extends LinearLayout {
    //Properties
    private EditText editText;
    private Button button;
    private LinearLayout linearLayout;
    private String onShow;
    private String onHide;
    private int onHideStateResourceId;
    private int onShowStateResourceId;


    //Constructor
    public CustomPasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setup(context, attrs);
    }

    public Editable getText() {
        return editText.getText();
    }

    /***Getter and Setter Methods
     * for setting and getting Text, Hint, Text Color and Hint Color
     **/
    public void setText(String text) {
        editText.setText(text);

    }

    public CharSequence getHint() {
        return editText.getHint();
    }

    public void setHint(String text) {
        editText.setHint(text);

    }

    public void setTextColor(int color) {
        editText.setTextColor(color);
    }

    public ColorStateList getTextColor() {
        return editText.getTextColors();
    }

    public void setHintColor(int color) {
        editText.setHintTextColor(color);
    }

    public ColorStateList getHintColor() {
        return editText.getHintTextColors();
    }


    /***Setup the UI and Initializes everything
     **/
    private void setup(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomPasswordEditText);
        // Inflates the Linear Layout
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_password, this, true);


        this.editText = (EditText) findViewById(R.id.edit_text);
        this.button = (Button) findViewById(R.id.button);
        this.linearLayout = (LinearLayout) findViewById(R.id.linear_layout);


        String text = typedArray.getString(R.styleable.CustomPasswordEditText_text);
        onShowStateResourceId = typedArray.getResourceId(R.styleable.CustomPasswordEditText_onshowstate_background, R.drawable.white_color_border);
        onHideStateResourceId = typedArray.getResourceId(R.styleable.CustomPasswordEditText_onhidestate_background, R.drawable.red_color_border);
        String hint = typedArray.getString(R.styleable.CustomPasswordEditText_hint);
        onShow = typedArray.getString(R.styleable.CustomPasswordEditText_onshowstatetoggle_text);
        onHide = typedArray.getString(R.styleable.CustomPasswordEditText_onhidestatetoggle_text);
        final String specialCharacter = typedArray.getString(R.styleable.CustomPasswordEditText_password_mask);
        int textColor = typedArray.getColor(R.styleable.CustomPasswordEditText_text_color, Color.BLACK);
        int hintColor = typedArray.getColor(R.styleable.CustomPasswordEditText_hint_color, Color.rgb(128, 128, 128));

        setTextColor(textColor);
        setHintColor(hintColor);
        if (text != null) {
            this.setText(text);
        }
        if (hint != null) {
            this.setHint(hint);
        }

        if (onShow != null) {
            button.setText(onShow);
        }
        if ((onShow == null && onHide == null)) {
            onShow = "SHOW";
            onHide = "HIDE";
            button.setText(onShow);
        } else if (onShow == null) {
            onShow = "SHOW";
            button.setText(onShow);
        } else if (onHide == null) {
            onHide = "HIDE";
        }

        typedArray.recycle();

        this.hideState(specialCharacter);


        //Button On ClickListener
        this.button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentTitle = button.getText().toString();

                if (currentTitle.equals(onHide)) {
                    hideState(specialCharacter);
                } else {
                    showState();
                }
                button.setText(currentTitle.equals(onHide) ? onShow : onHide);


            }

        });
    }

    //Method for dealing with the Hide State
    private void hideState(String specialCharacter) {
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if (specialCharacter != null && !specialCharacter.isEmpty()) {
            char special = specialCharacter.charAt(0);
            editText.setTransformationMethod(new CustomMaskingCharacter(special));
        } else {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        linearLayout.setBackgroundResource(onShowStateResourceId);
    }

    //for dealing with Show State
    private void showState() {
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTransformationMethod(null);
        linearLayout.setBackgroundResource(onHideStateResourceId);
    }
}
