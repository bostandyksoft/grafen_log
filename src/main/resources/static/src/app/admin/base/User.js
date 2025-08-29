Ext.define('Admin.base.User', {
    extend: 'Log.base.Object',

    loadUrl: 'app/admin/user/all',
    saveUrl: 'app/admin/user',
    deleteUrl: 'app/admin/user',

    fields: [{
        name: 'oid',
        title: 'ID',
        type: 'auto'
    },{
        name: 'username'
    },{
        name: 'info'
    },{
        name: 'active'
    },{
        name: 'admin'
    }],
}, ()=> {
    Log.base.Object.put(new Admin.base.User())
})