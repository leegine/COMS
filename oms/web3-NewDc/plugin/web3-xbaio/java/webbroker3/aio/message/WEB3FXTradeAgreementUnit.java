head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTradeAgreementUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX取引同意質問情報(WEB3FXTradeAgreementUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
Revesion History : 2008/10/07 王志葵 (中訊) 仕様変更・モデル1061
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX取引同意質問情報) <BR>
 * FX取引同意質問情報 <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTradeAgreementUnit extends Message
{
    /**
     * (質問番号) <BR>
     * 質問番号 <BR>
     */
    public String questionNumber;

    /**
     * (質問内容) <BR>
     * 質問内容 <BR>
     */
    public String questionContents;

    /**
     * (質問回答) <BR>
     * <BR>
     * 1：同意 <BR>
     * 2：非同意 <BR>
     * A：回答A<BR>
     * B：回答B<BR>
     * C：回答C<BR>
     * D：回答D<BR>
     */
    public String questionAnswer;

    /**
     * (FX取引同意質問情報) <BR>
     * コンストラクタ。 <BR>
     * 
     * @@return webbroker3.aio.message.WEB3FXTradeAgreementUnit
     * @@roseuid 41B039BB0108
     */
    public WEB3FXTradeAgreementUnit()
    {
    }
}@
