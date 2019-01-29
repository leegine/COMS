head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecInputCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҐV�K�����͋��ʃ��N�G�X�g(WEB3AdminBondExecInputCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҐV�K�����͋��ʃ��N�G�X�g)<BR>
 * �Ǘ��ҐV�K�����͋��ʃ��N�G�X�g�N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecInputCommonRequest extends WEB3GenRequest
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_input_common";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecInputCommonRequest.class);
    
    /**
     * (�����R�[�h(WEB3))<BR>
     * �����R�[�h�iWEB3�j
     */
    public String productCode;
    
    /**
     * (�ڋq���)<BR>
     * �ڋq���
     */
    public WEB3AdminBondAccountInfo accountInfo;
    
    /**
     * (�������)<BR>
     * �������
     */
    public WEB3AdminBondOrderInfo orderInfo;
    
    /**
     * (�����)<BR>
     * �����
     */
    public WEB3AdminBondOrderExecInfo execInfo;
    
    /**
     * (�Ǘ��Җ����͋��ʃ��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 44BDD06F0155
     */
    public WEB3AdminBondExecInputCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P)�@@�ڋq���`�F�b�N <BR>
     * �@@this.�ڋq���.validate()���R�[������B <BR>
     * <BR>
     * �Q)�@@�������`�F�b�N <BR>
     * �@@this.�������.validate()���R�[������B <BR>
     * <BR>
     * �R)�@@�����`�F�b�N <BR>
     * �@@this.�����.validate()���R�[������B <BR>
     * <BR>
     * �S)�@@�����R�[�h�iWEB3�j�`�F�b�N <BR>
     * �@@this.�����R�[�h�iWEB3�j==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00079
     * @@throws WEB3BaseException
     * @@roseuid 44BDD06F0194
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
        //�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
        //�P)�@@�ڋq���`�F�b�N 
        //this.�ڋq���.validate()���R�[������B 
        this.accountInfo.validate();
        
        //�Q)�@@�������`�F�b�N 
        //this.�������.validate()���R�[������B 
        this.orderInfo.validate();
        
        //�R)�@@�����`�F�b�N
        //this.�����.validate()���R�[������B 
        this.execInfo.validate();
        
        //�S)�@@�����R�[�h�iWEB3�j�`�F�b�N 
        //this.�����R�[�h�iWEB3�j==null�̏ꍇ�A��O���X���[����B
        if (this.productCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�����w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    public WEB3GenResponse createResponse()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
@