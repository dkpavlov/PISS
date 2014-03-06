(function($) {

    $.extend({
        add2cart: function(source_id, target_id, callback) {

            var source = $('#' + source_id );
            var target = $('#' + target_id );
            var shadow = $('#' + source_id.substring(2) + '_shadow');
            if( !shadow.attr('id') ) {
                $('body').prepend('<div id="' + source.attr('id') + '_shadow" class="small_product animation_product">' +
                    '<div></div>'  +
                    '<a>Продукта е добавен <span>за печат</span></a>' +
                    '</div>');
                shadow = $('#'+source.attr('id')+'_shadow');
            }

            if( !shadow ) {
                alert('Cannot create the shadow div');
            }

            shadow.width(210);
            shadow.height(30);
            shadow.css('top', source.offset().top);
            shadow.css('left', source.offset().left);
            shadow.css('z-index', 9999);
            shadow.css('position', 'absolute');
            shadow.show();

            shadow.animate( {  top: target.offset().top, left: target.offset().left }, { duration: 900 } );
            shadow.animate( { opacity: 0 }, { duration: 300, complete: function()
            {
                shadow.remove();

            } });


        }
    });
})(jQuery);