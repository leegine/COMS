head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ��ύX�������N�G�X�g(WEB3AdminBondExecChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Җ��ύX�������N�G�X�g)<BR>
 * �Ǘ��Җ��ύX�������N�G�X�g
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecChangeCompleteRequest extends WEB3AdminBondExecChangeCommonRequest 
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecChangeCompleteRequest.class);
    
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_change_complete";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (���͎�������)<BR>
     * ���͎�������
     */
    public Date inpOrderDate;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 44E3363402DE
     */
    public WEB3AdminBondExecChangeCompleteRequest() 
    {
     
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q)�@@���͎��������`�F�b�N <BR>
     * �@@this.���͎�������==null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00406<BR>
     * �R)��萔�ʃ`�F�b�N  <BR>
     * �@@this.�����.��萔�� == null�̏ꍇ�A��O���X���[����<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02634<BR>
     * <BR>
     * �S)���P���`�F�b�N  <BR>
     * �@@this.�����.���P�� == null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02021<BR>
     * <BR>
     * �T)�@@�Ïؔԍ��`�F�b�N <BR>
     * �@@this.�Ïؔԍ�==null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01090
     * @@throws WEB3BaseException
     * @@roseuid 44BDE11203E1
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
        //�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
        //�P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B
        super.validate();
        
        //�Q)�@@���͎��������`�F�b�N 
        //this.���͎�������==null�̏ꍇ�A��O���X���[����B 
        if (this.inpOrderDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����������w��ł��B");
        }
        
        // �R)��萔�ʃ`�F�b�N  
        // �@@this.�����.��萔�� == null�̏ꍇ�A��O���X���[����
        if (this.execInfo.execFaceAmount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02634,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z�����w��ł��B");
        }
        
        // �S)���P���`�F�b�N  
        // �@@this.�����.���P�� == null�̏ꍇ�A��O���X���[����B 
        if (this.execInfo.execPrice == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P���������͂ł��B");
        }
        
        //�T)�@@�Ïؔԍ��`�F�b�N 
        //this.�Ïؔԍ�==null�̏ꍇ�A��O���X���[����B 
        if (this.password == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ïؔԍ������w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �Ǘ��Җ��ύX�������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
     * @@return WEB3GenResponse
     * @@roseuid 44BEDBCF008C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondExecChangeCompleteResponse(this);
    }
}
@
