$(function () {

    switch (menu) {
        case 'About as':
            $('#about').addClass('active');
            break;
        case 'Our contact':
            $('#contact').addClass('active');
            break;
        case 'All products':
            $('#listProducts').addClass('active');
            break;
        default:
            $('#listProducts').addClass('active');
            $('#a_' + menu).addClass('active');
            break;
    }
});