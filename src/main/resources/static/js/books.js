var delete_ajax = function(url, id){
  return $.ajax({
    type:'DELETE',
    url: url+id,
    error: function (request, status, error) {
      alert(request.responseText);
  }
  });
}

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

var success_message = function(text, event){
  $(".alert_success").text($(this).prev().prev().text() + text);
  $(".alert_success").css({"visibility":"visible"});
  $(".alert_success").animate({opacity: "-0"},3000,function(){
  $(this).css({"opacity":"1","visibility":"hidden"});
  });
}

function create_card(data){
  $(".cards_container").append($(`
  <div id=${data.id} class="card" style="width: 18rem;">
    <img class="card-img-top" src="img/book.jpg" alt="Card image cap">
        <div class="card-body">
        <h5 class="card-title"><p class="card-text">${data.title}</p></h5>
        <p class="card-text">${data.iyear}</p>
        <div class="delete btn btn-dark" value="${data.id}">Удалить</div>
    </div>`));

}

function hide_card(id){
$(`.card[id="${id}"]`).remove();
}

$(".delete").on("click", function(elem){
delete_ajax("api/books/", $(this).attr("value")).done(success_message("DELETED")).then(hide_card($(this).attr("value")));
})

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
      success_message("added to DB!");
      create_card(data);     
  },
    error: function (jqXHR, textStatus, errorThrown) {
    }
  });

})
