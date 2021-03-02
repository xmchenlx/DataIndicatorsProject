# This is the tool of test demo program named Data Indicators Project.

This tool can help the business people who are meeting the trouble which process the data information but can not understand the meaning of some word. 

This tool is stored data in Neo4j Desktp(4.2.1) , proceed requests by Springboot. if you want to running this demo, you have to installed the JDK11 in your PC, and checked  the environment variable was correct also.



# Commit Log
##2021-02-26
- 新增自定义查询排序功能
- 修复因网页与词典双端发起请求产生的Session不一致导致设置无效的问题
- 新增自定义Session监听
- 新增排序设置实体对象
- 优化请求处理顺序（仍在优化）

## 2021-02-23

- 完善Java调用外部程序的代码
- 新增从Jar读取资源文件至指定文件夹的函数（配合代理设置.exe）
- 优化代理设置.exe的代码（C#）

## 2021-02-22

- 优化控制层传参（新增关系）
- 优化基础节点实体
- 新增关系实体
- 基本词类词与指标实体新增关系查询
- lx工具新增判断辅助函数
- 前端新增图表显示（Echarts）
- 修复图表显示逻辑
- 优化前端界面
- 新增图表数据处理函数

## 2021-02-11

- 新年快乐~
- Happy Chinese Year~

## 2021-02-08

- 新增“报表/报文实体”；
- 新增查询向导：查询结果总数量超出50项时，展示查询向导块，单击向导标题可跳转至该类的结果列表。
- 前端页面展示逻辑更改为Handlebars处理。
- 优化页面排版效果；
- 追加有道代理设置程序【C#】，程序可以使用，待编写java执行代码；
- 修复基础结点的继承细则。

## 2021-02-05

- 新增“报表/报文实体”；
- 新增查询向导：查询结果总数量超出50项时，展示查询向导块，单击向导标题可跳转至该类的结果列表。
- 新增“IBM分类”实体，但查询出现问题，处于未完成状态。

## 2021-02-04

- 更改ip动态获取方式；
- 优化控制台输出文字效果；
- 更改“基本词类词”的显示内容，修补null时仍然执行导致报错的问题；
- 将“基础数据标准”与“代码”拆分查询，但目前无法排除多个标签的节点导致死查询且查询结果列表会重复，所以目前取消拆开显示；
- 修改显示界面边框超出的问题



## 2020-12-31

做了好多，不记得了