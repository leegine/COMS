head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用ソートキー(WEB3SrvRegiSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用ソートキー)<BR>
 * サービス利用ソートキー　@データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3SrvRegiSortKey extends Message
{
   
    /**
     * (キー項目)<BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名をキー項目とする。 <BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A:昇順　@D:降順<BR>
     */
    public String ascDesc;
    
    /**
     * (サービス利用ソートキー)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 410762EE0150
     */
    public WEB3SrvRegiSortKey() 
    {
     
    }
}
@
