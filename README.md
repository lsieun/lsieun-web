# lsieun.cn

:smile:

## TODO

- [ ] 读取images
- [ ] [cache2k – High Performance Java Caching](https://cache2k.org/) 我想用它来进行Java内存cache

- [x] 缓存
  - [x] HTML进行缓存（5分钟）
  - [x] CSS进行缓存（3年）
  - [x] Images进行缓存（3年）
  - [x] font进行缓存（3年）
- [x] 压缩Response的内容，使用算法：gzip, deflate
- [x] 整理日志，打印request和response的Header内容
- [x] Connection可以为keep-alive
- [x] Etag: "5dc3a743-0"
- [x] Last-Modified: Thu, 07 Nov 2019 05:10:27 GMT
- [ ] 要注册URi Path和Handler
- [ ] bytes --> HttpUtils --> HttpConnection --> HttpPipeline --> HttpRouter request -->  ResourceHandler --> response --> bytes
- [ ] HttpRouter负责uri_path，并返回可能的相关的ResourceHandler的关联
- [x] 先将主页进行拆分，这是一个通用的列表页
- [x] 图片，标题，作者，日期，显示内容，链接，浏览数
- [x] 分页信息，一共几页，当前是第几页
- [x] /json/home/list/1
- [x] 第一次访问的时候，是静态页面，后续通过ajax请求来完成
- [x] 文件列表，应该存放在什么地方呢？应该存放在用户的当前目录当中，
- [x] 这些静态文件，总是复制来、复制去，一定会修改它的last modified time，如果直接从用户的当前目录读取，是不是就节省了复制的过程
- [ ] blacklist的名单，放到用户目录中来
- [x] 我觉得，首页还是要用自己的home_list.txt文件
- [x] 日志太多
- [ ] HTML拼接
- [ ] 缓存2：用If-Modified-Since
- [ ] 视频

- [x] 添加链接[Project Nayuki](https://www.nayuki.io/)

- [ ] 如果发现是blacklist，应该提早返回，不用去读取资源了，因为可能是视频资源，非常耗费时间

- [x] `sub`添加class为my_note

- [ ] 我有一些关键字，作为path当中的blacklist，但是，如果有一个合法的路径就是包含那些关键字，该怎么办呢？blacklist是不是应该记录原因，是不是应该限定一个时间解除block呢？

- [ ] 对IP地址进行查询，在日志当中显示出它的所在位置

- [ ] jquery.lazyload.js会有用吗？

- [ ] [The Java2HTML Tool](http://www.java2html.com/)

- [ ] 分离：程序与HTML页面分离，有必要吗？



- [ ] HttpConnection
    - [ ] HttpUtils: bytes --> request
    - [ ] HttpPipeline request --> response
        - [ ] HttpRouter: uri --> ResourceHandler 
            - [ ] HTML Handler
            - [ ] Javascript Handler
            - [ ] CSS Handler
            - [ ] Font Handler
            - [ ] Image Handler
            - [ ] JSON Handler

## HTML

- [首页模板（可编辑）](https://www.w3schools.com/w3css/tryit.asp?filename=tryw3css_templates_blog&stacked=h)
- [首页模板（展示）](https://www.w3schools.com/w3css/tryw3css_templates_blog.htm)
- [About Me的理想界面（展示）](https://www.w3schools.com/w3css/tryw3css_templates_cv.htm)
- [About Me的理想界面（可编辑）](https://www.w3schools.com/w3css/tryit.asp?filename=tryw3css_templates_cv&stacked=h)
- [fontawesome](https://fontawesome.com/) 这里的CSS样式很不错，将来可能会用到

## Banner

生成banner的URL地址：

```text
http://patorjk.com/software/taag/#p=display&f=Blocks&t=Web
```
## Utils Order

- [x] LogUtils + logging.properties
- [x] PropertyUtils + config.properties


