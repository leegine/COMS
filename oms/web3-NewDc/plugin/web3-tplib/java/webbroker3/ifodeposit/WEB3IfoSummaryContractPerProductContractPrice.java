head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoSummaryContractPerProductContractPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄建単価別先物OP建玉集計(WEB3IfoSummaryContractPerProductContractPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 nakazato(ACT) 新規作成
Revesion History : 2007/07/06 hijikata(SRA) 夕場対応 モデルNo.061④ 計算式書No.022
Revesion History : 2007/08/02 hijikata(SRA) 夕場対応 計算式書No.035
Revesion History : 2007/08/07 k.yamashita(SRA) 夕場対応 計算式書No.038
*/
package webbroker3.ifodeposit;

import java.math.BigDecimal;

import webbroker3.ifodeposit.define.WEB3IfoDepositProfitLossCalcDef;

/**
 * (銘柄建単価別先物OP建玉集計)<BR>
 * 銘柄ID、建単価をキーに集計される建玉集計クラス。<BR>
 * 評価損益の算出に用いられる。<BR>
 *
 *  [集計内容]<BR>
 * 　@・先物/オプション別、買建/売建別の建数量<BR>
 */
public class WEB3IfoSummaryContractPerProductContractPrice extends WEB3IfoSummaryContract
{

    /**
     * (建単価)<BR>
     */
    private double contractPrice;

    /**
     * (コンストラクタ)
     */
    public WEB3IfoSummaryContractPerProductContractPrice()
    {

    }

    /**
     * (create銘柄建単価別先物OP建玉集計)<BR>
     * 
     * 銘柄建単価別先物OP建玉集計を生成する。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerProductContractPrice
     */
    public static WEB3IfoSummaryContractPerProductContractPrice create()
    {
        return new WEB3IfoSummaryContractPerProductContractPrice();
    }

    /**
     * (calc銘柄建単価別先物買建評価損益)<BR>
     * 
     * 
     * 「銘柄建単価別先物買建評価損益」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@return double
     */
    public double calcFuturesBuyContractProfitLoss()
    {
        //銘柄建単価別先物買建評価損益
        double l_dblProfitLoss = 0;

        //先物の場合
        if (this.isFutures() == true)
        {
            //時価(BigDecimal型)
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //建単価(BigDecimal型)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //先物買建数量(BigDecimal型)
            BigDecimal l_bdBuyQuantity;
            //先物の評価損益計算に当日建を含めない場合
            if(WEB3IfoDepositProfitLossCalcDef.EXCEPT_TODAY_CONTRACT.equals(this.getProfitLossCalcType()))
            {
                l_bdBuyQuantity = new BigDecimal(this.getBuyQuantity() - this.getTodayBuyQuantity());
            }
            else
            {
                l_bdBuyQuantity = new BigDecimal(this.getBuyQuantity());
            }
            //指数乗数(BigDecimal型)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //銘柄建単価別先物買建評価損益　@=　@(時価　@?　@建単価)　@×　@先物買建数量　@×　@指数乗数
            BigDecimal l_bdProfitLoss =
                l_bdCurrentPrice.subtract(l_bdContractPrice).multiply(l_bdBuyQuantity).multiply(
                    l_bdUnitSize);

            //少数点以下切捨て処理を行う
            l_bdProfitLoss = l_bdProfitLoss.setScale(0, BigDecimal.ROUND_DOWN);
            //計算した銘柄単価別先物買建評価損益をdoubleに変換する
            l_dblProfitLoss = l_bdProfitLoss.doubleValue();
        }
        //以外の場合(オプションの場合)
        else
        {
            //銘柄建単価別先物買建評価損益に0を代入
            l_dblProfitLoss = 0;
        }

        //計算した銘柄建単価別先物買建評価損益を返却する。
        return l_dblProfitLoss;
    }

    /**
     * (calc銘柄建単価別先物売建評価損益)<BR>
     * 
     * 「銘柄建単価別先物売建評価損益」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@return double
     */
    public double calcFuturesSellContractProfitLoss()
    {
        //銘柄建単価別先物売建評価損益
        double l_dblProfitLoss = 0;

        //先物の場合
        if (this.isFutures() == true)
        {
            //時価(BigDecimal型)
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //建単価(BigDecimal型)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //先物売建数量(BigDecimal型)
            BigDecimal l_bdSellQuantity;
            //先物の評価損益計算に当日建を含めない場合
            if(WEB3IfoDepositProfitLossCalcDef.EXCEPT_TODAY_CONTRACT.equals(this.getProfitLossCalcType()))
            {
                l_bdSellQuantity = new BigDecimal(this.getSellQuantity() - this.getTodaySellQuantity());
            }
            else
            {
                l_bdSellQuantity = new BigDecimal(this.getSellQuantity());
            }
            //指数乗数(BigDecimal型)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());
            
            //銘柄建単価別先物売建評価損益　@=　@(建単価 - 時価)　@×　@先物売建数量　@×　@指数乗数
            BigDecimal l_bdProfitLoss =
                l_bdContractPrice.subtract(l_bdCurrentPrice).multiply(l_bdSellQuantity).multiply(l_bdUnitSize);

            //少数点以下切捨て処理を行う
            l_bdProfitLoss = l_bdProfitLoss.setScale(0, BigDecimal.ROUND_DOWN);
            //計算した銘柄単価別先物売建評価損益をdoubleに変換する
            l_dblProfitLoss = l_bdProfitLoss.doubleValue();
        }
        //以外の場合(オプションの場合)
        else
        {
            //銘柄建単価別先物売建評価損益に0を代入
            l_dblProfitLoss = 0;
        }

        //計算した銘柄建単価別先物売建評価損益を返却する。
        return l_dblProfitLoss;
    }

    /**
     * (calc銘柄建単価別オプション買建評価損益)<BR>
     * 
     * 
     * 「銘柄建単価別オプション買建評価損益」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@return double
     */
    public double calcOptionBuyContractProfitLoss()
    {
        //銘柄建単価別オプション買建評価損益
        double l_dblProfitLoss = 0;

        //オプションの場合
        if (this.isFutures() == false)
        {
            //時価(BigDecimal型)
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //建単価(BigDecimal型)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //オプション買建数量(BigDecimal型)
            BigDecimal l_bdBuyQuantity = new BigDecimal(this.getBuyQuantity());
            //指数乗数(BigDecimal型)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //銘柄建単価別オプション買建評価損益　@=　@(時価　@?　@建単価)　@×　@オプション買建数量　@×　@指数乗数
            BigDecimal l_bdProfitLoss =
                l_bdCurrentPrice.subtract(l_bdContractPrice).multiply(l_bdBuyQuantity).multiply(
                    l_bdUnitSize);

            //少数点以下切捨て処理を行う
            l_bdProfitLoss = l_bdProfitLoss.setScale(0, BigDecimal.ROUND_DOWN);
            //計算した銘柄単価別先物買建評価損益をdoubleに変換する
            l_dblProfitLoss = l_bdProfitLoss.doubleValue();
        }
        //以外の場合(先物の場合)
        else
        {
            //銘柄建単価別オプション買建評価損益に0を代入
            l_dblProfitLoss = 0;
        }

        //計算した銘柄建単価別オプション買建評価損益を返却する。
        return l_dblProfitLoss;
    }

    /**
     * (calc銘柄建単価別オプション売建評価損益)<BR>
     * 
     * 「銘柄建単価別オプション売建評価損益」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@return double
     */
    public double calcOptionSellContractProfitLoss()
    {
        //銘柄建単価別オプション売建評価損益
        double l_dblProfitLoss = 0;

        //オプションの場合
        if (this.isFutures() == false)
        {
            //時価(BigDecimal型)
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //建単価(BigDecimal型)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //オプション売建数量(BigDecimal型)
            BigDecimal l_bdSellQuantity = new BigDecimal(this.getSellQuantity());
            //指数乗数(BigDecimal型)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //銘柄建単価別オプション売建評価損益　@=　@(建単価 - 時価)　@×　@オプション売建数量　@×　@指数乗数
            BigDecimal l_bdProfitLoss =
                l_bdContractPrice.subtract(l_bdCurrentPrice).multiply(l_bdSellQuantity).multiply(
                    l_bdUnitSize);

            //少数点以下切捨て処理を行う
            l_bdProfitLoss = l_bdProfitLoss.setScale(0, BigDecimal.ROUND_DOWN);
            //計算した銘柄単価別オプション売建評価損益をdoubleに変換する
            l_dblProfitLoss = l_bdProfitLoss.doubleValue();
        }
        //以外の場合(先物の場合)
        else
        {
            //銘柄建単価別オプション売建評価損益に0を代入
            l_dblProfitLoss = 0;
        }

        //計算した銘柄建単価別オプション売建評価損益を返却する。
        return l_dblProfitLoss;
    }

    /**
     * (calc銘柄建単価別先物買建評価損益＜証拠金不足仮確定＞)<BR>
     * 
     * 
     * 「銘柄建単価別先物買建評価損益＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@return double
     */
    public double calcFuturesBuyContractProfitLossTemp()
    {
        //銘柄建単価別先物買建評価損益＜証拠金不足仮確定＞
        double l_dblProfitLossTemp = 0;

        //先物の場合
        if (this.isFutures() == true)
        {
            //当日清算値  
            double l_dblPrice = this.getCurrentBizDateLiquidationPrice();
            if (l_dblPrice == 0) 
            {
                //当日清算値==ZEROの場合、時価
                l_dblPrice = this.getCurrentPrice();
            }
            //当日清算値(BigDecimal型) 
            BigDecimal l_bdPrice = new BigDecimal(Double.toString(l_dblPrice));
            //建単価(BigDecimal型) 
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //先物買建数量(BigDecimal型)
            BigDecimal l_bdBuyQuantity = new BigDecimal(this.getBuyQuantityTemp());

            //指数乗数(BigDecimal型)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //銘柄建単価別先物買建評価損益＜証拠金不足仮確定＞ = (当日清算値 - 建単価) × 先物買建数量 × 指数乗数
            BigDecimal l_bdProfitLossTemp =
                l_bdPrice.subtract(l_bdContractPrice).multiply(l_bdBuyQuantity).multiply(
                    l_bdUnitSize);

            //小数点以下切り捨て
            l_bdProfitLossTemp = l_bdProfitLossTemp.setScale(0, BigDecimal.ROUND_DOWN);
            //計算した銘柄単価別先物買建評価損益＜証拠金不足仮確定＞をdoubleに変換する
            l_dblProfitLossTemp = l_bdProfitLossTemp.doubleValue();
        }
        //オプションの場合
        else
        {
            l_dblProfitLossTemp = 0;
        }

         return l_dblProfitLossTemp;
    }

    /**
     * (calc銘柄建単価別先物売建評価損益＜証拠金不足仮確定＞)<BR>
     * 
     * 「銘柄建単価別先物売建評価損益＜証拠金不足仮確定＞」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * @@return double
     */
    public double calcFuturesSellContractProfitLossTemp()
    {
        //銘柄建単価別先物売建評価損益＜証拠金不足仮確定＞
        double l_dblProfitLossTemp = 0;

        //先物の場合
        if (this.isFutures() == true)
        {
            //当日清算値  
            double l_dblPrice = this.getCurrentBizDateLiquidationPrice();
            if (l_dblPrice == 0) 
            {
                //当日清算値==ZEROの場合、時価
                l_dblPrice = this.getCurrentPrice();
            }
            //当日清算値(BigDecimal型) 
            BigDecimal l_bdPrice = new BigDecimal(Double.toString(l_dblPrice));
            //建単価(BigDecimal型)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //先物売建数量(BigDecimal型)
            BigDecimal l_bdSellQuantity = new BigDecimal(this.getSellQuantityTemp());
			           
            //指数乗数(BigDecimal型)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());
            
            //銘柄建単価別先物売建評価損益＜証拠金不足仮確定＞ = (建単価 - 当日清算値) × 先物売建数量 × 指数乗数
            BigDecimal l_bdProfitLossTemp =
                l_bdContractPrice.subtract(l_bdPrice).multiply(l_bdSellQuantity).multiply(l_bdUnitSize);

            //小数点以下切り捨て
            l_bdProfitLossTemp = l_bdProfitLossTemp.setScale(0, BigDecimal.ROUND_DOWN);
            //計算した銘柄単価別先物売建評価損益＜証拠金不足仮確定＞をdoubleに変換する
            l_dblProfitLossTemp = l_bdProfitLossTemp.doubleValue();
        }
        //オプションの場合
        else
        {
            l_dblProfitLossTemp = 0;
        }

        return l_dblProfitLossTemp;
    }

    /**
     * (get建単価)<BR>
     * 
     * this.建単価を返却する。<BR>
     * @@return double
     */
    public double getContractPrice()
    {
        return this.contractPrice;
    }

    /**
     * (set建単価)<BR>
     * 
     * 引数.建単価をthis.建単価にセットする。<BR>
     * @@param l_dblContractPrice - (建単価)<BR>
     */
    public void setContractPrice(double l_dblContractPrice)
    {
        this.contractPrice = l_dblContractPrice;
    }
}
@
