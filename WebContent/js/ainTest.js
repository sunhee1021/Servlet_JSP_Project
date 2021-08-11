function reply_check() {
	var frm = document.reply;
	if (frm.reply_content.value == "") {
		alert("내용을 입력해주세요!");
		return false;
	}

	var formData = {
		reply_content: frm.querySelector('textarea[name=reply_content]').value,
		cNumber: frm.querySelector('input[name=cNumber]').value
	}

	runAjax("replyOk.ain", formData);
	
}

function reply_del_check(frm) {
	var result = confirm("댓글을 삭제하시겠습니까?😥");
		
		if(result) {
			reply_del(frm);
		}
}

function reply_del(frm) {

	var formData = {
		crNumber: frm.querySelector('input[name=crNumber]').value,
		cNumber: frm.querySelector('input[name=cNumber]').value
	}

	runAjax("replyDelete.ain", formData);
}

function runAjax(url, formData) {
	$.ajax(
		{
			url: url,
			type: "POST",
			data: formData,
			dataType: "json",
			success: function(responseData) {

				$('#span-reply').empty();

				var whynot = "";
				$.each(responseData, function(index, reply) {
					whynot += setReply(reply);

				});
				$('#span-reply').html(whynot);
				
				$('#replyContent').val('');
			},
			error: function(xhr) {
				console.log("here");
			}
		}
	)
}


function setReply(reply) {
	 
	var innerValue = "";
	
	innerValue += `<table width="80%" border="1" class="aintable">
							<tr align="left">
								<td width="80%">
								[${reply.nickname}] : ${reply.content}
								<br> 
								작성일:${reply.writedate}
								</td>
								<td width="20%" align="right">
								<form action="ReplyDeleteOk.do" method="POST" name="replyDel">
									<input type="hidden" name="crNumber" value="${reply.crNumber}"> 
									<input type="hidden" name="cNumber" value="${reply.cNumber}">`; 
									if(document.querySelector('#hidden-email').value === reply.email) {
										innerValue += `<input type="button" value="삭제" class="genric-btn success-border medium" onclick="reply_del_check(this.form)">`;
									}
									
	innerValue +=				`</form>
								</td>
							</tr>
						</table>`;
						
	return innerValue;
}
