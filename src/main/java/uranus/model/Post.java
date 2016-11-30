package uranus.model;

public class Post {
	int id;
	String title;
	String content;
	String summary;
	String add_time;
	String modify_time;
	String keywords;
	String description;
	String url;
	String origin_url;
	int index_id;
	int status;
	int error_times;
	
	public int getError_times() {
		return error_times;
	}
	public void setError_times(int error_times) {
		this.error_times = error_times;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIndex_id() {
		return index_id;
	}
	public void setIndex_id(int index_id) {
		this.index_id = index_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOrigin_url() {
		return origin_url;
	}
	public void setOrigin_url(String origin_url) {
		this.origin_url = origin_url;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", summary=" + summary + ", add_time="
				+ add_time + ", modify_time=" + modify_time + ", keywords=" + keywords + ", description=" + description
				+ ", url=" + url + ", origin_url=" + origin_url + ", index_id=" + index_id + "]";
	}
	
}
