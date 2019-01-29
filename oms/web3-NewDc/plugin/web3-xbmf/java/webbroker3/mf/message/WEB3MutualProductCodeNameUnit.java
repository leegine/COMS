head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  投資信託銘柄一覧用データクラス(WEB3MutualProductCodeNameUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
                   2004/12/07 于美麗 (中訊) 残対応
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (投信銘柄コード名称)
 * 投資信託銘柄一覧用データクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualProductCodeNameUnit extends Message 
{

    /**
     * 銘柄名に対応した銘柄コード<BR>
     */
    public String mutualProductCode;
    
    /**
     * 銘柄コードに対応した銘柄名<BR>
     */
    public String mutualProductName;
    
    /**
     * 買付口座区分一覧<BR>
     * <BR>
     * 0:一般　@1:特定<BR>
     */
    public String[ ] taxTypeList;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4073BCCD0389<BR>
     */
    public WEB3MutualProductCodeNameUnit() 
    {
     
    }
}
@
