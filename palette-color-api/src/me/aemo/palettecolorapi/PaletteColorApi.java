package me.aemo.palettecolorapi;

import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.util.MediaUtil;

import java.io.IOException;

public class PaletteColorApi extends AndroidNonvisibleComponent implements Component {

    // Default Colors
    private int dvc = 0x000000;
    private int dlvc = 0x000000;
    private int ddvc = 0x000000;
    private int dmc = 0x000000;
    private int dlmc = 0x000000;
    private int ddmc = 0x000000;
    private int ddc = 0x000000;

    // Max Colors
    private int mcc = 16;

    public PaletteColorApi(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER, defaultValue = "16")
    public void MaximumColorCount(int mcc) {
        this.mcc = mcc;
    }

    @SimpleProperty
    public int MaximumColorCount() {
        return mcc;
    }

    // VIBRANT
    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_DEFAULT)
    public void DefaultVibrantColor(int dvc) {
        this.dvc = dvc;
    }

    @SimpleProperty
    public int DefaultVibrantColor() {
        return dvc;
    }

    // LIGHT VIBRANT
    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_DEFAULT)
    public void DefaultLightVibrantColor(int dlvc) {
        this.dlvc = dlvc;
    }

    @SimpleProperty
    public int DefaultLightVibrantColor() {
        return dlvc;
    }

    // DARK VIBRANT
    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_DEFAULT)
    public void DefaultDarkVibrantColor(int ddvc) {
        this.ddvc = ddvc;
    }

    @SimpleProperty
    public int DefaultDarkVibrantColor() {
        return ddvc;
    }

    // MUTED
    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_DEFAULT)
    public void DefaultMutedColor(int dmc) {
        this.dmc = dmc;
    }

    @SimpleProperty
    public int DefaultMutedColor() {
        return dmc;
    }

    // LIGHT MUTED
    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_DEFAULT)
    public void DefaultLightMutedColor(int dlmc) {
        this.dlmc = dlmc;
    }

    @SimpleProperty
    public int DefaultLightMutedColor() {
        return dlmc;
    }

    // DARK MUTED
    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_DEFAULT)
    public void DefaultDarkMutedColor(int ddmc) {
        this.ddmc = ddmc;
    }

    @SimpleProperty
    public int DefaultDarkMutedColor() {
        return ddmc;
    }

    // DOMINANT
    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_DEFAULT)
    public void DefaultDominantColor(int ddc) {
        this.ddc = ddc;
    }

    @SimpleProperty
    public int DefaultDominantColor() {
        return ddc;
    }


    @SimpleFunction
    public void Extract(String imgPath) {
        try {

            Palette.from(MediaUtil.getBitmapDrawable(form, imgPath).getBitmap())
                    .maximumColorCount(mcc)
                    .generate(new Palette.PaletteAsyncListener() {

                        @Override
                        public void onGenerated(@Nullable Palette palette) {
//            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//            Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
//            Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
//            Palette.Swatch mutedSwatch = palette.getMutedSwatch();
//            Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
//            Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
//            Palette.Swatch dominantSwatch = palette.getDominantSwatch();

                            OnExtracted(palette.getVibrantColor(dvc),
                                    palette.getLightVibrantColor(dlvc),
                                    palette.getDarkVibrantColor(ddvc),
                                    palette.getMutedColor(dmc),
                                    palette.getLightMutedColor(dlmc),
                                    palette.getDarkMutedColor(ddmc),
                                    palette.getDominantColor(ddc));
                        }
                    });
        } catch (IOException e) {
            OnErrorOccurred(e.getMessage(), "Extract");
        }

    }

    @SimpleEvent
    public void OnErrorOccurred(String error, String errorFrom) {
        EventDispatcher.dispatchEvent(this, "OnErrorOccurred", error, errorFrom);
    }

    @SimpleEvent
    public void OnExtracted(int vibrantColor, int lightVibrantColor, int darkVibrantColor, int mutedColor, int lightMutedColor, int darkMutedColor, int dominantColor) {
        EventDispatcher.dispatchEvent(this, "OnExtracted", vibrantColor, lightVibrantColor, darkVibrantColor, mutedColor, lightMutedColor, darkMutedColor, dominantColor);
    }

}
