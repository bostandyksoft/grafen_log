Ext.define('Index.view.UsersGrid', {
    extend : 'Ext.container.Container',
    requires: [
        'Log.base.User',
        'Index.controller.UsersGridController'
    ],

    alias: 'widget.users',

    controller: 'users.grid',

    layout: 'fit',

    viewModel : true,

    initComponent: function() {
        const me =this;
        me.createLayout();
        me.callParent(arguments);
    },

    createLayout : function() {
        const me = this;
        me.items = [me.grid = new Ext.grid.Panel({
            tbar: [{
                text: 'Редактировать',
                iconCls: 'fa fa-edit',
                handler: 'edit',
                disabled : true,
                bind : {
                    disabled: '{!grid.selection}'
                }
            }, {
                text: 'Удалить',
                iconCls: 'fa fa-remove',
                handler: 'remove',
                disabled : true,
                bind : {
                    disabled: '{!grid.selection}'
                }
            }],
            reference : 'grid',
            columns : [{
                text: 'Логин',
                dataIndex: 'username',
                width: '10%'
            }, {
                text: 'Информация',
                dataIndex: 'info',
                flex: 1
            }, {
                xtype: 'checkcolumn',
                text: 'Администратор',
                dataIndex: 'admin',
                width: '20px',
                disabled: true
            }],
            store : Log.base.Object.get('Log.base.User').all(),
            viewConfig: {
                getRowClass: function(record){
                    return record.get("active") ? "row-valid" : "row-disabled";
                }
            },
            listeners : {
                rowdblclick : 'edit'
            }
        })]
    }

})