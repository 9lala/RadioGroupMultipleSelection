package cn.photon.multiple;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rg_1)
    RadioGroupForMarketLayout rg1;
    @BindView(R.id.rg_2)
    RadioGroupForMarketLayout rg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        rg1.setLayoutCheckedListener(new RadioGroupForMarketLayout.LayoutCheckedistener() {
            @Override
            public void onclick(String str) {
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
        rg2.setLayoutCheckedListener(new RadioGroupForMarketLayout.LayoutCheckedistener() {
            @Override
            public void onclick(String str) {
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
