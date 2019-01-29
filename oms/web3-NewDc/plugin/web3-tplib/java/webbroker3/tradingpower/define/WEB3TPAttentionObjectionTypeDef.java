head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAttentionObjectionTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意文言表示区分(WEB3TPAttentionObjectionTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
*/

package webbroker3.tradingpower.define;

/**
 * (注意文言表示区分)
 */
public interface WEB3TPAttentionObjectionTypeDef
{

    /**
     * (注意文言表示無し)<BR>
     * <BR>
     * 返済前入金請求：○ or　@×<BR>
     * 返済後入金請求：×<BR>
     */
    public final static String NO_ATTENTION = "0";

    /**
     * (注意文言表示有り(返済前入金請求無し)<BR>
     * <BR>
     * 返済前入金請求：×<BR>
     * 返済後入金請求：○<BR>
     */
    public final static String ATTENTION_AFTER_REPAY = "1";

    /**
     * (注意文言表示有り(返済前入金請求有り)<BR>
     * <BR>
     * 返済前入金請求：○<BR>
     * 返済後入金請求：○<BR>
     */
    public final static String ATTENTION_BEFORE_REPAY = "2";

}
@
