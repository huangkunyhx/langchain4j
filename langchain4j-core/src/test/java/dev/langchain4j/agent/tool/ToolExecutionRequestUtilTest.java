package dev.langchain4j.agent.tool;

import com.google.gson.JsonPrimitive;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class ToolExecutionRequestUtilTest implements WithAssertions {
    @Test
    public void test_argument() {
        ToolExecutionRequest request = ToolExecutionRequest.builder()
                .id("id")
                .name("name")
                .arguments("{\"foo\":\"bar\", \"qux\": 12}")
                .build();

        assertThat(ToolExecutionRequestUtil.argumentsAsMap(request.arguments()))
                .containsEntry("foo", new JsonPrimitive("bar"))
                        .containsEntry("qux", new JsonPrimitive(12.0));

        assertThat((String) ToolExecutionRequestUtil.argument(request, "foo"))
                .isEqualTo("bar");
        assertThat((Number) ToolExecutionRequestUtil.argument(request, "qux"))
                .isEqualTo(12.0);
    }
}