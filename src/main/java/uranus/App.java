package uranus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import uranus.helper.middel.Count;
import uranus.helper.util.CommonUtil;
import uranus.task.ContentTask;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		final ConfigurableApplicationContext cxt = SpringApplication.run(App.class, args);
//		CrawlerTask crawlerTask=cxt.getBean(CrawlerTask.class);
//		crawlerTask.init();
		
		
		final ContentTask contentTask=cxt.getBean(ContentTask.class);
		final Count count=new Count();
		count.init(contentTask.threadNum);
		
		for (int i = 0; i < contentTask.threadNum; i++) {
			final int threadId = i;
			new Thread() {
				public void run() {
					contentTask.init(threadId,1);
					synchronized (count) {
						count.reduce();
					}
				}
			}.start();
		}
		CommonUtil.wait2000(count);
	}
}
