head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・投信累投注文約定照会リクエスト(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/23 周捷 (中訊) 仕様変更・モデル080
Revesion History : 2007/02/26 徐大方(中訊)仕様変更モデルNo.094
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・投信累投注文約定照会リクエスト)<BR>
 * <BR>
 * 管理者・投信累投注文約定照会リクエストクラス<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest
    extends WEB3AdminOROrderExecutionRefCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_mutual_ruito_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * 銘柄ID<BR>
     * <BR>
     * productId<BR>
     * <BR>
     */
    public String productId = null;

    /**
     * (口座区分)<BR>
     * <BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     * 2：　@その他<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * taxType<BR>
     * 0: Def.NORMAL<BR>
     * 1: Def.SPECIAL<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (受渡方法@)<BR>
     * <BR>
     * 受渡方法@<BR>
     * <BR>
     * 1：　@銀行振込<BR>
     * 2：　@証券口座入力<BR>
     * 3：　@無関係<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * deliveryDiv<BR>
     * 1: Def.BANK_TRANSFER<BR>
     * 2: Def.SECURITIES_ACCOUNT_INPUT_SELL<BR>
     * 3: Def.IRRELEVENT_BUY<BR>
     * <BR>
     */
    public String deliveryDiv = null;

    /**
     * (投信・外貨MMF表示区分)<BR>
     * 投信･外貨MMF表示区分<BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分<BR>
     * <BR>
     * 0:投信のみ<BR>
     * 1:外貨MMFのみ<BR>
     * 2:両方<BR>
     * <BR>
     * ※nullの場合、「2:両方」とする<BR>
     */
    public String mutualFrgnMmfDisplayDiv = null;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest.class);

    /**
     * @@roseuid 4212FC1C0259
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）スーパークラスのvalidate()をコールする。<BR>
     * <BR>
     * ２）口座区分チェック<BR>
     * 　@this.口座区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.口座区分が以下の値以外の場合、<BR>
     * 　@　@　@　@「口座区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@・一般<BR>
     *             ・特定<BR>
     *             　@・"その他"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00605<BR>
     * <BR>
     * ３）受渡方法@チェック<BR>
     * 　@this.受渡方法@ != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.受渡方法@が以下の値以外の場合、<BR>
     * 　@　@　@　@「受渡方法@が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@・銀行振込<BR>
     * 　@　@　@　@　@・ 証券口座入力<BR>
     * 　@　@　@　@　@・無関係<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00099<BR>
     * <BR>
     * ４）ソートキーチェック<BR>
     * 　@４－１）this.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@４－１－１）ソートキー.キー項目に下記の項目以外が<BR>
     * 　@　@　@設定されていたら、<BR>
     * 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・「部店コード」<BR>
     * 　@　@　@・「顧客コード」<BR>
     * 　@　@　@・「銘柄コード」<BR>
     * 　@　@　@・「取引区分」<BR>
     * 　@　@　@・「注文時間」<BR>
     * 　@　@　@・「発注日」<BR>
     * 　@　@　@・「受渡日」<BR>
     * 　@　@　@・「扱者コード(SONAR)」<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * 1)Call super.validate()<BR>
     * <BR>
     * 2)taxType check<BR>
     * 　@Check the followings if this.taxType!= null<BR>
     * 　@2-1)If this.taxType contains values other than the following values<BR>
     * 　@　@　@　@Throw the exception "taxType has an undefined value"<BR>
     * 　@　@　@　@・Def.NORMAL<BR>
     *          ・Def.SPECIAL<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00605<BR>
     * <BR>
     * 3)deliveryDiv check<BR>
     * 　@Check the followings if this.deliveryDiv != nullBR>
     * 　@　@3－１)If this.deliveryDiv contains values other than the followings<BR>
     * 　@　@　@　@Throw the exception "deliveryDiv has an undefined value"<BR>
     * 　@　@　@　@ ・Def.BANK_TRANSFER<BR>
     * 　@        ・Def.SECURITIES_ACCOUNT_INPUT_SELL<BR>
     *           ・Def.IRRELEVENT_BUY<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00099<BR>
     * <BR>
     * 4)sortKeys check<BR>
     * 　@4-1)Loop for as many times as the number of elements in this.sortKeys<BR>
     * 　@　@4-1-1)If sortKeys.keyItem contains values other than the followings,<BR>
     * 　@　@　@Throw the exception "sortKeys.keyItem has an undefined value"<BR>
     * 　@　@　@・branchCode<BR>
     * 　@　@　@・accountCode<BR>
     * 　@　@　@・productCode<BR>
     * 　@　@　@・tradingDiv<BR>
     * 　@　@　@・orderDate<BR>
     * 　@　@　@・orderBizDate<BR>
     * 　@　@　@・deliveryDate<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     *  @@roseuid 41ADC95F0163
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_intsortKeysLength = 0;

        // 1-1 call super.validate()
        super.validate();

        // 2-1 If taxType is not any of Df files, throw Exception
        if (this.taxType != null)
        {
            if ((!WEB3MFAccountDivDef.NORMAL.equals(this.taxType))
                && (!WEB3MFAccountDivDef.SPECIAL.equals(this.taxType))
                && (!WEB3MFAccountDivDef.OTHER.equals(this.taxType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if deliveryDiv is not any of Def values, throw Exception
        if (this.deliveryDiv != null)
        {
            if ((!WEB3DeliveryMethodDef.BANK_TRANSFER.equals(deliveryDiv))
                && (!WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL.equals(deliveryDiv))
                && (!WEB3DeliveryMethodDef.IRRELEVENT_BUY.equals(deliveryDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00099,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if sortKeys[i].keyItem is not any of Def values, throw Exception.
        l_intsortKeysLength = sortKeys.length;
        for (int i = 0; i < l_intsortKeysLength; i++)
        {
            if ((!WEB3AdminKeyItemDef.BRANCH_CODE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.PRODUCT_CODE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.TRADING_TYPE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_DATE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_BIZ_DATE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.DELIVERY_DATE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.SONAR_TRADER_CODE.equals(sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse(this);
    }
}@
