head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductTradingStatusUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品別取扱状況(WEB3AdminTMProductTradingStatusUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 車進　@  (中訊) モデルNo.110  
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.define.WEB3AdminTMProductRegistDivDef;

/**
 * （商品別取扱状況）<BR>
 * <BR>
 * 商品別取扱状況クラス<BR>
 * <BR>
 * WEB3AdminTMProductTradingStatusUnit<BR>
 * <BR>
 * WEB3AdminTMProductTradingStatusUnit class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMProductTradingStatusUnit extends Message
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMProductTradingStatusUnit.class);
    /**
     * （注文受付商品）<BR>
     * 注文受付商品<BR>
     * <BR>
     * 注文受付ステイタステーブル.注文受付商品の値<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * orderProduct<BR>
     * <BR>
     * order_accept_status.orderAcceptProduct<BR>
     */
    public String orderProduct;

    /**
     * （注文受付トランザクション）<BR>
     * 注文受付トランザクション<BR>
     * <BR>
     * 注文受付ステイタステーブル.注文受付トランザクションの値<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * order receipt transaction<BR>
     * <BR>
     * order_accept_status.orderAcceptTransaction<BR>
     */
    public String orderProductTran;

    /**
     * ※The latest DB data is set in the AP layer.<BR>
     * <BR>
     * （登録区分）<BR>
     * <BR>
     * 0：　@取扱可能<BR>
     * 1：　@バッチ処理中<BR>
     * 2：　@停止<BR>
     * <BR>
     * ※AP層で最新DBデータをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * （productRegistDiv）<BR>
     * <BR>
     * 0：　@Def.NORMAL<BR>
     * 1：　@Def.BATCH<BR>
     * 2：　@Def.SCRAM<BR>
     * <BR>
     */
    public String productRegistDiv;

    /**
     * （変更後登録区分）<BR>
     * 変更後の登録区分<BR>
     * <BR>
     * 0：　@取扱可能<BR>
     * 1：　@バッチ処理中<BR>
     * 2：　@停止<BR>
     * <BR>
     * ※PR層での入力値をセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * （afterProductRegistDiv）<BR>
     * <BR>
     * 0：　@Def.NORMAL<BR>
     * 1：　@Def.BATCH<BR>
     * 2：　@Def.SCRAM<BR>
     * <BR>
     * ※The input value in PR layer is set. <BR>
     */
    public String afterProductRegistDiv = null;

    /**
     * （コンストラクタ）<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * コンストラクタ<BR>
     * @@roseuid 41735C96012D
     */
    public WEB3AdminTMProductTradingStatusUnit()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）注文受付商品チェック<BR>
     * 　@１－１）this.注文受付商品 == nullの場合、<BR>
     * 　@　@　@　@　@「注文受付商品がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01424<BR>
     * <BR>
     * ２）注文受付トランザクションチェック<BR>
     * 　@２－１）this.注文受付トランザクション == nullの場合、<BR>
     * 　@　@　@　@　@「注文受付トランザクションがnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01425<BR>
     * <BR>
     * ３）登録区分チェック<BR>
     * 　@this.登録区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.登録区分が以下の値のいづれにも該当しない場合、<BR>
     * 　@　@　@　@　@「登録区分エラー(未定義の値)」の例外をスローする。<BR>
     * 0：　@取扱可能<BR>
     * 1：　@バッチ処理中<BR>
     * 2：　@停止<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00841<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)orderProduct check<BR>
     *   1-1)If this.orderProduct == null<BR>
     *            Throw the following error [orderProduct is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01424<BR>
     * <BR>
     * 2)orderProductTran check<BR>
     *   2-1)If orderProductTran == null<BR>
     *            Throw the following error [orderProductTran is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01425<BR>
     * <BR>
     * 3)productRegistDiv check<BR>
     *   3-1)If this.productRegistDiv != null<BR>
     *     3-1-1)If this.productRegistDiv = 0 : Def.NORMAL  or<BR>
     *                  this.productRegistDiv = 1 : Def.BATCH  or<BR>
     *                  this.productRegistDiv = 2 : Def.SCRAM<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00841<BR>
     * @@roseuid 417383D000D7
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1if orderProduct = null, throw Exception.
        if (this.orderProduct == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01424,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1 orderProductTran = null, throw Exception.
        if (this.orderProductTran == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01425,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 3-1productRegistDiv != null, throw Exception.
        if (this.productRegistDiv != null)
        {
            // 3-1-1 if productRegistDiv != NORMAL, BATCH, SCRAM, throw Exception.
            if ((!WEB3AdminTMProductRegistDivDef.NORMAL.equals(this.productRegistDiv))
                && (!WEB3AdminTMProductRegistDivDef.BATCH.equals(this.productRegistDiv))
                && (!WEB3AdminTMProductRegistDivDef.SCRAM.equals(this.productRegistDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
