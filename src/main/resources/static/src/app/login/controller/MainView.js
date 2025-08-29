Ext.define('Login.controller.MainView', {
    extend: 'Ext.app.ViewController',

    requires: ['Login.data.LoginManager'],

    alias: 'controller.main.view',

    doRegister: function () {
        const view = this.getView();
        const data = view.getValues();

        const registerForm = new Ext.form.Panel({
            title: 'Регистрация',
            floating: true,
            bodyPadding: 10,
            autoHeight: true,
            width: '20%',
            defaults: {
                anchor: '100%',
                labelWidth: 120
            },
            defaultType: 'textfield',
            items: [{
                allowBlank: false,
                fieldLabel: 'Логин',
                name: 'username',
                value: data.username,
                msgTarget: 'qtip'
            }, {
                allowBlank: false,
                fieldLabel: 'Пароль',
                name: 'pass',
                inputType: 'password',
                msgTarget: 'qtip'
            }, {
                allowBlank: false,
                fieldLabel: 'Повторите пароль',
                name: 'rePass',
                inputType: 'password',
                msgTarget: 'qtip',
                validator: function (val) {
                    const origin = registerForm.getValues()['pass'];
                    if (val !== origin) {
                        return 'Пароли не совпадают'
                    }
                    return true
                }
            }, {
                allowBlank: false,
                xtype: 'textarea',
                fieldLabel: 'Информация',
                name: 'info',
            }],
            buttons: [{
                text: 'Зарегистрироваться',
                formBind: true,
                handler: function () {
                    const registerData = registerForm.getValues();
                    Login.data.LoginManager.doRegister(registerData, () => {
                        registerForm.hide();
                        registerForm.close();
                    });
                }
            }, {
                text: 'Отмена',
                handler: function () {
                    registerForm.hide();
                    registerForm.close();
                }
            }]
        });
        registerForm.show();
    },

    doLogin: function () {
        const view = this.getView();
        if (view.isValid()) {
            Ext.getBody().mask('Выполняется вход...');
            view.getForm().submit({
                standardSubmit: true,
                url: '/perform_login',
                method: 'POST',
                headers: {
                    'X-CSRF-TOKEN': Ext.getCSRF()
                },
                success: function(form, action) {
                    Ext.getBody().unmask();
                    // Spring Security вернет редирект, обрабатываем его
                    var response = Ext.decode(action.response.responseText);
                    if (response.redirectUrl) {
                        window.location.href = response.redirectUrl;
                    } else {
                        window.location.href = '/home';
                    }
                },
                failure: function(form, action) {
                    Ext.getBody().unmask();
                    switch (action.failureType) {
                        case Ext.form.action.Action.CONNECT_FAILURE:
                            Ext.Msg.alert('Error', 'Server connection failed');
                            break;
                        case Ext.form.action.Action.SERVER_INVALID:
                            Ext.Msg.alert('Error', 'Invalid credentials');
                            break;
                        default:
                            Ext.Msg.alert('Error', 'Authentication failed');
                    }
                }
            });
        }
    }

})