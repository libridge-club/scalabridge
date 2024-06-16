package club.libridge.libridgebackend.app.controller;

import static club.libridge.libridgebackend.logging.LibridgeLogger.LOGGER;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.libridge.libridgebackend.app.persistence.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    @NonNull
    private final ClientRepository repository;

    @GetMapping("/")
    public List<Long> getAllClients() {
        LOGGER.trace("ClientController_getAllClients");
        return repository.findAll().stream().map(entity -> entity.getCount()).sorted().toList();
    }

}
