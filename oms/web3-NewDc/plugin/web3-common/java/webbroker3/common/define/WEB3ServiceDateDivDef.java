head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ServiceDateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付日区分  定数定義インタフェイス(WEB3ServiceDateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 受付日区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3ServiceDateDivDef
{

    /**
     * １：当日 
     */
    public static final String DAY_BIZ_DATE = "1";

    /**
     *  ２：翌日
     */
    public static final String DATE = "2";
    
}
@
