package com.liu.localaddresslist;

import java.io.InputStream;
import java.util.ArrayList;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.text.TextUtils;

import com.liu.QRCode.R;

public class LocalAddresslist {
	/** ��ȡ��Phone���ֶ� **/
	private static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };
	/** ��ϵ����ʾ���� **/
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;
	/** �绰���� **/
	private static final int PHONES_NUMBER_INDEX = 1;
	/** ͷ��ID **/
	private static final int PHONES_PHOTO_ID_INDEX = 2;
	/** ��ϵ�˵�ID **/
	private static final int PHONES_CONTACT_ID_INDEX = 3;
	/** ��ϵ������ **/
	private ArrayList<String> mContactsName = new ArrayList<String>();
	/** ��ϵ��ͷ�� **/
	private ArrayList<String> mContactsNumber = new ArrayList<String>();
	/** ��ϵ��ͷ�� **/
	private ArrayList<Bitmap> mContactsPhoto = new ArrayList<Bitmap>();

	private Context context;

	public LocalAddresslist(Context context) {
		this.context = context;
	}

	public void getPhoneContacts() {
		ContentResolver resolver = context.getContentResolver();
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
				PHONES_PROJECTION, null, null, null);
		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
				// ���ֻ�����Ϊ�յĻ���Ϊ���ֶ� ������ǰѭ��
				if (TextUtils.isEmpty(phoneNumber))
					continue;
				// �õ���ϵ������
				String contactName = phoneCursor
						.getString(PHONES_DISPLAY_NAME_INDEX);
				if (TextUtils.isEmpty(contactName))
					contactName ="δ��������";
				// �õ���ϵ��ID
				Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);

				// �õ���ϵ��ͷ��ID
				Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

				// �õ���ϵ��ͷ��Bitmap
				Bitmap contactPhoto = null;
				// photoid ����0 ��ʾ��ϵ����ͷ�� ���û�и���������ͷ�������һ��Ĭ�ϵ�
				if (photoid > 0) {
					Uri uri = ContentUris.withAppendedId(
							ContactsContract.Contacts.CONTENT_URI, contactid);
					InputStream input = ContactsContract.Contacts
							.openContactPhotoInputStream(resolver, uri);
					contactPhoto = BitmapFactory.decodeStream(input);
				} else {
					contactPhoto = BitmapFactory.decodeResource(
							context.getResources(), R.drawable.notsethead);
				}

				mContactsName.add(contactName);
				mContactsNumber.add(phoneNumber);
				mContactsPhoto.add(contactPhoto);

			}
			phoneCursor.close();
		}

	}

	public ArrayList<String> getmContactsName() {
		return mContactsName;
	}

	public ArrayList<String> getmContactsNumber() {
		return mContactsNumber;
	}

	public ArrayList<Bitmap> getmContactsPhoto() {
		return mContactsPhoto;
	}
}
