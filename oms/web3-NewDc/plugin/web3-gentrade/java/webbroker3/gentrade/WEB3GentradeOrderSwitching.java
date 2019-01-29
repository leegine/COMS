head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeOrderSwitching.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 発注先切替(WEB3GentradeOrderSwitching.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/28 鄒政 (中訊) 新規作成
Revesion History : 2006/02/26 凌建平(中訊) 共通仕様変更・モデルNo176
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeCancelEnableFlag;
import webbroker3.common.define.WEB3OrderEngineDivDef;
import webbroker3.common.define.WEB3SubmitMqTriggerDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.gentrade.data.OrderSwitchingDao;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (発注先切替)<BR>
 * 証券会社＋商品＋市場毎の注文の発注先の切り替えを表現する。<BR>
 * <BR>
 * （DBレイアウト 「発注先切替テーブル仕様.xls」参照）<BR>
 */
public class WEB3GentradeOrderSwitching implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeOrderSwitching.class);

    /**
     * 発注先切替Rowオブジェクト
     * （DAO自動生成クラス）
     */
    private OrderSwitchingRow orderSwitchingRow;

//    /**
//     * コンストラクタ。<BR>
//     * 引数の条件に一致する発注先切替オブジェクトを返却する。<BR> 
//     * <BR>
//     * this.発注先切替(証券会社コード, 銘柄タイプ, 市場コード, <BR>
//     * 発注経路区分, フロント発注システム区分)に<BR>
//     * delegateする。<BR>
//     * <BR>
//     * ※引数「フロント発注システム区分」にはnullをセットする。<BR>
//     * <BR> 
//     * @@param l_strInstitutionCode - 証券会社コード<BR>
//     * @@param l_productType 銘柄タイプ
//     * @@param l_strMarketCode 市場コード
//     * @@param l_strSubmitOrderRouteDiv 発注経路区分
//     *    （0：SONAR正系  1：SONAR副系  2：フロント発注正系  3：フロント発注副系）<BR>
//     *     ※発注経路の追加に伴い、コード値も追加される。<BR>
//     * @@return WEB3GentradeOrderSwitching
//     * @@throws WEB3SystemLayerException
//     * @@roseuid 423FCE0A02B5
//     */
//    public WEB3GentradeOrderSwitching(
//        String l_strInstitutionCode,
//        ProductTypeEnum l_productType,
//        String l_strMarketCode,
//        String l_strSubmitOrderRouteDiv)
//        throws WEB3SystemLayerException
//    {
//        this(l_strInstitutionCode,
//            l_productType,
//            l_strMarketCode,
//            l_strSubmitOrderRouteDiv,
//            null);
//    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、<BR>
     * 引数のRowオブジェクトをプロパティにセットする。<BR>
     * @@param l_orderSwitchingRow - 発注先切替Rowオブジェクト
     * @@return WEB3GentradeOrderSwitching
     * @@roseuid 423FD0D3032A
     */
    public WEB3GentradeOrderSwitching(OrderSwitchingRow l_orderSwitchingRow)
    {
        final String STR_METHOD_NAME = "WEB3GentradeOrderSwitching(OrderSwitchingRow)";
        if (l_orderSwitchingRow == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注先切替Rowオブジェクト = null");
        }
        this.orderSwitchingRow = l_orderSwitchingRow;
    }

    /**
     * this.発注先切替Rowを返却する。
     * @@return java.lang.Object
     * @@roseuid 423FCE0A02B4
     */
    public java.lang.Object getDataSourceObject()
    {
        return this.orderSwitchingRow;
    }

    /**
     * (is訂正取消可能)<BR>
     * 訂正取消が可能かどうかを返す。<BR>
     * <BR>
     * ・this.発注先切替Row.有効フラグ == "ON"の場合は、trueを返す。<BR>
     * ・this.発注先切替Row.有効フラグ == "OFF"の場合は、以下の通りとする。<BR>
     * 　@－this.発注先切替Row.訂正取消可能フラグ == "可能"の場合は、<BR>
     *      trueを返す。<BR>
     * 　@－上記以外の場合は、falseを返す。<BR>
     * @@return boolean
     * @@roseuid 423FCFC60074
     */
    public boolean isChangeCancelEnable()
    {     
        //this.発注先切替Row.有効フラグ == "ON"の場合は、trueを返す。
        if(WEB3ValidFlag.ON.equals(this.orderSwitchingRow.getValidFlag()))
        {
            return true;
        }
        else
        {
            //this.発注先切替Row.有効フラグ == "OFF"の場合は、以下の通りとする。
            // 　@－this.発注先切替Row.訂正取消可能フラグ == "可能"の場合は、trueを返す。
            // 　@－上記以外の場合は、falseを返す。
            if(WEB3ChangeCancelEnableFlag.ENABLE.equals(this.orderSwitchingRow.getChangeCancelEnableFlag()))
            {
                return true;
            }
            return false;
        }

    }

    /**
     * コンストラクタ。<BR>
     * 引数の条件に一致する発注先切替オブジェクトを返却する。<BR> 
     *<BR>
     * １）　@DB検索<BR> 
     *  引数の値にて発注先切替テーブルを検索する。<BR> 
     *<BR>
     *　@[条件]<BR> 
     *　@　@　@　@証券会社コード = 引数の証券会社コード<BR> 
     *　@かつ　@銘柄タイプ = 引数の銘柄タイプ <BR>
     *　@かつ　@市場コード = 引数の市場コード <BR>
     *　@かつ　@発注経路区分 = 引数の発注経路区分 <BR>
     *　@かつ　@フロント発注システム区分 = 引数のフロント発注システム区分<BR> 
     *<BR>
     *　@該当するデータが存在しない場合は、<BR> 
     *　@例外をthrowする。 <BR>
     *<BR>
     * ２）　@行オブジェクトセット<BR> 
     *　@検索結果の行オブジェクト（発注先切替Row）を、<BR>
     *  this.発注先切替Rowにセットする。<BR> 
     *<BR>
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_productType 銘柄タイプ
     * @@param l_strMarketCode 市場コード
     * @@param l_strSubmitOrderRouteDiv 発注経路区分 
     * @@param l_strFrontOrderSystemCode フロント発注システム区分
     * @@return WEB3GentradeOrderSwitching
     * @@throws WEB3SystemLayerException
     */
    public WEB3GentradeOrderSwitching(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strMarketCode,
        String l_strSubmitOrderRouteDiv,
        String l_strFrontOrderSystemCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeOrderSwitching(String, ProductTypeEnum, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //引数の値にて発注先切替テーブルを検索する。
            this.orderSwitchingRow =
                OrderSwitchingDao.findRowByPk(
                    l_strInstitutionCode,
                    l_productType,
                    l_strMarketCode,
                    l_strSubmitOrderRouteDiv,
                    l_strFrontOrderSystemCode
                    );
        }
        catch (DataFindException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        catch (DataNetworkException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        catch (DataQueryException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get有効発注先切替 )<BR>
     * （staticメソッド） <BR>
     * 指定に該当する、有効な発注先切替オブジェクトを取得する。<BR> 
     *<BR>
     *１）　@DB検索<BR> 
     *　@発注先切替テーブルを以下の条件で検索する。<BR> 
     *　@[条件] <BR>
     *　@証券会社コード = 引数の証券会社コード<BR> 
     *　@かつ　@銘柄タイプ = 引数の銘柄タイプ <BR>
     *　@かつ　@市場コード = 引数の市場コード<BR>
     *  かつ　@フロント発注システム区分 = 引数のフロント発注システム区分<BR>
     *　@かつ　@有効フラグ = "ON" <BR>
     *<BR>
     *　@該当するデータが存在しない場合は、nullを返す。<BR> 
     *<BR>
     *　@合致するレコードが複数件存在する場合は、<BR>
     * 「発注先切替テーブル設定不正（有効データが複数存在）」の例外をthrowする。<BR>
     * <BR> 
     *２）　@オブジェクト生成<BR> 
     * １）で取得した発注先切替行オブジェクトを指定し、発注先切替オブジェクトを生成する。<BR> 
     *　@生成したオブジェクトを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_strInstitutionCode 銘柄タイプ
     * @@param l_strMarketCode 市場コード
     * @@param l_strFrontSystemOrderCode フロント発注システム区分
     * @@return WEB3GentradeOrderSwitching
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeOrderSwitching getOnOrderSwitching(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strMarketCode,
        String l_strFrontSystemOrderCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getOnOrderSwitching(String, ProductTypeEnum, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeOrderSwitching l_return = null;
        try
        {
            List l_lstRecords;

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and product_type = ? ");
            l_sbWhere.append(" and market_code = ? ");
            l_sbWhere.append(" and front_order_system_code = ? ");
            l_sbWhere.append(" and valid_flag = ? ");

            List l_lstOrderSwitchingWhere = new ArrayList();
            l_lstOrderSwitchingWhere.add(l_strInstitutionCode);
            l_lstOrderSwitchingWhere.add(l_productType);
            l_lstOrderSwitchingWhere.add(l_strMarketCode);
            l_lstOrderSwitchingWhere.add(l_strFrontSystemOrderCode);
            l_lstOrderSwitchingWhere.add(WEB3ValidFlag.ON);

            Object[] l_objOrderSwitchingWhere = 
                new Object[l_lstOrderSwitchingWhere.size()];
            l_lstOrderSwitchingWhere.toArray(l_objOrderSwitchingWhere);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                l_objOrderSwitchingWhere);

            //該当するデータが存在しない場合は、nullを返す。 
            int l_intSize = l_lstRecords.size();
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //合致するレコードが複数件存在する場合は、 
            // 「発注先切替テーブル設定不正（有効データが複数存在）」の例外をthrowする。 
            if (l_intSize > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02187,
                    WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                    "「発注先切替テーブル設定不正（有効データが複数存在）」の例外をthrowする。"
                    );
            }
            
            l_return = 
                new WEB3GentradeOrderSwitching((OrderSwitchingRow)l_lstRecords.get(0));
        }
        catch (DataNetworkException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        catch (DataQueryException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_return;
    }

    /**
     * (isMQトリガ発行経路 )<BR>
     * 指定の発注経路がMQトリガ発行要かどうかを判定し返す。<BR> 
     * －全経路がOFFの場合（＝引数の発注経路区分 == null）は、falseを返す。<BR> 
     * －上記以外は、指定の経路のMQトリガ発行要否を返す。 <BR>
     *<BR>
     *１）　@引数の発注経路区分 == nullの場合は、falseを返却する。<BR> 
     *　@　@以外、以下の処理を行う。 <BR>
     *<BR>
     *２）　@発注先切替クラスのインスタンスを生成する。<BR> 
     *<BR>
     *　@　@----------------------------------------------------------<BR> 
     *　@　@＜発注先切替：コンストラクタ引数設定仕様＞ <BR>
     *<BR>
     *　@　@証券会社コード：　@引数の同項目<BR> 
     *　@　@銘柄タイプ：　@引数の同項目 <BR>
     *　@　@市場コード：　@引数の同項目 <BR>
     *　@　@発注経路区分：　@引数の同項目 <BR>
     *　@　@フロント発注システム区分：　@引数の同項目<BR> 
     *　@　@----------------------------------------------------------<BR> 
     *<BR>
     *３）　@取得したインスタンス.MQトリガ発行 == "MQトリガを実施する"の場合はtrueを、<BR> 
     *　@　@　@"MQトリガを実施しない"の場合はfalseを、それぞれ返却する。   <BR>
　@　@ *　@※該当データなしの場合はtrueを返却する。<BR>
　@　@ *　@※（該当データなしは、SONAR入力のフロント発注対応市場指定の注文の場合のみありえる）<BR>
     *<BR>
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_productType 銘柄タイプ
     * @@param l_strMarketCode 市場コード
     * @@param l_strSubmitOrderRouteDiv 発注経路区分
     * @@param l_strFrontOrderSystemCode フロント発注システム区分
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public static boolean isSubmitMQTriggerEnable(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strMarketCode,
        String l_strSubmitOrderRouteDiv,
        String l_strFrontOrderSystemCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "isSubmitMQTriggerEnable(String, ProductTypeEnum, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //－全経路がOFFの場合（＝引数の発注経路区分 == null）は、falseを返す。 
        if (l_strSubmitOrderRouteDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        try
        {
            WEB3GentradeOrderSwitching l_OrderSwitching = 
                new WEB3GentradeOrderSwitching(
                    l_strInstitutionCode,
                    l_productType,
                    l_strMarketCode,
                    l_strSubmitOrderRouteDiv,
                    l_strFrontOrderSystemCode
                    );
                    
            String l_strSubmitMqTrigger = 
            l_OrderSwitching.orderSwitchingRow.getSubmitMqTrigger();
            
            //取得したインスタンス.MQトリガ発行 == "MQトリガを実施する"の場合はtrueを返却する。 
            if (WEB3SubmitMqTriggerDef.TRIGGER.equals(l_strSubmitMqTrigger))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            //"MQトリガを実施しない"の場合はfalseを、それぞれ返却する。  
            else if (WEB3SubmitMqTriggerDef.NOT_TRIGGER.equals(l_strSubmitMqTrigger))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    WEB3GentradeOrderSwitching.class.getName()
                    + "." + STR_METHOD_NAME);
            }
        }
        catch (WEB3SystemLayerException l_e)
        {
            //※該当データなしの場合はtrueを返却する。
            //※（該当データなしは、SONAR入力のフロント発注対応市場指定の注文の場合のみありえる）
            if(WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_e.getErrorInfo()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }    
            
            throw new WEB3SystemLayerException(
                l_e.getErrorInfo(),
                WEB3GentradeTradingTimeManagement.class.getName() + "." 
                    + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    }
    
    /**
     * (isSONAR)<BR>
     * 発注経路のエンジンがSONARかどうかを返す。<BR>
     * <BR>
     * ・this.発注先切替Row.発注エンジン区分 == "SONAR"の場合は、trueを返す。<BR>
     * ・this.発注先切替Row.発注エンジン区分 != "SONAR"の場合は、falseを返す。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 423FCFC60074
     */
    public boolean isSONAR()
    {
        final String STR_METHOD_NAME = "isSONAR()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3OrderEngineDivDef.SONAR.equals(this.orderSwitchingRow.getOrderEngineDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
