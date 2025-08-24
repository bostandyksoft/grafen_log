Ext.define('Admin.MainView', {
    extend : 'Ext.tab.Panel',
    requires: [
        'Admin.CRUDGrid',
        'Admin.base.SchoolClass',
        'Admin.base.Student',
        'Admin.base.Teacher',
        'Admin.base.Subject',
    ],

    layout: 'fit',

    initComponent: function() {
        this.items = [{
            xtype: 'crud',
            title: 'Классы',
            object: Admin.base.Object.get('Admin.base.SchoolClass')
        },{
            xtype: 'crud',
            title: 'Предметы',
            object: Admin.base.Object.get('Admin.base.Subject')
        },{
            xtype: 'crud',
            title: 'Учителя',
            object: Admin.base.Object.get('Admin.base.Teacher')
        }, {
            xtype: 'crud',
            title: 'Ученики',
            object: Admin.base.Object.get('Admin.base.Student')
        }];
        this.callParent(arguments);
    }

})