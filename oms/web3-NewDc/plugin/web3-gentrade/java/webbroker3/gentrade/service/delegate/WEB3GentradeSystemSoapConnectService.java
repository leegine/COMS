head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeSystemSoapConnectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部システムSOAP接続サービス(WEB3GentradeSystemSoapConnectService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 凌建平(中訊) 新規作成
*/
package webbroker3.gentrade.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * 外部システムSOAP接続サービスインターフェイス<BR>
 * <BR>
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3GentradeSystemSoapConnectService extends Service
{
	/**
	 * SOAPメッセージを生成し、メッセージの送信を行う。<BR>
     * <BR>
     * ※Docment/literal方式でのSOAP接続を行う。<BR>
	 * @@param l_lngBranchId - 部店ID
     * @@param l_strConnectDiv - 接続区分
     * @@param l_strParameterlists - パラメータリスト
	 * @@throws WEB3BaseException
	 * @@return Object[]
	 * @@roseuid 421036A8039E
	 */
	public Object[] sendMessage(long l_lngBranchId, String l_strConnectDiv, String[] l_strParameterlists) throws WEB3BaseException;

    /**
     * RPC方式でSOAP接続を行う。<BR>
     * <BR>
     * @@param l_lngBranchId - 部店ID
     * @@param l_strConnectDiv - 接続区分
     * @@param l_strParameterlists - パラメータリスト
     * @@throws WEB3BaseException
     * @@return Object
     * @@roseuid 421036A8039E
     */
    public Object rpcCall(long l_lngBranchId, String l_strConnectDiv, String[] l_strParameterlists) throws WEB3BaseException;
}
@
