pragma solidity 0.4.13;

import "Tokens\StandardToken.sol";
import "multiowned.sol";
EdisonToken is multiowned,StandardToken {
    using Math for *;
    /*
     *  Field
     */
    string public constant name = "TAE"; // Thomas Alva Edison
    uint256 public constant issuingAmount = 10 * 10000 * 10000;
    /*
     *  Events
     */
    event Issuance(address indexed owner, uint amount);
    /*
     *  Functions
     */
     // construct
     // input _owners, addresses to have priviledge to sign
     //       _required, uint, the number of the addresses to require 
    EdisonToken(address[] _owners, uint _required) multiowned(_owners,_required){
        totalTokens = 0;
        balances[msg.sender] = 3000 * 10000; // 15% among 20%
        balances[_owners[0]] = 1 * 10000 * 10000; // 50% among 20%
        balances[_owners[1]] = 1600 * 10000; // 8% among 20%
        balances[_owners[2]] = 1200 * 10000; // 6% among 20%
    }
    
    // function: issue some TAE tokens
    // input: _for, address, the account to receive tokens.
    //        _outcomeTokenCount, uint256, the amount of TAE tokens to issue
    issue(address _for, uint256 _outcomeTokenCount) onlymanyowners(sha3(msg.data)) returns (bool){
        require(_outcomeTokenCount + totalTokens <= issuingAmount);
        totalTokens += _outcomeTokenCount;
        balances[_for].safeToAdd(_outcomeTokenCount);
        Issuance(_for,_outcomeTokenCount);
    }

}