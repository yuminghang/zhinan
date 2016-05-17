/**
 * Created by xiangshoulai on 2016/1/5.
 * V1.1
 * ����ͼƬ��������
 * lazy load
 *
 */
;(function(){
    'use strict';
    $.fn.imglazyLoad=function(opts){
        return new LazyLoad(opts);
    };
    var LazyLoad=(function(){
        /*
         * _src ͼƬ����·��
         * duration ��ʱ������ʱ��
         * container ָ�������ڵ�img�����û�б�ʾ���ȫ��ҳ���
         * rangeH���·�Χ����
         * animationName ��������
         * showDelay����ִ�е�ʱ��
         * */
        var imgLoad=function(opts){
            this.duration=opts.duration?opts.duration:this.duration;
            this.container=opts.container?opts.container:this.container;
            this._src=opts._src?opts._src:this._src;
            this.rangeH=opts.rangeH?opts.rangeH:this.rangeH;
            this.animationName=opts.animationName?opts.animationName:this.animationName;
            this.showDelay=opts.showDelay?opts.showDelay:this.showDelay;
            this.styleContianer=opts.styleContianer?opts.styleContianer:this.styleContianer;
            this.init();
        };
        imgLoad.prototype={
            refreshTimer:null,
            duration:300,
            _src:'_src',
            container:'imgContainer',
            rangeH:0,
            animationName:'fade',
            showDelay:'400ms',
            styleContianer:'styleID',
            /*���ϵķ�Χ*/
            belowthefold:function(element){
                return $(window).scrollTop()-this.rangeH <= $(element).offset().top;
            },
            /*���µķ�Χ*/
            abovethetop:function(element){
                return  $(element).offset().top<= $(window).scrollTop() + document.documentElement.clientHeight+this.rangeH;
            },
            /*
             *�ж�Ԫ���Ƿ������viewport�� ��������������չ����
             */
            inViewport:function(element){
                return this.belowthefold(element) && this.abovethetop(element);
            },
            setStyle: function(obj, json) {
                for (var i in json) {
                    obj.style[i] = json[i];
                }
            },
            addStyle:function(){
                var styleDiv=document.getElementById(this.styleContianer);
                if(styleDiv){
                    styleDiv.innerHTML += '<style>' +
                        ' @-webkit-keyframes fade {' +
                        ' 0%   { opacity: 0; }' +
                        ' 100% { opacity: 1; } ' +
                        '}' +
                        ' @keyframes fade { ' +
                        ' 0%   { opacity: 0; } ' +
                        ' 100% { opacity: 1; } ' +
                        '}' +
                        '</style>';
                }
            },
            loading:function(){
                var self=this;
                var list=[];
                var container=document.getElementById(self.container);
                if(container){
                    list=$(container).find('img['+self._src+']')
                }else{
                    list=$('img['+self._src+']');
                }
                list.each(function (i) {
                    var img = list.eq(i);
                    if (self.inViewport(img)) {
                        self.changSrc(img);
                    }
                });
            },
            changSrc:function(img){
                var self=this,p;
                var src = img.attr(''+self._src+'');
                img.attr('src', src).removeAttr(''+self._src+'');
                //p=img.parent();
                self.setStyle(img[0], {
                    animation:self.animationName+' '+self.showDelay+' 0s',
                    webkitAnimation:self.animationName+' '+self.showDelay+' 0s'
                });
            },
            init:function(){
                var self=this;
                self.addStyle();
                if (self.refreshTimer) {
                    clearTimeout(self.refreshTimer);
                    self.refreshTimer = null;
                }
                self.refreshTimer = setTimeout(function(){
                    self.loading();
                },10);
                /*
                 *�������� ��Ļ��ֹһ�������ЩͼƬ������viewport��
                 *��PC�˲�ͬ ���������ٶ����� ���ֻ����������Ĳ��� 1���ӵ��ӳٶ��ֻ��˵��û���˵��������
                 */
                $(window).on('scroll', function () {
                    if (self.refreshTimer) {
                        clearTimeout(self.refreshTimer);
                        self.refreshTimer = null;
                    }
                    self.refreshTimer = setTimeout(function(){
                        self.loading();
                    }, self.duration);
                });
            }
        };
        return imgLoad;
    })();
    $.loadImg={
        lazyLoad:function(opts){
            /*
             * ������
             * */
            $(document.body).imglazyLoad(opts);
        },
        preLoad:function(){
            /*
             * Ԥ����
             * */
        }
    };
})($);