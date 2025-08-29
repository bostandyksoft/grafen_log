Ext.define('Admin.base.SchoolClass', {
    extend: 'Log.base.Object',

    loadUrl: 'app/admin/class/all',
    saveUrl: 'app/admin/class/save',
    deleteUrl: 'app/admin/class/delete',

    fields: [{
        name: 'oid',
        title: 'ID',
        type: 'auto'
    },{
        name: 'name',
        title: 'Название'
    }],

}, ()=> {
    Log.base.Object.put(new Admin.base.SchoolClass())
})