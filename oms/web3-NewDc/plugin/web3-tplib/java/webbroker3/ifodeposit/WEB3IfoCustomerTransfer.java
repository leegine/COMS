head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoCustomerTransfer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP顧客移動明細クラス(WEB3IfoCustomerTransfer.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/21 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.util.WEB3DateUtility;

/**
 * (先物OP顧客移動明細)<BR>
 * 顧客移動明細を表すクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoCustomerTransfer
{

    /**
     * 残高[T+0..2]
     */
    private double[] balances;

    /**
     * (振替額[T+0])<BR>
     * 
     * 振替額[T+0]。<BR>
     * 証拠金口座への振替はプラス、<BR>
     * 預り金口座への振替はマイナスとする。<BR>
     */
    private double currentBizDateTransferAmount = 0;

    /**
     * (振替額[T+1]。)<BR>
     * 
     * 証拠金口座への振替はプラス、<BR>
     * 預り金口座への振替はマイナスとする。<BR>
     */
    private double nextBizDateTransferAmount = 0;

    /**
     * 先物決済損益[T+1]。
     */
    private double nextBizDateFuturesCloseProfitLoss = 0;

    /**
     * 先物決済損益[T+2]。
     */
    private double next2BizDateFuturesCloseProfitLoss = 0;

    /**
     * オプション受渡代金[T+1]。
     */
    private double nextBizDateOptionNetAmount = 0;

    /**
     * オプション受渡代金[T+2]。
     */
    private double next2BizDateOptionNetAmount = 0;

    /**
     * オプション買建概算受渡代金[T+1]。
     */
    private double nextBizDateOptionBuyEstimatedNetAmount = 0;

    /**
     * オプション買建概算受渡代金[T+2]。
     */
    private double next2BizDateOptionBuyEstimatedNetAmount = 0;
    
    /**
     * 入金額[T+0]。<BR>
     * 
     *T+0に証拠金口座への振替を行った金額。<BR>
     */
    private double currentBizDateCashinAmount = 0;
    
    /**
     * 入金額[T+1]。<BR>
     * 
     *T+1に証拠金口座への振替を行った金額。<BR>
     */
    private double nextBizDateCashinAmount = 0;
    
    /**
     * 出金額[T+0]。<BR>
     * 
     *T+0に預り金口座への振替を行った金額。<BR>
     */
    private double currentBizDateCashoutAmount = 0;
    
    /**
     * 出金額[T+1]。<BR>
     * 
     *T+1に預り金口座への振替を行った金額。<BR>
     */
    private double nextBizDateCashoutAmount = 0;

    public WEB3IfoDepositCalc theWEB3IfoDepositCalc;

    /**
     * (先物OP顧客移動明細)<BR>
     * 
     * コンストラクタ。<BR>
     * @@roseuid 4158C516026E
     */
    public WEB3IfoCustomerTransfer()
    {

    }

    /**
     * (set残高[T+0..2])<BR>
     * 
     * 引数.残高[T+0..2]をthis.残高[T+0..2]にセットする。<BR>
     * @@param l_balance - (残高[T+0..2])<BR>
     * 
     * 残高[T+0]<BR>
     * 残高[T+1]<BR>
     * 残高[T+2]<BR>
     * の配列。<BR>
     * @@roseuid 41132B7301BE
     */
    public void setBalances(double[] l_balances)
    {
        balances = l_balances;
    }

    /**
     * (add先物決済損益[T+1])<BR>
     * 
     * this.先物決済損益[T+1]に引数.先物決済損益[T+1]を加算する。<BR>
     * 
     * this.先物決済損益[T+1] = this.先物決済損益[T+1] + 引数.先物決済損益[T+1] <BR>
     * @@param l_dblNextBizDateFutureCloseProfitLoss - 先物決済損益[T+1]。
     * @@roseuid 41132D8501AE
     */
    public void addNextBizDateFuturesCloseProfitLoss(double l_dblNextBizDateFutureCloseProfitLoss)
    {
        nextBizDateFuturesCloseProfitLoss
            += l_dblNextBizDateFutureCloseProfitLoss;
    }

    /**
     * (add先物決済損益[T+2])<BR>
     * 
     * this.先物決済損益[T+2]に引数.先物決済損益[T+2]を加算する。<BR>
     * 
     * this.先物決済損益[T+2] = this.先物決済損益[T+2] + 引数.先物決済損益[T+2] <BR>
     * @@param l_dblNext2BizDateFutureCloseProfitLoss - 先物決済損益[T+2]。
     */
    public void addNext2BizDateFuturesCloseProfitLoss(double l_dblNext2BizDateFutureCloseProfitLoss)
    {
        next2BizDateFuturesCloseProfitLoss
            += l_dblNext2BizDateFutureCloseProfitLoss;
    }

    /**
     * (addオプション受渡代金[T+1])<BR>
     * 
     * this.オプション受渡代金[T+1]に引数.オプション受渡代金[T+1]を加算する。<BR>
     * 
     * this.オプション受渡代金[T+1] = this.オプション受渡代金[T+1] + 
     * 引数.オプション受渡代金[T+1]<BR>
     * @@param l_dblNextOptionNetAmount - オプション受渡代金[T+1]。
     * @@roseuid 41132DAB01BE
     */
    public void addNextBizDateOptionNetAmount(double l_dblNextOptionNetAmount)
    {
        nextBizDateOptionNetAmount += l_dblNextOptionNetAmount;
    }

    /**
     * (addオプション受渡代金[T+2])<BR>
     * 
     * this.オプション受渡代金[T+2]に引数.オプション受渡代金[T+2]を加算する。<BR>
     * 
     * this.オプション受渡代金[T+2] = this.オプション受渡代金[T+2] + 
     * 引数.オプション受渡代金[T+2]<BR>
     * @@param l_dblNext2OptionNetAmount - オプション受渡代金[T+2]。
     */
    public void addNext2BizDateOptionNetAmount(double l_dblNext2OptionNetAmount)
    {
        next2BizDateOptionNetAmount += l_dblNext2OptionNetAmount;
    }


    /**
     * (addオプション買建概算受渡代金[T+1])<BR>
     * 
     * this.オプション買建概算受渡代金[T+1]に引数.買建オプション概算受渡代金[T+1]を加算
     * する。<BR>
     * 
     * this.オプション買建概算受渡代金[T+1] = this.オプション買建概算受渡代金[T+1] + 
     * 引数.オプション買建概算受渡代金[T+1]<BR>
     * @@param l_dblNextBizDateOptionBuyEstimateNetAmount - 
     * オプション買建概算受渡代金[T+1]
     * @@roseuid 41132DCB03A2
     */
    public void addNextBizDateOptionBuyEstimatedNetAmount(double l_dblNextBizDateOptionBuyEstimatedNetAmount)
    {
        nextBizDateOptionBuyEstimatedNetAmount
            += l_dblNextBizDateOptionBuyEstimatedNetAmount;
    }

    /**
     * (addオプション買建概算受渡代金[T+2])<BR>
     * 
     * this.オプション買建概算受渡代金[T+2]に引数.オプション買建概算受渡代金[T+2]を加算
     * する。<BR>
     * 
     * this.オプション買建概算受渡代金[T+2] = this.オプション買建概算受渡代金[T+2] + 
     * 引数.オプション買建概算受渡代金[T+2]<BR>
     * @@param l_dblNext2BizDateOptionBuyEstimatedNetAmount - 
     * オプション買建概算受渡代金[T+2]
     * @@roseuid 4119BDEF034E
     */
    public void addNext2BizDateOptionBuyEstimatedNetAmount(double l_dblNext2BizDateOptionBuyEstimatedNetAmount)
    {
        next2BizDateOptionBuyEstimatedNetAmount
            += l_dblNext2BizDateOptionBuyEstimatedNetAmount;
    }

    /**
     * (get残高)<BR>
     * 
     * 引数で指定された指定日(=n)に対応する残高を返却する。<BR>
     * 
     * １）　@引数の指定日チェックを行う。<BR>
     * 　@　@　@指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。<BR>
     * 
     * ２）　@残高の取得を行う。<BR>
     * 　@　@　@n == 0の場合、this.残高[T+0..2][0]を返却する。<BR>
     * 　@　@　@n == 1の場合、this.残高[T+0..2][1]を返却する。<BR>
     * 　@　@　@n == 2の場合、this.残高[T+0..2][2]を返却する。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     * @@roseuid 41132BB803B2
     */
    public double getBalance(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 0 :
                return balances[0];
            case 1 :
                return balances[1];
            case 2 :
                return balances[2];
            default :
                return 0.0;
        }
    }

    /**
     * (get振替額)<BR>
     * 
     * 引数で指定された指定日(=n)の、「振替額」を返却する。<BR>
     * 
     * １）　@引数の指定日チェックを行う。<BR>
     * 　@　@　@指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。<BR>
     * 
     * ２）　@n == 0の場合(引数.指定日 == 0)<BR>
     * 　@　@　@this.get振替額[T+0]( )を返却する。<BR>
     * 
     * ３）　@n == 1、または2の場合(引数.指定日 != 0)<BR>
     * 　@　@　@this.get振替額[T+0]( )<BR>
     *       + this.get振替額[T+1]( )を返却する。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double
     * @@roseuid 414047F6038F
     */
    public double getTransferAmount(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 0 :
                return getCurrentBizDateTransferAmount();
            case 1 :
            case 2 :
                return (
                    getCurrentBizDateTransferAmount()
                        + getNextBizDateTransferAmount());
            default :
                return 0.0;
        }
    }

    /**
     * (get振替額[T+0])<BR>
     * 
     * this.振替額[T+0]を返却する。<BR>
     * @@return double
     * @@roseuid 412C79A402EC
     */
    public double getCurrentBizDateTransferAmount()
    {
        return currentBizDateTransferAmount;
    }

    /**
     * (get振替額[T+1])<BR>
     * 
     * this.振替額[T+1]を返却する。<BR>
     * @@return double
     * @@roseuid 414047B000EE
     */
    public double getNextBizDateTransferAmount()
    {
        return nextBizDateTransferAmount;
    }

    /**
     * (get先物決済損益)<BR>
     * 
     * 引数で指定された指定日(=n)の、「先物決済損益」を返却する。<BR>
     * 
     * １）　@引数の指定日チェックを行う。<BR>
     * 　@　@　@指定日が範囲外の場合(nが1以上2以下でない場合)、0を返却する。<BR>
     * 
     * ２）　@n == 1の場合 <BR>
     *       this.get先物決済損益[T+1]( )を返却する。<BR> 
     *
     * ３）　@n == 2の場合 <BR>
     *       this.get先物決済損益[T+1]( )+this.get先物決済損益[T+2]( )を返却する。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 1、2のいずれかの値。<BR>
     * @@return double
     * @@roseuid 411089CC02D6
     */
    public double getFuturesCloseProfitLoss(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 1 :
                return getNextBizDateFuturesCloseProfitLoss();
            case 2 :
                return (getNextBizDateFuturesCloseProfitLoss() + 
                          getNext2BizDateFuturesCloseProfitLoss());
            default :
                return 0.0;
        }
    }

    /**
     * (get先物決済損益[T+1])<BR>
     * 
     * this.先物決済損益[T+1]を返却する。<BR>
     * @@return double
     * @@roseuid 412C78C70078
     */
    public double getNextBizDateFuturesCloseProfitLoss()
    {
        return nextBizDateFuturesCloseProfitLoss;
    }

    /**
     * (get先物決済損益[T+2])<BR>
     * 
     * this.先物決済損益[T+2]を返却する。<BR>
     * @@return double
     */
    public double getNext2BizDateFuturesCloseProfitLoss()
    {
        return next2BizDateFuturesCloseProfitLoss;
    }

    /**
     * (getオプション受渡代金)<BR>
     * 
     * 引数で指定された指定日(=n)の、「オプション受渡代金」を返却する。<BR>
     * 
     * １）　@引数の指定日チェックを行う。<BR>
     * 　@　@　@指定日が範囲外の場合(nが1以上2以下でない場合)、0を返却する。<BR>
     * 
     * ２）　@n == 1の場合 <BR>
     *     　@this.getオプション受渡代金[T+1]( )を返却する。 <BR>
     *
     * ３）　@n == 2の場合 <BR>
     *     　@this.getオプション受渡代金[T+1]( )　@+　@this.getオプション受渡代金[T+2]( )を返却する。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 1、2のいずれかの値。<BR>
     * @@return double
     * @@roseuid 412C79440349
     */
    public double getOptionNetAmount(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 1 :
                return getNextBizDateOptionNetAmount();
            case 2 :
                return (getNextBizDateOptionNetAmount() + 
                          getNext2BizDateOptionNetAmount());
            default :
                return 0.0;
        }
    }

    /**
     * (getオプション受渡代金[T+1])<BR>
     * 
     * this.オプション受渡代金[T+1]を返却する。<BR>
     * @@return double
     * @@roseuid 41132C9B0085
     */
    public double getNextBizDateOptionNetAmount()
    {
        return nextBizDateOptionNetAmount;
    }

    /**
     * (getオプション受渡代金[T+2])<BR>
     * 
     * this.オプション受渡代金[T+2]を返却する。<BR>
     * @@return double
     */
    public double getNext2BizDateOptionNetAmount()
    {
        return next2BizDateOptionNetAmount;
    }

    /**
     * (getオプション買建概算受渡代金)<BR>
     * 
     * 引数で指定された指定日(=n)の、「オプション買建概算受渡代金」を返却する。<BR>
     * 
     * １）　@引数の指定日チェックを行う。<BR>
     * 　@　@　@指定日が範囲外の場合(nが1以上2以下でない場合)、0を返却する。<BR>
     * 
     * ２）　@n == 1の場合(引数.指定日 == 1)<BR>
     * 　@　@　@　@this.getオプション買建概算受渡代金[T+1]( )を返却する。<BR>
     * 
     * ３）　@n == 2の場合(引数.指定日 == 2)<BR>
     * 　@　@　@　@this.getオプション買建概算受渡代金[T+1]( )<BR>
     *       + this.getオプション買建概算受渡代金[T+2]( )を返却する。<BR>
     * @@param l_indReservedDate - (指定日)<BR>
     * 1、2のいずれかの値。<BR>
     * @@return double
     * @@roseuid 412C6A2600C2
     */
    public double getOptionBuyEstimatedNetAmount(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 1 :
                return getNextBizDateOptionBuyEstimatedNetAmount();
            case 2 :
                return (
                    getNextBizDateOptionBuyEstimatedNetAmount()
                        + getNext2BizDateOptionBuyEstimatedNetAmount());
            default :
                return 0.0;
        }
    }

    /**
     * (getオプション買建概算受渡代金[T+1])<BR>
     * 
     * this.オプション買建概算受渡代金[T+1]を返却する。<BR>
     * @@return double
     * @@roseuid 41132CB70373
     */
    public double getNextBizDateOptionBuyEstimatedNetAmount()
    {
        return nextBizDateOptionBuyEstimatedNetAmount;
    }

    /**
     * (getオプション買建概算受渡代金[T+2])<BR>
     * 
     * this.オプション買建概算受渡代金[T+2]を返却する。<BR>
     * @@return double
     * @@roseuid 4118604801D0
     */
    public double getNext2BizDateOptionBuyEstimatedNetAmount()
    {
        return next2BizDateOptionBuyEstimatedNetAmount;
    }
    
    /**
     * (add入金額)<BR>
     * 
     * 受渡日がいつであるかにもとづき、該当する入金額の加算処理を行う。<BR>
     * 
     * 証拠金口座への振替の場合(引数.注文種別 == 
     * (”振替注文（預り金から株先証拠金）”）)のみ、<BR>
     * 以下の振替額の加算を行う。<BR>
     * 
     * ○当日振替の場合(引数.受渡日 == 引数.営業日[T+0])<BR>
     * 　@　@this.入金額[T+0] += 引数.振替額<BR>
     * 
     * ○翌日振替の場合(引数.受渡日 != 引数.営業日[T+0])<BR>
     * 　@　@this.入金額[T+1] += 引数.振替額<BR>
     * @@param l_orderType - (注文種別)
     * @@param l_dblTransferAmount - 振替額。
     * @@param l_datDeliveryDate - 受渡日。
     * @@param l_datCurrentBizDate - 営業日[T+0]。
     */
    public void addCashinAmount(
        OrderTypeEnum l_orderType,
        double l_dblTransferAmount,
        Date l_datDeliveryDate,
        Date l_datCurrentBizDate)
    {
        if (OrderTypeEnum
            .FROM_DEPOSIT_AMOUNT_MARGIN
            .equals(l_orderType))
        {
            if (WEB3DateUtility
                .compareToDay(l_datDeliveryDate, l_datCurrentBizDate)
                == 0)
            {
                currentBizDateCashinAmount += l_dblTransferAmount;
            } else
            {
                nextBizDateCashinAmount += l_dblTransferAmount;
            }
        }
    }
    
    /**
     * (add出金額)<BR>
     * 
     * 受渡日がいつであるかにもとづき、該当する出金額の加算処理を行う。<BR>
     * 
     * 預り金口座への振替の場合(引数.注文種別 == 
     * (”振替注文（株先証拠金から預り金）”）)のみ、<BR>
     * 以下の振替額の減算を行う。<BR>
     * 
     * ○当日振替の場合(引数.受渡日 == 引数.営業日[T+0])<BR>
     * 　@　@this.出金額[T+0] += 引数.振替額<BR>
     * 
     * ○翌日振替の場合(引数.受渡日 != 引数.営業日[T+0])<BR>
     * 　@　@this.出金額[T+1] += 引数.振替額<BR>
     * @@param l_orderType - (注文種別)
     * @@param l_dblTransferAmount - 振替額。
     * @@param l_datDeliveryDate - 受渡日。
     * @@param l_datCurrentBizDate - 営業日[T+0]。
     */
    public void addCashoutAmount(
        OrderTypeEnum l_orderType,
        double l_dblTransferAmount,
        Date l_datDeliveryDate,
        Date l_datCurrentBizDate)
    {
        if (OrderTypeEnum
            .MARGIN_FROM_DEPOSIT_AMOUNT
            .equals(l_orderType))
        {
            if (WEB3DateUtility
                .compareToDay(l_datDeliveryDate, l_datCurrentBizDate)
                == 0)
            {
                currentBizDateCashoutAmount += l_dblTransferAmount;
            } else
            {
                nextBizDateCashoutAmount += l_dblTransferAmount;
            }
        }
    }
    
    /**
     * (get入金額[T+0])<BR>
     * 
     * this.入金額[T+0]を返却する。<BR>
     * @@return double
     */
    public double getCurrentBizDateCashinAmount()
    {
        return currentBizDateCashinAmount;
    }
    
    /**
     * (get入金額[T+1])<BR>
     * 
     * this.入金額[T+1]を返却する。<BR>
     * @@return double
     */
    public double getNextBizDateCashinAmount()
    {
        return nextBizDateCashinAmount;
    }
    
    /**
     * (get出金額[T+0])<BR>
     * 
     * this.出金額[T+0]を返却する。<BR>
     * @@return double
     */
    public double getCurrentBizDateCashoutAmount()
    {
        return currentBizDateCashoutAmount;
    }
    
    /**
     * (get出金額[T+1])<BR>
     * 
     * this.出金額[T+1]を返却する。<BR>
     * @@return double
     */
    public double getNextBizDateCashoutAmount()
    {
        return nextBizDateCashoutAmount;
    }
    
    /**
     * (set振替額[T+0])<BR>
     * 
     * 引数.振替額[T+0]をthis.振替額[T+0]にセットする。<BR>
     * @@param l_dblCurrentBizDateTransferAmount - (振替額[T+0]。)<BR>
     */
    public void setCurrentBizDateTransferAmount(double l_dblCurrentBizDateTransferAmount)
    {
        currentBizDateTransferAmount = l_dblCurrentBizDateTransferAmount;
    }
    
    /**
     * (set振替額[T+1])<BR>
     * 
     * 引数.振替額[T+1]をthis.振替額[T+1]にセットする。<BR>
     * @@param l_dblNextBizDateTransferAmount - (振替額[T+1]。)<BR>
     */
    public void setNextBizDateTransferAmount(double l_dblNextBizDateTransferAmount)
    {
        nextBizDateTransferAmount = l_dblNextBizDateTransferAmount;
    }
    
}
@
