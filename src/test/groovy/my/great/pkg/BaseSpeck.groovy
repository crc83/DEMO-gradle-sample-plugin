package my.great.pkg

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

public class BaseSpeck extends Specification{

	@Rule
	final TemporaryFolder testProjectDir = new TemporaryFolder()
	File buildFile
	File settingsFile
	List<File> pluginClasspath

	def setup() {
		settingsFile = testProjectDir .newFile('settings.gradle')

		buildFile = testProjectDir .newFile('build.gradle')

		pluginClasspath = preparePluginClassPath()
	}

	private List<String> preparePluginClassPath() {
		def pluginClasspathResource = getClass().classLoader.findResource("plugin-classpath.txt" )
		if (pluginClasspathResource == null) {
			throw new IllegalStateException("Did not find plugin classpath resource, run `testClasses` build task.")
		}

		pluginClasspathResource. readLines().collect { new File(it ) }
	}
}

