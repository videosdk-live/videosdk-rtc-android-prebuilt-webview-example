package live.videosdk.vslwebview;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static String ACCESS_TOKEN = "YOUR ACCESS TOKEN GOES HERE";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView myWebView = new WebView(this);
        setContentView(myWebView);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request.grant(request.getResources());
                }
            }
        });

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("dev-embed.zujonow.com")
                .appendQueryParameter("name", "John Doe")
                .appendQueryParameter("micEnabled", "true")
                .appendQueryParameter("webcamEnabled", "true")
                .appendQueryParameter("chatEnabled", "true")
                .appendQueryParameter("screenShareEnabled", "true")
                .appendQueryParameter("meetingId", "q48s-7ior-vvis")
                .appendQueryParameter("redirectOnLeave", "https://videosdk.live")
                .appendQueryParameter("participantCanToggleSelfWebcam", "true")
                .appendQueryParameter("participantCanToggleSelfMic", "true")
                .appendQueryParameter("raiseHandEnabled", "true")
                .appendQueryParameter("token", ACCESS_TOKEN)
                .appendQueryParameter("recordingEnabled", "false")
                .appendQueryParameter("recordingWebhookUrl", "");

        String url = builder.build().toString();
        myWebView.loadUrl(url);
    }
}