head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CustdyDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルの保管区分インタフェイス(WEB3CustdyDiv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルの保管区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3CustdyDiv 
{

    /**
     * 1（集中保管）
     */
    public static final String INTERNAL_SAVE = "1";

    /**
     * 5（支店保管）
     */
    public static final String BRANCH_SAVE = "5";
    
    /**
     * 8（機@構保管）
     */
    public static final String INSTITUTE_SAVE = "8";
}
@
