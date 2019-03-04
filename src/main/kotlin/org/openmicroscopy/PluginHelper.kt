package org.openmicroscopy

import com.google.common.base.CaseFormat
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.publish.maven.MavenPom

class PluginHelper {
    companion object {
        fun getRuntimeClasspathConfiguration(project: Project): Configuration? =
                project.configurations.findByName(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME)

        fun MavenPom.licenseGnu2() = licenses {
            license {
                name.set("GNU General Public License, Version 2")
                url.set("https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html")
                distribution.set("repo")
            }
        }

        fun Project.resolveProperty(envVarKey: String, projectPropKey: String): String? {
            val propValue = System.getenv()[envVarKey]
            if (propValue != null) {
                return propValue
            }
            return findProperty(projectPropKey).toString()
        }

        fun Project.camelCaseName(): String {
            return CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, name)
        }
    }
}

