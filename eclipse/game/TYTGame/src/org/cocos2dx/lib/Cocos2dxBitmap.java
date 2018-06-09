package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;

public class Cocos2dxBitmap {
    private static final int HORIZONTALALIGN_CENTER = 3;
    private static final int HORIZONTALALIGN_LEFT = 1;
    private static final int HORIZONTALALIGN_RIGHT = 2;
    private static final int VERTICALALIGN_BOTTOM = 2;
    private static final int VERTICALALIGN_CENTER = 3;
    private static final int VERTICALALIGN_TOP = 1;
    private static Context _context;

    private static class TextProperty {
        private final int mHeightPerLine;
        private final String[] mLines;
        private final int mMaxWidth;
        private final int mTotalHeight;

        TextProperty(int i, int i2, String[] strArr) {
            this.mMaxWidth = i;
            this.mHeightPerLine = i2;
            this.mTotalHeight = strArr.length * i2;
            this.mLines = strArr;
        }
    }

    private static TextProperty computeTextProperty(String str, int i, int i2, Paint paint) {
        FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int ceil = (int) Math.ceil((double) (fontMetricsInt.bottom - fontMetricsInt.top));
        String[] splitString = splitString(str, i, i2, paint);
        if (i == 0) {
            i = 0;
            for (String str2 : splitString) {
                int ceil2 = (int) Math.ceil((double) paint.measureText(str2, 0, str2.length()));
                if (ceil2 > i) {
                    i = ceil2;
                }
            }
        }
        return new TextProperty(i, ceil, splitString);
    }

    private static int computeX(String str, int i, int i2) {
        switch (i2) {
            case 2:
                return i;
            case 3:
                return i / 2;
            default:
                return 0;
        }
    }

    private static int computeY(FontMetricsInt fontMetricsInt, int i, int i2, int i3) {
        int i4 = -fontMetricsInt.top;
        if (i <= i2) {
            return i4;
        }
        switch (i3) {
            case 1:
                return -fontMetricsInt.top;
            case 2:
                return (-fontMetricsInt.top) + (i - i2);
            case 3:
                return (-fontMetricsInt.top) + ((i - i2) / 2);
            default:
                return i4;
        }
    }

