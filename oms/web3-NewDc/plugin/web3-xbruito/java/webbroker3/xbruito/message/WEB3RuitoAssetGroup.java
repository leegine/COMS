head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoAssetGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÛLYês(WEB3RuitoAssetGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ü E (u) VKì¬
                   2004/12/03 èèOàù (u) cÎ
*/
package webbroker3.xbruito.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * ÛLYês<BR>
 */
public class WEB3RuitoAssetGroup extends Message
{

    /**
     * Á¿R[h<BR>
     */
    public String ruitoProductCode;

    /**
     * Á¿¼<BR>
     */
    public String ruitoProductName;

    /**
     * c<BR>
     */
    public String ruitoBalance;

    /**
     * à30úoßc<BR>
     */
    public String ruito30DayPassBal;

    /**
     * à30ú¢oßc<BR>
     */
    public String ruito30DayNotPassBal;

    /**
     * MõàY¯Ûz<BR>
     */
    public String estateReserve;

    /**
     * ðñÂ\c<BR>
     */
    public String ruitoSellPossBalance;

    /**
     * ðñ¶¾×<BR>
     */
    public webbroker3.xbruito.message.WEB3RuitoSellOrderUnit[] ruitoSellOrderUnits;

    /**
     * ðñitjÂ\æª  <BR>
     * nullFæøÂ\  <BR>
     * PFVXeæøâ~G[  <BR>
     * QFótÔG[  <BR>
     * RFæøâ~  <BR>
     * SFÙ}â~  <BR>
     * TFSðñ  <BR>
     */
    public String sellPossDiv;

    /**
     * ftHgRXgN^<BR>
     * @@roseuid 40922B76032C
     */
    public WEB3RuitoAssetGroup()
    {
    }
}
@
