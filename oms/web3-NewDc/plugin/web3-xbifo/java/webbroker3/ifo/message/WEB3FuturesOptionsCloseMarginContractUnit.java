head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsCloseMarginContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʕԍώ��̏�����\��(WEB3FuturesOptionsCloseMarginContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�ԍό���)<BR>
 * ���ʕԍώ��̏�����\���N���X<BR>
 * @@author ���C�g
 * @@version 1.0 
 */
public class WEB3FuturesOptionsCloseMarginContractUnit extends Message
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOptionsCloseMarginContractUnit.class);
    /**
     * ���ʂh�c
     */
    public String id;
   
    /**
     * (����)<BR>
     * �ꊇ�ԍώ��Ɂu�����_�����[�h�v�̏ꍇ�ݒ�<BR>
     */
    public String contractOrderQuantity;
   
    /**
     * (���Ϗ���)<BR>
     * �ꊇ�ԍώ��Ɂu�����_�����[�h�v�̏ꍇ�ݒ�<BR>
     */
    public String settlePriority;
    
    /**
     * @@roseuid 40C0A8F302EE
     */
    public WEB3FuturesOptionsCloseMarginContractUnit() 
    {
    
    }
   
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j
     * 
     * �P�j�@@�h�c�`�F�b�N
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     * �Q�j�@@���ʃ`�F�b�N
     * �@@�Q�|�P�jthis.���Ϗ��ʂ�null�ȊO�̒l�ł���
     * �@@�@@�@@�@@this.���ʂ�null�̒l�ł���Ώꍇ��O���X���[����B
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00180<BR>
     * �@@�Q�|�Q�jthis.���Ϗ��ʂ�null�ȊO�̒l�ł���
     * �@@�@@�@@�@@this.���ʂ������ȊO�̒l�ł���Ώꍇ��O���X���[����B
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00901<BR>
     * �@@�Q�|�R�jthis.���Ϗ��ʂ�null�ȊO�̒l�ł���
     * �@@�@@�@@�@@this.���ʁ��O�̒l�ł���Ώꍇ��O���X���[����B
     * �@@�@@  class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00902<BR>
     * �R�j�@@���Ϗ��ʃ`�F�b�N
     * �@@�R�|�P�jthis.���Ϗ��ʂ�null�ȊO�̒l�ł���
     * �@@�@@�@@�@@this.���Ϗ��ʂ������ȊO�̒l�ł���Ώꍇ��O���X���[����B
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00329<BR>
     * �@@�R�|�Q�jthis.���Ϗ��ʂ�null�ȊO�̒l�ł���
     * �@@�@@�@@�@@this.���Ϗ��ʁ��O�̒l�ł���Ώꍇ��O���X���[����B
     * �@@�@@  class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00246<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407DF83A0075
     */
    public void validate() throws WEB3BaseException 
    {
        //�h�c�`�F�b�N
        log.debug("this.�h�c��null�̒l�ł���Η�O���X���[����B");
        log.debug("this.id = " + this.id);
        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "validate"
            );
        }
        log.debug("�h�c�`�F�b�N.....OK>>>>>");
        log.debug("this.���Ϗ��ʂ�null�ȊO�̒l�ł���this.���ʂ�null�̒l�ł���Ώꍇ��O���X���[����B");
        log.debug("this.settlePriority = " + this.settlePriority);
        log.debug("this.contractOrderQuantity = " + this.contractOrderQuantity);
        //���ʃ`�F�b�N(�Q�|�P)
        if(this.settlePriority != null && this.contractOrderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                getClass().getName() + "validate"
            );
        }
        log.debug("���ʃ`�F�b�N(1).....OK>>>>>");
        log.debug("this.���Ϗ��ʂ�null�ȊO�̒l�ł���this.���ʂ������ȊO�̒l�ł���Ώꍇ��O���X���[����");
        //���ʃ`�F�b�N(�Q�|�Q)
        if(this.settlePriority != null && !(WEB3StringTypeUtility.isNumber(this.contractOrderQuantity))) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901,
                getClass().getName() + "validate"
            );
        }
        log.debug("���ʃ`�F�b�N(2).....OK>>>>>");
        log.debug("this.���Ϗ��ʂ�null�ȊO�̒l�ł���this.���ʁ��O�̒l�ł���Ώꍇ��O���X���[����B");
        //���ʃ`�F�b�N(�Q�|�R)
        if(this.settlePriority != null && Long.parseLong(this.contractOrderQuantity) < 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00902,
                getClass().getName() + "validate"
            );
        }
        log.debug("���ʃ`�F�b�N(3).....OK>>>>>");
		log.debug("this.���Ϗ��ʂ�null�ȊO�̒l�ł���this.���Ϗ��ʂ������ȊO�̒l�ł���Ώꍇ��O���X���[����");
		//���ʃ`�F�b�N(�Q�|�Q)
		if(this.settlePriority != null && !(WEB3StringTypeUtility.isNumber(this.settlePriority))) 
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00329,
				getClass().getName() + "validate"
			);
		}
		log.debug("���Ϗ��ʃ`�F�b�N(1).....OK>>>>>");
		log.debug("this.���Ϗ��ʂ�null�ȊO�̒l�ł���this.���Ϗ��ʁ��O�̒l�ł���Ώꍇ��O���X���[����B");
		//���ʃ`�F�b�N(�Q�|�R)
		if(this.settlePriority != null && Long.parseLong(this.settlePriority) < 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00246,
				getClass().getName() + "validate"
			);
		}
		log.debug("���Ϗ��ʃ`�F�b�N(2).....OK>>>>>");
    }
}
@
