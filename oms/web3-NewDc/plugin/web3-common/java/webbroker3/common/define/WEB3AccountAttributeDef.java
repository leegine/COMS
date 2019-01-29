head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountAttributeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客属性定数定義インタフェイス(WEB3AccountAttributeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/10 陸文静 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客属性定数定義インタフェイス<BR>
 * (入金請求管理テーブルの顧客属性の參照)<BR>
 * <BR>
 * @@author 陸文静 (中訊)
 * @@version 1.0
 */
public interface WEB3AccountAttributeDef
{
    /**
     * 0.現物（前金制）
     */
    public static final String EQUITY_NOT_ASSET_EVAL = "0";

    /**
     * 1.現物（預り証券評価制）
     */
    public static final String EQUITY_ASSET_EVAL = "1";

    /**
     * 2.信用
     */
    public static final String MARGIN = "2";
}@
