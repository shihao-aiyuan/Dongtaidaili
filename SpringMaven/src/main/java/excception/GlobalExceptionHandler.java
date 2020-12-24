package excception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shihao
 * @create 2020-11-02 17:38
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyException.class)
    public Reslut myExceptionHandler(HttpServletRequest request, HttpServletResponse response, final Exception e) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        MyException myException = (MyException) e;
        return new Reslut(myException.getCode(), myException.getMessage());

    }

    @ExceptionHandler({RuntimeException.class})
    public Reslut runTimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, final Exception e) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException runtimeException = (RuntimeException) e;
        return new Reslut(500, runtimeException.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return new ResponseEntity<>(new Reslut(status.value(), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
        }

        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败,方法:" + exception.getParameter().getMethod().getName() + ", 参数: " + exception.getName()
                    + ", 信息: " + exception.getLocalizedMessage());
            return new ResponseEntity<>(new Reslut(status.value(), "参数转换失败"), status);
        }

        return new ResponseEntity<>(new Reslut(status.value(),"参数转换失败"),status);

    }

}
