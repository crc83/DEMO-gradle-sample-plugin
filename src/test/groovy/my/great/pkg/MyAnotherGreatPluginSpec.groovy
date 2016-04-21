package my.great.pkg

import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*

class MyAnotherGreatPluginSpec extends BaseSpeck {

	def setup() {
		buildFile << """
                    plugins {
                          id 'java'
                          id 'my-another-super-plugin'
                    }

                    myconfig {
                    	user="ADMIN"
						password="secret"
                    }
              """ .stripIndent ()

		settingsFile << """rootProject.name = 'foo-bar'""" .stripIndent ()
	}


	def 'extension information is printed out'() {
		given:

		when:
			def result
			result = GradleRunner.create()
								.withProjectDir( testProjectDir.root)
								.withArguments( 'printInfo')
								.withPluginClasspath( pluginClasspath)
								.forwardOutput()
								.build() //

		 then:
		 	result.task( ":printInfo").outcome == SUCCES
	}

}
