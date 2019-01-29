head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderexecutionEndTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出来終了区分定数定義インタフェイス(WEB3OrderexecutionEndTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/12 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 出来終了区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3OrderexecutionEndTypeDef
{
    /**
     * 1：夕場前出来終了（夕場実施する会社）
     */
    public final static String BEFORE_EVENING_SESSION_ORDEREXECUTION_END = "1";

    /**
     * 0：DEFAULT（出来終了（最終））
     */
    public final static String DEFAULT = "0";
}
@
