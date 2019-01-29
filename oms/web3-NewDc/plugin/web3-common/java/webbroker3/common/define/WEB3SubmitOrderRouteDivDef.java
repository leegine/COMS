head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SubmitOrderRouteDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 発注経路区分定数定義インタフェイス(WEB3SubmitOrderRouteDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 発注経路区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3SubmitOrderRouteDivDef
{
    /**
     * 0：SONAR正系
     */
    public static final String SONAR_MAIN_FACTION = "0";

    /**
     * 1：SONAR副系
     */
    public static final String SONAR_SUB_FACTION = "1";

    /**
     * 2：フロント発注正系
     */
    public static final String FRONT_ORDER_MAIN_FACTION = "2";

    /**
     * 3：フロント発注副系
     */
    public static final String FRONT_ORDER_SUB_FACTION  = "3";

    /**
     * 9：発注停止
     */
    public static final String ORDER_STOP  = "9";
}
@
