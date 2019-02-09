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
<div align="center">![](./image/image004.jpg) </div>
输入账号密码，选择身份，登录。
<center>![](./image/image006.jpg)</center>
进入首页，到达管理员首页，可以在侧边栏选择项目进行操作。
<center>![](./image/image008.jpg) </center>   
2. 修改个人信息，管理员可以修改自己账号的账户名和密码。<center>![](./image/image010.jpg) ![](./image/image012.jpg)</center>   
3. 添加图书馆管理员，输入图书管理员姓名、初始化密码，即可得到图书管理员ID。<center>![](./image/image014.jpg) ![](./image/image016.jpg) </center>   
4. 搜索图书馆管理员 <center>![](./image/image018.jpg)  ![](./image/image020.jpg)</center>管理员可以按照ID和姓名两种方式查询图书管理员，可以查看全体图书管理员列表。查看全体人员列表以后可以选择删除或修改其中某一个图书管理员。</center>![](./image/image022.jpg) ![](./image/image024.jpg) ![](./image/image026.jpg) ![](./image/image028.jpg)</center>   
5. 修改图书管理员信息<center>![](./image/image030.jpg) ![](./image/image032.jpg) ![](./image/image034.jpg)</center>   
6. 删除图书管理员。名字与ID不匹配或是管理员有其他原因将不可删除。<center>![](./image/image036.jpg) ![](./image/image038.jpg) ![](./image/image040.jpg) ![](./image/image042.jpg)</center>   
### 图书馆管理员(librarian)操作   
1. 在登陆界面登陆后，进入librarian主页面，界面中心处显示了Librarian ID，LibrarianName以及Librarian State，右上角处显示登陆的Librarian name，如下所示：<center>![](./image/image044.jpg)   
2. 在点击侧边栏的Change Password后，进入更改密码界面，需要输入原先的密码，以及更新后的密码，以及确认密码，如下所示：<center>![](./image/image046.jpg) </center> 若输入原先的密码错误，则弹出相应提示框，如下所示：<center> ![](./image/image048.jpg)</center> 点击确定后回到之前的界面 <center>![](./image/image050.jpg) </center>输入正确的密码后点击Modify <center>![](./image/image052.jpg)</center>点击确定返回Librarian主界面 <center>![](./image/image054.jpg)</center>    
3. 点击Manage Books后进行对图书的管理操作 <center>![](./image/image056.jpg)</center> 点击Add Book：<center> ![](./image/image058.jpg) ![](./image/image060.jpg)</center>  在输入正确的ISBN后，会自动通过ISBN输入信息 <center>![](./image/image062.jpg) </center>点击Validate and GO！后，进行ISBN的判断，若不是正确的ISBN则提示并重新输入，若是正确的，则自动显示所有信息 <center>![](./image/image064.jpg) ![](./image/image066.jpg) ![](./image/image068.jpg)</center> 可手动选择图书位置： <center> ![](./image/image070.jpg)</center> 点击Add进行提交 <center>![](./image/image072.jpg)</center> 点击Confirm返回Add Book界面 <center> ![](./image/image074.jpg)</center>   
4. 点击Delete Book进行删书操作<center>![](./image/image076.jpg)</center> 输入Book ID进行查询<center>![](./image/image078.jpg)</center>若有相应的Book，点击Delete进行删除操作，下图表示删除成功：<center>![](./image/image080.jpg)</center>点击确定返回Delete Book界面：：<center>![](./image/image082.jpg)</center>   
5. 点击Search Book进行书籍的查询操作：<center>![](./image/image084.jpg)</center>默认查询方式为BookName：<center>![](./image/image086.jpg)</center>可进行模糊搜索：<center>![](./image/image088.jpg)</center>结果如下：<center>![](./image/image090.jpg)</center>点击Details可查看本书的详细信息，并且进行修改信息操作：<center>![](./image/image092.jpg)</center><center>![](./image/image094.jpg)</center> 点击Edit进行信息修改 <center>![](./image/image096.jpg)</center>点击Refresh进行信息修改的保存：<center>![](./image/image098.jpg)</center>修改成功:<center>![](./image/image100.jpg)</center> 通过Author进行搜书：<center>![](./image/image102.jpg)</center> 通过Publisher进行搜书：<center>![](./image/image104.jpg)</center>通过ISBN进行搜书：<center>![](./image/image106.jpg)</center> 
6. 点击Return Book进行还书操作:<center>![](./image/image108.jpg)</center> 输入BookID，点击Search后显示Book Information和Borrower：<center>![](./image/image110.jpg)</center>点击Return：<center>![](./image/image112.jpg)</center>    
7. 点击Manage Reader进行读者管理操作：<center>![](./image/image114.jpg)</center> 添加读者：<center>![](./image/image116.jpg)</center>输入信息，其中读者状态默认为Unlock：<center>![](./image/image118.jpg)</center>点击Add： <center>![](./image/image120.jpg)</center> 表示添加成功,点击确认显示读者信息：<center>![](./image/image122.jpg)</center> Modify Reader Information进行该读者信息修改：<center>![](./image/image124.jpg)</center> <center>![](./image/image126.jpg)</center> 点击Reset可重置修改信息：<center>![](./image/image128.jpg)</center> 点击Submit提交修改信息：<center>![](./image/image130.jpg)</center>输入Reader ID可查询其余读者信息：<center>![](./image/image132.jpg)</center> <center>![](./image/image134.jpg)</center>Modify Reader Information进行该读者信息修改：<center>![](./image/image135.jpg)</center>   
8. 点击Delete Reader进行删除读者操作：<center>![](./image/image137.jpg)</center>若有某栏目为空且进行删除，则出现提示信息： <center>![](./image/image139.jpg)</center> 
补齐后，进行删除：<center>![](./image/image141.jpg)</center>    
9. 点击Edit Reader进行读者信息修改：<center>![](./image/image143.jpg)</center> <center>![](./image/image144.jpg)</center> 点击Modify Reader Information进行Reader信息修改：<center>![](./image/image146.jpg)</center>修改操作与Add Reader之后的操作一致。   
10. 点击Search Reader进行读者信息查询 <center>![](./image/image148.jpg)</center>输入正确的信息后，进入如下界面：<center>![](./image/image150.jpg)</center> 可输入信息进入其他读者的信息查询界面：<center>![](./image/image152.jpg)</center> 可点击Detail进行详细信息查询：<center>![](./image/image154.jpg)</center> <center>![](./image/image156.jpg)</center> <center>![](./image/image158.jpg)</center> 
11. 借书操作：<center>![](./image/image160.jpg)</center>输入正确的信息后： <center>![](./image/image162.jpg)</center> <center>![](./image/image164.jpg)</center> 点击借阅：<center>![](./image/image166.jpg)</center> 若此书不可被借，则：<center>![](./image/image168.jpg)</center>   
12. 展示“借阅车”功能车<center>![](./image/image170.jpg)</center> 显示所有读者的借阅申请，以提交时间顺序进行排列，可对其进行审核, 若同意借阅，则点击Agree,将第一本借出后：<center>![](./image/image172.jpg)</center>此时Book ID为3的书的状态变为borrowed：<center>![](./image/image174.jpg)</center> 将第二本拒绝后：<center>![](./image/image176.jpg)</center> 此时Book ID为1的书的状态为Inlib   
13. 点击logout进行登出操作：<center>![](./image/image178.jpg)</center>点击确认，则自动跳转至首页:<center>![](./image/image180.jpg)</center> 
### 读者(reader)操作   
1. 登录系统<center>![](./image/image182.jpg)</center> <center>![](./image/image184.jpg)</center>   
2. 搜索书籍<center>![](./image/image186.jpg)</center>  <center>![](./image/image188.jpg)</center> <center>![](./image/image190.jpg)</center>    
3. 查看图书详细信息，图书详细信息将被展示，以及馆内所有这本书的状态将被分页显示，如果可以预约或者借阅，则会有添加至购物车按钮。<center>![](./image/image192.jpg)</center> <center>![](./image/image194.jpg)</center><center>![](./image/image196.jpg)</center>   
4. 添加书籍到购物车<center>![](./image/image197.jpg)</center><center>![](./image/image199.jpg)</center>
5. 修改个人信息 <center>![](./image/image201.jpg)</center> <center>![](./image/image203.jpg)</center> <center>![](./image/image205.jpg)</center>   
6. 查看借阅记录 <center>![](./image/image207.jpg)</center>   
7. 查看还书记录  <center>![](./image/image209.jpg)</center>   
8. 查看购物车，购物车内可以提交预约申请 <center>![](./image/image211.jpg)</center> <center>![](./image/image213.jpg)</center>
