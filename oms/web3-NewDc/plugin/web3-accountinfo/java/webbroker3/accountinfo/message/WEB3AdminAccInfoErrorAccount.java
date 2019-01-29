head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoErrorAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報エラー顧客(WEB3AdminAccInfoErrorAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  艾興(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (管理者お客様情報エラー顧客)<BR>
 * 管理者お客様情報エラー顧客<BR>
 * @@author 艾興
 * @@version 1.0 
 */
public class WEB3AdminAccInfoErrorAccount extends Message
{
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3AdminAccInfoErrorAccount() {}
}
@
