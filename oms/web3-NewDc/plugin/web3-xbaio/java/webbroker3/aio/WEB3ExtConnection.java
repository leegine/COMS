head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3ExtConnection.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部接続(WEB3ExtConnection.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 張騰宇 (中訊) 新規作成 モデル1168,1181
Revision History : 2009/09/16 孟亞南 (中訊) 仕様変更 モデル1202,1211
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;

/**
 * (外部接続)<BR>
 * 外部接続システムの共通インターフェース<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3ExtConnection
{
    /**
     * resultCode
     */
    public String RESULT_CODE = "resultCode";
    
    /**
     * connectResult
     */
    public String CONNECT_RESULT = "connectResult";

    /**
     * fx_acc_01
     */
    public String FX_ACC_01 = "fx_acc_01";

    /**
     * fx_acc_10
     */
    public String FX_ACC_10 = "fx_acc_10";

    /**
     * cfd_acc
     */
    public String CFD_ACC = "cfd_acc";

    /**
     * amount
     */
    public String AMOUNT = "amount";
    
    /**
     * 外部接続のシステムへ依頼電文の送付を行う。<BR>
     * <BR>
     * @@param l_message - (電文メッセージ)<BR>
     * 電文メッセージ<BR>
     * @@param l_prefRpcParams - (外部システムSOAPプリファ@レンス（RPC形式）)<BR>
     * 外部システムSOAPプリファ@レンス（RPC形式）<BR>
     * 補助口座<BR>
     * @@throws WEB3BaseException
     */
    public void sendMessage(
        Message l_message,
        SoapConnectPrefRpcParams l_prefRpcParams)
        throws WEB3BaseException;

    /**
     * 外部システムの結果通知から、指定した項目名のValueを取得する。<BR>
     * <BR>
     * @@param l_strName - (項目名)<BR>
     * 電文の項目名<BR>
     */
    public Object getResult(String l_strName);
}
@
