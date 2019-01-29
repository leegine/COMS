head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForeignSecDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルの外国証券区分インタフェイス(WEB3ForeignSecDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルの外国証券区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3ForeignSecDivDef 
{

    /**
     * 1（国内証券）
     */
    public static final String DOMESTIC_STOCK = "1";

    /**
     * 2（外国証券）
     */
    public static final String FOREIGN_STOCK = "2";
    
    /**
     * 3（非流通証券）
     */
    public static final String NOT_CURRENT_STOCK = "3";
}
@
