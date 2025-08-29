Ext.define('Log.base.User', {
    extend: 'Log.base.Object',

    loadUrl: 'app/user/all',
    saveUrl: 'app/user',
    deleteUrl: 'app/user',

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
    Log.base.Object.put(new Log.base.User())
})