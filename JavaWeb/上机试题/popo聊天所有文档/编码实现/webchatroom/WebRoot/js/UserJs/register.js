$(function(){
	 $("input").hover(function(){$(this).addClass("color")},
		  function(){$(this).removeClass("color")}			 
	  )
			 
			  
	 $("textarea").hover(function(){$(this).addClass("color")},
		  function(){$(this).removeClass("color")}			 
	  )
	  

	
	Stip.config.p= 'right';// String [left|top|right|bottom] Ĭ�ϵ�����ķ��� 
	Stip.config.closeBtn=true;
	Stip.config.time=5000;
	Stip.config.kind="error";
    var str="" ;
    
    //����֤
	$("#nickname").focusout(function() {
		var nickname=$(this).val().length;
		alert($(this).val());
		if(nickname==0){
		  Stip('nickname').show(function(){

                str="����д�ǳ�";
            	return  str;
     	  });
     	}

      if( nickname >6 ){
               
                 Stip('nickname').show(function(){
                 
                 str="�ǳƲ��ܳ���6���ַ�";
            	 return  str;
     	  });
               
	  } 
			 
  });
	
	$("#sex").focusout(function() {
		var sex =$("#sex").val().length;
		if(sex==0){
		
		     Stip('sex').show(function(){  
                 str="����д�Ա�";
         	     return  str;
    	     }) ;
		 }
    });
	
	$("#password").focusout(function() {
		 var password=$("#password").val().length;
		 if(password==0){
		     Stip('password').show(function(){
                 str="����������";
          	     return  str;
          	    });
        	}
        	

        if( password>0 && password <6 || password >12 ){
             Stip('password').show(function(){
                 str="��������ȷ���ȵ����루6--12λ��";
                 return  str ;	 
			 }) ;
		}
	});
	
	$("#ampwd").focusout(function() {
		var ampwd =$("#ampwd").val().length;
		if(ampwd==0){
		  Stip('ampwd').show(function(){
              str="���ٴ������������";
        	  return  str;
          });
 	    }

      if(  $("#password").val() != ( $("#ampwd").val()) ){
         Stip('ampwd').show(function(){
            str="�����ȷ���������벻һ��";
            return  str ;	 
			}); 
		}
	});
	
	$("#age").focusout(function() {
		var age=$("#age").val().length;
		if(age==0){
		   Stip('age').show(function(){
              str="����������";
      	      return  str;
      	      });
	     }
		
	});
	

	$("#birthday").focusout(function() {
		var birthday=$("#birthday").val().length;
		if(birthday==0){
		        Stip('birthday').show(function(){
                   str="����������";
      	           return  str;
      	            }) ;
     	}	
	});
	
	$("#sign").focusout(function() {
		var sign=$("#sign").val().length;
		if(sign > 25){
		     Stip('sign').show(function(){
                 str="��������ȷ���ַ�����25����";
    	         return  str;
    	      }) ;
	   }	
	});
	
	
	  
	 })
	