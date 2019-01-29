head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransmissionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TransmissionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * ホスト転送　@定数定義インタフェイス
 *
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3TransmissionDef
{
    /**
     * 0 : 未転送
     */
    public static final String NOT_TRANSMIT = "0";

    /**
     * 1 : 転送済
     */
    public static final String ALREADY_TRANSMIT = "1";

}
@
