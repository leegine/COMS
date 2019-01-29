head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOffFloorProductGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文銘柄選択レスポンス(WEB3EquityOffFloorProductGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 岡村和明(SRA) 新規作成
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （立会外分売銘柄明細）。<BR>
 * <BR>
 * 立会外分売銘柄明細。
 * @@author 岡村和明(SRA) 
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductGroup extends Message
{

    /**
     * （デフォルトコンストラクタ）。
     */
    public WEB3EquityOffFloorProductGroup()
    {
    }

    /**
     * （銘柄コード）。<BR>
     * <BR>
     * 銘柄コード。
     */
    public String productCode;
    /**
     * （銘柄名）。<BR>
     * <BR>
     * 銘柄名。
     */
    public String productName;
    /**
     * （市場コード）。<BR>
     * <BR>
     * 市場コード。
     */
    public String marketCode;
    /**
     * （受付開始日時）。<BR>
     * <BR>
     * 受付開始日時。
     */
    public Date orderStartDatetime;
    /**
     * （受付終了日時）。<BR>
     * <BR>
     * 受付終了日時。
     */
    public Date orderEndDatetime;
    /**
     * （実施日前営業日終値）。<BR>
     * <BR>
     * 実施日前営業日終値。
     */
    public String lastClosingPrice;
    /**
     * （分売価格）。<BR>
     * <BR>
     * 分売価格。
     */
    public String offFloorOrderPrice;
    /**
     * （割引率）。<BR>
     * <BR>
     * 割引率。（％単位）
     */
    public String discountRate;
    /**
     * （申込株数上限）。<BR>
     * <BR>
     * 申込株数上限。<BR>
     * （一人あたりの注文可能株数の上限値）
     */
    public String maxApplyQuantity;
    /**
     * （申込単位）。<BR>
     * <BR>
     * 申込単位。
     */
    public String applyUnit;
    /**
     * （買付可能フラグ）。<BR>
     * <BR>
     * 買付可能フラグ。<BR>
     * （true：買付可能　@false：買付不可）
     */
    public boolean buyPossFlag;
}
@
