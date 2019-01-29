head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesProfitAndLossCostComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :先物損益(諸経費込)Comparator(WEB3FuturesProfitAndLossCostComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 有山 祥子(SRA) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物損益(諸経費込)Comparator)<BR>
 */
public class WEB3FuturesProfitAndLossCostComparator implements Comparator
{

   /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
	        WEB3FuturesProfitAndLossCostComparator.class);

    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * コンストラクタ<BR>
     */
    public WEB3FuturesProfitAndLossCostComparator()
    {

    }

    /**
     * コンストラクタ<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@return Void
     */
    public WEB3FuturesProfitAndLossCostComparator(String l_strOrderBy)
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * 昇順、降順の指定にもとづく損益の比較を行う。<BR>
     * <BR>
     * １）パラメータのオブジェクトの判定<BR>
     * <BR>
     * 　@instanceofにて、引数の明細1、明細2が以下のクラスのどれかを判定する。<BR>
     * <BR>
     *     株価指数先物返済一覧行クラス<BR>
     *     株価指数先物建玉照会明細クラス<BR>
     *     建玉明細クラス<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * 　@１）明細1と明細2の損益(諸経費込)について比較を行う<BR>
     * <BR>
     * 　@[昇順指定の場合(this.orderBy == ”昇順”)]<BR>
     * <BR>
     * 　@・(明細1.損益(諸経費込) < 明細2.損益(諸経費込))の場合、負の整数(-1)を返却する<BR>
     * 　@・(明細1.損益(諸経費込) == 明細2.損益(諸経費込))の場合、0を返却する<BR>
     * 　@・(明細1.損益(諸経費込) > 明細2.損益(諸経費込))の場合、正の整数(1)を返却する<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”)]<BR>
     * <BR>
     * 　@・(明細1.損益(諸経費込) < 明細2.損益(諸経費込))の場合、正の整数(1)を返却する<BR>
     * 　@・(明細1.損益(諸経費込) == 明細2.損益(諸経費込))の場合、0を返却する<BR>
     * 　@・(明細1.損益(諸経費込) > 明細2.損益(諸経費込))の場合、負の整数(-1)を返却する<BR>
     * @@param l_obj1<BR>
     * @@param l_obj2<BR>
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblVal1 = 0;
        double l_dblVal2 = 0;
        
        String l_strAttr1 = null;
        String l_strAttr2 = null; 

        if (l_obj1 instanceof WEB3FuturesCloseMarginGroup
            && l_obj2 instanceof WEB3FuturesCloseMarginGroup)
        {
            //株価指数先物返済一覧行クラスの場合
            l_strAttr1 = ((WEB3FuturesCloseMarginGroup)l_obj1).incomeCost;
            l_strAttr2 = ((WEB3FuturesCloseMarginGroup)l_obj2).incomeCost; 
        }
        else if (l_obj1 instanceof WEB3FuturesContractReferenceUnit
            && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            //株価指数先物建玉照会明細クラス
            l_strAttr1 = ((WEB3FuturesContractReferenceUnit)l_obj1).incomeCost;
            l_strAttr2 = ((WEB3FuturesContractReferenceUnit)l_obj2).incomeCost; 
        }
        else if (l_obj1 instanceof WEB3FuturesOptionsContractUnit
            && l_obj2 instanceof WEB3FuturesOptionsContractUnit)
        {
            //株価指数オプション建玉明細クラス
            l_strAttr1 = ((WEB3FuturesOptionsContractUnit)l_obj1).incomeCost;
            l_strAttr2 = ((WEB3FuturesOptionsContractUnit)l_obj2).incomeCost; 
        }
        else
        {
            throw new IllegalArgumentException("パラメータの型が不正です。");
        }

        if (l_strAttr1 == null || l_strAttr2 == null)
        {
            int l_intResult;
               
            if (l_strAttr1 == null && l_strAttr2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strAttr1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            log.exiting(STR_METHOD_NAME);
            return l_intResult;            
        }
            
		l_dblVal1 = Double.parseDouble(l_strAttr1);
		l_dblVal2 = Double.parseDouble(l_strAttr2);
        
        // 値が等しい場合
		if (l_dblVal1 == l_dblVal2)
		{
            log.exiting(STR_METHOD_NAME);
			return 0;
		}
		
		// 昇順ソートの場合
		if (WEB3AscDescDef.ASC.equals(this.orderBy))
		{
			if (l_dblVal1 < l_dblVal2)
			{
				log.exiting(STR_METHOD_NAME);
				return -1;
			}
			else
			{
				log.exiting(STR_METHOD_NAME);
				return 1;
			}
		}
		// 降順ソートの場合
		else
		{
			if (l_dblVal1 < l_dblVal2)
			{
				log.exiting(STR_METHOD_NAME);
				return 1;
			}
			else
			{
				log.exiting(STR_METHOD_NAME);
				return -1;
			}
		}
    }

    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj
     * @@return boolean
     */
    public boolean equals(Object l_obj)
    {
        //スーパークラスのequalsをコールする。
        if (l_obj instanceof WEB3FuturesProfitAndLossCostComparator)
        {
			WEB3FuturesProfitAndLossCostComparator l_comparator =
                (WEB3FuturesProfitAndLossCostComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}

@
