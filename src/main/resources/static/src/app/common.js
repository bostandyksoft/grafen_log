Ext.Loader.setPath('Log', 'src/app')
Ext.Loader.setConfig({
    disableCaching: false
});
Ext.tip.QuickTipManager.init()

Ext.getCSRF = function() {
    return Ext.get('csrfToken') ? Ext.get('csrfToken').getValue() : ''
}

if (typeof Log === 'undefined') {
    Log = {}
}

Ext.apply(Log, {
    openForm: function (clazz, panelConfig, actionConfig) {
        const panel = Ext.create(clazz, Ext.apply({
            floating: true,
            frame: true,
            modal: true,
            width: '80%',
            autoHeight: true,
            buttons: [{
                text: actionConfig.text,
                formBind: true,
                handler : function() {
                    actionConfig.handler(panel, function() {
                        panel.close();
                        panel.destroy();
                    })
                }
            }, {
                text: 'Отменить',
                handler: function () {
                    panel.close();
                    panel.destroy();
                }
            }]
        }, panelConfig));
        panel.show();
    },
    sendRequest: function (url, data, success, opts) {
        Ext.Ajax.request(Ext.apply({
            url: url,
            method: 'POST',
            jsonData: data,
            headers: {
                'X-CSRF-TOKEN': Ext.getCSRF()
            },
            success: function (response) {
                if (typeof success == 'function') {
                    let data = [];
                    if (response.responseText) {
                        data.push(JSON.parse(response.responseText));
                    }
                    success.apply(null, data)
                }
            },
            failure: function (response) {
                Ext.toast({
                    html: 'Ошибка',
                    title: response.status
                });
            }
        }, opts));
    }
});