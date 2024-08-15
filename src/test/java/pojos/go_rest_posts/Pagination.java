package pojos.go_rest_posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {
	private Integer total;
	private Integer pages;
	private Integer page;
	private Integer limit;
	private Links links;


	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Pagination{" +
				"total=" + total +
				", pages=" + pages +
				", page=" + page +
				", limit=" + limit +
				", links=" + links +
				'}';
	}
}
