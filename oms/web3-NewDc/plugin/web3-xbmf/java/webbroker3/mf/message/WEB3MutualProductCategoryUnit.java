head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductCategoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄カテゴリーコード名称データクラス(WEB3MutualProductCategoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 投資信託銘柄カテゴリーコード名称データクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualProductCategoryUnit extends Message 
{

    /**
     * (投信銘柄カテゴリーコード)<BR>
     * 投信銘柄カテゴリー名称に対応した投信銘柄カテゴリーコード<BR>
     */
    public String categoryCode;
    
    /**
     * (投信銘柄カテゴリー名称)<BR>
     * 投信銘柄カテゴリーコードに対応した投信銘柄カテゴリー名称<BR>
     */
    public String categoryName;
    
    /**
     * (内包カテゴリーコード名称)<BR>
     * 当該カテゴリーに紐付く下位カテゴリー情報<BR>
     */
    public WEB3MutualProductCategoryUnit[ ] childCategory;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40AC0D240137
     */
    public WEB3MutualProductCategoryUnit() 
    {
     
    }
}
@
