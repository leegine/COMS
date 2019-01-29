head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票登録受付UnitServiceImpl(WEB3AccOpenVoucherRegAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 鄭海良(中訊) 新規作成
                   2006/07/12 黄建 (中訊) 仕様変更 モデル072、080
                   2006/09/12 李俊 (中訊) 仕様変更 モデル099
Revesion History : 2007/09/20 張騰宇 (中訊) 仕様変更 モデル146
Revesion History : 2009/08/13 柴双紅 (中訊) 仕様変更 モデル176
Revesion History : 2009/09/04 張騰宇 (中訊) 仕様変更 モデル211
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenVoucherStatusPK;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccRegVoucherPK;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherPK;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherRow;
import webbroker3.accountopen.data.HostAgreeTransVoucherPK;
import webbroker3.accountopen.data.HostAgreeTransVoucherRow;
import webbroker3.accountopen.data.HostBankTransVoucherPK;
import webbroker3.accountopen.data.HostBankTransVoucherRow;
import webbroker3.accountopen.data.HostChargedInfoVoucherPK;
import webbroker3.accountopen.data.HostChargedInfoVoucherRow;
import webbroker3.accountopen.data.HostConditionRegVoucherPK;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.accountopen.data.HostContMrgVoucherPK;
import webbroker3.accountopen.data.HostContMrgVoucherRow;
import webbroker3.accountopen.data.HostFDepositVoucherPK;
import webbroker3.accountopen.data.HostFDepositVoucherRow;
import webbroker3.accountopen.data.HostGpRegVoucherPK;
import webbroker3.accountopen.data.HostGpRegVoucherRow;
import webbroker3.accountopen.data.HostInsiderRegVoucherPK;
import webbroker3.accountopen.data.HostInsiderRegVoucherRow;
import webbroker3.accountopen.data.HostMrfAccVoucherPK;
import webbroker3.accountopen.data.HostMrfAccVoucherRow;
import webbroker3.accountopen.data.HostPostalTransVoucherPK;
import webbroker3.accountopen.data.HostPostalTransVoucherRow;
import webbroker3.accountopen.data.HostRealnameRegVoucherPK;
import webbroker3.accountopen.data.HostRealnameRegVoucherRow;
import webbroker3.accountopen.data.HostStockholderRegVoucherPK;
import webbroker3.accountopen.data.HostStockholderRegVoucherRow;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設伝票登録受付UnitServiceImpl)<BR>
 * 口座開設伝票登録受付１件サービス実装クラス<BR>
 * （トランザクション属性=TX_CREATE_NEW）<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenVoucherRegAcceptUnitServiceImpl implements WEB3AccOpenVoucherRegAcceptUnitService
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenVoucherRegAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 41B45E74003E
     */
    public WEB3AccOpenVoucherRegAcceptUnitServiceImpl()
    {

    }

    /**
     * (notify伝票登録受付)<BR>
     * 口座開設伝票登録受付１件処理を実施し、処理結果（処理済／エラー）を<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@登録キューデータ取得<BR>
     * 　@受付キュー.データコードに対応する登録キューを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@登録キューテーブル(*).識別コード = 受付キュー.識別コード And　@<BR>
     * ※登録キュー.識別コードは、伝票の識別コード（order_request_number）<BR>
     * 　@登録キューテーブル(*).データコード = 受付キュー.データコード And<BR>
     * 　@登録キューテーブル(*).証券会社コード = 受付キュー.証券会社コード And<BR>
     * 　@登録キューテーブル(*).部店コード = 受付キュー.部店コード And<BR>
     * 　@登録キューテーブル(*).顧客コード = 受付キュー.顧客コード<BR>
     * <BR>
     * 　@(*) [データコードに対応する登録キューテーブル名]<BR>
     * 　@GI82A：顧客登録受付　@→　@顧客登録伝票(G11)キューテーブル<BR>
     * 　@GI82G：契約書徴収受付　@→　@契約書徴収伝票（G1151）キューテーブル<BR>
     * 　@GI82C：振替申込（銀行）受付　@→　@振替申込（銀行）伝票（G26）キューテーブル<BR>
     * 　@GI82H：振替申込（郵貯）受付　@→　@振替申込（郵貯）伝票（G26）キューテーブル<BR>
     * 　@GI82B：保振同意受付　@→　@保振同意伝票（GA300）キューテーブル<BR>
     * 　@GI83G：有料情報受付　@→　@有料情報伝票（G5401）キューテーブル<BR>
     * 　@GI82E：MRF口座受付　@→　@MRF口座伝票（GI601)伝票キューテーブル<BR>
     * 　@GI81I：内部者登録受付　@→　@内部者登録伝票（G9801）キューテーブル <BR>
     * 　@GI82D：GP条件登録受付　@→　@GP条件登録伝票（G1220）キューテーブル<BR>
     * 　@GI84I：上場外株・登録受付　@→　@上場外株・株主登録伝票（G8610）キューテーブル <BR>
     * 　@GI84H：顧客名称登録受付　@→　@顧客正式名称登録伝票（G1190）キューテーブル <BR>
     * 　@GI84E：顧客登録（仲介業）受付　@→　@顧客登録伝票(G11)キューテーブル <BR>
     * 　@GI85D：外貨預金口座登録受付　@→　@外貨預金口座登録伝票(G43)キューテーブル <BR>
     * 　@GI84C：取報・取残電子交付・特定口座登録受付　@→　@<BR>
     * 　@取報・取残電子交付・特定口座登録（GI311）キューテーブル<BR>
     * 　@GI86E：機@構通知情報登録受付　@→　@機@構通知情報登録伝票（GS103）キューテーブル<BR>
     * <BR>
     * 　@取得できなかった場合は、処理区分.エラー を返却する。<BR>
     * <BR>
     * 　@ＭＲＦ口座受付（受付キュー.データコード == GI82E：MRF口座受付）の場合で、<BR>
     * 　@（取得した登録キュー.登録区分 != 新規）の場合は、nullを返却する。<BR>
     * <BR>
     * ２）　@口座開設伝票作成ステータステーブル更新（DB-update）。<BR>
     * 　@口座開設伝票作成ステータステーブルを、DB更新仕様の通り更新する。<BR>
     * <BR>
     * 　@[更新条件（PK）]<BR>
     * 　@証券会社コード = 受付キュー.証券会社コード<BR>
     * 　@識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）<BR>
     * 　@データコード = 受付キュー.データコード<BR>
     * 　@伝票通番 = １）で取得した登録キュー.伝票通番<BR>
     * <BR>
     * 　@更新内容は、DB更新仕様「受付_口座開設伝票作成ステータス<BR>
     * DB更新仕様.xls」<BR>
     * 　@　@－（受付キュー.受付結果 == 1：受付完了）の場合、「受付済」シート参照。<BR>
     * 　@　@－（受付キュー.受付結果 == 2：エラー）の場合、「受付エラー」シート参照。<BR>
     * <BR>
     * １）～２）の処理で、エラーが発生した場合、処理区分.エラー を返却する。<BR>
     * 以外、処理区分.処理済　@を返却する。<BR>
     * @@param l_accOpenAcceptParams - 口座開設伝票登録受付キュー<BR>
     * <BR>
     * ※　@口座開設伝票登録受付キューParamsクラスは、DDLより自動生成する。<BR>
     *
     * @@return String
     * @@roseuid 41A19BD700CD
     */
    public String notifyVoucherRegAccept(HostAccOpenAcceptParams l_accOpenAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " notifyVoucherRegAccept(HostAccOpenAcceptParams)";
        log.entering(STR_METHOD_NAME);

        if (l_accOpenAcceptParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        String l_strSerialNo = null;

        String l_strReqCode = l_accOpenAcceptParams.getRequestCode();
        String l_strOrderReqNumber = l_accOpenAcceptParams.getOrderRequestNumber();
        String l_strInstitutionCode = l_accOpenAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_accOpenAcceptParams.getBranchCode();
        String l_strAccountCode = l_accOpenAcceptParams.getAccountCode();
        String l_strAccOpenRequestNumber = null;

        String[] l_acceptReqCodes = 
            new String[]{"GI82A","GI82G","GI82C","GI82H","GI82B","GI83G","GI82E", 
                         "GI81I", "GI82D", "GI84I", "GI84H", "GI84E", "GI85D", "GI84C", "GI86E"};
        String[] l_registReqCodes = 
            new String[]{"GI821","GI827","GI823","GI828","GI822","GI837","GI825", 
                         "GI819", "GI824", "GI849", "GI848", "GI845", "GI854", "GI843", "GI865"};

        String l_strRegistReqCode = null;
        for (int i = 0; i < l_acceptReqCodes.length; i++)
        {
            if (l_acceptReqCodes[i].equals(l_strReqCode))
            {
                l_strRegistReqCode = l_registReqCodes[i];
                break;
            }
        }

        try
        {
            //１）　@登録キューデータ取得
            
            //GI82A：顧客登録受付　@→　@顧客登録伝票(G11)キューテーブル
            if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT.equals(l_strReqCode))
            {
                HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAccRegVoucherRow l_row =
                    (HostAccRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataFindException,DataQueryException,DataNetworkException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //GI82G：契約書徴収受付　@→　@契約書徴収伝票（G1151）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONTRACT_COLLECT_ACCEPT.equals(l_strReqCode))
            {
                HostContMrgVoucherPK l_pk = new HostContMrgVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostContMrgVoucherRow l_row =
                    (HostContMrgVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException

                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //　@GI82C：振替申込（銀行）受付　@→　@振替申込（銀行）伝票（G26）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT.equals(l_strReqCode))
            {
                HostBankTransVoucherPK l_pk = new HostBankTransVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostBankTransVoucherRow l_row =
                    (HostBankTransVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();

            }
            
            //GI82H：振替申込（郵貯）受付　@→　@振替申込（郵貯）伝票（G26）キューテーブル 
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT.equals(l_strReqCode))
            {
                HostPostalTransVoucherPK l_pk = new HostPostalTransVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostPostalTransVoucherRow l_row =
                    (HostPostalTransVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //GI82B：保振同意受付　@→　@保振同意伝票（GA300）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGREE_TRANSFER_ACCEPT.equals(l_strReqCode))
            {
                HostAgreeTransVoucherPK l_pk = new HostAgreeTransVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAgreeTransVoucherRow l_row =
                    (HostAgreeTransVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //　@GI83G：有料情報受付　@→　@有料情報伝票（G5401）キューテーブル 
            else if (WEB3HostRequestCodeDef.ACCOPEN_CHARGED_INFO_ACCEPT.equals(l_strReqCode))
            {
                HostChargedInfoVoucherPK l_pk = new HostChargedInfoVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostChargedInfoVoucherRow l_row =
                    (HostChargedInfoVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //GI82E：MRF口座受付　@→　@MRF口座伝票（GI601)伝票キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_MRF_ACCOUNT_ACCEPT.equals(l_strReqCode))
            {
                HostMrfAccVoucherPK l_pk = new HostMrfAccVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostMrfAccVoucherRow l_row =
                    (HostMrfAccVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                if (!WEB3RegDivDef.NEW.equals(l_row.getRegistDiv()))
                {
                    log.debug("登録キュー.登録区分 != 新規," + l_row.getRegistDiv());
                    return null;
                }
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }

            //GI81I：内部者登録受付　@→　@内部者登録伝票（G9801）キューテーブル 
            else if (WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_ACCEPT.equals(l_strReqCode))
            {
                HostInsiderRegVoucherPK l_pk = new HostInsiderRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostInsiderRegVoucherRow l_row =
                    (HostInsiderRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();     
            }
            
            //　@GI82D：GP条件登録受付　@→　@GP条件登録伝票（G1220）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_GP_REG_ACCEPT.equals(l_strReqCode))
            {
                HostGpRegVoucherPK l_pk = new HostGpRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostGpRegVoucherRow l_row =
                    (HostGpRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();     
            }

            //　@GI84I：上場外株・登録受付　@→　@上場外株・株主登録伝票（G8610）キューテーブル 
            else if (WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_ACCEPT.equals(l_strReqCode))
            {
                HostStockholderRegVoucherPK l_pk = new HostStockholderRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostStockholderRegVoucherRow l_row =
                    (HostStockholderRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();     
            }
            
            //GI84H：顧客名称登録受付　@→　@顧客正式名称登録伝票（G1190）キューテーブル 
            else if (WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_ACCEPT.equals(l_strReqCode))
            {
                HostRealnameRegVoucherPK l_pk = new HostRealnameRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostRealnameRegVoucherRow l_row =
                    (HostRealnameRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();     
            }

            //GI84E：顧客登録（仲介業）受付　@→　@顧客登録伝票(G11)キューテーブル 
            else if (WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(l_strReqCode))
            {
                HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAccRegVoucherRow l_row =
                    (HostAccRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataFindException,DataQueryException,DataNetworkException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();   
            }
            
            //GI85D：外貨預金口座登録受付　@→　@外貨預金口座登録伝票(G43)キューテーブル 
            else if (WEB3HostRequestCodeDef.F_DEPOSIT_REG_ACCEPT.equals(l_strReqCode))
            {
                HostFDepositVoucherPK l_pk = new HostFDepositVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostFDepositVoucherRow l_row =
                    (HostFDepositVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataFindException,DataQueryException,DataNetworkException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();   
            }

            //  GI84C：取報・取残電子交付・特定口座登録受付　@→
            //　@取報・取残電子交付・特定口座登録（GI311）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(l_strReqCode))
            {
                HostConditionRegVoucherPK l_pk = new HostConditionRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostConditionRegVoucherRow l_row =
                    (HostConditionRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            //GI86E：機@構通知情報登録受付　@→　@機@構通知情報登録伝票（GS103）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REG_ACCEPT.equals(l_strReqCode))
            {
                HostAgencyNotifyVoucherPK l_voucherPK = new HostAgencyNotifyVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAgencyNotifyVoucherRow l_voucherRow =
                    (HostAgencyNotifyVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_voucherPK);
                l_strSerialNo = l_voucherRow.getSerialNo();
                l_strAccOpenRequestNumber = l_voucherRow.getAccOpenRequestNumber();
            }
            else
            {
                log.debug("受付キュー.データコードエラー:" + l_strReqCode);
                log.exiting(STR_METHOD_NAME);
                return WEB3HostStatusDef.DATA_ERROR;
            }

            //(*2) 受付キューが、GI84E：顧客登録（仲介業）受付の場合
            //データコードは"GI821"（顧客登録受付）で検索する
            if (WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(l_strReqCode))
            {
                l_strRegistReqCode = "GI821";
            }

            //２）　@口座開設伝票作成ステータステーブル更新（DB-update）
            AccOpenVoucherStatusPK l_pkStatus = new AccOpenVoucherStatusPK(
                l_strInstitutionCode,
                l_strAccOpenRequestNumber,//l_strOrderReqNumber,
                l_strRegistReqCode,//l_strReqCode,
                l_strSerialNo);//DataException
            Map l_mapStatus = new Hashtable();
            if (WEB3AcceptStatusDef.OVER.equals(l_accOpenAcceptParams.getAcceptStatus()))
            {
                //受付完了
                l_mapStatus.put("voucher_status",WEB3VoucherStatusDef.RECEIVE_COMPLETE);
                l_mapStatus.put("recv_timestamp",GtlUtils.getTradingSystem().getSystemTimestamp());
                l_mapStatus.put("last_updated_timestamp",GtlUtils.getTradingSystem().getSystemTimestamp());
            }
            else if (WEB3AcceptStatusDef.ERROR.equals(l_accOpenAcceptParams.getAcceptStatus()))
            {
                //エラー
                l_mapStatus.put("voucher_status",WEB3VoucherStatusDef.RECEIVE_ERROR);
                l_mapStatus.put("recv_timestamp",GtlUtils.getTradingSystem().getSystemTimestamp());
                l_mapStatus.put("error_code",l_accOpenAcceptParams.getErrorCode());
                l_mapStatus.put("last_updated_timestamp",GtlUtils.getTradingSystem().getSystemTimestamp());
            }

            Processors.getDefaultProcessor().doUpdateQuery(l_pkStatus, l_mapStatus);//DataException
        }
        catch (DataFindException l_e)
        {
            log.error(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage() + "[" +
                "データコード:" + l_strReqCode +
                ", 証券会社コード:" + l_strInstitutionCode +
                ", 部店コード:" + l_strBranchCode +
                ", 口座コード:" + l_strAccountCode +
                ", 識別コード:" + l_strOrderReqNumber + "]"
                );
            return WEB3HostStatusDef.DATA_ERROR;
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        l_accOpenAcceptParams.setStatus(WEB3HostStatusDef.COMPLETE_PROCESS);
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_accOpenAcceptParams);//DataException
        }
        catch (DataException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
        return WEB3HostStatusDef.COMPLETE_PROCESS;
    }
}
@
