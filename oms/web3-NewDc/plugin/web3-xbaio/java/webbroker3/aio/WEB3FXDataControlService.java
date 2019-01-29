head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.28.06.22.26;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2f84d9029215dc5;
filename	WEB3FXDataControlService.java;

1.1
date	2011.03.16.02.22.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXDataControlService.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXデータ制御サービス(WEB3FXDataControlService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 周勇 (中訊) 新規作成
                 : 2006/02/08 鄭徳懇 (中訊) 仕様変更・モデル469、471
                 : 2006/02/24 黄浩澎 (中訊) 仕様変更・モデル446、459、465、510
                 : 2006/04/27 肖志偉 (中訊) 仕様変更・モデル546
                 : 2006/05/08 周捷 (中訊) 仕様変更・モデル550
                 : 2006/05/08 周捷 (中訊) 仕様変更・モデル549、557、559
                 : 2006/05/12 郭英 (中訊) 仕様変更・モデル571
                 : 2006/05/16 周捷 (中訊) 仕様変更・モデル583 
                 : 2006/07/12 韋念瓊 (中訊) 仕様変更・モデル598
                 : 2006/08/23 鈴木 (SCS) 仕様変更・モデル631
Revesion History : 2008/04/08 周墨洋 (中訊) 仕様変更・モデル838
                 : 2008/05/08 佐藤 (SCS) 仕様変更・モデル838
                 : 2008/05/23 三島 (SCS) 
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更・モデル851、856、867、872、873
Revesion History : 2008/06/17 柴双紅 (中訊) 仕様変更・モデル899
Revesion History : 2008/06/23 柴双紅 (中訊) 仕様変更・モデル904、905
Revesion History : 2008/09/08 王志葵 (中訊) 仕様変更・モデル973
Revesion History : 2008/10/02 竹村 (SCS) 仕様変更・モデル1046
Revesion History : 2008/10/07 王志葵 (中訊) 仕様変更・モデル990,1002,1027,1050,1065,1072
Revesion History : 2008/11/14 佐藤 (SCS) 仕様変更・モデル1084
Revesion History : 2008/12/16 大嶋 (SCS) 仕様変更・モデル1088,1089
Revesion History : 2009/01/22 吉原 (SCS) 仕様変更・モデル1092,1093,1094
Revesion History : 2009/03/12 柴双紅 (中訊) 仕様変更・モデル1108、1151
Revesion History : 2009/03/19 車進 (中訊) 仕様変更・モデル1124、1135、1136、1157
Revesion History : 2009/04/20 車進 (中訊) 仕様変更・モデル1161
Revesion History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1214
*/
package webbroker3.aio;

import java.util.ArrayList;
import java.util.Date;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftMessageParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.gftforex.soap_api.ResultInfoAddAccount;
import com.gftforex.soap_api.ResultInfoCreateUser;

/**
 * (FXデータ制御サービス) <BR>
 * FXデータ制御サービスインターフェイス。 <BR>
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public interface WEB3FXDataControlService extends Service
{
    /**
     * (getFX顧客) <BR>
     * 引数の証券会社コード、部店コード、FXシステムコード、顧客コードに<BR>
     *      該当するFX顧客Paramsを取得する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strFxSystemCode - FXシステムコード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.data.FxAccountParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41CFFC340100
     */
    public FxAccountParams getFXAccount(String l_strInstitutionCode,
        String l_strBranchCode, String l_strFxSystemCode,
        String l_strAccountCode) throws WEB3BaseException, NotFoundException;

    /**
     * (getFX顧客) <BR>
     * 引数の証券会社コード、部店コード、顧客コードに該当するFX顧客Paramsを取得する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.data.FxAccountParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41BEA852035B
     */
    public FxAccountParams getFXAccount(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException, NotFoundException;

    /**
     * (getFX顧客) <BR>
     * 引数の条件に該当するFX顧客Paramsの一覧を返却する。 <BR>
     * 
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@return webbroker3.aio.data.FxAccountParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BECC490223
     */
    public FxAccountParams[] getFXAccounts(String l_strQueryString,
        String[] l_queryContainer, String l_strSortCond) throws WEB3BaseException;

    /**
     * (getFX口座開設区分)<BR>
     * 引数の条件に該当するFX口座開設区分を取得する。<BR>
     * @@param l_strUpdatedAccOpenDiv - (更新後口座開設状況区分)<BR>
     * 更新後口座開設状況区分<BR>
     * @@return String
     * @@roseuid 41BECC490223
     */
    public String getFXAccountOpenDiv(String l_strUpdatedAccOpenDiv);

    /**
     * (getFX口座番号) <BR>
     * 引数の条件に該当するFX口座番号を取得する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strCourseDiv - コース区分
     * 
     * 0： DEFAULT 1： 1万通貨コース 2： 10万通貨コース
     * @@return webbroker3.aio.data.FxAccountCodeParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41C936E801D6
     */
    public FxAccountCodeParams getFXAccountCode(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode, String l_strCourseDiv) 
        throws WEB3BaseException, NotFoundException;

    /**
     * (getFX口座番号) <BR>
     * 引数の条件に該当するFX口座番号の一覧を取得する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.data.FxAccountCodeParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7F0B20087
     */
    public FxAccountCodeParams[] getFXAccountCodes(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;

    /**
     * (getFX口座番号)<BR>
     * 引数の条件に該当するFX口座番号の一覧を取得する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strCourseDiv - (コース区分)<BR>
     * コース区分<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@return FxAccountCodeParams[]
     * @@throws WEB3BaseException
     */
    public FxAccountCodeParams getFXAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strCourseDiv,
        String l_strFxSystemCode) throws WEB3BaseException, NotFoundException;

    /**
     * (get会社別FXシステム条件) <BR>
     * 引数の証券会社コード、部店コードに一致する会社別FXシステム条件Paramsを返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@return webbroker3.aio.data.CompFxConditionParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41BEA85203C9
     */
    public CompFxConditionParams getCompFxCondition(
        String l_strInstitutionCode, String l_strBranchCode) throws WEB3BaseException, NotFoundException;

    /**
     * (get質問) <BR>
     * 引数の証券会社コード、部店コード、FXシステムコードに一致する<BR>
     * 質問Paramsを返却する。
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strFxSystemCode - FXシステムコード
     * @@return QuestionParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41C94FB5035C
     */
    public QuestionParams[] getQuestions(String l_strInstitutionCode,
        String l_strBranchCode, String l_strFxSystemCode) throws WEB3BaseException;

    /**
     * (get質問回答) <BR>
     * 引数の証券会社コード、部店コード、識別コードに一致する <BR>
     * 質問回答Paramsを返却する。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strQuestionDiv - 質問区分
     * @@param l_strRequestNumber - 識別コード
     * @@return QuestionAnswerParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BEA853006D
     */
    public QuestionAnswerParams[] getQuestionAnswers(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strQuestionDiv, String l_strRequestNumber) throws WEB3BaseException;

    /**
     * (getGFT口座開設状況) <BR>
     * 引数の証券会社コード、部店コード、識別コードに <BR>
     * 該当するGFT口座開設状況Paramsを返却する。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.aio.data.GftAccountOpenStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41BEA853000F
     */
    public GftAccountOpenStatusParams getGFTAccountOpenStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestNumber) throws WEB3BaseException;

    /**
     * (getGFT口座開設状況) <BR>
     * 引数の条件に該当するGFT口座開設状況Paramsの一覧を返却する。 <BR>
     * 
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@return webbroker3.aio.data.GftAccountOpenStatusParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BEA853003E
     */
    public GftAccountOpenStatusParams[] getGFTAccountOpenStatuses(
        String l_strQueryString, String[] l_queryContainer, String l_strSortCond) 
        throws WEB3BaseException;

    /**
     * (getGFT振替状況) <BR>
     * 引数の証券会社コード、部店コード、識別コードに <BR>
     * 該当するGFT振替状況Paramsを返却する。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.aio.data.GftTransferStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41C130DC001E
     */
    public GftTransferStatusParams getGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestNumber) throws WEB3BaseException;

    /**
     * (getGFT振替状況) <BR>
     * 引数の証券会社コード、部店コード、顧客コード、入出金番号に <BR>
     * 該当するGFT振替状況Paramsを返却する。<BR>
     * ２）検索結果を返却する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strCashInOutNumber - 入出金番号
     * @@return webbroker3.aio.data.GftTransferStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41C130DC001E
     */
    public GftTransferStatusParams getGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode, String l_strCashInOutNumber) throws WEB3BaseException;

    /**
     * (getGFT電文保存) <BR>
     * 引数の電文種別区分、処理区分、証券会社コード、部店コード、識別コードに <BR>
     *  該当するGFT電文保存Paramsを返却する。
     * 
     * @@param l_strMessageDiv - 電文種別区分
     * @@param l_strOperationDiv - 処理区分
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.aio.data.GftMessageParams
     * @@throws WEB3BaseException
     * @@roseuid 41C15A1D0389
     */
    public GftMessageParams getGFTMessage(String l_strMessageDiv,
        String l_strOperationDiv, String l_strInstitutionCode,
        String l_strBranchCode, String l_strRequestNumber) throws WEB3BaseException;

    /**
     * (insertFX顧客) <BR>
     * GFT口座開設状況Paramsの内容より、 <BR>
     * FX顧客テーブル(fx_account)に行のinsertを行う。
     * 
     * @@param l_gftAccountOpenStatusParams - GFT口座開設状況Paramsオブジェクト
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41C8120402A5
     */
    public void insertFXAccount(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (insertFX顧客) <BR>
     * GFT結果通知電文の内容でFX顧客テーブル(fx_account)に行のinsertを行う。
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@param l_gftAccontOpenStatusParams - GFT口座開設状況Params
     * @@throws WEB3BaseException
     * @@roseuid 41C9726F03AA
     */
    public void insertFXAccount(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit, 
        GftAccountOpenStatusParams l_gftAccontOpenStatusParams)
        throws WEB3BaseException;

    /**
     * (insertFX口座番号) <BR>
     * GFT口座開設状況Params、FX口座情報の内容より、 <BR>
     * FX口座番号テーブル(fx_account_code)に行のinsertを行う。
     * 
     * @@param l_gftAccountOpenStatusParams - GFT口座開設状況Paramsオブジェクト
     * @@param l_fxAccInformation - FX口座情報オブジェクト
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41C8120402C5
     */
    public void insertFXAccountCode(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXAccInformationUnit l_fxAccInformation, String l_strUpdaterCode)
        throws WEB3BaseException;

    /**
     * (insertFX口座番号)<BR>
     * GFT結果通知電文の内容とFXシステムコードででFX口座番号テーブル(fx_account_code)<BR>
     * 　@に行のinsertを行う。<BR>
     * ※為替保証金口座番号別に2件insertを行う。<BR>
     * <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT結果通知電文明細)<BR>
     * GFT結果通知電文明細<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@param l_strSimultaneousFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@throws WEB3BaseException
     */
    public void insertFXAccountCode(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strFxSystemCode,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException;

    /**
     * (insert質問回答) <BR>
     * FX取引同意質問情報の内容で質問回答テーブル(question_answer)に行のinsertを行う。<BR>
     * ※引数.FX取引同意質問情報一覧の要素数分のLoop処理を行い、<BR>
     * 要素ごとに行のinsertを行う。
     * 
     * @@param l_strInstitution - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_fxTradeAgreementList - FX取引同意質問情報の一覧
     * @@param l_strFxSystemCode - FXシステムコード
     * @@throws WEB3BaseException
     * @@roseuid 41CF662701CF
     */
    public void insertQuestionAnswer(String l_strInstitution,
        String l_strBranchCode, String l_strRequestNumber,
        WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList,
        String l_strFxSystemCode) throws WEB3BaseException;

    /**
     * (insertGFT口座開設状況) <BR>
     * GFT依頼電文の内容とFXシステムコードでGFT口座開設状況テーブルに行のinsertを行う。<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@param l_strAgreementDiv - (約諾書区分)<BR>
     * 約諾書区分<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C961DA03A3
     */
    public void insertGFTAccountOpenStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strAgreementDiv,
        String l_strFxSystemCode) throws WEB3BaseException;

    /**
     * (insertGFT振替状況) <BR>
     * GFT依頼電文の内容でGFT振替状況テーブルに行のinsertを行う。
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@param l_strCourseDiv - コース区分
     * @@param l_strTransferDate - 受渡予定日
     * @@param l_strMrgTrnRequestNumber - 信用振替用識別コード
     * <BR>
     * ※信用口座からの強制振替を行わない場合、null
     * @@param l_compFxConditionParams - 会社別FXシステム条件Params
     * @@param l_strIoListTradeDiv - 入出金一覧取引区分
     * @@throws WEB3BaseException
     * @@roseuid 41BEA85300DB
     */
    public void insertGFTTransferStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strCourseDiv, String l_strTransferDate,
        String l_strMrgTrnRequestNumber,
        CompFxConditionParams l_compFxConditionParams,
        String l_strIoListTradeDiv) throws WEB3BaseException;

    /**
     * (insertGFT振替状況) <BR>
     * GFT依頼電文の内容でGFT振替状況テーブル(gft_transfer_status)に行のinsertを行う。 <BR>
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@param l_strCourseDiv - コース区分
     * @@param l_strTransferDate - 受渡予定日
     * @@param l_strMrgTrnRequestNumber - 信用振替用識別コード
     * @@param l_strCashInOutNumber - 入出金番号
     * @@param l_strIoListTradeDiv - 入出金一覧取引区分
     * @@throws WEB3BaseException
     * @@roseuid 41BE98D10109
     */
    public void insertGFTTransferStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strCourseDiv, String l_strTransferDate,
        String l_strMrgTrnRequestNumber,
        String l_strCashInOutNumber,
        String l_strIoListTradeDiv) throws WEB3BaseException;
    
    /**
     * (insertGFT電文保存) <BR>
     * GFT依頼電文の内容でGFT電文保存テーブルに行のinsertを行う。
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@throws WEB3BaseException
     * @@roseuid 41BEA8530129
     */
    public void insertGFTMessage(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit) throws WEB3BaseException;

    /**
     * (insertGFT電文保存) <BR>
     * GFT結果通知電文の内容でGFT電文保存テーブル(gft_transfer_status)に行のinsertを行う 。
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@throws WEB3BaseException
     * @@roseuid 41C10C4E032B
     */
    public void insertGFTMessage(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) 
        throws WEB3BaseException;

    /**
     * (updateFX顧客) <BR>
     * 更新後の値でFX顧客テーブルの口座開設状況区分を更新する。
     * 
     * @@param l_fxAccountParams - FX顧客Paramオブジェクト
     * @@param l_strUpdatedAccOpenStatusDiv - 更新後口座開設状況区分
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41BEEAF40018
     */
    public void updateFXAccount(FxAccountParams l_fxAccountParams,
        String l_strUpdatedAccOpenStatusDiv,
        String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (updateFX顧客) <BR>
     * 更新後の値でFX顧客テーブルのメールアドレスを更新する。
     * 
     * @@param l_fxAccountParams - FX顧客Paramオブジェクト
     * @@param l_strUpdatedMailAddress - 更新後メールアドレス
     * @@param l_strUpdaterCode - 更新者コード
     * @@param l_fxSystemCodeList - FXシステムコード一覧
     * @@throws WEB3BaseException
     * @@roseuid 41BEEAF40018
     */
    public void updateFXAccount(FxAccountParams l_fxAccountParams,
        String l_strUpdatedMailAddress, String l_strUpdaterCode, 
		ArrayList l_fxSystemCodeList) throws WEB3BaseException;

    /**
     * (updateFX顧客メールアドレス)<BR>
     * 更新後の値でFX顧客テーブルのメールアドレスを更新する。<BR>
     * <BR>
     * @@param l_fxAccountParams - (FX顧客Params)<BR>
     * FX顧客Paramオブジェクト<BR>
     * @@param l_strUpdatedMailAddress - (更新後メールアドレス)<BR>
     * 更新後メールアドレス<BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BEEAF40018
     */
    public void updateFXAccountMailAddress(
        FxAccountParams l_fxAccountParams,
        String l_strUpdatedMailAddress,
        String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (updateFX口座番号) <BR>
     * 更新後の値でFX口座番号テーブルを更新する。
     * 
     * @@param l_fxAccountCodeParams - FX口座番号Paramオブジェクト
     * @@param l_strUpdatedAccCode - 更新後のFX口座番号
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41C80EA000EC
     */
    public void updateFXAccountCode(FxAccountCodeParams l_fxAccountCodeParams,
        String l_strUpdatedAccCode, String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (updateGFT口座開設状況) <BR>
     * 引数のGFT口座開設状況ParamsでGFT口座開設状況テーブルを更新する。
     * 
     * @@param l_gftAccountOpenStatusParams - GFT口座開設状況Paramsオブジェクト
     * @@param l_strUpdatedStatusDiv - 更新後ステータス区分
     * @@param l_updatedFxAccInformationUnits - 更新後のFX口座情報の配列
     * @@param l_strUpdaterCode - 更新者コード
     * @@param l_strUpdatedAgreementDiv - (更新後約諾書区分)<BR>
     * 更新後約諾書区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BEA853009C
     */
    public void updateGFTAccountOpenStatus(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strUpdatedStatusDiv,
        WEB3FXAccInformationUnit[] l_updatedFxAccInformationUnits,
        String l_strUpdaterCode, 
        String l_strUpdatedAgreementDiv) throws WEB3BaseException;

    /**
     * (updateGFT口座開設状況) <BR>
     * GFT結果通知の状態でGFT口座開設状況テーブルのデータをupdateする。
     * 
     * @@param l_gftAccountOpenStatusParams - GFT口座開設状況Paramsオブジェクト
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@param l_strErrorReasonCode - エラー理由コード
     * @@throws WEB3BaseException
     * @@roseuid 41C97A830098
     */
    public void updateGFTAccountOpenStatus(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strErrorReasonCode) throws WEB3BaseException;

    /**
     * (updateGFT振替状況) <BR>
     * 引数のGFT振替状況ParamsでGFT振替状況テーブルを更新する。
     * 
     * @@param l_gftTransferStatusParams - GFT振替状況Paramsオブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 41C130FA031B
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams) throws WEB3BaseException;

    /**
     * (updateGFT振替状況) <BR>
     * GFT結果通知の状態でGFT振替状況テーブルのデータをupdateする。
     * 
     * @@param l_gftTransferStatusParams - GFT振替状況Paramsオブジェクト
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@param l_strUpdatedTransferDate - YYYYMMDD
     * 
     * 更新後受渡予定日
     * 
     * ※受渡予定日を更新しない場合はnull
     * @@param l_strErrorReasonCode - エラー理由コード
     * @@throws WEB3BaseException
     * @@roseuid 41C9714E002C
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams,
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strUpdatedTransferDate, String l_strErrorReasonCode) 
        throws WEB3BaseException;

    /**
     * (updateGFT振替状況) <BR>
     * GFT振替状況テーブルの振替状況区分をupdateする。 <BR>
     * @@param l_gftTransferStatusParams - GFT振替状況Paramsオブジェクト
     * @@param l_strUpdatedTransferStatusDiv - 更新後の振替状況区分
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41CFF87603C9
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams,
        String l_strUpdatedTransferStatusDiv, String l_strUpdaterCode)
        throws WEB3BaseException;

    /**
     * (updateGFT振替状況) <BR>
     * SOAP受信結果をGFT振替状況テーブルのデータに反映する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestCode - 識別コード
     * @@param l_strResultCode - 受付結果コード
     * @@throws WEB3BaseException
     * @@roseuid 41CBC3DD00B3
     */
    public void updateGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestCode, String l_strResultCode) throws WEB3BaseException;
    
    /**
     * (updateFX口座開設区分) <BR>
     * 顧客マスターテーブルのFX口座開設区分をupdateする。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strUpdatedFxAccOpenDiv - 更新後FX口座開設区分 0：DEFAULT(口座なし) 1：口座開設
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41CBF73000AF
     */
    public void updateFXAccountOpenDiv(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode,
        String l_strUpdatedFxAccOpenDiv, String l_strUpdaterCode) 
        throws WEB3BaseException;

    /**
     * (createFX口座情報一覧) <BR>
     * 引数の条件に該当するFX口座情報の一覧を作成する。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.message.WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7F0B20097
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException;

    /**
     * (get新規FXログインID) <BR>
     * FXログインIDを付番して返却する。
     * 
     * @@param l_strFXloginFirstChar - FXログインID頭文字
     * @@param l_strAccountCode - 顧客コード
     * @@return String
     * @@roseuid 41C9666F0298
     */
    public String getNewFXLoginID(String l_strFXloginFirstChar,
        String l_strAccountCode);

    /**
     * (get新規FX顧客ID) <BR>
     * FX顧客IDを付番して返却する。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strFxSystemCode - FXシステムコード
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C9753A02A1
     */
    public String getNewFXAccountID(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode, String l_strFxSystemCode)
        throws WEB3BaseException;

    /**
     * (validateFX取引同意質問) <BR>
     * FX取引同意質問に対する回答の整合性をチェックする。
     * 
     * @@param l_fxTradeAgreementList - FX取引同意質問情報の一覧
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public void validateFXTradingAgreeQuestion(
        WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList) 
        throws WEB3BaseException;
    
    /**
     * (submit取消注文) <BR>
     * 振替注文の取消処理と余力の更新処理を行う。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_strMrgTrnRequestNumber - 信用振替用識別コード
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public void submitCancelOrder(String l_strInstitutionCode, 
        String l_strBranchCode, String l_strAccountCode, 
        String l_strRequestNumber, String l_strMrgTrnRequestNumber) 
        throws WEB3BaseException;
    
    /**
     * (insert説明不要承諾履歴 ) <BR>
     * 口座開設申込時に「説明不要」を選択した場合、<BR> 
     * FX説明不要承諾履歴管理テーブル.に行のinsertを行う。<BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public void insertUnnecessaryExplanation(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode) 
            throws WEB3BaseException;
    
    /**
     * (validateFXドキュメント閲覧履歴) <BR>
     * 電子鳩システムへ接続し、該当顧客の閲覧履歴が存在する<BR>
     * かのチェックを行う。 <BR>
     * 
     * @@param l_strTypeCode - 種別コード
     * @@param l_strRequestCode - 識別コード
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public String[] validateFxDocReadHistory(
        String l_strTypeCode, 
        String[] l_strRequestCode) 
            throws WEB3BaseException;
    
    /**
     * (get会社別FXシステム条件)<BR>
     * 引数の証券会社コード、部店コード、FXシステムコードに一致する<BR>
     * 会社別FXシステム条件Paramsを返却する。<BR> 
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strFxSystemCode - (FXシステムコード)
     * @@return webbroker3.aio.data.CompFxConditionParams
     * @@throws WEB3BaseException 
     * @@throws NotFoundException 
     */
    public CompFxConditionParams getCompFxCondition(
        String l_strInstitutionCode, String l_strBranchCode, String l_strFxSystemCode) 
            throws WEB3BaseException, NotFoundException;

    /**
     * (getFX振替条件マスタ)<BR>
     * 引数のFXシステム条件ID、振替区分に一致する<BR>
     * FX振替条件マスタParamsを返却する<BR>
     * <BR>
     * @@param l_lngFxSystemId - (FXシステム条件ID)<BR>
     * FXシステム条件ID<BR>
     * @@param l_strTransferDiv - (振替区分)<BR>
     * 振替区分<BR>
     * @@return FxTransferMasterParams
     * @@throws WEB3BaseException
     */
    public FxTransferMasterParams getFxTransferMasterParams(
        long l_lngFxSystemId,
        String l_strTransferDiv) throws WEB3BaseException;

    /**
     * (get受渡日)<BR>
     * 受渡日を取得し、返却する。<BR> 
     * <BR>
     * @@param l_datOrderBizDate - (発注日)
     * @@param l_subAccount - (補助口座)
     * @@param l_strDeliveryDateSetDiv - (受渡日設定区分)
     * @@return Date
     * @@throws WEB3BaseException 
     */
    public Date getDeliveryDate(Date l_datOrderBizDate, SubAccount l_subAccount,
        String l_strDeliveryDateSetDiv) throws WEB3BaseException;
    
    /**
     * (updateFX口座開設区分更新者コード)<BR>
     * 顧客マスターテーブルのFX口座開設区分更新者コードをupdateする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strUpdaterCode- (更新者コード)
     * @@throws WEB3BaseException 
     */
    public void updateFXAccountOpenDivUpdaterCode(
    	String l_strInstitutionCode, String l_strBranchCode, 
        String l_strAccountCode, String l_strUpdaterCode) 
            throws WEB3BaseException;
    
    /**
     * (getアップロード最新履歴)<BR>
     * 最新のアップロード履歴を取得する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strUploadFileID - (アップロードファ@イルＩＤ)
     * @@param l_strProductType - (銘柄タイプ)
     * @@param l_lngDataKey - (データキー)
     * @@return Object
     * @@throws WEB3BaseException 
     */
    public Object getUploadNewHistory(String l_strInstitutionCode, 
        String l_strUploadFileID, String l_strProductType, long l_lngDataKey) 
            throws WEB3BaseException;
    
    /**
     * (createFX口座情報一覧)<BR>
     * 引数の条件に該当するFX口座情報の一覧を作成する。
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strFxSystemCode - (FXシステムコード)
     * @@return WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException 
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException;
    
    /**
     * (getFX口座番号)<BR>
     * 引数の条件に該当するFX口座番号の一覧を取得する。
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strFxSystemCode - (FXシステムコード)
     * @@return FxAccountCodeParams[]
     * @@throws WEB3BaseException 
     */
    public FxAccountCodeParams[] getFXAccountCodes(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException;
    
    /**
     * (get入出金額区分)<BR>
     * GFT電文明細.入出金額より、区分を返却する。<BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT結果通知電文明細)<BR>
     * GFT結果通知電文明細<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public String getCashInOutAmountDiv(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) throws WEB3BaseException;

    /**
     * (updateGFT口座開設状況)<BR>
     * SOAP受信結果をGFT口座開設状況テーブルのデータに反映する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * 部店コード<BR>
     * @@param l_strOrderRequestNumber - 識別コード<BR>
     * 識別コード<BR>
     * @@param l_strResultCode - 受付結果コード<BR>
     * 受付結果コード<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTAccountOpenStatus(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strOrderRequestNumber,
        String l_strResultCode)
            throws WEB3BaseException;
    
	/**
	 * (validate接続準備) <BR>
	 * SOAP接続準備を行う。<BR>
	 * @@param l_rpcParams - 外部システムSOAP接続プリファ@レンス（RPC形式）params<BR>
	 * 外部システムSOAP接続プリファ@レンス（RPC形式）params<BR>
	 * @@throws WEB3BaseException
	 */
	public void validateSetup(
			SoapConnectPrefRpcParams l_rpcParams)
		    throws WEB3BaseException;

	/**
	 * (get顧客コード)<BR>
	 * 引数の条件に該当する顧客コードを取得する。<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strFXAccountCode - (FX口座番号)<BR>
     * FX口座番号<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@return String
     * @@throws WEB3BaseException, NotFoundException
	 */
    public String getAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strFXAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException, NotFoundException;

	/**
	 * (getSOAPTFX受付結果コード)<BR>
	 * SOAPTFX受付結果コードを取得する。<BR>
	 * <BR>
	 * @@param l_strAcceptResultCode - (受付結果コード)<BR>
	 * 受付結果コード<BR>
	 * @@return String
	 */
	public String getSoapTFXAcceptResultCode(String l_strAcceptResultCode);

    /**
     * (sendSOAPメッセージ)<BR>
     * SOAP接続を行う。<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - (電文明細)<BR>
     * 電文明細<BR>
     * @@param l_rpcParams - (SOAPプリファ@レンス)<BR>
     * 外部システムSOAPプリファ@レンス（RPC形式）params<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String sendSoapMessage(
		WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
		SoapConnectPrefRpcParams l_rpcParams) throws WEB3BaseException;

    /**
     * (validate振替可能)<BR>
     * 引数の条件で取引可能かをチェックする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateChangePoss(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException;

    /**
     * (getGFTFXメールアドレス)<BR>
     * FXメールアドレスを取得する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座
     * @@param l_lisFxSystemCodeLists - (FXシステムコード一覧)
     * FXシステムコード一覧
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getGFTFxMailAddress(
        SubAccount l_subAccount,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException;

    /**
     * (isGFT口座開設)<BR>
     * 顧客がGFT口座開設済であるかチェックを行う。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_lisFxSystemCodeLists - (FXシステムコード一覧)<BR>
     * FXシステムコード一覧<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isGFTAccOpen(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException;

    /**
     * (createFX口座情報一覧)<BR>
     * 引数の条件に該当するFX口座情報の一覧を作成する。<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - (電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@param l_resultInfoCreateUser - (口座開設結果)<BR>
     * 口座開設結果<BR>
     * @@param l_strSameTimeFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@param l_resultInfoAddAccount - (追加開設結果)<BR>
     * 追加開設結果<BR>
     * @@return WEB3FXAccInformationUnit[]
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        ResultInfoCreateUser l_resultInfoCreateUser,
        String l_strSameTimeFxSystemCode,
        CompFxConditionParams l_compFxConditionParams,
        ResultInfoAddAccount l_resultInfoAddAccount);

    /**
     * (getFXメールアドレス)<BR>
     * FXメールアドレスを取得する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getFxMailAddress(SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (get同時開設FXシステムコード)<BR>
     * 同時開設するFXシステムコードを取得する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSameTimeFxSystemCode(String l_strInstitutionCode)
        throws WEB3BaseException;

    /**
     * (getGFTFXシステムコード一覧)<BR>
     * 引数.FXシステムコードが関連する外部機@関の、全てのFXシステムコードを取得する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException
     */
    public ArrayList getGFTFxSystemCodeLists(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException;

    /**
     * (insert同時口座開設)<BR>
     * GFT結果通知電文の内容でFX顧客テーブル(fx_account)に行のinsertを行う。<BR>
     * <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT結果通知電文明細)<BR>
     * GFT結果通知電文明細<BR>
     * @@param l_gftAccountOpenStatusParams - (GFT口座開設状況Params)<BR>
     * GFT口座開設状況Params<BR>
     * @@param l_strSimultaneousFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@throws WEB3BaseException
     */
    public void insertSimultaneousAccountOpen(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException;

    /**
     * (insert同時口座開設)<BR>
     * GFT結果通知電文の内容でFX顧客テーブル(fx_account)に行のinsertを行う。<BR>
     * <BR>
     * @@param l_gftAccountOpenStatusParams - (GFT口座開設状況Params)<BR>
     * GFT口座開設状況Params<BR>
     * @@param l_strSimultaneousFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@throws WEB3BaseException
     */
    public void insertSimultaneousAccountOpen(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strSimultaneousFxSystemCode,
        String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (insertFX口座番号)<BR>
     * GFT口座開設状況Params、FX口座情報の内容より、<BR>
     * FX口座番号テーブル(fx_account_code)に行のinsertを行う。<BR>
     * <BR>
     * @@param l_gftAccountOpenStatusParams - (GFT口座開設状況Params)<BR>
     * GFT口座開設状況Params<BR>
     * @@param l_fxAccInformation - (FX口座情報)<BR>
     * FX口座情報<BR>
     * @@param l_strUpdateCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@param l_strSimultaneousFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@throws WEB3BaseException
     */
    public void insertFXAccountCode(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXAccInformationUnit l_fxAccInformation,
        String l_strUpdateCode,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException;
    
    /**
     * (insertSOAPMessage)<BR>
     * SOAPメッセージ保存テーブルにSOAPメッセージを保存する。<BR>
     * <BR>
     * １）SOAPメッセージ保存Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）生成したインスタンスに、以下のとおりにプロパティをセットする。<BR>
     * <BR>
     * 部店ID： 引数.部店ID<BR>
     * 接続区分： 引数.接続区分<BR>
     * 送受信区分： 引数.送受信区分<BR>
     * メッセージ： 引数.メッセージ<BR>
     * 作成日付： システムタイムスタンプ<BR>
     * <BR>
     * ３）テーブルにインサートする。<BR>
     * @@param l_lngBranchId - 部店ID
     * @@param l_strConnectDiv - 接続区分
     * @@param l_strSendReceiveDiv - 送受信区分
     * @@param l_strMessage - メッセージ
     * 
     */
    
    public void insertSOAPMessage(
            long l_lngBranchId, 
            String l_strConnectDiv, 
            String l_strSendReceiveDiv,
            String l_strMessage);

    /**
     * (get変換FXログインID )<BR>
     * <BR>
     * FXログインIDを変換し、返却する。<BR>
     * @@param l_lngInstitutionID - (証券会社ID)<BR>
     * 証券会社ID<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@param l_strFXLoginFirstChar - (FXログインID頭文字)<BR>
     * FXログインID頭文字<BR>
     * @@param l_lngFXLoginID - (FXログインＩＤ)<BR>
     * FXログインＩＤ<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangedFXLoginID(
        long l_lngInstitutionID,
        String l_strFxSystemCode,
        String l_strFXLoginFirstChar,
        long l_lngFXLoginID) throws WEB3BaseException;
}@


1.1
log
@*** empty log message ***
@
text
@a36 6
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.gftforex.soap.api.ResultInfoCreateUser;
import com.gftforex.soap.api.ResultInfoAddAccount;

d52 6
@

