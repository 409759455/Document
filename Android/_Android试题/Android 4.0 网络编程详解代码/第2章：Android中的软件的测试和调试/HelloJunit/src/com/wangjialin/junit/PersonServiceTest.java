package com.wangjialin.junit;

import com.wangjialin.service.PersonService;

import android.test.AndroidTestCase;

public class PersonServiceTest extends AndroidTestCase {
	
	/**
	 * 1����Ԫ���Է�����Ҫ����Ϊpublic���ͣ�
	 * 2����Ԫ���Է����ķ���ֵ����Ϊvoid��
	 * 3������JUnit3�Ĺ淶Ҫ��Ԫ���Է����ķ���������Ҫ��test��ͷ��
	 * 4����Ԫ���Է�����Ҫ������Ԫ���Կ���׳��쳣��
	 */
	public void testSave() throws Throwable
	{
		Integer integer = 10 + 6;
		total(integer);
		//������ʵ����PersonService
		PersonService personService = new PersonService();
		//����save����
		personService.save();
	}

	private String total(Integer integer) {
		Integer integer1 = integer;
		Integer integer2 = 2011;
		Integer total = integer1 + integer2;
		String preString = "goushiAndroid";
		String result = preString + total ;
		
		return result;
	}
}
