head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TargetTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特殊執行条件取扱停止テーブル・設定対象種別 定数定義インタフェイス(WEB3TradeTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 凌建平(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 特殊執行条件取扱停止テーブル・設定対象種別 定数定義インタフェイス
 *                                                                      
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3TargetTypeDef
{
    /**
     * 1：商品
     */
    public static final String COMMODITY = "1";

    /**
     * 2：市場
     */
    public static final String MARKET = "2";

    /**
     * 3：銘柄
     */
    public static final String PRODUCT = "3";
}
@
