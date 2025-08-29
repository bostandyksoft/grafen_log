Ext.define('Log.base.Object', {
    extend: 'Ext.Base',

    statics: {

        map: {},

        get: function(value) {
            return Log.base.Object.map[value];
        },

        put: function(instance) {
            Log.base.Object.map[instance.$className] = instance;
        }

    },

    loadUrl: null,
    saveUrl: null,
    deleteUrl: null,

    fields: [],

    editPanel: null,

    getFields : function () {
        const me = this;
        return me.fields.map(field => {
            return field.name;
        });
    },

    all : function(config) {
        const me = this;
        return new Ext.data.Store(Ext.apply({
            model: new Ext.data.Model({
                fields : me.getFields()
            }),
            proxy: {
                type: 'ajax',
                url: me.loadUrl,
                reader: {
                    type: 'json'
                }
            },
            autoLoad: true
        }, config))
    }

})