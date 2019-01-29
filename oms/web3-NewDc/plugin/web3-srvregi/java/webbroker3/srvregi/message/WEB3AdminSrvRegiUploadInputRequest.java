head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiUploadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���̓��N�G�X�g(WEB3AdminSrvRegiUploadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���̓��N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃A�b�v���[�h���̓��N�G�X�g�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiUploadInputRequest extends WEB3GenRequest 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiUploadInputRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_uploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃A�b�v���[�h���̓��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 410075E400B6
     */
    public WEB3AdminSrvRegiUploadInputRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��҃A�b�v���[�h���̓��X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4100765200C6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiUploadInputResponse();
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     * �@@this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4100765200D5
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X�敪�̃`�F�b�N
        if(this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //U00871 BEGIN...
        if (this.serviceDiv.length() != 2)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                getClass().getName() + STR_METHOD_NAME); 
        }
        //U00871 END.

        log.exiting(STR_METHOD_NAME);     
    }
}
@
