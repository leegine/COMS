head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定時定額買付共通サービス(WEB3MutualFixedBuyCommonService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
                 : 2006/07/24 栄イ (中訊) 仕様変更 モデル No.459
Revesion History : 2008/07/09 武波 (中訊) 仕様変更 モデル No.607
Revesion History : 2008/07/31 武波 (中訊) 仕様変更 モデル No.621
*/
package webbroker3.mf;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;

/**
 * (定時定額買付共通サービス)<BR>
 * 定時定額買付共通サービス<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public interface WEB3MutualFixedBuyCommonService extends Service 
{
    /**
     * (validate定時定額買付金額)<BR>
     * 定時定額買付最低金額チェック、単位金額チェックを行なう。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strMonthlyBuyAmount - (買付金額（月々)）<BR>
     * 買付金額（月々)<BR>
     * @@param l_strIncreaseBuyAmount - (買付金額（積み増し))<BR>
     * 買付金額（積み増し）<BR>
     * @@throws WEB3BaseException
     */
    public void validateFixedBuyAmount(
        SubAccount l_subAccount, 
        String l_strMonthlyBuyAmount,
        String l_strIncreaseBuyAmount) throws WEB3BaseException;
 
    /**
     * (validate外国証券口座開設)<BR>
     * 外国証券口座の開設の必要があるかどうかをチェックする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_mfProduct - (拡張投信銘柄)<BR> 
     * 拡張投信銘柄<BR>
     * @@return boolean <BR>
     */
    public boolean validateForeignSecAccOpen(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct);
       
    /**
     * (get定時定額買付条件リスト)<BR>
     * 引数の条件に該当する定時定額買付条件のリストを取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_objQueryValues - (検索条件値)<BR>
     * 検索条件値<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     */
    public List getFixedBuyConditionList(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strQueryString, 
        Object[] l_objQueryValues) throws WEB3BaseException;
    
    /**
     * (get銀行支店名)<BR>
     * 引数の条件に該当する銀行名・支店名を取得する。<BR>
     * @@param l_strFinInstitutionCode - (銀行コード)<BR>
     * 銀行コード<BR>
     * @@param l_strFinBranchCode - (支店コード)<BR>
     * 支店コード<BR>
     * @@return String[] <BR>
     * @@throws WEB3BaseException
     */
    public String[] getFinBranchName(
        String l_strFinInstitutionCode,
        String l_strFinBranchCode) throws WEB3BaseException;

    /**
     * (get定時定額買付条件変更リスト)<BR>
     * 引数の条件に該当する定時定額買付条件変更のリストを取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_objQueryValues - (検索条件値)<BR>
     * 検索条件値<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     */
    public List getFixedBuyConditionChangeList(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strQueryString, 
        Object[] l_objQueryValues) throws WEB3BaseException;

    /**
     * (calc適用開始年月（業務日付）)<BR>
     * 業務日付ベースの適用開始年月を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcValidStartDateOrderBizdate(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException;

    /**
     * (calc適用開始年月（現在日時）)<BR>
     * 現在日時ベースの適用開始年月を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcValidStartDateCurrentDate(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException;

    /**
     * (validate引落口座登録)<BR>
     * 定時定額買付引落口座が登録されているかチェックする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     */
    public void validateDrawAccountRegist(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode) throws WEB3BaseException;

    /**
     * (calc賞与確定引落金額)<BR>
     * 賞与確定引落金額を取得する。<BR>
     * @@param l_mfFixedBuyingCondRow - (定時定額買付条件Row)<BR>
     * 定時定額買付条件Row<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException
     */
    public String calcPrizeAndDecisioDrawAmount(
        MfFixedBuyingCondRow l_mfFixedBuyingCondRow) throws WEB3BaseException;

    /**
     * (is賞与月)<BR>
     * 指定した年月が賞与月かどうか判定する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    public boolean isPrizeAndMonth(
        String l_strInstitutionCode,
        Date l_datSelectMY) throws WEB3BaseException;

    /**
     * (sort定時定額買付条件一覧)<BR>
     * 指定されたソートキー、<BR>
     * 昇降順に基づいて投信定時定額買付条件行のソートを行う。<BR>
     * @@param l_mutualFixedBuyConditionUnits - (投信定時定額買付条件行[] )<BR>
     * 投信定時定額買付条件行[] <BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyConditionUnit[] sortFixedBuyConditionList(
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException;
}
@
