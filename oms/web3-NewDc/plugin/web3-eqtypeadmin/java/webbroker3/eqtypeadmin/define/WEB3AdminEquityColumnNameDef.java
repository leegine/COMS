head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityColumnNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 設定対象列名定数定義インタフェイス(WEB3AdminEquityColumnNameDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 張少傑(中訊) 新規作成 モデルNo.219
*/

package webbroker3.eqtypeadmin.define;

/**
 * 設定対象列名 定数定義インタフェイス
 * <BR>
 * @@author 張少傑
 * @@version 1.0
 */
public interface WEB3AdminEquityColumnNameDef
{
    /**
     * 基準値（終値）
     */
    public final static String LAST_CLOSING_PRICE = "last_closing_price";

    /**
     * 強制値幅（上限値）
     */
    public final static String HIGH_COMPULSIVE_PRICE_RANGE = "high_compulsive_price_range";

    /**
     * 強制値幅（下限値）
     */
    public final static String LOW_COMPULSIVE_PRICE_RANGE = "low_compulsive_price_range";

    /**
     * 基準値
     */
    public final static String BASE_PRICE = "base_price";

    /**
     * 値幅区分
     */
    public final static String PRICE_RANGE_UNIT_TYPE = "price_range_unit_type";
}
@
