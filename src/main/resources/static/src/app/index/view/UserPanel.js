Ext.define('Index.view.UserPanel', {
    extend: 'Ext.form.Panel',

    requires: [
        'Log.form.ObjectsTag',
        'Log.form.ObjectLookup'
    ],

    config: {
        user: null
    },

    viewModel: true,

    bodyPadding: 5,

    defaults: {
        anchor: '100%',
        labelWidth: 200
    },
    defaultType: 'textfield',
    items: [{
        xtype: 'hidden',
        name: 'oid',
        bind: '{user.oid}'
    }, {
        allowBlank: false,
        fieldLabel: 'Логин',
        name: 'username',
        bind: '{user.username}',
        msgTarget: 'qtip'
    }, {
        allowBlank: false,
        xtype: 'textarea',
        fieldLabel: 'Информация',
        name: 'info',
        bind: '{user.info}',
    }, {
        xtype: 'checkbox',
        fieldLabel: 'Администратор',
        name: 'admin',
        bind: '{user.admin}',
    }, {
        xtype: 'checkbox',
        fieldLabel: 'Активирован',
        name: 'active',
        bind: '{user.active}',
    }, {
        xtype: 'objectlookup',
        fieldLabel: 'Асоциированый преподаватель',
        name: 'teacher',
        bind: '{user.teacher}',
        displayField: 'fullName',
        object: Log.base.Object.get('Log.base.Teacher')
    }, {
        xtype: 'objects.tag',
        fieldLabel: 'Ученики',
        name: 'students',
        bind: '{user.students}',
        displayField: 'fullName',
        object: Log.base.Object.get('Log.base.Student')
    }],

    updateUser: function (user) {
        this.getViewModel().set('user', user);
    }
})