package com.android.perarnaudalain;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.perarnaudalain.R;

public class MainActivity extends Activity {

	EditText urlText;
	ProgressBar pd;
	ImageView imgView;
	SampleResultReceiver resultReceiever;
	String defaultUrl = "https://developer.android.com/static/images/dac_logo.png";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		resultReceiever = new SampleResultReceiver(new Handler());
		urlText = (EditText) findViewById(R.id.urlText);
		pd = (ProgressBar) findViewById(R.id.downloadPD);
		imgView = (ImageView) findViewById(R.id.imgView);
	}

	private class SampleResultReceiver extends ResultReceiver {

		public SampleResultReceiver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			// TODO Auto-generated method stub
			switch (resultCode) {
			case SampleIntentService.DOWNLOAD_ERROR:
				Toast.makeText(getApplicationContext(), "error in download",
						Toast.LENGTH_SHORT).show();
				pd.setVisibility(View.INVISIBLE);
				break;

			case SampleIntentService.DOWNLOAD_SUCCESS:
				String filePath = resultData.getString("filePath");
				Bitmap bmp = BitmapFactory.decodeFile(filePath);
				if (imgView != null && bmp != null) {
					imgView.setImageBitmap(bmp);
					Toast.makeText(getApplicationContext(),
							"image download via IntentService is done",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"error in decoding downloaded file",
							Toast.LENGTH_SHORT).show();
				}
				pd.setIndeterminate(false);
				pd.setVisibility(View.INVISIBLE);

				break;
			}
			super.onReceiveResult(resultCode, resultData);
		}

	}

	public void onClick(View v) {
		Intent startIntent = new Intent(MainActivity.this,
				SampleIntentService.class);
		startIntent.putExtra("receiver", resultReceiever);
		startIntent.putExtra("url",
				TextUtils.isEmpty(urlText.getText()) ? defaultUrl : urlText
						.getText().toString());
		startService(startIntent);

		pd.setVisibility(View.VISIBLE);
		pd.setIndeterminate(true);
	}
}
