head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�������͉�ʃ��N�G�X�g(WEB3AdminAioCashinNoticeChangeInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/20 ���r (���u) �V�K�쐬
                 : 2006/11/09 ���G��(���u) �d�l�ύX ���f�� 682
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����ʒm�������͉�ʃ��N�G�X�g)<BR>
 * �����ʒm�������͉�ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ���r(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeChangeInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_notice_change_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211657L;    
        
    /**
     * (�����ʒm�e�[�u��ID)<BR>
     * �f�[�^�捞�敪 + �����ʒm�e�[�u��ID<BR>
     */
    public String[] cashinNoticeTableId;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeChangeInputRequest.class);

    
    /**
     * @@roseuid 4158EB620313
     */
    public WEB3AdminAioCashinNoticeChangeInputRequest() 
    {
     
    }
   
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �����ʒm�������͉�ʃ��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashinNoticeChangeInputResponse(this);
    }
    
    /**
     * �����ʒm�e�[�u��ID�̔z�񂠂邢��<BR>
     * ���̗v�f��null�̏ꍇ<BR>
     * �u�����ʒm�e�[�u��ID���w��G���[�v���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_02345<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        

        if (this.cashinNoticeTableId == null)
        {
        log.debug("�����ʒm�e�[�u��ID���w��ł��B");
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_02345,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            "�����ʒm�e�[�u��ID���w��ł��B");
        }
        
        for (int i = 0; i < this.cashinNoticeTableId.length; i++)
        {
            if (WEB3StringTypeUtility.isEmpty(this.cashinNoticeTableId[i]))
            {
            log.debug("�����ʒm�e�[�u��ID���w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02345,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ʒm�e�[�u��ID���w��ł��B");
            }
        }
       
    }
}
@
