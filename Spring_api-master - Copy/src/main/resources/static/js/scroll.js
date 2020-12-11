$('body').css('padding-top', $('.navmenu').outerHeight() + 'px')

// detect scroll top or down
if ($('.smart-scroll').length > 0) { // check if element exists
    var last_scroll_top = 0;
    $(window).on('scroll', function() {
        scroll_top = $(this).scrollTop();
        if(scroll_top < last_scroll_top) {
          $('.smart-scroll').removeClass('scrolled-down').addClass('scrolled-up');
          if($(".alert_success").attr("value")=="up"){
            $(".alert_success").attr("value","down");
            $(".alert_success").animate({"top":"+=60px"});
        }
        }
        else {
          $('.smart-scroll').removeClass('scrolled-up').addClass('scrolled-down');
          if($(".alert_success").attr("value")=="down"){
                $(".alert_success").attr("value","up");
                $(".alert_success").animate({"top":"-=60px"});
            }
        }
        last_scroll_top = scroll_top;
        $(".product_info_button").each(function(){
          if($(this).attr("value") == "up"){
          $(this).trigger("click");
          }
        });
    });
};

