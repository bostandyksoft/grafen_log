Ext.define('Admin.view.CRUDGrid', {
    extend: 'Ext.container.Container',

    requires: ['Admin.controller.CRUDGridController'],

    controller: 'crud.grid',

    alias: 'widget.crud',

    layout: 'fit',

    viewModel: true,

    config: {
        object: null
    },

    initComponent: function () {
        const me = this;
        me.createLayout();
        me.callParent(arguments);
    },

    createLayout: function () {
        const me = this;
        const obj = me.object;
        let columns = [];
        for (let i = 0; i < obj.fields.length; i++) {
            const field = obj.fields[i];
            if (field.name === 'oid') {
                continue;
            }
            let config = {
                text: field.title,
                dataIndex: field.name,
                editor: me.createEditor(field)
            };
            if (Ext.isObject(field.type)) {
                const store = Log.base.Object.get(field.type.object).all();
                Ext.apply(config, {
                    renderer: me.storeRenderer(store, field.type.displayField),
                    editor: {
                        xtype: 'combo',
                        typeAhead: true,
                        triggerAction: 'all',
                        selectOnFocus: true,
                        valueField: 'oid',
                        displayField: field.type.displayField,
                        store: store
                    }
                })
            }
            Ext.apply(config, {
                flex: field.flex || 1
            })
            columns.push(config)
        }
        me.columns = columns;

        me.items = [
            me.grid = new Ext.grid.Panel({
                reference: 'grid',
                tbar: [{
                    text: 'Добавить запись',
                    iconCls: 'fa fa-plus',
                    handler: 'addRecord'
                }, {
                    text: 'Удалить',
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
                plugins: [
                    new Ext.grid.plugin.CellEditing({
                        cellediting: {
                            clicksToEdit: 1
                        },
                        listeners: {
                            edit: 'edit'
                        }
                    })
                ],
                columns: columns,
                store: me.object.all()
            })
        ]

    },

    storeRenderer: function (store, displayField) {
        const me = this;
        return function (value) {
            if (!value) {
                return ''
            }
            let index = store.findExact('oid', value)
            return index < 0 ? '' : store.getAt(index).get(displayField)
        }
    },

    createEditor: function (field) {
        let config = {
            allowBlank: true
        }
        if (field.type === 'auto') {
            Ext.apply(config, {
                readOnly: true
            })
        }
        return config;
    }

})