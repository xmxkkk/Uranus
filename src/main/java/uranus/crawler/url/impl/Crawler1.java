package uranus.crawler.url.impl;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uranus.crawler.url.Crawler;
import uranus.dao.IndexMapper;
import uranus.dao.PostMapper;
import uranus.helper.util.DateUtil;
import uranus.helper.util.StringUtil;
import uranus.helper.util.URLUtil;
import uranus.model.Index;
import uranus.model.Post;

@Component
public class Crawler1 implements Crawler {
	Logger logger=Logger.getLogger(Crawler1.class);
	@Autowired IndexMapper indexMapper;
	@Autowired PostMapper postMapper;

	@Override
	public int fetch(Index index) {
		logger.info("[start]"+index);
		int cnt=0;
		try{
			String html = URLUtil.url2str(index.getUrl(), index.getCharset());
			if(StringUtil.isBlank(html)){
				return 0;
			}
			if(html.length()<100){
				return 0;
			}
			
			
			Document document = Jsoup.parse(html);
			String baseUri = document.baseUri();
			if (StringUtil.isBlank(baseUri)) {
				baseUri = index.getUrl().substring(0, index.getUrl().indexOf("/", 8));
			}
			
			Elements links = document.select(index.getAll_link_pattern());
			for (int i = 0; i < links.size(); i++) {
				Element temp = links.get(i);
				String link = temp.attr("href");
				if (link.matches(index.getLink_pattern())) {
					if (link.startsWith("http")) {
					} else {
						link = baseUri + link;
					}
	
					link = link.substring(0, 7)+link.substring(7, link.length()).replaceAll("//", "/");
					
					Post post = postMapper.findOriginUrl(link);
					if (post == null) {
						post = new Post();
						post.setAdd_time(DateUtil.datetime2());
						post.setModify_time(DateUtil.datetime2());
						post.setOrigin_url(link);
						post.setIndex_id(index.getId());
						post.setStatus(1);
						postMapper.insert(post);
					}
					cnt++;
				}
			}
		}catch(Exception e){
			logger.error("[except]",e);
		}
		logger.info("[end]"+cnt);
		return cnt;
	}

}
