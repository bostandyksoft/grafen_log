Ext.define('Index.view.ClassGrid', {
    extend : 'Ext.container.Container',
    requires: [
        'Log.base.SchoolClass',
        'Index.controller.ClassGridController'
    ],

    alias: 'widget.classes',

    controller: 'classes.grid',

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
                text: 'Добавить запись',
                iconCls: 'fa fa-plus',
                handler: 'addRecord'
            }, {
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
                text: 'Название',
                dataIndex: 'name',
                flex: 1
            }],
            store : Log.base.Object.get('Log.base.SchoolClass').all(),
            listeners : {
                rowdblclick : 'edit'
            }
        })]
    }

})