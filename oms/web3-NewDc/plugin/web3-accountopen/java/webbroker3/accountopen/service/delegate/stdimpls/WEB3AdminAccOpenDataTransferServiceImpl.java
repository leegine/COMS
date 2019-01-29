head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設データ移管サービスImpl(WEB3AdminAccOpenDataTransferServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 孟亞南(中訊) 新規作成 モデル 181   187
Revision History : 2009/08/26 孟亞南(中訊) モデル 192
Revision History : 2009/08/31 武波(中訊) モデル 196,199,202,209，212
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenTempRow;
import webbroker3.accountopen.define.WEB3UploadFileIdDef;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

/**
 * (管理者口座開設データ移管サービスImpl)<BR>
 * 管理者口座開設データ移管サービス実装クラス<BR>
 * このサービスに「口座開設サービスインタセプタ」を設定する<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferServiceImpl implements WEB3AdminAccOpenDataTransferService
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferServiceImpl.class);

    /**
     * @@roseuid 4A8A083403D8
     */
    public WEB3AdminAccOpenDataTransferServiceImpl()
    {

    }

    /**
     * 口座開設データ移管処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設データ移管入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設データ移管完了リクエストの場合 <BR>
     * 　@－submitデータ移管()をコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4A8220860290
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        //引数のリクエストデータが、管理者口座開設データ移管入力リクエストの場合
        //get入力画面()をコールする。
        if (l_request instanceof WEB3AdminAccOpenDataTransferInputRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminAccOpenDataTransferInputRequest)l_request);
        }
        //引数のリクエストデータが、管理者口座開設データ移管完了リクエストの場合
        //submitデータ移管()をコールする
        else if (l_request instanceof WEB3AdminAccOpenDataTransferCompleteRequest)
        {
            l_response =
                this.submitDataTransfer(
                    (WEB3AdminAccOpenDataTransferCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 入力画面の表示を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（データ移管）get入力画面」 参照 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminAccOpenDataTransferInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4A8221CA004E
     */
    protected WEB3AdminAccOpenDataTransferInputResponse getInputScreen(
        WEB3AdminAccOpenDataTransferInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminAccOpenDataTransferInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.口座開設
        //is更新：　@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ACC_OPEN, true);

        //createResponse( )
        WEB3AdminAccOpenDataTransferInputResponse l_response =
            (WEB3AdminAccOpenDataTransferInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitデータ移管)<BR>
     * データ移管の処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（データ移管）submitデータ移管」 参照 <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :isDIR管理者( )<BR>
     * 　@　@　@　@isDIR管理者 = falseの場合は、<BR>
     * 　@　@　@　@エラー「当機@能は、DIR管理者のみ実行できます。」をスローする。<BR>
     * 　@　@　@　@class      :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag      :  BUSINESS_ERROR_00985<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminAccOpenDataTransferCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4A8221D20109
     */
    protected WEB3AdminAccOpenDataTransferCompleteResponse submitDataTransfer(
        WEB3AdminAccOpenDataTransferCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitDataTransfer(WEB3AdminAccOpenDataTransferCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        // 機@能カテゴリコード：　@機@能カテゴリコード.口座開設
        // is更新：　@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ACC_OPEN, true);

        //isDIR管理者( )
        //管理者が"DIR管理者"である場合、trueを返却する。
        //管理者が"証券会社管理者"である場合、falseを返却する。
        if (!l_admin.isDirAdministrator())
        {
            log.debug("当機@能は、DIR管理者のみ実行できます。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00985,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "当機@能は、DIR管理者のみ実行できます。");
        }

        //validate取引パスワード(パスワード : String)
        //[validate取引パスワード()に指定する引数]
        // パスワード：　@リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        //validate同時アップロード(管理者 : 管理者)
        //[引数]
        // 管理者：取得した管理者
        this.validateSameTimeUpload(l_admin);

        //saveアップロード開始(管理者 : 管理者)
        //[引数]
        // 管理者：取得した管理者
        long l_lngUpload = this.saveUploadStart(l_admin);
        try
        {
            //システムプリファ@レンスから毎回の処理件数を取得する。
            int l_intPreferences = this.getPreferences();
            log.debug("システムプリファ@レンスから毎回の処理件数を取得する " + l_intPreferences);

            //口座開設見込客一時テーブルのデータ件数を取得する。 
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();

            int l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(
                ExpAccountOpenTempRow.TYPE,
                " institution_code = ? and status = ?  ",
                new Object[]{l_admin.getInstitutionCode(), WEB3StatusDef.NOT_DEAL});
            log.debug("口座開設見込客一時テーブルのデータ件数を取得する " + l_intReturnRecordCnt);

            // 負荷を軽減する為に、LOOP処理。
            int l_intLoopCount = 0;
            if (l_intPreferences != 0)
            {
                l_intLoopCount = (l_intReturnRecordCnt + l_intPreferences - 1) / l_intPreferences;
            }
            log.debug("ループ回数 " + l_intLoopCount);

            for (int i = 0; i < l_intLoopCount; i++)
            {
                //毎回処理のデータを取得する。
                //arg0：　@口座開設見込客一時テーブル.TYPE
                //arg1：　@" institution_code = ? and status = ?  "
                //arg2：　@null
                //arg3：　@null
                //arg4：　@管理者から証券会社コード　@と　@0:未処理
                //arg5：　@getプリファ@レンス( )の戻り値
                //arg6：　@0
                List l_lisExpAccountOpenTempRows = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        ExpAccountOpenTempRow.TYPE,
                        " institution_code = ? and status = ? ",
                        null,
                        null,
                        new Object[]{l_admin.getInstitutionCode(), WEB3StatusDef.NOT_DEAL} ,
                        l_intPreferences,
                        0);

                //検索したレコード数分、LOOP処理
                int l_intSenLoopCount = l_lisExpAccountOpenTempRows.size();
                ExpAccountOpenParams l_expAccountOpenParams = null;
                WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = null;
                if (l_intSenLoopCount > 0)
                {
                    l_expAccountOpenParams = new ExpAccountOpenParams();
                }

                for (int j = 0; j < l_intSenLoopCount; j++)
                {
                    //口座開設見込客一時rowから「処理区分」と「エラー情報」以外の属性をクローンする。
                    ExpAccountOpenTempRow l_expAccountOpenTempRow =
                        (ExpAccountOpenTempRow)l_lisExpAccountOpenTempRows.get(j);

                    //証券会社コード
                    l_expAccountOpenParams.setInstitutionCode(
                        l_expAccountOpenTempRow.getInstitutionCode());

                    //証券会社ID
                    l_expAccountOpenParams.setInstitutionId(
                        l_expAccountOpenTempRow.getInstitutionId());

                    //部店ＩＤ
                    l_expAccountOpenParams.setBranchId(
                            l_expAccountOpenTempRow.getBranchId());

                    //部店コード
                    l_expAccountOpenParams.setBranchCode(
                        l_expAccountOpenTempRow.getBranchCode());

                    //識別コード
                    l_expAccountOpenParams.setAccOpenRequestNumber(
                        l_expAccountOpenTempRow.getAccOpenRequestNumber());

                    //口座コード
                    l_expAccountOpenParams.setAccountCode(
                        l_expAccountOpenTempRow.getAccountCode());

                    //扱者コード（SONAR）
                    l_expAccountOpenParams.setSonarTraderCode(
                        l_expAccountOpenTempRow.getSonarTraderCode());

                    //既存口座フラグ
                    l_expAccountOpenParams.setExAccountFlag(
                        l_expAccountOpenTempRow.getExAccountFlag());

                    //既存口座部店名
                    l_expAccountOpenParams.setExBranchName(
                        l_expAccountOpenTempRow.getExBranchName());

                    //既存口座コード
                    l_expAccountOpenParams.setExAccountCode(
                        l_expAccountOpenTempRow.getExAccountCode());

                    //口座区分
                    l_expAccountOpenParams.setAccountDiv(
                        l_expAccountOpenTempRow.getAccountDiv());

                    //入力区分
                    l_expAccountOpenParams.setOrderDiv(
                        l_expAccountOpenTempRow.getOrderDiv());

                    //資料請求日時
                    l_expAccountOpenParams.setInfomationClaimDatetime(
                        l_expAccountOpenTempRow.getInfomationClaimDatetime());

                    //口座登録日
                    l_expAccountOpenParams.setAccountOpenDate(
                        l_expAccountOpenTempRow.getAccountOpenDate());

                    //初期パスワード
                    WEB3Crypt l_crypt = new WEB3Crypt();
                    l_expAccountOpenParams.setInitialPassword(
                        l_crypt.encrypt(l_expAccountOpenTempRow.getInitialPassword()));

                    //顧客姓（漢字）
                    l_expAccountOpenParams.setFamilyName(
                        l_expAccountOpenTempRow.getFamilyName());

                    //顧客名（漢字）
                    l_expAccountOpenParams.setGivenName(
                        l_expAccountOpenTempRow.getGivenName());

                    //顧客姓（カナ）
                    l_expAccountOpenParams.setFamilyNameAlt1(
                        l_expAccountOpenTempRow.getFamilyNameAlt1());

                    //顧客名（カナ）
                    l_expAccountOpenParams.setGivenNameAlt1(
                        l_expAccountOpenTempRow.getGivenNameAlt1());

                    //性別
                    l_expAccountOpenParams.setSex(
                        l_expAccountOpenTempRow.getSex());

                    //生年月日年号
                    l_expAccountOpenParams.setEraBorn(
                        l_expAccountOpenTempRow.getEraBorn());

                    //生年月日
                    l_expAccountOpenParams.setBornDate(
                        l_expAccountOpenTempRow.getBornDate());

                    //emailアドレス
                    l_expAccountOpenParams.setEmailAddress(
                        l_expAccountOpenTempRow.getEmailAddress());

                    //emailアドレス１
                    l_expAccountOpenParams.setEmailAddressAlt1(
                        l_expAccountOpenTempRow.getEmailAddressAlt1());

                    //郵便番号
                    l_expAccountOpenParams.setZipCode(
                        l_expAccountOpenTempRow.getZipCode());

                    //住所１
                    l_expAccountOpenParams.setAddressLine1(
                        l_expAccountOpenTempRow.getAddressLine1());

                    //住所２
                    l_expAccountOpenParams.setAddressLine2(
                        l_expAccountOpenTempRow.getAddressLine2());

                    //住所３
                    l_expAccountOpenParams.setAddressLine3(
                        l_expAccountOpenTempRow.getAddressLine3());

                    //住所１（カナ）
                    l_expAccountOpenParams.setAddressLine1Kana(
                        l_expAccountOpenTempRow.getAddressLine1Kana());

                    //住所２（カナ）
                    l_expAccountOpenParams.setAddressLine2Kana(
                        l_expAccountOpenTempRow.getAddressLine2Kana());

                    //住所３（カナ）
                    l_expAccountOpenParams.setAddressLine3Kana(
                        l_expAccountOpenTempRow.getAddressLine3Kana());

                    //電話番号
                    l_expAccountOpenParams.setTelephone(
                        l_expAccountOpenTempRow.getTelephone());

                    //連絡先電話番号（携帯）
                    l_expAccountOpenParams.setMobile(
                        l_expAccountOpenTempRow.getMobile());

                    //ＦＡＸ番号
                    l_expAccountOpenParams.setFax(
                        l_expAccountOpenTempRow.getFax());

                    //職業区分
                    l_expAccountOpenParams.setOccupationDiv(
                        l_expAccountOpenTempRow.getOccupationDiv());

                    //勤務先名称
                    l_expAccountOpenParams.setOffice(
                        l_expAccountOpenTempRow.getOffice());

                    //勤務先郵便番号
                    l_expAccountOpenParams.setOfficeZipCode(
                        l_expAccountOpenTempRow.getOfficeZipCode());

                    //勤務先住所
                    l_expAccountOpenParams.setOfficeAddress(
                        l_expAccountOpenTempRow.getOfficeAddress());

                    //勤務先電話番号
                    l_expAccountOpenParams.setOfficeTelephone(
                        l_expAccountOpenTempRow.getOfficeTelephone());

                    //勤務先FAX番号
                    l_expAccountOpenParams.setOfficeFax(
                        l_expAccountOpenTempRow.getOfficeFax());

                    //所属部署
                    l_expAccountOpenParams.setDepartment(
                        l_expAccountOpenTempRow.getDepartment());

                    //役職
                    l_expAccountOpenParams.setPost(
                        l_expAccountOpenTempRow.getPost());

                    //連絡先住所
                    l_expAccountOpenParams.setContactAddress(
                        l_expAccountOpenTempRow.getContactAddress());

                    //連絡先電話番号
                    l_expAccountOpenParams.setContactTelephone(
                        l_expAccountOpenTempRow.getContactTelephone());

                    //続柄区分
                    l_expAccountOpenParams.setFamilyRelationship(
                        l_expAccountOpenTempRow.getFamilyRelationship());

                    //続柄区分（その他）
                    l_expAccountOpenParams.setFamilyRelationshipEtc(
                        l_expAccountOpenTempRow.getFamilyRelationshipEtc());

                    //世帯主名（漢字）
                    l_expAccountOpenParams.setHouseholder(
                        l_expAccountOpenTempRow.getHouseholder());

                    //世帯主名（カナ）
                    l_expAccountOpenParams.setHouseholderKana(
                        l_expAccountOpenTempRow.getHouseholderKana());

                    //世帯主職業区分
                    l_expAccountOpenParams.setHouseholderOccupationDiv(
                        l_expAccountOpenTempRow.getHouseholderOccupationDiv());

                    //世帯主勤務先
                    l_expAccountOpenParams.setHouseholderOffice(
                        l_expAccountOpenTempRow.getHouseholderOffice());

                    //世帯主勤務先住所
                    l_expAccountOpenParams.setHouseholderOfficeAddress(
                        l_expAccountOpenTempRow.getHouseholderOfficeAddress());

                    //世帯主所属部署
                    l_expAccountOpenParams.setHouseholderDepartment(
                        l_expAccountOpenTempRow.getHouseholderDepartment());

                    //世帯主勤務先電話番号
                    l_expAccountOpenParams.setHouseholderOfficeTel(
                        l_expAccountOpenTempRow.getHouseholderOfficeTel());

                    //世帯主勤務先FAX番号
                    l_expAccountOpenParams.setHouseholderOfficeFax(
                        l_expAccountOpenTempRow.getHouseholderOfficeFax());

                    //世帯主役職
                    l_expAccountOpenParams.setHouseholderPost(
                        l_expAccountOpenTempRow.getHouseholderPost());

                    //居住／非居住区分
                    l_expAccountOpenParams.setResident(
                        l_expAccountOpenTempRow.getResident());

                    //振込先金融機@関情報   振替区分
                    l_expAccountOpenParams.setTransferDiv(
                        l_expAccountOpenTempRow.getTransferDiv());

                    //銀行コード
                    l_expAccountOpenParams.setFinInstitutionCode(
                        l_expAccountOpenTempRow.getFinInstitutionCode());

                    //銀行名
                    l_expAccountOpenParams.setFinInstitutionName(
                        l_expAccountOpenTempRow.getFinInstitutionName());

                    //支店コード
                    l_expAccountOpenParams.setFinBranchCode(
                        l_expAccountOpenTempRow.getFinBranchCode());

                    //支店名
                    l_expAccountOpenParams.setFinBranchName(
                        l_expAccountOpenTempRow.getFinBranchName());

                    //預金区分
                    l_expAccountOpenParams.setFinSaveDiv(
                        l_expAccountOpenTempRow.getFinSaveDiv());

                    //口座番号
                    l_expAccountOpenParams.setFinAccountNo(
                        l_expAccountOpenTempRow.getFinAccountNo());

                    //通帳記号
                    l_expAccountOpenParams.setPostalSaveCode(
                        l_expAccountOpenTempRow.getPostalSaveCode());

                    //通帳番号
                    l_expAccountOpenParams.setPostalSaveNo(
                        l_expAccountOpenTempRow.getPostalSaveNo());

                    //口座名義人
                    l_expAccountOpenParams.setFinAccountName(
                        l_expAccountOpenTempRow.getFinAccountName());

                    //振替手数料区分
                    l_expAccountOpenParams.setTransCommission(
                        l_expAccountOpenTempRow.getTransCommission());

                    //現物株式
                    l_expAccountOpenParams.setExperienceDivEquity(
                        l_expAccountOpenTempRow.getExperienceDivEquity());

                    //信用取引
                    l_expAccountOpenParams.setExperienceDivMargin(
                        l_expAccountOpenTempRow.getExperienceDivMargin());

                    //債券
                    l_expAccountOpenParams.setExperienceDivBond(
                        l_expAccountOpenTempRow.getExperienceDivBond());

                    //転換社債
                    l_expAccountOpenParams.setExperienceDivWt(
                        l_expAccountOpenTempRow.getExperienceDivWt());

                    //投資信託（株式）
                    l_expAccountOpenParams.setExperienceDivFundSk(
                        l_expAccountOpenTempRow.getExperienceDivFundSk());

                    //投資信託（公社債）
                    l_expAccountOpenParams.setExperienceDivFundBd(
                        l_expAccountOpenTempRow.getExperienceDivFundBd());

                    //先物・オプション
                    l_expAccountOpenParams.setExperienceDivFo(
                        l_expAccountOpenTempRow.getExperienceDivFo());

                    //外国証券
                    l_expAccountOpenParams.setExperienceDivFEquity(
                        l_expAccountOpenTempRow.getExperienceDivFEquity());

                    //その他
                    l_expAccountOpenParams.setExperienceDivEtc(
                        l_expAccountOpenTempRow.getExperienceDivEtc());

                    //現物株式フラグ
                    l_expAccountOpenParams.setExperienceFlagEquity(
                        l_expAccountOpenTempRow.getExperienceFlagEquity());

                    //信用取引フラグ
                    l_expAccountOpenParams.setExperienceFlagMargin(
                        l_expAccountOpenTempRow.getExperienceFlagMargin());

                    //債券フラグ
                    l_expAccountOpenParams.setExperienceFlagBond(
                        l_expAccountOpenTempRow.getExperienceFlagBond());

                    //転換社債フラグ
                    l_expAccountOpenParams.setExperienceFlagWt(
                        l_expAccountOpenTempRow.getExperienceFlagWt());

                    //投資信託（株式）フラグ
                    l_expAccountOpenParams.setExperienceFlagFundSk(
                        l_expAccountOpenTempRow.getExperienceFlagFundSk());

                    //投資信託（公社債）フラグ
                    l_expAccountOpenParams.setExperienceFlagFundBd(
                        l_expAccountOpenTempRow.getExperienceFlagFundBd());

                    //先物・オプションフラグ
                    l_expAccountOpenParams.setExperienceFlagFo(
                        l_expAccountOpenTempRow.getExperienceFlagFo());

                    //外国証券フラグ
                    l_expAccountOpenParams.setExperienceFlagFEquity(
                        l_expAccountOpenTempRow.getExperienceFlagFEquity());

                    //その他フラグ
                    l_expAccountOpenParams.setExperienceFlagEtc(
                        l_expAccountOpenTempRow.getExperienceFlagEtc());

                    //経験年数（自）
                    l_expAccountOpenParams.setExperienceFrom(
                        l_expAccountOpenTempRow.getExperienceFrom());

                    //経験年数（至）
                    l_expAccountOpenParams.setExperienceTo(
                        l_expAccountOpenTempRow.getExperienceTo());

                    //現物株式フラグ
                    l_expAccountOpenParams.setInterestFlagEquity(
                        l_expAccountOpenTempRow.getInterestFlagEquity());

                    //株式ミニ投資フラグ
                    l_expAccountOpenParams.setInterestFlagMinistock(
                        l_expAccountOpenTempRow.getInterestFlagMinistock());

                    //信用取引フラグ
                    l_expAccountOpenParams.setInterestFlagMargin(
                        l_expAccountOpenTempRow.getInterestFlagMargin());

                    //債券フラグ
                    l_expAccountOpenParams.setInterestFlagBond(
                        l_expAccountOpenTempRow.getInterestFlagBond());

                    //投資信託フラグ
                    l_expAccountOpenParams.setInterestFlagFund(
                        l_expAccountOpenTempRow.getInterestFlagFund());

                    //先物・オプションフラグ
                    l_expAccountOpenParams.setInterestFlagFo(
                        l_expAccountOpenTempRow.getInterestFlagFo());

                    //外国証券フラグ
                    l_expAccountOpenParams.setInterestFlagFEquity(
                        l_expAccountOpenTempRow.getInterestFlagFEquity());

                    //その他フラグ
                    l_expAccountOpenParams.setInterestFlagEtc(
                        l_expAccountOpenTempRow.getInterestFlagEtc());

                    //投資目的区分
                    l_expAccountOpenParams.setInvestPurposeDiv(
                        l_expAccountOpenTempRow.getInvestPurposeDiv());

                    //取引動機@区分
                    l_expAccountOpenParams.setAppliMotivatDiv(
                        l_expAccountOpenTempRow.getAppliMotivatDiv());

                    //年収区分
                    l_expAccountOpenParams.setAnnualIncomeDiv(
                        l_expAccountOpenTempRow.getAnnualIncomeDiv());

                    //年収（自）
                    l_expAccountOpenParams.setAnnualIncomeFrom(
                        l_expAccountOpenTempRow.getAnnualIncomeFrom());

                    //年収（至）
                    l_expAccountOpenParams.setAnnualIncomeTo(
                        l_expAccountOpenTempRow.getAnnualIncomeTo());

                    //金融資産区分
                    l_expAccountOpenParams.setAssetValueDiv(
                        l_expAccountOpenTempRow.getAssetValueDiv());

                    //金融資産（自）
                    l_expAccountOpenParams.setAssetValueFrom(
                        l_expAccountOpenTempRow.getAssetValueFrom());

                    //金融資産（至）
                    l_expAccountOpenParams.setAssetValueTo(
                        l_expAccountOpenTempRow.getAssetValueTo());

                    //運用予定額
                    l_expAccountOpenParams.setFundBudgetAmountDiv(
                        l_expAccountOpenTempRow.getFundBudgetAmountDiv());

                    //資金の性格
                    l_expAccountOpenParams.setFundBudgetDiv(
                        l_expAccountOpenTempRow.getFundBudgetDiv());

                    //資金の性格（その他）
                    l_expAccountOpenParams.setFundBudgetEtc(
                        l_expAccountOpenTempRow.getFundBudgetEtc());

                    //検索用区分
                    l_expAccountOpenParams.setIdConfirmFlag(
                        l_expAccountOpenTempRow.getIdConfirmFlag());

                    //本人確認書類区分
                    l_expAccountOpenParams.setIdConfirmDocDiv(
                        l_expAccountOpenTempRow.getIdConfirmDocDiv());

                    //本人確認書類区分（その他）
                    l_expAccountOpenParams.setIdConfirmDocEtc(
                        l_expAccountOpenTempRow.getIdConfirmDocEtc());

                    //特定口座区分
                    l_expAccountOpenParams.setSpecialAcc(
                        l_expAccountOpenTempRow.getSpecialAcc());

                    //信用取引特定口座区分
                    l_expAccountOpenParams.setSpecialAccMargin(
                        l_expAccountOpenTempRow.getSpecialAccMargin());

                    //内部者登録フラグ
                    l_expAccountOpenParams.setInsiderFlag(
                        l_expAccountOpenTempRow.getInsiderFlag());

                    //内部者銘柄名
                    l_expAccountOpenParams.setProductName(
                        l_expAccountOpenTempRow.getProductName());

                    //送付先郵便番号
                    l_expAccountOpenParams.setSendZipCode(
                        l_expAccountOpenTempRow.getSendZipCode());

                    //送付先住所１
                    l_expAccountOpenParams.setSendAddressLine1(
                        l_expAccountOpenTempRow.getSendAddressLine1());

                    //送付先住所２
                    l_expAccountOpenParams.setSendAddressLine2(
                        l_expAccountOpenTempRow.getSendAddressLine2());

                    //送付先住所３
                    l_expAccountOpenParams.setSendAddressLine3(
                        l_expAccountOpenTempRow.getSendAddressLine3());

                    //各社拡張項目（区分１）
                    l_expAccountOpenParams.setExtItemDiv1(
                        l_expAccountOpenTempRow.getExtItemDiv1());

                    //各社拡張項目（区分２）
                    l_expAccountOpenParams.setExtItemDiv2(
                        l_expAccountOpenTempRow.getExtItemDiv2());

                    //各社拡張項目（区分３）
                    l_expAccountOpenParams.setExtItemDiv3(
                        l_expAccountOpenTempRow.getExtItemDiv3());

                    //各社拡張項目（区分４）
                    l_expAccountOpenParams.setExtItemDiv4(
                        l_expAccountOpenTempRow.getExtItemDiv4());

                    //各社拡張項目（区分５）
                    l_expAccountOpenParams.setExtItemDiv5(
                        l_expAccountOpenTempRow.getExtItemDiv5());

                    //各社拡張項目（区分６）
                    l_expAccountOpenParams.setExtItemDiv6(
                        l_expAccountOpenTempRow.getExtItemDiv6());

                    //各社拡張項目（区分７）
                    l_expAccountOpenParams.setExtItemDiv7(
                        l_expAccountOpenTempRow.getExtItemDiv7());

                    //各社拡張項目（区分８）
                    l_expAccountOpenParams.setExtItemDiv8(
                        l_expAccountOpenTempRow.getExtItemDiv8());

                    //各社拡張項目（区分９）
                    l_expAccountOpenParams.setExtItemDiv9(
                        l_expAccountOpenTempRow.getExtItemDiv9());

                    //各社拡張項目（区分１０）
                    l_expAccountOpenParams.setExtItemDiv10(
                        l_expAccountOpenTempRow.getExtItemDiv10());

                    //各社拡張項目（フラグ1）
                    l_expAccountOpenParams.setExtItemFlag1(
                        l_expAccountOpenTempRow.getExtItemFlag1());

                    //各社拡張項目（フラグ2）
                    l_expAccountOpenParams.setExtItemFlag2(
                        l_expAccountOpenTempRow.getExtItemFlag2());

                    //各社拡張項目（フラグ3）
                    l_expAccountOpenParams.setExtItemFlag3(
                        l_expAccountOpenTempRow.getExtItemFlag3());

                    //各社拡張項目（フラグ4）
                    l_expAccountOpenParams.setExtItemFlag4(
                        l_expAccountOpenTempRow.getExtItemFlag4());

                    //各社拡張項目（フラグ5）
                    l_expAccountOpenParams.setExtItemFlag5(
                        l_expAccountOpenTempRow.getExtItemFlag5());

                    //各社拡張項目（フラグ６）
                    l_expAccountOpenParams.setExtItemFlag6(
                        l_expAccountOpenTempRow.getExtItemFlag6());

                    //各社拡張項目（フラグ７）
                    l_expAccountOpenParams.setExtItemFlag7(
                        l_expAccountOpenTempRow.getExtItemFlag7());

                    //各社拡張項目（フラグ８）
                    l_expAccountOpenParams.setExtItemFlag8(
                        l_expAccountOpenTempRow.getExtItemFlag8());

                    //各社拡張項目（フラグ９）
                    l_expAccountOpenParams.setExtItemFlag9(
                        l_expAccountOpenTempRow.getExtItemFlag9());

                    //各社拡張項目（フラグ１０）
                    l_expAccountOpenParams.setExtItemFlag10(
                        l_expAccountOpenTempRow.getExtItemFlag10());

                    //各社拡張項目（テキスト１）
                    l_expAccountOpenParams.setExtItemText1(
                        l_expAccountOpenTempRow.getExtItemText1());

                    //各社拡張項目（テキスト２）
                    l_expAccountOpenParams.setExtItemText2(
                        l_expAccountOpenTempRow.getExtItemText2());

                    //各社拡張項目（テキスト３）
                    l_expAccountOpenParams.setExtItemText3(
                        l_expAccountOpenTempRow.getExtItemText3());

                    //各社拡張項目（テキスト４）
                    l_expAccountOpenParams.setExtItemText4(
                        l_expAccountOpenTempRow.getExtItemText4());

                    //各社拡張項目（テキスト５）
                    l_expAccountOpenParams.setExtItemText5(
                        l_expAccountOpenTempRow.getExtItemText5());

                    //各社拡張項目（テキスト６）
                    l_expAccountOpenParams.setExtItemText6(
                        l_expAccountOpenTempRow.getExtItemText6());

                    //各社拡張項目（テキスト７）
                    l_expAccountOpenParams.setExtItemText7(
                        l_expAccountOpenTempRow.getExtItemText7());

                    //各社拡張項目（テキスト８）
                    l_expAccountOpenParams.setExtItemText8(
                        l_expAccountOpenTempRow.getExtItemText8());

                    //各社拡張項目（テキスト９）
                    l_expAccountOpenParams.setExtItemText9(
                        l_expAccountOpenTempRow.getExtItemText9());

                    //各社拡張項目（テキスト１０）
                    l_expAccountOpenParams.setExtItemText10(
                        l_expAccountOpenTempRow.getExtItemText10());

                    //更新者コード
                    l_expAccountOpenParams.setLastUpdater(
                        l_expAccountOpenTempRow.getLastUpdater());

                    //作成日時
                    l_expAccountOpenParams.setCreatedTimestamp(
                        l_expAccountOpenTempRow.getCreatedTimestamp());

                    //更新日時
                    l_expAccountOpenParams.setLastUpdatedTimestamp(
                        l_expAccountOpenTempRow.getLastUpdatedTimestamp());

                    //作成者コード
                    l_expAccountOpenParams.setCreator(
                        l_expAccountOpenTempRow.getCreator());

                    //専用振込先口座番号
                    l_expAccountOpenParams.setExclusiveUseAccountNo(
                        l_expAccountOpenTempRow.getExclusiveUseAccountNo());

                    //HULFT送信日時
                    l_expAccountOpenParams.setSendTimestamp(
                        l_expAccountOpenTempRow.getSendTimestamp());

                    //顧客正式名称作成区分
                    l_expAccountOpenParams.setRealNameVoucherDiv(
                        l_expAccountOpenTempRow.getRealNameVoucherDiv());

                    //顧客正式名称１
                    l_expAccountOpenParams.setRealName1(
                        l_expAccountOpenTempRow.getRealName1());

                    //顧客正式名称２
                    l_expAccountOpenParams.setRealName2(
                        l_expAccountOpenTempRow.getRealName2());

                    //（内部者）作成区分
                    l_expAccountOpenParams.setInsiderVoucherDiv(
                        l_expAccountOpenTempRow.getInsiderVoucherDiv());

                    //（内部者）銘柄コード
                    l_expAccountOpenParams.setInsiderProductCode(
                        l_expAccountOpenTempRow.getInsiderProductCode());

                    //（内部者）関係区分
                    l_expAccountOpenParams.setInsiderRelationDiv(
                        l_expAccountOpenTempRow.getInsiderRelationDiv());

                    //（内部者）役員名
                    l_expAccountOpenParams.setInsiderOfficerName(
                        l_expAccountOpenTempRow.getInsiderOfficerName());

                    //（内部者）役職名コード
                    l_expAccountOpenParams.setInsiderPostCode(
                        l_expAccountOpenTempRow.getInsiderPostCode());

                    //（内部者）役職名
                    l_expAccountOpenParams.setInsiderPostName(
                        l_expAccountOpenTempRow.getInsiderPostName());

                    //（ＧＰ）作成区分
                    l_expAccountOpenParams.setGpVoucherDiv(
                        l_expAccountOpenTempRow.getGpVoucherDiv());

                    //（ＧＰ）コース
                    l_expAccountOpenParams.setGpCourse(
                        l_expAccountOpenTempRow.getGpCourse());

                    //（ＧＰ）プラン
                    l_expAccountOpenParams.setGpPlan(
                        l_expAccountOpenTempRow.getGpPlan());

                    //（ＧＰ）目標額
                    l_expAccountOpenParams.setGpTargetFigure(
                        l_expAccountOpenTempRow.getGpTargetFigure());

                    //（ＧＰ）目標年
                    l_expAccountOpenParams.setGpTargetYear(
                        l_expAccountOpenTempRow.getGpTargetYear());

                    //（ＧＰ）目標月
                    l_expAccountOpenParams.setGpTargetMonth(
                        l_expAccountOpenTempRow.getGpTargetMonth());

                    //（ＧＰ）積立額
                    l_expAccountOpenParams.setGpInstallmentFigure(
                        l_expAccountOpenTempRow.getGpInstallmentFigure());

                    //（ＧＰ）入金周期
                    l_expAccountOpenParams.setGpDepositCycle(
                        l_expAccountOpenTempRow.getGpDepositCycle());

                    //（ＧＰ）受渡経路
                    l_expAccountOpenParams.setGpPaymentRoot(
                        l_expAccountOpenTempRow.getGpPaymentRoot());

                    //（ＧＰ）再投資区分
                    l_expAccountOpenParams.setGpReinvestDiv(
                        l_expAccountOpenTempRow.getGpReinvestDiv());

                    //（ＧＰ）税区分
                    l_expAccountOpenParams.setGpTaxDiv(
                        l_expAccountOpenTempRow.getGpTaxDiv());

                    //（ＧＰ）（優）限度額
                    l_expAccountOpenParams.setGpTaxfreeLimit(
                        l_expAccountOpenTempRow.getGpTaxfreeLimit());

                    //（ＧＰ）（特優）限度額
                    l_expAccountOpenParams.setGpSpecialTaxfreeLimit(
                        l_expAccountOpenTempRow.getGpSpecialTaxfreeLimit());

                    //（ＧＰ）加入摘要
                    l_expAccountOpenParams.setGpSubscrSummary(
                        l_expAccountOpenTempRow.getGpSubscrSummary());

                    //（ＧＰ）銘柄コード
                    l_expAccountOpenParams.setGpProductCode(
                        l_expAccountOpenTempRow.getGpProductCode());

                    //（ＧＰ）担保客
                    l_expAccountOpenParams.setGpMortgageCustomer(
                        l_expAccountOpenTempRow.getGpMortgageCustomer());

                    //（ＧＰ）ミックス客
                    l_expAccountOpenParams.setGpMixCustomer(
                        l_expAccountOpenTempRow.getGpMixCustomer());

                    //（ＧＰ）契約書
                    l_expAccountOpenParams.setGpContract(
                        l_expAccountOpenTempRow.getGpContract());

                    //（上場外株）作成区分
                    l_expAccountOpenParams.setStkVoucherDiv(
                        l_expAccountOpenTempRow.getStkVoucherDiv());

                    //（上場外株）譲渡
                    l_expAccountOpenParams.setStkTaxationTranDiv(
                        l_expAccountOpenTempRow.getStkTaxationTranDiv());

                    //（上場外株）住所（カナ）
                    l_expAccountOpenParams.setStkAddressLineKana(
                        l_expAccountOpenTempRow.getStkAddressLineKana());

                    //（上場外株）送金
                    l_expAccountOpenParams.setStkTransferDiv(
                        l_expAccountOpenTempRow.getStkTransferDiv());

                    //（上場外株）銀行コード
                    l_expAccountOpenParams.setStkFinInstitutionCode(
                        l_expAccountOpenTempRow.getStkFinInstitutionCode());

                    //（上場外株）支店コード
                    l_expAccountOpenParams.setStkFinBranchCode(
                        l_expAccountOpenTempRow.getStkFinBranchCode());

                    //（上場外株）預金区分
                    l_expAccountOpenParams.setStkFinSaveDiv(
                        l_expAccountOpenTempRow.getStkFinSaveDiv());

                    //（上場外株）口座番号
                    l_expAccountOpenParams.setStkFinAccountNo(
                        l_expAccountOpenTempRow.getStkFinAccountNo());

                    //仲介業扱者コード
                    l_expAccountOpenParams.setBrokerageTraderCode(
                        l_expAccountOpenTempRow.getBrokerageTraderCode());

                    //各社拡張項目（区分1１）
                    l_expAccountOpenParams.setExtItemDiv11(
                        l_expAccountOpenTempRow.getExtItemDiv11());

                    //各社拡張項目（区分１2）
                    l_expAccountOpenParams.setExtItemDiv12(
                        l_expAccountOpenTempRow.getExtItemDiv12());

                    //各社拡張項目（区分１3）
                    l_expAccountOpenParams.setExtItemDiv13(
                        l_expAccountOpenTempRow.getExtItemDiv13());

                    //各社拡張項目（区分１4）
                    l_expAccountOpenParams.setExtItemDiv14(
                        l_expAccountOpenTempRow.getExtItemDiv14());

                    //モバイル専用口座区分
                    l_expAccountOpenParams.setExtItemDiv15(
                        l_expAccountOpenTempRow.getExtItemDiv15());

                    //口座番号（外貨）
                    l_expAccountOpenParams.setForeignAccountNo(
                        l_expAccountOpenTempRow.getForeignAccountNo());

                    //口座名義人（外貨）
                    l_expAccountOpenParams.setForeignAccountName(
                        l_expAccountOpenTempRow.getForeignAccountName());

                    //口座名義人英数（外貨）
                    l_expAccountOpenParams.setForeignAccountNameEng(
                        l_expAccountOpenTempRow.getForeignAccountNameEng());

                    //預金区分（外貨）
                    l_expAccountOpenParams.setForeignSaveDiv(
                        l_expAccountOpenTempRow.getForeignSaveDiv());

                    //削除フラグ
                    l_expAccountOpenParams.setDeleteFlag(
                        l_expAccountOpenTempRow.getDeleteFlag());

                    //削除日時
                    l_expAccountOpenParams.setDeleteTimestamp(
                        l_expAccountOpenTempRow.getDeleteTimestamp());

                    //印刷フラグ
                    l_expAccountOpenParams.setPrintFlag(
                        l_expAccountOpenTempRow.getPrintFlag());

                    //受領フラグ
                    l_expAccountOpenParams.setReceiptFlag(
                        l_expAccountOpenTempRow.getReceiptFlag());

                    //承諾フラグ
                    l_expAccountOpenParams.setAgreementFlag(
                        l_expAccountOpenTempRow.getAgreementFlag());

                    // 外国人フラグ
                    l_expAccountOpenParams.setForeignFlag(
                        l_expAccountOpenTempRow.getForeignFlag());

                    //フリガナ1
                    l_expAccountOpenParams.setAgencyAccNameKana1(
                        l_expAccountOpenTempRow.getAgencyAccNameKana1());

                    //フリガナ2
                    l_expAccountOpenParams.setAgencyAccNameKana2(
                        l_expAccountOpenTempRow.getAgencyAccNameKana2());

                    //名称1
                    l_expAccountOpenParams.setAgencyAccName1(
                        l_expAccountOpenTempRow.getAgencyAccName1());

                    //名称2
                    l_expAccountOpenParams.setAgencyAccName2(
                        l_expAccountOpenTempRow.getAgencyAccName2());

                    //住所1
                    l_expAccountOpenParams.setAgencyAddressLine1(
                        l_expAccountOpenTempRow.getAgencyAddressLine1());

                    //住所2
                    l_expAccountOpenParams.setAgencyAddressLine2(
                        l_expAccountOpenTempRow.getAgencyAddressLine2());

                    //代表者の役職
                    l_expAccountOpenParams.setAgencyRepPost(
                        l_expAccountOpenTempRow.getAgencyRepPost());

                    //代表者のフリガナ1
                    l_expAccountOpenParams.setAgencyRepNameKana1(
                        l_expAccountOpenTempRow.getAgencyRepNameKana1());

                    //代表者のフリガナ2
                    l_expAccountOpenParams.setAgencyRepNameKana2(
                        l_expAccountOpenTempRow.getAgencyRepNameKana2());

                    //代表者の氏名1
                    l_expAccountOpenParams.setAgencyRepName1(
                        l_expAccountOpenTempRow.getAgencyRepName1());

                    //代表者の氏名2
                    l_expAccountOpenParams.setAgencyRepName2(
                        l_expAccountOpenTempRow.getAgencyRepName2());

                    l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                        l_expAccountOpenParams);

                    //口座開設移管の処理を行う。
                    WEB3AdminAccOpenDataTransferUnitService l_accOpenDataTransferUnitService = 
                        (WEB3AdminAccOpenDataTransferUnitService)Services.getService(
                                WEB3AdminAccOpenDataTransferUnitService.class);

                    l_accOpenDataTransferUnitService.process(l_accOpenExpAccountOpen);
                }
            }
        }
        catch(Exception l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        }
        try
        {
            //指定のアップロード行にアップロード終了情報を更新する。
            saveUploadEnd(l_lngUpload);
            log.exiting(STR_METHOD_NAME);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
        }
        //createResponse( )
        WEB3AdminAccOpenDataTransferCompleteResponse l_response =
            (WEB3AdminAccOpenDataTransferCompleteResponse)l_request.createResponse();

        return l_response;
    }

    /**
     * (saveアップロード開始)<BR>
     * アップロードテーブルに新規行を挿入し、アップロードＩＤを返却する。 <BR>
     * <BR>
     * １）　@行オブジェクト生成 <BR>
     * 　@（管理者共通）アップロードテーブルParamsを生成し、NotNull項目以外の各項目をNullで初期化する。 <BR>
     * <BR>
     * 　@※（管理者共通）アップロードテーブルParamsクラスはDDLより自動生成する。 <BR>
     * <BR>
     * ２）　@以下の通り、行オブジェクトに値をセットする。 <BR>
     * <BR>
     * 　@アップロードＩＤ = （新規採番）(*1) <BR>
     * 　@証券会社コード = 引数.管理者から証券会社コード() <BR>
     * 　@部店コード = 引数.管理者から部店コード() <BR>
     * 　@銘柄タイプ = 0：その他（ProductTypeEnumにて定義）<BR>
     * 　@アップロードファ@イルＩＤ = "口座開設データ移管"<BR>
     * 　@アップロード開始日時 = System.currentTimeMillis() <BR>
     * 　@更新者コード = 引数.管理者から管理者コード<BR>
     * <BR>
     * 　@(*1)　@アップロードＩＤ新規採番 <BR>
     * 　@（管理者共通）アップロードテーブルDAO.newPkValue()メソッドにて取得する。 <BR>
     * 　@※（管理者共通）アップロードテーブルDAOクラスはDDLより自動生成する。 <BR>
     * <BR>
     * ３）　@TransactionCallback生成する。 <BR>
     * 　@アップロードTransactionCallbackクラスを生成し、２）で作成した行オブジェクトをプロパティにセットする。 <BR>
     * <BR>
     * ４）　@DB-insert <BR>
     * 　@QueryProcessor.doTransaction()にてDB-insertを実行する。 <BR>
     * <BR>
     * 　@[doTransaction()に指定する引数] <BR>
     * 　@トランザクション属性：　@TX_CREATE_NEW <BR>
     * 　@トランザクションコールバック：　@３）で生成したTransactionCallback<BR>
     * <BR>
     * ５）　@新規採番したアップロードＩＤを返却する。<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者<BR>
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 4A8229940138
     */
    private long saveUploadStart(WEB3Administrator l_admin)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveUploadStart(WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        //（管理者共通）アップロードテーブルParamsを生成し、NotNull項目以外の各項目をNullで初期化する。
        AdministratorUploadParams l_administratorUploadParams =
            new AdministratorUploadParams();

        try
        {
            //以下の通り、行オブジェクトに値をセットする。
            //アップロードＩＤ = （新規採番）
            long l_lngNewPkValue = AdministratorUploadDao.newPkValue();
            l_administratorUploadParams.setAdministratorUploadId(l_lngNewPkValue);

            //証券会社コード = 引数.管理者から証券会社コード()
            l_administratorUploadParams.setInstitutionCode(l_admin.getInstitutionCode());

            //部店コード = 引数.管理者から部店コード()
            l_administratorUploadParams.setBranchCode(l_admin.getBranchCode());

            //銘柄タイプ = 0：その他（ProductTypeEnumにて定義）
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);

            //アップロードファ@イルＩＤ = "口座開設データ移管"
            l_administratorUploadParams.setUploadFileId(WEB3UploadFileIdDef.ACCOUNT_OPEN_DATA_TRANSFER);

            //アップロード開始日時 = System.currentTimeMillis()
            l_administratorUploadParams.setUploadStartTimestamp(new Timestamp(System.currentTimeMillis()));

            //更新者コード = 引数.管理者から管理者コード
            l_administratorUploadParams.setLastUpdater(l_admin.getAdministratorCode());

            //TransactionCallback生成する。
            UploadTransactionCallback l_uploadTransactionCallback = new UploadTransactionCallback();

            //アップロードTransactionCallbackクラスを生成し、２）で作成した行オブジェクトをプロパティにセットする。
            l_uploadTransactionCallback.administratorUploadParams = l_administratorUploadParams;

            //DB-insert
            //QueryProcessor.doTransaction()にてDB-insertを実行する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_uploadTransactionCallback);

            //新規採番したアップロードＩＤを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_lngNewPkValue;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (validate同時アップロード)<BR>
     * 他管理者が使用中でないかをチェックする。<BR>
     * <BR>
     * １）　@以下の条件で「（管理者共通）アップロードテーブル」を検索する。<BR>
     * [条件]<BR>
     * 　@証券会社コード = 引数.管理者から証券会社コード<BR>
     * 　@部店コード = 引数.管理者から部店コード<BR>
     * 　@銘柄タイプ = 0：その他（ProductTypeEnumにて定義）<BR>
     * 　@アップロードファ@イルＩＤ = "口座開設データ移管"<BR>
     * 　@アップロード終了日時 = null<BR>
     * <BR>
     * ２）　@行が取得できる場合、エラー「口座開設データ移管処理中」をスローする。<BR>
     * 　@　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@　@:　@BUSINESS_ERROR_03179<BR>
     * <BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A82956400FA
     */
    private void validateSameTimeUpload(WEB3Administrator l_admin)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSameTimeUpload(WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = l_admin.getInstitutionCode();
        String l_strBranchCode = l_admin.getBranchCode();

        //１）　@以下の条件で「（管理者共通）アップロードテーブル」を検索する。
        //[条件]
        //　@証券会社コード = 引数.管理者から証券会社コード
        //　@部店コード = 引数.管理者から部店コード
        //　@銘柄タイプ = 0：その他（ProductTypeEnumにて定義）
        //　@アップロードファ@イルＩＤ = "口座開設データ移管"
        //　@アップロード終了日時 = null
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and upload_file_id = ? ");
        l_sbWhere.append(" and upload_end_timestamp is null ");

        Object[] l_objAdministratorUploadWhere =
            {l_strInstitutionCode,
            l_strBranchCode,
            ProductTypeEnum.OTHER,
            WEB3UploadFileIdDef.ACCOUNT_OPEN_DATA_TRANSFER};

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //行が取得できる場合、エラー「口座開設データ移管処理中」をスローする。
        if (!l_lisRecords.isEmpty())
        {
            log.debug("口座開設データ移管処理中。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03179,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設データ移管処理中。");
        }
        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (getプリファ@レンス)<BR>
     * システムプリファ@レンスから毎回の処理件数を取得する。<BR>
     * <BR>
     * １）システムプリファ@レンスRowを取得する。<BR>
     * 検索条件：<BR>
     * 名称（環境変数名） = "web3.adminAccountOpenAccTransfer.MaxAmount"<BR>
     * <BR>
     * ２）取得したデータ行の値がint型に変換し返却する。<BR>
     * <BR>
     * ３）取得できない場合、デフォルト値「1000」を返却する。<BR>
     * @@throws WEB3BaseException
     * @@return int
     * @@roseuid 4A82668600EA
     */
    private int getPreferences() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPreferences() ";
        log.entering(STR_METHOD_NAME);

        int l_intMaxAmount = 1000;

        SystemPreferencesRow l_sysPreRow = null;

        try
        {
            //システムプリファ@レンスRowを取得する。
            l_sysPreRow = SystemPreferencesDao.findRowByName(
                WEB3SystemPreferencesNameDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER_MAX_AMOUNT);
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

        //取得したデータ行の値がint型に変換し返却する。
        if (l_sysPreRow != null)
        {
            String l_strValue = l_sysPreRow.getValue();
            if (l_strValue != null)
            {
                l_intMaxAmount = Integer.parseInt(l_strValue);
            }

        }

        log.exiting(STR_METHOD_NAME);

        //取得できない場合、デフォルト値「1000」を返却する。
        return l_intMaxAmount;
    }

    /**
     * (saveアップロード終了)<BR>
     * 指定のアップロード行にアップロード終了情報を更新する。<BR>
     * <BR>
     * 　@this.アップロードＩＤに該当する行について<BR>
     * 以下の通り、（管理者共通）アップロードテーブルを更新する。<BR>
     * <BR>
     * 　@アップロード終了日時 = System.currentTimeMillis()<BR>
     * <BR>
     * @@param l_lngUploadID - (アップロードＩＤ)<BR>
     * アップロードＩＤ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A827A5C01D4
     */
    private void saveUploadEnd(long l_lngUploadID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveUploadEnd()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //this.getアップロードＩＤ()に該当する行について
            //以下の通り、（管理者共通）アップロードテーブルを更新する。
            AdministratorUploadRow l_administratorUploadRow = 
                (AdministratorUploadRow)
                    AdministratorUploadDao.findRowByAdministratorUploadId(l_lngUploadID);

            if (l_administratorUploadRow == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            AdministratorUploadParams l_administratorUploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);

            //アップロード終了日時 = System.currentTimeMillis() 
            Timestamp l_tsSystemTime = (new Timestamp(System.currentTimeMillis()));
            l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);

            QueryProcessor l_queryProcessor =
                 Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
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
     * (アップロードTransactionCallback) <BR>
     * <BR>
     * アップロードテーブルに行を作成するTransactionCallbackクラス<BR>
     */
    public class UploadTransactionCallback implements TransactionCallback
    {
        /**
         * （管理者共通）アップロード行<BR>
         * （管理者共通）アップロード行オブジェクト<BR>
         * <BR>
         * ※　@（管理者共通）アップロードParamsクラスはDDLにて自動生成する。<BR>
         */
        public AdministratorUploadParams administratorUploadParams;

        /**
         * @@roseuid 4107644702DE
         */
        public UploadTransactionCallback()
        {

        }

        /**
         * （管理者共通）アップロードテーブルに行をinsertする。<BR>
         * <BR>
         * this.（管理者共通）アップロード行 の内容でDBに行を挿入（insert）する。<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            AdministratorUploadParams l_administratorUploadParams =
                this.administratorUploadParams;

            //this.（管理者共通）アップロード行 の内容でDBに行を挿入（insert）する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_administratorUploadParams);

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
