package com.david.lamp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.david.lamp.colors.Hue;
import com.david.lamp.colors.RGB;
import com.david.lamp.colors.Transition;

/**
 * Created by david on 6/7/17.
 */

public class HueView extends View {

    private Paint textPaint;
    private Paint colorPaint;
    private Paint arrowPaint;

    private Hue hue;

    private final int PADDING = 0;
    // the size of each colored bubble
    private final int CIRCLE_RAD = 75;

    // arrow properties
    private final int ARROW_LENGTH = 20;
    private final int ARROW_HEIGHT = 20;
    private final int STROKE = 8;

    // the margin between each bubble/arrow
    private final int MARGIN = 20;

    private final String START = "START";
    private final float TEXTSIZE = 50;

    public HueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(hue != null) {
            // get the height of the canvas
            int height = canvas.getHeight() - PADDING;
            int width = canvas.getWidth() - PADDING;

            // the x value of the circle that is to be drawn
            int currentX = CIRCLE_RAD + PADDING;
            int currentY = height / 2;

            // iterate through the colors and draw each one
            for (int i = 0; i < hue.colors.size(); i++) {
                RGB color = hue.colors.get(i);
                int currentColor = Color.rgb(color.red, color.green, color.blue);
                colorPaint.setColor(currentColor);
                // draw the circle
                canvas.drawCircle(currentX, currentY, CIRCLE_RAD, colorPaint);
                currentX += CIRCLE_RAD + MARGIN;

                // draw the arrow
                Transition interval = hue.transitions.get(i);
                //generate the path
                Path path = generateArrowPath(currentX, currentY);
                canvas.drawPath(path, arrowPaint);

                //move to start of next circle
                currentX += CIRCLE_RAD + ARROW_LENGTH + MARGIN;

            }
            currentX -= CIRCLE_RAD;
            Rect bounds = new Rect();
            textPaint.getTextBounds(START, 0, START.length(), bounds);
            canvas.drawText(START, currentX, currentY + bounds.height()/2, textPaint);
        }
    }

    public Path generateArrowPath(int currentX, int currentY) {
        Path path = new Path();
        int y1 = currentY - ARROW_HEIGHT;
        int y2 = currentY;
        int y3 = currentY + ARROW_HEIGHT;

        path.moveTo(currentX, y1);
//        path.lineTo(currentX + ARROW_LENGTH, CIRCLE_RAD);
//        path.lineTo(currentX + ARROW_LENGTH - ARROW_HEIGHT, CIRCLE_RAD - ARROW_HEIGHT);
//        path.moveTo(currentX + ARROW_LENGTH + ARROW_HEIGHT / 2, CIRCLE_RAD);
//        path.lineTo(currentX + ARROW_LENGTH - ARROW_HEIGHT, CIRCLE_RAD + ARROW_HEIGHT);
        path.lineTo(currentX + ARROW_LENGTH, y2);
        path.lineTo(currentX, y3);
        path.lineTo(currentX, y1);
        return path;
    }

    public void setHue(Hue hue) {
        this.hue = hue;
    }

    private void init() {
        // create the paints to be used with the canvas
        colorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        arrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arrowPaint.setColor(Color.BLACK);
        arrowPaint.setStyle(Paint.Style.FILL);
        arrowPaint.setStrokeWidth(STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(TEXTSIZE);
    }

}
