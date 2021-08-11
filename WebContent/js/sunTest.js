function reply_check() {
	var frm = document.reply;
	if (frm.reply_content.value == "") {
		alert("내용을 입력해주세요!");
		return false;
	}

	var formData = {
		reply_content: frm.querySelector('textarea[name=reply_content]').value,
		c_number: frm.querySelector('input[name=c_number]').value
	}

	runAjax("ReplyOk.sun", formData);
	
}

function reply_del(frm) {

	var formData = {
		cr_number: frm.querySelector('input[name=cr_number]').value,
		c_number: frm.querySelector('input[name=c_number]').value
	}

	runAjax("ReplyDeleteOk.sun", formData);
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
				
				$('#reply_content').val('');
			},
			error: function(xhr) {
				console.log("here");
			}
		}
	)
}


function setReply(reply) {
	return `<table width="80%" border="1" class="replysun">
							<tr>
								<th colspan="2">REPLY LIST</th>
							</tr>
							<tr align="left">
								<td width="80%">
								[${reply.nickname}] : ${reply.content}
								<br> 
								작성일:${reply.writedate}
								</td>
								<td width="20%" align="right">
								<form action="ReplyDeleteOk.do" method="POST" name="replyDel">
									<input type="hidden" name="cr_number" value="${reply.cr_number}"> 
									<input type="hidden" name="c_number" value="${reply.c_number}"> 
									<input type="button" value="삭제" onclick="reply_del(this.form)">
								</form>
								</td>
							</tr>
						</table>`;
}
