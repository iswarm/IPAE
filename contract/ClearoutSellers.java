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
public final class ClearoutSellers extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b604051611c17380380611c17833981016040528080518201919060200180519150505b81815b6000825160019081018155600160a060020a033316906002905b0160005b505550600160a060020a0333166000908152601660205260408120600190555b82518110156100f15782818151811061008857fe5b90602001906020020151600160a060020a03166002828101601481106100aa57fe5b0160005b505560028101601660008584815181106100c457fe5b90602001906020020151600160a060020a031681526020810191909152604001600020555b600101610073565b60008290555b5050505b50505b611b0a8061010d6000396000f300606060405236156101255763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663173825d9811461012a578063223bcbff1461014b5780632ebdc813146101895780632f54bf6e146101e15780633877256c146102145780633a5ec3d6146102535780634123cb6b146102865780634841a9f1146102ab5780634b0ac39d146102d75780637065cb481461030a578063746c91711461032b5780637f4a68de14610350578063850f43dd14610375578063a210c3b1146103d0578063b0665f5214610400578063b75c7dc614610439578063ba51a6df14610451578063c2cf732614610469578063c675a3d21461049f578063d9906b4c146104e6578063e48603391461052d578063f00d4b5d1461055e575b600080fd5b341561013557600080fd5b610149600160a060020a0360043516610585565b005b341561015657600080fd5b61016d600160a060020a036004351660243561066f565b604051600160a060020a03909116815260200160405180910390f35b341561019457600080fd5b6101a8600160a060020a03600435166106b1565b60405180878152602001868152602001858152602001848152602001838152602001828152602001965050505050505060405180910390f35b34156101ec57600080fd5b610200600160a060020a03600435166106e6565b604051901515815260200160405180910390f35b341561021f57600080fd5b610200600435602435604435606435600160a060020a0360843516610706565b604051901515815260200160405180910390f35b341561025e57600080fd5b610200600160a060020a0360043516610c64565b604051901515815260200160405180910390f35b341561029157600080fd5b610299610c79565b60405190815260200160405180910390f35b34156102b657600080fd5b6102006004351515610c7f565b604051901515815260200160405180910390f35b34156102e257600080fd5b610200600160a060020a0360043516610d1a565b604051901515815260200160405180910390f35b341561031557600080fd5b610149600160a060020a0360043516610d2f565b005b341561033657600080fd5b610299610e27565b60405190815260200160405180910390f35b341561035b57600080fd5b610299610e2d565b60405190815260200160405180910390f35b341561038057600080fd5b6101a8600160a060020a0360043516602435610e32565b60405180878152602001868152602001858152602001848152602001838152602001828152602001965050505050505060405180910390f35b34156103db57600080fd5b610200600435602435604435610e7b565b604051901515815260200160405180910390f35b341561040b57600080fd5b610200600160a060020a0360043581169060243516610f20565b604051901515815260200160405180910390f35b341561044457600080fd5b610149600435610fcd565b005b341561045c57600080fd5b610149600435611076565b005b341561047457600080fd5b610200600435600160a060020a03602435166110f8565b604051901515815260200160405180910390f35b34156104aa57600080fd5b610200600435602435600160a060020a03604435811690606435906084359060a4351661114b565b604051901515815260200160405180910390f35b34156104f157600080fd5b610200600435602435600160a060020a03604435811690606435906084359060a435166113d4565b604051901515815260200160405180910390f35b341561053857600080fd5b610299600160a060020a03600435166115ab565b60405190815260200160405180910390f35b341561056957600080fd5b610149600160a060020a03600435811690602435166115bd565b005b600080366040518083838082843782019150509250505060405180910390206105ad816116c1565b1561066857600160a060020a03831660009081526016602052604090205491508115156105d957610668565b600180540360005411156105ec57610668565b6000600283601481106105fb57fe5b0160005b5055600160a060020a038316600090815260166020526040812055610622611825565b61062a6118b4565b7f58619076adf5bb0943d100ef88d52d7c3fd691b19d3a9071b555b651fbf418da83604051600160a060020a03909116815260200160405180910390a15b5b5b505050565b601b6020528160005260406000208181548110151561068a57fe5b906000526020600020900160005b915091509054906101000a9004600160a060020a031681565b601960205260009081526040902080546001820154600283015460038401546004850154600590950154939492939192909186565b600160a060020a038116600090815260166020526040812054115b919050565b600160a060020a0333166000908152601d602052604081205460ff16151561072d57600080fd5b600160a060020a038281166000908152601b60205260409020805433909216918690811061075757fe5b906000526020600020900160005b9054600160a060020a036101009290920a9004161461078357600080fd5b8460011480156107dc5750600160a060020a0333166000908152601a60205260409020600c845b06600c81106107b557fe5b6006020160005b5054600160a060020a033316600090815260196020526040902054870310155b1561090357600160a060020a0333166000908152601a60205260409020600c845b06600c811061080857fe5b6006020160005b5054600160a060020a03331660009081526019602090815260408083208054909401909355601a9052908120600c855b06600c811061084a57fe5b6006020160005b5055600160a060020a0333166000908152601a60205260409020600c845b06600c811061087a57fe5b6006020160005b5060010154600160a060020a038084166000908152601c602090815260408083208054909501909455339092168152601a909152908120600c855b06600c81106108c757fe5b6006020160005b5060010155600080516020611a9f833981519152868660405191825260208201526040908101905180910390a1506001610c58565b8460021480156109645750600160a060020a0333166000908152601a60205260409020600c845b06600c811061093557fe5b6006020160005b50600290810154600160a060020a033316600090815260196020526040902090910154870310155b15610a9757600160a060020a0333166000908152601a60205260409020600c845b06600c811061099057fe5b6006020160005b50600290810154600160a060020a03331660009081526019602090815260408083209094018054909301909255601a909152908120600c855b06600c81106109db57fe5b6006020160005b5060020155600160a060020a0333166000908152601a60205260409020600c845b06600c8110610a0e57fe5b6006020160005b5060030154600160a060020a038084166000908152601c602090815260408083208054909501909455339092168152601a909152908120600c855b06600c8110610a5b57fe5b6006020160005b5060030155600080516020611a9f833981519152868660405191825260208201526040908101905180910390a1506001610c58565b846003148015610af85750600160a060020a0333166000908152601a60205260409020600c845b06600c8110610ac957fe5b6006020160005b50600490810154600160a060020a033316600090815260196020526040902090910154870310155b15610c2b57600160a060020a0333166000908152601a60205260409020600c845b06600c8110610b2457fe5b6006020160005b50600490810154600160a060020a03331660009081526019602090815260408083209094018054909301909255601a909152908120600c855b06600c8110610b6f57fe5b6006020160005b5060040155600160a060020a0333166000908152601a60205260409020600c845b06600c8110610ba257fe5b6006020160005b5060050154600160a060020a038084166000908152601c602090815260408083208054909501909455339092168152601a909152908120600c855b06600c8110610bef57fe5b6006020160005b5060050155600080516020611a9f833981519152868660405191825260208201526040908101905180910390a1506001610c58565b600080516020611a9f83398151915260008060405191825260208201526040908101905180910390a15060005b5b5b5b95945050505050565b601e6020526000908152604090205460ff1681565b60015481565b600160a060020a0333166000908152601d602052604081205460ff161515610ca657600080fd5b33600160a060020a0381166000908152601e602052604090819020805460ff19168515151790557f104b744588c2a1d49199fdafa641c13d028a86683ae7b2b0af115cbc931e98e8918491519115158252600160a060020a031660208201526040908101905180910390a15060015b919050565b601d6020526000908152604090205460ff1681565b600036604051808383808284378201915050925050506040518091039020610d56816116c1565b15610e2157610d64826106e6565b15610d6e57610e21565b610d76611825565b60015460149010610d8957610d896118b4565b5b60015460149010610d9a57610e21565b60018054810190819055600160a060020a0383169060029060148110610dbc57fe5b0160005b5055600154600160a060020a03831660009081526016602052604090819020919091557f994a936646fe87ffe4f1e469d3d6aa417d6b855598397f323de5b449f765f0c390839051600160a060020a03909116815260200160405180910390a15b5b5b5050565b60005481565b600c81565b601a60205260008281526040902081600c8110610e4b57fe5b6006020160005b508054600182015460028301546003840154600485015460059095015493965091945092909186565b600160a060020a0333166000908152601d602052604081205460ff161515610ea257600080fd5b600160a060020a03331660009081526019602052604090819020858155600281018590556004018390557facf8914692cb5f7391c193d704dade19e59d78f8637d721139d1ed33eb4a9ce7908590859085905180848152602001838152602001828152602001935050505060405180910390a15060015b9392505050565b60008036604051808383808284378201915050925050506040518091039020610f48816116c1565b15610fc457600160a060020a0383166000908152601b60205260409020805460018101610f7583826119e6565b916000526020600020900160005b8154600160a060020a038089166101009390930a838102910219909116179091556000908152601d60205260409020805460ff191660019081179091559250505b5b5b5092915050565b600160a060020a0333166000908152601660205260408120549080821515610ff45761106f565b505060008281526017602052604081206001810154600284900a92908316111561106f578054600190810182558101805483900390557fc7fb647e59b18047309aa15aad418e5d7ca96d173ad704f1031a2c3d7591734b3385604051600160a060020a03909216825260208201526040908101905180910390a15b5b50505050565b60003660405180838380828437820191505092505050604051809103902061109d816116c1565b15610e21576001548211156110b157610e21565b60008290556110be611825565b7facbdb084c721332ac59f9b8e392196c9eb0e4932862da8eb9beaf0dad4f550da8260405190815260200160405180910390a15b5b5b5050565b6000828152601760209081526040808320600160a060020a038516845260169092528220548281151561112e5760009350611142565b8160020a9050808360010154166000141593505b50505092915050565b60008036604051808383808284378201915050925050506040518091039020611173816116c1565b156113c457600160a060020a0386166000908152601d602052604090205460ff16151561119f57600080fd5b600160a060020a038381166000908152601b60205260409020805491881691869081106111c857fe5b906000526020600020900160005b9054600160a060020a036101009290920a900416146111f457600080fd5b866001141561127657600160a060020a0386166000908152601a602052604090208890600c875b06600c811061122657fe5b6006020160005b5055600080516020611abf8339815191528888886040519283526020830191909152600160a060020a03166040808301919091526060909101905180910390a1600191506113c4565b86600214156112fb57600160a060020a0386166000908152601a602052604090208890600c875b06600c81106112a857fe5b6006020160005b5060020155600080516020611abf8339815191528888886040519283526020830191909152600160a060020a03166040808301919091526060909101905180910390a1600191506113c4565b866003141561138057600160a060020a0386166000908152601a602052604090208890600c875b06600c811061132d57fe5b6006020160005b5060040155600080516020611abf8339815191528888886040519283526020830191909152600160a060020a03166040808301919091526060909101905180910390a1600191506113c4565b600080516020611abf833981519152600080886040519283526020830191909152600160a060020a03166040808301919091526060909101905180910390a1600091505b5b5b5b5b5b509695505050505050565b600080366040518083838082843782019150509250505060405180910390206113fc816116c1565b156113c457600160a060020a0386166000908152601d602052604090205460ff16151561142857600080fd5b600160a060020a038381166000908152601b602052604090208054918816918790811061145157fe5b906000526020600020900160005b9054600160a060020a036101009290920a9004161461147d57600080fd5b86600114156114c657600160a060020a0386166000908152601a602052604090208890600c865b06600c81106114af57fe5b6006020160005b5060010180549091019055611554565b866002141561150f57600160a060020a0386166000908152601a602052604090208890600c865b06600c81106114f857fe5b6006020160005b5060030180549091019055611554565b866003141561155457600160a060020a0386166000908152601a602052604090208890600c865b06600c811061154157fe5b6006020160005b50600501805490910190555b5b5b7f52abf0ee1c51330cd0473cae6ac72ce4bf9b29dce23e5c4e776145df1fec82838887604051918252600160a060020a031660208201526040908101905180910390a1600191505b5b5b509695505050505050565b601c6020526000908152604090205481565b600080366040518083838082843782019150509250505060405180910390206115e5816116c1565b1561106f576115f3836106e6565b156115fd5761106f565b600160a060020a03841660009081526016602052604090205491508115156116245761106f565b61162c611825565b600160a060020a0383166002836014811061164357fe5b0160005b5055600160a060020a0380851660009081526016602052604080822082905591851681528190208390557fb532073b38c83145e3e5135377a08bf9aab55bc0fd7c1179cd4fb995d2a5159c908590859051600160a060020a039283168152911660208201526040908101905180910390a15b5b5b50505050565b600160a060020a03331660009081526016602052604081205481808215156116e85761181b565b600085815260176020526040902080549092501515611748576000805483556001808401919091556018805491611721919083016119e6565b6002830181905560188054879290811061173757fe5b906000526020600020900160005b50555b8260020a9050808260010154166000141561181b577fe1c52dc63b719ade82e8bea94cc41a0d5d28e4aaf536adb5e9cccc9ff8c1aeda3386604051600160a060020a03909216825260208201526040908101905180910390a1815460019011611808576000858152601760205260409020600201546018805490919081106117cc57fe5b906000526020600020900160005b506000908190558581526017602052604081208181556001808201839055600290910191909155935061181b565b8154600019018255600182018054821790555b5b5b505050919050565b60185460005b818110156118a357601880548290811061184157fe5b906000526020600020900160005b50541561189a576017600060188381548110151561186957fe5b906000526020600020900160005b505481526020810191909152604001600090812081815560018101829055600201555b5b60010161182b565b610e2160186000611a3a565b5b5050565b60015b6001548110156119e2575b600154811080156118e45750600281601481106118db57fe5b0160005b505415155b156118f1576001016118c2565b5b6001805411801561191657506001546002906014811061190e57fe5b0160005b5054155b1561192a57600180546000190190556118f1565b6001548110801561194f57506001546002906014811061194657fe5b0160005b505415155b801561196b57506002816014811061196357fe5b0160005b5054155b156119dd576001546002906014811061198057fe5b0160005b50546002826014811061199357fe5b0160005b50558060166000600283601481106119ab57fe5b0160005b5054815260200190815260200160002081905550600060026001546014811015156119d657fe5b0160005b50555b6118b7565b5b50565b81548183558181151161066857600083815260209020610668918101908301611a5c565b5b505050565b81548183558181151161066857600083815260209020610668918101908301611a5c565b5b505050565b50805460008255906000526020600020908101906119e29190611a5c565b5b50565b611a7a91905b80821115611a765760008155600101611a62565b5090565b90565b611a7a91905b80821115611a765760008155600101611a62565b5090565b9056009d1c112abc511adeea2c2cb658a5a64a9904abc92ff58d6adebcdc50168fe30ecf90e7411838f85b3224424514415fa6b781437ca93b7a09f12322dbf828c44aa165627a7a723058207aea23bfdbbdd0c153e34d460196ccea8f1ec059ea0b00c3069edbaa21fbb0ce0029\n";

    private ClearoutSellers(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private ClearoutSellers(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public List<WithdrawTokenEventResponse> getWithdrawTokenEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("WithdrawToken", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<WithdrawTokenEventResponse> responses = new ArrayList<WithdrawTokenEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            WithdrawTokenEventResponse typedResponse = new WithdrawTokenEventResponse();
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<WithdrawTokenEventResponse> withdrawTokenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("WithdrawToken", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, WithdrawTokenEventResponse>() {
            @Override
            public WithdrawTokenEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                WithdrawTokenEventResponse typedResponse = new WithdrawTokenEventResponse();
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<SetInitialMeterEventResponse> getSetInitialMeterEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SetInitialMeter", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SetInitialMeterEventResponse> responses = new ArrayList<SetInitialMeterEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SetInitialMeterEventResponse typedResponse = new SetInitialMeterEventResponse();
            typedResponse._peak = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._valley = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._flat = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SetInitialMeterEventResponse> setInitialMeterEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("SetInitialMeter", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SetInitialMeterEventResponse>() {
            @Override
            public SetInitialMeterEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SetInitialMeterEventResponse typedResponse = new SetInitialMeterEventResponse();
                typedResponse._peak = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._valley = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._flat = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public List<PresaleEventResponse> getPresaleEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Presale", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<PresaleEventResponse> responses = new ArrayList<PresaleEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            PresaleEventResponse typedResponse = new PresaleEventResponse();
            typedResponse._electricity = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._type = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<PresaleEventResponse> presaleEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Presale", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, PresaleEventResponse>() {
            @Override
            public PresaleEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                PresaleEventResponse typedResponse = new PresaleEventResponse();
                typedResponse._electricity = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._type = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._meter = (Address) eventValues.getNonIndexedValues().get(2);
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

    public Future<Address> ownMeters(Address param0, Uint256 param1) {
        Function function = new Function("ownMeters", 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<List<Type>> initialMeter(Address param0) {
        Function function = new Function("initialMeter", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> isOwner(Address _addr) {
        Function function = new Function("isOwner", Arrays.<Type>asList(_addr), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> submitPower(Uint256 _power, Uint256 _type, Uint256 _seqMeter, Uint256 _seq, Address _owner) {
        Function function = new Function("submitPower", Arrays.<Type>asList(_power, _type, _seqMeter, _seq, _owner), Collections.<TypeReference<?>>emptyList());
        
        return executeTransactionAsync(function);
    }
    
    public String encodeSubmitPower(Uint256 _power, Uint256 _type, Uint256 _seqMeter, Uint256 _seq, Address _owner) {
    	    Function function = new Function("submitPower", Arrays.<Type>asList(_power, _type, _seqMeter, _seq, _owner), Collections.<TypeReference<?>>emptyList());
    	    String StrSubmit = FunctionEncoder.encode(function);
    	    return StrSubmit;
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
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
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

    public Future<TransactionReceipt> presale(Uint256 _electricity, Uint256 _type, Address _meter, Uint256 _seq, Uint256 _seqMeter, Address _owner) {
        Function function = new Function("presale", Arrays.<Type>asList(_electricity, _type, _meter, _seq, _seqMeter, _owner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> withdrawToken(Uint256 _value, Uint256 _type, Address _meter, Uint256 _seqMeter, Uint256 _seq, Address _owner) {
        Function function = new Function("withdrawToken", Arrays.<Type>asList(_value, _type, _meter, _seqMeter, _seq, _owner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> tokens(Address param0) {
        Function function = new Function("tokens", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> changeOwner(Address _from, Address _to) {
        Function function = new Function("changeOwner", Arrays.<Type>asList(_from, _to), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<ClearoutSellers> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, DynamicArray<Address> _owners, Uint256 _required) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_owners, _required));
        return deployAsync(ClearoutSellers.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<ClearoutSellers> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, DynamicArray<Address> _owners, Uint256 _required) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_owners, _required));
        return deployAsync(ClearoutSellers.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static ClearoutSellers load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClearoutSellers(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ClearoutSellers load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClearoutSellers(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class SubmitPowerEventResponse {
        public Uint256 _power;

        public Uint256 _type;
    }

    public static class WithdrawTokenEventResponse {
        public Uint256 _value;

        public Address _meter;
    }

    public static class SetInitialMeterEventResponse {
        public Uint256 _peak;

        public Uint256 _valley;

        public Uint256 _flat;
    }

    public static class PresaleEventResponse {
        public Uint256 _electricity;

        public Uint256 _type;

        public Address _meter;
    }

    public static class IsMeterOpenedEventResponse {
        public Bool _status;

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
