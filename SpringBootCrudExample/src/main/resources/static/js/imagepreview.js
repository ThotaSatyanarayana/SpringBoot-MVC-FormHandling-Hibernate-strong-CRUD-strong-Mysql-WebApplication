$(document).ready(
		function() {
			$('#preview').on(
					'change',
					function() {
						var extension = $('#preview').val().split('.').pop()
								.toLowerCase();
						if ($.inArray(extension,
								[ 'gif', 'png', 'jpg', 'jpeg' ]) == -1) {
							alert("please select only images");
						} else {
							$('#output').attr('src',
									window.URL.createObjectURL(this.files[0]))
									.width(150).height(150)
						}
					});
		});