head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsContractReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション建玉照会リクエスト(WEB3OptionsContractReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張威 (中訊) 新規作成
              001: 2004/07/30 李媛　@ (中訊) WEB3_IFO_UT-000086 000089を対応しました。
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数オプション建玉照会リクエスト)<BR>
 * 株価指数オプション建玉照会画面表示リクエストクラス<BR>
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionsContractReferenceRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsContractReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_contractReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111522L;

    /**
     * (銘柄コード)<BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     * 株価指数オプション銘柄コード<BR>
     */
    public String opProductCode;

    /**
     * (決済状態区分)<BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし <BR>
     * 0：決済済<BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     */
    public String settlementState;

    /**
     * (株価指数先物オプションソートキー)<BR>
     * 対象項目：銘柄コード、建年月日、損益、建区分<BR>
     */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
     * (要求ページ番号)<BR>
     * 表示させたいページ位置を指定　@<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;

    /**
     * @@roseuid 40C09E620177
     */
    public WEB3OptionsContractReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@決済状態区分のチェック<BR>
     * 　@　@　@以下の状態以外が存在した場合、例外とする。<BR>
     * 　@　@　@・null(指定なし) <BR>
     * 　@　@　@・0(決済済)<BR>
     *       ・1(未決済)<BR>
     *       ・2(決済中)<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00233<BR>
     * <BR>
     * ２）　@ソートキーのチェック<BR>
     * 　@　@ソートキーの配列の個数分、繰り返してチェックを行う。<BR>
     * 　@　@ソートキー.キー項目に以下の項目名以外が存在した場合、例外とする。
     * 　@　@　@　@・銘柄<BR>
     * 　@　@　@　@・建日<BR>
     * 　@　@　@　@・損益<BR>
     * 　@　@　@　@・建区分<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ３）　@要求ページ番号のチェック<BR>
     * リクエストデータ．要求ページ番号が未指定の場合、<BR>
     * リクエストデータ．要求ページ番号に「１」をセットする。<BR>
     * <BR>
     * ４）　@ページ内表示行数のチェック<BR>
     * リクエストデータ．ページ内表示行数が０、または未指定の場合、<BR>
     * 例外とする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00091<BR>
     * @@throws WEB3BaseException
     * @@roseuid 406BE86200BA
     */
    public void validate() throws WEB3BaseException
    {
        //１）　@決済状態区分のチェック
        log.debug("１）　@決済状態区分のチェック: ENTER");
        //以下の状態以外が存在した場合、例外とする。
        //null(指定なし)
        //0(決済済)
        //1(未決済)
        //2(決済中)
        log.debug("settlementState = " + settlementState);
        if (this.settlementState != WEB3IfoSettlementStateDef.NOT_DESIGNATION 
            && !WEB3IfoSettlementStateDef.SETTLEMENT_END.equals(this.settlementState)
            && !WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED.equals(this.settlementState)
            && !WEB3IfoSettlementStateDef.SETTLING.equals(this.settlementState))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00233,
                getClass().getName() + "validate",
                "決済状態区分がnull(指定なし) 、0(決済済)、1(未決済)、2(決済中)以外である場合がエラー");
        }
        log.debug("１）　@決済状態区分のチェック: END");

        //２）　@ソートキーのチェック
        log.debug("２）　@ソートキーのチェック: ENTER");
        //ソートキーの配列の個数分、繰り返してチェックを行う。
        //ソートキー.キー項目に以下の項目名以外が存在した場合、例外とする。
        //・銘柄
        //・建日
        //・損益
        //・建区分
        if(this.futOpSortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                getClass().getName() + "validate",
                "ソートキーが銘柄、建日、損益、建区分以外の場合エラー");
        }

        int l_intOpSortKeysLength = this.futOpSortKeys.length;
        log.debug("l_intOpSortKeysLength: " + l_intOpSortKeysLength);
        for (int i = 0; i < l_intOpSortKeysLength; i++)
        {
            if (!WEB3IfoKeyItemDef.PRODUCTCODE.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.OPEN_DATE.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.INCOME.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + "validate",
                    "ソートキーが銘柄、建日、損益、建区分以外の場合エラー");
            }
            log.debug("LOOP: END " + (i+1));
        }
        log.debug("２）　@ソートキーのチェック: END");

        //３）　@要求ページ番号のチェック
        log.debug("３）　@要求ページ番号のチェック: ENTER");
        //リクエストデータ．要求ページ番号が未指定の場合、
        //リクエストデータ．要求ページ番号に「１」をセットする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("リクエストデータ．要求ページ番号に「１」をセットする。: ENTER");
            pageIndex = "1";
            log.debug("リクエストデータ．要求ページ番号に「１」をセットする。: END");
        }
        log.debug("３）　@要求ページ番号のチェック: END");

        //４）　@ページ内表示行数のチェック
        log.debug("４）　@ページ内表示行数のチェック: ENTER");
        //リクエストデータ．ページ内表示行数が０、または未指定の場合、
        //例外とする
        if (this.pageSize.equals("0") || this.pageSize.equals(""))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "validate",
                "ページ内表示行数が０、または未指定の場合。");
        }
        log.debug("４）　@ページ内表示行数のチェック: END");
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsContractReferenceResponse(this);
    }
}
@
