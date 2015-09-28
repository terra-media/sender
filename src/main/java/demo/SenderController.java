package demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vinícius D Saraiva vrx_vsaraiva@uolinc.com.br
 */

@Controller
@RequestMapping("/manifestacao")
public class SenderController {

    final static String queueName = "manifestacao";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("{id}")
    public String gerarManifestacao(Model model, @PathVariable("id")String id) {
        rabbitTemplate.convertAndSend(queueName, "Gerar manifestação id:" + id);
        model.addAttribute("id", id);
        return "index";
    }
}
