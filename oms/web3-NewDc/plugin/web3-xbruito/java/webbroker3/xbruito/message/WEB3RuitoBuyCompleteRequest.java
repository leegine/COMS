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
filename	WEB3RuitoBuyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資買付注文完了リクエストクラス(WEB3RuitoBuyCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
                   2004/12/03 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * 累積投資買付注文完了リクエストクラス<BR>
 */
public class WEB3RuitoBuyCompleteRequest extends WEB3RuitoCommonRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_buy_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200400031539L;    
    
	/**
	 * ログ出力ユーティリティ。
	 */    
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoBuyCompleteRequest.class);
		
    /**
     * 確認時発注日<BR>
     * <BR>
     * 確認レスポンスの処理で使用した値を格納する。<BR>
     */
    public Date checkDate;

    /**
     * 暗証番号<BR>
     */
    public String password;

    /**
     * 指定方法@<BR>
     * 3：金額、4：口数<BR>
     */
    public String specifyDiv;

    /**
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922CC0033C
     */
    public WEB3RuitoBuyCompleteRequest()
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
     * 　@２－３）this.注文数量 ≦ 0 である場合、例外をスローする。<BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00076<BR>
     * 　@２－４）this.注文数量 ＞ 11桁 である場合、例外をスローする。<BR>
     *             class    : WEB3BusinessLayerException<BR>
     *             tag      : BUSINESS_ERROR_00077<BR>
     * <BR>
     * ３）　@確認時発注日チェック<BR>
     * 　@　@ this.確認時発注日がnullの値である場合、例外をスローする。<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00078<BR>
     * <BR>
     * ４）　@銘柄コードチェック<BR>
     * 　@　@ this.銘柄コードがnullの値であれば例外をスローする。<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     * ５）　@注文IDチェック <BR>
     *      this.注文ID ＝ nullである場合、例外をスローする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407367B30088
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

        //this.指定方法@の値が、"金額" "口数"以外の値である場合、例外をスローする
        if (!(WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("this.指定方法@の値が、'金額' '口数'以外の値である場合、例外をスローする");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@の値が、'金額' '口数'以外の値である場合");
        }

        //２） 注文数量チェック
        //２－１）this.注文数量 ＝ null である場合例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.ruitoOrderQuantity))
        {
            log.debug("注文数量が入力されていません。");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "注文数量が入力されていません。");

        }
        //２－２）this.注文数量 ≠ 数字 である場合、例外をスローする。
		try
	    {
	 		double l_dblTemp = Double.parseDouble(this.ruitoOrderQuantity);
	    }
	    catch (NumberFormatException l_ex)
	    {
            log.debug("this.注文数量 ≠ 数字 である場合、例外をスローする。");
		    throw new WEB3BusinessLayerException(
				   WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                    getClass().getName() + "." + STR_METHOD_NAME, 
                    "注文数量 ≠ 数字 である場合");
	    }        

		//２－３）this.注文数量 ≦ 0 である場合、例外をスローする。
	    if (Double.valueOf(this.ruitoOrderQuantity).doubleValue() <= 0)
        {            
            log.debug("this.注文数量 ≦ 0 である場合、例外をスローする。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "注文数量 ≦ 0 である場合");
        }

		//２－４）this.注文数量 ＞ 11桁 である場合、例外をスローする。
        if (this.ruitoOrderQuantity.length() > 11)
        {          
            log.debug("this.注文数量 ＞ 11桁 である場合、例外をスローする。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "注文数量 ＞ 11桁 である場合");
        }

        //３）　@確認時発注日チェック 
        //確認時発注日がnullの値である場合、例外をスローする
        if (this.checkDate == null)
        {
            log.debug("確認時発注日が入力されていません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "確認時発注日が入力されていません。");
        }
        
        //４）　@銘柄コードチェック 
        //this.銘柄コード ＝ nullである場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.ruitoProductCode))
        {
            log.debug("銘柄コードが入力されていません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "銘柄コードが入力されていません。");
        }
        //５）　@注文IDチェック
        //this.注文ID ＝ nullである場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            log.debug("注文IDが入力されていません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "注文IDが入力されていません");
        }
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投買付注文完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407297EF005C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoBuyCompleteResponse(this);
    }
}
@
