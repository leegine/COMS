head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoOrderPermitDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文認可区分 定数定義インタフェイス(WEB3AccInfoOrderPermitDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 注文認可区分 定数定義インタフェイス<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AccInfoOrderPermitDivDef 
{
    /**
     * 0：　@非認可　@　@
     */
    public final static String NOT_AUTHORIZATION = "0";
    
    /**
     *  1：　@認可<BR>
     * 　@　@
     */
    public final static String AUTHORIZATION = "1";
    

}
@
