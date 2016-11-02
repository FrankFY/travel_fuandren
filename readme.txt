���ĵ����ڷ��ΰ�����վ��Ŀ�����ڽ�Ա�̣�����д�ĵ��������ι�ͨ�����ĵ���ʽ��¼ÿһ���������̣����������Ŀ���ľ���
1.����readme.txt˵���ĵ�����¼��������
2.���뿪����
	beanUtils������(��ݲ���javabean)
	log4j������(��beanUtils����)
	jstl������(��jspҳ���м���java����)
	mysql-connector-java-5.1.7-bin.jar(jdbc����)
3.��֯�����
	���Ⱥ�˳�����£�
	com.fuandren.domain
	com.fuandren.dao
	com.fuandren.dao.impl
	com.fuandren.service
	com.fuandren.service.impl
	com.fuandren.web.controller(�Ŵ��������servlet)
	com.fuandren.web.UI(��Ϊ�û��ṩ�û������servlet��һ����servletר�Ÿ�������û�����ת��jsp�����û�����һ������)
	com.fuandren.utils(��Ź�����)
	com.fuandren.exception(����쳣��)
	junit.test(����ÿ��ģ���Ҫ����)
4.�������ݿ⣬��Ʊ���ֿ����ֶεı�Ҫ��
create database travel;
use travel;
create table users
(
	id varchar(40) primary key,
	username varchar(40) not null unique,
	password varchar(40) not null
);
ע��ȷ��������servlet�еķ����ж�
5.���ݱ��ֶ����javabean��������set()��get()����
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
6.���dao�㣬��װ�ʹ������ݣ��Լ�����У��
�½�UserDaoImpl.java 
����public void add(User user){...}�����������������ݣ�����javabean��װ�����ݣ��޷���ֵ
����public User find(String username,String password){...}���������ڴ����ݿ��в���ĳ���û��������û������������ݣ��ҵ�֮�����һ��user����(javabean��װ)���ظ�user����
����public boolean find(String username){...}�����������������û�����֮ǰ������ݿ��е��û������Ƿ���ڣ������û������ݣ�����һ������ֵ��ʾ�����Ƿ����
7.��junit.test���½�������UserDaoTest.java
����public void testAdd(){...}��������������д��������ݽ������Ӳ������鿴C:\web_develop\32bit\web_workspace2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\user\WEB-INF\classes\users.xml 
����public void testFind(){...}��������������д���û���������������ݣ���ӡuser������ڴ��ַ
����public void testFindByUsername(){...}��������������д���û������ݣ������û����Ƿ���ڣ���ӡ����ֵ
8.dao�㿪����ϣ���ʼ����web��
9.��service�����dao�㣬��ȡ�ӿ�UserDao.java
10.��ʼ����service�㣬��service��ͳһ��web���ṩ���е�ҵ�����web��������յ����������е����󶼽���service�㴦��
11.��com.fuandren.service.impl���½�BusinessServiceImpl.java ���ڶ�web���ṩ���е�ҵ���������ҵ���������������½��ע��
����public void register(User user) throws UserExistException{...}���������ڶ�web���ṩע����񣬽���һ��user����(javabean��װ)���޷���ֵ���ڲ���dao�������dao�㷽��
����public User login(String username,String password){...}���������ڶ�web���ṩ��½���񲢴����û����������ݣ��������벻��������ʾ�ʵ���md5�㷨���ܣ����ܴ�����Ҫ�����һ��ServiceUtils.java�����࣬�����û������������ݣ�����һ��user��������ǿ�ֵ���ʾû�ҵ���������ǿ�ֵ���ʾ�ҵ���
12.���۵�½����ע�ᶼҪ����dao�㣬�����ȴ���һ��ʵ������󣨴˴������ȱ�ݣ�dao���ĺ��Ӱ��˴����룩
����һ������ֵ������ҵ���߼� boolean b = dao.find(user.getUsername());
����service����web�������ã����������ҵ���߼���������û��Ѵ��ڣ�����Ҫ������Ϣ����web�㣬���׳�����ʱ�쳣�ķ�ʽ����web��
�½�һ���쳣��com.renbing.exception �����ж���һ���쳣��UserExistException.java�����ڷ�װ��Ϣ����web�㣬����ʱ��Ҫ�Ӹ������õ�һ�����캯�������web���յ����쳣�����ʾע���û��Ѵ���
ע��ʱ����ֱ��������룬Ҫ��������ܺ��͵������ļ���
��½ʱ�����в��ң�ͬ����Ҫ������ת����md5����ȥ�����ļ��в���
service�㿪�����
13.����service�㣬��junit.test�����½�ServiceTest.java������
���巽��public void testRegister(){...}�����������������ݼ���У�飬��try catch��ȡUserExistException�쳣����ӡ��Ӧ��Ϣ
���巽��public void testLogin(){...}���������Ե�½����ӡuser�����ڴ��ַ���ֵ
14.service�㳹�׿�����ϣ���̨���׿������
���ڿ�ʼ����web�㣬רע�����û�����
15.�½���ҳ index.jsp
д���������ӣ�ע��͵�½
16.��com.renbing.web.controller�����½�RegisterServlet.java�Ա����д���У�顢���ԡ�����
		1.���ύ�����ֶν��кϷ���У�飨�ѱ����ݷ�װ��formbean��
					1.1��Ӧ�÷ֱ��ȡȻ�󵥶�У�飬����Ӧ�����һ������ȥ���������������formbean��װ��
					�½�һ��com.fuandren.web.formbean�����ð�����web�㣬�ð��µ����еĶ������һ��������
					���ڴ���ע���RegisterForm.java �����װ�����ֶΣ�����set() get()����
					���ڣ���Ȼ�������һ��������ô�ֶ�У��Ӧ��Ҳ���ڸö���ķ������ڱ����ж��ֶν���У��
					����public boolean validate(){...}�������Ա��ֶν��кϷ���У�飬�޲Σ�����ֵ��һ������ֵ����Ϸ��򲻺Ϸ�
					//�û�������Ϊ�գ�����Ҫ��3-8λ��ĸ
					//���벻��Ϊ�գ�������3-8λ����
					//ȷ�����벻��Ϊ�գ�����Ҫ��һ��һ��
					//�������䲻��Ϊ�գ�����Ҫ��һ����ʽ�Ϸ�������
					//���տ���Ϊ�գ���Ϊ��ʱ������Ҫ��һ������
					//�ǳƲ�����Ϊ�գ�����Ҫ�Ǻ���
					
					1.2��ȡ�����ݣ�����װ��formbean�У���Ӧ�õ�����ȡ������Ӧ�ô���������WebUtils.java
						�ڹ��߰�com.fuandren.utils���½�web�㹤����WebUtils.java 
						����public static <T> T request2Bean(HttpServletRequest request,Class<T> beanCalss){...} ��������request�е����ݷŵ�formbean�У�����һ��request�����һ������ķ��䣬����һ����̬����󡣾���ϸ������BeanUtils��request������ȡ�����ֶκ��ֶ�ֵ��װ��formbean��
							
					��дȫ����Ϣ��ʾҳ��	��web��Ŀ¼���½�message.jsp ��ʾ��Ϣ����ʾ�ɹ����߷�����δ֪����				


		2.���У��ʧ�ܣ����ص���ҳ�棬����У��ʧ����Ϣ��ֱ�Ӱѱ����form����ͨ��request����� ����ע��ҳ��register.jsp��ȥ
		3.���У��ɹ�����Ҫ����ע�᷽������ʱ��Ҫ��ȡһ��user������Ϊ��������Ҫ��form�����е����ݷ���user�����У������Ҫbean�Ŀ���
						�ڹ�����WebUtils.java ���ٶ���һ������public static void copyBean(Object src, Object dest){...} ����ʵ��bean�Ŀ���������һ��ԭbean��һ��Ŀ��bean���޷���ֵ������BeanUtils�������������⣬��Ҫ��дһ������ת����������user�е��ֶα�form�е��ֶζ���һ��id������Ҫ�ֶ���дһ��id������UUID
						�ڹ�����WebUtils.java �ж���public static String generateID() {...}����������һ��ȫ����Ψһ��id
		4.���serivce�����ɹ�,���Ҳ��ɹ���ԭ������Ϊע���û��Ѵ��ڵĻ��������ص�ע��ҳ�棬��ʾע���û��Ѵ��ڵ���Ϣ
		5.���serivce�����ɹ�,���Ҳ��ɹ���ԭ������������Ļ�����ת����վ��ȫ����Ϣ��ʾҳ�棬Ϊ�û���ʾ�Ѻô�����Ϣ�����ں�̨��¼�쳣��
		6.���serivce����ɹ�����ת����վ��ȫ����Ϣ��ʾҳ�棬Ϊ�û�ע��ɹ�����Ϣ
			��дȫ����Ϣ��ʾҳ��	��web��Ŀ¼���½�message.jsp ��ʾ��Ϣ����ʾ�ɹ����߷�����δ֪����
17.ע�Ṧ�ܿ�����ϣ���ʼ������¼����
��com.renbing.web.controller�����½�LoginServlet.java ��ȡ�û������������ݲ�����service��ķ������е�½У��	
�ɹ��򷵻���ҳ
ʧ������ת��ȫ����Ϣ��ʾҳ�棬���������ʾ
18.��ҳӦ���ж��û���¼״̬������jstl���ʽ��<c:if>��ǩ���ж�user�����Ƿ�Ϊ��ֵ
��Ϊ�գ����ӡ�û��ǳ�
Ϊ�գ�����ʾ����½��ע�ᰴť
19.��¼���ܿ�����ϣ���ʼ����ע������
20.�ڻ�ӭ�û��ǳ��Ա����ע���ĳ����ӣ�ָ��LogoutServlet.java
��com.renbing.web.controller���� �½�LogoutServlet.java 
����session��session.removeAttribute("user"); �����Ƴ�user����ʹ֮Ϊ�գ�����������ҳ
	
	





































