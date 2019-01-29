head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FEqConTransferDataControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替連携データ制御サービスクラス(WEB3FEqConTransferDataControlService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 韋念瓊 (中訊) 新規作成       
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.aio.data.UwgTransferStatusParams;
import webbroker3.aio.message.WEB3FEqConAccountOpenQuestionInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionParams;

/**
 * (外株振替連携データ制御サービス)<BR>
 * 外株振替連携データ制御サービスインターフェイス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public interface WEB3FEqConTransferDataControlService extends Service 
{
    
    /**
     * (get外国株式顧客)<BR>
     * 引数の証券会社コード、部店コード、顧客コードに該当する<BR>
     * 外国株式顧客Paramsを取得する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return FeqAccountParams
     * @@roseuid 41E4B4E50020
     */
    public FeqAccountParams getFeqAccountByAccountCode(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode) 
        throws WEB3BaseException, NotFoundException;
    
    /**
     * (get外国株式顧客)<BR>
     * 引数の証券会社コード、部店コード、<BR>
     * 外株口座番号に該当する外国株式顧客Paramsを取得する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strFeqAccountCode - 外国株式口座番号
     * 
     * @@return FeqAccountParams
     * @@roseuid 41EF678B018D
     */
    public FeqAccountParams getFeqAccountByFeqAccountCode(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strFeqAccountCode) 
        throws WEB3BaseException, NotFoundException;
    
    /**
     * (get外国株式顧客)<BR>
     * 引数の外国株式顧客IDに該当する外国株式顧客Paramsを取得する。
     * @@param l_strFeqAccountId - 外国株式顧客ID
     * 
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 41E61D970136
     */
    public FeqAccountParams getFeqAccountByAccountId(String l_strFeqAccountId)
        throws WEB3BaseException, NotFoundException;
    
    /**
     * (get質問)<BR>
     * 引数の証券会社コード、部店コードに一致する<BR>
     * 質問Paramsを返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@return QuestionParams[]
     * @@roseuid 41E4B4E500DC
     */
    public QuestionParams[] getQuestion(
            String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException;
    
    /**
     * (get質問回答)<BR>
     * 引数の証券会社コード、部店コード、識別コードに一致する<BR>
     * 質問回答Paramsを返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return QuestionAnswerParams[]
     * @@roseuid 41E4B4E500FB
     */
    public QuestionAnswerParams[] getQuestionAnswer(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) throws WEB3BaseException;
    
    /**
     * (getUWG口座開設状況)<BR>
     * 引数の証券会社コード、部店コード、識別コードに<BR>
     * 該当するUWG口座開設状況Paramsを返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return UwgAccountOpenStatusParams
     * @@roseuid 41E4B4E5011A
     */
    public UwgAccountOpenStatusParams getUwgAccountOpenStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) throws WEB3BaseException;
    
    /**
     * (getUWG口座開設状況)<BR>
     * 引数の条件に該当するUWG口座開設状況Paramsの<BR>
     * 一覧を返却する。
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@return UwgAccountOpenStatusParams[]
     * @@roseuid 41EF5C7F0287
     */
    public UwgAccountOpenStatusParams[] getUwgAccountOpenStatus(
            String l_strQueryString, 
            String[] l_queryContainer, 
            String l_strSortCond) throws WEB3BaseException;
    
    /**
     * (getUWG振替状況)<BR>
     * 引数の証券会社コード、部店コード、識別コードに<BR>
     * 該当するUWG振替状況Paramsを返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return UwgTransferStatusParams
     * @@roseuid 41E4B4E50159
     */
    public UwgTransferStatusParams getUwgTransferStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) throws WEB3BaseException;
    
    /**
     * (insert外国株式顧客)<BR>
     * UWG口座開設状況Paramsの内容より、<BR>
     * 外国株式顧客テーブルに行のinsertを行う。
     * @@param l_uwgAccOpenStatusParams - UWG口座開設状況Paramsオブジェクト
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 41E4B4E50197
     */
    public void insertFeqAccount(
            UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, 
            String l_strUpdaterCode) throws WEB3BaseException;
    
    /**
     * (insert質問回答)<BR>
     * 外株口座開設質問情報の内容で<BR>
     * 質問回答テーブル(question_answer)に行のinsertを行う。<BR>
     * ※引数.質問情報一覧の要素数分のLoop処理を行い、要素ごとに行のinsertを行う。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_feqAccOpenQuestionInfo - 外株口座開設質問情報の一覧
     * @@roseuid 41E4B4E50243
     */
    public void insertQuestionAnswer(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber, 
            WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo)
        throws WEB3BaseException;
        
    /**
     * (insertUWG口座開設状況)<BR>
     * UWG口座開設状況テーブルに行のinsertを行う。
     * @@param l_mainAccount - 顧客オブジェクト
     * @@param l_strPassword - UWG用パスワード
     * @@param l_strOrderRequestNumber - 識別コード
     * @@roseuid 41E4B4E50262
     */
    public void insertUwgAccountOpenStatus(
            MainAccount l_mainAccount, 
            String l_strPassword, 
            String l_strOrderRequestNumber)
        throws WEB3BaseException;
    
    /**
     * (insertUWG振替状況)<BR>
     * UWG振替状況テーブルに行のinsertを行う。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strTransferDate - 受渡予定日
     * @@param l_strRequestNumber - 識別コード
     * @@param l_strMrgTrnRequestNumber - 信用振替用識別コード
     * ※信用口座からの強制振替を行わない場合、null
     * @@param l_strTransferAmount - 振替金額
     * @@roseuid 41E4B4E50281
     */
    public void insertUwgTransferStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strTransferDate, 
            String l_strRequestNumber, 
            String l_strMrgTrnRequestNumber, 
            String l_strTransferAmount) 
        throws WEB3BaseException;
    
    /**
     * (update外国株式顧客)<BR>
     * 更新後口座開設区分の値で外国株式顧客テーブルを更新する。
     * @@param l_feqAccountParams - 外国株式顧客Paramオブジェクト
     * @@param l_strUpdatedAccOpenDiv - 更新後口座開設区分
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 41E4B4E502EF
     */
    public void updateFeqAccount(
            FeqAccountParams l_feqAccountParams, 
            String l_strUpdatedAccOpenDiv, 
            String l_strUpdaterCode)
        throws WEB3BaseException;
    
    /**
     * (updateUWG口座開設状況)<BR>
     * 引数のUWG口座開設状況Paramsで<BR>
     * UWG口座開設状況テーブルを更新する。
     * @@param l_uwgAccOpenStatusParams - UWG口座開設状況Paramsオブジェクト
     * @@param l_strUpdatedStatusDiv - 更新後ステータス区分
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 41E4B4E5032D
     */
    public void updateUwgAccountOpenStatus(
            UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, 
            String l_strUpdatedStatusDiv, 
            String l_strUpdaterCode)
        throws WEB3BaseException;
    
    /**
     * (updateUWG振替状況)<BR>
     * 引数のUWG振替状況ParamsでUWG振替状況テーブルを更新する。
     * @@param l_uwgTransferStatusParams - UWG振替状況Paramsオブジェクト
     * @@param l_strUpdatedTransferStatusDiv - 更新後振替状況区分
     * @@roseuid 41ECE02B02CE
     */
    public void updateUwgTransferStatus(
            UwgTransferStatusParams l_uwgTransferStatusParams, 
            String l_strUpdatedTransferStatusDiv)
        throws WEB3BaseException;
    
    /**
     * (update外株口座開設区分)<BR>
     * 顧客マスターテーブルの外国証券口座開設区分をupdateする。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strUpdatedFeqAccOpenDiv - 更新後外株口座開設区分
     * 
     * 0：開設
     * 1：未開設
     * 
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 41E4B4E503D9
     */
    public void updateFeqAccountOpenDiv(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strUpdatedFeqAccOpenDiv, 
            String l_strUpdaterCode)
        throws WEB3BaseException;
    
    /**
     * (validate外株口座開設質問)<BR>
     * 外株口座開設質問に対する回答の整合性をチェックする。
     * @@param l_feqAccOpenQuestionInfo - 外株口座開設質問情報の一覧
     * @@roseuid 41E4B4E6009D
     */
    public void validateFeqAccountOpenQuestion(
            WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo)
        throws WEB3BaseException;
    
    /**
     * (get新規外国株式顧客ID)<BR>
     * 外国株式顧客IDを付番して返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return java.lang.String
     * @@roseuid 41E633DF027F
     */
    public String getNewFeqAccountId(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode)
        throws WEB3BaseException;
}
@
