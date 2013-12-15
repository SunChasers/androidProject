package com.liu.encoding;

import java.util.Hashtable;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Encode {
	private int QR_WIDTH =100;
	private int QR_HEIGHT =100;
	private String qr_text;
	public Encode(String qr_text){
		this.qr_text =qr_text;
	}
	public Bitmap createImage() {
		Bitmap bitmap = null;
		try {
			// 需要引入core包
			QRCodeWriter writer = new QRCodeWriter();
			if (qr_text == null || "".equals(qr_text) || qr_text.length() < 1) {
				return null;
			}

			// 把输入的文本转为二维码
			BitMatrix martix = writer.encode(qr_text, BarcodeFormat.QR_CODE,
					QR_WIDTH, QR_HEIGHT);

			System.out.println("w:" + martix.getWidth() + "h:"
					+ martix.getHeight());

			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new QRCodeWriter().encode(qr_text,
					BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					} else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}

				}
			}

			 bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
					Bitmap.Config.ARGB_8888);

			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
