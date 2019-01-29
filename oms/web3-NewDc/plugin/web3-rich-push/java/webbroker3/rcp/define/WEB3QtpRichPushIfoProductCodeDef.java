head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.14.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3QtpRichPushIfoProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QtpRichPushIfoProductCodeDefクラス(WEB3QtpRichPushIfoProductCodeDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 孫(FLJ) 新規作成
 */
package webbroker3.rcp.define;

/**
 * 「IFO銘柄コード」の定数定義クラス<br>
 *
 * @@author　@孫(FLJ)
 * @@version 1.0
 */
public interface WEB3QtpRichPushIfoProductCodeDef
{
    
    public static final String QTP_NIKKEI_225 = "101";
    public static final String QTP_NIKKEI_MINI_225 = "111";
    public static final String QTP_NIKKEI_300 = "103";
    public static final String QTP_TOPIX = "151";

    public static final String WEB3_NIKKEI_225 = "0018";
    public static final String WEB3_NIKKEI_MINI_225 = "0019";
    public static final String WEB3_NIKKEI_300 = "0016";
    public static final String WEB3_TOPIX = "0005";
    
    public static final String QTP_OPTION_DIV_CALL = "4";
    public static final String QTP_OPTION_DIV_PUT = "3";

}
@
