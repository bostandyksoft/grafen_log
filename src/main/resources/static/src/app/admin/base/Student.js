Ext.define('Admin.base.Student', {
    extend: 'Log.base.Object',

    uses: ['Admin.base.SchoolClass'],

    loadUrl: 'app/admin/student/all',
    saveUrl: 'app/admin/student',
    deleteUrl: 'app/admin/student',

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
            object: 'Admin.base.SchoolClass',
            displayField: 'name'
        }
    }],
}, ()=> {
    Log.base.Object.put(new Admin.base.Student())
})