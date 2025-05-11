package net.edmacdonald.jvmcli.impldetails.runtimehints;

import liquibase.changelog.ChangeLogHistoryServiceFactory;
import liquibase.changelog.FastCheckService;
import liquibase.changelog.visitor.ValidatingVisitorGeneratorFactory;
import liquibase.database.LiquibaseTableNamesFactory;
import liquibase.report.ShowSummaryGenerator;
import liquibase.report.ShowSummaryGeneratorFactory;
import liquibase.ui.LoggerUIService;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

import java.util.Collections;
import java.util.List;

// https://github.com/spring-projects/spring-boot/issues/38941#issuecomment-1871816760
public class LiquibaseRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        List.of(
                        ChangeLogHistoryServiceFactory.class,
                        LoggerUIService.class,
                        FastCheckService.class,
                        LiquibaseTableNamesFactory.class,
                        ValidatingVisitorGeneratorFactory.class,
                        ShowSummaryGenerator.class,
                        ShowSummaryGeneratorFactory.class)
                .forEach(clazz ->
                        hints.reflection().registerType(
                                clazz,
                                (type) -> type.withConstructor(Collections.emptyList(), ExecutableMode.INVOKE)));
    }
}
