head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAcceptUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : Oฎ๓tXVT[rX(WEB3FeqAcceptUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 เ๒(u) VK์ฌ
                 : 2005/07/26 ค๛U(u) r[
                 : 2006/12/19 Gฬ(u) f@@No.314ฮ
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import webbroker3.common.WEB3BaseException;

/**
 * (Oฎ๓tXVT[rX) <BR>
 * Oฎ๓tXVT[rXC^tFCX
 * 
 * @@ author เ๒ 
 * @@ version 1.0
 */
public interface WEB3FeqAcceptUpdateService extends Service
{
    
    /**
     * (update๓t) <BR>
     * ถ๓tXV๐sคB
     * @@param l_marketResponseMessage - (s๊X|XbZ[W)
     * @@throws WEB3BaseException
     * @@roseuid 42A579180083
     */
    public void updateAccept(MarketResponseMessage l_marketResponseMessage) throws WEB3BaseException;

    /**
     * (gets๊X|XbZ[W)<BR>
     * ฯXใ๓tๆชษฮท้s๊X|XbZ[W๐ถฌติpท้B<BR>
     * <BR>
     * @@param l_lngOrderId - (ถhc)<BR>
     * ถhc<BR>
     * @@param l_strAfterChangeAcceptDiv - (ฯXใ๓tๆช)<BR>
     * ฯXใ๓tๆช<BR>
     * <BR>
     * 01Fถ๓tฯ <BR>
     * 02Fถ๓tG[ <BR>
     * 03Fถ๓tฯๆม<BR>
     * <BR>
     * 11F๙ณฯ <BR>
     * 12F๙ณG[<BR>
     * <BR>
     * 21Fๆมฯ <BR>
     * 22FๆมG[<BR>
     * <BR>
     * 31Foธ<BR>
     * @@return MarketResponseMessage
     */
    public MarketResponseMessage getMarketResponseMessage(
        long l_lngOrderId, String l_strAfterChangeAcceptDiv);
}
@
