Ext.define('Index.view.ClassPanel', {
    extend: 'Ext.panel.Panel',

    requires: [
        'Log.form.ObjectsTag',
        'Log.form.ObjectLookup',
        'Index.controller.ClassPanelController'
    ],

    controller: 'class.panel',

    config: {
        schoolClass: null
    },

    viewModel: {
        stores: {
            students: {
                source: '{schoolClass.students}',
                model: new Ext.data.Model({
                    fields: ['oid', 'fullName']
                }),
                sorters: 'fullName'
            }
        }
    },

    layout: 'border',

    initComponent: function () {
        const me = this;

        me.items = [{
            region: 'north',
            height: 50,
            xtype: 'form',
            bodyPadding: 5,
            items: [{
                xtype: 'textfield',
                allowBlank: false,
                fieldLabel: 'Название',
                bind: '{schoolClass.name}',
                msgTarget: 'qtip'
            }]
        }, {
            region: 'center',
            xtype: 'grid',
            reference: 'grid',
            tbar: [{
                text: 'Добавить ученика',
                iconCls: 'fa fa-plus',
                handler: 'addRecord'
            }, {
                text: 'Удалить ученика',
                iconCls: 'fa fa-remove',
                handler: 'removeRecord',
                disabled: true,
                bind: {
                    disabled: '{!grid.selection}'
                }
            }],
            selModel: {
                type: 'cellmodel'
            },
            plugins: {
                cellediting: {
                    clicksToEdit: 1
                }
            },
            columns: [
                {xtype: 'rownumberer'},
                {
                    text: 'ФИО ученика',
                    dataIndex: 'fullName',
                    flex: 1,
                    editor: 'textfield'
                }],
            bind: {
                store: '{students}'
            }
        }];

        me.callParent(arguments);
    },

    updateSchoolClass: function (clazz) {
        this.getViewModel().set('schoolClass', clazz);
    },

    gatherSchoolClass: function () {
        const me = this;
        let result = me.getViewModel().get('schoolClass');
        const students = [];
        me.getViewModel().getStore('students').each(record => {
            students.push(record.getData());
        })
        return Ext.apply(result, {
            students: students
        });
    }

})