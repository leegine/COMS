head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・外国株式注文約定照会リクエスト(WEB3AdminORFeqOrderExecutionRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3AdminFeqSortKeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・外国株式注文約定照会リクエスト)<BR>
 * 管理者・外国株式注文約定照会リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefReferenceRequest extends WEB3AdminOROrderExecutionRefCommonRequest
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORFeqOrderExecutionRefReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqOrderExecutionRefReference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode = null;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode = null;
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     */
    public String taxType = null;
    
    /**
     * (運用コード)<BR>
     * 運用コード<BR>
     */
    public String managementCode = null;
    
    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode = null;
    
    /**
     * @@roseuid 42D1C8EA008C
     */
    public WEB3AdminORFeqOrderExecutionRefReferenceRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・外国株式注文約定照会レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF7B460371
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORFeqOrderExecutionRefReferenceResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）スーパークラスのvalidate()をコールする。<BR>
     * <BR>
     * ２）ソートキーチェック<BR>
     * 　@２－１）this.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@２－１－１）ソートキー.キー項目に下記の項目以外が<BR>
     * 　@　@　@設定されていたら、<BR>
     * 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@ 　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@ 　@tag:   BUSINESS_ERROR_00086<BR>
     * 　@　@　@・「注文ID」<BR>
     * 　@　@　@・「部店コード」<BR>
     * 　@　@　@・「顧客コード」<BR>
     * 　@　@　@・「銘柄コード」<BR>
     * 　@　@　@・「市場コード」<BR>
     * 　@　@　@・「口座区分」<BR>
     * 　@　@　@・「取引区分」<BR>
     * 　@　@　@・「決済区分」<BR>
     * 　@　@　@・「注文時間」<BR>
     * 　@　@　@・「発注日」<BR>
     * 　@　@　@・「執行条件」<BR>
     * 　@　@　@・「注文期限」<BR>
     * 　@　@　@・「発注条件」<BR>
     * 　@　@　@・「受渡日」<BR>
     * 　@　@　@・「運用コード」<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A65D0A005F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        

        //１）スーパークラスのvalidate()をコールする。
        super.validate();
        
         //2）ソートキーチェック
        //　@2－１）this.ソートキーの要素数分以下の処理を繰り返す。
        int l_intSorkKeyCount = 0;
        if (super.sortKeys != null)
        {
            l_intSorkKeyCount = super.sortKeys.length;
        }
        for (int i = 0; i < l_intSorkKeyCount; i++)
        {
            //　@　@2－１－１）ソートキー.キー項目に下記の項目以外が
            //　@　@　@設定されていたら、
            //　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。
            String l_strKeyItem = super.sortKeys[i].keyItem;
            if (!WEB3AdminFeqSortKeyDef.ORDER_ID.equals(l_strKeyItem)    //注文ID
                && !WEB3AdminFeqSortKeyDef.BRANCH_CODE.equals(l_strKeyItem)    //部店コード
                && !WEB3AdminFeqSortKeyDef.ACCOUNT_CODE.equals(l_strKeyItem)    //顧客コード
                && !WEB3AdminFeqSortKeyDef.PRODUCT_CODE.equals(l_strKeyItem)    //銘柄コード
                && !WEB3AdminFeqSortKeyDef.MARKET_CODE.equals(l_strKeyItem)    //市場コード
                && !WEB3AdminFeqSortKeyDef.TAX_TYPE.equals(l_strKeyItem)    //口座区分
                && !WEB3AdminFeqSortKeyDef.TRADING_TYPE.equals(l_strKeyItem)    //取引区分
                && !WEB3AdminFeqSortKeyDef.SETTLE_DIV.equals(l_strKeyItem)    //決済区分
                && !WEB3AdminFeqSortKeyDef.ORDER_START_DATE.equals(l_strKeyItem)    //注文時間
                && !WEB3AdminFeqSortKeyDef.ORDER_BIZ_DATE.equals(l_strKeyItem)    //発注日
                && !WEB3AdminFeqSortKeyDef.EXECCOND_TYPE.equals(l_strKeyItem)    //執行条件
                && !WEB3AdminFeqSortKeyDef.EXPIRATION_DATE_TYPE.equals(l_strKeyItem)    //注文期限
                && !WEB3AdminFeqSortKeyDef.ORDER_COND_TYPE.equals(l_strKeyItem)    //発注条件
                && !WEB3AdminFeqSortKeyDef.DELIVERY_DATE.equals(l_strKeyItem)    //受渡日
                && !WEB3AdminFeqSortKeyDef.MANAGEMENT_CODE.equals(l_strKeyItem))    //運用コード
            {
                String l_strMessage = "ソートキーのキー項目の値が存在しないコード値です。「" + l_strKeyItem + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
     
        log.exiting(STR_METHOD_NAME);
    }
}
@
