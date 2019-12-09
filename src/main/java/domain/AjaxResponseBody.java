package domain;

import java.util.List;
import domain.Says;

public class AjaxResponseBody {
	List<Says> msg;

	public List<Says> getMsg() {
		return msg;
	}

	public void setMsg(List<Says> says) {
		this.msg = says;
	}

}
