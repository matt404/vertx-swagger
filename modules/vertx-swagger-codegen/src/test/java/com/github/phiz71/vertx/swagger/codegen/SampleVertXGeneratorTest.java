package com.github.phiz71.vertx.swagger.codegen;

import io.swagger.codegen.SwaggerCodegen;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SampleVertXGeneratorTest {

    @Test
    public void generateSampleServer() {
        String[] args = new String[11];
        args[0] = "generate";
        args[1] = "-l";
        args[2] = "java-vertx";
        args[3] = "-i";
        args[4] = "petStore.json";
        args[5] = "-o";
        args[6] = "../../sample/petstore-vertx-server";
        args[7] = "--group-id";
        args[8] = "io.swagger";
        args[9] = "--artifact-id";
        args[10] = "petstore-vertx-server";
        SwaggerCodegen.main(args);
    }

    @Test
    public void generateSampleServerWithRX() {
        String[] args = new String[12];
        args[0] = "generate";
        args[1] = "-l";
        args[2] = "java-vertx";
        args[3] = "-i";
        args[4] = "petStore.json";
        args[5] = "-o";
        args[6] = "../../sample/petstore-vertx-rx-server";
        args[7] = "--group-id";
        args[8] = "io.swagger";
        args[9] = "--artifact-id";
        args[10] = "petstore-vertx-rx-server";
        args[11] = "-DrxInterface=true";
        SwaggerCodegen.main(args);
    }

    @Test
    public void testOptionalMainApiVerticleTrue() throws IOException {
        String[] args = new String[10];
        args[0] = "generate";
        args[1] = "-l";
        args[2] = "java-vertx";
        args[3] = "-i";
        args[4] = "testOptionalMainApiVerticle.json";
        args[5] = "-o";
        args[6] = "temp/test-server";
        args[7] = "--group-id";
        args[8] = "io.swagger.server.api";
        args[9] = "-DmainVerticleGeneration=true";
        SwaggerCodegen.main(args);

        File mainApiFile = new File(
                "temp/test-server/src/main/java/io/swagger/server/api/MainApiVerticle.java");
        Assert.assertTrue(mainApiFile.exists());

        FileUtils.deleteDirectory(new File("temp"));
    }

    @Test
    public void testOptionalMainApiVerticleDefaultValue() throws IOException {
        String[] args = new String[9];
        args[0] = "generate";
        args[1] = "-l";
        args[2] = "java-vertx";
        args[3] = "-i";
        args[4] = "testOptionalMainApiVerticle.json";
        args[5] = "-o";
        args[6] = "temp/test-server";
        args[7] = "--group-id";
        args[8] = "io.swagger.server.api";
        SwaggerCodegen.main(args);

        File mainApiFile = new File(
                "temp/test-server/src/main/java/io/swagger/server/api/MainApiVerticle.java");
        Assert.assertTrue(mainApiFile.exists());

        FileUtils.deleteDirectory(new File("temp"));
    }

    @Test
    public void testOptionalMainApiVerticleFalse() throws IOException {
        String[] args = new String[10];
        args[0] = "generate";
        args[1] = "-l";
        args[2] = "java-vertx";
        args[3] = "-i";
        args[4] = "testOptionalMainApiVerticle.json";
        args[5] = "-o";
        args[6] = "temp/test-server";
        args[7] = "--group-id";
        args[8] = "io.swagger.server.api";
        args[9] = "-DmainVerticleGeneration=false";
        SwaggerCodegen.main(args);

        File mainApiFile = new File(
                "temp/test-server/src/main/java/io/swagger/server/api/MainApiVerticle.java");
        Assert.assertTrue(!mainApiFile.exists());

        FileUtils.deleteDirectory(new File("temp"));
    }

    @Test
    public void testBodyWithList() throws IOException {
        String[] args = new String[9];
        args[0] = "generate";
        args[1] = "-l";
        args[2] = "java-vertx";
        args[3] = "-i";
        args[4] = "testListInBody.json";
        args[5] = "-o";
        args[6] = "temp/test-server";
        args[7] = "--group-id";
        args[8] = "io.swagger.server.api";
        SwaggerCodegen.main(args);

        File testApiVerticleFile = new File(
                "temp/test-server/src/main/java/io/swagger/server/api/verticle/TestApiVerticle.java");

        Assert.assertTrue(FileUtils.readFileToString(testApiVerticleFile).contains(
                "List<String> updateModel = Json.mapper.readValue(message.body().getJsonArray(\"updateModel\").encode(), new TypeReference<List<String>>(){});"));

        FileUtils.deleteDirectory(new File("temp"));
    }
    
    @Test
    public void testUUID() throws IOException {
        String[] args = new String[9];
        args[0] = "generate";
        args[1] = "-l";
        args[2] = "java-vertx";
        args[3] = "-i";
        args[4] = "testUUID.json";
        args[5] = "-o";
        args[6] = "temp/test-server";
        args[7] = "--group-id";
        args[8] = "io.swagger.server.api";
        SwaggerCodegen.main(args);

        File testApiVerticleFile = new File(
                "temp/test-server/src/main/java/io/swagger/server/api/verticle/TestApiVerticle.java");

        Assert.assertTrue(FileUtils.readFileToString(testApiVerticleFile).contains(
                "UUID uuidParam = UUID.fromString(message.body().getString(\"uuidParam\"));"));

        FileUtils.deleteDirectory(new File("temp"));
    }
}
