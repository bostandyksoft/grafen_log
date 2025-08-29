Ext.define('Log.form.ObjectsTag', {
    extend: 'Ext.form.field.Tag',

    alias:'widget.objects.tag',

    layout: 'fit',

    queryMode: 'local',

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