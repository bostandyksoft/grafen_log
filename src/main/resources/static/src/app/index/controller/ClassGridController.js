Ext.define('Index.controller.ClassGridController', {
    extend: 'Ext.app.ViewController',

    requires: ['Index.view.ClassPanel'],

    alias: 'controller.classes.grid',

    addRecord: function () {
        const me = this;
        const grid = me.getView().grid;
        me.editRecord('Новый класс', {}, function(saved) {
            const record = grid.store.add(saved);
            record.commit();
        });
    },

    edit: function () {
        const me = this;
        const grid = me.getView().grid;
        const record = grid.getSelectionModel().getSelection()[0];
        Log.sendRequest('/app/class', {}, function (object) {
            me.editRecord('Редактирование класса', object, function(saved) {
                record.set(saved);
                record.commit();
            });
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
        Log.base.Object.get('Log.base.SchoolClass').deleteRecords(store, grid.getSelectionModel().getSelection());
    },

    editRecord: function (title, object, cb) {
        const me = this;
        Log.openForm('Index.view.ClassPanel', {
            title: title,
            schoolClass: object,
            height: '90%'
        }, {
            text: 'Сохранить',
            handler: function(panel, close) {
                Log.base.Object.get('Log.base.SchoolClass').save(panel.gatherSchoolClass(), function(saved) {
                    cb.apply(me, saved);
                    close();
                })
            }
        })
    }
})