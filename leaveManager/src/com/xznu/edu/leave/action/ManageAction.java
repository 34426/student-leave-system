package com.xznu.edu.leave.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.service.ClassRoomService;
import com.xznu.edu.leave.service.ManageService;
import com.xznu.edu.leave.utils.Pager;
import com.xznu.edu.leave.utils.TimeSetUtil;

import net.sf.json.JSONArray;
@Controller("manageAction")
@Scope("prototype")
public class ManageAction extends ActionSupport implements ModelDriven<Manage>{

	@Autowired
    private ClassRoomService classRoomService;
   private Manage manage;
    @Autowired
   private ManageService manageService;
    //=============================================================================================================
    private String startTime;
    private String endTime;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	private File file;
	//提交过来的file的名字
    private String fileFileName;
    
    //提交过来的file的MIME类型
    private String fileContentType;
    public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	//=====================================
	
	public String setTime(){
		
		return SUCCESS;
	}
	
	 public String setTimes(){
		TimeSetUtil.endtTime = endTime;
		TimeSetUtil.startTime= 	startTime;
		HttpSession session =	ServletActionContext.getRequest().getSession();
		String s =TimeSetUtil.startTime;
	    String e =	TimeSetUtil.endtTime;
	    session.setAttribute("s", s);
	    session.setAttribute("e", e);
		ActionContext.getContext().put("url", "/manage_setTime.do");
		return "redirect";
	}
	
	 /**
		 * 列表
		 */
		public String manage(){
		Pager<Manage> pagers = manageService.findPager(manage);
		ActionContext.getContext().put("pagers", pagers);
		ActionContext.getContext().put("manage", manage);
		return SUCCESS;
		}
		
		/**
		 * 跳转到添加页面
		 * @return
		 */
		public String add(){
			//查询所有班级信息
			String hql="from ClassRoom where isDelete = 2";
			List<ClassRoom> clas = classRoomService.getByHQL(hql, null);
			ActionContext.getContext().put("clas", clas);
			return SUCCESS;
		}
		
		/**
		 * 添加
		 * @return
		 */
		public String exAdd(){
			manage.setIsDelete(2);
			manage.setType(2);
			manage.setPassWord("111111");
			manageService.save(manage);
			ActionContext.getContext().put("url", "/manage_manage.do");
			return "redirect";
		}
		/**
		 * 跳转修改页面
		 * @return
		 */
		public String update(){
			String hql="from ClassRoom where isDelete = 2";
			List<ClassRoom> clas = classRoomService.getByHQL(hql, null);
			ActionContext.getContext().put("clas", clas);
			Manage c = manageService.getById(manage.getId());
			ActionContext.getContext().put("c", c);
			return SUCCESS;
		}
	    
