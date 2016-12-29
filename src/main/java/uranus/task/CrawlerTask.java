package uranus.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import uranus.crawler.url.Crawler;
import uranus.dao.IndexMapper;
import uranus.dao.PostMapper;
import uranus.model.Index;

@Component
public class CrawlerTask extends ApplicationObjectSupport{
	Logger logger=Logger.getLogger(CrawlerTask.class);
	@Autowired IndexMapper indexMapper;
	@Autowired PostMapper postMapper;
	public void init(){
		logger.info("[start]");
		try{
			List<Index> indexs=indexMapper.findTimeStatus(1);
			for(Index index:indexs){
				Crawler crawler=(Crawler)getApplicationContext().getBean(index.getCrawler_class());
				crawler.fetch(index);
				logger.info("[message]"+index);
			}
		}catch(Exception e){
			logger.error("[except]",e);
		}
		logger.info("[end]");
	}
}
