Ext.define('Index.controller.UsersGridController', {
    extend: 'Ext.app.ViewController',

    requires: ['Index.view.UserPanel'],

    alias: 'controller.users.grid',

    edit: function () {
        const me = this;
        const grid = me.getView().grid;
        const record = grid.getSelectionModel().getSelection()[0];
        Log.sendRequest('/app/user', {}, function (user) {
            me.editUser(record, user);
        }, {
            method: 'GET',
            params: {
                id: record.get('oid')
            }
        })
    },

    remove: function () {
        const grid = this.getView().grid;
        const store = grid.getStore();
        Log.base.Object.get('Log.base.User').deleteRecords(store, grid.getSelectionModel().getSelection());
    },

    editUser: function (record, user) {
        Log.openForm('Index.view.UserPanel', {
            title: 'Изменение пользователя',
            user: user
        }, {
            text: 'Сохранить',
            handler: function(panel, close) {
                Log.base.Object.get('Log.base.User').save(panel.getUser(), function(saved) {
                    record.set(saved);
                    record.commit();
                    close();
                })
            }
        })
    }
})