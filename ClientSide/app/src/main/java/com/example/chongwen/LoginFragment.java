package com.example.chongwen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chongwen.util.Constant;
import com.example.xuwei.R;

public class LoginFragment extends Fragment {

    ImageView imageView;
    TextView textView;
    Button loginButton;
    Button registerButton;
    int count = 0;

    private EditText name, phoneNum, username, password_1, password_2;
    private final static int LOGIN_JUDGE = 1;
    private final static int REGISTER_JUDGE = 2;
    private static final Courier sCourier = new Courier();
//    private int RequestCode = 1;

    private static final String TAG = "MainActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);

        imageView = view.findViewById(R.id.ID_photo_1);
        textView = view.findViewById(R.id.textView);
        name = view.findViewById(R.id.name);
        name.setVisibility(View.GONE);
        phoneNum = view.findViewById(R.id.phoneNum);
        phoneNum.setVisibility(View.GONE);
        username = view.findViewById(R.id.username);
        password_1 = view.findViewById(R.id.password);
        password_2 = view.findViewById(R.id.password_2);
        password_2.setVisibility(View.GONE);
        loginButton = view.findViewById(R.id.btn_login);
        registerButton = view.findViewById(R.id.btn_register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    clickAction();
                } else {
                    changeToSignIn();
                }

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 1) {
                    clickAction();
                } else {
                    changeToSignUp();
                }
            }
        });


        return view;
    }

    private void clickAction() {
        // 登入
        if (count == 0) {
            final String strUsername = username.getText().toString();
            final String strPassword = password_1.getText().toString();

            if (strUsername.equals("") ){
                Toast.makeText(getActivity(), "账号不能为空！", Toast.LENGTH_LONG).show();
            }
            if (strPassword.equals("")) {
                Toast.makeText(getActivity(), "密码不能为空！", Toast.LENGTH_LONG).show();
            } else {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "进入主线程");
                        //使用下面类里的函数，连接servlet，返回一个result，使用handler处理这个result
                        sCourier.setUserName(strUsername);
                        sCourier.setPassword(strPassword);
                        int result = HttpLogin.LoginByPost(sCourier);
                        Log.i(TAG, "返回result");
                        Bundle bundle = new Bundle();
                        bundle.putInt("result", result);
                        Message message = new Message();
                        message.setData(bundle);
                        message.what = LOGIN_JUDGE;
                        new executeReply().execute(message);
                    }
                }).start();
            }
        } else {
            final String strNmae = name.getText().toString();
            final String strPhoneNum = phoneNum.getText().toString();
            final String strUsername = username.getText().toString();
            final String strPassword = password_1.getText().toString();
            final String strPassword_2 = password_2.getText().toString();
            if (strNmae.equals("")) {
                Toast.makeText(getActivity(), "姓名不能为空", Toast.LENGTH_LONG).show();
            }
            else if (strPhoneNum.equals("")) {
                Toast.makeText(getActivity(), "电话号码不能为空", Toast.LENGTH_LONG).show();
            }
            else if (strUsername.equals("")) {
                Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_LONG).show();
            }
            else if (strPassword.equals("")) {
                Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_LONG).show();
            }
            else if (strPassword_2.equals("")) {
                Toast.makeText(getActivity(), "请再次输入密码", Toast.LENGTH_LONG).show();
            }
            else if (!strPassword.equals(strPassword_2)) {
                Toast.makeText(getActivity(), "两次密码不一致！", Toast.LENGTH_LONG).show();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sCourier.setName(strNmae);
                        sCourier.setPhoneNum(strPhoneNum);
                        sCourier.setUserName(strUsername);
                        sCourier.setPassword(strPassword);
                        int result = HttpLogin.RegisterByPost(sCourier);
                        Bundle bundle = new Bundle();
                        bundle.putInt("result", result);
                        Message msg = new Message();
                        msg.what = REGISTER_JUDGE;
                        msg.setData(bundle);
                        new executeReply().execute(msg);
                    }
                }).start();
            }
            name.setText("");
            phoneNum.setText("");
            username.setText("");
            password_1.setText("");
            password_2.setText("");
        }
    }

    private class executeReply extends AsyncTask<Message, Void, Message> {
        @Override
        protected Message doInBackground(Message... messages) {
            Message msg = messages[0];
            Log.i("login", Integer.toString(msg.what));

            return msg;
        }

        @Override
        protected void onPostExecute(Message msg) {
            switch (msg.what) {
                case LOGIN_JUDGE: {
                    Bundle bundle = new Bundle();
                    bundle = msg.getData();
                    int result = bundle.getInt("result");
                    try {
                        if (result == Constant.SUCCESS_COURIER) {
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            intent.putExtra("courier", sCourier);
                            startActivity(intent);
                        } else if (result == Constant.ERROR_COURIER) {
                            Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
                            password_1.setText("");

                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case REGISTER_JUDGE: {
                    Bundle bundle = new Bundle();
                    bundle = msg.getData();
                    int result = bundle.getInt("result");
                    try {
                        if (result == Constant.SUCCESS_COURIER) {
                            Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                            changeToSignIn();
                        } else if (result == Constant.ERROR_COURIER_REPEAT) {
                            Toast.makeText(getActivity(), "账号已存在", Toast.LENGTH_SHORT).show();
                        } else if (result == Constant.ERROR_COURIER) {
                            Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                break;
                default:

            }
        }
    }

    private void changeToSignUp() {
        name.setText("");
        phoneNum.setText("");
        username.setText("");
        password_1.setText("");
        password_2.setText("");
        imageView.setImageResource(R.drawable.good_night_img);
        textView.setText("注册");
        name.setVisibility(View.VISIBLE);
        phoneNum.setVisibility(View.VISIBLE);
        password_2.setVisibility(View.VISIBLE);
        count = 1;
    }

    private void changeToSignIn() {
        textView.setText("登录");
        imageView.setImageResource(R.drawable.good_morning_img);
        name.setVisibility(View.GONE);
        phoneNum.setVisibility(View.GONE);
        password_2.setVisibility(View.GONE);
        count = 0;
    }
}
