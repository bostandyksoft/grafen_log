Ext.define('Admin.base.Subject', {
    extend: 'Log.base.Object',

    loadUrl: 'app/admin/subject/all',
    saveUrl: 'app/admin/subject',
    deleteUrl: 'app/admin/subject',

    fields: [{
        name: 'oid',
        title: 'ID',
        type: 'auto'
    },{
        name: 'title',
        title: 'Название',
        allowBlank: false
    },{
        name: 'comment',
        title: 'Комментарий',
        allowBlank: false
    }],
}, ()=> {
    Log.base.Object.put(new Admin.base.Subject())
})