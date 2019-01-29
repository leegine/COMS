head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleConnectorClientFactoryImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SleSendqProcessorServiceクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/06/5 李(FLJ) 新規作成
*/

package webbroker3.slegateway;


import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClient;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientFactory;
/**
 * xbConnectorの標準GlSleConnectorClientを取得するクラス
 */
public class WEB3SleConnectorClientFactoryImpl implements WEB3SleConnectorClientFactory{
	
	/**
	 * コンストラクタ
	 */
	public WEB3SleConnectorClientFactoryImpl(){
		super();
	}
	/**
	 * xConnnectorが提供する標準のGlSleConnectorClientクラスを取得する
	 * @@param ipAddr IPアドレス
	 * @@param port ポート
	 * @@param maxPoolSize プールサイズ
	 * @@return GlSleConnectorClient
	 */
	public GlSleConnectorClient getPoolClient(java.net.InetAddress ipAddr, int port, int maxPoolSize){
		return GlSleConnectorClientFactory.getPoolClient(ipAddr, port, maxPoolSize);	
	}
	
	/**
	 * xConnnectorが提供する標準のGlSleConnectorClientクラスを取得する
	 * @@param ipAddr IPアドレス
	 * @@param port ポート
	 * @@param maxPoolSize プールサイズ
	 * @@param props 設定プロパティ
	 * @@return GlSleConnectorClient
	 * ↑タイムアウト対応するため追加 2006/10/26
	 */
	public GlSleConnectorClient getPoolClient(java.net.InetAddress ipAddr, int port, int maxPoolSize, java.util.Properties props){
		return GlSleConnectorClientFactory.getPoolClient(ipAddr, port, maxPoolSize,props);	
	}

}
@
