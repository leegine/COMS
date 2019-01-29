head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張債券注文マネージャ(WEB3BondOrderManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 周捷(中訊) 新規作成
                      : 2006/09/30 張騰宇 (中訊) モデル 095,102
                      : 2006/10/08 張騰宇 (中訊) モデル 111,115
                      : 2006/10/10 張騰宇 (中訊) モデル 123
                      : 2006/10/10 齊珂   (中訊) モデル 093
                      : 2006/10/10 張騰宇 (中訊) モデル 129,130
Revesion History : 2007/7/25 武波 (中訊) 仕様変更・モデルNo.219,230,235,245
Revesion History : 2007/7/27 武波 (中訊) 仕様変更・モデルNo.249
Revesion History : 2007/8/07 武波 (中訊) 仕様変更・モデルNo.250
Revesion History : 2007/8/16 何文敏 (中訊) 仕様変更・モデルNo.252
*/

package webbroker3.bd;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import webbroker3.bd.data.BondBranchConditionDao;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.bd.data.BondOrderAcceptActionRow;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AutoExecDivDef;
import webbroker3.common.define.WEB3BondAssetCheckDef;
import webbroker3.common.define.WEB3BondBranchRecruitLimitBranchCodeDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BondOrderLockUseDivDef;
import webbroker3.common.define.WEB3BondOrderSettleDivDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondOrderManagerImpl;



/**
 * (拡張債券注文マネージャ)<BR>
 * 拡張債券注文マネージャクラス
 * 
 * @@author 周捷(中訊)
 * @@version 1.0 
 */
public class WEB3BondOrderManager extends BondOrderManagerImpl
{   
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
    	WEB3LogUtility.getInstance(WEB3BondOrderManager.class);
    /**
     * @@roseuid 44E33621033C
     */
    public WEB3BondOrderManager() 
    {
     
    }
    
    /**
     * (validate取消可能状態)<BR>
     * 約定取消可能であるかチェックする。<BR>
     * <BR>
     * １）引数.債券注文単位.get注文約定区分 == 取消済の場合、<BR>
     * 　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00675<BR>
     * <BR>
     * ２）引数.債券注文単位.get注文約定区分() == 約定済<BR>
     * 　@　@かつ　@<BR>
     * 　@　@債券注文単位.get受渡日() < 取引時間管理.get発注日()の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02534<BR>
     * <BR>
     * ３）債券部店別条件を生成する。<BR>
     * 　@[引数]<BR>
     * 　@　@　@部店ID：債券注文単位.get部店ID'<BR>
     * <BR>
     * 　@３－１）債券部店別条件.get注文ロック使用区分＝＝'注文ロック区分を使用する'の場合、<BR>
     * 　@　@　@　@引数.債券注文単位.get注文約定区分＝＝未約定<BR>
     * 　@　@　@　@かつ<BR>
     * 　@　@　@　@引数.債券注文単位.get注文ロック区分＝＝ロック中の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02535<BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44B745ED033B
     */
    public void validateCancelPossibleStatus(BondOrderUnit l_bondOrderUnit) 
    	throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateCancelPossibleStatus(BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１）引数.債券注文単位.get注文約定区分 == 取消済の場合、 
        //例外をスローする。        
        BondOrderUnitRow l_bondOrderUnitRow = 
        	(BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Date l_datOrderUnitDeliveryDate = l_bondOrderUnitRow.getDeliveryDate();
        
        if (WEB3BondOrderExecStatusDef.CANCELED.equals(l_bondOrderUnitRow.getOrderExecStatus()))
        {
            log.debug("指定の注文が既に取消済みです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00675,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定の注文が既に取消済みです。");
        }      
        else if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnitRow.getOrderExecStatus()) 
    		&&  WEB3DateUtility.compareToDay(l_datOrderUnitDeliveryDate, l_datBizDate) < 0 )
        {
    	    //２）引数.債券注文単位.get注文約定区分() == 約定済 
    	    // かつ　@ 
    		//債券注文単位.get受渡日() < 取引時間管理.get発注日()の場合、 
    		//例外をスローする。        	
            log.debug("約定取消可能期間が過ぎました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02534,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定取消可能期間が過ぎました。");
        }

        //３）債券部店別条件を生成する。 
        //[引数] 
        //　@部店ID：債券注文単位.get部店ID
        BondBranchConditionRow l_bondBranchConditionRow = null;
    	try
    	{
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_bondOrderUnit.getBranchId());
    	}
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
		//３－１）債券部店別条件.get注文ロック使用区分＝＝'注文ロック区分を使用する'の場合、 
		//引数.債券注文単位.get注文約定区分＝＝未約定 
		//かつ 
		//引数.債券注文単位.get注文ロック区分＝＝ロック中の場合、 
		//例外をスローする。 
        if (l_bondBranchConditionRow != null)
        {
        	if (WEB3BondOrderLockUseDivDef.EXCEPT.equals(
                l_bondBranchConditionRow.getOrderLockUseDiv()))
        	{
        		if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_bondOrderUnitRow.getOrderExecStatus()) 
        			&& WEB3LockStatusDef.LOCKING.equals(l_bondOrderUnitRow.getLockStatus()))
        		{
	                log.debug("注文ロック中です。注文ロック解除してから操作してください。");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3BusinessLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_02535,
	                    this.getClass().getName() + "." + STR_METHOD_NAME,
	                    "注文ロック中です。注文ロック解除してから操作してください。");
        		}
        	}
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （get債券注文単位リスト）<BR>
     * 任意条件を指定して、債券注文単位テーブルを検索し、<BR>
     * 検索結果を拡張注文単位のリストに変換して返す。<BR>
     * <BR>
     * [パラメータ]<BR>
     * 条件文字列: 検索条件のWHERE文字列<BR>
     * 条件データコンテナ:  検索条件の設定値<BR>
     * @@param l_strQueryString - (条件文字列)<BR>
     * DAOに設定する検索条件のWHERE文字列<BR>
     * @@param l_objQueryDataContainers - (条件データコンテナ)<BR>
     * 検索条件の値の配列<BR>
     * @@return List
     * @@throws WEB3BaseException 
     * @@roseuid 44BC441300BF
     */
    public List getBondOrderUnitList(String l_strQueryString, Object[] l_objQueryDataContainers) 
    	throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getBondOrderUnitList(String, Object[])";
        log.entering(STR_METHOD_NAME);
        List l_lisQuerys = null;
        
        //任意条件を指定して、債券注文単位テーブルを検索し、
        //検索結果を拡張注文単位のリストに変換して返す。 
        try
        {
    		QueryProcessor l_qp = Processors.getDefaultProcessor();
    		l_lisQuerys = l_qp.doFindAllQuery(
				BondOrderUnitRow.TYPE, 
				l_strQueryString,
				null,
				l_objQueryDataContainers);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisQuerys;
    }
    
    /**
     * (validate（応募/買付）注文)<BR>
     * validate応募/買付注文<BR>
     * <BR>
     * シーケンス図「validate（応募/買付）注文」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_strDealDiv - (取引区分)<BR>
     * 取引区分<BR>
     * @@param l_strSettlementDiv - (決済区分)<BR>
     * 決済区分<BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44C470ED0324
     */
    public void validateRecruitOrBuyOrder(SubAccount l_subAccount, 
		WEB3BondProduct l_bondProduct, String l_strDealDiv, 
		String l_strSettlementDiv, double l_dblOrderQuantity) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" validateRecruitOrBuyOrder(SubAccount, WEB3BondProduct, String, String, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //1.1.validate外国証券口座開設(SubAccount, 債券銘柄)
		//顧客が外国証券口座を開設しているかチェックする。 
		//[validate外国証券口座開設()に渡す引数] 
		//　@補助口座：　@引数.補助口座 
		//　@債券銘柄：　@引数.債券銘柄
        this.validateFeqAccountOpen(l_subAccount, l_bondProduct);
        
        //1.2.validate顧客取扱可能銘柄(債券銘柄, String)
        //顧客取扱可能銘柄チェック、取引可能チェックを行なう。 
		//[validate顧客取扱可能銘柄()に渡す引数] 
		//　@債券銘柄：　@引数.債券銘柄 
		//　@取引区分：　@引数.取引区分
        this.validateAccountHandlingPossibleProduct(l_bondProduct, l_strDealDiv);
        
        //1.3.validate数量(double, 債券銘柄)
		//額面金額チェックを行なう。 
		//[validate数量()に渡す引数] 
		//　@数量：　@引数.注文数量 
		//　@債券銘柄：　@引数.債券銘柄
        this.validateQuantity(l_dblOrderQuantity, l_bondProduct);
        
        //1.4.validate決済区分(String, 債券銘柄)
		//決済区分をチェックする。 
		//[validate決済区分()に渡す引数] 
		//　@決済区分：　@引数.決済区分 
		//　@債券銘柄：　@引数.債券銘柄
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
        	new WEB3BondOrderManagerReusableValidationsCheck();
        l_validationsCheck.validateSettlementDiv(l_strSettlementDiv, l_bondProduct);

        //1.5.get自動約定区分( )
        //銘柄の自動約定区分を取得する。
        String l_strAutoExecDiv = l_bondProduct.getAutoExecDiv();
        
        //1.6.＜分岐処理＞get自動約定区分()の戻り値 == "自動約定"の場合
        if (WEB3AutoExecDivDef.AUTO_EXECUTE.equals(l_strAutoExecDiv))
        {
        	//1.6.1.validate自動約定枠(証券会社, 債券銘柄, String)
			//注文数量が自動約定枠内であるかどうかチェックする。 
			//[validate自動約定枠()に渡す引数] 
			//　@証券会社：　@引数.補助口座.getInstitution()の戻り値 
			//　@債券銘柄：　@引数.債券銘柄 
			//　@注文数量：　@引数.注文数量
        	this.validateAutoExecLimit(
    			l_subAccount.getInstitution(),
    			l_bondProduct, 
    			WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity));
        }
              
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate自動約定枠)<BR>
     * 注文数量が自動約定枠の範囲内であるかチェックを行う。<BR> 
     * <BR>
     * （* 債券発注審査個別チェック.validate自動約定枠()に委譲する。） <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_strOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CDA7B10161
     */
    public void validateAutoExecLimit(Institution l_institution,
		WEB3BondProduct l_bondProduct, String l_strOrderQuantity) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" validateAutoExecLimit(Institution, WEB3BondProduct, String)";
        log.entering(STR_METHOD_NAME);
        
        //（* 債券発注審査個別チェック.validate自動約定枠()に委譲する。）
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck =
        	new WEB3BondOrderManagerReusableValidationsCheck();
        l_validationsCheck.validateAutoExecLimit(l_institution, l_bondProduct, l_strOrderQuantity);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update債券注文ロック区分)<BR>
     * 債券注文単位テーブルの「注文ロック区分」を更新する。<BR>
     * <BR>
     * １）債券注文単位に該当するDBレコードに対して、以下の列を更新する。<BR>
     * 　@注文ロック区分＝引数.注文ロック区分<BR>
     * 　@更新日付　@　@　@＝現在日時<BR>
     * @@param l_strOrderLockDiv - (注文ロック区分)<BR>
     * 注文ロック区分<BR>
     * @@param l_bondOrderUnit - (拡張債券注文単位)<BR>
     * 拡張債券注文単位<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44C56C7902D1
     */
	public void updateBondOrderLockStatus(String l_strOrderLockDiv,
		WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
        	" updateBondOrderLockStatus(String, WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）債券注文単位に該当するDBレコードに対して、以下の列を更新する。
        try
        {
        	BondOrderUnitRow l_orderUnitRow = 
        		BondOrderUnitDao.findRowByOrderUnitId(l_bondOrderUnit.getOrderUnitId());
        	BondOrderUnitParams l_orderUnitParams = 
        		new BondOrderUnitParams(l_orderUnitRow);
        	
        	//注文ロック区分＝引数.注文ロック区分
        	l_orderUnitParams.setLockStatus(l_strOrderLockDiv);
        	//更新日付　@　@　@＝現在日時
        	l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        	
        	QueryProcessor l_qp = Processors.getDefaultProcessor();
        	l_qp.doUpdateQuery(l_orderUnitParams);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get債券注文単位By注文ID)<BR>
     * 引数の注文IDに該当する拡張債券注文単位を返却する<BR>
     * <BR>
     * １）　@注文オブジェクト取得 <BR>
     * 　@getOrder(注文ＩＤ)にて注文オブジェクトを取得する。 <BR>
     * 　@注文ＩＤに該当する注文単位が存在しない場合は例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02011<BR>
     * <BR>
     * ２）　@注文単位取得 <BR>
     * 　@注文.getOrderUnits()にて注文単位の配列を取得する。 <BR>
     * 　@注文ＩＤに該当する注文単位が２件以上あった場合<BR>
     * （配列の要素数が１でない場合）、例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02012<BR>
     * <BR>
     * 　@取得した注文単位を返却する。 <BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@return webbroker3.bd.WEB3BondOrderUnit
     * @@throws WEB3BaseException 
     * @@roseuid 44C6CBBA019F
     */
    public WEB3BondOrderUnit getBondOrderUnitByOrderId(
		long l_lngOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getBondOrderUnitByOrderId(long)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文オブジェクト取得
        Order l_order = null;
        try
        {
        	//getOrder(注文ＩＤ)にて注文オブジェクトを取得する。
        	l_order = getOrder(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文ＩＤに該当する注文単位が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２）　@注文単位取得 
        //注文.getOrderUnits()にて注文単位の配列を取得する。
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        if (l_orderUnits == null || l_orderUnits.length != 1)
        {
            log.debug("注文ＩＤに該当する注文単位が２件以上あったので、エラーになります。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文ＩＤに該当する注文単位が２件以上あったので、エラーになります。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return (WEB3BondOrderUnit)l_orderUnits[0];
    }
    
    /**
     * (get債券銘柄約定済残高)<BR>
     * 注文から最新の約定済残高値を取得。<BR>
     * <BR>
     * １）債券プロダクトマネージャーのget債券銘柄()を呼び出し、債券銘柄オブジェクトを取得<BR>
     * 　@[引数]<BR>
     * 　@証券会社：引数.証券会社<BR>
     * 　@銘柄コード(WEB3)：引数.銘柄コード(WEB3)<BR>
     * <BR>
     * ２）債券銘柄オブジェクト.get約定済残高()で累計値を取得<BR>
     * <BR>
     * ３）債券注文単位テーブルから当日分の自動約定残高の合計値を取得<BR>
     * ３－１）クエリプロセッサを取得する。 <BR>
     *　@　@Processors.getAccountProcessor(account_id) <BR>
     *　@　@[引数] <BR>
     *     account_id：0L <BR>
     *　@　@注意）他のアカウントも含めて検索するので、getDefaultProcessorは使用しない。<BR>
     * ３－2）債券注文単位テーブルを検索し、検索結果行オブジェクトリストを取得 。<BR> 
     * 　@　@［検索条件］ <BR>
     * 　@　@　@銘柄ID = 取得した債券銘柄オブジェクト.getProductID()<BR>
     * 　@　@　@host送信区分 = '未送信'<BR>
     * 　@　@　@自動約定区分 =  　@'1：自動約定'<BR>
     * 　@　@　@注文種別　@= '債券買付'<BR>
     * <BR>
     * ４）取得した注文単位行オブジェクトリストより注文の約定済残高の合計値を計算<BR>
     * ４－１）取得した検索結果の債券注文単位行オブジェクトリストに対し、<BR>
     * 下記の処理を繰り返して行う。<BR>
     * 　@　@　@注文単位行オブジェクト.get注文約定区分<>'取消済'の場合<BR>
     * 　@　@　@変数.A += 注文単位行オブジェクト.get注文数量()<BR>
     * 　@　@　@他の場合<BR>
     * 　@　@　@　@変数.B += 注文単位行オブジェクト.get約定数量()<BR>
     * ４－２）注文の約定済残高の合計値を計算<BR>
     * 　@　@　@変数.C = 変数.A - 変数.B<BR>
     * <BR>
     * ５）　@　@２）の累計値　@＋　@４）の変数.C を返す。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)<BR>
     * @@return double
     * @@throws WEB3BaseException 
     * @@roseuid 44C808B602C2
     */
    public double getBondProductExecutedBalance(
		Institution l_institution, String l_strProductCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" getBondProductExecutedBalance(Institution, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）債券プロダクトマネージャーのget債券銘柄()を呼び出し、債券銘柄オブジェクトを取得
        //[引数]
        //証券会社：引数.証券会社
        //銘柄コード(WEB3)：引数.銘柄コード(WEB3)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_productManager = 
        	(WEB3BondProductManager) l_finApp.getTradingModule(
    			ProductTypeEnum.BOND).getProductManager();
        
        WEB3BondProduct l_bondProduct;
		try
		{
			l_bondProduct = (WEB3BondProduct) l_productManager.getBondProduct(l_institution, l_strProductCode);
		}
		catch (NotFoundException l_ex)
		{
			log.error("__error in 債券銘柄オブジェクトを取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
		}
        
        //２）債券銘柄オブジェクト.get約定済残高()で累計値を取得
        double l_dblAutoExecAmount = l_bondProduct.getAutoExecAmount();
        
        //３）債券注文単位テーブルから当日分の自動約定残高の合計値を取得
        //３－１）クエリプロセッサを取得する。 
        //　@Processors.getAccountProcessor(account_id) 
        //　@[引数] 
        //   account_id：0L 
        //　@注意）他のアカウントも含めて検索するので、getDefaultProcessorは使用しない。
        
        QueryProcessor l_qp = null;
        try
        {
            l_qp = Processors.getAccountProcessor(0L);
        } 
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
        //３－2 ）債券注文単位テーブルを検索し、検索結果行オブジェクトリストを取得 。
        //   ［検索条件］
        //   銘柄ID = 取得した債券銘柄オブジェクト.getProductID()
        //   host送信区分 = '未送信'
        //   自動約定区分 =  　@'1：自動約定'
        //   注文種別　@= '債券買付'
        StringBuffer l_sbQuery = new StringBuffer();
        l_sbQuery.append(" product_id = ?");
        l_sbQuery.append(" and host_send_div = ?");
        l_sbQuery.append(" and auto_exec_div = ?");
        l_sbQuery.append(" and order_type = ? ");

        List l_lisResult = null;
        Object[] l_objQuerys = 
        	new Object[]{
        		new Long(l_bondProduct.getProductId()),
        		WEB3HostSendDivDef.UNSEND,
        		WEB3AutoExecDivDef.AUTO_EXECUTE,
        		new Long(OrderTypeEnum.BOND_BUY.intValue())};
        try
        {
        	l_lisResult = l_qp.doFindAllQuery(
    			BondOrderUnitRow.TYPE,
    			l_sbQuery.toString(),
    			null,
    			l_objQuerys);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
        double l_dblA = 0.0D;
        double l_dblB = 0.0D;
        double l_dblTotal = 0.0D;
        //４）取得した注文単位行オブジェクトリストより注文の約定済残高の合計値を計算 
        if (l_lisResult != null && !l_lisResult.isEmpty())
        {
	        for (int i = 0; i < l_lisResult.size(); i++)
	        {
	        	//４－１）取得した検索結果の債券注文単位行オブジェクトリストに対し、
	        	BondOrderUnitRow l_orderUnitRow = (BondOrderUnitRow) l_lisResult.get(i);
	        	WEB3BondOrderUnit l_orderUnit = new WEB3BondOrderUnit(l_orderUnitRow);
				//注文単位行オブジェクト.get注文約定区分<>'取消済'の場合 
	        	if (!WEB3BondOrderExecStatusDef.CANCELED.equals(l_orderUnit.getOrderExecStatus()))
	        	{
	        		//下記の処理を繰り返して行う。変数.A += 注文単位行オブジェクト.get注文数量() 
	        		l_dblA += l_orderUnitRow.getQuantity();
	        	}
	        	else
	        	{
	    			//他の場合 
	    			// 変数.B += 注文単位行オブジェクト.get約定数量() 
	        		l_dblB += l_orderUnitRow.getExecutedQuantity();
	        	}
	        }
	        
			//４－２）注文の約定済残高の合計値を計算 
			//　@　@変数.C = 変数.A - 変数.B 
	        l_dblTotal = l_dblA - l_dblB;
        }
        
        //５）　@　@２）の累計値　@＋　@４）の変数.C を返す。
        log.exiting(STR_METHOD_NAME);
        return l_dblAutoExecAmount + l_dblTotal;
    }
    
    /**
     * (validate債券新規注文)<BR>
     * validate債券新規注文<BR>
     * <BR>
     * シーケンス図「validate債券新規注文」を参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_bondNewOrderSpec - (拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容<BR>
     * @@param l_bondExecutDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CB1E6B0393
     */
    public void validateBondNewOrder(SubAccount l_subAccount, 
		WEB3BondNewOrderSpec l_bondNewOrderSpec, 
		WEB3BondExecuteDateInfo l_bondExecutDateInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateBondNewOrder(SubAccount, WEB3BondNewOrderSpec, " 
        	+ "WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_bondNewOrderSpec == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //1.1.getInstance( )
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
        	new WEB3BondOrderManagerReusableValidationsCheck();
        
        //1.2.validate取引可能顧客(SubAccount)
		//取引可能顧客であるかチェックする 
		//[validate取引可能顧客()の引数] 
		//補助口座：引数.補助口座
        WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
        
        OrderValidationResult l_validationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }
        
        //1.3.getQuantity( )
        double l_dblQuantity = l_bondNewOrderSpec.getQuantity();
        
        //1.4.getInstitution( )
        Institution l_institution = l_subAccount.getInstitution();
        
        //1.5.getProductCode( )
        String l_strProductCode = l_bondNewOrderSpec.getProductCode();
        
        //1.6.get債券銘柄(Institution, String)
        //債券銘柄オブジェクトを取得 
		//[get債券銘柄(証券会社 : Institution, 銘柄コード(WEB3) : String)の引数] 
		//証券会社：補助口座.getInstitution()の戻り値 
		//銘柄コード(WEB3)：取得した銘柄コード(WEB3) 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_productManager = 
        	(WEB3BondProductManager) l_finApp.getTradingModule(
    			ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = null;
        try
        {
	        l_bondProduct = 
	        	(WEB3BondProduct) l_productManager.getBondProduct(l_institution, l_strProductCode);
        }
        catch(NotFoundException l_ex)
        {
			log.error("__error in 債券銘柄オブジェクトを取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        //1.7.validate外国証券口座開設(SubAccount, 債券銘柄)
		//外国証券口座開設かチェックする 
		//[引数] 
		//補助口座：引数.補助口座 
		//債券銘柄：get債券銘柄
        this.validateFeqAccountOpen(l_subAccount, l_bondProduct);
        
        //1.8.get決済区分( )
        String l_strSettlementDiv = l_bondNewOrderSpec.getSettlementDiv();
        
        //1.9.validate決済区分(String, 債券銘柄)
		//決済区分をチェックする 
		//[引数] 
		//決済区分：get決済区分 
		//債券銘柄：get債券銘柄
        l_validationsCheck.validateSettlementDiv(l_strSettlementDiv, l_bondProduct);
        
        //1.10.isSellOrder( )
        boolean l_blnSellOrder = l_bondNewOrderSpec.isSellOrder();
        
        //1.11.売却注文の場合（isSellOrder( ) == true）
        if (l_blnSellOrder)
        {
        	//1.11.1.get取引店( )
        	Branch l_branch = l_subAccount.getMainAccount().getBranch();
        	
        	//1.11.2 債券部店別条件
            //[引数] 
            //部店ID： get取引店.get部店ID
            BondBranchConditionRow l_bondBranchConditionRow = null;
        	try
        	{
                l_bondBranchConditionRow = 
                    BondBranchConditionDao.findRowByPk(l_branch.getBranchId());
        	}
            catch (DataException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);             
            }
            
            //1.11.3get保有資産チェック区分
            //1.11.4 get保有資産チェック区分！＝　@'チェックしない' の場合
            if (l_bondBranchConditionRow == null ||
        		WEB3BondAssetCheckDef.DEFAULT.equals(l_bondBranchConditionRow.getAssetCheckDiv()))
            {
            	//1.11.4.1validate売却可能数量(補助口座, 拡張債券新規注文内容, 債券銘柄)
				//売却可能数量であるかチェック 
				//[validate売却可能数量()の引数] 
				//補助口座：引数.補助口座 
				//拡張債券新規注文内容：引数.拡張債券新規注文内容 
				//債券銘柄：get債券銘柄
            	l_validationsCheck.validateTransferedPossibleQuantity(
        			l_subAccount, l_bondNewOrderSpec, l_bondProduct);
            }
        }
        else
        {
        	//1.12.買付注文の場合（isSellOrder( ) == false）
        	//1.12.1get自動約定区分( )   	
        	//1.12.2.get自動約定区分＝＝自動約定の場合
        	if (WEB3AutoExecDivDef.AUTO_EXECUTE.equals(l_bondProduct.getAutoExecDiv()))
        	{
        		//1.12.2.1.validate自動約定枠(証券会社, 債券銘柄, String)
				//自動約定枠のチェック 
				//[validate自動約定枠()の引数] 
				//証券会社：getInstitution 
				//債券銘柄：get債券銘柄 
				//注文数量：getQuantity
        		l_validationsCheck.validateAutoExecLimit(
    				l_institution, l_bondProduct, WEB3StringTypeUtility.formatNumber(l_dblQuantity));
        	}
        }
		log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate約定可能状態)<BR>
     * validate約定可能状態<BR>
     * <BR>
     * １）引数.債券注文単位.get注文約定区分＝＝取消済の場合、<BR>
     * 　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00675<BR>
     * <BR>
     * ２）引数.債券注文単位.get注文約定区分＝＝約定済<BR>
     * 　@　@かつ<BR>
     * 　@引数.債券注文単位.get発注日＜取引時間管理.get発注日の場合、<BR>
     * 　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02632<BR>
     * <BR>
     * ３）債券部店別条件を生成する。<BR>
     * 　@ [引数] <BR>
     * 　@　@　@部店ID：債券注文単位.get部店ID <BR>
     * 　@３－１）債券部店別条件.get注文ロック使用区分＝＝'注文ロック区分を使用する'の場合、<BR>
     * 　@　@　@　@引数.債券注文単位.get注文約定区分＝＝未約定<BR>
     * 　@　@　@　@かつ<BR>
     * 　@　@　@　@引数.債券注文単位.get注文ロック区分＝＝ロック解除中の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02633<BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CB430A010D
     */
    public void validateExecutePossibleStatus(
		BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateExecutePossibleStatus(BondOrderUnit) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        BondOrderUnitRow l_orderUnitRow = 
        	(BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
        //１）引数.債券注文単位.get注文約定区分＝＝取消済の場合、 
        // 例外をスローする。 
        WEB3BondOrderUnit l_orderUnit = new WEB3BondOrderUnit(l_orderUnitRow);
    	if (WEB3BondOrderExecStatusDef.CANCELED.equals(l_orderUnit.getOrderExecStatus()) )
        {
            log.debug("指定の注文が既に取消済みです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00675,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定の注文が既に取消済みです。");
        }
        
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Date l_datOrderUnitBizDate =
        	WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        
		//２）引数.債券注文単位.get注文約定区分＝＝約定済 
		// かつ 
		//引数.債券注文単位.get発注日＜取引時間管理.get発注日の場合、 
		//例外をスローする。
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus())
    		&& WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) < 0)
        {
            log.debug("約定変更可能期間が過ぎました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02632,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定変更可能期間が過ぎました。");
        }
        
        //３）債券部店別条件を生成する。 
        //[引数] 
        //　@部店ID：債券注文単位.get部店ID
        BondBranchConditionRow l_bondBranchConditionRow = null;
    	try
    	{
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_bondOrderUnit.getBranchId());
    	}
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
		//３－１）債券部店別条件.get注文ロック使用区分＝＝'注文ロック区分を使用する'の場合、
		//引数.債券注文単位.get注文約定区分＝＝未約定 
		//かつ 
		//引数.債券注文単位.get注文ロック区分＝＝ロック解除中の場合、 
		//例外をスローする。 
        if (l_bondBranchConditionRow != null)
        {
        	if (WEB3BondOrderLockUseDivDef.EXCEPT.equals(l_bondBranchConditionRow.getOrderLockUseDiv())
    			&& WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus())
    			&& WEB3LockStatusDef.RELEASING.equals(l_orderUnit.getLockStatus()))
        	{
                log.debug("注文ロック解除中です。注文ロックしてから操作してください。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02633,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文ロック解除中です。注文ロックしてから操作してください。");
        	}
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate数量)<BR>
     * 数量チェックを行う。 <BR>
     * <BR>
     * （* 債券発注審査個別チェック.validate数量()に委譲する。） <BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2B14602DF
     */
    public void validateQuantity(double l_dblOrderQuantity, WEB3BondProduct l_bondProduct) 
    	throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateQuantity(double, WEB3BondProduct) " ;
        log.entering(STR_METHOD_NAME);
        
        //（* 債券発注審査個別チェック.validate数量()に委譲する。）
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
        	new WEB3BondOrderManagerReusableValidationsCheck();
        
        l_validationsCheck.validateQuantity(l_dblOrderQuantity, l_bondProduct);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create債券約定日情報)<BR>
     * 債券約定日情報を作成する。<BR>
     * <BR>
     * １）約定日を取得する。<BR>
     * 　@　@[引数.債券銘柄.get約定日()に渡す引数]<BR>
     * 　@　@　@発注日：　@引数.発注日<BR>
     * <BR>
     * ２）受渡日を取得する。<BR>
     * 　@　@[引数.債券銘柄.get受渡日()に渡す引数]<BR>
     * 　@　@　@約定日：　@取得した約定日<BR>
     * 　@　@　@債券注文種別判定：　@引数.債券注文種別判定<BR>
     * <BR>
     *３）入金日を取得する。  <BR>
     *　@　@[引数.債券銘柄.get入金日()に渡す引数]  <BR>
     *　@　@　@約定日：　@取得した約定日  <BR>
     *　@　@　@債券注文種別判定：　@引数.債券注文種別判定  <BR>
     *　@　@　@決済区分：　@引数.決済区分  <BR>
     *　@　@　@部店：　@引数.部店 <BR>
     *<BR>
     * ４）引数.債券銘柄.is外国債券()の戻り値 == true の場合<BR>
     * <BR>
     * 　@　@４－１）現地発注日を取得する。<BR>
     * 　@　@　@　@　@　@　@[引数.債券銘柄.get現地発注日()に渡す引数]<BR>
     * 　@　@　@　@　@　@　@　@　@発注日：　@引数.発注日<BR>
     * <BR>
     * 　@　@４－２）現地約定日を取得する。<BR>
     * 　@　@　@　@　@　@　@[引数.債券銘柄.get現地約定日()に渡す引数]<BR>
     * 　@　@　@　@　@　@　@　@　@現地発注日：　@取得した現地発注日<BR>
     * <BR>
     * 　@　@４－３）現地受渡日を取得する。<BR>
     * 　@　@　@　@　@　@　@[引数.債券銘柄.get現地受渡日()に渡す引数]<BR>
     * 　@　@　@　@　@　@　@　@　@現地約定日：　@取得した現地約定日<BR>
     * 　@　@　@　@　@　@　@　@　@債券注文種別判定：　@引数.債券注文種別判定<BR>
     * <BR>
     * ４）債券約定日情報を生成し、<BR>
     * 取得した日付情報と引数の発注日をそれぞれセットして返却する。<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_strSettleDiv - (決済区分)<BR>
     * 決済区分
     * @@param l_branch - (部店)<BR>
     * 部店
     * @@return WEB3BondExecuteDateInfo
     * @@throws WEB3BaseException 
     * @@roseuid 44D2B33102F4
     */
    public WEB3BondExecuteDateInfo createBondExecutionDateInfo(Date l_datBizDate, 
		WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
		WEB3BondProduct l_bondProduct, String l_strSettleDiv, Branch l_branch) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" createBondExecutionDateInfo(Date, WEB3BondOrderTypeJudge, WEB3BondProduct, String, Branch) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();
        
		//１）約定日を取得する。 
		//[引数.債券銘柄.get約定日()に渡す引数] 
		//発注日：　@引数.発注日 
        Date l_datExecDate = l_bondProduct.getExecDate(l_datBizDate);
        l_bondExecuteDateInfo.setExecuteDate(l_datExecDate);
        
		//２）受渡日を取得する。 
		//[引数.債券銘柄.get受渡日()に渡す引数] 
		//約定日：　@取得した約定日 
		//債券注文種別判定：　@引数.債券注文種別判定 
        Date l_datDeliveryDate = 
        	l_bondProduct.getDeliveryDate(l_datExecDate, l_bondOrderTypeJudge);
        l_bondExecuteDateInfo.setDeliveryDate(l_datDeliveryDate);
        
        //３）入金日を取得する。 
        // [引数.債券銘柄.get入金日()に渡す引数] 
        //  約定日：　@取得した約定日 
        //  債券注文種別判定：　@引数.債券注文種別判定 
        //  決済区分：　@引数.決済区分 
        //  部店：　@引数.部店 
        Date l_datPaymentDate = 
            l_bondProduct.getPaymentDate(
                l_datExecDate, l_bondOrderTypeJudge, l_strSettleDiv, l_branch);
        l_bondExecuteDateInfo.setPaymentDate(l_datPaymentDate);
        
		//４）引数.債券銘柄.is外国債券()の戻り値 == true の場合 
        if (l_bondProduct.isForeignBond())
        {
			//４－１）現地発注日を取得する。 
			//[引数.債券銘柄.get現地発注日()に渡す引数] 
			//発注日：　@引数.発注日 
        	Date l_datForeignBizDate = l_bondProduct.getForeignBizDate(l_datBizDate);
        	l_bondExecuteDateInfo.setForeignBizDate(l_datForeignBizDate);
        	
			//４－２）現地約定日を取得する。 
			//[引数.債券銘柄.get現地約定日()に渡す引数] 
			//現地発注日：　@取得した現地発注日 
        	Date l_datForeignExecDate = 
        		l_bondProduct.getForeignExecDate(l_datForeignBizDate);
        	l_bondExecuteDateInfo.setForeignExecuteDate(l_datForeignExecDate);
        	
			//４－３）現地受渡日を取得する。 
			//[引数.債券銘柄.get現地受渡日()に渡す引数] 
			//現地約定日：　@取得した現地約定日 
			//債券注文種別判定：　@引数.債券注文種別判定 
        	Date LdatForeignDeliveryDate = 
        		l_bondProduct.getForeignDeliveryDate(
    				l_datForeignExecDate, l_bondOrderTypeJudge);
        	l_bondExecuteDateInfo.setForeignDeliveryDate(LdatForeignDeliveryDate);
        }
        
		//５）債券約定日情報を生成し、取得した
        //日付情報と引数の発注日をそれぞれセットして返却する。
        l_bondExecuteDateInfo.setBizDate(l_datBizDate);
        
        log.exiting(STR_METHOD_NAME);
    	return l_bondExecuteDateInfo;
    }
    
    /**
     * (validate外国証券口座開設)<BR>
     * 顧客が外国証券口座を開設しているかチェックする。<BR>
     * <BR>
     * １）引数.債券銘柄.is外国債券()の戻り値 == true の場合、以下の処理を行なう。<BR>
     * <BR>
     * 　@１－１）引数.補助口座.getMainAccount()をコールして、顧客を取得する。<BR>
     * <BR>
     * 　@１－２）顧客.is外国証券口座開設()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@「外国証券口座未開設エラー」<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_01341<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2DFC7030C
     */
    public void validateFeqAccountOpen(
		SubAccount l_subAccount, WEB3BondProduct l_bondProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateFeqAccountOpen(SubAccount, l_subAccount, WEB3BondProduct) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）引数.債券銘柄.is外国債券()の戻り値 == true の場合、以下の処理を行なう。
        if (l_bondProduct.isForeignBond())
        {
    		//１－１）引数.補助口座.getMainAccount()をコールして、顧客を取得する。
        	WEB3GentradeMainAccount l_mainAccount = 
        		(WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        	
        	//１－２）顧客.is外国証券口座開設()の戻り値 == false の場合、例外をスローする。
        	//「外国証券口座未開設エラー」
        	if (!l_mainAccount.isForeignAccountOpen())
        	{
                log.debug("当該顧客は外国証券口座開設なし。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "当該顧客は外国証券口座開設なし。");
        	}
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文ロック区分更新可能状態)<BR>
     * 注文ロック区分を更新可能であるかチェックする<BR>
     * <BR>
     * １）注文約定区分を判定<BR>
     * 　@　@１－１）拡張債券注文単位から注文約定区分を取得する。<BR>
     * 　@　@１－２）注文約定区分！＝未約定の場合、<BR>
     * 　@　@エラー「対象注文は既に未約定ではありません。」をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02619<BR>
     * <BR>
     *２）債券注文ロック使用区分をチェック <BR>
     *　@　@２－１）債券部店別条件を生成する。 <BR>
     *     　@　@　@　@[引数] <BR>
     *    　@　@ 　@　@部店ID：拡張債券注文単位.get部店ID <BR>
     *　@　@２－２）債券部店別条件.get注文ロック使用区分！＝'注文ロック区分を使用する'の場合、 <BR>
     *　@　@　@　@　@エラー「注文ロック区分を使用する部店ではありません。」をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02620<BR>
     * @@param l_bondOrderUnit - (拡張債券注文単位)<BR>
     * 拡張債券注文単位<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D4778E0157
     */
    public void validateOrderLockDivUpdatePossibleStatus(
		WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
    	final String STR_METHOD_NAME =
        	" validateOrderLockDivUpdatePossibleStatus(WEB3BondOrderUnit) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）注文約定区分を判定
        //１－１）拡張債券注文単位から注文約定区分を取得する。
        //１－２）注文約定区分！＝未約定の場合、
        //エラー「対象注文は既に未約定ではありません。」をスローする。
        if (!WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()))
        {
            log.debug("対象注文は既に未約定ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02619,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "対象注文は既に未約定ではありません。");
        }
        
        //２）債券注文ロック使用区分をチェック 
        //　@２－１）債券部店別条件を生成する。 
        //   　@　@　@　@[引数] 
        //  　@　@ 　@　@部店ID：拡張債券注文単位.get部店ID
        BondBranchConditionRow l_bondBranchConditionRow = null;
    	try
    	{
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_bondOrderUnit.getBranchId());
    	}
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
        //２－２）債券部店別条件.get注文ロック使用区分！＝'注文ロック区分を使用する'の場合、
        //エラー「注文ロック区分を使用する部店ではありません。」をスローする。
        if (l_bondBranchConditionRow == null || 
    		WEB3BondOrderLockUseDivDef.DEFAULT.equals(l_bondBranchConditionRow.getOrderLockUseDiv()))
        {
        	log.debug("注文ロック区分を使用する部店ではありません。");
        	log.exiting(STR_METHOD_NAME);
        	throw new WEB3BusinessLayerException(
    			WEB3ErrorCatalog.BUSINESS_ERROR_02620,
    			this.getClass().getName() + "." + STR_METHOD_NAME,
        	"注文ロック区分を使用する部店ではありません。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get債券注文単位)<BR>
     * get債券注文単位<BR>
     * <BR>
     * getOrderUnit(long)のオーバーライドメソッド<BR>
     * <BR>
     * １）　@引数.注文単位IDをもとに債券注文単位Rowオブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@this.toOrderUnit()の戻り値を返す。 <BR>
     * 　@[toOrderUnit()に指定する引数] <BR>
     * 　@　@取得した債券注文単位Rowオブジェクト<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@roseuid 44D485A202D0
     */
    public OrderUnit getOrderUnit(long l_lngOrderUnitId)
    {
        final String STR_METHOD_NAME = " getBondOrderUnit(long) " ;
        log.entering(STR_METHOD_NAME);
        
        try
		{
        	//getOrderUnit(long)のオーバーライドメソッド
        	//１）　@引数.注文単位IDをもとに債券注文単位Rowオブジェクトを取得する。
        	BondOrderUnit l_orderUnit = (BondOrderUnit) super.getOrderUnit(l_lngOrderUnitId);
        	BondOrderUnitRow l_orderUnitRow = 
        		(BondOrderUnitRow)l_orderUnit.getDataSourceObject();
        	
        	//２）　@this.toOrderUnit()の戻り値を返す。
        	//[toOrderUnit()に指定する引数] 
        	//取得した債券注文単位Rowオブジェクト
            log.exiting(STR_METHOD_NAME);
        	return toOrderUnit(l_orderUnitRow);
		}
        catch (NotFoundException l_ex)
        {
            log.error("__error in 債券注文単位Rowオブジェクトを取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (to債券注文単位)<BR>
     * to債券注文単位<BR>
     * <BR>
     * toOrderUnit(Row)のオーバーライド<BR>
     * <BR>
     * 引数.行オブジェクトをもとに拡張債券注文単位オブジェクトを生成し、返却する。<BR>
     * @@param l_row - (行オブジェクト)<BR>
     * 行オブジェクト<BR>
     * @@return OrderUnit
     * @@roseuid 44D485B302B1
     */
    public OrderUnit toOrderUnit(Row l_row) 
    {
        final String STR_METHOD_NAME = " toOrderUnit(Row) " ;
        log.entering(STR_METHOD_NAME);
        
        //toOrderUnit(Row)のオーバーライド
        //引数.行オブジェクトをもとに拡張債券注文単位オブジェクトを生成し、返却する。
        WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit((BondOrderUnitRow)l_row);
        
        log.exiting(STR_METHOD_NAME);
        return l_bondOrderUnit;
    }
    
    /**
     * (validate注文取消可能状態)<BR>
     * 注文取消が可能かチェックを行う。<BR> 
     * <BR>
     * （* 債券発注審査個別チェック.validate注文取消可能状態()に委譲する。） <BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D6C46403B8
     */
    public void validateOrderCancelPossibleStatus(
		WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateOrderCancelPossibleStatus(WEB3BondOrderUnit) " ;
        log.entering(STR_METHOD_NAME);
        
        //（* 債券発注審査個別チェック.validate注文取消可能状態()に委譲する。）
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
        	new WEB3BondOrderManagerReusableValidationsCheck();
        
        l_validationsCheck.validateTransferedPossibleDays(l_bondOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate顧客取扱可能銘柄)<BR>
     * 顧客向けに取扱可能な銘柄かどうかかチェックする。<BR>
     * <BR>
     * １）引数.債券銘柄.is顧客取扱可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢顧客取扱不可銘柄エラー。｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02661<BR>
     * <BR>
     * ２）引数.取引区分　@==　@応募　@の場合<BR>
     * 　@引数.債券銘柄.is応募可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢応募不可銘柄エラー。｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02612<BR>
     * <BR>
     * ３）引数.取引区分　@==　@買付　@の場合<BR>
     * 　@引数.債券銘柄.is買付可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢買付不可銘柄エラー。｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02613<BR>
     * <BR>
     * ４）引数.取引区分　@==　@売却　@の場合<BR>
     * 　@引数.債券銘柄.is売却可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢売却不可銘柄エラー。｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02614<BR>
     * 　@
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_strDealDiv - (取引区分)<BR>
     * 取引区分<BR>
     * <BR>
     * 1：　@応募<BR>
     * 2：　@買付<BR>
     * 3：　@売却<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D843A00160
     */
    public void validateAccountHandlingPossibleProduct(
		WEB3BondProduct l_bondProduct, String l_strDealDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" validateAccountHandlingPossibleProduct(WEB3BondProduct, String) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）引数.債券銘柄.is顧客取扱可能()の戻り値 == false の場合、例外をスローする。
        if (!l_bondProduct.isCustomerTradePossible())
        {
            log.debug("顧客取扱不可銘柄エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02661,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客取扱不可銘柄エラー。");
        }
        
        //２）引数.取引区分　@==　@応募　@の場合
        //引数.債券銘柄.is応募可能()の戻り値 == false の場合、例外をスローする。
        if (WEB3BondDealDivDef.RECRUIT.equals(l_strDealDiv))
        {
        	if (!l_bondProduct.isRecruitPossible())
            {
                log.debug("応募不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02612,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "応募不可銘柄エラー。");
            }
        }
        
        //３）引数.取引区分　@==　@買付　@の場合
        //引数.債券銘柄.is買付可能()の戻り値 == false の場合、例外をスローする。
        if (WEB3BondDealDivDef.BUY.equals(l_strDealDiv))
        {
        	if (!l_bondProduct.isBuyPossible())
            {
                log.debug("買付不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02613,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買付不可銘柄エラー。");
            }
        }
        
        //４）引数.取引区分　@==　@売却　@の場合
        //引数.債券銘柄.is売却可能()の戻り値 == false の場合、例外をスローする。
        if (WEB3BondDealDivDef.SELL.equals(l_strDealDiv))
        {
        	if (!l_bondProduct.isSellPossible())
            {
                log.debug("売却不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
            		WEB3ErrorCatalog.BUSINESS_ERROR_02614,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売却不可銘柄エラー。");
            }
        }     
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate約定上限数量)<BR>
     * 約定上限数量チェックを行う。 <BR>
     * <BR>
     * １）債券注文単位.get注文数量　@＜　@引数.約定数量の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00300<BR>
     * ２）債券注文単位.注文約定区分＝約定済　@<BR>
     * 　@　@　@かつ　@　@<BR>
     * 　@　@債券注文単位.注文種別判定.is売却＝trueの場合、売却可能数量をチェックする。　@<BR>
     * 　@２－１）引数.約定数量＜＝約定済数量の場合、処理をせずにreturnする。　@<BR>
     * 　@２－２）債券部店別条件テーブルから該当レコードを取得する。　@<BR>
     * 　@２－３）取得した債券部店別条件.get保有資産チェック区分（）の戻り値 = ”チェックしない”の場合、処理をせずにreturnする。　@<BR>
     * 　@２－４）債券発注審査個別チェック.validate売却可能数量を呼ぶ　@<BR>
     * 　@　@　@　@　@[引数]　@<BR>
     * 　@　@　@　@　@　@　@補助口座：拡張アカウントマネージャ.get補助口座(債券注文単位.口座ID、債券注文単位.補助口座ID)　@<BR>
     * 　@　@　@　@　@　@　@拡張債券新規注文内容：拡張債券新規注文内容.create拡張債券新規注文内容()　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@[引数]　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@オペレータ： null　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@債券注文種別判定： 債券注文単位.get債券注文種別判定　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@銘柄コード(WEB3)： 債券銘柄.銘柄コード(WEB3)　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@数量： 約定数量　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@単価： 債券注文単位.約定単価　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@税区分： 債券注文単位.税区分　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@受渡日： 債券注文単位.受渡日　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@決済区分： 債券注文単位.決済区分　@<BR>
     * 　@　@　@　@　@　@　@債券銘柄：債券プロダクトマネージャ.get債券銘柄(債券注文単位.銘柄ID)　@<BR>
     * @@param l_dblOrderQuantity - (約定数量)<BR>
     * 注文数量<BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D9D485025D
     */
    public void validateExecuteMaxQuantity(
		double l_dblOrderQuantity, WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" validateExecuteMaxQuantity(double, WEB3BondOrderUnit) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）債券注文単位.get注文数量　@＜　@引数.約定数量の場合、
        //    例外をスローする。
        if (l_bondOrderUnit.getQuantity() < l_dblOrderQuantity)
        {
            log.debug("約定数量が注文数量を超えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00300,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定数量が注文数量を超えています。");
        }
        //２）債券注文単位.注文約定区分＝約定済
        //      かつ  
        //    債券注文単位.注文種別判定.is売却＝trueの場合、売却可能数量をチェックする。
        WEB3BondOrderTypeJudge l_orderTypeJudge = l_bondOrderUnit.getBondOrderTypeJudge();
        
        if(WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnit.getOrderExecStatus())
           && l_orderTypeJudge.isSellOrder())
        {

            //  ２－１）引数.約定数量＜＝約定済数量の場合、処理をせずにreturnする。
            if(l_dblOrderQuantity <= l_bondOrderUnit.getExecutedQuantity())
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
            double l_dblSellCheckQuantity = l_dblOrderQuantity - l_bondOrderUnit.getExecutedQuantity();

            try
            {
                //  ２－２）債券部店別条件テーブルから該当レコードを取得する。
                WEB3BondBranchCondition l_branchCondition = new WEB3BondBranchCondition(l_bondOrderUnit.getBranchId());

                //  ２－３）取得した債券部店別条件.get保有資産チェック区分（）の戻り値 = ”チェックしない”の場合、処理をせずにreturnする。
                if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return;
                }

            }
            catch (WEB3BaseException l_ex)
            {
                log.error("__error in 部店用プリファ@レンステーブルからレコードを取得__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //  ２－４）債券発注審査個別チェック.validate売却可能数量を呼ぶ
            //          [引数]
            //              補助口座：拡張アカウントマネージャ.get補助口座(債券注文単位.口座ID、債券注文単位.補助口座ID)
            //              拡張債券新規注文内容：拡張債券新規注文内容.create拡張債券新規注文内容()
            //                                              [引数]
            //                                                オペレータ： null
            //                                                債券注文種別判定： 債券注文単位.get債券注文種別判定
            //                                                銘柄コード(WEB3)： 債券銘柄.銘柄コード(WEB3)
            //                                                数量： 新規入力数量 - 引数.約定数量
            //                                                単価： 債券注文単位.約定単価
            //                                                税区分： 債券注文単位.税区分
            //                                                受渡日： 債券注文単位.受渡日
            //                                                決済区分： 債券注文単位.決済区分
            //              債券銘柄：債券プロダクトマネージャ.get債券銘柄(債券注文単位.銘柄ID)        
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                        l_bondOrderUnit.getAccountId(), l_bondOrderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in 拡張アカウントマネージャから顧客を取得__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            WEB3BondProductManager l_bondProductManager = 
                (WEB3BondProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getProductManager();
            WEB3BondProduct l_bondProduct = 
                (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProductId());
            
            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                WEB3BondNewOrderSpec.createBondNewOrderSpec(
                null,
                l_orderTypeJudge,
                l_bondProduct.getProductCode(),
                l_dblSellCheckQuantity,
                l_bondOrderUnit.getExecutedPrice(),
                l_bondOrderUnit.getTaxType(),
                l_bondOrderUnit.getDeliveryDate(),
                l_bondOrderUnit.getSettlementDiv());
            
            WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
                new WEB3BondOrderManagerReusableValidationsCheck();
            
            l_validationsCheck.validateTransferedPossibleQuantity(l_subAccount, l_bondNewOrderSpec, l_bondProduct);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate管理者取扱可能銘柄)<BR>
     * 管理者向けに取扱可能な銘柄かどうかかチェックする。<BR>
     * <BR>
     * １）引数.債券銘柄.is管理者取扱可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢取扱不可銘柄エラー｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00362<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44DC00C70277
     */
    public void validateAdminTradingPossibleProduct(WEB3BondProduct l_bondProduct)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateAdminTradingPossibleProduct(WEB3BondProduct) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //管理者向けに取扱可能な銘柄かどうかかチェックする。
        //１）引数.債券銘柄.is管理者取扱可能()の戻り値 == false の場合、例外をスローする。
        if (!l_bondProduct.isAdminTradePossible())
        {
            log.debug("取扱不可銘柄エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱不可銘柄エラー。");
        }       
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate売却注文)<BR>
     * validate売却注文<BR>
     * <BR>
     * シーケンス図「validate売却注文」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄
     * @@param l_bondNewOrderSpec - (拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容
     * @@throws WEB3BaseException 
     */
    public void validateSellOrder(
        SubAccount l_subAccount,
        WEB3BondProduct l_bondProduct,
        WEB3BondNewOrderSpec l_bondNewOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateSellOrder(SubAccount, WEB3BondProduct, WEB3BondNewOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_bondNewOrderSpec == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //1.1)validate外国証券口座開設()
        this.validateFeqAccountOpen(l_subAccount, l_bondProduct);
        
        //1.2)validate顧客取扱可能銘柄()
        this.validateAccountHandlingPossibleProduct(
            l_bondProduct, WEB3BondDealDivDef.SELL);
        
        //1.3) validate数量()
        this.validateQuantity(l_bondNewOrderSpec.getQuantity(), l_bondProduct);
        
        //1.4)validate決済区分()
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
            new WEB3BondOrderManagerReusableValidationsCheck();
        l_validationsCheck.validateSettlementDiv(
            l_bondNewOrderSpec.getSettlementDiv(), l_bondProduct);
        
        //1.5)債券部店別条件テーブルから該当レコードを取得する。 
        long l_lngBranchId = l_subAccount.getMainAccount().getBranch().getBranchId();
        
        BondBranchConditionRow l_bondBranchConditionRow = null;
        try
        {
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_lngBranchId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6)＜分岐処理＞取得した債券部店別条件.get保有資産チェック区分（）の戻り値 != ”チェックしない”の場合
        if (l_bondBranchConditionRow == null || 
            !WEB3BondAssetCheckDef.EXCEPT.equals(l_bondBranchConditionRow.getAssetCheckDiv()))
        {
            //1.6.1)validate売却可能数量()
            l_validationsCheck.validateTransferedPossibleQuantity(
                l_subAccount, l_bondNewOrderSpec, l_bondProduct);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate為替レート)<BR>
     * validate為替レート <BR>
     * <BR>
     * １）引数.債券銘柄.is外貨建()＝＝true <BR>
     * 　@　@かつ <BR>
     * 　@ 引数.為替レート＝＝nullの場合 <BR>
     * 　@ 例外をスローする <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02643<BR>
     * ２）引数.債券銘柄.is外貨建()＝＝false <BR>
     *　@　@かつ <BR>
     *　@ 引数.為替レート !＝nullの場合 <BR>
     *　@ 例外をスローする <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02678<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_strFxRate - (為替レート)<BR>
     * 為替レート<BR>
     * @@throws WEB3BaseException 
     */
    public void validateFxRate(WEB3BondProduct l_bondProduct, String l_strFxRate) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
        	" validateFxRate(WEB3BondProduct, String) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１）引数.債券銘柄.is外貨建()＝＝true かつ
        //引数.為替レート＝＝nullの場合,例外をスローする
        if (l_bondProduct.isForeignCurrency() 
            && WEB3StringTypeUtility.isEmpty(l_strFxRate))
        {
            log.debug("為替レートが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02643,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "為替レートが未指定です。");
        }
        
        //２）引数.債券銘柄.is外貨建()＝＝false 
        //　@かつ 
        // 引数.為替レート !＝nullの場合 
        // 例外をスローする 
        if (!l_bondProduct.isForeignCurrency() 
            && !WEB3StringTypeUtility.isEmpty(l_strFxRate))
        {
            log.debug("債券銘柄が“外貨建”以外の場合は、為替レートがnull以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02678,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "債券銘柄が“外貨建”以外の場合は、為替レートがnull以外の値です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get為替レート)<BR>
     *為替レートを取得する。 <BR>
     *<BR>
     *１）入力為替レート != null and <BR>
     *　@　@入力為替レート != 0　@　@の場合、 <BR>
     *　@　@　@入力為替レートをreturnする。<BR>
     *2）（債券注文種別判定.is応募注文 == true or  <BR>
     *　@ 債券注文種別判定.is買付注文 == true） and  <BR>
     *　@　@決済区分 == "外貨" and <BR>
     *　@　@債券銘柄.仕入時の為替レート ≠ null の場合、 <BR>
     *<BR>
     *　@　@　@債券銘柄.仕入時の為替レートをreturnする。 <BR>
     *<BR>
     *3）上記以外の場合、 <BR>
     *<BR>
     *　@　@3－１）債券銘柄.get通貨()により（共通）通貨を取得する。 <BR>
     *<BR>
     *　@　@３－２）（共通）通貨.get為替レート()により取得した為替レートをreturnする。 <BR>
     *<BR>
     *　@　@　@　@　@　@　@　@[引数] <BR>
     *　@　@　@　@　@　@　@　@　@　@is買付：　@債券注文種別判定.is応募注文 == true or  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@債券注文種別判定.is買付注文 == true の場合、 <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@true をセットする。 <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@債券注文種別判定.is売却注文 == true の場合、 <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@false をセットする。 <BR>
     *　@　@　@　@　@　@　@　@　@　@is約定計算：　@is約定計算 <BR>
     *　@　@　@　@　@　@　@　@　@　@入力為替レート：　@入力為替レート<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_orderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_strSettleDiv - (決済区分)<BR>
     * 決済区分<BR>
     * @@param l_bdFxRate - (入力為替レート)<BR>
     * 入力為替レート<BR>
     * @@param l_blnIsExecuteCalc - (is約定計算)<BR>
     * is約定計算<BR>
     * <BR>
     * true：　@約定計算<BR>
     * false：　@概算計算<BR>
     * @@throws WEB3BaseException
     * @@return BigDecimal
     */
    public BigDecimal  getFxRate(
        WEB3BondProduct l_bondProduct,
        WEB3BondOrderTypeJudge l_orderTypeJudge, 
        String l_strSettleDiv,
        BigDecimal l_bdFxRate,
        boolean l_blnIsExecuteCalc) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getFxRate(WEB3BondProduct,WEB3BondOrderTypeJudge,String,BigDecimal,boolean) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_orderTypeJudge == null || l_bdFxRate == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
    
        //１）入力為替レート != null and 
        //　@入力為替レート != 0　@　@の場合、 
        //　@　@入力為替レートをreturnする。
        if (l_bdFxRate != null && !(new BigDecimal("0")).equals(l_bdFxRate))
        {
            log.exiting(STR_METHOD_NAME);
            return l_bdFxRate;
        }
    
        //2）（債券注文種別判定.is応募注文 == true or  
        //債券注文種別判定.is買付注文 == true） and  
        //決済区分 == "外貨" and 
        //債券銘柄.仕入時の為替レート ≠ null の場合
        //債券銘柄.仕入時の為替レートをreturnする。
        BondProductRow l_productRow = (BondProductRow) l_bondProduct.getDataSourceObject();
        if ((l_orderTypeJudge.isRecruitOrder() || l_orderTypeJudge.isBuyOrder())
            && WEB3BondOrderSettleDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv)
            && !l_productRow.getBuyingFxRateIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return new BigDecimal(String.valueOf(l_bondProduct.getBuyingFxRate()));
        }
        //3－１）債券銘柄.get通貨()により（共通）通貨を取得する。
        WEB3GentradeCurrency l_gentradeCurrency = l_bondProduct.getCurrency();
        
        //3－２）（共通）通貨.get為替レート()により取得した為替レートをreturnする。
        double l_dblFxRate = l_bdFxRate.doubleValue();
        boolean l_blnIsBuyOrder = false;
        
        //[引数] 
        //is買付：　@債券注文種別判定.is応募注文 == true or  
        //　@　@　@　@　@  債券注文種別判定.is買付注文 == true の場合、 
        //　@　@　@　@　@　@true をセットする。 
        //　@　@　@　@　@　@債券注文種別判定.is売却注文 == true の場合、 
        //　@　@　@　@　@　@false をセットする。 
        //is約定計算：　@is約定計算 
        //入力為替レート：　@入力為替レート
        if (l_orderTypeJudge.isRecruitOrder()  
            || l_orderTypeJudge.isBuyOrder() )
        {
            l_blnIsBuyOrder = true;
        }
        else if (l_orderTypeJudge.isSellOrder())
        {
            l_blnIsBuyOrder = false;
        }
        double l_dblExchangeRate =  
            l_gentradeCurrency.getExchangeRate(l_blnIsBuyOrder, l_blnIsExecuteCalc, l_dblFxRate);
    
        log.exiting(STR_METHOD_NAME);
        return new  BigDecimal(String.valueOf(l_dblExchangeRate));
    }
    
    /**
     * (get自動約定対象注文)<BR>
     * get自動約定対象注文 <BR>
     * <BR>
     * 自動約定対象となる注文単位の一覧を取得する。<BR>
     * １）　@債券注文単位オブジェクトを全て取得する。<BR>
     * <BR>
     * 　@クエリプロセッサを使用し、以下の条件に合致する注文単位オブジェクトを全て取得する。<BR>
     * 　@（受注日時の昇順指定） <BR>
     * 　@　@部店ID in 拡張アカウントマネージャ.get証券会社(引数.証券会社コード).getBranches()<BR>
     * 　@　@のいずれかの部店ID<BR>
     * <BR>
     * 　@　@引数.from口座ID <= 口座ID<BR>
     * 　@　@口座ID <= 引数.to口座ID<BR>
     * 　@　@注文約定区分 == "未約定" <BR>
     * 　@　@自動約定区分 == "自動約定"<BR>
     * 　@　@発注日 == 業務日付(*1)<BR>
     * 　@　@(*1)業務日付は、GtlUtils.getTradingSystem( ).getBizDate( ) で取得する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param l_strInstitutitonCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_lngFromAccountId - (from口座ID)<BR>
     * from口座ID<BR>
     * @@param l_lngToAccountId - (to口座ID)<BR>
     * to口座ID<BR>
     * @@return WEB3BondOrderUnit[]
     */
    public WEB3BondOrderUnit[] getAutoExecuteOrder(String l_strInstitutitonCode, 
    	long l_lngFromAccountId, 
    	long l_lngToAccountId) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
        	" getAutoExecuteOrder(String, long, long) " ;
        log.entering(STR_METHOD_NAME);
        
        int l_intCount = 0;
        List l_lisQuerys = null;
        String l_strQueryString = "";
        WEB3BondOrderUnit[] l_bondOrderUnits = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);  
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();  

        try
        {
        	//クエリプロセッサを使用し、
        	//以下の条件に合致する注文単位オブジェクトを全て取得する。
        	//部店ID
            Branch[] l_branches = 
            	l_accountManager.getInstitution(l_strInstitutitonCode).getBranches();   
            Object[] l_objQueryDataContainers = null;
            if (l_branches != null && l_branches.length != 0)
            {
                l_objQueryDataContainers = new Object[l_branches.length + 5];
                l_intCount = l_branches.length;
                l_strQueryString = " branch_id in ( ";
				for (int i = 0; i < l_intCount; i++)
				{
				    if (i == l_intCount - 1)
				    {
				        l_strQueryString = l_strQueryString + " ? ";
				        l_objQueryDataContainers[i] = new Long(l_branches[i].getBranchId());
				    }
				    else
				    {
				        l_strQueryString = l_strQueryString + " ?, ";
				        l_objQueryDataContainers[i] = new Long(l_branches[i].getBranchId());
				    }
                }
                
                l_strQueryString = l_strQueryString + " ) ";
            }
            else
            {
                l_objQueryDataContainers = new Object[5];
            }

            l_strQueryString = l_strQueryString + " and account_id >= ? ";
            l_strQueryString = l_strQueryString + " and account_id <= ? ";
            l_strQueryString = l_strQueryString + " and order_exec_status = ? ";
            l_strQueryString = l_strQueryString + " and auto_exec_div = ? ";
            l_strQueryString = l_strQueryString + " and biz_date = ? ";
            
            //from口座ID
            l_objQueryDataContainers[l_intCount] = 
                new Long(l_lngFromAccountId);
            
            //to口座ID
            l_objQueryDataContainers[l_intCount + 1] = 
                new Long(l_lngToAccountId);
            
            //注文約定区分
            l_objQueryDataContainers[l_intCount + 2] =
                WEB3BondOrderExecStatusDef.UNEXECUTED;
            
            //自動約定区分
            l_objQueryDataContainers[l_intCount + 3] = 
                WEB3AutoExecDivDef.AUTO_EXECUTE;
            
			//発注日
			l_objQueryDataContainers[l_intCount + 4] = 
			    WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
            
            String l_strSortKey = " received_date_time ASC ";
            
    		QueryProcessor l_qp = Processors.getDefaultProcessor();
    		l_lisQuerys = l_qp.doFindAllQuery(
				BondOrderUnitRow.TYPE, 
				l_strQueryString,
				l_strSortKey, 
				null,
				l_objQueryDataContainers);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 検索結果を返却する。
        if (l_lisQuerys != null && !l_lisQuerys.isEmpty())
        {
        	int l_intSize = l_lisQuerys.size();
        	l_bondOrderUnits = new WEB3BondOrderUnit[l_intSize];
        	
        	for (int i = 0; i < l_intSize; i++)
        	{
        		BondOrderUnitRow l_bondOrderUnitRow = 
                    (BondOrderUnitRow)l_lisQuerys.get(i);
                
        		WEB3BondOrderUnit l_bondOrderUnit = 
                    new WEB3BondOrderUnit(l_bondOrderUnitRow);
                
        		l_bondOrderUnits[i] = l_bondOrderUnit;
        	}
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_bondOrderUnits;
    }
    
    /**
     * (validate単価)<BR>
     * validate単価<BR>
     *   no operation
     *<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_strExecPrice - (単価)<BR>
     * 単価<BR>
     * @@throws WEB3BaseException
     */
    public void validateExecPrice(WEB3BondProduct l_bondProduct, String l_strExecPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateExecPrice(WEB3BondProduct, String) " ;
        log.entering(STR_METHOD_NAME);
        
        //no operation
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc国内債券注文金額合計)<BR>
     * 国内債券銘柄の注文金額合計を算出する。<BR>
     * <BR>
     * ○前日までの注文金額を取得。<BR>
     * <BR>
     * 　@１）債券注文受付履歴テーブルを検索し、<BR>
     * 　@　@　@直近の注文受付日付の債券注文受付履歴Rowを取得する。<BR>
     * <BR>
     * 　@　@　@[検索条件]<BR>
     * 　@　@　@　@　@銘柄ID = 引数.銘柄ID<BR>
     * 　@　@　@　@　@and 証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@　@　@and 部店コード = 引数.部店コード<BR>
     * 　@　@　@　@　@and 注文受付日付 =<BR>
     * 　@　@　@　@　@　@　@ ( select max(注文受付日付) from 債券注文受付履歴テーブル<BR>
     * 　@　@　@　@　@　@　@　@　@where 銘柄ID = 引数.銘柄ID<BR>
     *           　@   　@      and 証券会社コード = 引数.証券会社コード<BR>
     *           　@   　@      and 部店コード = 引数.部店コード )<BR>
     * <BR>
     * 　@２）取得した債券注文受付履歴.注文金額累計を<BR>
     * 　@　@　@前日までの注文金額として、注文金額合計にセットする。<BR>
     * <BR>
     * 　@　@　@※）取得できなかった場合、0をセットする。<BR>
     * <BR>
     * ○当日分の注文金額と当日に過日取消された注文金額を取得。<BR>
     * <BR>
     * 　@３）引数.部店コード ≠ "全部店" の場合、<BR>
     * 　@　@　@拡張アカウントマネージャ.get部店()をコールし、部店を取得する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@　@証券会社コード　@：　@引数.証券会社コード<BR>
     * 　@　@　@　@　@部店コード　@：　@引数.部店コード<BR>
     * <BR>
     * 　@４）当日の応募注文と当日に過日取消された注文を取得。<BR>
     * 　@　@　@債券注文単位テーブルを検索し、<BR>
     * 　@　@　@債券注文単位行のリストを取得する。<BR>
     * <BR>
     * 　@　@４－１）債券注文受付履歴Rowが取得できた場合、<BR>
     * 　@　@　@　@　@　@以下の検索条件で検索する。<BR>
     * <BR>
     * 　@　@　@　@　@　@［検索条件］<BR>
     * 　@　@　@　@　@　@銘柄ID = 引数.銘柄ID<BR>
     * 　@　@　@　@　@　@and部店ID = 取得した部店ID(*1)<BR>
     * 　@　@　@　@　@　@and　@注文種別　@= ’国内債券応募注文’<BR>
     * 　@　@　@　@　@　@and (( 注文有効状態　@= ’オープン’<BR>
     *         　@　@　@　@　@　@　@and  受注日時　@＞ 債券注文受付履歴.注文受付日付(*2))<BR>
     * 　@　@　@　@　@　@or ( 注文有効状態 = ’クローズ’<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@and 注文状態 = ’発注済（取消注文）’<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@and 受注日時　@≦　@債券注文受付履歴.注文受付日付(*2)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@and 更新日付　@＞　@債券注文受付履歴.注文受付日付(*2)))<BR>
     * <BR>
     * 　@　@　@　@　@(*1)引数.部店コード ≠ "全部店" の場合、部店IDの検索条件を追加する。<BR>
     * 　@　@　@　@　@(*2)年月日部分のみ比較。<BR>
     * <BR>
     * 　@　@４－２）債券注文受付履歴Rowが取得できなかった場合、<BR>
     * 　@　@　@　@　@　@以下の検索条件で検索する。<BR>
     * <BR>
     * 　@　@　@　@　@　@［検索条件］<BR>
     * 　@　@　@　@　@　@銘柄ID = 引数.銘柄ID<BR>
     * 　@　@　@　@　@　@and部店ID = 取得した部店ID(*1)<BR>
     * 　@　@　@　@　@　@and　@注文種別　@= ’国内債券応募注文’<BR>
     * 　@　@　@　@　@　@and ( 注文有効状態　@= ’オープン’<BR>
     * <BR>
     * 　@　@　@　@　@(*1)引数.部店コード ≠ "全部店" の場合、部店IDの検索条件を追加する。<BR>
     * <BR>
     * 　@５）債券注文単位行のリストの要素数分以下の処理をLOOPし、<BR>
     * 　@　@注文金額合計を算出する。<BR>
     * <BR>
     * 　@　@５－１）債券注文単位行.注文有効状態　@= ’オープン’の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@注文金額合計に債券注文単位行.注文数量を加算（+）する。<BR>
     * <BR>
     * 　@　@５－２）債券注文単位行.注文有効状態　@= ’クローズ’の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@注文金額合計から債券注文単位行.注文数量を減算（-）する。<BR>
     * <BR>
     * ６）算出した注文金額合計を返却する。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcBondDomesticOrderAmountTotal(
        long l_lngProductId,
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " calcBondDomesticOrderAmountTotal(long, String, String)";
        log.entering(STR_METHOD_NAME);

        double l_dblOrderAmountTotalCnt = 0.0D;

        Long l_productId = new Long(l_lngProductId);
        //○前日までの注文金額を取得。
        List l_lisQuerys = null;
        BondOrderAcceptActionRow l_bondOrderAcceptActionRow = null;
        try
        {
            //１）債券注文受付履歴テーブルを検索し、
            //直近の注文受付日付の債券注文受付履歴Rowを取得する。
            //[検索条件]
            //銘柄ID = 引数.銘柄ID
            //and 証券会社コード = 引数.証券会社コード
            //and 部店コード = 引数.部店コード
            //and 注文受付日付 = ( select max(注文受付日付) from 債券注文受付履歴テーブル
            //where 銘柄ID = 引数.銘柄ID
            //and 証券会社コード = 引数.証券会社コード
            //and 部店コード = 引数.部店コード )
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append(" product_id = ? ");
            l_sbQueryString.append(" and institution_code = ? ");
            l_sbQueryString.append(" and branch_code = ? ");
            l_sbQueryString.append(
                " and order_accept_date = (select max(order_accept_date) from bond_order_accept_action ");
            l_sbQueryString.append(" where product_id = ? ");
            l_sbQueryString.append(" and institution_code = ? ");
            l_sbQueryString.append(" and branch_code = ? )");
            log.debug("l_sbQueryString==" + l_sbQueryString.toString());

            Object[] l_queryDataContainers = {l_productId, l_strInstitutionCode, l_strBranchCode,
                l_productId, l_strInstitutionCode, l_strBranchCode};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisQuerys = l_queryProcessor.doFindAllQuery(
                BondOrderAcceptActionRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainers);

            //２）取得した債券注文受付履歴.注文金額累計を
            //前日までの注文金額として、注文金額合計にセットする。
            //※）取得できなかった場合、0をセットする。
            if (!l_lisQuerys.isEmpty())
            {
                l_bondOrderAcceptActionRow = (BondOrderAcceptActionRow)l_lisQuerys.get(0);
                l_dblOrderAmountTotalCnt = l_bondOrderAcceptActionRow.getTotalOrderAmount();
            }
            else
            {
                l_dblOrderAmountTotalCnt = 0;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //○当日分の注文金額と当日に過日取消された注文金額を取得。
        //３）引数.部店コード ≠ "全部店" の場合、
        //拡張アカウントマネージャ.get部店()をコールし、部店を取得する。
        //[引数]
        //証券会社コード　@：　@引数.証券会社コード
        //部店コード　@：　@引数.部店コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        long l_lngBranchId = 0L;
        if (!WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH.equals(l_strBranchCode))
        {
            try
            {
                Institution l_institution =
                    l_gentradeAccountManager.getInstitution(l_strInstitutionCode);
                Branch l_branch = l_gentradeAccountManager.getBranch(l_institution, l_strBranchCode);
                l_lngBranchId = l_branch.getBranchId();
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        Long l_branchId = new Long(l_lngBranchId);

        //４）当日の応募注文と当日に過日取消された注文を取得。
        //債券注文単位テーブルを検索し、
        //債券注文単位行のリストを取得する。
        List l_lisBondOrderUnitRows = null;
        try
        {
            //［検索条件］
            StringBuffer l_sbQueryString = new StringBuffer();
            List l_lisValues = new ArrayList();
            Object[] l_queryDataContainers = null;

            if (l_bondOrderAcceptActionRow != null)
            {
                //銘柄ID = 引数.銘柄ID
                l_sbQueryString.append(" product_id = ? ");
                l_lisValues.add(l_productId);

                //and部店ID = 取得した部店ID(*1)
                //(*1)引数.部店コード ≠ "全部店" の場合、部店IDの検索条件を追加する。
                if (!WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH.equals(l_strBranchCode))
                {
                    l_sbQueryString.append(" and branch_id = ? ");
                    l_lisValues.add(l_branchId);
                }

                //and　@注文種別　@= ’国内債券応募注文’
                l_sbQueryString.append(" and order_type = ? ");
                l_lisValues.add(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);

                //and (( 注文有効状態　@= ’オープン’
                l_sbQueryString.append(" and (( order_open_status = ? ");
                l_lisValues.add(OrderOpenStatusEnum.OPEN);

                Timestamp l_tsOrderAcceptDate =
                    l_bondOrderAcceptActionRow.getOrderAcceptDate();
                Date l_datOrderAcceptDate = WEB3DateUtility.toDay(l_tsOrderAcceptDate);
                String l_strOrderAcceptDate =
                    WEB3DateUtility.formatDate(l_datOrderAcceptDate, WEB3GentradeTimeDef.DATE_SPLIT_YMD);

                //and  受注日時　@＞ 債券注文受付履歴.注文受付日付(*2))
                //(*2)年月日部分のみ比較。
                l_sbQueryString.append(" and to_char(received_date_time, 'yyyy/MM/dd')> ? ) ");
                l_lisValues.add(l_strOrderAcceptDate);

                //or ( 注文有効状態 = ’クローズ’
                l_sbQueryString.append(" or ( order_open_status = ? ");
                l_lisValues.add(OrderOpenStatusEnum.CLOSED);

                //and 注文状態 = ’発注済（取消注文）’
                l_sbQueryString.append(" and order_status = ? ");
                l_lisValues.add(OrderStatusEnum.CANCELLED);

                //and 受注日時　@≦　@債券注文受付履歴.注文受付日付(*2)
                //(*2)年月日部分のみ比較。
                l_sbQueryString.append(" and to_char(received_date_time, 'yyyy/MM/dd') <= ? ");
                l_lisValues.add(l_strOrderAcceptDate);

                //and 更新日付　@＞　@債券注文受付履歴.注文受付日付(*2)))
                //(*2)年月日部分のみ比較。
                l_sbQueryString.append(" and to_char(last_updated_timestamp, 'yyyy/MM/dd') > ? ))");
                l_lisValues.add(l_strOrderAcceptDate);

                l_queryDataContainers = new Object[l_lisValues.size()];
                l_lisValues.toArray(l_queryDataContainers);
            }
            else
            {
                //４－１）債券注文受付履歴Rowが取得できなかった場合、
                //以下の検索条件で検索する。
                //銘柄ID = 引数.銘柄ID
                l_sbQueryString.append(" product_id = ? ");
                l_lisValues.add(l_productId);

                //and部店ID = 取得した部店ID(*1)
                //(*1)引数.部店コード ≠ "全部店" の場合、部店IDの検索条件を追加する。
                if (!WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH.equals(l_strBranchCode))
                {
                    l_sbQueryString.append(" and branch_id = ? ");
                    l_lisValues.add(l_branchId);
                }

                //and　@注文種別　@= ’国内債券応募注文’
                l_sbQueryString.append(" and order_type = ? ");
                l_lisValues.add(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);

                //and 注文有効状態　@= ’オープン’
                l_sbQueryString.append(" and order_open_status = ? ");
                l_lisValues.add(OrderOpenStatusEnum.OPEN);

                l_queryDataContainers = new Object[l_lisValues.size()];
                l_lisValues.toArray(l_queryDataContainers);
            }
            log.debug("l_sbQueryString==" + l_sbQueryString.toString());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondOrderUnitRows = l_queryProcessor.doFindAllQuery(
                BondOrderUnitRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //５）債券注文単位行のリストの要素数分以下の処理をLOOPし、
        //注文金額合計を算出する。
        BigDecimal l_bdOrderAmountTotalCnt =
            new BigDecimal(String.valueOf(l_dblOrderAmountTotalCnt));

        Iterator l_iteratorBondOrderUnitRow = l_lisBondOrderUnitRows.iterator();
        while (l_iteratorBondOrderUnitRow.hasNext())
        {
            BondOrderUnitRow l_bondOrderUnitRow =
                (BondOrderUnitRow)l_iteratorBondOrderUnitRow.next();
            OrderOpenStatusEnum l_orderOpenStatusEnum =
                l_bondOrderUnitRow.getOrderOpenStatus();

            //債券注文単位行.注文数量
            double l_dblQuantity = l_bondOrderUnitRow.getQuantity();

            BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));

            //５－１）債券注文単位行.注文有効状態　@= ’オープン’の場合、
            //注文金額合計に債券注文単位行.注文数量を加算（+）する。
            if (OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusEnum))
            {
                l_bdOrderAmountTotalCnt = l_bdOrderAmountTotalCnt.add(l_bdQuantity);
            }

            //５－２）債券注文単位行.注文有効状態　@= ’クローズ’の場合、
            //注文金額合計から債券注文単位行.注文数量を減算（-）する。
            if (OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusEnum))
            {
                l_bdOrderAmountTotalCnt = l_bdOrderAmountTotalCnt.subtract(l_bdQuantity);
            }
        }

        l_dblOrderAmountTotalCnt = l_bdOrderAmountTotalCnt.doubleValue();

        //６）算出した注文金額合計を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblOrderAmountTotalCnt;
    }

    /**
     * (validate国内債券応募注文)<BR>
     * validate国内債券応募注文<BR>
     * <BR>
     * シーケンス図「validate国内債券応募注文」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@throws WEB3BaseException
     */
    public void validateBondDomesticApplyOrder(
        SubAccount l_subAccount,
        WEB3BondProduct l_bondProduct,
        double l_dblOrderQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateBondDomesticApplyOrder(SubAccount, WEB3BondProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //validate法@人顧客
        this.validateCorporationAccount(l_subAccount, l_bondProduct);

        //validate顧客取扱可能銘柄<国内債券>
        this.validateAccountHandlingPossibleProductBondDomestic(
            l_bondProduct, WEB3BondDealDivDef.RECRUIT);

        //validate数量
        this.validateQuantity(l_dblOrderQuantity, l_bondProduct);

        //validate国内債券応募枠
        WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
            new WEB3BondOrderManagerReusableValidationsCheck();
        //引数]
        //部店ID：引数.補助口座.getMainAccountRow().getBranchId()
        //債券銘柄：引数.債券銘柄
        //注文数量：引数.注文数量

        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();

        l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(
            l_mainAccountRow.getBranchId(),
            l_bondProduct,
            l_dblOrderQuantity);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate顧客取扱可能銘柄<国内債券>)<BR>
     * 顧客向けに取扱可能な銘柄かどうかかチェックする。<BR>
     * <BR>
     * １）引数.債券銘柄.is顧客取扱可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢顧客取扱不可銘柄エラー｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02661<BR>
     * <BR>
     * ２）引数.取引区分　@==　@応募　@の場合<BR>
     * 　@引数.債券銘柄.is国内債券応募可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢応募不可銘柄エラー｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02612<BR>
     * <BR>
     * ３）引数.取引区分　@==　@買付　@の場合<BR>
     * 　@引数.債券銘柄.is買付可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢買付不可銘柄エラー｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02613<BR>
     * <BR>
     * ４）引数.取引区分　@==　@売却　@の場合<BR>
     * 　@引数.債券銘柄.is売却可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@｢売却不可銘柄エラー｣<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02614<BR>
     * <BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_strDealDiv - (取引)<BR>
     * 取引<BR>
     * @@throws WEB3BaseException
     */
    public void validateAccountHandlingPossibleProductBondDomestic(
        WEB3BondProduct l_bondProduct, String l_strDealDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateAccountHandlingPossibleProductBondDomestic(WEB3BondProduct, String)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）引数.債券銘柄.is顧客取扱可能()の戻り値 == false の場合、例外をスローする
        if (!l_bondProduct.isCustomerTradePossible())
        {
            log.debug("顧客取扱不可銘柄エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02661,
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客取扱不可銘柄エラー。");
        }

        //２）引数.取引区分　@==　@応募　@の場合
        //引数.債券銘柄.is国内債券応募可能()の戻り値 == false の場合、例外をスローする。
        if (WEB3BondDealDivDef.RECRUIT.equals(l_strDealDiv))
        {
            if (!l_bondProduct.isBondDomesticApplyPossible())
            {
                log.debug("応募不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02612,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "応募不可銘柄エラー。");
            }
        }

        //３）引数.取引区分　@==　@買付　@の場合
        //引数.債券銘柄.is買付可能()の戻り値 == false の場合、例外をスローする。
        if (WEB3BondDealDivDef.BUY.equals(l_strDealDiv))
        {
            if (!l_bondProduct.isBuyPossible())
            {
                log.debug("買付不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02613,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "買付不可銘柄エラー。");
            }
        }

        //４）引数.取引区分　@==　@売却　@の場合
        //引数.債券銘柄.is売却可能()の戻り値 == false の場合、例外をスローする。
        if (WEB3BondDealDivDef.SELL.equals(l_strDealDiv))
        {
            if (!l_bondProduct.isSellPossible())
            {
                log.debug("売却不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02614,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "売却不可銘柄エラー。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create国内債券約定日情報)<BR>
     * 債券約定日情報を作成する。<BR>
     * <BR>
     * １）債券約定日情報インスタンスを生成<BR>
     * <BR>
     * ２）債券約定日情報.発注日へセットする。<BR>
     * 　@　@[債券約定日情報.set発注日()に渡す引数]<BR>
     * 　@　@　@発注日：　@債券銘柄.get国内債券発注日()<BR>
     * <BR>
     * ３）債券約定日情報.約定日へセットする。<BR>
     * 　@　@[債券約定日情報.set約定日()に渡す引数]<BR>
     * 　@　@　@約定日：　@債券銘柄.get国内債券発注日()<BR>
     * <BR>
     * ４）債券約定日情報.受渡日へセットする。<BR>
     * 　@　@[債券約定日情報.set受渡日()に渡す引数]<BR>
     * 　@　@　@受渡日：　@債券銘柄.get受渡日()<BR>
     * <BR>
     * ５）債券約定日情報.入金日へセットする。<BR>
     * 　@　@[債券約定日情報.set入金日()に渡す引数]<BR>
     * 　@　@　@入金日：　@債券銘柄.get受渡日()<BR>
     * <BR>
     * ６）債券約定日情報を返却する。<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@return WEB3BondExecuteDateInfo
     * @@throws WEB3BaseException
     */
    public WEB3BondExecuteDateInfo createBondDomesticExecutionDateInfo(
        WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createBondDomesticExecutionDateInfo(WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //債券約定日情報インスタンスを生成
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();

        //債券約定日情報.発注日へセットする。
        //[債券約定日情報.set発注日()に渡す引数]
        //発注日：　@債券銘柄.get国内債券発注日()
        l_bondExecuteDateInfo.setBizDate(l_bondProduct.getBondDomesticBizDate());

        //債券約定日情報.約定日へセットする。
        //[債券約定日情報.set約定日()に渡す引数]
        //約定日：　@債券銘柄.get国内債券発注日()
        l_bondExecuteDateInfo.setExecuteDate(l_bondProduct.getBondDomesticBizDate());

        //債券約定日情報.受渡日へセットする。
        //[債券約定日情報.set受渡日()に渡す引数]
        //受渡日：　@債券銘柄.get受渡日()
        l_bondExecuteDateInfo.setDeliveryDate(l_bondProduct.getDeliveryDate());

        //債券約定日情報.入金日へセットする。
        //[債券約定日情報.set入金日()に渡す引数]
        //入金日：　@債券銘柄.get受渡日()
        l_bondExecuteDateInfo.setPaymentDate(l_bondProduct.getDeliveryDate());

        log.exiting(STR_METHOD_NAME);
        return l_bondExecuteDateInfo;
    }

    /**
     * (validate法@人顧客)<BR>
     * 顧客が法@人かどうかをチェックする。<BR>
     * <BR>
     * １）引数.補助口座.getMainAccount()をコールして、顧客を取得する。<BR>
     * <BR>
     * ２）顧客.is法@人()の戻り値 == true<BR>
     * 　@かつ引数.債券銘柄.is個人向け国債() == true の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@「法@人顧客エラー」<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag: BUSINESS_ERROR_02884<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@throws WEB3BaseException
     */
    public void validateCorporationAccount(
        SubAccount l_subAccount, WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateCorporationAccount(SubAccount, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //引数.補助口座.getMainAccount()をコールして、顧客を取得する。
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount;

        //顧客.is法@人()の戻り値 == true
        //かつ引数.債券銘柄.is個人向け国債() == true の場合、例外をスローする。
        //「法@人顧客エラー」
        if (l_gentradeMainAccount.isCorporation()
            && l_bondProduct.isIndividualGovernmentBond())
        {
            log.debug("法@人顧客エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02884,
                this.getClass().getName() + STR_METHOD_NAME,
                "法@人顧客エラー。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
