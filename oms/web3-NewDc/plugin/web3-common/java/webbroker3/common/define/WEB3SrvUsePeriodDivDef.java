head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SrvUsePeriodDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 利用期間区分(WEB3SrvUsePeriodDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * サービス利用期間料金テーブル.利用期間区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3SrvUsePeriodDivDef
{

    /**
     * 1：年　@　@
     */
    public final static String YEAR = "1";

    /**
     * 2：月　@　@
     */
    public final static String MONTH = "2";
    
    /**
     * 3：日
     */
    public final static String DATE = "3";
    
}
 @
