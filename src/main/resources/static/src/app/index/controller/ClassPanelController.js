Ext.define('Index.controller.ClassPanelController', {
    extend : 'Ext.app.ViewController',

    alias : 'controller.class.panel',

    addRecord : function () {
        const grid = this.lookupReference('grid');
        grid.store.add({})
    },

    removeRecord : function(){
        const grid = this.lookupReference('grid');
        const selection = grid.getSelectionModel().getSelection();
        grid.store.remove(selection)
    }
})