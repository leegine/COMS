head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InputDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入力区分  定数定義インタフェイス(WEB3InputDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/22　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 金融機@関連携入出金状況テーブル.入力区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3InputDivDef
{
    /**
      * null：PC(WEB) 
      */
    public static final String PC = null;

    /**
     *  1：コールセンター 
     */
    public static final String CALLCENTER = "1";

    /**
     *  ２：I-mode 
     */
    public static final String I_MODE = "2";

    /**
     *  ３：SlingShot (スリングショット) 
     */
    public static final String SLINGSHOT = "3";

    /**
     *  ４：J-Phone (Vodafone )
     */
    public static final String J_PHONE = "4";

    /**
     *  ５：EZ-WEB (AU)
     */
    public static final String EZ_WEB = "5";

    /**
     *  ９：HOST
     */
    public static final String HOST = "9";

}
@
