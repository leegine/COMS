head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 暗証番号変更顧客情報(WEB3AccInfoPasswordChangeAccountInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (暗証番号変更顧客情報)<BR>
 * 暗証番号変更顧客情報<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0 
 */
public class WEB3AccInfoPasswordChangeAccountInfo extends Message 
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
     * @@roseuid 418F38610167
     */
    public WEB3AccInfoPasswordChangeAccountInfo() 
    {
     
    }
}
@
