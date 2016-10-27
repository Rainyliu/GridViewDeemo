package com.example.rainy.gridviewdeemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

/**
 * Author: Rainy <br>
 * Description: GridViewDeemo <br>
 * Since: 2016/9/21 0021 下午 3:35 <br>
 */

public class SvgView extends View {
    String filename = "act_icon_label.svg";
    Bitmap newBM;
    public SvgView(Context context) throws SVGParseException, IOException {
        super(context);
        // Read an SVG from the assets folder
        SVG  svg = SVG.getFromAsset(getContext().getAssets(),filename);

        // Create a canvas to draw onto
        if(svg.getDocumentWidth() != -1){
            newBM = Bitmap.createBitmap((int)Math.ceil(svg.getDocumentWidth()),(int)Math.ceil(svg.getDocumentHeight()), Bitmap.Config.ARGB_8888);
        }

            Canvas bmcanvas = new Canvas(newBM);

            // Clear background to white
            bmcanvas.drawRGB(255, 255, 255);

            // Render our document onto our canvas
            svg.renderToCanvas(bmcanvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawBitmap(newBM,newBM.getWidth(),newBM.getHeight(),null);
    }
}
