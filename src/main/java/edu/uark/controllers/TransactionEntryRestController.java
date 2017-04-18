package edu.uark.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import edu.uark.commands.transactionEntry.ProductByLookupCodeQuery;
import edu.uark.commands.transactionEntry.TransactionEntryQuery;
import edu.uark.commands.transactionEntry.TransactionEntrySaveCommand;
import edu.uark.commands.transactionEntry.TransactionEntrysQuery;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.api.TransactionEntryListing;

@RestController
@RequestMapping(value = "/transactionEntry")
public class TransactionEntryRestController {
	//Is this where we add the functionality for the transactions?
	@RequestMapping(value = "/apiv0/{entryId}", method = RequestMethod.GET)
	public TransactionEntry getTransactionEntry(@PathVariable UUID entryId) {
		return (new TransactionEntryQuery()).
			setProductId(entryId).
			execute();
	}

	@RequestMapping(value = "/apiv0/TransactionEntry", method = RequestMethod.GET)
	public TransactionEntryListing getTransactionEntrys() {
		return (new TransactionEntrysQuery()).execute();
	}
	
	@RequestMapping(value = "/apiv0/", method = RequestMethod.PUT)
	public TransactionEntry putTransactionEntry(@RequestBody TransactionEntry transactionEntry) {
		return (new TransactionEntrySaveCommand()).
			setApiTransactionEntry(transactionEntry).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (ProductRestController)";
	}
}
