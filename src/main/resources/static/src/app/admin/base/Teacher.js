Ext.define('Admin.base.Teacher', {
    extend: 'Admin.base.Object',

    loadUrl: 'app/admin/teacher/all',
    saveUrl: 'app/admin/teacher/save',
    deleteUrl: 'app/admin/teacher/delete',

    fields: [{
        name: 'oid',
        title: 'ID',
        type: 'auto'
    },{
        name: 'fullName',
        title: 'ФИО',
        allowBlank: false,
    }],
}, ()=> {
    Admin.base.Object.put(new Admin.base.Teacher())
})