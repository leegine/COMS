head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.55.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�������N�G�X�g(WEB3AdminMCAdminPermGrpRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�������N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�������N�G�X�g<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpRegistCompleteRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpRegistCompleteRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpRegistComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     * <BR>
     */
    public String password;
    
    /**
     * (�Ǘ��҃^�C�v���)<BR>
     * �Ǘ��҃^�C�v���<BR>
     */
    public WEB3AdminMCAdminTypeUnit adminTypeUnit;
    
    /**
     * (�����\�@@�\�J�e�S���ꗗ)<BR>
     * �����\�@@�\�J�e�S���ꗗ<BR>
     */
    public WEB3AdminMCTransactionCategoryUnit[] transactionCategoryUnits;
    
    /**
     * @@roseuid 4198641E0261
     */
    public WEB3AdminMCAdminPermGrpRegistCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�Ǘ��҃^�C�v���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01218          <BR>
     * �@@�P�|�Q�j�@@�Ǘ��҃^�C�v���.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����\�@@�\�J�e�S���ꗗ�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@null�łȂ��ꍇ�A�����\�@@�\�J�e�S���ꗗ.validate()���R�[������B<BR>
     * <BR>
     * @@roseuid 417617C60206
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);

        // �P�j�@@�Ǘ��҃^�C�v���̃`�F�b�N<BR>
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :     BUSINESS_ERROR_01218          <BR>
        if (this.adminTypeUnit == null )
        {
            log.error("�Ǘ��҃^�C�v��񖢓���.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01218,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }   
        
        // �@@�P�|�Q�j�@@�Ǘ��҃^�C�v���.validate()���R�[������B<BR>
        this.adminTypeUnit.validate();

        // �Q�j�@@�����\�@@�\�J�e�S���ꗗ�̃`�F�b�N<BR>
        // �@@�P�|�P�j�@@null�łȂ��ꍇ�A�����\�@@�\�J�e�S���ꗗ.validate()���R�[������B<BR>
        if (this.transactionCategoryUnits != null )
        {
            int l_length = this.transactionCategoryUnits.length;
            for(int i = 0; i < l_length; i++)
            {
                this.transactionCategoryUnits[i].validate();
            }
        }   
          
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641E02AF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpRegistCompleteResponse(this);
    }
}
@
