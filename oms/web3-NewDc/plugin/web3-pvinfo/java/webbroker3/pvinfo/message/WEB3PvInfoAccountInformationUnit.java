head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAccountInformationUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 対象顧客情報(WEB3PvInfoAccountInformationUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (対象顧客情報)<BR>
 * 対象顧客情報クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoAccountInformationUnit extends Message 
{
    
    /**
     * (部店コード)<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     */
    public String accountCode;
    
    /**
     * (最終閲覧日時)<BR>
     */
    public Date lastBrowseDate;
}
@
