head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleConnectorClientFactory.java;


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

/**
 * GlSleConnectorClientFactoryラップしたインタフェース
 */
public interface WEB3SleConnectorClientFactory {
	/**
	 * 全てのSleコネクタに利用できるクライアントを取得します
	 * @@param ipAddr　@IPアドレス
	 * @@param port　@ポート番号
	 * @@param maxPoolSize　@最大プールサイズ
	 * @@return Sleコネクタに利用するクライアント
	 */
	public GlSleConnectorClient getPoolClient(java.net.InetAddress ipAddr, int port, int maxPoolSize); 
	
	/**
	 * xConnnectorが提供する標準のGlSleConnectorClientクラスを取得する
	 * @@param ipAddr IPアドレス
	 * @@param port ポート
	 * @@param maxPoolSize プールサイズ
	 * @@param props 設定プロパティ
	 * @@return GlSleConnectorClient
	 * ↑タイムアウト対応するため追加 2006/10/26
	 */
	public GlSleConnectorClient getPoolClient(java.net.InetAddress ipAddr, int port, int maxPoolSize, java.util.Properties props);
}
@
