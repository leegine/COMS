head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保有銘柄明細(WEB3AioSecurityTransferProductCodeNameUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (保有銘柄明細)<BR>
 * 保有銘柄明細クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferProductCodeNameUnit extends Message
{    
    /**
     * (商品タイプ)<BR>
     * 商品タイプ<BR>
     * <BR>
     * 1： 株式<BR>
     * 2： 債券<BR>
     * 3： 投資信託<BR>
     */
    public String instrumentsType;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名
     */
    public String productName;
    
    /**
     * (保有銘柄明細)<BR>
     * コンストラクタ
     * @@roseuid 4163C9AD030F
     */
    public WEB3AioSecurityTransferProductCodeNameUnit() 
    {
      
    }
}
@
