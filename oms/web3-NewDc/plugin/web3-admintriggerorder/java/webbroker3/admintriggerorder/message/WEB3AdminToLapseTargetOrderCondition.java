head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToLapseTargetOrderCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 失効対象注文条件(WEB3AdminToLapseTargetOrderCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (失効対象注文条件)<BR>
 * 失効対象注文条件<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToLapseTargetOrderCondition extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToLapseTargetOrderCondition.class);
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String id = null;
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列 <BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している <BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;
    
    /**
     * (条件注文種別一覧)<BR>
     * 条件注文種別<BR>
     * <BR>
     * 1：　@連続注文<BR>
     * 2：　@OCO注文<BR>
     * 3：　@IFD注文<BR>
     * 4：　@逆指値注文<BR>
     * 5：　@W指値注文<BR>
     * <BR>
     * ※"連続注文"は定義上存在するが、本クラスでは使用されない。<BR>
     */
    public String[] triggerOrderTypeList = null;
    
    /**
     * (商品区分一覧)<BR>
     * 商品区分 <BR>
     * <BR>
     * 1：　@現物株式 <BR>
     * 2：　@信用取引 <BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     */
    public String[] productDivList;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コード<BR>
     */
    public String[] marketList = null;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode = null;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode = null;
    
    /**
     * (失効対象注文条件)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44051F270167
     */
    public WEB3AdminToLapseTargetOrderCondition() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@注文IDチェック<BR>
     * 　@this.注文ID != nullの場合、以下のチェックを行う。<BR>
     * 　@１－１）　@this.注文ID != 数字の場合、 <BR>
     * 　@　@　@　@　@「注文IDが数字以外」の例外をスローする。 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01476<BR>
     * <BR>
     * ２）　@部店コードチェック <BR>
     * 　@２－１）　@this.部店コード == nullの場合、 <BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02174<BR>
     * <BR>
     * 　@２－２）　@this.部店コードの要素数分以下の処理を行う。 <BR>
     * 　@　@２－２－１）　@this.部店コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@　@　@　@「部店コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@　@・部店コード != 数字 <BR>
     * 　@　@　@　@　@　@　@・部店コード.length != 3 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ３）　@条件注文種別一覧チェック<BR>
     * 　@３－１）　@ID直接指定時でない（注文ID == null）場合、<BR>
     * 　@　@this.条件注文種別一覧 == nullであれば、<BR>
     * 　@　@「条件注文種別が未指定です。」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02396<BR>
     * <BR>
     * 　@３－２）　@this.条件注文種別一覧 != nullの場合、<BR>
     * 　@　@this.条件注文種別一覧に下記の項目以外が<BR>
     * 　@　@設定されていた場合、「条件注文種別が未定義の値です。」の<BR>
     * 　@　@例外をスローする。 <BR>
     * 　@　@　@・"連続注文" <BR>
     * 　@　@　@・"OCO注文"<BR>
     * 　@　@　@・"IFD注文"<BR>
     * 　@　@　@・"逆指値注文" <BR>
     * 　@　@　@・"W指値注文"<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02397<BR>
     * <BR>
     * ４）　@商品区分一覧チェック<BR>
     * 　@４－１）　@this.商品区分一覧 == nullの場合、<BR>
     * 　@　@「商品区分一覧が未指定です。」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01462<BR>
     * <BR>
     * 　@４－２）　@this.商品区分一覧に下記の項目以外が設定されていたら、 <BR>
     * 　@　@　@　@「商品区分が存在しないコード値です。」の例外をスローする。 <BR>
     * 　@　@　@　@・"現物株式" <BR>
     * 　@　@　@　@・"信用取引" <BR>
     * 　@　@　@　@・"先物"<BR>
     * 　@　@　@　@・"オプション" <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * ５）　@市場コード一覧チェック<BR>
     * 　@５－１）　@this.商品区分一覧に"現物株式" or "信用取引"が含まれる場合、<BR>
     * 　@　@ID直接指定時でない（注文ID == null）　@かつ<BR>
     * 　@　@this.市場コード一覧 == nullであれば、<BR>
     * 　@　@「市場コードが未指定です。」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * 　@５－２）　@this.市場コード一覧 != nullの場合、<BR>
     * 　@　@this.市場コード一覧に下記の項目以外が設定されていたら、<BR>
     * 　@　@「市場コードが未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・"東京" <BR>
     * 　@　@　@・"大阪" <BR>
     * 　@　@　@・"名古屋" <BR>
     * 　@　@　@・"福岡" <BR>
     * 　@　@　@・"札幌" <BR>
     * 　@　@　@・"NNM" <BR>
     * 　@　@　@・"JASDAQ" <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * ６）　@銘柄コードチェック <BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。 <BR>
     * 　@６－１）　@this.銘柄コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@「銘柄コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@・銘柄コード != 数字 <BR>
     * 　@　@　@　@・銘柄コード.length != 5 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ７）　@顧客コードチェック<BR>
     * 　@this.顧客コード != nullの場合、以下のチェックを行う。<BR>
     * 　@７－１）　@this.顧客コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@「顧客コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@・顧客コード != 数字 <BR>
     * 　@　@　@　@・顧客コード.length != 6 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00780<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44051F23035B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文IDチェック
        //　@this.注文ID != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.id))
        {
            //１－１）　@this.注文ID != 数字の場合、「注文IDが数字以外」の例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.id))
            {
                log.debug("注文IDが数字以外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文IDが数字以外です。");
            }
        }
        
        //２）　@部店コードチェック 
        //　@２－１）　@this.部店コード == nullの場合、「部店コードがnull」の例外をスローする。
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }
        
        //２－２）　@this.部店コードの要素数分以下の処理を行う。 
        //　@２－２－１）　@this.部店コードが以下の条件に該当する場合、
        //　@　@　@　@　@　@「部店コードエラー」の例外をスローする。
        //　@　@　@　@　@　@・部店コード != 数字
        //　@　@　@　@　@　@・部店コード.length != 3 
        int l_intlen = this.branchCode.length;
        for (int i = 0; i < l_intlen; i++)
        {
            if (!(WEB3StringTypeUtility.isDigit(this.branchCode[i]) 
                && this.branchCode[i].length() == 3))
            {
                log.debug("部店コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの入力が不正です。");
            }
        }
        
        //３）　@条件注文種別一覧チェック
        //　@３－１）　@ID直接指定時でない（注文ID == null）場合、
        //　@　@this.条件注文種別一覧 == nullであれば、
        //　@　@「条件注文種別が未指定です。」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.id) 
            && (this.triggerOrderTypeList == null || this.triggerOrderTypeList.length == 0))
        {
            log.debug("条件注文種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02396,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件注文種別が未指定です。");
        }
        
        //　@３－２）　@this.条件注文種別一覧 != nullの場合、
        //　@　@this.条件注文種別一覧に下記の項目以外が設定されていた場合、
        //　@　@「条件注文種別が未定義の値です。」の例外をスローする。
        //　@　@　@・"連続注文" 
        //　@　@　@・"OCO注文"
        //　@　@　@・"IFD注文"
        //　@　@　@・"逆指値注文"
        //　@　@　@・"W指値注文"
        
        if (this.triggerOrderTypeList != null && this.triggerOrderTypeList.length != 0)
        {
            l_intlen = this.triggerOrderTypeList.length;
            for (int i = 0; i < l_intlen; i++)
            {
                if (!(WEB3TriggerOrderTypeDef.SUCC.equals(this.triggerOrderTypeList[i])
                    || WEB3TriggerOrderTypeDef.OCO.equals(this.triggerOrderTypeList[i])
                    || WEB3TriggerOrderTypeDef.IFD.equals(this.triggerOrderTypeList[i])
                    || WEB3TriggerOrderTypeDef.STOP.equals(this.triggerOrderTypeList[i])
                    || WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderTypeList[i])))
                {
                    log.debug("条件注文種別が未定義の値です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02397,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "条件注文種別が未定義の値です。");
                }
            }
        }
        
        //４）　@商品区分一覧チェック
        //　@４－１）　@this.商品区分一覧 == nullの場合、「商品区分一覧が未指定です。」の
        //　@　@　@例外をスローする。
        if (this.productDivList == null || this.productDivList.length == 0)
        {
            log.debug("商品区分一覧が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01462,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品区分一覧が未指定です。");
        }
        
        //　@４－２）　@this.商品区分一覧に下記の項目以外が設定されていたら、
        //　@　@　@　@「商品区分が存在しないコード値です。」の例外をスローする。
        //　@　@　@　@・"現物株式" 
        //　@　@　@　@・"信用取引" 
        //　@　@　@　@・"先物"
        //　@　@　@　@・"オプション" 
        l_intlen = this.productDivList.length;
        for (int i = 0; i < l_intlen; i++)
        {
            if (!(WEB3CommodityDivDef.EQUITY.equals(this.productDivList[i])
                || WEB3CommodityDivDef.MARGIN.equals(this.productDivList[i])
                || WEB3CommodityDivDef.FUTURE.equals(this.productDivList[i])
                || WEB3CommodityDivDef.OPTION.equals(this.productDivList[i])))
            {
                log.debug("商品区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "商品区分が存在しないコード値です。");
            }
        }
        
        //５）　@市場コード一覧チェック
        //　@５－１）　@this.商品区分一覧に"現物株式" or "信用取引"が含まれる場合、
        //　@　@ID直接指定時でない（注文ID == null）　@かつ　@
        //　@　@this.市場コード一覧 == nullであれば、
        //　@　@「市場コードが未指定です。」の例外をスローする。
        boolean l_blnFlag = false;
        for (int i = 0; i < l_intlen; i++)
        {
            if (WEB3CommodityDivDef.EQUITY.equals(this.productDivList[i])
                || WEB3CommodityDivDef.MARGIN.equals(this.productDivList[i]))
            {
                l_blnFlag = true;
            }
        }
        
        if (l_blnFlag)
        {
            if (WEB3StringTypeUtility.isEmpty(this.id) 
                && (this.marketList == null || this.marketList.length == 0))
            {
                log.debug("市場コードが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "市場コードが未指定です。");
            }
        }
        
        //　@５－２）　@this.市場コード一覧 != nullの場合、
        //　@　@this.市場コード一覧に下記の項目以外が設定されていたら、
        //　@　@「市場コードが未定義の値」の例外をスローする。
        //　@　@　@・"東京"
        //　@　@　@・"大阪" 
        //　@　@　@・"名古屋"
        //　@　@　@・"福岡"
        //　@　@　@・"札幌"
        //　@　@　@・"NNM"
        //　@　@　@・"JASDAQ"
        if (this.marketList != null && this.marketList.length != 0)
        {
            l_intlen = this.marketList.length;
            for (int i = 0; i < l_intlen; i++)
            {
                if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketList[i])
                    || WEB3MarketCodeDef.OSAKA.equals(this.marketList[i])
                    || WEB3MarketCodeDef.NAGOYA.equals(this.marketList[i])
                    || WEB3MarketCodeDef.FUKUOKA.equals(this.marketList[i])
                    || WEB3MarketCodeDef.SAPPORO.equals(this.marketList[i])
                    || WEB3MarketCodeDef.NNM.equals(this.marketList[i])
                    || WEB3MarketCodeDef.JASDAQ.equals(this.marketList[i])))
                {
                    log.debug("市場コードが存在しないコード値です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "市場コードが存在しないコード値です。");
                }
            }
        }
        
        //６）　@銘柄コードチェック
        //　@this.銘柄コード != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.productCode))
        {
            //６－１）　@this.銘柄コードが以下の条件に該当する場合、
            //　@　@　@「銘柄コードエラー」の例外をスローする。
            //　@　@　@・銘柄コード != 数字
            //　@　@　@・銘柄コード.length != 5
            if (!(WEB3StringTypeUtility.isDigit(this.productCode)
                && this.productCode.length() == 5))
            {
                log.debug("銘柄コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄コードの入力が不正です。");
            }
        }
        
        //７）　@顧客コードチェック
        //　@this.顧客コード != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.accountCode))
        {
            //７－１）　@this.顧客コードが以下の条件に該当する場合、
            //　@　@　@「顧客コードエラー」の例外をスローする。
            //　@　@　@・顧客コード != 数字
            //　@　@　@・顧客コード.length != 6
            if (!(WEB3StringTypeUtility.isDigit(this.accountCode)
                && this.accountCode.length() == 6))
            {
                log.debug("顧客コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
