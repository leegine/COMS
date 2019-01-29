head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TheDayTransferDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TheDayTransferDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 韋念瓊 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 証券会社テーブルの当日出金振込実施　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */
public interface WEB3TheDayTransferDef
{
    /**
     * 0 : 未実施
     */
    public static final String NOT_ENFORCEMENT = "0";

    /**
     * 1 : 実施
     */
    public static final String ENFORCEMENT = "1";    
}
@
