Ext.define('Log.base.Lesson', {
    extend: 'Log.base.Object',

    loadUrl: 'app/lesson/all',
    saveUrl: 'app/lesson',
    deleteUrl: 'app/lesson',

}, ()=> {
    Log.base.Object.put(new Log.base.Lesson())
})