		public String exUpdate(){
			manage.setIsDelete(2);
			manage.setType(2);
			manage.setPassWord("111111");
			manageService.update(manage);
			ActionContext.getContext().put("url", "/manage_manage.do");
			return "redirect";
		}
		public String exUpdate2(){
			manage.setIsDelete(2);
			manage.setType(2);
			
			manageService.update(manage);
			return "login";
		}
		public String exUpdate3(){
			Manage c = manageService.getById(manage.getId());
			c.setPassWord(manage.getPassWord());
			manageService.update(manage);
			return "login";
		}
		/**
		 * 删除
		 * @return
		 */
		public String delete(){
			Manage c = manageService.getById(manage.getId());
			c.setIsDelete(1);
			manageService.update(c);
			ActionContext.getContext().put("url", "/manage_manage.do");
			return "redirect";
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    /**
	 * 登陆以后进入首页
	 * @return
	 */
	public String index(){
		Manage ma =	manageService.login(manage);
		if(ma == null){
			return "login";
		}
		HttpSession session =	ServletActionContext.getRequest().getSession();
		session.setAttribute("userName", ma.getName());
		session.setAttribute("userIdLogo", ma.getId());
		session.setAttribute("userType", ma.getType());
		session.setAttribute("userId", ma.getType());
	    return "index";
	}
	
	/**
	 * 跳到管理员页面,并且顺带查询管理员列表分页
	 * @return
	 */
//	public String manage(){
//		Pager<Manage> pagers = manageService.list(manage);
//		ActionContext.getContext().put("pagers", pagers);
//		return SUCCESS;
//		
//	}
    /**
     * 跳转添加页面
     * @return
     */
	public String addManage(){
		return SUCCESS;
	}
	/**
	 * 添加
	 * @return
	 * @throws IOException 
	 */
    public void manageAdd() throws IOException{
    	HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		manage.setType(2);
		manageService.save(manage);
    	out = resp.getWriter();
		 out.write(JSONArray.fromObject(1).toString());
    }
    
    /**
     * 
     * @return
     */
	public String manageEdit(){
		Manage ma =	manageService.getById(manage.getId());
		ActionContext.getContext().put("ma", ma);
		return SUCCESS;
		
	}
	
	public String manageEdit2(){
		Manage ma =	manageService.getById(manage.getId());
		ActionContext.getContext().put("ma", ma);
		return SUCCESS;
		
	}
	
	public void  editmanage() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		//Manage ma =	manageService.getById(manage.getId());
		/*ma.setPassWord(manage.getPassWord());
		ma.setRealName(manage.getRealName());
		ma.setName(manage.getName());
		ma.setId(manage.getId());*/
    	manageService.updateInfo(manage);
    	out = resp.getWriter();
		 out.write(JSONArray.fromObject(1).toString());
		
	}
    
    
    public String delManage(){
    	manageService.deleteInfo(manage.getId());
    	ActionContext.getContext().put("url", "/manage_manage.do");
		return "redirect";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public Manage getModel() {
		if(manage==null) manage = new Manage();
		return manage;
	}
	
	/**
	 * 获取用户列表
	 * @return
	 *//*
	public String userList(){
	Pager<User>	pagers = userService.listAll(userName);
	//这里需要对等级进行遍历
	List<Grade> list = gradeService.list();
	if(pagers != null && pagers.getDatas() != null && pagers.getDatas().size()>0){
		for(User u : pagers.getDatas()){
			//对这里的人 进行遍历
			for(Grade g: list){
				if(u.getJifen()>=g.getStartMin() && u.getJifen() <=g.getEndMax()){
					u.setDengji(g.getName());
				}
			}
		}
	}
	ActionContext.getContext().put("pagers", pagers);
	ActionContext.getContext().put("userName1", userName);
	return SUCCESS;
	}*/
	
	/**
	 * 根据用户id查询所有图片
	 * @return
	 */
	/*public String userPhotos(){
		Pager<SayMood>	pagers = sayMoodService.findAllphotosById(userId);
		ActionContext.getContext().put("pagers", pagers);
		return SUCCESS;
	}
	*/
	/**
	 * 删除照片
	 * @return
	 */
	/*public String delsay(){
		 sayMoodService.del(sayId);
		ActionContext.getContext().put("url", "/manage_userPhotos.do");
		return "redirect";
	}*/
	/*//删除用户
	public String delUse(){
		userService.delUse(userId);
		ActionContext.getContext().put("url", "/manage_userList.do");
		return "redirect";
	}
	
	//经警告用户
	
	public String jinggao(){
		messageService.updatejinggao(userId);
		ActionContext.getContext().put("url", "/manage_userList.do");
		return "redirect";
	}*/
	/**
	 * 图片上传
	 * @return
	 * @throws Exception
	 */
	/*public String fileUpload() throws Exception{
		HttpSession session =	ServletActionContext.getRequest().getSession();
		if(session.getAttribute("userId") != null){
			int userId = Integer.parseInt( session.getAttribute("userId").toString());
			
			 String root = ServletActionContext.getServletContext().getRealPath("/")+"upload";
			//String root = ServletActionContext.getServletContext().getRealPath("upload"); 
			//String root = ServletActionContext.getRequest().getContextPath()+"/"+"upload";
		        InputStream is = new FileInputStream(file);
		        fileFileName = UUIDUtils.create()+fileFileName;
		        OutputStream os = new FileOutputStream(new File(root, fileFileName));
		        System.out.println("fileFileName: " + fileFileName);
		        System.out.println("file: " + file.getName());
		        System.out.println("file: " + file.getPath());
		        byte[] buffer = new byte[500];
		        int length = 0;
		        
		        while(-1 != (length = is.read(buffer, 0, buffer.length)))
		        {
		            os.write(buffer);
		        }
		        os.close();
		        is.close();
		        //接下来存到说说表中
		        SayMood sayMood = new SayMood();
		        sayMood.setContent("\\upload\\"+fileFileName);
		        sayMood.setCreateTime(new Date());
		        sayMood.setDzs(0);
		        sayMood.setIsDelete(2);
		        sayMood.setType(2);
		        User u = userService.getUser(userId);
		        sayMood.setSayUser(u);
		        sayMoodService.save(sayMood);
		        *//**
		         * 积分规则还没有做。上传图片需要加积分
		         *//*
		        //上传完毕，跳转列表action
		        ActionContext.getContext().put("url", "/user_homePage.do");
		        return "redirect";
		}else{
			return "login";
		}
	}*/
	
	public String report(){
		return SUCCESS;
	}
	//下面进行报表
	/**
	 * 查询上个月和这个月 账号注册
	 * @throws IOException 
	 */
	/*public void reportUser() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		//首先查询本月和上个月用户总人数
		List<User>	users = userService.findSYuser();
		List<User>	users2 = userService.findBYuser();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", getsy());
		if(users != null && users.size()>0){
			map1.put("TOTAL", users.size());
		}else{
			map1.put("TOTAL", 0);
		}
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", getBy());
		if(users2 != null && users2.size()>0){
			map2.put("TOTAL", users2.size());
		}else{
			map2.put("TOTAL", 0);
		}
		list.add(map1);
		list.add(map2);
		
		 //创建Option
	    GsonOption option = new GsonOption();
	    option.title("注册人数").tooltip(Trigger.axis).legend("数量（人）");
	    //横轴为值轴
	    option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));
	    //创建类目轴
	    CategoryAxis category = new CategoryAxis();
	    //柱状数据
	    Bar bar = new Bar("月份");
	    //饼图数据
	    Pie pie = new Pie("月份");
	    //循环数据
	    for (Map<String, Object> objectMap : list) {
	        //设置类目
	        category.data(objectMap.get("NAME"));
	        //类目对应的柱状图
	        bar.data(objectMap.get("TOTAL"));
	        //饼图数据
	        pie.data(new PieData(objectMap.get("NAME").toString(), objectMap.get("TOTAL")));
	    }
	    //设置类目轴
	    option.yAxis(category);
	    //饼图的圆心和半径
	    pie.center(900,380).radius(100);
	    //设置数据
	    option.series(bar, pie);
	    //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
	    option.grid().x(180);
	    //返回Option
	    out = resp.getWriter();
		 out.write(option.toString());
	}
	*/
	
	  public String getBy(){
		  Calendar c = Calendar.getInstance();
		   c.add(Calendar.MONTH, -0);
		  SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
		  String time = format.format(c.getTime());
		  System.out.println(time);
		  return time;
	}
	  
	  public String getsy(){
		  Calendar c = Calendar.getInstance();
		   c.add(Calendar.MONTH, -1);
		  SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
		  String time = format.format(c.getTime());
		  System.out.println(time);
		  return time;
	}
	  
	  
	  public void reportSay() throws IOException{/*
		  HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = null;
			//首先查询本月和上个月用户总人数
			List<SayMood>	users = sayMoodService.findSYusay();
			List<SayMood>	users2 = sayMoodService.findBYsay();
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("NAME", getsy());
			if(users != null && users.size()>0){
				map1.put("TOTAL", users.size());
			}else{
				map1.put("TOTAL", 0);
			}
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("NAME", getBy());
			if(users2 != null && users2.size()>0){
				map2.put("TOTAL", users2.size());
			}else{
				map2.put("TOTAL", 0);
			}
			list.add(map1);
			list.add(map2);
			
			 //创建Option
		    GsonOption option = new GsonOption();
		    option.title("发表图片数").tooltip(Trigger.axis).legend("数量（人）");
		    //横轴为值轴
		    option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));
		    //创建类目轴
		    CategoryAxis category = new CategoryAxis();
		    //柱状数据
		    Bar bar = new Bar("月份");
		    //饼图数据
		    Pie pie = new Pie("月份");
		    //循环数据
		    for (Map<String, Object> objectMap : list) {
		        //设置类目
		        category.data(objectMap.get("NAME"));
		        //类目对应的柱状图
		        bar.data(objectMap.get("TOTAL"));
		        //饼图数据
		        pie.data(new PieData(objectMap.get("NAME").toString(), objectMap.get("TOTAL")));
		    }
		    //设置类目轴
		    option.yAxis(category);
		    //饼图的圆心和半径
		    pie.center(900,380).radius(100);
		    //设置数据
		    option.series(bar, pie);
		    //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
		    option.grid().x(180);
		    //返回Option
		    out = resp.getWriter();
			 out.write(option.toString());
	  */}
	  
	  
	  public String welcome(){
		  return SUCCESS;
	  }
}
