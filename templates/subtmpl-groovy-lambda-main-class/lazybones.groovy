println "Subtemplate Lazybones script being executed."

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

import static org.apache.commons.io.FilenameUtils.concat

def props = [:]
props.packageName = parentParams.packageName
props.mainClassName = parentParams.mainClassName

processTemplates "GroovyLambdaMainClassoovy" props

def pkgPath = params.pkg.replace('.' as char, '/' as char)
def destFile = new File(projectDir, concat(concat(concat("src/main/groovy", pkgPath), props.mainClassName), ".groovy"))
destFile.parentFile.mkdirs()
FileUtils.moveFile(new File(templateDir, "${props.mainClassName}.groovy"), destFile)
println "Created new Main class ${FilenameUtils.normalize(destFile.path)}"
