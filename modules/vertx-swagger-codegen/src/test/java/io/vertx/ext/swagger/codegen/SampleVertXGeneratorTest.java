package io.vertx.ext.swagger.codegen;

import org.junit.Test;

import io.swagger.codegen.Codegen;
import io.swagger.codegen.SwaggerCodegen;

public class SampleVertXGeneratorTest {

	@Test
	public void test() {
		String[] args = new String[11];
		args[0] = "generate";
		args[1] = "-l";
		args[2] = "java-vertx";
		args[3] = "-i";
		args[4] = "/home/ubu/Documents/workspace/cloud.mgr/specs/user.yaml";
		args[5] = "-o";
		args[6] = "/home/ubu/Documents/workspace/cloud.mgr/user/";
		args[7] = "--group-id";
        args[8] = "cloud.mgr.api";
        args[9] = "--artifact-id";
        args[10] = "user";
		SwaggerCodegen.main(args);
	}

}
