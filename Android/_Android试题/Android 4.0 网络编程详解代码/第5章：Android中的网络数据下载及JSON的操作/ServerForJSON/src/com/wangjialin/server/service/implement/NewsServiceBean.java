package com.wangjialin.server.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.wangjialin.server.domain.News;
import com.wangjialin.server.service.NewsService;


public class NewsServiceBean implements NewsService {
	/**
	 * ��ȡ���µ���Ƶ��Ѷ
	 * @return
	 */
	public List<News> getLastNews(){
		List<News> newes = new ArrayList<News>();
		newes.add(new News(10, "������", 20));
		newes.add(new News(45, "���ָ��", 10));
		newes.add(new News(89, "Android is amazing", 50));
		return newes;
	}
}