    public static void createTextBitmap(String str, String str2, int i, int i2, int i3, int i4) {
        createTextBitmapShadowStroke(str, str2, i, 1.0f, 1.0f, 1.0f, i2, i3, i4, false, 0.0f, 0.0f, 0.0f, 0.0f, false, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static boolean createTextBitmapShadowStroke(String str, String str2, int i, float f, float f2, float f3, int i2, int i3, int i4, boolean z, float f4, float f5, float f6, float f7, boolean z2, float f8, float f9, float f10, float f11) {
        int i5 = i2 & 15;
        int i6 = (i2 >> 4) & 15;
        String refactorString = refactorString(str);
        Paint newPaint = newPaint(str2, i, i5);
        if (i3 == 0 || ((int) Math.ceil((double) newPaint.measureText(refactorString, 0, 1))) <= i3) {
            newPaint.setARGB(255, (int) (0.0d * ((double) f)), (int) (0.0d * ((double) f2)), (int) (0.0d * ((double) f3)));
            TextProperty computeTextProperty = computeTextProperty(refactorString, i3, i4, newPaint);
            int access$000 = i4 == 0 ? computeTextProperty.mTotalHeight : i4;
            if (computeTextProperty.mMaxWidth == 0 || access$000 == 0) {
                Log.w("createTextBitmapShadowStroke warning:", "textProperty MaxWidth is 0 or bitMapTotalHeight is 0\n");
                return false;
            }
            Bitmap createBitmap = Bitmap.createBitmap(computeTextProperty.mMaxWidth + ((int) 0.0f), access$000 + ((int) 0.0f), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            FontMetricsInt fontMetricsInt = newPaint.getFontMetricsInt();
            if (z2) {
                Paint newPaint2 = newPaint(str2, i, i5);
                newPaint2.setStyle(Style.STROKE);
                newPaint2.setStrokeWidth(f11);
                newPaint2.setARGB(255, (int) (255.0f * f8), (int) (255.0f * f9), (int) (255.0f * f10));
                i6 = computeY(fontMetricsInt, i4, computeTextProperty.mTotalHeight, i6);
                for (String str3 : computeTextProperty.mLines) {
                    int computeX = computeX(str3, computeTextProperty.mMaxWidth, i5);
                    canvas.drawText(str3, 0.0f + ((float) computeX), 0.0f + ((float) i6), newPaint2);
                    canvas.drawText(str3, ((float) computeX) + 0.0f, 0.0f + ((float) i6), newPaint);
                    i6 += computeTextProperty.mHeightPerLine;
                }
            } else {
                i6 = computeY(fontMetricsInt, i4, computeTextProperty.mTotalHeight, i6);
                for (String str4 : computeTextProperty.mLines) {
                    canvas.drawText(str4, 0.0f + ((float) computeX(str4, computeTextProperty.mMaxWidth, i5)), 0.0f + ((float) i6), newPaint);
                    i6 += computeTextProperty.mHeightPerLine;
                }
            }
            initNativeObject(createBitmap);
            return true;
        }
        Log.w("createTextBitmapShadowStroke warning:", "the input width is less than the width of the pString's first word\n");
        return false;
    }

    private static LinkedList<String> divideStringWithMaxWidth(String str, int i, Paint paint) {
        int length = str.length();
        LinkedList<String> linkedList = new LinkedList();
        int i2 = 1;
        int i3 = 0;
        while (i2 <= length) {
            int ceil = (int) Math.ceil((double) paint.measureText(str, i3, i2));
            if (ceil >= i) {
                int lastIndexOf = str.substring(0, i2).lastIndexOf(" ");
                if (lastIndexOf != -1 && lastIndexOf > i3) {
                    linkedList.add(str.substring(i3, lastIndexOf));
                    i2 = lastIndexOf + 1;
                } else if (ceil > i) {
                    linkedList.add(str.substring(i3, i2 - 1));
                    i2--;
                } else {
                    linkedList.add(str.substring(i3, i2));
                }
                while (i2 < length && str.charAt(i2) == ' ') {
                    i2++;
                }
                i3 = i2;
            }
            i2++;
        }
        if (i3 < length) {
            linkedList.add(str.substring(i3));
        }
        return linkedList;
    }

    private static int getFontSizeAccordingHeight(int i) {
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTypeface(Typeface.DEFAULT);
        int i2 = 0;
        int i3 = 1;
        while (i2 == 0) {
            paint.setTextSize((float) i3);
            paint.getTextBounds("SghMNy", 0, "SghMNy".length(), rect);
            i3++;
            if (i - rect.height() <= 2) {
                i2 = 1;
            }
            Log.d("font size", "incr size:" + i3);
        }
        return i3;
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] bArr = new byte[((bitmap.getWidth() * bitmap.getHeight()) * 4)];
        Buffer wrap = ByteBuffer.wrap(bArr);
        ((ByteBuffer) wrap).order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer(wrap);
        return bArr;
    }

    private static String getStringWithEllipsis(String str, float f, float f2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(f2);
        return TextUtils.ellipsize(str, textPaint, f, TruncateAt.END).toString();
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels != null) {
            nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
        }
    }

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    private static Paint newPaint(String str, int i, int i2) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setTextSize((float) i);
        paint.setAntiAlias(true);
        if (str.endsWith(".ttf")) {
            try {
                paint.setTypeface(Cocos2dxTypefaces.get(_context, str));
            } catch (Exception e) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + str);
                paint.setTypeface(Typeface.create(str, 0));
            }
        } else {
            paint.setTypeface(Typeface.create(str, 0));
        }
        switch (i2) {
            case 2:
                paint.setTextAlign(Align.RIGHT);
                break;
            case 3:
                paint.setTextAlign(Align.CENTER);
                break;
            default:
                paint.setTextAlign(Align.LEFT);
                break;
        }
        return paint;
    }

    private static String refactorString(String str) {
        if (str.compareTo("") == 0) {
            return " ";
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        int i = 0;
        int indexOf = stringBuilder.indexOf("\n");
        while (indexOf != -1) {
            if (indexOf == 0 || stringBuilder.charAt(indexOf - 1) == '\n') {
                stringBuilder.insert(i, " ");
                i = indexOf + 2;
            } else {
                i = indexOf + 1;
            }
            if (i > stringBuilder.length() || indexOf == stringBuilder.length()) {
                break;
            }
            indexOf = stringBuilder.indexOf("\n", i);
        }
        return stringBuilder.toString();
    }

    public static void setContext(Context context) {
        _context = context;
    }

    private static String[] splitString(String str, int i, int i2, Paint paint) {
        int i3 = 0;
        String[] split = str.split("\\n");
        FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int ceil = i2 / ((int) Math.ceil((double) (fontMetricsInt.bottom - fontMetricsInt.top)));
        LinkedList linkedList;
        if (i != 0) {
            linkedList = new LinkedList();
            int length = split.length;
            while (i3 < length) {
                String str2 = split[i3];
                if (((int) Math.ceil((double) paint.measureText(str2))) > i) {
                    linkedList.addAll(divideStringWithMaxWidth(str2, i, paint));
                } else {
                    linkedList.add(str2);
                }
                if (ceil > 0 && linkedList.size() >= ceil) {
                    break;
                }
                i3++;
            }
            if (ceil > 0 && linkedList.size() > ceil) {
                while (linkedList.size() > ceil) {
                    linkedList.removeLast();
                }
            }
            split = new String[linkedList.size()];
            linkedList.toArray(split);
            return split;
        } else if (i2 == 0 || split.length <= ceil) {
            return split;
        } else {
            linkedList = new LinkedList();
            while (i3 < ceil) {
                linkedList.add(split[i3]);
                i3++;
            }
            split = new String[linkedList.size()];
            linkedList.toArray(split);
            return split;
        }
    }
}