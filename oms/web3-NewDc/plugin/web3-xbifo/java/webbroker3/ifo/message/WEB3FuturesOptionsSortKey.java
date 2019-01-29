head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物オプションソートキー(WEB3FuturesOptionsSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李頴淵 (中訊) 新規作成
              001: 2004/08/05 王暁傑 (中訊) 対応バグ U00021
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (株価指数先物オプションソートキー)<BR>
 * 一覧表示時のソート順制御クラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3FuturesOptionsSortKey extends Message
{
    
    /**
     * (キー項目)<BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名<BR>
     * をキー項目とする。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A：昇順　@D：降順<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 40C0754400AB
     */
    public WEB3FuturesOptionsSortKey() 
    {
     
    }
}
@
