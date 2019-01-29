head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP保有建玉情報クラス(WEB3IfoContract.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/12 山田　@卓司(FLJ) 新規作成
 Revision History : 2007/07/06 hijikata(SRA)  夕場対応 モデルNo.061③, No.068, No.071
 Revision History : 2007/08/02 hijikata(SRA)  夕場対応 モデルNo.100, No.102
 Revision History : 2007/08/13 k.yamashita(SRA)  夕場対応 U03048,U03049
 
 */
package webbroker3.ifodeposit;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;

/**
 * (先物OP保有建玉情報)<BR>
 * 既存建・当日建の建玉の情報を表すクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoContract
{
    
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoContract.class);

    /**
     * (建玉ID)
     */
    public long contractId;

    /**
     * (銘柄ID)
     */
    public long productId;

    /**
     * 市場ID。
     */
    public long marketId;

    /**
     * (建区分)<BR>
     * 1：買建<BR>
     * 2：売建<BR>
     */
    public ContractTypeEnum contractType;

    /**
     * (建日)
     */
    public Date openDate;

    /**
     * 建単価。
     */
    public double contractPrice;

    /**
     * 建玉数量。（リアル残)<BR>
     */
    public double quantity = 0;

    /**
     * 先物OP保有建玉取引勘定明細リスト
     */
    public List ifoFinTransactionList;

    /**
     * 立会区分
     */
    public String sessionType;

    /**
     * 建玉数量（証拠金不足仮確定)
     */
    public double quantityTemp = 0;

    /**
     * @@roseuid 4158CAED0220
     */
    public WEB3IfoContract()
    {
        ifoFinTransactionList = new ArrayList();
    }

    //  Public methods ----------------------------------------------------------

    /**
     * (create先物OP保有建玉情報)<BR>
     * 
     * 先物OP保有建玉情報を生成する。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoContract
     * @@roseuid 4112EBD60187
     */
    public WEB3IfoContract create()
    {
        return new WEB3IfoContract();
    }

    /**
     * (add先物OP保有建玉取引勘定明細)<BR>
     * 
     * 先物OP保有建玉取引勘定明細を作成し、this.先物OP保有建玉取引勘定明細一覧に追加する<BR>
     * 
     * 
     * <BR>
     * 
     * １）　@オブジェクトの生成<BR>
     * 　@create先物OP保有建玉取引勘定明細( 
     * )により先物OP保有建玉情報オブジェクトを生成する。<BR>
     * 
     * ２）　@プロパティのセット<BR>
     * 　@生成した先物OP保有建玉取引勘定明細に下記プロパティセットを行う。<BR>
     * 　@
     * 　@・トランザクションID：　@引数.トランザクションParams.トランザクションID<BR>
     * 　@・トランザクションタイプ：　@引数.トランザクションParams.トランザクションタイプ<BR>
     * 　@・トランザクションカテゴリ：　@引数.トランザクションParams.トランザクションカテゴリ<BR>
     * 　@・注文単位ID：　@引数.トランザクションParams.注文単位ID<BR>
     * 　@・受渡日：　@引数トランザクションParams..受渡日<BR>
     * 　@・受渡代金：　@引数.トランザクションParams.受渡代金<BR>
     * 　@　@　@
     * ３）　@オブジェクトの追加<BR>
     * 　@this.先物OP保有建玉取引勘定明細一覧.add(
     *       プロパティセットした先物OP保有建玉取引勘定明細)により、
     *   先物OP保有建玉取引勘定明細を追加する。<BR>
     * 
     * @@param l_transactionParams - (トランザクションParams)
     * @@roseuid 41220927031C
     */
    public void addIfoFinTransaction(IfoFinTransactionParams l_transactionParams)
    {
        WEB3IfoFinTransaction l_transaction = WEB3IfoFinTransaction.create();
        l_transaction.setFinTransactionId(
            l_transactionParams.getFinTransactionId());
        l_transaction.setFinTransactionType(
            l_transactionParams.getFinTransactionType());
        l_transaction.setFinTransactionCateg(
            l_transactionParams.getFinTransactionCateg());
        l_transaction.setOrderUnitId(l_transactionParams.getOrderUnitId());
        l_transaction.setDeliveryDate(l_transactionParams.getDeliveryDate());
        l_transaction.setNetAmount(l_transactionParams.getNetAmount());
        ifoFinTransactionList.add(l_transaction);
        
        log.debug("Added IfoFinTransaction : " + l_transaction.toString());
        
    }

    /**
     * (get先物OP保有建玉情報)<BR>
     * 
     * （staticメソッド）<BR>
     * 引数の建玉Paramsより、先物OP保有建玉情報オブジェクトを作成する。<BR>
     * 
     * １）　@オブジェクトの生成<BR>
     * 　@this.create先物OP保有建玉情報( 
     * )により先物OP保有建玉情報オブジェクトを生成する。<BR>
     * 
     * ２）　@プロパティのセット<BR>
     * 　@生成した先物OP保有建玉情報に下記プロパティセットを行う。<BR>
     * 　@
     * 　@・建玉ID：　@引数.建玉Params.建玉ID<BR>
     * 　@・銘柄ID：　@引数.建玉Params.銘柄ID<BR>
     * 　@・市場ID：　@引数.建玉Params.市場ID<BR>
     * 　@・建区分：　@引数.建玉Params.建区分<BR>
     * 　@・建日：　@引数.建玉Params.建日<BR>
     * 　@・建単価：　@引数.建玉Params.建単価<BR>
     * 　@・建玉数量：　@引数.建玉Params.建玉元数量<BR>
     *   ・建玉数量＜証拠金不足仮確定＞：　@引数.建玉Params.建玉元数量(*) <BR>
     *   ・立会区分：　@引数.建玉Params.立会区分<BR>
     *   ・先物OP保有建玉取引勘定明細一覧： ArrayListを新たに生成してセット<BR>
     * 
     *    (*)ただし、引数.発注日==引数.建玉Params.建日　@かつ 
     *       引数.建玉Params.立会区分==”夕場”の場合、 
     *       ZEROをセット。 
     * 
     * ３）　@プロパティセットした先物OP保有建玉情報を返却する。
     * 　@
     * 
     * @@param l_contractParams - 建玉Params。
     * @@param l_datBizDate - 発注日。
     * @@return webbroker3.ifodeposit.WEB3IfoContract
     * @@roseuid 413ED3430205
     */
    public static WEB3IfoContract getIfoContract(IfoContractParams l_contractParams,
        Date l_datBizDate)
    {
        WEB3IfoContract l_contract = new WEB3IfoContract();
        l_contract.setContractId(l_contractParams.getContractId());
        l_contract.setProductId(l_contractParams.getProductId());
        l_contract.setMarketId(l_contractParams.getMarketId());
        l_contract.setContractType(l_contractParams.getContractType());
        l_contract.setOpenDate(l_contractParams.getOpenDate());
        l_contract.setContractPrice(l_contractParams.getContractPrice());
        l_contract.setQuantity(l_contractParams.getOriginalQuantity());

        String l_strSessionType = l_contractParams.getSessionType();
        // 建玉数量＜証拠金不足仮確定＞
        if (WEB3SessionTypeDef.EVENING_SESSION.equals(l_strSessionType) &&
                WEB3DateUtility.compareToDay(l_datBizDate, 
                    l_contractParams.getOpenDate()) == 0)
        {
            // 引数.発注日==引数.建玉Params.建日 かつ 引数.建玉Params.立会区分==”夕場”の場合 
            // ZEROをセット。
            l_contract.quantityTemp = 0;
        } else {   	
            l_contract.quantityTemp = l_contractParams.getOriginalQuantity();
        }

        l_contract.sessionType = l_strSessionType;
        return l_contract;
    }

    /**
     * (get先物決済損益[T+1])<BR>
     * 
     * 受渡日がT+1の先物決済損益を取得する。<BR>
     * (受渡日がT+1の先物決済損益が存在しない場合は、0)<BR>
     * 
     * １）　@戻り値となる先物決済損益を格納する変数を作成<BR>
     * 　@　@　@double 先物決済損益 = 0<BR>
     * 
     * ２）　@this.先物OP保有建玉取引勘定明細一覧要素ごとのLoop処理<BR>
     * 
     * 　@　@　@下記条件に一致する場合のみ、先物決済損益の加算を行う。<BR>
     * 
     * 　@　@　@先物決済損益 = 先物決済損益 + 先物OP保有建玉取引勘定明細.受渡代金　@　@<BR>
     * 
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.is先物 == true &&<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.受渡日 ==　@引数.受渡日<BR>
     * 
     * ３）　@先物決済損益を返却する。<BR>
     * 
     * 
     * 
     * 
     * 　@
     * 
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * @@return double
     * @@roseuid 41249AF6006F
     */
    public double getNextBizDateFuturesCloseProfitLoss(Date l_datDeliveryDate)
    {
        
        double l_dblPL = 0.0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            WEB3IfoFinTransaction l_tran = (WEB3IfoFinTransaction) l_it.next();
            if (l_tran.isFutures()
                && l_datDeliveryDate.getTime()
                    == l_tran.getDeliveryDate().getTime())
            {
                l_dblPL += l_tran.getNetAmount();
            }
        }

        log.debug(
            "Calculated Futures P/L="
                + l_dblPL
                + ",DeliveryDate = "
                + l_datDeliveryDate
                + ","
                + this.toString());
        
        return l_dblPL;
    }


    /**
     * (get先物決済損益[T+2])<BR>
     * 
     * 受渡日がT+2の先物決済損益を取得する。<BR>
     * (受渡日がT+2の先物決済損益が存在しない場合は、0)<BR>
     * 
     * １）　@戻り値となる先物決済損益を格納する変数を作成<BR>
     * 　@　@　@double 先物決済損益 = 0<BR>
     * 
     * ２）　@this.先物OP保有建玉取引勘定明細一覧要素ごとのLoop処理<BR>
     * 
     * 　@　@　@下記条件に一致する場合のみ、先物決済損益の加算を行う。<BR>
     * 
     * 　@　@　@先物決済損益 = 先物決済損益 + 先物OP保有建玉取引勘定明細.受渡代金　@　@<BR>
     * 
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.is先物 == true &&<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.受渡日 ==　@引数.受渡日<BR>
     * 
     * ３）　@先物決済損益を返却する。<BR>
     * 
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * @@return double
     */
    public double getNext2BizDateFuturesCloseProfitLoss(Date l_datDeliveryDate)
    {
        
        double l_dblNext2PL = 0.0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            WEB3IfoFinTransaction l_tran = (WEB3IfoFinTransaction) l_it.next();
            if (l_tran.isFutures()
                && l_datDeliveryDate.getTime()
                    == l_tran.getDeliveryDate().getTime())
            {
                l_dblNext2PL += l_tran.getNetAmount();
            }
        }

        log.debug(
            "Calculated Futures P/L="
                + l_dblNext2PL
                + ",DeliveryDate = "
                + l_datDeliveryDate
                + ","
                + this.toString());
        
        return l_dblNext2PL;
    }

    /**
     * (getオプション受渡代金[T+1])<BR>
     * 
     * 受渡日がT+1のオプション受渡代金を返却する。<BR>
     * (受渡日がT+1のオプション受渡代金が存在しない場合は、0)<BR>
     * 
     * １）　@戻り値となるオプション受渡代金を格納する変数を作成<BR>
     * 　@　@　@double オプション受渡代金 = 0<BR>
     * 
     * ２）　@this.先物OP保有建玉取引勘定明細一覧要素ごとのLoop処理<BR>
     * 
     * 　@　@　@下記条件に一致する場合のみ、オプション受渡代金の加算を行う。<BR>
     * 
     * 　@　@　@オプション受渡代金 = オプション受渡代金 + 
     * 先物OP保有建玉取引勘定明細.受渡代金　@　@<BR>
     * 
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.is先物 == false &&<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.受渡日 ==　@引数.受渡日<BR>
     * 
     * ３）　@オプション受渡代金を返却する。<BR>
     * 
     * 
     * 
     * 
     * 　@
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@roseuid 4124A31A0210
     */
    public double getNextBizDateOptionNetAmount(Date l_datDeliveryDate)
    {
        double l_dblNetAmount = 0.0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            WEB3IfoFinTransaction l_tran = (WEB3IfoFinTransaction) l_it.next();
            if (!l_tran.isFutures()
                && (l_tran.getDeliveryDate().getTime()
                    == l_datDeliveryDate.getTime()))
            {
                l_dblNetAmount += l_tran.getNetAmount();
            }
        }

        log.debug(
            "Calculated Options NetAmount="
                + l_dblNetAmount
                + ",DeliveryDate = "
                + l_datDeliveryDate
                + ","
                + this.toString());
        
        return l_dblNetAmount;
    }


    /**
     * (getオプション受渡代金[T+2])<BR>
     * 
     * 受渡日がT+2のオプション受渡代金を返却する。<BR>
     * (受渡日がT+2のオプション受渡代金が存在しない場合は、0)<BR>
     * 
     * １）　@戻り値となるオプション受渡代金を格納する変数を作成<BR>
     * 　@　@　@double オプション受渡代金 = 0<BR>
     * 
     * ２）　@this.先物OP保有建玉取引勘定明細一覧要素ごとのLoop処理<BR>
     * 
     * 　@　@　@下記条件に一致する場合のみ、オプション受渡代金の加算を行う。<BR>
     * 
     * 　@　@　@オプション受渡代金 = オプション受渡代金 + 
     * 先物OP保有建玉取引勘定明細.受渡代金　@　@<BR>
     * 
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.is先物 == false &&<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.受渡日 ==　@引数.受渡日<BR>
     * 
     * ３）　@オプション受渡代金を返却する。<BR>
     * 
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    public double getNext2BizDateOptionNetAmount(Date l_datDeliveryDate)
    {
        double l_dblNext2NetAmount = 0.0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            WEB3IfoFinTransaction l_tran = (WEB3IfoFinTransaction) l_it.next();
            if (!l_tran.isFutures()
                && (l_tran.getDeliveryDate().getTime()
                    == l_datDeliveryDate.getTime()))
            {
                l_dblNext2NetAmount += l_tran.getNetAmount();
            }
        }

        log.debug(
            "Calculated Options NetAmount="
                + l_dblNext2NetAmount
                + ",DeliveryDate = "
                + l_datDeliveryDate
                + ","
                + this.toString());
        
        return l_dblNext2NetAmount;
    }

    /**
     * (get受渡代金)<BR>
     * 
     * 注文に対応する受渡代金を返却する。<BR>
     * (注文に対応する受渡代金が存在しない場合は、0)<BR>
     * 
     * １）　@this.建日 != 引数.建日の場合は、0を返却し、終了する。<BR>
     * 
     * ２）　@戻り値となる受渡代金を格納する変数を作成<BR>
     * 　@　@　@double 受渡代金 = 0<BR>
     * 
     * ３）　@this.先物OP保有建玉取引勘定明細一覧要素ごとのLoop処理<BR>
     * 
     * 　@　@　@下記条件に一致する場合のみ、受渡代金の加算を行う。<BR>
     * 
     * 　@　@　@受渡代金 = 受渡代金 + 先物OP保有建玉取引勘定明細.受渡代金<BR>　@　@
     * 
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@先物OP保有建玉取引勘定明細.注文単位ID == 引数.注文単位ID<BR>
     * 
     * ４）　@受渡代金を返却する。<BR>
     * 　@
     * @@param l_lngOrderUnitId - 注文単位ID。
     * @@param l_openDate - (建日)<BR>
     * @@return double
     * @@roseuid 4129C61F0176
     */
    public double getNetAmount(long l_lngOrderUnitId, Date l_datOpenDate)
    {
        double l_dblNetAmount = 0.0;
        if (WEB3DateUtility.compareToDay(getOpenDate(), l_datOpenDate) != 0)
        {

        } else
        {
            for (Iterator l_it = ifoFinTransactionList.iterator();
                l_it.hasNext();
                )
            {
                WEB3IfoFinTransaction l_tran =
                    (WEB3IfoFinTransaction) l_it.next();
                if (l_tran.getOrderUnitId() == l_lngOrderUnitId)
                {
                    l_dblNetAmount += l_tran.getNetAmount();
                }
            }
        }

        log.debug(
            "Calculated NetAmount="
                + l_dblNetAmount
                + ",OrderUnitId = "
                + l_lngOrderUnitId
                + ",OpenDate="
                + l_datOpenDate
                + ","
                + this.toString());

        return l_dblNetAmount;

    }

    /**
     * (subtract建玉数量)<BR>
     * 
     * ・this.建玉数量から返済建玉数量を減算する。 <BR>
     * ・this.建玉数量＜証拠金不足仮確定＞から 返済建玉数量＜当日夕場以外＞を減算する <BR>
     * 
     * 　@　@※建玉数量には予め建玉の元建玉数量が設定されている。 <BR>
     *
     * １）　@返済取引であるかの判定<BR>
     * 　@    引数.トランザクションカテゴリ == <BR>
     *       (”先物返済取引”、または、”OP返済取引”）の場合のみ、<BR>
     *       ２）、３）の数量の減算を行う。 <BR>
     * 
     * ２）　@数量の減算<BR>
     *       this.建玉数量 = this.建玉数量 - 引数.数量<BR>
     * 
     * ３）　@証拠金不足仮確定用の数量の減算 
     * ３－１）以下の場合、減算を行なわず、return。 
     *         引数.立会区分==”夕場” 
     *          　@（夕場返済約定は証拠金不足仮確定の計算には含めないため） 
     * 
     * ３－２）３－１）以外は減算を行なう。 
     *         this.建玉数量＜証拠金不足仮確定＞ = this.建玉数量＜証拠金不足仮確定＞ - 引数.数量 
     * 
     * 
     * @@param l_transactionCateg - (トランザクションカテゴリ)
     * @@param l_dblQuantity - (数量)<BR>
     * @@param l_strSessionType - (立会区分)<BR>
     * @@roseuid 4121FDAA0085
     */
    public void subtractQuantity(
        FinTransactionCateg l_transactionCateg,
        double l_dblQuantity,
        String l_strSessionType)
    {
        if (FinTransactionCateg
            .EQTYPE_IDX_FUTURES_CLOSE
            .equals(l_transactionCateg)
            || FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE.equals(
                l_transactionCateg))
        {
            setQuantity(getQuantity() - l_dblQuantity);

            //証拠金不足仮確定用の数量の減算 
            if (WEB3SessionTypeDef.EVENING_SESSION.equals(l_strSessionType))
            {
                //引数.立会区分==”夕場”の場合、
                //減算を行なわず、return。 
            } else {
                //this.建玉数量＜証拠金不足仮確定＞ = this.建玉数量＜証拠金不足仮確定＞ - 引数.数量
                this.quantityTemp = this.quantityTemp - l_dblQuantity;    
            }

        }
    }

    /**
     * (is決済済)<BR>
     * 
     * 当該建玉が決済済であるかを判定する。<BR>
     * 
     * this.建玉数量==0の場合、trueを返却する。以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 411375010214
     */
    public boolean isSettled()
    {
        if (getQuantity() == 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * (is買建)<BR>
     * 
     * 該当建玉が買建であるかを判定する。<BR>
     * 
     * this.建区分==”買建”の場合、trueを返却する。以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 411708CE02E8
     */
    public boolean isBuy()
    {
        if (ContractTypeEnum.LONG.equals(getContractType()))
        {
            return true;
        } else
        {
            return false;
        }
    }
    
    /**
     * (is当日建)<BR>
     *  
     * 当該建玉が当日建であるかを判定する。 
     * 
     * １） 営業日[T-1]、営業日[T+0]を取得する。 
     * （それぞれ、変数：営業日（T-1）、営業日（T+0）に格納） 
     * 
     * ・営業日（T-1） = 引数.証拠金計算条件.get営業日(-1) 
     * ・営業日（T+0） = 引数.証拠金計算条件.get営業日(0) 
     * 
     * ２）証拠金計算基準日を取得する。 
     * 
     *  ・証拠金計算基準日 = 引数.証拠金計算条件.get証拠金計算基準日() 
     * 
     * ３） 証拠金計算基準日 == 1:当日注文時間帯 の場合 
     * 
     * ３）-1 A or Bの場合、trueを返す 
     * A. this.建日==営業日（T-1） かつ this.立会区分=夕場 
     * B. this.建日==営業日（T+0） 
     * 
     * ３）-２ ３）-1以外の場合,falseを返す。 
     * 
     * ４） 証拠金計算基準日 == 2:翌日注文時間帯 の場合 
     * 
     * ４）-１ 以下の場合、trueを返す 
     * ・ this.建日==営業日（T+0） かつ this.立会区分=夕場 
     * 
     * ４）-２ ４）-１以外の場合,falseを返す。 
     * 
     *  
     * @@param l_ifoDepositCalcCondition - (証拠金計算条件)<BR>
     * 
     * @@return boolean
     * @@roseuid 411708CE02E8
     */
    public boolean isTodayContract(WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
    {
        // １） get営業日[T-1]、get営業日[T+0]を取得する。
        Date l_datPreBizDate = l_ifoDepositCalcCondition.getBizDate(-1); 
        Date l_datBizDate    = l_ifoDepositCalcCondition.getBizDate(0);
        
        // ２）証拠金計算基準日を取得する。
        int l_intIfoDepositCalcBaseDate = l_ifoDepositCalcCondition.getIfoDepositBaseDate();

        // ３） 証拠金計算基準日 == 1:当日注文時間帯 の場合 		
        if(l_intIfoDepositCalcBaseDate == WEB3IfoReservedDateDef.T_1)
        {
            //３）-1 A or Bの場合、trueを返す 
            //  A. this.建日==営業日（T-1） かつ this.立会区分=夕場 
            //  B. this.建日==営業日（T+0） 
            //３）-２ ３）-1以外の場合,falseを返す。
            if ( ((WEB3DateUtility.compareToDay(this.openDate, l_datPreBizDate) == 0) && 
                     (WEB3SessionTypeDef.EVENING_SESSION.equals(this.sessionType))) ||
                  (WEB3DateUtility.compareToDay(this.openDate, l_datBizDate) == 0)  )
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        // ４） 証拠金計算基準日 == 2:翌日注文時間帯 の場合 
        else if(l_intIfoDepositCalcBaseDate == WEB3IfoReservedDateDef.T_2)
        {
            // ４）-１ 以下の場合、trueを返す 
            // ・ this.建日==営業日（T+0） かつ this.立会区分=夕場
            if ( (WEB3DateUtility.compareToDay(this.openDate, l_datBizDate) == 0) &&
                 (WEB3SessionTypeDef.EVENING_SESSION.equals(this.sessionType)) ) 
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
        	return false;
        }		
    }
    /**
     * 建玉IDを取得する。
     * 
     * @@return　@建玉ID
     */
    public long getContractId()
    {
        return contractId;
    }

    /**
     * 建単価を取得する。
     * 
     * @@return　@建単価
     */
    public double getContractPrice()
    {
        return contractPrice;
    }

    /**
     * 建区分を取得する。
     * 
     * @@return　@建区分
     */
    public ContractTypeEnum getContractType()
    {
        return contractType;
    }

    /**
     * 市場IDを取得する。
     * 
     * @@return　@市場ID
     */
    public long getMarketId()
    {
        return marketId;
    }

    /**
     * 建日を取得する。
     * 
     * @@return　@建日
     */
    public Date getOpenDate()
    {
        return openDate;
    }

    /**
     * 銘柄IDを取得する。
     * 
     * @@return　@銘柄ID
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * 数量を取得する。
     * 
     * @@return　@数量
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * 建玉IDを設定する。
     * 
     * @@param l_lngContractId　@建玉ID
     */
    public void setContractId(long l_lngContractId)
    {
        this.contractId = l_lngContractId;
    }

    /**
     * 建単価を設定する。
     * 
     * @@param l_dblContractPrice　@建単価
     */
    public void setContractPrice(double l_dblContractPrice)
    {
        this.contractPrice = l_dblContractPrice;
    }

    /**
     * 建区分を設定する。
     * 
     * @@param l_contractType　@建区分
     */
    public void setContractType(ContractTypeEnum l_contractType)
    {
        this.contractType = l_contractType;
    }

    /**
     * 市場IDを設定する。
     * 
     * @@param l_lngMarketId　@市場ID
     */
    public void setMarketId(long l_lngMarketId)
    {
        this.marketId = l_lngMarketId;
    }

    /**
     * 建日を設定する。
     * 
     * @@param l_datOpenDate　@建日
     */
    public void setOpenDate(Date l_datOpenDate)
    {
        this.openDate = l_datOpenDate;
    }

    /**
     * 銘柄IDを設定する。
     * 
     * @@param l_lngProductId　@銘柄ID
     */
    public void setProductId(long l_lngProductId)
    {
        this.productId = l_lngProductId;
    }

    /**
     * 数量を設定する。
     * 
     * @@param l_dblQuantity　@数量
     */
    public void setQuantity(double l_dblQuantity)
    {
        this.quantity = l_dblQuantity;
    }
    
    /**
     * このオブジェクトの文字列表現を取得する。
     * 
     * @@return このオブジェクトの文字列表現
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoContract={");
        l_sb.append("contractId=").append(getContractId());
        l_sb.append(",productId=").append(getProductId());
        l_sb.append(",marketId=").append(getMarketId());
        l_sb.append(",contractType=").append(getContractType());
        l_sb.append(",openDate=").append(getOpenDate());
        l_sb.append(",contractPrice=").append(getContractPrice());
        l_sb.append(",quantity=").append(getQuantity());
        l_sb.append(",sessionType=").append(this.sessionType);
        l_sb.append(",quantityTemp=").append(this.quantityTemp);
        l_sb.append(",ifoFinTransactionList={");
        int i = 0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            if (i > 0)
            {
                l_sb.append(",");
            }
            l_sb.append(l_it.next().toString());
            i++;
        }
        l_sb.append("}");
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
