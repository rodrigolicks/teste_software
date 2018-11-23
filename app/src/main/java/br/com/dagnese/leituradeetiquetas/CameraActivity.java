package br.com.dagnese.leituradeetiquetas;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class CameraActivity extends Activity implements Detector.Processor {

    private TextView fabricacao;
    private SurfaceView cameraSurface;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private String chapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cameraSurface = (SurfaceView) this.findViewById(R.id.surfaceView);
        fabricacao = (TextView) this.findViewById(R.id.fabricacao);

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build();
        barcodeDetector.setProcessor(this);

        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setRequestedPreviewSize(854, 480)
                .setRequestedFps(10.0f)
                .setAutoFocusEnabled(true)
                .build();

        final Activity activity = this;

        cameraSurface.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 1024);
                        return;
                    }
                    cameraSource.start(cameraSurface.getHolder());
                } catch (IOException ie) {
                    Log.e("Camera sem permissão", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });
    }

    @Override
    public void release() {}

    @Override
    public void receiveDetections(Detector.Detections detections) {
        final SparseArray<Barcode> barcodes = detections.getDetectedItems();
        if (barcodes.size() == 1) {
            Intent returnIntent = new Intent();
            final StringBuilder sb = new StringBuilder();
            sb.append(barcodes.valueAt(0).rawValue);
            Log.d("Barcode", sb.toString());
            if (sb.toString().length() == 8 && sb.toString().substring(0, 2).equals("57")) {
                chapa = sb.toString().substring(2, 6);
                Log.d("Chapa detectada", chapa);
                returnIntent.putExtra("tipo", "0");
                returnIntent.putExtra("leitura", sb.toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            } else if (sb.toString().split("\\.").length == 3) {
                Log.d("Etiqueta detectada", sb.toString());
                returnIntent.putExtra("tipo", "1");
                returnIntent.putExtra("leitura", sb.toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }
}