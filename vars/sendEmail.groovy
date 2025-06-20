def call(Map config = [:]) {
    def isSuccess = config.get('isSuccess', false)
    def currentBuild = config.currentBuild
    def env = config.env
    def message = config.get('message', '')

    def status = isSuccess ? "SUCCESS" : "FAILURE"
    def color = isSuccess ? "green" : "red"
    def icon = isSuccess ? "✅" : "❌"

    def stageInfo = !isSuccess ? """
        <b>Failed Stage:</b> ${env.FAILED_STAGE ?: "Unknown"}<br/>
        <b>Reason:</b> ${env.FAILURE_REASON ?: message}<br/>
    """ : ""

    def logExcerpt = isSuccess ? "" : """
        <pre style='font-size:13px;background:#f9f9f9;border:1px solid #ccc;padding:10px;'>
        ${currentBuild.rawBuild.getLog(50).join("<br/>")}
        </pre>
    """

    def body = """
        <h2 style='color:${color};'>Build ${status}</h2>
        <b>Job:</b> ${env.JOB_NAME}<br/>
        <b>Build Number:</b> #${env.BUILD_NUMBER}<br/>
        <b>Duration:</b> ${currentBuild.durationString}<br/>
        <b>URL:</b> <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a><br/>
        ${stageInfo}
        ${logExcerpt}
    """

    mail to: 'mulesanketcyb@gmail.com',
         subject: "${icon} ${status}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
         body: body,
         mimeType: 'text/html',
         attachmentsPattern: '**/*.html'
}
