head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.55.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�������N�G�X�g(WEB3AdminMCAdminPermGrpChangeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 ���z (���u) �V�K�쐬 
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�������N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�������N�G�X�g<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeCompleteRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_admin_perm_grp_change_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411181510L;
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpChangeCompleteRequest.class);
    
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
     * @@roseuid 4198641B00AB
     */
    public WEB3AdminMCAdminPermGrpChangeCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�Ǘ��҃^�C�v���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01218           <BR>
     * �@@�P�|�Q�j�@@�Ǘ��҃^�C�v���.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����\�@@�\�J�e�S���ꗗ�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@null�łȂ��ꍇ�A�����\�@@�\�J�e�S���ꗗ.validate()���R�[������B<BR>
     * <BR>
     * @@roseuid 417724F90399
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //�P�j�Ǘ��҃^�C�v���̃`�F�b�N
        //�P�|�P�j�����͂̏ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException 
        //         tag:BUSINESS_ERROR_01218 
        if (this.adminTypeUnit == null || "".equals(this.adminTypeUnit))
        {
            log.error("�Ǘ��҃^�C�v��񖢓���");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01218,
                this.getClass().getName() + "." + l_strMethodName);             
        }
        //�P�|�Q�j�Ǘ��҃^�C�v���.validate()���R�[������B
        this.adminTypeUnit.validate();
        
        //�Q�j�����\�@@�\�J�e�S���ꗗ�̃`�F�b�N<BR>
        //�P�|�P�jnull�łȂ��ꍇ�A�����\�@@�\�J�e�S���ꗗ.validate()���R�[������B
        if ((this.transactionCategoryUnits != null) && !("".equals(this.adminTypeUnit)))
        {
            for (int i = 0; i < transactionCategoryUnits.length; i++)
            {
                this.transactionCategoryUnits[i].validate();    
            }
        }
     
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641B00DA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpChangeCompleteResponse(this);
    }
}
@
