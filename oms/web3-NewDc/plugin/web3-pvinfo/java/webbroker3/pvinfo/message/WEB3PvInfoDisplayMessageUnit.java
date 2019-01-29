head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDisplayMessageUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 表示メッセージ情報(WEB3PvInfoDisplayMessageUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
*/
package webbroker3.pvinfo.message;

import java.util.Date;


/**
 * (表示メッセージ情報)<BR>
 * 表示メッセージ情報クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoDisplayMessageUnit extends WEB3PvInfoDisplayContentsUnit 
{
    
    /**
     * (閲覧履歴ID)<BR>
     * 閲覧履歴ID<BR>
     */    
    public String browseHistoryId;
    
    /**
     * (表示メッセージ発生日)<BR>
     * 表示メッセージ発生日<BR>
     */
    public Date displayMessageDate;
    
    /**
     * (未読既読フラグ)<BR>
     * 未読既読フラグ<BR>
     * <BR>
     * false：　@未読<BR>
     * true：　@既読<BR>
     */
    public boolean readFlag;
    
    /**
     * (表示メッセージ情報)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDisplayMessageUnit
     * @@roseuid 4146714B03BB
     */
    public WEB3PvInfoDisplayMessageUnit() 
    {
     
    }
}
@
