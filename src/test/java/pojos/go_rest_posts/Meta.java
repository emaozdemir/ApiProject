package pojos.go_rest_posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
	private Pagination pagination;


	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String toString() {
		return "Meta{" +
				"pagination=" + pagination +
				'}';
	}
}
