var delete_ajax = function(url, id){
  $.ajax({
    type:'DELETE',
    url: url+id,
    success: function (data) {
      succes_message("sucessfully deleted");
      fill_container();      
  },
    error: function (jqXHR, textStatus, errorThrown) {
    }
  });
}

var create_card = function(element){
  $(".cards_container").append($('<div class="card" style="width: 18rem;"><img class="card-img-top" src="img/book.jpg" alt="Card image cap"><div class="card-body"><h5 class="card-title">Card title</h5><p class="card-text"></p><div id="delete_id_'+ element.id +'" class="delete btn btn-dark">Удалить</div></div></div>').attr("id", element.id));
  $(".card-title" ,"#"+element.id).text(element.title);
  $(".card-text","#"+element.id).text(element.iyear);
  $(".delete[id='delete_id_"+ element.id +"']").on("click", function(){
    delete_ajax("api/books/", element.id);
  })
}

var fill_container = function(){
  $(".cards_container").empty();
  $.ajax({
    type:'GET',
      url: '/api/books',
      success: function (data) {
        books = data;
        books.forEach(element => {
          create_card(element);
        });
      },
      error: function (jqXHR, textStatus, errorThrown) {}
  });
  
}

//! START !//
fill_container();
//! START !//

var open_form = function(){
  $("#grey_cart_background").css({"visibility":"visible"});
  var form = $(".form").css({"visibility":"visible"});
  form.animate({width:"+=55%",height:"+=550px"});
  form.attr("value","opened");
}

var close_form = function(){
  var form = $(".form").css({"visibility":"visible"});
  form.attr("value","closed");
  form.animate({width:"-=55%",height:"-=550px"}, function(){
    $(this).css({"visibility":"hidden"});
    $("#grey_cart_background").css({"visibility":"hidden"});
  });
 
}

var succes_message = function(text){
  $(".alert_success").text($(this).prev().prev().text() + text);
  $(".alert_success").css({"visibility":"visible"});
  $(".alert_success").animate({opacity: "-0"},3000,function(){
  $(this).css({"opacity":"1","visibility":"hidden"});
  });
}



$(".exit_form").on("click",function(){
  close_form();
})

$(".add").on("click", function(){
open_form();
})

$(".create").on("click", function(){
  
  var book = {
    "title": $("#title").val(),
    "iyear": $("#iyear").val(),
    "author": $("#author").val()
  }
  
  $.ajax({
    type:'POST',
    url: '/api/books',
    headers: {
                  "Content-Type": "application/json"
              },
    data: JSON.stringify(book),
    success: function (data) {
      close_form();
      succes_message("added to DB!");
      fill_container();      
  },
    error: function (jqXHR, textStatus, errorThrown) {
    }
  });

})
