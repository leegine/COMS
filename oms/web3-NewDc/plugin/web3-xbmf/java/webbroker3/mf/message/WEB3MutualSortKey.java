head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託ソートキーデータクラス(WEB3MutualSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 韋念瓊 (中訊) 新規作成
                   2004/08/24 黄建 (中訊) レビュー 
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * 投資信託ソートキーデータクラス<BR>
 * <BR>
 * 一覧表示時のソート順制御クラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSortKey extends Message 
{
    
    /**
     * (キー項目)<BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名を<BR>
     * キー項目とする。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A:昇順　@D:降順<BR>
     */
    public String ascDesc;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A9B8B4003A
     */
    public WEB3MutualSortKey() 
    {
     
    }
}
@
