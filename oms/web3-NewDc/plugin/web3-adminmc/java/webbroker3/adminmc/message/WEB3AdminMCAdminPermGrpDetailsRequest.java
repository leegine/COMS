head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.47.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��N�G�X�g(WEB3AdminMCAdminPermGrpDetailsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18  �Ɍ��t (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��N�G�X�g<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpDetailsRequest extends WEB3GenRequest 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpDetailsRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpDetails";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (�������x���R�[�h)<BR>
     * �������x���R�[�h<BR>
     */
    public String permissionLevel;
    
    /**
     * @@roseuid 4198641D0213
     */
    public WEB3AdminMCAdminPermGrpDetailsRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�������x���R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01201          <BR>
     * �@@�P�|�Q�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01202              <BR>
     * �@@�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01203             <BR>
     * <BR>
     * @@roseuid 417742ED03A9
     */
    public void validate() throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�������x���R�[�h�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.permissionLevel == null || "".equals(this.permissionLevel))
        {
            //��O
            log.error("�u�������x���R�[�h�����́v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01201,
                                            this.getClass().getName() + STR_METHOD_NAME);
        }       
         
        //�P�|�Q�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.permissionLevel) != 3)
        {
            //��O
            log.error("�u�������x���R�[�h��������3byte�łȂ��ꍇ�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01202,
                                            this.getClass().getName() + STR_METHOD_NAME);
        }      
          
        //�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.permissionLevel))
        {
            //��O
            log.error("�u�������x���R�[�h�����ȊO�̕������܂܂��ꍇ�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01203,
                                            this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641D0232
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpDetailsResponse(this);
    }
}
@
