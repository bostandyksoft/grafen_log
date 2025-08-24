Ext.define('Admin.base.Student', {
    extend: 'Admin.base.Object',

    uses: ['Admin.base.Class'],

    loadUrl: 'app/admin/student/all',
    saveUrl: 'app/admin/student/save',
    deleteUrl: 'app/admin/student/delete',

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
    Admin.base.Object.put(new Admin.base.Student())
})