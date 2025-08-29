Ext.define('Index.controller.LessonsGridController', {
    extend: 'Ext.app.ViewController',

    requires: ['Index.view.LessonCommonForm'],

    alias: 'controller.lessons.grid',

    newLesson: function () {
        const me = this;
        const grid = me.grid;
        const lesson = {
            date: new Date()
        };

        Log.openForm(
            'Index.view.LessonCommonForm', {
                title: 'Новый урок',
                lesson: lesson,
                width: '30%'
            },
            {
                text: 'Начать',
                handler: function (panel, close) {
                    Log.base.Object.get('Log.base.Lesson').save(panel.getLesson(), function (saved) {
                        grid.store.add(saved);
                        grid.store.commit();
                        close();
                    })
                }
            }
        )
    },

    editLesson: function () {
        const me = this;
    },

    removeLesson: function () {
        alert(3);
    }

})