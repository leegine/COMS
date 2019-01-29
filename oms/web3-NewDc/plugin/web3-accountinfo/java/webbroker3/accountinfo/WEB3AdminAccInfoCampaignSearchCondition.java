head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignSearchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キャンペーン検索条件(WEB3AdminAccInfoCampaignSearchCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 魏 (中訊) 新規作成
Revision History : 2007/02/03 魏 (中訊) モデルNo.173
Revision History : 2007/02/03 魏 (中訊) モデルNo.177
Revision History : 2007/02/03 魏 (中訊) モデルNo.183
Revision History : 2007/02/28 Inomata(SCS)モデルNo.207
*/
package webbroker3.accountinfo;

import java.util.Date;

import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.util.WEB3LogUtility;

/**
 * (キャンペーン検索条件)<BR>
 * キャンペーン検索条件オブジェクト<BR>
 * <BR>
 * @@author  魏
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignSearchCondition
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignSearchCondition.class);

    /**
     * 証券会社コード<BR>
     */
    protected String institutionCode;

    /**
     * 部店コード<BR>
     */
    protected String branchCode;

    /**
     * 顧客コード<BR>
     */
    protected String accountCode;

    /**
     * 商品コード配列<BR>
     */
    protected String[] itemCodes;

    /**
     * キャンペーン名称<BR>
     */
    protected String campaignName;

    /**
     * 対象日<BR>
     */
    protected Date targetDate;

    /**
     * 対象期間From<BR>
     */
    protected String targetPeriodFrom;

    /**
     * 口座開設日From<BR>
     */
    protected Date accountOpenDateFrom;

    /**
     * 徴収率<BR>
     */
    protected String collectRate;

    /**
     * 扱者コード<BR>
     */
    protected String traderCode;

    /**
     * 口座開設区分<BR>
     */
    protected String accountOpenDiv;

    /**
     * 削除フラグ<BR>
     */
    protected String deleteFlag;

    /**
     * 手数料割引キャンペーン条件ID<BR>
     */
    protected String commissionCampaignConditionId;

    /**
     * 登録タイプの配列<BR>
     */
    protected String[] registerTypes;

    /**
     * @@roseuid 45C08B50007D
     */
    public WEB3AdminAccInfoCampaignSearchCondition()
    {
        this.resetCampaignCondition();
    }

    /**
     * (resetキャンペーン検索条件_all)<BR>
     * 属性の初期化を行う。<BR>
     * <BR>
     * -this.set手数料割引キャンペーン条件ID(null)<BR>
     * <BR>
     * -this.set証券会社コード(null)<BR>
     * <BR>
     * -this.set部店コード(null)<BR>
     * <BR>
     * -this.set顧客コード(null)<BR>
     * <BR>
     * -this.set商品コード(null)<BR>
     * <BR>
     * -this.set対象日(null)<BR>
     * <BR>
     * -this.set対象期間From(null)<BR>
     * <BR>
     * -this.set口座開設From(null)<BR>
     * <BR>
     * -this.set徴収率(null)<BR>
     * <BR>
     * -this.set扱者コード(null)<BR>
     * <BR>
     * -this.set口座開設区分(null)<BR>
     * <BR>
     * -this.set削除フラグ(null)<BR>
     * <BR>
     * -this.setキャンペーン名称(null)<BR>
     * <BR>
     * -this.set登録タイプ(null)<BR>
     * <BR>
     * <BR>
     * @@roseuid 45B8356F001C
     */
    public void resetCampaignCondition()
    {
        final String STR_METHOD_NAME = " resetCampaignCondition() ";
        log.entering(STR_METHOD_NAME);

        //this.set手数料割引キャンペーン条件ID(null)
        this.setCommissionCampaignConditionId(null);

        //this.set証券会社コード(null)
        this.setInstitutionCode(null);

        //this.set部店コード(null)
        this.setBranchCode(null);

        //this.set顧客コード(null)
        this.setAccountCode(null);

        //this.set商品コード(null)
        this.setItemCode(null);

        //this.set対象日(null)
        this.setTargetDate(null);

        //this.対象期間From(null)
        this.setTargetPeriodFrom(null);

        //this.set口座開設From(null)
        this.setAccountOpenDateFrom(null);

        //this.set徴収率(null)
        this.setCollectRate(null);

        //this.set扱者コード(null)
        this.setTraderCode(null);

        //this.set口座開設区分(null)
        this.setAccountOpenDiv(null);

        //this.set削除フラグ(null)
        this.setDeleteFlag(null);

        //this.setキャンペーン名称((null)
        this.setCampaignName(null);

        //this.set登録タイプ(null)
        this.setRegisterType(null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setキャンペーン検索条件_all)<BR>
     * 属性値の一括設定を行う。<BR>
     * <BR>
     * （引数）手数料割引キャンペーン条件ID != nullの場合、<BR>
     * -this.set手数料割引キャンペーン条件ID(（引数）手数料割引キャンペーン条件ID)<BR>
     * <BR>
     * （引数）証券会社コード != nullの場合、<BR>
     * -this.set証券会社コード(（引数）証券会社コード)<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.部店コード != nullの場合、<BR>
     * -this.set部店コード(（引数）手数料割引キャンペーン検索条件.部店コード)<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.顧客コード!= nullの場合、<BR>
     * -this.set顧客コード(（引数）手数料割引キャンペーン検索条件.顧客コード)<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.商品コード != nullの場合、<BR>
     * --this.set商品コード(（引数）手数料割引キャンペーン検索条件.<BR>
     * 商品コードを要素に持つ長さ１のString配列）<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.対象日 != nullの場合、<BR>
     * -this.set対象日(（引数）手数料割引キャンペーン検索条件.対象日)<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.徴収率 != nullの場合、<BR>
     * -this.set徴収率(（引数）手数料割引キャンペーン検索条件.徴収率)<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.扱者コード != nullの場合、<BR>
     * -this.set扱者コード(（引数）手数料割引キャンペーン検索条件.扱者コード)<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.口座開設区分 != nullの場合、<BR>
     * -this.set口座開設区分(（引数）手数料割引キャンペーン検索条件.口座開設区分)<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.削除フラグ != nullの場合、<BR>
     * -this.set削除フラグ(（引数）手数料割引キャンペーン検索条件.削除フラグ)<BR>
     * <BR>
     * （引数）手数料割引キャンペーン検索条件.キャンペーン名称 != nullの場合、<BR>
     * -this.setキャンペーン名称(（引数）手数料割引キャンペーン検索条件.キャンペーン名称)<BR>
     * <BR>
     * （引数）登録タイプ != nullの場合、<BR>
     * -this.set登録タイプ(（引数）登録タイプ)<BR>
     * <BR>
     * <BR>
     * @@param l_campaignSearchCondition -
     *            手数料割引キャンペーン検索条件オブジェクト<BR>
     * @@param l_strCampaignSearchConditionId -
     *            手数料割引キャンペーン条件ID<BR>
     * @@param l_strInstitutionCode -
     *            証券会社コード<BR>
     * @@param l_strRegistTypes -
     *            登録タイプの配列<BR>
     * @@roseuid 45B7070C007F
     */
    public void setCampaignCondition(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strCampaignSearchConditionId,
            String l_strInstitutionCode,
            String[] l_strRegistTypes)
    {
        final String STR_METHOD_NAME = " setCampaignCondition(WEB3AccInfoCampaignSearchCondition, String, String, String) ";
        log.entering(STR_METHOD_NAME);

        //（引数）手数料割引キャンペーン条件ID != nullの場合、
        if (l_strCampaignSearchConditionId != null)
        {
            this.setCommissionCampaignConditionId(l_strCampaignSearchConditionId);
        }
        //（引数）証券会社コード != nullの場合
        if (l_strInstitutionCode != null)
        {
            this.setInstitutionCode(l_strInstitutionCode);
        }

        if (l_campaignSearchCondition != null)
        {
            //（引数）手数料割引キャンペーン検索条件.部店コード != nullの場合
            if (l_campaignSearchCondition.branchCode != null)
            {
                this.setBranchCode(l_campaignSearchCondition.branchCode);
            }
            //（引数）手数料割引キャンペーン検索条件.顧客コード != nullの場合
            if (l_campaignSearchCondition.accountCode != null)
            {
                this.setAccountCode(l_campaignSearchCondition.accountCode);
            }
            //（引数）手数料割引キャンペーン検索条件.商品コード  != nullの場合
            if (l_campaignSearchCondition.itemCode != null)
            {
                String[] l_strItemCodes = new String[1];
                l_strItemCodes[0] = l_campaignSearchCondition.itemCode;
                this.setItemCode(l_strItemCodes);
            }
            //（引数）手数料割引キャンペーン検索条件.対象日  != nullの場合
            if (l_campaignSearchCondition.targetDate != null)
            {
                this.setTargetDate(l_campaignSearchCondition.targetDate);
            }
            //（引数）手数料割引キャンペーン検索条件.徴収率 != nullの場合
            if (l_campaignSearchCondition.collectRate != null)
            {
                this.setCollectRate(l_campaignSearchCondition.collectRate);
            }
            //（引数）手数料割引キャンペーン検索条件.扱者コード  != nullの場合
            if (l_campaignSearchCondition.traderCode != null)
            {
                this.setTraderCode(l_campaignSearchCondition.traderCode);
            }
            //（引数）手数料割引キャンペーン検索条件.口座開設区分  != nullの場合
            if (l_campaignSearchCondition.accountOpenDiv != null)
            {
                this.setAccountOpenDiv(l_campaignSearchCondition.accountOpenDiv);
            }
            //（引数）手数料割引キャンペーン検索条件.削除フラグ != nullの場合
            if (l_campaignSearchCondition.deleteFlag != null)
            {
                this.setDeleteFlag(l_campaignSearchCondition.deleteFlag);
            }
            //（引数）手数料割引キャンペーン検索条件.キャンペーン名称 != nullの場合
            if (l_campaignSearchCondition.campaignName != null)
            {
                this.setCampaignName(l_campaignSearchCondition.campaignName);
            }
        }

        //（引数）登録タイプ  != nullの場合、
        if (l_strRegistTypes != null)
        {
            this.setRegisterType(l_strRegistTypes);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setキャンペーン名称)<BR>
     * キャンペーン名称のセットを行う。<BR>
     * <BR>
     * this.キャンペーン名称 = （引数）キャンペーン名称<BR>
     * <BR>
     * @@param l_strCampaignName -
     *            キャンペーン名称<BR>
     * @@roseuid 45B80E1B005E
     */
    public void setCampaignName(String l_strCampaignName)
    {
        final String STR_METHOD_NAME = " setCampaignName(String) ";
        log.entering(STR_METHOD_NAME);

        this.campaignName = l_strCampaignName;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set扱者コード)<BR>
     * 扱者コードのセットを行う。<BR>
     * <BR>
     * this.扱者コード = （引数）扱者コード<BR>
     * <BR>
     * @@param l_strTraderCode -
     *            扱者コード<BR>
     * @@roseuid 45B80E1A0291
     */
    public void setTraderCode(String l_strTraderCode)
    {
        final String STR_METHOD_NAME = " setTraderCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.traderCode = l_strTraderCode;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客コード)<BR>
     * 顧客コードのセットを行う。<BR>
     * <BR>
     * this.顧客コード = （引数）顧客コード<BR>
     * <BR>
     * @@param l_strAccountCode -
     *            顧客コード<BR>
     * @@roseuid 45B80E1A010A
     */
    public void setAccountCode(String l_strAccountCode)
    {
        final String STR_METHOD_NAME = " setAccountCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.accountCode = l_strAccountCode;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set口座開設区分)<BR>
     * 口座開設区分のセットを行う。<BR>
     * <BR>
     * this.口座開設区分 = （引数）口座開設区分<BR>
     * <BR>
     * @@param l_strAccountOpenDiv -
     *            口座開設区分<BR>
     * @@roseuid 45B80E1A031D
     */
    public void setAccountOpenDiv(String l_strAccountOpenDiv)
    {
        final String STR_METHOD_NAME = " setAccountOpenDiv(String) ";
        log.entering(STR_METHOD_NAME);

        this.accountOpenDiv = l_strAccountOpenDiv;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set削除フラグ)<BR>
     * 削除フラグのセットを行う。<BR>
     * <BR>
     * this.削除フラグ = （引数）削除フラグ<BR>
     * <BR>
     * @@param l_strDeleteFlag -
     *            削除フラグ<BR>
     * @@roseuid 45B80E280168
     */
    public void setDeleteFlag(String l_strDeleteFlag)
    {
        final String STR_METHOD_NAME = " setDeleteFlag(String) ";
        log.entering(STR_METHOD_NAME);

        this.deleteFlag = l_strDeleteFlag;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set手数料割引キャンペーン条件ID)<BR>
     * 手数料割引キャンペーン条件IDのセットを行う。<BR>
     * <BR>
     * this.手数料割引キャンペーン条件ID= （引数）手数料割引キャンペーン条件ID<BR>
     * <BR>
     * @@param l_strCommissionCampaignConditionId -
     *            手数料割引キャンペーン条件ID<BR>
     * @@roseuid 45B80E280242
     */
    public void setCommissionCampaignConditionId(String l_strCommissionCampaignConditionId)
    {
        final String STR_METHOD_NAME = " setAccInfoCampaignConditionId(String) ";
        log.entering(STR_METHOD_NAME);

        this.commissionCampaignConditionId = l_strCommissionCampaignConditionId;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set商品コード)<BR>
     * 商品コードのセットを行う。<BR>
     * <BR>
     * this.商品コード = （引数）商品コード<BR>
     * <BR>
     * @@param l_strItemCodes -
     *            商品コード配列<BR>
     * @@roseuid 45B80E1A03AA
     */
    public void setItemCode(String[] l_strItemCodes)
    {
        final String STR_METHOD_NAME = " setItemCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.itemCodes = l_strItemCodes;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set証券会社コード)<BR>
     * 証券会社コードのセットを行う。<BR>
     * <BR>
     * this.証券会社コード= （引数）証券会社コード<BR>
     * <BR>
     * @@param l_strInstitutionCode -
     *            証券会社コード<BR>
     * @@roseuid 45B8119B00A4
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = " setInstitutionCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.institutionCode = l_strInstitutionCode;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set対象日)<BR>
     * 対象日のセットを行う。<BR>
     * <BR>
     * this.対象日= （引数）対象日<BR>
     * <BR>
     * @@param l_datTargetDate -
     *            対象日<BR>
     * @@roseuid 45B80E1B00EB
     */
    public void setTargetDate(Date l_datTargetDate)
    {
        final String STR_METHOD_NAME = " setTargetDate(Date) ";
        log.entering(STR_METHOD_NAME);

        this.targetDate = l_datTargetDate;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set対象期間From)<BR>
     * 対象期間Fromのセットを行う。<BR>
     * <BR>
     * this.対象期間From = （引数）対象期間From<BR>
     * <BR>
     * @@param l_datTargetPeriodFrom -
     *            対象期間From<BR>
     * @@roseuid 45B80E1A01D5
     */

    public void setTargetPeriodFrom(String l_datTargetPeriodFrom)
    {
        final String STR_METHOD_NAME = " setTargetPeriodFrom(Date) ";
        log.entering(STR_METHOD_NAME);

        this.targetPeriodFrom = l_datTargetPeriodFrom;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set口座開設日From)<BR>
     * 口座開設日Fromのセットを行う。<BR>
     * <BR>
     * this.口座開設日From= （引数）口座開設日From<BR>
     * <BR>
     * @@param l_datAccountOpenDateFrom -
     *            口座開設日From<BR>
     * @@roseuid 45B8115B0065
     */
    public void setAccountOpenDateFrom(Date l_datAccountOpenDateFrom)
    {
        final String STR_METHOD_NAME = " setAccountOpenDateFrom(Date) ";
        log.entering(STR_METHOD_NAME);

        this.accountOpenDateFrom = l_datAccountOpenDateFrom;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set徴収率)<BR>
     * 徴収率のセットを行う。<BR>
     * <BR>
     * this.徴収率 = （引数）徴収率<BR>
     * <BR>
     * @@param l_strCollectRate -
     *            徴収率<BR>
     * @@roseuid 45B80E1B0177
     */
    public void setCollectRate(String l_strCollectRate)
    {
        final String STR_METHOD_NAME = " setCollectRate(String) ";
        log.entering(STR_METHOD_NAME);

        this.collectRate = l_strCollectRate;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set登録タイプ)<BR>
     * 登録タイプのセットを行う。<BR>
     * <BR>
     * this.登録タイプ[] = （引数）登録タイプ[]<BR>
     * <BR>
     * @@param l_strRegisterTypes -
     *            登録タイプの配列<BR>
     * @@roseuid 45BD5855038C
     */
    public void setRegisterType(String[] l_strRegisterTypes)
    {
        final String STR_METHOD_NAME = " setRegisterType(String[]) ";
        log.entering(STR_METHOD_NAME);

        this.registerTypes = l_strRegisterTypes;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店コード)<BR>
     * 部店コードのセットを行う。<BR>
     * <BR>
     * this.部店コード = （引数）部店コード<BR>
     * <BR>
     * @@param l_strBranchCode -
     *            部店コード<BR>
     * @@roseuid 45B80DB1001F
     */
    public void setBranchCode(String l_strBranchCode)
    {
        final String STR_METHOD_NAME = " setBranchCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.branchCode = l_strBranchCode;

        log.exiting(STR_METHOD_NAME);
    }
}
@
