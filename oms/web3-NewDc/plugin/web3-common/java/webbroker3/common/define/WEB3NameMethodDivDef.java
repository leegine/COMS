head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3NameMethodDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルの名義・仕法@区分インタフェイス(WEB3NameMethodDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルの名義・仕法@区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3NameMethodDivDef 
{

    /**
     * 1（株式:単位本人）
     */
    public static final String COMPANY_SELF = "1";

    /**
     * 3（株式:単位他人）
     */
    public static final String COMPANY_OTHER = "3";
    
    /**
     * 4（株式:単位書換）
     */
    public static final String COMPANY_TRANSFER = "4";

    /**
     * 5（株式:非単位本人）
     */
    public static final String NOT_COMPANY_SELF = "5";

    /**
     * 7（株式:非単位他人）
     */
    public static final String NOT_COMPANY_OTHER = "7";
    
    /**
     * 8（株式:非単位書換）
     */
    public static final String NOT_COMPANY_TRANSFER = "8";

    /**
     * 1（投信:本券）
     */
    public static final String ISSUE_TICKET = "1";

    /**
     * 4（投信:マル優）
     */
    public static final String MARU_SECURITIES = "4";

    /**
     * 1（債券:本券）
     */
    public static final String BOND_ISSUE_TICKET = "1";

    /**
     * 2（債券:登録）
     */
    public static final String BOND_LOGIN = "2";

    /**
     * 3（債券:振決）
     */
    public static final String BOND_CONFIRMED = "3";

    /**
     * 4（債券:マル優）
     */
    public static final String BOND_MARU_SECURITIES = "4";

    /**
     * 6（債券:特優）
     */
    public static final String BOND_TOKU_SECURITIES = "6";
}
@
