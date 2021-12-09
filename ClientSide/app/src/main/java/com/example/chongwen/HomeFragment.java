package com.example.chongwen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.chongwen.util.DESUtil;
import com.example.chongwen.zbar.CaptureActivity;
import com.example.xuwei.R;

import java.util.Objects;


public class HomeFragment extends Fragment {
    TextView var_name;
    TextView var_courierID;
    TextView tv_scanResult;
    Button btn_scanner;
    private static final int REQUEST_CODE_SCAN = 0x0000;// 扫描二维码
    private Courier mCourier;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        var_name = view.findViewById(R.id.var_name);
        var_courierID = view.findViewById(R.id.var_courierID);
        btn_scanner = view.findViewById(R.id.btn_scanner);
        tv_scanResult = view.findViewById(R.id.tv_scanResult);
        mCourier = (Courier) Objects.requireNonNull(getActivity()).getIntent().getExtras().get("courier");

        if (mCourier != null) {
            var_courierID.setText(mCourier.getID() + "");
            var_name.setText(mCourier.getName());
        }

        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_scanner:
                        //动态权限申请
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                        } else {
                            goScan();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:// 二维码
                // 扫描二维码回传
                if (resultCode == getActivity().RESULT_OK) {
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_STRING);
                        boolean flag = true;

                        int DESKey = mCourier.getID();
                        Log.i("", "onActivityResult: ");
                        result = DESUtil.myDecrypt(result, DESKey);

                        String[] strs = result.split("&");
                        Log.i("结果", strs[0]);
                        if (!strs[0].startsWith("订单号：")) {
                            flag = false;
                        }
                        StringBuilder content = new StringBuilder();
                        int mid = strs.length / 2;
                        for (int i = 0, j = mid; i < mid; i++, j++) {
                            content.append(strs[i]).append(strs[j]).append("\n");
                        }
                        result = content.toString();
                        if (flag) {
                            tv_scanResult.setText(result);
                        } else {
                            tv_scanResult.setText("您无权访问该客户信息");
                        }

                    }
                }
                break;
            default:
                break;
        }
    }
}
