Ext.define('Log.base.Teacher', {
    extend: 'Log.base.Object',

    loadUrl: 'app/teacher/all',
    saveUrl: 'app/teacher',
    deleteUrl: 'app/teacher',

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
    Log.base.Object.put(new Log.base.Teacher())
})