head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontNoticeHistoryInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ʒm�����Q�Ɠ��̓��N�G�X�g�N���X (WEB3AdminFrontNoticeHistoryInputRequest.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �Ǘ��ҁE�ʒm�����Q�Ɠ��̓��N�G�X�g�N���X<BR>
 */
public class WEB3AdminFrontNoticeHistoryInputRequest extends WEB3GenRequest 
{

	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_front_Notice_History_Input";
   											
   /**
    * �،���ЃR�[�h<BR>
    */
   public String institutionCode;
   
   /**
    * @@roseuid 42FFFEAE011C
    */
   public WEB3AdminFrontNoticeHistoryInputRequest() 
   {
    
   }

    /* (�� Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse() {
	   // TODO �����������ꂽ���\�b�h�E�X�^�u
       return new WEB3AdminFrontNoticeHistoryInputResponse(this);
    }
}
@
