Ext.Loader.setPath('Admin', 'src/app/admin')

Ext.application({
    name: 'AdminConsole',
    requires: ['Admin.MainView'],

    layout: 'fit',
    mainView: 'Admin.MainView'
});

Log = {
    sendRequest: function (url, data, success, opts) {
        Ext.Ajax.request(Ext.apply({
            url: url,
            method : 'POST',
            jsonData: data,
            headers: {
                'X-CSRF-TOKEN': Ext.getCSRF()
            },
            success: function(response) {
                if (typeof success == 'function') {
                    let data = [];
                    if (response.responseText) {
                        data.push(JSON.parse(response.responseText));
                    }
                    success.apply(null, data)
                }
            },
            failure: function (response, opts) {
                Ext.toast({
                    html: 'Ошибка',
                    title: response.status
                });
            }
        }, opts));
    }
}