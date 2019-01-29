head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座種別定数定義インタフェイス(WEB3AccTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/23 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 口座種別定数定義インタフェイス<BR>
 * (口座開設区分テーブルの口座種別の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3AccTypeDef
{
    /**
     * 01：FX
     */
    public final static String FX = "01";

    /**
     * 02：CFD
     */
    public final static String CFD = "02";
}@
