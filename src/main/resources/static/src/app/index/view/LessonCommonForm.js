Ext.define('Index.view.LessonCommonForm', {
    extend: 'Ext.form.Panel',

    requires: [
        'Log.base.Subject',
        'Log.base.SchoolClass',
        'Log.form.ObjectLookup'
    ],

    config: {
        lesson: null
    },

    viewModel: true,

    bodyPadding: 5,

    defaults: {
        anchor: '100%',
        labelWidth: 200
    },

    initComponent : function() {
        this.items = [{
            allowBlank: false,
            xtype: 'datefield',
            format: 'd F Y',
            fieldLabel: 'Дата',
            bind: '{lesson.date}'
        }, {
            allowBlank: false,
            xtype: 'objectlookup',
            fieldLabel: 'Класс',
            bind: '{lesson.schoolClass}',
            object: Log.base.Object.get('Log.base.SchoolClass'),
            displayField: 'name'
        }, {
            allowBlank: false,
            xtype: 'objectlookup',
            fieldLabel: 'Предмет',
            object: Log.base.Object.get('Log.base.Subject'),
            displayField: 'title',
            disabled: true,
            bind: {
                disabled: '{!lesson.schoolClass}',
                value: '{lesson.subject}'
            },
        }, {
            allowBlank: false,
            xtype: 'textfield',
            fieldLabel: 'Тема',
            bind: '{lesson.topic}'
        }, {
            xtype: 'tagfield',
            fieldLabel: 'Формируемые навыки',
            typeAhead: true,
            disabled: true,
            forceSelection: false,
            bind: {
                disabled: '{!lesson.subject}',
                value: '{lesson.skills}'
            }
        }]
        this.callParent(arguments);
    },

    updateLesson : function (lesson) {
        this.getViewModel().set('lesson', lesson)
    }
})