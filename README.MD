<!-- TOC -->

- [开发环境](#开发环境)
- [开发文档](#开发文档)
- [运行示例](#运行示例)
    - [Administrator操作](#administrator操作)
    - [图书馆管理员(librarian)操作](#图书馆管理员librarian操作)
    - [读者(reader)操作](#读者reader操作)

<!-- /TOC -->
## 开发环境   
* 操作系统：Windows 10 64位
* IDE：[Eclipse](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2018-12/R/eclipse-inst-win64.exe)   
* 数据库: [MySQL8.0](https://dev.mysql.com/downloads/installer/)   
* 服务器：[tomcat8.5或以上](https://tomcat.apache.org/download-80.cgi)  
## 开发文档   
* [基于MVC设计模式的在线图书馆管理系统软件开发计划](./Reference/document/基于MVC设计模式的在线图书馆管理系统软件开发计划.pdf)   
* [基于MVC设计模式的在线图书馆管理系统软件设计说明](./Reference/document/基于MVC设计模式的在线图书馆管理系统软件设计说明.pdf)   
* [基于MVC设计模式的在线图书馆管理系统软件需求规格说明](./Reference/document/基于MVC设计模式的在线图书馆管理系统软件需求规格说明.pdf)   
* [基于MVC设计模式的在线图书馆管理系统软件研制总结报告](./Reference/document/基于MVC设计模式的在线图书馆管理系统软件研制总结报告.pdf)
* [数据库设计](./Reference/DatabaseDesign/)   
## 运行示例   
### Administrator操作   
1. 登录系统:   
<div align=center><img src="./image/image004.jpg"/></div>   
输入账号密码，选择身份，登录。
<div align=center><img src="./image/image006.jpg"/></div>   
进入首页，到达管理员首页，可以在侧边栏选择项目进行操作。
<div align=center><img src="./image/image008.jpg"/></div>   
2. 修改个人信息，管理员可以修改自己账号的账户名和密码。
<div align=center><img src="./image/image010.jpg"/></div>   
<div align=center><img src="./image/image012.jpg"/></div>   
3. 添加图书馆管理员，输入图书管理员姓名、初始化密码，即可得到图书管理员ID。   
<div align=center><img src="./image/image014.jpg"/></div>   
<div align=center><img src="./image/image016.jpg"/></div>   
4. 搜索图书馆管理员    
<div align=center><img src="./image/image018.jpg"/></div>   
<div align=center><img src="./image/image020.jpg"/></div>   
管理员可以按照ID和姓名两种方式查询图书管理员，可以查看全体图书管理员列表。查看全体人员列表以后可以选择删除或修改其中某一个图书管理员。 
<div align=center><img src="./image/image022.jpg"/></div>   
<div align=center><img src="./image/image024.jpg"/></div>   
<div align=center><img src="./image/image026.jpg"/></div>   
<div align=center><img src="./image/image028.jpg"/></div>   
5. 修改图书管理员信息   
<div align=center><img src="./image/image030.jpg"/></div>   
<div align=center><img src="./image/image032.jpg"/></div>   
<div align=center><img src="./image/image034.jpg"/></div>   
6. 删除图书管理员。名字与ID不匹配或是管理员有其他原因将不可删除。   
<div align=center><img src="./image/image036.jpg"/></div>   
<div align=center><img src="./image/image038.jpg"/></div>   
<div align=center><img src="./image/image040.jpg"/></div>   
<div align=center><img src="./image/image042.jpg"/></div>   
### 图书馆管理员(librarian)操作      
1. 在登陆界面登陆后，进入librarian主页面，界面中心处显示了Librarian ID，LibrarianName以及Librarian State，右上角处显示登陆的Librarian name，如下所示：
<div align=center><img src="./image/image044.jpg"/></div>   
2. 在点击侧边栏的Change Password后，进入更改密码界面，需要输入原先的密码，以及更新后的密码，以及确认密码，如下所示：
<div align=center><img src="./image/image046.jpg"/></div>   
若输入原先的密码错误，则弹出相应提示框，如下所示：   
<div align=center><img src="./image/image048.jpg"/></div>   
点击确定后回到之前的界面   
<div align=center><img src="./image/image050.jpg"/></div>   
输入正确的密码后点击Modify   
<div align=center><img src="./image/image052.jpg"/></div>   
点击确定返回Librarian主界面   
<div align=center><img src="./image/image054.jpg"/></div>   
3. 点击Manage Books后进行对图书的管理操作   
<div align=center><img src="./image/image056.jpg"/></div>   
点击Add Book：   
<div align=center><img src="./image/image058.jpg"/></div>   
<div align=center><img src="./image/image060.jpg"/></div>   
在输入正确的ISBN后，会自动通过ISBN输入信息   
<div align=center><img src="./image/image062.jpg"/></div>   
点击Validate and GO！后，进行ISBN的判断，若不是正确的ISBN则提示并重新输入，若是正确的，则自动显示所有信息    
<div align=center><img src="./image/image064.jpg"/></div>   
<div align=center><img src="./image/image066.jpg"/></div>   
<div align=center><img src="./image/image068.jpg"/></div>   
可手动选择图书位置：   
<div align=center><img src="./image/image070.jpg"/></div>   
点击Add进行提交   
<div align=center><img src="./image/image072.jpg"/></div>   
点击Confirm返回Add Book界面   
<div align=center><img src="./image/image074.jpg"/></div>   
4. 点击Delete Book进行删书操作   
<div align=center><img src="./image/image076.jpg"/></div>   
输入Book ID进行查询   
<div align=center><img src="./image/image078.jpg"/></div>   
若有相应的Book，点击Delete进行删除操作，下图表示删除成功：   
<div align=center><img src="./image/image080.jpg"/></div>   
点击确定返回Delete Book界面：   
<div align=center><img src="./image/image082.jpg"/></div>   
5. 点击Search Book进行书籍的查询操作：   
<div align=center><img src="./image/image084.jpg"/></div>   
默认查询方式为BookName：   
<div align=center><img src="./image/image086.jpg"/></div>   
可进行模糊搜索：   
<div align=center><img src="./image/image088.jpg"/></div>   
结果如下：   
<div align=center><img src="./image/image090.jpg"/></div>   
点击Details可查看本书的详细信息，并且进行修改信息操作：   
<div align=center><img src="./image/image092.jpg"/></div>   
<div align=center><img src="./image/image094.jpg"/></div>   
点击Edit进行信息修改   
<div align=center><img src="./image/image096.jpg"/></div>   
点击Refresh进行信息修改的保存：   
<div align=center><img src="./image/image098.jpg"/></div>   
修改成功:   
<div align=center><img src="./image/image100.jpg"/></div>   
通过Author进行搜书：   
<div align=center><img src="./image/image102.jpg"/></div>   
通过Publisher进行搜书：   
<div align=center><img src="./image/image104.jpg"/></div>   
通过ISBN进行搜书：   
<div align=center><img src="./image/image106.jpg"/></div>   
6. 点击Return Book进行还书操作:   
<div align=center><img src="./image/image108.jpg"/></div>   
输入BookID，点击Search后显示Book Information和Borrower：   
<div align=center><img src="./image/image110.jpg"/></div>   
点击Return：   
<div align=center><img src="./image/image112.jpg"/></div>   
7. 点击Manage Reader进行读者管理操作：   
<div align=center><img src="./image/image114.jpg"/></div>   
 添加读者：   
<div align=center><img src="./image/image116.jpg"/></div>   
 输入信息，其中读者状态默认为Unlock：   
<div align=center><img src="./image/image118.jpg"/></div>   
 点击Add：   
<div align=center><img src="./image/image120.jpg"/></div>   
表示添加成功,点击确认显示读者信息：   
<div align=center><img src="./image/image122.jpg"/></div>   
Modify Reader Information进行该读者信息修改：   
<div align=center><img src="./image/image124.jpg"/></div>   
<div align=center><img src="./image/image126.jpg"/></div>   
点击Reset可重置修改信息：   
<div align=center><img src="./image/image128.jpg"/></div>   
点击Submit提交修改信息：   
<div align=center><img src="./image/image130.jpg"/></div>   
输入Reader ID可查询其余读者信息：   
<div align=center><img src="./image/image132.jpg"/></div>   
<div align=center><img src="./image/image134.jpg"/></div>   
Modify Reader Information进行该读者信息修改：   
<div align=center><img src="./image/image135.jpg"/></div>   
8. 点击Delete Reader进行删除读者操作：   
<div align=center><img src="./image/image137.jpg"/></div>   
若有某栏目为空且进行删除，则出现提示信息：  
<div align=center><img src="./image/image139.jpg"/></div>   
补齐后，进行删除：   
<div align=center><img src="./image/image141.jpg"/></div>   
9. 点击Edit Reader进行读者信息修改：   
<div align=center><img src="./image/image143.jpg"/></div>   
<div align=center><img src="./image/image144.jpg"/></div>   
点击Modify Reader Information进行Reader信息修改：   
<div align=center><img src="./image/image146.jpg"/></div>      
修改操作与Add Reader之后的操作一致。    

10. 点击Search Reader进行读者信息查询   
<div align=center><img src="./image/image148.jpg"/></div>      
输入正确的信息后，进入如下界面：   
<div align=center><img src="./image/image150.jpg"/></div>      
可输入信息进入其他读者的信息查询界面：   
<div align=center><img src="./image/image152.jpg"/></div>      
可点击Detail进行详细信息查询：   
<div align=center><img src="./image/image154.jpg"/></div>      
<div align=center><img src="./image/image156.jpg"/></div>      
<div align=center><img src="./image/image158.jpg"/></div>      
11. 借书操作：   
<div align=center><img src="./image/image160.jpg"/></div>      
输入正确的信息后：    
<div align=center><img src="./image/image162.jpg"/></div>      
<div align=center><img src="./image/image164.jpg"/></div>      
点击借阅：  
<div align=center><img src="./image/image166.jpg"/></div>      
若此书不可被借，则：  
<div align=center><img src="./image/image168.jpg"/></div>      
12. 展示“借阅车”功能车   
<div align=center><img src="./image/image170.jpg"/></div>      
显示所有读者的借阅申请，以提交时间顺序进行排列，可对其进行审核, 若同意借阅，则点击Agree,将第一本借出后：   
<div align=center><img src="./image/image172.jpg"/></div>      
此时Book ID为3的书的状态变为borrowed：   
<div align=center><img src="./image/image174.jpg"/></div>      
将第二本拒绝后：   
<div align=center><img src="./image/image176.jpg"/></div>      
此时Book ID为1的书的状态为Inlib   
   
13. 点击logout进行登出操作：   
<div align=center><img src="./image/image178.jpg"/></div>      
<div align=center><img src="./image/image180.jpg"/></div>      
### 读者(reader)操作      
1. 登录系统   
<div align=center><img src="./image/image182.jpg"/></div>      
<div align=center><img src="./image/image184.jpg"/></div>      
2. 搜索书籍   
<div align=center><img src="./image/image186.jpg"/></div>      
<div align=center><img src="./image/image188.jpg"/></div>      
<div align=center><img src="./image/image190.jpg"/></div>      
3. 查看图书详细信息，图书详细信息将被展示，以及馆内所有这本书的状态将被分页显示，如果可以预约或者借阅，则会有添加至购物车按钮。   
<div align=center><img src="./image/image192.jpg"/></div>      
<div align=center><img src="./image/image194.jpg"/></div>      
<div align=center><img src="./image/image196.jpg"/></div>      
4. 添加书籍到购物车   
<div align=center><img src="./image/image197.jpg"/></div>      
<div align=center><img src="./image/image199.jpg"/></div>      
5. 修改个人信息   
<div align=center><img src="./image/image201.jpg"/></div>      
<div align=center><img src="./image/image203.jpg"/></div>      
<div align=center><img src="./image/image205.jpg"/></div>      
6. 查看借阅记录   
<div align=center><img src="./image/image207.jpg"/></div>      
7. 查看还书记录    
<div align=center><img src="./image/image209.jpg"/></div>      
8. 查看购物车，购物车内可以提交预约申请    
<div align=center><img src="./image/image211.jpg"/></div>      
<div align=center><img src="./image/image213.jpg"/></div>      