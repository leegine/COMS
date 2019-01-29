head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSOrderDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・株式(PTS)注文詳細Unit（WEB3AdminEquityPTSOrderDetailUnit.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 金傑 (中訊) 新規作成モデル186
 Revision History : 2008/02/27 趙林鵬 (中訊) 仕様変更 モデル199
 */
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (管理者・株式（PTS）注文詳細Unit)<BR>
 * 管理者・株式（PTS）注文詳細Unitクラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminEquityPTSOrderDetailUnit extends Message
{
    /**
     * (注文ID)<BR>
     */
    public String orderId;

    /**
     * (部店コード)<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     */
    public String accountCode;

    /**
     * (銘柄コード)<BR>
     */
    public String productCode;

    /**
     * (市場コード)<BR>
     */
    public String marketCode;

    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定　@5：ストックオプション<BR>
     */
    public String taxType;

    /**
     * (商品区分)<BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     */
    public String productDiv;

    /**
     * (取引区分)<BR>
     * 1：　@現物買付注文 <BR>
     * 2：　@現物売付注文 <BR>
     * 3：　@新規買建注文 <BR>
     * 4：　@新規売建注文 <BR>
     * 5：　@買建返済注文 <BR>
     * 6：　@売建返済注文 <BR>
     * 7：　@現引注文 <BR>
     * 8：　@現渡注文 <BR>
     */
    public String tradingType;

    /**
     * (弁済区分)<BR>
     * 1：　@制度信用 <BR>
     * 2：　@一般信用 <BR>
     * <BR>
     * ※信用注文の場合セットする。<BR>
     */
    public String repaymentDiv = null;

    /**
     * (執行条件)<BR>
     * 1：無条件　@3：寄付　@4：引け　@7：不出来引け成行<BR>
     */
    public String execCondType;

    /**
     * (注文有効期限)<BR>
     */
    public Date expirationDate = null;

    /**
     * (値段条件)<BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値 <BR>
     * 7:成行残数取消 <BR>
     */
    public String priceCondType;

    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値 <BR>
     */
    public String orderCondType;

    /**
     * (注文数量)<BR>
     */
    public String orderQuantity;

    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     */
    public String limitPrice = null;

    /**
     * (約定数量)<BR>
     */
    public String execQuantity = null;

    /**
     * (約定単価)<BR>
     */
    public String execPrice = null;

    /**
     * (注文状態区分)<BR>
     * 0：その他 1：受付済（新規注文） 2：発注中（新規注文） 3：発注済（新規注文）<BR>
     * 6：発注失敗（新規注文） 7：受付済（変更注文） 8：発注中（変更注文）<BR>
     * 10：発注済（変更注文） 11：発注失敗（変更注文） 12：受付済（取消注文）<BR>
     * 13：発注中（取消注文） 14：発注済（取消注文） 15：発注失敗（取消注文）<BR>
     * 20：一部失効 21：全部失効 22：無効 23：手動失効 <BR>
     * 24:切替注文 25:切替受付 26:切替完了 27:切替注文(失敗) <BR>
     * 50：繰越済 51：繰越失敗<BR>
     */
    public String orderState;

    /**
     * (約定状態区分)<BR>
     * 0：　@未約定 <BR>
     * 1：　@一部成立 <BR>
     * 2：　@全部成立 <BR>
     */
    public String execType;

    /**
     * (訂正取消区分)<BR>
     * 0：　@初期値 <BR>
     * 1：　@取消中 <BR>
     * 2：　@一部取消完了<BR>
     * 3：　@全部取消完了 <BR>
     * 4：　@取消失敗 <BR>
     * 5：　@訂正中 <BR>
     * 6：　@一部訂正完了<BR>
     * 7：　@全部訂正完了 <BR>
     * 8：　@訂正失敗 <BR>
     * 9：　@エラー <BR>
     * A：　@W指値注文切替中 <BR>
     * B：　@W指値注文一部切替完了<BR>
     * C：　@W指値注文全部切替完了 <BR>
     * D：　@W指値注文切替失敗 <BR>
     */
    public String changeCancelDiv;

    /**
     * (注文時間)<BR>
     */
    public Date orderDate;

    /**
     * (発注日)<BR>
     */
    public Date orderBizDate;

    /**
     * (受渡日)<BR>
     */
    public Date deliveryDate;

    /**
     * @@roseuid 4795A0F902F2
     */
    public WEB3AdminEquityPTSOrderDetailUnit()
    {

    }
}
@
