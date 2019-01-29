head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント申込マネージャ(WEB3PointApplyManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/30 張学剛 (中訊) 新規作成
*/

package webbroker3.point;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.data.PointApplyParams;

/**
 * (ポイント申込マネージャ)<BR>
 * ポイント申込マネージャインターフェイス<BR>
 * 
 * @@author 張学剛
 * @@version 1.0 
 */
public interface WEB3PointApplyManager extends Service 
{
    
    /**
     * (get申込)<BR>
     * 申込オブジェクトを取得する。<BR>
     * @@param l_lngApplyId - (申込ID)<BR>
     * 申込ID<BR>
     * 
     * @@return webbroker3.point.WEB3PointApply
     * @@roseuid 418F2BFB03D6
     */
    public WEB3PointApply getApply(long l_lngApplyId) throws WEB3BaseException;
    
    /**
     * (get申込)<BR>
     * 該当顧客の過去7日の申込データを取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@return webbroker3.point.data.PointApplyParams
     * @@roseuid 419DC5870019
     */
    public PointApplyParams[] getApply(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (get申込件数)<BR>
     * 引数の証券会社コード、景品番号に該当する景品のポイント申込をしている<BR>件数を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@return long
     * @@roseuid 4193480A0240
     */
    public long getApplyNumber(String l_strInstitutionCode, String l_strPremiumNo) throws WEB3BaseException;
    
    /**
     * (get利用可能ポイント)<BR>
     * 利用可能ポイントを取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@return long
     * @@roseuid 418F2A2B003C
     */
    public long getUsePossiblePoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (get失効注意ポイント)<BR>
     * 失効注意ポイントを取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@return long
     * @@roseuid 418F2A2B006B
     */
    public long getExpirationAttentionPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (get申込ポイント)<BR>
     * 引数.対象月に申込を行ったポイントの合計を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 処理対象となる年月（YYYYMM）<BR>
     * 
     * @@return long
     * @@roseuid 41B6864A0314
     */
    public long getApplyPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException;
    
    /**
     * (get引落未済ポイント)<BR>
     * 有効月から引落未済のポイントの合計を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@return long
     * @@roseuid 41AFE92F023A
     */
    public long getNotWithdrawnPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (get未集計調整ポイント)<BR>
     * 対象月の未集計の調整ポイントの合計を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 処理対象となる年月（YYYYMM）<BR>
     * 
     * @@return long
     * @@roseuid 41B6705202F4
     */
    public long getNotTotalAdjustPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException;
    
    /**
     * (get有効期限月)<BR>
     * 有効期限月を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 算出対象となる年月（YYYYMM）<BR>
     * 
     * @@return String
     * @@roseuid 41AFEF0F02C7
     */
    public String getValidTermMon(String l_strInstitutionCode, String l_strObjectMonth) throws WEB3BaseException;
    
    /**
     * (validate調整ポイント)<BR>
     * 調整ポイントのチェックを行う。<BR>
     * @@param l_strAdjustPoint - (調整ポイント)<BR>
     * 調整ポイント<BR>
     * 
     * @@param l_lngUsePossiblePoint - (利用可能ポイント)<BR>
     * 利用可能ポイント<BR>
     * @@roseuid 419468B0004E
     */
    public void validateAdjustPoint(String l_strAdjustPoint, long l_lngUsePossiblePoint) throws WEB3BaseException;
    
    /**
     * (validateポイント余力)<BR>
     * ポイント余力のチェックを行う。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_premium - (景品)<BR>
     * 景品オブジェクト<BR>
     * @@roseuid 418F2C7402EC
     */
    public void validatePointPower(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, WEB3PointPremium l_premium) throws WEB3BaseException;
    
    /**
     * (validateポイント余力)<BR>
     * 申込の取消解除でポイントポイント申込が有効になった時のポイント余力のチェックを行う。<BR>
     * @@param l_appyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * @@roseuid 419D86CA0393
     */
    public void validatePointPower(WEB3PointApply l_appyData) throws WEB3BaseException;
    
    /**
     * (saveNew調整)<BR>
     * 調整データをDBに登録する。<BR>
     * @@param l_adjustData - (調整データ)<BR>
     * ポイント調整オブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419478CC0242
     */
    public void saveNewAdjust(WEB3PointAdjust l_adjustData, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (saveNew申込)<BR>
     * 申込データをDBに登録する。<BR>
     * @@param l_applyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * 
     * 
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者オブジェクト<BR>
     * @@roseuid 41A451200008
     */
    public void saveNewApply(WEB3PointApply l_applyData, Trader l_trader) throws WEB3BaseException;
    
    /**
     * (save申込受付)<BR>
     * 申込受付状態にDBを更新する。<BR>
     * @@param l_applyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419C8FF20259
     */
    public void saveApplyAccept(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (save申込取消)<BR>
     * 申込取消状態にDBを更新する。<BR>
     * @@param l_applyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419D62FA0001
     */
    public void saveApplyCancel(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (save申込取消解除)<BR>
     * 申込取消解除状態にDBを更新する。<BR>
     * @@param l_applyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419D746702F7
     */
    public void saveApplyCancelRelease(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (validate申込可能)<BR>
     * ポイント申込可能かどうかのチェックを行う。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * @@roseuid 41A6E4160153
     */
    public void validateApplyPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strPremiumNo) throws WEB3BaseException;
}
@
