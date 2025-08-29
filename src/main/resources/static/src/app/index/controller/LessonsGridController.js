Ext.define('Index.controller.LessonsGridController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.lessons.grid',

    newLesson: function() {
        alert(1);
    },

    editLesson: function() {
        alert(2);
    },

    removeLesson: function() {
        alert(3);
    }

})