head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellPossibleDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3RuitoSellPossibleDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 wei-nianqiong(sinocom)　@新規作成
*/
package webbroker3.xbruito.define;

/**
 * 解約（売付）可能区分　@定数定義インタフェイス
 *                                                                      
 * @@author wei-nianqiong
 * @@version 1.0
 */
public interface WEB3RuitoSellPossibleDivDef
{ 
    /**
     * null：取引可能
     */
    public static final String TRADING_POSSIBLE = null;

    /**
     * １：システム取引停止エラー
     */
    public static final String SYSTEM_TRADING_STOP_ERROR = "1";
    
    /**
     * ２：受付時間エラー
     */
    public static final String ACCEPTED_TIME_ERROR = "2";
    
    /**
     * ３：取引停止中
     */
    public static final String TRADING_STOPING= "3";
    
    /**
     * ４：緊急停止中
     */
    public static final String SCRAM_STOPING = "4";
    
    /**
     * ５：全部解約中
     */
    public static final String ALL_SELLING = "5";
    

}@
