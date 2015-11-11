import org.apache.log4j.DailyRollingFileAppender
// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    appender new DailyRollingFileAppender(name: 'dailyAppender',
                                        datePattern: "'.'yyyy-MM-dd",  // See the API for all patterns.
                                        fileName: "logs/app.log",
                                        layout: pattern(conversionPattern:'%d [%t] %-5p %c{2} %x - %m%n')       
    )

    root {
      info 'dailyAppender'
    } 

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

grails.plugin.springsecurity.logout.postOnly = false


// Apache LDAP Config
grails.plugin.springsecurity.providerNames = ['ldapAuthProvider','anonymousAuthenticationProvider','rememberMeAuthenticationProvider']
grails.plugin.springsecurity.ldap.context.managerDn = 'uid=admin,ou=system'
grails.plugin.springsecurity.ldap.context.managerPassword = 'secret'
grails.plugin.springsecurity.ldap.context.server = 'ldap://localhost:10389'
grails.plugin.springsecurity.ldap.authorities.groupSearchBase = 'ou=groups,dc=example,dc=com'
grails.plugin.springsecurity.ldap.search.base = 'dc=example,dc=com'

grails.plugin.springsecurity.ldap.authorities.retrieveGroupRoles = true
grails.plugin.springsecurity.ldap.authorities.retrieveDatabaseRoles = false
grails.plugin.springsecurity.ldap.authorities.groupSearchFilter = 'member={0}'

grails.plugin.springsecurity.ldap.search.attributesToReturn = ['mail', 'cn', 'sn', 'givenName', 'jpegPhoto' , 'telephoneNumber']
grails.plugin.springsecurity.ldap.authenticator.attributesToReturn = ['mail', 'cn', 'sn', 'givenName', 'jpegPhoto' , 'telephoneNumber']


grails.plugin.springsecurity.securityConfigType = "Requestmap"
grails.plugin.springsecurity.requestMap.className = 'org.example.Requestmap'

//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//	'/':                ['permitAll'],
//	'/index':           ['permitAll'],
//	'/index.gsp':       ['permitAll'],
//	'/assets/**':       ['permitAll'],
//	'/**/js/**':        ['permitAll'],
//	'/**/css/**':       ['permitAll'],
//	'/**/images/**':    ['permitAll'],
//	'/**/favicon.ico':  ['permitAll']
//]

/*
grails.plugin.springsecurity.ldap.context.managerDn = 'csm23admbpm'
grails.plugin.springsecurity.ldap.context.managerPassword = 'csm..2013'
grails.plugin.springsecurity.ldap.context.server = 'ldap://wndkmadprd01.ironmountain.cl:389'
grails.plugin.springsecurity.ldap.authorities.ignorePartialResultException = true


grails.plugin.springsecurity.ldap.search.searchSubtree = true
grails.plugin.springsecurity.ldap.auth.hideUserNotFoundExceptions = false
grails.plugin.springsecurity.ldap.search.attributesToReturn = ['mail', 'displayName'] // extra attributes you want returned
grails.plugin.springsecurity.providerNames = ['ldapAuthProvider'] //, 'anonymousAuthenticationProvider'
 
// role-specific LDAP config
grails.plugin.springsecurity.ldap.useRememberMe = false
grails.plugin.springsecurity.ldap.authorities.retrieveGroupRoles = false
grails.plugin.springsecurity.ldap.authorities.retrieveDatabaseRoles = true
grails.plugin.springsecurity.ldap.authorities.groupSearchBase = 'ou=CSP16,dc=cstkmimcl,dc=cl'

grails.plugin.springsecurity.ldap.search.base = 'dc=cstkmimcl,dc=cl'
grails.plugin.springsecurity.ldap.search.filter = '(sAMAccountName={0})'
*/

//grails.plugin.springsecurity.ldap.authorities.groupSearchBase = 'dc=cstkmimcl,dc=cl'
//grails.plugin.springsecurity.ldap.authorities.groupSearchFilter = 'member={0}'
