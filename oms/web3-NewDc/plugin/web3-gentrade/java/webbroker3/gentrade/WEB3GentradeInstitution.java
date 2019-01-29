head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeInstitution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券会社クラス(WEB3GentradeInstitution.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 本郷　@千草(SRA) 新規作成
Revesion History : 2004/02/10 今井　@高史(SRA) 編集
Revesion History : 2004/07/13 鄒政 (中訊) 変更
Revesion History : 2007/06/27 栄イ (中訊)【共通】仕様変更・モデルNo.255
Revesion History : 2007/10/09 栄イ (中訊)【共通】仕様変更・モデルNo.272
Revesion History : 2010/01/12 趙林鵬 (中訊)【共通】仕様変更・モデルNo.348
*/
package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3FeqDayTradeDivDef;
import webbroker3.common.define.WEB3FeqNettingDivDef;
import webbroker3.common.define.WEB3ForcedsettleorderDivDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.OrderexecutionEndDao;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.util.WEB3LogUtility;

/**
 * 証券会社を表現する。<BR>
 * xTradeのInstitutionを拡張したクラス。<BR>
 * <BR>
 * @@author 本郷　@千草(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl
 */
public class WEB3GentradeInstitution extends InstitutionImpl
{
    /**
     * (証券会社コード) <BR>
     */
    private String institutionCode;
    
    /**
     * (市場開局) <BR>
     *  <BR>
     * 市場開局時間帯かどうかを保存するフラグ。<BR>
     * 出来終了受信処理にてfalseが設定される。<BR>
     */
    private boolean isTimeZoneMarket;
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeInstitution.class);
    


    /**
     * コンストラクタ。<BR>
     * <BR> 
     * @@param l_strInstitutionCode 証券会社コード
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 403496ED02C8
     */
    public WEB3GentradeInstitution(String l_strInstitutionCode)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_strInstitutionCode);
    }

    /**
     * コンストラクタ。<BR>
     * <BR> 
     * @@param l_lngInstId 証券会社ID
     * @@param l_institutionId
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 403496ED00E3
     */
    public WEB3GentradeInstitution(long l_institutionId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institutionId);
    }

    /**
     * コンストラクタ。<BR>
     * <BR> 
     * @@param l_lngInstId 証券会社ID
     * @@param l_institutionRow
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 403496EC0315
     */
    public WEB3GentradeInstitution(InstitutionRow l_institutionRow)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institutionRow);
    }

    /**
     * (get注文失効日) <BR>
     * 発注日に対する注文失効日を取得する。 <BR>
     * <BR>
     * １）　@有効期限取得 <BR>
     * 　@該当証券会社の注文有効日数を取得する。 <BR>
     * 　@　@（本インスタンスが保持する証券会社Paramsより） <BR>
     * <BR>
     * ２）　@注文失効日算出 <BR>
     * 　@発注日(*1)の注文有効日数後の日付を算出する。 <BR>
     * 　@算出した日付が休日でない場合、その日付を返却する。 <BR>
     * 　@算出した日付が休日であった場合、算出した日付の前営業日を取得し返却する。 <BR>
     * <BR>
     * (*1)　@発注日 <BR>
     * TradingCalendarImpl.getCurrentBizDate( )で取得した日付。 <BR>
     * <BR>
     * @@return java.util.Date
     * @@roseuid 40A8945F031C
     */
    public Date getExpirationDate() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getExpirationDate";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (is出来終了) <BR>
     * 引数に指定された商品が、出来終了済かどうかを判別する。<BR>
     * [戻り値] <BR>
     *   true: 処理済  false: 未処理 <BR>
     *  <BR>
     * １）DB検索 <BR>
     *  以下の条件で出来終了テーブルを検索する。<BR>
     *  <BR>
     *  [条件] <BR>
     * 　@　@証券会社コード　@　@　@　@ ＝　@this.証券会社コード <BR>
     * 　@　@銘柄タイプ　@　@　@　@　@　@　@ ＝　@パラメータ.銘柄タイプ <BR>
     * 　@　@先物／オプション区分　@＝　@パラメータ.先物／オプション区分 <BR>
     * 　@　@出来終了区分　@　@　@　@  ＝　@"DEFAULT" <BR>
     *  <BR>
     *  データが取得できた場合はtrueを返却する。<BR>
     *  取得できなかった場合はfalseを返却する。<BR>
     * @@param l_productType - (銘柄タイプ) <BR>
     * @@param l_strFutureOptionDiv - (先物／オプション区分) <BR>
     * 　@0：DEFAULT（先物オプション以外） <BR>
     * 　@1：先物 <BR>
     * 　@2：オプション <BR>
     * @@return boolean<BR>
     * @@throws WEB3SystemLayerException
     */
    public boolean isOrderExecEnd(ProductTypeEnum l_productType,String l_strFutureOptionDiv) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isOrderExecEnd(ProductTypeEnum,String)";
        //１）DB検索 
        //以下の条件で出来終了テーブルを検索する。
        //*   証券会社コード　@　@　@　@ ＝　@this.証券会社コード 
        //*   銘柄タイプ　@　@　@　@　@　@　@ ＝　@パラメータ.銘柄タイプ 
        //*   先物／オプション区分　@＝　@パラメータ.先物／オプション区分
        OrderexecutionEndRow l_orderexecutionEndRow = null;
        try
        {
            l_orderexecutionEndRow = 
                OrderexecutionEndDao.findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(
                    this.getInstitutionCode(),
                    l_productType,
                    l_strFutureOptionDiv,
                    WEB3OrderexecutionEndTypeDef.DEFAULT);
        }
        catch (DataFindException dfe)
        {

        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //データが取得できた場合はtrueを返却する。
        //取得できなかった場合はfalseを返却する。
        if(l_orderexecutionEndRow == null)
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
    /**
     * (is預り証券評価制＜現物株式評価＞) <BR>
     * 証券会社が現物株式を預り証券として評価するかどうかを判別する<BR>
     * [戻り値] <BR>
     *   true: 実施  false: 未実施 <BR>
     *  <BR>
     * １）預り証券評価するか判別する。<BR>
     *  <BR>
     * [this.預り証券評価制 = ”実施” <BR>
     * かつthis.現物株式評価 = ”実施”の場合]<BR>
     * trueを返却する。<BR>
     * <BR>
     * [上記以外]<BR>
     * falseを返却する。<BR> 
     *  <BR>
     * @@return boolean<BR>
     */
    public boolean isInstitutionStockEvaluation()
    {
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_isInstitutionStockEvaluation = false;
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()) &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getStockEvaluation()))
        {
            l_isInstitutionStockEvaluation = true;
        }
        return l_isInstitutionStockEvaluation;

    }
    
    /**
     * (is預り証券評価制＜GP評価＞) <BR>
     * 証券会社がGPを預り証券として評価するかどうかを判別する<BR>
     * [戻り値] <BR>
     *   true: 実施  false: 未実施 <BR>
     *  <BR>
     * １）預り証券評価するか判別する。<BR>
     *  <BR>
     * [this.預り証券評価制= ”実施” かつthis.GP評価= ”実施”の場合]<BR>
     * trueを返却する。<BR>
     *  <BR>
     * [上記以外] <BR>
     * falseを返却する。<BR> 
     *  <BR>
     * @@return boolean<BR>
     */
    public boolean isInstitutionGpEvaluation()
    {
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_isInstitutionGpEvaluation = false;
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()) &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getGpEvaluation()))
        {
            l_isInstitutionGpEvaluation = true;
        }
        return l_isInstitutionGpEvaluation;
    }
    
    /**
     * (is預り証券評価制＜投信評価＞) <BR>
     * 証券会社が投信を預り証券として評価するかどうかを判別する<BR>
     * [戻り値] <BR>
     *   true: 実施  false: 未実施 <BR>
     *  <BR>
     * １）預り証券評価するか判別する。<BR> 
     *  <BR> 
     * [this.預り証券評価制= ”実施” かつ<BR> 
     * this.投信評価= ”実施”の場合] <BR> 
     * trueを返却する。<BR> 
     * <BR> 
     * [上記以外]<BR> 
     * falseを返却する。<BR> 
     *  <BR>
     * @@return boolean<BR>
     */
    public boolean isInstitutionFundEvaluation()
    {
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_isInstitutionFundEvaluation = false;
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()) &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getFundEvaluation()))
        {
            l_isInstitutionFundEvaluation = true;
        }
        return l_isInstitutionFundEvaluation;
    }
    
    /**
     * (is預り証券評価制＜債券評価＞) <BR>
     * 証券会社が債券を預り証券として評価するかどうかを判別する<BR>
     * [戻り値] <BR>
     *   true: 実施  false: 未実施 <BR>
     *  <BR>
     * １）預り証券評価するか判別する。<BR>
     *  <BR>  
     * [this.預り証券評価制= ”実施” かつ <BR> 
     * this.債券評価= ”実施”の場合] <BR> 
     * trueを返却する。<BR> 
     *  <BR> 
     * [上記以外] <BR> 
     * falseを返却する。<BR> 
     *  <BR>
     * @@return boolean<BR>
     */
    public boolean isInstitutionBondEvaluation()
    {
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_isInstitutionBondEvaluation = false;
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()) &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getBondEvaluation()))
        {
            l_isInstitutionBondEvaluation = true;
        }
        return l_isInstitutionBondEvaluation;
    }
    
    /**
     * (get注文繰越処理区分) <BR>
     *  <BR>
     * 引数に指定された商品の注文繰越処理区分を返却する。 <BR>
     * <BR>
     * オーバーロードメソッドに処理を委譲（delegate）する。 <BR>
     * <BR>
     * 　@[引数設定仕様] <BR>
     * 　@　@銘柄タイプ　@　@　@　@　@　@　@ ＝　@パラメータ.銘柄タイプ <BR>
     * 　@　@先物／オプション区分　@＝　@パラメータ.先物／オプション区分 <BR>
     * 　@　@出来終了区分　@　@　@　@  ＝　@"DEFAULT" <BR>
     * <BR>
     * @@param l_productType - (銘柄タイプ) <BR>
     * (ProductTypeEnumにて定義)<BR>
     * @@param l_strFutureOptionDiv - (先物／オプション区分) <BR>
     * 　@0：DEFAULT（先物オプション以外） <BR>
     * 　@1：先物 <BR>
     * 　@2：オプション <BR>
     * @@return String <BR>
     * @@throws WEB3SystemLayerException <BR>
     */
    public String getCarryoverEndType(ProductTypeEnum l_productType, String l_strFutureOptionDiv)
        throws WEB3SystemLayerException
    {
        return getCarryoverEndType(
            l_productType,
            l_strFutureOptionDiv,
            WEB3OrderexecutionEndTypeDef.DEFAULT);
    }

    /**
     * (get注文繰越処理区分) <BR>
     *  <BR>
     * 引数に指定された商品の注文繰越処理区分を返却する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 　@以下の条件で出来終了テーブルを検索する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@　@証券会社コード　@　@　@　@ ＝　@this.証券会社コード <BR>
     * 　@　@銘柄タイプ　@　@　@　@　@　@　@ ＝　@パラメータ.銘柄タイプ <BR>
     * 　@　@先物／オプション区分　@＝　@パラメータ.先物／オプション区分 <BR>
     * 　@　@出来終了区分　@　@　@　@  ＝　@パラメータ.出来終了区分 <BR>
     * <BR>
     * 　@検索結果が取得できなかった場合はnullを返却する。 <BR>
     * <BR>
     * ２）取得した検索結果の注文繰越処理区分を返却する。 <BR>
     * <BR>
     * @@param l_productType - 銘柄タイプ<BR>
     * (ProductTypeEnumにて定義)<BR>
     * @@param l_strFutureOptionDiv - 先物／オプション区分 <BR>
     * 0:DEFAULT(先物／オプション以外) <BR>
     * 1:先物 <BR>
     * 2:オプション<BR>
     * @@param l_strOrderExecutionEndType - 出来終了区分<BR>
     * @@return String <BR>
     * @@throws WEB3SystemLayerException <BR>
     */
    public String getCarryoverEndType(
        ProductTypeEnum l_productType,
        String l_strFutureOptionDiv,
        String l_strOrderExecutionEndType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getCarryoverEndType(ProductTypeEnum,String,String)";

        //１）DB検索
        //以下の条件で出来終了テーブルを検索する。
        //  証券会社コード　@　@　@　@ ＝　@this.証券会社コード
        //  銘柄タイプ　@　@　@　@　@　@　@ ＝　@パラメータ.銘柄タイプ
        //  先物／オプション区分　@＝　@パラメータ.先物／オプション区分
        //  出来終了区分　@　@　@　@  ＝　@パラメータ.出来終了区分
        OrderexecutionEndRow l_orderexecutionEndRow = null;
        try
        {
            l_orderexecutionEndRow =
                OrderexecutionEndDao.findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(
                    this.getInstitutionCode(),
                    l_productType,
                    l_strFutureOptionDiv,
                    l_strOrderExecutionEndType);
        }
        catch (DataFindException l_dfe)
        {
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        //２）取得した検索結果の注文繰越処理区分を返却する。
        //検索結果が取得できなかった場合はnullを返却する。
        if (l_orderexecutionEndRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_orderexecutionEndRow.getCarryoverEndType();
        }

    }
    
    /**
     * (is発注遅延無視)<BR>
     * <BR>
     * トリガー注文の発注遅延を<BR>
     * 無視する会社かどうか判別する。<BR>
     * 無視する場合はtrueを、以外、falseを返却する。<BR>
     * <BR>
     * １）　@システムプリファ@レンステーブルより<BR>
     * 　@発注遅延無視かどうかのフラグを取得する。<BR>
     * 　@GtlUtils.getTradingSystem().getPreference()<BR>
     * 　@メソッドをコールする。<BR>
     * <BR>
     * 　@[getPreference()に指定する引数]<BR>
     * 　@　@arg0：　@this.証券会社コード<BR>
     * 　@　@　@+ IS_SUBMIT_DELAY_ORDER(*1)<BR>
     * <BR>
     * ２）　@１）の戻り値が"true"であれば、trueを返却する。<BR>
     * 　@以外、falseを返却する。<BR>
     * <BR>
     * (*1)IS_SUBMIT_DELAY_ORDER<BR>
     * 　@コード値：　@".rls.is.submit.delay.order"<BR>
     * 　@※上記内容で定数定義ファ@イルを作成し、<BR>
     * 　@　@その定義値を参照すること。<BR>
     * @@return boolean
     */
    public boolean isSubmitOrderDelayDisregard()
    {
        final String STR_METHOD_NAME = "isSubmitOrderDelayDisregard()";
        log.entering(STR_METHOD_NAME);
        
        // システムプリファ@レンステーブルより発注遅延無視かどうかのフラグを取得する。
        String l_strPreferenceName = this.getInstitutionCode()
            + WEB3SystemPreferencesNameDef.IS_SUBMIT_DELAY_ORDER;
        log.debug("システムプリファ@レンステーブル検索値：[" + l_strPreferenceName + "]");
        
        String l_strIsSubmitDelayOrder =
            GtlUtils.getTradingSystem().getPreference(l_strPreferenceName);
        
        // 発注遅延無視の会社かどうか判別する。
        boolean l_blnIsSubmitOrderDelayDisregard = false;
        if ("true".equals(l_strIsSubmitDelayOrder))
        {
            l_blnIsSubmitOrderDelayDisregard = true;
            log.debug("***** 発注遅延無視の会社 ***　@証券会社コード：[" + this.getInstitutionCode() + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsSubmitOrderDelayDisregard;
    }

    /**
     * (is信用強制決済実施)<BR>
     * <BR>
     * 信用強制決済実施会社であるかを判定する。 <BR>
     * 実施の場合はtrueを、以外、falseを返却する。 <BR>
     * <BR>
     * １）　@Rowオブジェクトより信用強制決済実施区分を取得する。 <BR>
     * <BR>
     * ２）　@１）の戻り値が0またはnullの場合、falseを、 <BR>
     * 　@　@　@以外、trueを返却する。<BR>
     * @@return boolean
     */
    public boolean isForcedSettleOrder()
    {
        final String STR_METHOD_NAME = "isForcedSettleOrder()";
        log.entering(STR_METHOD_NAME);

        //Rowオブジェクトより信用強制決済実施区分を取得する
        //信用強制決済実施区分が0またはnullの場合、falseを、 以外、trueを返却する
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_blnIsForcedsettleorder = true;

        if ((l_institutionRow.getForcedsettleorderDiv() == null)
            || (WEB3ForcedsettleorderDivDef.NOT_ENFORCEMENT.equals(
            l_institutionRow.getForcedsettleorderDiv())))
        {
            l_blnIsForcedsettleorder = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsForcedsettleorder;
    }

    /**
     * (is日計り取引採用)<BR>
     * 日計り取引会社であるか判定する。<BR>
     * 日計り取引会社の場合、trueを、日計り取引採用会社でない場合、falseを返却する。<BR>
     * <BR>
     * １）　@証券会社プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード ＝　@this.証券会社コード<BR>
     * 　@　@　@プリファ@レンス項目名 = プリファ@レンス名.外株日計り取引区分<BR>
     * 　@　@　@項目名連番 = 1<BR>
     * <BR>
     * ２）　@取得レコード.プリファ@レンスの値が"外株日計り取引採用"の場合、<BR>
     * 　@　@　@trueを返却する。<BR>
     * <BR>
     * 　@　@　@上記以外、または該当レコードが存在しない場合、falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isDayTradeAdoption() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDayTradeAdoption()";
        log.entering(STR_METHOD_NAME);

        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        //１）　@証券会社プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。
        try
        {
            l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                    this.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@取得レコード.プリファ@レンスの値が"外株日計り取引採用"の場合、
        //trueを返却する。
        if (l_institutionPreferencesRow != null
            && WEB3FeqDayTradeDivDef.EXECUTE.equals(l_institutionPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //上記以外、または該当レコードが存在しない場合、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isネッティング採用会社)<BR>
     * ネッティング採用会社であるか判定する。<BR>
     * ネッティング採用会社の場合、trueを、ネッティング採用会社でない場合、falseを返却する。<BR>
     * <BR>
     * １）　@証券会社プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード ＝　@this.証券会社コード<BR>
     * 　@　@　@プリファ@レンス項目名 = プリファ@レンス名.外株ネッティング会社区分<BR>
     * 　@　@　@項目名連番 = 1<BR>
     * <BR>
     * ２）　@取得レコード.プリファ@レンスの値が"外株ネッティング採用"の場合、<BR>
     * 　@　@　@trueを返却する。<BR>
     * <BR>
     * 　@　@　@上記以外、または該当レコードが存在しない場合、falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isNettingAdoptCompany() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNettingAdoptCompany()";
        log.entering(STR_METHOD_NAME);

        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        //１）　@証券会社プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。
        try
        {
            l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                    this.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.FEQ_NETTING_DIV,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@取得レコード.プリファ@レンスの値が"外株ネッティング採用"の場合、
        //trueを返却する。
        if (l_institutionPreferencesRow != null
            && WEB3FeqNettingDivDef.EXECUTE.equals(l_institutionPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //上記以外、または該当レコードが存在しない場合、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
