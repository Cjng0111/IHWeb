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