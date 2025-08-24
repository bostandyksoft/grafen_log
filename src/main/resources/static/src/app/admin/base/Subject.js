Ext.define('Admin.base.Subject', {
    extend: 'Admin.base.Object',

    loadUrl: 'app/admin/subject/all',
    saveUrl: 'app/admin/subject/save',
    deleteUrl: 'app/admin/subject/delete',

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
    Admin.base.Object.put(new Admin.base.Subject())
})