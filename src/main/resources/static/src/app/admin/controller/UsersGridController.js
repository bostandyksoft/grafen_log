Ext.define('Admin.controller.UsersGridController', {
    extend: 'Ext.app.ViewController',

    requires: ['Admin.view.UserPanel'],

    alias: 'controller.users.grid',

    edit: function () {
        const me = this;
        const grid = me.getView().grid;
        const record = grid.getSelectionModel().getSelection()[0];
        Log.sendRequest('/app/admin/user', {}, function (user) {
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
        Log.base.Object.get('Admin.base.User').deleteRecords(store, grid.getSelectionModel().getSelection());
    },

    editUser: function (record, user) {
        const panel = new Admin.view.UserPanel({
            title: 'Изменение пользователя',
            floating: true,
            frame: true,
            modal: true,
            width: '80%',
            autoHeight: true,
            user: user,
            buttons: [{
                text: 'Сохранить',
                formBind: true,
                handler: function() {
                    Log.base.Object.get('Admin.base.User').save(panel.getUser(), function(saved) {
                        record.set(saved);
                        record.commit();
                        panel.close();
                        panel.destroy();
                    })
                }
            }, {
                text: 'Отменить',
                handler : function() {
                    panel.close();
                    panel.destroy();
                }
            }]
        })
        panel.show();
    }
})