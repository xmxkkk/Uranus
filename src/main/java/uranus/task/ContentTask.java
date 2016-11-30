package uranus.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import uranus.crawler.content.Content;
import uranus.crawler.content.impl.Content1;
import uranus.dao.IndexMapper;
import uranus.dao.PostMapper;
import uranus.model.Post;

@Component
public class ContentTask extends ApplicationObjectSupport{
	Logger logger=Logger.getLogger(ContentTask.class);
	@Autowired IndexMapper indexMapper;
	@Autowired PostMapper postMapper;
	@Autowired Content content;
	@Value("${content-task-threadnum}")
	public int threadNum;
	
	public static List<Post> posts;
	public void init(int threadId,int error_times){
		logger.info("[start]");
		try{
			content=(Content1)getApplicationContext().getBean("content1");
			
			if(posts==null){
				posts=postMapper.findErrorTimes(error_times);
			}
			
			for(int i=0;i<posts.size();i++){
				if (i % threadNum != threadId) {
					continue;
				}
				
				Post post=posts.get(i);
				try{
					content.fetch(post);
					post.setStatus(2);
					postMapper.update(post);
				}catch(Exception e){
					post.setStatus(3);
					post.setError_times(post.getError_times()+1);
					postMapper.update(post);
				}
				logger.info("[message]"+post);
			}
		}catch(Exception e){
			logger.error("[except]"+e.getMessage());
		}
		logger.info("[end]");
	}
}