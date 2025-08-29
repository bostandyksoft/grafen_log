Ext.Loader.setPath('Log', 'src/app')
Ext.tip.QuickTipManager.init()

Ext.getCSRF = function() {
    return Ext.get('csrfToken') ? Ext.get('csrfToken').getValue() : ''
}