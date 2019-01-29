head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.50.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用顧客データアップロードUnitService(WEB3AdminSrvRegiAccountDataUploadUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 鄭海良(中訊) 新規作成
Revesion History : 2007/06/06 趙林鵬 (中訊) モデル254
Revesion History : 2007/07/11 孟亜南(中訊) モデル280
*/

package webbroker3.srvregi.service.delegate;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;


/**
 * (サービス利用顧客データアップロードUnitService)<BR>
 * サービス利用顧客データアップロードUnitService　@インターフェイス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public interface WEB3AdminSrvRegiAccountDataUploadUnitService extends Service 
{
    /**
     * (update申込登録)<BR>
     * update申込登録処理を行う。<BR>
     * <BR>
     * シーケンス図「(サービス利用)顧客データアップロード・update申込登録」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strUploadDiv - (アップロード区分)<BR>
     * 登録／変更／抽選結果アップロード<BR>
     * @@param l_registId - (申込登録ID)<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@param l_tsAppliDate - (申込日)<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 試用／申込／当選（本申込）／落選／取消<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 無料／有料<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * @@roseuid 41109351035E
     */
    public void updateAppliRegist(
        WEB3GentradeSubAccount l_subAccount, 
        String l_strUploadDiv, 
        Long   l_registId, 
        String l_strInstitutionCode, 
        String l_strSrvDiv, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        Timestamp l_tsAppliStartDate, 
        Timestamp l_tsAppliEndDate, 
        Timestamp l_tsAppliDate, 
        String l_strAppliLotDiv, 
        String l_strPaymentDiv, 
        Double l_dblUseAmt, 
        Timestamp l_tsPaymentDate, 
        String l_strPassword) throws WEB3BaseException; 

    /**
     * (insertサービス申込属性)<BR>
     * insertサービス申込属性処理を行う。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 試用／申込／当選（本申込）／落選／取消<BR>
     * <BR>
     * ※アップロード区分 = 3：申込属性 の場合<BR>
     * 無料／利用不可<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * 適用終了日<BR>
     * @@throws WEB3BaseException
     */
    public void insertSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv,
        String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate)
        throws WEB3BaseException;

    /**
     * (updateサービス申込属性)<BR>
     * updateサービス申込属性処理を行う。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 試用／申込／当選（本申込）／落選／取消<BR>
     * <BR>
     * ※アップロード区分 = 3：申込属性 の場合<BR>
     * 無料／利用不可<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * 適用終了日<BR>
     * @@throws WEB3BaseException
     */
    public void updateSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv,
        String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate)
        throws WEB3BaseException;
    
    /**
     * (deleteサービス申込属性)<BR>
     * deleteサービス申込属性処理を行う。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@throws WEB3BaseException
     */
    public int deleteSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv)
        throws WEB3BaseException;
}
@
