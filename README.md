# IHWeb——Cjng非遗项目网页

## 一、 项目配置

### 1.1  Nginx配置

​		nginx.conf配置代码：

```
server {
        listen       90;
        server_name  localhost;

        location / {
            root   html;
            index  index.html;
        }

        location ^~ /api/ {
			rewrite ^/api/(.*)$ /$1 break;
			proxy_pass http://localhost:8081;
        }

        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
```

### 1.2  Springboot相关配置

​		Spring相关基础配置和工具类配置详见各模块下的pom.xml，在[application.yml]([IHWeb/cjng-parent/cjng-management/src/main/resources/application.yml at main · Cjng0111/IHWeb (github.com)](https://github.com/Cjng0111/IHWeb/blob/main/cjng-parent/cjng-management/src/main/resources/application.yml))中对JDBC和阿里云accessKey进行配置。

## 二、 实现功能

### 2.1  登陆验证

​		设置MVC配置类对除了登陆login界面的http请求进行登陆检查，使用拦截器验证http请求中的jwt令牌，如果没有登录或异常访问，则给前端传回msg="NOT_LOGIN"的error异常信息，前端接收到异常信息后进入到登陆界面

### 2.2  全局异常处理器

​		设置全局异常处理器，设置任何Exception都会返回msg="操作失败"的error异常信息，前端接收到异常信息后将弹出"操作失败"的弹窗，后将数据回滚至操作前的状态。

### 2.3  AOP记录操作日志

​		设置注解@CUDLog，对被@CUDLog注解的方法进行面向切面编程，记录操作者、操作时间、操作方法等详细信息，对用户的编辑行为进行日志留存，并将日志保存到数据库operate_log中。

### 2.4  增加用户数据 

​		根据填写内容，判断内容是否合法，将合法数据存入数据库中，并重新进行分页查询，并将操作记录在数据库operate_log中。

​		用户上传图片作为用户头像时，会在阿里云的OSS仓库建立相关对象，并返回链接供前端显示。

>当用户名，姓名，性别都不为空，且用户名不与数据库中数据重复时，我们认为该数据是合法的

### 2.5  查询用户数据

​		显示姓名、头像、性别、用户等级、创建日期和最后操作时间，头像为创建/编辑账户时建立的阿里云OSS链接，可以在前端展示图片。并支持编辑和删除操作。

​		在查询员工时支持姓名模糊查询，支持包含姓名，性别，创建日期一项或多项信息进行查询，查询后数据信息按照更新时间降序排序。默认设置5条/页的分页查询，支持显示总数、调整每页显示条数、直接前往的完整功能。

### 2.6  删除用户数据  

​		点击删除操作后，可以删除用户数据。并将操作日志传输到数据库operate_log中。

### 2.7  修改用户数据

​		点击编辑操作后，可以修改用户数据。前端会自动调取数据放在修改表单中，如果表单的某个数据为空，则该数据不进行更新，其他数据照常修改，并将操作记录在数据库operate_log中。

### 2.8  增加项目分类 

​		根据填写内容，判断内容是否合法，将合法数据存入数据库中，并重新进行分页查询，并将操作记录在数据库operate_log中。

>当项目分类名不为空，且不与数据库中的数据重复时，我们认为该数据是合法的

### 2.9  删除项目分类  

​		点击删除操作后，可以删除项目分类数据。并将操作日志传输到数据库operate_log中。

### 2.10  修改项目分类

​		点击编辑操作后，可以修改项目分类名。前端会自动调取数据放在修改表单中，操作后，会将操作记录在数据库operate_log中

>需要注意的是，与修改用户数据不同，如果修改的项目分类名为空，则会将空字符串作为数据进行修改