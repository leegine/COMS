head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAttentionObjection.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済時注意文言(WEB3TPAttentionObjection.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
*/

package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (返済時注意文言)
 */
public class WEB3TPAttentionObjection
{

    /**
     * (注意文言表示区分)<BR>
     * <BR>
     * [返済前入金請求：○ or ×、返済後入金請求：×]<BR>
     * 　@・注意文言表示区分＝注意文言表示区分Def.注意文言表示無し<BR>
     * <BR>
     * [返済前入金請求：○、返済後入金請求：○]<BR>
     * 　@・注意文言表示区分＝注意文言表示区分Def.注意文言表示有り(返済前入金請求有り)<BR>
     * <BR>
     * [返済前入金請求：×、返済後入金請求：○]<BR>
     * 　@・注意文言表示区分＝注意文言表示区分Def.注意文言表示有り(返済前入金請求無し)<BR>
     */
    public String attentionObjectionType;

    /**
     * (入金請求額)<BR>
     * <BR>
     * [返済後入金請求：×]<BR>
     * 　@・入金請求額＝0<BR>
     * <BR>
     * [返済後入金請求：○]<BR>
     * 　@・入金請求額＝(直近の)返済後入金請求額<BR>
     */
    public double demandAmount;

    /**
     * @@roseuid 41E384290161
     */
    public WEB3TPAttentionObjection()
    {

    }

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("attentionObjectionType", this.attentionObjectionType)
            .append("demandAmount", this.demandAmount)
            .toString();
    }
}
@
