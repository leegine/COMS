head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultOfferCounter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選結果・購入申込状況集計結果データ(Counter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo;

import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3LogUtility;


/**
 * 抽選結果・購入申込状況集計結果データクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultOfferCounter 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoLotResultOfferCounter.class);
    
    /**
     * IPO銘柄オブジェクト
     */
    private WEB3IpoProductImpl ipoProduct;
    
    /**
     * 当選者購入申込数量
     */
    private double prizerApplicationQuantity = 0;
    
    /**
     * 当選者購入申込件数
     */
    private double prizerOfferNumber = 0;
    
    /**
     * 当選者辞退数量
     */
    private double prizerDeclineQuantity = 0;
    
    /**
     * 当選者辞退件数
     */
    private double prizerDeclineNumber = 0;
    
    /**
     * 当選者未定数量
     */
    private double prizerUndecideQuantity = 0;
    
    /**
     * 当選者未定件数
     */
    private double prizerUndecideNumber = 0;
    
    /**
     * 補欠者購入申込数量
     */
    private double waitingApplicationQuantity = 0;
    
    /**
     * 補欠者購入申込件数
     */
    private double waitingOfferNumber = 0;
    
    /**
     * 補欠者辞退数量
     */
    private double waitingDeclineQuantity = 0;
    
    /**
     * 補欠者辞退件数
     */
    private double waitingDeclineNumber = 0;
    
    /**
     * 補欠者未定数量
     */
    private double waitingUndecideQuantity = 0;
    
    /**
     * 補欠者未定件数
     */
    private double waitingUndecideNumber = 0;
    
    /**
     * 落選者件数
     */
    private double rejectedNumber = 0;
    
    /**
     * 補欠当選数量
     */
    private double waitingPrizeQuantity = 0;
    
    /**
     * 補欠当選件数
     */
    private double waitingPrizeNumber = 0;
    
    /**
     * 補欠落選件数
     */
    private double waitingRejectedNumber = 0;
    
    /**
     * デフォルトコンストラクタ。
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * 
     * @@return webbroker3.ipo.WEB3AdminIpoLotResultOfferCounter
     * @@roseuid 40EBA80E01B4
     */
    public WEB3AdminIpoLotResultOfferCounter(WEB3IpoProductImpl l_ipoProduct) 
    {
        this.ipoProduct = l_ipoProduct;
    }
    
    /**
     * (get当選者購入申込数量)<BR>
     * this.当選者購入申込数量を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB5FB0221
     */
    public double getPrizerApplicationQuantity() 
    {
        return this.prizerApplicationQuantity;
    }
    
    /**
     * (get当選者購入申込件数)<BR>
     * this.当選者購入申込件数を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB61D03E7
     */
    public double getPrizerOfferNumber() 
    {
        return this.prizerOfferNumber;
    }
    
    /**
     * (get当選者辞退数量)<BR>
     * this.当選者辞退数量を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB656002D
     */
    public double getPrizerDeclineQuantity() 
    {
        return this.prizerDeclineQuantity;
    }
    
    /**
     * (get当選者辞退件数)<BR>
     * this.当選者辞退件数を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB66303D7
     */
    public double getPrizerDeclineNumber() 
    {
        return this.prizerDeclineNumber;
    }
    
    /**
     * (get当選者未定数量)<BR>
     * this.当選者未定数量を返却する。 <BR>
     * @@return double
     * @@roseuid 40EBB67A0137
     */
    public double getPrizerUndecideQuantity() 
    {
        return this.prizerUndecideQuantity;
    }
    
    /**
     * (get当選者未定件数)<BR>
     * this.当選者未定件数を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB6850231
     */
    public double getPrizerUndecideNumber() 
    {
        return this.prizerUndecideNumber;
    }
    
    /**
     * (get当選者合計件数)<BR>
     * 当選者合計件数を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * this.get当選者購入申込件数() + this.get当選者辞退件数() <BR>
     * + this.get当選者未定件数()<BR>
     * @@return double
     * @@roseuid 40EBBE5C00AA
     */
    public double getPrizerTotalNumber() 
    {
        log.debug("this.getPrizerOfferNumber() = " + this.getPrizerOfferNumber());
        log.debug("this.getPrizerDeclineNumber() = " + this.getPrizerDeclineNumber());
        log.debug("this.getPrizerUndecideNumber() = " + this.getPrizerUndecideNumber());
        return (this.getPrizerOfferNumber() + this.getPrizerDeclineNumber() + this.getPrizerUndecideNumber());
    }
    
    /**
     * (get当選者合計数量)<BR>
     * 当選者合計数量を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * this.get当選者購入申込数量() + this.get当選者辞退数量() + <BR>
     * this.get当選者未定数量()<BR>
     * @@return double
     * @@roseuid 40EBC4630185
     */
    public double getPrizerTotalQuantity() 
    {
        log.debug("this.getPrizerApplicationQuantity() = " + this.getPrizerApplicationQuantity());
        log.debug("this.getPrizerDeclineQuantity() = " + this.getPrizerDeclineQuantity());
        log.debug("this.getPrizerUndecideQuantity() = " + this.getPrizerUndecideQuantity());
        return (this.getPrizerApplicationQuantity() + this.getPrizerDeclineQuantity() + this.getPrizerUndecideQuantity());
    }
    
    /**
     * (get当選者購入確定件数)<BR>
     * 当選者購入申込件数を取得する。<BR>
     * <BR>
     * 以下を返却する。<BR>
     * <BR>
     * this.get当選者購入申込件数()<BR>
     * @@return double
     * @@roseuid 40EBC49402CD
     */
    public double getPrizerDecisionNumber() 
    {
        return this.getPrizerOfferNumber();
    }
    
    /**
     * (get当選者購入確定数量)<BR>
     * 当選者購入申込数量を取得する。<BR>
     * <BR>
     * 以下を返却する。<BR>
     * <BR>
     * this.get当選者購入申込数量()<BR>
     * @@return double
     * @@roseuid 40EBC49402DD
     */
    public double getPrizerDecisionQuantity() 
    {
        return this.getPrizerApplicationQuantity();
    }
    
    /**
     * (get当選者辞退落選件数)<BR>
     * 当選者辞退落選件数を返却する。<BR>
     * <BR>
     * ○購入申込が終了している場合（this.IPO銘柄.is購入申込終了（当社設定）() == true）<BR>
     * 　@－（this.当選者辞退件数 + this.当選者未定件数）を返却する。<BR>
     * <BR>
     * ○購入申込が終了していない場合<BR>（this.IPO銘柄.is購入申込終了（当社設定）() == false）<BR>
     * 　@－this.当選者辞退件数を返却する。<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EBC4E8000E
     */
    public double getPrizerDeclineRejectedNumber() 
    {
        final String STR_METHOD_NAME = " getPrizerDeclineRejectedNumber()";
        log.entering(STR_METHOD_NAME);
        
        if (this.ipoProduct.isOfferEnd())
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.prizerDeclineNumber = " + this.prizerDeclineNumber);
            log.debug("this.prizerUndecideNumber = " + this.prizerUndecideNumber);
            return (this.prizerDeclineNumber + this.prizerUndecideNumber);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.prizerDeclineNumber = " + this.prizerDeclineNumber);
            return this.prizerDeclineNumber;
        }
    }
    
    /**
     * (get当選者辞退落選数量)<BR>
     * 当選者辞退落選数量を返却する。<BR>
     * <BR>
     * ○購入申込が終了している場合<BR>（this.IPO銘柄.is購入申込終了（当社設定）() == true）<BR>
     * 　@－（this.当選者辞退数量 + this.当選者未定数量）を返却する。<BR>
     * <BR>
     * ○購入申込が終了していない場合<BR>（this.IPO銘柄.is購入申込終了（当社設定）() == false）<BR>
     * 　@－this.当選者辞退数量を返却する。<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EBC4E8001E
     */
    public double getPrizerDeclineRejectedQuantity() 
    {
        final String STR_METHOD_NAME = " getPrizerDeclineRejectedQuantity()";
        log.entering(STR_METHOD_NAME);
        
        if (this.ipoProduct.isOfferEnd())
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.prizerDeclineQuantity = " + this.prizerDeclineQuantity);
            log.debug("this.prizerUndecideQuantity = " + this.prizerUndecideQuantity);
            return (this.prizerDeclineQuantity + this.prizerUndecideQuantity);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.prizerDeclineQuantity = " + this.prizerDeclineQuantity);
            return this.prizerDeclineQuantity;
        }
    }
    
    /**
     * (get補欠者購入申込数量)<BR>
     * this.補欠者購入申込数量を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB6BF0176
     */
    public double getWaitingApplicationQuantity() 
    {
        return this.waitingApplicationQuantity;
    }
    
    /**
     * (get補欠者購入申込件数)<BR>
     * this.補欠者購入申込件数を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB6BF0177
     */
    public double getWaitingOfferNumber() 
    {
        return this.waitingOfferNumber;
    }
    
    /**
     * (get補欠者辞退数量)<BR>
     * this.補欠者辞退数量を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB6BF0185
     */
    public double getWaitingDeclineQuantity() 
    {
        return this.waitingDeclineQuantity;
    }
    
    /**
     * (get補欠者辞退件数)<BR>
     * this.補欠者辞退件数を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB6BF0186
     */
    public double getWaitingDeclineNumber() 
    {
        return this.waitingDeclineNumber;
    }
    
    /**
     * (get補欠者未定数量)<BR>
     * this.補欠者未定数量を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB6BF0187
     */
    public double getWaitingUndecideQuantity() 
    {
        return this.waitingUndecideQuantity;
    }
    
    /**
     * (get補欠者未定件数)<BR>
     * this.補欠者未定件数を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB6BF0188
     */
    public double getWaitingUndecideNumber() 
    {
        return this.waitingUndecideNumber;
    }
    
    /**
     * (get補欠者合計件数)<BR>
     * 補欠者合計件数を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * this.get補欠者購入申込件数() + this.get補欠者辞退件数() + <BR>this.get補欠者未定件数()<BR>
     * @@return double
     * @@roseuid 40EBD684002D
     */
    public double getWaitingTotalNumber() 
    {
        log.debug("this.getWaitingOfferNumber() = " + this.getWaitingOfferNumber());
        log.debug("this.getWaitingDeclineNumber() = " + this.getWaitingDeclineNumber());
        log.debug("this.getWaitingUndecideNumber() = " + this.getWaitingUndecideNumber());
        return this.getWaitingOfferNumber() + this.getWaitingDeclineNumber() + this.getWaitingUndecideNumber();
    }
    
    /**
     * (get補欠者合計数量)<BR>
     * 補欠者合計数量を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * this.get補欠者購入申込数量() + this.get補欠者辞退数量() + <BR>this.get補欠者未定数量()<BR>
     * @@return double
     * @@roseuid 40EBD684002E
     */
    public double getWaitingTotalQuantity() 
    {
        log.debug("this.getWaitingApplicationQuantity() = " + this.getWaitingApplicationQuantity());
        log.debug("this.getWaitingDeclineQuantity() = " + this.getWaitingDeclineQuantity());
        log.debug("this.getWaitingUndecideQuantity() = " + this.getWaitingUndecideQuantity());
        return this.getWaitingApplicationQuantity() + this.getWaitingDeclineQuantity() + this.getWaitingUndecideQuantity();
    }
    
    /**
     * (get補欠者購入確定件数)<BR>
     * 補欠者購入確定件数を取得する。<BR>
     * <BR>
     * this.補欠当選件数を返却する。<BR>
     * @@return double
     * @@roseuid 40EBD684002F
     */
    public double getWaitingDecisionNumber() 
    {
        return this.waitingPrizeNumber;
    }
    
    /**
     * (get補欠者購入確定数量)<BR>
     * 補欠者購入確定数量を取得する。<BR>
     * <BR>
     * this.補欠当選数量を返却する。<BR>
     * @@return double
     * @@roseuid 40EBD684003D
     */
    public double getWaitingDecisionQuantity() 
    {
        return this.waitingPrizeQuantity;
    }
    
    /**
     * (get補欠者辞退落選件数)<BR>
     * 補欠者辞退落選件数を返却する。<BR>
     * <BR>
     * （this.補欠者辞退件数 + this.補欠落選件数）を返却する。<BR>
     * @@return double
     * @@roseuid 40EBD684003E
     */
    public double getWaitingDeclineRejectedNumber() 
    {
        log.debug("this.waitingDeclineNumber = " + this.waitingDeclineNumber);
        log.debug("this.waitingRejectedNumber = " + this.waitingRejectedNumber);
        return this.waitingDeclineNumber + this.waitingRejectedNumber;
    }
    
    /**
     * (get補欠者辞退落選数量)<BR>
     * 補欠者辞退落選数量を返却する。<BR>
     * <BR>
     * ○購入申込が終了している場合（this.IPO銘柄.is購入申込終了（当社設定）() == true）<BR>
     * 　@－（this.補欠者辞退数量 + this.補欠者未定数量）を返却する。<BR>
     * <BR>
     * ○購入申込が終了していない場合<BR>（this.IPO銘柄.is購入申込終了（当社設定）() == false）<BR>
     * 　@－this.補欠者辞退数量を返却する。<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EBD684003F
     */
    public double getWaitingDeclineRejectedQuantity() 
    {
        final String STR_METHOD_NAME = " getWaitingDeclineRejectedQuantity()";
        log.entering(STR_METHOD_NAME);
        
        if (this.ipoProduct.isOfferEnd())
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.waitingDeclineQuantity = " + this.waitingDeclineQuantity);
            log.debug("this.waitingUndecideQuantity = " + this.waitingUndecideQuantity);
            return (this.waitingDeclineQuantity + this.waitingUndecideQuantity);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.waitingDeclineQuantity = " + this.waitingDeclineQuantity);
            return this.waitingDeclineQuantity;
        }
    }
    
    /**
     * (get落選件数)<BR>
     * this.落選件数を返却する。<BR>
     * @@return double
     * @@roseuid 40EBB93A029E
     */
    public double getRejectedNumber() 
    {
        return this.rejectedNumber;
    }
    
    /**
     * (add当選者購入申込数量)<BR>
     * 当選者購入申込数量に引数の数量を加算する。<BR>
     * 当選者購入申込件数をインクリメントする。<BR>
     * <BR>
     * １）　@数量追加<BR>
     * this.当選者購入申込数量 = （this.get当選者購入申込数量() + 数量）<BR>
     * <BR>
     * ２）　@件数インクリメント<BR>
     * this.当選者購入申込件数 = （this.get当選者購入申込件数() + 1）<BR>
     * <BR>
     * @@param l_dblQuantity - 数量
     * @@roseuid 40EBB70B005C
     */
    public void addPrizerApplicationQuantity(double l_dblQuantity) 
    {
        //数量追加
        this.prizerApplicationQuantity = this.getPrizerApplicationQuantity() + l_dblQuantity;
        
        //件数インクリメント
        this.prizerOfferNumber = this.getPrizerOfferNumber() + 1;
    }
    
    /**
     * (add当選者辞退数量)<BR>
     * 当選者辞退数量に引数の数量を加算し、<BR>
     * 当選者辞退件数をインクリメントする。<BR>
     * <BR>
     * １）　@数量追加<BR>
     * this.当選者辞退数量 = （this.get当選者辞退数量() + 数量）<BR>
     * <BR>
     * ２）　@件数インクリメント<BR>
     * this.当選者辞退件数 = （this.get当選者辞退件数() + 1）<BR>
     * @@param l_dblQuantity - 数量
     * @@roseuid 40EBB7F20260
     */
    public void addPrizerDeclineQuantity(double l_dblQuantity) 
    {
        //数量追加
        this.prizerDeclineQuantity = this.getPrizerDeclineQuantity() + l_dblQuantity;
        
        //件数インクリメント
        this.prizerDeclineNumber = this.getPrizerDeclineNumber() + 1;
    }
    
    /**
     * (add当選者未定数量)<BR>
     * 当選者未定数量に引数の数量を加算し、<BR>
     * 当選者未定件数をインクリメントする。<BR>
     * <BR>
     * １）　@数量追加<BR>
     * this.当選者未定数量 = （this.get当選者未定数量() + 数量）<BR>
     * <BR>
     * ２）　@件数インクリメント<BR>
     * this.当選者未定件数 = （this.get当選者未定件数() + 1）<BR>
     * <BR>
     * @@param l_dblQuantity - 数量
     * @@roseuid 40EBB84E01E3
     */
    public void addPrizerUndecideQuantity(double l_dblQuantity) 
    {
        //数量追加
        this.prizerUndecideQuantity = this.getPrizerUndecideQuantity() + l_dblQuantity;
        
        //件数インクリメント
        this.prizerUndecideNumber = this.getPrizerUndecideNumber() + 1;
    }
    
    /**
     * (add補欠者購入申込数量)<BR>
     * 補欠者購入申込数量に引数の数量を加算し、<BR>
     * 補欠者購入申込件数をインクリメントする。<BR>
     * <BR>
     * １）　@数量追加<BR>
     * this.補欠者購入申込数量 = <BR>
     *  （this.get補欠者購入申込数量() + 数量）<BR>
     * <BR>
     * ２）　@件数インクリメント<BR>
     * this.補欠者購入申込件数 = <BR>
     *  （this.get補欠者購入申込件数() + 1）<BR>
     * <BR>
     * @@param l_dblQuantity - 購入申込数量
     * @@roseuid 40EBB8830185
     */
    public void addWaitingApplicationQuantity(double l_dblQuantity) 
    {
        //数量追加
        this.waitingApplicationQuantity = this.getWaitingApplicationQuantity() + l_dblQuantity;
        
        //件数インクリメント
        this.waitingOfferNumber = this.getWaitingOfferNumber() + 1;
    }
    
    /**
     * (add補欠者辞退数量)<BR>
     * 補欠者辞退数量に引数の数量を加算し、<BR>
     * 補欠者辞退件数をインクリメントする。<BR>
     * <BR>
     * １）　@数量追加<BR>
     * this.補欠者辞退数量 = （this.get補欠者辞退数量() + 数量）<BR>
     * <BR>
     * ２）　@件数インクリメント<BR>
     * this.補欠者辞退件数 = （this.get補欠者辞退件数() + 1）<BR>
     * @@param l_dblQuantity - 数量
     * @@roseuid 40EBB8830187
     */
    public void addWaitingDeclineQuantity(double l_dblQuantity) 
    {
        //数量追加
        this.waitingDeclineQuantity = this.getWaitingDeclineQuantity() + l_dblQuantity;
        
        //件数インクリメント
        this.waitingDeclineNumber = this.getWaitingDeclineNumber() + 1;
    }
    
    /**
     * (add補欠者未定数量)<BR>
     * 補欠者未定数量に引数の数量を加算し、<BR>
     * 補欠者未定件数をインクリメントする。<BR>
     * <BR>
     * １）　@数量追加<BR>
     * this.補欠者未定数量 = （this.get補欠者未定数量() + 数量）<BR>
     * <BR>
     * ２）　@件数インクリメント<BR>
     * this.補欠者未定件数 = （this.get補欠者未定件数() + 1）<BR>
     * @@param l_dblQuantity - 数量
     * @@roseuid 40EBB8830195
     */
    public void addWaitingUndecideQuantity(double l_dblQuantity) 
    {
        //数量追加
        this.waitingUndecideQuantity = this.getWaitingUndecideQuantity() + l_dblQuantity;
        
        //件数インクリメント
        this.waitingUndecideNumber = this.getWaitingUndecideNumber() + 1;
    }
    
    /**
     * (add補欠当選数量)<BR>
     * 補欠当選数量に引数の数量を加算し、<BR>
     * 補欠当選件数をインクリメントする。<BR>
     * <BR>
     * １）　@数量追加<BR>
     * this.補欠当選数量 = （this.補欠当選数量() + 数量）<BR>
     * <BR>
     * ２）　@件数インクリメント<BR>
     * this.補欠当選件数 = （this.補欠当選件数() + 1）<BR>
     * @@param l_dblQuantity - 数量
     * @@roseuid 40EBBCAD00D9
     */
    public void addWaitingPrizeQuantity(double l_dblQuantity) 
    {
        //数量追加
        this.waitingPrizeQuantity = this.waitingPrizeQuantity + l_dblQuantity;
        log.debug("***************" + this.waitingPrizeQuantity);
        
        //件数インクリメント
        this.waitingPrizeNumber = this.waitingPrizeNumber + 1;
        log.debug("***************" + this.waitingPrizeNumber);
    }
    
    /**
     * (add補欠落選件数)<BR>
     * 補欠落選件数をインクリメントする。<BR>
     * <BR>
     * １）　@件数インクリメント<BR>
     * this.補欠落選件数 = （this.補欠落選件数() + 1）<BR>
     * @@roseuid 40F79E0A0076
     */
    public void addWaitingRejectedNumber() 
    {
        this.waitingRejectedNumber = this.waitingRejectedNumber + 1;
        log.debug("**************" + this.waitingRejectedNumber);
    }
    
    /**
     * (add落選件数)<BR>
     * 落選件数をインクリメントする。<BR>
     * <BR>
     * １）　@件数インクリメント<BR>
     * this.落選件数 = （this.get落選件数() + 1）<BR>
     * @@roseuid 40EBB8EA0147
     */
    public void addRejectedNumber() 
    {
        this.rejectedNumber = this.getRejectedNumber() + 1;
    }
    
    /**
     * (get割当確定数量)<BR>
     * 割当確定数量を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * this.get当選者購入確定数量() + this.get補欠者購入確定数量()<BR>
     * @@return double
     * @@roseuid 40EBD89103A8
     */
    public double getAllotQuantity() 
    {
        return (this.getPrizerDecisionQuantity() + this.getWaitingDecisionQuantity());
    }
    
    /**
     * (get繰上可能数量)<BR>
     * 繰上可能数量を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * this.getIPO銘柄.IPO銘柄行.当社取扱数量 - <BR>（this.get割当確定数量() + this.get繰上待ち数量()） <BR>
     * @@return double
     * @@roseuid 40EBD8F202DD
     */
    public double getAdvanceQuantity() 
    {
        final String STR_METHOD_NAME = " getAdvanceQuantity()";
        log.entering(STR_METHOD_NAME);
        
        IpoProductRow l_ipoProductRow = (IpoProductRow)this.ipoProduct.getDataSourceObject();
        long l_lngDealingQuantity = l_ipoProductRow.getDealingQuantity();
        double l_lngQuantity = this.getAllotQuantity() + this.getAdvanceWaitQuantity();
        
        log.exiting(STR_METHOD_NAME);
        return (l_lngDealingQuantity - l_lngQuantity);
    }
    
    /**
     * (get繰上待ち数量)<BR>
     * 繰上待ち数量を取得する。<BR>
     * <BR>
     * ○購入申込が終了している場合<BR>（this.IPO銘柄.is購入申込終了（当社設定）() == true）<BR>
     * 　@－0を返却する。<BR>
     * <BR>
     * ○購入申込が終了していない場合<BR>（this.IPO銘柄.is購入申込終了（当社設定）() == false）<BR>
     * 　@－this.当選者未定数量を返却する。<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EBDB8B027F
     */
    public double getAdvanceWaitQuantity() 
    {
        final String STR_METHOD_NAME = " getAdvanceWaitQuantity()";
        log.entering(STR_METHOD_NAME);
        
        if (this.ipoProduct.isOfferEnd())
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.prizerUndecideQuantity;
        }
    }
}
@
