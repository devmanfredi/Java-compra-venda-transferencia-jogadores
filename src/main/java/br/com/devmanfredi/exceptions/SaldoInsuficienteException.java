package br.com.devmanfredi.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
    private static final long serialVersionUID = -3544451725858932368L;

    public SaldoInsuficienteException() {
    }

    public SaldoInsuficienteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SaldoInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(Throwable cause) {
        super(cause);
    }
}
