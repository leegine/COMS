head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioSoapConnectDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SOAP接続実施区分定数定義インタフェイス(WEB3AioSoapConnectDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/16 趙林鵬(中訊) 新規作成
Revision History : 2009/09/17 趙林鵬(中訊)【入出金】仕様変更管理台帳ＤＢレイアウトNo.175
*/

package webbroker3.common.define;

/**
 * SOAP接続実施区分定数定義インタフェイス<BR>
 * (会社別FXシステム条件テーブルのSOAP接続実施区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3AioSoapConnectDivDef
{
    /**
     * ０：SOAP接続未実施
     */
    public static final String SOAP_CONNECT_NOT_ENFORCEMENT = "0";

    /**
     * １：SOAP接続実施
     */
    public static final String SOAP_CONNECT_ENFORCEMENT = "1";

    /**
     * ２：振替のみ実施
     */
    public static final String TRANSFER_ENFORCEMENT = "2";
}@
