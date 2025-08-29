Ext.define('Admin.controller.CRUDGridController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.crud.grid',

    edit: function (editor, context) {
        const view = this.getView();
        const grid = view.grid;
        const record = context.record;
        let allRequired = true;
        for (let i = 0; i < view.object.fields.length; i++) {
            const field = view.object.fields[i];
            if (!record.get(field.name)) {
                if (field.allowBlank === false) {
                    allRequired = false;
                    break;
                }
            }
        }
        if (allRequired) {
            view.object.save(record.getData(), function(saved) {
                record.set(saved);
                record.commit();
            })
        }
    },

    addRecord : function() {
        this.getView().grid.store.add({});
    },

    removeRecord: function() {
        const view = this.getView();
        const grid = view.grid;
        view.object.deleteRecords(
            grid.getStore(),
            grid.getSelectionModel().getSelection()
        )
    }
});