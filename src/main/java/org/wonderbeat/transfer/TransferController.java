package org.wonderbeat.transfer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.wonderbeat.transfer.service.MoneyTransferService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.Serializable;

@Controller
@RequestMapping("/money")
public class TransferController implements Serializable {

    static final long serialVersionUID = 1L;

    @Inject
    private MoneyTransferService moneyTransfer;

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String transfer(@ModelAttribute @Valid TransferCommand command) {
        moneyTransfer.transfer(command.getFrom(), command.getTo(), command.getAmount());
        return "redirect:/";
    }


    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deposit(@ModelAttribute @Valid BalanceUpdateCommand command) {
        moneyTransfer.deposit(command.getUserId(), command.getAmount());
        return "redirect:/";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String withdraw(@ModelAttribute @Valid BalanceUpdateCommand command) {
        moneyTransfer.withdraw(command.getUserId(), command.getAmount());
        return "redirect:/";
    }

    public void setMoneyTransfer(MoneyTransferService moneyTransfer) {
        this.moneyTransfer = moneyTransfer;
    }
}
