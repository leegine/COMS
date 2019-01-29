head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeOtherConstDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3GentradeOtherConstDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/01 li-yunfeng(sinocom)　@新規作成
Revesion History : 2004/07/23 孟 東(中訊)　@packageを変更
*/
package webbroker3.gentrade.define;

/**
 * その他定数定義インタフェイス
 *                                                                     
 * @@author li-yunfeng
 * @@version 1.0
 */
public interface WEB3GentradeOtherConstDef 
{

    /**
     * ドット 
     */ 
    public  static final String  CONMA = ".";
    
    /**
     * 「000」にフォーマットする
     */
    public  static final String  FORMAT_ZERO = "000";
    /**
     * 「000001」にフォーマットする
     */
    public  static final String  FORMAT_NUM1 = "000001";
    /**
     * 「000000」にフォーマットする
     */ 
    public  static final String  FORMAT_NUM2 = "000000";
    /**
     * 「00'」にフォーマットする
     */ 
    public  static final String  FORMAT_NUM3 = "'00'";
    /**
     * 「'0'」にフォーマットする
     */ 
    public  static final String  FORMAT_NUM4 = "'0'";
    /**
     * 「0」にフォーマットする
     */ 
    public  static final String  FORMAT_NUM5 = "0";

}
@
