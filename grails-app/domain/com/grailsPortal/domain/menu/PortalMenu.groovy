package com.grailsPortal.domain.menu

class PortalMenu {
	
	String itemLabel
	String itemText
	static hasMany=[subMenus:PortalSubMenu]
    static constraints = {
		itemLabel(size:1..40)
		itemText(size:1..100)
    }
}
