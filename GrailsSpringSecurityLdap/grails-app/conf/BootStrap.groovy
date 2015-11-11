import org.example.*

class BootStrap {

    def init = { servletContext ->
		
		//def adminRole = new Role('ROLE_ADMIN').save()
		//def userRole = new Role('ROLE_USER').save()
  
		/*
		def testUser = new User('me', 'password').save()
  
		UserRole.create testUser, adminRole, true
		*/
		
		for (String url in [
			'/',
			'/index',
			'/index.gsp',
			'/assets/**',
			'/**/js/**',
			'/**/css/**',
			'/**/images/**',
			'/**/favicon.ico',
			'/login',
			'/login.*',
			'/login/*',
			'/dbconsole/**',
			 ]) {
			 new Requestmap(url: url, configAttribute: 'permitAll').save()
		}
		new Requestmap(url: '/desktop/**', configAttribute: 'ROLE_USER').save()
    }
    def destroy = {
    }
}
