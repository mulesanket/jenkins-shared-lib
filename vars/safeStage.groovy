def call(String name, Closure body) {
    try {
        log.info("${name} started")
        body()
        log.success("${name} completed")
    } catch (err) {
        log.error("${name} failed: ${err.getMessage()}")
        currentBuild.result = 'FAILURE'
        env.FAILED_STAGE = name
        env.FAILURE_REASON = err.getMessage()
        error("Aborting: ${name} - ${err.getMessage()}") 
    }
}
