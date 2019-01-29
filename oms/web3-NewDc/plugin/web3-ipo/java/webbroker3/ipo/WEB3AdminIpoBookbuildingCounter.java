head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoBookbuildingCounter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ブックビルディング状況集計結果データ(WEB3AdminIpoBookbuildingCounter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo;




/**
 * ブックビルディング状況集計結果データクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIpoBookbuildingCounter 
{
   
    /**
     * 抽選対象申告件数
     */
    private double lotTargetOrderNumber = 0;
    
    /**
     * 取消件数
     */
    private double cancelNumber = 0;
    
    /**
     * 抽選対象申告数量
     */
    private double lotTargetOrderQuantity = 0;
    
    /**
     * 取消数量
     */
    private double cancelQuantity = 0;
    
    /**
     * 申告価格（計算単価）の合計値
     */
    private double orderPriceTotal = 0;
    
    /**
     * 出金余力保持者合計人数
     */
    private long tradingPowerHolderTotalNumber = 0;
    
    /**
     * 出金余力保持者合計数量
     */
    private double tradingPowerHolderTotalQuantity = 0;
    
    /**
     * デフォルトコンストラクタ
     * @@return webbroker3.ipo.WEB3AdminIPOBookBuildingStateTotalResult
     * @@roseuid 40E36D9803B6
     */
    public WEB3AdminIpoBookbuildingCounter() 
    {
     
    }
    
    /**
     * (get抽選対象申告件数)<BR>
     * 抽選対象申告件数を取得する。<BR>
     * <BR>
     * this.抽選対象申告件数を返却する。<BR>
     * @@return double
     * @@roseuid 40E370C500C8
     */
    public double getLotTargetOrderNumber() 
    {
        return this.lotTargetOrderNumber;
    }
    
    /**
     * (get取消件数)<BR>
     * 取消件数を取得する。<BR>
     * <BR>
     * this.取消件数を返却する。<BR>
     * @@return double
     * @@roseuid 40E370E60145
     */
    public double getCancelNumber() 
    {
        return this.cancelNumber;
    }
    
    /**
     * (get全申告件数)<BR>
     * 全申告件数を取得する。<BR>
     * <BR>
     * （this.get抽選対象申告件数() + this.get取消件数()）を返却する。<BR>
     * @@return double
     * @@roseuid 40E3715E0193
     */
    public double getAllOrderNumber() 
    {
        return (this.getLotTargetOrderNumber() + this.getCancelNumber());
    }
    
    /**
     * (get平均申告価格)<BR>
     * 平均申告価格を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * this.申告価格合計 / this.get抽選対象申告件数()<BR>
     * <BR>
     * ※ 計算結果の小数点以下第3位を四捨五入。<BR>
     * 　@（小数点以下第2位まで有効）<BR>
     * @@return double
     * @@roseuid 40E3721100B8
     */
    public double getAverageOrderPrice() 
    {
    	// 2004/12/06 障害管理票No.U00519 小数点第3位を四捨五入して返却するように修正 情野@@SRA START
    	return Math.round(this.orderPriceTotal / this.getLotTargetOrderNumber() * 100) / 100.0;
//        return (this.orderPriceTotal / this.getLotTargetOrderNumber());
		// 2004/12/06 障害管理票No.U00519 小数点第3位を四捨五入して返却するように修正 情野@@SRA END
    }
    
    /**
     * (get抽選対象申告数量)<BR>
     * 抽選対象申告数量を取得する。<BR>
     * <BR>
     * this.抽選対象申告数量を返却する。<BR>
     * @@return double
     * @@roseuid 40E372D0025E
     */
    public double getLotTargetOrderQuantity() 
    {
        return this.lotTargetOrderQuantity;
    }
    
    /**
     * (get取消数量)<BR>
     * 取消数量を取得する。<BR>
     * <BR>
     * this.取消数量を返却する。<BR>
     * @@return double
     * @@roseuid 40E372E503B6
     */
    public double getCancelQuantity() 
    {
        return this.cancelQuantity;
    }
    
    /**
     * (get全申告数量)<BR>
     * 全申告数量を取得する。<BR>
     * <BR>
     * （this.get抽選対象申告数量() + this.get取消数量()）を返却する。<BR>
     * @@return double
     * @@roseuid 40E372FA027D
     */
    public double getAllOrderQuantity() 
    {
        return (this.getLotTargetOrderQuantity() + this.getCancelQuantity());
    }
    
    /**
     * (get出金余力保持者合計数量)<BR>
     * 出金余力保持者合計数量を取得する。<BR>
     * <BR>
     * this.出金余力保持者合計数量を返却する。<BR>
     * @@return double
     * @@roseuid 40ED29960169
     */
    public double getTradingPowerHolderTotalQuantity() 
    {
        return this.tradingPowerHolderTotalQuantity;
    }
    
    /**
     * (get出金余力保持者合計人数)<BR>
     * 出金余力保持者合計人数を取得する。<BR>
     * <BR>
     * this.出金余力保持者合計人数を返却する。<BR>
     * @@return long
     * @@roseuid 40ED29A9039C
     */
    public long getTradingPowerHolderTotalNumber() 
    {
        return this.tradingPowerHolderTotalNumber;
    }
    
    /**
     * (add抽選対象申告数量)<BR>
     * 抽選対象申告数量に引数の数量を加算し、<BR>
     * 抽選対象申告件数をインクリメントする。<BR>
     * <BR>
     * １）　@申告数量追加<BR>
     * this.抽選対象申告数量 = （this.get抽選対象申告数量() + 数量）<BR>
     * <BR>
     * ２）　@申告件数インクリメント<BR>
     * this.add抽選対象申告件数()<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 申告数量<BR>
     * @@roseuid 40E373300164
     */
    public void addLotTargetOrderQuantity(double l_dblQuantity) 
    {
        //申告数量追加
        this.lotTargetOrderQuantity = this.getLotTargetOrderQuantity() + l_dblQuantity;

        //申告件数インクリメント
        this.addLotTargetOrderNumber();
    }
    
    /**
     * (add取消数量)<BR>
     * 取消数量に引数の数量を加算し、<BR>
     * 取消件数をインクリメントする。<BR>
     * <BR>
     * １）　@取消数量追加<BR>
     * this.取消数量 = （this.get取消数量() + 数量）<BR>
     * <BR>
     * ２）　@取消件数インクリメント<BR>
     * this.add取消件数()<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 申告数量<BR>
     * @@roseuid 40E3742E0089
     */
    public void addCancelQuantity(double l_dblQuantity) 
    {
        //取消数量追加
        this.cancelQuantity = this.getCancelQuantity() + l_dblQuantity;
        
        //取消件数インクリメント
        this.addCancelNumber();
    }
    
    /**
     * (add申告価格)<BR>
     * 申告価格合計値に、引数の申告価格を加算する。<BR>
     * <BR>
     * this.申告価格合計 = this.get申告価格合計() + 申告価格<BR>
     * @@param l_dblOrderPrice - 申告価格（計算単価）
     * @@roseuid 40E3746B01D1
     */
    public void addOrderPrice(double l_dblOrderPrice) 
    {
        this.orderPriceTotal = this.orderPriceTotal + l_dblOrderPrice;
    }
    
    /**
     * (add抽選対象申告件数)<BR>
     * 抽選対象申告件数をインクリメントする。<BR>
     * <BR>
     * this.抽選対象申告件数 = （this.get抽選対象申告件数() + 1）<BR>
     * @@roseuid 40E508C802C2
     */
    public void addLotTargetOrderNumber() 
    {
        this.lotTargetOrderNumber = this.getLotTargetOrderNumber() + 1;
    }
    
    /**
     * (add取消件数)<BR>
     * 取消件数をインクリメントする。<BR>
     * <BR>
     * this.取消件数 = （this.get取消件数() + 1）<BR>
     * @@roseuid 40E5093A010D
     */
    public void addCancelNumber() 
    {
        this.cancelNumber = this.getCancelNumber() + 1;
    }
    
    /**
     * (add出金余力保持者合計人数)<BR>
     * 出金余力保持者合計人数をインクリメントする。<BR>
     * <BR>
     * this.出金余力保持者合計人数 = （this.get出金余力保持者合計人数() + 1）<BR>
     * @@roseuid 40ED2930005F
     */
    public void addTradingPowerHolderTotalNumber() 
    {
        this.tradingPowerHolderTotalNumber = this.getTradingPowerHolderTotalNumber() + 1;
    }
    
    /**
     * (add出金余力保持者合計数量)<BR>
     * 出金余力保持者合計数量に引数の数量を加算し、<BR>
     * 出金余力保持者合計人数をインクリメントする。<BR>
     * <BR>
     * １）　@申告数量追加<BR>
     * this.出金余力保持者合計数量 = （this.get出金余力保持者合計数量() + 数量）<BR>
     * <BR>
     * ２）　@人数インクリメント<BR>
     * this.add出金余力保持者合計人数()<BR>
     * <BR>
     * <BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 申告数量<BR>
     * @@roseuid 40ED295F0198
     */
    public void addTradingPowerHolderTotalQuantity(double l_dblQuantity) 
    {
        this.tradingPowerHolderTotalQuantity = this.getTradingPowerHolderTotalQuantity() + l_dblQuantity;
        
        this.addTradingPowerHolderTotalNumber();
    }
}
@
