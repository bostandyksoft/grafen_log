Ext.Loader.setPath('Index', 'src/app/index')

Ext.application({
    name: 'IndexView',
    requires: ['Index.view.MainView'],

    layout: 'fit',
    mainView: 'Index.view.MainView'
});