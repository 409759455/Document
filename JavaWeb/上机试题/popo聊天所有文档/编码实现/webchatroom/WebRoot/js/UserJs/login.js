   
$(function(){ 

	
	//���ݴ���
	  $("#submit").click(function(){    
          var qq = $("input[name='qq']").val();    //��ȡ�˺�    
          var password = $("input[name='password']").val();        //��ȡ����    
 
          var jsonUser = {qq:qq, password:password};    //JSON����    
              
          //ע��:jsonUser.toString()���ַ�������,javaScript�е�toString�����ڲ����ͱ�����,��Ӧ�����·���    
          var strUser = JSON.stringify(jsonUser);    //��JSON����ת���JSON��ʽ���ַ���,    
          //var strUser = jsonUser.toJSONString();  
              
          $.post("/webchatroom/login.do", {json: strUser}, callback,'json');    
      });    
 
      function callback(json){
      
          	//alert( json.msg + " \n���룺"  + json.password + "\n������" + json.name); 
          	jAlert(json.msg , ' ');
          	
          	
          	
          
      }    
		
})
