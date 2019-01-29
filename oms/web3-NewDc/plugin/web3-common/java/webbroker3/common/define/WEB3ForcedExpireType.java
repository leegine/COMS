head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForcedExpireType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制失効区分定数定義インタフェイス(WEB3ForcedExpireType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/25 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 強制失効区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ForcedExpireType
{
    /**
     * 0：オープン
     */
    public final static String OPENING = "0";

    /**
     * 1：強制失効済
     */
    public final static String FORCED_EXPIRED = "1";
}
@
