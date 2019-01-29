head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenExclusiveAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 専用振込先口座情報(WEB3AccOpenExclusiveAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 柴雙紅 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (専用振込先口座情報)<BR>
 * 専用振込先口座情報メッセージ<BR>
 * <BR>
 * @@author 柴雙紅(中訊)
 * @@version 1.0
 */
public class WEB3AccOpenExclusiveAccountInfo extends Message
{
    /**
     * 専用振込先口座銀行コード<BR>
     * （専用振込先口座銀行コード）<BR>
     */
    public String exclusiveAccountFinancialInstitutionCode;
    
    /**
     * 専用振込先口座残数<BR>
     * (専用振込先口座残数)<BR>
     */
    public String exclusiveAccountNumber;
    
    /**
     * 専用振込先口座警告区分<BR>
     * (専用振込先口座警告区分)<BR>
     * 0：警告なし 1：注意
     */
    public String exclusiveAccountWarningDiv;

}
@
