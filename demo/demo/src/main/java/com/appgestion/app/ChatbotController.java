package com.appgestion.app;

import com.appgestion.app.services.GroqService;
import com.appgestion.app.repo.TareaRepo;
import com.appgestion.app.repo.ProyectoRepo;
import com.appgestion.app.model.TareaEntity;
import com.appgestion.app.model.ProyectoEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatbotController {
    
    private final GroqService groqService;
    private final TareaRepo tareaRepo;
    private final ProyectoRepo proyectoRepo;
    
    public ChatbotController(GroqService groqService, TareaRepo tareaRepo, ProyectoRepo proyectoRepo) {
        this.groqService = groqService;
        this.tareaRepo = tareaRepo;
        this.proyectoRepo = proyectoRepo;
    }
    
    @PostMapping("/message")
    public Map<String, Object> chat(@RequestBody Map<String, Object> request) {
        String userMessage = (String) request.get("message");
        Map<String, Object> context = (Map<String, Object>) request.get("context");
        
        String contextStr = buildContext(context);
        
        String aiResponse = groqService.generateResponse(userMessage, contextStr);
        
        Map<String, Object> action = detectAction(userMessage);
        
        return Map.of(
            "message", aiResponse,
            "action", action != null ? action : Map.of()
        );
    }
    
    private String buildContext(Map<String, Object> context) {
        if (context == null) {
            return "Usuario no identificado";
        }
        
        String userName = (String) context.get("userName");
        Boolean isAdmin = (Boolean) context.get("userRole");
        
        List<TareaEntity> todasTareas = tareaRepo.findAll();
        List<ProyectoEntity> todosProyectos = proyectoRepo.findAll();
        
        StringBuilder tareasInfo = new StringBuilder();
        if (todasTareas.isEmpty()) {
            tareasInfo.append("No hay tareas registradas.\n");
        } else {
            tareasInfo.append("TAREAS ACTUALES:\n");
            int count = 0;
            for (TareaEntity tarea : todasTareas) {
                if (count >= 10) break; 
                tareasInfo.append(String.format("  * %s - Estado: %s - Tipo: %s - Fecha: %s\n", 
                    tarea.getNombre(),
                    tarea.getEstado() != null ? tarea.getEstado() : "Sin estado",
                    tarea.getTipo() != null ? tarea.getTipo() : "Sin tipo",
                    tarea.getFecha_ini() != null ? tarea.getFecha_ini().toString() : "Sin fecha"
                ));
                count++;
            }
            if (todasTareas.size() > 10) {
                tareasInfo.append(String.format("  ... y %d tareas más\n", todasTareas.size() - 10));
            }
        }
        
        StringBuilder proyectosInfo = new StringBuilder();
        if (todosProyectos.isEmpty()) {
            proyectosInfo.append("No hay proyectos registrados.\n");
        } else {
            proyectosInfo.append("\nPROYECTOS ACTUALES:\n");
            int count = 0;
            for (ProyectoEntity proyecto : todosProyectos) {
                if (count >= 10) break; 
                proyectosInfo.append(String.format("  * %s - Estado: %s - Fecha inicio: %s\n",
                    proyecto.getNombre(),
                    proyecto.getEstado() != null ? proyecto.getEstado() : "Sin estado",
                    proyecto.getFecha_ini() != null ? proyecto.getFecha_ini().toString() : "Sin fecha"
                ));
                count++;
            }
            if (todosProyectos.size() > 10) {
                proyectosInfo.append(String.format("  ... y %d proyectos más\n", todosProyectos.size() - 10));
            }
        }
        
        return String.format("""
            Usuario: %s
            Rol: %s
            Fecha: %s
            
            Esta es una app de gestión de proyectos y tareas.
            El usuario puede consultar sus tareas, proyectos y crear nuevos elementos.
            
            %s
            %s
            
            ESTADÍSTICAS:
            - Total de tareas: %d
            - Total de proyectos: %d
            """,
            userName != null ? userName : "Anónimo",
            Boolean.TRUE.equals(isAdmin) ? "Administrador" : "Usuario",
            java.time.LocalDate.now(),
            tareasInfo.toString(),
            proyectosInfo.toString(),
            todasTareas.size(),
            todosProyectos.size()
        );
    }
    
    private Map<String, Object> detectAction(String message) {
        String lower = message.toLowerCase();
        
        if (lower.contains("ver tareas") || lower.contains("ir a tareas") || lower.contains("mostrar tareas")) {
            return Map.of("type", "navigate", "payload", "/user/tareas");
        }
        if (lower.contains("ver proyectos") || lower.contains("ir a proyectos")) {
            return Map.of("type", "navigate", "payload", "/user/proyectos");
        }
        if (lower.contains("ir a home") || lower.contains("inicio")) {
            return Map.of("type", "navigate", "payload", "/user/home");
        }
        
        if (lower.contains("crear tarea") || lower.contains("nueva tarea")) {
            return Map.of("type", "create", "payload", Map.of("entity", "tareas"));
        }
        if (lower.contains("crear proyecto") || lower.contains("nuevo proyecto")) {
            return Map.of("type", "create", "payload", Map.of("entity", "proyectos"));
        }
        
        return null;
    }
}
