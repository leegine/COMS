head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報ソートキー(WEB3AdminMailInfoSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (メール情報ソートキー)<BR>
 * メール情報ソートキー　@データクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoSortKey extends Message 
{
    
    /**
     * (キー項目)<BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中<BR>
     * のシンボル名をキー項目とする。 <BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A:昇順　@D:降順<BR>
     */
    public String ascDesc;
    
    /**
     * (メール情報ソートキー)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 413C13D8032C
     */
    public WEB3AdminMailInfoSortKey() 
    {
     
    }
}
@
