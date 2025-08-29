Ext.define('Log.base.Student', {
    extend: 'Log.base.Object',

    uses: ['Log.base.SchoolClass'],

    loadUrl: 'app/student/all',
    saveUrl: 'app/student',
    deleteUrl: 'app/student',

    fields: [{
        name: 'oid',
        title: 'ID',
        type: 'auto'
    },{
        name: 'fullName',
        title: 'ФИО',
        allowBlank: false
    }, {
        name: 'classId',
        title: 'Класс',
        type: {
            object: 'Log.base.SchoolClass',
            displayField: 'name'
        }
    }],
}, ()=> {
    Log.base.Object.put(new Log.base.Student())
})