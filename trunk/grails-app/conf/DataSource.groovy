dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	username = "grailsPortal"
	password = "grailsPortal"
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://localhost/grailsPortal"
		}
	}
	test {
		dataSource {
			dbCreate = "create-drop"
			url = "jdbc:mysql://localhost/grailsPortal"
		}
	}
	production {
		dataSource {
			dbCreate = "create-drop"
			url = "jdbc:mysql://localhost/grailsPortal"
		}
	}
}