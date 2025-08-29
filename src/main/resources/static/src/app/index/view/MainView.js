Ext.define('Index.view.MainView', {
    extend: 'Ext.tab.Panel',
    requires: [
        'Index.view.LessonsGrid',
        'Index.view.CRUDGrid',
        'Index.view.UsersGrid',
        'Log.base.Object',
        'Log.base.SchoolClass',
        'Log.base.Student',
        'Log.base.Teacher',
        'Log.base.Subject',
    ],

    layout: 'fit',

    initComponent: function () {
        const me = this;

        this.callParent(arguments);

        Log.sendRequest('/app/index/currentUser', {}, function (context) {
            me.buildLayout(context);
        }, {
            method: 'GET'
        })
    },

    buildLayout: function (context) {
        const me = this;
        let tasks = false;
        if (!!context.hasStudents) {
            me.add({
                xtype: 'container',
                title: 'Расписание',
                html: 'Расписание'
            });
            tasks = true;
        }
        if (!!context.isTeacher) {
            me.add(new Index.view.LessonsGrid({
                title: 'Уроки'
            }));
            tasks = true;
        }
        if (tasks === true) {
            me.add({
                xtype: 'container',
                title: 'Задания'
            })
        }
        if (context.canEditClasses) {
            me.add({
                xtype: 'crud',
                title: 'Классы',
                object: Log.base.Object.get('Log.base.SchoolClass')
            })
        }
        if (context.canEditTeachers) {
            me.add({
                xtype: 'crud',
                title: 'Учителя',
                object: Log.base.Object.get('Log.base.Teacher')
            })
        }
        if (context.canEditSubjects) {
            me.add({
                xtype: 'crud',
                title: 'Предметы',
                object: Log.base.Object.get('Log.base.Subject')
            })
        }
        if (context.canEditUsers) {
            me.add({
                xtype: 'users',
                title: 'Пользователи'
            })
        }
    }

})