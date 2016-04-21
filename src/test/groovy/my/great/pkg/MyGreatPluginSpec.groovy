package my.great.pkg

import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*

class MyGreatPluginSpec extends BaseSpeck {

	def setup() {
		buildFile << """
                    plugins {
                          id 'java'
                          id 'my-super-plugin'
                    }

                    version = '0.1.0'
                    group = 'foo.bar'


              """ .stripIndent ()

		settingsFile << """rootProject.name = 'foo-bar'""" .stripIndent ()
	}

	private void createHelloWorld() {
		def src = new File(testProjectDir .folder , 'src/main/java/example')
		src.mkdirs()

		new File(src, 'HelloWorld.java').text = '''\
              package example;

              /**
			   * HelloWorld class for test
			   *
			   * @copyright.placeholder@
			   */
              public class HelloWorld {
              }
              ''' .stripIndent ()

		def resources = new File(testProjectDir. folder, 'src/main/resources' )
		resources.mkdirs()

		new File(resources, 'foobar.properties').text = '''\
              # some comments
              * @copyright.placeholder@
              useful text
              ''' .stripIndent ()
	}

	def 'sources jar is created'() {
		given:
			createHelloWorld();

		when:
			def result
			result = GradleRunner.create()
								.withProjectDir( testProjectDir.root)
								.withArguments( 'sourcesJar')
								.withPluginClasspath( pluginClasspath)
								.forwardOutput()
								.build() //

		 then:
		 	result.task( ":sourcesJar").outcome == SUCCESS //import static org.gradle.testkit.runner.TaskOutcome.*
			File resultJar= new File(testProjectDir .root, 'build/libs/foo-bar-0.1.0-sources.jar' )
			resultJar.exists()
	}

}
