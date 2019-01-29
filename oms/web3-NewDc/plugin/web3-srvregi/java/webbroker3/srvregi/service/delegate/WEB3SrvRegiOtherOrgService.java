head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiOtherOrgService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用外部連携サービス(WEB3SrvRegiOtherOrgService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 武波 新規作成 モデル310,314,315,317,318,319,320
Revision History : 2008/02/26 武波 仕様変更 モデル321
Revision History : 2008/03/03 武波 仕様変更 モデル330
Revision History : 2008/03/19 武波 仕様変更 モデル354,356
*/
package webbroker3.srvregi.service.delegate;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;

/**
 * (サービス利用外部連携サービス)<BR>
 * サービス利用外部連携サービスインターフェイスクラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3SrvRegiOtherOrgService extends Service
{

    /**
     * (get外部連携情報)<BR>
     * 外部連携情報管理テーブルより、データを取得する。<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getOtherOrgInfo(
        String l_strServiceDiv,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (get外部連携情報)<BR>
     * 引数で指定された通番、サービス区分に該当する<BR>
     * 外部連携情報管理オブジェクトを返す。<BR>
     * @@param l_lngSequenceNumber - (通番)<BR>
     * 通番<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * is行ロック<BR>
     * true：行ロックをする。　@false：行ロックをしない。<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strServiceDiv,
        boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (get外部連携未使用件数)<BR>
     * 外部連携情報管理テーブルのステータスが未使用のレコードの件数を返却<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@return Long
     * @@throws WEB3BaseException
     */
    public Long getOtherOrgUnUsedCount(
        String l_strServiceDiv) throws WEB3BaseException;


    /**
     * (get外部連携情報一覧)<BR>
     * 指定された条件に合致する外部連携情報管理ﾃｰﾌﾞﾙを検索し、<BR>
     * その結果を外部連携情報Paramsオブジェクトの配列にして返却する。<BR>
     * @@param l_strSequenceNumber - (通番)<BR>
     * 通番<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strID - (ID)<BR>
     * ID<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード一覧<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_tsAppliStartDate - (適用開始日（自）)<BR>
     * 適用開始日（自）<BR>
     * @@param l_tsAppliEndDate - (適用開始日（至）)<BR>
     * 適用開始日（至）<BR>
     * @@param l_sortKeys - (ソート条件)<BR>
     * 対象項目<BR>
     * ≪照会の場合≫<BR>
     * 　@通番／ID／ステータス／部店／顧客／適用開始日／適用終了日／最終更新日／最終更新者<BR>
     * ≪ﾀﾞｳﾝﾛｰﾄﾞの場合≫<BR>
     * 　@通番<BR>
     * @@return OtherOrgInfoAdminParams[]
     * @@throws WEB3BaseException
     */
    public OtherOrgInfoAdminParams[] getOtherOrgInfoList(
        String l_strSequenceNumber,
        String l_strServiceDiv,
        String l_strID,
        String l_strStatus,
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        WEB3SrvRegiSortKey[] l_sortKeys) throws WEB3BaseException;

    /**
     * (get未使用通番情報)<BR>
     * 外部連携情報管理テーブルにおいて、ステータスが未使用のレコードの中で、<BR>
     * 通番が最小のレコード（外部連携情報管理オブジェクト）を返却する。<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * is行ロック<BR>
     * true：行ロックをする。　@false：行ロックをしない。<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getUnUseSequenceNumberInfo(
        String l_strServiceDiv,
        boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (submit外部連携情報)<BR>
     * 外部連携情報管理テーブルのUPDATEを行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）submit外部連携情報」参照<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_tsAppliEndDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_blnIsNewApplyDiv - (新規申込区分)<BR>
     * 新規申込区分<BR>
     * @@throws WEB3BaseException
     */
    public void submitOtherOrgInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strServiceDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        boolean l_blnIsNewApplyDiv) throws WEB3BaseException;

    /**
     * (submit外部連携情報)<BR>
     * 外部連携情報管理テーブルのUPDATEを行う。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strApplyLotteryDiv - (申込抽選区分)<BR>
     * 申込抽選区分<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_tsAppliEndDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_blnIsNewApplyDiv - (新規申込区分)<BR>
     * 新規申込区分<BR>
     * @@throws WEB3BaseException
     */
    public void submitOtherOrgInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strApplyLotteryDiv,
        String l_strServiceDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        boolean l_blnIsNewApplyDiv) throws WEB3BaseException;
}
@
