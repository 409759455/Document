package com.wangjialin.server.service;

import java.util.List;

import com.wangjialin.server.domain.News;


public interface NewsService {

	/**
	 * ��ȡ���µ���Ƶ��Ѷ
	 * @return
	 */
	public List<News> getLastNews();

}