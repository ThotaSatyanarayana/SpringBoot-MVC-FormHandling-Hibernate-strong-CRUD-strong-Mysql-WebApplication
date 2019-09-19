$(document).ready(
		function() {
			$.ajax({
				type : "get",
				url : "chekedsubjects",
				data : "student_id=" + $("#student_id").val(),
				success : function(result) {
					var result = JSON.parse(result);
					$("input[name='subjects']").each(
							function() {
								if ($(this).checked) {
									var sThisVal = ($(this).checked ? $(this)
											.parent().children("span").attr(
													"class") : "");
									result += (result == "" ? sThisVal : ","
											+ sThisVal);
								}
							});
					for (var i = 0; i < result.length; i++) {
						$("input[name='subjects'][value='" + result[i] + "']")
								.prop('checked', true)
					}
				},

				error : function() {
					alert("error")
				}
			});
		});
