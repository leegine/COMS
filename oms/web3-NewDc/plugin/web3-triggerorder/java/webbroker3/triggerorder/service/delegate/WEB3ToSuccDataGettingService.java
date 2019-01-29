head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccDataGettingService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文データ取得サービス(WEB3ToSuccDataGettingService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 孟東(中訊) 新規作成
                   2006/11/30 徐宏偉(中訊) 仕様変更モデル201
Revesion History : 2008/03/20 趙林鵬(中訊) 仕様変更モデル258
*/

package webbroker3.triggerorder.service.delegate;

import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;

/**
 * (連続注文データ取得サービス)<BR>
 * 連続注文データ取得サービスインタフェイス<BR>
 * <BR>
 * 連続注文機@能の共通処理を実装する。<BR>
 * <BR>
 * @@author 孟東 <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToSuccDataGettingService extends Service 
{
    
    /**
     * (create連続注文明細)<BR>
     * 引数の連続注文明細にプロパティをセットする。
     * @@param l_succOrderUnit - (連続注文明細)<BR>
     * 連続注文明細オブジェクト
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@param l_blnIsPossibleFlagSet - (is可能フラグ設定)<BR>
     * 訂正・取消・連続注文可能フラグを<BR>
     * 設定するかどうかのフラグ<BR>
     * <BR>
     * false：　@設定しない<BR>
     * true：　@設定する<BR>
     * @@exception WEB3BaseException
     * @@roseuid 43280A5100DA
     */
    public void createSuccOrderUnit(
        WEB3SuccOrderUnit l_succOrderUnit, 
        OrderUnit l_orderUnit, 
        boolean l_blnIsPossibleFlagSet)
        throws WEB3BaseException;
    
    /**
     * (create予約注文明細)<BR>
     * 引数の前提注文明細に予約注文明細をセットする。
     * @@param l_requiredOrderUnit - (前提注文明細)<BR>
     * 前提注文明細
     * @@param l_rsvOrderRowList - (予約注文Row一覧)<BR>
     * 予約注文一覧
     * @@param l_blnIsPossibleFlagSet - (is可能フラグ設定)<BR>
     * 訂正・取消・連続注文可能フラグを<BR>
     * 設定するかどうかのフラグ<BR>
     * <BR>
     * false：　@設定しない<BR>
     * true：　@設定する<BR>
     * @@exception WEB3BaseException
     * @@roseuid 43280A5100EA
     */
    public void createRsvOrderUnit(
        WEB3SuccOrderUnit l_requiredOrderUnit, 
        Row[] l_rsvOrderRowList, 
        boolean l_blnIsPossibleFlagSet)
        throws WEB3BaseException;
    
    /**
     * (set可能フラグ)<BR>
     * 引数の連続注文明細に訂正・取消・連続可能フラグを<BR>
     * セットする。<BR>
     * @@param l_succOrderUnit - (連続注文明細)<BR>
     * 連続注文明細オブジェクト
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@exception WEB3BaseException
     * @@roseuid 43280A5100F9
     */
    public void setPossibleFlag(
        WEB3SuccOrderUnit l_succOrderUnit, 
        OrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (get予約注文単位一覧)<BR>
     * 顧客が保持する予約注文の一覧を返却する。
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID
     * @@param l_strCommodityTypeList - (商品区分一覧)<BR>
     * 商品区分一覧
     * @@return Hashtable
     * @@exception WEB3BaseException
     * @@roseuid 43280A5100FC
     */
    public Hashtable getRsvOrderUnitList(long l_lngAccountId, String[] l_strCommodityTypeList)
        throws WEB3BaseException;
    
    /**
     * (get商品区分)<BR>
     * 引数の注文種別より商品区分を判別し、<BR>
     * 返却する。<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 43280A510109
     */
    public String getCommodityDiv(OrderTypeEnum l_orderType)
        throws WEB3BaseException;
    
    /**
     * (get銘柄)<BR>
     * 引数の証券会社、銘柄コード、商品区分に <BR>
     * 該当する銘柄を取得し、返却する。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード
     * @@param l_strCommodityDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * 
     * @@return Product
     * @@exception WEB3BaseException
     * @@roseuid 43280A51010B
     */
    public Product getProduct(
        WEB3GentradeInstitution l_institution, 
        String l_strProductCode, 
        String l_strCommodityDiv)
        throws WEB3BaseException;
        
    /**
     * （get受付時間区分） <BR>
     * 引数の注文カテゴリより取引時間テーブルreadに使用する受付時間区分を判別し、<BR>
     * 返却する。 <BR>
     * <BR>
     * @@param l_orderCateg - (注文カテゴリ)<BR>
     * 注文カテゴリ<BR>
     * @@param l_strSonarTradedCode - (取引コード（SONAR）)<BR>
     * 取引コード（SONAR）<BR>
     * @@return String
     * @@exception WEB3SystemLayerException
     * @@roseuid 431F90BF00DF
     */
    public String getTradingTimeType(OrderCategEnum l_orderCateg, String l_strSonarTradedCode) 
        throws WEB3SystemLayerException;
        
    /**
     * (get銘柄)<BR>
     * 引数の銘柄タイプ、銘柄IDに該当する銘柄を<BR>
     * 取得し、返却する。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ
     * @@return Product
     * @@exception WEB3BaseException
     * @@roseuid 43280A510118
     */
    public Product getProduct(
        long l_lngProductId, 
        ProductTypeEnum l_productType)
        throws WEB3BaseException;
    
    /**
     * (get執行条件（PR層）)<BR>
     * 引数の執行条件に該当する執行条件（PR層）を<BR>
     * 返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 43280A51011B
     */
    public String getExecutionConditionTypeByPr(OrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (get処理状況)<BR>
     * 注文単位に該当する処理状況区分を返却する。
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 43280A510128
     */
    public String getTransactionState(OrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (get注文単位)<BR>
     * 引数の注文単位ID、銘柄タイプに該当する注文単位を<BR>
     * 取得し、返却する。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ
     * @@return OrderUnit
     * @@exception WEB3BaseException
     * @@roseuid 43280A51012A
     */
    public OrderUnit getOrderUnit(
        long l_lngOrderUnitId, 
        ProductTypeEnum l_productType)
        throws WEB3BaseException;
    
    /**
     * (get注文単位)<BR>
     * 引数の注文ID、商品区分に該当する注文単位を<BR>
     * 取得し、返却する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * @@param l_strCommodityDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * 
     * @@return OrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 43280A510138
     */
    public OrderUnit getOrderUnit(long l_lngOrderId, String l_strCommodityDiv)
        throws WEB3BaseException;
    
    /**
     * (get注文単位一覧)<BR>
     * 引数の銘柄タイプに該当する注文単位の一覧を<BR>
     * 取得し、返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト）
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件 文字列
     * @@param l_strQueryContainers - (検索条件データコンテナ)<BR>
     * 検索条件
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 43280A510147
     */
    public List getOrderUnitList(
        WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strQueryString, 
        String[] l_strQueryContainers, 
        String l_strSortCond)
        throws WEB3BaseException;
        
    /**
     * (get連続注文発注状況区分)<BR>
     * パラメータ.注文単位より<BR>
     * PR層で使用する連続注文の発注状況区分を返却する。 <BR>
     * @@param l_orderUnit - (注文単位)
     * 注文単位オブジェクト<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getToSuccTriggerOrderStatusType(OrderUnit l_orderUnit)
        throws WEB3BaseException;
        
}
@
