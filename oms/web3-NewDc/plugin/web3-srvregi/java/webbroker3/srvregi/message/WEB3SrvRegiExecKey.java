head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用起動キー(WEB3SrvRegiExecKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用起動キー)<BR>
 * サービス利用起動キーデータクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3SrvRegiExecKey extends Message 
{
    
    /**
     * (利用キー種別区分)<BR>
     * 0:第２URL<BR>
     * 1:ハッシュ値<BR>
     * 2:送信パラメータ<BR>
     * 3:送信方法@区分<BR>
     * 4:ハッシュ計算方式区分<BR>
     * 5:ハッシュ計算手順区分<BR>
     * 6:送信パラメータ区分<BR>
     * 7:暗号化顧客コード区分<BR>
     */
    public String keyKindDiv;
    
    /**
     * (利用キーID)
     */
    public String keyId;

    /**
     * (利用キー)
     */
    public String key;
    
    /**
     * (無効区分)<BR>
     * true:無効　@false:有効<BR>
     */
    public boolean invalidDiv;
    
    /**
     * (サービス利用起動キー)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F1ECAA0026
     */
    public WEB3SrvRegiExecKey() 
    {
     
    }
}
@
