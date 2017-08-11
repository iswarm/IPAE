package eth;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.1.
 */
public final class ClearoutBuyers extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60405161177a38038061177a833981016040528080518201919060200180519150505b81815b6000825160019081018155600160a060020a033316906002905b0160005b505550600160a060020a0333166000908152601660205260408120600190555b82518110156100f15782818151811061008857fe5b90602001906020020151600160a060020a03166002828101601481106100aa57fe5b0160005b505560028101601660008584815181106100c457fe5b90602001906020020151600160a060020a031681526020810191909152604001600020555b600101610073565b60008290555b5050505b50505b61166d8061010d6000396000f300606060405236156101045763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663173825d981146101095780632ebdc8131461012a5780632f54bf6e1461016d5780633a5ec3d6146101a05780634123cb6b146101d35780634841a9f1146101f85780634b0ac39d146102245780634d28c05b146102575780637065cb481461029e578063746c9171146102bf5780637f4a68de146102e4578063850f43dd14610309578063a210c3b11461034f578063b0665f521461037f578063b75c7dc6146103b8578063ba51a6df146103d0578063c2cf7326146103e8578063c9e1bb451461041e578063f00d4b5d1461044e575b600080fd5b341561011457600080fd5b610128600160a060020a0360043516610475565b005b341561013557600080fd5b610149600160a060020a036004351661055f565b60405180848152602001838152602001828152602001935050505060405180910390f35b341561017857600080fd5b61018c600160a060020a0360043516610580565b604051901515815260200160405180910390f35b34156101ab57600080fd5b61018c600160a060020a03600435166105a0565b604051901515815260200160405180910390f35b34156101de57600080fd5b6101e66105b5565b60405190815260200160405180910390f35b341561020357600080fd5b61018c60043515156105bb565b604051901515815260200160405180910390f35b341561022f57600080fd5b61018c600160a060020a0360043516610656565b604051901515815260200160405180910390f35b341561026257600080fd5b61018c600435602435600160a060020a03604435811690606435906084359060a4351661066b565b604051901515815260200160405180910390f35b34156102a957600080fd5b610128600160a060020a03600435166109a6565b005b34156102ca57600080fd5b6101e6610a9e565b60405190815260200160405180910390f35b34156102ef57600080fd5b6101e6610aa4565b60405190815260200160405180910390f35b341561031457600080fd5b610149600160a060020a0360043516602435610aa9565b60405180848152602001838152602001828152602001935050505060405180910390f35b341561035a57600080fd5b61018c600435602435604435610ae0565b604051901515815260200160405180910390f35b341561038a57600080fd5b61018c600160a060020a0360043581169060243516610b96565b604051901515815260200160405180910390f35b34156103c357600080fd5b610128600435610c43565b005b34156103db57600080fd5b610128600435610cec565b005b34156103f357600080fd5b61018c600435600160a060020a0360243516610d6e565b604051901515815260200160405180910390f35b341561042957600080fd5b61018c600435602435604435610dc1565b604051901515815260200160405180910390f35b341561045957600080fd5b610128600160a060020a0360043581169060243516611120565b005b6000803660405180838380828437820191505092505050604051809103902061049d81611224565b1561055857600160a060020a03831660009081526016602052604090205491508115156104c957610558565b600180540360005411156104dc57610558565b6000600283601481106104eb57fe5b0160005b5055600160a060020a038316600090815260166020526040812055610512611388565b61051a611417565b7f58619076adf5bb0943d100ef88d52d7c3fd691b19d3a9071b555b651fbf418da83604051600160a060020a03909116815260200160405180910390a15b5b5b505050565b60196020526000908152604090208054600182015460029092015490919083565b600160a060020a038116600090815260166020526040812054115b919050565b601d6020526000908152604090205460ff1681565b60015481565b600160a060020a0333166000908152601c602052604081205460ff1615156105e257600080fd5b33600160a060020a0381166000908152601d602052604090819020805460ff19168515151790557f104b744588c2a1d49199fdafa641c13d028a86683ae7b2b0af115cbc931e98e8918491519115158252600160a060020a031660208201526040908101905180910390a15060015b919050565b601c6020526000908152604090205460ff1681565b6000803660405180838380828437820191505092505050604051809103902061069381611224565b1561099657600160a060020a038381166000908152601b60205260409020805491881691879081106106c157fe5b906000526020600020900160005b9054600160a060020a036101009290920a900416146106ed57600080fd5b86600114801561072a5750600160a060020a0386166000908152601a60205260409020600c855b06600c811061071f57fe5b6003020160005b5054155b156107b257600160a060020a0386166000908152601a602052604090208890600c865b06600c811061075857fe5b6003020160005b5055600080516020611622833981519152888888866040519384526020840192909252600160a060020a03908116604080850191909152911660608301526080909101905180910390a160019150610996565b8660021480156107f25750600160a060020a0386166000908152601a60205260409020600c855b06600c81106107e457fe5b6003020160005b5060010154155b1561087d57600160a060020a0386166000908152601a602052604090208890600c865b06600c811061082057fe5b6003020160005b5060010155600080516020611622833981519152888888866040519384526020840192909252600160a060020a03908116604080850191909152911660608301526080909101905180910390a160019150610996565b8660031480156108bd5750600160a060020a0386166000908152601a60205260409020600c855b06600c81106108af57fe5b6003020160005b5060020154155b1561094857600160a060020a0386166000908152601a602052604090208890600c865b06600c81106108eb57fe5b6003020160005b5060020155600080516020611622833981519152888888866040519384526020840192909252600160a060020a03908116604080850191909152911660608301526080909101905180910390a160019150610996565b60008051602061162283398151915260008088866040519384526020840192909252600160a060020a03908116604080850191909152911660608301526080909101905180910390a1600091505b5b5b5b5b5b509695505050505050565b6000366040518083838082843782019150509250505060405180910390206109cd81611224565b15610a98576109db82610580565b156109e557610a98565b6109ed611388565b60015460149010610a0057610a00611417565b5b60015460149010610a1157610a98565b60018054810190819055600160a060020a0383169060029060148110610a3357fe5b0160005b5055600154600160a060020a03831660009081526016602052604090819020919091557f994a936646fe87ffe4f1e469d3d6aa417d6b855598397f323de5b449f765f0c390839051600160a060020a03909116815260200160405180910390a15b5b5b5050565b60005481565b600c81565b601a60205260008281526040902081600c8110610ac257fe5b6003020160005b508054600182015460029092015490935090915083565b600160a060020a0333166000908152601c602052604081205460ff161515610b0757600080fd5b33600160a060020a03811660009081526019602052604090819020868155600181018690556002018490557f0bb7d3d01f129b53fd108b505d7f1e3901fb68a4283cbcd46f71c2e27949977691869186918691519384526020840192909252604080840191909152600160a060020a0390911660608301526080909101905180910390a15060015b9392505050565b60008036604051808383808284378201915050925050506040518091039020610bbe81611224565b15610c3a57600160a060020a0383166000908152601b60205260409020805460018101610beb8382611549565b916000526020600020900160005b8154600160a060020a038089166101009390930a838102910219909116179091556000908152601c60205260409020805460ff191660019081179091559250505b5b5b5092915050565b600160a060020a0333166000908152601660205260408120549080821515610c6a57610ce5565b505060008281526017602052604081206001810154600284900a929083161115610ce5578054600190810182558101805483900390557fc7fb647e59b18047309aa15aad418e5d7ca96d173ad704f1031a2c3d7591734b3385604051600160a060020a03909216825260208201526040908101905180910390a15b5b50505050565b600036604051808383808284378201915050925050506040518091039020610d1381611224565b15610a9857600154821115610d2757610a98565b6000829055610d34611388565b7facbdb084c721332ac59f9b8e392196c9eb0e4932862da8eb9beaf0dad4f550da8260405190815260200160405180910390a15b5b5b5050565b6000828152601760209081526040808320600160a060020a0385168452601690925282205482811515610da45760009350610db8565b8160020a9050808360010154166000141593505b50505092915050565b600160a060020a0333166000908152601c602052604081205460ff161515610de857600080fd5b826001148015610e345750600160a060020a033316600090815260196020908152604080832054601a909252909120600c845b06600c8110610e2657fe5b6003020160005b5054850310155b15610edb57600160a060020a0333166000908152601a60205260409020600c835b06600c8110610e6057fe5b6003020160005b5054600160a060020a03331660009081526019602090815260408083208054909401909355601a9052908120600c845b06600c8110610ea257fe5b6003020160005b5055600080516020611602833981519152848460405191825260208201526040908101905180910390a1506001610b8f565b826002148015610f2d5750600160a060020a033316600090815260196020908152604080832060010154601a909252909120600c845b06600c8110610f1c57fe5b6003020160005b5060010154850310155b15610fe057600160a060020a0333166000908152601a60205260409020600c835b06600c8110610f5957fe5b6003020160005b50600190810154600160a060020a03331660009081526019602090815260408083209094018054909301909255601a909152908120600c845b06600c8110610fa457fe5b6003020160005b5060010155600080516020611602833981519152848460405191825260208201526040908101905180910390a1506001610b8f565b8260031480156110325750600160a060020a033316600090815260196020908152604080832060020154601a909252909120600c845b06600c811061102157fe5b6003020160005b5060020154850310155b156110e557600160a060020a0333166000908152601a60205260409020600c835b06600c811061105e57fe5b6003020160005b50600290810154600160a060020a03331660009081526019602090815260408083209094018054909301909255601a909152908120600c845b06600c81106110a957fe5b6003020160005b5060020155600080516020611602833981519152848460405191825260208201526040908101905180910390a1506001610b8f565b60008051602061160283398151915260008060405191825260208201526040908101905180910390a1506000610b8f565b5b5b5b9392505050565b6000803660405180838380828437820191505092505050604051809103902061114881611224565b15610ce55761115683610580565b1561116057610ce5565b600160a060020a038416600090815260166020526040902054915081151561118757610ce5565b61118f611388565b600160a060020a038316600283601481106111a657fe5b0160005b5055600160a060020a0380851660009081526016602052604080822082905591851681528190208390557fb532073b38c83145e3e5135377a08bf9aab55bc0fd7c1179cd4fb995d2a5159c908590859051600160a060020a039283168152911660208201526040908101905180910390a15b5b5b50505050565b600160a060020a033316600090815260166020526040812054818082151561124b5761137e565b6000858152601760205260409020805490925015156112ab57600080548355600180840191909155601880549161128491908301611549565b6002830181905560188054879290811061129a57fe5b906000526020600020900160005b50555b8260020a9050808260010154166000141561137e577fe1c52dc63b719ade82e8bea94cc41a0d5d28e4aaf536adb5e9cccc9ff8c1aeda3386604051600160a060020a03909216825260208201526040908101905180910390a181546001901161136b5760008581526017602052604090206002015460188054909190811061132f57fe5b906000526020600020900160005b506000908190558581526017602052604081208181556001808201839055600290910191909155935061137e565b8154600019018255600182018054821790555b5b5b505050919050565b60185460005b818110156114065760188054829081106113a457fe5b906000526020600020900160005b5054156113fd57601760006018838154811015156113cc57fe5b906000526020600020900160005b505481526020810191909152604001600090812081815560018101829055600201555b5b60010161138e565b610a986018600061159d565b5b5050565b60015b600154811015611545575b6001548110801561144757506002816014811061143e57fe5b0160005b505415155b1561145457600101611425565b5b6001805411801561147957506001546002906014811061147157fe5b0160005b5054155b1561148d5760018054600019019055611454565b600154811080156114b25750600154600290601481106114a957fe5b0160005b505415155b80156114ce5750600281601481106114c657fe5b0160005b5054155b1561154057600154600290601481106114e357fe5b0160005b5054600282601481106114f657fe5b0160005b505580601660006002836014811061150e57fe5b0160005b50548152602001908152602001600020819055506000600260015460148110151561153957fe5b0160005b50555b61141a565b5b50565b815481835581811511610558576000838152602090206105589181019083016115bf565b5b505050565b815481835581811511610558576000838152602090206105589181019083016115bf565b5b505050565b508054600082559060005260206000209081019061154591906115bf565b5b50565b6115dd91905b808211156115d957600081556001016115c5565b5090565b90565b6115dd91905b808211156115d957600081556001016115c5565b5090565b9056009d1c112abc511adeea2c2cb658a5a64a9904abc92ff58d6adebcdc50168fe30ed9709c71ed4e1c03e7c28c23d514742b2e8e84c10175324e0a6398fc21fd102aa165627a7a7230582054b78317c2f44f92caa499697758fb26320e416e9f6b8f3c664a92ee9584f4b10029\n";

    private ClearoutBuyers(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private ClearoutBuyers(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<SubmitPowerEventResponse> getSubmitPowerEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SubmitPower", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SubmitPowerEventResponse> responses = new ArrayList<SubmitPowerEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SubmitPowerEventResponse typedResponse = new SubmitPowerEventResponse();
            typedResponse._power = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._type = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SubmitPowerEventResponse> submitPowerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("SubmitPower", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SubmitPowerEventResponse>() {
            @Override
            public SubmitPowerEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SubmitPowerEventResponse typedResponse = new SubmitPowerEventResponse();
                typedResponse._power = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._type = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<WithdrawPowerEventResponse> getWithdrawPowerEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("WithdrawPower", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<WithdrawPowerEventResponse> responses = new ArrayList<WithdrawPowerEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            WithdrawPowerEventResponse typedResponse = new WithdrawPowerEventResponse();
            typedResponse._power = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._type = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(2);
            typedResponse._owner = (Address) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<WithdrawPowerEventResponse> withdrawPowerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("WithdrawPower", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, WithdrawPowerEventResponse>() {
            @Override
            public WithdrawPowerEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                WithdrawPowerEventResponse typedResponse = new WithdrawPowerEventResponse();
                typedResponse._power = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._type = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(2);
                typedResponse._owner = (Address) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public List<IsMeterOpenedEventResponse> getIsMeterOpenedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("IsMeterOpened", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<IsMeterOpenedEventResponse> responses = new ArrayList<IsMeterOpenedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            IsMeterOpenedEventResponse typedResponse = new IsMeterOpenedEventResponse();
            typedResponse._status = (Bool) eventValues.getNonIndexedValues().get(0);
            typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<IsMeterOpenedEventResponse> isMeterOpenedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("IsMeterOpened", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, IsMeterOpenedEventResponse>() {
            @Override
            public IsMeterOpenedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                IsMeterOpenedEventResponse typedResponse = new IsMeterOpenedEventResponse();
                typedResponse._status = (Bool) eventValues.getNonIndexedValues().get(0);
                typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<SetIntialMeterEventResponse> getSetIntialMeterEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SetIntialMeter", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SetIntialMeterEventResponse> responses = new ArrayList<SetIntialMeterEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SetIntialMeterEventResponse typedResponse = new SetIntialMeterEventResponse();
            typedResponse._peak = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._valley = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._flat = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SetIntialMeterEventResponse> setIntialMeterEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("SetIntialMeter", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SetIntialMeterEventResponse>() {
            @Override
            public SetIntialMeterEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SetIntialMeterEventResponse typedResponse = new SetIntialMeterEventResponse();
                typedResponse._peak = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._valley = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._flat = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public List<ConfirmationEventResponse> getConfirmationEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Confirmation", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ConfirmationEventResponse> responses = new ArrayList<ConfirmationEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ConfirmationEventResponse typedResponse = new ConfirmationEventResponse();
            typedResponse.owner = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.operation = (Bytes32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ConfirmationEventResponse> confirmationEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Confirmation", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ConfirmationEventResponse>() {
            @Override
            public ConfirmationEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ConfirmationEventResponse typedResponse = new ConfirmationEventResponse();
                typedResponse.owner = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.operation = (Bytes32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<RevokeEventResponse> getRevokeEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Revoke", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<RevokeEventResponse> responses = new ArrayList<RevokeEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            RevokeEventResponse typedResponse = new RevokeEventResponse();
            typedResponse.owner = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.operation = (Bytes32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RevokeEventResponse> revokeEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Revoke", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, RevokeEventResponse>() {
            @Override
            public RevokeEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                RevokeEventResponse typedResponse = new RevokeEventResponse();
                typedResponse.owner = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.operation = (Bytes32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<OwnerChangedEventResponse> getOwnerChangedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OwnerChanged", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OwnerChangedEventResponse> responses = new ArrayList<OwnerChangedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OwnerChangedEventResponse typedResponse = new OwnerChangedEventResponse();
            typedResponse.oldOwner = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnerChangedEventResponse> ownerChangedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OwnerChanged", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnerChangedEventResponse>() {
            @Override
            public OwnerChangedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OwnerChangedEventResponse typedResponse = new OwnerChangedEventResponse();
                typedResponse.oldOwner = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<OwnerAddedEventResponse> getOwnerAddedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OwnerAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OwnerAddedEventResponse> responses = new ArrayList<OwnerAddedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OwnerAddedEventResponse typedResponse = new OwnerAddedEventResponse();
            typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnerAddedEventResponse> ownerAddedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OwnerAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnerAddedEventResponse>() {
            @Override
            public OwnerAddedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OwnerAddedEventResponse typedResponse = new OwnerAddedEventResponse();
                typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<OwnerRemovedEventResponse> getOwnerRemovedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OwnerRemoved", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OwnerRemovedEventResponse> responses = new ArrayList<OwnerRemovedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OwnerRemovedEventResponse typedResponse = new OwnerRemovedEventResponse();
            typedResponse.oldOwner = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnerRemovedEventResponse> ownerRemovedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OwnerRemoved", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnerRemovedEventResponse>() {
            @Override
            public OwnerRemovedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OwnerRemovedEventResponse typedResponse = new OwnerRemovedEventResponse();
                typedResponse.oldOwner = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<RequirementChangedEventResponse> getRequirementChangedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("RequirementChanged", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<RequirementChangedEventResponse> responses = new ArrayList<RequirementChangedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            RequirementChangedEventResponse typedResponse = new RequirementChangedEventResponse();
            typedResponse.newRequirement = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RequirementChangedEventResponse> requirementChangedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("RequirementChanged", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, RequirementChangedEventResponse>() {
            @Override
            public RequirementChangedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                RequirementChangedEventResponse typedResponse = new RequirementChangedEventResponse();
                typedResponse.newRequirement = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> removeOwner(Address _owner) {
        Function function = new Function("removeOwner", Arrays.<Type>asList(_owner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> initialMeter(Address param0) {
        Function function = new Function("initialMeter", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> isOwner(Address _addr) {
        Function function = new Function("isOwner", Arrays.<Type>asList(_addr), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> isMeterOpened(Address param0) {
        Function function = new Function("isMeterOpened", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> m_numOwners() {
        Function function = new Function("m_numOwners", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> meterOpened(Bool _status) {
        Function function = new Function("meterOpened", Arrays.<Type>asList(_status), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> isMeter(Address param0) {
        Function function = new Function("isMeter", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> withdrawPower(Uint256 _power, Uint256 _type, Address _meter, Uint256 _seqMeter, Uint256 _seq, Address _owner) {
        Function function = new Function("withdrawPower", Arrays.<Type>asList(_power, _type, _meter, _seqMeter, _seq, _owner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> addOwner(Address _owner) {
        Function function = new Function("addOwner", Arrays.<Type>asList(_owner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> m_required() {
        Function function = new Function("m_required", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> numAsset() {
        Function function = new Function("numAsset", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<List<Type>> powers(Address param0, Uint256 param1) {
        Function function = new Function("powers", 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setIntialMeter(Uint256 _peak, Uint256 _valley, Uint256 _flat) {
        Function function = new Function("setIntialMeter", Arrays.<Type>asList(_peak, _valley, _flat), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> setMeterOwner(Address _meter, Address _owner) {
        Function function = new Function("setMeterOwner", Arrays.<Type>asList(_meter, _owner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> revoke(Bytes32 _operation) {
        Function function = new Function("revoke", Arrays.<Type>asList(_operation), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> changeRequirement(Uint256 _newRequired) {
        Function function = new Function("changeRequirement", Arrays.<Type>asList(_newRequired), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> hasConfirmed(Bytes32 _operation, Address _owner) {
        Function function = new Function("hasConfirmed", 
                Arrays.<Type>asList(_operation, _owner), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> submitPower(Uint256 _power, Uint256 _type, Uint256 _seq) {
        Function function = new Function("submitPower", Arrays.<Type>asList(_power, _type, _seq), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> changeOwner(Address _from, Address _to) {
        Function function = new Function("changeOwner", Arrays.<Type>asList(_from, _to), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<ClearoutBuyers> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, DynamicArray<Address> _owners, Uint256 _required) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_owners, _required));
        return deployAsync(ClearoutBuyers.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<ClearoutBuyers> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, DynamicArray<Address> _owners, Uint256 _required) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_owners, _required));
        return deployAsync(ClearoutBuyers.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static ClearoutBuyers load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClearoutBuyers(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ClearoutBuyers load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClearoutBuyers(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class SubmitPowerEventResponse {
        public Uint256 _power;

        public Uint256 _type;
    }

    public static class WithdrawPowerEventResponse {
        public Uint256 _power;

        public Uint256 _type;

        public Address _meter;

        public Address _owner;
    }

    public static class IsMeterOpenedEventResponse {
        public Bool _status;

        public Address _meter;
    }

    public static class SetIntialMeterEventResponse {
        public Uint256 _peak;

        public Uint256 _valley;

        public Uint256 _flat;

        public Address _meter;
    }

    public static class ConfirmationEventResponse {
        public Address owner;

        public Bytes32 operation;
    }

    public static class RevokeEventResponse {
        public Address owner;

        public Bytes32 operation;
    }

    public static class OwnerChangedEventResponse {
        public Address oldOwner;

        public Address newOwner;
    }

    public static class OwnerAddedEventResponse {
        public Address newOwner;
    }

    public static class OwnerRemovedEventResponse {
        public Address oldOwner;
    }

    public static class RequirementChangedEventResponse {
        public Uint256 newRequirement;
    }
}
