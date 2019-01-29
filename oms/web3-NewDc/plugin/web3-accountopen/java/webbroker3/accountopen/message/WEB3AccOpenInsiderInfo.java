head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInsiderInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 内部者情報(WEB3AccOpenInsiderInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/13 齊珂(中訊) 新規作成
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (内部者情報)<BR>
 * 内部者情報<BR>
 * 
 * @@author 齊珂
 * @@version 1.0 
 */
public class WEB3AccOpenInsiderInfo extends Message
{

    /**
     * (作成区分)<BR>
     * 作成区分<BR>
     */
    public String createDiv;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (関係区分)<BR>
     * 関係区分<BR>
     */
    public String relationCode;
    
    /**
     * (役員名)<BR>
     * 役員名<BR>
     */
    public String executive;
    
    /**
     * (役職名コード)<BR>
     * 役職名コード<BR>
     */
    public String positionCode;
    
    /**
     * (役職名)<BR>
     * 役職名<BR>
     */
    public String position;
    
    
}
@
