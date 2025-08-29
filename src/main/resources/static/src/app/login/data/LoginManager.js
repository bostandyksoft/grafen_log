Ext.define('Login.data.LoginManager', {
    singleton: true,

    doRegister : function(data, cb, scope) {
        Ext.Ajax.request({
            url: 'app/login/register',
            jsonData: data,
            headers: {
                'X-CSRF-TOKEN': Ext.getCSRF()
            },
            success: function () {
                if (typeof cb === 'function') {
                    cb.call(scope);
                }
            },
            failure: function (response, opts) {
                Ext.toast({
                    html: 'Ошибка',
                    title: response.status
                });
            }
        });
    }
})