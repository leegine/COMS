head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityDataTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 設定データ型定数定義インタフェイス(WEB3AdminEquityDataTypeDef.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.define;

/**
 * 設定データ型定数定義インタフェイス
 * <BR>
 * @@author Babu Prasad
 * @@version 1.0
 */
public interface WEB3AdminEquityDataTypeDef
{
    /**
     * variable for VARCHAR2
     */
    public final static String VARCHAR2 = "0";

    /**
     * variable for NUMBER
     */
    public final static String NUMBER = "1";

    /**
     * variable for DECIMAL
     */
    public final static String DECIMAL = "2";
}
@
