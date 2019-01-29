head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommissionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 無手数料区分定数定義インタフェイス(WEB3CommissionDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 無手数料区分 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3CommissionDivDef
{

    /**
     * 5：乗換優遇　@　@　@　@  　@　@
     */
    public final static String SWITCHING_PREFERENCE = "5";

    /**
     * 9：無手数料
     */
    public final static String NO_COMMISSION = "9";

} @
