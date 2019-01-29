head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransferSkipOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替スキップ注文(WEB3FXTransferSkipOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/28 鄭徳懇(中訊) 新規作成
Revesion History : 2008/09/10 王志葵(中訊) 仕様変更・モデル976
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX振替スキップ注文)<BR>
 * FX振替スキップ注文<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3FXTransferSkipOrderUnit extends Message
{
    
    /**
     * (利用者コード)<BR>
     * 利用者コード<BR>
     */
    public String userCode;

    /**
     * (入出金番号)<BR>
     * 入出金番号<BR>
     */
    public String cashInoutNumber;    

    /**
     * コンストラクタ
     * @@roseuid 43CDDA480186
     */
    public WEB3FXTransferSkipOrderUnit() 
    {
     
    }
}
@
