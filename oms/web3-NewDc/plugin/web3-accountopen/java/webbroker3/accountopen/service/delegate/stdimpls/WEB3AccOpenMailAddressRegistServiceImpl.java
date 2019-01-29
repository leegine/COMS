head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddressRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス登録サービスImpl(WEB3AccOpenMailAddressRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 孟亞南 (中訊) 新規作成 モデル No.166 No.169 ＤＢ更新仕様 039 043
Revision History : 2009/08/26 張騰宇 (中訊) モデル No.189
Revision History : 2009/09/03 張騰宇 (中訊) モデル No.207 ＤＢ更新仕様 051
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.MailAddressRegiDao;
import webbroker3.accountopen.data.MailAddressRegiParams;
import webbroker3.accountopen.data.MailAddressRegiRow;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenMailAddressRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3AccOpenSendMailStatusDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMailAddressDuplicationCheck;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.WEB3GentradeMobileMailAddressCheck;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設メールアドレス登録サービスImpl)<BR>
 * 口座開設メールアドレス登録サービス実装クラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AccOpenMailAddressRegistServiceImpl implements WEB3AccOpenMailAddressRegistService
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddressRegistServiceImpl.class);

    /**
     * 口座開設メールアドレス登録サービスImpl
     */
    public WEB3AccOpenMailAddressRegistServiceImpl()
    {
    }

    /**
     * 口座開設メールアドレス登録処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、口座開設メールアドレス登録入力リクエストの場合<BR>
     * 　@－get入力画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、口座開設メールアドレス登録完了リクエストの場合<BR>
     * 　@－submit登録()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
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

        //引数のリクエストデータが、口座開設メールアドレス登録入力リクエストの場合
        //get入力画面()
        if (l_request instanceof WEB3AccOpenMailAddrRegInputRequest)
        {
            WEB3AccOpenMailAddrRegInputResponse l_response =
                getInputScreen((WEB3AccOpenMailAddrRegInputRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //引数のリクエストデータが、口座開設メールアドレス登録完了リクエストの場合
        //submit登録()
        else if (l_request instanceof WEB3AccOpenMailAddrRegCompleteRequest)
        {
            WEB3AccOpenMailAddrRegCompleteResponse l_response =
                submitRegist((WEB3AccOpenMailAddrRegCompleteRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
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
    }

    /**
     * (get入力画面)<BR>
     * 口座開設メールアドレス登録入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（メールアドレス登録）get入力画面」参照。 <BR>
     * <BR>
     * @@param l_request - 口座開設メールアドレス登録入力リクエスト
     * @@return WEB3AccOpenMailAddrRegInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenMailAddrRegInputResponse getInputScreen(WEB3AccOpenMailAddrRegInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AccOpenMailAddrRegInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //createResponse
        WEB3AccOpenMailAddrRegInputResponse l_response =
            (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit登録)<BR>
     * 口座開設メールアドレス登録完了処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（メールアドレス登録）submit登録」参照。<BR>
     * ======================================================== <BR>
     * シーケンス図 ：(口座開設メールアドレス登録 / 口座開設（メールアドレス登録）submit登録) <BR>
     * 具体位置：(メールアドレスとして適切でない場合（isMailAddress() == false）、例外をスローする。)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00777 <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_request - 口座開設メールアドレス登録完了リクエスト
     * @@return WEB3AccOpenMailAddrRegCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenMailAddrRegCompleteResponse submitRegist(WEB3AccOpenMailAddrRegCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitRegist(WEB3AccOpenMailAddrRegCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //isMailAddress
        boolean l_blnIsMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.mailAddress);

        //メールアドレスとして適切でない場合（isMailAddress() == false）、例外をスローする。
        if (!l_blnIsMailAddress)
        {
            log.debug("メールアドレスチェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "メールアドレスチェックエラー。");
        }

        //validate携帯アドレス
        WEB3GentradeMobileMailAddressCheck.validateMobileAddress(l_request.mailAddress);
        //validate重複メールアドレス
        this.validateRepeatAddress(l_request.institutionCode, l_request.branchCode, l_request.mailAddress);
        //doInsertQuery
        //挿入する行の内容は、DB更新仕様「メールアドレス登録DB更新（Insert）仕様.xls」参照
        MailAddressRegiParams l_mailAddressRegiParams = new MailAddressRegiParams();
        try
        {
            long l_lngValue = MailAddressRegiDao.newPkValue();
            //メールアドレス登録ID = xTradeにより自動採番された値
            l_mailAddressRegiParams.setMailAddressRegiId(l_lngValue);
            //証券会社コード = リクエストデータ.証券会社コード
            l_mailAddressRegiParams.setInstitutionCode(l_request.institutionCode);
            //部店コード = リクエストデータ.部店コード
            l_mailAddressRegiParams.setBranchCode(l_request.branchCode);
            //emailアドレス = リクエストデータ.メールアドレス
            l_mailAddressRegiParams.setEmailAddress(l_request.mailAddress);
            //顧客姓（漢字） = リクエストデータ.姓
            l_mailAddressRegiParams.setFamilyName(l_request.accountFamilyName);
            //顧客名（漢字） = リクエストデータ.名
            l_mailAddressRegiParams.setGivenName(l_request.accountName);
            //仲介業扱者コード = リクエストデータ.仲介業扱者コード
            l_mailAddressRegiParams.setBrokerageTraderCode(l_request.brokerageCode);
            //リンク元判別コード = リクエストデータ.リンク元判別コード
            l_mailAddressRegiParams.setLinkDistinctionCode(l_request.linkCode);
            //口座区分 = リクエストデータ.口座区分
            l_mailAddressRegiParams.setAccountDiv(l_request.accountType);
            //削除フラグ = 0：有効
            l_mailAddressRegiParams.setDeleteFlag(BooleanEnum.FALSE);
            //更新者コード = メールアドレス登録ID（xTradeにより自動採番された値）
            l_mailAddressRegiParams.setLastUpdater(String.valueOf(l_lngValue));
            //作成日時 = 処理日時
            l_mailAddressRegiParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //更新日時 = 処理日時
            l_mailAddressRegiParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doInsertQuery(l_mailAddressRegiParams);
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

        try
        {
            //メール(証券会社コード : String, 送信メール区分 : String, 識別ID : String)
            //識別ID：リクエスト.口座区分＝「0：個人アカウント」の場合、"1"
            //　@　@　@　@　@リクエスト.口座区分＝「1：法@人アカウント」の場合、"2"
            String l_strDiscernmentId = "";
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_request.accountType))
            {
                l_strDiscernmentId = "1";
            }
            else if (WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_request.accountType))
            {
                l_strDiscernmentId = "2";
            }
            WEB3GentradeMailInfo l_mailInfo =
                new WEB3GentradeMailInfo(
                    l_request.institutionCode,
                    WEB3SendmailDivDef.ACCOPEN_APPLICATION_MAIL,
                    l_strDiscernmentId);

            //doInsertQuery
            //挿入する行の内容は、DB更新仕様「メールアドレス登録_メール送信テーブル.xls」参照
            MailProcParams l_mailProcParams = new MailProcParams();
            //証券会社コード = メールアドレス登録テーブル.証券会社コード
            l_mailProcParams.setInstitutionCode(l_mailAddressRegiParams.getInstitutionCode());
            //部店コード = メールアドレス登録テーブル.部店コード
            l_mailProcParams.setBranchCode(l_mailAddressRegiParams.getBranchCode());
            //送信メール区分 = 口座開設申込メール（0201）
            l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.ACCOPEN_APPLICATION_MAIL);
            //識別ID  リクエストデータ.口座区分＝0：個人アカウントの場合、"1"
            //           リクエストデータ.口座区分＝1：法@人アカウントの場合、"2"
            l_mailProcParams.setDiscernmentId(l_strDiscernmentId);
            //口座コード = "----"
            l_mailProcParams.setAccountCode("----");
            //メールID = メールアドレス登録テーブル.メールアドレス登録ID
            l_mailProcParams.setMailId(l_mailAddressRegiParams.getMailAddressRegiId());
            //年月日１ = 処理日（TradingSystem.getBizDate()）
            l_mailProcParams.setDate1(GtlUtils.getTradingSystem().getBizDate());
            //ID = メールアドレス登録テーブル.メールアドレス登録ID
            l_mailProcParams.setOrderId(l_mailAddressRegiParams.getMailAddressRegiId());
            //名称1 = メールアドレス登録テーブル.顧客姓（漢字）
            l_mailProcParams.setName1(l_mailAddressRegiParams.getFamilyName());
            //名称2 = メールアドレス登録テーブル.顧客名（漢字）
            l_mailProcParams.setName2(l_mailAddressRegiParams.getGivenName());
            //電子メール送信ステイタス = "0：未処理（Email未送信）"
            l_mailProcParams.setStatus(WEB3AccOpenSendMailStatusDef.EMAIL_NOT_SEND);
            //emailアドレス = メールアドレス登録テーブル.メールアドレス
            l_mailProcParams.setEmailAddress(l_mailAddressRegiParams.getEmailAddress());
            //メール本文 = メールアドレス登録テーブル.メールアドレス
            l_mailProcParams.setMailText(l_mailAddressRegiParams.getEmailAddress());
            //削除フラグ = "0:FALSE（有効）"
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
            //作成日時 = 現在日時（GtlUtils.getSystemTimestamp()）
            l_mailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //更新日時 = 現在日時（GtlUtils.getSystemTimestamp()）
            l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //doInsertQuery(Row)
            Processors.getDefaultProcessor().doInsertQuery(l_mailProcParams);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug("メールオブジェクトが取得なぃ." + l_ex.getErrorMessage());
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse
        WEB3AccOpenMailAddrRegCompleteResponse l_response =
            (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
        //(*) プロパティセット
        //レスポンスデータプロパティに以下の通り値をセットする。
        //メールアドレス登録ＩＤ：　@メールアドレス登録テーブルのxTradeにより自動採番されたメールアドレス登録ＩＤ
        l_response.mailRegiID = String.valueOf(l_mailAddressRegiParams.getMailAddressRegiId());
        //現在日時：　@TradingSystem.getSystemTimestamp
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate重複メールアドレス)<BR>
     * 入力したメールアドレスが登録されていないかチェックする。 <BR>
     * <BR>
     * １） 顧客マスタテーブルにメールアドレスが存在するかチェックする。 <BR>
     * 　@　@１－１） webbroker3.gentrade.メールアドレス重複チェッククラスを使用する。 <BR>
     * <BR>
     * 　@　@[メールアドレス重複チェック.get重複アドレス()に指定する引数] <BR>
     * 　@　@メールアドレス：引数.メールアドレス <BR>
     * 　@　@顧客コード：null <BR>
     * 　@　@部店コード：引数.部店コード <BR>
     * 　@　@証券会社コード：引数.証券会社コード <BR>
     * <BR>
     * 　@　@１－２） 重複アドレスが存在する<BR>
     * 　@　@（メールアドレス重複チェック.get重複アドレス()の戻り値の長さ>0）場合、エラーをスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02443<BR>
     * <BR>
     * ２） 口座開設見込客テーブルにメールアドレスが存在するかチェックする <BR>
     * 　@　@２－１） 口座開設見込客テーブルより以下の条件で重複アドレス検索を行う。 <BR>
     * <BR>
     * 　@　@[重複アドレス検索条件] <BR>
     * 　@　@口座開設見込客.証券会社コード = 引数.証券会社コード <BR>
     * 　@　@AND 口座開設見込客.部店コード = 引数.部店コード <BR>
     * 　@　@AND 口座開設見込客.emailアドレス = 引数.メールアドレス <BR>
     * <BR>
     * 　@　@２－２） 該当行が存在する場合、エラーをスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02443<BR>
     * <BR>
     * ３） メールアドレス登録テーブルにメールアドレスが存在するかチェックする <BR>
     * 　@　@３－１）メールアドレス登録テーブルより以下の条件で重複アドレス検索を行う。 <BR>
     * <BR>
     * 　@　@[重複アドレス検索条件] <BR>
     * 　@　@メールアドレス登録.証券会社コード = 引数.証券会社コード <BR>
     * 　@　@AND メールアドレス登録.部店コード = 引数.部店コード <BR>
     * 　@　@AND メールアドレス登録.emailアドレス = 引数.メールアドレス <BR>
     * 　@　@AND メールアドレス登録.削除フラグ = "0：有効" <BR>
     * <BR>
     * 　@　@３－２） 該当行が存在する場合、エラーをスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02443<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strMailAddress - メールアドレス
     * @@throws WEB3BaseException
     */
    private void validateRepeatAddress(String l_strInstitutionCode, String l_strBranchCode, String l_strMailAddress)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRepeatAddress(String, String, String)";
        log.entering(STR_METHOD_NAME);
        //webbroker3.gentrade.メールアドレス重複チェッククラスを使用する。
        Object[] l_objs =
            WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                l_strMailAddress, null, l_strBranchCode, l_strInstitutionCode);
        //重複アドレスが存在する（メールアドレス重複チェック.get重複アドレス()の戻り値の長さ>0）場合、エラーをスローする。
        if (l_objs != null && l_objs.length > 0)
        {
            log.debug("メールアドレスは既に登録済みです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02443,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "メールアドレスは既に登録済みです。");
        }
        //口座開設見込客テーブルにメールアドレスが存在するかチェックする
        //[重複アドレス検索条件]
        //口座開設見込客.証券会社コード = 引数.証券会社コード
        //AND 口座開設見込客.部店コード = 引数.部店コード
        //AND 口座開設見込客.emailアドレス = 引数.メールアドレス
        String l_strWhere = " institution_code = ? and branch_code = ? and email_address = ? ";
        Object[] l_objConds =  new Object[]{l_strInstitutionCode, l_strBranchCode, l_strMailAddress};
        QueryProcessor l_queryProcessor = null;
        List l_lisRecordexcs = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordexcs = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strWhere,
                l_objConds);
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
        //該当行が存在する場合、エラーをスローする。
        if (!l_lisRecordexcs.isEmpty())
        {
            log.debug("メールアドレスは既に登録済みです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02443,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "メールアドレスは既に登録済みです。");
        }

        //メールアドレス登録テーブルにメールアドレスが存在するかチェックする
        //[重複アドレス検索条件]
        //メールアドレス登録.証券会社コード = 引数.証券会社コード
        //AND メールアドレス登録.部店コード = 引数.部店コード
        //AND メールアドレス登録.emailアドレス = 引数.メールアドレス
        //AND メールアドレス登録.削除フラグ = "0：有効"
        String l_strWhere1 = " institution_code = ? and branch_code = ? and email_address = ? and delete_flag = ? ";
        Object[] l_objConds1 =  new Object[]{l_strInstitutionCode, l_strBranchCode, l_strMailAddress, BooleanEnum.FALSE};
        List l_lisRecordexcs1 = null;
        try
        {
            l_lisRecordexcs1 = l_queryProcessor.doFindAllQuery(
                MailAddressRegiRow.TYPE,
                l_strWhere1,
                l_objConds1);
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
        //該当行が存在する場合、エラーをスローする。
        if (!l_lisRecordexcs1.isEmpty())
        {
            log.debug("メールアドレスは既に登録済みです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02443,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "メールアドレスは既に登録済みです。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
