Ext.define('Login.view.MainView', {
    extend: 'Ext.form.Panel',

    requires: ['Login.controller.MainView'],

    alias: 'widget.login.main.view',

    controller: 'main.view',

    bodyPadding: 10,

    defaultType: 'textfield',

    height: 250,
    width: '20%',

    title: 'Вход',
    frame: true,

    items: [{
        allowBlank: false,
        fieldLabel: 'Логин',
        name: 'username',
        msgTarget: 'qtip',
        listeners: {
            specialkey: 'fieldKey'
        }
    }, {
        allowBlank: false,
        fieldLabel: 'Пароль',
        name: 'password',
        inputType: 'password',
        msgTarget: 'qtip',
        listeners: {
            specialkey: 'fieldKey'
        }
    }, {
        xtype: 'checkbox',
        fieldLabel: 'Запомнить меня',
        name: 'remember-me',
        value : true
    }],

    buttons: [
        {text: 'Регистрация', handler: 'doRegister'},
        {text: 'Вход', id: 'login', handler: 'doLogin', formBind: true}
    ],

    defaults: {
        anchor: '100%',
        labelWidth: 120
    }

})