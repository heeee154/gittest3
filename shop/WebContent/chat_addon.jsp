<%@page import="shop.login_mem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	login_mem mLogin_mem = (login_mem) session.getAttribute("mem_info");
	if(mLogin_mem == null) {
		mLogin_mem = new login_mem();
		mLogin_mem.setMem_id("방문자");
	}
	
	String mem_id = mLogin_mem.getMem_id();

%>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<body>
    <input type="hidden" value='<%=mem_id%>' id='chat_id' />
        

	<!-- 채팅창 -->
	<div id="_chatbox" style="display:none; background:#fff ">
		<fieldset>
			<div id="messageWindow" style="overflow-y: scroll; height: 200px"></div>
			<br/>
			<input id="inputMessage" type="text" onkeyup="enterkey()"/>
			<input type="submit" value="send" onclick="send()"/>
		</fieldset>	
	</div>
		<img class="chat" src="img/chat.png" style="width: 70px;"/>     
</body>

<!-- 말풍선 아이콘 클릭시 채팅창 열고 닫기 -->

<script>
	$(".chat").on({
		"click" : function(){
			if($(this).attr("src") == "img/chat.png") {
				$(".chat").attr("src", "img/chathide.png");
				$("#_chatbox").css("display", "block");
			} else if($(this).attr("src") == "img/chathide.png") {
				$(".chat").attr("src", "img/chat.png");
				$("#_chatbox").css("display", "none");
			}
		}
	});
</script>
<script type="text/javascript">
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket('ws://localhost:8088/shop/broadcasting');
	var inputMessage = document.getElementById('inputMessage');
	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event)
	};
	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	
	function onMessage(event) {
		var message = event.data.split("|");
		var sender = message[0];
		var content = message[1];
		
		if(content == "") {
		} else {
			if(content.match("/")) {
				if(content.match(("/" + $('#chat_id').val()))) {
					var temp = content.replace("/" + $('#chat_id').val(), "(귓속말) : ").split(":");
					if(temp[1].trim() == ""){
						// 문자가 비어있는 경우
					} else{
						$('#messageWindow').html($('#messageWindow').html() + "<p class='whisper'>"
								+ sender + content.replace("/" + $('#chat_id').val(), "(귓속말) : ") + "</p>");
					}
				} else {	
				}			
			} else{
				if(content.match("!")) {
					$('#messageWindow').html($('#messageWindow').html() 
							+ "<p class='chat_content'><b class='impress'>" + sender + " : " + content + "</b></p>");
				} else{
					$("#messageWindow").html($("#messageWindow").html()
	                        + "<p class='chat_content'>" + sender + " : " + content + "</p>");
				}
			}
		}
	}
	
	function onOpen(event) {
		$("#messageWindow").html("<p class='chat_content'>채팅에 참여하셨습니다.</p>");
	}
	
	function onError(event) {
// 		alert(event.data);
		console.log(event.data);
		$("#messageWindow").html("<p class='chat_content'>채팅 접속 불가</p>");
	}
	
	function send(){
		if(inputMessage.value == "" ) {
		} else {
			$("#messageWindow").html($('#messageWindow').html()
					+ "<p class='chat_content'>나 : " + inputMessage.value + "</p>");
		}
		webSocket.send($("#chat_id").val() + "|" + inputMessage.value);
		inputMessage.value = "";
	}
	
	// enter 키를 통해 send event 
	function enterkey() {
		if (window.event.keyCode == 13) {
            send();
        }
	}
	
	// 채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게 함
	window.setInterval(function(){
		var elem = document.getElementById('messageWindow');
		elem.scrollTop = elem.scrollHeight;
	}, 0);
	
</script>