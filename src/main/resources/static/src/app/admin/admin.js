Ext.Loader.disableCaching = true;
Ext.Loader.setPath('Admin', 'src/app/admin')

Ext.application({
    name: 'AdminConsole',
    requires: ['Admin.MainView'],

    layout: 'fit',
    mainView: 'Admin.MainView'
});