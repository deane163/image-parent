# 功能说明：
### image-Server 提供文件上传功能，并监控image-client的客户端接入情况。
  1-- 提供文件上传
  2-- 提供客户端选择

### image-client 负责数据的写入操作。
  1-- 负责数据文件的写入

### Zookeeper 负责服务器的注册，Image-client 客户端的注册操作，多个ImageClient节点可以注册到Zookeeper上
  1-- Image-Client 负责数据的写入操作（考虑后面使用Habase进行图片信息的存储，提高图片存储的效率）
  2-- 图片存储的处理结果，可以通过消息系统进行返回，并将结果返回给 Image - Server 记录图片是否上传成功；



