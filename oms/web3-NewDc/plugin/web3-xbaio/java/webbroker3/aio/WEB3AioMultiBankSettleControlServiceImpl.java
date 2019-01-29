head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMultiBankSettleControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : マルチバンク決済制御サービスImpl(WEB3AioMultiBankSettleControlServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
Revesion History : 2004/10/22 韋念瓊 (中訊) レビュー 
Revesion History : 2006/04/13 肖志偉 (中訊) 仕様変更・モデル527
Revesion History : 2006/04/26 WeiXin (中訊) 仕様変更・モデル542
Revesion History : 2006/05/07 佐藤（SCS）　@仕様変更・モデル551
Revesion History : 2007/06/19 柴双紅 (中訊)  仕様変更・モデル No.727
Revesion History : 2007/07/28 孟亜南 (中訊)  仕様変更・モデル No.740
*/
package webbroker3.aio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.BankOrderRequestParams;
import webbroker3.aio.data.BankOrderRequestRow;
import webbroker3.aio.data.BankSettleResultResponseParams;
import webbroker3.aio.data.BankSettleResultResponseRow;
import webbroker3.aio.data.BankSettleStartRequestParams;
import webbroker3.aio.data.BankSettleStartRequestRow;
import webbroker3.aio.data.CompBankCareerManagementRow;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.aio.data.CooperateBankMasterRow;
import webbroker3.aio.data.HostCashTransOrderAcceptRow;
import webbroker3.aio.define.WEB3AioTelegramFormatDef;
import webbroker3.aio.define.WEB3AioTelegramHttpRequestDef;
import webbroker3.aio.define.WEB3AioTelegramReturnCodeDef;
import webbroker3.aio.message.WEB3AioPrSessionUnit;
import webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit;
import webbroker3.aio.message.WEB3AioSettleInstitutionUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CareerDivDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3InputDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3ServiceDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (マルチバンク決済制御サービスImpl)<BR>
 * マルチバンク決済制御サービス実装クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AioMultiBankSettleControlServiceImpl
    implements WEB3AioMultiBankSettleControlService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioMultiBankSettleControlServiceImpl.class);

    /**
     * @@roseuid 415A48C80022
     */
    public WEB3AioMultiBankSettleControlServiceImpl()
    {

    }

    /**
     * (get選択決済機@関明細)<BR>
     * 該当する証券会社、部店で取扱っている決済機@関の一覧と受付状況を取得する。<BR> 
     * <BR>
     * シーケンス図<BR>
     * 「（オンライン入金）get選択決済機@関明細」  参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strCareerDiv - (キャリア区分)<BR>
     * 2 : モバイル以外 <BR>
     * 4 : i-mode <BR>
     * 5 : vodafone <BR>
     * 6 : au<BR>
     * @@return WEB3AioSelectSettleInstitutionUnit
     * @@throws WEB3BaseException<BR>
     * @@roseuid 40F20CC8032E
     */
    public WEB3AioSelectSettleInstitutionUnit[] getSelectPaySchemeDetails(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strCareerDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getSelectPaySchemeDetails("
                + "String l_strInstitutionCode, "
                + "String l_strBranchCode,"
                + "String l_strCareerDiv)";
        log.entering(STR_METHOD_NAME);

        List l_lisCompBankCareerManagement = null;
        try
        {
            // 1) QueryProcessorの取得
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //検索用
            String l_strWhere = "institution_code = ? and branch_code = ? and career_div = ? and mg_flg = ?";
            Object[] l_objWhere = { l_strInstitutionCode, l_strBranchCode, l_strCareerDiv, WEB3DealtDef.CAN_DEALT};

            // 2) 検索する
            //[引数] 
            //Rowタイプ： 会社別金融機@関キャリア取扱行オブジェクト.TYPE 
            //Where条件： ”insutitution_code=? and branch_code=?” and career_div=?” 
            //orderBy： ”pay_scheme_id” 
            //condition： null 
            //リスト： Where条件にセットする値のリスト(=[引数.証券会社コード, 引数.部店コード,引数.キャリア区分]) 
            l_lisCompBankCareerManagement =
                l_queryProcessor.doFindAllQuery(
                    CompBankCareerManagementRow.TYPE,
                    l_strWhere,
                    " pay_scheme_id ",
                    null,
                    l_objWhere);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 3) Listの生成
        List l_lisAioSelectSettleInstitutionUnit = new Vector();

        int l_intLength = 0;
        if (l_lisCompBankCareerManagement != null && l_lisCompBankCareerManagement.size() != 0)
        {
            l_intLength = l_lisCompBankCareerManagement.size();
        }

        // 4) Listの全部でloop
        for (int i = 0; i < l_intLength; i++)
        {
            CompBankCareerManagementRow l_compBankCareerManagementRow = 
                (CompBankCareerManagementRow) l_lisCompBankCareerManagement.get(i);

            // 4 - 1) 選択決済機@関明細インスタンスを生成する
            WEB3AioSelectSettleInstitutionUnit l_aioSelectSettleInstitutionUnit =
                new WEB3AioSelectSettleInstitutionUnit();
            WEB3AioSettleInstitution l_aioSettleInstitution = null;

            // 4 - 2)提携決済機@関インスタンスを生成する
            l_aioSettleInstitution =
                 new WEB3AioSettleInstitution(
                         l_compBankCareerManagementRow.getPaySchemeId());

            // 4 - 3)決済機@関が受付時間内かどうかをチェックする
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_orderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            boolean l_blnFlag = false;

            l_blnFlag =
                l_orderManager.validatePaySchemeAcceptPossible(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_compBankCareerManagementRow.getPaySchemeId());

            // 4 - 4) 提携決済機@関明細を生成し、追加する
            l_aioSelectSettleInstitutionUnit.paySchemeId =
                l_compBankCareerManagementRow.getPaySchemeId();
            l_aioSelectSettleInstitutionUnit.paySchemeName =
                l_aioSettleInstitution.getName();
            if (l_blnFlag)
            {
                l_aioSelectSettleInstitutionUnit.receptionStatus = WEB3ServiceDivDef.INT_ACCEPT;
            }
            else
            {
                l_aioSelectSettleInstitutionUnit.receptionStatus = WEB3ServiceDivDef.STOPPING;
            }
            l_lisAioSelectSettleInstitutionUnit.add(
                l_aioSelectSettleInstitutionUnit);
        }

        WEB3AioSelectSettleInstitutionUnit[] l_arrayAioSelectSettleInstitutionUnit =
            null;

        // toArray()
        l_arrayAioSelectSettleInstitutionUnit =
            new WEB3AioSelectSettleInstitutionUnit[l_lisAioSelectSettleInstitutionUnit.size()];
        l_lisAioSelectSettleInstitutionUnit.toArray(
            l_arrayAioSelectSettleInstitutionUnit);

        log.exiting(STR_METHOD_NAME);
        return l_arrayAioSelectSettleInstitutionUnit;
    }

    /**
     * (get提携決済機@関明細)<BR>
     * 該当する証券会社取扱っている決済機@関の一覧を取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（オンライン入金）get提携決済機@関明細」  参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@return WEB3AioSettleInstitutionUnit
     * @@throws WEB3BaseException<BR>
     * @@roseuid 410A11B70137
     */
    public WEB3AioSettleInstitutionUnit[] getAffiliatedPaySchemeDetails(String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getAffiliatedPaySchemeDetails(" 
                + "String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisCompBankCondition = null;
        try
        {
            // 1) QueryProcessorの取得
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //検索用
            String l_strWhere = " institution_code = ? ";
            Object[] l_objWhere = { l_strInstitutionCode };

            // 2) 検索する
            l_lisCompBankCondition =
                l_queryProcessor.doFindAllQuery(
                    CompBankConditionRow.TYPE,
                    l_strWhere,
                    l_objWhere);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 3) Listの生成
        List l_lisAioSettleInstitutionUnit = new Vector();

        int l_intLength = 0;
        if (l_lisCompBankCondition != null && l_lisCompBankCondition.size() != 0)
        {
            l_intLength = l_lisCompBankCondition.size();
        }

        // 4) Listの全部でloop
        for (int i = 0; i < l_intLength; i++)
        {
            CompBankConditionRow l_compBankConditionRow = 
                (CompBankConditionRow) l_lisCompBankCondition.get(i);

            // 4 - 1) 提携決済機@関明細インスタンスを生成する
            WEB3AioSettleInstitutionUnit l_aioSettleInstitutionUnit =
                new WEB3AioSettleInstitutionUnit();
            WEB3AioSettleInstitution l_aioSettleInstitution = null;

            // 4 - 2)提携決済機@関インスタンスを生成する
            l_aioSettleInstitution =
                new WEB3AioSettleInstitution(
                    l_compBankConditionRow.getPaySchemeId());

            // 4 - 3)提携決済機@関明細は　@もう存在するかどうか判断する
            
            l_aioSettleInstitutionUnit.paySchemeId =
                    l_compBankConditionRow.getPaySchemeId();
            l_aioSettleInstitutionUnit.paySchemeName =
                l_aioSettleInstitution.getName();

            if(!this.contain(
                l_lisAioSettleInstitutionUnit, l_aioSettleInstitutionUnit))
            {
                l_lisAioSettleInstitutionUnit.add(l_aioSettleInstitutionUnit);    
            }
        }

        WEB3AioSettleInstitutionUnit[] l_arrayAioSettleInstitutionUnit = null;

        // toArray()
        l_arrayAioSettleInstitutionUnit =
            new WEB3AioSettleInstitutionUnit[l_lisAioSettleInstitutionUnit.size()];
        l_lisAioSettleInstitutionUnit.toArray(
            l_arrayAioSettleInstitutionUnit);

        log.exiting(STR_METHOD_NAME);
        return l_arrayAioSettleInstitutionUnit;
    }

    /**
     * (get入出金状況)<BR>
     * オンライン入出金の状況を取得する。<BR>
     * <BR>
     * １）証券会社コードを取得する。<BR>
     *    引数.補助口座.getInstitution().getInstitutionCode()<BR>
     * <BR>
     * ２）部店コードを取得する。<BR>
     *    引数.補助口座.get取引店().getBranchCode()<BR>
     * <BR>
     * ３）顧客コードを取得する。<BR>
     *    引数.補助口座.getMainAccout().getAccountCode()<BR>
     * <BR>
     * ４）金融機@関連携入出金状況インスタンスを生成する。<BR>
     *    金融機@関連携入出金状況(証券会社コード, 部店コード, 識別コード)<BR>
     * <BR>
     *    [引数]<BR>
     *    証券会社コード： １）で取得したコード<BR>
     *    部店コード： ２）で取得したコード<BR>
     *    識別コード： 引数.識別コード<BR>
     * <BR>
     * ５）入出金伝票受付キューテーブルから、以下の条件のデータを取得する。<BR>
     * <BR>
     *    [条件]<BR>
     *    データコード = "GI80C"<BR>
     *    証券会社コード = [１）で取得したコード]<BR>
     *    部店コード = [２）で取得したコード]<BR>
     *    顧客コード = [２）で取得したコード]<BR>
     *    識別コード = 引数.識別コード<BR>
     * <BR>
     * ４）金融機@関連携入出金状況が持つフラグと入出金伝票受付キューが<BR>
     * 持つフラグと引数の注文状態から<BR>
     *    現在の入出金状況を判定し、その結果を返却する。<BR>
     * <BR>
     *    詳細は、「入出金ステータス構成表.xls」 参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * @@param l_orderStatus - (注文状態)<BR>
     * @@param l_strOrderCancleStatus - (注文取消区分)<BR>
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 410600780252
     */
    public String getCashTransSituation(
        SubAccount l_subAccount,
        String l_strOrderRequestNumber,
        OrderStatusEnum l_orderStatus,
        String l_strOrderCancleStatus) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getCashTransSituation("
                + "SubAccount l_subAccount, "
                + "String l_strOrderRequestNumber, "
                + "OrderTypeEnum l_orderStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）証券会社コードを取得する
        //    引数.補助口座.getInstitution().getInstitutionCode()
        String l_strInstitutionCode =
            l_subAccount.getInstitution().getInstitutionCode();

        // ２）部店コードを取得する
        //    引数.補助口座.get取引店().getBranchCode()
        String l_strBranchCode =
            l_subAccount.getMainAccount().getBranch().getBranchCode();

        // ３）顧客コードを取得する
        //    引数.補助口座.getMainAccout().getAccountCode()
        String l_strAccountCode =
            l_subAccount.getMainAccount().getAccountCode();

        // ４）金融機@関連携入出金状況インスタンスを生成する
        //    金融機@関連携入出金状況(証券会社コード, 部店コード, 識別コード)
        WEB3AioFinInstitutionCashTransStatus l_aioFinInstitutionCashTransStatus = null;

        l_aioFinInstitutionCashTransStatus =
            new WEB3AioFinInstitutionCashTransStatus(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber);
        
        BankCashTransferStatusRow l_bankCashTransferStatusRow = 
            (BankCashTransferStatusRow) l_aioFinInstitutionCashTransStatus.getDataSourceObject();        

        // ５）入出金伝票受付キューテーブルから、以下の条件のデータを取得する
        //    [条件]
        //    データコード = "GI80C"
        //    証券会社コード = [１）で取得したコード]
        //    部店コード = [２）で取得したコード]
        //    顧客コード = [２）で取得したコード]
        //    識別コード = 引数.識別コード
        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_whereClause =
                " request_code = ? "
                    + "and institution_code = ? "
                    + "and branch_code = ? "
                    + "and account_code = ? "
                    + "and order_request_number = ? ";
            Object[] l_bindVars =
                {
                    WEB3HostRequestCodeDef.AIO_SLIP_ACCEPT,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strOrderRequestNumber };

            l_lisRows =
                l_queryProcessor.doFindAllQuery(
                    HostCashTransOrderAcceptRow.TYPE,
                    l_whereClause,
                    l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(
                " DBへのアクセスに失敗しました when search table HostCashTransOrderAccept",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(
                " DBへのアクセスに失敗しました when search table HostCashTransOrderAccept",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }

        HostCashTransOrderAcceptRow l_hostCashTransOrderAcceptRow = null;
        if (l_lisRows != null && l_lisRows.size() != 0)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            l_hostCashTransOrderAcceptRow = 
                (HostCashTransOrderAcceptRow) l_lisRows.get(0);
        }
        
        String l_strKeyTableStatus = null;
        String l_strTransactionStatus = l_bankCashTransferStatusRow.getTransactionStatus();
        String l_strOrderStatusFlag = l_bankCashTransferStatusRow.getOrderStatusFlag();
        String l_strStartStatusFlg = l_bankCashTransferStatusRow.getStartStatusFlag();
        String l_strResultStatusFlag = l_bankCashTransferStatusRow.getResultStatusFlag();
        
        if (l_hostCashTransOrderAcceptRow != null)
        {
            l_strKeyTableStatus = l_hostCashTransOrderAcceptRow.getStatus();
        }

        // ４）金融機@関連携入出金状況が持つフラグと入出金伝票受付キューが
        // 持つフラグと引数の注文状態から
        //    現在の入出金状況を判定し、その結果を返却する。
        log.debug("l_strTransactionStatus = " + l_strTransactionStatus);
        log.debug("l_strOrderStatusFlag = " + l_strOrderStatusFlag);
        log.debug("l_strStartStatusFlg = " + l_strStartStatusFlg);
        log.debug("l_strResultStatusFlag = " + l_strResultStatusFlag);
        log.debug("l_orderStatus = " + l_orderStatus);
        log.debug("l_strOrderCancleStatus = " + l_strOrderCancleStatus);
        log.debug("l_strKeyTableStatus = " + l_strKeyTableStatus);
        
        WEB3AioMutilBankStatusUtility l_statusUtility = new WEB3AioMutilBankStatusUtility();
        String l_strCashTransSituation =
            l_statusUtility.getStatus(
                l_strTransactionStatus,
                l_strOrderStatusFlag,
                l_strStartStatusFlg,
                l_strResultStatusFlag,
                (l_orderStatus == null) ? null : l_orderStatus.intValue() + "",
                l_strOrderCancleStatus,
                l_strKeyTableStatus);

        log.exiting(STR_METHOD_NAME);
        return l_strCashTransSituation;
    }

    /**
     * (get金融機@関名)<BR>
     * オンライン入金の決済機@関の名称を取得する。<BR>
     * <BR>
     * １）証券会社コードを取得する。<BR>
     *    引数.補助口座.getInstitution().getInstitutionCode()<BR>
     * <BR>
     * ２）部店コードを取得する。<BR>
     *    引数.補助口座.get取引店().getBranchCode()<BR>
     * <BR>
     * ３）金融機@関連携入出金状況インスタンスを生成する。<BR>
     *    金融機@関連携入出金状況(証券会社コード, 部店コード, 識別コード)<BR>
     * <BR>
     *    [引数]<BR>
     *    証券会社コード： １）で取得したコード<BR>
     *    部店コード： ２）で取得したコード<BR>
     *    識別コード： 引数.識別コード<BR>
     * <BR>
     * ４）提携決済機@関インスタンスを生成する。<BR>
     *    提携決済機@関（決済機@関ID）<BR>
     * <BR>
     *    [引数]<BR>
     *    決済機@関ID： ３）で取得した金融機@関連携入出金状況.決済機@関ID<BR>
     * <BR>
     * ５）提携決済機@関.略称を返却する。 <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strOrderRequestNumber - (識別コード)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 410602C6039A
     */
    public String getFinInstitutionName(
        SubAccount l_subAccount,
        String l_strOrderRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getFinInstitutionName("
                + "SubAccount l_subAccount, "
                + "String l_strOrderRequestNumber)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）証券会社コードを取得する
        //    引数.補助口座.getInstitution().getInstitutionCode()
        String l_strInstitutionCode =
            l_subAccount.getInstitution().getInstitutionCode();

        // ２）部店コードを取得する
        //    引数.補助口座.get取引店().getBranchCode()
        String l_strBranchCode =
            l_subAccount.getMainAccount().getBranch().getBranchCode();

        // ３）金融機@関連携入出金状況インスタンスを生成する
        //    金融機@関連携入出金状況(証券会社コード, 部店コード, 識別コード)
        WEB3AioFinInstitutionCashTransStatus l_aioFinInstitutionCashTransStatus = null;

        l_aioFinInstitutionCashTransStatus =
            new WEB3AioFinInstitutionCashTransStatus(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber);

        // ４）提携決済機@関インスタンスを生成する
        //    提携決済機@関（決済機@関ID）
        WEB3AioSettleInstitution l_aioSettleInstitution = null;

        l_aioSettleInstitution = new WEB3AioSettleInstitution(
            ((BankCashTransferStatusRow)
                 l_aioFinInstitutionCashTransStatus.getDataSourceObject()).getPaySchemeId());

        // ５）提携決済機@関.略称を返却する。         
        CooperateBankMasterRow l_cooperateBandMasterRow = 
            (CooperateBankMasterRow)l_aioSettleInstitution.getDataSourceObject();
            
        String l_strFinInstitutionName = l_cooperateBandMasterRow.getShortName();
            
        log.exiting(STR_METHOD_NAME);
        return l_strFinInstitutionName;
    }

    /**
     * (create決済依頼URL)<BR>
     * 決済依頼の際に返却するリダイレクトURLの文字列を生成する。<BR>
     * <BR>
     * １）加盟店IDの取得<BR>
     * <BR>
     *　@１－１）セッション情報.注文経路区分を取得 <BR>
     *<BR>
     *　@１－２）this.getキャリア区分（注文経路区分）で、キャリア区分を取得する。<BR> 
     *<BR>
     *　@１－３）キャリア別加盟店IDインスタンスを生成する。<BR> 
     * <BR>
     *    ［コンストラクタの引数］<BR>
     *    証券会社コード = 補助口座.getInstitution().getInsutitutionCode()<BR>
     *    部店コード = 補助口座.get取引店().getBranchCode()<BR>
     *   キャリア区分 = getキャリア区分（）で取得した値<BR> 
     *<BR>
     *　@１－４）加盟店IDを取得する。<BR> 
     *　@　@キャリア別加盟店ID.get加盟店ID() <BR>
     * <BR>
     * ２）URL文字列の生成<BR>
     * ２－１）パラメータ文字列の生成<BR> 
     *  <BR> 
     *  protocolVersion='V1.0'<BR> 
     *  linked_1=補助口座.getInstitution().getInsutitutionCode() <BR>
     *   + 補助口座.get取引店().getBranchCode() + 引数.識別コード 
     *  shopId=［１）で取得した加盟店ID］<BR> 
     *  date=スレッドから取得した現在時刻 <BR>
     * cancelURL=[URL]?io_rturl=2&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]<BR>
     * errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]<BR>
     * pSId=引数.決済機@関ID <BR>
     * prsid=[*1] <BR>
     * praid=[*2] <BR>
     * praarsid=[*3] <BR>
     * prssid=[*4] <BR>
     * prdpdv=[*5] <BR>
     * apsid=[*6] <BR>
     * cpy=[*7] <BR>
     * btn=[*8] <BR>
     * acc=[*9] <BR>
     * req=[*10]  <BR>
     * rdiv=[*11] <BR>
     * web3Request=OrderDemand <BR>
     * <BR>
     * ※1 URL： キャリア別加盟店ID.getリターンURL()で取得した値<BR>
     * <BR>
     * *1： PR層保持情報.セッションキー <BR>
     * *2： PR層保持情報.アプリケーションID <BR>
     * *3： PR層保持情報.再生成サービスID <BR>
     * *4： PR層保持情報.SSID <BR>
     * *5： PR層保持情報.表示区分 <BR>
     * *6： AP層セッションID <BR>
     * *7： 証券会社コード（ 補助口座.getInstitution().getInsutitutionCode() ）<BR> 
     * *8： 部店コード（ 補助口座.get取引店().getBranchCode() ） <BR>
     * *9： 顧客コード（ 補助口座.getMainAccount().getAccountCode() ） <BR>
     * *10： 識別コード（ 引数.識別コード ） <BR>
     * *11：注文経路区分（セッション情報.注文経路区分 ）<BR>
     * <BR>
     * ２－２）URL文字列の生成<BR> 
     * <BR>
     * ［決済PFのURL］?［２－１）で生成したパラメータ文字列］<BR>
     * <BR>
     * ※決済PFのURL： <BR>
     *     キャリア別加盟店ID.get決済URL()で取得した値 
     * <BR>
     * @@param  l_prSessionUnit - (PR層保持情報)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strPaySchemeId - (決済機@関ID)<BR>
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4116E7350335
     */
    public String createSettlementRequestURL(
        WEB3AioPrSessionUnit l_prSessionUnit,
        SubAccount l_subAccount,
        String l_strPaySchemeId,
        String l_strOrderRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "createSettlementRequestURL("
                + "String l_strPRSessionId, "
                + "SubAccount l_subAccount, "
                + "String l_strPaySchemeId, "
                + "String l_strOrderRequestNumber)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）加盟店IDの取得
        WEB3AioCareerShopId l_aioCareerShopId = null;

        //１－１）セッション情報.注文経路区分を取得
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv = l_opLoginSecurityService.getSessionProperty(
            WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        
        //１－２）this.getキャリア区分（注文経路区分）で、キャリア区分を取得する。
        String l_strCareerDiv = getCareerDiv(l_strOrderRootDiv);
        
        //１－３）キャリア別加盟店IDインスタンスを生成する。        
        try
        {
            l_aioCareerShopId = new WEB3AioCareerShopId(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_strCareerDiv);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("Error In  createSettlementRequestURL()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // １－４）加盟店IDを取得する
        String l_strShopId =
            l_aioCareerShopId.getShopId();

        //=========remain zhou-yong NO.1 begin ==========
        
        // ２）URL文字列の生成     
        
        //※1 URL： キャリア別加盟店ID.getリターンURL()で取得した値 
        String l_strURL_WEB3 = l_aioCareerShopId.getReturnURL();

        //*1： PR層保持情報.セッションキー
        String l_strwolfSession = l_prSessionUnit.wolfSession;
        
        //*2： PR層保持情報.アプリケーションID 
        String l_strwolfAid = l_prSessionUnit.wolfAid;
        
        //*3： PR層保持情報.再生成サービスID 
        String l_strregetServiceId = l_prSessionUnit.regetServiceId;
        
        //*4： PR層保持情報.SSID
        String l_strWolfSsid = l_prSessionUnit.wolfSsid;
        
        //*5： PR層保持情報.表示区分
        String l_strDisplayDiv = l_prSessionUnit.displayDiv;
        
        //*6： AP層セッションID 
        //APSessionIDの取得
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        String l_strAPSessionID =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.SESSION_ID);
        
        //*7： 証券会社コード（ 補助口座.getInstitution().getInsutitutionCode() ）
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        
        //*8： 部店コード（ 補助口座.get取引店().getBranchCode() ）
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //*9： 顧客コード（ 補助口座.getMainAccount().getAccountCode() ） 
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        
        //*10： 識別コード（ 引数.識別コード ）         
        //*11：注文経路区分（セッション情報.注文経路区分 ）
        
        StringBuffer l_sbURL = new StringBuffer();

        // ２－１）パラメータ文字列の生成
        // protocolVersion=V1.0
        l_sbURL.append(WEB3AioTelegramFormatDef.protocolVersion).append("=")
            .append(WEB3AioTelegramHttpRequestDef.V1DOT0).append("&");

        // linked_1=補助口座.getInstitution().getInsutitutionCode() + 
        // 補助口座.get取引店().getBranchCode() + 引数.識別コード
        l_sbURL.append(WEB3AioTelegramFormatDef.linked_1).append("=");

        l_sbURL.append(l_subAccount.getInstitution().getInstitutionCode());

        l_sbURL.append(l_subAccount.getMainAccount().getBranch().getBranchCode());

        l_sbURL.append(l_strOrderRequestNumber).append("&");        

        // shopId=［１）で取得した加盟店ID］
        l_sbURL.append(WEB3AioTelegramFormatDef.shopId)
            .append("=").append(l_strShopId).append("&");

        // date=スレッドから取得した現在時刻
        l_sbURL.append(WEB3AioTelegramFormatDef.date).append("=") 
            .append(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHHmmss")) 
            .append("&");

        //cancelURL=[URL]?io_rturl=2&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]  
        l_sbURL.append(WEB3AioTelegramFormatDef.cancelURL).append("=");
        StringBuffer l_sbCancelURL = new StringBuffer();
        l_sbCancelURL.append(l_strURL_WEB3);
//            .append("?").append(WEB3AioTelegramFormatDef.io_rturl).append("=")
//            .append(WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS2).append("&") 
//            .append(WEB3AioTelegramFormatDef.wolfSessionKey).append("=").append(l_strwolfSession).append("&")
//            .append(WEB3AioTelegramFormatDef.aa_aid).append("=").append(l_strwolfAid).append("&")
//            .append(WEB3AioTelegramFormatDef.aa_rsid).append("=").append(l_strregetServiceId).append("&")
//            .append(WEB3AioTelegramFormatDef.ssid).append("=").append(l_strWolfSsid).append("&")
//            .append(WEB3AioTelegramFormatDef.aa_dpdv).append("=").append(l_strDisplayDiv).append("&")
//            .append(WEB3AioTelegramFormatDef.apsid).append("=").append(l_strAPSessionID).append("&")
//            .append(WEB3AioTelegramFormatDef.cpy).append("=").append(l_strInstitutionCode).append("&")
//            .append(WEB3AioTelegramFormatDef.btn).append("=").append(l_strBranchCode).append("&")
//            .append(WEB3AioTelegramFormatDef.acc).append("=").append(l_strAccountCode).append("&")
//            .append(WEB3AioTelegramFormatDef.req).append("=").append(l_strOrderRequestNumber);
        l_sbURL.append(URLEncoder.encode(l_sbCancelURL.toString())).append("&");

        //errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10] 
        l_sbURL.append(WEB3AioTelegramFormatDef.errorURL).append("=");
        StringBuffer l_sbErrorURL = new StringBuffer();
        l_sbErrorURL.append(l_strURL_WEB3).append("?")
            .append(WEB3AioTelegramFormatDef.io_rturl).append("=")
            .append(WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS1).append("&")
            .append(WEB3AioTelegramFormatDef.wolfSessionKey).append("=").append(l_strwolfSession).append("&")
            .append(WEB3AioTelegramFormatDef.aa_aid).append("=").append(l_strwolfAid).append("&")
            .append(WEB3AioTelegramFormatDef.aa_rsid).append("=").append(l_strregetServiceId).append("&")
            .append(WEB3AioTelegramFormatDef.ssid).append("=").append(l_strWolfSsid).append("&")
            .append(WEB3AioTelegramFormatDef.aa_dpdv).append("=").append(l_strDisplayDiv).append("&")
            .append(WEB3AioTelegramFormatDef.apsid).append("=").append(l_strAPSessionID).append("&")
            .append(WEB3AioTelegramFormatDef.cpy).append("=").append(l_strInstitutionCode).append("&")
            .append(WEB3AioTelegramFormatDef.btn).append("=").append(l_strBranchCode).append("&")
            .append(WEB3AioTelegramFormatDef.acc).append("=").append(l_strAccountCode).append("&")
            .append(WEB3AioTelegramFormatDef.req).append("=").append(l_strOrderRequestNumber);
        l_sbURL.append(URLEncoder.encode(l_sbErrorURL.toString())).append("&");

        //pSId=引数.決済機@関ID 
        l_sbURL.append(WEB3AioTelegramFormatDef.pSId).append("=").append(l_strPaySchemeId).append("&");

        //prsid=[*1] 
        l_sbURL.append(WEB3AioTelegramFormatDef.prsid).append("=").append(l_strwolfSession).append("&");

        //praid=[*2] 
        l_sbURL.append(WEB3AioTelegramFormatDef.praid).append("=").append(l_strwolfAid).append("&");

        //praarsid=[*3]
        l_sbURL.append(WEB3AioTelegramFormatDef.praarsid).append("=").append(l_strregetServiceId).append("&");

        //prssid=[*4] 
        l_sbURL.append(WEB3AioTelegramFormatDef.prssid).append("=").append(l_strWolfSsid).append("&");

        //prdpdv=[*5] 
        l_sbURL.append(WEB3AioTelegramFormatDef.prdpdv).append("=").append(l_strDisplayDiv).append("&");
        
        //apsid=[*6] 
        l_sbURL.append(WEB3AioTelegramFormatDef.apsid).append("=").append(l_strAPSessionID).append("&");

        //cpy=[*7] 
        l_sbURL.append(WEB3AioTelegramFormatDef.cpy).append("=").append(l_strInstitutionCode).append("&");

        //btn=[*8]
        l_sbURL.append(WEB3AioTelegramFormatDef.btn).append("=").append(l_strBranchCode).append("&");

        //acc=[*9] 
        l_sbURL.append(WEB3AioTelegramFormatDef.acc).append("=").append(l_strAccountCode).append("&");

        //req=[*10] 
        l_sbURL.append(WEB3AioTelegramFormatDef.req).append("=").append(l_strOrderRequestNumber).append("&");
        
        //rdiv=[*11]
        l_sbURL.append(WEB3AioTelegramFormatDef.rdiv).append("=").append(l_strOrderRootDiv).append("&");

        //=========remain zhou-yong NO.1 end ==========

        // web3Request=OrderDemand
        l_sbURL.append(WEB3AioTelegramFormatDef.web3Request).append("=")
            .append(WEB3AioTelegramHttpRequestDef.ORDERDEMAND);

        // ２－２）URL文字列の生成
        //    ［決済PFのURL］?［２－１）で生成したパラメータ文字列］
        //※決済PFのURL： 
        //キャリア別加盟店ID.get決済URL()で取得した値 
        String l_strPfUrl = l_aioCareerShopId.getPfURL();
        
        String l_strReturnURL = l_strPfUrl + "?" + l_sbURL.toString();
        
        log.exiting(STR_METHOD_NAME);
        return l_strReturnURL;
    }

    /**
     * (insert入出金状況)<BR>
     * 決済依頼のステータスでの金融機@関連携入出金状況テーブルのレコードを追加する。<BR>
     * <BR>
     * １）金融機@関連携入出金状況Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）パラメータをセットする。<BR>
     * <BR>
     *   詳細は、DB更新仕様「決済依頼_金融機@関連携入出金状況テーブル.xls」<BR>参照<BR>
     * <BR>
     * ３）WEB3DataAccessUtility.insertRow()にて、レコードを追加する。<BR>
     * <BR>
     *   ［引数］<BR>
     *   金融機@関連携入出金状況Paramsインスタンス<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者オブジェクト
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strPaySchemeId - (決済機@関ID)
     * @@param l_strNetAmount - (金額)<BR>
     * 入金金額<BR>
     * @@param l_datBizDate - (発注日)
     * @@param l_strOrderRequestNumber - (識別コード)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 40EA4347019F
     */
    public void insertCashTransSituation(
        Trader l_trader,
        SubAccount l_subAccount,
        String l_strPaySchemeId,
        String l_strNetAmount,
        Date l_datBizDate,
        String l_strOrderRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertCashTransSituation("
                + "Trader l_trader, "
                + "SubAccount l_subAccount, "
                + "String l_strPaySchemeId, "
                + "String l_strNetAmount, "
                + "Date l_datBizDate, "
                + "String l_strOrderRequestNumber)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_datBizDate == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）金融機@関連携入出金状況Paramsインスタンスを生成する
        BankCashTransferStatusParams l_bankCashTransferStatusParams =
            new BankCashTransferStatusParams();

        // ２）パラメータをセットする
        //   詳細は、DB更新仕様「決済依頼_金融機@関連携入出金状況テーブル.xls」参照
        l_bankCashTransferStatusParams.setInstitutionCode(
            l_subAccount.getInstitution().getInstitutionCode());
        l_bankCashTransferStatusParams.setBranchCode(
            l_subAccount.getMainAccount().getBranch().getBranchCode());
        l_bankCashTransferStatusParams.setAccountCode(
            l_subAccount.getMainAccount().getAccountCode());
        l_bankCashTransferStatusParams.setOrderRequestNumber(
            l_strOrderRequestNumber);
        l_bankCashTransferStatusParams.setPaySchemeId(l_strPaySchemeId);
        l_bankCashTransferStatusParams.setAmount(new Long(l_strNetAmount));
        l_bankCashTransferStatusParams.setOrderDateTime(
            GtlUtils.getSystemTimestamp());
        l_bankCashTransferStatusParams.setDeliveryScheduledDate(
            new Timestamp(l_datBizDate.getTime()));
        l_bankCashTransferStatusParams.setBaseDate(
            new Timestamp(l_datBizDate.getTime()));
            
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        String l_strInputDiv = null;        
        
        if(WEB3OrderRootDivDef.CALLCENTER.equals(l_strOrderRootDiv))
        {
            l_strInputDiv = WEB3InputDivDef.CALLCENTER;
        }
        else
        {
            if(WEB3OrderRootDivDef.PC.equals(l_strOrderRootDiv))
            {
                l_strInputDiv = WEB3InputDivDef.PC;
            }
            else
            {
                if(WEB3OrderRootDivDef.SLINGSHOT.equals(l_strOrderRootDiv))
                {
                    l_strInputDiv = WEB3InputDivDef.SLINGSHOT;
                }
                else
                {
                    if(WEB3OrderRootDivDef.I_MODE.equals(l_strOrderRootDiv))
                    {
                        l_strInputDiv = WEB3InputDivDef.I_MODE;
                    }
                    else
                    {
                        if(WEB3OrderRootDivDef.VODAFONE.equals(l_strOrderRootDiv))
                        {
                            l_strInputDiv = WEB3InputDivDef.J_PHONE;
                        }
                        else
                        {
                            if(WEB3OrderRootDivDef.AU.equals(l_strOrderRootDiv))
                            {
                                l_strInputDiv = WEB3InputDivDef.EZ_WEB;
                            }
                            else
                            {
                                if(WEB3OrderRootDivDef.HOST.equals(l_strOrderRootDiv))
                                {
                                    l_strInputDiv = WEB3InputDivDef.HOST;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        l_bankCashTransferStatusParams.setInputDiv(l_strInputDiv);
        l_bankCashTransferStatusParams.setComondebiCaptureDate(null);
        l_bankCashTransferStatusParams.setCenterPayId(null);
        l_bankCashTransferStatusParams.setOrderStatusFlag(WEB3OrderStatusFlagDef.NOT_DEAL);
        l_bankCashTransferStatusParams.setOrderRequestTime(null);
        l_bankCashTransferStatusParams.setOrderResponseTime(null);
        l_bankCashTransferStatusParams.setStartStatusFlag(WEB3StartStatusFlgDef.NOT_DEAL);
        l_bankCashTransferStatusParams.setStartRequestTime(null);
        l_bankCashTransferStatusParams.setStartResponseTime(null);
        l_bankCashTransferStatusParams.setResultStatusFlag(WEB3ResultStatusFlagDef.NOT_DEAL);
        l_bankCashTransferStatusParams.setResultRequestTime(null);
        l_bankCashTransferStatusParams.setResultResponseTime(null);
        l_bankCashTransferStatusParams.setTransactionStatus(WEB3TransactionStatusDef.NOT_DEAL);
        l_bankCashTransferStatusParams.setTransactionTime(null);
        l_bankCashTransferStatusParams.setBatchFlag(null);
        l_bankCashTransferStatusParams.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());
            
        String l_strLoginID = l_opLoginSec.getLoginId() + "";
        l_bankCashTransferStatusParams.setLastUpdateUser(l_strLoginID);

        // ３）WEB3DataAccessUtility.insertRow()にて、レコードを追加する
        try
        {
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.insertRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (validate受信電文)<BR>
     * 受信電文データのチェックを行う。<BR>
     * <BR>
     * １）電文発行元のリモートアドレスチェック<BR>
     * <BR>
     * １－１）システム内に保持するマルチバンクのリモートアドレスを取得する。 <BR>
     * <BR>
     * this.getプリファ@レンス()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.受信電文データ.get("cpy")の戻り値 <BR>
     * 設定名称： ”_REMOTE_ADDRESS” <BR>
     * <BR>
     * １－２）引数.受信電文データ.get("remoteAdd")の戻り値と１－１）で取得した値が一致しない場合、 <BR>
     * "ERROR"を返す。 <BR>
     * <BR>
     * ２）セッションIDチェック<BR>
     * <BR>
     *   引数.受信電文データ.get(WEB3AioTelegramFormatDef.apsid)の戻り値をIDとするセッションの<BR>
     * 存在チェックを行う。<BR>
     *   存在しない場合、すでにセッションが終了している場合は、<BR>
     * "ERROR"を返す。
     * <BR>
     * ２－１）LoginSessionテーブルから、該当セッションのレコードを取得する。<BR>
     * <BR>
     *      LoginSessionDao.findRowByPk（l_session_id)<BR>
     * <BR>
     *      [引数］<BR>
     *      l_session_id： 引数.受信電文データ.get(WEB3AioTelegramFormatDef.apsid)の戻り値<BR>
     * <BR>
     * ２－２）レコードが取得できなかった or <BR>
     * LoginSessionParams.getExpairationDateIsSet() = true の場合は、<BR>
     * "ERROR"を返す。<BR>
     * <BR>
     * ３）タイムアウトチェック <BR>
     * <BR>
     * システムプリファ@レンステーブルのレコードが存在しない場合は"ERROR"を返す。<BR> 
     * システムタイムスタンプから金融機@関連携入出金状況テーブル.更新日付比較し <BR>
     * タイムアウト値を超えている場合は、"ERROR"を返す。 <BR>
     * <BR>
     * ３－１）金融機@関連携入出金状況テーブルより更新日時を取得 <BR>
     * <BR>
     * ３－２）システムプリファ@レンステーブルより、タイムアウト値を取得する。 <BR>
     * <BR>
     * this.getプリファ@レンス()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.受信電文データ.get("cpy")の戻り値 <BR>
     * 設定名称： ”_AIO_TIMEOUT” <BR>
     * <BR>
     * ３－３）システムプリファ@レンスよりレコードが取得出来なかった or <BR>
     * システムタイムスタンプ － 金融機@関入出金状況テーブル.更新日付 > <BR>v
     *      XX_AIO_TIOMEOUT の場合は、"ERROR"を返す。<BR> 
     * <BR>
     * ※ XX_AIO_TIOMEOUTは秒単位で設定。 <BR>
     * システムタイムスタンプ － 金融機@関入出金状況テーブル.更新日付は秒単位での比較とする。 <BR>
     * <BR>
     * ４）電文ダブりチェック<BR>
     * <BR>
     * ４－１）引数.受信電文データ.get("web3Request")の戻り値 = OrderDemand の場合<BR>
     * <BR>
     * 注文情報要求テーブルに同じ内容の電文がないかをチェックする。<BR>
     * 同じ内容の電文があった場合は、"ERROR"を返す。<BR>
     * <BR>
     * ［検索条件］<BR>
     * 　@注文番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.linked_1)の戻り値<BR>
     * 　@.comデビット決済取引番号 = 引数.受信電文データ<BR>
     * 　@.get(WEB3AioTelegramFormatDef.centerPayId)の戻り値<BR>
     * <BR>
     * ４－２）引数.受信電文データ.get("web3Request")の戻り値 = SettleStart の場合<BR>
     * <BR>
     * 決済開始要求テーブルに同じ内容の電文がないかをチェックする。<BR>
     * 同じ内容の電文があった場合は、"ERROR"を返す。<BR>
     * <BR>
     * ［検索条件］<BR>
     * 　@注文番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.linked_1)の戻り値<BR>
     * 　@.comデビット決済取引番号 = 引数.受信電文データ<BR>
     * 　@.get(WEB3AioTelegramFormatDef.centerPayId)の戻り値<BR>
     * <BR>
     * ４－３）引数.受信電文データ.get("web3Request")の戻り値 = SettleResult の場合<BR>
     * <BR>
     * 　@決済結果通知テーブルに同じ内容の電文がないかをチェックする。<BR>
     * 　@同じ内容の電文があった場合は、"ERROR"を返す。<BR>
     * <BR>
     * ［検索条件］<BR>
     * 　@注文番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.linked_1)の戻り値<BR>
     * 　@.comデビット決済取引番号 = 引数.受信電文データ<BR>
     * 　@.get(WEB3AioTelegramFormatDef.centerPayId)の戻り値<BR>
     * <BR>
     * ５）加盟店ID、アクセスキーのチェック<BR>
     * <BR>
     * ５－１）会社別決済機@関条件インスタンスを生成する。<BR>
     * <BR>
     *   ［コンストラクタの引数］<BR>
     *   証券会社コード： 引数.受信電文データ.get(WEB3AioTelegramFormatDef.cpy)の戻り値<BR>
     *   部店コード： 引数.受信電文データ.get(WEB3AioTelegramFormatDef.btn)の戻り値<BR>
     *   決済機@関ID = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.paySchemeId)の戻り値<BR>
     * <BR>
     *５－２）キャリア別加盟店IDインスタンスを生成する。 <BR> 
     * <BR>
     *  ［コンストラクタの引数］ <BR> 
     *  証券会社コード： 引数.受信電文データ.get("cpy")の戻り値 <BR> 
     *  部店コード： 引数.受信電文データ.get("btn")の戻り値  <BR>
     *  キャリア区分 = this.getキャリア区分（引数.受信電文データ.get("rdiv")）の戻り値 <BR> 
     * <BR>
     *  キャリア別加盟店ID.get加盟店ID()の戻り値 != 引数.受信電文データ.get("shopId")の戻り値 の場合、 <BR>
     *   "ERROR"を返す。<BR>
     * <BR>
     * ５－３）<BR>
     *   会社別決済機@関条件.getアクセスキー()の戻り値 != <BR>
     * 引数.受信電文データ.get(WEB3AioTelegramFormatDef.accessKey)の戻り値 の場合、<BR>
     *   "NG"を返す。<BR>
     * <BR>
     * ６）注文番号、決済機@関ID、注文日時のチェック<BR>
     * <BR>
     * ６－１）<BR>
     *   入出金状況.証券会社コード+入出金状況.部店コード+<BR>
     * 入出金状況.識別コード != 引数.受信電文データ.get(WEB3AioTelegramFormatDef.linked_1)の<BR>
     * 戻り値<BR>
     *   の場合、"ERROR"を返す。<BR>
     * <BR>
     * ６－２）<BR>
     *   入出金状況.決済機@関ID != <BR>
     * 引数.受信電文データ.get(WEB3AioTelegramFormatDef.paySchemeId)の戻り値 の<BR>
     * 場合、"ERROR"を返す。<BR>
     * <BR>
     * ６－３）<BR>
     *   入出金状況.注文日時 != 引数.受信電文データ.get(WEB3AioTelegramFormatDef.date)の<BR>
     * 戻り値 の場<BR>合、"ERROR"を返す。<BR>
     * <BR>
     * ７）.comデビット決済取引番号のチェック<BR>
     * <BR>
     * ７－１）引数.受信電文データ.get("web3Request")の戻り値 = <BR>
     * SettleStart or 引数.受信電文データ.get("web3Request")の戻り値 = <BR>
     * SettleResult の場合<BR>
     * <BR>
     *   入出金状況..comデビット決済取引番号 != 
     *   引数.受信電文デー<BR>.get(WEB3AioTelegramFormatDef.centerPayId)の戻り値 の場合、”NG"を返す。<BR>
     * <BR>
     * ８）処理状態のチェック<BR>
     * <BR>
     * ８－１）引数.受信電文データ.get("web3Request")の戻り値 = <BR>
     * SettleStart の<BR>場合<BR>
     * <BR>
     *   引数.受信電文データ.get(WEB3AioTelegramFormatDef.payStatus)の戻り値 !=<BR>
     *  "START" の場合、”NG"を返す。<BR>
     * <BR>
     * ９）金額のチェック<BR> 
     * <BR>
     * ９－１）引数.受信電文データ.get("web3Request")の戻り値 = SettleResult and <BR>
     * 引数.受信電文データ.get("payStatus")の戻り値 != "ERROR" の場合 <BR>
     * 入出金状況.金額 != 引数.受信電文データ.get("amount")の戻り値 の場合、”NG"を返す。 <BR>
     * <BR>
     * １０）プロトコルバージョンのチェック<BR>
     * <BR>
     *   引数.受信電文データ.get(WEB3AioTelegramFormatDef.protocolVersion)の戻り値 != <BR>
     * "V1.0" の場合、”ERROR"を返す。<BR>
     * <BR>
     * １１）"OK"を返す。<BR>
     * @@param l_receiptTelegramData - (受信電文データ)<BR>
     * @@param l_bankCashTransferStatusParams - (入出金状況)<BR>
     * 金融機@関連携入出金状況オブジェクト<BR>
     * @@return String
     * @@roseuid 4117412202F7
     */
    public String validateReceiptTelegram(
        HashMap l_receiptTelegramData,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
    {
        String STR_METHOD_NAME =
            "validateReceiptTelegram("
                + "HashMap l_receiptTelegramData, "
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_receiptTelegramData == null || l_cashTransStatus == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        BankCashTransferStatusRow l_bankCashTransferStatusRow = 
            (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();    

        // １）電文発行元のリモートアドレスチェック
        //   引数.受信電文データ.get("remoteAdd")の戻り値とシステム内で
        // 定義されているマルチバンクのリモートアドレスが一致しない場合、
        //   "ERROR"を返す。        
//        if(!WEB3AioTelegramHttpRequestDef.REMOTE_ADDRESS.equals(
//            l_receiptTelegramData.get(WEB3AioTelegramFormatDef.remoteAdd)))
//        {
//            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
//            log.error("アクセス不正エラー");
//            log.error("From Address : " + l_receiptTelegramData.get(WEB3AioTelegramFormatDef.remoteAdd).toString());
//            log.exiting(STR_METHOD_NAME);
//            return WEB3AioTelegramReturnCodeDef.ERROR;
//        }

        //１－１）システム内に保持するマルチバンクのリモートアドレスを取得する。 
        //this.getプリファ@レンス()をコールする。 
        //[引数] 
        //証券会社コード： 引数.受信電文データ.get("cpy")の戻り値 
        //設定名称： ”_REMOTE_ADDRESS”
        /*
        String l_strInstitutionCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy);
        String l_strPreference = null;
        try
        {
            l_strPreference = this.getPreference(
                l_strInstitutionCode, 
                WEB3AioTelegramHttpRequestDef.REMOTE_ADDRESS);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１－２）引数.受信電文データ.get("remoteAdd")の戻り値と１－１）で取得した値が一致しない場合、 
        //"ERROR"を返す。 
        String l_strRemoteAdd = 
            (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.remoteAdd);
        if (!l_strRemoteAdd.equals(l_strPreference))
        {
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        */
        
        // ２）セッションIDチェック
        //   引数.受信電文データ.get(WEB3AioTelegramFormatDef.apsid)の戻り値をIDとするセッションの存在チェックを行う
        //   存在しない場合、すでにセッションが終了している場合は、
        // "ERROR"を返す。
        long l_strSessionID =
            Long.parseLong((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.apsid));

        try
        {
            // ２－１）LoginSessionテーブルから、該当セッションのレコードを取得する。
            //      LoginSessionDao.findRowByPk（l_session_id)
            //      [引数］
            //      l_session_id： 引数.受信電文データ.get(WEB3AioTelegramFormatDef.apsid)の戻り値
            LoginSessionRow l_LoginSessionRow =
                LoginSessionDao.findRowByPk(l_strSessionID);             
            
            Date l_datExpirationDate = l_LoginSessionRow.getExpirationDate();
            Date l_datSystemDate = GtlUtils.getSystemTimestamp();
            
            // ２－２）レコードが取得できなかった or 
            // LoginSessionParams.getExpairationDate() < システムタイムスタンプ の場合は、 
            // "ERROR"を返す。
            if (l_LoginSessionRow == null 
                || l_datSystemDate.compareTo(l_datExpirationDate) >= 0)
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
                log.debug("session_id = " + l_strSessionID);
                log.debug("sessionRow = " + l_LoginSessionRow);
                log.debug("expairationDate = " + l_datExpirationDate);
                log.debug("systemDate = " + l_datSystemDate);
                log.error("セッションエラー");
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
            log.error("セッションエラー ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        //３）タイムアウトチェック 
        //３－１）金融機@関連携入出金状況テーブルより更新日時を取得
        Date l_datUpdTimestamp = l_bankCashTransferStatusRow.getLastUpdateTimestamp();
        
        //３－２）システムプリファ@レンステーブルより、タイムアウト値を取得する。 
        //this.getプリファ@レンス()をコールする。 
        //[引数] 
        //証券会社コード： 引数.受信電文データ.get("cpy")の戻り値 
        //設定名称： ”_AIO_TIMEOUT” 
        String l_strTimeoutValue = null;
        try
        {
            l_strTimeoutValue = this.getPreference(
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy), 
                WEB3AioTelegramHttpRequestDef.AIO_TIMEOUT);        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
			log.exiting(STR_METHOD_NAME);
			return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        
        //３－３）システムプリファ@レンスよりレコードが取得出来なかった or 
        //システムタイムスタンプ － 金融機@関入出金状況テーブル.更新日付 > 
        //      XX_AIO_TIOMEOUT の場合は、"ERROR"を返す。 
        //※ XX_AIO_TIOMEOUTは秒単位で設定。 
        //システムタイムスタンプ － 金融機@関入出金状況テーブル.更新日付は秒単位での比較とする。
        
        long l_lngDateRange = 
            GtlUtils.getSystemTimestamp().getTime() - l_datUpdTimestamp.getTime();
        
        long l_lngTimeoutValueSec = Long.parseLong(l_strTimeoutValue);
        long l_lngTimeoutValue = l_lngTimeoutValueSec * 1000L;
        
        if (WEB3StringTypeUtility.isEmpty(l_strTimeoutValue) || 
                (l_lngDateRange > l_lngTimeoutValue))
        {
			log.debug("NOTICE : --> --> --> --> --> --> Into Case 19");
			log.debug("dateRange = " + l_lngDateRange);
			log.debug("timeoutValue = " + l_lngTimeoutValue);
			log.error("タイムアウトエラー");
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }        
        
        // ４）電文ダブりチェック
        try
        {
            // QueryProcessorの取得
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();

            //引数.受信電文データ.get("web3Request")の戻り値 = OrderDemand の場合
            if (WEB3AioTelegramHttpRequestDef.ORDERDEMAND.equals(
                l_receiptTelegramData.get(WEB3AioTelegramFormatDef.web3Request)))
            {
                //検索用
                String l_strWhere = "linked_1 = ? and center_pay_id = ? ";

                //［検索条件］
                //注文番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.linked_1)の戻り値
                //.comデビット決済取引番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.centerPayId)の戻り値
                Object[] l_wheres =
                {
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1),
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId)
                };

                // 検索する
                List l_lisRows =
                    l_queryProcessor.doFindAllQuery(
                        BankOrderRequestRow.TYPE,
                        l_strWhere,
                        l_wheres);

                //注文情報要求テーブルに同じ内容の電文がないかをチェックする。
                //同じ内容の電文があった場合は、"ERROR"を返す。
                if (l_lisRows != null && !l_lisRows.isEmpty())
                {
                    log.debug("電文ダブりエラー");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.ERROR;
                }
            }
            //引数.受信電文データ.get("web3Request")の戻り値 = SettleStart の場合
            else if (WEB3AioTelegramHttpRequestDef.SETTLE_START.equals(
                l_receiptTelegramData.get(WEB3AioTelegramFormatDef.web3Request)))
            {
                //検索用
                String l_strWhere = "linked_1 = ? and center_pay_id = ? ";

                //［検索条件］
                //注文番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.linked_1)の戻り値
                //.comデビット決済取引番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.centerPayId)の戻り値
                Object[] l_wheres =
                {
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1),
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId)
                };

                // 検索する
                List l_lisRows =
                    l_queryProcessor.doFindAllQuery(
                        BankSettleStartRequestRow.TYPE,
                        l_strWhere,
                        l_wheres);

                //決済開始要求テーブルに同じ内容の電文がないかをチェックする。
                //同じ内容の電文があった場合は、"ERROR"を返す。
                if (l_lisRows != null && !l_lisRows.isEmpty())
                {
                    log.debug("電文ダブりエラー");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.ERROR;
                }
            }
            //引数.受信電文データ.get("web3Request")の戻り値 = SettleResult の場合
            else if (WEB3AioTelegramHttpRequestDef.SETTLE_RESULT.equals(
                l_receiptTelegramData.get(WEB3AioTelegramFormatDef.web3Request)))
            {
                //検索用
                String l_strWhere = "linked_1 = ? and center_pay_id = ? ";

                //［検索条件］
                //注文番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.linked_1)の戻り値
                //.comデビット決済取引番号 = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.centerPayId)の戻り値
                Object[] l_wheres =
                {
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1),
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId)
                };

                // 検索する
                List l_lisRows =
                    l_queryProcessor.doFindAllQuery(
                        BankSettleResultResponseRow.TYPE,
                        l_strWhere,
                        l_wheres);

                //決済結果通知テーブルに同じ内容の電文がないかをチェックする。
                //同じ内容の電文があった場合は、"ERROR"を返す。
                if (l_lisRows != null && !l_lisRows.isEmpty())
                {
                    log.debug("電文ダブりエラー");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.ERROR;
                }
            }

        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // ５）加盟店ID、アクセスキーのチェック
        WEB3AioCompanySettleInstitutionConditions l_aioCompanySettleInstitutionConditions =
            null;
        try
        {
            // ５－１）会社別決済機@関条件インスタンスを生成する。
            //   ［コンストラクタの引数］
            //   証券会社コード： 引数.受信電文データ.get(WEB3AioTelegramFormatDef.cpy)の戻り値
            //   部店コード： 引数.受信電文データ.get(WEB3AioTelegramFormatDef.btn)の戻り値
            //   決済機@関ID = 引数.受信電文データ.get(WEB3AioTelegramFormatDef.paySchemeId)の戻り値
            l_aioCompanySettleInstitutionConditions =
                new WEB3AioCompanySettleInstitutionConditions(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId));
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 8");
            log.debug("提携決済機@関インスタンスを生成できない", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        //５－２）キャリア別加盟店IDインスタンスを生成する。 
        //
        //  ［コンストラクタの引数］ 
        //  証券会社コード： 引数.受信電文データ.get("cpy")の戻り値 
        //  部店コード： 引数.受信電文データ.get("btn")の戻り値 
        //  キャリア区分 = this.getキャリア区分（引数.受信電文データ.get("rdiv")）の戻り値 
        //
        //  キャリア別加盟店ID.get加盟店ID()の戻り値 != 引数.受信電文データ.get("shopId")の戻り値 の場合、 
        //  "ERROR"を返す。 
        WEB3AioCareerShopId l_aioCareerShopIds = null;            
        try {
            l_aioCareerShopIds =
                new WEB3AioCareerShopId(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
//                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv));
                    this.getCareerDiv((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv)));
        } catch (WEB3BaseException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        } catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
            if (!l_aioCareerShopIds.getShopId().equals(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.shopId)))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 9");
                log.error("不正電文エラー(加盟店IDの不一致)");
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTelegramReturnCodeDef.ERROR;
            }

            // ５－３）
            //   会社別決済機@関条件.getアクセスキー()の戻り値 != 
            // 引数.受信電文データ.get(WEB3AioTelegramFormatDef.accessKey)の戻り値 の場合、
            //   "NG"を返す。
            if (!l_aioCompanySettleInstitutionConditions.getAccessKey().equals(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.accessKey)))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 10");
                log.error("不正電文エラー(アクセスキーの不一致)");
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTelegramReturnCodeDef.NG;
            }

        // ６）注文番号、決済機@関ID、注文日時のチェック
        // ６－１）
        //    入出金状況.証券会社コード+入出金状況.部店コード+
        // 入出金状況.識別コード != 引数.受信電文データ.get(WEB3AioTelegramFormatDef.linked_1)の
        // 戻り値の場合、"ERROR"を返す。
        if (!(l_bankCashTransferStatusRow.getInstitutionCode()
                 + l_bankCashTransferStatusRow.getBranchCode()
                 + l_bankCashTransferStatusRow.getOrderRequestNumber()).equals(
                     (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1)))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 11");
            log.error("不正電文エラー(一致する注文番号がシステム内に存在しない場合)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // ６－２）
        //   入出金状況.決済機@関ID != 
        // 引数.受信電文データ.get(WEB3AioTelegramFormatDef.paySchemeId)の戻り値 の
        // 場合、"ERROR"を返す。
        if (!l_bankCashTransferStatusRow.getPaySchemeId().equals(
                (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId)))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 12");
            log.error("不正電文エラー(決済機@関IDが不一致)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // ６－３）
        //   入出金状況.注文日時 != 引数.受信電文データ.get(WEB3AioTelegramFormatDef.date)の
        // 戻り値 の場<BR>合、"ERROR"を返す。
        String l_strDate = WEB3DateUtility.formatDate(l_bankCashTransferStatusRow.getOrderDateTime(),"yyyyMMddHHmmss");
        if (!l_strDate.equals((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date)))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 13");
            log.error("不正電文エラー(注文日時が不一致)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // ７）.comデビット決済取引番号のチェック
        // ７－１）引数.受信電文データ.get("web3Request")の戻り値 = 
        // SettleStart or 引数.受信電文データ.get("web3Request")の戻り値 = 
        // SettleResult の場合
        //   入出金状況..comデビット決済取引番号 != 
        //   引数.受信電文デー.get(WEB3AioTelegramFormatDef.centerPayId)の戻り値 の場合、”NG"を返す。
        if ((WEB3AioTelegramHttpRequestDef.SETTLE_START.equals(
                (String) l_receiptTelegramData.get(
                    WEB3AioTelegramFormatDef.web3Request)))
            || (WEB3AioTelegramHttpRequestDef.SETTLE_RESULT.equals(
                    (String) l_receiptTelegramData.get(
                        WEB3AioTelegramFormatDef.web3Request))))
        {
            if(l_bankCashTransferStatusRow.getCenterPayId() == null )
            {
                if((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId) != null)
                {
                    log.debug("NOTICE : --> --> --> --> --> --> Into Case 18");
                    log.error("不正電文エラー(.comデビット決済取引番号が不一致)");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.NG;                    
                }
            }
            else
            {
                if(!l_bankCashTransferStatusRow.getCenterPayId().equals(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId)))
                {
                    log.debug("NOTICE : --> --> --> --> --> --> Into Case 14");
                    log.error("不正電文エラー(.comデビット決済取引番号が不一致)");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.NG;
                }
            }
        }

        // ８）処理状態のチェック
        // ８－１）引数.受信電文データ.get("web3Request")の戻り値 = 
        // SettleStart の場合
        //    引数.受信電文データ.get(WEB3AioTelegramFormatDef.payStatus)の戻り値 !=
        //   "START" の場合、”NG"を返す。
        if ((WEB3AioTelegramHttpRequestDef.SETTLE_START.equals(
                (String) l_receiptTelegramData.get(
                    WEB3AioTelegramFormatDef.web3Request)))
            && (!WEB3AioTelegramHttpRequestDef.START.equals(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus))))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 15");
            log.error("不正電文エラー(処理状態が不正)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.NG;
        }

        // ９）金額のチェック
        // ９－１）引数.受信電文データ.get("web3Request")の戻り値 = SettleResult and 
        // 引数.受信電文データ.get("payStatus")の戻り値 != "ERROR" の場合 

        if ((WEB3AioTelegramHttpRequestDef.SETTLE_RESULT.equals(
                (String) l_receiptTelegramData.get(
                    WEB3AioTelegramFormatDef.web3Request)))
            && (!WEB3AioTelegramHttpRequestDef.ERROR.equals(
                (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus))))
        {
            // 入出金状況.金額 != 引数.受信電文データ.get("amount")の戻り値 の場合、”NG"を返す。 
            long l_lngAmount = 
                Long.parseLong((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.amount));
                
            if (l_bankCashTransferStatusRow.getAmount() != l_lngAmount)
            {
                log.debug("NOTICE : --> --> --> --> --> --> 金額が不正");
                log.error("不正電文エラー(金額が不正)");
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTelegramReturnCodeDef.NG;
            }
        }
        
        // １０）プロトコルバージョンのチェック<BR>
        //   引数.受信電文データ.get(WEB3AioTelegramFormatDef.protocolVersion)の戻り値 != <BR>
        // "V1.0" の場合、”ERROR"を返す。<BR>
        if (!WEB3AioTelegramHttpRequestDef.V1DOT0.equals(
                (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.protocolVersion)))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 16");
            log.error("不正電文エラー(プロトコルバージョンが不正)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // １１）"OK"を返す。

        log.debug("NOTICE : --> --> --> --> --> --> Into Case 17");
        log.exiting(STR_METHOD_NAME);
        return WEB3AioTelegramReturnCodeDef.OK;
    }

    /**
     * (create送信電文)<BR>
     * 送信電文を生成する。<BR>
     * <BR>
     * １）レスポンスデータにContentTypeを設定する。<BR>
     *    レスポンスデータ.setContentType("text/plain")<BR>
     * <BR>
     * ２）レスポンスデータから、Writerを取得する。<BR>
     * <BR>
     * ３）レスポンスのメッセージボディに以下の文字列を出力する。<BR>
     * <BR>
     *    "&lt;SHOPMSG&gt;"<BR>
     * <BR>
     * ４）引数.送信電文データの全要素をレスポンスのメッセージボディに出力する。<BR>
     * <BR>
     * ５）レスポンスのメッセージボディに以下の文字列を出力する。<BR>
     * <BR>
     *    "&lt;/SHOPMSG&gt;"<BR>
     * <BR>
     * ６）Writerをクローズする。<BR>
     * @@param l_response - (レスポンスデータ)<BR>
     * @@param l_strSendTelegramData - (送信電文データ)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411741540141
     */
    public void createSendTelegram(
        HttpServletResponse l_response,
        String[] l_strSendTelegramData) throws IOException
    {
        String STR_METHOD_NAME =
            "createSendTelegram("
                + "HttpServletResponse l_response, "
                + "String[] l_strSendTelegramData)";
        log.entering(STR_METHOD_NAME);

        if (l_response == null || l_strSendTelegramData == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）レスポンスデータにContentTypeを設定する
        //    レスポンスデータ.setContentType("text/plain")
        l_response.setContentType(WEB3AioTelegramHttpRequestDef.ContentType);

        PrintWriter l_printWriter = null;

        // ２）レスポンスデータから、Writerを取得する
        l_printWriter = l_response.getWriter();

        //３）レスポンスのメッセージボディに"<SHOPMSG>"を出力する
        l_printWriter.println(WEB3AioTelegramHttpRequestDef.SHOPMSG_START);

        //４）引数.送信電文データの全要素をレスポンスのメッセージボディに出力する
        for (int i = 0; i < l_strSendTelegramData.length; i++)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
            log.debug("l_strSendTelegramData[" + i + "] = " + l_strSendTelegramData[i]);
            l_printWriter.println(l_strSendTelegramData[i]);
        }

        // ５）レスポンスのメッセージボディに"</SHOPMSG>"を出力する
        l_printWriter.println(WEB3AioTelegramHttpRequestDef.SHOPMSG_END);

        // ６）Writerをクローズする
        l_printWriter.close();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert注文情報要求)<BR>
     * 注文情報要求テーブルにレコードを追加する。<BR>
     * <BR>
     * １）注文情報要求Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）パラメータをセットする。<BR>
     * <BR>
     * 詳細は、DB更新仕様 「注文要求受付_注文情報要求テーブル.xls」 参照<BR>
     * <BR>
     * ３）WEB3DataAccessUtility.insertRow()にて、レコードを追加する。<BR>
     * <BR>
     *   ［引数］<BR>
     *   注文情報要求Paramsインスタンス<BR>
     * @@param l_receiptTelegramData - (受信電文データ)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41174288027A
     */
    public void insertOrderInfoRequire(HashMap l_receiptTelegramData) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertOrderInfoRequire(HashMap l_receiptTelegramData)";
        log.entering(STR_METHOD_NAME);

        if (l_receiptTelegramData == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）注文情報要求Paramsインスタンスを生成する
        BankOrderRequestParams l_orderInfoRequireParams =
            new BankOrderRequestParams();

        // ２）パラメータをセットする
        // 詳細は、DB更新仕様 「注文要求受付_注文情報要求テーブル.xls」参照
        l_orderInfoRequireParams.setCreatedTimestamp(
            WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(),
                "yyyyMMddHHmmss"));
        l_orderInfoRequireParams.setProtocolVersion(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.protocolVersion));
        l_orderInfoRequireParams.setLinked1(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1));
        l_orderInfoRequireParams.setShopId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.shopId));
        l_orderInfoRequireParams.setOrderDateTime(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date));
        l_orderInfoRequireParams.setCenterPayId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId));
        l_orderInfoRequireParams.setPaySchemeId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId));
        l_orderInfoRequireParams.setAccessKey(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.accessKey));

        // ３）WEB3DataAccessUtility.insertRow()にて、レコードを追加する
        try
        {
            log.debug("l_orderInfoRequireParams = " + l_orderInfoRequireParams);
            WEB3DataAccessUtility.insertRow(l_orderInfoRequireParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (insert決済開始要求)<BR>
     * 決済開始要求テーブルにレコードを追加する。<BR>
     * <BR>
     * １）決済開始要求Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）パラメータをセットする。<BR>
     * <BR>
     * 詳細は、DB更新仕様 「決済開始受付_決済開始要求テーブル.xls」 参照<BR>
     * <BR>
     * ３）WEB3DataAccessUtility.insertRow()にて、レコードを追加する。<BR>
     * <BR>
     *   ［引数］<BR>
     *   決済開始要求Paramsインスタンス<BR>
     * @@param l_receiptTelegramData - (受信電文データ)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411742AE021C
     */
    public void insertSettleStartRequire(HashMap l_receiptTelegramData) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertSettleStartRequire(HashMap l_receiptTelegramData)";
        log.entering(STR_METHOD_NAME);

        if (l_receiptTelegramData == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）決済開始要求Paramsインスタンスを生成する
        BankSettleStartRequestParams l_bankSettleStartRequestParams =
            new BankSettleStartRequestParams();

        // ２）パラメータをセットする
        // 詳細は、DB更新仕様 「決済開始受付_決済開始要求テーブル.xls」参照
        l_bankSettleStartRequestParams.setCreatedTimestamp(
            WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(),
                "yyyyMMddHHmmss"));
        l_bankSettleStartRequestParams.setProtocolVersion(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.protocolVersion));
        l_bankSettleStartRequestParams.setLinked1(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1));
        l_bankSettleStartRequestParams.setShopId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.shopId));
        l_bankSettleStartRequestParams.setOrderDateTime(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date));
        l_bankSettleStartRequestParams.setCenterPayId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId));
        l_bankSettleStartRequestParams.setPaySchemeId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId));
        l_bankSettleStartRequestParams.setAccessKey(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.accessKey));
        l_bankSettleStartRequestParams.setPayStatus(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus));

        // ３）WEB3DataAccessUtility.insertRow()にて、レコードを追加する
        try
        {
            log.debug("l_bankSettleStartRequestParams = " + l_bankSettleStartRequestParams);
            WEB3DataAccessUtility.insertRow(l_bankSettleStartRequestParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (insert決済結果通知)<BR>
     * 決済結果通知テーブルにレコードを追加する。<BR>
     * <BR>
     * １）決済結果通知Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）パラメータをセットする。<BR>
     * <BR>
     * 詳細は、DB更新仕様 「決済結果通知_決済結果通知テーブル.xls」 参照<BR>
     * <BR>
     * ３）WEB3DataAccessUtility.insertRow()にて、レコードを追加する。<BR>
     * <BR>
     *   ［引数］<BR>
     *   決済結果通知Paramsインスタンス<BR>
     * @@param l_receiptTelegramData - (受信電文データ)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119F7BC0244
     */
    public void insertSettleResultNotify(HashMap l_receiptTelegramData) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertSettleResultNotify(HashMap l_receiptTelegramData)";
        log.entering(STR_METHOD_NAME);

        if (l_receiptTelegramData == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）決済結果通知Paramsインスタンスを生成する
        BankSettleResultResponseParams l_bankSettleResultResponseParams =
            new BankSettleResultResponseParams();

        // ２）パラメータをセットする 
        // 詳細は、DB更新仕様 「決済結果通知_決済結果通知テーブル.xls」参照
        l_bankSettleResultResponseParams.setCreatedTimestamp(
            WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(),
                "yyyyMMddHHmmss"));
        l_bankSettleResultResponseParams.setProtocolVersion(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.protocolVersion));
        l_bankSettleResultResponseParams.setLinked1(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1));
        l_bankSettleResultResponseParams.setShopId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.shopId));
        l_bankSettleResultResponseParams.setOrderDateTime(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date));
        l_bankSettleResultResponseParams.setCenterPayId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId));
        l_bankSettleResultResponseParams.setComondebiSalesSlip(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiSalesSlip));
        l_bankSettleResultResponseParams.setComondebiAuthDate(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiAuthDate));
        l_bankSettleResultResponseParams.setPayStatus(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus));
        l_bankSettleResultResponseParams.setComondebiAuthresCode(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiAuthresCode));
        l_bankSettleResultResponseParams.setComondebiCaptureDate(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiCaptureDate));
        l_bankSettleResultResponseParams.setAmount(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.amount));
        l_bankSettleResultResponseParams.setPaySchemeId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId));
        l_bankSettleResultResponseParams.setAccessKey(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.accessKey));
        l_bankSettleResultResponseParams.setDescription(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.description));

        // ３）WEB3DataAccessUtility.insertRow()にて、レコードを追加する
        try
        {
            log.debug("l_bankSettleResultResponseParams = " + l_bankSettleResultResponseParams);
            WEB3DataAccessUtility.insertRow(l_bankSettleResultResponseParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update注文要求受付状態)<BR>
     * 金融機@関連携入出金状況テーブルを注文受付時の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）.comデビット決済取引番号<BR>
     *    引数.入出金状況.set.comデビット決済取引番号()<BR>
     * <BR>
     *    ［引数］<BR>
     *     引数..comデビット決済取引番号<BR>
     * <BR>
     * １－２）処理時間（注文要求）<BR>
     *    引数.入出金状況.set処理時間（注文要求）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－３）引数.returnCode = "OK" の場合<BR>
     * <BR>
     * １－３－１）処理FLAG（注文）<BR>
     *    引数.入出金状況.set処理FLAG（注文）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '1'（要求受信）<BR>
     * <BR>
     * １－４）引数.returnCode = "NG" の場合<BR>
     * <BR>
     * １－４－１）処理FLAG（注文）<BR>
     *    引数.入出金状況.set処理FLAG（注文）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（要求エラーNG）<BR>
     * <BR>
     * １－４－２）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（エラー）<BR>
     * <BR>
     * １－４－３）処理時間<BR>
     *    引数.入出金状況.set処理時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－５）引数.returnCode = "ERROR" の場合<BR>
     * <BR>
     * １－５－１）処理FLAG（注文）<BR>
     *    引数.入出金状況.set処理FLAG（注文）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '4'（要求エラーERROR）<BR>
     * <BR>
     * １－５－２）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（エラー）<BR>
     * <BR>
     * １－５－３）処理時間<BR>
     *    引数.入出金状況.set処理時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－６）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * @@param l_strReturnCode - (受信電文のチェック結果)<BR>
     * @@param l_strComDebitNumber - (.comデビット決済取引番号)<BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況オブジェクト
     * @@roseuid 4117476E0047
     */
    public void updateOrderRequireAccept(
        String l_strReturnCode,
        String l_strComDebitNumber,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateOrderRequireAccept("
                + "String l_strReturnCode, "
                + "String l_strComDebitNumber, "
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）.comデビット決済取引番号<BR>
        //    引数.入出金状況.set.comデビット決済取引番号()
        l_cashTransStatus.setComDebitNumber(l_strComDebitNumber);

        // １－２）処理時間（注文要求）
        //    引数.入出金状況.set処理時間（注文要求）()
        l_cashTransStatus.setOrderRequestTime(
            GtlUtils.getSystemTimestamp());

        // １－３）引数.returnCode = "OK" の場合
        if (WEB3AioTelegramReturnCodeDef.OK.equals(l_strReturnCode))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            // １－３－１）処理FLAG（注文）
            //    引数.入出金状況.set処理FLAG（注文）()
            l_cashTransStatus.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_RECEIPT);
        }
        else
        {
            //１－４）引数.returnCode = "NG" の場合
            if (WEB3AioTelegramReturnCodeDef.NG.equals(l_strReturnCode))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
                // １－４－１）処理FLAG（注文）
                //    引数.入出金状況.set処理FLAG（注文）()
                l_cashTransStatus.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_ERROR_NG);

                // １－４－２）処理区分
                //    引数.入出金状況.set処理区分()
                l_cashTransStatus.setStatus(WEB3TransactionStatusDef.ERROR);

                // １－４－３）処理時間
                //    引数.入出金状況.set処理時間()
                l_cashTransStatus.setTransactionTime(
                    GtlUtils.getSystemTimestamp());
            }
            else
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
                //１－５）引数.returnCode = "ERROR" の場合
                if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
                {
                    // １－５－１）処理FLAG（注文）
                    //    引数.入出金状況.set処理FLAG（注文）()  
                    l_cashTransStatus.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_ERROR_ERROR);

                    // １－５－２）処理区分
                    //    引数.入出金状況.set処理区分()  
                    l_cashTransStatus.setStatus(WEB3TransactionStatusDef.ERROR);

                    // １－５－３）処理時間
                    //    引数.入出金状況.set処理時間()  
                    l_cashTransStatus.setTransactionTime(
                        GtlUtils.getSystemTimestamp());
                }
            }
        }

        // １－６）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update注文要求応答状態)<BR>
     * 金融機@関連携入出金状況テーブルを注文受付応答時の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）処理FLAG（注文）<BR>
     *    引数.入出金状況.set処理FLAG（注文）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '2'（応答送信）<BR>
     * <BR>
     * １－２）処理時間（注文応答）<BR>
     *    引数.入出金状況.set処理時間（注文応答）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－３）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況オブジェクト
     * @@roseuid 41197ED502EA
     */
    public void updateOrderRequireResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateOrderRequireResponse("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）処理FLAG（注文）
        //    引数.入出金状況.set処理FLAG（注文）()
        l_cashTransStatus.setOrderStatusFlag(
            WEB3OrderStatusFlagDef.RESPONSE_SEND);

        // １－２）処理時間（注文応答）
        //    引数.入出金状況.set処理時間（注文応答）()
        l_cashTransStatus.setOrderResponseTime(
            GtlUtils.getSystemTimestamp());

        // １－３）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update注文要求中止状態)<BR>
     * 金融機@関連携入出金状況テーブルを注文受付中止時の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）処理FLAG（決済開始）<BR>
     *    引数.入出金状況.set処理FLAG（決済開始）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '5'（キャンセル）<BR>
     * <BR>
     * １－２）処理時間（決済開始要求）<BR>
     *    引数.入出金状況.set処理時間（決済開始要求）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－３）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（エラー）<BR>
     * <BR>
     * １－４）処理時間<BR>
     *    引数.入出金状況.set処理時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－５）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況オブジェクト
     * @@roseuid 4119AE1B005F
     */
    public void updateOrderRequireDiscontinuation(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateOrderRequireDiscontinuation("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）処理FLAG（決済開始）
        //    引数.入出金状況.set処理FLAG（決済開始）()
        l_cashTransStatus.setStartStatusFlg(
            WEB3StartStatusFlgDef.CANCEL);

        // １－２）処理時間（決済開始要求）
        //    引数.入出金状況.set処理時間（決済開始要求）()
        l_cashTransStatus.setStartRequestTime(
            GtlUtils.getSystemTimestamp());

        // １－３）処理区分
        //    引数.入出金状況.set処理区分()
        l_cashTransStatus.setStatus(
            WEB3TransactionStatusDef.ERROR);

        // １－４）処理時間
        //    引数.入出金状況.set処理時間()
        l_cashTransStatus.setTransactionTime(
            GtlUtils.getSystemTimestamp());

        // １－５）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update決済開始受付状態)<BR>
     * 金融機@関連携入出金状況テーブルを決済開始受付時の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）処理時間（決済開始要求）<BR>
     *    引数.入出金状況.set処理時間（決済開始要求）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－２）引数.returnCode = "OK" の場合<BR>
     * <BR>
     * １－２－１）処理FLAG（決済開始）<BR>
     *    引数.入出金状況.set処理FLAG（決済開始）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '1'（要求受信）<BR>
     * <BR>
     * １－３）引数.returnCode = "NG" の場合<BR>
     * <BR>
     * １－３－１）処理FLAG（決済開始）<BR>
     *    引数.入出金状況.set処理FLAG（決済開始）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（要求エラーNG）<BR>
     * <BR>
     * １－３－２）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（エラー）<BR>
     * <BR>
     * １－３－３）処理時間<BR>
     *    引数.入出金状況.set処理時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－４）引数.returnCode = "ERROR" の場合<BR>
     * <BR>
     * １－４－１）処理FLAG（決済開始）<BR>
     *    引数.入出金状況.set処理FLAG（決済開始）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '4'（要求エラーERROR）<BR>
     * <BR>
     * １－４－２）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（エラー）<BR>
     * <BR>
     * １－４－３）処理時間<BR>
     *    引数.入出金状況.set処理時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－５）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * @@param l_strReturnCode - (受信電文のチェック結果)<BR>
     * @@param l_cashTransSituation - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況オブジェクト<BR>
     * @@roseuid 4117476E0067
     */
    public void updateSettleStartAccept(
        String l_strReturnCode,
        WEB3AioFinInstitutionCashTransStatus l_cashTransSituation) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleStartAccept("
                + "String l_strReturnCode, "
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransSituation == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）処理時間（決済開始要求）
        //    引数.入出金状況.set処理時間（決済開始要求）()
        l_cashTransSituation.setStartRequestTime(GtlUtils.getSystemTimestamp());

        // １－２）引数.returnCode = "OK" の場合<BR>
        if (WEB3AioTelegramReturnCodeDef.OK.equals(l_strReturnCode))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            // １－２－１）処理FLAG（決済開始）
            //    引数.入出金状況.set処理FLAG（決済開始）()
            l_cashTransSituation.setStartStatusFlg(
                WEB3StartStatusFlgDef.REPUEST_RECEIPT);
        }
        else
        {
            //１－３）引数.returnCode = "NG" の場合
            if (WEB3AioTelegramReturnCodeDef.NG.equals(l_strReturnCode))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
                // １－３－１）処理FLAG（決済開始）
                //    引数.入出金状況.set処理FLAG（決済開始）()
                l_cashTransSituation.setStartStatusFlg(
                    WEB3StartStatusFlgDef.REPUEST_ERROR_NG);

                // １－３－２）処理区分
                //    引数.入出金状況.set処理区分()
                l_cashTransSituation.setStatus(WEB3TransactionStatusDef.ERROR);

                // １－３－３）処理時間
                //    引数.入出金状況.set処理時間()
                l_cashTransSituation.setTransactionTime(
                    GtlUtils.getSystemTimestamp());
            }
            else
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
                //１－４）引数.returnCode = "ERROR" の場合
                if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
                {
                    // １－４－１）処理FLAG（決済開始）
                    //    引数.入出金状況.set処理FLAG（決済開始）()
                    l_cashTransSituation.setStartStatusFlg(
                        WEB3StartStatusFlgDef.REPUEST_ERROR_ERROR);

                    // １－４－２）処理区分
                    //    引数.入出金状況.set処理区分()
                    l_cashTransSituation.setStatus(
                        WEB3TransactionStatusDef.ERROR);

                    // １－４－３）処理時間
                    //    引数.入出金状況.set処理時間()
                    l_cashTransSituation.setTransactionTime(
                        GtlUtils.getSystemTimestamp());
                }
            }
        }

        // １－５）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransSituation.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransSituation.getDataSourceObject();
                
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update決済開始応答状態)<BR>
     * 金融機@関連携入出金状況テーブルを決済開始応答時の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）処理FLAG（決済開始）<BR>
     *    引数.入出金状況.set処理FLAG（決済開始）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '2'（応答送信）<BR>
     * <BR>
     * １－２）処理時間（決済開始応答）<BR>
     *    引数.入出金状況.set処理時間（決済開始応答）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－３）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト<BR>
     * @@roseuid 4119D059034D
     */
    public void updateSettleStartResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleStartResponse("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）処理FLAG（決済開始）
        //    引数.入出金状況.set処理FLAG（決済開始）()
        l_cashTransStatus.setStartStatusFlg(
            WEB3StartStatusFlgDef.RESPONSE_SEND);

        // １－２）処理時間（決済開始応答）
        //    引数.入出金状況.set処理時間（決済開始応答）()
        l_cashTransStatus.setStartResponseTime(
            GtlUtils.getSystemTimestamp());

        // １－３）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update決済結果通知状態)<BR>
     * 金融機@関連携入出金状況テーブルを決済結果通知時の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）処理時間（決済結果通知）<BR>
     *    引数.入出金状況.set処理時間（決済結果通知）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－２）処理時間<BR>
     *    引数.入出金状況.set処理時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－３）引数.returnCode = "COMPLETE" の場合<BR>
     * <BR>
     * １－３－１）処理FLAG（決済結果）<BR>
     *    引数.入出金状況.set処理FLAG（決済結果）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '1'（要求受信）<BR>
     * <BR>
     * １－３－２）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '1'（OK）<BR>
     * <BR>
     * １－３－３）受渡予定日<BR>
     *    引数.入出金状況.set受渡予定日()<BR>
     * <BR>
     *    ［引数］<BR>
     *     引数.受渡予定日<BR>
     * <BR>
     * １－３－４）振込入金予定日<BR>
     *    引数.入出金状況.set振込入金予定日()<BR>
     * <BR>
     *    ［引数］<BR>
     *     引数.振込入金予定日<BR>
     * <BR>
     * １－４）引数.returnCode = "FAIL" の場合<BR>
     * <BR>
     * １－４－１）処理FLAG（決済結果）<BR>
     *    引数.入出金状況.set処理FLAG（決済結果）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（通知エラーFAIL）<BR>
     * <BR>
     * １－４－２）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '2'（NG）<BR>
     * <BR>
     * １－５）引数.returnCode = "ERROR" の場合<BR>
     * <BR>
     * １－５－１）処理FLAG（決済結果）<BR>
     *    引数.入出金状況.set処理FLAG（決済結果）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '4'（通知エラーERROR）<BR>
     * <BR>
     * １－５－２）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（エラー）<BR>
     * <BR>
     * １－６）引数.returnCode = "NG" の場合<BR>
     * <BR>
     * １－６－１）処理FLAG（決済結果）<BR>
     *    引数.入出金状況.set処理FLAG（決済結果）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '5'（応答エラー）<BR>
     * <BR>
     * １－６－２）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（エラー）<BR>
     * <BR>
     * １－７）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * @@param l_strReturnCode - (受信電文のチェック結果)
     * @@param l_datDeliveryScheduledDate - (受渡予定日)
     * @@param l_datComondebiCaptureDate - (振込入金予定日)
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況オブジェクト
     * @@roseuid 4119F7830234
     */
    public void updateSettleResultNotify(
        String l_strReturnCode,
        Date l_datDeliveryScheduledDate,
        Date l_datComondebiCaptureDate,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleResultNotify("
                + "String l_strReturnCode, "
                + "Date l_datDeliveryScheduledDate, "
                + "Date l_datComondebiCaptureDate, "
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）処理時間（決済結果通知）
        //    引数.入出金状況.set処理時間（決済結果通知）()
        l_cashTransStatus.setResultRequestTime(GtlUtils.getSystemTimestamp());

        // １－２）処理時間
        //    引数.入出金状況.set処理時間()
        l_cashTransStatus.setTransactionTime(GtlUtils.getSystemTimestamp());

        // １－３）引数.returnCode = "COMPLETE" の場合
        if (WEB3AioTelegramReturnCodeDef.COMPLETE.equals(l_strReturnCode))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            // １－３－１）処理FLAG（決済結果）
            //    引数.入出金状況.set処理FLAG（決済結果）()
            l_cashTransStatus.setResultStatusFlag(
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT);

            // １－３－２）処理区分
            //    引数.入出金状況.set処理区分()
            l_cashTransStatus.setStatus(WEB3TransactionStatusDef.OK);

            // １－３－３）受渡予定日
            //    引数.入出金状況.set受渡予定日()           
            ((BankCashTransferStatusParams) 
               l_cashTransStatus.getDataSourceObject()).setDeliveryScheduledDate(
                 l_datDeliveryScheduledDate);

            // １－３－４）振込入金予定日<BR>
            //    引数.入出金状況.set振込入金予定日()
            ((BankCashTransferStatusParams)
                l_cashTransStatus.getDataSourceObject()).setComondebiCaptureDate(
                    l_datComondebiCaptureDate);
        }
        else
        {
            //１－４）引数.returnCode = "FAIL" の場合
            if (WEB3AioTelegramReturnCodeDef.FAIL.equals(l_strReturnCode))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
                // １－４－１）処理FLAG（決済結果）
                //    引数.入出金状況.set処理FLAG（決済結果）()
                l_cashTransStatus.setResultStatusFlag(
                    WEB3ResultStatusFlagDef.NOTIFY_ERROR_FAIL);

                // １－４－２）処理区分
                //    引数.入出金状況.set処理区分()>
                l_cashTransStatus.setStatus(WEB3TransactionStatusDef.NG);
            }
            else
            {
                // １－５）引数.returnCode = "ERROR" の場合
                if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
                {
                    log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
                    // １－５－１）処理FLAG（決済結果）
                    //    引数.入出金状況.set処理FLAG（決済結果）()
                    l_cashTransStatus.setResultStatusFlag(
                        WEB3ResultStatusFlagDef.NOTIFY_ERROR_ERROR);

                    // １－５－２）処理区分
                    //    引数.入出金状況.set処理区分()>
                    l_cashTransStatus.setStatus(WEB3TransactionStatusDef.ERROR);
                }
                else
                {
                    //１－６）引数.returnCode = "NG" の場合
                    if (WEB3AioTelegramReturnCodeDef.NG.equals(l_strReturnCode))
                    {
                        log.debug("NOTICE : --> --> --> --> --> --> Into Case 5");
                        // １－６－１）処理FLAG（決済結果）
                        //    引数.入出金状況.set処理FLAG（決済結果）()
                        l_cashTransStatus.setResultStatusFlag(
                            WEB3ResultStatusFlagDef.RESPONSE_ERROR_TELEGRAM);

                        // １－６－２）処理区分
                        //    引数.入出金状況.set処理区分()>
                        l_cashTransStatus.setStatus(
                            WEB3TransactionStatusDef.ERROR);
                    }
                }
            }
        }

        // １－７）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransStatus.setLastUpdateTimestamp(GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update決済結果応答状態)<BR>
     * 金融機@関連携入出金状況テーブルを決済結果応答時の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）処理FLAG（決済結果）<BR>
     *    引数.入出金状況.set処理FLAG（決済結果）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '2'（応答送信）<BR>
     * <BR>
     * １－２）処理時間（決済結果応答）<BR>
     *    引数.入出金状況.set処理時間（決済結果応答）()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－３）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * <BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 4119F7830282
     */
    public void updateSettleResultResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleResultResponse("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）処理FLAG（決済結果）
        //    引数.入出金状況.set処理FLAG（決済結果）()
        l_cashTransStatus.setResultStatusFlag(
            WEB3ResultStatusFlagDef.RESPONSE_SEND);

        // １－２）処理時間（決済結果応答）
        //    引数.入出金状況.set処理時間（決済結果応答）()
        l_cashTransStatus.setResultResponseTime(
            GtlUtils.getSystemTimestamp());

        // １－３）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update決済完了状態)<BR>
     * 金融機@関連携入出金状況テーブルを決済完了時（エラー）の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '1'（OK）<BR>
     * <BR>
     * １－２）処理時間<BR>
     *    引数.入出金状況.set処理時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－３）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 411B18C4007F
     */
    public void updateSettleComplete(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleComplete("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）処理区分
        //    引数.入出金状況.set処理区分()
        l_cashTransStatus.setStatus(
            WEB3TransactionStatusDef.OK);

        // １－２）処理時間
        //    引数.入出金状況.set処理時間()
        l_cashTransStatus.setTransactionTime(
            GtlUtils.getSystemTimestamp());

        // １－３）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
            
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update決済エラー状態)<BR>
     * 金融機@関連携入出金状況テーブルを決済エラー時の状態に更新する。<BR>
     * <BR>
     * １）引数.入出金状況に、パラメータをセットする。<BR>
     * <BR>
     * １－１）処理区分<BR>
     *    引数.入出金状況.set処理区分()<BR>
     * <BR>
     *    ［引数］<BR>
     *     '3'（エラー）<BR>
     * <BR>
     * １－２）処理時間<BR>
     *    引数.入出金状況.set処理時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * １－３）最終更新日時<BR>
     *    引数.入出金状況.set最終更新日時()<BR>
     * <BR>
     *    ［引数］<BR>
     *     現在時刻（システムタイムスタンプ）<BR>
     * <BR>
     * ２）レコードを更新する。<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [引数]<BR>
     *    引数.入出金状況.getDataSourceObject()の戻り値<BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 411B32690264
     */
    public void updateSettleError(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleError("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）引数.入出金状況に、パラメータをセットする
        // １－１）処理区分
        //    引数.入出金状況.set処理区分()
        l_cashTransStatus.setStatus(
            WEB3TransactionStatusDef.ERROR);

        // １－２）処理時間
        //    引数.入出金状況.set処理時間()
        l_cashTransStatus.setTransactionTime(
            GtlUtils.getSystemTimestamp());

        // １－３）最終更新日時
        //    引数.入出金状況.set最終更新日時()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // ２）レコードを更新する
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (getメッセージコード)<BR>
     * メッセージコードを取得する。<BR>
     * <BR>
     * １）注文単位テーブルから、以下の条件のデータを取得する。<BR>
     * <BR>
     *    ［条件］<BR>
     *    識別コード = 引数.識別コード<BR>
     *    .comデビット決済取引番号 = 引数..comデビット決済取引番号<BR>
     * <BR>
     * ２）入出金伝票受付キューテーブルから、以下の条件のデータを取得する。<BR>
     * <BR>
     *    [条件]<BR>
     *    データコード = "GI80C"<BR>
     *    証券会社コード = 引数.証券会社コード<BR>
     *    部店コード = 引数.部店コード<BR>
     *    顧客コード = 引数.顧客コード<BR>
     *    識別コード = 引数.識別コード<BR>
     * <BR>
     * ３）注文単位が持つフラグと入出金伝票受付キューが持つフラグと引数のフラグから<BR>
     *    現在の入出金状況を判定し、その結果を返却する。<BR>
     * <BR>
     *    詳細は、「入出金ステータス構成表.xls」 参照<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strOrderRequestNumber - (識別コード)
     * @@param l_strComDebitNumber - (.comデビット決済取引番号)
     * @@param l_strStatus - (処理区分)
     * @@param l_strOrderStatusFlag - (処理FLAG（注文）)
     * @@param l_strStartStatusFlg - (処理FLAG（決済開始）)
     * @@param l_strResultStatusFlag - (処理FLAG（決済結果）)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41184FB001F6
     */
    public String getMessageCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strOrderRequestNumber,
        String l_strComDebitNumber,
        String l_strStatus,
        String l_strOrderStatusFlag,
        String l_strStartStatusFlg,
        String l_strResultStatusFlag) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getMessageCode("
                + "String l_strInstitutionCode, "
                + "String l_strBranchCode, "
                + "String l_strAccountCode, "
                + "String l_strOrderRequestNumber, "
                + "String l_strComDebitNumber, "
                + "String l_strStatus, "
                + "String l_strOrderStatusFlag, "
                + "String l_strStartStatusFlg, "
                + "String l_strResultStatusFlag)";
        log.entering(STR_METHOD_NAME);

        // １）注文単位テーブルから、以下の条件のデータを取得する。
        //    ［条件］
        //    識別コード = 引数.識別コード
        //    .comデビット決済取引番号 = 引数..comデビット決済取引番号
        List l_lisOrderRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_whereClause =
                " order_request_number = ? " + "and com_debit_number = ? ";
            Object[] l_bindVars = { l_strOrderRequestNumber, l_strComDebitNumber };

            l_lisOrderRows =
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(
                " DBへのアクセスに失敗しました when search table AioOrderUnit",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(
                " DBへのアクセスに失敗しました when search table AioOrderUnit",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }

        OrderStatusEnum l_orderStatus = null;
        String l_strCancelType = null;
        if (l_lisOrderRows != null && l_lisOrderRows.size() != 0)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            AioOrderUnitRow l_aioOrderUnitRow = 
                (AioOrderUnitRow) l_lisOrderRows.get(0);
            l_orderStatus = l_aioOrderUnitRow.getOrderStatus();
            l_strCancelType = l_aioOrderUnitRow.getCancelType();
        }

        // ２）入出金伝票受付キューテーブルから、以下の条件のデータを取得する。
        //    [条件]<BR>
        //    データコード = "GI80C"
        //    証券会社コード = 引数.証券会社コード
        //    部店コード = 引数.部店コード
        //    顧客コード = 引数.顧客コード
        //    識別コード = 引数.識別コード
        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_whereClause =
                " request_code = ? "
                    + "and institution_code = ? "
                    + "and branch_code = ? "
                    + "and account_code = ? "
                    + "and order_request_number = ? ";
            Object[] l_bindVars =
                {
                    WEB3HostRequestCodeDef.AIO_SLIP_ACCEPT,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strOrderRequestNumber };

            l_lisRows =
                l_queryProcessor.doFindAllQuery(
                    HostCashTransOrderAcceptRow.TYPE,
                    l_whereClause,
                    l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(
                " DBへのアクセスに失敗しました ",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(
                " DBへのアクセスに失敗しました ",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }

        String l_strKeyTableStatus = null;
        if (l_lisRows != null && l_lisRows.size() != 0)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
            HostCashTransOrderAcceptRow l_hostCashTransOrderAcceptRow = 
                (HostCashTransOrderAcceptRow) l_lisRows.get(0);
            l_strKeyTableStatus = l_hostCashTransOrderAcceptRow.getStatus();
        }

        // ３）注文単位が持つフラグと入出金伝票受付キューが持つフラグと引数のフラグから<BR>
        //    現在の入出金状況を判定し、その結果を返却する。<BR>
        log.debug("l_strStatus = " + l_strStatus);
        log.debug("l_strOrderStatusFlag = " + l_strOrderStatusFlag);
        log.debug("l_strStartStatusFlg = " + l_strStartStatusFlg);
        log.debug("l_strResultStatusFlag = " + l_strResultStatusFlag);
        log.debug("l_orderStatus = " + l_orderStatus);
        log.debug("l_strCancelType = " + l_strCancelType);
        log.debug("l_strKeyTableStatus = " + l_strKeyTableStatus);
        
        WEB3AioMutilBankStatusUtility l_statusUtility = new WEB3AioMutilBankStatusUtility();
        String l_strMessageCode =
            l_statusUtility.getStatus(
                l_strStatus,
                l_strOrderStatusFlag,
                l_strStartStatusFlg,
                l_strResultStatusFlag,
                (l_orderStatus == null) ? null : l_orderStatus.intValue() + "",
                l_strCancelType,
                l_strKeyTableStatus);

        log.exiting(STR_METHOD_NAME);
        return l_strMessageCode;
    }

    /**
     * (createHashMapFrom受信電文)<BR>
     * 受信電文から、HashMapを生成する。<BR>
     * <BR>
     * １）空のHashMapを生成する。<BR>
     * <BR>
     * ２）リクエストデータのメッセージボディから、1行読み込む。<BR>
     * <BR>
     * ２－１）その行の内容が <SHOPMSG> もしくは </SHOPMSG> の<BR>
     * 場合は、何もしない。<BR>
     * <BR>
     * ２－２）その行の内容が［キー］=［値］の形式の場合、<BR>
     * HashMapに登録する。<BR>
     * <BR>
     * ２－３）その行の内容が上記2パターン以外の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BaseRuntimeException<BR>
     *         tag:   BUSINESS_ERROR_00830<BR>
     * <BR>
     * ３）すべての行について、２）の処理を行う。<BR>
     * <BR>
     * ４）リクエストデータ.getRemoteAddr()の戻り値をキー"remoteAdd"で<BR>
     *     HashMap<BR>に登録する。<BR>
     * <BR>
     * ５）生成されたHashMapを返却する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)
     * @@return HashMap
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4118884E0225
     */
    public HashMap createHashMapFromReceiptTelegram(HttpServletRequest l_request) 
        throws IOException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "createHashMapFromReceiptTelegram("
                + "HttpServletRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）空のHashMapを生成する。
        HashMap l_hashMap = new HashMap();

        //２）リクエストデータからキーと値を取得する。
        String l_strKey = null;
        String l_strValue = null;
        for (Enumeration l_enumeration = l_request.getParameterNames(); l_enumeration.hasMoreElements();) {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
            l_strKey = l_enumeration.nextElement().toString();
            l_strValue = l_request.getParameter(l_strKey);
            log.debug("l_strKey = " + l_strKey);
            log.debug("l_strValue = " + l_strValue);
            l_hashMap.put(l_strKey, l_strValue);
        }

        //４）リクエストデータ.getRemoteAddr()の戻り値をキー"remoteAdd"で
        //     HashMap<BR>に登録する
        l_hashMap.put(WEB3AioTelegramFormatDef.remoteAdd, l_request.getRemoteAddr());

        //５）生成されたHashMapを返却する
        log.exiting(STR_METHOD_NAME);
        return l_hashMap;
    }
    
    public boolean contain(List l_lisAioSettleInstitutionUnit, 
        WEB3AioSettleInstitutionUnit l_aioSettleInstitutionUnit)
    {
        if(l_lisAioSettleInstitutionUnit == null 
            || l_lisAioSettleInstitutionUnit.size() == 0)
        {
            return false;
        }
        if(l_aioSettleInstitutionUnit == null)
        {
            return true;
        }
        
        for(int i = 0; i<l_lisAioSettleInstitutionUnit.size(); i++)
        {
            WEB3AioSettleInstitutionUnit l_temp = 
                (WEB3AioSettleInstitutionUnit) l_lisAioSettleInstitutionUnit.get(i);
            
            if(l_temp.paySchemeId.equals(l_aioSettleInstitutionUnit.paySchemeId))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 文字列とオブジェクトを比較します。<BR>
     * 両方ともnullの場合はtrueを返します。<BR>
     * @@param l_str 比較対象の文字列
     * @@param l_obj 比較対象のオブジェクト
     * @@return 一致する場合はtrue,そうでない場合はfalse
     */
    private boolean compare(
        String l_str,
        Object l_obj)
    {
        if (l_obj == null)
        {
            if (l_str == null)
            {
                return true;
            }
            else
            {
                return false; 
            }
        }
        else
        {
            if (l_obj.toString().equals(l_str))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
    /**
     * (getプリファ@レンス)<BR>
     * パラメータに指定された設定名称の設定値をオンライン入金プリファ@レンステーブルから取得する。<BR> 
     * <BR>
     * パラメータに指定された設定名称の設定値をシステムプリファ@レンステーブルから取得する。<BR> 
     * <BR>
     * １）システムプリファ@レンステーブルから以下の条件でレコードを取得する。 <BR>
     * <BR>
     * [取得条件] <BR>
     * 名称 = 引数.証券会社コード + 引数.設定名称 <BR>
     * <BR>
     * ２）取得したシステムプリファ@レンステーブルのレコードの設定値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strName - (設定名称)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD83011B
     */
    public String getPreference(
        String l_strInstitutionCode,
        String l_strName)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getPreferences(String l_strInstitutionCode, String l_strName)";
        log.entering(STR_METHOD_NAME);
        
        SystemPreferencesRow l_systemPreferencesRow = null;
        try
        {
            //１）システムプリファ@レンステーブルから以下の条件でレコードを取得する。 
            //[取得条件] 
            //名称 = 引数.証券会社コード + 引数.設定名称 
            l_systemPreferencesRow = SystemPreferencesDao.findRowByPk(
                    l_strInstitutionCode + l_strName);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        
        //２）取得したオンライン入金プリファ@レンステーブルのレコードの設定値を返却する。
        return l_systemPreferencesRow.getValue();
    }
    
    /**
     *  (getキャリア区分)<BR>
     * 注文経路区分からキャリア区分を取得し、値を変換する。<BR> 
     * <BR>
     * １）　@引数:注文経路区分 == WEB3OrderRootDivDef.I_MODEの場合、<BR> 
     * 　@　@　@キャリア区分：4（i-mode）を返却する。<BR> 
     * <BR>
     * ２）　@引数:注文経路区分 == WEB3OrderRootDivDef.VODAFONEの場合、<BR> 
     * 　@　@　@キャリア区分：5（vodafone）を返却する。 <BR>
     * <BR>
     * ３）　@引数:注文経路区分 == WEB3OrderRootDivDef.AUの場合、<BR> 
     * 　@　@　@キャリア区分：6（au）を返却する。<BR> 
     * <BR>
     * ４）　@1）～３）以外の場合、キャリア区分：2（モバイル以外）を返却する。<BR>
     * <BR>
     * @@param l_strOrderRootDiv - (注文経路区分) 
     * 1：call <BR>
     * 2：PC <BR>
     * 3：スリングショット <BR>
     * 4：i-mode <BR>
     * 5：vodafone <BR>
     * 6；au<BR> 
     * 7：スリングショット（無料） <BR>
     * 9：HOST <BR>
     * A：管理者 <BR>
     * B：保証金自動振替バッチ<BR>
     * @@return String
     */
    public String getCareerDiv(String l_strOrderRootDiv) 
    {
        String STR_METHOD_NAME =
            "getCareerDiv(String l_strOrderRootDiv)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数:注文経路区分 == WEB3OrderRootDivDef.I_MODEの場合、
        if (WEB3OrderRootDivDef.I_MODE.equals(l_strOrderRootDiv)) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3CareerDivDef.I_MODE;
        }
        
        //２）　@引数:注文経路区分 == WEB3OrderRootDivDef.VODAFONEの場合
        else if (WEB3OrderRootDivDef.VODAFONE.equals(l_strOrderRootDiv)) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3CareerDivDef.VODAFONE;   
        }
        
        //３）　@引数:注文経路区分 == WEB3OrderRootDivDef.AUの場合
        else if (WEB3OrderRootDivDef.AU.equals(l_strOrderRootDiv)) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3CareerDivDef.AU;    
        }
        
        //４）　@1）～３）以外の場合、キャリア区分：2（モバイル以外）を返却する
        else 
        {
            log.exiting(STR_METHOD_NAME);
            return  WEB3CareerDivDef.MOBILE_OTHER_PC;   
        }
        
    }
}
@
