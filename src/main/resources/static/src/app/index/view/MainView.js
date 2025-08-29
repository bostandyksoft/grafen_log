Ext.define('Index.view.MainView', {
    extend : 'Ext.tab.Panel',
    requires: [
        'Index.view.LessonsGrid'
    ],

    layout: 'fit',

    initComponent: function() {
        this.items = [{
            xtype: 'container',
            title: 'Расписание',
            html: 'Расписание'
        }, new Index.view.LessonsGrid({
            title: 'Уроки'
        })];
        this.callParent(arguments);
    }

})