本文档用于仿游啊游网站项目，由于结对编程，必须写文档进行深层次沟通，以文档形式记录每一步开发过程，这才是做项目最宝贵的经验
1.创建readme.txt说明文档，记录开发过程
2.导入开发包
	beanUtils开发包(便捷操作javabean)
	log4j开发包(与beanUtils关联)
	jstl开发包(在jsp页面中减少java代码)
	mysql-connector-java-5.1.7-bin.jar(jdbc驱动)
3.组织程序包
	依先后顺序如下：
	com.fuandren.domain
	com.fuandren.dao
	com.fuandren.dao.impl
	com.fuandren.service
	com.fuandren.service.impl
	com.fuandren.web.controller(放处理请求的servlet)
	com.fuandren.web.UI(放为用户提供用户界面的servlet，一部分servlet专门负责接收用户请求，转到jsp，给用户传递一个界面)
	com.fuandren.utils(存放工具类)
	com.fuandren.exception(存放异常类)
	junit.test(做好每个模块后要测试)
4.创建数据库，设计表，充分考虑字段的必要性
create database travel;
use travel;
create table users
(
	id varchar(40) primary key,
	username varchar(40) not null unique,
	password varchar(40) not null
);
注：确认密码由servlet中的方法判断
5.根据表字段设计javabean，并生成set()和get()方法
public class User {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
6.设计dao层，封装和传递数据，以及数据校验
新建UserDaoImpl.java 
定义public void add(User user){...}方法，用于增加数据，接收javabean封装的数据，无返回值
定义public User find(String username,String password){...}方法，用于从数据库中查找某个用户，接收用户名和密码数据，找到之后会有一个user对象(javabean封装)返回该user对象
定义public boolean find(String username){...}方法，用于在增加用户数据之前检测数据库中的用户数据是否存在，接收用户名数据，返回一个布尔值表示查找是否存在
7.在junit.test中新建测试类UserDaoTest.java
定义public void testAdd(){...}方法，方法体中写入测试数据进行增加操作，查看C:\web_develop\32bit\web_workspace2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\user\WEB-INF\classes\users.xml 
定义public void testFind(){...}方法，方法体中写入用户名和密码测试数据，打印user对象的内存地址
定义public void testFindByUsername(){...}方法，方法体中写入用户名数据，测试用户名是否存在，打印布尔值
8.dao层开发完毕，开始开发web层
9.用service层调用dao层，抽取接口UserDao.java
10.开始开发service层，由service层统一对web层提供所有的业务服务，web层如果接收到请求，则所有的请求都交给service层处理
11.在com.fuandren.service.impl中新建BusinessServiceImpl.java 用于对web层提供所有的业务服务，现在业务服务有两个：登陆和注册
定义public void register(User user) throws UserExistException{...}方法，用于对web层提供注册服务，接收一个user对象(javabean封装)，无返回值，内部有dao对象调用dao层方法
定义public User login(String username,String password){...}方法，用于对web层提供登陆服务并传送用户名密码数据，由于密码不能明文显示故调用md5算法加密，加密代码需要抽象成一个ServiceUtils.java工具类，接收用户名和密码数据，返回一个user对象，如果是空值则表示没找到，如果不是空值则表示找到了
12.无论登陆还是注册都要操作dao层，这里先创建一个实现类对象（此处设计有缺陷，dao更改后会影响此处代码）
定义一个布尔值来控制业务逻辑 boolean b = dao.find(user.getUsername());
由于service层是web层来调用，所以这里的业务逻辑如果发现用户已存在，则需要返回消息提醒web层，用抛出编译时异常的方式提醒web层
新建一个异常包com.renbing.exception 在其中定义一个异常类UserExistException.java，用于封装信息带给web层，定义时需要从父类中拿到一个构造函数，如果web层收到该异常，则表示注册用户已存在
注册时不能直接添加密码，要将密码加密后传送到数据文件中
登陆时，进行查找，同样需要将密码转换成md5码再去数据文件中查找
service层开发完毕
13.测试service层，在junit.test包中新建ServiceTest.java测试类
定义方法public void testRegister(){...}，用来测试增加数据及其校验，用try catch获取UserExistException异常，打印对应信息
定义方法public void testLogin(){...}，用来测试登陆，打印user对象内存地址或空值
14.service层彻底开发完毕，后台彻底开发完毕
现在开始开发web层，专注于与用户交互
15.新建首页 index.jsp
写两个超链接，注册和登陆
16.在com.renbing.web.controller包中新建RegisterServlet.java对表单进行处理、校验、回显、反错
		1.对提交表单的字段进行合法性校验（把表单数据封装到formbean）
					1.1不应该分别获取然后单独校验，而是应该设计一个对象去代表这个表单，创建formbean封装表单
					新建一个com.fuandren.web.formbean包，该包属于web层，该包下的所有的对象代表一个个表单。
					现在创建注册表单RegisterForm.java 此类封装大量字段，生成set() get()方法
					现在，既然表单类代表一个表单，那么字段校验应该也属于该对象的方法，在表单类中对字段进行校验
					定义public boolean validate(){...}方法，对表单字段进行合法性校验，无参，返回值是一个布尔值代表合法或不合法
					//用户名不能为空，并且要是3-8位字母
					//密码不能为空，并且是3-8位数字
					//确认密码不能为空，并且要和一次一致
					//电子邮箱不能为空，并且要是一个格式合法的邮箱
					//生日可以为空，不为空时，必须要是一个日期
					//昵称不可以为空，并且要是汉字
					
