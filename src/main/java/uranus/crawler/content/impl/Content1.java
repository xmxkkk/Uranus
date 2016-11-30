package uranus.crawler.content.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uranus.crawler.content.Content;
import uranus.dao.IndexMapper;
import uranus.dao.PostMapper;
import uranus.helper.util.StringUtil;
import uranus.helper.util.URLUtil;
import uranus.model.Index;
import uranus.model.Post;

@Component
public class Content1 implements Content{
	Logger logger=Logger.getLogger(Content1.class);
	@Autowired PostMapper postMapper;
	@Autowired IndexMapper indexMapper;
	@Override
	public boolean fetch(Post post) throws IOException{
		logger.info("[start]"+post);
		boolean result=false;
		try{
			Index index=indexMapper.findId(post.getIndex_id());
			String html =URLUtil.url2str(post.getOrigin_url(), index.getCharset());
			
			if(StringUtil.isBlank(html)){
				return false;
			}
			if(html.length()<100){
				return false;
			}
			
			Document document=Jsoup.parse(html);
			Elements elements=document.select(index.getTitle_pattern());
			if(elements.size()<=0)return false;
			String title=elements.get(0).text();
			
			elements=document.select(index.getContent_pattern());
			if(elements.size()<=0)return false;
			String content=elements.get(0).outerHtml();
			
			post.setTitle(title);
			post.setContent(content);
			postMapper.update(post);

		}catch(Exception e){
			logger.error("[except]"+e.getMessage());
			throw e;
		}
		result=true;
		
		logger.info("[end]"+result);
		return result;
	}
	
}
