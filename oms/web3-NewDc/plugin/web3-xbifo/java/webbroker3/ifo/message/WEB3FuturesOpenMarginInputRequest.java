head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物新規建注文入力画面リクエストクラス(WEB3FuturesOpenMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 呉艶飛 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;

/**
 * (株価指数先物新規建注文入力画面リクエスト)<BR>
 * 株価指数先物新規建注文入力画面リクエストクラス<BR>
 * 
 * @@author 呉艶飛
 * @@version 1.0 
 */
public class WEB3FuturesOpenMarginInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_OpenMarginInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201655L;

    /**
     * (銘柄コード)<BR>
     * スリングショッ起動等、銘柄指定の場合<BR>
     */
    public String futProductCode;

    /**
     * (建区分)<BR>
     * 1：買建　@2：売建<BR>
     */
    public String contractType;

    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪<BR>
     * <BR>
     * 銘柄選択画面より遷移時に指定される<BR>
     */
    public String marketCode;

    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     * <BR>
     * 銘柄選択画面より遷移時に指定される<BR>
     */
    public String targetProductCode;

    /**
     * (限月)<BR>
     * YYYYMM形式<BR>
     * <BR>
     * 銘柄選択画面より遷移時に指定される<BR>
     */
    public String delivaryMonth;

    /**
     * @@roseuid 40F7AE0C007D
     */
    public WEB3FuturesOpenMarginInputRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@建区分チェック<BR>
     * 　@１－１）this.建区分がnullの値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00263<BR>
     * 　@１－２）this.建区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”1：買建”<BR>
     * 　@　@　@　@・”2：売建”<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * ２）銘柄設定チェック<BR>
     *　@２－１）全てのリクエスト項目を設定している場合、例外をスローする。<BR>
     *　@　@　@・銘柄コード<BR>
     *　@　@　@・取引市場<BR>
     *　@　@　@・指数種別<BR>
     *　@　@　@・限月<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00830<BR>
     *　@２－２）銘柄選択時に<BR>
     *　@　@取引市場,指数種別,限月の<BR>
     *  　@いずれかの項目が設定されていない場合、例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00830<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A218890278
     */
    public void validate() throws WEB3BaseException
    {
        // (１）建区分チェック
        //１－１）this.建区分がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263, 
                this.getClass().getName() + "validate",
                "建区分がnullである");
        }
        //１－２）this.建区分が以下の値以外の場合例外をスローする。
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType) 
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264, 
                this.getClass().getName() + "validate",
                "建区分が”1：買建”、”2：売建”以外である");
        }
        
        // ２）銘柄設定チェック         
        // ２－１）全てのリクエスト項目を設定している場合、例外をスローする。           
        if((futProductCode!=null)          
            &&(marketCode!=null)           
            &&(targetProductCode!=null)            
            &&(delivaryMonth!=null))           
        {          
            throw new WEB3BusinessLayerException(      
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,     
                this.getClass().getName() + "validate",    
                "入力パラメータチェックエラー。");  
        }          
            
        // ２－２）銘柄選択時に取引市場,指数種別,限月のいずれかの項目が         
        // 設定されていない場合、例外をスローする。         
        if((marketCode==null)
            &&(targetProductCode==null)
            &&(delivaryMonth==null))           
        {          
            return;        
        }          
        else           
        {          
            if((marketCode==null)
                ||(targetProductCode==null)
                ||(delivaryMonth==null))       
            {      
                throw new WEB3BusinessLayerException(  
                    WEB3ErrorCatalog.BUSINESS_ERROR_00830, 
                    this.getClass().getName() + "validate",
                    "入力パラメータチェックエラー。");
            }      
        }          
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE0C009C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginInputResponse(this);
    }
}
@
