import org.gradle.api.Project

object Log {

    private const val LOG_FORMAT = "=====flag->%s=====||  %s"
    private const val DEFAULT_FLAG = "KIM-LOG"

    private lateinit var project: Project
    private val logger by lazy { project.logger }

    fun init(project: Project) {
        Log.project = project
    }

    fun d(message: Any?,flag: String= DEFAULT_FLAG) {
        logger.debug(String.format(LOG_FORMAT, flag, message.toString()))
    }

    fun i(message: Any?,flag: String= DEFAULT_FLAG) {
        logger.info(String.format(LOG_FORMAT, flag, message.toString()))
    }

    fun l(message: Any?,flag: String= DEFAULT_FLAG) {
        logger.lifecycle(String.format(LOG_FORMAT, flag, message.toString()))
    }

    fun w(message: Any?,flag: String= DEFAULT_FLAG) {
        logger.warn(String.format(LOG_FORMAT, flag, message.toString()))
    }

    fun q(message: Any?,flag: String= DEFAULT_FLAG) {
        logger.quiet(String.format(LOG_FORMAT, flag, message.toString()))
    }

    fun e(message: Any?,flag: String= DEFAULT_FLAG) {
        logger.error(String.format(LOG_FORMAT, flag, message.toString()))
    }
}