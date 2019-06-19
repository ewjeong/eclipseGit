package kr.or.ddit.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExampleController {
	@RequestMapping(value="/ex/example", method=RequestMethod.GET)
	public String example() {
		return "ex/example";
	}
}
