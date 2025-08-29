Ext.define('Log.form.ObjectLookup', {
    extend: 'Ext.form.field.ComboBox',

    alias: 'widget.objectlookup',

    queryMode: 'local',
    forceSelection: true,
    typeAhead: true,

    config: {
        object: null,
        displayField: null
    },

    initComponent: function () {
        const me = this;

        me.valueField = 'oid';
        me.store = me.object.all({
            listeners: {
                load: function (store) {
                    if (store.count() === 1) {
                        me.setValue(store.get(me.valueField))
                    }
                }
            }
        });


        me.callParent(arguments);
    },
})