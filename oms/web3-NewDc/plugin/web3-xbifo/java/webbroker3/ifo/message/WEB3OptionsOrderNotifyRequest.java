head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ʒm���N�G�X�g(WEB3OptionsOrderNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�����w���I�v�V���������ʒm���N�G�X�g)<BR>
 * �����w���I�v�V���������ʒm���N�G�X�g�N���X<BR>
 * @@author  : ������
 * @@version : 1.0
 */
public class WEB3OptionsOrderNotifyRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_orderNotify";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412081451L;
      
   /**
    * @@roseuid 41AAE7E8006D
    */
   public WEB3OptionsOrderNotifyRequest() 
   {
    
   }
   
   /**
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@roseuid 41AAE7E8009C
    */
   public WEB3BackResponse createResponse() 
   {
       return new WEB3OptionsOrderNotifyResponse(this);
   }
}
@
