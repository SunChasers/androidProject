package com.liu.sqlite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BinaryChang {

	// ��ͼƬת�����ֽ�
	public byte[] imageToBinary(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		try{
			baos.flush();
			baos.close();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	// �����ݿ��еĶ�����ת����ͼƬ
	public Bitmap binaryToImage(byte[] in) {
		Bitmap bitmap = BitmapFactory.decodeByteArray(in, 0, in.length);
		return bitmap;
	}
}
