head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍Čv�Z����Request�N���X(WEB3AdminTPReCalcInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 *  �]�͍Čv�Z���̓��N�G�X�g�N���X
 */
public class WEB3AdminTPReCalcInputRequest extends WEB3GenRequest 
{
   
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_recalc_input";
   
   /**
    */
   public WEB3AdminTPReCalcInputRequest() 
   {
    
   }
      
   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse() 
   {
    return new WEB3AdminTPReCalcInputResponse();
   }
   
   
}
@
