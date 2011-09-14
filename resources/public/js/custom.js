// SmartCV custom Scripts below

$(document).ready(function() {

	// Color switcher when you click an nav anchor
    $('ul li a').click(function(){
    	var anyActive = $(this).parent().parent().find('li a.active');
    	// checks to see if the anchor parent list has an active anchor setted
    		if(anyActive) { 
  		  		// remove the active class from the previous selected anchor
    			$(this).parent().parent().find('li a.active').removeClass('active');
    			// add active state to the anchor clicked
    			$(this).addClass('active');
    		}
    });
    
    //The code below bind the slider to the different navs.
    $('li.sidebarLi a').bind("click", jump2);
    $('li.yearsLi a').bind("click", jump);
	
	
////////////////////////////////////////////////////////////	
///////////////	 Form Scripts  /////////////////////////////
////////////////////////////////////////////////////////////

///// FORM SETTINGS 
	var overlayClick = true; // If false when you click at the overlay the form won't close
	$('.formOverlay').css({opacity: 0.9}); // Transparency of the overlay ( 0 - 1 )

///// Open form Settings 
	$('a.openForm').click(function(){		
		$('.formWrap').show().find('div.formOverlay').fadeIn(1000, function(){
			$('.formWrap').find('div.formBox').slideDown(600);	
		});
	});

///// Overlay click to close	
	$('div.formOverlay').click(function(event){
		if(overlayClick){ 
			if(event.target == this){
			$('a.closeForm').click();
			}
		}
	});
	
///// Close form Settings	
	$('a.closeForm').click(function(){
		$('div.formBox').delay(300).slideUp(400, function(){
			$(this).siblings().fadeOut(1000, function(){
				var msgSent = $('.notification_ok');
				if(!msgSent){
					$(".note").empty();
				} 
				$('div.formBox').hide();
				$('div.formOverlay').hide();
				$('div.formWrap').hide();
			});
		});
		
	});
		
/////////////////////
// FORM VALIDATION //
/////////////////////

$(".contactForm").submit(function(){
	
	var str = $(this).serialize();
	
	$.ajax({
		   type: "POST",
		   url: "resources/form/contact.php", // path to contact.php
		   data: str,
		   success: function(msg){
	    	
		$("#note").ajaxComplete(function(event, request, settings){
			   
			if(msg == 'OK') {// Message Sent? Show the 'Thank You' message and hide the form
			    result = '<div class="notificationOk">Your message has been sent. Thank you!</div>';
			    $(".contactForm").fadeTo(500, 0.01).slideUp(500); // hides contact form and leaves Thank you message!
			  
			} // end if msg ok
			else {
			    result = msg;
			} 					
				$(this).html(result);	
		
				}); // end ajax complete
				
			} // end function success
		
		}); // end $.ajax 
	
	return false;	
	
	}); // end contact form submit function

	
});

