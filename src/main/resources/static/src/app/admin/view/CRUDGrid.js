Ext.define('Admin.view.CRUDGrid', {
    extend: 'Ext.grid.Panel',

    alias: 'widget.crud',

    config: {
        object: null
    },

    initComponent: function () {
        const me = this;
        me.createLayout();

        me.plugins = [
            new Ext.grid.plugin.CellEditing({
                cellediting: {
                    clicksToEdit: 1
                },
                listeners: {
                    edit: function (editor, context) {
                        const record = context.record;
                        let allRequired = true;
                        for (let i = 0; i < me.object.fields.length; i++) {
                            const field = me.object.fields[i];
                            if (!record.get(field.name)) {
                                if (field.allowBlank === false) {
                                    allRequired = false;
                                    break;
                                }
                            }
                        }
                        if (allRequired) {
                            Ext.Ajax.request({
                                url: me.object.saveUrl,
                                jsonData: record.getData(),
                                headers: {
                                    'X-CSRF-TOKEN': Ext.getCSRF()
                                },
                                success: function (response, opts) {
                                    record.commit();
                                    const data = JSON.parse(response.responseText);
                                    if (!record.get('oid')) {
                                        record.set('oid', data.oid);
                                        me.store.add({});
                                    }
                                },
                                failure: function (response, opts) {
                                    Ext.toast({
                                        html: 'Ошибка',
                                        title: response.status
                                    });
                                }
                            });
                        }
                    }
                }
            })
        ]

        me.store = me.object.all({
            listeners: {
                load: function (sender) {
                    sender.add({})
                }
            }
        })

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