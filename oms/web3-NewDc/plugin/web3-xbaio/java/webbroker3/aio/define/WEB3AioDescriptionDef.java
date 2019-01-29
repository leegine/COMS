head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioDescriptionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioDescriptionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/7/21 韋念瓊 (中訊) 新規作成
                 : 2005/11/17 李俊 (中訊) フィデリティ対応 
                 : 2006/07/13 韋念瓊 (中訊) 仕様変更 No.598
*/

package webbroker3.aio.define;

/**
 * (振替記述)<BR>
 * 振替記述<BR>
 *
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public interface WEB3AioDescriptionDef
{
    /**
     * "feq_transfer"<BR>
     */
    public static final String FEQ_TRANSFER = "feq_transfer";
    
    /**
     * "cashout_gross"<BR>
     */    
    public static final String CASHOUT_GROSS = "cashout_gross";
    
    /**
     * '71'（株先証拠金：東証)<BR>
     */    
    public static final String DESCRIPTION_71 = "71";

    /**
     * '72'（株先証拠金：大証)<BR>
     */    
    public static final String DESCRIPTION_72 = "72";
}
@
