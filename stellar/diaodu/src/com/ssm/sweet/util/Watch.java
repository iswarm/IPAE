package com.ssm.sweet.util;

import org.glassfish.jersey.media.sse.EventSource;
import org.stellar.sdk.*;
import org.stellar.sdk.requests.EventListener;
import org.stellar.sdk.requests.PaymentsRequestBuilder;
import org.stellar.sdk.responses.operations.OperationResponse;
import org.stellar.sdk.responses.operations.PaymentOperationResponse;

import java.util.concurrent.*;

public class Watch {
	static KeyPair account = null;
	public static String myToken = null;
	static EventSource eventSource;
	static PaymentsRequestBuilder paymentsRequest;
	 final ExecutorService exec = Executors.newFixedThreadPool(1);
	/*public static void main(String[] args) {
		new Watch().test();

	}*/

	public void test(String gkey) {
		
		// TODO Auto-generated method stub
		Network.useTestNetwork();
		Server server = new Server("https://horizon-testnet.stellar.org");
		account = KeyPair
				.fromAccountId(gkey);
		final PaymentsRequestBuilder paymentsRequest = server.payments().forAccount(
				account);
		/*new Thread(new Runnable() {  
	           @Override  
	           public void run() {  
	                System.out.println("111111111111111111111");
	           }  
	       }).start();*/
		
		final Callable<String> call = new Callable<String>() {
		
		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			
			eventSource = paymentsRequest.stream(new EventListener<OperationResponse>() {

				@Override
				public void onEvent(OperationResponse payment) {
					// Record the paging token so we can start from here next time.
					// ��¼��ҳ���ƣ��Ա������´ο��Դ����￪ʼ��
					//(payment.getPagingToken());
					String myevent = payment.getPagingToken();
					System.out.print(myevent);

					// The payments stream includes both sent and received
					// payments.�������������ͺͽ��ո��� We only
					// want to process received payments here.�������ﴦ���յ��ĸ���
					if (payment instanceof PaymentOperationResponse) {
						if (((PaymentOperationResponse) payment).getTo().equals(
								account)) {
							return;
						}

						String amount = ((PaymentOperationResponse) payment)
								.getAmount();

						Asset asset = ((PaymentOperationResponse) payment)
								.getAsset();
						String assetName;
						if (asset.equals(new AssetTypeNative())) {
							assetName = "lumens";
						} else {
							StringBuilder assetNameBuilder = new StringBuilder();
							assetNameBuilder
									.append(((AssetTypeCreditAlphaNum) asset)
											.getCode());
							assetNameBuilder.append(":");
							assetNameBuilder
									.append(((AssetTypeCreditAlphaNum) asset)
											.getIssuer().getAccountId());
							assetName = assetNameBuilder.toString();

						}

						System.out.println("assetName=" + assetName);

						StringBuilder output = new StringBuilder();
						output.append(amount);
						output.append(" ");
						output.append(assetName);
						output.append(" from ");
						output.append(((PaymentOperationResponse) payment)
								.getFrom().getAccountId());
						System.out.println(output.toString());
					}

				}
				
			});
			return "�߳�ִ�����.";
		}
		
		
		 };
		 try {  
	            Future<String> future = exec.submit(call);  
	            String obj = future.get(1000 * 5, TimeUnit.MILLISECONDS); //������ʱʱ����Ϊ 1 ��
	            System.out.println("����ɹ�����:" + obj);  
	        } catch (Exception e) {  
	            System.out.println("����ʧ��.");  
	            e.printStackTrace();  
	        } 
		System.out.println(eventSource.isOpen());

		String lastToken = loadLastPagingToken();
		System.out.println(lastToken);
		if (lastToken != null) {
			paymentsRequest.cursor(lastToken);
		}
	}

	protected static void savePagingToken(String pagingToken) {
		// TODO Auto-generated method stub
		myToken = pagingToken;
		System.out.println(String.format("myToken is %s", myToken));
	}

	private static String loadLastPagingToken() {
		// TODO Auto-generated method stub
		return myToken;
	}

}
