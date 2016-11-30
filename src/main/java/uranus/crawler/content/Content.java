package uranus.crawler.content;

import uranus.model.Post;

public interface Content {
	public boolean fetch(Post post) throws Exception;
}
