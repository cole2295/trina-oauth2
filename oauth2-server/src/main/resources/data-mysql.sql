insert into user_info (id,username,email,password,mobile,role,urls) values(1,'总裁','111111@qq.com','e10adc3949ba59abbe56e057f20f883e','13811111111','ADMIN','/user/list,user/add,/user/mod,/user/del');
insert into user_info (id,username,email,password,mobile,role,urls) values(2,'总监','222222@qq.com','e10adc3949ba59abbe56e057f20f883e','13822222222','ADMIN','/user/list,user/add,/user/mod,/user/del');
insert into user_info (id,username,email,password,mobile,role,urls) values(3,'经理','333333@qq.com','e10adc3949ba59abbe56e057f20f883e','13833333333','ADMIN','/user/list,user/add,/user/mod,/user/del');
insert into user_info (id,username,email,password,mobile,role,urls) values(4,'员工1','444444@qq.com','e10adc3949ba59abbe56e057f20f883e','13844444444','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(5,'员工2','555555@qq.com','e10adc3949ba59abbe56e057f20f883e','13855555555','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(6,'员工3','666666@qq.com','e10adc3949ba59abbe56e057f20f883e','13866666666','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(7,'员工4','777777@qq.com','e10adc3949ba59abbe56e057f20f883e','13812312312','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(8,'员工5','888888@qq.com','e10adc3949ba59abbe56e057f20f883e','13811223344','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(9,'员工6','999999@qq.com','e10adc3949ba59abbe56e057f20f883e','13812233455','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(10,'员工7','100000@qq.com','e10adc3949ba59abbe56e057f20f883e','13812345678','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(11,'员工8','110000@qq.com','e10adc3949ba59abbe56e057f20f883e','13812332143','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(12,'员工9','111000@qq.com','e10adc3949ba59abbe56e057f20f883e','13812312123','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(13,'员工10','111100@qq.com','e10adc3949ba59abbe56e057f20f883e','13812314132','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(14,'员工11','111112@qq.com','e10adc3949ba59abbe56e057f20f883e','13812344321','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(15,'员工12','111123@qq.com','e10adc3949ba59abbe56e057f20f883e','13812344444','ADMIN','/user/list');
insert into user_info (id,username,email,password,mobile,role,urls) values(16,'员工13','111234@qq.com','e10adc3949ba59abbe56e057f20f883e','13812345555','ADMIN','/user/list');

insert into oauth_client_details(client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,client_name) values('trina_app','trina_user','5788dc4f70f00b2586a19c95','read,write,trust','password,authorization_code,refresh_token','http://localhost:9099/login','',null,null,'{}','','测试应用');