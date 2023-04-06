package uz.mk.springbootcrudapp.util.testForValiTest;

import fido.valitest.ParsedResponse;
import fido.valitest.TestResult;
import org.jetbrains.annotations.NotNull;

// Класс, в который будет парситься тело ответа на тестовый запрос
public class ParserClassExample implements ParsedResponse {
    // По результатам запросов будет получен массив TestResult
    // Метод matchResult будет вызван для каждого из него
    // Метод должен содержать набор правил, по которым проверяется ответ и возвращать true, если проверка успешная
    // Также метод может содержать вывод сообщений в консоль или запись логов в файл. Делайте так, как удобней вам
    Integer code;
    String msg;

    @Override
    public boolean matchResult(@NotNull TestResult testResult) {
        boolean isOK = true;
        if (testResult.getStatus() == 200) {
            isOK = false;
            System.out.println("Field: " + testResult.getTestInfo().getField() + " status 200");
        }

        return isOK;
    }


}
