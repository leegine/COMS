head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄明細(WEB3AdminOffFloorProductGroup.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

/**
 * (管理者立会外分売銘柄明細)<BR>
 * <BR>
 * 管理者立会外分売銘柄明細。<BR>
 * <BR>
 * WEB3AdminOffFloorProductGroup<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorProductGroup extends Message
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductRegistInfoUnit.class);
    /**
     * (立会外分売銘柄キー)<BR>
     * <BR>
     * 立会外分売銘柄キー。<BR>
     * <BR>
     * productKey<BR>
     * <BR>
     */
    public WEB3AdminOffFloorProductKey productKey;

    /**
     * (銘柄名)<BR>
     * <BR>
     * 銘柄名。<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * (受付開始日時)<BR>
     * <BR>
     * 受付開始日時。<BR>
     * <BR>
     * orderStartDatetime<BR>
     * <BR>
     */
    public Date orderStartDatetime;

    /**
     * (分売価格)<BR>
     * <BR>
     * 分売価格。<BR>
     * <BR>
     * offFloorOrderPrice<BR>
     * <BR>
     */
    public String offFloorOrderPrice;

    /**
     * (申込株数上限)<BR>
     * <BR>
     * 申込株数上限。<BR>
     * （一人あたりの注文可能株数の上限値）<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * maxApplyQuantity<BR>
     * （maximum value of applyQuantity per person）<BR>
     * <BR>
     */
    public String maxApplyQuantity;

    /**
     * (登録日時)<BR>
     * <BR>
     * 登録日時。<BR>
     * <BR>
     * registDatetime<BR>
     * <BR>
     */
    public Date registDatetime;

    /**
     * (申込株数合計)<BR>
     * <BR>
     * 申込株数合計。<BR>
     * （当該立会外分売への、申込株数のグロス値）<BR>
     * <BR>
     * -----<English>---------<BR>
     * <BR>
     * totalApplyQuantity<BR>
     * （a gross value of apply quantity about the corresponding off floor）<BR>
     * <BR>
     */
    public String totalApplyQuantity;

    /**
     * (約定株数合計)<BR>
     * <BR>
     * 約定株数合計。<BR>
     * （当該立会外分売への、申込に対する約定数量のグロス値）<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * totalExecuteQuantity<BR>
     * （a gross value of execution quantity to apply quantity about the corresponding
     * off floor）<BR>
     * <BR>
     */
    public String totalExecuteQuantity;

    /**
     * (更新削除可能フラグ)<BR>
     * <BR>
     * 更新、削除が可能かどうかのフラグ。<BR>
     * （当該立会外分売の受付開始前：　@true<BR>
     * 　@当該立会外分売の受付中：　@登録未完のデータはtrue、登録完了のデータはfalse<BR>
     * 　@当該立会外分売の受付終了後：　@false）<BR>
     * <BR>
     * -----<English>-----------------<BR>
     * <BR>
     * Flag if it is able to update and delete<BR>
     * （Before starting ordering for corresponding off floor: true<BR>
     * 　@While ordering the corresponding off floor: true for not registered data,
     * false for registered data<BR>
     * 　@After starting ordering for corresponding off floor: false）<BR>
     * <BR>
     */
    public boolean changeDeletePossFlag;

    /**
     * @@roseuid 421AE323037D
     */
    public WEB3AdminOffFloorProductGroup()
    {

    }
}
@
