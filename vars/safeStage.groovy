//Error Handling at every stage

def call(String name, Closure body) {
    try {
        log.info("${name} started")
        body()
        log.success("${name} completed")
    } catch (err) {
        log.error("${name} failed: ${err.getMessage()}")
        currentBuild.result = 'FAILURE'
        throw err
    }
}
