Ext.define('Log.base.SchoolClass', {
    extend: 'Log.base.Object',

    loadUrl: 'app/class/all',
    saveUrl: 'app/class',
    deleteUrl: 'app/class',

    fields: [{
        name: 'oid',
        title: 'ID',
        type: 'auto'
    },{
        name: 'name',
        title: 'Название'
    }],

}, ()=> {
    Log.base.Object.put(new Log.base.SchoolClass())
})