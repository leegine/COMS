head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontNoticeHistoryReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ʒm�����Q�ƃ��X�|���X�N���X (WEB3AdminFrontNoticeHistoryReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �Ǘ��ҁE�ʒm�����Q�ƃ��X�|���X�N���X<BR>
 */
public class WEB3AdminFrontNoticeHistoryReferenceResponse extends WEB3GenResponse 
{
	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_front_Notice_History_Reference";
   
   /**
    * ���y�[�W��<BR>
    */
   public String totalPages;
   
   /**
    * �����R�[�h��<BR>
    */
   public String totalRecords;
   
   /**
    * �\���y�[�W�ԍ�<BR>
    */
   public String pageIndex;
   
   /**
	* �s��ʒm���𖾍�[]<BR>
	*/
   public WEB3AdminFrontMarketNoticeHistoryUnit referenceList[];
   
   /**
    * @@roseuid 42FFFEAE01F7
    */
   public WEB3AdminFrontNoticeHistoryReferenceResponse() 
   {
    
   }
   
   /**
    *
    * @@param l_request WEB3GenRequest<BR>
    */
   public WEB3AdminFrontNoticeHistoryReferenceResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   }
}
@
