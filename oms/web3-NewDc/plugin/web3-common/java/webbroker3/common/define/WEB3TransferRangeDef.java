head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransferRangeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振出範囲(WEB3TransferRangeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
Revesion History : 2007/06/06 栄イ(中訊) ＤＢレイアウト(各種連絡テーブル)による
*/

package webbroker3.common.define;

/**
 * 振出範囲 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3TransferRangeDef
{

    /**
     * 1：全口座
     */
    public final static String ALL_ACCOUNT  = "1";

    /**
     * 2：保護預り口座
     */
    public final static String SAFE_CUSTODY_ACCOUNT  = "2";

    /**
     * 3：積立全口座
     */
    public final static String RESERVE_ALL_ACCOUNT  = "3";

    /**
     * 4：積立個別口座又は個別銘柄
     */
    public final static String RESERVE_INDIVIDUAL_ACCOUNT  = "4";

    /**
     * 5：公共債
     */
    public final static String PUBLIC_BOND  = "5";

    /**
     * 6：円貨建外貨を除く国内債
     */
    public final static String EN_EXCEPT_FOREIGN_DOMESTIC_BOND  = "6";

    /**
     * A：売却引出代金
     */
    public final static String SALE_TURNOVER  = "A";

    /**
     * B：売却引出代金２
     */
    public final static String SALE_TURNOVER_2  = "B";

    /**
     * C：売却引出代金３
     */
    public final static String SALE_TURNOVER_3  = "C";

}@
