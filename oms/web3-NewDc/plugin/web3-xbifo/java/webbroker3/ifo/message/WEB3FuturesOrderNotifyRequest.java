head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ʒm���N�G�X�g(WEB3FuturesOrderNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�����w���敨�����ʒm���N�G�X�g)<BR>
 * �����w���敨�����ʒm���N�G�X�g�N���X
 * @@author  : ������
 * @@version : 1.0
 */
public class WEB3FuturesOrderNotifyRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_orderNotify";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412081451L;
    
   /**
    * @@roseuid 41AD6547000F
    */
   public WEB3FuturesOrderNotifyRequest() 
   {
    
   }
   
   /**
    * @@return WEB3GenResponse
    * @@roseuid 41AD6547001F
    */
   public WEB3BackResponse createResponse() 
   {
       return new WEB3FuturesOrderNotifyResponse(this);
   }
}
@
