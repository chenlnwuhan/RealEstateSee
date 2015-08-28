package com.sales.realestate.android.view;

import java.io.IOException;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import org.kymjs.kjframe.qrcode.camera.CameraManager;
import org.kymjs.kjframe.qrcode.decode.CaptureActivityHandler;
import org.kymjs.kjframe.qrcode.decode.InactivityTimer;
import org.kymjs.kjframe.qrcode.view.ViewfinderView;
import com.sales.realestate.android.R;

public class QRCodeScanActivity  extends Activity implements Callback {
	 private static final String TAG = QRCodeScanActivity.class.getSimpleName();
		private CaptureActivityHandler handler;
		private ViewfinderView viewfinderView;
		private boolean hasSurface;
		private Vector<BarcodeFormat> decodeFormats;
		private String characterSet;
		private InactivityTimer inactivityTimer;
		private boolean scanOk = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_qrcode_scan);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
		}
		decodeFormats = null;
		characterSet = null;
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}	
	
	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		if(!scanOk) {
			setResult(RESULT_CANCELED, new Intent());  
		}
		super.onDestroy();
	}
	
	/**
	 * Handler scan result
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		String resultString = result.getText();
		Log.e(TAG, "QRCode resultString =" + resultString);
		
		if (resultString.equals("")) {
			scanOk = false;
			Toast.makeText(QRCodeScanActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
		}else {
			scanOk = true;
			Intent resultIntent = new Intent();
			resultIntent.putExtra("type", "QRCODE");
//			resultIntent.setClass(QRCodeScanActivity.this, HomeShellActivity.class);
			resultIntent.putExtra("qrCodeResult", resultString);
//			startActivity(resultIntent);
//			QRCodeScanActivity.this.finish();
			setResult(RESULT_OK, resultIntent);  
	        finish();
		}
	}
	
	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();
	}		
	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};
}
