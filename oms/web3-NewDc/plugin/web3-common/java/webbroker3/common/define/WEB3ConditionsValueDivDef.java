head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ConditionsValueDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 条件値区分(WEB3ConditionsValueDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * サービス申込条件マスターテーブル.条件値区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3ConditionsValueDivDef
{

    /**
     * 0：無　@　@
     */
    public final static String HAVE_NOT = "0";

    /**
     * 1：有　@
     */
    public final static String HAVE = "1";
    
}@
