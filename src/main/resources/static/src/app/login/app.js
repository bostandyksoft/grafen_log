Ext.Loader.setPath('Login', 'src/app/login')

Ext.application({
    name: 'LoginApp',
    requires: ['Login.view.MainView'],

    mainView: {
        xtype: 'container',
        layout: 'center',
        items : [{
            xtype : 'login.main.view'
        }]
    }
});