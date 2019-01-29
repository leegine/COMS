head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashInOutAmountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金額区分　@定数定義(WEB3AioCashInOutAmountDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/12 韋念瓊 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 入出金額区分  定数定義インタフェイス
 * <BR>
 * @@author 韋念瓊
 * @@version 1.0
 */
public class WEB3AioCashInOutAmountDivDef 
{
    
    /**
     * 1：入出金額
     */
    public static final String CASHIN_OUT_AMOUNT = "1";
    
    /**
     * 2：入出金額2
     */
    public static final String CASHIN_OUT_AMOUNT2 = "2";
    
    /**
     * 3：入出金額と入出金額2の両方
     */
    public static final String CASHIN_OUT_AMOUNT_AND_AMOUNT2 = "3";
  
}
@
