package my.great.pkg

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar

class MyGreatPlugin implements Plugin<Project> {

	@Override
	public void apply(Project prj) {
		prj.tasks.create("sourcesJar", Jar) {
				dependsOn: project.tasks.getByName('classes')
				classifier = 'sources'
				from prj.sourceSets.main.allSource
		}
	}

}
