package io.vertx.ext.swagger.codegen;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.BooleanUtils;

import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.CodegenType;
import io.swagger.codegen.SupportingFile;
import io.swagger.codegen.languages.JavaClientCodegen;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.util.Json;

public class JavaVertXServerGenerator extends JavaClientCodegen implements CodegenConfig {

    // source folder where to write the files

    protected String resourceFolder = "src/main/resources";
    protected String configFolder = "src/main/conf";
    protected String rootPackage = "com.godaddy.ecomm.payments";
    protected String apiVersion = "1.0.0-SNAPSHOT";


    public JavaVertXServerGenerator() {
        super();

        sourceFolder = "src/main/java";

        // set the output folder here
        outputFolder = "generated-code/javaVertXServer";

        /**
         * Models. You can write model files using the modelTemplateFiles map.
         * if you want to create one template for file, you can do so here. for
         * multiple files for model, just put another entry in the
         * `modelTemplateFiles` with a different extension
         */

        modelTemplateFiles.clear();
        modelTemplateFiles.put("model.mustache", // the template to use
                ".java"); // the extension for each file to write

        /**
         * Api classes. You can write classes for each Api file with the
         * apiTemplateFiles map. as with models, add multiple entries with
         * different extensions for multiple files per class
         */
        apiTemplateFiles.clear();
        apiTemplateFiles.put("api.mustache", // the template to use
                ".java"); // the extension for each file to write
        apiTemplateFiles.put("apiImpl.mustache", // the template to use
                "Impl.java"); // the extension for each file to write
        apiTemplateFiles.put("apiVerticle.mustache", // the template to use
                "Verticle.java"); // the extension for each file to write

        apiTestTemplateFiles.clear();
        apiTestTemplateFiles.put("apiTest.mustache", // the template to use
                "Test.java"); // the extension for each file to write
        apiTestTemplateFiles.put("apiTestModule.mustache", // the template to use
                "TestModule.java"); // the extension for each file to write


        /**
         * Template Location. This is the location which templates will be read
         * from. The generator will use the resource stream to attempt to read
         * the templates.
         */
        embeddedTemplateDir = templateDir = "javaVertXServer";

        /**
         * Api Package. Optional, if needed, this can be used in templates
         */
        apiPackage = rootPackage + ".verticle";
        testPackage

        /**
         * Model Package. Optional, if needed, this can be used in templates
         */
        modelPackage = rootPackage + ".model";

        additionalProperties.put("apiVersion", apiVersion);
        additionalProperties.put("rootPackage", rootPackage);

        groupId = rootPackage;
        artifactId = "payment-api-sandbox";
        artifactVersion = apiVersion;

    }

    /**
     * Configures the type of generator.
     *
     * @return the CodegenType for this generator
     * @see io.swagger.codegen.CodegenType
     */
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    /**
     * Configures a friendly name for the generator. This will be used by the
     * generator to select the library with the -l flag.
     *
     * @return the friendly name for the generator
     */
    public String getName() {
        return "java-vertx";
    }

    /**
     * Returns human-friendly help for the generator. Provide the consumer with
     * help tips, parameters here
     *
     * @return A string value for the help message
     */
    public String getHelp() {
        return "Generates a java-Vert.X Server application.";
    }

    @Override
    public void processOpts() {
        super.processOpts();

        //apiTestTemplateFiles.clear();

        importMapping.remove("ApiModelProperty");
        importMapping.remove("ApiModel");
        importMapping.put("JsonInclude", "com.fasterxml.jackson.annotation.JsonInclude");
        importMapping.put("JsonProperty", "com.fasterxml.jackson.annotation.JsonProperty");

        modelDocTemplateFiles.clear();
        apiDocTemplateFiles.clear();

        supportingFiles.clear();
        supportingFiles.add(new SupportingFile("swagger.mustache", resourceFolder, "swagger.json"));

        supportingFiles.add(new SupportingFile("config-json.mustache", configFolder, "config.json"));

        supportingFiles.add(new SupportingFile("MainApiVerticle.mustache", sourceFolder + File.separator + rootPackage.replace(".", File.separator), "MainApiVerticle.java"));

        writeOptional(outputFolder, new SupportingFile("vertx-default-jul-logging.mustache", resourceFolder, "vertx-default-jul-logging.properties"));
        writeOptional(outputFolder, new SupportingFile("pom.mustache", "", "pom.xml"));
        writeOptional(outputFolder, new SupportingFile(".gitignore.mustache", "", ".gitignore"));
        writeOptional(outputFolder, new SupportingFile("README.mustache", "", "README.md"));
        writeOptional(outputFolder, new SupportingFile("Dockerfile.mustache", "", "Dockerfile"));
    }

