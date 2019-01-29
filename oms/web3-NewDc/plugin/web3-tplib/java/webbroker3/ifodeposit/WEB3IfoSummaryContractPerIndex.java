head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoSummaryContractPerIndex.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指数別先物OP建玉集計(WEB3IfoSummaryContractPerIndex.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 nakazato(ACT) 新規作成
Revesion History : 2007/07/06 hijikata(SRA) 夕場対応 
                            モデルNo.061⑦, No.067, No.074①@
                            計算式書No.022, No.024, No.026
Revesion History : 2007/08/02 hijikata(SRA) 夕場対応 計算式書No.035
*/

package webbroker3.ifodeposit;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;


/**
 * (指数別先物OP建玉集計)<BR>
 * 　@原資産銘柄コードをキーに集計される建玉集計クラス。<BR>
 * 　@簡易SPAN証拠金の算出に用いられる。<BR>
 * 　@[集計内容]<BR>
 * 　@　@・先物/オプションプット/オプションコール別、先物買建/売建別の建数量<BR>
 * 　@　@・先物/オプションプット/オプションコール別、先物買建/売建別の注文数量<BR>
 * 　@　@※オプション買建については、簡易SPAN証拠金の算出に使用しないため、集計を行わない<BR>
 * 
 * @@author  nakazato(ACT)
 */
public class WEB3IfoSummaryContractPerIndex
{
    /**
     * (原資産銘柄コード)
     */
    private String targetProductCode;

    /**
     * (規定証拠金)
     */
    private double ifoDepositPerUnit;

    /**
     * (規定証拠金レッド)
     */
    private double ifoDepositPerUnitRed;

    /**
     * (規定証拠金＜証拠金不足仮確定＞)
     */
    private double ifoDepositPerUnitTemp = 0;
    
    /**
     * (先物買建数量)
     */
    private double futuresBuyContractQuantity = 0;

    /**
     * (先物売建数量)
     */
    private double futuresSellContractQuantity = 0;

    /**
     * (オプションプット売建数量)
     */
    private double optionPutSellContractQuantity = 0;

    /**
     * (オプションコール売建数量)
     */
    private double optionCallSellContractQuantity = 0;

    /**
     * (先物買建注文数量[T+0])
     */
    private double currentBizDateFuturesBuyOrderQuantity = 0;

    /**
     * (先物買建注文数量[T+1])
     */
    private double nextBizDateFuturesBuyOrderQuantity = 0;

    /**
     * (先物売建注文数量[T+0]
     */
    private double currentBizDateFuturesSellOrderQuantity = 0;

    /**
     * (先物売建注文数量[T+1])
     */
    private double nextBizDateFuturesSellOrderQuantity = 0;

    /**
     * (オプションプット売建注文数量[T+0])
     */
    private double currentBizDateOptionPutSellOrderQuantity = 0;

    /**
     * (オプションプット売建注文数量[T+1])
     */
    private double nextBizDateOptionPutSellOrderQuantity = 0;

    /**
     * (オプションコール売建注文数量[T+0])
     */
    private double currentBizDateOptionCallSellOrderQuantity = 0;

    /**
     * (オプションコール売建注文数量[T+1])
     */
    private double nextBizDateOptionCallSellOrderQuantity = 0;

    /**
     * (先物買建数量＜証拠金不足仮確定＞)
     */
    private double futuresBuyContractQuantityTemp = 0;

    /**
     * (先物売建数量＜証拠金不足仮確定＞)
     */
    private double futuresSellContractQuantityTemp = 0;

    /**
     * (オプションプット売建数量＜証拠金不足仮確定＞)
     */
    private double optionPutSellContractQuantityTemp = 0;

    /**
     * (オプションコール売建数量＜証拠金不足仮確定＞)
     */
    private double optionCallSellContractQuantityTemp = 0;

    /**
     * (コンストラクタ)<BR>
     */
    public WEB3IfoSummaryContractPerIndex()
    {

    }

    /**
     * (create指数別先物OP建玉集計)<BR>
     * 
     * 指数別先物OP建玉集計を生成する。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerIndex
     * @@roseuid 4119854B0116
     */
    public static WEB3IfoSummaryContractPerIndex create()
    {
        return new WEB3IfoSummaryContractPerIndex();
    }

    /**
     * (add建数量)<BR>
     * 
     * ・先物/プットオプション/コールオプションであるか<BR>
     * ・買建/売建であるか<BR>
     * にもとづき、該当する建数量の加算処理を行う。<BR>
     * 
     * １）　@先物買建の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”先物” && 引数.is買建 == true)<BR>
     * 
     * 　@　@　@    this.先物買建数量 = this.get先物買建数量( ) + 引数.数量<BR>
     * 
     * ２）　@先物売建の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”先物” && 引数.is買建 == false)<BR>
     * 
     * 　@　@　@    this.先物売建数量 = this.get先物売建数量( ) + 引数.数量<BR>
     * 
     * ３）　@オプションプット売建の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”プットオプション” && <BR> 
     *        引数.is買建 == false)<BR>
     *  
     * 　@　@　@     this.オプションプット売建数量 =<BR>
     *                this.getオプションプット売建数量( ) + 引数.数量<BR>
     *
     * ４）　@オプションコール売建の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”コールオプション” && <BR>
     *        引数.is買建 == false)<BR>
     * 
     * 　@　@　@    this.オプションコール売建数量 = <BR>
     *               this.getオプションコール売建数量( ) + 引数.数量<BR>
     * 
     * @@param l_ifoDerivativeType - (先物オプション商品区分)<BR>
     * 
     * 1：先物<BR>
     * 2：コールオプション<BR>
     * 3：プットオプション<BR>
     * @@param l_blnIsBuy - (is買建)<BR>
     * 
     * 建玉が買建である場合、true。以外、false。<BR>
     * @@param l_dblQuantity - 数量<BR>
     * 
     */
    public void addContractQuantity(
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        boolean l_blnIsBuy,
        double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "addContractQuantity()";

        //先物買建の場合
        if (l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
            && l_blnIsBuy == true)
        {
            //this.先物買建数量 = this.get先物買建数量( ) + 引数.数量
            this.futuresBuyContractQuantity = this.futuresBuyContractQuantity + l_dblQuantity;
        }
        //先物売建の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
                && l_blnIsBuy == false)
        {
            //this.先物売建数量 = this.get先物売建数量( ) + 引数.数量
            this.futuresSellContractQuantity = this.futuresSellContractQuantity + l_dblQuantity;
        }
        //オプションプット売建の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS
                && l_blnIsBuy == false)
        {
            //this.オプションプット売建数量 = this.getオプションプット売建数量( ) + 引数.数量
            this.optionPutSellContractQuantity = this.optionPutSellContractQuantity + l_dblQuantity;
        }
        //オプションコール売建の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS
                && l_blnIsBuy == false)
        {
            //this.オプションコール売建数量 = this.getオプションコール売建数量( ) + 引数.数量
            this.optionCallSellContractQuantity =
                this.optionCallSellContractQuantity + l_dblQuantity;
        }
        //以外の時
        else
        {
            //何も行わない
        }
    }

    /**
     * (add建数量＜証拠金不足仮確定＞)<BR>
     * 
     * ・先物/プットオプション/コールオプションであるか<BR>
     * ・買建/売建であるか<BR>
     * にもとづき、該当する建数量＜証拠金不足仮確定＞の加算処理を行う。<BR>
     * 
     * １）　@先物買建の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”先物” && 引数.is買建 == true)<BR>
     * 
     * 　@　@　@this.先物買建数量＜証拠金不足仮確定＞ = <BR>
     *            this.get先物買建数量＜証拠金不足仮確定＞( ) + 引数.数量<BR>
     * 
     * ２）　@先物売建の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”先物” && 引数.is買建 == false)<BR>
     * 
     * 　@　@　@this.先物売建数量＜証拠金不足仮確定＞ = 
     *           this.get先物売建数量＜証拠金不足仮確定＞( ) + 引数.数量<BR>
     * 
     * ３）　@オプションプット売建の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”プットオプション” && <BR>
     *        引数.is買建 == false)<BR>
     *  
     * 　@　@　@this.オプションプット売建数量＜証拠金不足仮確定＞ = 
     *           this.getオプションプット売建数量＜証拠金不足仮確定＞( ) + 引数.数量<BR>
     *  
     * ４）　@オプションコール売建の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”コールオプション” && <BR>
     *        引数.is買建 == false)<BR>
     * 
     * 　@　@　@this.オプションコール売建数量＜証拠金不足仮確定＞ = 
     *           this.getオプションコール売建数量＜証拠金不足仮確定＞( ) + 引数.数量<BR>
     * 
     * @@param l_ifoDerivativeType - (先物オプション商品区分)<BR>
     * 
     * 1：先物<BR>
     * 2：コールオプション<BR>
     * 3：プットオプション<BR>
     * @@param l_blnIsBuy - (is買建)<BR>
     * 
     * 建玉が買建である場合、true。以外、false。<BR>
     * @@param l_dblQuantity - 数量<BR>
     * 
     */
    public void addContractQuantityTemp(
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        boolean l_blnIsBuy,
        double l_dblQuantity)
    {
        //先物買建の場合
        if (l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES && 
            l_blnIsBuy == true)
        {
            //this.先物買建数量＜証拠金不足仮確定＞ = this.get先物買建数量＜証拠金不足仮確定＞( ) + 引数.数量
            this.futuresBuyContractQuantityTemp = 
                this.getFuturesBuyContractQuantityTemp() + l_dblQuantity;
        }
        //先物売建の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES && 
            l_blnIsBuy == false)
        {
            //this.先物売建数量＜証拠金不足仮確定＞ = this.get先物売建数量＜証拠金不足仮確定＞( ) + 引数.数量
            this.futuresSellContractQuantityTemp = 
                this.getFuturesSellContractQuantityTemp() + l_dblQuantity;
        }
        //オプションプット売建の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS && 
            l_blnIsBuy == false)
        {
            //this.オプションプット売建数量＜証拠金不足仮確定＞ = this.getオプションプット売建数量＜証拠金不足仮確定＞( ) + 引数.数量
            this.optionPutSellContractQuantityTemp = 
                this.getOptionPutSellContractQuantityTemp() + l_dblQuantity;
        }
        //オプションコール売建の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS && 
            l_blnIsBuy == false)
            {
                //this.オプションコール売建数量＜証拠金不足仮確定＞ = this.getオプションコール売建数量＜証拠金不足仮確定＞( ) + 引数.数量
                this.optionCallSellContractQuantityTemp =
                    this.getOptionCallSellContractQuantityTemp() + l_dblQuantity;
        }
    }

    /**
     * (add注文数量)<BR>
     * 
     * ・先物/プットオプション/コールオプションであるか<BR>
     * ・買建/売建であるか<BR>
     * ・発注日がいつであるか<BR>
     * にもとづき、該当する注文数量の加算処理を行う。<BR>
     * 
     * １）　@先物買建[T+0]の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”先物”  && 引数.is買建 == true && 
     * 引数.発注日 == 引数.営業日[T+0])<BR>
     * 
     * 　@　@　@this.先物買建注文数量[T+0] = this.get先物買建注文数量[T+0]( ) + 
     * 引数.数量<BR>
     * 
     * ２）　@先物買建[T+1]の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”先物”  && 引数.is買建 == true && 
     * 引数.発注日 == 引数.営業日[T+1])<BR>
     * 
     * 　@　@　@this.先物買建注文数量[T+1] = this.get先物買建注文数量[T+1]( ) + 
     * 引数.数量<BR>
     * 
     * ３）　@先物売建[T+0]の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”先物”  && 引数.is買建 == false && 
     * 引数.発注日 == 引数.営業日[T+0])<BR>
     * 
     * 　@　@　@this.先物売建注文数量[T+0] = this.get先物売建注文数量[T+0]( ) + 
     * 引数.数量<BR>
     * 
     * ４）　@先物売建[T+1]の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”先物”  && 引数.is買建 == false && 
     * 引数.発注日 == 引数.営業日[T+1])<BR>
     * 
     * 　@　@　@this.先物売建注文数量[T+1] = this.get先物売建注文数量[T+1]( ) + 
     * 引数.数量<BR>
     * 
     * ５）　@オプションプット売建[T+0]の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”プットオプション”  && 引数.is買建 == 
     * false && 引数.発注日 == 引数.営業日[T+0])<BR>
     * 
     * 　@　@　@this.オプションプット売建注文数量[T+0] = 
     * this.getオプションプット売建注文数量[T+0]( ) + 引数.数量<BR>
     * 
     * ６）　@オプションプット売建[T+1]の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”プットオプション”  && 引数.is買建 == 
     * false && 引数.発注日 == 引数.営業日[T+1])<BR>
     * 
     * 　@　@　@this.オプションプット売建注文数量[T+1] = 
     * this.getオプションプット売建注文数量[T+1]( ) + 引数.数量<BR>
     * 
     * ７）　@オプションコール売建[T+0]の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”コールオプション”  && 引数.is買建 == 
     * false && 引数.発注日 == 引数.営業日[T+0])<BR>
     * 
     * 　@　@　@this.オプションコール売建注文数量[T+0] = 
     * this.getオプションコール売建注文数量[T+0]( ) + 引数.数量<BR>
     * 
     * ８）　@オプションコール売建[T+1]の場合<BR>
     * 　@　@　@(引数.先物オプション商品区分 == ”コールオプション”  && 引数.is買建 == 
     * false && 引数.発注日 == 引数.営業日[T+1])<BR>
     * 
     * 　@　@　@this.オプションコール売建注文数量[T+1] = 
     * this.getオプションコール売建注文数量[T+1]( ) + 引数.数量<BR>
     * @@param l_ifoDerivativeType - (先物オプション商品区分)<BR>
     * 
     * 1：先物<BR>
     * 2：コールオプション<BR>
     * 3：プットオプション<BR>
     * @@param l_blnIsBuy - (is買建)<BR>
     * 
     * 建玉が買建である場合、true。以外、false。<BR>
     * @@param l_datOrderDate - 発注日。
     * @@param l_datCurrentBizDate - 営業日[T+0]。
     * @@param l_datNextBizDate - 営業日[T+1]。
     * @@param l_dblQuantity - 数量。
     */
    public void addOrderQuantity(
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        boolean l_blnIsBuy,
        Date l_datOrderDate,
        Date l_datCurrentBizDate,
        Date l_datNextBizDate,
        double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "addOrderQuantity()";

        //先物買建[T+0]の場合
        if (l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
            && l_blnIsBuy == true
            && l_datOrderDate.equals(l_datCurrentBizDate))
        {
            //this.先物買建注文数量[T+0] = this.get先物買建注文数量[T+0]( ) + 引数.数量
            this.currentBizDateFuturesBuyOrderQuantity =
                this.currentBizDateFuturesBuyOrderQuantity + l_dblQuantity;
        }
        //先物買建[T+1]の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
                && l_blnIsBuy == true
                && l_datOrderDate.equals(l_datNextBizDate))
        {
            //this.先物買建注文数量[T+1] = this.get先物買建注文数量[T+1]( ) + 引数.数量
            this.nextBizDateFuturesBuyOrderQuantity =
                this.nextBizDateFuturesBuyOrderQuantity + l_dblQuantity;
        }
        //先物売建[T+0]の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datCurrentBizDate))
        {
            //this.先物売建注文数量[T+0] = this.get先物売建注文数量[T+0]( ) + 引数.数量
            this.currentBizDateFuturesSellOrderQuantity =
                this.currentBizDateFuturesSellOrderQuantity + l_dblQuantity;
        }
        //先物売建[T+1]の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datNextBizDate))
        {
            //this.先物売建注文数量[T+1] = this.get先物売建注文数量[T+1]( ) + 引数.数量
            this.nextBizDateFuturesSellOrderQuantity =
                this.nextBizDateFuturesSellOrderQuantity + l_dblQuantity;
        }
        //オプションプット売建[T+0]の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datCurrentBizDate))
        {
            //this.オプションプット売建注文数量[T+0] = this.getオプションプット売建注文数量[T+0]( ) + 引数.数量
            this.currentBizDateOptionPutSellOrderQuantity =
                this.currentBizDateOptionPutSellOrderQuantity + l_dblQuantity;
        }
        //オプションプット売建[T+1]の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datNextBizDate))
        {
            //this.オプションプット売建注文数量[T+1] = this.getオプションプット売建注文数量[T+1]( ) + 引数.数量
            this.nextBizDateOptionPutSellOrderQuantity =
                this.nextBizDateOptionPutSellOrderQuantity + l_dblQuantity;
        }

        //オプションコール売建[T+0]の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datCurrentBizDate))
        {
            //this.オプションコール売建注文数量[T+0] = this.getオプションコール売建注文数量[T+0]( ) + 引数.数量
            this.currentBizDateOptionCallSellOrderQuantity =
                this.currentBizDateOptionCallSellOrderQuantity + l_dblQuantity;
        }
        //オプションコール売建[T+1]の場合
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datNextBizDate))
        {
            //this.オプションコール売建注文数量[T+1] = this.getオプションコール売建注文数量[T+1]( ) + 引数.数量
            this.nextBizDateOptionCallSellOrderQuantity =
                this.nextBizDateOptionCallSellOrderQuantity + l_dblQuantity;
        }
        //以外(オプション買建)の時
        else
        {
            //何もしない
        }
    }

    /**
     * (calc指数別買ポジション建玉)<BR>
     * 
     * 引数で指定された指定日(=n)の、「指数別買ポジション建玉」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyContractQty(int l_intReservedDate)
    {
        final String STR_METHOD_NAME = "calcBuyContractQty()";

        //指数別買ポジション建玉(n)
        double l_dblBuyPosition = 0;

        //引数.指定日が0の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //指数別買ポジション建玉(n)  =　@指数別先物OP建玉集計.get先物買建数量()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.get先物買建注文数量[T+0]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションプット売建数量( )
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションプット売建注文数量[T+0]( )           
            l_dblBuyPosition =
                this.futuresBuyContractQuantity
                    + this.currentBizDateFuturesBuyOrderQuantity
                    + this.optionPutSellContractQuantity
                    + this.currentBizDateOptionPutSellOrderQuantity;

        }
        //引数.指定日が1または2の時
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //指数別買ポジション建玉(n)　@=　@指数別先物OP建玉集計.get先物買建数量()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.get先物買建注文数量[T+0]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.get先物買建注文数量[T+1]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションプット売建数量( )
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションプット売建注文数量[T+0]( )
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションプット売建注文数量[T+1]( )
            l_dblBuyPosition =
                this.futuresBuyContractQuantity
                    + this.currentBizDateFuturesBuyOrderQuantity
                    + this.nextBizDateFuturesBuyOrderQuantity
                    + this.optionPutSellContractQuantity
                    + this.currentBizDateOptionPutSellOrderQuantity
                    + this.nextBizDateOptionPutSellOrderQuantity;

        }
        //それ以外
        else
        {
            //指数別買ポジション建玉(n)に0を代入
            l_dblBuyPosition = 0;
        }

        //計算した指数別買ポジション建玉(n)を返却する。
        return l_dblBuyPosition;
    }

    /**
     * (calc指数別売ポジション建玉)<BR>
     * 
     * 引数で指定された指定日(=n)の、「指数別売ポジション建玉」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellContractQty(int l_intReservedDate)
    {
        final String STR_METHOD_NAME = "calcSellContractQty()";

        //指数別売ポジション建玉(n)
        double l_dblSellPosition = 0;

        //引数.指定日が0の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //指数別売ポジション建玉(n)　@=　@指数別先物OP建玉集計.get先物売建数量()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.get先物売建注文数量[T+0]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションコール売建数量( )
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションコール売建注文数量[T+0]( )
            l_dblSellPosition =
                this.futuresSellContractQuantity
                    + this.currentBizDateFuturesSellOrderQuantity
                    + this.optionCallSellContractQuantity
                    + this.currentBizDateOptionCallSellOrderQuantity;

        }
        //引数.指定日が1または2の時
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //指数別売ポジション建玉(n)　@=　@指数別先物OP建玉集計.get先物売建数量()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.get先物売建注文数量[T+0]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.get先物売建注文数量[T+1]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションコール売建数量( )
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションコール売建注文数量[T+0]( )
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションコール売建注文数量[T+1]( )
            l_dblSellPosition =
                this.futuresSellContractQuantity
                    + this.currentBizDateFuturesSellOrderQuantity
                    + this.nextBizDateFuturesSellOrderQuantity
                    + this.optionCallSellContractQuantity
                    + this.currentBizDateOptionCallSellOrderQuantity
                    + this.nextBizDateOptionCallSellOrderQuantity;

        }
        //それ以外
        else
        {
            //指数別売ポジション建玉(n)に0を代入
            l_dblSellPosition = 0;
        }

        //計算した指数別売ポジション建玉(n)を返却する。
        return l_dblSellPosition;
    }

    /**
     * (calc指数別注文中買ポジション建玉)<BR>
     * 
     * 引数で指定された指定日(=n)の、「指数別注文中買ポジション建玉」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<br>
     * 
     * 0、1、2のいずれかの値。<br>
     * @@return double
     */
    public double calcBuyOrderQty(int l_intReservedDate)
    {
        final String STR_METHOD_NAME = "calcBuyOrderQty()";

        //指数別注文中買ポジション建玉(n)
        double l_dblOrderBuyPosition = 0;

        //引数.指定日が0の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //指数別注文中買ポジション建玉(n)　@=　@指数別先物OP建玉集計.get先物買建注文数量[T+0]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションプット売建注文数量[T+0]( )
            l_dblOrderBuyPosition =
                this.currentBizDateFuturesBuyOrderQuantity
                    + this.currentBizDateOptionPutSellOrderQuantity;

        }
        //引数.指定日が1または2の時
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //指数別注文中買ポジション建玉(n)　@=　@指数別先物OP建玉集計.get先物買建注文数量[T+0]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.get先物買建注文数量[T+1]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションプット売建注文数量[T+0]( )
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションプット売建注文数量[T+1]( )
            l_dblOrderBuyPosition =
                this.currentBizDateFuturesBuyOrderQuantity
                    + this.nextBizDateFuturesBuyOrderQuantity
                    + this.currentBizDateOptionPutSellOrderQuantity
                    + this.nextBizDateOptionPutSellOrderQuantity;
        }
        //それ以外
        else
        {
            //指数別注文中買ポジション建玉(n)に0を代入
            l_dblOrderBuyPosition = 0;
        }

        //計算した指数別注文中買ポジション建玉(n)返却する。
        return l_dblOrderBuyPosition;
    }

    /**
     * (calc指数別注文中売ポジション建玉)<BR>
     * 
     * 引数で指定された指定日(=n)の、「指数別注文中売ポジション建玉」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellOrderQty(int l_intReservedDate)
    {
        final String STR_METHOD_NAME = "calcSellOrderQty()";

        //指数別注文中売ポジション建玉(n)
        double l_dblOrderSellPosition = 0;

        //引数.指定日が0の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //指数別注文中売ポジション建玉(n)　@=　@指数別先物OP建玉集計.get先物売建注文数量[T+0]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションコール売建注文数量[T+0]( )
            l_dblOrderSellPosition =
                this.currentBizDateFuturesSellOrderQuantity
                    + this.currentBizDateOptionCallSellOrderQuantity;
        }
        //引数.指定日が1または2の時
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //指数別注文中売ポジション建玉(n)　@=　@指数別先物OP建玉集計.get先物売建注文数量[T+0]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.get先物売建注文数量[T+1]()
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションコール建注文数量[T+0]( )
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 指数別先物OP建玉集計.getオプションコール売建注文数量[T+1]( )
            l_dblOrderSellPosition =
                this.currentBizDateFuturesSellOrderQuantity
                    + this.nextBizDateFuturesSellOrderQuantity
                    + this.currentBizDateOptionCallSellOrderQuantity
                    + this.nextBizDateOptionCallSellOrderQuantity;
        }
        //それ以外
        else
        {
            //指数別注文中売ポジション建玉(n)に0を代入
            l_dblOrderSellPosition = 0;
        }

        //計算した指数別注文中売ポジション建玉(n)返却する。
        return l_dblOrderSellPosition;
    }

    /**
     * (calc指数別買ポジション金額 )<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「指数別買ポジション金額」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcPossibleBuyAmt(int l_intReservedDate)
    {
        /*
         * 引数の指定日チェックを行う。
         */
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        //引数.指定日が0、1、2いずれかの時
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        /*
         * 指数別買ポジション金額(n)を計算する。
         * 
         * 指数別買ポジション金額(n)　@=　@指数別買ポジション建玉(n) × 規定証拠金
         * ※   nは、引数の指定日。
         * ※   計算式に使用する各値の取得方法@
         *        ・指数別買ポジション建玉(n) ・・・指数別先物OP建玉集計.calc指数別買ポジション建玉(n)
         *        ・規定証拠金 ・・・指数別先物OP建玉集計.get規定証拠金()
         */
        //指数別買ポジション金額
        double l_dblPossAmt = this.calcBuyContractQty(l_intReservedDate) * this.getIfoDepositPerUnit();

        /*
         * 計算した指数別買ポジション金額(n)を返却する。
         */
        return l_dblPossAmt;
    }

    /**
     * (calc指数別売ポジション金額)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「指数別売ポジション金額」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcPossibleSellAmt(int l_intReservedDate)
    {
        /*
         * 引数の指定日チェックを行う。
         */
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        //引数.指定日が0、1、2いずれかの時
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        /*
         * 指数別売ポジション金額(n)を計算する。
         * 
         * 指数別売ポジション金額(n)　@=　@指数別売ポジション建玉(n) × 規定証拠金
         * ※   nは、引数の指定日。
         * ※   計算式に使用する各値の取得方法@
         *        ・指数別売ポジション建玉(n) ・・・指数別先物OP建玉集計.calc指数別売ポジション建玉(n)
         *        ・規定証拠金 ・・・指数別先物OP建玉集計.get規定証拠金()
         */
        //指数別売ポジション金額
        double l_dblPossAmt = this.calcSellContractQty(l_intReservedDate) * this.getIfoDepositPerUnit();

        /*
         * 計算した指数別売ポジション金額(n)を返却する。
         */
        return l_dblPossAmt;
    }

    /**
     * (calc指数別買ポジション金額レッド)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「指数別買ポジション金額レッド」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcPossibleBuyAmtRed(int l_intReservedDate)
    {
        /*
         * 引数の指定日チェックを行う。
         */
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        //引数.指定日が0、1、2いずれかの時
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        /*
         * 指数別買ポジション金額(n)を計算する。
         * 
         * 指数別買ポジション金額(n)　@=　@指数別買ポジション建玉(n) × 規定証拠金レッド
         * ※   nは、引数の指定日。
         * ※   計算式に使用する各値の取得方法@
         *        ・指数別買ポジション建玉(n) ・・・指数別先物OP建玉集計.calc指数別買ポジション建玉(n)
         *        ・規定証拠金レッド ・・・指数別先物OP建玉集計.get規定証拠金レッド()
         */
        //指数別買ポジション金額レッド
        double l_dblPossAmt = this.calcBuyContractQty(l_intReservedDate) * this.getIfoDepositPerUnitRed();

        /*
         * 計算した指数別買ポジション金額レッド(n)を返却する。
         */
        return l_dblPossAmt;
    }

    /**
     * (calc指数別売ポジション金額レッド )<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「指数別売ポジション金額レッド」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcPossibleSellAmtRed(int l_intReservedDate)
    {
        /*
         * 引数の指定日チェックを行う。
         */
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        //引数.指定日が0、1、2いずれかの時
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        /*
         * 指数別売ポジション金額レッド(n)を計算する。
         * 
         * 指数別売ポジション金額(n)　@=　@指数別売ポジション建玉(n) × 規定証拠金レッド
         * ※   nは、引数の指定日。
         * ※   計算式に使用する各値の取得方法@
         *        ・指数別売ポジション建玉(n) ・・・指数別先物OP建玉集計.calc指数別売ポジション建玉(n)
         *        ・規定証拠金レッド ・・・指数別先物OP建玉集計.get規定証拠金()レッド
         */
        //指数別売ポジション金額レッド
        double l_dblPossAmt = this.calcSellContractQty(l_intReservedDate) * this.getIfoDepositPerUnitRed();

        /*
         * 計算した指数別売ポジション金額レッド(n)を返却する。
         */
        return l_dblPossAmt;
    }

    /**
     * (calc指数別買ポジション建玉＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「指数別買ポジション建玉＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyContractQtyTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //指数別買ポジション建玉＜証拠金不足仮確定＞
        double l_dblBuyPositionTemp = 0;

        //指数別買ポジション建玉＜証拠金不足仮確定＞ = 
        //   指数別先物OP建玉集計.get先物買建数量＜証拠金不足仮確定＞ ()
        //    + 指数別先物OP建玉集計.getオプションプット売建数量＜証拠金不足仮確定＞ ()
        l_dblBuyPositionTemp =
            this.getFuturesBuyContractQuantityTemp()
            + this.getOptionPutSellContractQuantityTemp();

        //計算した指数別買ポジション建玉＜証拠金不足仮確定＞(n)を返却する。
        return l_dblBuyPositionTemp;
    }

    /**
     * (calc指数別売ポジション建玉)＜証拠金不足仮確定＞<BR>
     * 
     * 引数で指定された指定日(=n)の、「指数別売ポジション建玉＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellContractQtyTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //指数別売ポジション建玉＜証拠金不足仮確定＞
        double l_dblSellPositionTemp = 0;

        //指数別売ポジション建玉＜証拠金不足仮確定＞  = 
        //    指数別先物OP建玉集計.get先物売建数量＜証拠金不足仮確定＞ () 
        //    + 指数別先物OP建玉集計.getオプションコール売建数量＜証拠金不足仮確定＞ ()
        l_dblSellPositionTemp =
            this.getFuturesSellContractQuantityTemp()
            + this.getOptionCallSellContractQuantityTemp();

        //計算した指数別売ポジション建玉＜証拠金不足仮確定＞を返却する。
        return l_dblSellPositionTemp;
    }

   /**
     * (calc指数別買ポジション金額＜証拠金不足仮確定＞ )<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「指数別買ポジション金額＜証拠金不足仮確定＞」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcPossibleBuyAmtTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //指数別買ポジション金額＜証拠金不足仮確定＞ =
        //    指数別買ポジション建玉＜証拠金不足仮確定＞ × 規定証拠金＜証拠金不足仮確定＞
        double l_dblPossAmtTemp = 
            this.calcBuyContractQtyTemp(l_intReservedDate) * this.getIfoDepositPerUnitTemp();
         
        return l_dblPossAmtTemp;
    }

    /**
     * (calc指数別売ポジション金額＜証拠金不足仮確定＞)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「指数別売ポジション金額＜証拠金不足仮確定＞」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcPossibleSellAmtTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //指数別売ポジション金額＜証拠金不足仮確定＞ =
        //    指数別売ポジション建玉＜証拠金不足仮確定＞ × 規定証拠金＜証拠金不足仮確定＞
        double l_dblPossAmtTemp = 
            this.calcSellContractQtyTemp(l_intReservedDate) * this.getIfoDepositPerUnitTemp();
         
        return l_dblPossAmtTemp;
    }

    /**
     * (get原資産銘柄コード)<BR>
     * 
     * this.原資産銘柄コードを返却する。<BR>
     * @@return double
     */
    public String getTargetProductCode()
    {
        return this.targetProductCode;
    }

    /**
     * (get規定証拠金)<BR>
     * 
     * this.規定証拠金を返却する。<BR>
     * @@return double
     */
    public double getIfoDepositPerUnit()
    {
        return this.ifoDepositPerUnit;
    }

    /**
     * (get規定証拠金レッド)<BR>
     * 
     * this.規定証拠金レッドを返却する。<BR>
     * @@return double
     */
    public double getIfoDepositPerUnitRed()
    {
        return this.ifoDepositPerUnitRed;
    }

    /**
     * (get先物買建数量)<BR>
     * 
     * this.先物買建数量を返却する。<BR>
     * @@return double
     */
    public double getFuturesBuyContractQuantity()
    {
        return this.futuresBuyContractQuantity;
    }

    /**
     * (get先物売建数量)<BR>
     * 
     * this.先物売建数量を返却する。<BR>
     * @@return double
     */
    public double getFuturesSellContractQuantity()
    {
        return this.futuresSellContractQuantity;
    }

    /**
     * (getオプションプット売建数量)<BR>
     * 
     * this.オプションプット売建数量を返却する。<BR>
     * @@return double
     */
    public double getOptionPutSellContractQuantity()
    {
        return this.optionPutSellContractQuantity;
    }

    /**
     * (getオプションコール売建数量)<BR>
     * 
     * this.オプションコール売建数量を返却する。<BR>
     * @@return double
     */
    public double getOptionCallSellContractQuantity()
    {
        return this.optionCallSellContractQuantity;
    }

    /**
     * (get先物買建注文数量[T+0])<BR>
     * 
     * this.先物買建注文数量[T+0]を返却する。<BR>
     * @@return double
     */
    public double getCurrentBizDateFuturesBuyOrderQuantity()
    {
        return this.currentBizDateFuturesBuyOrderQuantity;
    }

    /**
     * (get先物買建注文数量[T+1])<BR>
     * 
     * this.先物買建注文数量[T+1]を返却する。<BR>
     * @@return double
     */
    public double getNextBizDateFuturesBuyOrderQuantity()
    {
        return this.nextBizDateFuturesBuyOrderQuantity;
    }

    /**
     * (get先物売建注文数量[T+0])<BR>
     * 
     * this.先物売建注文数量[T+0]を返却する。<BR>
     * @@return double
     */
    public double getCurrentBizDateFuturesSellOrderQuantity()
    {
        return this.currentBizDateFuturesSellOrderQuantity;
    }

    /**
     * (get先物売建注文数量[T+1])<BR>
     * 
     * this.先物売建注文数量[T+1]を返却する。<BR>
     * @@return double
     */
    public double getNextBizDateFuturesSellOrderQuantity()
    {
        return this.nextBizDateFuturesSellOrderQuantity;
    }

    /**
     * (getオプションプット売建注文数量[T+0])<BR>
     * 
     * this.オプションプット売建注文数量[T+0]を返却する。<BR>
     * @@return double
     */
    public double getCurrentBizDateOptionPutSellOrderQuantity()
    {
        return this.currentBizDateOptionPutSellOrderQuantity;
    }

    /**
     * (getオプションプット売建注文数量[T+1])<BR>
     * 
     * this.オプションプット売建注文数量[T+1]を返却する。<BR>
     * @@return double
     */
    public double getNextBizDateOptionPutSellOrderQuantity()
    {
        return this.nextBizDateOptionPutSellOrderQuantity;
    }

    /**
     * (getオプションコール売建注文数量[T+0])<BR>
     * 
     * this.オプションコール売建注文数量[T+0]を返却する。<BR>
     * @@return double
     */
    public double getCurrentBizDateOptionCallSellOrderQuantity()
    {
        return this.currentBizDateOptionCallSellOrderQuantity;
    }

    /**
     * (getオプションコール売建注文数量[T+1])<BR>
     * 
     * this.オプションコール売建注文数量[T+1]を返却する。<BR>
     * @@return double
     */
    public double getNextBizDateOptionCallSellOrderQuantity()
    {
        return this.nextBizDateOptionCallSellOrderQuantity;
    }

    /**
     * (set原資産銘柄コード)<BR>
     * 
     * 引数.原資産銘柄コードをthis.原資産銘柄コードにセットする。<BR>
     * @@param l_strTargetProductCode - 原資産銘柄コード
     */
    public void setTargetProductCode(String l_strTargetProductCode)
    {
        this.targetProductCode = l_strTargetProductCode;
    }

    /**
     * (set規定証拠金)<BR>
     * 
     * 引数.規定証拠金をthis.規定証拠金にセットする。<BR>
     * @@param l_dblIfoDepositPerUnit - 規定証拠金
     */
    public void setIfoDepositPerUnit(double l_dblIfoDepositPerUnit)
    {
        this.ifoDepositPerUnit = l_dblIfoDepositPerUnit;
    }

    /**
     * (set規定証拠金レッド)<BR>
     * 
     * 引数.規定証拠金レッドをthis.規定証拠金レッドにセットする。<BR>
     * @@param l_dblIfoDepositPerUnitRed - 規定証拠金レッド
     */
    public void setIfoDepositPerUnitRed(double l_dblIfoDepositPerUnitRed)
    {
        this.ifoDepositPerUnitRed = l_dblIfoDepositPerUnitRed;
    }

    /**
     * (set規定証拠金＜証拠金不足仮確定＞)<BR>
     * 
     * 引数.規定証拠金＜証拠金不足仮確定＞をthis.規定証拠金＜証拠金不足仮確定＞にセットする。<BR>
     * @@param l_dblIfoDepositPerUnitTemp  - 規定証拠金＜証拠金不足仮確定＞
     */
    public void setIfoDepositPerUnitTemp(double l_dblIfoDepositPerUnitTemp)
    {
        this.ifoDepositPerUnitTemp = l_dblIfoDepositPerUnitTemp;
    }    

    /**
     * (set先物買建数量)<BR>
     * 
     * 引数.先物買建数量をthis.先物買建数量にセットする。<BR>
     * @@param l_dblFuturesBuyContractQuantity - 先物買建数量
     */
    public void setFuturesBuyContractQuantity(double l_dblFuturesBuyContractQuantity)
    {
        this.futuresBuyContractQuantity = l_dblFuturesBuyContractQuantity;
    }

    /**
     * (set先物売建数量)<BR>
     * 
     * 引数.先物売建数量をthis.先物売建数量にセットする。<BR>
     * @@param l_dblFuturesSellContractQuantity - 先物売建数量
     */
    public void setFuturesSellContractQuantity(double l_dblFuturesSellContractQuantity)
    {
        this.futuresSellContractQuantity = l_dblFuturesSellContractQuantity;
    }

    /**
     * (setオプションコール売建数量)<BR>
     * 
     * 引数.オプションコール売建数量をthis.オプションコール売建数量にセットする。<BR>
     * @@param l_dblOptionCallSellContractQuantity - オプションコール売建数量
     */
    public void setOptionCallSellContractQuantity(double l_dblOptionCallSellContractQuantity)
    {
        this.optionCallSellContractQuantity = l_dblOptionCallSellContractQuantity;
    }

    /**
     * (setオプションプット売建数量)<BR>
     * 
     * 引数.オプションプット売建数量をthis.オプションプット売建数量にセットする。<BR>
     * @@param l_dblOptionPutSellContractQuantity - オプションプット売建数量
     */
    public void setOptionPutSellContractQuantity(double l_dblOptionPutSellContractQuantity)
    {
        this.optionPutSellContractQuantity = l_dblOptionPutSellContractQuantity;
    }

    /**
     * (set先物買建注文数量[T+0])<BR>
     * 
     * 引数.先物買建注文数量[T+0]をthis.先物買建注文数量[T+0]にセットする。<BR>
     * @@param l_dblCurrentBizDateFuturesBuyOrderQuantity - 先物買建注文数量[T+0]
     */
    public void setCurrentBizDateFuturesBuyOrderQuantity(double l_dblCurrentBizDateFuturesBuyOrderQuantity)
    {
        this.currentBizDateFuturesBuyOrderQuantity = l_dblCurrentBizDateFuturesBuyOrderQuantity;
    }

    /**
     * (set先物買建注文数量[T+1])<BR>
     * 
     * 引数.先物買建注文数量[T+1]をthis.先物買建注文数量[T+1]にセットする。<BR>
     * @@param l_dblNextBizDateFuturesBuyOrderQuantity - 先物買建注文数量[T+1]
     */
    public void setNextBizDateFuturesBuyOrderQuantity(double l_dblNextBizDateFuturesBuyOrderQuantity)
    {
        this.nextBizDateFuturesBuyOrderQuantity = l_dblNextBizDateFuturesBuyOrderQuantity;
    }

    /**
     * (set先物売建注文数量[T+0])<BR>
     * 
     * 引数.先物売建注文数量[T+0]をthis.先物売建注文数量[T+0]にセットする。<BR>
     * @@param l_dblCurrentBizDateFuturesSellOrderQuantity - 先物売建注文数量[T+0]
     */
    public void setCurrentBizDateFuturesSellOrderQuantity(double l_dblCurrentBizDateFuturesSellOrderQuantity)
    {
        this.currentBizDateFuturesSellOrderQuantity = l_dblCurrentBizDateFuturesSellOrderQuantity;
    }

    /**
     * (set先物売建注文数量[T+1])<BR>
     * 
     * 引数.先物売建注文数量[T+1]をthis.先物売建注文数量[T+1]にセットする。<BR>
     * @@param l_dblNextBizDateFuturesSellOrderQuantity - 先物売建注文数量[T+1]
     */
    public void setNextBizDateFuturesSellOrderQuantity(double l_dblNextBizDateFuturesSellOrderQuantity)
    {
        this.nextBizDateFuturesSellOrderQuantity = l_dblNextBizDateFuturesSellOrderQuantity;
    }

    /**
     * (setオプションプット売建注文数量[T+0])<BR>
     * 
     * 引数.オプションプット売建注文数量[T+0]をthis.オプションプット売建注文数量[T+0]にセットする。<BR>
     * @@param l_dblCurrentBizDateOptionPutSellOrderQuantity - オプションプット売建注文数量[T+0]
     */
    public void setCurrentBizDateOptionPutSellOrderQuantity(double l_dblCurrentBizDateOptionPutSellOrderQuantity)
    {
        this.currentBizDateOptionPutSellOrderQuantity =
            l_dblCurrentBizDateOptionPutSellOrderQuantity;
    }

    /**
     * (setオプションプット売建注文数量[T+1])<BR>
     * 
     * 引数.オプションプット売建注文数量[T+1]をthis.オプションプット売建注文数量[T+1]にセットする。<BR>
     * @@param l_dblNextBizDateOptionPutSellOrderQuantity - オプションプット売建注文数量[T+1]
     */
    public void setNextBizDateOptionPutSellOrderQuantity(double l_dblNextBizDateOptionPutSellOrderQuantity)
    {
        this.nextBizDateOptionPutSellOrderQuantity = l_dblNextBizDateOptionPutSellOrderQuantity;
    }

    /**
     * (setオプションコール売建注文数量[T+0])<BR>
     * 
     * 引数.オプションコール売建注文数量[T+0]をthis.オプションコール売建注文数量[T+0]にセットする。<BR>
     * @@param l_dblCurrentBizDateOptionCallSellOrderQuantity - オプションコール売建注文数量[T+0]
     */
    public void setCurrentBizDateOptionCallSellOrderQuantity(double l_dblCurrentBizDateOptionCallSellOrderQuantity)
    {
        this.currentBizDateOptionCallSellOrderQuantity =
            l_dblCurrentBizDateOptionCallSellOrderQuantity;
    }

    /**
     * (setオプションコール売建注文数量[T+1])<BR>
     * 
     * 引数.オプションコール売建注文数量[T+1]をthis.オプションコール売建注文数量[T+1]にセットする。<BR>
     * @@param l_dblNextBizDateOptionCallSellOrderQuantity - オプションコール売建注文数量[T+1]
     */
    public void setNextBizDateOptionCallSellOrderQuantity(double l_dblNextBizDateOptionCallSellOrderQuantity)
    {
        this.nextBizDateOptionCallSellOrderQuantity = l_dblNextBizDateOptionCallSellOrderQuantity;
    }

    /**
     * (get規定証拠金＜証拠金不足仮確定＞)<BR>
     * 
     * this.規定証拠金＜証拠金不足仮確定＞を返却する。<BR>
     * @@return double
     */
    public double getIfoDepositPerUnitTemp()
    {
        return this.ifoDepositPerUnitTemp;
    }   

    /**
     * (get先物買建数量＜証拠金不足仮確定＞)<BR>
     * 
     * this.先物買建数量＜証拠金不足仮確定＞を返却する。<BR>
     * @@return double
     */
    public double getFuturesBuyContractQuantityTemp()
    {
        return this.futuresBuyContractQuantityTemp;
    }

    /**
     * (get先物売建数量＜証拠金不足仮確定＞)<BR>
     * 
     * this.先物売建数量＜証拠金不足仮確定＞を返却する。<BR>
     * @@return double
     */
    public double getFuturesSellContractQuantityTemp()
    {
        return this.futuresSellContractQuantityTemp;
    }

    /**
     * (getオプションプット売建数量＜証拠金不足仮確定＞)<BR>
     * 
     * this.オプションプット売建数量＜証拠金不足仮確定＞を返却する。<BR>
     * @@return double
     */
    public double getOptionPutSellContractQuantityTemp()
    {
        return this.optionPutSellContractQuantityTemp;
    }

    /**
     * (getオプションコール売建数量＜証拠金不足仮確定＞)<BR>
     * 
     * this.オプションコール売建数量＜証拠金不足仮確定＞を返却する。<BR>
     * @@return double
     */
    public double getOptionCallSellContractQuantityTemp()
    {
        return this.optionCallSellContractQuantityTemp;
    }

}
@
