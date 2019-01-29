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
filename	WEB3AdminRuitoTradeStopConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投銘柄別売買停止確認リクエスト(WEB3AdminRuitoTradeStopConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import java.util.Date;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (累投銘柄別売買停止確認リクエスト)<BR>
 * 累投銘柄別売買停止確認リクエストクラス
 */
public class WEB3AdminRuitoTradeStopConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_ruito_trade_stop_confirm";
    
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;   
    
    /**
     * (銘柄更新情報)<BR>
     * 銘柄更新情報
     */
    public WEB3AdminRuitoUpdatedTradeInfo[] productUpdateInfoList;
    
    /**
     * (オペレーション日付)<BR>
     * オペレーション日付<BR>
     * 入力画面取得時の現在日付
     */
    public Date operationDate;
    
    /**
     * (累投銘柄別売買停止確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922CAA03B9
     */
    public WEB3AdminRuitoTradeStopConfirmRequest()
    {
    }
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminRuitoTradeStopConfirmRequest.class); 
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     *（ただし、当クラス内で完結する簡易チェックのみとする。）BR>
     *<BR>
     *１）　@銘柄更新情報のチェック<BR> 
　@   *－this.銘柄更新情報＝NULLの場合、例外をスローする。<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01235<BR>
　@   *－this.銘柄更新情報の要素数＝0件の場合、例外をスローする。<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01236<BR>
　@   *－this.銘柄更新情報.銘柄コード＝NULLの場合、例外をスローする。<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01237<BR>
　@   *－this.銘柄更新情報.買付可能区分（翌日）＝NULLの場合、例外をスローする。<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01238<BR>
　@   *－this.銘柄更新情報.解約可能区分（翌日）＝NULLの場合、例外をスローする。<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01239<BR>
　@   *－this.銘柄更新情報.買付開始日!=NULLかつthis.銘柄更新情報.買付終了日!=NULLであり、<BR> 
　@　@ *かつ買付開始日≧買付終了日の場合、例外をスローする。 <BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01240<BR>
　@   *－this.銘柄更新情報.解約開始日!=NULLかつthis.銘柄更新情報.解約終了日!=NULLであり、<BR> 
　@　@ *かつ解約開始日≧解約終了日の場合、例外をスローする。 <BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01241<BR>
     *<BR>
     *２）　@オペレーション日付のチェック<BR> 
　@   *this.オペレーション日付＝NULLの場合、例外をスローする。<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01242<BR>
     *@@roseuid 4073679802CB<BR>
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@銘柄更新情報のチェック 
        //　@－this.銘柄更新情報＝NULLの場合、例外をスローする。 
        if (this.productUpdateInfoList == null)
        {
            log.debug("this.銘柄更新情報＝NULLの場合、例外をスローする");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01235,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "銘柄更新情報＝NULLの場合");              
        }
        
        //　@－this.銘柄更新情報の要素数＝0件の場合、例外をスローする。 
        if (this.productUpdateInfoList.length == 0)
        {
            log.debug("this.銘柄更新情報の要素数＝0件の場合、例外をスローする");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01236,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "銘柄更新情報の要素数＝0件の場合"); 
        }
        
        //　@－this.銘柄更新情報.銘柄コード＝NULLの場合、例外をスローする。
        for (int i = 0; i < this.productUpdateInfoList.length; i++)
        {
            WEB3AdminRuitoUpdatedTradeInfo l_adminRuitoUpdatedTradeInfo = 
                this.productUpdateInfoList[i];
            if (WEB3StringTypeUtility.isEmpty(l_adminRuitoUpdatedTradeInfo.ruitoProductCode))
            {
                log.debug("銘柄更新情報.銘柄コードが入力されていません。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01237,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "銘柄更新情報.銘柄コードが入力されていません。");                 
            }
        
            //　@－this.銘柄更新情報.買付開始日!=NULLかつthis.銘柄更新情報.買付終了日!=NULLであり、 
            //　@　@かつ買付開始日≧買付終了日の場合、例外をスローする。
            Date l_datBuyStartDate = l_adminRuitoUpdatedTradeInfo.buyStartDate;
            Date l_datBuyEndDate = l_adminRuitoUpdatedTradeInfo.buyEndDate;
            
            if (l_datBuyStartDate != null && 
                l_datBuyEndDate != null &&
                WEB3DateUtility.compareToDay(l_datBuyStartDate, l_datBuyEndDate) >= 0)               
            {
                log.debug("this.銘柄更新情報.買付開始日!=NULLかつ" +
                        "this.銘柄更新情報.買付終了日!=NULLであり、かつ" +
                        "買付開始日≧買付終了日の場合、例外をスローする");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01240,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "銘柄更新情報.買付開始日!=NULLかつ銘柄更新情報.買付終了日!=NULLであり、" +
                    "かつ買付開始日≧買付終了日の場合");                
            }
        
            //　@－this.銘柄更新情報.解約開始日!=NULLかつthis.銘柄更新情報.解約終了日!=NULLであり、 
            //　@　@かつ解約開始日≧解約終了日の場合、例外をスローする。          
            Date l_datSellStartDate = l_adminRuitoUpdatedTradeInfo.sellStartDate;
            Date l_datSellEndDate = l_adminRuitoUpdatedTradeInfo.sellEndDate;
            
            if (l_datSellStartDate != null && 
                l_datSellEndDate != null &&
                WEB3DateUtility.compareToDay(l_datSellStartDate, l_datSellEndDate) >= 0)
            {
                log.debug("this.銘柄更新情報.解約開始日!=NULLかつthis.銘柄更新情報.解約終了日!=NULLであり、" +
                    "かつ解約開始日≧解約終了日の場合、例外をスローする");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01241,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "銘柄更新情報.解約開始日!=NULLかつ銘柄更新情報.解約終了日!=NULLであり、" +
                    "かつ解約開始日≧解約終了日の場合"); 
                
            }
        }
        
        //２）　@オペレーション日付のチェック 
        //　@this.オペレーション日付＝NULLの場合、例外をスローする。 
        if (this.operationDate == null)
        {
            log.debug("this.オペレーション日付＝NULLの場合、例外をスローする");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01242,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "オペレーション日付＝NULLの場合");
        }        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投銘柄別売買停止確認レスポンスオブジェクトを生成し、返却する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407297A702FB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminRuitoTradeStopConfirmResponse(this);
    }
}@
