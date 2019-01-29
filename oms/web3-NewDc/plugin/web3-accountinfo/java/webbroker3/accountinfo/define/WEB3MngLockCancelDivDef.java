head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3MngLockCancelDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （送信用）管理解除区分(WEB3MngLockCancelDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/22 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * （送信用）管理解除区分 定数定義インタフェイス <BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public interface WEB3MngLockCancelDivDef 
{
    /**
     *  0：未登録 <BR>
     * 　@　@
     */
    public final static String NOTREGISTER = "0";
    
    /**
     * 1：登録　@　@
     */
    public final static String REGISTER = "1";
}
@
