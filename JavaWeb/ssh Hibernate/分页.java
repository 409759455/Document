 ��ҳ���κ�ϵͳ�ж��Ƿǳ�ͷ�۵����飬�е����ݿ����﷨��֧�ַ�ҳ������MYSQL��select  * from xxx limit 0,5 ��ʾ�ӵ�1����¼��ʼ��ʾ5����¼)�����е����ݿ�����Ҫʹ�ÿɹ����α���ʵ�֣������ڲ�֧�ֿɹ����α��ϵͳ��ֻ��ʹ�õ����α��𲽽ӽ�Ҫȡ�õ����ݡ� 
    ��Hibernate�ṩ��һ��֧�ֿ�ϵͳ�ķ�ҳ���ƣ��������۵ײ���ʲô�������ݿⶼ����ͳһ�Ľӿڽ��з�ҳ������ ����дOracleר�õ�3��Ƕ����һ����ô���˵��°�����

    �ٸ�����

 ����

public List cutPage(String pageHql,int firstResult,int MaxResults)
{
  
  session = HibernateSessionFactory.currentSession();
  query = session.createQuery(pageHql);
  query.setFirstResult(firstResult);
  query.setMaxResults(MaxResults);
  return query.list();
}

    ���ڵײ�����ôʵ�ֵ���ȫ����ȥ�ܣ���ʡ�˺ܶ�ʱ�䣬Ҫ���Լ�дSQL�Ļ��������ORACLE����3��Ƕ��Ҫд���ˡ�������Ҫ��ҳ��ʱ��͵����������������HQL��䣬�پ����ӵڼ�����¼��ʼÿҳ��ʾ��������Ȼ����д��������

public int getCountPage(String pageHql,int MaxResults)
{
  int count = 0;
  
  session = HibernateSessionFactory.currentSession();
  query = session.createQuery(pageHql);
  count = ((Integer)query.iterate().next()).intValue();
  double countPage = count/(MaxResults*1.0);
  
  int countpage=(int)Math.ceil(countPage);
  System.out.println("�ܹ���"+count+"����¼ÿҳ��ʾ"
    +MaxResults+"���ܹ�"+countpage+"ҳ");
  return countpage;
  
}
���������������HQL����ÿҳ��ʾ�������Ի����ҳ��

    �����Ϳ�����JSP��Action��Servlet֮ǰȡ�úʹ��ݵ�ǰҳ����ÿҳ��ʾ�����Լ���ҳ����ֵ��
����HQL�й���select count(*)��д��������"select count(*) from UserInfoPO in class com.fw.po.UserInfoPO"  ����UserInfoPO��һ�����ݿ���ӳ��model�� ���ڰ�com.fw.po����
��Ҫע�����countPage ��double �ģ�Ҳ���Ǵ�С����ġ����磬���ÿҳ��ʾ3�����ܹ���16����¼�Ļ�����ôӦ����6ҳ�����һҳ��ʾ1����¼��Ҳ���ǴӼ�¼��������ÿҳ��ʾ������16/3=5��1�������ж϶�16/3ȡģ������0�Ļ����û�õ�����Ϊ���͵���ҳ��+1������������ķ���16/3�Ľ����һ��double�ͣ���˾���Math.ceil����������������ϱ�Ϊ������ 

 




package org.crazyit.common.hibernate3.support;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class YeekuHibernateDaoSupport extends HibernateDaoSupport {
	/**
	 * ʹ��hql�����з�ҳ��ѯ
	 * 
	 * @param hql
	 *            ��Ҫ��ѯ��hql���
	 * @param offset=(curr-1)*pageSize+1
	 *            ��һ����¼����
	 * @param pageSize
	 *            ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	public List findByPage(final String hql, final int offset,
			final int pageSize) {
		// ͨ��һ��HibernateCallback������ִ�в�ѯ
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			// ʵ��HibernateCallback�ӿڱ���ʵ�ֵķ���
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// ִ��Hibernate��ҳ��ѯ
				List result = session.createQuery(hql).setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	/**
	 * ʹ��hql�����з�ҳ��ѯ
	 * 
	 * @param hql
	 *            ��Ҫ��ѯ��hql���
	 * @param value
	 *            ���hql��һ��������Ҫ���룬value���Ǵ���hql���Ĳ���
	 * @param offset=(curr-1)*pageSize+1
	 *            ��һ����¼����
	 * @param pageSize
	 *            ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	public List findByPage(final String hql, final Object value,
			final int offset, final int pageSize) {
		// ͨ��һ��HibernateCallback������ִ�в�ѯ
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			// ʵ��HibernateCallback�ӿڱ���ʵ�ֵķ���
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// ִ��Hibernate��ҳ��ѯ
				List result = session.createQuery(hql)
						// Ϊhql��䴫�����
						.setParameter(0, value).setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	/**
	 * ʹ��hql�����з�ҳ��ѯ
	 * 
	 * @param hql
	 *            ��Ҫ��ѯ��hql���
	 * @param values
	 *            ���hql�ж����������Ҫ���룬values���Ǵ���hql�Ĳ�������
	 * @param offset=(curr-1)*pageSize+1
	 *            ��һ����¼����
	 * @param pageSize
	 *            ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	public List findByPage(final String hql, final Object[] values,
			final int offset, final int pageSize) {
		// ͨ��һ��HibernateCallback������ִ�в�ѯ
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			// ʵ��HibernateCallback�ӿڱ���ʵ�ֵķ���
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// ִ��Hibernate��ҳ��ѯ
				Query query = session.createQuery(hql);
				// Ϊhql��䴫�����
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
				List result = query.setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}
}
