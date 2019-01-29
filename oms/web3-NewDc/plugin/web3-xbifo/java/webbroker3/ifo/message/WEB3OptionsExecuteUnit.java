head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション注文約定照会約定(WEB3OptionsExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 鄒鋭 (中訊) 新規作成
              001: 2004/07/28 王暁傑 (中訊) 対応 詳細設計チェック指摘事項 (日本側) 
                   com.fitechlabs.xtrade.kernel.message.Messageを継承。
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (株価指数オプション注文約定照会約定)<BR>
 * 株価指数オプション注文約定照会約定クラス<BR>                                                                    
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsExecuteUnit extends Message
{

    /**
     * 約定日時
     */
    public Date executionTimestamp;

    /**
     * 約定数量
     */
    public String execQuantity;

    /**
     * 約定単価
     */
    public String execPrice;

    /**
     * @@roseuid 40C0754B037A
     */
    public WEB3OptionsExecuteUnit()
    {

    }
}
@
