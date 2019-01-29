head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付条件一覧レスポンス(WEB3MutualFixedBuyConditionListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信定時定額買付条件一覧レスポンス)<BR>
 * 投信定時定額買付条件一覧レスポンス<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200607221353L; 
   
    /**
     *(投信銘柄カテゴリー一覧)<BR>
     *<BR>
     *投信銘柄カテゴリー一覧<BR>
     *(nullの場合はカテゴリ指定無しとする)<BR>
     */
    public WEB3MutualProductCategoryUnit[] categoryList;
    
    /**
     * (投信定時定額買付条件一覧)<BR>
     * 投信定時定額買付条件一覧<BR>
     */
    public WEB3MutualFixedBuyConditionUnit[] conditionList;
    
    /**
     * (定時定額買付金額合計)<BR>
     * 定時定額買付金額合計クラスの配列<BR>
     * <BR>
     * 定時定額買付金額合計[0] ：　@1番目の合計 <BR>
     * 定時定額買付金額合計[1] ：　@2番目の合計 <BR>
     */
    public WEB3MutualFixedBuyTotalUnit[] totalList;
    
    /**
     * (定時定額引落口座情報)<BR>
     * 定時定額引落口座情報<BR>
     */
    public WEB3MutualFixedBuyAccountInfo acountInfo;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MutualFixedBuyConditionListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
   
    /**
     * (投信定時定額買付条件一覧レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyConditionListResponse() 
    {     
    }  
}
@
