head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ChangeRoundDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 換算丸め方式 定数定義インタフェイス(WEB3ChangeRoundDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/28 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 換算丸め方式 定数定義インタフェイス<BR>
 *<BR>
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3ChangeRoundDivDef
{
    /**
     * 0：四捨五入
     */
    public final static String ROUNDING_OFF = "0";

    /**
     * 1：切捨
     */
    public final static String CUTTING_OFF = "1";

    /**
     * 2：切上
     */
    public final static String RAISING_TO_A_UNIT = "2";
}
@
