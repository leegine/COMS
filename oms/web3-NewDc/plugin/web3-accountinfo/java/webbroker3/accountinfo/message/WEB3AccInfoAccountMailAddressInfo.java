head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountMailAddressInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客メールアドレス情報(WEB3AccInfoAccountMailAddressInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (顧客メールアドレス情報)<BR>
 * 顧客メールアドレス情報<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoAccountMailAddressInfo extends Message 
{
    
    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;
    
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
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;
    
    /**
     * (更新日)<BR>
     * 更新日<BR>
     */
    public Date updateDate;
    
    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;
    
    /**
     * (送信フラグ)<BR>
     * 送信フラグ<BR>
     */
    public String sendFlag;
    
    /**
     * @@roseuid 418F386201B5
     */
    public WEB3AccInfoAccountMailAddressInfo() 
    {
     
    }
}
@
