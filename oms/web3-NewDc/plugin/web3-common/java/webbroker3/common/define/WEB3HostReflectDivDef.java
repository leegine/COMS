head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostReflectDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : host反映区分 定数定義インタフェイス(WEB3HostReflectDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/26 栄イ (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 債券注文単位テーブルのhost反映区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3HostReflectDivDef
{
    /**
     * 0：未反映
     */
    public static final String NOT_REFLECT = "0";

    /**
     * 1：送信済
     */
    public static final String SENDED = "1";

    /**
     * 2:客勘反映済
     */
    public static final String CUSTOMER_CALCULATION_REFLECTED = "2";
}
@