					1.2获取表单数据，并封装到formbean中，不应该单独获取，而是应该创建工具类WebUtils.java
						在工具包com.fuandren.utils中新建web层工具类WebUtils.java 
						定义public static <T> T request2Bean(HttpServletRequest request,Class<T> beanCalss){...} 方法，将request中的数据放到formbean中，接收一个request对象和一个表单类的反射，返回一个静态类对象。具体细节是用BeanUtils把request对象中取出的字段和字段值封装到formbean中
							
					编写全局消息显示页面	在web根目录下新建message.jsp 显示消息，表示成功或者服务器未知错误				


		2.如果校验失败，跳回到表单页面，回显校验失败信息，直接把表单类的form对象通过request域对象 带到注册页面register.jsp中去
		3.如果校验成功，需要调用注册方法，这时需要获取一个user对象作为参数，需要把form对象中的数据方法user对象中，这就需要bean的拷贝
						在工具类WebUtils.java 中再定义一个方法public static void copyBean(Object src, Object dest){...} 用于实现bean的拷贝，接收一个原bean和一个目标bean，无返回值，调用BeanUtils。由于日期特殊，需要手写一个日期转换器。由于user中的字段比form中的字段多了一个id，所以要手动编写一个id产生器UUID
						在工具类WebUtils.java 中定义public static String generateID() {...}方法，产生一个全世界唯一的id
		4.如果serivce处理不成功,并且不成功的原因，是因为注册用户已存在的话，则跳回到注册页面，显示注册用户已存在的消息
		5.如果serivce处理不成功,并且不成功的原因是其它问题的话，跳转到网站的全局消息显示页面，为用户显示友好错误消息，并在后台记录异常，
		6.如果serivce处理成功，跳转到网站的全局消息显示页面，为用户注册成功的消息
			编写全局消息显示页面	在web根目录下新建message.jsp 显示消息，表示成功或者服务器未知错误
17.注册功能开发完毕，开始开发登录功能
在com.renbing.web.controller包中新建LoginServlet.java 获取用户名和密码数据并调用service层的方法进行登陆校验	
成功则返回首页
失败则跳转到全局消息显示页面，输出错误提示
18.首页应该判断用户登录状态，调用jstl表达式的<c:if>标签，判断user对象是否为空值
不为空，则打印用户昵称
为空，则显示出登陆和注册按钮
19.登录功能开发完毕，开始开发注销功能
20.在欢迎用户昵称旁边添加注销的超链接，指向LogoutServlet.java
在com.renbing.web.controller包中 新建LogoutServlet.java 
操作session的session.removeAttribute("user"); 方法移除user对象，使之为空，重新跳回首页
	
	





































