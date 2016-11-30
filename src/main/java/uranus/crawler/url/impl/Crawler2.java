package uranus.crawler.url.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uranus.crawler.url.Crawler;
import uranus.dao.IndexMapper;
import uranus.dao.PostMapper;
import uranus.helper.util.StringUtil;
import uranus.model.Index;

@Component
public class Crawler2 implements Crawler {
	Logger logger=Logger.getLogger(Crawler2.class);
	@Autowired IndexMapper indexMapper;
	@Autowired PostMapper postMapper;
	@Autowired Crawler1 crawler1;
	@Override
	public int fetch(Index index) {
		logger.info("[start]"+index);
		int cnt=0;
		try{
			String url=index.getUrl();
			String start_page=index.getStart_page();
			String link_pattern=index.getLink_pattern();
			
			String[] params=index.getParam().split("\\|");
			for(String param:params){
				
				String urlFormat=StringUtil.textFormat(url, param);
				index.setStart_page(StringUtil.textFormat(start_page, param));
				index.setLink_pattern(StringUtil.textFormat(link_pattern, param));
				
				int page=index.getStart_pageno();
				if(page>=2){
					index.setUrl(index.getStart_page());
					int subCnt=crawler1.fetch(index);
					cnt+=subCnt;
					System.out.println(subCnt+"	:"+index.getUrl());
				}
				
				
				do{
					System.out.println(1);
					String urlReal=String.format(urlFormat, page);page++;
					index.setUrl(urlReal);
					
					int subCnt=crawler1.fetch(index);
					cnt+=subCnt;
					
					System.out.println(subCnt+"	:"+index.getUrl());
					if(subCnt==0){
						break;
					}
				}while(true);
			}
			
		}catch(Exception e){
			logger.error("[except]"+e.getMessage());
		}
		logger.info("[end]"+cnt);
		return cnt;
	}

}
