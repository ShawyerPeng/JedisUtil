package exception;

/**
 * 异常类
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -9084561727097703075L;

    protected String code;
    protected String message;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
        this.message = message;
    }

    public ApplicationException(String code, String message) {
        super(code + " : " + message);
        this.code = code;
        this.message = message;
    }

    public ApplicationException(Throwable t) {
        super(t);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        // return super.fillInStackTrace();
        // 为了提高性能，不记录堆栈信息
        return null;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}