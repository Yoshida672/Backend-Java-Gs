package br.com.fiap.globalsolution2025.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String message;

        switch (status) {
            case 404:
                message = "Rota não encontrada. Verifique a URL.";
                break;
            case 403:
                message = "Acesso negado. Permissões insuficientes.";
                break;
            case 401:
                message = "Não autenticado. Token ausente ou inválido.";
                break;
            default:
                message = "Erro interno no servidor.";
        }

        return ResponseEntity.status(status).body(Map.of(
                "status", status,
                "mensagem", message
        ));
    }
}