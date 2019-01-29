head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoSummaryContractPerProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄別先物OP建玉集計(WEB3IfoSummaryContractPerProduct.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 nakazato(ACT) 新規作成
Revesion History : 2007/07/06 hijikata(SRA) 夕場対応 
                        モデルNo.061⑤
                        計算式書No.022, No.024, No.026
Revesion History : 2007/08/02 hijikata(SRA) 夕場対応 計算式書No.035
*/

package webbroker3.ifodeposit;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;

/**
 * (銘柄別先物OP建玉集計)<BR>
 * 銘柄IDをキーに集計される建玉集計クラス。<BR>
 * ネットオプション価値総額、および、SPAN証拠金の算出に用いられる。<BR>
 * [集計内容]<BR>
 * 　@・先物/オプション別、買建/売建別の建数量<BR>
 * 　@・先物/オプション別、買建/売建別の注文数量<BR>
 * 
 * @@author  nakazato(ACT)
 */
public class WEB3IfoSummaryContractPerProduct extends WEB3IfoSummaryContract
{
    /**
     * (買建注文数量[T+0])<BR>
     */
    private double currentBizDateBuyOrderQuantity = 0;

    /**
     * 買建注文数量[T+1]
     */
    private double nextBizDateBuyOrderQuantity = 0;

    /**
     * 売建注文数量[T+0]
     */
    private double currentBizDateSellOrderQuantity = 0;

    /**
     * 売建注文数量[T+1]
     */
    private double nextBizDateSellOrderQuantity = 0;

    /**
     * (コンストラクタ)
     */
    public WEB3IfoSummaryContractPerProduct()
    {

    }

    /**
     * (create銘柄別先物OP建玉集計)<BR>
     * 
     * 銘柄別先物OP建玉集計を生成する。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerProduct
     */
    public static WEB3IfoSummaryContractPerProduct create()
    {
        return new WEB3IfoSummaryContractPerProduct();
    }

    /**
     * (add注文数量)<BR>
     * 
     * 買建/売建であるか、発注日がいつであるかにもとづき、該当する注文数量の加算処理を行う<BR>
     * 
     * １）　@買建[T+0](引数.is買建 == true && 引数.発注日 == 
     * 引数.営業日[T+0])の場合<BR>
     * 　@　@　@this.買建注文数量[T+0] = this.get買建注文数量[T+0]( ) + 引数.数量<BR>
     * 
     * ２）　@買建[T+1](引数.is買建 == true && 引数.発注日 == 
     * 引数.営業日[T+1])の場合<BR>
     * 　@　@　@this.買建注文数量[T+1] = this.get買建注文数量[T+1]( ) + 引数.数量<BR>
     * 
     * ３）　@売建[T+0](引数.is買建 == false && 引数.発注日 == 
     * 引数.営業日<BR>[T+0])の場合<BR>
     * 　@　@　@this.売建注文数量[T+0] = this.get売建注文数量[T+0]( ) + 引数.数量<BR>
     * 
     * ４）　@売建[T+1](引数.is買建 == false && 引数.発注日 == 
     * 引数.営業日<BR>[T+1])の場合<BR>
     * 　@　@　@this.売建注文数量[T+1] = this.get売建注文数量[T+1]( ) + 引数.数量<BR>
     * @@param l_blnIsBuy - (is買建)<BR>
     * 
     * 建玉が買建である場合、true。以外、false。<BR>
     * @@param l_datOrderBizDate - 発注日。
     * @@param l_datCurrentBizDate - (営業日[T+0])
     * @@param l_datNextBizDate - (営業日[T+1])
     * @@param l_dblQuantity - 数量。
     */
    public void addOrderQuantity(
        boolean l_blnIsBuy,
        Date l_datOrderBizDate,
        Date l_datCurrentBizDate,
        Date l_datNextBizDate,
        double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "addOrderQuantity()";

        //買建[T+0]の時
        if (l_blnIsBuy == true && l_datOrderBizDate.equals(l_datCurrentBizDate))
        {
            //this.買建注文数量[T+0] = this.get買建注文数量[T+0]( ) + 引数.数量
            this.currentBizDateBuyOrderQuantity =
                this.currentBizDateBuyOrderQuantity + l_dblQuantity;
        }
        //買建[T+1]の時
        else if (l_blnIsBuy == true && l_datOrderBizDate.equals(l_datNextBizDate))
        {
            //this.買建注文数量[T+1] = this.get買建注文数量[T+1]( ) + 引数.数量
            this.nextBizDateBuyOrderQuantity = this.nextBizDateBuyOrderQuantity + l_dblQuantity;
        }
        //売建[T+0]の時
        else if (l_blnIsBuy == false && l_datOrderBizDate.equals(l_datCurrentBizDate))
        {
            //this.売建注文数量[T+0] = this.get売建注文数量[T+0]( ) + 引数.数量
            this.currentBizDateSellOrderQuantity =
                this.currentBizDateSellOrderQuantity + l_dblQuantity;
        }
        //売建[T+1]の時
        else if (l_blnIsBuy == false && l_datOrderBizDate.equals(l_datNextBizDate))
        {
            //this.売建注文数量[T+1] = this.get売建注文数量[T+1]( ) + 引数.数量
            this.nextBizDateSellOrderQuantity = this.nextBizDateSellOrderQuantity + l_dblQuantity;
        }
        //以外の時
        else
        {
        }
    }

    /**
     * (calc買建数量)<BR>
     * 
     * 引数で指定された指定日(=n)の、「買建数量」を算出する。<BR>
     * 
     * １）　@n == 0の場合(引数.指定日 == 0)<BR>
     * 　@　@　@this.get買建数量( )<BR>
     *       + this.get買建注文数量[T+0]( )を返却する。<BR>
     * 
     * ２）　@n == 1、または2の場合(引数.指定日 != 0)<BR>
     * 　@　@　@this.get買建数量( )<BR>
     *       + this.get買建注文数量[T+0]( )<BR>
     *       + this.get買建注文数量[T+1]( )を返却する。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyContractQuantity(int l_intReservedDate)
    {
        //買建数量
        double l_dblBuyContractQuantity = 0;

        //引数.指定日が0の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //買建数量 = this.get買建数量( ) + this.get買建注文数量[T+0]( )
            l_dblBuyContractQuantity =
                this.getBuyQuantity() + this.getCurrentBizDateBuyOrderQuantity();
        }
        //引数.指定日が1または2の時
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //買建数量 = this.get買建数量( ) + this.get買建注文数量[T+0]( ) + this.get買建注文数量[T+1]( )
            l_dblBuyContractQuantity =
                this.getBuyQuantity()
                    + this.getCurrentBizDateBuyOrderQuantity()
                    + this.getNextBizDateBuyOrderQuantity();
        }
        //以外の時
        else
        {
            //買建数量に0を代入
            l_dblBuyContractQuantity = 0;
        }

        //計算した買建数量を返却する。
        return l_dblBuyContractQuantity;
    }

    /**
     * (calc売建数量)<BR>
     * 
     * 
     * 引数で指定された指定日(=n)の、「売建数量」を算出する。<BR>
     * 
     * １）　@n == 0の場合(引数.指定日 == 0)<BR>
     * 　@　@　@this.get売建数量( )<BR>
     *       + this.get売建注文数量[T+0]( )を返却する。<BR>
     * 
     * ２）　@n == 1、または2の場合(引数.指定日 != 0)<BR>
     * 　@　@　@this.get売建数量( )<BR>
     *       + this.get売建注文数量[T+0]( )<BR>
     *       + this.get売建注文数量[T+1]( )を返却する。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellContractQuantity(int l_intReservedDate)
    {
        //売建数量
        double l_dblSellContractQuantity = 0;

        //引数.指定日が0の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //売建数量 = this.get売建数量( ) + this.get売建注文数量[T+0]( )
            l_dblSellContractQuantity =
                this.getSellQuantity() + this.getCurrentBizDateSellOrderQuantity();
        }
        //引数.指定日が1または2の時
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //売建数量 = this.get売建数量( ) + this.get売建注文数量[T+0]( ) + this.get売建注文数量[T+1]( )
            l_dblSellContractQuantity =
                this.getSellQuantity()
                    + this.getCurrentBizDateSellOrderQuantity()
                    + this.getNextBizDateSellOrderQuantity();
        }
        //以外の時
        else
        {
            //売建数量に0を代入
            l_dblSellContractQuantity = 0;
        }

        //計算した売建数量を返却する。
        return l_dblSellContractQuantity;
    }

    /**
     * (calc銘柄別オプション買建数)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別オプション買建数」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionBuyContractQuantity(int l_intReservedDate)
    {
        //銘柄別オプション買建数
        double l_dblOptionBuyContractQuantity = 0;

        //先物の時
        if (this.isFutures() == true)
        {
            //銘柄別オプション買建数量に0を代入する
            l_dblOptionBuyContractQuantity = 0;
        }
        //オプションの時
        else
        {
            //引数.指定日が0の時
            if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
            {
                //銘柄別オプション買建数　@=　@銘柄別先物OP建玉集計.get買建数量()
                //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 銘柄別先物OP建玉集計.get買建注文数量[T+0]()
                l_dblOptionBuyContractQuantity =
                    this.getBuyQuantity() + this.getCurrentBizDateBuyOrderQuantity();
            }
            //引数.指定日が1または2の時
            else if (
                l_intReservedDate == WEB3IfoReservedDateDef.T_1
                    || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
            {
                //銘柄別オプション買建数　@=　@銘柄別先物OP建玉集計.get買建数量()
                //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 銘柄別先物OP建玉集計.get買建注文数量[T+0]()
                //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 銘柄別先物OP建玉集計.get買建注文数量[T+1]()
                l_dblOptionBuyContractQuantity =
                    this.getBuyQuantity()
                        + this.getCurrentBizDateBuyOrderQuantity()
                        + this.getNextBizDateBuyOrderQuantity();

            }
            //以外の時
            else
            {
                //銘柄別オプション買建数量に0を代入する
                l_dblOptionBuyContractQuantity = 0;
            }
        }

        //計算した銘柄別オプション買建数量を返却する
        return l_dblOptionBuyContractQuantity;
    }

    /**
     * (calc銘柄別オプション売建数)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別オプション売建数」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionSellContractQuantity(int l_intReservedDate)
    {
        //銘柄別オプション売建数
        double l_dblOptionSellContractQuantity = 0;

        //先物の時
        if (this.isFutures() == true)
        {
            //銘柄別オプション売建数量に0を代入する
            l_dblOptionSellContractQuantity = 0;
        }
        //オプションの時
        else
        {
            //引数.指定日が0、1、2の時
            if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
                || l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
            {
                //銘柄別オプション売建数　@=　@銘柄別先物OP建玉集計.get売建数量()
                l_dblOptionSellContractQuantity = this.getSellQuantity();
            }
            //以外の時
            else
            {
                //銘柄別オプション売建数量に0を代入する                
                l_dblOptionSellContractQuantity = 0;
            }
        }

        //計算した銘柄別オプション売建数量を返却する
        return l_dblOptionSellContractQuantity;
    }

    /**
     * (calc銘柄別オプション買超過建玉数)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別オプション買超過建玉数」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日）<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcOptionBuyOverContractQuantity(int l_intReservedDate)
    {
        //銘柄別オプション買超過建玉数
        double l_dblOptionBuyOverContractQuantity = 0;

        //引数.指定日が0、1、2の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
            || l_intReservedDate == WEB3IfoReservedDateDef.T_1
            || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //銘柄別オプション買超過建玉数　@=　@Max(0, (銘柄別オプション買建数(n) － 銘柄別オプション売建数(n)))
            l_dblOptionBuyOverContractQuantity =
                Math.max(
                    0,
                    this.calcOptionBuyContractQuantity(l_intReservedDate)
                        - this.calcOptionSellContractQuantity(l_intReservedDate));
        }
        //以外の時
        else
        {
            //銘柄別オプション買超過建玉数に0を代入
            l_dblOptionBuyOverContractQuantity = 0;
        }

        return l_dblOptionBuyOverContractQuantity;
    }

    /**
     * (calc銘柄別オプション売超過建玉数)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別オプション売超過建玉数」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionSellOverContractQuantity(int l_intReservedDate)
    {
        //銘柄別オプション売超過建玉数
        double l_dblOptionSellOverContractQuantity = 0;

        //引数.指定日が0、1、2の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
            || l_intReservedDate == WEB3IfoReservedDateDef.T_1
            || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //銘柄別オプション売超過建玉数　@=　@Max(0, (銘柄別オプション売建数(n) － 銘柄別オプション買建数(n)))
            l_dblOptionSellOverContractQuantity =
                Math.max(
                    0,
                    this.calcOptionSellContractQuantity(l_intReservedDate)
                        - this.calcOptionBuyContractQuantity(l_intReservedDate));
        }
        //以外の時
        else
        {
            //銘柄別オプション売超過建玉数に0を代入
            l_dblOptionSellOverContractQuantity = 0;
        }

        return l_dblOptionSellOverContractQuantity;
    }

    /**
     * (calc銘柄別買オプション価値)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別買オプション価値」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyOptionValue(int l_intReservedDate)
    {
        //銘柄別買オプション価値
        double l_dblBuyOptionValue = 0;

        //引数.指定日が0、1、2の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
            || l_intReservedDate == WEB3IfoReservedDateDef.T_1
            || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {

            //銘柄別買超過建玉数(n)
            BigDecimal l_bdOverContract =
                new BigDecimal(this.calcOptionBuyOverContractQuantity(l_intReservedDate));
            //時価
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //指数乗数
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //銘柄別買オプション価値　@=　@銘柄別オプション買超過建玉数(n)　@×　@時価　@×　@指数乗数
            BigDecimal l_bdBuyOptionValue =
                l_bdOverContract.multiply(l_bdCurrentPrice).multiply(l_bdUnitSize);
                
            //少数点以下切捨て処理を行う
            l_bdBuyOptionValue = l_bdBuyOptionValue.setScale(0, BigDecimal.ROUND_DOWN);
            //計算した銘柄別買オプション価値(BigDecimal型)をdouble型に変換する
            l_dblBuyOptionValue = l_bdBuyOptionValue.doubleValue();
        }
        //以外の時
        else
        {
            //銘柄別買オプション価値に0を代入する
            l_dblBuyOptionValue = 0;
        }

        return l_dblBuyOptionValue;
    }

    /**
     * (calc銘柄別売オプション価値)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別売オプション価値」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellOptionValue(int l_intReservedDate)
    {
        //銘柄別売オプション価値
        double l_dblSellOptionValue = 0;

        //引数.指定日が0、1、2の時
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
            || l_intReservedDate == WEB3IfoReservedDateDef.T_1
            || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {

            //銘柄別売超過建玉数(n)
            BigDecimal l_bdOverContract =
                new BigDecimal(this.calcOptionSellOverContractQuantity(l_intReservedDate));
            //時価
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //指数乗数
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //銘柄別売オプション価値　@=　@銘柄別オプション売超過建玉数(n)　@×　@時価　@×　@指数乗数
            BigDecimal l_bdSellOptionValue =
                l_bdOverContract.multiply(l_bdCurrentPrice).multiply(l_bdUnitSize);
                
            //少数点以下切捨て処理を行う
            l_bdSellOptionValue = l_bdSellOptionValue.setScale(0, BigDecimal.ROUND_DOWN);
            //計算した銘柄別売オプション価値(BigDecimal型)をdouble型に変換する
            l_dblSellOptionValue = l_bdSellOptionValue.doubleValue();
        }
        //以外の時
        else
        {
            //銘柄別売オプション価値に0を代入する
            l_dblSellOptionValue = 0;
        }

        return l_dblSellOptionValue;
    }


   /**
     * (calc銘柄別オプション買建数＜証拠金不足仮確定＞ )<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別オプション買建数＜証拠金不足仮確定＞ 」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionBuyContractQuantityTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //銘柄別オプション買建数＜証拠金不足仮確定＞ 
        double l_dblOptionBuyContractQuantityTemp = 0;

        //先物の場合
        if (this.isFutures() == true)
        {
            //銘柄別オプション買建数量＜証拠金不足仮確定＞ = 0
            l_dblOptionBuyContractQuantityTemp = 0;
        }
        //オプションの場合
        else
        {
            //銘柄別オプション買建数＜証拠金不足仮確定＞
            //    = 買建数量＜証拠金不足仮確定＞
           l_dblOptionBuyContractQuantityTemp =
                    this.getBuyQuantityTemp();
        }

        //計算した銘柄別オプション買建数量＜証拠金不足仮確定＞ を返却する
        return l_dblOptionBuyContractQuantityTemp;
    }

    /**
     * (calc銘柄別オプション売建数＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別オプション売建数＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionSellContractQuantityTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //銘柄別オプション売建数＜証拠金不足仮確定＞
        double l_dblOptionSellContractQuantityTemp = 0;

        //先物の場合
        if (this.isFutures() == true)
        {
            //銘柄別オプション売建数量＜証拠金不足仮確定＞ = 0
            l_dblOptionSellContractQuantityTemp = 0;
        }
        //オプションの場合
        else
        {
            //銘柄別オプション売建数＜証拠金不足仮確定＞ = 
            //  売建数量＜証拠金不足仮確定＞
            l_dblOptionSellContractQuantityTemp = 
                this.getSellQuantityTemp();
        }

        //計算した銘柄別オプション売建数量を＜証拠金不足仮確定＞返却する
        return l_dblOptionSellContractQuantityTemp;
    }

    /**
     * (calc銘柄別オプション買超過建玉数＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別オプション買超過建玉数＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日）<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcOptionBuyOverContractQuantityTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }
        
        //銘柄別オプション買超過建玉数＜証拠金不足仮確定＞
        double l_dblOptionBuyOverContractQuantityTemp = 0;

        //銘柄別オプション買超過建玉数＜証拠金不足仮確定＞ = 
        //    Max(0, (銘柄別オプション買建数＜証拠金不足仮確定＞ - 銘柄別オプション売建数＜証拠金不足仮確定＞ (n)))
        l_dblOptionBuyOverContractQuantityTemp =
            Math.max(
                0,
                (this.calcOptionBuyContractQuantityTemp(l_intReservedDate)
                    - this.calcOptionSellContractQuantityTemp(l_intReservedDate)));

        return l_dblOptionBuyOverContractQuantityTemp;
    }

    /**
     * (calc銘柄別オプション売超過建玉数＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別オプション売超過建玉数＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionSellOverContractQuantityTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //銘柄別オプション売超過建玉数＜証拠金不足仮確定＞
        double l_dblOptionSellOverContractQuantityTemp = 0;

        //銘柄別オプション売超過建玉数＜証拠金不足仮確定＞ = 
        //    Max(0, (銘柄別オプション売建数＜証拠金不足仮確定＞ - 銘柄別オプション買建数＜証拠金不足仮確定＞ (n)))
        l_dblOptionSellOverContractQuantityTemp =
            Math.max(
                0,
                (this.calcOptionSellContractQuantityTemp(l_intReservedDate)
                    - this.calcOptionBuyContractQuantityTemp(l_intReservedDate)));

        return l_dblOptionSellOverContractQuantityTemp;
    }

    /**
     * (calc銘柄別買オプション価値＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別買オプション価値＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyOptionValueTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //銘柄別買オプション価値＜証拠金不足仮確定＞
        double l_dblBuyOptionValueTemp = 0;

        //銘柄別買超過建玉数＜証拠金不足仮確定＞
        BigDecimal l_bdOverContractTemp =
            new BigDecimal(this.calcOptionBuyOverContractQuantityTemp(l_intReservedDate));

        //当日清算値  
        double l_dblPrice = this.getCurrentBizDateLiquidationPrice();
        if (l_dblPrice == 0) 
        {
            //当日清算値==ZEROの場合、時価
            l_dblPrice = this.getCurrentPrice();
        }
        //当日清算値(BigDecimal型) 
        BigDecimal l_bdPrice = new BigDecimal(Double.toString(l_dblPrice));
        //指数乗数
        BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

        //銘柄別売オプション価値＜証拠金不足仮確定＞ = 
        //    銘柄別オプション売超過建玉数＜証拠金不足仮確定＞×当日清算値×指数乗数
        BigDecimal l_bdBuyOptionValueTemp =
            l_bdOverContractTemp.multiply(l_bdPrice).multiply(l_bdUnitSize);
             
        //小数点以下切り捨て
        l_bdBuyOptionValueTemp = l_bdBuyOptionValueTemp.setScale(0, BigDecimal.ROUND_DOWN);

        //計算した銘柄別買オプション価値(BigDecimal型)をdouble型に変換する
        l_dblBuyOptionValueTemp = l_bdBuyOptionValueTemp.doubleValue();

        return l_dblBuyOptionValueTemp;
    }

    /**
     * (calc銘柄別売オプション価値＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「銘柄別売オプション価値＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellOptionValueTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        //銘柄別売オプション価値＜証拠金不足仮確定＞
        double l_dblSellOptionValueTemp = 0;

        //銘柄別売超過建玉数＜証拠金不足仮確定＞
        BigDecimal l_bdOverContractTemp =
            new BigDecimal(this.calcOptionSellOverContractQuantityTemp(l_intReservedDate));
        //当日清算値  
        double l_dblPrice = this.getCurrentBizDateLiquidationPrice();
        if (l_dblPrice == 0) 
        {
            //当日清算値==ZEROの場合、時価
            l_dblPrice = this.getCurrentPrice();
        }
        //当日清算値(BigDecimal型) 
        BigDecimal l_bdPrice = new BigDecimal(Double.toString(l_dblPrice));
        //指数乗数
        BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());
        //銘柄別売オプション価値＜証拠金不足仮確定＞ =
        //銘柄別オプション売超過建玉数＜証拠金不足仮確定＞ × 当日清算値 × 指数乗数
        BigDecimal l_bdSellOptionValueTemp =
            l_bdOverContractTemp.multiply(l_bdPrice).multiply(l_bdUnitSize);
                
        //小数点以下切り捨て
        l_bdSellOptionValueTemp = l_bdSellOptionValueTemp.setScale(0, BigDecimal.ROUND_DOWN);
        //計算した銘柄別売オプション価値(BigDecimal型)をdouble型に変換する
        l_dblSellOptionValueTemp = l_bdSellOptionValueTemp.doubleValue();

        return l_dblSellOptionValueTemp;
    }
    
    /**
     * (get買建注文数量[T+0])<BR>
     * 
     * this.買建注文数量[T+0]を返却する。<BR>
     * @@return double
     */
    public double getCurrentBizDateBuyOrderQuantity()
    {
        return this.currentBizDateBuyOrderQuantity;
    }

    /**
     * (get買建注文数量[T+1])<BR>
     * 
     * this.買建注文数量[T+1]を返却する。<BR>
     * @@return double
     */
    public double getNextBizDateBuyOrderQuantity()
    {
        return this.nextBizDateBuyOrderQuantity;
    }

    /**
     * (get売建注文数量[T+0])<BR>
     * 
     * this.売建注文数量[T+0]を返却する。<BR>
     * @@return double
     */
    public double getCurrentBizDateSellOrderQuantity()
    {
        return this.currentBizDateSellOrderQuantity;
    }

    /**
     * (get売建注文数量[T+1])<BR>
     * 
     * this.売建注文数量[T+1]を返却する。<BR>
     * @@return double
     */
    public double getNextBizDateSellOrderQuantity()
    {
        return this.nextBizDateSellOrderQuantity;
    }

    /**
     * (set買建注文数量[T+0])<BR>
     * 
     * 引数.買建注文数量[T+0]をthis.買建注文数量[T+0]にセットする。<BR>
     * @@param l_dblCurrentBizDateBuyOrderQuantity - 買建注文数量[T+0]
     */
    public void setCurrentBizDateBuyOrderQuantity(double l_dblCurrentBizDateBuyOrderQuantity)
    {
        this.currentBizDateBuyOrderQuantity = l_dblCurrentBizDateBuyOrderQuantity;
    }

    /**
     * (set買建注文数量[T+1])<BR>
     * 
     * 引数.買建注文数量[T+1]をthis.買建注文数量[T+1]にセットする。<BR>
     * @@param l_dblNextBizDateBuyOrderQuantity - 買建注文数量[T+1]
     */
    public void setNextBizDateBuyOrderQuantity(double l_dblNextBizDateBuyOrderQuantity)
    {
        this.nextBizDateBuyOrderQuantity = l_dblNextBizDateBuyOrderQuantity;
    }

    /**
     * (set売建注文数量[T+0])<BR>
     * 
     * 引数.売建注文数量[T+0]をthis.売建注文数量[T+0]にセットする。<BR>
     * @@param l_dblCurrentBizDateSellOrderQuantity - 売建注文数量[T+0]
     */
    public void setCurrentBizDateSellOrderQuantity(double l_dblCurrentBizDateSellOrderQuantity)
    {
        this.currentBizDateSellOrderQuantity = l_dblCurrentBizDateSellOrderQuantity;
    }

    /**
     * (set売建注文数量[T+1])<BR>
     * 
     * 引数.売建注文数量[T+1]をthis.売建注文数量[T+1]にセットする。<BR>
     * @@param l_dblNextBizDateSellOrderQuantity - 売建注文数量[T+1]
     */
    public void setNextBizDateSellOrderQuantity(double l_dblNextBizDateSellOrderQuantity)
    {
        this.nextBizDateSellOrderQuantity = l_dblNextBizDateSellOrderQuantity;
    }

}
@
