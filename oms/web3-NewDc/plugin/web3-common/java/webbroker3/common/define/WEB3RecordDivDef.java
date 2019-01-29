head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RecordDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルのレコード区分インタフェイス(WEB3RecordDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルのレコード区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3RecordDivDef 
{

    /**
     * 1（銘柄変更による入出庫）
     */
    public static final String PRODUCT_CHANGE_SIO = "1";

    /**
     * 2（約定による入出庫）
     */
    public static final String EXECUTE_SIO = "2";
    
    /**
     * 3（単純入出庫）
     */
    public static final String SIO = "3";
}
@
