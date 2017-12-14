# 接口文档


---

 1. 测试接口
  1. get

 ```
 url:http://ip:port/pentaKill/test/{id}
 
 例子:http://ip:port/pentaKill/test/1
 数据例子:
    {
    	"success": true,
    	"result": {
    		"id": 1,
    		"name": "a",
    		"value": "aa"
    	},
    	"msg": null
    }
  ```
 
 2. post
 
 ```
 url:http://ip:port/pentaKill/test 
 header:
 Content-Type: application/json
 
 body例子:
 {
	"name":"b",
	"value":"bb"
}

 返回样例:
 {
	"success": true,
	"result": {
		"id": 4,
		"name": "b",
		"value": "bb"
	},
	"msg": null
}
 
 
 ```
    

