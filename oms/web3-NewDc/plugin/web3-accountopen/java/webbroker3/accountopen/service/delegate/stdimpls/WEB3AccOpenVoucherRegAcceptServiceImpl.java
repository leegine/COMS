head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.08.52.25;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b06481808;
filename	WEB3AccOpenVoucherRegAcceptServiceImpl.java;

1.1
date	2011.03.14.03.35.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptServiceImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 口座開設伝票登録受付サービスImpl(WEB3AccOpenVoucherRegAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/12/15 鄭海良(中訊) 新規作成
Revesion History : 2006/07/13 周捷 (中訊) 仕様変更 モデル072、080
Revesion History : 2006/08/15 車進 (中訊) 仕様変更 モデル091 ＤＢ更新仕様  No.009
Revesion History : 2006/09/12 李俊 (中訊) 仕様変更 モデル099
Revesion History : 2006/09/14 李俊 (中訊) 仕様変更 モデル104
Revesion History : 2006/09/14 柴雙紅 (中訊) 仕様変更 モデル098、103 ＤＢ更新仕様No.014 015 016
Revesion History : 2006/09/19 柴雙紅 (中訊) モデル No.105  ＤＢ更新仕様  No.017
Revesion History : 2006/10/08 徐宏偉 (中訊) ＤＢ更新仕様  No.019
Revesion History : 2006/10/17 柴雙紅 (中訊) ①@仕様変更 モデルNo.107、②ソースの修正依頼
Revesion History : 2006/10/26 徐宏偉 (中訊) 障害No.Ｕ02917対応
Revesion History : 2006/10/29 何文敏 (中訊) 仕様変更 モデル No.108  ＤＢ更新仕様  No.021 022
Revesion History : 2006/11/16 何文敏 (中訊) 仕様変更 モデル No.109  ＤＢ更新仕様  No.023
Revesion History : 2006/11/20 何文敏 (中訊) 仕様変更 モデル No.110
Revesion History : 2006/11/20 何文敏 (中訊) 障害No.Ｕ02952対応
Revesion History : 2006/11/20 何文敏 (中訊) 障害No.Ｕ02953対応
Revesion History : 2006/11/21 何文敏 (中訊) 仕様変更 モデル No.111
Revesion History : 2006/11/28 何文敏 (中訊) 仕様変更 モデル No.114
Revesion History : 2006/11/28 何文敏 (中訊) 仕様変更 モデル No.116
Revesion History : 2006/12/05 何文敏 (中訊) 仕様変更 モデル No.118
Revesion History : 2007/05/28 何文敏 (中訊) 仕様変更 モデル No.124,No.125,No.126,No.129
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更 モデル No.131,No.134
Revesion History : 2007/06/11 何文敏 (中訊) 仕様変更 モデル No.143
Revesion History : 2007/09/20 張騰宇 (中訊) 仕様変更 モデル No.144,145
Revesion History : 2009/08/13 柴双紅 (中訊) 仕様変更 モデル No.177,178,183 ＤＢ更新仕様No.046
Revesion History : 2009/09/04 張騰宇 (中訊) 仕様変更 モデル211
Revesion History : 2010/02/10 武波 (中訊) 仕様変更 モデルNo.218
Revesion History : 2010/11/10 劉レイ (中訊) 仕様変更 モデル No.221  ＤＢ更新仕様  No.054
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.WEB3AccOpenBranch;
import webbroker3.accountopen.data.ExpAccountOpenDao;
import webbroker3.accountopen.data.ExpAccountOpenPK;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccOpenAcceptRow;
import webbroker3.accountopen.data.HostAccRegVoucherDao;
import webbroker3.accountopen.data.HostAccRegVoucherPK;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.data.HostConditionRegVoucherDao;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.accountopen.define.WEB3AccOpenLastUpdaterDef;
import webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInformAcceptUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRealUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenRealDivDef;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3AccountOpenDateUpdateDeterminationDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3PrintFlagDef;
import webbroker3.common.define.WEB3ReportRegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.EleDeliveryManagementDao;
import webbroker3.gentrade.data.EleDeliveryManagementParams;
import webbroker3.gentrade.data.EleDeliveryManagementRow;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設伝票登録受付サービスImpl)<BR>
 * 口座開設伝票登録受付サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenVoucherRegAcceptServiceImpl implements WEB3AccOpenVoucherRegAcceptService
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenVoucherRegAcceptServiceImpl.class);

    /**
     * @@roseuid 41B45E740000
     */
    public WEB3AccOpenVoucherRegAcceptServiceImpl()
    {

    }

    /**
     * 口座開設伝票登録受付処理を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（伝票登録受付）execute」参照。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A19AD600AE
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3AccOpenVoucherRegAcceptResponse l_response = null;

        try
        {
            //1.1 getDefaultProcessor( )
            QueryProcessor l_processor = Processors.getDefaultProcessor();//DataException

            //1.2 口座開設伝票登録受付TransactionCallback( )
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptTransactionCallback();//DataCallbackException

            //1.3 doTransaction(int, TransactionCallback)
            Object l_objRet = l_processor.doTransaction(TransactionalInterceptor.TX_JOIN_EXISTING, l_callback);//DataException

            if (l_objRet instanceof WEB3BaseException)
            {
                WEB3BaseException l_e = (WEB3BaseException)l_objRet;
                log.debug(l_e.getErrorMessage(),l_e);
                throw l_e;
            }

            //1.4 createResponse( )
            l_response =
                (WEB3AccOpenVoucherRegAcceptResponse)l_request.createResponse();
        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。",l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (口座開設伝票登録受付TransactionCallback)<BR>
     * 口座開設伝票登録受付TransactionCallback<BR>
     */
    public class WEB3AccOpenVoucherRegAcceptTransactionCallback implements TransactionCallback
    {

        /**
         * (口座開設伝票登録受付TransactionCallback)<BR>
         * デフォルトコンストラクタ<BR>
         * @@return
         * webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptServiceImpl.WEB3AccOpenVoucherRegAcceptTrans
         * actionCallback
         * @@throws DataCallbackException
         * @@roseuid 41A19B490012
         */
        public WEB3AccOpenVoucherRegAcceptTransactionCallback() throws DataCallbackException
        {

        }

        /**
         * (process)<BR>
         * トランザクション処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「口座開設（伝票登録受付）process」参照<BR>
         * @@return Object
         * @@throws DataCallbackException
         * @@roseuid 41A19B110050
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                //1.1 get受付キュー( )
                HostAccOpenAcceptParams[] l_acceptParams = this.getAcceptQueue();

                int l_intCount = l_acceptParams.length;
                WEB3AccOpenVoucherRegAcceptUnitService l_unitService =
                    (WEB3AccOpenVoucherRegAcceptUnitService)Services.getService(WEB3AccOpenVoucherRegAcceptUnitService.class);

                WEB3AccOpenRealUnitService l_accOpenRealUnitService =
                    (WEB3AccOpenRealUnitService)Services.getService(
                        WEB3AccOpenRealUnitService.class);
                
                //1.2 受付キュー（get受付キュー()戻り値）各要素毎のLOOP処理
                for (int i = 0; i < l_intCount; i++)
                {
                    // is既存客申込受付(口座開設伝票登録受付キューParams)
                    // データコードが既存客からの申込受付かどうかを判定する。
                    boolean l_blnIsExistingAccountAccept = this.isExistingAccountAccept(l_acceptParams[i]);

                    String l_strRequestCode = null;
                    // 口座開設伝票登録受付キューParams.データコードがGI82Cの場合、GI823：振替申込（銀行）
                    if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT.equals(
                        l_acceptParams[i].getRequestCode()))
                    {
                        l_strRequestCode = WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK;
                    }
                    // 口座開設伝票登録受付キューParams.データコードがGI82Hの場合、GI828：振替申込（郵貯）
                    else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT.equals(
                        l_acceptParams[i].getRequestCode()))
                    {
                        l_strRequestCode = WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL;
                    }
                    //口座開設伝票登録受付キューParams.データコードがGI84Cの場合、
                    //GI843：取報・取残電子交付・特定口座登録
                    else if (WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(
                        l_acceptParams[i].getRequestCode()))
                    {
                        l_strRequestCode = WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST;
                    }

                    //  既存客からの申込受付の場合
                    if (l_blnIsExistingAccountAccept)
                    {
                        VariousInformParams l_variousInformParams = this.getVariousInformParams(
                            l_acceptParams[i].getInstitutionCode(),
                            l_acceptParams[i].getBranchCode(),
                            l_acceptParams[i].getOrderRequestNumber(),
                            l_strRequestCode);

                        // 各種連絡Paramsが取得できる場合
                        if (l_variousInformParams != null)
                        {
                            // notify各種連絡受付
                            WEB3AccOpenInformAcceptUnitService l_accOpenInformAcceptUnitService =
                                (WEB3AccOpenInformAcceptUnitService)Services.getService(
                                    WEB3AccOpenInformAcceptUnitService.class);
                            l_accOpenInformAcceptUnitService.notifyInformAccept(
                                l_acceptParams[i],
                                l_variousInformParams,
                                WEB3StatusDef.DEALT);

                            // continue
                            continue;
                        }
                    }

                    //1.2.1 notify伝票登録受付(口座開設伝票登録受付キューParams)
                    String l_strStatusDiv = l_unitService.notifyVoucherRegAccept(l_acceptParams[i]);//WEB3BaseException
                 
                    //1.2.2 updateキュー(口座開設伝票登録受付キューParams, String)
                    this.updateQueue(l_acceptParams[i], l_strStatusDiv);
                    
                    //1.2.3 部店行オブジェクトを取得する 
                    //[部店()に指定する引数] 
                    //証券会社コード：口座開設伝票登録受付キュー.証券会社コード 
                    //部店コード：口座開設伝票登録受付キュー.部店コード 
                    WEB3AccOpenBranch l_accOpenBranch = new WEB3AccOpenBranch(
                        l_acceptParams[i].getInstitutionCode(),
                        l_acceptParams[i].getBranchCode());
                    
                    //1.2.4部店用プリファ@レンスデータを取得する。　@ 
                    //[get部店用プリファ@レンス()に設定する引数] 
                    //部店ID：部店.get部店ID の戻り値 
                    //プリファ@レンス名 ："accopen.real.div"(口座開設リアル連携実施区分)
                    String l_strPerences = this.getBranchPreferences(l_accOpenBranch.getBranchId(),
                        WEB3BranchPreferencesNameDef.ACCOPEN_REAL_DIV);

                    //get証券会社プリファ@レンス
                    //証券会社コード：口座開設伝票登録受付キュー.証券会社コード
                    //プリファ@レンス名 ："accountopendate.update.determination"(口座開設日更新判定区分)
                    String l_strInstitutionPreferences = this.getInstitutionPreferences(
                        l_acceptParams[i].getInstitutionCode(),
                        WEB3InstitutionPreferencesNameDef.ACCOUNTOPENDATE_UPDATE_DETERMINATION);
                    //1.2.5下記の条件に該当する場合、口座開設リアル連携処理を実施する。
                    //[処理実施条件]
                    //　@１）1.2.1.1.の戻り値 = "1" (処理済)　@かつ、
                    //　@２）1.2.4. get部店用プリファ@レンス()の戻り値 = "1"　@（処理実施）　@かつ、
                    //　@３）口座開設伝票登録受付キュー.データコード = "GI84E" または "GI82A"
                    //  ４）口座開設伝票登録受付キュー.受付結果 = "1"（受付完了）
                    boolean l_blnDataCode = 
                        WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(
                            l_acceptParams[i].getRequestCode()) || 
                            WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT.equals(
                                l_acceptParams[i].getRequestCode());
                    
                    if (WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strStatusDiv) && 
                        WEB3AccOpenRealDivDef.EXECUTE.equals(l_strPerences) && 
                        l_blnDataCode &&
                        WEB3AcceptStatusDef.OVER.equals(l_acceptParams[i].getAcceptStatus()))
                    {
                        try
                        {
                            l_accOpenRealUnitService.process(l_acceptParams[i]);
                        }
                        catch (WEB3BaseException l_e)
                        {
                            log.debug(STR_METHOD_NAME);
                        }
                    }
                    
                    // 口座登録日更新処理実施
                    // get部店用プリファ@レンス()の戻り値 = "1"(処理実施)の場合
                    if (WEB3AccOpenRealDivDef.EXECUTE.equals(l_strPerences))
                    {
                        // update口座開設登録日
                        WEB3AccOpenRealUnitServiceImpl l_accOpenRealUnitServiceImpl =
                            new WEB3AccOpenRealUnitServiceImpl();
                        l_accOpenRealUnitServiceImpl.updateAccOpenOpenDate(l_acceptParams[i]);
                    }

                    //印刷フラグと口座登録日更新処理実施
                    //下記の条件に該当する場合、口座開設見込客の印刷フラグを更新する。
                    //[処理実施条件]
                    //　@１）1.2.3.の戻り値 = "1" (処理済)　@かつ、
                    //　@２）口座開設伝票登録受付キュー.データコード = "GI84E" または "GI82A"　@かつ、
                    //　@３）口座開設伝票登録受付キュー.受付結果 = "1"　@（受付完了）
                    if (WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strStatusDiv)
                        && l_blnDataCode
                        && WEB3AcceptStatusDef.OVER.equals(l_acceptParams[i].getAcceptStatus()))
                    {
                        //update印刷フラグ(口座開設伝票登録受付キュー : 口座開設伝票登録受付キューParams)
                        //[引数]
                        // 口座開設伝票登録受付キュー： 口座開設伝票登録受付キューParams
                        this.updatePrintFlag(l_acceptParams[i]);

                        if (WEB3AccountOpenDateUpdateDeterminationDef.EXECUTE.equals(
                            l_strInstitutionPreferences))
                        {
                            this.updateAccOpenOpenDate(l_acceptParams[i]);
                        }
                    }

                    //update電子交付管理
                    //下記の条件に該当する場合、処理を実施する。
                    //[処理実施条件]
                    //　@１）1.2.3.の戻り値 = "1" (処理済)　@かつ、
                    //　@２）口座開設伝票登録受付キュー.データコード = "GI84C"　@かつ、
                    //　@３）口座開設伝票登録受付キュー.受付結果 = "1"　@（受付完了）
                    if (WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strStatusDiv)
                        && WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(
                            l_acceptParams[i].getRequestCode())
                        && WEB3AcceptStatusDef.OVER.equals(
                            l_acceptParams[i].getAcceptStatus()))
                    {
                        //電子交付管理テーブルを更新する。
                        //[引数]
                        //口座開設伝票登録受付キュー： 口座開設伝票登録受付キューParams
                        this.updateEleDeliveryManagement(l_acceptParams[i]);
                    }
                }
            }
            catch (WEB3BaseException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                return l_ex;
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (updateキュー)<BR>
         * 口座開設伝票登録受付キューを更新する。<BR>
         * <BR>
         * －（処理区分 == null）の場合、処理対象外と判断し、更新を行わず処理を<BR>
         * 終了する。<BR>
         * －以外、引数の口座開設伝票登録受付キューに以下の通り値を<BR>
         * セットして更新する。<BR>
         * <BR>
         * 　@　@口座開設伝票登録受付キュー.処理区分 = 処理区分<BR>
         * @@param l_accOpenAcceptParams - 口座開設伝票登録受付キュー<BR>
         * <BR>
         * ※　@口座開設伝票登録受付キューParamsクラスは、DDLより自動生成する。<BR>
         *
         * @@param l_strProcessDiv - 処理区分<BR>
         * <BR>
         * 1： 処理済<BR>
         * 9： エラー<BR>
         * @@roseuid 41A4472B02E7
         */
        protected void updateQueue(HostAccOpenAcceptParams l_accOpenAcceptParams, String l_strProcessDiv)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " updateQueue(HostAccOpenAcceptParams, String)";
            log.entering(STR_METHOD_NAME);

            //（処理区分 == null）の場合、処理対象外と判断し、更新を行わず処理を終了する。
            if (l_strProcessDiv == null || WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strProcessDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //以外、引数の口座開設伝票登録受付キューに以下の通り値をセットして更新する。
            try
            {
                l_accOpenAcceptParams.setStatus(l_strProcessDiv);
                Processors.getDefaultProcessor().doUpdateQuery(l_accOpenAcceptParams);//DataException
            }
            catch (DataException l_e)
            {
                log.error("DBへのアクセスに失敗しました。",l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (get受付キュー)<BR>
         * 口座開設伝票登録受付キューデータを取得する。 <BR>
         * <BR>
         * １）　@キューテーブル検索 <BR>
         * 　@以下の条件で、口座開設伝票登録受付キューテーブルを検索する。 <BR>
         * <BR>
         * 　@[条件] <BR>
         * 　@口座開設伝票登録受付キュー.処理区分 = 0:未処理 And <BR>
         * 　@（口座開設伝票登録受付キュー.データコード in  <BR>
         * 　@　@（GI82A：顧客登録受付，GI82G：契約書徴収受付，<BR>
         * 　@　@　@GI82C：振替申込（銀行）受付，<BR>
         * 　@　@　@GI82H：振替申込（郵貯）受付，GI82B：保振同意受付，<BR>
         * 　@　@　@GI83G：有料情報受付，<BR>
         * 　@　@　@GI82E：MRF口座受付，GI84I：上場外株・株主登録受付，<BR>
         * 　@　@　@GI84H：顧客名称登録受付，<BR>
         * 　@　@　@GI81I：内部者登録受付，GI82D：GP条件登録受付，<BR>
         * 　@　@　@GI84E：顧客登録（仲介業）受付），<BR>
         * 　@　@　@GI85D：外貨預金口座登録受付,<BR>
         * 　@　@　@GI84C：取報・取残電子交付・特定口座登録受付，<BR>
         * 　@　@　@GI86E：機@構通知情報登録受付）<BR>
         * <BR>
         * 　@※　@未処理の各伝票受付データ<BR>
         * <BR>
         * ２）　@検索結果を配列にして返却する。<BR>
         * 　@　@
         * @@return webbroker3.accountopen.data.HostAccOpenAcceptParams[]
         * @@roseuid 41A4483200E3
         */
        protected HostAccOpenAcceptParams[] getAcceptQueue()
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " getAcceptQueue()";
            log.entering(STR_METHOD_NAME);

            List l_lisRecords = null;

            try
            {
                //execute query
                String l_strWhere = "status = ? and request_code in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                Object[] l_objs = new Object[]{
                    WEB3HostStatusDef.NOT_STARTED_PROCESS,
                    WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_CONTRACT_COLLECT_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_AGREE_TRANSFER_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_CHARGED_INFO_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_MRF_ACCOUNT_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_GP_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT,
                    WEB3HostRequestCodeDef.F_DEPOSIT_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REG_ACCEPT};
                    l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                    HostAccOpenAcceptRow.TYPE,
                    l_strWhere,
                    null, 
                    null,
                    l_objs);//DataException
            }
            catch (DataException l_e)
            {
                log.error("DBへのアクセスに失敗しました。",l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }

            if (l_lisRecords.isEmpty())
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            //to array
            HostAccOpenAcceptParams[] l_params = new HostAccOpenAcceptParams[l_lisRecords.size()];
            l_lisRecords.toArray(l_params);

            log.exiting(STR_METHOD_NAME);
            return l_params;
        }
        
        /**
         * (get部店用プリファ@レンス)<BR>
         * 部店用プリファ@レンスデータを取得する。<BR>
         * <BR>
         * １） 部店用プリファ@レンステーブルを下記の条件で検索する。<BR>
         *   [条件] <BR>
         *   部店用プリファ@レンス.部店ID = 引数.部店ID<BR>
         *   部店用プリファ@レンス.プリファ@レンス名 = 引数.プリファ@レンス名<BR>
         *   部店用プリファ@レンス.プリファ@レンス名の連番 = 1 <BR>
         * <BR>
         * ２） 戻り値 <BR>
         *   １）の処理より、検索結果が0件の場合、戻り値として「0」を返す。<BR>
         *   検索結果が1件の場合、戻り値として、プリファ@レンスの値 を返す。<BR>
         * 　@　@
         * @@return String
         * @@roseuid 41A4483200E3
         */  
        protected String getBranchPreferences(String l_strBranchId, String l_strPreferencesname)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getBranchPreferences()";
            log.entering(STR_METHOD_NAME);
            
            String l_strBranchPreferences = null;
            try
            {
                //１） 部店用プリファ@レンステーブルを下記の条件で検索する。 
                //[条件] 
                //部店用プリファ@レンス.部店ID = 引数.部店ID 
                //部店用プリファ@レンス.プリファ@レンス名 = 引数.プリファ@レンス名
                //部店用プリファ@レンス.プリファ@レンス名の連番 = 1
                if (WEB3StringTypeUtility.isEmpty(l_strBranchId))
                {
                    log.debug("パラメータ値不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                log.debug("引数.部店ID" + Long.parseLong(l_strBranchId) + 
                    "引数.プリファ@レンス名" +l_strPreferencesname + 
                    "部店用プリファ@レンス.プリファ@レンス名の連番" + 1);
                BranchPreferencesRow l_row = 
                    (BranchPreferencesRow)BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                        Long.parseLong(l_strBranchId), l_strPreferencesname, 1);
                
                //２） 戻り値 
                //１）の処理より、検索結果が0件の場合、戻り値として「0」を返す。 
                //検索結果が1件の場合、戻り値として、プリファ@レンスの値 を返す。
                if (l_row == null)
                {
                    l_strBranchPreferences = "0";
                } 
                else if (l_row != null)
                {
                    l_strBranchPreferences = l_row.getValue();
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }            
            log.exiting(STR_METHOD_NAME);
            return l_strBranchPreferences;   
        }    

        /**
         * (get各種連絡テーブル)<BR>
         * 各種連絡テーブルデータを取得する。<BR>
         * <BR>
         * １）　@各種連絡テーブルを検索<BR>
         * <BR>
         * 　@［条件］<BR>
         * 　@証券会社コード = 引数.証券会社コード AND<BR>
         * 　@部店コード = 引数.部店コード AND<BR>
         * 　@伝票識別コード = 引数.伝票識別コード AND<BR>
         * 　@データコード = 引数.データコード<BR>
         * <BR>
         * ２）　@検索結果(Listの先頭要素)を返却<BR>
         * ※検索結果が0件の場合、nullを返す。<BR>
         * <BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード
         * @@param l_strBranchCode - (部店コード)<BR>
         * 部店コード
         * @@param l_strOrderRequestNumber - (伝票識別コード)<BR>
         * 伝票識別コード
         * @@param l_strRequestCode - (データコード)<BR>
         * データコード
         * @@return VariousInformParams
         * @@throws WEB3BaseException
         */
        private VariousInformParams getVariousInformParams(
            String l_strInstitutionCode,
            String l_strBranchCode,
            String l_strOrderRequestNumber,
            String l_strRequestCode) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " getVariousInformParams(String, String, String, String)";
            log.entering(STR_METHOD_NAME);

            List l_lisRecord = null;
            try
            {
                // 各種連絡テーブルを検索
                // 証券会社コード = 引数.証券会社コード AND
                // 部店コード = 引数.部店コード AND
                // 伝票識別コード = 引数.伝票識別コード AND
                // データコード = 引数.データコード
                StringBuffer l_sbQueryString = new StringBuffer();
                l_sbQueryString.append("institution_code = ? and ");
                l_sbQueryString.append(" branch_code = ? and ");
                l_sbQueryString.append(" order_request_number = ? and ");
                l_sbQueryString.append(" request_code = ?");

                Object[] l_queryDataContainers = new Object[]{
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strOrderRequestNumber,
                    l_strRequestCode};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_lisRecord = l_queryProcessor.doFindAllQuery(
                    VariousInformRow.TYPE,
                    l_sbQueryString.toString(),
                    l_queryDataContainers);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // ２）　@検索結果(Listの先頭要素)を返却
            // ※検索結果が0件の場合、nullを返す。
            if (l_lisRecord.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            VariousInformRow l_variousInformRow = (VariousInformRow)l_lisRecord.get(0);
            VariousInformParams l_variousInformParams =
                new VariousInformParams(l_variousInformRow);

            log.exiting(STR_METHOD_NAME);
            return l_variousInformParams;
        }

        /**
         * (is既存客申込受付)<BR>
         * データコードが既存客からの申込受付かどうかを判定する。<BR>
         * <BR>
         * 既存客からの申込の場合はtrue、それ以外はfalse <BR>
         * <BR>
         * １）既存客申込判定<BR>
         * <BR>
         * 　@引数.受付キューParams.データコード が<BR>
         * 　@GI82C：振替申込（銀行）受付 又は、<BR>
         * 　@GI82H：振替申込（郵貯）受付 又は、<BR>
         * 　@GI84C：取報・取残電子交付・特定口座登録受付 <BR>
         * 　@の場合trueを返却する。<BR>
         * <BR>
         * ２）falseを返却する。<BR>
         * <BR>
         * @@param l_hostAccOpenAcceptParams - (受付キューParams)<BR>
         * 口座開設伝票登録受付キューParams<BR>
         * <BR>
         * ※　@口座開設伝票登録受付キューParamsクラスは、DDLより自動生成する。
         * @@return boolean
         */
        private boolean isExistingAccountAccept(HostAccOpenAcceptParams l_hostAccOpenAcceptParams)
        {
            final String STR_METHOD_NAME = " isExistingAccountAccept(HostAccOpenAcceptParams)";
            log.entering(STR_METHOD_NAME);

            if (l_hostAccOpenAcceptParams == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            // 引数.受付キューParams.データコード
            String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();
            // 引数.受付キューParams.データコード が
            // GI82C：振替申込（銀行）受付 又は、
            // GI82H：振替申込（郵貯）受付 又は、
            // GI84C：取報・取残電子交付・特定口座登録受付の場合trueを返却する。
            if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT.equals(l_strRequestCode)
                || WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT.equals(l_strRequestCode)
                || WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(l_strRequestCode))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }

            // ２）falseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        /**
         * (update印刷フラグ)<BR>
         * 口座開設見込客テーブルの印刷フラグを更新する。<BR>
         * <BR>
         * １）　@登録キューデータ取得<BR>
         * 　@口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。<BR>
         * <BR>
         * 　@[検索条件]<BR>
         * 　@登録キューテーブル(*1).識別コード = 受付キュー.識別コード And　@※登録キュー.識別コードは、<BR>
         * 　@　@伝票の識別コード（order_request_number）<BR>
         * 　@登録キューテーブル(*1).データコード = 受付キュー.データコード And<BR>
         * 　@登録キューテーブル(*1).証券会社コード = 受付キュー.証券会社コード And<BR>
         * 　@登録キューテーブル(*1).部店コード = 受付キュー.部店コード And<BR>
         * 　@登録キューテーブル(*1).顧客コード = 受付キュー.顧客コード<BR>
         * <BR>
         * 　@(*1) [データコードに対応する登録キューテーブル名]<BR>
         * 　@GI82A：顧客登録受付　@→　@顧客登録伝票(G11)キューテーブル<BR>
         * 　@GI84E：顧客登録（仲介業）受付　@→　@顧客登録伝票(G11)キューテーブル<BR>
         * <BR>
         * 　@取得できなかった場合は、後続処理は行わない。<BR>
         * <BR>
         * ２）　@口座開設見込客データの取得<BR>
         * <BR>
         * 　@口座開設見込客テーブルを以下の条件で検索する。<BR>
         * 　@<BR>
         * 　@[検索条件]<BR>
         * 　@証券会社コード = 口座開設伝票登録受付キュー.証券会社コード<BR>
         * 　@識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）<BR>
         * <BR>
         * 　@取得できなかった場合、後続処理は行わない。<BR>
         * <BR>
         * ３）　@印刷フラグの設定<BR>
         * 　@２）で検索されたレコードに以下の値を設定する。<BR>
         * <BR>
         * 　@[設定値]<BR>
         * 　@印刷フラグ："0：印刷可"<BR>
         * 　@更新日時：処理日時<BR>
         * <BR>
         * ４）　@更新処理<BR>
         * 　@３）の設定値で口座開設見込客テーブルの更新処理を行う。<BR>
         * <BR>
         * 　@更新内容は、DB更新仕様「口座開設見込客DB更新（印刷フラグ）仕様.xls」 参照。<BR>
         * <BR>
         * @@param l_hostAccOpenAcceptParams - (口座開設伝票登録受付キュー)<BR>
         * 口座開設伝票登録受付キュー<BR>
         * @@throws WEB3BaseException
         */
        protected void updatePrintFlag(HostAccOpenAcceptParams l_hostAccOpenAcceptParams)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "updatePrintFlag(HostAccOpenAcceptParams)";
            log.entering(STR_METHOD_NAME);

            ExpAccountOpenRow l_expAccountOpenRow = null;
            try
            {
                //１）　@登録キューデータ取得
                //口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。
                //[検索条件]
                //登録キューテーブル(*1).識別コード = 受付キュー.識別コード And　@※登録キュー.識別コードは、
                //伝票の識別コード（order_request_number）
                //登録キューテーブル(*1).データコード = 受付キュー.データコード And
                //登録キューテーブル(*1).証券会社コード = 受付キュー.証券会社コード And
                //登録キューテーブル(*1).部店コード = 受付キュー.部店コード And
                //登録キューテーブル(*1).顧客コード = 受付キュー.顧客コード
                String l_strOrderRequestNumber = l_hostAccOpenAcceptParams.getOrderRequestNumber();
                String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();
                String l_strInstitutionCode = l_hostAccOpenAcceptParams.getInstitutionCode();
                String l_strBranchCode = l_hostAccOpenAcceptParams.getBranchCode();
                String l_strAccountCode = l_hostAccOpenAcceptParams.getAccountCode();
                String l_strAccOpenRequestNumber = null;

                String[] l_acceptReqCodes =
                    new String[]{"GI82A", "GI84E"};
                String[] l_registReqCodes =
                    new String[]{"GI821", "GI845"};

                String l_strRegistReqCode = null;
                for (int i = 0; i < l_acceptReqCodes.length; i++)
                {
                    if (l_acceptReqCodes[i].equals(l_strRequestCode))
                    {
                        l_strRegistReqCode = l_registReqCodes[i];
                        break;
                    }
                }
                //(*1) [データコードに対応する登録キューテーブル名]
                //GI82A：顧客登録受付　@→　@顧客登録伝票(G11)キューテーブル
                //GI84E：顧客登録（仲介業）受付　@→　@顧客登録伝票(G11)キューテーブル
                //取得できなかった場合は、後続処理は行わない。
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                // GI82A：顧客登録受付　@→　@顧客登録伝票(G11)キューテーブル
                if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT.equals(l_strRequestCode))
                {
                    HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                        l_strOrderRequestNumber,
                        l_strRegistReqCode,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
                    HostAccRegVoucherRow l_row =
                        (HostAccRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                    l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
                }
                // GI84E：顧客登録（仲介業）受付　@→　@顧客登録伝票(G11)キューテーブル
                else if (WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(l_strRequestCode))
                {
                    HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                        l_strOrderRequestNumber,
                        l_strRegistReqCode,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
                    HostAccRegVoucherRow l_row =
                        (HostAccRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                    l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
                }
                else
                {
                    log.debug("取得できなかった場合は、後続処理は行わない。");
                    log.exiting(STR_METHOD_NAME);
                    return;
                }

                //２）　@口座開設見込客データの取得
                //口座開設見込客テーブルを以下の条件で検索する
                //　@[検索条件]
                //　@証券会社コード = 口座開設伝票登録受付キュー.証券会社コード
                //　@識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）
                l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk(
                    new ExpAccountOpenPK(
                        l_hostAccOpenAcceptParams.getInstitutionCode(),
                        l_strAccOpenRequestNumber));

                //３）　@印刷フラグの設定
                ExpAccountOpenParams l_expAccountOpenParams =
                    new ExpAccountOpenParams(l_expAccountOpenRow);

                //２）で検索されたレコードに以下の値を設定する。
                //[設定値]
                //印刷フラグ："0：印刷可"
                l_expAccountOpenParams.setPrintFlag(WEB3PrintFlagDef.ENABLE_PRINT);

                //更新日時：処理日時
                l_expAccountOpenParams.setLastUpdatedTimestamp(
                    GtlUtils.getSystemTimestamp());

                //４）　@更新処理
                //３）の設定値で口座開設見込客テーブルの更新処理を行う
                //更新内容は、DB更新仕様「口座開設見込客DB更新（印刷フラグ）仕様.xls」 参照。
                WEB3DataAccessUtility.updateRow(l_expAccountOpenParams);
            }
            catch (DataFindException l_ex)
            {
                log.debug("取得できなかった場合は、後続処理は行わない。");
                log.exiting(STR_METHOD_NAME);
                return;
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

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (get証券会社プリファ@レンス)<BR>
         * 証券会社プリファ@レンスデータを取得する。<BR>
         * <BR>
         * １）引数.証券会社コードに該当する証券会社IDを取得する。<BR>
         * <BR>
         * ２）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する。<BR>
         * 　@　@[条件]<BR>
         * 　@　@証券会社ID = １）で取得した証券会社ID<BR>
         * 　@　@プリファ@レンス名 = 引数.プリファ@レンス名<BR>
         * 　@　@プリファ@レンス名の連番 = "1"<BR>
         * <BR>
         * ３） 戻り値<BR>
         * 　@２）の処理より、検索結果が0件の場合、戻り値として「0」を返す。<BR>
         * 　@検索結果が1件の場合、戻り値として、プリファ@レンスの値 を返す。<BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード<BR>
         * @@param l_strInstitutionPreferencesName - (プリファ@レンス名)<BR>
         * プリファ@レンス名<BR>
         * @@throws WEB3BaseException
         */
        protected String getInstitutionPreferences(
            String l_strInstitutionCode, 
            String l_strInstitutionPreferencesName) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getInstitutionPreferences(String, String)";
            log.entering(STR_METHOD_NAME);

            String l_strInstitutionPreferences = null;
            try
            {
                //１）引数.証券会社コードに該当する証券会社IDを取得する。
                AccountManager l_accountManager = GtlUtils.getAccountManager();

                Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
                long l_lngInstitutionId = l_institution.getInstitutionId();

                //２）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する。
                InstitutionPreferencesRow l_row = 
                    (InstitutionPreferencesRow)InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                        l_lngInstitutionId, l_strInstitutionPreferencesName, 1);

                //３） 戻り値
                //２）の処理より、検索結果が0件の場合、戻り値として「0」を返す。
                if (l_row == null)
                {
                    l_strInstitutionPreferences = "0";
                } 
                else
                {
                    //検索結果が1件の場合、戻り値として、プリファ@レンスの値 を返す。
                    l_strInstitutionPreferences = l_row.getValue();
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            log.exiting(STR_METHOD_NAME);
            return l_strInstitutionPreferences;
        }

        /**
         * (update口座開設登録日)<BR>
         * 口座開設見込客テーブルの口座登録日を更新する。<BR>
         * <BR>
         * １）　@登録キューデータ取得<BR>
         * 　@口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。<BR>
         * <BR>
         * 　@[検索条件]<BR>
         * 　@登録キューテーブル(*1).識別コード = 受付キュー.識別コード And<BR>
         * 　@　@※登録キュー.識別コードは、伝票の識別コード（order_request_number）<BR>
         * 　@登録キューテーブル(*1).データコード = 受付キュー.データコード And<BR>
         * 　@登録キューテーブル(*1).証券会社コード = 受付キュー.証券会社コード And<BR>
         * 　@登録キューテーブル(*1).部店コード = 受付キュー.部店コード And<BR>
         * 　@登録キューテーブル(*1).顧客コード = 受付キュー.顧客コード<BR>
         * <BR>
         * 　@(*1) [データコードに対応する登録キューテーブル名]<BR>
         * 　@GI82A：顧客登録受付　@→　@顧客登録伝票(G11)キューテーブル<BR>
         * 　@GI84E：顧客登録（仲介業）受付　@→　@顧客登録伝票(G11)キューテーブル<BR>
         * <BR>
         * 　@取得できなかった場合は、後続処理は行わない。<BR>
         * <BR>
         * ２）　@口座開設見込客データの取得<BR>
         * <BR>
         * 　@口座開設見込客テーブルを以下の条件で検索する。　@<BR>
         * 　@<BR>
         * 　@[検索条件]<BR>
         * 　@証券会社コード = 口座開設伝票登録受付キュー.証券会社コード<BR>
         * 　@識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）<BR>
         * <BR>
         * 　@取得できなかった場合、後続処理は行わない。<BR>
         * <BR>
         * ３）　@口座登録日の設定<BR>
         * 　@２）で検索されたレコードに以下の値を設定する。<BR>
         * <BR>
         * 　@[設定値]<BR>
         * 　@口座登録日：処理日時（YYYY/MM/DD 00:00:00）<BR>
         * 　@更新日時：処理日時<BR>
         * <BR>
         * ４）　@更新処理<BR>
         * 　@３）の設定値で口座開設見込客テーブルの更新処理を行う。<BR>
         * <BR>
         * 　@更新内容は、DB更新仕様「口座開設見込客DB更新（口座開設日）仕様.xls」 参照。<BR>
         * <BR>
         * @@param l_hostAccOpenAcceptParams - (口座開設伝票登録受付キュー)<BR>
         * 口座開設伝票登録受付キュー<BR>
         * 口座開設見込客テーブルの口座登録日を更新する。<BR>
         * <BR>
         * [引数]<BR> 
         * 口座開設伝票登録受付キュー： 口座開設伝票登録受付キューParams<BR>
         * @@throws WEB3BaseException
         */
        protected void updateAccOpenOpenDate(
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "updateAccOpenOpenDate(HostAccOpenAcceptParams)";
            log.entering(STR_METHOD_NAME);

            //１）　@登録キューデータ取得
            //口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。
            //登録キューテーブル(*1).識別コード = 受付キュー.識別コード And
            //※登録キュー.識別コードは、伝票の識別コード（order_request_number）
            //登録キューテーブル(*1).データコード = 受付キュー.データコード And
            //登録キューテーブル(*1).証券会社コード = 受付キュー.証券会社コード And
            //登録キューテーブル(*1).部店コード = 受付キュー.部店コード And
            //登録キューテーブル(*1).顧客コード = 受付キュー.顧客コード
            try
            {
                String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();

                String[] l_acceptReqCodes =
                    new String[]{"GI82A", "GI84E"};
                String[] l_registReqCodes =
                    new String[]{"GI821", "GI845"};

                String l_strRegistReqCode = null;
                for (int i = 0; i < l_acceptReqCodes.length; i++)
                {
                    if (l_acceptReqCodes[i].equals(l_strRequestCode))
                    {
                        l_strRegistReqCode = l_registReqCodes[i];
                        break;
                    }
                }
                HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    l_hostAccOpenAcceptParams.getOrderRequestNumber(),
                    l_strRegistReqCode,
                    l_hostAccOpenAcceptParams.getInstitutionCode(),
                    l_hostAccOpenAcceptParams.getBranchCode(),
                    l_hostAccOpenAcceptParams.getAccountCode());

                //２）　@口座開設見込客データの取得
                //口座開設見込客テーブルを以下の条件で検索する。
                //証券会社コード = 口座開設伝票登録受付キュー.証券会社コード
                //識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）
                ExpAccountOpenRow l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk(
                    l_hostAccOpenAcceptParams.getInstitutionCode(),
                    l_hostAccRegVoucherRow.getAccOpenRequestNumber());

                //３）　@口座登録日の設定
                //２）で検索されたレコードに以下の値を設定する。
                //[設定値]
                ExpAccountOpenParams l_expAccountOpenParams = 
                    new ExpAccountOpenParams(l_expAccountOpenRow);
                //口座登録日：処理日時（YYYY/MM/DD 00:00:00）
                l_expAccountOpenParams.setAccountOpenDate(
                    WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
                //更新日時：処理日時
                l_expAccountOpenParams.setLastUpdatedTimestamp(
                    GtlUtils.getSystemTimestamp());

                //４）　@更新処理
                //３）の設定値で口座開設見込客テーブルの更新処理を行う。
                //更新内容は、DB更新仕様「口座開設見込客DB更新（口座開設日）仕様.xls」 参照。
                log.exiting(STR_METHOD_NAME);
                Processors.getDefaultProcessor().doUpdateQuery(l_expAccountOpenParams);
            }
            catch (DataFindException l_ex)
            {
                //取得できなかった場合は、後続処理は行わない。
                log.exiting(STR_METHOD_NAME);
                return;
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
        }


        /**
         * (update電子交付管理)<BR>
         * 電子交付管理テーブルを更新する。<BR>
         * <BR>
         * １）　@登録キューデータ取得<BR>
         * 　@口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。<BR>
         * <BR>
         * 　@[検索条件]<BR>
         * 　@登録キューテーブル(*1).識別コード = 受付キュー.識別コード And<BR>
         * 　@　@※登録キュー.識別コードは、伝票の識別コード（order_request_number）<BR>
         * 　@登録キューテーブル(*1).データコード = 受付キュー.データコード And<BR>
         * 　@登録キューテーブル(*1).証券会社コード = 受付キュー.証券会社コード And<BR>
         * 　@登録キューテーブル(*1).部店コード = 受付キュー.部店コード And<BR>
         * 　@登録キューテーブル(*1).顧客コード = 受付キュー.顧客コード<BR>
         * <BR>
         * 　@(*1) [データコードに対応する登録キューテーブル名]<BR>
         * 　@GI84C：取報・取残電子交付・特定口座登録受付　@→　@取報・取残電子交付・特定口座登録（GI311）キューテーブル<BR>
         * <BR>
         * 　@取得できなかった場合は、後続処理は行わない。<BR>
         * <BR>
         * ２）　@電子交付管理テーブルデータの取得<BR>
         * 　@口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。<BR>
         * <BR>
         * 　@[検索条件]<BR>
         * 　@電子交付管理テーブル.証券会社コード = 受付キュー.証券会社コード And<BR>
         * 　@電子交付管理テーブル.部店コード = 受付キュー.部店コード And<BR>
         * 　@電子交付管理テーブル.顧客コード = 受付キュー.顧客コード<BR>
         * <BR>
         * 　@取得できなかった場合は、後続処理は行わない。<BR>
         * <BR>
         * ３）　@電子交付管理テーブルの更新処理<BR>
         * <BR>
         * 　@２）で検索されたレコードの更新処理を行う。<BR>
         * <BR>
         * 　@更新内容は、DB更新仕様「電子交付管理テーブルＤＢ更新仕様.xls」 参照。<BR>
         * 　@※１）で検索されたレコードにより更新処理を行う。<BR>
         * <BR>
         * @@param l_hostAccOpenAcceptParams - (口座開設伝票登録受付キュー)<BR>
         * 口座開設伝票登録受付キュー<BR>
         * @@throws WEB3BaseException
         */
        protected void updateEleDeliveryManagement(
                HostAccOpenAcceptParams l_hostAccOpenAcceptParams) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "updateEleDeliveryManagement(HostAccOpenAcceptParams)";
            log.entering(STR_METHOD_NAME);

            try
            {
                //１）　@登録キューデータ取得
                //　@口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。
                //　@[検索条件]
                //　@登録キューテーブル(*1).識別コード = 受付キュー.識別コード And
                //　@　@※登録キュー.識別コードは、伝票の識別コード（order_request_number）
                //　@登録キューテーブル(*1).データコード = 受付キュー.データコード And
                //　@登録キューテーブル(*1).証券会社コード = 受付キュー.証券会社コード And
                //　@登録キューテーブル(*1).部店コード = 受付キュー.部店コード And
                //　@登録キューテーブル(*1).顧客コード = 受付キュー.顧客コード
                String l_strOrderRequestNumber = l_hostAccOpenAcceptParams.getOrderRequestNumber();
                String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();
                String l_strInstitutionCode = l_hostAccOpenAcceptParams.getInstitutionCode();
                String l_strBranchCode = l_hostAccOpenAcceptParams.getBranchCode();
                String l_strAccountCode = l_hostAccOpenAcceptParams.getAccountCode();

                String l_strRegistReqCode = null;

                if (WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(l_strRequestCode))
                {
                    l_strRegistReqCode = WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST;
                }

                //　@(*1) [データコードに対応する登録キューテーブル名]
                //　@GI84C：取報・取残電子交付・特定口座登録受付　@→　@取報・取残電子交付・特定口座登録（GI311）キューテーブル
                //　@取得できなかった場合は、後続処理は行わない。
                HostConditionRegVoucherRow l_hostConditionRegVoucherRow =
                    HostConditionRegVoucherDao.findRowByPk(
                        l_strOrderRequestNumber,
                        l_strRegistReqCode,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);

                //２）　@電子交付管理テーブルデータの取得
                //　@口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。
                //　@[検索条件]
                //　@電子交付管理テーブル.証券会社コード = 受付キュー.証券会社コード And
                //　@電子交付管理テーブル.部店コード = 受付キュー.部店コード And
                //　@電子交付管理テーブル.顧客コード = 受付キュー.顧客コード
                EleDeliveryManagementRow l_eleDeliveryManagementRow = null;

                l_eleDeliveryManagementRow =
                    EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);

                if (l_eleDeliveryManagementRow == null)
                {
                    //取得できなかった場合は、後続処理は行わない。
                    log.debug("取得できなかった場合は、後続処理は行わない。");
                    log.exiting(STR_METHOD_NAME);
                    return;
                }

                //３）　@電子交付管理テーブルの更新処理
                //　@２）で検索されたレコードの更新処理を行う
                //　@更新内容は、DB更新仕様「電子交付管理テーブルＤＢ更新仕様.xls」 参照。
                //　@※１）で検索されたレコードにより更新処理を行う。
                EleDeliveryManagementParams l_eleDeliveryManagementParams =
                    new EleDeliveryManagementParams(l_eleDeliveryManagementRow);

                //電子交付　@取引報告書
                String l_strTradingEReportDiv =
                    l_hostConditionRegVoucherRow.getTradingEReportDiv();

                //登録（GI311）キュー.電子交付　@取引報告書 !=null
                if (l_strTradingEReportDiv != null)
                {
                    //取引報告書申込区分
                    //9:SONAR送信済
                    l_eleDeliveryManagementParams.setTradingReportRegDiv(
                        WEB3ReportRegDivDef.SONAR_MAIL_SENDED);

                    //取引報告書交付区分更新日
                    //処理日時
                    l_eleDeliveryManagementParams.setTradingReportDivUpdDate(
                        GtlUtils.getSystemTimestamp());
                }

                //取引残高報告書　@電子交付（都度）
                String l_strPosReportCycleDiv =
                    l_hostConditionRegVoucherRow.getPosReportCycleDiv();

                //登録（GI311）キュー.取引残高報告書　@電子交付（都度） !=null
                if (l_strPosReportCycleDiv != null)
                {
                    //取引残高報告書申込区分
                    //9:SONAR送信済
                    l_eleDeliveryManagementParams.setPositionReportRegDiv(
                        WEB3ReportRegDivDef.SONAR_MAIL_SENDED);

                    //取引残高報告書交付区分更新日
                    //処理日時
                    l_eleDeliveryManagementParams.setPositionReportDivUpdDate(
                        GtlUtils.getSystemTimestamp());
                }

                //電子交付　@投信運用報告書
                String l_strInvEReportDiv =
                    l_hostConditionRegVoucherRow.getInvEReportDiv();

                //登録（GI311）キュー.電子交付　@投信運用報告書 !=null
                if (l_strInvEReportDiv != null)
                {
                    //運用報告書申込区分
                    //9:SONAR送信済
                    l_eleDeliveryManagementParams.setOpeReportRegDiv(
                        WEB3ReportRegDivDef.SONAR_MAIL_SENDED);

                    //運用報告書交付区分更新日
                    //処理日時
                    l_eleDeliveryManagementParams.setOpeReportDivUpdDate(
                        GtlUtils.getSystemTimestamp());
                }

                //更新者コード
                l_eleDeliveryManagementParams.setLastUpdater(
                    WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

                //更新日付
                l_eleDeliveryManagementParams.setLastUpdatedTimestamp(
                    GtlUtils.getSystemTimestamp());

                log.exiting(STR_METHOD_NAME);
                Processors.getDefaultProcessor().doUpdateQuery(
                    l_eleDeliveryManagementParams);
            }
            catch (DataFindException l_ex)
            {
                //取得できなかった場合は、後続処理は行わない。
                log.debug("取得できなかった場合は、後続処理は行わない。");
                log.exiting(STR_METHOD_NAME);
                return;
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
        }
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d2 1
a2 1
Copyright        : (株)大和総研 証券ソリューションシステム第二部
d4 1
a4 1
Author Name      : Daiwa Institute of Research
d31 1
d63 3
d83 1
d89 3
d377 18
d1176 207
@

