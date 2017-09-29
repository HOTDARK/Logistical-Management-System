package cn.itcast.ik.action.sysadmin;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import cn.itcast.ik.action.BaseAction;
import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Dept;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.utils.Page;

public class DeptAction extends BaseAction implements ModelDriven<Dept> {
	
	Dept model = new Dept();
	@Override
	public Dept getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	//分页查询
	private Page page = new Page();
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	//注入service
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	/**
	 * 分页查询
	 */

	public String list() throws Exception{
		deptService.findPage("from Dept", page, Dept.class, null);
		page.setUrl("deptAction_list");
		
		//把page放入栈顶
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看部门
	 */
	
	public String toview(){
		//调用业务方法根据id获得对象
		Dept dept = deptService.get(Dept.class,model.getId());
		
		System.out.println(dept.getDeptName());
		//放入栈顶
		super.push(dept);
		
		return "toview";
		
	}
	
	/**
	 * 新增部门
	 * @return
	 */
	public String tocreate(){
		List<Dept> deptList = deptService.find("from Dept where state = 1",Dept.class,null);
		
		ActionContext.getContext().put("deptList", deptList);
		return "tocreate";
	}
	
	/**
	 * 保存新增 的 部门
	 */
	
	public String insert() throws Exception{
		deptService.saveOrUpdate(model);
		return "alist";
	}
	
	/**
	 * 修改部门信息
	 */
	
	public String toupdate(){
		Dept dept = deptService.get(Dept.class,model.getId());
		
		//压栈
		super.push(dept);
		
		//把父部门信息压context
		List<Dept> deptList = deptService.find("from Dept where state = 1",Dept.class,null);
		deptList.remove(dept);
		super.put("deptList",deptList);

		return "toupdate";
	}
	
	public String update(){
		Dept dept = deptService.get(Dept.class, model.getId());
		
		//ss设置修改的值
		dept.setDeptName(model.getDeptName());
		dept.setParent(model.getParent());
		
		deptService.saveOrUpdate(dept);
		return "alist";
	}
	
	
	public String delete() throws Exception {
		//用户删除的时候有可能是多选，struts2框架默认把多个同名的String1属性拼接成一个字符串，用,隔开
		
		String[] id = model.getId().split(",");
		
		//调用service 方法实现批量的删除
		deptService.delete(Dept.class,id);
		
		return "alist";
	}
}