    @Override
    public void postProcessModelProperty(CodegenModel model, CodegenProperty property) {
        super.postProcessModelProperty(model, property);

        if (!BooleanUtils.toBoolean(model.isEnum)) {
            model.imports.remove("ApiModelProperty");
            model.imports.remove("ApiModel");
            model.imports.add("JsonInclude");
            model.imports.add("JsonProperty");
        }

        return;
    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        Map<String, Object> newObjs = super.postProcessOperations(objs);

        Map<String, Object> operations = (Map<String, Object>) newObjs.get("operations");
        if (operations != null) {
            List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
            for (CodegenOperation operation : ops) {
                operation.httpMethod = operation.httpMethod.toLowerCase();

                if ("Void".equalsIgnoreCase(operation.returnType)) {
                    operation.returnType = null;
                }

                if (operation.getHasPathParams()) {
                    operation.path = camelizePath(operation.path);
                }
            }
        }
        return newObjs;
    }

    @Override
    public void preprocessSwagger(Swagger swagger) {
        super.preprocessSwagger(swagger);

        // add full swagger definition in a mustache parameter
        String swaggerDef = Json.pretty(swagger);
        this.additionalProperties.put("fullSwagger", swaggerDef);

        // add server port from the swagger file, 8080 by default
        String host = swagger.getHost();
        String port = extractPortFromHost(host);
        this.additionalProperties.put("serverPort", port);

        // retrieve api version from swagger file, 1.0.0-SNAPSHOT by default
        if (swagger.getInfo() != null && swagger.getInfo().getVersion() != null)
            artifactVersion = apiVersion = swagger.getInfo().getVersion();
        else
            artifactVersion = apiVersion;

        // manage operation & custom serviceId
        Map<String, Path> paths = swagger.getPaths();
        if (paths != null) {
            for (Entry<String, Path> entry : paths.entrySet()) {
                manageOperationNames(entry.getValue(), entry.getKey());
            }

        }
    }

    private void manageOperationNames(Path path, String pathname) {
        String serviceIdTemp;

        Map<HttpMethod, Operation> operationMap = path.getOperationMap();
        if (operationMap != null) {
            for (Entry<HttpMethod, Operation> entry : operationMap.entrySet()) {
                serviceIdTemp = computeServiceId(pathname, entry);
                entry.getValue().setVendorExtension("x-serviceId", serviceIdTemp);
                entry.getValue().setVendorExtension("x-serviceId-varName", serviceIdTemp.toUpperCase() + "_SERVICE_ID");
            }
        }
    }

    private String computeServiceId(String pathname, Entry<HttpMethod, Operation> entry) {
        String operationId = entry.getValue().getOperationId();
        String result = (operationId != null) ? operationId : entry.getKey().name() + pathname.replaceAll("-", "_").replaceAll("/", "_").replaceAll("[{}]", "");
        return result;
    }

    private String extractPortFromHost(String host) {
        if (host != null) {
            int portSeprator = host.indexOf(':');
            if (portSeprator >= 0) {
                return host.substring(portSeprator);
            }
        }
        return "8080";
    }

    private String camelizePath(String path) {
        String word = path;
        Pattern p = Pattern.compile("\\{([^/]*)\\}");
        Matcher m = p.matcher(word);
        while (m.find()) {
            word = m.replaceFirst(":" + m.group(1));
            m = p.matcher(word);
        }
        p = Pattern.compile("(_)(.)");
        m = p.matcher(word);
        while (m.find()) {
            word = m.replaceFirst(m.group(2).toUpperCase());
            m = p.matcher(word);
        }
        return word;
    }
}
