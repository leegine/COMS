head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3OrderAppStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申告申込状況区分定数定義インタフェイス(WEB3OrderAppStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10  李海波(sinocom)　@新規作成
*/
package webbroker3.ipo.define;

/**
 * 申告申込状況区分
 *                                                                     
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3OrderAppStatusDef
{

    /**
     * 01：ブックビルディング申告済<BR>
     */
    public static final String BOOK_BUILDING_DEMAND_COMPLETE = "01";

    /**
     * 02：ブックビルディングキャンセル<BR>
     */
    public static final String BOOK_BUILDING_DEL = "02";
    
    /**
     * 03：当選
     */
    public static final String ELECTION = "03";
    
    /**
     * 04：当選キャンセル
     */
    public static final String ELECTION_DEL = "04";
    
    /**
     * 05：申込済
     */
    public static final String APPLICATION = "05";
    
    /**
     * 06：約定済
     */
    public static final String EXECUTED = "06";
    
    /**
     * 07：補欠
     */
    public static final String SUPPLEMENT = "07";
    
    /**
     * 08：補欠キャンセル
     */
    public static final String SUPPLEMENT_DEL = "08";
    
    /**
     * 09：補欠申込済
     */
    public static final String SUPPLEMENT_APPLICATION = "09";
    
    /**
     * 10：補欠約定済
     */
    public static final String SUPPLEMENT_EXECUTED = "10";
    
    /**
     * 11：補欠落選
     */
    public static final String SUPPLEMENT_DEFEAT = "11";
    
    /**
     * 12：落選
     */
    public static final String DEFEAT = "12";
    
    
    
    
    
    
}@
