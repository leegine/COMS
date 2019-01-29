head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductConditionsGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄条件登録照会銘柄一覧行(WEB3MutualProductConditionsGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 投資信託銘柄条件登録照会銘柄一覧行<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualProductConditionsGroup extends Message 
{
    
    /**
     * 投信銘柄ID
     */
    public String id;
    
    /**
     * 投信銘柄コード
     */
    public String mutualProductCode;
    
    /**
     * 投信協会銘柄コード
     */
    public String mutualAssocProductCode;
    
    /**
     * 銘柄名
     */
    public String mutualProductName;
    
    /**
     * 投信銘柄カテゴリーコード
     */
    public String categoryCode;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40DF77740343
     */
    public WEB3MutualProductConditionsGroup() 
    {
     
    }
}
@
