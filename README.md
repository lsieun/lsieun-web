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
- [ ] 先将主页进行拆分，这是一个通用的列表页
- [ ] 图片，标题，作者，日期，显示内容，链接，浏览数
- [x] 分页信息，一共几页，当前是第几页
- [x] /json/home/list/1
- [x] 第一次访问的时候，是静态页面，后续通过ajax请求来完成
- [ ] 文件列表，应该存放在什么地方呢？应该存放在用户的当前目录当中，
- [ ] 这些静态文件，总是复制来、复制去，一定会修改它的last modified time，如果直接从用户的当前目录读取，是不是就节省了复制的过程
- [ ] blacklist的名单，放到用户目录中来
- [ ] 我觉得，首页还是要用自己的home_list.txt文件
- [ ] 日志太多
- [ ] HTML拼接
- [ ] 缓存2：用If-Modified-Since
- [ ] 视频

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

- [首页模板](https://www.w3schools.com/w3css/tryit.asp?filename=tryw3css_templates_blog&stacked=h)
- [fontawesome](https://fontawesome.com/) 这里的CSS样式很不错，将来可能会用到

## Banner

生成banner的URL地址：

```text
http://patorjk.com/software/taag/#p=display&f=Blocks&t=Web
```
## Utils Order

- [x] LogUtils + logging.properties
- [x] PropertyUtils + config.properties

