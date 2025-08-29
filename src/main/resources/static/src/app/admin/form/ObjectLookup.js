Ext.define('Admin.form.ObjectLookup', {
    extend: 'Ext.form.field.ComboBox',

    alias: 'widget.objectlookup',

    config: {
        object: null,
        displayField: null
    },

    initComponent: function () {
        const me = this;

        me.valueField = 'oid';
        me.store = me.object.all()

        me.callParent(arguments);
    },
})