head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資買付注文確認リクエストクラス(WEB3RuitoBuyConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
                   2004/12/01 韋念瓊 (中訊) 残対応
Revesion History : 2007/10/25 趙林鵬 (中訊) モデルNO.093
*/
package webbroker3.xbruito.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 累積投資買付注文確認リクエストクラス<BR>
 */
public class WEB3RuitoBuyConfirmRequest extends WEB3RuitoCommonRequest
{
	/**
	 * ログ出力ユーティリティ。
	 */    
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoBuyConfirmRequest.class);
			
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_buy_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * 指定方法@<BR>
     * 3：金額、4：口数<BR>
     */
    public String specifyDiv;

    /**
     * (電子鳩チェックフラグ)<BR>
     * 電子鳩チェックフラグ<BR>
     * <BR>
     * true：チェック要<BR>
     * false：チェック不要<BR>
     */
    public boolean batoCheckFlag;
    
    /**
     * (種別コード)<BR>
     * 種別コード<BR>
     */
    public String typeCode;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922CAA03B9
     */
    public WEB3RuitoBuyConfirmRequest()
    {
    }
    

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@this.指定方法@の値が、"金額" "口数"以外の値である場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *       class    : WEB3BusinessLayerException<BR>
     *       tag      : BUSINESS_ERROR_00073<BR>
     * <BR>
     * ２）　@注文数量チェック<BR>
     * 　@２－１）this.注文数量 ＝ null である場合、例外をスローする。<BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00074<BR>
     * 　@２－２）this.注文数量 ≠ 数字 である場合、例外をスローする。<BR>    
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00075<BR>
     * 　@２－３）this.注文数量 ≦ 0　@である場合、例外をスローする。 <BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00076<BR>
     * 　@２－４）this.注文数量 ＞ 11桁 である場合、例外をスローする。<BR>            
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00077<BR>
     * <BR>
     * ３）　@銘柄コードチェック<BR>
     * 　@　@ this.銘柄コードがnullの値であれば例外をスローする。<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4073679802CB
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

        //１）this.指定方法@の値が、"金額" "口数"以外の値である場合、例外をスローする。
        if (!(WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("指定方法@の値が、「金額」「口数」以外の値である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "指定方法@の値が、「金額」「口数」以外の値である。");
        }

        //２）注文数量チェック
        //２－１）this.注文数量 ＝ null である場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.ruitoOrderQuantity))
        {
            log.debug("注文数量が入力されていません。");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "注文数量が入力されていません");

        }
        //２－２）this.注文数量 ≠ 数字 である場合、例外をスローする。
		try
		{
            //this.注文数量 ≠ 数字 である場合、例外をスローする。
			double l_dbTemp = Double.parseDouble(this.ruitoOrderQuantity);
		}
		catch(NumberFormatException l_ex)
		{
            log.debug("注文数量が数字以外の値である。");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "注文数量が数字以外の値である。");
		}

        //２－３）this.注文数量 ≦ 0 である場合、例外をスローする。
        if (Double.valueOf(this.ruitoOrderQuantity).doubleValue() <= 0)
        {            
            log.debug("注文数量が0以下の値である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "注文数量が0以下の値である。");
        }
        
		//２－４）this.注文数量 ＞ 11桁 である場合、例外をスローする。
        if (this.ruitoOrderQuantity.length() > 11)
        {            
            log.debug("注文数量が上限値を超えています。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "注文数量が上限値を超えています。");
        }

        //３）　@銘柄コードチェック 
        //銘柄コードがnullの値であれば例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.ruitoProductCode))
        {
            log.debug("銘柄コードが入力されていません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "銘柄コードが入力されていません。");
        }
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投買付注文確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407297A702FB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoBuyConfirmResponse(this);
    }

}
@
