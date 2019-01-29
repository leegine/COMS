head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式現地手数料登録共通リクエスト(WEB3AdminFeqForeignCostRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/03 鄭海良(中訊) レビュー       
Revesion History : 2008/11/12 劉仁和 (中訊) モデルNo.493
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqCostDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式現地手数料登録共通リクエスト)<BR>
 * 管理者外国株式現地手数料登録共通リクエストクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistCommonRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostRegistCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_foreignCostRegistCommon";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;
    
    /**
     * (コスト区分)<BR>
     * コスト区分<BR>
     * <BR>
     * 01：現地手数料<BR>
     * 02：現地取引税<BR>
     * 03：その他現地コスト１<BR>
     * 04：その他現地コスト２
     */
    public String costDiv;
    
    /**
     * (現地手数料情報一覧)<BR>
     * 現地手数料情報の配列
     */
    public WEB3AdminFeqForeignCostUnit[] feqLocalFeeUnit;

    /**
     * (売買区分)<BR>
     * 売買区分<BR>
     * <BR>
     * １：買い<BR>
     * ２：売り
     */
    public String dealingType;

    /**
     * @@roseuid 42CE39FF005D
     */
    public WEB3AdminFeqForeignCostRegistCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）市場コード<BR>
     * <BR>
     *    this.市場コード == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00443<BR>
     * <BR>
     * ２）コスト区分<BR>
     * <BR>
     *    this.コスト区分 != （”現地手数料” or ”現地取引税” or <BR>
     * ”その他現地コスト１” or ”その他現地コスト２”）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02030<BR>
     * <BR>
     * ３）売買区分<BR>
     * <BR>
     * 　@this.売買区分 != （”買い” or ”売り”）<BR>
     * 　@　@の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_01403<BR>
     * <BR>
     * ４）現地手数料情報一覧<BR>
     * <BR>
     *    this.現地手数料情報一覧 の各要素にて、要素.validate()メソッドをコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B0E7900077
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）市場コード
        //this.市場コード == nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(marketCode)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + STR_METHOD_NAME,
                " 市場コード整合性をチェック"); 
        }
        
        //２）コスト区分 
        //this.コスト区分 != （”現地手数料” or ”現地取引税” or
        //”その他現地コスト１” or ”その他現地コスト２”）の場合、例外をスローする。
        if (!WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(costDiv)
            && !WEB3FeqCostDivDef.FOREIGN_TAX.equals(costDiv)
            && !WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(costDiv)
            && !WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(costDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02030,
                this.getClass().getName() + STR_METHOD_NAME,
                " コスト区分整合性をチェック"); 
        }

        //３）売買区分
        //this.売買区分 != （”買い” or ”売り”）の場合、例外をスローする。
        if (!WEB3BuySellTypeDef.BUY.equals(this.dealingType)
            && !WEB3BuySellTypeDef.SELL.equals(this.dealingType))
        {
            log.debug("売買区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "売買区分の値が存在しないコード値です。");
        }

        //４）現地手数料情報一覧
        //this.現地手数料情報一覧 の各要素にて、
        //要素.validate()メソッドをコールする。
        for (int i = 0; i < feqLocalFeeUnit.length; i++) 
        {
            feqLocalFeeUnit[i].validate();
        }
        
        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
