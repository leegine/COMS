head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.20.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SecuredLoanAccOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保ローン口座開設区分定数定義(WEB3SecuredLoanAccOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 周墨洋 (中訊) 新規作成
*/

package webbroker3.login.define;


/**
 * (担保ローン口座開設区分定数定義)<BR>
 * 担保ローン口座開設区分定数定義<BR>
 * <BR>
 * @@author  周墨洋(中訊)
 * @@version 1.0
 */
public interface WEB3SecuredLoanAccOpenDivDef
{

    /**
     * 0:口座なし
     */
   public static final String SECURED_LOAN_ACCOUNT_NOT_OPEN = "0";

   /**
    *  1:口座開設
    */
   public static final String SECURED_LOAN_ACCOUNT_OPEN  = "1";

}
@
