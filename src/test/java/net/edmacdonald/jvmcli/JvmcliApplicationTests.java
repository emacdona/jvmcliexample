package net.edmacdonald.jvmcli;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.BeansException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
@Import(JvmcliApplicationTests.TestOverrides.class)
@ExtendWith(OutputCaptureExtension.class)
class JvmcliApplicationTests {

    private static Path tempDir;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    static void beforeTestClass() throws IOException {
        tempDir = Files.createTempDirectory(JvmcliApplicationTests.class.getName());
        Files.writeString(tempDir.resolve("one.txt"), "hi");
        Files.writeString(tempDir.resolve("two.txt"), "hi");
        Files.writeString(tempDir.resolve("three.txt"), "hi");
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testLs(CapturedOutput output) throws IOException {
        AutowireCapableBeanFactory bf = applicationContext.getAutowireCapableBeanFactory();
        JvmcliApplication.CliEntrypoint cli = bf.createBean(JvmcliApplication.CliEntrypoint.class);
        cli.run("filesys", "ls", tempDir.toString());
        cli.run("filesys", "find", tempDir.toString());
        cli.run("history", "show");

        //String all = output.getOut() + output.getErr();
        //assertThat(all).contains("Temp Dir:")
        //                .contains("hello.txt");
    }

    @TestConfiguration
    static class TestOverrides implements BeanDefinitionRegistryPostProcessor {
        private static final String CLI_BEAN_CLASS = "net.edmacdonald.jvmcli.JvmcliApplication$CliEntrypoint";
        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
            for (String name : registry.getBeanDefinitionNames()) {
                var bd = registry.getBeanDefinition(name);
                if (CLI_BEAN_CLASS.equals(bd.getBeanClassName())) {
                    registry.removeBeanDefinition(name);
                }
            }
        }
        @Override
        public void postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory beanFactory) throws BeansException {
            // no-op
        }
    }
}
