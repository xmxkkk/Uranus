package uranus.model;

public class Index {
	int id;
	String url;
	String fetch_time;
	int interval_time;
	String charset;
	String crawler_class;
	String all_link_pattern;
	String link_pattern;
	int start_pageno;
	String start_page;
	String param;
	String title_pattern;
	String content_pattern;
	String theme;
	
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getTitle_pattern() {
		return title_pattern;
	}
	public void setTitle_pattern(String title_pattern) {
		this.title_pattern = title_pattern;
	}
	public String getContent_pattern() {
		return content_pattern;
	}
	public void setContent_pattern(String content_pattern) {
		this.content_pattern = content_pattern;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public int getStart_pageno() {
		return start_pageno;
	}
	public void setStart_pageno(int start_pageno) {
		this.start_pageno = start_pageno;
	}
	public String getStart_page() {
		return start_page;
	}
	public void setStart_page(String start_page) {
		this.start_page = start_page;
	}
	public String getCrawler_class() {
		return crawler_class;
	}
	public void setCrawler_class(String crawler_class) {
		this.crawler_class = crawler_class;
	}
	public String getAll_link_pattern() {
		return all_link_pattern;
	}
	public void setAll_link_pattern(String all_link_pattern) {
		this.all_link_pattern = all_link_pattern;
	}
	public String getLink_pattern() {
		return link_pattern;
	}
	public void setLink_pattern(String link_pattern) {
		this.link_pattern = link_pattern;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFetch_time() {
		return fetch_time;
	}
	public void setFetch_time(String fetch_time) {
		this.fetch_time = fetch_time;
	}
	public int getInterval_time() {
		return interval_time;
	}
	public void setInterval_time(int interval_time) {
		this.interval_time = interval_time;
	}
	@Override
	public String toString() {
		return "Index [id=" + id + ", url=" + url + ", fetch_time=" + fetch_time + ", interval_time=" + interval_time
				+ ", charset=" + charset + ", crawler_class=" + crawler_class + ", all_link_pattern=" + all_link_pattern
				+ ", link_pattern=" + link_pattern + ", start_pageno=" + start_pageno + ", start_page=" + start_page
				+ ", param=" + param + ", title_pattern=" + title_pattern + ", content_pattern=" + content_pattern
				+ ", theme=" + theme + "]";
	}
	
	
}
