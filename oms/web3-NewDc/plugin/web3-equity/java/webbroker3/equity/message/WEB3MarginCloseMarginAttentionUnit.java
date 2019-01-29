head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginAttentionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引返済時注意文言(WEB3MarginCloseMarginAttentionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 沢村仁士 (SRA) 新規作成
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引返済時注意文言）。<BR>
 * <BR>
 * 信用取引返済時注意文言クラス<BR>
 */
public class WEB3MarginCloseMarginAttentionUnit extends Message 
{
    /**
     * (注意文言表示区分)<BR>
     * <BR>
     * 注意文言表示区分。 <BR>
     * （0：注意文言表示なし <BR>
     * 　@1：注意文言表示有り(返済前入金請求無し) <BR>
     * 　@2：注意文言表示有り(返済前入金請求有り)）<BR>
     */
    public String attentionDispDiv;
    
    /**
     * (入金請求額)<BR>
     * <BR>
     * 入金請求額。<BR>
     */
    public String payClaimAmount;
    
    /**
     * (信用取引返済時注意文言)<BR>
     * <BR>
     * コンストラクタ。
     * @@return WEB3MarginCloseMarginAttentionUnit
     */
    public WEB3MarginCloseMarginAttentionUnit() 
    {
     
    }
}
@
