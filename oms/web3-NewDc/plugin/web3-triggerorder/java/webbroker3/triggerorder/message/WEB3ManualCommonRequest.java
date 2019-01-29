head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ManualCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手動発注共通リクエスト(WEB3ManualCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.triggerorder.define.WEB3ToProductTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (手動発注共通リクエスト)<BR>
 * 手動発注共通リクエストクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3ManualCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ManualCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "manual_common";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602162240L;
    
    /**
     * (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * <BR>
     * 1：　@株式<BR>
     * 6：　@先物オプション<BR>
     */
    public String productType;
    
    /**
     * (条件注文種別)<BR>
     * 条件注文種別<BR>
     * <BR>
     * 1：　@連続注文<BR>
     * 2：　@OCO注文<BR>
     * 3：　@IFD注文<BR>
     * 4：　@逆指値注文<BR>
     * 5：　@W指値注文<BR>
     */
    public String triggerOrderType;
    
    /**
     * (注文ID)<BR>
     * 注文IDの配列<BR>
     * 　@※管理者リクエストの場合は１～複数件<BR>
     * 　@※ユーザーリクエストの場合は1件<BR>
     */
    public String[] orderId;
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列<BR>
     * <BR>
     * ※管理者は必須<BR>
     */
    public String[] branchCode = null;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F4889200AB
     */
    public WEB3ManualCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@銘柄タイプチェック<BR>
     * 　@１－１）this.銘柄タイプ == nullの場合、<BR>
     * 　@　@　@　@　@「銘柄タイプがnull」の例外をスローする。<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_02394<BR>
     * <BR>
     * 　@１－２）this.銘柄タイプが以下の値以外の場合、<BR>
     * 　@　@　@　@　@「銘柄タイプが未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・”1：株式”<BR>
     * 　@　@　@　@・”6：指数先物・オプション”<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_02395<BR>
     * <BR>
     * ２）　@条件注文種別チェック<BR>
     * 　@２－１）this.条件注文種別 == nulの場合、<BR>
     * 　@　@　@　@　@「条件注文種別がnull」の例外をスローする。<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_02396<BR>
     * <BR>
     * 　@２－２）this.条件注文種別が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「条件注文種別が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・”1：連続注文”<BR>
     * 　@　@　@　@・”2：OCO注文”<BR>
     * 　@　@　@　@・”3：IFD注文”<BR>
     * 　@　@　@　@・”4：逆指値”<BR>
     * 　@　@　@　@・”5：W指値”<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_02397<BR>
     * <BR>
     * ３）　@注文IDチェック<BR>
     * 　@３－１）this.注文IDの要素数 == nullの場合、<BR>
     * 　@　@　@　@　@「注文IDがnull」の例外をスローする。<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_00600<BR>
     * <BR>
     * 　@３－２）this.注文IDの要素数分繰り返してチェックを行う。<BR>
     * 　@　@　@・注文ID[ｎ番目の要素] == nullの場合、<BR>
     * 　@　@　@　@　@「注文IDがnull」の例外をスローする。<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E733C80275
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@銘柄タイプチェック
        //１－１）this.銘柄タイプ == nullの場合、
        //　@　@　@　@「銘柄タイプがnull」の例外をスローする。
        if (this.productType == null)
        {
            log.debug("銘柄タイプが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02394,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄タイプが未指定です。");
        }
        
        //１－２）this.銘柄タイプが以下の値以外の場合、
        //　@　@　@　@「銘柄タイプが未定義の値」の例外をスローする。
        //　@　@　@　@・”1：株式”
        //　@　@　@　@・”6：指数先物・オプション”
        if (!(WEB3ToProductTypeDef.EQUITY.equals(this.productType)
            || WEB3ToProductTypeDef.FUTURE_OPTION.equals(this.productType)))
        {
            log.debug("銘柄タイプが未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02395,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄タイプが未定義の値です。");
        }
        
        //２）　@条件注文種別チェック
        //２－１）this.条件注文種別 == nulの場合、
        //　@　@　@　@「条件注文種別がnull」の例外をスローする。
        if (this.triggerOrderType == null)
        {
            log.debug("条件注文種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02396,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件注文種別が未指定です。");
        }
        
        //２－２）this.条件注文種別が以下の値以外の場合、
        //　@　@　@　@「条件注文種別が未定義の値」の例外をスローする。
        //　@　@　@・”1：連続注文”
        //　@　@　@・”2：OCO注文”
        //　@　@　@・”3：IFD注文”
        //　@　@　@・”4：逆指値”
        //　@　@　@・”5：W指値”
        if (!(WEB3TriggerOrderTypeDef.SUCC.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.OCO.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.IFD.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.STOP.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType)))
        {
            log.debug("条件注文種別が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02397,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件注文種別が未定義の値です。");
        }
        
        //３）　@注文IDチェック
        //３－１）this.注文IDの要素数 == nullの場合、
        //　@　@　@　@「注文IDがnull」の例外をスローする。
        if (this.orderId == null || this.orderId.length == 0)
        {
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }
        
        //３－２）this.注文IDの要素数分繰り返してチェックを行う。
        //　@　@・注文ID[ｎ番目の要素] == nullの場合、
        //　@　@　@　@「注文IDがnull」の例外をスローする。
        int l_intLen = this.orderId.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (this.orderId[i] == null || this.orderId[i].length() == 0)
            {
                log.debug("注文IDが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文IDが未指定です。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createResponseの実装)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return null;        
    }
}
@
