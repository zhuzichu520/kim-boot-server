import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

object App {

    private enum class ConfigKey(val key: String) {
        APP_NAME("APP_VERSION")
    }

    private lateinit var project: Project
    private lateinit var rootDir: String
    fun init(project: Project) {
        this.project = project
        this.rootDir = project.rootDir.absolutePath.plus(File.separator)
        Log.init(project)
    }

    private val resourcesDir by lazy {
        rootDir.plus("buildSrc")
            .plus(File.separator)
            .plus("src")
            .plus(File.separator)
            .plus("main")
            .plus(File.separator)
            .plus("resources")
            .plus(File.separator)
    }

    private val configProperties by lazy {
        loadProperties(
            resourcesDir.plus("build-config.properties")
        )
    }

    fun version(): String {
        return configProperties.getProperty(
            ConfigKey.APP_NAME.key
        )
    }

    @Throws(Exception::class)
    private fun loadProperties(path: String): Properties {
        return Properties().apply {
            load(InputStreamReader(FileInputStream(path)))
        }
    }


}