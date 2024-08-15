package pojos.go_rest_posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestHomeworkPojo {
	private Meta meta;
	private List<Data> data;

	// Getter ve Setter'lar

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RestHomeworkPojo{" +
				"meta=" + meta +
				", data=" + data +
				'}';
	}
}
