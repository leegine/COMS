head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMainAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客(WEB3GentradeMainAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 鄒政 (中訊) 新規作成
Revesion History : 2004/10/09 孟東 (中訊) getDisplayAccountCode()を追加
Revesion History : 2005/07/11 孟東 (中訊) is取引停止銘柄()に外株ロジックを追加
Revesion History : 2005/09/26 孟東 (中訊) is投信手数料無料()を追加
Revesion History : 2005/09/26 孟東 (中訊) is投信非課税()を追加
Revesion History : 2005/10/07 孟東 (中訊) is法@人()を追加
Revesion History : 2005/11/22 孟東 (中訊) is投信手数料無料()を削除
Revesion History : 2005/11/22 孟東 (中訊) is投信非課税()を削除
Revesion History : 2005/12/08 孟東 (中訊) is累投口座開設()を追加
Revesion History : 2006/06/14 凌建平 (中訊) 仕様変更・モデルNo.196を対応
Revesion History : 2006/09/19 栄イ (中訊) 仕様変更・モデルNo.211を対応
Revesion History : 2007/03/09 栄イ (中訊) 仕様変更・モデルNo.223を対応
Revesion History : 2007/09/12 栄イ (中訊) 仕様変更・モデルNo.268を対応
Revesion History : 2007/12/11 栄イ (中訊) 仕様変更・モデルNo.295を対応
Revesion History : 2007/12/17 柴双紅 (中訊) 仕様変更 モデルNo.287,No.298,No.302
Revesion History : 2008/01/26 栄イ (中訊) 仕様変更・モデルNo.316を対応
Revesion History : 2008/10/06 陸文静 (中訊) 仕様変更・モデルNo.335を対応
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Participant;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3AccountProductOrderStopDivDef;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3CfdAccOpenDivDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3OrgDepositDivDef;
import webbroker3.common.define.WEB3PTSAccOpenDivDef;
import webbroker3.common.define.WEB3ProductIdDef;
import webbroker3.common.define.WEB3QuoteTypeDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.common.define.WEB3SexDef;
import webbroker3.gentrade.data.AccountProductOrderStopRow;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeEquityMarginDivDef;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (顧客) <BR>
 * 顧客の属性情報を表現する。<BR>
 * xTradeのMainAcountを拡張したクラス。<BR>
 * <BR>
 * @@author 本郷　@千草(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl
 */
public class WEB3GentradeMainAccount extends MainAccountImpl
{

    /**
     * 口座コード <BR>
     */
    private String accountCode;

    /**
     * (証券会社コード) <BR>
     */
    private String institutionCode;

    /**
     * (部店コード) <BR>
     */
    private String branchCode;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMainAccount.class);

    /**
     * 顧客Rowオブジェクト。<BR>
     */
    private MainAccountRow accountRow;

    /**
     * 部店オブジェクト。<BR>
     */
    private Branch branch;

    /**
     * 補助口座オブジェクトの配列。<BR>
     */
    private SubAccount[] subAccounts;

    /**
     * 関係者オブジェクトの配列。<BR>
     */
    private Participant[] participants;

    /**
     * コンストラクタ。<BR>
     * <BR>
     * @@param l_lngInstId 証券会社ID
     * @@param l_strBranchCode 部店コード
     * @@param l_strAccountCode 口座コード
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQLの実行に失敗した場合
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DBサーバとの接続に失敗した場合
     * @@roseuid 403496F0022B
     */
    public WEB3GentradeMainAccount(
        long l_lngInstId,
        String l_strBranchCode,
        String l_strAccountCode)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngInstId, l_strBranchCode, l_strAccountCode);
        this.accountRow = (MainAccountRow)this.getDataSourceObject();
        this.accountCode = this.accountRow.getAccountCode();
        this.institutionCode = this.accountRow.getInstitutionCode();
        
    }

    /**
     * コンストラクタ。<BR>
     * <BR> 
     * @@param l_lngInstId 証券会社ID
     * @@param l_lngBranchId 部店ID
     * @@param l_strAccountCode 口座コード
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQLの実行に失敗した場合
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DBサーバとの接続に失敗した場合
     * @@roseuid 403496F000E3
     */
    public WEB3GentradeMainAccount(
        long l_lngInstId,
        long l_lngBranchId,
        String l_strAccountCode)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngInstId, l_lngBranchId, l_strAccountCode);
        this.accountRow = (MainAccountRow)this.getDataSourceObject();
        this.accountCode = this.accountRow.getAccountCode();
        this.institutionCode = this.accountRow.getInstitutionCode();
    }

    /**
     * コンストラクタ。<BR>
     * <BR> 
     * @@param l_row 顧客Rowオブジェクト
     * @@roseuid 403496F000D3
     */
    public WEB3GentradeMainAccount(MainAccountRow l_row)
    {
        super(l_row);
        this.accountRow = (MainAccountRow)this.getDataSourceObject();
        this.accountCode = this.accountRow.getAccountCode();
        this.institutionCode = this.accountRow.getInstitutionCode();
    }

    /**
     * コンストラクタ。<BR>
     * <BR> 
     * @@param l_accountId 顧客ID
     * @@param l_lngAccountLd
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 403496EF0382
     */
    public WEB3GentradeMainAccount(long l_lngAccountLd)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngAccountLd);
        this.accountRow = (MainAccountRow)this.getDataSourceObject();
        this.accountCode = this.accountRow.getAccountCode();
        this.institutionCode = this.accountRow.getInstitutionCode();
    }

    /**
     * 当オブジェクトに関連する部店オブジェクトを返す。<BR>
     * <BR> 
     * @@return 部店オブジェクト
     * @@roseuid 403496F00363
     */
    public Branch getBranch() 
    {
        final String STR_METHOD_NAME = "getBranch()";
        
        if (branch == null)
        {
            if (this.accountRow == null)
            {
                 this.accountRow = (MainAccountRow) this.getDataSourceObject();
            }
            long l_lngBranchId = accountRow.getBranchId();
            try
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                AccountManager l_accountMgr = l_finApp.getAccountManager();
                branch = l_accountMgr.getBranch(l_lngBranchId);
                return branch;
            }
            catch (NotFoundException nfe)
            {
                String s =
                    "Could not obtain branch  for id : "
                        + l_lngBranchId
                        + ", for account  with id "
                        + getAccountId();
                log.error(s, nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
        }
        else
        {
            return branch;
        }

    }

    /**
     * 与えられた引数をもとに顧客Rowオブジェクトを生成して返す。<BR>
     * <BR> 
     * @@param l_lngInstId 証券会社ID
     * @@param l_lngBranchId 部店ID
     * @@param l_strAccountCode 口座コード
     * @@return 顧客Rowオブジェクト
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQLの実行に失敗した場合
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DBサーバとの接続に失敗した場合
     * @@roseuid 403496F003A2
     */
    public static MainAccountRow getMainAccountRow(
        long l_lngInstId,
        long l_lngBranchId,
        String l_strAccountCode)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "getMainAccountRow(long, long, String)";
        
        try
        {
            MainAccountRow l_row =
                MainAccountDao.findRowByInstitutionIdBranchIdAccountCode(
                    l_lngInstId,
                    l_lngBranchId,
                    l_strAccountCode);
            if (l_row != null)
            {
                return l_row;
            }
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting account for instId,branchId,accountCode="
                    + l_lngInstId
                    + ','
                    + l_lngBranchId
                    + ","
                    + l_strAccountCode;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        throw new DataFindException(
            "No main_account row found for instId,branchId,accountCode="
                + l_lngInstId
                + ','
                + l_lngBranchId
                + ","
                + l_strAccountCode);
    }

    /**
     * 与えられた引数をもとに顧客Rowオブジェクトを生成して返す。<BR>
     * <BR>
     * @@param l_lngInstId 証券会社ID
     * @@param l_strBranchCode 部店コード
     * @@param l_strAccountCode 口座コード
     * @@return 顧客Rowオブジェクト
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQLの実行に失敗した場合
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DBサーバとの接続に失敗した場合
     * @@roseuid 403496F10111
     */
    public static MainAccountRow getMainAccountRow(
        long l_lngInstId,
        String l_strBranchCode,
        String l_strAccountCode)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "getMainAccountRow(long, String, String)";
        
        try
        {
            MainAccountRow l_row =
                MainAccountDao.findRowByInstitutionIdBranchCodeAccountCode(
                    l_lngInstId,
                    l_strBranchCode,
                    l_strAccountCode);
            if (l_row != null)
            {
                return l_row;
            }
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting account for instId,branchCode,accountCode="
                    + l_lngInstId
                    + ','
                    + l_strBranchCode
                    + ","
                    + l_strAccountCode;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        throw new DataFindException(
            "No main_account row found for instId,branchCode,accountCode="
                + l_lngInstId
                + ','
                + l_strBranchCode
                + ","
                + l_strAccountCode);
    }

    /**
     * 当オブジェクトに関連する補助口座オブジェクトの配列を返す。<BR>
     * <BR> 
     * @@return 補助口座オブジェクトの配列
     * @@roseuid 4042EDB8039F
     */
    public SubAccount[] getSubAccounts()
    {
        final String STR_METHOD_NAME = "getSubAccounts()";
        
        if (subAccounts == null)
        {
            try
            {
                subAccounts = WEB3GentradeSubAccount.getSubAccounts(this);
            }
            catch (DataQueryException de)
            {
                log.error(
                    "Exception while getting SubAccounts for account_id = "
                        + getAccountId(),
                    de);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            catch (DataNetworkException de)
            {
                log.error(
                    "Exception while getting SubAccounts for account_id = "
                        + getAccountId(),
                    de);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
        }
        return subAccounts;
    }

//    /**
//     * 当オブジェクトに関連する関係者オブジェクトの配列を返す。<BR>
//     * <BR> 
//     * @@return 関係者オブジェクトの配列
//     * @@roseuid 4042EDB803BE
//     */
//    public Participant[] getParticipants()
//    {
//        final String STR_METHOD_NAME = "getParticipants()";
//        
//        if (participants == null)
//        {
//            try
//            {
//                participants = ParticipantImpl.getParticipants(this);
//            }
//            catch (DataQueryException de)
//            {
//                log.error(
//                    "Exception while getting Participant rows from participant table for account id :"
//                        + getAccountId(),
//                    de);
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                    WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
//                    de.getMessage(),
//                    de);
//            }
//            catch (DataNetworkException de)
//            {
//                log.error(
//                    "Exception while getting Participant rows from participant table for account id :"
//                        + getAccountId(),
//                    de);
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                    WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
//                    de.getMessage(),
//                    de);
//            }
//        }
//        return participants;
//    }

    /**
     * (get受渡日税区分) <BR>
     * 顧客の、引数の受渡日における税区分（TaxTypeEnum）を返却する。<BR>
     *  <BR>
     * 基準日となるシステム日付を取得する。<BR>
     * （GtlUtils.getTradingSystem().getSystemTimestamp()）<BR>
     *  <BR>
     * 引数.受渡日と取得した基準日のYYYYの値により、以下の判定を行う。<BR>
     *  <BR>
     * ○引数.受渡日の日付のYYYY＝基準日のYYYYの場合（＝今年である）<BR>
     * ・this.税区分 を返す。<BR>
     *  <BR>
     * ○引数.受渡日の日付のYYYY＞基準日のYYYYの場合（＝次年である）<BR>
     * ・this.税区分（次年） を返す。<BR>
     *  <BR>
     * ○引数.受渡日の日付のYYYY＜基準日のYYYYの場合<BR>
     * ・例外をthrowする。<BR>
     *   class：WEB3BusinessLayerException <BR>
     *   tag：  BUSINESS_ERROR_00065 <BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日) <BR>
     *    取引銘柄.getDailyDeliveryDate() <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 4074AAB101DC
     */
    public TaxTypeEnum getDeliveryDateTaxType(Date l_datDeliveryDate)
        throws WEB3BusinessLayerException
    {
        TaxTypeEnum l_taxType;
        
        final String STR_METHOD_NAME = "getDeliveryDateTaxType(Date)";
        log.entering(STR_METHOD_NAME);
        
        //基準日となるシステム日付を取得する
        Date l_datBaseDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        String l_strBaseDate = l_format.format(l_datBaseDate);
        String l_strDeliveryDate = l_format.format(l_datDeliveryDate);

        //引数.受渡日の日付のYYYY＝基準日のYYYYの場合（＝今年である） 
        //this.税区分 を返す。
        if (l_strDeliveryDate.compareTo(l_strBaseDate) == 0)
        {
            l_taxType = accountRow.getTaxType();
        }
        //引数.受渡日の日付のYYYY＞基準日のYYYYの場合（＝次年である） 
        //this.税区分（次年） を返す。
        else if (l_strDeliveryDate.compareTo(l_strBaseDate) > 0)
        {            
            l_taxType = accountRow.getTaxTypeNext();
        }
        //引数.受渡日の日付のYYYY＜基準日のYYYYの場合 
        //例外をthrowする。
        else
        {
            log.info(STR_METHOD_NAME + " ： 受渡日の日付のYYYY＜基準日のYYYYの場合 ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00065,
                WEB3GentradeMainAccount.class.getName()
                    + "." + STR_METHOD_NAME,
                "受渡日 = " + l_datDeliveryDate + " , 基準日 = " + l_datBaseDate);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_taxType;
    }
      
    /**
     * (isリアル顧客)<BR>
     * リアル時価を表示する顧客かどうかの判別をする。<BR>
     * 
     * this.時価取得区分 = ”リアル客”の場合trueを、<BR>
     * this.時価取得区分 = ”ディレイ客”の場合falseを返却する。<BR>
     * 
     * @@return boolean
     */
    public boolean isRealCustomer() 
    {
        if (WEB3QuoteTypeDef.REAL_CUSTOMER.equals(this.accountRow.getQuotoType())) 
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * (is特定口座開設) <BR>
     * 顧客が、引数の受渡日に、特定口座を開設しているかチェックする。<BR> 
     * 特定口座を開設している場合はtrueを、開設していない場合はfalseを、<BR> 
     * それぞれ返却する。<BR> 
     *  <BR>
     * １）　@GtlUtils.getTradingSystem().getSystemTimestamp( )により、<BR> 
     *  システム日付を取得する。<BR> 
     *  <BR>
     * ２）　@引数.受渡日と１）で取得したシステム日付のYYYYの値により、<BR>
     *  以下の判定を行う。 <BR>
     *  <BR>
     * -------------------------------------------------------<BR> 
     * ○引数.受渡日の日付のYYYY＝システム日付のYYYYの場合 <BR>
     *  <BR> 
     *   ・this.税区分＝TaxTypeEnum.SPECIAL（特定） <BR> 
     *     またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、<BR>
     *     trueを返す。<BR> 
     *   ・this.税区分＝TaxTypeEnum.NORMAL（一般）であれば、falseを返す。<BR> 
     *   ・上記以外の場合は、例外をthrowする。<BR> 
     *      クラス名：WEB3BusinessLayerException  <BR>
     *      タグ：BUSINESS_ERROR_00064 <BR>
     *  <BR>
     * ----------------------------------------------------------<BR> 
     * ○引数.受渡日の日付のYYYY＞システム日付のYYYYの場合 <BR> 
     *   ・this.税区分（次年）＝TaxTypeEnum.SPECIAL（特定）<BR> 
     *     またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、<BR>
     *     trueを返す。<BR> 
     *   ・this.税区分（次年）＝TaxTypeEnum.NORMAL（一般）であれば、<BR>
     *     falseを返す。 
     *   ・上記以外の場合は、例外をthrowする。<BR>
     *      クラス名：WEB3BusinessLayerException  <BR>
     *      タグ：BUSINESS_ERROR_00064 <BR> 
     *  <BR>
     * ----------------------------------------------------------<BR> 
     * ○引数.受渡日の日付のYYYY＜システム日付のYYYYの場合 <BR> 
     *   ・例外をthrowする。 
     *      クラス名：WEB3BusinessLayerException  <BR>
     *      タグ：BUSINESS_ERROR_00065 <BR>
     *  <BR>
     * @@param l_deliveryDate - (受渡日) <BR>
     * @@param l_subAccount - (補助口座) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(
        Date l_deliveryDate,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(Date,SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@GtlUtils.getTradingSystem().getSystemTimestamp( )により、
        //システム日付を取得する
        Date l_datSystemDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        //get システム日付のYYYY
        String l_strSystemDate = l_format.format(l_datSystemDate);
        //get 受渡日の日付のYYYY
        String l_strDeliveryDate = l_format.format(l_deliveryDate);
        
        //get税区分
        TaxTypeEnum l_taxType = accountRow.getTaxType();
        //get税区分（次年）
        TaxTypeEnum l_taxTypeNext = accountRow.getTaxTypeNext();
        
        boolean l_isSpecialAccountEstablished = false;
        //○引数.受渡日の日付のYYYY＝システム日付のYYYYの場合
        if (l_strDeliveryDate.compareTo(l_strSystemDate) == 0)
        {
            //this.税区分＝TaxTypeEnum.SPECIAL（特定） 
            //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、trueを返す。
            //this.税区分＝TaxTypeEnum.NORMAL（一般）であれば、falseを返す。
            //上記以外の場合は、例外をthrowする。
            if (TaxTypeEnum.SPECIAL.equals(l_taxType) 
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = true;
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = false;
            }
            else
            {
                log.info(
                    STR_METHOD_NAME + " ： 税区分 = " + l_taxType);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                        + "." + STR_METHOD_NAME,
                    "税区分 = " + l_taxType);
             }
            
        } 
        //○引数.受渡日の日付のYYYY＞システム日付のYYYYの場合
        if (l_strDeliveryDate.compareTo(l_strSystemDate) > 0)
        {
            //this.税区分（次年）＝TaxTypeEnum.SPECIAL（特定)
            //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、trueを返す。
            //this.税区分（次年）＝TaxTypeEnum.NORMAL（一般）であれば、
            //falseを返す。 
            //上記以外の場合は、例外をthrowする。     
            if (TaxTypeEnum.SPECIAL.equals(l_taxTypeNext) 
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = true;
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = false;
            }
            else
            {
                log.info(
                    STR_METHOD_NAME + " ： 税区分（次年） = " + l_taxTypeNext);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                        + "." + STR_METHOD_NAME,
                    "税区分（次年） = " + l_taxTypeNext);
            }  
        }
        //○引数.受渡日の日付のYYYY＜システム日付のYYYYの場合
        if (l_strDeliveryDate.compareTo(l_strSystemDate) < 0)
        {
            //例外をthrowする。
            log.info(STR_METHOD_NAME 
                + " ： 引数.受渡日の日付のYYYY＜システム日付のYYYYの場合 ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00065,
                WEB3GentradeMainAccount.class.getName()
                    + "." + STR_METHOD_NAME,
                "受渡日 = " + l_strDeliveryDate + " , システム日付 = " + l_strSystemDate);
        }
        
        log.exiting(STR_METHOD_NAME);    
        return l_isSpecialAccountEstablished;
        
    }
    
    /**
     * (is特定口座開設) <BR>
     *  <BR>
     * 顧客が、基準日に対応する受渡日に、特定口座を開設しているか<BR>
     * チェックする。（チェックの際は権利落ち銘柄を考慮し、受渡日としてＴ＋３、<BR>
     * Ｔ＋４の両方の日付から特定口座開設有無を判定する。）特定口座を<BR>
     * 開設している場合はtrueを、開設していない場合はfalseを、それぞれ返却する。<BR> 
     *  <BR>
     * １）　@GtlUtils.getTradingSystem().getSystemTimestamp( )により、<BR> 
     *  受付日時を取得する。<BR> 
     *  <BR> 
     *  ※取得した受付日時を「基準日」とする。<BR> 
     *  <BR>
     * ２）　@Ｔ＋３の日付の取得 <BR>
     *  基準日の、３営業日後の日付を取得する。<BR> 
     *  <BR>
     * ３）　@Ｔ＋４の日付の取得 <BR>
     *  基準日の、４営業日後の日付を取得する。<BR> 
     *  <BR>
     * ４）　@２）と３）の日付のYYYYの値により、以下の判定を行う。<BR> 
     *  <BR>
     * ------------------------------------------------------------------<BR> 
     * ○Ｔ＋３の日付のYYYY、Ｔ＋４の日付のYYYY共に基準日のYYYYと等しい場合<BR>
     *    （＝今年である） <BR>
     *   ・this.税区分＝TaxTypeEnum.SPECIAL（特定）<BR> 
     *     またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、trueを返す。<BR> 
     *   ・this.税区分＝TaxTypeEnum.NORMAL（一般）であれば、falseを返す。<BR>  
     *   ・上記以外の場合は、例外をthrowする。<BR>  
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00064  <BR>
     *  <BR> 
     * ------------------------------------------------------------------<BR>
     * ○Ｔ＋３の日付のYYYY、Ｔ＋４の日付のYYYY共に基準日のYYYYより未来の場合<BR>
     *   （＝次年である）<BR> 
     *  ・this.税区分（次年）＝TaxTypeEnum.SPECIAL（特定） <BR> 
     *    またはSPECIAL_WITHHOLD（特定かつ源泉徴収）の場合は、trueを返す。<BR> 
     *  ・this.税区分（次年）＝TaxTypeEnum.NORMAL（一般）の場合は、falseを返す。<BR> 
     *  ・上記以外の場合は、例外をthrowする。<BR> 
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * -------------------------------------------------------------------<BR>
     * ○（Ｔ＋３の日付のYYYY＝基準日のYYYY）、 <BR>
     *     かつ（Ｔ＋４の日付のYYYY）＞基準日のYYYY）の場合 <BR> 
     *    （＝Ｔ＋３は今年、Ｔ＋４は次年である）<BR> 
     *  ・this.税区分、this.税区分（次年）共にTaxTypeEnum.NORMAL（一般）で <BR>
     *    ある場合は、falseを返す。<BR> 
     *  ・this.税区分またはthis.税区分（次年）が、TaxTypeEnum.SPECIAL（特定）<BR> 
     *    またはSPECIAL_WITHHOLD（特定かつ源泉徴収）の場合は、trueを返す。<BR> 
     *  ・上記以外の場合は、例外をthrowする。<BR> 
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * --------------------------------------------------------------------<BR> 
     * ○上記以外の場合 <BR>
     *  ・いずれにも当てはまらない場合は、例外をthrowする。<BR> 
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00136  <BR>
     *  <BR>
     * @@param l_subAccount - (補助口座) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //１）　@GtlUtils.getTradingSystem().getSystemTimestamp( )により、
        //受付日時を取得する。
        //※取得した受付日時を「基準日」とする
        Date l_datStandardDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        // get 基準日のYYYY
        String l_strStandardYear = l_format.format(l_datStandardDate);
        
        // get 営業日計算
        WEB3GentradeBizDate l_dateCalc =
            new WEB3GentradeBizDate(new Timestamp(l_datStandardDate.getTime()));
        
        //２）Ｔ＋３の日付の取得 
        //    基準日の、３営業日後の日付を取得する。
        Date l_datThreeDaysAfter = l_dateCalc.roll(3);
        String l_strT3Year = l_format.format(l_datThreeDaysAfter);
        //３)Ｔ＋４の日付の取得 
        //基準日の、４営業日後の日付を取得する。
        Date l_datFourDaysAfter = l_dateCalc.roll(4);
        String l_strT4Year = l_format.format(l_datFourDaysAfter);
        
        //get税区分
        TaxTypeEnum l_taxType = accountRow.getTaxType();
        //get税区分（次年）
        TaxTypeEnum l_taxTypeNext = accountRow.getTaxTypeNext();
        
        boolean l_isSpecialAccountEstablished = false;
        //○Ｔ＋３の日付のYYYY、Ｔ＋４の日付のYYYY共に基準日のYYYYと等しい場合
        //（＝今年である）
        if ((l_strT3Year.compareTo(l_strT4Year) == 0)
            && (l_strT3Year.compareTo(l_strStandardYear) == 0))
        {
            //this.税区分＝TaxTypeEnum.SPECIAL（特定）
            //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、trueを返す。
            //this.税区分＝TaxTypeEnum.NORMAL（一般）であれば、falseを返す。
            //上記以外の場合は、例外をthrowする
            if (TaxTypeEnum.SPECIAL.equals(l_taxType)
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = true;
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = false;
            }
            else
            {
                log.error(STR_METHOD_NAME + "：税区分 = " + l_taxType);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                      + "." + STR_METHOD_NAME,
                    "税区分 = " + l_taxType);
            }
            return l_isSpecialAccountEstablished;
        }
        //○Ｔ＋３の日付のYYYY、Ｔ＋４の日付のYYYY共に基準日のYYYYより未来の場合
        //（＝次年である）
        if ((l_strT3Year.compareTo(l_strT4Year) == 0)
            && (l_strT3Year.compareTo(l_strStandardYear) > 0))
        {
            //this.税区分（次年）＝TaxTypeEnum.SPECIAL（特定） 
            //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）の場合は、trueを返す。
            //this.税区分（次年）＝TaxTypeEnum.NORMAL（一般）の場合は、falseを返す。
            //上記以外の場合は、例外をthrowする
            if (TaxTypeEnum.SPECIAL.equals(l_taxTypeNext) 
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = true;
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = false;
            }
            else
            {
                log.error(
                    STR_METHOD_NAME + "：税区分（次年） = " + l_taxTypeNext);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                        + "." + STR_METHOD_NAME,
                    "税区分（次年） = " + l_taxTypeNext);
            }
            return l_isSpecialAccountEstablished;
        }
        //○（Ｔ＋３の日付のYYYY＝基準日のYYYY）、 
        // かつ（Ｔ＋４の日付のYYYY）＞基準日のYYYY）の場合 
        //（＝Ｔ＋３は今年、Ｔ＋４は次年である）
        if ((l_strT3Year.compareTo(l_strStandardYear) == 0)
            && (l_strT4Year.compareTo(l_strStandardYear) > 0))
        {
            //this.税区分、this.税区分（次年）共にTaxTypeEnum.NORMAL（一般）で 
            //ある場合は、falseを返す。
            //this.税区分またはthis.税区分（次年）が、TaxTypeEnum.SPECIAL（特定）
            //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）の場合は、trueを返す。
            //上記以外の場合は、例外をthrowする。
            if (l_taxType.equals(l_taxTypeNext) && TaxTypeEnum.NORMAL.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = false;
            }
            else if(TaxTypeEnum.SPECIAL.equals(l_taxType)
                     ||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType)
                     ||TaxTypeEnum.SPECIAL.equals(l_taxTypeNext)
                     ||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = true;
            }
            else
            {
                log.error(STR_METHOD_NAME + "：税区分 = " + l_taxType + " 、税区分（次年） = " + l_taxTypeNext);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                        + "." + STR_METHOD_NAME,
                   "税区分 = " + l_taxType + " 、税区分（次年） = " + l_taxTypeNext);
            }
            return l_isSpecialAccountEstablished;    
        }
        //○上記以外の場合 
        //いずれにも当てはまらない場合は、例外をthrowする
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00136,
            WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
            "基準日 = " + l_strStandardYear + " 、T+3 = " + l_strT3Year + " 、T+4 = " + l_strT4Year);
  
    }
    
    /**
     * (is特定口座開設) <BR>
     *  <BR>
     * 顧客が、引数の受渡日に、引数指定の補助口座、<BR>
     * 税区分タイプに対し特定口座を開設しているかチェックする。<BR>
     * 特定口座を開設している場合はtrueを、開設していない<BR>
     * 場合はfalseを、それぞれ返却する。<BR>
     *  <BR>
     * １）　@引数.税区分タイプ＝"現物株式"の場合は、<BR> 
     *  this.is特定口座開設(受渡日, 補助口座)にdelegateし、<BR>
     *  処理を終了する。<BR> 
     *  以外、以下の処理を行う。<BR> 
     *  <BR>
     * ２）　@GtlUtils.getTradingSystem().getSystemTimestamp( )により、<BR>
     *  システム日付を取得する。<BR>
     *  <BR> 
     * ３）　@引数.受渡日と２）で取得したシステム日付のYYYYの値により、<BR>
     *  以下の判定を行う。<BR>
     *  <BR>
     * --------------------------------------------------------<BR> 
     * ○引数.受渡日の日付のYYYY＝システム日付のYYYYの場合 <BR> 
     *  <BR>
     *  引数.税区分タイプ＝"信用取引"の場合は、<BR> 
     *  ・this.信用取引税区分＝TaxTypeEnum.SPECIAL（特定）<BR> 
     *   またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、<BR>
     *   trueを返す。<BR> 
     *  ・this.信用取引税区分＝TaxTypeEnum.NORMAL（一般）であれば、<BR>
     *    falseを返す。 <BR>
     *  ・上記以外の場合は、例外をthrowする。<BR> 
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * --------------------------------------------------------<BR>
     * ○引数.受渡日の日付のYYYY＞システム日付のYYYYの場合 <BR>
     *  <BR>
     *  引数.税区分タイプ＝"信用取引"の場合は、<BR> 
     *  ・this.信用取引税区分（次年）＝TaxTypeEnum.SPECIAL（特定） <BR>
     *    またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、 <BR>
     *    trueを返す。 <BR>
     *  ・this.信用取引税区分（次年）＝TaxTypeEnum.NORMAL（一般）で <BR>
     *    あれば、falseを返す。 
     *  ・上記以外の場合は、例外をthrowする。<BR>
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00064  <BR> 
     *  <BR>
     * --------------------------------------------------------<BR> 
     * ○引数.受渡日の日付のYYYY＜システム日付のYYYYの場合 <BR>
     *  ・例外をthrowする。<BR> 
     *      クラス名：WEB3BusinessLayerException  <BR>
     *      タグ：BUSINESS_ERROR_00065 <BR>
     *  <BR>
     * @@param l_deliveryDate - 受渡日 <BR>
     * @@param l_subAccount - 補助口座 <BR>
     * @@param l_strTaxTypeDivDef - 税区分タイプ <BR>
     *     (WEB3GentradeEquityMarginDivDefにて定義) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(
        Date l_deliveryDate,
        SubAccount l_subAccount,
        String l_strTaxTypeDivDef)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(Date, SubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数.税区分タイプ＝"現物株式"の場合は、
        //this.is特定口座開設(受渡日, 補助口座)にdelegateし、
        //処理を終了する。
        if(WEB3GentradeEquityMarginDivDef.EQUITY.equals(l_strTaxTypeDivDef))
        {
            return this.isSpecialAccountEstablished(l_deliveryDate,l_subAccount);
        }
        
        //２）　@システム日付を取得する。
        Date l_datSystemDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        //get システム日付のYYYY
        String l_strSystemDate = l_format.format(l_datSystemDate);
        //get 受渡日の日付のYYYY
        String l_strDeliveryDate = l_format.format(l_deliveryDate);
        
        //get信用取引税区分
        TaxTypeEnum l_marginTaxType = accountRow.getMarginTaxType();
        //get信用取引税区分（次年）
        TaxTypeEnum l_marginTaxTypeNext = accountRow.getMarginTaxTypeNext();
             
        boolean l_isSpecialAccountEstablished = false;
        // ○引数.受渡日の日付のYYYY＝システム日付のYYYYの場合
        if (l_strDeliveryDate.compareTo(l_strSystemDate) == 0)
        {
            // 引数.税区分タイプ＝"信用取引"の場合
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.信用取引税区分＝TaxTypeEnum.SPECIAL（特定）
                //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、
                //trueを返す。
                //this.信用取引税区分＝TaxTypeEnum.NORMAL（一般）であれば、
                //falseを返す。 
                //上記以外の場合は、例外をthrowする。
                if (TaxTypeEnum.SPECIAL.equals(l_marginTaxType) 
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else if (TaxTypeEnum.NORMAL.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else
                {
                    log.info(
                        STR_METHOD_NAME + " ： 信用取引税区分 = " + l_marginTaxType);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "信用取引税区分 = " + l_marginTaxType);
                 }
            }
        }
        //○引数.受渡日の日付のYYYY＞システム日付のYYYYの場合
        if (l_strDeliveryDate.compareTo(l_strSystemDate) > 0)
        {
            // 引数.税区分タイプ＝"信用取引"の場合
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.信用取引税区分（次年）＝TaxTypeEnum.SPECIAL（特定） 
                //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、 
                //trueを返す。 
                //this.信用取引税区分（次年）＝TaxTypeEnum.NORMAL（一般）で 
                //あれば、falseを返す。 
                //上記以外の場合は、例外をthrowする
                if (TaxTypeEnum.SPECIAL.equals(l_marginTaxTypeNext) 
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else if (TaxTypeEnum.NORMAL.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else
                {
                    log.info(
                        STR_METHOD_NAME + "： 信用取引税区分（次年） = " + l_marginTaxTypeNext);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "信用取引税区分（次年） = " + l_marginTaxTypeNext);
                }
                
            }
        }
        //○引数.受渡日の日付のYYYY＜システム日付のYYYYの場合 
        if (l_strDeliveryDate.compareTo(l_strSystemDate) < 0)
        {
            //例外をthrowする。
            log.info(STR_METHOD_NAME + " ： 受渡日の日付＜システム日付の場合 ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00065,
                WEB3GentradeMainAccount.class.getName()
                    + "." + STR_METHOD_NAME,
                "受渡日 = " + l_strDeliveryDate + " , システム日付 = " + l_strSystemDate);
        }
        
        log.exiting(STR_METHOD_NAME);    
        return l_isSpecialAccountEstablished;
    }
    
    /**
     * (is特定口座開設) <BR>
     *  <BR>
     *顧客が、基準日に対応する受渡日に、引数指定の補助口座、<BR>
     *税区分タイプに対し特定口座を開設しているかチェックする。<BR>
     *（チェックの際は権利落ち銘柄を考慮し、受渡日としてＴ＋３、<BR>
     *Ｔ＋４の両方の日付から特定口座開設有無を判定する。）<BR>
     *特定口座を開設している場合はtrueを、開設していない場合はfalseを、<BR>
     *それぞれ返却する。 <BR>
     *  <BR>
     * １）　@引数.税区分タイプ＝"現物株式"の場合は、<BR> 
     *  this.is特定口座開設(補助口座)にdelegateし、処理を終了する。<BR> 
     *  以外、以下の処理を行う。<BR> 
     *  <BR>
     * ２）　@GtlUtils.getTradingSystem().getSystemTimestamp( )により、<BR> 
     *  受付日時を取得する。<BR> 
     *  <BR> 
     *  ※取得した受付日時を「基準日」とする。<BR> 
     *  <BR> 
     * ３）　@Ｔ＋３の日付の取得 <BR> 
     *  基準日の、３営業日後の日付を取得する。<BR>  
     *  <BR> 
     * ４）　@Ｔ＋４の日付の取得 <BR> 
     *  基準日の、４営業日後の日付を取得する。<BR>  
     *  <BR> 
     * ５）　@３）と４）の日付のYYYYの値により、以下の判定を行う。<BR>  
     *  <BR>
     * --------------------------------------------------------------<BR>
     * ○Ｔ＋３の日付のYYYY、Ｔ＋４の日付のYYYY共に基準日のYYYYと等しい場合<BR>
     *  （＝今年である）<BR> 
     *  引数.税区分タイプ＝"信用取引"の場合は、<BR> 
     *  ・this.信用取引税区分＝TaxTypeEnum.SPECIAL（特定）<BR> 
     *    またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、trueを返す。<BR> 
     *  ・this.信用取引税区分＝TaxTypeEnum.NORMAL（一般）であれば、<BR>
     *    falseを返す。<BR> 
     *  ・上記以外の場合は、例外をthrowする。<BR> 
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * -------------------------------------------------------------<BR> 
     * ○Ｔ＋３の日付のYYYY、Ｔ＋４の日付のYYYY共に基準日のYYYYより未来の場合<BR>
     *  （＝次年である） <BR>
     *  引数.税区分タイプ＝"信用取引"の場合は、<BR> 
     *  ・this.信用取引税区分（次年）＝TaxTypeEnum.SPECIAL（特定） <BR> 
     *    またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、trueを返す。<BR> 
     *  ・this.信用取引税区分（次年）＝TaxTypeEnum.NORMAL（一般）であれば、<BR>
     *    falseを返す。 
     *  ・上記以外の場合は、例外をthrowする。<BR> 
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * --------------------------------------------------------------<BR> 
     * ○（Ｔ＋３の日付のYYYY＝基準日のYYYY）、 <BR>
     *  かつ（Ｔ＋４の日付のYYYY）＞基準日のYYYY）の場合 <BR> 
     *  （＝Ｔ＋３は今年、Ｔ＋４は次年である） <BR>
     *  引数.税区分タイプ＝"信用取引"の場合は、<BR> 
     *  ・this.信用取引税区分、this.信用取引税区分（次年）共に <BR>
     *    TaxTypeEnum.NORMAL（一般）である場合は、falseを返す。 <BR>
     *  ・this.信用取引税区分またはthis.信用取引税区分（次年）が、<BR>
     *    TaxTypeEnum.SPECIAL（特定）またはSPECIAL_WITHHOLD<BR>
     *    （特定かつ源泉徴収）の場合は、trueを返す。<BR> 
     *  ・上記以外の場合は、例外をthrowする。<BR> 
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * ---------------------------------------------------------------<BR> 
     * ○上記以外の場合 <BR>
     *  ・いずれにも当てはまらない場合は、例外をthrowする。<BR> 
     *         class：WEB3BusinessLayerException  <BR>
     *         tag：  BUSINESS_ERROR_00136  <BR>
     *  <BR> 
     * @@param l_subAccount - (補助口座) <BR>
     * @@param l_strTaxTypeDivDef - (税区分タイプ) <BR>
     *     (WEB3GentradeEquityMarginDivDefにて定義) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(SubAccount l_subAccount,String l_strTaxTypeDivDef) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(SubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数.税区分タイプ＝"現物株式"の場合は、
        // this.is特定口座開設(補助口座)にdelegateし、処理を終了する。
        if(WEB3GentradeEquityMarginDivDef.EQUITY.equals(l_strTaxTypeDivDef))
        {
            return this.isSpecialAccountEstablished(l_subAccount);
        }
        
        //２）　@GtlUtils.getTradingSystem().getSystemTimestamp( )により、
        //受付日時を取得する。
        //※取得した受付日時を「基準日」とする。
        Date l_datStandardDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        // get 基準日のYYYY
        String l_strStandardYear = l_format.format(l_datStandardDate);
        
        // get 営業日計算
        WEB3GentradeBizDate l_dateCalc =
            new WEB3GentradeBizDate(new Timestamp(l_datStandardDate.getTime()));
        
        //３）Ｔ＋３の日付の取得 
        //    基準日の、３営業日後の日付を取得する。
        Date l_datThreeDaysAfter = l_dateCalc.roll(3);
        String l_strT3Year = l_format.format(l_datThreeDaysAfter);
        //４)Ｔ＋４の日付の取得 
        //基準日の、４営業日後の日付を取得する。
        Date l_datFourDaysAfter = l_dateCalc.roll(4);
        String l_strT4Year = l_format.format(l_datFourDaysAfter);
        
        //get信用取引税区分
        TaxTypeEnum l_marginTaxType = accountRow.getMarginTaxType();
        //get信用取引税区分（次年）
        TaxTypeEnum l_marginTaxTypeNext = accountRow.getMarginTaxTypeNext();
        
        boolean l_isSpecialAccountEstablished = false;
        //○Ｔ＋３の日付のYYYY、Ｔ＋４の日付のYYYY共に基準日のYYYYと等しい場合
        // （＝今年である）
        if ((l_strT3Year.compareTo(l_strT4Year) == 0)
            && (l_strT3Year.compareTo(l_strStandardYear) == 0))
        {
            //引数.税区分タイプ＝"信用取引"の場合
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.信用取引税区分＝TaxTypeEnum.SPECIAL（特定）
                //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、trueを返す。
                //this.信用取引税区分＝TaxTypeEnum.NORMAL（一般）であれば、
                //falseを返す。
                //上記以外の場合は、例外をthrowする
                if (TaxTypeEnum.SPECIAL.equals(l_marginTaxType) 
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else if (TaxTypeEnum.NORMAL.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else
                {
                    log.info(
                        STR_METHOD_NAME + " ： 信用取引税区分 = " + l_marginTaxType);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "信用取引税区分 = " + l_marginTaxType);
                 }
            }
            return l_isSpecialAccountEstablished;
        }
        //○Ｔ＋３の日付のYYYY、Ｔ＋４の日付のYYYY共に基準日のYYYYより未来の場合
        //（＝次年である）
        if ((l_strT3Year.compareTo(l_strT4Year) == 0)
            && (l_strT3Year.compareTo(l_strStandardYear) > 0))
        {
            //引数.税区分タイプ＝"信用取引"の場合
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.信用取引税区分（次年）＝TaxTypeEnum.SPECIAL（特定） 
                //またはSPECIAL_WITHHOLD（特定かつ源泉徴収）であれば、trueを返す。
                //this.信用取引税区分（次年）＝TaxTypeEnum.NORMAL（一般）であれば、
                //falseを返す。 
                //上記以外の場合は、例外をthrowする。
                if (TaxTypeEnum.SPECIAL.equals(l_marginTaxTypeNext) 
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else if (TaxTypeEnum.NORMAL.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else
                {
                    log.debug(
                        STR_METHOD_NAME + "： 信用取引税区分（次年） = " + l_marginTaxTypeNext);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "信用取引税区分（次年） = " + l_marginTaxTypeNext);
                }
            }
            return l_isSpecialAccountEstablished;
        }
        //○（Ｔ＋３の日付のYYYY＝基準日のYYYY）、
        // かつ（Ｔ＋４の日付のYYYY）＞基準日のYYYY）の場合（＝Ｔ＋３は今年、Ｔ＋４は次年である）
        if ((l_strT3Year.compareTo(l_strStandardYear) == 0)
            && (l_strT4Year.compareTo(l_strStandardYear) > 0))
        {
            //引数.税区分タイプ＝"信用取引"の場合
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.信用取引税区分、this.信用取引税区分（次年）共に
                //TaxTypeEnum.NORMAL（一般）である場合は、falseを返す。
                //this.信用取引税区分またはthis.信用取引税区分（次年）が、
                //TaxTypeEnum.SPECIAL（特定）またはSPECIAL_WITHHOLD 
                //（特定かつ源泉徴収）の場合は、trueを返す。
                //上記以外の場合は、例外をthrowする
                if (l_marginTaxType.equals(l_marginTaxTypeNext) && TaxTypeEnum.NORMAL.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else if(TaxTypeEnum.SPECIAL.equals(l_marginTaxType)
                         ||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxType)
                         ||TaxTypeEnum.SPECIAL.equals(l_marginTaxTypeNext)
                         ||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else
                {
                    log.info(STR_METHOD_NAME 
                        + "： 信用取引税区分 = " 
                        + l_marginTaxType 
                        + " 、信用取引税区分（次年） = " 
                        + l_marginTaxTypeNext);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "信用取引税区分 = " + l_marginTaxType + " 、信用取引税区分（次年） = " + l_marginTaxTypeNext);
                }
            }
            return l_isSpecialAccountEstablished;                
        }
        
        //○上記以外の場合 
        //いずれにも当てはまらない場合は、例外をthrowする。
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00136,
            WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
            "基準日 = " + l_strStandardYear + " , T+3 = " + l_strT3Year + " , T+4 = " + l_strT4Year);
    }
    
    /**
     * (is信用口座開設) <BR>
     *  <BR>
     * 当該顧客が、指定の信用取引口座を開設しているかどうかを判定する。<BR>
     *  <BR>
     * ○引数の弁済区分＝”指定なし”の場合 <BR>
     *    －this.制度信用取引口座開設区分＝”口座開設”、<BR>
     *       または this.一般信用取引口座開設区分＝”口座開設” <BR>
     *       の場合のみ、trueを返す。 <BR>
     *    －以外、falseを返す。 <BR>
     *  <BR>
     * ○引数の弁済区分＝”制度信用”の場合 <BR>
     *    －this.制度信用取引口座開設区分＝”口座開設” <BR>
     *       の場合はtrueを返す。 <BR>
     *    －以外、falseを返す。<BR>
     *  <BR>
     * ○引数の弁済区分＝”一般信用”の場合 <BR>
     *    －this.一般信用取引口座開設区分＝”口座開設” <BR>
     *       の場合はtrueを返す。<BR>
     *    －以外、falseを返す。 <BR> 
     *  <BR>
     * ○引数の弁済区分が上記以外の場合 <BR>
     *    －例外をthrowする。 <BR>
     *        class    : WEB3BaseRuntimeException<BR>
     *        tag      : SYSTEM_ERROR_80017<BR>
     *  <BR>
     * @@param l_strRepaymentType - (弁済区分) <BR>
     *   (WEB3GentradeRepaymentDivDefにて定義) <BR>
     */
    public boolean isMarginAccountEstablished(String l_strRepaymentType)
    {
        final String STR_METHOD_NAME = "isMarginAccountEstablished(String)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_isMarginAccountEstablished = false;
        //○引数の弁済区分＝”指定なし”の場合
        if(WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
        {
            //this.制度信用取引口座開設区分＝”口座開設”、
            //または this.一般信用取引口座開設区分＝”口座開設” 
            //の場合のみ、trueを返す
            if(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(this.accountRow.getMarginGenAccOpenDiv())
             || WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(this.accountRow.getMarginSysAccOpenDiv()))
            {
                l_isMarginAccountEstablished = true;
            }
            else
            {
                l_isMarginAccountEstablished = false;
            }
        }
        //○引数の弁済区分＝”制度信用”の場合 
        else if(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
        {
            //this.制度信用取引口座開設区分＝”口座開設” の場合はtrueを返す。 
            if(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(this.accountRow.getMarginSysAccOpenDiv()))
            {
                l_isMarginAccountEstablished = true;
            }
            else
            {
                l_isMarginAccountEstablished = false;
            }
        }
        //○引数の弁済区分＝”一般信用”の場合 
        else if(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
        {
            //this.一般信用取引口座開設区分＝”口座開設”の場合はtrueを返す
            if(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(this.accountRow.getMarginGenAccOpenDiv()))
            {
                l_isMarginAccountEstablished = true;
            }
            else
            {
                l_isMarginAccountEstablished = false;
            }
            
        }
        //○引数の弁済区分が上記以外の場合
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数の弁済区分 = " + l_strRepaymentType);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isMarginAccountEstablished;
    }
    
    /**
     * (is預り証券評価制) <BR>
     *  <BR>
     * 顧客が預り証券評価制顧客かどうかを判別する。 <BR>
     * [戻り値] true：　@実施 false：　@未実施 <BR>
     *  <BR>
     * １）DB検索 <BR>
     * 以下の条件で余力条件テーブルを検索し <BR> 
     * 預り証券評価制区分を取得する。 <BR> 
     *  <BR>
     * [条件] <BR> 
     * 口座ID = this.getAccountId() <BR>
     * <BR> 
     * ２）証券会社オブジェクトを取得する。<BR> 
     * 証券会社 = this.getInstitution() <BR> 
     *  <BR>
     * ３）預り証券評価制かどうか判別する。<BR>
     *  <BR> 
     * [証券会社.預り証券評価制 = ”未実施”の場合] <BR>
     * falseを返却する。 <BR>
     *  <BR>
     * [証券会社.預り証券評価制 = ”実施”　@かつ <BR>
     * 預り証券評価制区分 = ”未実施”の場合] <BR>
     * falseを返却する。<BR> 
     *  <BR>
     * [証券会社.預り証券評価制 = ”実施”　@かつ <BR> 
     * 預り証券評価制区分 = ”実施”の場合] <BR>
     * trueを返却する。 <BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     */
    public boolean isAssetEvaluation() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "isAssetEvaluation()";
            
        //１）DB検索 
        //以下の条件で余力条件テーブルを検索し 
        //預り証券評価制区分を取得する。
        TradingpowerCalcConditionRow l_tradingpowerCalcConditionRow = null;
        try
        {
            l_tradingpowerCalcConditionRow = 
                TradingpowerCalcConditionDao.findRowByAccountId(this.getAccountId());
        }
        catch(DataException de)
        {
            WEB3SystemLayerException l_wse = 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de
                );
            log.error(STR_METHOD_NAME,l_wse);
            throw l_wse;
        }
        if(l_tradingpowerCalcConditionRow == null)
        {
            WEB3SystemLayerException l_wse = 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "余力条件テーブルを検索する(口座ID = " + this.getAccountId() + ")"
                );
            log.debug(STR_METHOD_NAME,l_wse);
            throw l_wse;
        }
        
        //２）証券会社オブジェクトを取得する
        Institution l_institution = this.getInstitution();
        InstitutionRow l_institutionRow  = (InstitutionRow)l_institution.getDataSourceObject();
        
        //３）預り証券評価制かどうか判別する。
        boolean l_isAssetEvaluation = false;
        //証券会社.預り証券評価制 = ”実施”
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()))
        {
            //預り証券評価制区分 = ”実施”
            if(WEB3EnforcementDef.ENFORCEMENT.equals(l_tradingpowerCalcConditionRow.getAssetEvaluationDiv()))
            {
                l_isAssetEvaluation = true;
            }
        }
        
        return l_isAssetEvaluation;                    

    }
    
    /**
     * (get振込先金融機@関) <BR>
     *  <BR>
     * 顧客に関連する振込先金融機@関オブジェクトを取得する。<BR>
     *  <BR>
     * １）以下の項目を取得する。<BR>
     *  <BR>
     * 証券会社コード： this.getInstitution().getInstitutionCode()の戻り値 <BR>
     * 部店コード： this.getBranch().getBranchCode()の戻り値<BR>
     * 顧客コード： this.顧客コード<BR>
     * <BR>
     * ２）１）で取得した項目を引数として、振込先金融機@関オブジェクトを<BR>
     * 取得し返却する。<BR>
     *   指定区分： 'A'<BR>
     *  <BR>
     * @@return WEB3GentradeTransferedFinInstitution - 振込先金融機@関<BR>
     * @@throws WEB3BaseException <BR>
     */
    public WEB3GentradeTransferedFinInstitution getTransferedFinInstitution()
        throws WEB3BaseException
    {
        return new WEB3GentradeTransferedFinInstitution(
            this.getInstitution().getInstitutionCode(),
            this.getBranch().getBranchCode(),
            this.accountCode,
            "A");
    }
    
    /**
     * (get振込先登録区分) <BR>
     *  <BR>
     * this.振込先（銀行口座）登録区分を返却する。<BR>
     *  <BR>
     * @@return String - 振込先登録区分<BR>
     */
    public String getTransferedRegistDiv()
    {
        return this.accountRow.getBankAccountRegi();
    }
    
    /**
     * (get表示顧客コード) <BR>
     *  <BR>
     * 顧客コード（表示用）を取得する。 <BR>
     *  <BR>
     * this.顧客マスタParams.顧客コードの左6byteを返却する。 <BR>
     *  <BR>
     * @@return String - 表示用顧客コード<BR>
     */
    public String getDisplayAccountCode()
    {
        String l_strAccountCode = this.accountRow.getAccountCode();
        return l_strAccountCode.substring(0,6);
    }
    
    /**
     * (get顧客表示名) <BR>
     *  <BR>
     * 顧客名を取得する。 <BR>
     *  <BR>
     * 「姓 + ”　@” + 名」のフォーマットで返却する。 <BR>
     *  <BR>
     * １）　@顧客名取得 <BR> 
     * getNameDetails()にて、PersonNameDetailsオブジェクトを取得する。<BR> 
     *  <BR>
     * ２）　@顧客名を表示用に編集する。<BR> 
     * PersonNameDetailsオブジェクトより、<BR> 
     * 姓（getFamilyName()）、名（getGivenName()）を取得し、 <BR>
     * 表示用に編集（姓と名の間に全角Spaceを挿入）して返却する。 <BR>
     *  <BR>
     *     PersonNameDetail.getFamilyName() + ”　@”  <BR>
     * + PersonNameDetail.getGivenName()<BR> 
     *  <BR>
     * ※PersonNameDetail.getGivenName()の戻り値がnullの場合は、<BR>
     * PersonNameDetail.getFamilyName()の戻り値を返却する。<BR>
     *  <BR>
     * @@return String - 表示用顧客名<BR>
     */
    public String getDisplayAccountName()
    {
        //１）　@顧客名取得
        PersonNameDetails l_personNameDetails = this.getNameDetails();
        
        //２）　@顧客名を表示用に編集する
        String l_strName; 
        if (l_personNameDetails.getGivenName() != null)
        {
            l_strName =
                l_personNameDetails.getFamilyName()
                    + "　@"
                    + l_personNameDetails.getGivenName();
        }
        else
        {
            l_strName = l_personNameDetails.getFamilyName();
        }
        return l_strName;
    }
    

    /**
     * (getログインＩＤ)<BR>
     *  <BR>
     * 顧客に該当するログインＩＤを取得する。<BR>
     *  <BR>
     * ログインテーブルを検索し、該当するログイン行を取得する。<BR> 
     *  <BR>
     * [検索条件]<BR>
     * ログイン.口座ＩＤ = this.getAccountId()<BR>
     * 取得したログイン.ログインＩＤを返却する。<BR>
     * 該当行が存在しない場合、または、複数件取得できた場合は例外をスローする。<BR>
     * <BR>
     * @@return long - ログインＩＤ<BR>
     */
    public long getLoginId()
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getLoginId()";
        log.entering(STR_METHOD_NAME);
        
        long l_lngLoginId = 0;
        long l_lngAccountId = this.getAccountId();
        
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            List l_lisRecords = null;

            //[検索条件]
            //ログイン.口座ＩＤ = this.getAccountId()
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    LoginRow.TYPE,
                    "account_id = ? ",
                    new Object[] { "" + l_lngAccountId });

            //該当行が存在しない場合、または、複数件取得できた場合は例外をスローする。
            if(l_lisRecords.size() == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if(l_lisRecords.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            LoginRow l_loginRow = (LoginRow)l_lisRecords.get(0);
            
            l_lngLoginId = l_loginRow.getLoginId();
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngLoginId;
    }   
    
    /**
     * (get受渡日信用取引税区分)<BR>
     *  <BR>
     * 顧客の、引数の受渡日における信用取引税区分（TaxTypeEnum）を返却する。<BR>
     *  <BR>
     * 基準日となるシステム日付を取得する。<BR>
     * （GtlUtils.getTradingSystem().getSystemTimestamp( )）<BR>
     *  <BR>
     * 引数.受渡日と取得した基準日のYYYYの値により、以下の判定を行う。<BR>
     *  <BR>
     * ○引数.受渡日の日付のYYYY＝基準日のYYYYの場合（＝今年である）<BR>
     * ・this.信用取引税区分 を返す。<BR>
     *  <BR>
     * ○引数.受渡日の日付のYYYY＞基準日のYYYYの場合（＝次年である）<BR>
     * ・this.信用取引税区分（次年） を返す。<BR>
     *  <BR>
     * ○引数.受渡日の日付のYYYY＜基準日のYYYYの場合<BR>
     * ・例外をthrowする。<BR>
     *  class：WEB3BusinessLayerException <BR>
     *  tag：  BUSINESS_ERROR_00065 <BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     *     取引銘柄.getDailyDeliveryDate()<BR>
     * @@return TaxTypeEnum <BR>
     * @@throws webbroker3.common.WEB3BusinessLayerException<BR>
     */
    public TaxTypeEnum getDeliveryDateMarginTaxType(Date l_datDeliveryDate)
        throws WEB3BusinessLayerException
    {
        TaxTypeEnum l_marginTaxType;
        
        final String STR_METHOD_NAME = "getDeliveryDateMarginTaxType(Date)";
        log.entering(STR_METHOD_NAME);
        
        //基準日となるシステム日付を取得する
        Date l_datBaseDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        
        //引数.基準日のYYYYの値
        String l_strBaseYear = l_format.format(l_datBaseDate);
        //引数.受渡日のYYYYの値
        String l_strDeliveryYear = l_format.format(l_datDeliveryDate);
        
        if (l_strDeliveryYear.compareTo(l_strBaseYear) == 0)
        {
            // ○引数.受渡日の日付のYYYY＝基準日のYYYYの場合（＝今年である）
            // ・this.信用取引税区分 を返す。
            l_marginTaxType = accountRow.getMarginTaxType();
        }
        else if (l_strDeliveryYear.compareTo(l_strBaseYear) > 0)
        {
            // ○引数.受渡日の日付のYYYY＞基準日のYYYYの場合（＝次年である）
            // ・this.信用取引税区分（次年） を返す。
            l_marginTaxType = accountRow.getMarginTaxTypeNext();
        }
        else
        {
            // ○引数.受渡日の日付のYYYY＜基準日のYYYYの場合
            // ・例外をthrowする。
            log.info(STR_METHOD_NAME + " ： 受渡日の日付のYYYY＜基準日のYYYYの場合 ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00065,
                WEB3GentradeMainAccount.class.getName()
                    + "." + STR_METHOD_NAME,
                "受渡日 = " + l_datDeliveryDate + " , 基準日 = " + l_datBaseDate);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_marginTaxType;
    }
  
    /**
     * (is先物OP口座開設)<BR>
     *  <BR>
     * 顧客が、指定された先物／オプション区分に <BR>
     * 該当する口座を開設しているか判別する。<BR>
     * 開設している場合はtrue、以外falseを返却する。<BR>
     *  <BR>
     * １）口座開設区分チェック <BR>
     *  <BR>
     * [パラメータ.先物／オプション区分==”オプション”の場合] <BR>
     * 以下の項目の何れかが”OP口座開設”または”先物OP口座開設” <BR>
     * になっていれば、trueを返却する。 <BR>
     * すべての項目が”DEFAULT（口座なし）”または”先物口座開設”で <BR>
     * あればfalseを返却する。 <BR>
     *  <BR>
     *   this.先物OP口座開設区分（東証） <BR>
     *   this.先物OP口座開設区分（大証） <BR> 
     *   this.先物OP口座開設区分（名証） <BR> 
     *  <BR>
     * [パラメータ.先物／オプション区分==”先物”の場合] <BR>
     * 以下の項目の何れかが”先物口座開設”または”先物OP口座開設” <BR>
     * になっていれば、trueを返却する。<BR>
     * すべての項目が”DEFAULT（口座なし）”または”OP口座開設”で <BR>
     * あればfalseを返却する。 <BR>
     *  <BR>
     *   this.先物OP口座開設区分（東証） <BR>
     *   this.先物OP口座開設区分（大証） <BR>
     *   this.先物OP口座開設区分（名証） <BR>
     *  <BR>
     * @@param l_strFuturesOptionDiv - (先物／オプション区分)
     *    1：　@先物  2：　@オプション
     * @@return boolean 
     */
    public boolean isIfoAccountOpen(String l_strFuturesOptionDiv)
    {
        final String STR_METHOD_NAME = "isIfoAccountOpen(String)";
        log.entering(STR_METHOD_NAME);
        
        if ((!WEB3FuturesOptionDivDef.FUTURES.equals(l_strFuturesOptionDiv))
            && (!WEB3FuturesOptionDivDef.OPTION.equals(l_strFuturesOptionDiv)))
        {
            log.error( "先物／オプション区分 = " + l_strFuturesOptionDiv);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "先物／オプション区分 = " + l_strFuturesOptionDiv);
        }
        
        //get 先物OP口座開設区分（東証）
        String l_strIfoAccOpenDivTokyo = this.accountRow.getIfoAccOpenDivTokyo();
        //get 先物OP口座開設区分（大証）
        String l_strIfoAccOpenDivOsaka = this.accountRow.getIfoAccOpenDivOsaka();
        //get 先物OP口座開設区分（名証）
        String l_strIfoAccOpenDivNagoya = this.accountRow.getIfoAccOpenDivNagoya();
        
        boolean l_isIfoAccountOpen = false;
        //[パラメータ.先物／オプション区分==”オプション”の場合]
        if(WEB3FuturesOptionDivDef.OPTION.equals(l_strFuturesOptionDiv))
        {
            if(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
             ||WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
             ||WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya))
            {
                l_isIfoAccountOpen = true;
            }
        }
        //[パラメータ.先物／オプション区分==”先物”の場合] 
        else
        {
            if(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
             ||WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
             ||WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya))
            {
                l_isIfoAccountOpen = true;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isIfoAccountOpen;
    }
   
    /**
     * (getOP取引口座タイプ)<BR>
     *  <BR>
     * 顧客のオプション取引口座を判別し、該当の補助口座タイプ <BR>
     * （SubAccountTypeEnum）を返却する。オプション口座未開設 <BR>
     * の場合は、例外をthrowする。 <BR>
     *  <BR>
     * １）　@証拠金口座開設済の場合<BR>
     * 顧客.先物OP口座開設区分（東証）==3：”先物OP口座開設”or<BR>
     * 顧客.先物OP口座開設区分（大証）==3：”先物OP口座開設”or<BR>
     * 顧客.先物OP口座開設区分（名証）==3：”先物OP口座開設” <BR>
     * <BR>
     * SubAccountTypeEnum.株式オプション取引口座(先物証拠金)(=7)<BR>
     * を返却する。<BR>
     * <BR>
     * ２）　@証拠金口座未開設の場合<BR>
     * 顧客.先物OP口座開設区分（東証）==1：”OP口座開設”or<BR>
     * 顧客.先物OP口座開設区分（大証）==1：”OP口座開設”or<BR>
     * 顧客.先物OP口座開設区分（名証）==1：”OP口座開設”<BR>  
     * <BR>
     * SubAccountTypeEnum.株式取引口座(預り金)(=1)　@を返却する。<BR>
     * <BR>
     * ３）　@オプション口座未開設の場合(１）、２）以外)<BR>
     * <BR>
     * 「オプション口座未開設エラー」の例外をthrowする。<BR>
     *      class：WEB3BusinessLayerException  <BR>
     *      tag：  BUSINESS_ERROR_00283  <BR>
     *  <BR>
     * @@return SubAccountTypeEnum
     * @@throws WEB3BusinessLayerException
     */
    public SubAccountTypeEnum getOpSubAccountType()
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "getOPSubAccountType()";
        log.entering(STR_METHOD_NAME);
        
        //get 先物OP口座開設区分（東証）
        String l_strIfoAccOpenDivTokyo = this.accountRow.getIfoAccOpenDivTokyo();
        //get 先物OP口座開設区分（大証）
        String l_strIfoAccOpenDivOsaka = this.accountRow.getIfoAccOpenDivOsaka();
        //get 先物OP口座開設区分（名証）
        String l_strIfoAccOpenDivNagoya = this.accountRow.getIfoAccOpenDivNagoya();
        
        if(WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
         ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
         ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya))
        {
            //１）　@証拠金口座開設済の場合 
            //(先物証拠金)(=7)　@を返却する。
            log.exiting(STR_METHOD_NAME);
            return SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;
        }
        else if(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
         ||WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
         ||WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya))
        {
            //２）　@証拠金口座未開設の場合
            //SubAccountTypeEnum.株式取引口座(預り金)(=1)　@を返却する
            log.exiting(STR_METHOD_NAME);
            return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;

        }
        else
        {
            // ３）　@オプション口座未開設の場合(１）、２）以外) 
            //「オプション口座未開設エラー」の例外をthrowする。
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00283,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (is取引停止銘柄)<BR>
     *  <BR>
     * 顧客・銘柄別取引停止チェック。<BR>
     * －顧客が、引数で指定された銘柄＋取引について、<BR>
     * 取引停止中であるかどうかを返す。<BR>
     * （DBレイアウト「顧客銘柄別取引停止テーブル仕様.xls」参照）<BR>
     *  <BR>
     * １）　@チェック実施有無の判定<BR>
     *  <BR>
     * 発注日が特定できる場合(*)は、以降の処理を続行する。<BR>
     * 発注日が特定できない場合は、何もせずにfalse（＝取引停止銘柄でない）を<BR>
     * returnする。<BR>
     *  <BR>
     * (*) 発注日が特定できる場合<BR>
     * 取引時間管理.get発注日()で正常に発注日が取得できる<BR>
     * （例外がスローされない）場合は、発注日が特定できると判断する。<BR>
     * 取引時間管理.get発注日()で発注日が取得できない<BR>
     * （例外がスローされる）場合は、発注日が特定できないと判断する。<BR>
     *  <BR>
     * ２）　@DB検索 <BR>
     * 顧客銘柄別取引停止テーブルを以下の条件で検索する。<BR>
     *  <BR>
     *    [条件] <BR>
     * 証券会社コード = this.証券会社コード<BR>
     * 部店ID = this.部店ID<BR>
     * 口座ID = this.口座ID<BR>
     * 銘柄ID in （"全銘柄", 引数の銘柄ID）<BR>
     * 適用開始年月日 ＜＝ 取引時間管理.get発注日(*1)<BR>
     * 適用終了年月日 ＞＝ 取引時間管理.get発注日(*1)<BR>
     *  <BR>
     * ３）　@指定取引の停止中かどうかの判定 <BR>
     * 取得したデータのいずれかで、該当する取引停止項目(*1)が <BR>
     * "停止中"の場合は、trueを返す。上記以外、または該当データなし<BR>
     * の場合は、falseを返す。<BR>
     *  <BR>
     * (*1)該当する取引停止項目は、引数の「注文種別」より以下のように<BR>
     * 特定する。<BR>
     *  <BR>
     * "現物買注文"：　@買現物取引停止<BR>
     * "現物売注文"：　@売現物取引停止<BR>
     * "新規買建注文"：　@買新規建取引停止<BR>
     * "新規売建注文"：　@売新規建取引停止<BR>
     * "買建返済注文"：　@買建返済（売返済）取引停止<BR>
     * "売建返済注文"：　@売建返済（買返済）取引停止<BR>
     * "現引注文"：　@現引取引停止<BR>
     * "現渡注文"：　@現渡取引停止<BR>
     * "株式ミニ株買注文"：　@買ミニ株取引停止<BR>
     * "株式ミニ株売注文"：　@売ミニ株取引停止<BR>
     * "外株買い"：　@買現物取引停止<BR>
     * "外株売り"：　@売現物取引停止<BR> 
     *  <BR>   
     * @@param l_lngProductId (銘柄ID)
     * @@param l_orderTypeEnum (注文種別)
     * @@throws WEB3SystemLayerException
     */
    public boolean isTradeStopProduct(
        long l_lngProductId,
        OrderTypeEnum l_orderTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isTradeStopProduct(long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        final long ALL_PRODUCT = 0;
        
        //１）　@チェック実施有無の判定
        //発注日が特定できる場合(*)は、以降の処理を続行する。
        //発注日が特定できない場合は、何もせずにfalse（＝取引停止銘柄でない）をreturnする。
        Date l_bizDate = null;
        try
        {
            l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        catch(WEB3SystemLayerException wse)
        {
            l_bizDate = null;
        }
        if(l_bizDate == null)
        {
            return false;
        }
               
        //２） 顧客銘柄別取引停止テーブルを以下の条件で検索する。
        //[条件] 
        //証券会社コード = this.証券会社コード
        //部店ID = this.部店ID
        //口座ID = this.口座ID
        //銘柄ID in （"全銘柄", 引数の銘柄ID）
        //適用開始年月日 ＜＝ 取引時間管理.get発注日(void)
        //適用終了年月日 ＞＝ 取引時間管理.get発注日(void)
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_id = ? ");
        l_sbWhere.append(" and account_id = ? ");
        l_sbWhere.append(" and ( product_id = ?  or product_id = ? )");
        Object[] l_objWhere = {
            this.institutionCode, 
            new Long(this.accountRow.getBranchId()),
            new Long(this.accountRow.getAccountId()),
            new Long(ALL_PRODUCT),
            new Long(l_lngProductId)
        };
        List l_lstRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                AccountProductOrderStopRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        int l_intSize = l_lstRecords.size();
        AccountProductOrderStopRow l_accountProductOrderStopRow = null;
        for (int i = 0; i < l_intSize; i++)
        {
            AccountProductOrderStopRow l_tmpRow = (AccountProductOrderStopRow)l_lstRecords.get(i);
            if((WEB3DateUtility.compareToDay(l_tmpRow.getApplyStartDate(),l_bizDate) <= 0) 
            &&(WEB3DateUtility.compareToDay(l_bizDate,l_tmpRow.getApplyEndDate()) <= 0))
            {
                l_accountProductOrderStopRow = l_tmpRow;
                break;
            }
        }
        if (l_accountProductOrderStopRow == null)
        {
            log.debug("顧客銘柄別取引停止テーブル検索： 件数 = 0");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //３）　@指定取引の停止中かどうかの判定 
        //取得したデータのいずれかで、該当する取引停止項目(*1)が 
        //"停止中"の場合は、trueを返す。上記以外、または該当データなしの場合は、falseを返す
        boolean l_isTradeStopProduct = false;
        if(OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum))
        {
            //"現物買注文"：　@買現物取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivBuyCash()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.EQUITY_SELL.equals(l_orderTypeEnum))
        {
            // "現物売注文"：　@売現物取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivSellCash()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum))
        {
            //"新規買建注文"：　@買新規建取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivLongMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum))
        {
            //"新規売建注文"：　@売新規建取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivShortMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderTypeEnum))
        {
            //"買建返済注文"：　@買建返済（売返済）取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivLongCloseMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderTypeEnum))
        {
            //"売建返済注文"：　@売建返済（買返済）取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivShortCloseMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderTypeEnum))
        {
            //"現引注文"：　@現引取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivLongSwapMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderTypeEnum))
        {
            //"現渡注文"：　@現渡取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivShortSwapMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderTypeEnum))
        {
            // "株式ミニ株買注文"：　@買ミニ株取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivBuyMiniStock()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderTypeEnum))
        {
            //"株式ミニ株売注文"：　@売ミニ株取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivSellMiniStock()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.FEQ_BUY.equals(l_orderTypeEnum))
        {
            //"外株買い"：　@買現物取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivBuyCash()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.FEQ_SELL.equals(l_orderTypeEnum))
        {
            //"外株売り"：　@売現物取引停止
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivSellCash()))
            {
                l_isTradeStopProduct = true;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isTradeStopProduct;
          
    }
    
    /**
     * (is外国証券口座開設)<BR>
     *  <BR>
     * 当該顧客が、外国証券口座を開設しているかどうかを判定する。<BR>
     *  <BR>
     * this.外国証券口座開設区分＝"口座開設"の場合は、<BR>
     * trueを返却する。以外、falseを返却する。<BR>
     */
    public boolean isForeignAccountOpen()
    {
        return WEB3ForeignSecAccOpenDiv.OPEN.equals(
            this.accountRow.getForeignSecAccOpenDiv());
    }

    /**
     * (is法@人)<BR>
     * 法@人顧客かどうかの判別をする。<BR>
     * <BR>
     * this.性別 == ”法@人” の場合trueを、 <BR>
     * this.性別 != ”法@人” の場合falseを返却する。 <BR>
     * @@return boolean
     */
    public boolean isCorporation()
    {
        return WEB3SexDef.CORPORATE.equals(this.accountRow.getSex());
    }
    
    /**
     * (is累投口座開設)<BR>
     *  <BR>
     * 当該顧客が、累投口座を開設しているかどうかを判定する。<BR>
     *  <BR>
     * this.累投口座開設区分＝"口座開設"の場合は、<BR>
     * trueを返却する。以外、falseを返却する。<BR>
     */
    public boolean isRuitoAccountOpen()
    {
        return WEB3CumulativeAccountDivDef.ESTABLISH.equals(
            this.accountRow.getRuitoAccOpenDiv());
    }

    /**
     * (get顧客行)<BR>
     *  <BR>
     * this.顧客Rowオブジェクトを返却する。<BR>
     *  <BR>
     * @@return MainAccountRow
     */
    public MainAccountRow getMainAccountRow()
    {
        return this.accountRow;
    }

    /**
     * (get顧客)<BR>
     *  <BR>
     * （static メソッド）  <BR>
     * 指定に該当する「顧客マスタオブジェクト」のListを取得する。  <BR>
     * <BR>
     * １）　@QueryProcessor.doFindAllQuery( )により、<BR>
     * 「顧客マスタ行オブジェクト」のListを取得する。  <BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数]  <BR>
     * 　@　@rowType：　@顧客マスタRow.TYPE  <BR>
     * 　@　@where：　@検索条件文字列  <BR>
     * 　@　@orderBy：　@ソート条件  <BR>
     * 　@　@conditions：　@null  <BR>
     * 　@　@bindVars：　@検索条件データコンテナ  <BR>
     * <BR>
     * ２）　@検索結果の行オブジェクトで「顧客マスタオブジェクト」を生成し、<BR>
     * Listで返却する。 <BR>
     *  <BR>
     * @@param l_strWhere (検索条件文字列)
     * @@param l_bindVars (検索条件データコンテナ)
     * @@param l_strOrderBy (ソート条件)
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getMainAccount(
        String l_strWhere,
        String[] l_bindVars,
        String l_strOrderBy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccount(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstReturn = new ArrayList();
        List l_lstRecords = null;
        try
        {
            //QueryProcessor.doFindAllQuery( )により、「顧客マスタ行オブジェクト」のListを取得する。
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lstRecords = l_qp.doFindAllQuery(
                MainAccountRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        //検索結果の行オブジェクトで「顧客マスタオブジェクト」を生成し、 Listで返却する。
        WEB3GentradeMainAccount[] l_gentradeMainAccounts = 
            new WEB3GentradeMainAccount[l_lstRecords.size()];
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_lstRecords.get(i);
            l_gentradeMainAccounts[i] = new WEB3GentradeMainAccount(l_mainAccountRow);
            l_lstReturn.add(l_gentradeMainAccounts[i]);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lstReturn;
    }

    /**
     * (is円貨振込先（銀行口座）登録)<BR>
     *  <BR>
     * 当該顧客が、振込先（銀行口座）に円貨登録しているかどうかを判定する。 <BR>
     * <BR>
     * this.振込先（銀行口座）登録＝"1：円貨のみ登録済"or"3：両方登録済"の場合は、trueを返却する。 <BR>
     * 以外、falseを返却する。<BR>
     *  <BR>
     * @@return boolean
     */
    public boolean isJapaneseCurrencyBankAccountRegi()
    {
        return WEB3BankAccountRegiDef.ALREADY_REGISTER.equals(
            this.accountRow.getBankAccountRegi())
            || WEB3BankAccountRegiDef.BOTH_REGISTERED.equals(
            this.accountRow.getBankAccountRegi());
    }

    /**
     * (is外貨振込先（銀行口座）登録)<BR>
     *  <BR>
     * 当該顧客が、振込先（銀行口座）に外貨登録しているかどうかを判定する。 <BR>
     * <BR>
     * this.振込先（銀行口座）登録＝"2：外貨のみ登録済"or"3：両方登録済"の場合は、trueを返却する。 <BR>
     * 以外、falseを返却する。<BR>
     *  <BR>
     * @@return boolean
     */
    public boolean isForeignCurrencyBankAccountRegi()
    {
        return WEB3BankAccountRegiDef.ONLY_FOREIGN_CURRENCY_REGISTERED.equals(
            this.accountRow.getBankAccountRegi())
            || WEB3BankAccountRegiDef.BOTH_REGISTERED.equals(
            this.accountRow.getBankAccountRegi());
    }

    /**
     * (is証券担保ローン口座開設)<BR>
     *  <BR>
     * 証券担保ローン口座開設顧客チェック。 <BR>
     * <BR>
     * 当該顧客が証券担保ローン口座を開設済であればtrue、 <BR>
     * 未開設であればfalseを返却する。 <BR>
     * <BR>
     * １） 顧客行.証券担保ローン区分 == "1"(開設) の場合 <BR>
     * <BR>
     *   １-１） 株券担保ローン口座テーブルの当該行を取得する。 <BR>
     * <BR>
     *       [検索条件] <BR>
     *         ・口座ID： this.口座ID <BR>
     *         ・開設状況： "2"(成約) <BR>
     * <BR>
     *   １-２） １-１） の戻り値Listの長さ > 0 の場合、trueを返却する。 <BR>
     * <BR>
     *   １-３） １-１） の戻り値Listの長さ = 0 の場合、falseを返却する。 <BR>
     * <BR>
     * ２） 顧客行.証券担保ローン区分 != "1" の場合、falseを返却する。 <BR>
     *  <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public boolean isSecuredLoanAccountOpen() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMainAccount()";
        log.entering(STR_METHOD_NAME);

        //顧客行.証券担保ローン区分 == "1"(開設) の場合
        if (WEB3SecuredLoanSecAccOpenDivDef.OPEN.equals(
            this.accountRow.getSecuredLoanSecAccOpenDiv()))
        {
            //[検索条件]
            //口座ID： this.口座ID
            //開設状況： "2"(成約)
            List l_lisReturns = null;
            String l_strWhere = " account_id = ? and account_open_status = ? ";
            List l_lisValues = new ArrayList();
            l_lisValues.add(new Long(this.accountRow.getAccountId()));
            l_lisValues.add(WEB3AccountOpenStatusDef.PROMISE);
            Object[] l_objValues = l_lisValues.toArray();

            //株券担保ローン口座テーブルの当該行を取得する
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisReturns = l_queryProcessor.doFindAllQuery(
                    StockSecuredLoanRow.TYPE,
                    l_strWhere,
                    l_objValues);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error("DBへのアクセスに失敗しました。", l_dnex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dnex.getMessage(),
                    l_dnex);
            }
            catch (DataQueryException l_dqex)
            {
                log.error("DBへのアクセスに失敗しました。", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }

            if (l_lisReturns.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //顧客行.証券担保ローン区分 != "1" の場合
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is書面交付)<BR>
     * <BR>
     * 書面が交付済みかどうかのチェックを行う。<BR>
     * <BR>
     * １）以下の条件で、書面交付管理テーブルを検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR> 
     * 　@　@口座ID = this.getAccountId()の戻り値<BR>
     * 　@　@書面区分 = 引数.書面区分<BR>
     * 　@　@銘柄コード = 引数.銘柄コード<BR>
     * 　@　@書面交付日 ≦ 現在日付の日付部分<BR>
     * 　@　@削除フラグ = ”有効”<BR>
     * <BR>
     * ２）テーブルからレコードが取得できた場合、trueを返却する。<BR>
     * 　@　@取得できなかった場合、falseを返却する。<BR>
     * <BR>
     * @@param l_documentDivision 書面区分（種別コード） 
     * @@param l_productCode 銘柄コード
     * @@return boolean
     */
    public boolean isDocumentDelivery(String l_documentDivision, String l_productCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDocumentDelivery(String, String)";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intSize;
        Date l_datBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        
        // １）以下の条件で、書面交付管理テーブルを検索する。 
        // [条件] 
        // 口座ID = this.getAccountId()の戻り値
        // 書面区分 = 引数.書面区分
        // 銘柄コード = 引数.銘柄コード
        // 書面交付日 ≦ 現在日付の日付部分
        // 削除フラグ = ”有効”
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and document_div = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and delivery_date <= to_date(?, 'yyyyMMdd') ");
        l_sbWhere.append(" and delete_flag = ? ");
        
        Object[] l_objWhere = {
            String.valueOf(this.getAccountId()),
            l_documentDivision,
            l_productCode,
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
            BooleanEnum.FALSE
        };
        
        List l_lisRecords = null;
        QueryProcessor l_queryProcessor;
		try 
        {
			l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = 
                l_queryProcessor.doFindAllQuery(
                    DocDeliveryManagementRow.TYPE, 
                    l_sbWhere.toString(), 
                    l_objWhere); 

		} 
        catch (DataException e) 
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);

		} 
        
        l_intSize = l_lisRecords.size();
        
        // ２）テーブルからレコードが取得できた場合、trueを返却する。
        // 取得できなかった場合、falseを返却する。
        if (l_intSize < 1) 
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
    }

    /**
     * (isPTS口座開設)<BR>
     * 当該顧客が、PTS口座を開設しているかどうかを判定する。<BR>
     * <BR>
     * this.PTS口座開設区分＝"口座開設"の場合は、trueを返却する。<BR>
     * 以外、falseを返却する<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isPTSAccountOpen()
    {
        final String STR_METHOD_NAME = "isPTSAccountOpen()";
        log.entering(STR_METHOD_NAME);

        String l_strPtsAccOpenDiv = this.accountRow.getPtsAccOpenDiv();
        if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_strPtsAccOpenDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isPTS取引停止銘柄)<BR>
     * 顧客・銘柄別取引停止チェック。<BR>
     * －顧客が、引数で指定された銘柄＋取引について、取引停止中であるかどうかを返す。<BR>
     * 　@（DBレイアウト「顧客銘柄別取引停止テーブル仕様.xls」参照）<BR>
     * <BR>
     * １）　@チェック実施有無の判定<BR>
     * <BR>
     * 　@発注日が特定できる場合(*)は、以降の処理を続行する。<BR>
     * 　@発注日が特定できない場合は、何もせずにfalse（＝取引停止銘柄でない）をreturnする。<BR>
     * <BR>
     * 　@(*) 発注日が特定できる場合<BR>
     * 　@取引時間管理.getPTS発注日()で正常に発注日が取得できる（例外がスローされない）場合は、<BR>
     * 　@発注日が特定できると判断する<BR>
     * 　@取引時間管理.getPTS発注日()で発注日が取得できない（例外がスローされる）場合は<BR>
     * 　@発注日が特定できないと判断する。<BR>
     * <BR>
     * ２）　@DB検索<BR>
     * 　@顧客銘柄別取引停止テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = this.証券会社コード<BR>
     * 　@部店ID = this.部店ID<BR>
     * 　@口座ID = this.口座ID<BR>
     * 　@銘柄ID in （"全銘柄", 引数の銘柄ID）<BR>
     * 　@適用開始年月日 <= 発注日(*1)<BR>
     * 　@適用終了年月日 >= 発注日(*1)<BR>
     * <BR>
     * ３）　@指定取引の停止中かどうかの判定<BR>
     * 　@取得したデータのいずれかで、該当する取引停止項目(*1)が"停止中"の場合は、<BR>
     * 　@trueを返す。<BR>
     * 　@上記以外、または該当データなしの場合は、falseを返す。<BR>
     * <BR>
     * (*1)該当する取引停止項目は、引数の「注文種別」より以下のように特定する。<BR>
     * <BR>
     * "現物買注文"：　@買現物取引停止<BR>
     * "現物売注文"：　@売現物取引停止<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isPTSTradeStopProduct(long l_lngProductId, OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSTradeStopProduct(long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //チェック実施有無の判定
        Date l_datPTSOrderBizDate = null;
        try
        {
            //発注日が特定できる場合(*)は、以降の処理を続行する。
            l_datPTSOrderBizDate = WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            l_datPTSOrderBizDate = null;
        }

        if (l_datPTSOrderBizDate == null)
        {
            //発注日が特定できない場合は、何もせずにfalse（＝取引停止銘柄でない）をreturnする。
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //DB検索
        //顧客銘柄別取引停止テーブルを以下の条件で検索する。
        //[条件]
        //　@証券会社コード = this.証券会社コード
        //　@部店ID = this.部店ID
        //　@口座ID = this.口座ID
        //　@銘柄ID in （"全銘柄", 引数の銘柄ID）
        //　@適用開始年月日 <= 発注日(*1)
        //　@適用終了年月日 >= 発注日(*1)
        StringBuffer l_sbSql = new StringBuffer();
        l_sbSql.append(" institution_code = ? ");
        l_sbSql.append(" and branch_id = ? ");
        l_sbSql.append(" and account_id = ? ");
        l_sbSql.append(" and product_id in (?, ?) ");

        Object[] l_sqlValues = new Object[]{
            this.institutionCode,
            new Long(this.accountRow.getBranchId()),
            new Long(this.accountRow.getAccountId()),
            new Long(WEB3ProductIdDef.ALL_PRODUCT),
            new Long(l_lngProductId)};

        List l_lisResults;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResults = l_processor.doFindAllQuery(
                AccountProductOrderStopRow.TYPE,
                l_sbSql.toString(),
                l_sqlValues);
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

        AccountProductOrderStopRow l_orderStopRow = null;
        int l_intCnt = l_lisResults.size();
        for (int i = 0; i < l_intCnt; i++)
        {
            l_orderStopRow = (AccountProductOrderStopRow)l_lisResults.get(i);
            if (WEB3DateUtility.compareToDay(l_orderStopRow.getApplyStartDate(), l_datPTSOrderBizDate) <= 0
                && WEB3DateUtility.compareToDay(l_datPTSOrderBizDate, l_orderStopRow.getApplyEndDate()) <= 0)
            {
                //取得したデータのいずれかで、該当する取引停止項目(*1)が"停止中"の場合は、
                //trueを返す。
                if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
                {
                    if (WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_orderStopRow.getStopTradeDivBuyCash()))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return true;
                    }
                }

                if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
                {
                    if (WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_orderStopRow.getStopTradeDivSellCash()))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return true;
                    }
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is保振（機@構預託）同意)<BR>
     *  <BR>
     * 当該顧客が、保振（機@構預託）に同意しているかどうかを判定する。 <BR>
     * <BR>
     * this.保振区分（機@構預託）＝"同意"の場合は、trueを返却する。 <BR>
     * 以外、falseを返却する。 <BR>
     *  <BR>
     * @@return boolean
     */
    public boolean isOrgDepositAgree()
    {
        return WEB3OrgDepositDivDef.AGREE.equals(
            this.accountRow.getOrgDepositDiv());
    }

    /**
     * (isCFD口座開設)<BR>
     * 当該顧客が、CFD口座を開設しているかどうかを判定する。<BR>
     * <BR>
     * this.CFD口座開設区分＝"口座開設"の場合は、 true を返却する。<BR>
     * それ以外は false を返却する。<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isCFDAccountOpen()
    {
        final String STR_METHOD_NAME = "isCFDAccountOpen()";
        log.entering(STR_METHOD_NAME);

        String l_strCfdAccOpenDiv = this.accountRow.getCfdAccOpenDiv();
        if (WEB3CfdAccOpenDivDef.ACCOUNT_OPEN.equals(l_strCfdAccOpenDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}@
