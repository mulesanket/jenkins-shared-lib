// vars/log.groovy

def info(String message) {
    echo "\033[1;34m[INFO] ${message}\033[0m"
}

def success(String message) {
    echo "\033[1;32m[SUCCESS] ${message}\033[0m"
}

def warn(String message) {
    echo "\033[1;33m[WARNING] ${message}\033[0m"
}

def error(String message) {
    echo "\033[1;31m[ERROR] ${message}\033[0m"
}

def json(Map data) {
    def logEntry = [
        timestamp: new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
        job: env.JOB_NAME,
        build: env.BUILD_NUMBER,
        message: data.message,
        level: data.level ?: "INFO"
    ]
    echo groovy.json.JsonOutput.toJson(logEntry)
}

