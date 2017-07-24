$(function () {

    switch (menu) {
        case 'About as':
            $('#about').addClass('active');
            break;
        case 'Our contact':
            $('#contact').addClass('active');
            break;
        default:
            $('#home').addClass('active');
            break
    }
})