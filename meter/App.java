package yineng.meter;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import eth.ClearoutSellers;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
		int kwhnum = Integer.parseInt(args[0]);
		int ptype = Integer.parseInt(args[1]);
		int _seq = Integer.parseInt(args[2]);
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials("3311", "/root/pi/UTC--2017-08-13T07-05-37Z--7219a7cc-75d0-2507-85cd-866ebd4381f5");
		} catch (IOException | CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClearoutSellers mysellers;
		mysellers=ClearoutSellers.load("0x48cccb7f55056b9115a9212b5a2e9de84a1e94e7", web3j, credentials, BigInteger.valueOf(18000), BigInteger.valueOf(4700000));
		Future<TransactionReceipt> ftr = mysellers.submitPower(new Uint256(kwhnum), new Uint256(ptype), new Uint256(0), new Uint256(_seq), new Address(credentials.getAddress()));
		String subHash = null;
		try {
			subHash = ftr.get().getTransactionHash();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(subHash);
    }
}
