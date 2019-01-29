head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替ソートキー(WEB3AioSecurityTransferSortKeyUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (証券振替ソートキー)<BR>
 * 証券振替ソートキークラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferSortKeyUnit extends Message
{    
    /**
     * (キー項目)<BR>
     * ソートの際のキーとなる項目<BR>
     * <BR>
     * 対象キー項目：<BR>
     * 商品タイプ<BR>
     * 銘柄コード<BR>
     * 口座区分<BR>
     * 数量<BR>
     * 評価額<BR>
     * 預り区分
     */
    public String keyItem;
    
    /**
     * A： 昇順<BR>
     * D： 降順
     */
    public String ascDesc;
    
    /**
     * @@roseuid 41B031A2036B
     */
    public WEB3AioSecurityTransferSortKeyUnit() 
    {
     
    }
}
@
