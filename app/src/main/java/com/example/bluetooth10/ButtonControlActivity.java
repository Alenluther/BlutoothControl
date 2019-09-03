package com.example.bluetooth10;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonControlActivity extends Activity implements OnTouchListener {

	private Button upButton, downButton, leftButton, rightButton;
	private Button accButton,slowButton,steering_Left,steering_Middle,steering_Right;
	private OutputStream outputStream;
	private InputStream inputStream=MainActivity.inputStream;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		upButton = (Button) findViewById(R.id.upButton);
		downButton = (Button) findViewById(R.id.downButton);
		leftButton = (Button) findViewById(R.id.leftButton);
		rightButton = (Button) findViewById(R.id.rightButton);


		accButton = (Button) findViewById(R.id.accButton);
		slowButton = (Button) findViewById(R.id.slowButton);
		steering_Left = (Button) findViewById(R.id.steering_Left);
		steering_Middle=(Button) findViewById(R.id.steering_Middle);
		steering_Right=(Button) findViewById(R.id.steering_Right);
		textView = (TextView) findViewById(R.id.textView1);


		upButton.setOnTouchListener(this);
		downButton.setOnTouchListener(this);
		leftButton.setOnTouchListener(this);
		rightButton.setOnTouchListener(this);

		accButton.setOnTouchListener(this);
		slowButton.setOnTouchListener(this);
		steering_Left.setOnTouchListener(this);
		steering_Middle.setOnTouchListener(this);
		steering_Right.setOnTouchListener(this);


	}


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		//----------------------------------
//		try{
//			readStreamToString(MainActivity.inputStream);
//		}catch (Exception e)
//		{
//			System.out.println("read mainacttivty inputstring failed");
//		}

		switch (arg0.getId()) {
			case R.id.upButton:
				writeOutputStream(arg1, "$1,0,0,0,0,0,0,0,0,0,0,0,0#", "$0,0,0,0,0,0,0,0,0,0,0,0,0#");
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					upButton.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.up1));
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					upButton.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.up));
				}
				break;

			case R.id.downButton:
				writeOutputStream(arg1, "$2,0,0,0,0,0,0,0,0,0,0,0,0#", "$0,0,0,0,0,0,0,0,0,0,0,0,0#");
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					downButton.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.down1));
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					downButton.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.down));
				}
				break;

			case R.id.leftButton:
				writeOutputStream(arg1, "$3,0,0,0,0,0,0,0,0,0,0,0,0#", "$0,0,0,0,0,0,0,0,0,0,0,0,0#");
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					leftButton.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.left1));
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					leftButton.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.left));
				}
				break;

			case R.id.rightButton:
				writeOutputStream(arg1, "$4,0,0,0,0,0,0,0,0,0,0,0,0#", "$0,0,0,0,0,0,0,0,0,0,0,0,0#");
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					rightButton.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.right1));
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					rightButton.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.right));
				}
				break;

			case R.id.accButton:
				writeOutputStream(arg1,"$0,0,0,0,0,0,0,0,0,0,0,0,0#","$0,0,0,1,0,0,0,0,0,0,0,0,0#");
				break;

			case R.id.slowButton:
				writeOutputStream(arg1,"$0,0,0,0,0,0,0,0,0,0,0,0,0#","$0,0,0,0,1,0,0,0,0,0,0,0,0#");
				break;

			case R.id.steering_Left:
				writeOutputStream(arg1,"$0,0,0,0,0,0,0,0,0,0,0,0,0#","$0,0,0,0,0,1,0,0,0,0,0,0,0#");
				break;

			case R.id.steering_Middle:
				writeOutputStream(arg1,"$0,0,0,0,0,0,0,0,0,0,0,0,0#","$0,0,0,0,0,0,0,0,0,0,1,0,0#");
				break;

			case R.id.steering_Right:
				writeOutputStream(arg1,"$0,0,0,0,0,0,0,0,0,0,0,0,0#","$0,0,0,0,0,0,1,0,0,0,0,0,0#");
				break;
		}

		return false;
	}

//	public  String readStreamToString(InputStream inputStream) throws IOException {
//		//创建字节数组输出流 ，用来输出读取到的内容
//		int string;
//		string = inputStream.read();
//		textView.setText(string);
//		System.out.println("dddd"+string);
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		//创建读取缓存,大小为1024
//		byte[] buffer = new byte[1024];
//		//每次读取长度
//		int len = 0;
//		//开始读取输入流中的文件
//		while( (len = inputStream.read(buffer) ) != -1){ //当等于-1说明没有数据可以读取了
//			byteArrayOutputStream.write(buffer,0,len); // 把读取的内容写入到输出流中
//		}
//		//把读取到的字节数组转换为字符串
//		String result = byteArrayOutputStream.toString();
//
//		//关闭输入流和输出流
//		byteArrayOutputStream.close();
//		//返回字符串结果
//		return result;
//	}

	private void writeOutputStream(MotionEvent arg1, String m1, String m2) {
		// 每次点击都要重新获得输出流，以防止蓝牙断开重新连接后输出流改变了
		outputStream = MainActivity.outputStream;
		if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
			String message;
			byte[] buffer;
			message = m1;
			buffer = message.getBytes();
			try {
				outputStream.write(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg1.getAction() == MotionEvent.ACTION_UP) {
			String message;
			byte[] buffer;
			message = m2;
			buffer = message.getBytes();
			try {
				outputStream.write(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
