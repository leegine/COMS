head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PayStartDateAdvancedDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3PayStartDateAdvancedDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 支払開始日繰上げ区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3PayStartDateAdvancedDivDef
{
    /**
     * 1:繰上げ 
     */
    public static final String MOVE_FORWARD = "1";

    /**
     * 2:繰下げ
     */
    public static final String MOVE_DOWN = "2";

}@
