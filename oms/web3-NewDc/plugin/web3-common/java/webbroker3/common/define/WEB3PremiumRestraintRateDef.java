head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PremiumRestraintRateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 概算金額計算方式フラグ定数定義クラス(WEB3PremiumRestraintRateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/23 今井　@高史(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 概算金額計算方式フラグ定数定義クラス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3PremiumRestraintRateDef
{

    /**
     * 1:STOP高拘束方式
     */
    public final static int STOP_QUANTITY_RESTRANT = 1;

    /**
     * 2:割増拘束方式
     */
    public final static int PREMIUM_RESTRANT = 2;
}
@
