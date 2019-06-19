package kr.or.ddit.configuration.menuManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.configuration.menuManage.dao.ISettingDAO;
import kr.or.ddit.vo.ResourcesVO;

@ControllerAdvice
public class menuAdviceController {
	//tes
	@Inject
	ISettingDAO settingDAO;
	
	@ModelAttribute("menuList")
	public List<ResourcesVO> getMenu(){
		return settingDAO.selectMenuList();
	}
	
	
	
}
