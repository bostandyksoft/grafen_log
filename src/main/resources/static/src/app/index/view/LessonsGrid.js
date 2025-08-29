Ext.define('Index.view.LessonsGrid', {
    extend : 'Ext.grid.Panel',
    requires: [
        'Index.controller.LessonsGridController'
    ],

    controller: 'lessons.grid',

    layout: 'fit',

    initComponent: function() {
        const me =this;
        me.createLayout();
        me.callParent(arguments);
    },

    createLayout : function() {
        const me = this;
        me.tbar = [{
            text: 'Провести новый урок',
            iconCls: 'fa fa-plus',
            handler: 'newLesson'
        }, {
            text: 'Изменить информацию',
            iconCls: 'fa fa-edit',
            handler: 'editLesson'
        }, {
            text: 'Удалить',
            iconCls: 'fa fa-remove',
            handler: 'removeLesson'
        }];

        this.columns = [{
            text: 'Дата',
            dataIndex: 'lesson_date',
            width: '10%'
        }, {
            text: 'Класс',
            dataIndex: 'school_class',
            width: '10%'
        }, {
            text: 'Предмет',
            dataIndex: 'subject',
            width: '10%'
        }, {
            text: 'Тема',
            dataIndex: 'topic',
            flex: 1
        }];

        this.store = new Ext.data.Store({
            data: []
        })
    }

})