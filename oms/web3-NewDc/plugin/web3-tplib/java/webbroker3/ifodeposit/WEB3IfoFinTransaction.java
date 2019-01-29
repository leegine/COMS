head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoFinTransaction.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP保有建玉取引勘定明細クラス(WEB3IfoFinTransaction.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/12 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;

/**
 * (先物OP保有建玉取引勘定明細)<BR>
 * 建玉ごとの取引勘定明細を表すクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoFinTransaction
{

    /**
     * (トランザクションID)
     */
    public long finTransactionId;

    /**
     * (トランザクションタイプ)
     */
    public FinTransactionType finTransactionType;

    /**
     * トランザクションカテゴリ。
     */
    public FinTransactionCateg finTransactionCateg;

    /**
     * (注文単位ID)
     */
    public long orderUnitId;

    /**
     * (受渡日)
     */
    public Date deliveryDate;

    /**
     * (受渡代金)<BR>
     * ※オプション買建（新規買建、売建買返済)の場合はマイナス値がセットされる。<BR>
     */
    public double netAmount = 0;

    /**
     * (The先物OP保有建玉情報）<BR>
     */
    public WEB3IfoContract web3IfoContract;

    /**
     * @@roseuid 416120D8000D
     */
    public WEB3IfoFinTransaction()
    {
    }

    /**
     * (create先物OP保有建玉取引勘定明細)<BR>
     * 
     * 先物OP保有建玉取引勘定明細を生成する。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoFinTransaction
     * @@roseuid 4121CBC402A7
     */
    public static WEB3IfoFinTransaction create()
    {
        return new WEB3IfoFinTransaction();
    }

    /**
     * (is先物 )<BR>
     * 
     * 該当取引が先物であるかを判定する。<BR>
     * this.トランザクションカテゴリ== 
     * （”先物新規取引”、または、”先物返済取引”）の場合、<BR>
     * trueを返却する。以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4160E3D70174
     */
    public boolean isFutures()
    {
        switch (finTransactionCateg.intValue())
        {
            case (FinTransactionCateg.IntValues.EQTYPE_IDX_FUTURES_OPEN) :
            case (FinTransactionCateg.IntValues.EQTYPE_IDX_FUTURES_CLOSE) :
                return true;
            default :
                return false;
        }
    }
    /**
     * 受渡日を取得する。
     * 
     * @@return　@受渡日
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * トランザクションカテゴリを取得する。
     * 
     * @@return　@トランザクションカテゴリ
     */
    public FinTransactionCateg getFinTransactionCateg()
    {
        return finTransactionCateg;
    }

    /**
     * トランザクションIDを取得する。
     * 
     * @@return　@トランザクションID
     */
    public long getFinTransactionId()
    {
        return finTransactionId;
    }

    /**
     * トランザクションタイプを取得する。
     * 
     * @@return　@トランザクションタイプ
     */
    public FinTransactionType getFinTransactionType()
    {
        return finTransactionType;
    }

    /**
     * 受渡代金を取得する。
     * 
     * @@return　@受渡代金
     */
    public double getNetAmount()
    {
        return netAmount;
    }

    /**
     * 注文単位IDを取得する。
     * 
     * @@return　@注文単位ID
     */
    public long getOrderUnitId()
    {
        return orderUnitId;
    }

    /**
     * 先物OP保有建玉情報を取得する。
     * 
     * @@return　@先物OP保有建玉情報
     */
    public WEB3IfoContract getWEB3IfoContract()
    {
        return web3IfoContract;
    }

    /**
     * 受渡日を設定する。
     * 
     * @@param l_datDeliveryDate　@受渡日
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * トランザクションカテゴリを設定する。
     * 
     * @@param l_finTransactionCateg　@トランザクションカテゴリ
     */
    public void setFinTransactionCateg(FinTransactionCateg l_finTransactionCateg)
    {
        finTransactionCateg = l_finTransactionCateg;
    }

    /**
     * トランザクションIDを設定する。
     * 
     * @@param l_finTransactionId　@トランザクションID
     */
    public void setFinTransactionId(long l_finTransactionId)
    {
        finTransactionId = l_finTransactionId;
    }

    /**
     * トランザクションタイプを設定する。
     * 
     * @@param l_finTransactionType　@トランザクションタイプ
     */
    public void setFinTransactionType(FinTransactionType l_finTransactionType)
    {
        finTransactionType = l_finTransactionType;
    }

    /**
     * 受渡代金を設定する。
     * 
     * @@param l_dblNetAmount　@受渡代金
     */
    public void setNetAmount(double l_dblNetAmount)
    {
        netAmount = l_dblNetAmount;
    }

    /**
     * 注文単位IDを設定する。
     * 
     * @@param l_lngOrderUnitId　@注文単位ID
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        orderUnitId = l_lngOrderUnitId;
    }

    /**
     * 先物OP保有建玉情報を設定する。
     * 
     * @@param l_web3IfoContract　@先物OP保有建玉情報
     */
    public void setWEB3IfoContract(WEB3IfoContract l_web3IfoContract)
    {
        web3IfoContract = l_web3IfoContract;
    }
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoFinTransaction={");
        l_sb.append("finTransactionId=").append(getFinTransactionId());
        l_sb.append(",finTransactionType=").append(getFinTransactionType());
        l_sb.append(",finTransactionCateg=").append(getFinTransactionCateg());
        l_sb.append(",orderUnitId=").append(getOrderUnitId());
        l_sb.append(",deliveryDate=").append(getDeliveryDate());
        l_sb.append(",netAmount=").append(getNetAmount());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
