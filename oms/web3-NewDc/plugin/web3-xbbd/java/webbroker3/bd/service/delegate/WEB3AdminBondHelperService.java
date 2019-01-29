head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondHelperService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者ヘルパーサービス(WEB3AdminBondHelperService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 齊珂(中訊) 新規作成         
*/

package webbroker3.bd.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.data.CustodianRow;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;

/**
 * (債券管理者ヘルパーサービス)<BR>
 * 債券管理者ヘルパーサービス　@インターフェイスクラス
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public interface WEB3AdminBondHelperService extends Service
{
    
    /**
     * (to顧客情報)<BR>
     * 引数より顧客情報を作成する
     * @@param l_orderUnit - (債券注文単位)<BR>
     * 債券注文単位
     * @@return webbroker3.bd.message.WEB3AdminBondAccountInfo
     * @@throws WEB3BaseException
     * @@roseuid 44C74D46005C
     */
    public WEB3AdminBondAccountInfo toAccountInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (to注文情報)<BR>
     * 引数より注文情報を作成する
     * @@param l_orderUnit - (債券注文単位)<BR>
     * 債券注文単位
     * @@return webbroker3.bd.message.WEB3AdminBondOrderInfo
     * @@throws WEB3BaseException
     * @@roseuid 44C74D46005E
     */
    public WEB3AdminBondOrderInfo toOrderInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (to約定情報)<BR>
     * 引数より約定情報を作成する
     * @@param l_orderUnit - (債券注文単位)<BR>
     * 債券注文単位
     * @@return webbroker3.bd.message.WEB3AdminBondOrderExecInfo
     * @@throws WEB3BaseException
     * @@roseuid 44C74D460060
     */
    public WEB3AdminBondOrderExecInfo toOrderExecInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (to約定情報)<BR>
     * 引数より約定情報を作成する.
     * @@param l_executeDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報
     * @@param l_calcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果
     * @@param l_custodianUnit - (カストディアン)<BR>
     * カストディアン
     * @@param l_orderUnit - (拡張債券注文単位)<BR>
     * 債券注文単位
     * @@return webbroker3.bd.message.WEB3AdminBondOrderExecInfo
     * @@throws WEB3BaseException
     * @@roseuid 44DAD04E039B
     */
    public WEB3AdminBondOrderExecInfo toOrderExecInfo(
        WEB3BondExecuteDateInfo l_executeDateInfo, 
        WEB3BondEstimatedPriceCalcResult l_calcResult, 
        WEB3AdminBondCustodianUnit l_custodianUnit, 
        WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (to銘柄情報)<BR>
     * to銘柄情報<BR>
     * <BR>
     * 　@引数を元に銘柄情報を返す
     * @@param l_product - (債券銘柄)<BR>
     * 債券銘柄
     * @@return webbroker3.bd.message.WEB3AdminBondProductInfo
     * @@throws WEB3BaseException
     * @@roseuid 44D93D6C000F
     */
    public WEB3AdminBondProductInfo toProductInfo(WEB3BondProduct l_product)
        throws WEB3BaseException;
    
    /**
     * (get補助口座)<BR>
     * get補助口座
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@return SubAccount
     * @@throws WEB3BaseException
     * @@roseuid 44CB1315035E
     */
    public SubAccount getSubAccount(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (reset受渡代金)<BR>
     * reset受渡代金
     * @@param l_calcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果
     * @@param l_orderExecInfo - (約定情報)<BR>
     * 約定情報
     * @@param l_product - (債券銘柄)<BR>
     * 債券銘柄
     * 
     * @@return webbroker3.bd.WEB3BondPriceCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 44CB1DF8020B
     */
    public WEB3BondEstimatedPriceCalcResult resetEstimatedPrice(
        WEB3BondEstimatedPriceCalcResult l_calcResult, 
        WEB3AdminBondOrderExecInfo l_orderExecInfo, 
        WEB3BondProduct l_product) throws WEB3BaseException;
    
    /**
     * (reset約定日情報)<BR>
     * reset約定日情報<BR>
     * <BR>
     * １）引数.約定情報.約定日！＝nullの場合、<BR>
     * 　@引数.債券約定日情報.約定日＝引数.約定情報.約定日<BR>
     * <BR>
     * ２）引数.約定情報.現地約定日！＝nullの場合、<BR>
     * 　@引数.債券約定日情報.現地約定日＝引数.約定情報.現地約定日<BR>
     * <BR>
     * ３）引数.約定情報.受渡日！＝nullの場合、<BR>
     * 　@引数.債券約定日情報.受渡日＝引数.約定情報.受渡日<BR>
     * <BR>
     * ４）引数.約定情報.現地受渡日！＝nullの場合、<BR>
     * 　@引数.債券約定日情報.現地受渡日＝引数.約定情報.現地受渡日<BR>
     * <BR>
     * ５）引数.約定情報.入金日　@!= nullの場合、<BR>
     * 　@　@引数.債券約定日情報.入金日＝引数.約定情報.入金日<BR>
     * <BR>
     * ６）引数.約定情報.入金日　@== nullの場合、<BR>
     * 　@６－１）債券部店別条件を生成する。
     * 　@　@　@　@　@[引数]
     * 　@　@　@　@　@　@部店ID：引数.部店.部店ID
     * 　@６－２）債券部店別条件.get入金日設定区分＝＝'約定日基準'
     * 　@　@　@　@　@かつ
     * 　@　@　@　@　@引数.債券注文種別判定.is応募＝＝true　@の場合、
     * 　@　@　@　@　@　@引数.債券約定日情報.入金日＝引数.債券銘柄.get入金日
     * 　@　@　@　@　@　@　@　@[引数]
     * 　@　@　@　@　@　@　@　@　@約定日　@　@　@　@　@：　@引数.債券約定日情報.約定日
     * 　@　@　@　@　@　@　@　@　@債券注文種別判定：　@引数.債券注文種別判定
     * 　@　@　@　@　@　@　@　@　@決済区分　@　@　@　@：　@引数.決済区分
     * 　@　@　@　@　@　@　@　@　@部店　@　@　@　@　@　@：　@引数.部店
     * 　@６－３）上記以外の場合、
     * 　@　@　@　@　@　@引数.債券約定日情報.入金日＝引数.約定情報.受渡日
     * ７）債券約定日情報を返す。
     * @@param l_orderExecInfo - (約定情報)<BR>
     * 約定情報
     * @@param l_executeDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_strSettleDiv - (決済区分)<BR>
     * 決済区分
     * @@param l_branch - (部店)<BR>
     * 部店
     * @@return webbroker3.bd.WEB3BondExecuteDateInfo
     * @@throws WEB3BaseException
     * @@roseuid 44D9526903B9
     */
    public WEB3BondExecuteDateInfo resetExecuteDateInfo(
        WEB3AdminBondOrderExecInfo l_orderExecInfo, 
        WEB3BondExecuteDateInfo l_executeDateInfo,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
        WEB3BondProduct l_bondProduct, String l_strSettleDiv, Branch l_branch) throws WEB3BaseException;
    
    /**
     * (get注文ロック解除ボタン区分)<BR>
     * get注文ロック解除ボタン区分<BR>
     * <BR>
     * 注文ロック解除ボタン区分を決定し、該当する値を返す。
     * @@param l_orderUnit - (注文単位)<BR>
     * 拡張債券注文単位
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D2DADE0339
     */
    public String getOrderLockButtonDiv(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (get約定変更ボタン区分)<BR>
     * get約定変更ボタン区分<BR>
     * <BR>
     * 約定変更ボタン区分を決定し、該当する値を返す。
     * @@param l_orderUnit - (注文単位)<BR>
     * 拡張債券注文単位
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D3241501E7
     */
    public String getExecuteChangButtonDiv(WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get取消ボタン区分)<BR>
     * get取消ボタン区分<BR>
     * <BR>
     * 取消ボタン区分を決定し、該当する値を返す。
     * @@param l_orderUnit - (注文単位)<BR>
     * 拡張債券注文単位
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D3251D02F9
     */
    public String getCancelButtonDiv(WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (toカストディアン一覧)<BR>
     * toカストディアン一覧<BR>
     * <BR>
     * 引数を元にカストディアン一覧を返す
     * @@param l_lisCustodian - (カストディアンリスト)<BR>
     * カストディアンリスト<BR>
     * <BR>
     *  CustodianRowのList
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44D93D5D033C
     */
    public List toCustodianList(List l_lisCustodian) throws WEB3BaseException;
    
    /**
     * (toカストディアン)<BR>
     * toカストディアン<BR>
     * <BR>
     * 引数を元にカストディアンを返す
     * @@param l_row - (カストディアンRow)<BR>
     * カストディアンRow
     * @@return WEB3AdminBondCustodianUnit
     * @@throws WEB3BaseException
     * @@roseuid 44D94A2D01B5
     */
    public WEB3AdminBondCustodianUnit toCustodian(CustodianRow l_row) throws WEB3BaseException;
    
    /**
     * (to顧客情報)<BR>
     * 引数より顧客情報を作成する <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客
     * @@return WEB3AdminBondAccountInfo
     * @@throws WEB3BaseException
     * @@roseuid 44D94A2D01B5
     */
    public WEB3AdminBondAccountInfo toAccountInfo(MainAccount l_mainAccount) throws WEB3BaseException;
}
@
