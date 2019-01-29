head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInfoCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設情報作成サービスImpl(WEB3AccOpenInfoCreatedServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 郭英 (中訊) 新規作成
                 : 2006/06/08 周捷(中訊) 仕様変更・モデル048、050
                 : 2006/07/13 黄建(中訊) 仕様変更・モデル072、075、078 ＤＢ更新仕様007            
                 : 2006/08/14 張騰宇(中訊) 仕様変更・モデル087       
Revesion History : 2009/08/12 張騰宇(中訊) ＤＢレイアウト053、056
Revesion History : 2009/08/13 武波 (中訊) 仕様変更 モデル No.172
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenInvalidValues;
import webbroker3.accountopen.WEB3AccOpenJudgeWaiting;
import webbroker3.accountopen.WEB3AccOpenMailAddressDuplicationCheck;
import webbroker3.accountopen.WEB3AccOpenTelNumberDuplicationCheck;
import webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus;
import webbroker3.accountopen.data.AccOpenInvalidValuesParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.define.WEB3AccOpenApplyInfoItemDef;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.message.WEB3AccOpenAgencyInfo;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.message.WEB3AccOpenForeignSaveInfo;
import webbroker3.accountopen.message.WEB3AccOpenGpInfo;
import webbroker3.accountopen.message.WEB3AccOpenInsiderInfo;
import webbroker3.accountopen.message.WEB3AccOpenInterestDealingInfo;
import webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo;
import webbroker3.accountopen.message.WEB3AccOpenInvestmentExperienceInfo;
import webbroker3.accountopen.message.WEB3AccOpenListedFeqInfo;
import webbroker3.accountopen.message.WEB3AccOpenOfficeInfo;
import webbroker3.accountopen.message.WEB3AccOpenPostalTransferInfo;
import webbroker3.accountopen.message.WEB3AccOpenTransferBankInfo;
import webbroker3.accountopen.message.WEB3AccOpenVoucherInfo;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExistenceDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

/**
 * (口座開設情報作成サービスImpl)<BR>
 * 口座開設情報作成サービス実装クラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AccOpenInfoCreatedServiceImpl implements WEB3AccOpenInfoCreatedService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenInfoCreatedServiceImpl.class);

    /**
     * @@roseuid 41B45E7203C8
     */
    public WEB3AccOpenInfoCreatedServiceImpl() 
    {
     
    }
    
    /**
     * (to口座開設申込情報)<BR>
     * 口座開設見込客オブジェクトより、口座開設申込情報を生成する。<BR>
     * <BR>
     * １）　@オブジェクト生成<BR>
     * 　@口座開設申込情報オブジェクト，投資経験情報オブジェクト，<BR>
     * 興味のある取引情報オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@プロパティセット<BR>
     * 　@口座開設見込客.口座開設見込客行オブジェクトより、生成した<BR>
     * メッセージオブジェクトの対応する項目に値をセットし返却する。<BR>
     * 　@但し、暗証番号（初期パスワード）は編集しない。<BR>
     * <BR>
     * 　@※ BooleanEnum型の項目は、対応するboolean値に変換すること。<BR>
     * 　@※ （口座開設見込客行.振替区分 == 銀行振込）の場合のみ、<BR>
     * 振込先銀行情報オブジェクトを生成しプロパティをセットする。<BR>
     * 　@　@　@以外の場合、口座開設申込情報.振込先銀行情報にnullをセットする。<BR>
     * 　@※ （口座開設見込客行.振替区分 == 郵貯振替）の場合のみ、<BR>
     * 郵便振替情報オブジェクトを生成しプロパティをセットする。<BR>
     * 　@　@　@以外の場合、口座開設申込情報.郵便振替情報にnullをセットする。<BR>
     *  ※ 暗証番号（初期パスワード）は、復号化（WEB3Crypt.decrypt()）してセットする。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * 
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyInfo
     * @@roseuid 4191A5FE01AA
     */
    public WEB3AccOpenApplyInfo toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) 
    {
        final String STR_METHOD_NAME = " toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenExpAccountOpen == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）　@オブジェクト生成
        WEB3AccOpenApplyInfo l_applyInfo = new WEB3AccOpenApplyInfo();
        WEB3AccOpenInvestmentExperienceInfo l_investmentExperienceInfo = 
            new WEB3AccOpenInvestmentExperienceInfo();
        WEB3AccOpenInterestDealingInfo l_interestDealingInfo = 
            new WEB3AccOpenInterestDealingInfo();
        WEB3AccOpenInsiderInfo l_insiderInfo = new WEB3AccOpenInsiderInfo();
        WEB3AccOpenGpInfo l_gpInfo = new WEB3AccOpenGpInfo();
        WEB3AccOpenListedFeqInfo l_listedFeqInfo = new WEB3AccOpenListedFeqInfo();
        WEB3AccOpenForeignSaveInfo l_foreignSaveInfo = new WEB3AccOpenForeignSaveInfo();
        
        //２）　@プロパティセット
        ExpAccountOpenRow l_row = (ExpAccountOpenRow)
            l_accOpenExpAccountOpen.getDataSourceObject();
        //資料請求日
        l_applyInfo.infoClaimDate = l_row.getInfomationClaimDatetime();
    
        //更新日
        l_applyInfo.updateDate = l_row.getLastUpdatedTimestamp();
    
        //更新者コード
        l_applyInfo.updaterCode = l_row.getLastUpdater();
    
        //識別コード
        l_applyInfo.requestNumber = l_row.getAccOpenRequestNumber();
        
        //入力区分
        l_applyInfo.inputDiv = l_row.getOrderDiv();
    
        //作成者コード
        l_applyInfo.creatorCode = l_row.getCreator();
    
        //既存口座フラグ
        if (BooleanEnum.TRUE.equals(l_row.getExAccountFlag()))
        {
            l_applyInfo.exAccountFlag = true;
        }
        else
        {
            l_applyInfo.exAccountFlag = false;
        }
    
        //既存口座コード
        l_applyInfo.exAccountCode = l_row.getExAccountCode();
    
        //既存口座部店名
        l_applyInfo.exAccountBranchName = l_row.getExBranchName();
    
        //証券会社コード
        l_applyInfo.institutionCode = l_row.getInstitutionCode();
    
        //部店コード
        l_applyInfo.branchCode = l_row.getBranchCode();
    
        //口座区分
        l_applyInfo.accountType = l_row.getAccountDiv();
    
        //顧客コード
        l_applyInfo.accountCode = l_row.getAccountCode();
    
        //扱者コード（SONAR）
        l_applyInfo.traderCode = l_row.getSonarTraderCode();
    
        //メールアドレス
        l_applyInfo.mailAddress = l_row.getEmailAddress();
    
        //メールアドレス（携帯）
        l_applyInfo.mobileMailAddress = l_row.getEmailAddressAlt1();
    
        //顧客姓（漢字）
        l_applyInfo.accountFamilyName = l_row.getFamilyName();
    
        //顧客名（漢字）
        l_applyInfo.accountName = l_row.getGivenName();
    
        //顧客姓（カナ）
        l_applyInfo.accountFamilyNameKana = l_row.getFamilyNameAlt1();
    
        //顧客名（カナ）
        l_applyInfo.accountNameKana = l_row.getGivenNameAlt1();
    
        //生年月日年号
        l_applyInfo.eraBorn = l_row.getEraBorn();
    
        //生年月日
        l_applyInfo.bornDate = l_row.getBornDate();
    
        //性別
        l_applyInfo.sex = l_row.getSex();
        
        //暗証番号
        WEB3Crypt l_crypt = new WEB3Crypt();
        l_applyInfo.password = l_crypt.decrypt(l_row.getInitialPassword());
    
        //郵便番号
        l_applyInfo.zipCode = l_row.getZipCode();
    
        //住所１（カナ）
        l_applyInfo.addressKana1 = l_row.getAddressLine1Kana();
    
        //住所２（カナ）
        l_applyInfo.addressKana2 = l_row.getAddressLine2Kana();
    
        //住所３（カナ）
        l_applyInfo.addressKana3 = l_row.getAddressLine3Kana();
    
        //住所１（漢字）
        l_applyInfo.address1 = l_row.getAddressLine1();
    
        //住所２（漢字）
        l_applyInfo.address2 = l_row.getAddressLine2();
    
        //住所３（漢字）
        l_applyInfo.address3 = l_row.getAddressLine3();
    
        //電話番号
        l_applyInfo.telephone = l_row.getTelephone();
    
        //携帯番号
        l_applyInfo.mobileTelephone = l_row.getMobile();
    
        //ＦＡＸ番号
        l_applyInfo.faxTelephone = l_row.getFax();
    
        //勤務先情報
        WEB3AccOpenOfficeInfo l_officeInfo = new WEB3AccOpenOfficeInfo();
        
        //職業区分
        l_officeInfo.occupationDiv = l_row.getOccupationDiv();
    
        //勤務先名称
        l_officeInfo.officeName = l_row.getOffice();
    
        //勤務先郵便番号<BR>
        l_officeInfo.officeZipCode = l_row.getOfficeZipCode();
    
        //勤務先住所
        l_officeInfo.officeAdress = l_row.getOfficeAddress();
    
        //勤務先電話番号
        l_officeInfo.officeTelephone = l_row.getOfficeTelephone();
    
        //勤務先ＦＡＸ番号
        l_officeInfo.officeFaxTelephone = l_row.getOfficeFax();
    
        //所属部署
        l_officeInfo.department = l_row.getDepartment();
    
        //役職名
        l_officeInfo.position = l_row.getPost();
        
        l_applyInfo.officeInfo = l_officeInfo;
    
        //連絡先住所
        l_applyInfo.contactAddress = l_row.getContactAddress();
    
        //連絡先電話番号
        l_applyInfo.contactTelephone = l_row.getContactTelephone();
    
        //続柄区分
        l_applyInfo.familyRelationDiv = l_row.getFamilyRelationship();
    
        //続柄（その他）
        l_applyInfo.familyRelationEtc = l_row.getFamilyRelationshipEtc();
    
        //世帯主名（漢字）
        l_applyInfo.houseHolderName = l_row.getHouseholder();
    
        //世帯主名（カナ）
        l_applyInfo.houseHolderNameKana = l_row.getHouseholderKana();
    
        //世帯主勤務先情報
        WEB3AccOpenOfficeInfo l_officeInfo2 = new WEB3AccOpenOfficeInfo();
        
        //職業区分
        l_officeInfo2.occupationDiv = l_row.getHouseholderOccupationDiv();
            
        //勤務先名称
        l_officeInfo2.officeName = l_row.getHouseholderOffice();
            
        //勤務先住所
        l_officeInfo2.officeAdress = l_row.getHouseholderOfficeAddress();
            
        //勤務先電話番号
        l_officeInfo2.officeTelephone = l_row.getHouseholderOfficeTel();
            
        //勤務先ＦＡＸ番号
        l_officeInfo2.officeFaxTelephone = l_row.getHouseholderOfficeFax();
            
        //所属部署
        l_officeInfo2.department = l_row.getHouseholderDepartment();
            
        //役職名
        l_officeInfo2.position = l_row.getHouseholderPost(); 
        
        l_applyInfo.houseHolderOfficeInfo = l_officeInfo2;
    
        //居住（日本）／非居住（日本以外）区分
        l_applyInfo.residentDiv = l_row.getResident();
    
        //振替区分
        l_applyInfo.transferDiv = l_row.getTransferDiv();
    
        //口座名義人
        l_applyInfo.financialAccountName = l_row.getFinAccountName();
    
        //投資目的区分
        l_applyInfo.investPurposeDiv = l_row.getInvestPurposeDiv();
    
        //取引動機@区分
        l_applyInfo.appliMotivatDiv = l_row.getAppliMotivatDiv();
    
        //年収区分
        l_applyInfo.annualIncomeDiv = l_row.getAnnualIncomeDiv();
    
        //金融資産区分
        l_applyInfo.assetValueDiv = l_row.getAssetValueDiv();
    
        //運用予定額区分
        l_applyInfo.fundBundgetAmountDiv = l_row.getFundBudgetAmountDiv();
    
        //資産の性格区分
        l_applyInfo.fundBundgetDiv = l_row.getFundBudgetDiv();
    
        //資金の性格（その他
        l_applyInfo.fundBundgetEtc = l_row.getFundBudgetEtc();
    
        //検索用区分
        if (BooleanEnum.TRUE.equals(l_row.getIdConfirmFlag()))
        {
            l_applyInfo.searchDiv = WEB3ExistenceDivDef.HAVING;
        }
        else
        {
            l_applyInfo.searchDiv = WEB3ExistenceDivDef.NOT_HAVING;
        }
    
        //本人確認書類区分
        l_applyInfo.idConfirmDocDiv = l_row.getIdConfirmDocDiv();
    
        //本人確認書類（その他
        l_applyInfo.idConfirmDocEtc = l_row.getIdConfirmDocEtc();
    
        //現物株式口座区分
        l_applyInfo.equityTaxType = l_row.getSpecialAcc();
    
        //信用取引口座区分
        l_applyInfo.marginTaxType = l_row.getSpecialAccMargin();
    
        //内部者登録フラグ
        if (BooleanEnum.TRUE.equals(l_row.getInsiderFlag()))
        {
            l_applyInfo.insiderFlag = true;
        }
        else
        {
            l_applyInfo.insiderFlag = false;
        }
    
        //内部者銘柄名
        l_applyInfo.insiderProductName = l_row.getProductName();
    
        //送付先郵便番号
        l_applyInfo.sendZipCode = l_row.getSendZipCode();
    
        //送付先住所１
        l_applyInfo.sendAddress1 = l_row.getSendAddressLine1();
    
        //送付先住所２
        l_applyInfo.sendAddress2 = l_row.getSendAddressLine2();
    
        //送付先住所３
        l_applyInfo.sendAddress3 = l_row.getSendAddressLine3();
    
        //区分1
        l_applyInfo.extItemDiv1 = l_row.getExtItemDiv1();
    
        //区分2
        l_applyInfo.extItemDiv2 = l_row.getExtItemDiv2();
    
        //区分3
        l_applyInfo.extItemDiv3 = l_row.getExtItemDiv3();
    
        //区分4
        l_applyInfo.extItemDiv4 = l_row.getExtItemDiv4();
    
        //区分5
        l_applyInfo.extItemDiv5 = l_row.getExtItemDiv5();
    
        //区分6
        l_applyInfo.extItemDiv6 = l_row.getExtItemDiv6();
    
        //区分7
        l_applyInfo.extItemDiv7 = l_row.getExtItemDiv7();
    
        //区分8
        l_applyInfo.extItemDiv8 = l_row.getExtItemDiv8();
    
        //区分9
        l_applyInfo.extItemDiv9 = l_row.getExtItemDiv9();
    
        //区分10
        l_applyInfo.extItemDiv10 = l_row.getExtItemDiv10();
    
        //フラグ１
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag1()))
        {
            l_applyInfo.extItemFlag1 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag1()))
        {
            l_applyInfo.extItemFlag1 = false;
        }

        //フラグ2
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag2()))
        {
            l_applyInfo.extItemFlag2 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag2()))
        {
            l_applyInfo.extItemFlag2 = false;
        }
    
        //フラグ3
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag3()))
        {
            l_applyInfo.extItemFlag3 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag3()))
        {
            l_applyInfo.extItemFlag3 = false;
        }
    
        //フラグ4
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag4()))
        {
            l_applyInfo.extItemFlag4 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag4()))
        {
            l_applyInfo.extItemFlag4 = false;
        }
    
        //フラグ5
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag5()))
        {
            l_applyInfo.extItemFlag5 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag5()))
        {
            l_applyInfo.extItemFlag5 = false;
        }
    
        //フラグ6
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag6()))
        {
            l_applyInfo.extItemFlag6 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag6()))
        {
            l_applyInfo.extItemFlag6 = false;
        }
    
        //フラグ7
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag7()))
        {
            l_applyInfo.extItemFlag7 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag7()))
        {
            l_applyInfo.extItemFlag7 = false;
        }
    
        //フラグ8
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag8()))
        {
            l_applyInfo.extItemFlag8 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag8()))
        {
            l_applyInfo.extItemFlag8 = false;
        }
    
        //フラグ9
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag9()))
        {
            l_applyInfo.extItemFlag9 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag9()))
        {
            l_applyInfo.extItemFlag9 = false;
        }
    
        //フラグ10
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag10()))
        {
            l_applyInfo.extItemFlag10 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag10()))
        {
            l_applyInfo.extItemFlag10 = false;
        }
    
        //テキスト1
        l_applyInfo.extItemText1 = l_row.getExtItemText1();
    
        //テキスト2
        l_applyInfo.extItemText2 = l_row.getExtItemText2();
    
        //テキスト3
        l_applyInfo.extItemText3 = l_row.getExtItemText3();
    
        //テキスト4
        l_applyInfo.extItemText4 = l_row.getExtItemText4();
    
        //テキスト5
        l_applyInfo.extItemText5 = l_row.getExtItemText5();
    
        //テキスト6
        l_applyInfo.extItemText6 = l_row.getExtItemText6();
    
        //テキスト7
        l_applyInfo.extItemText7 = l_row.getExtItemText7();
    
        //テキスト8
        l_applyInfo.extItemText8 = l_row.getExtItemText8();
    
        //テキスト9
        l_applyInfo.extItemText9 = l_row.getExtItemText9();
        
        //テキスト10
        l_applyInfo.extItemText10 = l_row.getExtItemText10();
    
        //振込先銀行情報
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_applyInfo.transferDiv))
        {
            WEB3AccOpenTransferBankInfo l_transferBankInfo = 
                new WEB3AccOpenTransferBankInfo();
                
            //銀行コード
            l_transferBankInfo.financialInstitutionCode = l_row.getFinInstitutionCode();
    
            //銀行名
            l_transferBankInfo.financialInstitutionName = l_row.getFinInstitutionName();
    
            // 支店コード
            l_transferBankInfo.financialBranchCode = l_row.getFinBranchCode();
    
            //支店名
            l_transferBankInfo.financialBranchName = l_row.getFinBranchName();
    
            //預金区分
            l_transferBankInfo.financialAccountDiv = l_row.getFinSaveDiv();
    
            //口座番号
            l_transferBankInfo.financialAccountCode = l_row.getFinAccountNo();
    
            //振替手数料区分
            l_transferBankInfo.transferCommissionDiv = l_row.getTransCommission();
        
            l_applyInfo.transferBankInfo = l_transferBankInfo;
        }
        else
        {
            l_applyInfo.transferBankInfo = null;
        }
        
    
        //郵便振替情報
        if (WEB3TransferDivDef.POSTAL_TRANSFER.equals(l_applyInfo.transferDiv))
        {
            WEB3AccOpenPostalTransferInfo l_postalTransferInfo = 
                new WEB3AccOpenPostalTransferInfo();
            
            //通帳番号
            l_postalTransferInfo.passbookCode = l_row.getPostalSaveNo();
    
            //通帳記号
            l_postalTransferInfo.passbookSign = l_row.getPostalSaveCode();
            
            l_applyInfo.postalTransferInfo = l_postalTransferInfo;
        }
        else
        {
            l_applyInfo.postalTransferInfo = null;
        }
    
        //投資経験情報
        //現物株式
        l_investmentExperienceInfo.experienceEquityDiv = l_row.getExperienceDivEquity();
    
        //信用取引
        l_investmentExperienceInfo.experienceMarginDiv = l_row.getExperienceDivMargin();
    
        //債券
        l_investmentExperienceInfo.experienceBondDiv = l_row.getExperienceDivBond();
    
        //転換社債
        l_investmentExperienceInfo.experienceWtDiv = l_row.getExperienceDivWt();
    
        //投資信託（株式）
        l_investmentExperienceInfo.experienceFundSkDiv = l_row.getExperienceDivFundSk();
    
        //投資信託（債券）
        l_investmentExperienceInfo.experienceFundBdDiv = l_row.getExperienceDivFundBd();
    
        //先物・オプション
        l_investmentExperienceInfo.experienceFoDiv = l_row.getExperienceDivFo();
    
        //外国証券
        l_investmentExperienceInfo.experienceFEquityDiv = l_row.getExperienceDivFEquity();
    
        //その他
        l_investmentExperienceInfo.experienceEtcDiv = l_row.getExperienceDivEtc();
        
        l_applyInfo.investExpInfo = l_investmentExperienceInfo;
    
        //興味のある取引情報
        //現物株式フラグ
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagEquity()))
        {
            l_interestDealingInfo.interestEquityFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestEquityFlag = false;
        }
    
        //株式ミニ投資フラグ
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagMinistock()))
        {
            l_interestDealingInfo.interestMstkFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestMstkFlag = false;
        }
    
        //信用取引フラグ
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagMargin()))
        {
            l_interestDealingInfo.interestMarginFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestMarginFlag = false;
        }
    
        //債券フラグ
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagBond()))
        {
            l_interestDealingInfo.interestBondFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestBondFlag = false;
        }
    
        //投資信託フラグ
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagFund()))
        {
            l_interestDealingInfo.interestFundFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestFundFlag = false;
        }
    
        //先物・オプションフラグ
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagFo()))
        {
            l_interestDealingInfo.interestFoFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestFoFlag = false;
        }
    
        //外国証券フラグ
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagFEquity()))
        {
            l_interestDealingInfo.interestFEquityFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestFEquityFlag = false;
        }
    
        //その他フラグ
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagEtc()))
        {
            l_interestDealingInfo.interestEtcFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestEtcFlag = false;
        }

        l_applyInfo.interestDealInfo = l_interestDealingInfo;
        //顧客正式名称作成区分
        l_applyInfo.accountRealNameDiv = l_row.getRealNameVoucherDiv();
        //顧客正式名称1
        l_applyInfo.accountRealName1 = l_row.getRealName1();
        //顧客正式名称2
        l_applyInfo.accountRealName2 = l_row.getRealName2();
        //仲介業扱者コード
        l_applyInfo.brokerageCode = l_row.getBrokerageTraderCode();
        //区分１１
        l_applyInfo.extItemDiv11 = l_row.getExtItemDiv11();
        //区分１２
        l_applyInfo.extItemDiv12 = l_row.getExtItemDiv12();
        //区分１３
        l_applyInfo.extItemDiv13 = l_row.getExtItemDiv13();
        //区分１４
        l_applyInfo.extItemDiv14 = l_row.getExtItemDiv14();
        //区分１５
        l_applyInfo.extItemDiv15 = l_row.getExtItemDiv15();
        
        //内部者情報
        l_insiderInfo.createDiv = l_row.getInsiderVoucherDiv();
        l_insiderInfo.productCode = l_row.getInsiderProductCode();
        l_insiderInfo.relationCode = l_row.getInsiderRelationDiv();
        l_insiderInfo.executive = l_row.getInsiderOfficerName();
        l_insiderInfo.positionCode = l_row.getInsiderPostCode();
        l_insiderInfo.position = l_row.getInsiderPostName();
        l_applyInfo.insiderInfo = l_insiderInfo;
        
        //GP情報
        l_gpInfo.createDiv = l_row.getGpVoucherDiv();
        l_gpInfo.course = l_row.getGpCourse();
        l_gpInfo.plan = l_row.getGpPlan();
        l_gpInfo.targetFigure = l_row.getGpTargetFigure();
        l_gpInfo.targetYear = l_row.getGpTargetYear();
        l_gpInfo.targetMonth = l_row.getGpTargetMonth();
        l_gpInfo.installmentFigure = l_row.getGpInstallmentFigure();
        l_gpInfo.depositCycle = l_row.getGpDepositCycle();
        l_gpInfo.paymentRoot = l_row.getGpPaymentRoot();
        l_gpInfo.reinvestDiv = l_row.getGpReinvestDiv();
        l_gpInfo.taxType = l_row.getGpTaxDiv();
        l_gpInfo.taxfreeLimit = l_row.getGpTaxfreeLimit();
        l_gpInfo.specialTaxfreeLimit = l_row.getGpSpecialTaxfreeLimit();
        l_gpInfo.subscrSummary = l_row.getGpSubscrSummary();
        l_gpInfo.productCode = l_row.getGpProductCode();
        l_gpInfo.mortgageCustomer = l_row.getGpMortgageCustomer();
        l_gpInfo.mixCustomer = l_row.getGpMixCustomer();
        l_gpInfo.contract = l_row.getGpContract();
        l_applyInfo.gpInfo = l_gpInfo;
        
        //上場外株情報
        l_listedFeqInfo.createDiv = l_row.getStkVoucherDiv();
        l_listedFeqInfo.taxationTran = l_row.getStkTaxationTranDiv();
        l_listedFeqInfo.addressKana = l_row.getStkAddressLineKana();
        l_listedFeqInfo.transferDiv = l_row.getStkTransferDiv();
        l_listedFeqInfo.financialInstitutionCode = l_row.getStkFinInstitutionCode();
        l_listedFeqInfo.financialBranchCode = l_row.getStkFinBranchCode();
        l_listedFeqInfo.financialAccountDiv = l_row.getStkFinSaveDiv();
        l_listedFeqInfo.financialAccountCode = l_row.getStkFinAccountNo();
        l_applyInfo.listedFeqInfo = l_listedFeqInfo;
        
        //外貨預金口座情報
        l_foreignSaveInfo.financialAccountCode = l_row.getForeignAccountNo();
        l_foreignSaveInfo.financialAccountDiv = l_row.getForeignSaveDiv();
        l_foreignSaveInfo.financialAccountName = l_row.getForeignAccountName();
        l_foreignSaveInfo.financialAccountNameAlpha = l_row.getForeignAccountNameEng();
        l_applyInfo.foreignSaveInfo = l_foreignSaveInfo;

        //削除フラグ
        if (l_row.getDeleteFlag() != null)
        {
            l_applyInfo.deleteFlag = l_row.getDeleteFlag().intValue() + "";
        }

        //削除日時
        l_applyInfo.deleteDate = l_row.getDeleteTimestamp();

        //印刷フラグ
        l_applyInfo.printFlag = l_row.getPrintFlag();

        //受領フラグ
        if (l_row.getReceiptFlag() != null)
        {
            l_applyInfo.receiveFlag = l_row.getReceiptFlag().intValue() + "";
        }

        //承諾フラグ
        if (l_row.getAgreementFlag() != null)
        {
            l_applyInfo.approveFlag = l_row.getAgreementFlag().intValue() + "";
        }

        //外国人フラグ
        if (l_row.getForeignFlag() != null)
        {
            l_applyInfo.foreignerFlag = l_row.getForeignFlag().intValue() + "";
        }

        //機@構通知情報
        WEB3AccOpenAgencyInfo l_agencyInfo= new WEB3AccOpenAgencyInfo();
        //フリガナ1
        l_agencyInfo.agencyAccNameKana1 = l_row.getAgencyAccNameKana1();
        //フリガナ2
        l_agencyInfo.agencyAccNameKana2 = l_row.getAgencyAccNameKana2();
        //名称1
        l_agencyInfo.agencyAccName1 = l_row.getAgencyAccName1();
        //名称2
        l_agencyInfo.agencyAccName2 = l_row.getAgencyAccName2();
        //住所1
        l_agencyInfo.agencyAddress1 = l_row.getAgencyAddressLine1();
        //住所2
        l_agencyInfo.agencyAddress2 = l_row.getAgencyAddressLine2();
        //代表者の役職
        l_agencyInfo.agencyRepPost = l_row.getAgencyRepPost();
        //代表者のフリガナ1
        l_agencyInfo.agencyRepNameKana1 = l_row.getAgencyRepNameKana1();
        //代表者のフリガナ2
        l_agencyInfo.agencyRepNameKana2 = l_row.getAgencyRepNameKana2();
        //代表者の氏名1
        l_agencyInfo.agencyRepName1 = l_row.getAgencyRepName1();
        //代表者の氏名2
        l_agencyInfo.agencyRepName2 = l_row.getAgencyRepName2();
        l_applyInfo.agencyInfo = l_agencyInfo;

        log.exiting(STR_METHOD_NAME); 
        return l_applyInfo;
    }
    
    /**
     * (to口座開設見込客)<BR>
     * 口座開設申込情報オブジェクトより、口座開設見込客オブジェクトを生成する。<BR>
     * <BR>
     * １）　@行オブジェクト生成 <BR>
     * 　@デフォルトコンストラクタにて、口座開設見込客行オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@プロパティセット <BR>
     * 　@口座開設申込情報メッセージオブジェクトより、生成した
     *　@　@口座開設見込客行オブジェクトの対応する項目に値をセットする。<BR>
     * <BR>
     * 　@※ boolean型の項目は、対応するBooleanEnum値に変換すること。　@<BR>
     * 　@※ （振込先銀行情報 != null）の場合、振替区分に「1：銀行振込」をセットする。<BR>
     * 　@※ （郵便振替情報 != null）の場合、振替区分に「5：郵貯振込」をセットする。<BR>
     * <BR>
     * <BR>
     * ３）　@オブジェクト生成<BR>
     * 　@生成した行オブジェクトを指定し、口座開設見込客オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを返却する。<BR>
     * @@param l_accOpenApplyInfo - 口座開設申込情報メッセージデータオブジェクト
     * @@throws WEB3BaseException
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@roseuid 41945E74032F
     */
    public WEB3AccOpenExpAccountOpen toAccOpenExpAccountOpen(WEB3AccOpenApplyInfo l_accOpenApplyInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenApplyInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）　@行オブジェクト生成
        ExpAccountOpenParams l_params = new ExpAccountOpenParams();

        //２）　@プロパティセット
        //資料請求日
        l_params.setInfomationClaimDatetime(l_accOpenApplyInfo.infoClaimDate); 
    
        //更新日
        l_params.setLastUpdatedTimestamp(l_accOpenApplyInfo.updateDate);
    
        //更新者コード
        l_params.setLastUpdater(l_accOpenApplyInfo.updaterCode);
    
        //識別コード
        l_params.setAccOpenRequestNumber(l_accOpenApplyInfo.requestNumber);
        
        //入力区分
        l_params.setOrderDiv(l_accOpenApplyInfo.inputDiv);
        
        //作成者コード
        l_params.setCreator(l_accOpenApplyInfo.creatorCode);
    
        //既存口座フラグ
        if (l_accOpenApplyInfo.exAccountFlag)
        {
            l_params.setExAccountFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExAccountFlag(BooleanEnum.FALSE);
        }
    
        //既存口座コード
        l_params.setExAccountCode(l_accOpenApplyInfo.exAccountCode);
    
        //既存口座部店名
        l_params.setExBranchName(l_accOpenApplyInfo.exAccountBranchName);
    
        //証券会社コード
        l_params.setInstitutionCode(l_accOpenApplyInfo.institutionCode);
    
        //部店コード
        l_params.setBranchCode(l_accOpenApplyInfo.branchCode);
    
        //口座区分
        l_params.setAccountDiv(l_accOpenApplyInfo.accountType);
    
        //顧客コード
        l_params.setAccountCode(l_accOpenApplyInfo.accountCode);
    
        //扱者コード（SONAR）
        l_params.setSonarTraderCode(l_accOpenApplyInfo.traderCode);
    
        //メールアドレス
        l_params.setEmailAddress(l_accOpenApplyInfo.mailAddress);
    
        // メールアドレス（携帯）
        l_params.setEmailAddressAlt1(l_accOpenApplyInfo.mobileMailAddress);
    
        //顧客姓（漢字）
        l_params.setFamilyName(l_accOpenApplyInfo.accountFamilyName);
    
        //顧客名（漢字）
        l_params.setGivenName(l_accOpenApplyInfo.accountName);
    
        //顧客姓（カナ）
        l_params.setFamilyNameAlt1(l_accOpenApplyInfo.accountFamilyNameKana);
    
        //顧客名（カナ）
        l_params.setGivenNameAlt1(l_accOpenApplyInfo.accountNameKana);
    
        //生年月日年号
        l_params.setEraBorn(l_accOpenApplyInfo.eraBorn);
    
        //生年月日
        l_params.setBornDate(l_accOpenApplyInfo.bornDate);
    
        //性別
        l_params.setSex(l_accOpenApplyInfo.sex);
        
        //暗証番号
        WEB3Crypt l_crypt = new WEB3Crypt();
        l_params.setInitialPassword(l_crypt.encrypt(l_accOpenApplyInfo.password));
    
        //郵便番号
        l_params.setZipCode(l_accOpenApplyInfo.zipCode);
    
        //住所１（カナ）
        l_params.setAddressLine1Kana(l_accOpenApplyInfo.addressKana1);
    
        //住所２（カナ）
        l_params.setAddressLine2Kana(l_accOpenApplyInfo.addressKana2);
    
        //住所３（カナ）
        l_params.setAddressLine3Kana(l_accOpenApplyInfo.addressKana3);
    
        //住所１（漢字）
        l_params.setAddressLine1(l_accOpenApplyInfo.address1);
    
        //住所２（漢字）
        l_params.setAddressLine2(l_accOpenApplyInfo.address2);
    
        //住所３（漢字）
        l_params.setAddressLine3(l_accOpenApplyInfo.address3);
    
        //電話番号
        l_params.setTelephone(l_accOpenApplyInfo.telephone);
    
        //携帯番号
        l_params.setMobile(l_accOpenApplyInfo.mobileTelephone);
    
        //ＦＡＸ番号
        l_params.setFax(l_accOpenApplyInfo.faxTelephone);
    
        //勤務先情報
        WEB3AccOpenOfficeInfo l_officeInfo = l_accOpenApplyInfo.officeInfo; 
        
        if (l_officeInfo != null)
        {
            //職業区分
            l_params.setOccupationDiv(l_officeInfo.occupationDiv);
    
            //勤務先名称
            l_params.setOffice(l_officeInfo.officeName);
    
            // 勤務先郵便番号<BR>
            l_params.setOfficeZipCode(l_officeInfo.officeZipCode);
    
            //勤務先住所
            l_params.setOfficeAddress(l_officeInfo.officeAdress);
    
            //勤務先電話番号
            l_params.setOfficeTelephone(l_officeInfo.officeTelephone);
    
            //勤務先ＦＡＸ番号
            l_params.setOfficeFax(l_officeInfo.officeFaxTelephone);
    
            //所属部署
            l_params.setDepartment(l_officeInfo.department);
    
            //役職名
            l_params.setPost(l_officeInfo.position);
        }
    
        //連絡先住所
        l_params.setContactAddress(l_accOpenApplyInfo.contactAddress);
    
        //連絡先電話番号
        l_params.setContactTelephone(l_accOpenApplyInfo.contactTelephone);
    
        //続柄区分
        l_params.setFamilyRelationship(l_accOpenApplyInfo.familyRelationDiv);
    
        //続柄（その他）
        l_params.setFamilyRelationshipEtc(l_accOpenApplyInfo.familyRelationEtc);
    
        //世帯主名（漢字）
        l_params.setHouseholder(l_accOpenApplyInfo.houseHolderName);
    
        //世帯主名（カナ）
        l_params.setHouseholderKana(l_accOpenApplyInfo.houseHolderNameKana);
    
        //世帯主勤務先情報
        l_officeInfo = l_accOpenApplyInfo.houseHolderOfficeInfo;
        
        if (l_officeInfo != null)
        {
            //職業区分
            l_params.setHouseholderOccupationDiv(l_officeInfo.occupationDiv);
            
            //勤務先名称
            l_params.setHouseholderOffice(l_officeInfo.officeName);
            
            //勤務先住所
            l_params.setHouseholderOfficeAddress(l_officeInfo.officeAdress);
            
            //勤務先電話番号
            l_params.setHouseholderOfficeTel(l_officeInfo.officeTelephone);
            
            //勤務先ＦＡＸ番号
            l_params.setHouseholderOfficeFax(l_officeInfo.officeFaxTelephone);
            
            //所属部署
            l_params.setHouseholderDepartment(l_officeInfo.department);
            
            //役職名
            l_params.setHouseholderPost(l_officeInfo.position); 
        }
    
        //居住（日本）／非居住（日本以外）区分
        l_params.setResident(l_accOpenApplyInfo.residentDiv);
    
        //振替区分
        if (l_accOpenApplyInfo.transferBankInfo != null)
        {
            l_params.setTransferDiv(WEB3TransferDivDef.BANK_TRANSFER);
        }
        if (l_accOpenApplyInfo.postalTransferInfo != null)
        {
            l_params.setTransferDiv(WEB3TransferDivDef.POSTAL_TRANSFER);
        }
        
        //口座名義人
        l_params.setFinAccountName(l_accOpenApplyInfo.financialAccountName);
    
        //投資目的区分
        l_params.setInvestPurposeDiv(l_accOpenApplyInfo.investPurposeDiv);
    
        //取引動機@区分
        l_params.setAppliMotivatDiv(l_accOpenApplyInfo.appliMotivatDiv);
    
        //年収区分
        l_params.setAnnualIncomeDiv(l_accOpenApplyInfo.annualIncomeDiv);
    
        //金融資産区分
        l_params.setAssetValueDiv(l_accOpenApplyInfo.assetValueDiv);
    
        //運用予定額区分
        l_params.setFundBudgetAmountDiv(l_accOpenApplyInfo.fundBundgetAmountDiv);
    
        //資産の性格区分
        l_params.setFundBudgetDiv(l_accOpenApplyInfo.fundBundgetDiv);
    
        //資金の性格（その他
        l_params.setFundBudgetEtc(l_accOpenApplyInfo.fundBundgetEtc);
    
        //検索用区分
        if (WEB3ExistenceDivDef.HAVING.equals(l_accOpenApplyInfo.searchDiv))
        {
            l_params.setIdConfirmFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setIdConfirmFlag(BooleanEnum.FALSE);
        }
    
        //本人確認書類区分
        l_params.setIdConfirmDocDiv(l_accOpenApplyInfo.idConfirmDocDiv);
    
        //本人確認書類（その他
        l_params.setIdConfirmDocEtc(l_accOpenApplyInfo.idConfirmDocEtc);
    
        //現物株式口座区分
        l_params.setSpecialAcc(l_accOpenApplyInfo.equityTaxType);
    
        //信用取引口座区分
        l_params.setSpecialAccMargin(l_accOpenApplyInfo.marginTaxType);
    
        //内部者登録フラグ
        if (l_accOpenApplyInfo.insiderFlag)
        {
            l_params.setInsiderFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setInsiderFlag(BooleanEnum.FALSE);
        }
    
        //内部者銘柄名
        l_params.setProductName(l_accOpenApplyInfo.insiderProductName);
    
        //送付先郵便番号
        l_params.setSendZipCode(l_accOpenApplyInfo.sendZipCode);
    
        //送付先住所１
        l_params.setSendAddressLine1(l_accOpenApplyInfo.sendAddress1);
    
        //送付先住所２
        l_params.setSendAddressLine2(l_accOpenApplyInfo.sendAddress2);
    
        //送付先住所３
        l_params.setSendAddressLine3(l_accOpenApplyInfo.sendAddress3);
    
        //区分1
        l_params.setExtItemDiv1(l_accOpenApplyInfo.extItemDiv1);
    
        //区分2
        l_params.setExtItemDiv2(l_accOpenApplyInfo.extItemDiv2);
    
        //区分3
        l_params.setExtItemDiv3(l_accOpenApplyInfo.extItemDiv3);
    
        //区分4
        l_params.setExtItemDiv4(l_accOpenApplyInfo.extItemDiv4);
    
        //区分5
        l_params.setExtItemDiv5(l_accOpenApplyInfo.extItemDiv5);
    
        //区分6
        l_params.setExtItemDiv6(l_accOpenApplyInfo.extItemDiv6);
    
        //区分7
        l_params.setExtItemDiv7(l_accOpenApplyInfo.extItemDiv7);
    
        //区分8
        l_params.setExtItemDiv8(l_accOpenApplyInfo.extItemDiv8);
    
        //区分9
        l_params.setExtItemDiv9(l_accOpenApplyInfo.extItemDiv9);
    
        //区分10
        l_params.setExtItemDiv10(l_accOpenApplyInfo.extItemDiv10);
    
        //フラグ１
        if (l_accOpenApplyInfo.extItemFlag1)
        {
            l_params.setExtItemFlag1(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag1(BooleanEnum.FALSE);
        }
    
        //フラグ2
        if (l_accOpenApplyInfo.extItemFlag2)
        {
            l_params.setExtItemFlag2(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag2(BooleanEnum.FALSE);
        }
    
        //フラグ3
        if (l_accOpenApplyInfo.extItemFlag3)
        {
            l_params.setExtItemFlag3(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag3(BooleanEnum.FALSE);
        }
    
        //フラグ4
        if (l_accOpenApplyInfo.extItemFlag4)
        {
            l_params.setExtItemFlag4(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag4(BooleanEnum.FALSE);
        }
    
        //フラグ5
        if (l_accOpenApplyInfo.extItemFlag5)
        {
            l_params.setExtItemFlag5(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag5(BooleanEnum.FALSE);
        }
    
        //フラグ6
        if (l_accOpenApplyInfo.extItemFlag6)
        {
            l_params.setExtItemFlag6(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag6(BooleanEnum.FALSE);
        }
    
        //フラグ7
        if (l_accOpenApplyInfo.extItemFlag7)
        {
            l_params.setExtItemFlag7(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag7(BooleanEnum.FALSE);
        }
    
        //フラグ8
        if (l_accOpenApplyInfo.extItemFlag8)
        {
            l_params.setExtItemFlag8(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag8(BooleanEnum.FALSE);
        }
    
        //フラグ9
        if (l_accOpenApplyInfo.extItemFlag9)
        {
            l_params.setExtItemFlag9(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag9(BooleanEnum.FALSE);
        }
    
        //フラグ10
        if (l_accOpenApplyInfo.extItemFlag10)
        {
            l_params.setExtItemFlag10(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag10(BooleanEnum.FALSE);
        }
    
        //テキスト1
        l_params.setExtItemText1(l_accOpenApplyInfo.extItemText1);
    
        //テキスト2
        l_params.setExtItemText2(l_accOpenApplyInfo.extItemText2);
    
        //テキスト3
        l_params.setExtItemText3(l_accOpenApplyInfo.extItemText3);
    
        //テキスト4
        l_params.setExtItemText4(l_accOpenApplyInfo.extItemText4);
    
        //テキスト5
        l_params.setExtItemText5(l_accOpenApplyInfo.extItemText5);
    
        //テキスト6
        l_params.setExtItemText6(l_accOpenApplyInfo.extItemText6);
    
        //テキスト7
        l_params.setExtItemText7(l_accOpenApplyInfo.extItemText7);
    
        //テキスト8
        l_params.setExtItemText8(l_accOpenApplyInfo.extItemText8);
    
        //テキスト9
        l_params.setExtItemText9(l_accOpenApplyInfo.extItemText9);
        
        //テキスト10
        l_params.setExtItemText10(l_accOpenApplyInfo.extItemText10);
    
        //振込先銀行情報
        WEB3AccOpenTransferBankInfo l_transferBankInfo = 
            l_accOpenApplyInfo.transferBankInfo;
        if (l_accOpenApplyInfo.transferBankInfo != null)
        {
            //銀行コード
            l_params.setFinInstitutionCode(l_transferBankInfo.financialInstitutionCode);
    
            //銀行名
            l_params.setFinInstitutionName(l_transferBankInfo.financialInstitutionName);
    
            // 支店コード
            l_params.setFinBranchCode(l_transferBankInfo.financialBranchCode);
    
            //支店名
            l_params.setFinBranchName(l_transferBankInfo.financialBranchName);
    
            //預金区分
            l_params.setFinSaveDiv(l_transferBankInfo.financialAccountDiv);
    
            //口座番号
            l_params.setFinAccountNo(l_transferBankInfo.financialAccountCode);
    
            //振替手数料区分
            l_params.setTransCommission(l_transferBankInfo.transferCommissionDiv);
        }
        
        //郵便振替情報
        WEB3AccOpenPostalTransferInfo l_postalTransferInfo = 
            l_accOpenApplyInfo.postalTransferInfo;
        if (l_accOpenApplyInfo.postalTransferInfo != null)
        {
            //通帳番号
            l_params.setPostalSaveNo(l_postalTransferInfo.passbookCode);
    
            //通帳記号
            l_params.setPostalSaveCode(l_postalTransferInfo.passbookSign);
        }
    
        //投資経験情報
        WEB3AccOpenInvestmentExperienceInfo l_investmentExperienceInfo = 
            l_accOpenApplyInfo.investExpInfo;
            
        if (l_investmentExperienceInfo != null)
        {
            //現物株式
            l_params.setExperienceDivEquity(l_investmentExperienceInfo.experienceEquityDiv);
    
            //信用取引
            l_params.setExperienceDivMargin(l_investmentExperienceInfo.experienceMarginDiv);
    
            //債券
            l_params.setExperienceDivBond(l_investmentExperienceInfo.experienceBondDiv);
    
            //転換社債
            l_params.setExperienceDivWt(l_investmentExperienceInfo.experienceWtDiv);
    
            //投資信託（株式）
            l_params.setExperienceDivFundSk(l_investmentExperienceInfo.experienceFundSkDiv);
    
            //投資信託（債券）
            l_params.setExperienceDivFundBd(l_investmentExperienceInfo.experienceFundBdDiv);
    
            //先物・オプション
            l_params.setExperienceDivFo(l_investmentExperienceInfo.experienceFoDiv);
    
            //外国証券
            l_params.setExperienceDivFEquity(l_investmentExperienceInfo.experienceFEquityDiv);
    
            //その他
            l_params.setExperienceDivEtc(l_investmentExperienceInfo.experienceEtcDiv);
        }

        //興味のある取引情報
        WEB3AccOpenInterestDealingInfo l_interestDealingInfo = 
            l_accOpenApplyInfo.interestDealInfo;
            
        if (l_interestDealingInfo != null)
        {
            //現物株式フラグ
            if (l_interestDealingInfo.interestEquityFlag)
            {
                l_params.setInterestFlagEquity(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagEquity(BooleanEnum.FALSE);
            }
    
            //株式ミニ投資フラグ
            if (l_interestDealingInfo.interestMstkFlag)
            {
                l_params.setInterestFlagMinistock(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagMinistock(BooleanEnum.FALSE);
            }
    
            //信用取引フラグ
            if (l_interestDealingInfo.interestMarginFlag)
            {
                l_params.setInterestFlagMargin(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagMargin(BooleanEnum.FALSE);
            }
    
            //債券フラグ
            if (l_interestDealingInfo.interestBondFlag)
            {
                l_params.setInterestFlagBond(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagBond(BooleanEnum.FALSE);
            }
    
            //投資信託フラグ
            if (l_interestDealingInfo.interestFundFlag)
            {
                l_params.setInterestFlagFund(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagFund(BooleanEnum.FALSE);
            }
    
            //先物・オプションフラグ
            if (l_interestDealingInfo.interestFoFlag)
            {
                l_params.setInterestFlagFo(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagFo(BooleanEnum.FALSE);
            }
    
            //外国証券フラグ
            if (l_interestDealingInfo.interestFEquityFlag)
            {
                l_params.setInterestFlagFEquity(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagFEquity(BooleanEnum.FALSE);
            }
    
            //その他フラグ
            if (l_interestDealingInfo.interestEtcFlag)
            {
                l_params.setInterestFlagEtc(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagEtc(BooleanEnum.FALSE);
            }
        }
        else
        {
            //現物株式フラグ
            l_params.setInterestFlagEquity(BooleanEnum.FALSE);

            //株式ミニ投資フラグ
            l_params.setInterestFlagMinistock(BooleanEnum.FALSE);
    
            //信用取引フラグ
            l_params.setInterestFlagMargin(BooleanEnum.FALSE);
    
            //債券フラグ
            l_params.setInterestFlagBond(BooleanEnum.FALSE);
    
            //投資信託フラグ
            l_params.setInterestFlagFund(BooleanEnum.FALSE);

            //先物・オプションフラグ
            l_params.setInterestFlagFo(BooleanEnum.FALSE);

            //外国証券フラグ
            l_params.setInterestFlagFEquity(BooleanEnum.FALSE);
    
            //その他フラグ
            l_params.setInterestFlagEtc(BooleanEnum.FALSE);
        }
        
        //顧客正式名称作成区分
        l_params.setRealNameVoucherDiv(l_accOpenApplyInfo.accountRealNameDiv);
        
        //顧客正式名称１
        l_params.setRealName1(l_accOpenApplyInfo.accountRealName1);
        
        //顧客正式名称２
        l_params.setRealName2(l_accOpenApplyInfo.accountRealName2);
        
        //内部者登録伝票
        WEB3AccOpenInsiderInfo l_insiderInfo = l_accOpenApplyInfo.insiderInfo;
        if (l_insiderInfo != null)
        {
            //（内部者）作成区分
            l_params.setInsiderVoucherDiv(l_insiderInfo.createDiv);
            
            //（内部者）銘柄コード
            l_params.setInsiderProductCode(l_insiderInfo.productCode);
            
            //（内部者）関係区分
            l_params.setInsiderRelationDiv(l_insiderInfo.relationCode);
            
            //（内部者）役員名
            l_params.setInsiderOfficerName(l_insiderInfo.executive);
            
            //（内部者）役職名コード
            l_params.setInsiderPostCode(l_insiderInfo.positionCode);
            
            //（内部者）役職名
            l_params.setInsiderPostName(l_insiderInfo.position);
        }
        
        //GP条件登録伝票
        WEB3AccOpenGpInfo l_gpInfo = l_accOpenApplyInfo.gpInfo;
        if (l_gpInfo != null)
        {
            //（ＧＰ）作成区分
            l_params.setGpVoucherDiv(l_gpInfo.createDiv);
            
            //（ＧＰ）コース
            l_params.setGpCourse(l_gpInfo.course);
            
            //（ＧＰ）プラン
            l_params.setGpPlan(l_gpInfo.plan);
            
            //（ＧＰ）目標額
            l_params.setGpTargetFigure(l_gpInfo.targetFigure);
            
            //（ＧＰ）目標年
            l_params.setGpTargetYear(l_gpInfo.targetYear);
            
            //（ＧＰ）目標月
            l_params.setGpTargetMonth(l_gpInfo.targetMonth);
            
            //（ＧＰ）積立額
            l_params.setGpInstallmentFigure(l_gpInfo.installmentFigure);
            
            //（ＧＰ）入金周期
            l_params.setGpDepositCycle(l_gpInfo.depositCycle);
            
            //（ＧＰ）受渡経路
            l_params.setGpPaymentRoot(l_gpInfo.paymentRoot);
            
            //（ＧＰ）再投資区分
            l_params.setGpReinvestDiv(l_gpInfo.reinvestDiv);
            
            //（ＧＰ）税区分
            l_params.setGpTaxDiv(l_gpInfo.taxType);
            
            //（ＧＰ）（優）限度額
            l_params.setGpTaxfreeLimit(l_gpInfo.taxfreeLimit);
            
            //（ＧＰ）（特優）限度額
            l_params.setGpSpecialTaxfreeLimit(l_gpInfo.specialTaxfreeLimit);
            
            //（ＧＰ）加入摘要
            l_params.setGpSubscrSummary(l_gpInfo.subscrSummary);
            
            //（ＧＰ）銘柄コード
            l_params.setGpProductCode(l_gpInfo.productCode);
            
            //（ＧＰ）担保客
            l_params.setGpMortgageCustomer(l_gpInfo.mortgageCustomer);
            
            //（ＧＰ）ミックス客
            l_params.setGpMixCustomer(l_gpInfo.mixCustomer);
            
            //（ＧＰ）契約書
            l_params.setGpContract(l_gpInfo.contract);
        }
        
        //上場外株・株主登録伝票
        WEB3AccOpenListedFeqInfo l_listedFeqInfo = l_accOpenApplyInfo.listedFeqInfo;
        if (l_listedFeqInfo != null)
        {
            //（上場外株）作成区分
            l_params.setStkVoucherDiv(l_listedFeqInfo.createDiv);
            
            //（上場外株）譲渡
            l_params.setStkTaxationTranDiv(l_listedFeqInfo.taxationTran);
            
            //（上場外株）住所（カナ）
            l_params.setStkAddressLineKana(l_listedFeqInfo.addressKana);
            
            //（上場外株）送金
            l_params.setStkTransferDiv(l_listedFeqInfo.transferDiv);
            
            //（上場外株）銀行コード
            l_params.setStkFinInstitutionCode(l_listedFeqInfo.financialInstitutionCode);
            
            //（上場外株）支店コード
            l_params.setStkFinBranchCode(l_listedFeqInfo.financialBranchCode);
            
            //（上場外株）預金区分 
            l_params.setStkFinSaveDiv(l_listedFeqInfo.financialAccountDiv);
            
            //（上場外株）口座番号
            l_params.setStkFinAccountNo(l_listedFeqInfo.financialAccountCode);
        }

        //仲介業扱者コード
        l_params.setBrokerageTraderCode(l_accOpenApplyInfo.brokerageCode);
        
        //各社拡張項目（区分1１）
        l_params.setExtItemDiv11(l_accOpenApplyInfo.extItemDiv11);
        
        //各社拡張項目（区分１2）
        l_params.setExtItemDiv12(l_accOpenApplyInfo.extItemDiv12);

        //各社拡張項目（区分１3）
        l_params.setExtItemDiv13(l_accOpenApplyInfo.extItemDiv13);

        //各社拡張項目（区分１4）
        l_params.setExtItemDiv14(l_accOpenApplyInfo.extItemDiv14);

        //各社拡張項目（区分１5）
        l_params.setExtItemDiv15(l_accOpenApplyInfo.extItemDiv15);
        
        //外貨預金口座情報
        WEB3AccOpenForeignSaveInfo l_foreignSaveInfo = l_accOpenApplyInfo.foreignSaveInfo;
        if (l_foreignSaveInfo != null)
        {
            //口座番号（外貨）
            l_params.setForeignAccountNo(l_foreignSaveInfo.financialAccountCode);
            
            //口座名義人（外貨）
            l_params.setForeignAccountName(l_foreignSaveInfo.financialAccountName);
            
            //口座名義人英数（外貨）
            l_params.setForeignAccountNameEng(l_foreignSaveInfo.financialAccountNameAlpha);
            
            //預金区分（外貨）
            l_params.setForeignSaveDiv(l_foreignSaveInfo.financialAccountDiv);
        }

        //削除フラグ
        if (!WEB3StringTypeUtility.isEmpty(l_accOpenApplyInfo.deleteFlag))
        {
            l_params.setDeleteFlag(
                (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                    BooleanEnum.class, Integer.parseInt(l_accOpenApplyInfo.deleteFlag)));
        }

        //削除日時
        l_params.setDeleteTimestamp(l_accOpenApplyInfo.deleteDate);

        //印刷フラグ
        l_params.setPrintFlag(l_accOpenApplyInfo.printFlag);

        //受領フラグ
        if (!WEB3StringTypeUtility.isEmpty(l_accOpenApplyInfo.receiveFlag))
        {
            l_params.setReceiptFlag(
                (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                    BooleanEnum.class, Integer.parseInt(l_accOpenApplyInfo.receiveFlag)));
        }

        //承諾フラグ
        if (!WEB3StringTypeUtility.isEmpty(l_accOpenApplyInfo.approveFlag))
        {
            l_params.setAgreementFlag(
                (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                    BooleanEnum.class, Integer.parseInt(l_accOpenApplyInfo.approveFlag)));
        }

        //外国人フラグ
        if (!WEB3StringTypeUtility.isEmpty(l_accOpenApplyInfo.foreignerFlag))
        {
            l_params.setForeignFlag(
                (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                    BooleanEnum.class, Integer.parseInt(l_accOpenApplyInfo.foreignerFlag)));
        }

        //機@構通知情報
        WEB3AccOpenAgencyInfo l_agencyInfo= l_accOpenApplyInfo.agencyInfo;
        if (l_agencyInfo != null)
        {
            //フリガナ1
            l_params.setAgencyAccNameKana1(l_agencyInfo.agencyAccNameKana1);
            //フリガナ2
            l_params.setAgencyAccNameKana2(l_agencyInfo.agencyAccNameKana2);
            //名称1
             l_params.setAgencyAccName1(l_agencyInfo.agencyAccName1);
            //名称2
             l_params.setAgencyAccName2(l_agencyInfo.agencyAccName2);
            //住所1
             l_params.setAgencyAddressLine1(l_agencyInfo.agencyAddress1);
            //住所2
             l_params.setAgencyAddressLine2(l_agencyInfo.agencyAddress2);
            //代表者の役職
             l_params.setAgencyRepPost(l_agencyInfo.agencyRepPost);
            //代表者のフリガナ1
             l_params.setAgencyRepNameKana1(l_agencyInfo.agencyRepNameKana1);
            //代表者のフリガナ2
             l_params.setAgencyRepNameKana2(l_agencyInfo.agencyRepNameKana2);
            //代表者の氏名1
             l_params.setAgencyRepName1(l_agencyInfo.agencyRepName1);
            //代表者の氏名2
             l_params.setAgencyRepName2(l_agencyInfo.agencyRepName2);
        }

        //３）　@オブジェクト生成
        WEB3AccOpenExpAccountOpen l_expAccountOpen = new WEB3AccOpenExpAccountOpen(l_params);
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_expAccountOpen;
    }
    
    /**
     * (to伝票作成情報)<BR>
     * 口座開設伝票作成ステータスオブジェクトの配列より、伝票作成情報を生成する。<BR>
     * <BR>
     * １）　@伝票作成情報オブジェクト生成<BR>
     * 　@伝票作成情報オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@プロパティセット<BR>
     * 　@以下の通り、伝票作成情報オブジェクトに値をセットし返却する。<BR>
     * <BR>
     * 　@伝票作成情報.G11顧客登録 = （データコード=顧客登録）に該当する要素の<BR>
     * 伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G1151契約書徴収 = （データコード=契約書徴収）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G26振替申込 = （データコード=振替申込（銀行），<BR>
     * データコード=振替申込（郵貯））に該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.GA300保振同意 = （データコード=保振同意）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G5401有料情報 = （データコード=有料情報）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.GI601MRF口座 = （データコード=MRF口座）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G5511暗証番号 = （データコード=暗証番号）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G1159重要事項 = （データコード=重要事項）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G1175本人確認 = （データコード=本人確認）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.GI311取残・電子交付・特定口座 = （伝票コード=<BR>
     * 取残・電子交付・特定口座）に該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G9801内部者 = （データコード=内部者）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G1220GP条件 = （データコード=GP条件）に<BR>
     * 該当する要素の伝票作成ステータスの配列※ <BR>
     * 　@伝票作成情報.G8610上場外株・株主 = （データコード=上場外株・株主）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.G1190顧客正式名称 = （データコード=顧客正式名称）に<BR>
     * 該当する要素の伝票作成ステータスの配列※ <BR>
     * 　@伝票作成情報.G43外貨預金口座登録 = （データコード=外貨預金口座登録）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * 　@伝票作成情報.GS103機@構通知 = （データコード=機@構通知情報登録）に<BR>
     * 該当する要素の伝票作成ステータスの配列※<BR>
     * <BR>
     * 　@※ 伝票作成エラーがある場合（口座開設伝票作成ステータス.エラーコード != null）、<BR>
     * 　@　@該当要素は、「口座開設伝票作成ステータス.伝票作成ステータス：<BR>
     * 口座開設伝票作成ステータス.エラーコード」の形式で、エラーコードを<BR>
     * 追加したステータスを編集する。<BR>
     * 　@　@以外、「口座開設伝票作成ステータス.伝票作成ステータス」を編集する。<BR>
     * @@param l_accOpenVoucherCreatedStatuses - 口座開設伝票作成ステータスオブジェクトの配列
     * @@return webbroker3.accountopen.message.WEB3AccOpenVoucherInfo
     * @@roseuid 4191A6460330
     */
    public WEB3AccOpenVoucherInfo toAccOpenVoucherInfo(WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatuses) 
    {
        final String STR_METHOD_NAME = " toAccOpenVoucherInfo(WEB3AccOpenVoucherCreatedStatus[]) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@伝票作成情報オブジェクト生成
        WEB3AccOpenVoucherInfo l_voucherInfo = 
            new WEB3AccOpenVoucherInfo();
        if (l_accOpenVoucherCreatedStatuses == null || l_accOpenVoucherCreatedStatuses.length == 0)
        {
             return l_voucherInfo;
        } 
        
        //２）　@プロパティセット
        
        ArrayList l_g11List = new ArrayList();
        ArrayList l_g1151List = new ArrayList();
        ArrayList l_g26List = new ArrayList();
        ArrayList l_ga300List = new ArrayList();
        ArrayList l_g5401List = new ArrayList();
        ArrayList l_g1601List = new ArrayList();
        ArrayList l_g5511List = new ArrayList();
        ArrayList l_g1159List = new ArrayList();
        ArrayList l_g1175List = new ArrayList();
        ArrayList l_g1311List = new ArrayList();
        ArrayList l_g9801List = new ArrayList();
        ArrayList l_g1220List = new ArrayList();
        ArrayList l_g8610List = new ArrayList();
        ArrayList l_g1190List = new ArrayList();
        ArrayList l_g43List = new ArrayList();
        ArrayList l_gs103List = new ArrayList();
        
        int l_intCnt = l_accOpenVoucherCreatedStatuses.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3AccOpenVoucherCreatedStatus l_voucherCreatedStatuse = 
                l_accOpenVoucherCreatedStatuses[i];
            
            if (l_voucherCreatedStatuse == null)
            {
                continue;
            }
        
            AccOpenVoucherStatusParams l_params = (AccOpenVoucherStatusParams)
                l_voucherCreatedStatuse.getDataSourceObject();
        
            String l_strRequestCode = l_params.getRequestCode();
        
            //G11顧客登録
            //顧客登録（仲介業）伝票
            if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST.equals(l_strRequestCode) ||
                WEB3HostRequestCodeDef.ACCOPEN_REG_BROKERAGE.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g11List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g11List.add(l_params.getVoucherStatus());
                }
             }
            //G1151契約書徴収 
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONTRACT_COLLECT.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1151List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1151List.add(l_params.getVoucherStatus());
                }
            }
            //G26振替申込
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK.equals(l_strRequestCode) || WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g26List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g26List.add(l_params.getVoucherStatus());
                }
            }
            //GA300保振同意
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGREE_TRANSFER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_ga300List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_ga300List.add(l_params.getVoucherStatus());
                }
            }
            //G5401有料情報
            else if (WEB3HostRequestCodeDef.ACCOPEN_CHARGED_INFO.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g5401List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g5401List.add(l_params.getVoucherStatus());
                }
            }
            //GI601MRF口座  
            else if (WEB3HostRequestCodeDef.ACCOPEN_MRF_ACCOUNT.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1601List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1601List.add(l_params.getVoucherStatus());
                }
            }
            //G5511暗証番号
            else if (WEB3HostRequestCodeDef.ACCOPEN_PASSWORD.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g5511List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g5511List.add(l_params.getVoucherStatus());
                }
            }
            //G1159重要事項 
            else if (WEB3HostRequestCodeDef.ACCOPEN_IMPORTANT_ITEM.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1159List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1159List.add(l_params.getVoucherStatus());
                }
            }
            //G1175本人確認
            else if (WEB3HostRequestCodeDef.ACCOPEN_ID_CONFIRM.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1175List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1175List.add(l_params.getVoucherStatus());
                }
            } 
            //伝票作成情報.GI311取残・電子交付・特定口座 
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRADE_CONDITION.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1311List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1311List.add(l_params.getVoucherStatus());
                }
            }
            //伝票作成情報.G9801内部者 
            else if (WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_VOUCHER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g9801List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g9801List.add(l_params.getVoucherStatus());
                }
            }
            //伝票作成情報.G1220GP条件 
            else if (WEB3HostRequestCodeDef.ACCOPEN_GP_REG_VOUCHER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1220List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1220List.add(l_params.getVoucherStatus());
                }
            }
            //伝票作成情報.G8610上場外株・株主 
            else if (WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_VOUCHER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g8610List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g8610List.add(l_params.getVoucherStatus());
                }
            }
            //伝票作成情報.G1190顧客正式名称 
            else if (WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_VOUCHER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1190List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1190List.add(l_params.getVoucherStatus());
                }
            }
            //伝票作成情報.G43外貨預金口座登録
            else if (WEB3HostRequestCodeDef.F_DEPOSIT_REG.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g43List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g43List.add(l_params.getVoucherStatus());
                }
            }
            //伝票作成情報.GS103機@構通知
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REGIST.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_gs103List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_gs103List.add(l_params.getVoucherStatus());
                }
            }
        }

        int l_intG11Cnt = l_g11List.size();
        String[] l_strG11s = null;
        if (l_intG11Cnt > 0)
        {
            l_strG11s = new String[l_intG11Cnt];
            l_g11List.toArray(l_strG11s);
            l_voucherInfo.accRegVoucherDiv = l_strG11s;
        }

        int l_intG1151Cnt = l_g1151List.size();
        String[] l_strG1151s = null;
        if (l_intG1151Cnt > 0)
        {
            l_strG1151s = new String[l_intG1151Cnt];
            l_g1151List.toArray(l_strG1151s);
            l_voucherInfo.contMrgVoucherDiv = l_strG1151s;
        }
        
        int l_intG26Cnt = l_g26List.size();
        String[] l_strG26s = null;
        if (l_intG26Cnt > 0)
        {
            l_strG26s = new String[l_intG26Cnt];
            l_g26List.toArray(l_strG26s);
            l_voucherInfo.transVoucherDiv = l_strG26s;
        }
        
        int l_intGa300Cnt = l_ga300List.size();
        String[] l_strGa300s = null;
        if (l_intGa300Cnt > 0)
        {
            l_strGa300s = new String[l_intGa300Cnt];
            l_ga300List.toArray(l_strGa300s);
            l_voucherInfo.agreeTransVoucherDiv = l_strGa300s;
        }
        
        int l_intG5401Cnt = l_g5401List.size();
        String[] l_strG5401s = null;
        if (l_intG5401Cnt > 0)
        {
            l_strG5401s = new String[l_intG5401Cnt];
            l_g5401List.toArray(l_strG5401s);
            l_voucherInfo.chargedInfoVoucherDiv = l_strG5401s;
        }
        
        int l_intG1601Cnt = l_g1601List.size();
        String[] l_strG1601s = null;
        if (l_intG1601Cnt > 0)
        {
            l_strG1601s = new String[l_intG1601Cnt];
            l_g1601List.toArray(l_strG1601s);
            l_voucherInfo.mrfAccVoucherDiv = l_strG1601s;
        }
        
        int l_intG5511Cnt = l_g5511List.size();        
        String[] l_strG5511s = null;
        if (l_intG5511Cnt > 0)
        {
            l_strG5511s = new String[l_intG5511Cnt];
            l_g5511List.toArray(l_strG5511s);
            l_voucherInfo.passwordVoucherDiv = l_strG5511s;
        }
        
        int l_intG1159Cnt = l_g1159List.size();
        String[] l_strG1159s = null;
        if (l_intG1159Cnt > 0)
        {
            l_strG1159s = new String[l_intG1159Cnt];
            l_g1159List.toArray(l_strG1159s);
            l_voucherInfo.impConfirmVoucherDiv = l_strG1159s;
        }
        
        int l_intG1175Cnt = l_g1175List.size();
        String[] l_strG1175s = null;
        if (l_intG1175Cnt > 0)
        {
            l_strG1175s = new String[l_intG1175Cnt];
            l_g1175List.toArray(l_strG1175s);
            l_voucherInfo.confirmVoucherDiv = l_strG1175s;
        }
        
        int l_intG1311Cnt = l_g1311List.size();
        String[] l_strG1311s = null;
        if (l_intG1311Cnt > 0)
        {
            l_strG1311s = new String[l_intG1311Cnt];
            l_g1311List.toArray(l_strG1311s);
            l_voucherInfo.tradeConditionVoucherDiv = l_strG1311s;
        }

        int l_intG9801Cnt = l_g9801List.size();
        String[] l_strG9801s = null;
        if (l_intG9801Cnt > 0)
        {
            l_strG9801s = new String[l_intG9801Cnt];
            l_g9801List.toArray(l_strG9801s);
            l_voucherInfo.insiderVoucherDiv = l_strG9801s;
        }
        
        int l_intG1220Cnt = l_g1220List.size();
        String[] l_strG1220s = null;
        if (l_intG1220Cnt > 0)
        {
            l_strG1220s = new String[l_intG1220Cnt];
            l_g1220List.toArray(l_strG1220s);
            l_voucherInfo.gpVoucherDiv = l_strG1220s;
        }
        
        int l_intG8610Cnt = l_g8610List.size();
        String[] l_strG8610s = null;
        if (l_intG8610Cnt > 0)
        {
            l_strG8610s = new String[l_intG8610Cnt];
            l_g8610List.toArray(l_strG8610s);
            l_voucherInfo.listedHolderVoucherDiv = l_strG8610s;
        }
        
        int l_intG1190Cnt = l_g1190List.size();
        String[] l_strG1190s = null;
        if (l_intG1190Cnt > 0)
        {
            l_strG1190s = new String[l_intG1190Cnt];
            l_g1190List.toArray(l_strG1190s);
            l_voucherInfo.accRealNameVoucherDiv = l_strG1190s;
        } 
        
        int l_intG43Cnt = l_g43List.size();
        String[] l_strG43s = null;
        if (l_intG43Cnt > 0)
        {
            l_strG43s = new String[l_intG43Cnt];
            l_g43List.toArray(l_strG43s);
            l_voucherInfo.foreignSaveInfoVoucherDiv = l_strG43s;
        }

        int l_intGs103Cnt = l_gs103List.size();
        String[] l_strGs103s = null;
        if (l_intGs103Cnt > 0)
        {
            l_strGs103s = new String[l_intGs103Cnt];
            l_gs103List.toArray(l_strGs103s);
            l_voucherInfo.agencyVoucherDiv = l_strGs103s;
        }
        log.exiting(STR_METHOD_NAME);
        return l_voucherInfo;
    }
    
    /**
     * (to不備項目情報)<BR>
     * 口座開設不備オブジェクトより、不備項目情報の配列を生成する。<BR>
     * <BR>
     * １）　@不備項目情報List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@不備項目数分（10回 Loop）、不備項目情報を生成する。<BR>
     * <BR>
     * 　@※ 対象indexの不備項目に登録がある場合<BR>
     * （口座開設不備.is不備項目登録(index) == true）のみ<BR>
     * 　@　@　@２－１）～２－３）を実施する。以外continue;<BR>
     * <BR>
     * 　@２－１）　@不備項目情報生成<BR>
     * 　@　@不備項目情報オブジェクトを生成する。<BR>
     * <BR>
     * 　@２－２）　@プロパティセット<BR>
     * 　@　@不備項目情報に以下の通りプロパティをセットする。<BR>
     * <BR>
     * 　@　@不備項目情報.不備項目名 = 口座開設不備.get不備項目名(index)<BR>
     * 　@　@不備項目情報.コメント = 口座開設不備.getコメント(index)<BR>
     * 　@　@不備項目情報.完了フラグ = 口座開設不備.is完了(index)<BR>
     * <BR>　@
     * 　@２－３）　@不備項目情報List（：ArrayList）に要素を追加する。<BR>
     * 　@　@不備項目情報List（：ArrayList）.add()にて、生成した不備項目情報を<BR>
     * 追加する。<BR>
     * <BR>
     * ３）　@値返却<BR>
     * 　@不備項目情報List（：ArrayList）.toArray()を返却する。<BR>
     * @@param l_accOpenInvalidValues - 口座開設不備オブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo[]
     * @@roseuid 4191B3EF0275
     */
    public WEB3AccOpenInvalidItemInfo[] toAccOpenInvalidItemInfo(WEB3AccOpenInvalidValues l_accOpenInvalidValues) 
    {
        final String STR_METHOD_NAME = " toAccOpenInvalidItemInfo(WEB3AccOpenInvalidValues) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenInvalidValues == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）　@不備項目情報List（：ArrayList）生成
        ArrayList l_list = new ArrayList();
        
        //２）　@不備項目数分（10回 Loop）、不備項目情報を生成する。
        for (int i = 1; i < 11; i++)
        {
            //対象indexの不備項目に登録がある場合<BR>
            //（口座開設不備.is不備項目登録(index) == true）のみ
            //２－１）～２－３）を実施する。以外continue;
            if (l_accOpenInvalidValues.isInvalidItemRegist(i))
            {
                //２－１）　@不備項目情報生成 
                WEB3AccOpenInvalidItemInfo l_invaildItemInfo = 
                    new WEB3AccOpenInvalidItemInfo();
                    
                //２－２）　@プロパティセット 
                //不備項目情報.不備項目名 = 口座開設不備.get不備項目名(index)
                l_invaildItemInfo.invalidItemName = l_accOpenInvalidValues.getInvalidItemName(i);
                
                //不備項目情報.コメント = 口座開設不備.getコメント(index) 
                l_invaildItemInfo.comment = l_accOpenInvalidValues.getComment(i);
                
                //不備項目情報.完了フラグ = 口座開設不備.is完了(index)
                l_invaildItemInfo.compFlag = l_accOpenInvalidValues.isComplete(i);
                 
                //２－３）　@不備項目情報List（：ArrayList）に要素を追加する。 
                l_list.add(l_invaildItemInfo);
            }
        }

        //３）　@値返却
        int l_intCnt = l_list.size();
        WEB3AccOpenInvalidItemInfo[] l_invalidItemInfos = 
            new  WEB3AccOpenInvalidItemInfo[l_intCnt];
        
        l_list.toArray(l_invalidItemInfos);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_invalidItemInfos;
    }
    
    /**
     * (to口座開設不備)<BR>
     * 不備項目情報の配列より、口座開設オブジェクトを生成する。<BR>
     * <BR>
     * １）　@口座開設不備生成<BR>
     * 　@口座開設不備オブジェクトを生成する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@証券会社コード：　@証券会社コード<BR>
     * 　@識別コード：　@識別コード<BR>
     * <BR>
     * 　@生成できなかった場合、デフォルトコンストラクタにてオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@不備項目数分（10回 Loop）、不備項目をセットする。<BR>
     * <BR>
     * 　@２－１）　@不備項目セット<BR>
     * 　@　@口座開設不備.set不備項目()にて、不備項目をセットする。<BR>
     * <BR>
     * 　@　@○ 不備項目情報の要素がある場合（index < 不備項目情報.length）<BR>
     * <BR>
     * 　@　@　@[set不備項目()に指定する引数]<BR>
     * 　@　@　@index：　@index<BR>
     * 　@　@　@不備項目名：　@不備項目情報[index].不備項目名<BR>
     * 　@　@　@コメント：　@不備項目情報[index].コメント<BR>
     * 　@　@　@is完了：　@不備項目情報[index].完了フラグ<BR>
     * <BR>
     * 　@　@○ 不備項目情報の要素がない場合（index >= 不備項目情報.length）<BR>
     * <BR>
     * 　@　@　@[set不備項目()に指定する引数]<BR>
     * 　@　@　@index：　@index<BR>
     * 　@　@　@不備項目名：　@null<BR>
     * 　@　@　@コメント：　@null<BR>
     * 　@　@　@is完了：　@false<BR>
     * <BR>
     * ３）　@値返却<BR>
     * 　@口座開設不備オブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_accOpenInvalidItemInfo - 不備項目情報メッセージデータ
     * 
     * @@return webbroker3.accountopen.WEB3AccOpenInvalidValues
     * @@roseuid 41944A2F0198
     */
    public WEB3AccOpenInvalidValues toAccOpenInvalidValues(String l_strInstitutionCode, String l_strRequestNumber, WEB3AccOpenInvalidItemInfo[] l_accOpenInvalidItemInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " toAccOpenInvalidValues(String, String, WEB3AccOpenInvalidItemInfo[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenInvalidItemInfo == null)
        {
            l_accOpenInvalidItemInfo = new WEB3AccOpenInvalidItemInfo[0];
        }
        
        WEB3AccOpenInvalidValues l_invalidValues = null;
        
        try
        {
            //１）　@口座開設不備生成 
            l_invalidValues = new WEB3AccOpenInvalidValues
                (l_strInstitutionCode, l_strRequestNumber);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            //生成できなかった場合、デフォルトコンストラクタにてオブジェクトを生成する。
            AccOpenInvalidValuesParams l_params = 
                new AccOpenInvalidValuesParams();
            l_params.setInstitutionCode(l_strInstitutionCode);
            l_params.setAccOpenRequestNumber(l_strRequestNumber);
            l_invalidValues = new WEB3AccOpenInvalidValues(l_params);            
        }
        
        int l_intCnt = l_accOpenInvalidItemInfo.length;
        
        //２）　@不備項目数分（10回 Loop）、不備項目をセットする。
        for (int i = 0; i < 10; i++)
        {
            //２－１）　@不備項目セット 
            //口座開設不備.set不備項目()にて、不備項目をセットする。
            //不備項目情報の要素がある場合（index < 不備項目情報.length）
            if (i < l_intCnt && l_accOpenInvalidItemInfo[i] != null)
            {
                //index：index
                //不備項目名：　@不備項目情報[index].不備項目名 
                //コメント：　@不備項目情報[index].コメント 
                //is完了：　@不備項目情報[index].完了フラグ
                WEB3AccOpenInvalidItemInfo l_invalidItemInfo = 
                    l_accOpenInvalidItemInfo[i];
                l_invalidValues.setInvalidItem(
                    i+1, 
                    l_invalidItemInfo.invalidItemName,
                    l_invalidItemInfo.comment,
                    l_invalidItemInfo.compFlag);                 
            }
            //不備項目情報の要素がない場合（index >= 不備項目情報.length） 
            else
            {
                //index：　@index         
                //不備項目名：　@null 
                //コメント：　@null 
                //is完了：　@false
                l_invalidValues.setInvalidItem(i+1, 
                    null,
                    null,
                    false);  
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_invalidValues;
    }
    
    /**
     * (to列物理名)<BR>
     * メッセージオブジェクトの項目名を、対応する口座開設見込客テーブルの<BR>
     * 列物理名に変換する。<BR>
     * <BR>
     * 例１）　@引数で、"addressKana1"が指定された場合、<BR>
     * "address_line1_kana"(*1)を返却する。<BR>
     * 例２）　@引数で、"houseHolderOfficeInfo.occupationDiv"(*2) が<BR>
     * 指定された場合、"householder_occupation_div"を返却する。<BR>
     * <BR>
     * (*1) 住所１（カナ）　@<BR>
     * (*2) 世帯主勤務先情報.職業区分　@<BR>
     * @@param l_strMessageItemSymbolName - メッセージ項目物理名<BR>
     * <BR>
     * ※　@口座開設申込情報の各プロパティ物理名<BR>
     * ※　@Unitクラスの変数は、世帯主勤務先情報.役職名のように、<BR>
     * 「変数名.項目名」の形式で指定する。<BR>
     * 
     * 
     * @@return String
     * @@roseuid 419348B900B2
     */
    public String toColumnSymbolName(String l_strMessageItemSymbolName) 
    {
        final String STR_METHOD_NAME = " toColumnSymbolName(String) ";
        log.entering(STR_METHOD_NAME);
         
        String l_strColSymName = "";
        
        //資料請求日
        if (WEB3AccOpenApplyInfoItemDef.INFO_CLAIM_DATE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INFOMATION_CLAIM_DATETIME;
        }
        //更新日
        else if (WEB3AccOpenApplyInfoItemDef.UPDATE_DATE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.LAST_UPDATED_TIMESTAMP;
        }
        //更新者コード
        else if (WEB3AccOpenApplyInfoItemDef.UPDATER_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.LAST_UPDATER;
        }
        //識別コード
        else if (WEB3AccOpenApplyInfoItemDef.REQUEST_NUMBER.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
        }
        //入力区分
        else if (WEB3AccOpenApplyInfoItemDef.INPUT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ORDER_DIV;
        }
        //作成者コード
        else if (WEB3AccOpenApplyInfoItemDef.CREATOR_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.CREATOR;
        }
        //既存口座フラグ
        else if (WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_ACCOUNT_FLAG;
        }
        //既存口座コード
        else if (WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_ACCOUNT_CODE;
        }
        //既存口座部店名
        else if (WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_BRANCH_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_BRANCH_NAME;
        }
        //証券会社コード
        else if (WEB3AccOpenApplyInfoItemDef.INSTITUTION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;
        }
        //部店コード
        else if (WEB3AccOpenApplyInfoItemDef.BRANCH_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
        }
        //口座区分
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_TYPE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_DIV;
        }
        //顧客コード
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
        }
        //扱者コード（SONAR）
        else if (WEB3AccOpenApplyInfoItemDef.TRADER_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
        }
        //メールアドレス
        else if (WEB3AccOpenApplyInfoItemDef.MAIL_ADDRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EMAIL_ADDRESS;
        }
        //メールアドレス（携帯）
        else if (WEB3AccOpenApplyInfoItemDef.MOBILE_MAIL_ADDRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EMAIL_ADDRESS_ALT1;
        }
        //顧客姓（漢字）
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_FAMILY_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME;
        }
        //顧客名（漢字）
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME;
        }
        //顧客姓（カナ）
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_FAMILY_NAME_KANA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1;
        }
        //顧客名（カナ）
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_NAME_KANA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1;
        }
        //生年月日年号
        else if (WEB3AccOpenApplyInfoItemDef.ERA_BORN.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ERA_BORN;
        }
        //生年月日
        else if (WEB3AccOpenApplyInfoItemDef.BORN_DATE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.BORN_DATE;
        }
        //性別
        else if (WEB3AccOpenApplyInfoItemDef.SEX.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEX;
        }
        //暗証番号
        else if (WEB3AccOpenApplyInfoItemDef.PASSWORD.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INITIAL_PASSWORD;
        }
        //郵便番号
        else if (WEB3AccOpenApplyInfoItemDef.ZIP_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
        }
        //住所1（カナ）
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1_KANA;
        }
        //住所2（カナ）
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2_KANA;
        }
        //住所3（カナ）
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3_KANA;
        }
        //住所1（漢字）
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1;
        }
        //住所2（漢字）
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2;
        }
        //住所3（漢字）
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3;
        }
        //電話番号
        else if (WEB3AccOpenApplyInfoItemDef.TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.TELEPHONE;
        }
        //携帯番号
        else if (WEB3AccOpenApplyInfoItemDef.MOBILE_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.MOBILE;
        }
        //ＦＡＸ番号
        else if (WEB3AccOpenApplyInfoItemDef.FAX_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAX;
        }
        //勤務先情報.職業区分
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OCCUPATION_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OCCUPATION_DIV;
        }
        //勤務先情報.勤務先名称
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE;
        }
        //勤務先情報.勤務先郵便番号
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_ZIP_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_ZIP_CODE;
        }
        //勤務先情報.勤務先住所
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_ADRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_ADDRESS;
        }
        //勤務先情報.勤務先電話番号
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_TELEPHONE;
        }
        //勤務先情報.勤務先ＦＡＸ番号
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_FAX_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_FAX;
        }
        //勤務先情報.所属部署
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_DEPARTMENT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.DEPARTMENT;
        }
        //勤務先情報.役職名
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_POSITION.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.POST;
        }
        //連絡先住所
        else if (WEB3AccOpenApplyInfoItemDef.CONTACT_ADDRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_ADDRESS;
        }
        //連絡先電話番号
        else if (WEB3AccOpenApplyInfoItemDef.CONTACT_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_TELEPHONE;
        }
        //続柄区分
        else if (WEB3AccOpenApplyInfoItemDef.FAMILY_RELATION_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_RELATIONSHIP;
        }
        //続柄（その他）
        else if (WEB3AccOpenApplyInfoItemDef.FAMILY_RELATION_ETC.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_RELATIONSHIP_ETC;
        }
        //世帯主名（漢字）
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER;
        }
        //世帯主名（カナ）
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_NAME_KANA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_KANA;
        }
        //世帯主勤務先情報.職業区分
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OCCUPATION_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OCCUPATION_DIV;
        }
        //世帯主勤務先情報.勤務先名称
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE;
        }
        //世帯主勤務先情報.勤務先住所
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_ADRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_ADDRESS;
        }
        //世帯主勤務先情報.勤務先電話番号
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_TEL;
        }
        //世帯主勤務先情報.勤務先ＦＡＸ番号
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_FAX_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_FAX;
        }
        //世帯主勤務先情報.所属部署
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_DEPARTMENT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_DEPARTMENT;
        }
        //世帯主勤務先情報.役職名
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_POSITION.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_POST;
        }
        //居住／非居住区分
        else if (WEB3AccOpenApplyInfoItemDef.RESIDENT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.RESIDENT;
        }
        //振替区分
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.TRANSFER_DIV;
        }
        //口座名義人
        else if (WEB3AccOpenApplyInfoItemDef.FINANCIAL_ACCOUNT_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NAME;
        }
        //投資目的区分
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_PURPOSE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INVEST_PURPOSE_DIV;
        }
        //取引動機@区分
        else if (WEB3AccOpenApplyInfoItemDef.APPLI_MOTIVAT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.APPLI_MOTIVAT_DIV;
        }
        //年収区分
        else if (WEB3AccOpenApplyInfoItemDef.ANNUAL_INCOME_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_DIV;
        }
        //金融資産区分
        else if (WEB3AccOpenApplyInfoItemDef.ASSET_VALUE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_DIV;
        }
        //運用予定額区分
        else if (WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_AMOUNT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_AMOUNT_DIV;
        }
        //資産の性格区分
        else if (WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_DIV;
        }
        //資産の性格（その他）
        else if (WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_ETC.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_ETC;
        }
        //検索用区分
        else if (WEB3AccOpenApplyInfoItemDef.SEARCH_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_FLAG;
        }
        //本人確認書類区分
        else if (WEB3AccOpenApplyInfoItemDef.ID_CONFIRM_DOC_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_DIV;
        }
        //本人確認書類（その他）
        else if (WEB3AccOpenApplyInfoItemDef.ID_CONFIRM_DOC_ETC.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_ETC;
        }
        //現物株式口座区分
        else if (WEB3AccOpenApplyInfoItemDef.EQUITY_TAX_TYPE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SPECIAL_ACC;
        }
        //信用取引口座区分
        else if (WEB3AccOpenApplyInfoItemDef.MARGIN_TAX_TYPE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SPECIAL_ACC_MARGIN;
        }
        //内部者登録フラグ
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_FLAG;
        }
        //内部者銘柄名
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_PRODUCT_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.PRODUCT_NAME;
        }
        //送付先郵便番号
        else if (WEB3AccOpenApplyInfoItemDef.SEND_ZIP_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ZIP_CODE;
        }
        //送付先住所１
        else if (WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE1;
        }
        //送付先住所２
        else if (WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE2;
        }
        //送付先住所３
        else if (WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE3;
        }
        //区分１
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV1;
        }
        //区分２
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV2;
        }
        //区分３
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV3;
        }
        //区分４
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV4.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV4;
        }
        //区分５
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV5.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV5;
        }
        //区分６
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV6.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV6;
        }
        //区分７
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV7.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV7;
        }
        //区分８
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV8.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV8;
        }
        //区分９
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV9.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV9;
        }
        //区分１０
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV10.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV10;
        }
        //フラグ１
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG1;
        }
        //フラグ２
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG2;
        }
        //フラグ３
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG3;
        }
        //フラグ４
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG4.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG4;
        }
        //フラグ５
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG5.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG5;
        }
        //フラグ６
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG6.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG6;
        }
        //フラグ７
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG7.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG7;
        }
        //フラグ８
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG8.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG8;
        }
        //フラグ９
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG9.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG9;
        }
        //フラグ１０
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG10.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG10;
        }
        //テキスト１
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT1;
        }
        //テキスト２
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT2;
        }
        //テキスト３
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT3;
        }
        //テキスト４
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT4.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT4;
        }
        //テキスト５
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT5.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT5;
        }
        //テキスト６
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT6.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT6;
        }
        //テキスト７
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT7.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT7;
        }
        //テキスト８
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT8.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT8;
        }
        //テキスト９
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT9.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT9;
        }
        //テキスト１０
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT10.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT10;
        }
        //振込先銀行情報.銀行コード
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_CODE;
        }
        //振込先銀行情報.銀行名
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_NAME;
        }
        //振込先銀行情報.支店コード
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_BRANCH_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_CODE;
        }
        //振込先銀行情報.支店名
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_BRANCH_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_NAME;
        }
        //振込先銀行情報.預金区分
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_SAVE_DIV;
        }
        //振込先銀行情報.口座番号
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NO;
        }
        //振込先銀行情報.振替手数料区分
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_TRANSFER_COMMISSION_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.TRANS_COMMISSION;
        }
        //郵便振替情報.通帳番号
        else if (WEB3AccOpenApplyInfoItemDef.POSTAL_TRANSFER_INFO_PASSBOOK_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.POSTAL_SAVE_NO;
        }
        //郵便振替情報.通帳記号
        else if (WEB3AccOpenApplyInfoItemDef.POSTAL_TRANSFER_INFO_PASSBOOK_SIGN.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.POSTAL_SAVE_CODE;
        }
        //投資経験情報.現物株式
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_EQUITY_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_EQUITY;
        }
        //投資経験情報.信用取引
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_MARGIN_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_MARGIN;
        }
        //投資経験情報.債権
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_BOND_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_BOND;
        }
        //投資経験情報.転換社債
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_WT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_WT;
        }
        //投資経験情報.投資信託（株式）
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FUND_SK_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FUND_SK;
        }
        //投資経験情報.投資信託（債権）
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FUND_BD_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FUND_BD;
        }
        //投資経験情報.先物・オプション
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FO_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FO;
        }
        //投資経験情報.外国証券
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_F_EQUITY_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_F_EQUITY;
        }
        //投資経験情報.その他
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_ETC_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_ETC;
        }
        //興味のある取引情報.現物株式フラグ
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_EQUITY_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_EQUITY;
        }
        //興味のある取引情報.株式ミニ投資フラグ
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_MSTK_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MINISTOCK;
        }
        //興味のある取引情報.信用取引フラグ
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_MARGIN_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MARGIN;
        }
        //興味のある取引情報.債権フラグ
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_BOND_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_BOND;
        }
        //興味のある取引情報.投資信託フラグ
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_FUND_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FUND;
        }
        //興味のある取引情報.先物・オプションフラグ
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_FO_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FO;
        }
        //興味のある取引情報.外国証券フラグ
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_F_EQUITY_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_F_EQUITY;
        }
        //興味のある取引情報.その他フラグ
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_ETC_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_ETC;
        }
        //顧客正式名称作成区分
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME_VOUCHER_DIV;
        }
        //顧客正式名称１
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME1;
        }
        //顧客正式名称２
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME2;
        }
        //仲介業扱者コード
        else if (WEB3AccOpenApplyInfoItemDef.BROKERAGE_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.BROKERAGE_TRADER_CODE;
        }
        //各社拡張項目（区分1１）
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV11.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV11;
        }
        //各社拡張項目（区分１2）
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV12.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV12;
        }
        //各社拡張項目（区分１3）
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV13.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV13;
        }
        //各社拡張項目（区分１4）
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV14.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV14;
        }
        //各社拡張項目（区分１5）
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV15.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV15;
        }
        //（内部者）作成区分
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_CREATE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_VOUCHER_DIV;
        }
        //（内部者）銘柄コード
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_PRODUCT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_PRODUCT_CODE;
        }
        //（内部者）関係区分
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_RELATION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_RELATION_DIV;
        }
        //（内部者）役員名
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_EXECUTIVE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_OFFICER_NAME;
        }
        //（内部者）役職名コード
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_POSITION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_CODE;
        }
        //（内部者）役職名
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_POSITION.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_NAME;
        }
        //（ＧＰ）作成区分
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_CREATE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_VOUCHER_DIV;
        }
        //（ＧＰ）コース
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_COURSE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_COURSE;
        }
        //（ＧＰ）プラン
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_PLAN.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PLAN;
        }
        //（ＧＰ）目標額
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_FIGURE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_FIGURE;
        }
        //（ＧＰ）目標年
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_YEAR.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_YEAR;
        }
        //（ＧＰ）目標月
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_MONTH.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_MONTH;
        }
        //（ＧＰ）積立額
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_INSTALLMENT_FIGURE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_INSTALLMENT_FIGURE;
        }
        //（ＧＰ）入金周期
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_DEPOSIT_CYCLE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_DEPOSIT_CYCLE;
        }
        //（ＧＰ）受渡経路
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_PAYMENT_ROOT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PAYMENT_ROOT;
        }
        //（ＧＰ）再投資区分
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_REINVEST_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_REINVEST_DIV;
        }
        //（ＧＰ）税区分
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TAX_TYPE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TAX_DIV;
        }
        //（ＧＰ）（優）限度額
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TAXFREE_LIMIT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TAXFREE_LIMIT;
        }
        //（ＧＰ）（特優）限度額
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_SPECIAL_TAXFREE_LIMIT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_SPECIAL_TAXFREE_LIMIT;
        }
        //（ＧＰ）加入摘要
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_SUBSCR_SUMMARY.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_SUBSCR_SUMMARY;
        }
        //（ＧＰ）銘柄コード
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_PRODUCT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PRODUCT_CODE;
        }
        //（ＧＰ）担保客
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_MORTGAGE_CUSTOMER.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_MORTGAGE_CUSTOMER;
        }
        //（ＧＰ）ミックス客
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_MIX_CUSTOMER.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_MIX_CUSTOMER;
        }
        //（ＧＰ）契約書
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_CONTRACT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_CONTRACT;
        }
        //上場外株）作成区分
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_CREATE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_VOUCHER_DIV;
        }
        //（上場外株）譲渡
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_TAXATION_TRAN.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TAXATION_TRAN_DIV;
        }
        //（上場外株）住所（カナ）
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_ADDRESS_KANA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_ADDRESS_LINE_KANA;
        }
        //（上場外株）送金
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_TRANSFER_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TRANSFER_DIV;
        }
        //（上場外株）銀行コード
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_INSTITUTION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_INSTITUTION_CODE;
        }
        //（上場外株）支店コード
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_BRANCH_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_BRANCH_CODE;
        }
        //（上場外株）預金区分
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_SAVE_DIV;
        }
        //（上場外株）口座番号
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_ACCOUNT_NO;
        }
        //(外貨預金口座情報)口座番号
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NO;
        }
        //(外貨預金口座情報)口座名義人
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME;
        }
        //(外貨預金口座情報)口座名義人英数
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME_ALPHA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME_ENG;
        }
        //(外貨預金口座情報)預金区分
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_SAVE_DIV;
        }
        // 削除フラグ
        else if (WEB3AccOpenApplyInfoItemDef.DELETE_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.DELETE_FLAG;
        }
        //削除日時
        else if (WEB3AccOpenApplyInfoItemDef.DELETE_DATE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.DELETE_TIMESTAMP;
        }
        //印刷フラグ
        else if (WEB3AccOpenApplyInfoItemDef.PRINT_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.PRINT_FLAG;
        }
        //受領フラグ
        else if (WEB3AccOpenApplyInfoItemDef.RECEIVE_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.RECEIPT_FLAG;
        }
        //承諾フラグ
        else if (WEB3AccOpenApplyInfoItemDef.APPROVE_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGREEMENT_FLAG;
        }
        //外国人フラグ
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGNER_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_FLAG;
        }
        //機@構通知情報
        //フリガナ1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME_KANA1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA1;
        }
        //フリガナ2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME_KANA2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA2;
        }
        //名称1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME1;
        }
        //名称2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME2;
        }
        //住所1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ADDRESS1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE1;
        }
        //住所2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ADDRESS2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE2;
        }
        //代表者の役職
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_POST.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_POST;
        }
        //代表者のフリガナ1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME_KANA1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA1;
        }
        //代表者のフリガナ2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME_KANA2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA2;
        }
        //代表者の氏名1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME1;
        }
        //代表者の氏名2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME2;
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_strColSymName;
    }
    
    /**
     * (toメッセージ項目名)<BR>
     * 口座開設見込客テーブルの列物理名を対応するメッセージオブジェクトの<BR>
     * 項目名に変換する。<BR>
     * <BR>
     * 例１）　@引数で、"address_line1_kana"が指定された場合、<BR>
     * "addressKana1"(*1)を返却する。<BR>
     * 例２）　@引数で、"householder_occupation_div"が指定された場合、<BR>
     * "houseHolderOfficeInfo.occupationDiv"(*2) を返却する。<BR>
     * <BR>
     * (*1) 住所１（カナ）　@<BR>
     * (*2) 世帯主勤務先情報.職業区分<BR>
     * @@param l_strColumnSymbolName - 列物理名<BR>
     * <BR>
     * ※　@口座開設見込客テーブルの列物理名<BR>
     * 
     * 
     * @@return String
     * @@roseuid 419C593C033F
     */
    public String toMessageItemName(String l_strColumnSymbolName) 
    {
        final String STR_METHOD_NAME = " toMessageItemName(String) ";
        log.entering(STR_METHOD_NAME);
         
        String l_strMessageItemName = "";

        //証券会社コード
        if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSTITUTION_CODE;
        }
        //部店コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.BRANCH_CODE;
        }
        //識別コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.REQUEST_NUMBER;
        }
        //口座コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_CODE;
        }
        //扱者コード（SONAR）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRADER_CODE;
        }
        //既存口座フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_ACCOUNT_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_FLAG;
        }
        //既存口座部店名
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_BRANCH_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_BRANCH_NAME;
        }
        //既存口座コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_ACCOUNT_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_CODE;
        }
        //口座区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_TYPE;
        }
        //入力区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ORDER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INPUT_DIV;
        }
        //資料請求日時
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INFOMATION_CLAIM_DATETIME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INFO_CLAIM_DATE;
        }
        //初期パスワード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INITIAL_PASSWORD.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.PASSWORD;
        }
        //顧客姓（漢字）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_FAMILY_NAME;
        }
        //顧客名（漢字）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_NAME;
        }
        //顧客姓（カナ）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_FAMILY_NAME_KANA;
        }
        //顧客名（カナ）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_NAME_KANA;
        }
        //性別
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEX.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEX;
        }
        //生年月日年号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ERA_BORN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ERA_BORN;
        }
        //生年月日
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.BORN_DATE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.BORN_DATE;
        }
        //emailアドレス
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EMAIL_ADDRESS.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.MAIL_ADDRESS;
        }
        //emailアドレス１
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EMAIL_ADDRESS_ALT1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.MOBILE_MAIL_ADDRESS;
        }
        //郵便番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ZIP_CODE;
        }
        //住所１
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS1;
        }
        //住所２
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS2;
        }
        //住所３
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS3;
        }
        //住所１（カナ）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA1;
        }
        //住所２（カナ）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA2;
        }
        //住所３（カナ）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA3;
        }
        //電話番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.TELEPHONE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TELEPHONE;
        }
        //連絡先電話番号（携帯）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.MOBILE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.MOBILE_TELEPHONE;
        }
        //ＦＡＸ番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAX.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FAX_TELEPHONE;
        }
        //職業区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OCCUPATION_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OCCUPATION_DIV;
        }
        //勤務先名称
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_NAME;
        }
        //勤務先郵便番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_ZIP_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_ZIP_CODE;
        }
        //勤務先住所
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_ADDRESS.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_ADRESS;
        }
        //勤務先電話番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_TELEPHONE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_TELEPHONE;
        }
        //勤務先FAX番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_FAX.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_FAX_TELEPHONE;
        }
        //所属部署
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.DEPARTMENT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_DEPARTMENT;
        }
        //役職
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.POST.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_POSITION;
        }
        //連絡先住所
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_ADDRESS.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.CONTACT_ADDRESS;
        }
        //連絡先電話番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_TELEPHONE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.CONTACT_TELEPHONE;
        }
        //続柄区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_RELATIONSHIP.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FAMILY_RELATION_DIV;
        }
        //続柄区分（その他）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_RELATIONSHIP_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FAMILY_RELATION_ETC;
        }
        //世帯主名（漢字）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_NAME;
        }
        //世帯主名（カナ）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_NAME_KANA;
        }
        //世帯主職業区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OCCUPATION_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OCCUPATION_DIV;
        }
        //世帯主勤務先
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_NAME;
        }
        //世帯主勤務先住所
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_ADDRESS.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_ADRESS;
        }
        //世帯主所属部署
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_DEPARTMENT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_DEPARTMENT;
        }
        //世帯主勤務先電話番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_TEL.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_TELEPHONE;
        }
        //世帯主勤務先FAX番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_FAX.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_FAX_TELEPHONE;
        }
        //世帯主役職
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_POST.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_POSITION;
        }
        //居住／非居住区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.RESIDENT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.RESIDENT_DIV;
        }
        //振替区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.TRANSFER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_DIV;
        }
        //銀行コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_CODE;
        }
        //銀行名
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_NAME;
        }
        //支店コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_BRANCH_CODE;
        }
        //支店名
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_BRANCH_NAME;
        }
        //預金区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_SAVE_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_DIV;
        }
        //口座番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_CODE;
        }
        //通帳記号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.POSTAL_SAVE_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.POSTAL_TRANSFER_INFO_PASSBOOK_SIGN;
        }
        //通帳番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.POSTAL_SAVE_NO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.POSTAL_TRANSFER_INFO_PASSBOOK_CODE;
        }
        //口座名義人
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FINANCIAL_ACCOUNT_NAME;
        }
        //振替手数料区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.TRANS_COMMISSION.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_TRANSFER_COMMISSION_DIV;
        }
        //現物株式
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_EQUITY.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_EQUITY.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FROM.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_TO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_EQUITY_DIV;
        }
        //信用取引
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_MARGIN.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_MARGIN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_MARGIN_DIV;
        }
        //債券
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_BOND.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_BOND.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_BOND_DIV;
        }
        //転換社債
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_WT.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_WT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_WT_DIV;
        }
        //投資信託（株式）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FUND_SK.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FUND_SK.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FUND_SK_DIV;
        }
        //投資信託（公社債）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FUND_BD.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FUND_BD.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FUND_BD_DIV;
        }
        //先物・オプション
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FO.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FO_DIV;
        }
        //外国証券
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_F_EQUITY.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_F_EQUITY.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_F_EQUITY_DIV;
        }
        //その他
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_ETC.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_ETC_DIV;
        }
        //現物株式フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_EQUITY.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_EQUITY_FLAG;
        }
        //株式ミニ投資フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MINISTOCK.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_MSTK_FLAG;
        }
        //信用取引フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MARGIN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_MARGIN_FLAG;
        }
        //債券フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_BOND.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_BOND_FLAG;
        }
        //投資信託フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FUND.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_FUND_FLAG;
        }
        //先物・オプションフラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_FO_FLAG;
        }
        //外国証券フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_F_EQUITY.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_F_EQUITY_FLAG;
        }
        //その他フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_ETC_FLAG;
        }
        //投資目的区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INVEST_PURPOSE_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_PURPOSE_DIV;
        }
        //取引動機@区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.APPLI_MOTIVAT_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.APPLI_MOTIVAT_DIV;
        }
        //年収区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_DIV.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_FROM.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_TO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ANNUAL_INCOME_DIV;
        }
        //金融資産区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_DIV.equals(l_strColumnSymbolName) || 
            WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_FROM.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_TO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ASSET_VALUE_DIV;
        }
        //運用予定額
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_AMOUNT_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_AMOUNT_DIV;
        }
        //資金の性格
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_DIV;
        }
        //資金の性格（その他）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_ETC;
        }
        //検索用区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEARCH_DIV;
        }
        //本人確認書類区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ID_CONFIRM_DOC_DIV;
        }
        //本人確認書類区分（その他）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ID_CONFIRM_DOC_ETC;
        }
        //特定口座区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SPECIAL_ACC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EQUITY_TAX_TYPE;
        }
        //信用取引特定口座区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SPECIAL_ACC_MARGIN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.MARGIN_TAX_TYPE;
        }
        //内部者登録フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_FLAG;
        }
        //内部者銘柄名
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.PRODUCT_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_PRODUCT_NAME;
        }
        //送付先郵便番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ZIP_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEND_ZIP_CODE;
        }
        //送付先住所１
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS1;
        }
        //送付先住所２
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS2;
        }
        //送付先住所３
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS3;
        }
        //各社拡張項目（区分１）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV1;
        }
        //各社拡張項目（区分２）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV2;
        }
        //各社拡張項目（区分３）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV3;
        }
        //各社拡張項目（区分４）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV4.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV4;
        }
        //各社拡張項目（区分５）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV5.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV5;
        }
        //各社拡張項目（区分６）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV6.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV6;
        }
        //各社拡張項目（区分７）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV7.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV7;
        }
        //各社拡張項目（区分８）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV8.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV8;
        }
        //各社拡張項目（区分９）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV9.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV9;
        }
        //各社拡張項目（区分１０）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV10.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV10;
        }
        //各社拡張項目（フラグ1）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG1;
        }
        //各社拡張項目（フラグ2）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG2;
        }
        //各社拡張項目（フラグ3）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG3;
        }
        //各社拡張項目（フラグ4）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG4.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG4;
        }
        //各社拡張項目（フラグ5）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG5.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG5;
        }
        //各社拡張項目（フラグ６）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG6.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG6;
        }
        //各社拡張項目（フラグ７）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG7.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG7;
        }
        //各社拡張項目（フラグ８）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG8.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG8;
        }
        //各社拡張項目（フラグ９）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG9.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG9;
        }
        //各社拡張項目（フラグ１０）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG10.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG10;
        }
        //各社拡張項目（テキスト１）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT1;
        }
        //各社拡張項目（テキスト２）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT2;
        }
        //各社拡張項目（テキスト３）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT3;
        }
        //各社拡張項目（テキスト４）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT4.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT4;
        }
        //各社拡張項目（テキスト５）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT5.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT5;
        }
        //各社拡張項目（テキスト６）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT6.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT6;
        }
        //各社拡張項目（テキスト７）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT7.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT7;
        }
        //各社拡張項目（テキスト８）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT8.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT8;
        }
        //各社拡張項目（テキスト９）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT9.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT9;
        }
        //各社拡張項目（テキスト１０）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT10.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT10;
        }
        //更新者コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.LAST_UPDATER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.UPDATER_CODE;
        }
        //更新日時
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.LAST_UPDATED_TIMESTAMP.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.UPDATE_DATE;
        }
        //作成者コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.CREATOR.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.CREATOR_CODE;
        }
        //顧客正式名称作成区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME_VOUCHER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME_DIV;
        }
        //顧客正式名称１
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME1;
        }
        //顧客正式名称２
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME2;
        }
        //仲介業扱者コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.BROKERAGE_TRADER_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.BROKERAGE_CODE;
        }
        //各社拡張項目（区分1１）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV11.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV11;
        }
        //各社拡張項目（区分１2）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV12.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV12;
        }
        //各社拡張項目（区分１3）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV13.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV13;
        }
        //各社拡張項目（区分１4）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV14.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV14;
        }
        //各社拡張項目（区分１5）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV15.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV15;
        }
        //（内部者）作成区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_VOUCHER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_CREATE_DIV;
        }
        //（内部者）銘柄コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_PRODUCT_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_PRODUCT_CODE;
        }
        //（内部者）関係区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_RELATION_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_RELATION_CODE;
        }
        //（内部者）役員名
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_OFFICER_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_EXECUTIVE;
        }
        //（内部者）役職名コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_POSITION_CODE;
        }
        //（内部者）役職名
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_POSITION;
        }
        //（ＧＰ）作成区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_VOUCHER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_CREATE_DIV;
        }
        //（ＧＰ）コース
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_COURSE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_COURSE;
        }
        //（ＧＰ）プラン
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PLAN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_PLAN;
        }
        //（ＧＰ）目標額
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_FIGURE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_FIGURE;
        }
        //（ＧＰ）目標年
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_YEAR.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_YEAR;
        }
        //（ＧＰ）目標月
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_MONTH.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_MONTH;
        }
        //（ＧＰ）積立額
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_INSTALLMENT_FIGURE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_INSTALLMENT_FIGURE;
        }
        //（ＧＰ）入金周期
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_DEPOSIT_CYCLE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_DEPOSIT_CYCLE;
        }
        //（ＧＰ）受渡経路
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PAYMENT_ROOT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_PAYMENT_ROOT;
        }
        //（ＧＰ）再投資区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_REINVEST_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_REINVEST_DIV;
        }
        //（ＧＰ）税区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TAX_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TAX_TYPE;
        }
        //（ＧＰ）（優）限度額
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TAXFREE_LIMIT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TAXFREE_LIMIT;
        }
        //（ＧＰ）（特優）限度額
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_SPECIAL_TAXFREE_LIMIT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_SPECIAL_TAXFREE_LIMIT;
        }
        //（ＧＰ）加入摘要
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_SUBSCR_SUMMARY.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_SUBSCR_SUMMARY;
        }
        //（ＧＰ）銘柄コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PRODUCT_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_PRODUCT_CODE;
        }
        //（ＧＰ）担保客
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_MORTGAGE_CUSTOMER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_MORTGAGE_CUSTOMER;
        }
        //（ＧＰ）ミックス客
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_MIX_CUSTOMER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_MIX_CUSTOMER;
        }
        //（ＧＰ）契約書
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_CONTRACT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_CONTRACT;
        }
        //上場外株）作成区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_VOUCHER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_CREATE_DIV;
        }
        //（上場外株）譲渡
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TAXATION_TRAN_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_TAXATION_TRAN;
        }
        //（上場外株）住所（カナ）
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_ADDRESS_LINE_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_ADDRESS_KANA;
        }
        //（上場外株）送金
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TRANSFER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_TRANSFER_DIV;
        }
        //（上場外株）銀行コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_INSTITUTION_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_INSTITUTION_CODE;
        }
        //（上場外株）支店コード
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_BRANCH_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_BRANCH_CODE;
        }
        //（上場外株）預金区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_SAVE_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_DIV;
        }
        //（上場外株）口座番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_ACCOUNT_NO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_CODE;
        }            
        //(外貨預金口座情報)口座番号
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_CODE;
        }
        //(外貨預金口座情報)口座名義人
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME;
        }
        //(外貨預金口座情報)口座名義人英数
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME_ENG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME_ALPHA;
        }
        //(外貨預金口座情報)預金区分
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_SAVE_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_DIV;
        }
        // 削除フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.DELETE_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.DELETE_FLAG;
        }
        //削除日時
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.DELETE_TIMESTAMP.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.DELETE_DATE;
        }
        //印刷フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.PRINT_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.PRINT_FLAG;
        }
        //受領フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.RECEIPT_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.RECEIVE_FLAG;
        }
        //承諾フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGREEMENT_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.APPROVE_FLAG;
        }
        //外国人フラグ
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGNER_FLAG;
        }
        //機@構通知情報
        //フリガナ1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME_KANA1;
        }
        //フリガナ2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME_KANA2;
        }
        //名称1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME1;
        }
        //名称2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME2;
        }
        //住所1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ADDRESS1;
        }
        //住所2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ADDRESS2;
        }
        //代表者の役職
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_POST.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_POST;
        }
        //代表者のフリガナ1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME_KANA1;
        }
        //代表者のフリガナ2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME_KANA2;
        }
        //代表者の氏名1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME1;
        }
        //代表者の氏名2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME2;
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_strMessageItemName;
    }

    /**
     * (to口座開設審査待ち)<BR>
     * 口座開設審査待ちオブジェクトを生成する。<BR>
     * １）　@口座開設審査待ちオブジェクトを生成する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR> 
     * 　@なし<BR> 
     * <BR>
     * ２）　@値返却 <BR>
     * 　@口座開設審査待ちオブジェクトを返却する。<BR> 
     * @@return WEB3AccOpenJudgeWaiting
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenJudgeWaiting toAccOpenJudgeWaiting()
    {
        final String STR_METHOD_NAME = " toAccOpenJudgeWaiting() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@口座開設審査待ちオブジェクトを生成する。
        WEB3AccOpenJudgeWaiting l_judgeWaiting = new WEB3AccOpenJudgeWaiting();
        
        // ２）　@値返却 
        // 　@口座開設審査待ちオブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_judgeWaiting;
    }
    /**
     * (to口座開設メールアドレス重複チェック)<BR>
     * 口座開設メールアドレス重複チェックオブジェクトを生成する。<BR> 
     * <BR>
     * １）　@口座開設メールアドレス重複チェックオブジェクトを生成する。<BR> 
     * <BR>
     *   [コンストラクタの引数]<BR> 
     *   顧客コード。<BR> 
     *   識別コード。<BR> 
     *   証券会社コード。<BR> 
     * <BR>
     * ２）　@値返却<BR> 
     *   口座開設メールアドレス重複チェックオブジェクトを返却する。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード。<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return WEB3AccOpenMailAddressDuplicationCheck
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenMailAddressDuplicationCheck toAccOpenMailAddressDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = " toAccOpenMailAddressDuplicationCheck(String, String ,String) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@口座開設メールアドレス重複チェックオブジェクトを生成する。
        WEB3AccOpenMailAddressDuplicationCheck l_mailAddressDuplicationCheck = 
            new WEB3AccOpenMailAddressDuplicationCheck(
                l_strAccountCode, l_strRequestNumber, l_strInstitutionCode);
        
        log.exiting(STR_METHOD_NAME);
        // ２）　@値返却 
        //   口座開設メールアドレス重複チェックオブジェクトを返却する。
        return l_mailAddressDuplicationCheck;
    }
    
    /**
     * (to口座開設電話番号重複チェック )<BR>
     * 口座開設電話番号重複チェックオブジェクトを生成する。<BR> 
     * <BR>
     * １）　@口座開設電話番号重複チェックオブジェクトを生成する。<BR> 
     * <BR>
     *   [コンストラクタの引数]<BR> 
     *   顧客コード。<BR> 
     *   識別コード。<BR> 
     *   証券会社コード。<BR> 
     * <BR>
     * ２）　@値返却<BR> 
     * 　@口座開設電話番号重複チェックオブジェクトを返却する。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード。<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return WEB3AccOpenMailAddressDuplicationCheck
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenTelNumberDuplicationCheck toAccOpenTelNumberDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = " toAccOpenTelNumberDuplicationCheck(String, String ,String) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@口座開設電話番号重複チェックオブジェクトを生成する。
        WEB3AccOpenTelNumberDuplicationCheck l_telNumberDuplicationCheck = 
            new WEB3AccOpenTelNumberDuplicationCheck(
                l_strAccountCode, l_strRequestNumber, l_strInstitutionCode);
        
        // ２）　@値返却
        // 　@口座開設電話番号重複チェックオブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_telNumberDuplicationCheck;        
    }
    
}
@
