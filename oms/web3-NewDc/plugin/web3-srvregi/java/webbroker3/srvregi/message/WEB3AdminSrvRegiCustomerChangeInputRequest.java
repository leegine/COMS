head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��N�G�X�g(WEB3AdminSrvRegiCustomerChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��N�G�X�g�N���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerChangeInputRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiCustomerChangeInputRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (��������)
     */
    public WEB3AdminSrvRegiCustomerSearchCondition[ ] searchConditions;
    
    /**
     * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE585301EA
     */
    public WEB3AdminSrvRegiCustomerChangeInputRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F4EC5F0298
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiCustomerChangeInputResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     *  1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.�T�[�r�X�敪�̌���!=2���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) ���������̃`�F�b�N<BR>
     * �@@this.���������̌������A�ȉ����`�F�b�N����B<BR>
     * �@@�inull�̏ꍇ�A�܂��͗v�f����0�̏ꍇ�A��O���X���[����B�j<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00945<BR>
     *  2-1) this.��������.�\���o�^ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00832<BR>
     *  2-2) this.��������.���X�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *  2-3) this.��������.���X�R�[�h�̌���!=3���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *  2-4) this.��������.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     *  2-5) this.��������.�ڋq�R�[�h�̌���!=6���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4EC7D01EC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X�敪�̃`�F�b�N
        //1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X�敪.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //1-2) this.�T�[�r�X�敪�̌���!=2���̏ꍇ�A��O���X���[����B
        if (this.serviceDiv.length() != 2)
        {         
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X�敪�̌���.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //2) ���������̃`�F�b�N
        if (this.searchConditions == null || this.searchConditions.length == 0)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00945,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("��������.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;            
        }
        else
        {
            int l_intArrayLengh = this.searchConditions.length;
            for (int i = 0; i < l_intArrayLengh; i++)
            {
                //2-1) this.��������.�\���o�^ID==null�̏ꍇ�A��O���X���[����B
                if (this.searchConditions[i].applyRegId == null || 
                    "".equals(this.searchConditions[i].applyRegId.trim()))
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00832,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("��������.�\���o�^ID.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                
                //2-2) this.��������.���X�R�[�h==null�̏ꍇ�A��O���X���[����B
                if (this.searchConditions[i].branchCode == null
                    || "".equals(this.searchConditions[i].branchCode.trim()))
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("��������.���X�R�[�h.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                
                //2-3) this.��������.���X�R�[�h�̌���!=3���̏ꍇ�A��O���X���[����B
                if (this.searchConditions[i].branchCode.length() != 3)
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("��������.���X�R�[�h�̌���.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                
                //2-4) this.��������.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B
                if (this.searchConditions[i].accountCode == null ||
                    "".equals(this.searchConditions[i].accountCode.trim()))
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("��������.�ڋq�R�[�h.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                                
                //2-5) this.��������.�ڋq�R�[�h�̌���!=6���̏ꍇ�A��O���X���[����B
                if (this.searchConditions[i].accountCode.length() != 6)
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("��������.�ڋq�R�[�h�̌���.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }               
            }            
        }       
        log.exiting(STR_METHOD_NAME);
    }
}
@
