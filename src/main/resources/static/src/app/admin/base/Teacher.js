Ext.define('Admin.base.Teacher', {
    extend: 'Log.base.Object',

    loadUrl: 'app/admin/teacher/all',
    saveUrl: 'app/admin/teacher',
    deleteUrl: 'app/admin/teacher',

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
    Log.base.Object.put(new Admin.base.Teacher())
})