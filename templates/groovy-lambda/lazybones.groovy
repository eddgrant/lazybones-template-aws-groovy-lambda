import uk.co.cacoethes.util.NameType

final String defaultPackageName = "uk.co.bbc.pcs"

def props = [:]
props.componentName = ask("What is the name of the Lambda component e.g. pcs-pr-filter ?")
props.baseComponentName = props.componentName.replace("pcs-", "")
props.componentNameCamelCase = transformText(props.componentName, from: NameType.HYPHENATED, to: NameType.CAMEL_CASE)
props.baseComponentNameCamelCase = transformText(props.baseComponentName, from: NameType.HYPHENATED, to: NameType.CAMEL_CASE)
props.packageName = ask("What is the component's base package? e.g. uk.co.bbc.pcs.amp.prfilter", defaultPackageName.concat(".").concat((props.baseComponentName as String).replaceAll("-", "")))
props.mainClassName = ask("What is the component's Main class? e.g. PrFilterRequestHandler", "${props.baseComponentNameCamelCase}RequestHandler")

processTemplates "build.gradle", props
processTemplates "stacks/build.gradle", props
processTemplates "stacks/src/component/template.py", props
processTemplates "stacks/src/resources/template.py", props
