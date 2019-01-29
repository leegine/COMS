head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OtherOrgCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OtherOrgCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/28 王蘭芬 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 外部機@関コード　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3OtherOrgCodeDef
{
    /**
     * 郵貯  ：ComOndebizz  
     */
    public static final String POSTAL = "ComOndebizz";

    /**
     * 東京三菱 ：ComOndebi59  
     */
    public static final String TOKYO_MITUBISI = "ComOndebi59";
    
    /**
     *　@UFJ  ：ComOndebi5F  
     */
    public static final String OPEN_CLOSE = "ComOndebi5F";
    
    /**
     * 三井住友  ：ComOndebi5H 
     */
    public static final String MITUISUMITOMO = "ComOndebi5H";
    
    /**
     * みずほ　@：ComOndebi51
     */
    public static final String MIZUHO = "ComOndebi51";
    
    /**
     * GFT：01 
     */
    public static final String GFT = "01";
    
    /**
     * UWG：02 
     */
    public static final String UWG = "02";
    
    /**
     * 電子鳩：03
     */
    public static final String BATO = "03";
}
@
