var findComponentIds = function(text, xtype) {
	var items = '';
	Ext.ComponentMgr.all.find(function(c){
	    if(xtype){
	        if(text){
	            if((c.isVisible()) && (c.getXType() == xtype) && (c.text == text
	                                    || c.title == text
	                                    || c.fieldLabel == text
	                                    || c.boxLabel == text
	                                    || c.iconCls == text))
	            items += c.id + ',';
	        }else{
	            if((c.isVisible()) && (c.getXType() == xtype))
	                items += c.id + ',';
	        }

	    }else{
            if(text){
               if((c.isVisible()) && (c.text == text
	                                    || c.title == text
	                                    || c.fieldLabel == text
	                                    || c.boxLabel == text
	                                    || c.iconCls == text))
	            items += c.id + ',';
            }
	    }

	});
	return items;
}