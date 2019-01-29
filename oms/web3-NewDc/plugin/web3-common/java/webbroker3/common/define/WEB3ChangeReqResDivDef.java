head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ChangeReqResDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 切替指示応答区分コード定数定義インタフェイス(WEB3ChangeReqResDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 切替指示応答区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3ChangeReqResDivDef
{
    /**
     * 01:通番照会要求
     */
    public static final String SERIAL_NUMBER_REFERENCE_REQUEST = "01";

    /**
     * 02:通番照会応答
     */
    public static final String SERIAL_NUMBER_REFERENCE_RESPONSE = "02";

    /**
     * 03:通知代行解除要求
     */
    public static final String NOTICE_AGENCY_RELEASE_REQUEST = "03";

    /**
     * 04:通知代行解除応答
     */
    public static final String NOTICE_AGENCY_RELEASE_RESPONSE = "04";

    /**
     * 05:通知代行要求
     */
    public static final String NOTICE_AGENCY_REQUEST = "05";

    /**
     * 06:通知代行応答
     */
    public static final String NOTICE_AGENCY_RESPONSE = "06";

    /**
     * 07:通知再送要求
     */
    public static final String NOTICE_RESEND_REQUEST = "07";

    /**
     * 08:通知再送応答
     */
    public static final String NOTICE_RESEND_RESPONSE = "08";

    /**
     * 09:SONAR全障害
     */
    public static final String SONAR_ALL_TROUBLES = "09";
}
@
