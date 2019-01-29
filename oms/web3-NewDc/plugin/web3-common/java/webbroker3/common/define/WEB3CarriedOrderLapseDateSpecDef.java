head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CarriedOrderLapseDateSpecDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3CarriedOrderLapseDateSpecDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 li-yunfeng(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * (出来るまで注文)失効日指定　@定数定義インタフェイス
 *                                                                     
 * @@author li-yunfeng
 * @@version 1.0
 */
public interface WEB3CarriedOrderLapseDateSpecDef
{    
    /**
      * (出来るまで注文)失効日指定  0：期間内失効日ユーザ指定可 
      */
    public static final String EXPIRATION_DATE_USER_DES = "0";
    
    /**
      * (出来るまで注文)失効日指定  1：最終日のみ指定可 
      */
    public static final String FINAL_DATE_DESIGNATA = "1";
 
}
@
