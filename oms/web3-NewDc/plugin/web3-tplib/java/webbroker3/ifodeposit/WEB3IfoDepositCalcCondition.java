head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金計算条件クラス(WEB3IfoDepositCalcCondition.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/21 山田　@卓司(FLJ) 新規作成
 Revesion History : 2007/06/28 hijikata(SRA) 夕場対応 モデルNo.056③, No.058, No.059, No.082
 Revesion History : 2007/08/01 hijikata(SRA) 夕場対応 モデルNo.097
 Revision History : 2007/08/13 k.yamashita(SRA)  夕場対応 U03048,U03049
 Revision History : 2007/10/18 k.yamashita(SRA)  未取込要件No.021, モデルNo.117
 
 
 */
package webbroker3.ifodeposit;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3SessionTypeDef;

/**
 * (証拠金計算条件)<BR>
 * 
 * 会社・部店別/顧客別証拠金計算条件を表すクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositCalcCondition
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositCalcCondition.class);

    /**
     * (営業日[T-1..T+2])<BR>
     * 営業日[T-1..T+2]<BR>
     * T-1、T+0、T+1、T+2の営業日。<BR>
     */
    private Date[] bizDates;

    /**
     * (証拠金計算基準日)<BR>
     * 証拠金計算基準日。
     */
    private int ifoDepositCalcBaseDate;

    /**
     * (新規建余力可能フラグ)<BR>
     * 
     * 該当顧客が先物OP新規建余力可能であるか(余力停止になっていないか)を判定するフラグ
     * 。<BR>
     * 　@・余力可能の場合：　@true<BR>
     * 　@・余力不可の場合：　@false<BR>
     */
    private boolean newOpenTradingPowerAvailableFlag = false;

    /**
     * (リアル時価証拠金計算実施フラグ)<BR>
     * リアル時価証拠金計算を実施しているかを判定するフラグ。<BR>
     * 　@・実施している場合：　@true<BR>
     * 　@・実施していない場合：　@false<BR>
     */
    private boolean realPriceIfoDepositCalcFlag = false;

    /**
     * (簡易SPAN計算実施フラグ)<BR>
     * 簡易SPAN計算を実施しているかを判定するフラグ。<BR>
     * 　@・実施している場合：　@true <BR>
     * 　@・実施していない場合：　@false<BR>
     */
    private boolean simpleSPANCalcFlag = false;

    /**
     * (SPANトラブルフラグ)<BR>
     * 
     * PC-SAPNがトラブル時かどうかを判定するフラグ。<BR>
     * 　@・トラブルの場合：　@true<BR>
     * 　@・正常の場合：　@false<BR>
     */
    private boolean SPANTroubleFlag = false;

    /**
     * (規定証拠金変更フラグ)<BR>
     * <BR>
     * 規定証拠金が変更されたかどうかを判定するフラグ。<BR>
     * ・規定証拠金が変更されている場合：　@true<BR>
     * ・規定証拠金が変更されていない場合：　@false<BR>
     */
    private boolean IfoDepositPerUnitChangeFlag = false;

    /**
     * (証拠金不足メール送信済フラグ)<BR>
     * <BR>
     * T+0の証拠金不足メールが送信されたかどうかを判定するフラグ。<BR>
     * ・証拠金不足メール送信済の場合：　@true<BR>
     * ・証拠金不足メール未送信の場合：　@false<BR>
     */
    private boolean IfoDepositMailFlag = false;

    /**
     * (清算値速報受信済フラグ)<BR>
     * <BR>
     * 清算値速報が受信されたかどうかを判定するフラグ。<BR>
     * ・清算値速報受信済の場合：　@true<BR>
     * ・清算値速報受信未済の場合：　@false<BR>
     */
    private boolean quickReportReceivedFlag = false;

    /**
     * (夕場実施フラグ)<BR>
     * <BR>
     * 夕場実施するかどうかを判定するフラグ。<BR>
     * ・夕場実施の場合：　@true<BR>
     * ・夕場未実施の場合：　@false<BR>
     */
    private boolean eveningSessionFlag = false;

    /**
     * (証拠金不足額非管理フラグ)<BR>
     * <BR>
     * 証拠金不足額を管理するかどうかを判定するフラグ。<BR>
     * ・管理しない場合：　@true<BR>
     * ・管理する場合：　@false<BR>
     */
    private boolean lackChargeNonManagementFlag = false;
    
    /**
     * (証拠金SQ日銘柄ポジション非計上)<BR>
     * <BR> 
     * SQ日銘柄をポジションに計上するかを判定するフラグ。<BR>
     * ・計上する場合：　@false<BR>
     * ・計上しない場合：　@true<BR>
     * 
     *  ※計上する＝false、計上しない＝trueなので注意すること  
     * 
     */
    private boolean ifodepositNonCalcSqProductFlag = false;
    

    /**
     * (SPAN係数)<BR>
     * 
     * SPAN係数。
     */
    private double SPANFactor = 0;

    /**
     * SPAN係数レッド。
     */
    private double SPANFactorRed = 0;

    /**
     * 振替余力係数。
     */
    private double transferPowerFactor = 0;

    /**
     * 必要最低証拠金。
     */
    private double minIfoDeposit = 0;

    /**
     * (前日証拠金不足額)<BR>
     * 
     * 営業日[T-1]に証拠金不足メールとして送信された証拠金不足額。<BR>
     * 
     * 営業日[T-1]に証拠金不足が発生している場合のみ設定される。<BR>
     * 以下の場合は0となる。<BR>
     * ・営業日[T-1]に証拠金不足が発生していない場合<BR>
     * ・営業日[T+0]の証拠金不足メールが送信済の場合(当日証拠金不足額 > 0)<BR>
     */
    private double preBizDateIfoDepositLackCharge = 0;

    /**
     * (当日証拠金不足額)<BR>
     * 
     * 営業日[T+0]に証拠金不足メールとして送信された証拠金不足額。<BR>
     * 
     * 営業日[T+0]に証拠金不足が発生している場合のみ設定される。<BR>
     * 以下の場合は0となる。<BR>
     * ・営業日[T+0]に証拠金不足が発生していない場合<BR>
     * ・営業日[T+0]の証拠金不足メールが未送信の場合<BR>
     */
    private double currentBizDateIfoDepositLackCharge = 0;

    /**
     * 部店別証拠金計算条件一覧<BR>
     */
    private Map calcConditionPerBranch;
    
    /**
     * 指数別証拠金計算条件一覧<BR>
     */
    private WEB3IfoDepositCalcConditionPerIndex[] calcConditions;

    /**
     * @@roseuid 416120B4006B
     */
    public WEB3IfoDepositCalcCondition()
    {
        calcConditions = new WEB3IfoDepositCalcConditionPerIndex[0];
        calcConditionPerBranch = new TreeMap();
    }

    /**
     * (set営業日[T-1..T+2])<BR>
     * 
     * 引数.営業日[T-1..T+2]をthis.営業日[T-1..T+2]にセットする。<BR>
     * @@param l_bizDates - (営業日[T-1..T+2])<BR>
     * 
     * 営業日[T-1]<BR>
     * 営業日[T+0]<BR>
     * 営業日[T+1]<BR>
     * 営業日[T+2]<BR>
     * の配列。<BR>
     * @@roseuid 41131FC402D7
     */
    public void setBizDates(Date[] l_bizDates)
    {
        bizDates = l_bizDates;
    }

    /**
     * (set証拠金計算基準日)<BR>
     * 
     * 引数.証拠金計算基準日をthis.証拠金計算基準日にセットする。<BR>
     * @@param l_intBaseDate - (証拠金計算基準日)<BR>
     * 
     * 1、または2いずれかの値。<BR>
     * @@roseuid 41345EC50051
     */
    public void setIfoDepositCalcBaseDate(int l_intBaseDate)
    {
        ifoDepositCalcBaseDate = l_intBaseDate;
    }

    /**
     * (set新規建余力可能フラグ)<BR>
     * 
     * 引数.新規建余力可能フラグをthis.新規建余力可能フラグにセットする。<BR>
     * @@param l_newOpenTradingPowerAvailableFlag - (新規建余力可能フラグ)<BR>
     * 
     * 余力可能：　@true<BR>
     * 余力不可：　@false<BR>
     * @@roseuid 41131BCE02F6
     */
    public void setNewOpenTradingPowerAvailableFlag(boolean l_blnNewOpenTradingPowerAvailableFlag)
    {
        newOpenTradingPowerAvailableFlag =
            l_blnNewOpenTradingPowerAvailableFlag;
    }

    /**
     * (setリアル時価証拠金計算実施フラグ)<BR>
     * 
     * 引数.リアル時価証拠金計算実施フラグをthis.リアル時価証拠金計算実施フラグにセット
     * する。<BR>
     * @@param l_blnRealCurrentPriceIfoDepositCalcFlag - 
     * (リアル時価証拠金計算実施フラグ)<BR>
     * 実施：　@true<BR>
     * 未実施：　@false<BR>
     * @@roseuid 4111BD2C022B
     */
    public void setRealPriceIfoDepositCalcFlag(boolean l_blnRealCurrentPriceIfoDepositCalcFlag)
    {
        realPriceIfoDepositCalcFlag = l_blnRealCurrentPriceIfoDepositCalcFlag;
    }

    /**
     * (set簡易SPAN計算実施フラグ)<BR>
     * 
     * 引数.簡易SPAN計算実施フラグをthis.簡易SPAN計算実施フラグにセットする。<BR>
     * @@param l_blnSimpleSPANCalcFlag - (簡易SPAN計算実施フラグ)<BR>
     * 
     * 実施：　@true<BR>
     * 未実施：　@false<BR>
     * @@roseuid 4111CDEB0141
     */
    public void setSimpleSPANCalcFlag(boolean l_blnSimpleSPANCalcFlag)
    {
        simpleSPANCalcFlag = l_blnSimpleSPANCalcFlag;
    }

    /**
     * (setSPANトラブルフラグ)<BR>
     * 
     * 引数.SPANトラブルフラグをthis.SPANトラブルフラグにセットする。<BR>
     * @@param l_blnSPANTroubleFlag - (SPANトラブルフラグ)<BR>
     * 
     * トラブルの場合：　@true<BR>
     * 正常の場合：　@false<BR>
     * @@roseuid 411319B600C4
     */
    public void setSpanTroubleFlag(boolean l_blnSPANTroubleFlag)
    {
        SPANTroubleFlag = l_blnSPANTroubleFlag;
    }

    /**
     * (setSPAN係数)<BR>
     * 
     * 引数.SPAN係数をthis.SPAN係数にセットする。<BR>
     * @@param l_dblSPANFactor - (SPAN係数)<BR>
     * 
     * SPAN係数。<BR>
     * @@roseuid 41131B0900A5
     */
    public void setSPANFactor(double l_dblSPANFactor)
    {
        SPANFactor = l_dblSPANFactor;
    }

    /**
     * (setSPAN係数レッド)<BR>
     * 
     * 引数.SPAN係数レッドをthis.SPAN係数レッドにセットする。<BR>
     * @@param l_dblSPANFactorRate - SPAN係数レッド。
     * @@roseuid 41131CB302C7
     */
    public void setSPANFactorRed(double l_dblSPANFactorRad)
    {
        SPANFactorRed = l_dblSPANFactorRad;
    }

    /**
     * (set振替余力係数)<BR>
     * 
     * 引数.振替余力係数をthis.振替余力係数にセットする。<BR>
     * @@param l_dblTransferPowerFactor - 振替余力係数。
     * @@roseuid 41131CEA0066
     */
    public void setTransferPowerFactor(double l_dblTransferPowerFactor)
    {
        transferPowerFactor = l_dblTransferPowerFactor;
    }

    /**
     * (set必要最低証拠金)<BR>
     * 
     * 引数.必要最低証拠金をthis.必要最低証拠金にセットする。<BR>
     * @@param l_dblMinIfoDeposit - 必要最低証拠金。
     * @@roseuid 41131D3C018F
     */
    public void setMinIfoDeposit(double l_dblMinIfoDeposit)
    {
        minIfoDeposit = l_dblMinIfoDeposit;
    }

    /**
     * (set前日証拠金不足額)<BR>
     * 
     * 引数.前日証拠金不足額をthis.前日証拠金不足額にセットする。<BR>
     * @@param l_dblPreBizDateInfoDepositLackCharge - 前日証拠金不足額。
     * @@roseuid 41401DC500C9
     */
    public void setPreBizDateInfoDepositLackCharge(double l_dblPreBizDateInfoDepositLackCharge)
    {
        preBizDateIfoDepositLackCharge = l_dblPreBizDateInfoDepositLackCharge;
    }

    /**
     * (set当日証拠金不足額)<BR>
     * 
     * 引数.当日証拠金不足額をthis.当日証拠金不足額にセットする。<BR>
     * @@param l_dblCurrentBizIfoDepositLackCharge - 当日証拠金不足額。
     * @@roseuid 4132DF4002AB
     */
    public void setCurrentBizIfoDepositLackCharge(double l_dblCurrentBizIfoDepositLackCharge)
    {
        currentBizDateIfoDepositLackCharge =
            l_dblCurrentBizIfoDepositLackCharge;
    }

    /**
     * (set指数別証拠金計算条件一覧)<BR>
     * 
     * 引数.指数別証拠金計算条件一覧をthis.指数別証拠金計算条件一覧にセットする。<BR>
     * @@param l_ifoDepositCalcConditionPerIndexList - (指数別証拠金計算条件一覧)<BR>
     * 
     * 指数別証拠金計算条件の一覧。<BR>
     * @@roseuid 41131DA90316
     */
    public void setIfoDepositCalcPerIndexList(WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList)
    {
        calcConditions = l_ifoDepositCalcConditionPerIndexList;
    }

    /**
     * (get営業日)<BR>
     * 
     * 指定日に対応する営業日を返却する。<BR>
     * 
     * 引数.指定日 == -1の場合、this.営業日[T-1..T+2][0]を返却する。<BR>
     * 引数.指定日 == 0の場合、this.営業日[T-1..T+2][1]を返却する。<BR>
     * 引数.指定日 == 1の場合、this.営業日[T-1..T+2][2]を返却する。<BR>
     * 引数.指定日 == 2の場合、this.営業日[T-1..T+2][3]を返却する。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * -1、0、1、2のいずれかの値。<BR>
     * @@return Date
     * @@roseuid 41131F35025A
     */
    public Date getBizDate(int l_intReservedDate)
    {
        if (bizDates == null)
        {
            return null;
        }
        switch (l_intReservedDate)
        {
            case -1 :
                return bizDates[0];
            case 0 :
                return bizDates[1];
            case 1 :
                return bizDates[2];
            case 2 :
                return bizDates[3];
            default :
                return null;
        }
    }

    /**
     * (get営業日[T-1])<BR>
     * 
     * this.営業日[T-1..T+2][0]を返却する。<BR>
     * @@return Date
     * @@roseuid 41401EB30157
     */
    public Date getPreBizDate()
    {
        return getBizDate(-1);
    }

    /**
     * (get営業日[T+0])<BR>
     * 
     * this.営業日[T-1..T+2][1]を返却する。<BR>
     * @@return Date
     * @@roseuid 41131F84025A
     */
    public Date getCurrentBizDate()
    {
        return getBizDate(0);
    }

    /**
     * (get営業日[T+1])<BR>
     * 
     * this.営業日[T-1..T+2][2]を返却する。<BR>
     * @@return Date
     * @@roseuid 41131F840289
     */
    public Date getNextBizDate()
    {
        return getBizDate(1);
    }

    /**
     * (get営業日[T+2])<BR>
     * 
     * this.営業日[T-1..T+2][3]を返却する。<BR>
     * @@return Date
     * @@roseuid 41131F840299
     */
    public Date getNext2BizDate()
    {
        return getBizDate(2);
    }

    /**
     * (get証拠金計算基準日)<BR>
     * 
     * this.証拠金計算基準日を返却する。<BR>
     * @@return int
     * @@roseuid 41345EC403BC
     */
    public int getIfoDepositBaseDate()
    {
        return ifoDepositCalcBaseDate;
    }

    /**
     * (getSPAN係数)<BR>
     * 
     * this.SPAN係数の値を返却する。<BR>
     * @@return double
     * @@roseuid 4113233D020C
     */
    public double getSPANFactor()
    {
        return SPANFactor;
    }

    /**
     * (getSPAN係数レッド)<BR>
     * 
     * this.SPAN係数レッドの値を返却する。<BR>
     * @@return double
     * @@roseuid 411323590131
     */
    public double getSPANFactorRed()
    {
        return SPANFactorRed;
    }

    /**
     * (get必要最低証拠金)<BR>
     * 
     * this.必要最低証拠金の値を返却する。<BR>
     * @@return double
     * @@roseuid 411323820102
     */
    public double getMinIfoDeposit()
    {
        return minIfoDeposit;
    }

    /**
     * (get前日証拠金不足額)<BR>
     * 
     * this.前日証拠金不足額の値を返却する。<BR>
     * @@return double
     * @@roseuid 4132DF7D01C0
     */
    public double getPreBizDateIfoDepositLackCharge()
    {
        return preBizDateIfoDepositLackCharge;
    }

    /**
     * (get当日証拠金不足額)<BR>
     * 
     * this.当日証拠金不足額の値を返却する。<BR>
     * @@return double
     * @@roseuid 41401E04001E
     */
    public double getCurrentBizDateIfoDepositLackCharge()
    {
        return currentBizDateIfoDepositLackCharge;
    }

    /**
     * (get振替余力係数)<BR>
     * 
     * this.振替余力係数の値を返却する。<BR>
     * @@return double
     * @@roseuid 4113236B0047
     */
    public double getTransferPowerFactor()
    {
        return transferPowerFactor;
    }

    /**
     * (get規定証拠金)<BR>
     * 
     * 指定の原資産銘柄コードに対応する規定証拠金を返却する。<BR>
     * 
     * this.指数別証拠金計算条件一覧の要素ごとのLoop処理を行い、<BR>
     * 指数別証拠金計算条件.原資産銘柄コード == 引数.原資産銘柄コードとなる<BR>
     * 指数別証拠金計算条件の規定証拠金を返却する。<BR>
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     * @@return double
     * @@roseuid 4133D0640173
     */
    public double getIfoDepositPerUnit(String l_strUnderlyingProductCode)
    {
        WEB3IfoDepositCalcConditionPerIndex l_indexTypeCondition =
            findCalcConditionPerIndex(l_strUnderlyingProductCode);
        if (l_indexTypeCondition != null)
        {
            return l_indexTypeCondition.getIfoDepositPerUnit();
        } else
        {
            return -1.0;
        }
    }

    /**
     * (get規定証拠金レッド)<BR>
     * 
     * 指定の原資産銘柄コードに対応する規定証拠金レッドを返却する。<BR>
     * 
     * this.指数別証拠金計算条件一覧の要素ごとのLoop処理を行い、<BR>
     * 指数別証拠金計算条件.原資産銘柄コード == 引数.原資産銘柄コードとなる<BR>
     * 指数別証拠金計算条件の規定証拠金レッドを返却する。<BR>
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     * @@return double
     * @@roseuid 4133D15401A2
     */
    public double getIfoDepositPerUnitRed(String l_strUnderlyingProductCode)
    {
        WEB3IfoDepositCalcConditionPerIndex l_indexTypeCondition =
            findCalcConditionPerIndex(l_strUnderlyingProductCode);
        if (l_indexTypeCondition != null)
        {
            return l_indexTypeCondition.getIfoDepositPerUnitRed();
        } else
        {
            return -1.0;
        }
    }
    
    /**
     * (get規定証拠金＜証拠金不足仮確定＞)<BR>
     * 
     * 指定の原資産銘柄コードに対応する規定証拠金＜証拠金不足仮確定＞を返却する。<BR>
     * 
     * this.指数別証拠金計算条件一覧の要素ごとのLoop処理を行い、<BR>
     * 指数別証拠金計算条件.原資産銘柄コード == 引数.原資産銘柄コードとなる<BR>
     * 指数別証拠金計算条件があれば、breakして該当の規定証拠金＜証拠金不足仮確定＞を返却する。<BR>
     * breakせずにLoop処理が終了した場合(指定の原資産銘柄コードが実施対象外)、-1を返却する。<BR>
     * 
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     * @@return double
     */
    public double getIfoDepositPerUnitTemp(String l_strUnderlyingProductCode)
    {
        WEB3IfoDepositCalcConditionPerIndex l_indexTypeCondition =
            findCalcConditionPerIndex(l_strUnderlyingProductCode);
        if (l_indexTypeCondition != null)
        {
            return l_indexTypeCondition.getIfoDepositPerUnitTemp();
        } else
        {
            return -1.0;
        }
    }

    /**
     * (get部店別証拠金計算条件)<BR>
     * 
     * 引数.条件項目名に対応する条件項目値をマップ(this.部店別証拠金計算条件）より検索し返却する。<BR> 
     * ※条件に該当する値が存在しない場合、例外をthrowする。<BR>
     * 
     * @@param l_strConditionName - (条件項目名)
     * @@return String
     */
    public String getCalcConditionPerBranch(String l_strConditionName)
    {
        final String STR_METHOD_NAME =
            "getCalcConditionPerBranch(String)";
            
        boolean l_isRegistered = calcConditionPerBranch.containsKey(l_strConditionName);

        String l_strValue = null;
        
        //条件に該当する値が存在する場合
        if(l_isRegistered == true)
        {
            l_strValue = (String)calcConditionPerBranch.get(l_strConditionName);
            return l_strValue;
        }
        //条件に該当する値が存在しない場合
        else
        {
            log.error("部店別条件項目名に該当する値が存在しませんでした");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME);
        }

    }

    /**
     * (get指数別証拠金計算条件)
     * 
     * this.指数別証拠金計算条件を返却する。
     * 
     * @@return 指数別証拠金計算条件
     */
    public WEB3IfoDepositCalcConditionPerIndex[] getIfoDepositCalcPerIndexList()
    {
        return calcConditions;
    }

    /**
     * (is新規建余力可能)<BR>
     * 
     * this.新規建余力可能フラグの値を返却する。<BR>
     * @@return boolean
     * @@roseuid 4113037E0335
     */
    public boolean isNewOpenTradingPowerAvailable()
    {
        return newOpenTradingPowerAvailableFlag;
    }

    /**
     * (isリアル時価証拠金計算実施)<BR>
     * 
     * this.リアル時価証拠金計算実施フラグの値を返却する。<BR>
     * @@return boolean
     * @@roseuid 411322BF0131
     */
    public boolean isRealPriceIfoDepositCalc()
    {
        return realPriceIfoDepositCalcFlag;
    }

    /**
     * (is簡易SPAN計算実施)<BR>
     * 
     * this.簡易SPAN計算実施フラグの値を返却する。<BR>
     * @@return boolean
     * @@roseuid 411322E601DD
     */
    public boolean isSimpleSPANCalc()
    {
        return simpleSPANCalcFlag;
    }

    /**
     * (isSPANトラブル)<BR>
     * 
     * this.SPANトラブルフラグの値を返却する。<BR>
     * @@return boolean
     * @@roseuid 4113230C0056
     */
    public boolean isSPANTrouble()
    {
        return SPANTroubleFlag;
    }

    /**
     * (isSPAN使用可能)<BR>
     * 
     * this.is簡易SPAN計算実施( ) == false、かつ、<BR>
     * this.isSPANトラブル( ) == falseの場合のみ、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4121B5890017
     */
    public boolean isSPANUsable()
    {
        if (!isSimpleSPANCalc() && !isSPANTrouble())
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * (calc営業日)<BR>
     * 
     * 営業日[T-1..T+2]を算出し、this.営業日[T-1..T+2]にセットする。<BR>
     * 
     * シーケンス図<BR>
     * 「(証拠金計算条件)calc営業日」参照。<BR>
     * @@roseuid 411321070008
     */
    public void calcBizDate()
    {

        TradingSystem l_ts = GtlUtils.getTradingSystem();
        Date l_datBaseDate = l_ts.getBizDate();

        try
        {

            Date[] l_datBizDates = new Date[4];
            WEB3GentradeBizDate l_bizDateUtil =
                new WEB3GentradeBizDate(new Timestamp(l_datBaseDate.getTime()));
            for (int i = -1; i < l_datBizDates.length - 1; i++)
            {
                l_datBizDates[i + 1] =
                    new Date(l_bizDateUtil.roll(i).getTime());
            }
            setBizDates(l_datBizDates);

        } 
        catch(WEB3SystemLayerException sle)
        {
            log.error(sle.getMessage(), sle);
            throw new WEB3BaseRuntimeException(sle.getErrorInfo(), sle
                .getErrorMethod(), sle.getErrorMessage(), sle.getException());
        }

    }

    /**
     * (calc証拠金計算基準日) <BR>
     * 
     * 取引ごとの証拠金計算基準日を算出し、自身のプロパティにセットする。 <BR>
     * 
     * １） T+0の営業日を取得する。 <BR>
     * this.get営業日[T+0]()をコールする。 <BR>
     * 
     * ２） 発注日を取得する。 <BR>
     * 取引時間管理.get発注日()をコールする。 <BR>
     * 
     * ３） 証拠金計算基準日をセットする。(※先物オプションの受渡日はT+1) <BR>
     * this.set証拠金計算基準日(証拠金計算基準日)をコールする。 <BR>
     * 
     * [引数の設定] <BR>
     * 証拠金計算基準日： <BR>
     * 
     * ３）-１　@夕場時間帯(取引時間管理.get立会区分 == "夕場")の場合、2 
     * ３）-２　@３）-1以外の場合 
     * -当日注文時間帯(取引時間管理.get発注日 == this.get営業日[T+0])の場合、1 <BR>
     * -翌日注文時間帯(取引時間管理.get発注日 != this.get営業日[T+0])の場合、2 <BR>
     * 
     * @@roseuid 41345EC500DD
     */
    public void calcIfoDepositCalcBaseDate()
    {
        try
        {
            // 営業日T+0
            Date l_datBaseDate = getCurrentBizDate();
            // 発注日
            Date l_datOrderBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                
			//３）-１　@夕場時間帯(取引時間管理.get立会区分 == "夕場")の場合、2
            if(WEB3SessionTypeDef.EVENING_SESSION.equals(WEB3GentradeTradingTimeManagement.getSessionType()))
            {
                setIfoDepositCalcBaseDate(WEB3IfoReservedDateDef.T_2);
            }
			else
			{			    			
	            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datBaseDate)
	                == 0)
	            {
	                // 当日注文時間帯（発注日 == 営業日）の場合
	                setIfoDepositCalcBaseDate(WEB3IfoReservedDateDef.T_1);
	            } else
	            {
	                // 翌日注文時間帯（発注日 != 営業日）の場合
	                setIfoDepositCalcBaseDate(WEB3IfoReservedDateDef.T_2);
	            }
            }

        }
        catch(WEB3SystemLayerException sle)
        {
            log.error(sle.getMessage(), sle);
            throw new WEB3BaseRuntimeException(sle.getErrorInfo(), sle
                .getErrorMethod(), sle.getErrorMessage(), sle.getException());
        }
    }

    /**
     * WEB3IfoDepositCalcConditionの文字列表現を返す。
     * 
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoDepositCalcCondition={");
        l_sb.append("bizDates={");
        for (int i = 0; i < bizDates.length; i++)
        {
            if (i > 0)
            {
                l_sb.append(",");
            }
            l_sb.append("[").append(i).append("]=").append(bizDates[i]);
        }
        l_sb.append("}");
        l_sb.append(",ifoDepositBaseDate=").append(getIfoDepositBaseDate());
        l_sb.append(",isNewOpenTradingPowerAvailable=").append(
            isNewOpenTradingPowerAvailable());
        l_sb.append(",isRealPriceIfoDepositCalc=").append(
            isRealPriceIfoDepositCalc());
        l_sb.append(",isSimpleSPANCalc=").append(isSimpleSPANCalc());
        l_sb.append(",isSPANTrouble=").append(isSPANTrouble());
        l_sb.append(",SPANFactor=").append(getSPANFactor());
        l_sb.append(",SPANFactorRed=").append(getSPANFactorRed());
        l_sb.append(",transferPowerFactor=").append(getTransferPowerFactor());
        l_sb.append(",miniIfoDeposit=").append(getMinIfoDeposit());
        l_sb.append(",preBizDateIfoDepositLackCharge=").append(getPreBizDateIfoDepositLackCharge());
        l_sb.append(",currentBizDateIfoDepositLackCharge=").append(getCurrentBizDateIfoDepositLackCharge());
        l_sb.append(",perIndexCalcConditions={");
        for (int i = 0; i < calcConditions.length; i++)
        {
            if (i > 0)
            {
                l_sb.append(",");
            }
            l_sb.append("[").append(i).append("]=").append(calcConditions[i]);
        }
        l_sb.append("}");
        //夕場対応追加項目
        l_sb.append(",isIfoDepositMailFlag=").append(isIfoDepositMailFlag());
        l_sb.append(",isQuickReportReceived=").append(isQuickReportReceived());
        l_sb.append(",isLackChargeNonManagement=").append(isLackChargeNonManagement());      
        l_sb.append(",isIfodepositNonCalcSqProductFlag=").append(isIfodepositNonCalcSqProductFlag());
        l_sb.append("}");
        return l_sb.toString();
    }

    /**
     * (create指数別証拠金計算条件一覧)<BR>
     * 
     * 指数別証拠金計算条件の一覧を作成し、this.指数別証拠金計算条件一覧にセットする。<BR>
     * 
     * シーケンス図<BR>
     * 「（証拠金計算条件）create指数別証拠金計算条件一覧」参照。<BR>
     * 
     * 【使用DB】<BR>
     * ・（部店指数別）取扱条件テーブル<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 
     * 補助口座オブジェクト。<BR>
     * @@roseuid 411C77200142
     */
    public void createIfoDepositCalcConditionPerIndexList(WEB3GentradeSubAccount l_subAccount)
    {

        final String STR_METHOD_NAME =
            "createIfoDepositCalcConditionPerIndexList(WEB3GentradeSubAccount)";

        Map l_map = new TreeMap();

        /*
         * 証券会社コード、部店コードに該当する
         *（部店指数別）取扱条件オブジェクトを全て取得する。
         */ 
        WEB3GentradeBranchIndexDealtCond l_handLingCondBranchIndex[] = null;

        try{

            l_handLingCondBranchIndex = WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndexList(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode());

        } catch( WEB3SystemLayerException l_ex ) {

            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                l_ex.getErrorMethod(),
                l_ex.getErrorMessage(),
                l_ex.getException());
        }

        if (l_handLingCondBranchIndex == null || l_handLingCondBranchIndex.length == 0)
        {
            log.error("（部店指数別）取扱条件が一件も取得できませんでした");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME);
        }

        BranchIndexDealtCondParams[] l_conds = new BranchIndexDealtCondParams[l_handLingCondBranchIndex.length];

        for(int i = 0; i < l_handLingCondBranchIndex.length; i++)
        {
            l_conds[i] = new BranchIndexDealtCondParams((BranchIndexDealtCondRow)l_handLingCondBranchIndex[i].getDataSourceObject());
        }

        //取得した（部店指数別）取扱条件ごとのLoop処理
        for (int i = 0; i < l_conds.length; i++)
        {
            //キー
            String l_strUnderlyingProductCode =
                l_conds[i].getTargetProductCode();
            
            //（原資産銘柄コード重複チェック)
            if (!l_map.containsKey(l_strUnderlyingProductCode))
            {
                //（部店指数別）取扱条件.規定証拠金（当日+　@０日） 
                //  != （部店指数別）取扱条件.規定証拠金（当日+　@１日）の場合
                if (l_conds[i].getIfoDepositPerUnit0()
                    != l_conds[i].getIfoDepositPerUnit1())
                {
                    //規定証拠金変更フラグをセットする。
                    this.setIfoDepositPerUnitChangeFlag(true);
                }

                //指数別証拠金計算条件オブジェクトを生成
                WEB3IfoDepositCalcConditionPerIndex l_cond =
                    new WEB3IfoDepositCalcConditionPerIndex();

                //原資産銘柄コード
                l_cond.setUnderlyingProductCode(l_strUnderlyingProductCode);

                /*
                 * 規定証拠金,規定証拠金レッド
                 */
                // 1) this.清算値速報受信フラグ == true or this.証拠金不足メール送信済フラグ == true の場合
                if ( this.quickReportReceivedFlag || this.isIfoDepositMailFlag() )
                {
                    //（部店指数別）取扱条件.規定証拠金（当日+　@１日）
                    l_cond.setIfoDepositPerUnit(
                        l_conds[i].getIfoDepositPerUnit1());
                        
                    //（部店指数別）取扱条件.規定証拠金レッド（当日+　@１日）
                    l_cond.setIfoDepositPerUnitRed(
                        l_conds[i].getIfoDepositPerUnit1Red());                        
                        
                }
                else
                {
                    //（部店指数別）取扱条件.規定証拠金（当日+　@０日）
                    l_cond.setIfoDepositPerUnit(
                        l_conds[i].getIfoDepositPerUnit0());
                        
                   //（部店指数別）取扱条件.規定証拠金レッド（当日+　@０日）
                    l_cond.setIfoDepositPerUnitRed(
                        l_conds[i].getIfoDepositPerUnit0Red());
                }

                /*
                 * 規定証拠金<証拠金不足仮確定用>
                 */
                //this.清算値速報受信済フラグ == true　@かつ、
                //this.証拠金不足メール送信済フラグ == falseの場合のみセット
                if (this.quickReportReceivedFlag &&  
                        !this.isIfoDepositMailFlag())
                {
                    //（部店指数別）取扱条件.規定証拠金（当日+　@０日）
                    l_cond.setIfoDepositPerUnitTemp(
                        l_conds[i].getIfoDepositPerUnit0());
                }

                l_map.put(l_strUnderlyingProductCode, l_cond);
  
            }
        }

        Collection l_col = l_map.values();
        WEB3IfoDepositCalcConditionPerIndex[] l_perIndexConditions =
            (WEB3IfoDepositCalcConditionPerIndex[]) l_col.toArray(
                new WEB3IfoDepositCalcConditionPerIndex[0]);
        setIfoDepositCalcPerIndexList(l_perIndexConditions);

    }
    
    /**
     * (add部店別証拠金計算条件)<BR>
     * 
     * 引数.条件項目名をキーとして引数.条件項目値をマップ(this.部店別証拠金計算条件）にセットする。<BR>
     * 
     * @@param l_strConditionName - (条件項目名)
     * @@param l_strConditionValue - (条件項目値)
     */
    public void addCalcConditionPerBranch(String l_strConditionName, String l_strConditionValue) 
    {
        calcConditionPerBranch.put(l_strConditionName, l_strConditionValue);
    }

    // private methods ---------------------------------------------------------

    /**
     * (find指数別証拠金計算条件)<BR>
     * 
     * 指定した原資産コードの指数別証拠金計算条件が存在か検索する。<BR>
     * 存在しない場合は、<code>null</code>を返す。
     * 
     * @@param l_strUnderlyingProductCode 原資産コード<BR>
     * @@return 指数別証拠金計算条件<BR>
     */
    private WEB3IfoDepositCalcConditionPerIndex findCalcConditionPerIndex(String l_strUnderlyingProductCode)
    {
        WEB3IfoDepositCalcConditionPerIndex l_indexTypeCondition = null;
        for (int i = 0; i < calcConditions.length; i++)
        {
            if (calcConditions[i]
                .getUnderlyingProductCode()
                .equals(l_strUnderlyingProductCode))
            {
                l_indexTypeCondition = calcConditions[i];
                break;
            }
        }
        return l_indexTypeCondition;
    }

    /**
     * (is証拠金不足メール送信済)<BR>
     * 
     * this.証拠金不足メール送信済フラグの値を返却する。<BR>
     * @@return boolean
     */
    public boolean isIfoDepositMailFlag()
    {
        return this.IfoDepositMailFlag;
    }

    /**
     * (is清算値速報受信済)<BR>
     * 
     * this.清算値速報受信済フラグの値を返却する。<BR>
     * @@return boolean
     */
    public boolean isQuickReportReceived()
    {
        return this.quickReportReceivedFlag;
    }

    /**
     * (is証拠金不足額非管理)<BR>
     * 
     * this.証拠金不足額非管理フラグの値を返却する。<BR>
     * @@return boolean
     */
    public boolean isLackChargeNonManagement()
    {
        return this.lackChargeNonManagementFlag;
    }
   
    /**
     * (is夕場実施)<BR>
     * 
     * this.夕場実施フラグの値を返却する。<BR>
     * @@return boolean
     */
    public boolean isEveningSessionEnforcemented()
    {
        return this.eveningSessionFlag;
    }  
   
    /**
     * (is規定証拠金変更)<BR>
     * 
     * this.規定証拠金変更フラグの値を返却する。<BR>
     * @@return boolean
     */
    public boolean isIfoDepositPerUnitChangeFlag()
    {
        return this.IfoDepositPerUnitChangeFlag;
    }

    /**
     * (is証拠金SQ日銘柄ポジション非計上)<BR>
     * 
     * this.証拠金SQ日銘柄ポジション非計上の値を返却する。<BR>
     * @@return boolean
     */
    public boolean isIfodepositNonCalcSqProductFlag()
    {
        return this.ifodepositNonCalcSqProductFlag;
    }

    /**
     * (set証拠金不足メール送信済フラグ)<BR>
     * 
     * 引数.証拠金不足メール送信済フラグをthis.証拠金不足メール送信済フラグにセットする。<BR>
     * @@param l_ifoDepositMailFlagFlag - (証拠金不足メール送信済フラグ)<BR>
     */
    public void setIfoDepositMailFlag(boolean l_ifoDepositMailFlagFlag)
    {
        this.IfoDepositMailFlag = l_ifoDepositMailFlagFlag;
    }

    /**
     * (set規定証拠金変更フラグ)<BR>
     * 
     * 引数.規定証拠金変更フラグをthis.規定証拠金変更フラグにセットする。<BR>
     * @@param l_ifoDepositPerUnitChangeFlag - (規定証拠金変更フラグ)<BR>
     */
    public void setIfoDepositPerUnitChangeFlag(boolean l_ifoDepositPerUnitChangeFlag)
    {
        IfoDepositPerUnitChangeFlag = l_ifoDepositPerUnitChangeFlag;
    }

    /**
     * (set清算値速報受信フラグ)<BR>
     * 
     * 引数.清算値速報受信フラグをthis.清算値速報受信フラグにセットする。<BR>
     * @@param l_QuickReportReceivedFlag - (清算値速報受信済フラグ)<BR>
     */
    public void setQuickReportReceivedFlag(boolean l_QuickReportReceivedFlag)
    {
        this.quickReportReceivedFlag = l_QuickReportReceivedFlag;
    }

    /**
     * (set夕場実施フラグ)<BR>
     * 
     * 引数.夕場実施フラグをthis.夕場実施フラグにセットする。<BR>
     * @@param l_EveningSessionFlag - (夕場実施フラグ)<BR>
     */
    public void setEveningSessionFlag(boolean l_EveningSessionFlag)
    {
        this.eveningSessionFlag = l_EveningSessionFlag;
    }

    /**
     * (set証拠金不足額非管理フラグ)<BR>
     * 
     * 引数.証拠金不足額非管理フラグをthis.証拠金不足額非管理フラグにセットする。<BR>
     * @@param l_ClosingPriceUpdateFlag - (当日終値更新フラグ)<BR>
     */
    public void setLackChargeNonManagementFlag(boolean l_LackChargeNonManagementFlag)
    {
        this.lackChargeNonManagementFlag = l_LackChargeNonManagementFlag;        
    }
    
    /**
     * (set証拠金SQ日銘柄ポジション非計上)<BR>
     * 
     * 引数.証拠金SQ日銘柄ポジション非計上を、this.証拠金SQ日銘柄ポジション非計上にセットする。<BR>
     * @@param l_ifodepositNonCalcSqProductFlag - (証拠金SQ日銘柄ポジション非計上)<BR>
     */
    public void setIfodepositNonCalcSqProductFlag(boolean l_ifodepositNonCalcSqProductFlag)
    {
        this.ifodepositNonCalcSqProductFlag = l_ifodepositNonCalcSqProductFlag;        
    }

}
@
