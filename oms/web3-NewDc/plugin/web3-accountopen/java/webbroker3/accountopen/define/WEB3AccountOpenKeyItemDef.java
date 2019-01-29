head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccountOpenKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目 定数定義インタフェイス(WEB3AccountOpenKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 郭英 (中訊) 新規作成
                   2006/07/13 周捷 (中訊) 仕様変更 モデル078
*/

package webbroker3.accountopen.define;

/**
 * キー項目 定数定義インタフェイス
 * 
 * @@author 郭英(中訊)
 * @@version 1.0
 */
public interface WEB3AccountOpenKeyItemDef
{
    /**
     * branchCode：　@口座開設状況.部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * accountCode：　@口座開設状況.顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * requestNumber：　@口座開設状況.識別コード
     */
    public static final String REQUEST_NUMBER = "requestNumber";

    /**
     * infoClaimDate：　@口座開設状況.資料請求日  
     */
    public static final String INFO_CLAIM_DATE = "infoClaimDate";
    
    /**
     * accountOpenDate：　@口座開設状況.口座開設日 
     */
    public static final String ACCOUNT_OPEN_DATE = "accountOpenDate";
}




@
