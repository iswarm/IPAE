pragma solidity 0.4.13;

import "multiowned.sol";

contract ClearoutBuyers is multiowned {
    // TYPE
    struct _Power {
        uint256  peak;
        uint256  valley;
        uint256 flat;
    }
    
    // Fields
    uint256 public constant numAsset = 12 ; // the total number of power asset: m, m+1, m+2, m+3, ..., m+numAsset -1;
    mapping(address=>_Power) public initialMeter; //  record intial values of kilo-watt meters
    mapping(address=>_Power[12]) public powers; // record kWh number of an address can use for m, m+1, m+2, m+3, ..., month
    mapping(address=>address[])  ownMeters; // one buyer has several meters.
    mapping(address=>bool) public isMeter; // check if an address is a meter.
    mapping(address=>bool) public isMeterOpened; // check if a meter is already opened after being installed.
    
    
    
    // EVENT
    
    event SubmitPower(uint256 _power, uint256 _type);
    event WithdrawPower(uint256 _power, uint256 _type, address _meter, address _owner);
    event IsMeterOpened(bool _status, address _meter);
    event SetIntialMeter(uint256 _peak, uint256 _valley, uint256 _flat, address _meter);
    
    // FUNCTION
    
    // name: construct
    function ClearoutBuyers(address[] _owners, uint _required) multiowned(_owners,_required) {
        
    }

    // name: setMeterOwner
    // function: set the owner of a meter, and register the meter, that is, set isMeter true.
    // input: 1. _meter, address, the ethereum account address of a meter.
    //        2. _seqMeter, refer the sequence of the meter owned by the _owner.
    
    function setMeterOwner(address _meter, address _owner) onlymanyowners(sha3(msg.data)) returns (bool) {
        ownMeters[_owner].push( _meter);
        isMeter[_meter] = true;
        return true;
    }

    // name: submitPower
    // function: kilo-watt meter submit power to clear the POWER recorded so that the buyer can buy more for his meter.
    // input: 1. _power, uint256, amount of some POW asset.
    //        2. _type, uint256, type of POW asset.
    //        3. _seq, the sequence to buy the power asset of type _type, this parameter to determine the location of stote.
    // return: bool, only for debug. 
    
    function submitPower(uint256 _power, uint256 _type, uint256 _seq) returns (bool) {
        require(! isMeter[msg.sender] ) ;
        if (1==_type && _power-powers[msg.sender][_seq%numAsset].peak  >= initialMeter[msg.sender].peak )
        {
           initialMeter[msg.sender].peak += powers[msg.sender][_seq%numAsset].peak;
           powers[msg.sender][_seq%numAsset].peak = 0;
           SubmitPower(_power, _type);
           return true;
        } else if (2==_type &&  _power- powers[msg.sender][_seq%numAsset].valley  >= initialMeter[msg.sender].valley){
            initialMeter[msg.sender].valley += powers[msg.sender][_seq%numAsset].valley;
            powers[msg.sender][_seq%numAsset].valley = 0;
            SubmitPower(_power, _type);
           return true;
        } else if (3 == _type && _power- powers[msg.sender][_seq%numAsset].flat  >= initialMeter[msg.sender].flat){
            initialMeter[msg.sender].flat += powers[msg.sender][_seq%numAsset].flat;
            powers[msg.sender][_seq%numAsset].flat = 0;
            SubmitPower(_power, _type);
           return true;
        }else {
            SubmitPower(0, 0);
            return false;
        }
        
    }
    
    // name: withdrawPower
    // function: The buyer withdraw his POW asset from stellar to the smart contract to record in ethereum
    // input: 1. _power, uint256, amount of some POW asset.
    //        2. _type, uint256, type of POW asset.
    //        3. _meter, the meter's address.
    //        4. _seqMeter, refer the sequence of the meter owned by the _owner.
    //        5. _seq, the sequence to buy the power asset of type _type, this parameter to determine the location of stote.
    //        5. _owner, the user to buy electricity.
    // return: bool, only for debug. 
    
    function withdrawPower(uint256 _power, uint256 _type, address _meter, uint256 _seqMeter, uint256 _seq, address _owner) onlymanyowners(sha3(msg.data)) returns (bool) {
        require (ownMeters[_owner][_seqMeter] != _meter) ;
        if (1==_type && 0==powers[_meter][_seq%numAsset].peak) {
            powers[_meter][_seq%numAsset].peak = _power;
            WithdrawPower(_power,_type,_meter,_owner);
            return true;
        } else if (2 == _type && 0==powers[_meter][_seq%numAsset].valley) {
            powers[_meter][_seq%numAsset].valley = _power;
            WithdrawPower(_power,_type,_meter,_owner);
            return true;
        } else if (3 == _type && 0==powers[_meter][_seq%numAsset].flat) {
            powers[_meter][_seq%numAsset].flat = _power;
            WithdrawPower(_power,_type,_meter,_owner);
            return true;
        } else {
            WithdrawPower(0,0,_meter,_owner);
            return false;
        }
        
    }
    
    // name: setIntialMeter
    // function: the intial value by meter
    // input: 1. _peak , uint 256
    //        2. _valley, uint256
    //        3. _flat, uint256
    
    function setIntialMeter(uint256 _peak, uint256 _valley, uint256 _flat) returns (bool) {
        require(!isMeter[msg.sender]) ;
        initialMeter[msg.sender].peak = _peak;
        initialMeter[msg.sender].valley = _valley;
        initialMeter[msg.sender].flat = _flat;
        SetIntialMeter(_peak,_valley,_flat,msg.sender);
        return true;
    }

    // name: meterOpened
    // function: submit the phsical status of a meter
    // input: 1. _status, bool
    
    function meterOpened(bool _status) returns (bool) {
        require (!isMeter[msg.sender]) ;

        isMeterOpened[msg.sender] = _status;
        IsMeterOpened(_status,msg.sender);
        return true;
    }
    
}