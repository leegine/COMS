head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング訂正確認レスポンスクラス(WEB3IPOBookBuildingChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 劉江涛(中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPOブックビルディング訂正確認レスポンスクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingChangeConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171437L;
    
    /**
     * 申告相当額
     */
    public String demandEquivalentPrice;
    
    /**
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * 確認時基準値
     */
    public String checkValue;
    
    /**
     * @@roseuid 4112EDC40035
     */
    public WEB3IPOBookBuildingChangeConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D16DE70315
     */
    public WEB3IPOBookBuildingChangeConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
