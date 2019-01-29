head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付注文確認リクエストクラス(WEB3MstkSellConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李海波 (中訊) 新規作成
                   2004/12/13 岡村和明(SRA) 残案件対応 Ｎｏ.４０７
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式ミニ投資売付注文確認リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資売付注文確認リクエストクラス
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellConfirmRequest.class);
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mstk_sellConfirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * (銘柄コード)
     */
    public String productCode;
    
    /**
     * (注文株数)
     */
    public String orderQuantity;
    
    /**
     * @@roseuid 4167B05001BD
     */
    public WEB3MstkSellConfirmRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@　@銘柄コードチェック<BR>
     * 　@　@　@　@this.銘柄コードが、以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * <BR>
     * 　@　@　@・this.銘柄コード＝null(「銘柄コードがnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * ２）　@　@注文株数チェック<BR>
     * 　@　@　@　@this.注文株数が以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * <BR>
     * 　@　@　@・this.注文株数＝null(「注文株数がnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * 　@　@　@・this.注文株数≠数字(「注文株数が数字以外」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00901<BR>
     * <BR>
     * 　@　@　@・this.注文株数≦０(「注文株数が0以下」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00902<BR>
     * <BR>
     * 　@　@　@・this.注文株数＞8byte(「注文株数が8byte以上」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00903<BR>
     * <BR>
     * @@roseuid 4111BC730203
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@　@銘柄コードチェック
        log.debug("銘柄コードチェック");
        if(productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        log.debug("銘柄コードチェック");
        //２）　@　@注文株数チェック
        if(orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(!WEB3StringTypeUtility.isNumber(orderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblOrderQuantity = Double.parseDouble(orderQuantity);
        if(l_dblOrderQuantity <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00902, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(WEB3StringTypeUtility.getNubmerLength(orderQuantity) > 8)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00903, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4167B05001D1
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkSellConfirmResponse(this);
    }
}
@
