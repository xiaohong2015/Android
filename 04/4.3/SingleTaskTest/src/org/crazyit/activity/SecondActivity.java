package org.crazyit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Description: <br/>
 * site: <a href="http://www.crazyit.org">crazyit.org</a> <br/>
 * Copyright (C), 2001-2014, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class SecondActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		setContentView(layout);
		// ����һ��TextView����ʾ��Activity��������Task ID��
		TextView tv = new TextView(this);
		tv.setText("ActivityΪ��" + this.toString() 
			+ "\n" + "��Task IDΪ:" + this.getTaskId());
		Button button = new Button(this);
		button.setText("����SingleTaskTest");
		// ����TextView��Button
		layout.addView(button);
		layout.addView(tv);
		// Ϊbutton�����¼����������������ð�ťʱ����SingleTaskTest
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(SecondActivity.this,
					SingleTaskTest.class);
				startActivity(intent);
			}
		});
	}
}