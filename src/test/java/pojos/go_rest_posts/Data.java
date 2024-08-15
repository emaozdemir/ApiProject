package pojos.go_rest_posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	private Integer id;
	private Integer user_id;
	private String title;
	private String body;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(Integer user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Data{" +
				"id=" + id +
				", user_id=" + user_id +
				", title='" + title + '\'' +
				", body='" + body + '\'' +
				'}';
	}
}
