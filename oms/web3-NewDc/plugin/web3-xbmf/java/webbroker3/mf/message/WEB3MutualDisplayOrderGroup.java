head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualDisplayOrderGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者銘柄表示順序登録一覧行(WEB3MutualDisplayOrderGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (投信管理者銘柄表示順序登録一覧行)<BR>
 * 投資信託管理者銘柄表示順序登録一覧行データクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualDisplayOrderGroup extends Message 
{   
    /**
     * (表示順)<BR>
     *  表示順
     */
    public String displayOrder;
    
    /**
     * (銘柄コード)<BR>
     *  銘柄コード
     */
    public String mutualProductCode;
    
    /**
     * (投信協会銘柄コード)<BR>
     *  投信協会銘柄コード
     */
    public String mutualAssocProductCode;
    
    /**
     * (銘柄名)<BR>
     *  銘柄名
     */
    public String mutualProductName;
    
    /**
     * (投信銘柄カテゴリーコード１)<BR>
     *  投信銘柄カテゴリーコード１
     */
    public String categoryCode1;
    
    /**
     * (投信銘柄カテゴリー名称１)<BR>
     *  投信銘柄カテゴリー名称１
     */
    public String categoryName1;
    
    /**
     * (投信銘柄カテゴリーコード２)<BR>
     *  投信銘柄カテゴリーコード２
     */
    public String categoryCode2;
    
    /**
     * (投信銘柄カテゴリー名称２)<BR>
     *  投信銘柄カテゴリー名称２
     */
    public String categoryName2;
    
    /**
     * (投信銘柄カテゴリーコード３)<BR>
     *  投信銘柄カテゴリーコード３
     */
    public String categoryCode3;
    
    /**
     * (投信銘柄カテゴリー名称３)<BR>
     *  投信銘柄カテゴリー名称３
     */
    public String categoryName3;
    
    /**
     * (注文受付締切時間)<BR>
     *  注文受付締切時間<BR>
     *  "HH：MM" (24時間形式で渡される）
     */
    public String orderCloseTime;
    
    /**
     * (投信管理者銘柄表示順序登録一覧行)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 4153BBC9019E
     */
    public WEB3MutualDisplayOrderGroup()
    {
    }
}
@
