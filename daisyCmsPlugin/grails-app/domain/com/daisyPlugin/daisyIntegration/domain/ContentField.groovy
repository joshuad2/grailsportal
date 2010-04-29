package com.daisyPlugin.daisyIntegration.domain


class ContentField {
	static mapping ={		
		fieldName         column:'field_name'
		fieldValue        column:'field_value'
		valueType         column:'value_type_id'
		displayOrder      column:'display_order'
	}
	
    Content content
    String fieldName
    String value
    String valueType
    int displayOrder
    static constraints = {
		fieldName(size:1..20, blank:false,nullable:false)
		value(size:1..40, blank:false,nullable:false)
		valueType(size:1..20, blank:false,nullable:false)
		displayOrder(nullable:false)
		content(nullable:false)
    }
}
