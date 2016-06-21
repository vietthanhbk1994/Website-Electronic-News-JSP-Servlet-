$(function() {
	$(window).scroll(function() {
		if ($(this).scrollTop() > 100)
			$('#goTop').fadeIn();
		else
			$('#goTop').fadeOut();
	});
	$('#goTop').click(function() {
		$('body,html').animate({
			scrollTop : 0
		}, 'slow');
	});
});
