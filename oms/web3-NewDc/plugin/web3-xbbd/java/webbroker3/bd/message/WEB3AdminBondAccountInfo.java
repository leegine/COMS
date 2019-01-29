head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq���(WEB3AdminBondAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�ڋq���)<BR>
 * �ڋq���N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondAccountInfo  extends Message
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondAccountInfo.class);
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;
    
    /**
     * @@roseuid 44E336320148
     */
    public WEB3AdminBondAccountInfo() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P)�@@���X�R�[�h�`�F�b�N<BR>
     * �@@this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@this.���X�R�[�h���R���łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00834<BR>
     * <BR>
     * �@@this.���X�R�[�h�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01729<BR>
     * <BR>
     * �Q)�@@�ڋq�R�[�h�`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00835<BR>
     * <BR>
     * �@@this.�ڋq�R�[�h���U���łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00836<BR>
     * <BR>
     * �@@this.�ڋq�R�[�h�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01043
     * @@throws WEB3BaseException�@@
     * @@roseuid 44BDF629036B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
        //�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
        //�P)�@@���X�R�[�h�`�F�b�N<BR>
        //this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B 
        if (this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        
        //this.���X�R�[�h���R���łȂ��ꍇ�A��O���X���[����B
        if (this.branchCode.length() != 3)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }
        
        //this.���X�R�[�h�����l�łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.branchCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����l�ȊO�̒l�ł��B");
        }
        
        //�Q)�@@�ڋq�R�[�h�`�F�b�N
        //this.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B 
        if (this.accountCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł�");
        }
        
        //this.�ڋq�R�[�h���U���łȂ��ꍇ�A��O���X���[����B
        if (this.accountCode.length() != 6)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }
        
        //this.�ڋq�R�[�h�����l�łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.accountCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
