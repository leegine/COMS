head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー明細(WEB3AdminPointCategoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (カテゴリー明細)<BR>
 * カテゴリー明細クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryUnit extends Message
{
    
    /**
     * (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     */
    public String categoryNo;
    
    /**
     * (カテゴリー名)<BR>
     * カテゴリーの名称<BR>
     */
    public String categoryName;
    
    /**
     * (カテゴリー概要)<BR>
     * カテゴリーの概要<BR>
     */
    public String categoryOutline;
    
    /**
     * (カテゴリー明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4187248E03DF
     */
    public WEB3AdminPointCategoryUnit() 
    {
     
    }
}
@
