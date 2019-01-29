head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RoundDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 未満処理区分定数定義インタフェイス(WEB3RoundDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 未満処理区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3RoundDivDef
{
    /**
     * 1:切り捨て
     */
    public final static String CUTTING_OFF = "1";

    /**
     * 2:四捨五入
     */
    public final static String ROUNDING_OFF = "2";

    /**
     * 3:切り上げ
     */
    public final static String RAISING_TO_A_UNIT = "3";

    /**
     * 4:スイス特殊方式
     */
    public final static String SWITZERLAND_SPECIALITY_FORM = "4";
}
@
