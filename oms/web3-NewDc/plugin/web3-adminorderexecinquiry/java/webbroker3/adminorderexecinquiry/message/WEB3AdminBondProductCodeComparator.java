head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondProductCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (債券)銘柄コード（WEB3）Comparator(WEB3AdminBondProductCodeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((債券)銘柄コード（WEB3）Comparator)<BR>
 * (債券)銘柄コード（WEB3）Comparator<BR>
 *  
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3AdminBondProductCodeComparator implements Comparator 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderTypeComparator.class);
    
    /**
     * orderBy<BR>
     * A：昇順<BR>
     * D：降順
     */
    private String orderBy;
    
    /**
     * ((債券)銘柄コード(WEB3)Comparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする
     * @@param l_strOrderBy - (orderBy)<BR>
     * ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順
     * @@roseuid 44C03D3101AE
     */
    public WEB3AdminBondProductCodeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AdminBondProductCodeComparator(String l_strOrderBy)";
            log.entering(STR_METHOD_NAME);
        
        if(l_strOrderBy == null ||
            (!l_strOrderBy.equals(WEB3AscDescDef.ASC) &&
            !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException(
            "パラメータの値が'A：昇順'、'D：降順'以外です。"); 
        }
        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。
     * @@param l_obj - (Object)<BR>
     * 比較対象の参照オブジェクト
     * @@return boolean
     * @@roseuid 44C04F9100F6
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 昇順、降順の指定にもとづく銘柄コード（WEB3）の比較を行う。<BR>
     * <BR>
     　@１）パラメータを債券管理者注文約定照会行にキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     *　@　@１）　@１）でキャストした明細1、明細2の通貨コードを取得する。 。<BR>
     * 　@ 　@明細1、明細2の通貨を国内債券か外国債券か判定する。<BR>
     * 　@ 　@・国内債券＝「通貨コード==NULL」 <BR>
     * 　@ 　@・外国債券＝上記以外 <BR>
     * <BR>
     *　@　@２）明細1が「国内債券」　@かつ　@明細2が「国内債券」の場合<BR>
     *　@　@　@２－１）　@１）でキャストした明細1、明細2の銘柄コード（WEB3）を二つの文字列に分割する。 <BR>
     *　@　@　@　@A部分：後ろ4桁 (比較時の優先順位1) <BR>
     *　@　@　@　@B部分：先頭5桁 (比較時の優先順2)<BR>  
     *　@　@　@２－２）昇順指定の場合、<BR>
     *　@　@　@　@２－２－１）明細1.A部分が、明細2.A部分より小さい場合、負の整数（-1）を返却する。<BR>
     *　@　@　@　@２－２－２）明細1.A部分が、明細2.A部分より大きい場合、正の整数（1）を返却する。<BR>
     *　@　@　@　@２－２－３）明細1.A部分が、明細2.A部分と等しい場合、、<BR>
     * 　@　@　@　@　@２－２－３－１）明細1.B部分が、明細2.B部分より小さい場合、負の整数（-1）を返却する。 <BR>
     * 　@　@　@　@　@２－２－３－２）明細1.B部分が、明細2.B部分より大きい場合、正の整数（1）を返却する <BR>
     * 　@　@　@　@　@２－２－３－３）明細1.B部分が、明細2.B部分と等しい場合、0を返却する。<BR>
     * <BR>
     *　@　@　@２－３)降順指定の場合、<BR>
     *　@　@　@　@２－３－１）明細1.A部分が、明細2.A部分より小さい場合、正の整数（1）を返却する。<BR>
     *　@　@　@　@２－３－２）明細1.A部分が、明細2.A部分より大きい場合、負の整数（-1）を返却する。 <BR>  
     *　@　@　@　@２－３－３）明細1.A部分が、明細2.A部分と等しい場合、<BR>
     *　@　@　@　@　@２－３－３－１）明細1.B部分が、明細2.B部分より小さい場合、正の整数（1）を返却する。<BR>
     *　@　@　@　@　@２－３－３－２）明細1.B部分が、明細2.B部分より大きい場合、負の整数（-1）を返却する。<BR>
     *　@　@　@　@　@２－３－３－３）明細1.B部分が、明細2.B部分と等しい場合、0を返却する。<BR>
     * <BR>
     *　@　@３）明細1が「国内債券」　@かつ　@明細2が「外国債券」の場合<BR>
     *　@　@　@３－１）昇順指定の場合、正の整数（1）を返却する。 <BR> 
     *　@　@　@３－２）降順指定の場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     *　@　@４）明細1が「外国債券」　@かつ　@明細2が「国内債券」の場合<BR>
     *　@　@　@４－１）昇順指定の場合、負の整数（-1）を返却する。<BR>
     *　@　@　@４－２）降順指定の場合、正の整数（1）を返却する。<BR>
     *<BR>
     *　@　@５）明細1が「外国債券」　@かつ　@明細2が「外国債券」の場合 <BR>
     *　@　@　@５－１）　@１）でキャストした明細1、明細2の銘柄コード（WEB3）を三つの文字列に分割する。<BR> 
     *　@　@　@　@A部分：先頭2桁 (比較時の優先順位1)<BR>
     *　@　@　@　@B部分：後ろ4桁 (比較時の優先順2) <BR>
     *　@　@　@　@C部分：中央3桁 (比較時の優先順3)<BR>
     *<BR>
     *　@　@　@５－２)昇順指定の場合、<BR>
     *　@　@　@　@５－２－１）明細1.A部分が、明細2.A部分より小さい場合、負の整数（-1）を返却する。<BR>
     *　@　@　@　@５－２－２）明細1.A部分が、明細2.A部分より大きい場合、正の整数（1）を返却する。<BR>
     *　@　@　@　@５－２－３）明細1.A部分が、明細2.A部分と等しい場合、<BR>
     *　@　@　@　@　@５－２－３－１）明細1.B部分が、明細2.B部分より小さい場合、負の整数（-1）を返却する。<BR> 
     *　@　@　@　@　@５－２－３－２）明細1.B部分が、明細2.B部分より大きい場合、正の整数（1）を返却する。<BR> 
     *　@　@　@　@　@５－２－３－３）明細1.B部分が、明細2.B部分と等しい場合、<BR>
     *　@　@　@　@　@　@５－２－３－３－１)明細1.C部分が、明細2.C部分より小さい場合、負の整数（-1）を返却する。<BR> 
     *　@　@　@　@　@　@５－２－３－３－２)明細1.C部分が、明細2.C部分より大きい場合、正の整数（1）を返却する。<BR>
     *　@　@　@　@　@　@５－２－３－３－３)明細1.C部分が、明細2.C部分と等しい場合、0を返却する。<BR>
     *<BR>
     *　@　@　@５－３)降順指定の場合、<BR>
     *　@　@　@　@５－３－１）明細1.A部分が、明細2.A部分より小さい場合、正の整数（1）を返却する。<BR>
     *　@　@　@　@５－３－２）明細1.A部分が、明細2.A部分より大きい場合、負の整数（-1）を返却する。<BR>
     *　@　@　@　@５－３－３）明細1.A部分が、明細2.A部分と等しい場合、<BR>
     *　@　@　@　@　@５－３－３－１）明細1.B部分が、明細2.B部分より小さい場合、正の整数（1）を返却する。<BR>
     *　@　@　@　@　@５－３－３－２）明細1.B部分が、明細2.B部分より大きい場合、負の整数（-1）を返却する。<BR>
     *　@　@　@　@　@５－３－３－３）明細1.B部分が、明細2.B部分と等しい場合、<BR>
     *　@　@　@　@　@　@５－３－３－３－１)明細1.C部分が、明細2.C部分より小さい場合、正の整数（1）を返却する。<BR>
     *　@　@　@　@　@　@５－３－３－３－２)明細1.C部分が、明細2.C部分より大きい場合、負の整数（-1）を返却する。<BR>
     *　@　@　@　@　@　@５－３－３－３－３)明細1.C部分が、明細2.C部分と等しい場合、0を返却する。<BR>            　@　@
     * <BR>
     * 　@昇降順の判定はコンストラクタでセットされたorderByの値を用いる
     * @@param l_obj1 - (明細1)<BR>
     * 比較したいオブジェクト１
     * @@param l_obj2 - (明細2)<BR>
     * 比較したいオブジェクト２
     * @@return int
     * @@roseuid 44C0502D0196
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        
        
        //１）パラメータを債券管理者注文約定照会行にキャストする。     
        String l_strVal1 = null;
        String l_strVal2 = null;
        String l_strVal3 = null;
        String l_strVal4 = null;
        
        int l_intReturn = 0;
        if(l_obj1 instanceof WEB3AdminORBondExecRefUnit && 
           l_obj2 instanceof WEB3AdminORBondExecRefUnit)
        {
            l_strVal1 = ((WEB3AdminORBondExecRefUnit) l_obj1).productCode;
            l_strVal2 = ((WEB3AdminORBondExecRefUnit) l_obj2).productCode;
            l_strVal3 = ((WEB3AdminORBondExecRefUnit) l_obj1).currencyCode;
            l_strVal4 = ((WEB3AdminORBondExecRefUnit) l_obj2).currencyCode;
        }
        else
        {

            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3AdminORBondExecRefUnit'類型。");
        }
        
        int l_intVal3Flag = 0;
        int l_intVal4Flag = 0;
        if (l_strVal3 == null)
        {
            l_intVal3Flag = 1; 
        }
        else 
        {
            l_intVal3Flag = 2;
        }
        if (l_strVal4 == null)
        {
            l_intVal4Flag = 1; 
        }
        else 
        {
            l_intVal4Flag = 2;
        }
        
        if (l_strVal1 == null || l_strVal2 == null)
        {
            if (l_strVal1 == null && l_strVal2 == null)
            {
                return 0;
            }
            else if (l_strVal1 == null) 
            {
                if (l_intVal3Flag == 1)
                {
                    if (l_intVal4Flag == 1)
                    {
                        if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                     else
                     {
                         if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                else
                {
                    if (l_intVal4Flag == 1)
                    {
                        if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                     else
                     {
                         if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                }
            }
            else
            {
                if (l_intVal3Flag == 1)
                {
                    if (l_intVal4Flag == 1)
                    {
                        if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                     else
                     {
                         if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                else
                {
                    if (l_intVal4Flag == 1)
                    {
                        if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                     else
                     {
                         if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
            }
        }
        
        if (l_intVal3Flag == 1)
        {
            //２）明細1が「国内債券」　@かつ　@明細2が「国内債券」の場合
            if (l_intVal4Flag == 1)
            {
                //A部分：後ろ4桁 (比較時の優先順位1) 
                //B部分：先頭5桁 (比較時の優先順2) 
                String l_strA1 = l_strVal1.substring(5,9);
                String l_strB1 = l_strVal1.substring(0,5);
                
                String l_strA2 = l_strVal2.substring(5,9);
                String l_strB2 = l_strVal2.substring(0,5);
                
                //２－２）昇順指定の場合、
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    //２－２－１）明細1.A部分が、明細2.A部分より小さい場合、負の整数（-1）を返却する。
                    if (l_strA1.compareTo(l_strA2) < 0)
                    {
                        log.exiting(STR_METHOD_NAME);
                        l_intReturn = -1;
                    }
                    //２－２－２）明細1.A部分が、明細2.A部分より大きい場合、正の整数（1）を返却する。
                    else if (l_strA1.compareTo(l_strA2) > 0)
                    {
                        log.exiting(STR_METHOD_NAME);
                        l_intReturn = 1;
                    }
                    //２－２－３）明細1.A部分が、明細2.A部分と等しい場合、 
                    else
                    {
                        //２－２－３－１）明細1.B部分が、明細2.B部分より小さい場合、負の整数（-1）を返却する。
                        if (l_strB1.compareTo(l_strB2) < 0)
                        {
                            log.exiting(STR_METHOD_NAME);
                            l_intReturn = -1;
                        }
                        //２－２－３－２）明細1.B部分が、明細2.B部分より大きい場合、正の整数（1）を返却する。
                        else if (l_strB1.compareTo(l_strB2) > 0)
                        {
                            l_intReturn = 1;
                        }
                        //２－２－３－３）明細1.B部分が、明細2.B部分と等しい場合、0を返却する。 
                        else
                        {
                            l_intReturn = 0;
                        }
                    } 
                }
                //２－３)降順指定の場合、 　@　@　@ 
                else
                {
                    //２－３－１）明細1.A部分が、明細2.A部分より小さい場合、正の整数（1）を返却する。 
                    if (l_strA1.compareTo(l_strA2) < 0)
                    {
                        l_intReturn = 1;
                    }
                    //２－３－２）明細1.A部分が、明細2.A部分より大きい場合、負の整数（-1）を返却する。
                    else if (l_strB1.compareTo(l_strB2) > 0)
                    {
                        l_intReturn = -1;
                    }
                    //２－３－３）明細1.A部分が、明細2.A部分と等しい場合、
                    else
                    {
                        //２－３－３－１）明細1.B部分が、明細2.B部分より小さい場合、正の整数（1）を返却する。
                        if (l_strB1.compareTo(l_strB2) < 0)
                        {
                            l_intReturn = 1;
                        }
                        //２－３－３－２）明細1.B部分が、明細2.B部分より大きい場合、負の整数（-1）を返却する。
                        else if (l_strB1.compareTo(l_strB2) > 0)
                        {
                            l_intReturn = -1;
                        }
                        //２－３－３－３）明細1.B部分が、明細2.B部分と等しい場合、0を返却する。 
                        else
                        {
                            l_intReturn = 0;
                        }
                    } 
                }  
            }
            //　@３）明細1が「国内債券」　@かつ　@明細2が「外国債券」の場合 
            else
            {
                //３－１）昇順指定の場合、正の整数（1）を返却する。 
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = 1;
                }
                //３－２）降順指定の場合、負の整数（-1）を返却する。 
                else
                {
                    l_intReturn = -1;
                }
            }
        }
        else
        {
            //４）明細1が「外国債券」　@かつ　@明細2が「国内債券」の場合 
            if (l_intVal4Flag == 1)
            {
                //４－１）昇順指定の場合、負の整数（-1）を返却する。
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = -1;
                }
                //４－２）降順指定の場合、正の整数（1）を返却する。
                else
                {
                    l_intReturn = 1;
                }
            }
            //５）明細1が「外国債券」　@かつ　@明細2が「外国債券」の場合
            else
            {
                //A部分：先頭2桁 (比較時の優先順位1) 
                //B部分：後ろ4桁 (比較時の優先順2) 
                //C部分：中央3桁 (比較時の優先順3) 
                String l_strA1 = l_strVal1.substring(0,2);
                String l_strB1 = l_strVal1.substring(5,9);
                String l_strC1 = l_strVal1.substring(2,5);

                String l_strA2 = l_strVal2.substring(0,2);
                String l_strB2 = l_strVal2.substring(5,9);
                String l_strC2 = l_strVal2.substring(2,5);  
                
                //５－２)昇順指定の場合、 
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    //５－２－１）明細1.A部分が、明細2.A部分より小さい場合、負の整数（-1）を返却する。
                    if (l_strA1.compareTo(l_strA2) < 0)
                    {
                        l_intReturn = -1;
                    }
                    //５－２－２）明細1.A部分が、明細2.A部分より大きい場合、正の整数（1）を返却する。 
                    else if (l_strA1.compareTo(l_strA2) > 0)
                    {
                        l_intReturn = 1;
                    }
                    //５－２－３）明細1.A部分が、明細2.A部分と等しい場合、 
                    else
                    {
                        //５－２－３－１）明細1.B部分が、明細2.B部分より小さい場合、負の整数（-1）を返却する。 
                        if (l_strB1.compareTo(l_strB2) < 0)
                        {
                            l_intReturn = -1; 
                        }
                        //５－２－３－２）明細1.B部分が、明細2.B部分より大きい場合、正の整数（1）を返却する。 
                        else if (l_strB1.compareTo(l_strB2) > 0)
                        {
                            l_intReturn = 1;
                        }
                        //５－２－３－３）明細1.B部分が、明細2.B部分と等しい場合、
                        else
                        {
                            //５－２－３－３－１)明細1.C部分が、明細2.C部分より小さい場合、負の整数（-1）を返却する。
                            if (l_strC1.compareTo(l_strC2) < 0)
                            {
                                l_intReturn = -1;
                            }
                            //５－２－３－３－２)明細1.C部分が、明細2.C部分より大きい場合、正の整数（1）を返却する。
                            else if (l_strC1.compareTo(l_strC2) > 0)
                            {
                                l_intReturn = 1;
                            }
                            //５－２－３－３－３)明細1.C部分が、明細2.C部分と等しい場合、0を返却する。
                            else 
                            {
                                l_intReturn = 0;
                            }
                        }
 
                    }
                }
                
                //５－３)降順指定の場合、 
                else
                {
                    //５－３－１）明細1.A部分が、明細2.A部分より小さい場合、正の整数（1）を返却する。
                    if (l_strA1.compareTo(l_strA2) < 0)
                    {
                        l_intReturn = 1;
                    }
                    //５－３－２）明細1.A部分が、明細2.A部分より大きい場合、負の整数（-1）を返却する。
                    else if (l_strA1.compareTo(l_strA2) > 0)
                    {
                        l_intReturn = -1;
                    }
                    //５－３－３）明細1.A部分が、明細2.A部分と等しい場合、 
                    else
                    {
                        //５－３－３－１）明細1.B部分が、明細2.B部分より小さい場合、正の整数（1）を返却する。
                        if (l_strB1.compareTo(l_strB2) < 0)
                        {
                            l_intReturn = 1; 
                        }
                        //５－３－３－２）明細1.B部分が、明細2.B部分より大きい場合、負の整数（-1）を返却する。 
                        else if (l_strB1.compareTo(l_strB2) > 0)
                        {
                            l_intReturn = -1;
                        }
                        //５－３－３－３）明細1.B部分が、明細2.B部分と等しい場合、 
                        else
                        {
                            //５－３－３－３－１)明細1.C部分が、明細2.C部分より小さい場合、正の整数（1）を返却する。
                            if (l_strC1.compareTo(l_strC2) < 0)
                            {
                                l_intReturn = 1;
                            }
                            //５－３－３－３－２)明細1.C部分が、明細2.C部分より大きい場合、負の整数（-1）を返却する。
                            else if (l_strC1.compareTo(l_strC2) > 0)
                            {
                                l_intReturn = -1;
                            }
                            //５－３－３－３－３)明細1.C部分が、明細2.C部分と等しい場合、0を返却する。
                            else
                            {
                                l_intReturn = 0;
                            }
                        }
                    }
                }

            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}
@
