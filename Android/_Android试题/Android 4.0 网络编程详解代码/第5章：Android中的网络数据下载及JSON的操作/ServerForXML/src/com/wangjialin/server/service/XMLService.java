package com.wangjialin.server.service;

import java.util.List;

import com.wangjialin.server.domain.News;


public interface XMLService {

	/**
	 * ��ȡ���µ���Ƶ��Ѷ
	 * @return
	 */
	public List<News> getLastNews();

}