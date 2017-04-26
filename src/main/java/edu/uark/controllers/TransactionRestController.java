package edu.uark.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.commands.transactions.TransactionQuery;
import edu.uark.commands.transactions.TransactionSaveCommand;
import edu.uark.models.api.Transaction;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionRestController {
	//open
	//get
	//close transaction
	@RequestMapping(value = "/apiv0/{transactionId}", method = RequestMethod.GET)
	public Transaction getTransaction(@PathVariable UUID transactionId) {
		return (new TransactionQuery()).
			setTransactionId(transactionId).
			execute();
	}

	

	/*@RequestMapping(value = "/apiv0/transactions", method = RequestMethod.GET)
	public TransactionListing getTransactions() {
		return (new TransactionsQuery()).execute();
	}*/
	
	@RequestMapping(value = "/apiv0/", method = RequestMethod.PUT)
	public Transaction putTransaction(@RequestBody Transaction transaction) {
		return (new TransactionSaveCommand()).
			setApiTransaction(transaction).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (TransactionRestController)";
	}
}
