head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 預り証券明細(WEB3AioSecurityTransferUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (預り証券明細)<BR>
 * 預り証券明細クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferUnit extends Message
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
     * (口座区分)<BR>
     * 口座区分（株式、投信の場合）<BR>
     * <BR>
     * 0： 一般<BR>
     * 1： 特定<BR>
     * <BR>
     * ※株式、投資以外の場合、null
     */
    public String taxType;
    
    /**
     * (数量)<BR>
     * 保有数量
     */
    public String changePossQuantity;
    
    /**
     * (評価額)<BR>
     * 評価額
     */
    public String marketValue;
    
    /**
     * (預り区分)<BR>
     * 預り区分<BR>
     * <BR>
     * 1： 保護<BR>
     * 2： 代用
     */
    public String depositDiv;
    
    /**
     * (預り証券明細)<BR>
     * コンストラクタ
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit
     * @@roseuid 416D1BA80187
     */
    public WEB3AioSecurityTransferUnit() 
    {
     
    }
}
@
