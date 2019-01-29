head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込登録サービス(WEB3SrvRegiRegistService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 李頴淵 新規作成
Revesion History : 2007/06/20 崔遠鵬(sinocom) 仕様変更モデルNo.249
Revesion History : 2007/06/26 崔遠鵬(sinocom) 仕様変更モデルNo.274
*/

package webbroker3.srvregi.service.delegate;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;

/**
 * (サービス利用申込登録サービス)<BR>
 * @@author 李頴淵
 * @@version 1.0
 *
 * サービス利用申込登録サービスインターフェイス<BR>
 * （トランザクション属性：何もセットしない）<BR>
 */
public interface WEB3SrvRegiRegistService extends Service
{

    /**
     * (validate適用期間)<BR>
     * 指定された適用期間が正しいかどうかを判定する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@param l_lngRegistId - (申込登録ID)<BR>
     * @@roseuid 413E8E3702E3
     */
    public void validateAppliPeriod(String l_strInstitutionCode, String l_strSrvDiv, String l_strBranchCode, String l_strMainAccountCode, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate, Long l_lngRegistId) throws WEB3BaseException;

    /**
     * (calc適用終了日)<BR>
     * 指定された適用開始日から、適用終了日を算出して返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@param l_lngSrvUsePeriodId - (利用期間ID)<BR>
     * @@param l_strSpecialDiv - (特殊申込区分)<BR>
     * @@param l_strFreeAttributeApplyDiv - (無料属性申込区分)<BR>
     * @@return Timestamp
     * @@roseuid 413E8E370322
     */
    public Timestamp calcAppliEndDate(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, Timestamp l_tsAppliStartDate, long l_lngSrvUsePeriodId, String l_strSpecialDiv, String l_strFreeAttributeApplyDiv) throws WEB3BaseException;

    /**
     * (submitサービス申込登録)<BR>
     * サービス申込登録の更新処理を行う。<BR>
     * @@param l_newAppliSpec - (申込内容)<BR>
     * サービス利用新規申込内容<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * @@roseuid 413E8E370360
     */
    public void submitServiceRegist(WEB3SrvRegiNewAppliSpec l_newAppliSpec, Long l_lngOrderId) throws WEB3BaseException;

    /**
     * (submitサービス申込変更)<BR>
     * サービス申込登録の更新処理を行う。<BR>
     * @@param l_changeAppliSpec - (申込内容)<BR>
     * サービス利用変更申込内容オブジェクト<BR>
     * @@roseuid 413E8E37039F
     */
    public void submitServiceRegistChange(WEB3SrvRegiChangeAppliSpec l_changeAppliSpec) throws WEB3BaseException;

    /**
     * (submit余力拘束)<BR>
     * サービスの利用申込に伴う出金の為、入出金注文単位を作成し、<BR>
     * その際に作成した注文IDを返却する。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者オブジェクト<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strOrderChannel - (注文チャネル)<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * @@return long
     * @@roseuid 413E8E3703DD
     */
    public long submitRemainingPowerRestraint(WEB3GentradeSubAccount l_subAccount, Trader l_trader, double l_dblUseAmt, Timestamp l_tsPaymentDate, String l_strSrvDiv, String l_strOrderChannel, String l_strPassword) throws WEB3BaseException;

    /**
     * (submit余力解放)<BR>
     * サービスの利用申込の取消などに伴う余力解放の為、<BR>
     * 入出金注文単位に取り消し処理を実施する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * @@roseuid 413E8E380024
     */
    public void submitRemainingPowerRelease(WEB3GentradeSubAccount l_subAccount, long l_lngOrderId, String l_strPassword) throws WEB3BaseException;

    /**
     * (getサービス申込登録)<BR>
     * 指定されたサービス申込登録を取得し、それをサービス申込登録オブジェクトを
     * 作成し返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_lngRegistId - (申込登録ID)<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return WEB3GentradeSrvRegiApplication
     * @@roseuid 413E8E380063
     */
    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId, boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (getサービス申込登録)<BR>
     * 現在有効なサービス申込登録を取得し、それを元に<BR>
     * サービス申込登録オブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_strCancelDiv - (取消区分)<BR>
     * 0:通常　@1:取消　@null:指定無<BR>
     * @@param l_strEffectiveDiv - (有効区分)<BR>
     * 0:有効　@1:無効　@null:指定無<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return WEB3GentradeSrvRegiApplication
     * @@roseuid 413E8E3800A1
     */
    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, String l_strCancelDiv, String l_strEffectiveDiv, boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (getサービス申込登録一覧)<BR>
     * 指定された条件に合致するサービス申込登録一覧を検索し、<BR>
     * その結果をサービス申込登録Paramsオブジェクトの配列にして返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード（必須）<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分（必須）<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 0:有料　@1:無料　@2:全て<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * @@param l_tsAppliStartDateFrom - (適用開始日（自）)<BR>
     * @@param l_tsAppliStartDateTo - (適用開始日（至）)<BR>
     * @@param l_tsAppliDateFrom - (申込日（自）)<BR>
     * @@param l_tsAppliDateTo - (申込日（至）)<BR>
     * @@param l_sortConds - (ソート条件)<BR>
     * 対象項目:<BR>
     * ≪抽選無の場合≫<BR>
     * "部店","顧客","適用開始日","適用終了日","登録区分","利用料金"<BR>
     * ,"最終更新日","最終更新者"<BR>
     * ≪抽選有の場合≫<BR>
     * "部店","顧客","申込抽選区分","申込日","適用開始日","適用終了日"<BR>
     * ,"登録区分","利用料金","最終更新日","最終更新者"<BR>
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     * @@roseuid 413E8E3800E0
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String[] l_strBranchCodes, String l_strSrvDiv, String l_strAccountCode, String l_strPaymentDiv, String l_strAppliLotDiv, Timestamp l_tsAppliStartDateFrom, Timestamp l_tsAppliStartDateTo, Timestamp l_tsAppliDateFrom, Timestamp l_tsAppliDateTo, WEB3SrvRegiSortKey[] l_sortConds) throws WEB3BaseException;

    /**
     * (getサービス申込登録一覧)<BR>
     * 現在有効なサービス申込登録を取得し、返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_blnIsAppliEndDateDiv - (適用終了日区分)<BR>
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     * @@roseuid 413E8E38011E
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, boolean l_blnIsAppliEndDateDiv) throws WEB3BaseException;

    /**
     * (is利用可能)<BR>
     * 当該サービスが利用可能かどうか判定する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_lngRegistId - (申込登録ID)<BR>
     * @@return boolean
     * @@roseuid 416B72AE007A
     */
    public boolean isUsePossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId) throws WEB3BaseException;

    /**
     * (is取消可能)<BR>
     * 当該のサービス申込登録が取消可能なものかどうかを判定する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_lngRegistId - (申込登録ID)<BR>
     * @@return boolean
     * @@roseuid 416B72AE0099
     */
    public boolean isCancelPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId) throws WEB3BaseException;

    /**
     * (validate取引余力)<BR>
     * 取引余力残高が十分あるかを判定する。 <BR>
     *<BR>
     * シーケンス図「（サービス利用）validate取引余力」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_trader - (代理入力者)<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strOrderChannel - (注文チャネル)<BR>
     * @@return boolean
     * @@roseuid 416B72A802FA
     */
    public void validateTradingPower(WEB3GentradeSubAccount l_subAccount, Trader l_trader, double l_dblUseAmt, Timestamp l_tsPaymentDate, String l_strSrvDiv, String l_strOrderChannel) throws WEB3BaseException;

    /**
     * (get初期申込区分)<BR>
     * 引数にて指定されたサービス、顧客の組み合わせで過去１度でも申込があったかを<BR>
     * 判定し、判定結果(*)を返却する。<BR>
     * <BR>
     * <BR>
     * (*) [返却値内容] <BR>
     * （サービス利用顧客サービス情報一覧共通情報.初期申込区分、 <BR>
     * サービス利用管理者アップロード確認レスポンス.初期申込区分のコード定義と同じ<BR>
     * ただし、nullはありえない。）<BR>
     * 0:無 <BR>
     * 1:有<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@return String<BR>
     * @@roseuid 416B72A802FA
     */
    public String getInitializeAppliDiv(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode)
        throws WEB3BaseException;
	
	/**
	 * (getサービス申込登録取消対象)<BR>
	 * 取消可能なサービス申込登録を取得し、
	 * それを元にサービス申込登録オブジェクトを返却する。<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - (証券会社コード)<BR>
	 * @@param l_strBranchCode - (部店コード)<BR>
	 * @@param l_strSrvDiv - (サービス区分)<BR>
	 * @@param l_strAccountCode - (口座コード)<BR>
	 * @@param l_blnIsRowLock - (is行ロック)<BR>
	 * true : 行ロックを行う   false : 行ロックを行わない<BR>
	 * @@return WEB3GentradeSrvRegiApplication
	 * @@roseuid 41130761012E
	 */
	public WEB3GentradeSrvRegiApplication getServiceRegistCancelUnit(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, boolean l_blnIsRowLock)
		throws WEB3BaseException;
        
    /**
     * (get取引パスワード)<BR>
     * 取引パスワードを取得する。
     * 
     * OpLoginSecurityServiceより、ログインタイプ属性を取得する。
     * －ログインタイプ属性.属性名 == 取引パスワード設定（：TRADING_PWD_ENV）の属性値が 
     *   ”0：DEFAULT（取引パスワード項目を使用しない）”の場合、引数.暗証番号を返却する 。
     * －ログインタイプ属性.属性名 == 取引パスワード設定（：TRADING_PWD_ENV）の属性値が 
     * 　@”1：取引パスワード使用”の場合、引数.補助口座より顧客オブジェクト.取引パスワードを取得し、 
     *　@返却する。※
     *
     * ※顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの 
     * 
     * @@param l_subAccount (補助口座）<BR>
     * @@param l_strPassword (暗証番号）<BR>
     * @@return String<BR>
     * @@author sra518
     */
    public String getTradingPassword(SubAccount l_subAccount, String l_strPassword);

    /**
     * (getサービス申込属性一覧)<BR>
     * 指定された条件に合致するサービス申込属性一覧を検索し、<BR> 
     * その結果をサービス申込登録Paramsオブジェクトの配列にして返却する。<BR> 
     * <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード（必須）<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード一覧<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分（必須）<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 7:無料対象　@8:申込不可　@9:全て<BR>
     * @@param l_tsAppDate - (適用日)<BR>
     * 適用日<BR>
     * @@param l_sortCondition - (ソート条件)<BR>
     * 対象項目:<BR> 
     * "部店","顧客","申込属性","適用開始日","適用終了日","最終更新日","最終更新者"<BR> 
     * <BR>
     * @@return 操作 サービス申込属性Params[]
     */
    public SrvAppliAttributeParams[] getServiceAttributeLists(String l_strInstitutionCode, String[] l_strBranchCodes,
        String l_strSrvDiv, String l_strAccountCode, String l_strAppliLotDiv, Timestamp l_tsAppDate,
        WEB3SrvRegiSortKey[] l_sortConditions) throws WEB3BaseException;
}
@
