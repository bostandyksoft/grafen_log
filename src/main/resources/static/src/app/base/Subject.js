Ext.define('Log.base.Subject', {
    extend: 'Log.base.Object',

    loadUrl: 'app/subject/all',
    saveUrl: 'app/subject',
    deleteUrl: 'app/subject',

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
    Log.base.Object.put(new Log.base.Subject())
})